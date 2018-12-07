package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;

public class Ingresso {
	private Sessao sessao;
	private BigDecimal preco = BigDecimal.TEN;
	
	public Ingresso() {
		
	}
	
	public Ingresso(Sessao sessao, Desconto tipo) {
		this.sessao = sessao;
		this.preco = tipo.aplica(sessao);
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
}
