package br.com.sicredi.election.annotation;

import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de eleitores",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = VoterResponse.class))),
        @ApiResponse(responseCode = "404", description = "Lista de vazia",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.LIST_ALL_VOTER_SUMMARY, description = Constants.LIST_ALL_VOTER_DESCRIPTION)
public @interface ListAllVoterCodeStandard {
}
