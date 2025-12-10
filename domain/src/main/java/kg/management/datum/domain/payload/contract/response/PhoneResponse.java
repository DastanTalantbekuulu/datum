package kg.management.datum.domain.payload.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullTimeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PhoneResponse")
public class PhoneResponse  extends AbstractFullTimeResponse<Long> {

        private String number;

        private boolean hasWhatsapp;

        private boolean hasTelegram;

}