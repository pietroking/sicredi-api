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
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_id_seq")
    @SequenceGenerator(name = "vote_id_seq", allocationSize = 1)
    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @Column(name = "number")
    private Long number;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name="vote_session",joinColumns = @JoinColumn(name="vote_id"), inverseJoinColumns = @JoinColumn(name="session_id"))
    private List<Session> session;

    public void addSession(Session session){
        if(this.session==null){
            this.session= new ArrayList<>();
        }
        this.session.add(session);
    }
}
