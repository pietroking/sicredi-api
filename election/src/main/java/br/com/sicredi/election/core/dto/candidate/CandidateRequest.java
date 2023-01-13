package br.com.sicredi.election.core.dto.candidate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CandidateRequest {
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    @NotNull(message = "O cpf não pode ser nulo")
    private Long cpf;
    @NotNull(message = "O number não pode ser nulo")
    private Long number;
    @NotNull(message = "O party não pode ser nulo")
    private String party;
}
