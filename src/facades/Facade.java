package facades;

import easyaccept.EasyAccept;
import controllers.SistemaController;

public class Facade {
    private SistemaController sistemaController;

    public Facade(){
        this.sistemaController = new SistemaController();
    }

    public static void main(String args[]){
        args = new String[]{
                "facades.Facade",
//                "tests/acceptance/us1_test.txt",
                "tests/acceptance/us2_test.txt",
//                "tests/acceptance/us3_test.txt",
//                "tests/acceptance/use4_test.txt"
        };

        EasyAccept.main(args);
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
}
