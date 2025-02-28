package com.standard.function.jpa;

import com.standard.domain.Withdrawal;
import com.standard.entity.WithdrawalEntity;
import com.standard.function.JpaFunctions;
import com.standard.util.DateTimeConverter;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class WithdrawalEntityToWithdrawalFunction implements java.util.function.Function<WithdrawalEntity, Withdrawal> {

    private final DateTimeConverter dateTimeConverter;

    @Override
    public Withdrawal apply(WithdrawalEntity input) {
        Withdrawal output = new Withdrawal();
        if (input != null) {
            output.setId(input.getId());
            output.setDescription(input.getDescription());
            output.setAmount(input.getAmount());
            output.setCreationDate(dateTimeConverter.convert(input.getCreationDate()));
            output.setCreationTime(dateTimeConverter.convert(input.getCreationTime()));
            Optional.ofNullable(input.getPos())
                    .ifPresent(pos -> output.setPos(JpaFunctions.posToPosEntity.apply(pos)));
        }
        return output;
    }
}
