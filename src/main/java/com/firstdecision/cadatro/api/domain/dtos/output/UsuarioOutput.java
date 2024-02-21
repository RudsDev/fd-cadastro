package com.firstdecision.cadatro.api.domain.dtos.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsuarioOutput(
    String nome,
    String email
) {}
