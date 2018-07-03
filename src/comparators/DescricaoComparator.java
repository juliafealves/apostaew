package comparators;

import entities.Cenario;

import java.util.Comparator;

/**
 * Classe responsável por comparar dois cenários por descrição em ordem crescente.
 */
public class DescricaoComparator implements Comparator<Cenario> {

    /**
     * Compara dois cenários em ordem crescente em relação a descrição.
     * Em caso de empate, será considerado o que tiver menor identificador.
     *
     * @param cenario Primeiro cenário.
     * @param outroCenario Outro cenário.
     * @return Retorna a posição relacionadas aos cenários de aposta.
     */
    @Override
    public int compare(Cenario cenario, Cenario outroCenario) {
        int comparacao = cenario.getDescricao().compareTo(outroCenario.getDescricao());

        if(comparacao == 0)
            return cenario.getId() - outroCenario.getId();

        return comparacao;
    }
}
