package comparators;

import entities.Cenario;

import java.util.Comparator;

/**
 * Classe responsável por comparar dois cenários pelo total de apostas, em ordem descrente.
 */
public class TotalApostasComparator implements Comparator<Cenario> {

    /**
     * Compara dois cenários em ordem crescente em relação ao total de apostas, em ordem descrente.
     * Em caso de empate, será considerado o que tiver menor identificador.
     *
     * @param cenario Primeiro cenário.
     * @param outroCenario Outro cenário.
     * @return Retorna a posição relacionadas aos cenários de aposta.
     */
    @Override
    public int compare(Cenario cenario, Cenario outroCenario) {
        int comparacao = outroCenario.obtemTotalApostas() - cenario.obtemTotalApostas();

        if(comparacao == 0)
            return cenario.getId() - outroCenario.getId();

        return comparacao;
    }
}
