package controllers;

import entities.Cenario;
import enums.PrevisaoEnum;
import utils.Validador;

import java.util.ArrayList;

public class SistemaController {
    private int caixa;
    private ArrayList<Cenario> cenarios;
    private double taxa;

    /**
     * Inicializa o sistema, definindo o valor do caixa (em centavos) e a taxa.
     *
     * @param caixa Valor em centavos.
     * @param taxa Taxa das apostas perdedoras destinadas ao caixa.
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
        this.validaCenario(cenario, "Erro na consulta de cenario");
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

    /**
     * Retorna o valor atual do caixa do sistema.
     * @return Caixa em centavos.
     */
    public int getCaixa() {
        return this.caixa;
    }

    /**
     * Cadastra uma aposta em um cenário.
     * @param cenario Numeração do cenário
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public void cadastraAposta(int cenario, String apostador, int valor, String previsao) {
        this.validaCenario(cenario, "Erro no cadastro de aposta");
        this.validaAposta(apostador, valor, previsao, "Erro no cadastro de aposta");

        this.cenarios.get(cenario - 1).cadastraAposta(apostador, valor, previsao);
    }

    /**
     * Listas as apostas de um cenário.
     * @param cenario Numeração do cenário.
     * @return Representação textual: Nome - Valor - Previsão.
     */
    public String listaApostas(int cenario) {
        this.validaCenario(cenario, "Erro no exibe apostas");
        return this.cenarios.get(cenario - 1).listaApostas();
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     * @param cenario Numeracão do cenário.
     * @return Total de apostas realizadas.
     */
    public int obtemTotalApostas(int cenario) {
        this.validaCenario(cenario, "Erro na consulta do total de apostas");
        return this.cenarios.get(cenario - 1).obtemTotalApostas();
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     * @param cenario Numeracão do cenário.
     * @return Total de apostas realizadas.
     */
    public int obtemValorTotalApostas(int cenario) {
        this.validaCenario(cenario, "Erro na consulta do valor total de apostas");
        return this.cenarios.get(cenario - 1).obtemValorTotalApostas();
    }

    /**
     * Finaliza um cenário e calcula o valor a ser destinado ao caixa.
     *
     * @param cenario Numeracão do cenário.
     * @param ocorreu Retorna se ocorreu ou não o cenário.
     */
    public void finalizaCenario(int cenario, boolean ocorreu) {
        this.validaCenario(cenario, "Erro ao fechar aposta");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");

        int valor = cenarioAtual.finaliza(ocorreu);
        this.calculaCaixa(valor);
    }

    /**
     * Calcula os valores das apostas de um cenário para o caixa.
     * @param valor
     */
    private void calculaCaixa(int valor){
        this.caixa += (int) valor * taxa;
    }

    public int calculaCaixaCenario(int cenario){
        this.validaCenario(cenario, "Erro na consulta do caixa do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");

        return cenarioAtual.calculaApostas();
    }

    public int calculaCaixaRateioCenario(int cenario){
        this.validaCenario(cenario, "Erro na consulta do total de rateio do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");

        return cenarioAtual.calculaRateioApostas(taxa);
    }

    /**
     * Valida os dados da aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    private void validaAposta(String apostador, int valor, String previsao, String localErro) {
        // Valida apostador
        Validador.validaNaoNulo(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        // Valida Valor
        Validador.validaNumeroPositivo(valor, localErro + ": Valor nao pode ser menor ou igual a zero", false);
        // Valida Previsao
        Validador.validaNaoNulo(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        String[] previsoes = {PrevisaoEnum.NAO_VAI_ACONTECER.getPrevisao(), PrevisaoEnum.VAI_ACONTECER.getPrevisao()};
        Validador.validaStringIguais(previsao, previsoes, localErro + ": Previsao invalida");
    }

    /**
     * Valida a numeração do cenário.
     *
     * @param cenario Numeração do cenário.
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    private void validaCenario(int cenario, String localErro){
        Validador.validaNumeroPositivo(cenario, localErro + ": Cenario invalido", false);
        Validador.validaIndiceColecao(cenario - 1, this.cenarios.size(),  localErro + ": Cenario nao cadastrado");
    }
}
