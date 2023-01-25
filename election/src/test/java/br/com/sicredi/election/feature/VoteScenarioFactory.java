package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.vote.VoteRequest;
import br.com.sicredi.election.core.dto.vote.VoteResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteScenarioFactory {
    public static final Vote VOTE = createVote();
    public static final VoteResponse VOTE_RESPONSE = createVoteResponse();
    public static final VoteRequest VOTE_REQUEST = createVoteRequest();

    private static VoteRequest createVoteRequest() {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setSessionId(SessionScenarioFactory.SESSION_PAYLOAD.getSessionId());
        voteRequest.setNumber(CandidateScenarioFactory.CANDIDATE.getNumber());
        voteRequest.setCpf(VoterScenarioFactory.VOTER.getCpf());
        return voteRequest;
    }

    private static VoteResponse createVoteResponse() {
        VoteResponse voteResponse = new VoteResponse();
        voteResponse.setVoteId(1L);
        voteResponse.setNumber(CandidateScenarioFactory.CANDIDATE.getNumber());
        voteResponse.setSessionId(SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getSessionId());
        return voteResponse;
    }

    private static Vote createVote() {
        List<Session> list = new ArrayList<>();
        list.add(SessionScenarioFactory.SESSION_PAYLOAD);
        return Vote.builder().voteId(1L).number(CandidateScenarioFactory.CANDIDATE.getNumber()).session(list).build();
    }
}
