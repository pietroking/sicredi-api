package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.entities.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionScenarioFactory {
    public static final List<Session> LIST_SESSION = loadListSession();
    public static final List<SessionResponse> LIST_SESSION_RESPONSE = loadListSessionResponse();

    private static List<Session> loadListSession(){
        Session session1 = Session.builder().sessionId(1L).number(1L).zone(ZoneScenarioFactory.PAYLOAD_ZONE_13).build();
        Session session2 = Session.builder().sessionId(2L).number(2L).zone(ZoneScenarioFactory.PAYLOAD_ZONE_13).build();
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        return sessionList;
    }

    private static List<SessionResponse> loadListSessionResponse(){
        SessionResponse session1 = new SessionResponse();
        SessionResponse session2 = new SessionResponse();
        session1.setSessionId(1L);
        session1.setNumber(1L);
        session1.setZoneId(1L);
        session2.setSessionId(2L);
        session2.setNumber(2L);
        session2.setZoneId(1L);
        List<SessionResponse> sessionResponseList = new ArrayList<>();
        sessionResponseList.add(session1);
        sessionResponseList.add(session2);
        return sessionResponseList;
    }
}
