package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessaoController {

    @Autowired
    private SalaDao salaDao;
    @Autowired
    private FilmeDao filmeDao;
    @Autowired
    private SessaoDao sessaoDao;

    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
        ModelAndView model = new ModelAndView("sessao/sessao");

        model.addObject("sala", salaDao.findOne(salaId));
        model.addObject("filmes", filmeDao.findAll());
        model.addObject("form", form);
        return model;
    }

    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {

        if (result.hasErrors()) {
            return form(form.getSalaId(), form);
        }

        Sessao sessaoNova = form.toSessao(salaDao, filmeDao);

        List<Sessao> sessoes = sessaoDao.buscaSessoes(sessaoNova.getSala());

        GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);

        if (gerenciadorDeSessao.cabe(sessaoNova)) {

            ModelAndView model = new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");

            sessaoDao.save(sessaoNova);

            return model;
        }

        return form(form.getSalaId(), form);
    }


}
