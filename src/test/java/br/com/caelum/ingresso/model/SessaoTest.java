package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;



public class SessaoTest {

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme(){
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		Sala sala = new Sala("Sala 15", BigDecimal.TEN);
		
		BigDecimal somaTotal = sala.getPreco().add(filme.getPreco());
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		
		Assert.assertEquals(somaTotal, sessao.getPreco());
	}
}
