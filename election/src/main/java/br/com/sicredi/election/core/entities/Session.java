package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id_seq")
    @SequenceGenerator(name = "session_id_seq", allocationSize = 1)
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
    private Zone zone;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Collaborator> listCollaborator=new ArrayList<>();

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voter> listVoter= new ArrayList<>();

    @Column(name = "number")
    private Long number;

    @Column(name = "urn_number")
    private Long urnNumber;

    public void addCollaborator(Collaborator collaborator){
        this.listCollaborator.add(collaborator);
    }
    public void addListVoter(Voter voter){
        this.listVoter.add(voter);
    }
    public void deleteCollaborator(Collaborator collaborator){this.listCollaborator.remove(collaborator);
    collaborator.setSession(null);}
    public void updateUrnNumber(Long urnNumber){this.urnNumber = urnNumber;}
}
