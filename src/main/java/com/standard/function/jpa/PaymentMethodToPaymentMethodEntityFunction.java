package com.standard.function.jpa;

import com.standard.domain.PaymentMethod;
import com.standard.entity.PaymentMethodEntity;

import java.util.function.Function;

public class PaymentMethodToPaymentMethodEntityFunction implements Function<PaymentMethodEntity, PaymentMethod> {

	@Override
	public PaymentMethod apply(PaymentMethodEntity input) {
		PaymentMethod output = new PaymentMethod();
		if(input != null) {
			output.setCodigo(input.getId());
			output.setNome(input.getName());
			output.setDescricao(input.getDescription());
			output.setPorcentagemDesconto(input.getDiscountPercent());
			output.setData(input.getCreationDate());
			output.setHora(input.getCreationTime());
		}
		return output;
	}

}
