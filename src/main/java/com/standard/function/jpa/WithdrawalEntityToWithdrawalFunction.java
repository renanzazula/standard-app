package com.standard.function.jpa;

import com.standard.domain.Withdrawal;
import com.standard.entity.WithdrawalEntity;
import com.standard.function.JpaFunctions;

import java.util.Optional;

public class WithdrawalEntityToWithdrawalFunction implements java.util.function.Function<WithdrawalEntity, Withdrawal> {

    @Override
    public Withdrawal apply(WithdrawalEntity input) {
        Withdrawal output = new Withdrawal();
        if (input != null) {
            output.setId(input.getId());
            output.setDescription(input.getDescription());
            output.setAmount(input.getAmount());
            output.setCreationDate(input.getCreationDate());
            output.setCreationTime(input.getCreationTime());
            Optional.ofNullable(input.getPos())
                    .ifPresent(pos -> output.setPos(JpaFunctions.posToPosEntity.apply(pos)));
        }
        return output;
    }
}
