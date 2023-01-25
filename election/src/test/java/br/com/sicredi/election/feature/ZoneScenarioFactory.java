package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.zone.ZoneRequest;
import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.entities.Zone;

import java.util.ArrayList;
import java.util.List;

public class ZoneScenarioFactory {
    public static final List<Zone> LIST_ZONE = loadListZone();
    public static final List<ZoneResponse> LIST_ZONE_RESPONSE = loadListZoneResponse();
    public static final ZoneRequest PAYLOAD_REQUEST_13 = loadZoneRequest();
    public static final Zone PAYLOAD_ZONE_13 = loadZone13();
    public static final ZoneResponse PAYLOAD_ZONE_13_RESPONSE = loadZone13Response();
    public static final ZoneRequest PAYLOAD_REQUEST_17 = loadZone17Request();

    private static ZoneRequest loadZone17Request() {
        ZoneRequest zoneRequest = new ZoneRequest();
        zoneRequest.setNumber(17L);
        return zoneRequest;
    }

    private static ZoneResponse loadZone13Response() {
        ZoneResponse zone13 = new ZoneResponse();
        zone13.setZoneId(1L);
        zone13.setNumber(13L);
        return zone13;
    }

    private static Zone loadZone13() {
        return Zone.builder().zoneId(1L).number(13L).build();
    }

    private static ZoneRequest loadZoneRequest() {
        ZoneRequest zoneRequest = new ZoneRequest();
        zoneRequest.setNumber(13L);
        return zoneRequest;
    }

    private static List<ZoneResponse> loadListZoneResponse() {
        ZoneResponse zone13 = new ZoneResponse();
        ZoneResponse zone17 = new ZoneResponse();
        zone13.setZoneId(1L);
        zone13.setNumber(13L);
        zone17.setZoneId(2L);
        zone17.setNumber(17L);
        List<ZoneResponse> zoneResponseList = new ArrayList<>();
        zoneResponseList.add(zone13);
        zoneResponseList.add(zone17);
        return zoneResponseList;
    }

    private static List<Zone> loadListZone() {
        Zone zone13 = Zone.builder().zoneId(1L).number(13L).build();
        Zone zone17 = Zone.builder().zoneId(2L).number(17L).build();
        List<Zone> zoneList = new ArrayList<>();
        zoneList.add(zone13);
        zoneList.add(zone17);
        return zoneList;
    }
}
