package br.com.sicredi.election.core.dto.session;

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
public class SessionUpdateRequest {
    @NotNull(message = "O urnNumber não pode ser nulo")
    @PositiveOrZero
    private Long urnNumber;
}
