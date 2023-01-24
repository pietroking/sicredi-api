package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateUpdateRequest;
import br.com.sicredi.election.core.entities.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateScenarioFactory {

    public static final List<Candidate> LIST_CANDIDATE = listCandidate();
    public static final List<CandidateResponse> LIST_CANDIDATE_RESPONSE = listCandidateRespose();
    public static final Candidate CANDIDATE = createCandidate();
    public static final CandidateResponse CANDIDATE_RESPONSE = createCandidateResponse();
    public static final CandidateRequest CANDIDATE_REQUEST = createCandidateRequest();
    public static final List<CandidateResultResponse> LIST_CANDIDATE_RESULT_RESPONSE= createListCandidateResultResponse();

    private static List<CandidateResultResponse> createListCandidateResultResponse() {
        CandidateResultResponse candidateResultResponse = new CandidateResultResponse("Fulano",12L);
        List<CandidateResultResponse> list = new ArrayList<>();
        list.add(candidateResultResponse);
        return list;
    }

    public static final CandidateUpdateRequest CANDIDATE_UPDATE_REQUEST = createUpdateRequest();

    private static CandidateUpdateRequest createUpdateRequest() {
        CandidateUpdateRequest candidateUpdateRequest = new CandidateUpdateRequest();
        candidateUpdateRequest.setNumber(334L);
        candidateUpdateRequest.setParty("SSL");
        return candidateUpdateRequest;
    }

    private static CandidateRequest createCandidateRequest() {
        CandidateRequest candidateRequest = new CandidateRequest();
        candidateRequest.setCpf("94123018041");
        candidateRequest.setParty("SSL");
        candidateRequest.setName("Fulano");
        candidateRequest.setNumber(334L);
        return candidateRequest;
    }

    private static CandidateResponse createCandidateResponse() {
        CandidateResponse candidate = new CandidateResponse();
        candidate.setCandidateId(1L);
        candidate.setNumber(334L);
        candidate.setName("Fulano");
        candidate.setParty("SSL");
        candidate.setCpf("94123018041");
        return candidate;
    }

    private static Candidate createCandidate() {
        return Candidate.builder().candidateId(1L).cpf("94123018041").name("Fulano").party("SSL").number(334L).build();
    }

    private static List<Candidate> listCandidate() {
        Candidate candidate = Candidate.builder().candidateId(1L).cpf("94123018041").name("Fulano").party("SSL").number(334L).build();
        List<Candidate> list= new ArrayList<>();
        list.add(candidate);
        return list;
    }

    private static List<CandidateResponse> listCandidateRespose() {
        CandidateResponse candidate = new CandidateResponse();
        candidate.setCandidateId(1L);
        candidate.setNumber(334L);
        candidate.setName("Fulano");
        candidate.setParty("SSL");
        candidate.setCpf("94123018041");
        List<CandidateResponse> list= new ArrayList<>();
        list.add(candidate);
        return list;
    }
}
