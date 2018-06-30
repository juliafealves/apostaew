package entities;

import utils.Validador;

/**
 * Representa um cenário do tipo bônus.
 */
public class CenarioBonus extends Cenario {

    private int bonus;

    /**
     * Cria um objeto Cenario.
     *
     * @param descricao A descrição não pode ser vazia ou nula.
     * @param numeracao Numeração do cenário de aposta.
     * @param bonus Valor do bônus da aposta.
     */
    public CenarioBonus(String descricao, int numeracao, int bonus) {
        super(descricao, numeracao);
        Validador.validaNumeroPositivo(bonus, "Bonus nao pode ser inferior ou igual a 0", false);

        this.bonus = bonus;
    }

    /**
     * Formatação textual do cenário bônus.
     * @return String formatada: DESCRICAO - ESTADO - BONUS
     */
    public String toString(){
        return this.id + " - " + this.descricao + " - " + this.estado + " - R$ " + String.format("%.2f", this.bonus / 100.0);
    }

    /**
     * Calcula o rateio do bônus.
     *
     * @param taxa Taxa do caixa.
     * @return
     */
    @Override
    public int calculaRateio(double taxa) {
        return super.calculaRateio(taxa) + this.bonus;
    }
}
