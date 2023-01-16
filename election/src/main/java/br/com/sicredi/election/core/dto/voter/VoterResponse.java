package br.com.sicredi.election.core.dto.voter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoterResponse {
    private Long voterId;
    private String name;
    private Long sessionId;
    private String cpf;
    private Boolean statusVote=false;
}
