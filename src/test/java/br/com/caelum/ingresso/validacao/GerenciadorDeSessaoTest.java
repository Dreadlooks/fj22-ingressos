package br.com.caelum.ingresso.validacao;


import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GerenciadorDeSessaoTest {

    private Sala sala;
    private Filme filme;
    private LocalTime inicio;


    @Before
    public void setUp() {

        this.sala = new Sala("Sala 1");
        this.filme = new Filme("HP", Duration.ofMinutes(90), "Ficção");
        this.inicio = LocalTime.of(10, 00);
    }


    @Test
    public void deveCaberQuandoAListaDeSessoesEstiverVazia() {

        List<Sessao> sessoes = Collections.emptyList();

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

        Sessao sessao = new Sessao(inicio, sala, filme);

        boolean coube = gerenciador.cabe(sessao);

        assertTrue(coube);

    }

    @Test
    public void deveHaverConflitoQuandoHorarioDeInicioForemIguais() {

        Sessao sessao = new Sessao(inicio, sala, filme);

        List<Sessao> sessoes = Arrays.asList(sessao);

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

        boolean coube = gerenciador.cabe(sessao);

        assertFalse(coube);


    }
}