package kg.management.datum.domain.payload.person.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import kg.management.datum.domain.payload.dictionary.response.PhotoTypeResponse;
import kg.management.datum.domain.payload.storage.response.StorageFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PersonPhotoResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonPhotoResponse extends AbstractFullResponse<Long> {

    private PersonResponse person;

    private StorageFileResponse storageFile;

    private PhotoTypeResponse type;

    private boolean primary;
}