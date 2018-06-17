package facades;

import controllers.SistemaController;

public class Facade {
    private SistemaController sistemaController;

    public Facade(){
        this.sistemaController = new SistemaController();
    }

    /**
     * Cadastra um cenário para apostas no sistema.
     *
     * @param descricao Descrição do cenário
     * @return Retorna a numeração única do cenário.
     */
    public int cadastraCenario(String descricao){
        return this.sistemaController.cadastraCenario(descricao);
    }
}
