package br.com.accenture.apipessoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
