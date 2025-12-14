package kg.management.common;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ICD10CodesManipulator {

    @Value("classpath:icd_10_v2019.xml")
    private Resource icdFileResource;

    private List<ICDNode> chapterList;
    private Map<String, ICDNode> codeToNode;

    @Getter
    private List<String> allCodesList;
    @Getter
    private List<String> allCodesListNoDots;

    private Map<String, Integer> codeToIndexMap;

    @PostConstruct
    public void init() {
        try (InputStream inputStream = icdFileResource.getInputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            parseDocument(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ICD-10 XML file", e);
        }
    }

    private void parseDocument(Document document) {
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        chapterList = new ArrayList<>();
        codeToNode = new HashMap<>();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                chapterList.add(new ICDNode((Element) node, null, codeToNode));
            }
        }

        allCodesList = new ArrayList<>();
        allCodesListNoDots = new ArrayList<>();
        codeToIndexMap = new HashMap<>();

        for (ICDNode chapter : chapterList) {
            populateFlatLists(chapter);
        }

        this.chapterList = Collections.unmodifiableList(chapterList);
        this.allCodesList = Collections.unmodifiableList(allCodesList);
        this.allCodesListNoDots = Collections.unmodifiableList(allCodesListNoDots);
    }

    private void populateFlatLists(ICDNode node) {
        String name = node.getName();
        allCodesList.add(name);

        String nameNoDot = (name.length() > 4 && name.charAt(3) == '.')
                ? name.replace(".", "")
                : name;
        allCodesListNoDots.add(nameNoDot);

        String codeWithDot = addDotToCode(name);
        codeToIndexMap.put(codeWithDot, allCodesList.size() - 1);

        for (ICDNode child : node.getChildren()) {
            populateFlatLists(child);
        }
    }

    private static class ICDNode {
        @Getter
        private String name;
        @Getter
        private String description;
        @Getter
        private final ICDNode parent;
        @Getter
        private final List<ICDNode> children;
        @Getter
        private final String type;

        private ICDNode(Element element, ICDNode parent, Map<String, ICDNode> registry) {
            this.parent = parent;
            this.type = element.getAttribute("type");
            this.children = new ArrayList<>();

            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) node;
                    String tag = childElement.getTagName();
                    switch (tag) {
                        case "item" -> this.children.add(new ICDNode(childElement, this, registry));
                        case "name" -> this.name = childElement.getTextContent();
                        case "description" -> this.description = childElement.getTextContent();
                    }
                }
            }
            if (this.name != null) {
                registry.put(this.name, this);
            }
        }
    }

    private String addDotToCode(String code) {
        if (code == null) return null;
        if (code.length() > 3 && code.charAt(3) != '.') {
            String candidate = code.substring(0, 3) + "." + code.substring(3);
            return codeToNode.containsKey(candidate) ? candidate : code;
        }
        return code;
    }

    private ICDNode getNode(String code) {
        if (code == null) return null;
        return codeToNode.get(addDotToCode(code));
    }

    private void validateCode(String code) {
        if (!isValidItem(code)) {
            throw new IllegalArgumentException("\"" + code + "\" is not a valid ICD-10 code.");
        }
    }

    public boolean isValidItem(String code) {
        return getNode(code) != null;
    }

    public boolean isChapter(String code) {
        ICDNode node = getNode(code);
        return node != null && "chapter".equals(node.getType());
    }

    public boolean isBlock(String code) {
        ICDNode node = getNode(code);
        return node != null && "block".equals(node.getType());
    }

    public boolean isCategory(String code) {
        ICDNode node = getNode(code);
        return node != null && "category".equals(node.getType());
    }

    public boolean isSubcategory(String code) {
        ICDNode node = getNode(code);
        return node != null && "subcategory".equals(node.getType());
    }

    public boolean isChapterOrBlock(String code) {
        return isChapter(code) || isBlock(code);
    }

    public boolean isCategoryOrSubcategory(String code) {
        return isCategory(code) || isSubcategory(code);
    }

    public String getDescription(String code) {
        validateCode(code);
        return getNode(code).getDescription();
    }

    public String getParent(String code) {
        validateCode(code);
        ICDNode parent = getNode(code).getParent();
        return parent == null ? "" : parent.getName();
    }

    public List<String> getChildren(String code) {
        validateCode(code);
        return getNode(code).getChildren().stream()
                .map(ICDNode::getName)
                .collect(Collectors.toList());
    }

    public boolean isLeaf(String code) {
        validateCode(code);
        return getNode(code).getChildren().isEmpty();
    }

    public List<String> getAncestors(String code) {
        validateCode(code);
        List<String> result = new ArrayList<>();
        ICDNode node = getNode(code).getParent();
        while (node != null) {
            result.add(node.getName());
            node = node.getParent();
        }
        return result;
    }

    public List<String> descendants(String code) {
        validateCode(code);
        List<String> result = new ArrayList<>();
        collectDescendants(getNode(code), result);
        return result;
    }

    private void collectDescendants(ICDNode node, List<String> result) {
        for (ICDNode child : node.getChildren()) {
            result.add(child.getName());
            collectDescendants(child, result);
        }
    }

    public boolean isAncestor(String ancestor, String descendant) {
        validateCode(ancestor);
        if (!isValidItem(descendant)) return false;

        ICDNode node = getNode(descendant);
        String ancestorCode = addDotToCode(ancestor);

        while (node.getParent() != null) {
            if (node.getParent().getName().equals(ancestorCode)) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    public boolean isDescendant(String descendant, String ancestor) {
        return isAncestor(ancestor, descendant);
    }

    public String getNearestCommonAncestor(String codeA, String codeB) {
        if (!isValidItem(codeA) || !isValidItem(codeB)) {
            return "";
        }

        Set<String> ancestorsA = new HashSet<>();
        ICDNode nodeA = getNode(codeA);
        while (nodeA != null) {
            ancestorsA.add(nodeA.getName());
            nodeA = nodeA.getParent();
        }

        ICDNode nodeB = getNode(codeB);
        while (nodeB != null) {
            if (ancestorsA.contains(nodeB.getName())) {
                return nodeB.getName();
            }
            nodeB = nodeB.getParent();
        }

        return "";
    }

    public List<String> getAllCodes(boolean withDots) {
        return withDots ? allCodesList : allCodesListNoDots;
    }

    public int getIndex(String code) {
        validateCode(code);
        String cleanCode = addDotToCode(code);
        Integer index = codeToIndexMap.get(cleanCode);
        if (index == null) {
            return allCodesList.indexOf(cleanCode);
        }
        return index;
    }

    public String removeDot(String code) {
        return allCodesListNoDots.get(getIndex(code));
    }

    public String addDot(String code) {
        return allCodesList.get(getIndex(code));
    }
}