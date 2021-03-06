package facades;

import easyaccept.EasyAccept;
import controllers.SistemaController;

public class Facade {
    private SistemaController sistemaController;

    /**
     * Inicializa todos testes de aceitação com EasyAccept.
     * @param args
     */
    public static void main(String args[]){
        args = new String[]{
                "facades.Facade",
                "tests/acceptance/us1_test.txt",
                "tests/acceptance/us2_test.txt",
                "tests/acceptance/us3_test.txt",
                "tests/acceptance/us4_test.txt",
                "tests/acceptance/us5_test.txt",
                "tests/acceptance/us6_test.txt",
                "tests/acceptance/us7_test.txt"
        };

        EasyAccept.main(args);
    }

    /**
     * Inicializa o sistema de apostas.
     *
     * @param caixa Caixa valor em centavos.
     * @param taxa Taxa de cobrança do sistema para cada aposta perdedora.
     */
    public void inicializa(int caixa, double taxa){
        this.sistemaController = new SistemaController(caixa, taxa);
    }

    /**
     * Retorna o valor do caixa atual.
     */
    public int getCaixa(){
        return this.sistemaController.getCaixa();
    }

    /**
     * Cadastra um cenário para apostas no sistema.
     *
     * @param descricao Descrição do cenário.
     * @return Retorna a numeração única do cenário.
     */
    public int cadastrarCenario(String descricao){
        return this.sistemaController.cadastraCenario(descricao);
    }

    /**
     * Cadastra um cenário de aposta com bônus a ser destinado para os vencedores.
     *
     * @param descricao Descrição do cenário.
     * @param bonus Valor do bônus em centavos.
     * @return Retorna a numeração única do cenário.
     */
    public int cadastrarCenario(String descricao, int bonus){
        return this.sistemaController.cadastraCenario(descricao, bonus);
    }

    /**
     * Exibe um cenário de aposta correspondente o identificador informado.
     *
     * @param cenario Identificador único do cenário de aposta.
     * @return Retorna formatação textual: Numeracao - Descricao - Estado
     */
    public String exibirCenario(int cenario){
        return this.sistemaController.consultaCenario(cenario);
    }

    /**
     * Exibe todos os cenários cadastrados no sistema de apostas.
     *
     * @return Retorna formatação textual: Numeracao - Descricao - Estado
     */
    public String exibirCenarios(){
        return this.sistemaController.listaCenarios();
    }

    /**
     * Cadastra uma aposta em um determinado cenário existente no sistema.
     *
     * @param cenario Identificador do cenário de aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da aposta.
     */
    public void cadastrarAposta(int cenario, String apostador, int valor, String previsao){
        this.sistemaController.cadastraAposta(cenario, apostador, valor, previsao);
    }

    /**
     * Cadastra uma aposta segura do tipo valor em um determinado cenário existente no sistema.
     *
     * @param cenario Identificador do cenário de aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da aposta.
     * @param valorAssegurado Valor do seguro.
     * @param custo Custo da aposta.
     * @return Retorna o identificador da aposta.
     */
    public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo){
        return this.sistemaController.cadastraAposta(cenario, apostador, valor, previsao, valorAssegurado, custo);
    }

    /**
     * Cadastra uma aposta segura do tipo taxa em um determinado cenário existente no sistema.
     *
     * @param cenario Identificador do cenário de aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da aposta.
     * @param taxa Taxa do seguro.
     * @param custo Custo da aposta.
     * @return Retorna o identificador da aposta.
     */
    public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo){
        return this.sistemaController.cadastraAposta(cenario, apostador, valor, previsao, taxa, custo);
    }

    /**
     * Configura a ordenação do cenário.
     *
     * Cadastro: A ordem natural de cadastro dos cenários (ordenados pela identificação)
     * Nome: Ordenação baseando-se na descrição do cenário ( A < Z )
     * Apostas: Ordenação baseando-se no número total de apostas (mais apostas primeiro)
     *
     * @param ordem Tipo de ordenação "cadastro", "nome", "apostas".
     */
    public void alterarOrdem(String ordem){
        this.sistemaController.configuraOrdenacao(ordem);
    }

    /**
     * Altera o tipo de seguro de uma aposta para aposta segurada por valor.
     * @param cenario Identificador do cenário de aposta.
     * @param apostaAssegurada Identificador da aposta.
     * @param valor Valor assegurada da aposta.
     * @return Retorna o identificador da aposta.
     */
    public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor){
        return this.sistemaController.modificaAposta(cenario, apostaAssegurada, valor);
    }

    /**
     * Exibe um cenário ordenado de acordo com a configuração de ordenação configura.
     * Por padrão, a ordenação utilizada é a ordem pelo identificador do cenário.
     * @param cenario Identificador do cenário.
     *
     * @return
     */
    public String exibirCenarioOrdenado(int cenario){
        return this.sistemaController.consultaCenarioOrdenado(cenario);
    }

    /**
     * Altera o tipo de seguro de uma aposta para aposta segurada por valor.
     * @param cenario Identificador do cenário de aposta.
     * @param apostaAssegurada Identificador da aposta.
     * @param taxa Taxa da aposta.
     * @return Retorna o identificador da aposta.
     */
    public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa){
        return this.sistemaController.modificaAposta(cenario, apostaAssegurada, taxa);
    }

    /**
     * Retorna o valor total de apostas realizadas em um cenário.
     *
     * @param cenario Identificador do cenário de Aposta.
     * @return Quantitativo em centavos de apostas realizadas em um cenário.
     */
    public int valorTotalDeApostas(int cenario){
        return this.sistemaController.obtemValorTotalApostas(cenario);
    }

    /**
     * Retorna o número total de apostas feitas em um cenário.
     *
     * @param cenario Identificador do cenário de aposta.
     * @return Total de apostas realizadas.
     */
    public int totalDeApostas(int cenario){
        return this.sistemaController.obtemTotalApostas(cenario);
    }

    /**
     * Exibe todas as apostas cadastradas em um cenário.
     *
     * @return Retorna formatação textual: Apostador - Valor - Previsão
     */
    public String exibeApostas(int cenario){
        return this.sistemaController.listaApostas(cenario);
    }

    /**
     * Fecha as apostas do cenário.
     *
     * @param cenario Identificador do cenário de aposta.
     * @param ocorreu Indica se ocorreu (true) ou não ocorreu (false) o cenário.
     */
    public void fecharAposta(int cenario, boolean ocorreu){
        this.sistemaController.finalizaCenario(cenario, ocorreu);
    }

    /**
     * Retorna o valor total de um cenário encerrado que será destinado ao caixa.
     *
     * @param cenario Identificador do cenário de aposta.
     * @return Valor total do cenário encerrado destinado ao caixa.
     */
    public int getCaixaCenario(int cenario){
        return this.sistemaController.calculaCaixaCenario(cenario);
    }

    /**
     * Retorna o valor total de um cenário encerrado que será destinado a distribuição entre as apostas vencedoras.
     *
     * @param cenario Identificador do cenário de aposta.
     * @return Valor a ser rateado com os vencedores.
     */
    public int getTotalRateioCenario(int cenario){
        return this.sistemaController.calculaCaixaRateioCenario(cenario);
    }
}
