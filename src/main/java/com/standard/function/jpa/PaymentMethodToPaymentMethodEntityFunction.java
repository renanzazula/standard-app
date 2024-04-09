package com.standard.function.jpa;

import com.standard.domain.PaymentMethod;
import com.standard.entity.PaymentMethodEntity;

import java.util.function.Function;

public class PaymentMethodToPaymentMethodEntityFunction implements Function<PaymentMethodEntity, PaymentMethod> {

	@Override
	public PaymentMethod apply(PaymentMethodEntity input) {
		PaymentMethod output = new PaymentMethod();
		if(input != null) {
			output.setId(input.getId());
			output.setName(input.getName());
			output.setDescription(input.getDescription());
			output.setDiscountPercent(input.getDiscountPercent());
			output.setCreationDate(input.getCreationDate());
			output.setCreationTime(input.getCreationTime());
		}
		return output;
	}

}
