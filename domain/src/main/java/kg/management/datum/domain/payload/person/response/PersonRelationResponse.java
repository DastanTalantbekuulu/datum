package kg.management.datum.domain.payload.person.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import kg.management.datum.domain.payload.dictionary.response.RelationTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PersonRelationResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonRelationResponse extends AbstractFullResponse<Long> {

    private PersonResponse person;

    private PersonResponse relative;

    private RelationTypeResponse relationType;

    @Builder.Default
    private Boolean biological = true;
}