package br.com.sicredi.election.core.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteRequest {
    @NotNull(message = "O number não pode ser nulo")
    private Long number;
    @NotNull(message = "O sessionId não pode ser nulo")
    private Long sessionId;
    @NotNull(message = "O cpf não pode ser nulo")
    private String cpf;
}
