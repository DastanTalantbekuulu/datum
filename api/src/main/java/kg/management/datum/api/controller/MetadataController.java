//package kg.management.datum.api.controller;
//
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import kg.management.datum.domain.entity.user.Role;
//import kg.management.datum.domain.entity.user.User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import support.model.EntityConfig;
//import support.model.FieldConfig;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class MetadataController {
//
//    private final Map<String, Class<?>> managedEntities = Map.of("user", User.class, "role", Role.class);
//
//    @GetMapping("/metadata")
//    public Map<String, EntityConfig> getMetadata() {
//        Map<String, EntityConfig> metadata = new HashMap<>();
//        managedEntities.forEach((resourceName, clazz) -> metadata.put(resourceName, generateConfig(resourceName, clazz)));
//        return metadata;
//    }
//
//    private EntityConfig generateConfig(String resourceName, Class<?> clazz) {
//        List<FieldConfig> fields = new ArrayList<>();
//
//        for (Field field : clazz.getDeclaredFields()) {
//            FieldConfig.FieldConfigBuilder config = FieldConfig.builder();
//            config.name(field.getName())
//                    .label(capitalize(field.getName()))
//                    .type(determineType(field));
//
//            if (field.isAnnotationPresent(Id.class)) {
//                config.readOnly(true);
//            }
//            if (field.isAnnotationPresent(ManyToMany.class) || field.isAnnotationPresent(OneToMany.class)) {
//                continue;
//            }
//
//            fields.add(config.build());
//        }
//
//        return new EntityConfig(resourceName, capitalize(resourceName), fields);
//    }
//
//    private String determineType(Field field) {
//        Class<?> type = field.getType();
//        if (type.equals(Long.class) || type.equals(Integer.class)) return "number";
//        if (type.equals(LocalDate.class) || type.equals(Date.class)) return "date";
//        if (type.equals(Boolean.class)) return "boolean";
//        return "text";
//    }
//
//    private String capitalize(String str) {
//        if (str == null || str.isEmpty()) return str;
//        return str.substring(0, 1).toUpperCase() + str.substring(1);
//    }
//}