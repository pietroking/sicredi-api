package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.dto.voter.VoterUpdateRequest;
import br.com.sicredi.election.core.entities.Voter;

import java.util.ArrayList;
import java.util.List;

public class VoterScenarioFactory {
    public static final List<Voter> LIST_VOTER = listVoter();
    public static final List<VoterResponse> LIST_VOTER_RESPONSE = listVoterResponse();
    public static final Voter VOTER = createVoter();
    public static final VoterResponse VOTER_RESPONSE = createVoterResponse();
    public static final VoterRequest VOTER_REQUEST = createVoterRequest();
    public static final VoterUpdateRequest VOTER_UPDATE_REQUEST = voterUpdateRequest();

    private static List<Voter> listVoter() {
        Voter voter = Voter.builder().voterId(1L).name("Fulano").session(SessionScenarioFactory.SESSION_PAYLOAD).cpf("94123018041").build();
        List<Voter> list = new ArrayList<>();
        list.add(voter);
        return list;
    }

    private static List<VoterResponse> listVoterResponse() {
        VoterResponse voterResponse = new VoterResponse();
        voterResponse.setVoterId(1L);
        voterResponse.setName("Fulano");
        voterResponse.setSessionId(1L);
        voterResponse.setCpf("94123018041");
        List<VoterResponse> list = new ArrayList<>();
        list.add(voterResponse);
        return list;
    }

    private static Voter createVoter() {
        return Voter.builder().voterId(1L).name("Fulano").session(SessionScenarioFactory.SESSION_PAYLOAD).cpf("94123018041").statusVote(false).build();
    }

    private static VoterResponse createVoterResponse() {
        VoterResponse voterResponse = new VoterResponse();
        voterResponse.setVoterId(1L);
        voterResponse.setName("Fulano");
        voterResponse.setSessionId(1L);
        voterResponse.setCpf("94123018041");
        return voterResponse;
    }

    private static VoterRequest createVoterRequest() {
        VoterRequest voterRequest = new VoterRequest();
        voterRequest.setName("Fulano");
        voterRequest.setSessionId(1L);
        voterRequest.setCpf("94123018041");
        return voterRequest;
    }

    private static VoterUpdateRequest voterUpdateRequest() {
        VoterUpdateRequest voterUpdateRequest = new VoterUpdateRequest();
        voterUpdateRequest.setSessionId(1L);
        return voterUpdateRequest;
    }
}
