package br.com.sicredi.election.annotation;

import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
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
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de candidatos com o mesmo nome",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = CandidateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Candidato já existente",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "404", description = "Candidato não existe",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.CANDIDATE_VOTE_FIND_BY_NAME_SUMMARY, description = Constants.CANDIDATE_VOTE_FIND_BY_NAME_DESCRIPTION)
public @interface CandidateFindByNameCodeStandard {
}
