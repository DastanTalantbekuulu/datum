package kg.management.datum.domain.payload.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "RoleRequest", description = "Request payload for creating or updating a Role")
public record RoleRequest(

        @Schema(description = "Unique role name. Must be uppercase letters and underscores",
                example = "PROJECT_MANAGER",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Role name cannot be blank")
        @Size(max = 50, message = "Role name must be up to 50 characters long")
        @Pattern(regexp = "^[A-Z_]+$", message = "Role name must contain only uppercase letters and underscores")
        String role,

        @Schema(description = "Map for internationalization of the role's name. Must contain 'en', 'kg', and 'ru' keys.",
                example = "{\"en\": \"Project Manager\", \"kg\": \"Проект менеджер\", \"ru\": \"Менеджер проекта\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n,

        @Schema(description = "Map for internationalization of the role's description. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"A user who can manage projects\", \"kg\": \"Долбоорлорду башкара алган колдонуучу\", \"ru\": \"Пользователь, который может управлять проектами\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "description_i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> descriptionI18n
) {
}
