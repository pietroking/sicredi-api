package br.com.sicredi.election.core.dto.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SessionResponse {
    private Long sessionId;
    private Long zoneId;
    private Long number;
    private Long urnNumber;
}
