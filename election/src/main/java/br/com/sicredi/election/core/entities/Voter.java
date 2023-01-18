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
    @Column(name = "voter_id", nullable = false)
    private Long voterId;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private Session session;

    @Column(name = "status_vote")
    private Boolean statusVote;

    @PrePersist
    private void prePersist(){
        this.statusVote=Boolean.FALSE;
    }

    public void update(Session session){this.session = session;}
}
