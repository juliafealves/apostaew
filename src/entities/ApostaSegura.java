package entities;

public class ApostaSegura extends Aposta {

    /**
     * Inicializa uma aposta.
     *
     * @param id
     * @param apostador Nome do apostador.
     * @param valor     Valor da aposta.
     * @param previsao  Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public ApostaSegura(int id, String apostador, int valor, String previsao) {
        super(id, apostador, valor, previsao);
    }
}
