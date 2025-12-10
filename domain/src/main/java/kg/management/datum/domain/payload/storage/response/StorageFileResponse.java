package kg.management.datum.domain.payload.storage.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "StorageFileResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorageFileResponse extends AbstractFullResponse<Long> {

    private String originalName;

    private String mimeType;

    private Long size;

    private String bucket;

    private String path;

    private boolean isPublic;
}