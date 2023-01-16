package br.com.sicredi.election.core.dto.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoteResponse {
    private Long voteId;
    private Long number;
    private Long sessionId;
}
