package br.com.sicredi.election.service;

import br.com.sicredi.election.core.mapper.CandidateMapper;
import br.com.sicredi.election.repository.CandidateRepository;
import br.com.sicredi.election.repository.SessionRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {
    private CandidateRepository candidateRepository;
    private CandidateMapper candidateMapper;
    private CandidateService candidateService;
}
