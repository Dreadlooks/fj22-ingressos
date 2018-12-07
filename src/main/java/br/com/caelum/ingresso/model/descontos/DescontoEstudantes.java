package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.Sessao;

public class DescontoEstudantes implements Desconto {

	@Override
	public BigDecimal aplica(Sessao sessao) {
		return sessao.getPreco().multiply(new BigDecimal(0.5));
	}

}
