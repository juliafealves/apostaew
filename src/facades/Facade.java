package facades;

import easyaccept.EasyAccept;
import controllers.SistemaController;

public class Facade {
    private SistemaController sistemaController;

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
     * @param caixa Caixa valor em centavos.
     * @param taxa Taxa de cobrança.
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
     * @param descricao Descrição do cenário
     * @return Retorna a numeração única do cenário.
     */
    public int cadastrarCenario(String descricao){
        return this.sistemaController.cadastraCenario(descricao);
    }

    /**
     * Exibe um cenário correspondente a numeração informada.
     * @param cenario Numeração do cenário de Aposta.
     * @return Retorna formatação textual: Numeracao - Descricao - Estado
     */
    public String exibirCenario(int cenario){
        return this.sistemaController.consultaCenario(cenario);
    }

    /**
     * Exibe todos os cenários cadastrados.
     * @return Retorna formatação textual: Numeracao - Descricao - Estado
     */
    public String exibirCenarios(){
        return this.sistemaController.listaCenarios();
    }

    /**
     * Cadastra um cenário para apostas no sistema.
     *
     */
    public void cadastrarAposta(int cenario, String apostador, int valor, String previsao){
        this.sistemaController.cadastraAposta(cenario, apostador, valor, previsao);
    }

    /**
     * Retorna o número de apostas feitas em um cenário.
     * @param cenario Numeração do cenário de Aposta.
     * @return
     */
    public int totalDeApostas(int cenario){
        return this.sistemaController.obtemTotalApostas(cenario);
    }
}
