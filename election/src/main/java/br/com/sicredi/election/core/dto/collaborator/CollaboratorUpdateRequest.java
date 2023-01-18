package br.com.sicredi.election.core.dto.collaborator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CollaboratorUpdateRequest {
    @NotNull(message = "O sessionId não pode ser nulo")
    private Long sessionId;
}
