package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Sessao;

import java.time.LocalTime;
import java.util.List;

public class GerenciadorDeSessao {

    private List<Sessao> sessoes;

    public GerenciadorDeSessao(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public boolean cabe(Sessao nova) {

        return sessoes.stream().noneMatch(antiga -> temConflitoEntre(nova, antiga));

    }

    private boolean temConflitoEntre(Sessao nova, Sessao antiga) {
        LocalTime inicioAntiga = antiga.getHorario();
        LocalTime inicioNova = nova.getHorario();

        LocalTime fimAntiga = antiga.getHorarioTermino();
        LocalTime fimNova = nova.getHorarioTermino();

        if (inicioAntiga.equals(inicioNova)) {
            return true;
        }

        if (inicioNova.isBefore(inicioAntiga)) {
            return fimNova.isAfter(inicioAntiga);
        } else {
            return inicioNova.isBefore(fimAntiga);
        }

    }
}
