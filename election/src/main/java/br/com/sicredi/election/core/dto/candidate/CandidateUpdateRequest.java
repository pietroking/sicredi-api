package br.com.sicredi.election.core.dto.candidate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CandidateUpdateRequest {
    @NotNull(message = "O number não pode ser nulo")
    @PositiveOrZero
    private Long number;
    @NotNull(message = "O party não pode ser nulo")
    private String party;
}
