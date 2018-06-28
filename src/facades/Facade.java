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
                "tests/acceptance/us4_test.txt"
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
     * Exibe um cenário de aposta correspondente a numeração informada.
     *
     * @param cenario Numeração única do cenário de aposta.
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
     */
    public void cadastrarAposta(int cenario, String apostador, int valor, String previsao){
        this.sistemaController.cadastraAposta(cenario, apostador, valor, previsao);
    }

    /**
     * Retorna o valor total de apostas realizadas em um cenário.
     *
     * @param cenario Numeração do cenário de Aposta.
     * @return Quantitativo em centavos de apostas realizadas em um cenário.
     */
    public int valorTotalDeApostas(int cenario){
        return this.sistemaController.obtemValorTotalApostas(cenario);
    }

    /**
     * Retorna o número total de apostas feitas em um cenário.
     *
     * @param cenario Numeração do cenário de Aposta.
     * @return Quantitativo total de apostas realizadas.
     */
    public int totalDeApostas(int cenario){
        return this.sistemaController.obtemTotalApostas(cenario);
    }

    /**
     * Exibe todas as apostas cadastradas em um cenário.
     * @return Retorna formatação textual: Apostador - Valor - Previsão
     */
    public String exibeApostas(int cenario){
        return this.sistemaController.listaApostas(cenario);
    }

    /**
     * Fecha as apostas do cenário.
     *
     * @param cenario Numeração do cenário de aposta.
     * @param ocorreu Indica se ocorreu (true) ou não ocorreu (false) o cenário.
     */
    public void fecharAposta(int cenario, boolean ocorreu){
        this.sistemaController.finalizaCenario(cenario, ocorreu);
    }

    public int getCaixaCenario(int cenario){
        return this.sistemaController.calculaCaixaCenario(cenario);
    }

    public int getTotalRateioCenario(int cenario){
        return this.sistemaController.calculaCaixaRateioCenario(cenario);
    }
}
