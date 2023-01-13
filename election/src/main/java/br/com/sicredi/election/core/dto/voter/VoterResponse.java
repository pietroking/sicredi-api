package br.com.sicredi.election.core.dto.voter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoterResponse {
    private Long id;
    private String nome;
    private Long idSession;
    private Long cpf;
    private Boolean statusVote;
}
