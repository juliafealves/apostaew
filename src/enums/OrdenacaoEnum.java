package enums;

/**
 * Classe responsável por representar os diferentes tipos de ordenação de um cenário de aposta.
 */
public enum OrdenacaoEnum {
    CADASTRO("cadastro"),
    APOSTAS("apostas"),
    NOME("nome");

    private String ordenacao;

    /**
     * Construtor privado para instanciar os estados do Enum.
     *
     * @param ordenacao Estado dos cenários de aposta.
     */
    OrdenacaoEnum(String ordenacao){
        this.ordenacao = ordenacao;
    }

    /**
     * Retorna o valor da ordenacao.
     *
     * @return Retorna o valor em String do ordenacao.
     */
    public String toString(){
        return this.ordenacao;
    }
}
