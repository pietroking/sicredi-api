package br.com.sicredi.election.core.dto.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SessionResponse {
    private Long id;
    private Long idZone;
    private Long number;
}
