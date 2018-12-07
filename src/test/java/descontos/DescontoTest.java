package descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoBanco;
import br.com.caelum.ingresso.model.descontos.DescontoEstudantes;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {

	@Test
	public void oPrecoDaSessaoDeveSerSomadoComDescontoDeEstudante(){
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		Sala sala = new Sala("Sala 15", BigDecimal.TEN);

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, new DescontoEstudantes());
		
		BigDecimal valorCerto = new BigDecimal(10.00);
		Assert.assertEquals(valorCerto, ingresso.getPreco());
		System.out.println(ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualSomadoComDescontoDeBanco(){
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		Sala sala = new Sala("Sala 15", BigDecimal.TEN);

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, new DescontoBanco());
		
		BigDecimal valorCerto = new BigDecimal(14.00);
		Assert.assertEquals(valorCerto, ingresso.getPreco());
		System.out.println(ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme(){
		Filme filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		Sala sala = new Sala("Sala 15", BigDecimal.TEN);

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		
		BigDecimal valorCerto = new BigDecimal(10.00);
		Assert.assertEquals(valorCerto, ingresso.getPreco());
		System.out.println(ingresso.getPreco());
	}
}
