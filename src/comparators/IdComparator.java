package comparators;

import entities.Cenario;

import java.util.Comparator;

/**
 * Classe responsável por comparar dois cenários por identificador em ordem crescente.
 */
public class IdComparator implements Comparator<Cenario> {

    /**
     * Compara dois cenários em ordem crescente em relação o identificador.
     *
     * @param cenario Primeiro cenário.
     * @param outroCenario Outro cenário.
     * @return Retorna a posição relacionadas aos cenários de aposta.
     */
    @Override
    public int compare(Cenario cenario, Cenario outroCenario) {
        return cenario.getId() - outroCenario.getId();
    }
}
