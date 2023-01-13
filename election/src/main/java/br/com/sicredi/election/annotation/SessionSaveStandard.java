package br.com.sicredi.election.annotation;

import br.com.sicredi.election.core.dto.session.SessionResponse;
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
        @ApiResponse(responseCode = "201", description = "Seção criada",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = SessionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Seção já existente",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.SESSION_SAVE_SUMMARY, description = Constants.SESSION_SAVE_DESCRIPTION)
public @interface SessionSaveStandard {
}
