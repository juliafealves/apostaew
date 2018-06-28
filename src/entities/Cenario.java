package entities;

import enums.EstadoEnum;
import enums.PrevisaoEnum;
import utils.Validador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cenario {
    protected String descricao;
    protected EstadoEnum estado;
    protected int numeracao;
    protected List<Aposta> apostas;

    /**
     * Cria um objeto Cenario.
     * @param descricao A descrição não pode ser vazia ou nula.
     */
    public Cenario(String descricao, int numeracao){
        Validador.validaNaoNulo(descricao, "Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, "Descricao nao pode ser vazia");
        Validador.validaNumeroPositivo(numeracao, "Numeracao nao pode ser inferior ou igual a 0", false);

        this.descricao = descricao;
        this.numeracao = numeracao;
        this.estado = EstadoEnum.NAO_FINALIZADO;
        this.apostas = new ArrayList<>();
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
        return this.numeracao + " - " + this.descricao + " - " + this.estado;
    }

    /**
     * Adiciona uma aposta no cenário.
     *
     * @param apostador Nome do apostador
     * @param valor Valor da Aposta.
     * @param previsao Previsão da aposta.
     */
    public void adicionaAposta(String apostador, int valor, String previsao) {
        this.apostas.add(new Aposta(apostador, valor, previsao));
    }

    /**
     * Finaliza um cenário de aposta e calcula os valores das apostas.
     * @param ocorreu Valor booleano onde diz se ocorreu ou não o cenário de aposta.
     */
    public void finaliza(boolean ocorreu) {
        if(!this.finalizado()) {
            this.estado = ocorreu ? EstadoEnum.FINALIZADO_OCORREU : EstadoEnum.FINALIZADO_NAO_OCORREU;
        }
    }

    /**
     * Verifica se um cenário de aposta foi finalizado.
     * @return Retorna true caso um cenário foi finalizado.
     */
    public boolean finalizado(){
        return this.estado.equals(EstadoEnum.FINALIZADO_OCORREU) || this.estado.equals(EstadoEnum.FINALIZADO_NAO_OCORREU);
    }

    /**
     * Calcula os valores das apostas de acordo com o resultado da previsão.
     *
     * @param apostasVencedoras "Flag" para calcular aposta de acordo com aposta vencedora ou não.
     * @return Retorna o valor total das apostas caso vencedora ou perdedora.
     */
    public int calculaApostas(boolean apostasVencedoras) {
        int valor = 0;

        if(this.finalizado()) {
            PrevisaoEnum previsao = this.estado.equals(EstadoEnum.FINALIZADO_OCORREU) ? PrevisaoEnum.VAI_ACONTECER : PrevisaoEnum.NAO_VAI_ACONTECER;

            if(!apostasVencedoras)
                previsao = this.estado.equals(EstadoEnum.FINALIZADO_OCORREU) ? PrevisaoEnum.NAO_VAI_ACONTECER : PrevisaoEnum.VAI_ACONTECER;

            for(Aposta aposta : this.apostas)
                if(aposta.getPrevisao().equals(previsao))
                    valor += aposta.getValor();
        }

        return valor;
    }

    /**
     * Calcula o rateio do cenário.
     *
     * @param taxa Taxa do caixa.
     * @return Valor das apostas a serem destinadas aos ganhadores.
     */
    public int calculaRateio(double taxa){
        int valor = this.calculaApostas(false);

        return valor - (int) (valor * taxa);
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     *
     * @return Total de apostas feitas.
     */
    public int obtemTotalApostas() {
        return this.apostas.size();
    }

    /**
     * Retorna a soma do valor total das apostas.
     *
     * @return Total das apostas realizadas.
     */
    public int obtemValorTotalApostas(){
        return apostas.stream().mapToInt(Aposta::getValor).sum();
    }

    /**
     * Listas as apostas de um cenário.
     *
     * @return Representação textual: Apostador - Valor - Previsão.
     */
    public String listaApostas(){
        StringBuilder apostas = new StringBuilder();

        for(Aposta aposta : this.apostas)
            apostas.append(aposta).append(System.lineSeparator());

        return apostas.toString();
    }
}
