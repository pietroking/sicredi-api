package br.com.sicredi.election.core.dto.voter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoterRequest {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser nulo")
    private String name;
    @NotNull(message = "O sessionId não pode ser nulo")
    private Long sessionId;
    @NotNull(message = "O cpf não pode ser nulo")
    @CPF(message = "O CPF está inválido")
    private String cpf;
}
