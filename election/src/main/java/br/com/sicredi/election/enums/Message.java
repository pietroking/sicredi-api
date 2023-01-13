package br.com.sicredi.election.enums;

import br.com.sicredi.election.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    ZONE_NUMBER_IS_PRESENT("Número da zona já existe", HttpStatus.BAD_REQUEST),
    ZONE_IS_NOT_EXIST("A zona não existe", HttpStatus.BAD_REQUEST),
    SESSION_NUMBER_IS_PRESENT("Número da seção já existe", HttpStatus.BAD_REQUEST),
    SESSION_IS_NOT_EXIST("A seção não existe", HttpStatus.BAD_REQUEST),
    COLLABORATOR_CPF_IS_PRESENT("Colaborador com esse cpf já existe.", HttpStatus.BAD_REQUEST),
    COLLABORATOR_CPF_IS_NOT_EXIST("Não existe colaborador com esse cpf.", HttpStatus.BAD_REQUEST);

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
