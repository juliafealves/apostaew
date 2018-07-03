package controllers;

import entities.Cenario;
import entities.CenarioBonus;
import utils.ValidadorSistema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        ValidadorSistema.validaInicializacaoSistema(caixa, taxa, "Erro na inicializacao");
        this.caixa = caixa;
        this.taxa = taxa;
        this.cenarios = new ArrayList<>();
    }

    /**
     * Cadastra o cenário de aposta no sistema.
     *
     * @param descricao Descrição do cenário de aposta.
     * @return Retorna o identificador do cenário.
     */
    public int cadastraCenario(String descricao) {
        ValidadorSistema.validaCadastroCenario(descricao, "Erro no cadastro de cenario");
        int id = this.cenarios.size() + 1;
        this.cenarios.add(new Cenario(id, descricao));

        return id;
    }

    /**
     * Cadastra o cenário de aposta com bônus no sistema.
     *
     * @param descricao Descrição do cenário de aposta.
     * @param bonus Valor do bônus do cenário de aposta.
     * @return Retorna o identificador do cenário.
     */
    public int cadastraCenario(String descricao, int bonus){
        ValidadorSistema.validaCadastroCenario(descricao, bonus, "Erro no cadastro de cenario");
        int id = this.cenarios.size() + 1;
        this.cenarios.add(new CenarioBonus(id, descricao, bonus));
        this.caixa -= bonus;

        return id;
    }

    /**
     * Consulta as informações básicas de um cenário de aposta.
     *
     * @param cenario Localiza um cenário de aposta pelo número.
     * @return Retorna a string formatada NUMERACAO - DESCRICAO - ESTADO.
     */
    public String consultaCenario(int cenario) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro na consulta de cenario");
        return this.cenarios.get(cenario - 1).toString();
    }

    /**
     * Lista todos os cenários de aposta da coleção.
     *
     * @return String com todos os cenários cadastrados.
     */
    public String listaCenarios() {
        return this.cenarios
                .stream()
                .map(Cenario::toString)
                .collect(Collectors.joining(System.lineSeparator()));
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
     * @param cenario Identificador do cenário
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public void cadastraAposta(int cenario, String apostador, int valor, String previsao) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no cadastro de aposta");
        ValidadorSistema.validaCadastroAposta(apostador, valor, previsao, "Erro no cadastro de aposta");
        this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao);
    }

    /**
     * Cadastra uma aposta segura por valor em um cenário.
     *
     * @param cenario Identificador do cenário
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     * @param valorAssegurado Valor assegurado da aposta.
     * @param custo Custo da aposta.
     */
    public int cadastraAposta(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no cadastro de aposta assegurada por valor");
        ValidadorSistema.validaCadastroAposta(apostador, valor, previsao, valorAssegurado, custo, "Erro no cadastro de aposta assegurada por valor");
        this.caixa += custo;

        return this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao, valorAssegurado);
    }

    public int cadastraAposta(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no cadastro de aposta assegurada por taxa");
        ValidadorSistema.validaCadastroAposta(apostador, valor, previsao, taxa, custo, "Erro no cadastro de aposta assegurada por taxa");
        this.caixa += custo;

        return  this.cenarios.get(cenario - 1).adicionaAposta(apostador, valor, previsao, taxa);
    }

    /**
     * Modifica o tipo de aposta.
     * @param cenario Identificação do cenário.
     * @param apostaAssegurada Identificador de aposta.
     * @param valor Valor do seguro.
     * @return Retorna o identificador da aposta.
     */
    public int modificaAposta(int cenario, int apostaAssegurada, int valor) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no alterar seguro");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro no alterar seguro: Cenario esta fechado");

        return cenarioAtual.modificaTipoAposta(apostaAssegurada, valor);
    }

    /**
     * Modifica o tipo de aposta.
     * @param cenario Identificação do cenário.
     * @param apostaAssegurada Identificador de aposta.
     * @param taxa Taxa do seguro.
     * @return Retorna o identificador da aposta.
     */
    public int modificaAposta(int cenario, int apostaAssegurada, double taxa) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no alterar seguro");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro no alterar seguro: Cenario esta fechado");

        return cenarioAtual.modificaTipoAposta(apostaAssegurada, taxa);
    }

    /**
     * Lista as apostas de um cenário.
     *
     * @param cenario Identificador do cenário.
     * @return Representação textual: Nome - Valor - Previsão.
     */
    public String listaApostas(int cenario) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro no exibe apostas");
        return this.cenarios.get(cenario - 1).listaApostas();
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     *
     * @param cenario Idenficador do cenário de aposta.
     * @return Total de apostas realizadas.
     */
    public int obtemTotalApostas(int cenario) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro na consulta do total de apostas");
        return this.cenarios.get(cenario - 1).obtemTotalApostas();
    }

    /**
     * Retorna o valor total de apostas cadastradas em um cenário.
     *
     * @param cenario Identificador do cenário da aposta.
     * @return Valor total de apostas realizadas.
     */
    public int obtemValorTotalApostas(int cenario) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro na consulta do valor total de apostas");
        return this.cenarios.get(cenario - 1).obtemValorTotalApostas();
    }

    /**
     * Finaliza um cenário e calcula o valor a ser destinado ao caixa.
     *
     * @param cenario Numeracão do cenário.
     * @param ocorreu Retorna se ocorreu ou não o cenário.
     */
    public void finalizaCenario(int cenario, boolean ocorreu) {
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro ao fechar aposta");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");

        cenarioAtual.finaliza(ocorreu);
        this.caixa += this.calculaTaxa(cenarioAtual.calculaApostas());
        this.caixa -= cenarioAtual.calcularValorAssegurado();
    }

    /**
     * Calcula o valor total a ser destinado ao caixa das apostas perdedoras.
     *
     * @param cenario Numeração do cenário de aposta.
     * @return Valor total do cenário encerrado destinado ao caixa.
     */
    public int calculaCaixaCenario(int cenario){
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro na consulta do caixa do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");

        return this.calculaTaxa(cenarioAtual.calculaApostas());
    }

    /**
     * Calcula o valor a ser rateado para os ganhadoras das apostas, é retirado o valor destinado ao caixa.
     *
     * @param cenario Identificador do cenário.
     * @return Valor a ser rateado com os vencedores.
     */
    public int calculaCaixaRateioCenario(int cenario){
        ValidadorSistema.validaIdentificadorCenario(cenario, this.cenarios.size(), "Erro na consulta do total de rateio do cenario");
        Cenario cenarioAtual = this.cenarios.get(cenario - 1);

        if(!cenarioAtual.finalizado())
            throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");

        return cenarioAtual.calculaRateio(taxa);
    }

    /**
     * Calcula os valores das apostas de um cenário para o caixa.
     *
     * @param valor Valor a ser destinado ao caixa.
     */
    private int calculaTaxa(int valor){
        return (int) (valor * taxa);
    }
}
