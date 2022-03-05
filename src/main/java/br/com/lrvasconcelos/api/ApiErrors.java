package br.com.lrvasconcelos.api;

import lombok.Getter;

import java.util.List;

public class ApiErrors {

    @Getter
    private final List<String> errors;

    public ApiErrors(String message) {
        this.errors = List.of(message);
    }

}
