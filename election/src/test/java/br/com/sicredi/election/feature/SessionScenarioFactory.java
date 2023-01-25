package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.dto.session.SessionUpdateRequest;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Voter;

import java.util.ArrayList;
import java.util.List;

public class SessionScenarioFactory {
    public static final List<Session> LIST_SESSION = loadListSession();
    public static final List<SessionResponse> LIST_SESSION_RESPONSE = loadListSessionResponse();
    public static final Session SESSION_PAYLOAD = loadSession1();
    public static final SessionResponse SESSION_PAYLOAD_RESPONSE = loadSession1Response();
    public static final SessionRequest SESSION_PAYLOAD_REQUEST = loadSession1Request();
    public static final SessionUpdateRequest SESSION_UPDATE_REQUEST = loadSessionUpdate();

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

    private static Session loadSession1(){
        List<Voter> list = new ArrayList<>();
        list.add(VoterScenarioFactory.VOTER);
        return Session.builder().sessionId(1L).number(1L).zone(ZoneScenarioFactory.PAYLOAD_ZONE_13).urnNumber(1L).listVoter(list).listCollaborator(new ArrayList<>()).build();
    }

    private static SessionResponse loadSession1Response(){
        SessionResponse sessionResponse1 = new SessionResponse();
        sessionResponse1.setSessionId(1L);
        sessionResponse1.setNumber(1L);
        sessionResponse1.setZoneId(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId());
        return sessionResponse1;
    }

    private static SessionRequest loadSession1Request(){
        SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.setNumber(1L);
        return sessionRequest;
    }

    private static SessionUpdateRequest loadSessionUpdate(){
        SessionUpdateRequest sessionRequest = new SessionUpdateRequest();
        sessionRequest.setUrnNumber(1L);
        return sessionRequest;
    }
}
