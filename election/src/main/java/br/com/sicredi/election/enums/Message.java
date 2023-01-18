package br.com.sicredi.election.enums;

import br.com.sicredi.election.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    ZONE_NUMBER_IS_PRESENT("Número da zona já existe.", HttpStatus.BAD_REQUEST),
    ZONE_IS_NOT_EXIST("A zona não existe.", HttpStatus.BAD_REQUEST),
    ZONE_LIST_IS_EMPTY("Não existem zonas criadas.", HttpStatus.BAD_REQUEST),
    SESSION_NUMBER_IS_PRESENT("Número da seção já existe.", HttpStatus.BAD_REQUEST),
    SESSION_URN_NUMBER_IS_PRESENT("Número da urna já existe.", HttpStatus.BAD_REQUEST),
    SESSION_IS_NOT_EXIST("A seção não existe.", HttpStatus.BAD_REQUEST),
    SESSION_LIST_IS_EMPTY("Não existem seções criadas.", HttpStatus.BAD_REQUEST),
    COLLABORATOR_CPF_IS_PRESENT("Colaborador com esse cpf já existe.", HttpStatus.BAD_REQUEST),
    COLLABORATOR_IS_NOT_EXIST("O colaborador não existe.", HttpStatus.BAD_REQUEST),
    COLLABORATOR_LIST_IS_EMPTY("Não existem colaboradores criados.", HttpStatus.BAD_REQUEST),
    VOTER_CPF_IS_PRESENT("Eleitor com esse cpf já existe.", HttpStatus.BAD_REQUEST),
    VOTER_IS_NOT_EXIST("O eleitor não existe.", HttpStatus.BAD_REQUEST),
    VOTER_LIST_IS_EMPTY("Não existem eleitores criados.", HttpStatus.BAD_REQUEST),
    VOTER_HAS_ALREADY_VOTED("Eleitor já votou.", HttpStatus.BAD_REQUEST),
    SESSION_VOTER_CPF_IS_NOT_PRESENT("CPF do eleitor não encontrado nessa sessão.",HttpStatus.BAD_REQUEST),
    CANDIDATE_CPF_IS_PRESENT("Candidato com esse cpf já existe.", HttpStatus.BAD_REQUEST),
    CANDIDATE_NOT_EXIST("Candidato com esse número não existe.", HttpStatus.BAD_REQUEST),
    CANDIDATE_CPF_IS_NOT_EXIST("Não existe candidato com esse cpf.", HttpStatus.BAD_REQUEST),
    CANDIDATE_LIST_IS_EMPTY("Não existem candidatos criados.", HttpStatus.BAD_REQUEST),
    CANDIDATE_COUNT_LIST_IS_EMPTY("Não existe resultado de votação criadas.", HttpStatus.BAD_REQUEST);

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(HttpStatus statusCode){
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .status(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
