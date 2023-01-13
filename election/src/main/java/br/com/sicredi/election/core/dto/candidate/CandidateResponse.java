package br.com.sicredi.election.core.dto.candidate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CandidateResponse {
    private Long id;
    private String nome;
    private Long cpf;
    private Long number;
    private String party;
}
