package utils;

public class Validador extends Validador {

    /**
     * Valida a descrição do Cenário. Valida se não é nulo e se não é vazio.
     *
     * @param descricao
     * @return
     */
    public boolean validaDescricao(String descricao){
        String campo = "Descrição";
        this.validaNaoNulo(descricao, campo);
        this.validaNaoVazio(descricao, campo);

        return true;
    }
}
