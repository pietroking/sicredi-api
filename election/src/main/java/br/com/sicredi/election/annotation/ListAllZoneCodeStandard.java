package br.com.sicredi.election.annotation;

import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import java.lang.annotation.Target;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de zonas para votação",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ZoneResponse.class))),
        @ApiResponse(responseCode = "404", description = "Lista de vazia",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.LIST_ALL_ZONE_SUMMARY, description = Constants.LIST_ALL_ZONE_DESCRIPTION)
public @interface ListAllZoneCodeStandard {
}
