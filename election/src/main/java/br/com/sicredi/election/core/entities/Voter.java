package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voter_id_seq")
    @SequenceGenerator(name = "voter_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "idsession")
    private Long idSession;

    @Column(name = "status_vote")
    private Boolean statusVote;
}
