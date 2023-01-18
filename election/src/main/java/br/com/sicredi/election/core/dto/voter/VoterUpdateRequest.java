package br.com.sicredi.election.core.dto.voter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoterUpdateRequest {
    @NotNull(message = "O sessionId não pode ser nulo")
    private Long sessionId;
}
