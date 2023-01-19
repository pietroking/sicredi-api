package br.com.sicredi.election.core.dto.candidate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CandidateRequest {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser nulo")
    private String name;
    @NotNull(message = "O cpf não pode ser nulo")
    @CPF(message = "O CPF está inválida")
    private String cpf;
    @NotNull(message = "O number não pode ser nulo")
    @PositiveOrZero
    private Long number;
    @NotNull(message = "O party não pode ser nulo")
    private String party;
}
