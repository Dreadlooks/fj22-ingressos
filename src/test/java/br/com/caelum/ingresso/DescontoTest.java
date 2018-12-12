package br.com.caelum.ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;

public class DescontoTest {

	@Test
	public void oPrecoDaSessaoDeveSerSomadoComDescontoDeEstudante(){
		Lugar lugar = new Lugar("A",1);
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama",new BigDecimal("10"));
		Sala sala = new Sala("Sala 15", new BigDecimal("10"));

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		
		BigDecimal valorCerto = new BigDecimal("10.00");
		Assert.assertEquals(valorCerto, sessao.getPreco().intValue());
		System.out.println(valorCerto);
		System.out.println(ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualSomadoComDescontoDeBanco(){
		Lugar lugar = new Lugar("A",1);
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", new BigDecimal("10"));
		Sala sala = new Sala("Sala 15", new BigDecimal("10"));

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		
		BigDecimal valorCerto = new BigDecimal("10.00");
		Assert.assertEquals(valorCerto, ingresso.getPreco());
		System.out.println(ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme(){
		Lugar lugar = new Lugar("A",1);
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", new BigDecimal("10"));
		Sala sala = new Sala("Sala 15", new BigDecimal("10"));

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, lugar);
		
		BigDecimal valorCerto = new BigDecimal("20.00");
		Assert.assertEquals(valorCerto, ingresso.getPreco());
		System.out.println(ingresso.getPreco());
	}
}
