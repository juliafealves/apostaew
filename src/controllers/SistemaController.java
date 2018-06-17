package controllers;

import entities.Cenario;
import utils.ValidadorCenario;

import java.util.ArrayList;
import java.util.Collection;

public class SistemaController {
    private Collection<Cenario> cenarios;
    private ValidadorCenario validadorCenario;

    public SistemaController(){
        this.cenarios = new ArrayList<>();
        this.validadorCenario = new ValidadorCenario();
    }

    /**
     * Cadastra um cenário no sistema.
     * @param descricao Descrição não nula e vazia
     * @return Retorna a numeração do cenário.
     */
    public int cadastraCenario(String descricao) {
        this.validadorCenario.validaDescricao(descricao);
        this.cenarios.add(new Cenario(descricao));
        return this.cenarios.size();
    }
}
