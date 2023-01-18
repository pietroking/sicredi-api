package br.com.sicredi.election.core.entities;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorUpdateRequest;
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
    @Column(name = "collaborator_id", nullable = false)
    private Long collaboratorId;

    @Column(name = "name")
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private Session session;

    @Column(name = "cpf")
    private String cpf;

    public void update(Session session){
        this.session = session;
    }
}
