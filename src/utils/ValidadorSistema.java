package utils;

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
     * Valida o identificador do cenário de aposta.
     *
     * @param cenario Identificador não poderá ser menor ou igual a 0.
     * @param localErro Localização onde ocorreu o erro.
     */
    public static void validaIdentificadorCenario(int cenario, int totalCenarios, String localErro){
        Validador.validaNumeroPositivo(cenario, localErro + ": Cenario invalido", false);
        Validador.validaIndiceColecao(cenario - 1, totalCenarios,  localErro + ": Cenario nao cadastrado");
    }
}
