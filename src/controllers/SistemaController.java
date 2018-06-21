package controllers;

import entities.Cenario;
import utils.Validador;

import java.util.ArrayList;

public class SistemaController {
    private ArrayList<Cenario> cenarios;
    private double taxa;
    private int caixa;

    /**
     * Inicializa o sistema, definindo o valor do caixa (em centavos) e a taxa.
     * @param caixa Valor em centavos.
     * @param taxa Taxa das apostas.
     */
    public SistemaController(int caixa, double taxa) {
        Validador.validaNumeroPositivo(caixa, "Erro na inicializacao: Caixa nao pode ser inferior a 0", true);
        Validador.validaNumeroPositivo(taxa, "Erro na inicializacao: Taxa nao pode ser inferior a 0", true);
        this.caixa = caixa;
        this.taxa = taxa;
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

    /**
     * Lista todos os cenários da coleção.
     * @return String com todos os cenários cadastrados.
     */
    public String listaCenarios() {
        StringBuilder cenarios = new StringBuilder();

        for(Cenario cenario : this.cenarios)
            cenarios.append(cenario).append(System.lineSeparator());

        return cenarios.toString();
    }
}
