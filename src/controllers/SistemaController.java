package controllers;

import entities.Cenario;
import utils.Validador;

import java.util.ArrayList;

public class SistemaController {
    private ArrayList<Cenario> cenarios;

    public SistemaController(){
        this.cenarios = new ArrayList<>();
    }

    /**
     * Cadastra um cenário no sistema.
     * @param descricao Descrição não nula e vazia
     * @return Retorna a numeração do cenário.
     */
    public int cadastraCenario(String descricao) {
        Validador.validaNaoNulo(descricao, "Erro no cadastro de cenario: Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, "Erro no cadastro de cenario: Descricao nao pode ser vazia");

        int numeracao = this.cenarios.size() + 1;
        this.cenarios.add(new Cenario(descricao, numeracao));

        return numeracao;
    }

    /**
     * Exibe o cenário.
     * @param cenario Localiza um Cenario pelo número.
     * @return Retorna a String formatada DESCRICAO - ESTADO.
     */
    public String consultaCenario(int cenario) {
        Validador.validaNumeroPositivo(cenario, "Erro na consulta de cenario: Cenario invalido", false);
        Validador.validaIndiceColecao(cenario - 1, this.cenarios.size(), "Erro na consulta de cenario: Cenario nao cadastrado");
        return this.cenarios.get(cenario - 1).toString();
    }
}
