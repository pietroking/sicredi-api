package br.com.sicredi.election.core.dto.zone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ZoneRequest {
    @NotNull(message = "O number não pode ser nulo")
    @PositiveOrZero(message = "O number não pode ser negativo")
    private Long number;
}
