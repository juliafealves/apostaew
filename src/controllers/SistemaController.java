package controllers;

import entities.Cenario;
import utils.ValidadorCenario;

import java.util.ArrayList;

public class SistemaController {
    private ArrayList<Cenario> cenarios;
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

    /**
     * Exibe o cenário.
     * @param cenario Localiza um Cenario pelo número.
     * @return Retorna a String formatada DESCRICAO - ESTADO.
     */
    public String consultaCenario(int cenario) {
        return this.cenarios.get(cenario + 1).toString();
    }
}
