package br.com.sicredi.election.core.dto.collaborator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CollaboratorRequest {
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;
    @NotNull(message = "O sessionId não pode ser nulo")
    private Long sessionId;
    @NotNull(message = "O cpf não pode ser nulo")
    @CPF(message = "O CPF está inválida")
    private String cpf;
}
