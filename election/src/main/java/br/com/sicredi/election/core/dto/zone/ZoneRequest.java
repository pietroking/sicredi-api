package br.com.sicredi.election.core.dto.zone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ZoneRequest {
    @NotNull(message = "O number não pode ser nulo")
    private Long number;
}
