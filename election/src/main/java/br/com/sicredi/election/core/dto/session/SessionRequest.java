package br.com.sicredi.election.core.dto.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionRequest {
    @NotNull(message = "O idZone não pode ser nulo")
    private Long idZone;
    @NotNull(message = "O number não pode ser nulo")
    private Long number;
}
