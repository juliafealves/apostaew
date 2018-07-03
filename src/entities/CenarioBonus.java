package entities;

import utils.Validador;

/**
 * Representa um cenário do tipo bônus.
 */
public class CenarioBonus extends Cenario {

    /**
     * Valor do bônus do cenário.
     */
    private int bonus;

    /**
     * Cria um objeto Cenario.
     *
     *  @param id Numeração do cenário de aposta.
     * @param descricao A descrição não pode ser vazia ou nula.
     * @param bonus Valor do bônus da aposta.
     */
    public CenarioBonus(int id, String descricao, int bonus) {
        super(id, descricao);
        this.valida(bonus);
        this.bonus = bonus;
    }

    /**
     * Calcula o rateio do bônus.
     *
     * @param taxa Taxa do caixa.
     * @return Retorna o calculo do rateio com bônus.
     */
    @Override
    public int calculaRateio(double taxa) {
        return super.calculaRateio(taxa) + this.bonus;
    }

    /**
     * Formatação textual do cenário bônus.
     * @return String formatada: DESCRICAO - ESTADO - BONUS
     */
    public String toString(){
        return this.id + " - " + this.descricao + " - " + this.estado + " - R$ " + String.format("%.2f", this.bonus / 100.0);
    }

    /**
     * Valida os dados específicos do cenário com bônus.
     *
     * @param bonus Valor do bônus não poderá ser menor ou igual a 0.
     */
    private void valida(int bonus) {
        Validador.validaNumeroPositivo(bonus, "Bonus nao pode ser inferior ou igual a 0", false);
    }
}
