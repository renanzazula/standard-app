package com.standard.function.jpa;

import com.standard.domain.PaymentMethod;
import com.standard.entity.PaymentMethodEntity;
import com.standard.util.DateTimeConverter;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class PaymentMethodToPaymentMethodEntityFunction implements Function<PaymentMethodEntity, PaymentMethod> {

	private final DateTimeConverter dateTimeConverter;

	@Override
	public PaymentMethod apply(PaymentMethodEntity input) {
		PaymentMethod output = new PaymentMethod();
		if(input != null) {
			output.setId(input.getId());
			output.setName(input.getName());
			output.setDescription(input.getDescription());
			output.setCreationDate(dateTimeConverter.convert(input.getCreationDate()));
			output.setCreationTime(dateTimeConverter.convert(input.getCreationTime()));
			output.setDiscountPercent(input.getDiscountPercent());
			output.setStatus(input.getStatus() != null ? input.getStatus().name() : "");
		}
		return output;
	}

}
