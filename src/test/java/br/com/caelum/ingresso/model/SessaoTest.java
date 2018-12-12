package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;



public class SessaoTest {

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme() {
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", new BigDecimal("10"));
		Sala sala = new Sala("Sala 15",  new BigDecimal("10"));
		
		BigDecimal somaTotal = sala.getPreco().add(filme.getPreco());
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		
		Assert.assertEquals(somaTotal, sessao.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEORestoDisponivel(){
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", new BigDecimal("10"));
		Sala sala = new Sala("Sala 15",  new BigDecimal("10"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
	
		
	}
}
