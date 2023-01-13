package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "collaborator")
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collaborator_id_seq")
    @SequenceGenerator(name = "collaborator_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "idsession")
    private Long idSession;

    @Column(name = "cpf")
    private Long cpf;
}
