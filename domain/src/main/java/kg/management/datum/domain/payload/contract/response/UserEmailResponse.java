package kg.management.datum.domain.payload.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.dictionary.response.ContactTypeResponse;
import kg.management.datum.domain.payload.user.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserEmailResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEmailResponse {
    private UserResponse user;

    private EmailResponse email;

    private ContactTypeResponse type;

    private boolean primary;

    private boolean verified;

    private Instant createdAt;

    private Instant updatedAt;
}