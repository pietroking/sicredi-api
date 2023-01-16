package br.com.sicredi.election.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@ToString
@Builder
@Setter
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_id_seq")
    @SequenceGenerator(name = "zone_id_seq", allocationSize = 1)
    @Column(name = "zone_id", nullable = false)
    private Long zoneId;

    @Column(name = "number")
    private Long number;

    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> listSession;

    public void addSession(Session session){
        if(listSession==null){
            listSession = new ArrayList<>();
        }
        listSession.add(session);
    }

    public void updateNumber(Long number){
        this.number = number;
    }
}
