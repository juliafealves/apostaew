package controllers;

import entities.Cenario;
import entities.CenarioBonus;
import enums.PrevisaoEnum;
import utils.Validador;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o sistema de apostas.
 *
 * @author Júlia Fernandes Alves (117211383) - juliafealves@gmail.com
 */
public class SistemaController {
    /**
     * Quantitativo de centavos no caixa do sistema.
     */
    private int caixa;

    /**
     * Taxa a ser repassado para o caixa das apostas perdedoras.
     */
    private double taxa;

    /**
     * Coleção de todos os cenários de apostas cadastrados no sistema.
     */
    private List<Cenario> cenarios;

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
     * Cadastra um cenário de aposta no sistema.
     *
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

    public int cadastraCenario(String descricao, int bonus){
        Validador.validaNaoNulo(descricao, "Erro no cadastro de cenario: Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, "Erro no cadastro de cenario: Descricao nao pode ser vazia");
        Validador.validaNumeroPositivo(bonus, "Erro no cadastro de cenario: Bonus invalido", false);

        int numeracao = this.cenarios.size() + 1;
        this.cenarios.add(new CenarioBonus(descricao, numeracao, bonus));
        this.caixa -= bonus;

        return numeracao;
    }

    /**
     * Consulta as informações básicas de um cenário de aposta.
     *
     * @param cenario Localiza um Cenario pelo número.
     * @return Retorna a String formatada DESCRICAO - ESTADO.
     */
    public String consultaCenario(int cenario) {
        this.validaCenario(cenario, "Erro na consulta de cenario");
        return this.cenarios.get(cenario - 1).toString();
    }

    /**
     * Lista todos os cenários de aposta da coleção.
     *
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
     *
     * @return Valor do caixa em centavos.
     */
    public int getCaixa() {
        return this.caixa;
    }

    /**
     * Cadastra uma aposta em um cenário.
     *
     * @param cenario Numeração do cenário
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public void cadastraAposta(int cenario, String apostador, int valor, String previsao) {
        this.validaCenario(cenario, "Erro no cadastro de aposta");
        this.validaAposta(apostador, valor, previsao, "Erro no cadastro de aposta");

        this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao);
    }

    public int cadastraAposta(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
        this.validaCenario(cenario, "Erro no cadastro de aposta assegurada por valor");
        this.validaAposta(apostador, valor, previsao, "Erro no cadastro de aposta assegurada por valor");
        this.caixa += custo;

        return  this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao, valorAssegurado);
    }

    public int cadastraAposta(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
        this.validaCenario(cenario, "Erro no cadastro de aposta assegurada por taxa");
        this.validaAposta(apostador, valor, previsao, "Erro no cadastro de aposta assegurada por taxa");
        this.caixa += custo;

        return  this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao, taxa);
    }

    /**
     * Modifica o tipo de aposta.
     * @param cenario Identificação do cenário.
     * @param apostaAssegurada Identificador de aposta.
     * @param valor Valor do seguro.
     * @return
     */
    public int modificaAposta(int cenario, int apostaAssegurada, int valor) {
        this.validaCenario(cenario, "Erro no exibe apostas");
        return this.cenarios.get(cenario - 1).modificaTipoAposta(apostaAssegurada, valor);
    }

    /**
     * Modifica o tipo de aposta.
     * @param cenario Identificação do cenário.
     * @param apostaAssegurada Identificador de aposta.
     * @param taxa Taxa do seguro.
     * @return
     */
    public int modificaAposta(int cenario, int apostaAssegurada, double taxa) {
        this.validaCenario(cenario, "Erro no exibe apostas");
        return this.cenarios.get(cenario - 1).modificaTipoAposta(apostaAssegurada, taxa);
    }

    /**
     * Lista as apostas de um cenário.
     *
     * @param cenario Numeração do cenário.
     * @return Representação textual: Nome - Valor - Previsão.
     */
    public String listaApostas(int cenario) {
        this.validaCenario(cenario, "Erro no exibe apostas");
        return this.cenarios.get(cenario - 1).listaApostas();
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     *
     * @param cenario Numeracão do cenário.
     * @return Total de apostas realizadas.
     */
    public int obtemTotalApostas(int cenario) {
        this.validaCenario(cenario, "Erro na consulta do total de apostas");
        return this.cenarios.get(cenario - 1).obtemTotalApostas();
    }

    /**
     * Retorna o valor total de apostas cadastradas em um cenário.
     *
     * @param cenario Numeracão do cenário da aposta.
     * @return Valor total de apostas realizadas.
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

        cenarioAtual.finaliza(ocorreu);
        this.caixa += this.calculaTaxa(cenarioAtual.calculaApostas(false));
        this.caixa -= cenarioAtual.calcularValorAssegurado();
    }

    /**
     * Calcula o valor total a ser destinado ao caixa das apostas perdedoras.
     *
     * @param cenario Numeração do cenário de aposta.
     * @return Valor total do cenário encerrado destinado ao caixa.
     */
    public int calculaCaixaCenario(int cenario){
        this.validaCenario(cenario, "Erro na consulta do caixa do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");

        return this.calculaTaxa(cenarioAtual.calculaApostas(false));
    }

    /**
     * Calcula o valor a ser rateado para os ganhadoras das apostas, é retirado o valor destinado ao caixa.
     *
     * @param cenario Numeração do cenário.
     * @return Valor a ser rateado com os vencedores.
     */
    public int calculaCaixaRateioCenario(int cenario){
        this.validaCenario(cenario, "Erro na consulta do total de rateio do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");

        return cenarioAtual.calculaRateio(taxa);
    }

    /**
     * Calcula os valores das apostas de um cenário para o caixa.
     * @param valor Valor a ser destinado ao caixa.
     */
    private int calculaTaxa(int valor){
        return (int) (valor * taxa);
    }

    /**
     * Valida os dados da aposta.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    private void validaAposta(String apostador, int valor, String previsao, String localErro) {
        Validador.validaNaoNulo(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        Validador.validaNumeroPositivo(valor, localErro + ": Valor nao pode ser menor ou igual a zero", false);
        Validador.validaNaoNulo(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        String[] previsoes = {PrevisaoEnum.NAO_VAI_ACONTECER.toString(), PrevisaoEnum.VAI_ACONTECER.toString()};
        Validador.validaStringIguais(previsao, previsoes, localErro + ": Previsao invalida");
    }

    /**
     * Valida a numeração do cenário de aposta.
     *
     * @param cenario Numeração do cenário de aposta.
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    private void validaCenario(int cenario, String localErro){
        Validador.validaNumeroPositivo(cenario, localErro + ": Cenario invalido", false);
        Validador.validaIndiceColecao(cenario - 1, this.cenarios.size(),  localErro + ": Cenario nao cadastrado");
    }
}
