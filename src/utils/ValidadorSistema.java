package utils;

import enums.PrevisaoEnum;

/**
 * Validação específica do sistema de apostas.
 */
public class ValidadorSistema {

    /**
     * Valida a inicialização do sistema.
     *
     * @param caixa Valor do caixa não deverá menor que 0.
     * @param taxa Taxa não deverá menor que 0.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaInicializacaoSistema(int caixa, double taxa, String localErro){
        Validador.validaNumeroPositivo(caixa, localErro + ": Caixa nao pode ser inferior a 0", true);
        Validador.validaNumeroPositivo(taxa, localErro + ": Taxa nao pode ser inferior a 0", true);
    }

    /**
     * Valida o cadastro do cenário.
     *
     * @param descricao Descrição não poderá ser nula e vazia.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaCadastroCenario(String descricao, String localErro){
        Validador.validaNaoNulo(descricao, localErro + ": Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, localErro + ": Descricao nao pode ser vazia");
    }

    /**
     * Valida o cadastro do cenário.
     *
     * @param descricao Descrição não poderá ser nula e vazia.
     * @param bonus Valor do bônus não poderá ser menor ou igual 0.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaCadastroCenario(String descricao, int bonus, String localErro){
        Validador.validaNaoNulo(descricao, localErro + ": Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, localErro + ": Descricao nao pode ser vazia");
        Validador.validaNumeroPositivo(bonus, localErro + ": Bonus invalido", false);
    }

    /**
     * Valida o identificador da aposta.
     *
     * @param aposta Identificador não poderá ser menor ou igual a 0.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaIdentificadorAposta(int aposta, int totalApostas, String localErro){
        Validador.validaNumeroPositivo(aposta, localErro + ": Aposta invalida", false);
        Validador.validaIndiceColecao(aposta - 1, totalApostas,  localErro + ": Aposta nao cadastrada");
    }

    /**
     * Valida o identificador do cenário de aposta.
     *
     * @param cenario Identificador não poderá ser menor ou igual a 0.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaIdentificadorCenario(int cenario, int totalCenarios, String localErro){
        Validador.validaNumeroPositivo(cenario, localErro + ": Cenario invalido", false);
        Validador.validaIndiceColecao(cenario - 1, totalCenarios,  localErro + ": Cenario nao cadastrado");
    }

    /**
     * Valida os dados da aposta.
     *
     * @param apostador Nome do apostador não poderá ser nulo ou vazio.
     * @param valor Valor da aposta não poderá ser menor ou igual a 0.
     * @param previsao Previsão da Aposta só poderá ser N VAI ACONTECER ou VAI ACONTECER, não podendo ser nula ou vazia.
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    public static void validaCadastroAposta(String apostador, int valor, String previsao, String localErro) {
        Validador.validaNaoNulo(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, localErro + ": Apostador nao pode ser vazio ou nulo");
        Validador.validaNumeroPositivo(valor, localErro + ": Valor nao pode ser menor ou igual a zero", false);
        Validador.validaNaoNulo(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, localErro + ": Previsao nao pode ser vazia ou nula");
        String[] previsoes = { PrevisaoEnum.NAO_VAI_ACONTECER.toString(), PrevisaoEnum.VAI_ACONTECER.toString() };
        Validador.validaStringIguais(previsao, previsoes, localErro + ": Previsao invalida");
    }

    /**
     * Valida os dados da aposta segura por valor.
     *
     * @param apostador Nome do apostador não poderá ser nulo ou vazio.
     * @param valor Valor da aposta não poderá ser menor ou igual a 0.
     * @param previsao Previsão da Aposta só poderá ser N VAI ACONTECER ou VAI ACONTECER, não podendo ser nula ou vazia.
     * @param valorAssegurado Valor assegurado da aposta não poderá ser menor ou igual a 0.
     * @param custo Custo da aposta não poderá ser menor ou igual a 0.
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    public static void validaCadastroAposta(String apostador, int valor, String previsao, int valorAssegurado, int custo, String localErro) {
        ValidadorSistema.validaCadastroAposta(apostador, valor, previsao, localErro);
        Validador.validaNumeroPositivo(valorAssegurado, localErro + ": Valor assegurado nao pode ser menor ou igual a zero", false);
        Validador.validaNumeroPositivo(custo, localErro + ": Custo nao pode ser menor ou igual a zero", false);
    }

    /**
     * Valida os dados da aposta segura por taxa.
     *
     * @param apostador Nome do apostador não poderá ser nulo ou vazio.
     * @param valor Valor da aposta não poderá ser menor ou igual a 0.
     * @param previsao Previsão da Aposta só poderá ser N VAI ACONTECER ou VAI ACONTECER, não podendo ser nula ou vazia.
     * @param taxa Taxa da aposta não poderá ser menor ou igual a 0.
     * @param custo Custo da aposta não poderá ser menor ou igual a 0.
     * @param localErro Localização onde ocorreu o erro. Ex.: "Erro no cadastro de aposta".
     */
    public static void validaCadastroAposta(String apostador, int valor, String previsao, double taxa, int custo, String localErro) {
        ValidadorSistema.validaCadastroAposta(apostador, valor, previsao, localErro);
        Validador.validaNumeroPositivo(taxa, localErro + ": Taxa nao pode ser menor ou igual a zero", false);
        Validador.validaNumeroPositivo(custo, localErro + ": Custo nao pode ser menor ou igual a zero", false);
    }
}
