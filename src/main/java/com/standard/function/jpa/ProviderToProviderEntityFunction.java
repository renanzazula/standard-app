package com.standard.function.jpa;

import com.standard.domain.Provider;
import com.standard.entity.ProviderEntity;

import java.util.function.Function;

public class ProviderToProviderEntityFunction implements Function<ProviderEntity, Provider> {

    @Override
    public Provider apply(ProviderEntity input) {
        Provider output = new Provider();
        if (input != null) {
            output.setId(input.getId());
            output.setName(input.getName());
            output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
            output.setDescription(input.getDescription());
        }
        return output;
    }

}
