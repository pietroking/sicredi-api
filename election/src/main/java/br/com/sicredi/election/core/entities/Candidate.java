package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "number")
    private Long number;

    @Column(name = "party")
    private String party;
}
