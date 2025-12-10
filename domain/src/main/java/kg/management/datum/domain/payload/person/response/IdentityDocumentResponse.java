package kg.management.datum.domain.payload.person.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import kg.management.datum.domain.payload.dictionary.response.GenderResponse;
import kg.management.datum.domain.payload.geo.response.CountryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "IdentityDocumentResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentityDocumentResponse extends AbstractFullResponse<Long> {

    private PersonResponse person;

    private String documentType; // P, I, V

    private String docNumber;

    private String serial;

    private String authority;

    private LocalDate issueDate;

    private CountryResponse issuingCountry;

    private String mrzString1;

    private String mrzString2;

    private String mrzString3;

    private String mrzSurname;

    private String mrzGivenNames;

    private String mrzDocNumber;

    private LocalDate mrzBirthDate;

    private LocalDate mrzExpiryDate;

    private String mrzPersonalNumber;

    private GenderResponse mrzSex;

    private CountryResponse mrzNationality;

    private boolean primary;

    @Builder.Default
    private boolean active = true;
}