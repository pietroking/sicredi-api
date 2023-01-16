package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_id_seq")
    @SequenceGenerator(name = "candidate_id_seq", allocationSize = 1)
    @Column(name = "candidate_id", nullable = false)
    private Long candidateId;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "number")
    private Long number;

    @Column(name = "party")
    private String party;

}
