package entities;

import enums.EstadoEnum;

import java.util.Objects;

public class Cenario {
    private String descricao;
    private EstadoEnum estado;
    private int numeracao;

    /**
     * Cria um objeto Cenario.
     * @param descricao A descrição não pode ser vazia ou nula.
     */
    public Cenario(String descricao, int numeracao){
        if(descricao == null)
            throw new NullPointerException("Descrição não deve ser nula.");

        if(descricao.isEmpty())
            throw new IllegalArgumentException("Descrição não deve ser vazia.");

        if(numeracao <= 0)
            throw new NumberFormatException("A numeração deve ser um inteiro positivo.");

        this.descricao = descricao;
        this.numeracao = numeracao;
        this.estado = EstadoEnum.NAO_FINALIZADO;
    }

    /**
     * Verifica se dois objetos Cenários são iguais, através do atributo numeracao.
     * @param objeto Objeto a ser analizado.
     * @return Retorna true caso possuam a mesma numeração.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Cenario cenario = (Cenario) objeto;
        return numeracao == cenario.numeracao;
    }

    /**
     * Gera o hash através do atributo numeracao.
     * @return Hash referente a numeracao.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeracao);
    }

    /**
     * Formatação textual do cenário.
     * @return String formatada: DESCRICAO - ESTADO
     */
    public String toString(){
        return this.numeracao + " - " + this.descricao + " - " + this.estado.getEstado();
    }
}
