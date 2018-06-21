package entities;

import enums.PrevisaoEnum;
import utils.Validador;

public class Aposta {
    private PrevisaoEnum previsao;
    private int valor;
    private String apostador;

    /**
     * Inicializa uma aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public Aposta(String apostador, int valor, String previsao) {
        this.validaAposta(apostador, valor, previsao);
        this.apostador = apostador;
        this.valor = valor;
        this.previsao = previsao.equalsIgnoreCase(PrevisaoEnum.VAI_ACONTECER.getPrevisao()) ? PrevisaoEnum.VAI_ACONTECER : PrevisaoEnum.NAO_VAI_ACONTECER;
    }

    /**
     * Realiza as validações de aposta.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    private void validaAposta(String apostador, int valor, String previsao){
        // Valida apostador
        Validador.validaNaoNulo(apostador, "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
        // Valida Valor
        Validador.validaNumeroPositivo(valor, "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero", false);
        // Valida Previsao
        Validador.validaNaoNulo(previsao, "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
        String[] previsoes = {PrevisaoEnum.NAO_VAI_ACONTECER.getPrevisao(), PrevisaoEnum.VAI_ACONTECER.getPrevisao()};
        Validador.validaStringIguais(previsao, previsoes, "Erro no cadastro de aposta: Previsao invalida");
    }
}
