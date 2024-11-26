package com.two_weeks_backend.two_weeks_backend.utils.translator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Translator {
    private List<Idiom> values = Arrays.asList(
            new Idiom("user", "usuario"),
            new Idiom("password", "contraseña"));
    private String notFound = "Not found";

    public String getEs(String en) {
        String returnedEs = this.notFound;
        Optional<Idiom> found = this.values
                .stream()
                .filter(value -> value.getEn().equals(en))
                .findFirst();
        if (found.isPresent()) {
            returnedEs = found.get().getEs();
        }
        return returnedEs;
    }
}
