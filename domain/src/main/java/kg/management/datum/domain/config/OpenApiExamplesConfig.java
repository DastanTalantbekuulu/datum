package kg.management.datum.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springdoc.core.customizers.OpenApiBuilderCustomizer;
import org.springdoc.core.service.OpenAPIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Configuration
public class OpenApiExamplesConfig {

    private static final String EXAMPLES_PATH = "openapi/examples/";

    @Bean
    public OpenApiBuilderCustomizer openApiBuilderCustomizer() {
        return (OpenAPIService openApiService) -> {
                OpenAPI openApi = tryGetOpenApi(openApiService);
                if (openApi == null || openApi.getPaths() == null) return;

                openApi.getPaths().forEach((_, pathItem) ->
                        pathItem.readOperations().forEach(operation -> {
                            Optional.ofNullable(operation.getRequestBody())
                                    .map(RequestBody::getContent)
                                    .ifPresent(this::applyExamplesToContent);
                            Optional.ofNullable(operation.getResponses())
                                    .ifPresent(responses -> responses.forEach((_, apiResponse) -> {
                                        if (apiResponse.getContent() != null) {
                                            applyExamplesToContent(apiResponse.getContent());
                                        }
                                    }));
                        })
                );
                trySetCachedOpenApi(openApiService, openApi, Locale.getDefault());
        };
    }

    private void applyExamplesToContent(Content content) {
        if (content == null) return;

        for (Map.Entry<String, MediaType> e : content.entrySet()) {
            MediaType mediaType = e.getValue();
            if (mediaType == null || mediaType.getSchema() == null) continue;

            String ref = mediaType.getSchema().get$ref();
            if (ref == null || ref.isBlank()) continue;

            String name = ref.substring(ref.lastIndexOf('/') + 1);

            String valid = readResource(EXAMPLES_PATH + name + ".valid.json");
            String invalid = readResource(EXAMPLES_PATH + name + ".invalid.json");

            if (valid == null && invalid == null) continue;

            Map<String, Example> examples = mediaType.getExamples();
            if (examples == null) {
                examples = new java.util.LinkedHashMap<>();
                mediaType.setExamples(examples);
            }

            if (valid != null) {
                Example ex = new Example();
                ex.setSummary("Valid example");
                ex.setDescription("Auto-loaded valid example");
                ex.setValue(valid);
                examples.put("valid", ex);
            }
            if (invalid != null) {
                Example ex = new Example();
                ex.setSummary("Invalid example");
                ex.setDescription("Auto-loaded invalid example");
                ex.setValue(invalid);
                examples.put("invalid", ex);
            }
        }
    }

    private String readResource(String path) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (is == null) return null;
            return new String(is.readAllBytes());
        } catch (Exception _) {
            return null;
        }
    }

    private OpenAPI tryGetOpenApi(OpenAPIService svc) {
        try {
            try {
                var m = svc.getClass().getMethod("getCalculatedOpenAPI");
                return (OpenAPI) m.invoke(svc);
            } catch (NoSuchMethodException _) {
            }

            try {
                var m = svc.getClass().getMethod("getCachedOpenAPI");
                return (OpenAPI) m.invoke(svc);
            } catch (NoSuchMethodException _) {
            }

            try {
                var m = svc.getClass().getMethod("getOpenAPI");
                return (OpenAPI) m.invoke(svc);
            } catch (NoSuchMethodException _) {
            }

            try {
                var m = svc.getClass().getMethod("getOpenApi", Locale.class);
                return (OpenAPI) m.invoke(svc, Locale.getDefault());
            } catch (NoSuchMethodException _) {
            }
        } catch (Exception _) {
        }
        return null;
    }

    private void trySetCachedOpenApi(OpenAPIService svc, OpenAPI openApi, Locale locale) {
        try {
            var m = svc.getClass().getMethod("setCachedOpenAPI", OpenAPI.class, Locale.class);
            m.invoke(svc, openApi, locale);
        } catch (NoSuchMethodException _) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
