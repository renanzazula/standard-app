package com.standard.function.jpa;

import com.standard.domain.Withdrawal;
import com.standard.entity.WithdrawalEntity;
import com.standard.function.JpaFunctions;

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
            if(input.getPos() != null){
                output.setPos(JpaFunctions.posToPosEntity.apply(input.getPos()));
            }
        }
        return output;
    }
}
