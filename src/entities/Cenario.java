package entities;

import enums.EstadoEnum;
import sun.awt.geom.Curve;
import utils.Validador;

import java.util.*;

public class Cenario {
    /**
     * Identificador único.
     */
    protected int id;

    /**
     * Descrição do cenário.
     */
    protected String descricao;

    /**
     * Estado atual do cenário poderá ser "Nao finalizado", "Finalizado (ocorreu)", "Finalizado (n ocorreu)".
     */
    protected EstadoEnum estado;

    /**
     * Apostas cadastradas no cenário.
     */
    protected Map<Integer, Aposta> apostas;

    /**
     * Apostas seguras cadastradas no cenário.
     */
    protected Map<Integer, ApostaSegura> apostasSeguras;

    /**
     * Cria um objeto Cenario.
     *
     * @param id Identificador único do cenário.
     * @param descricao Descrição do cenário de aposta.
     */
    public Cenario(int id, String descricao){
        this.valida(descricao, id);
        this.descricao = descricao;
        this.id = id;
        this.estado = EstadoEnum.NAO_FINALIZADO;
        this.apostas = new HashMap<>();
        this.apostasSeguras = new HashMap<>();
    }

    /**
     * Adiciona uma aposta no cenário.
     *
     * @param apostador Nome do apostador
     * @param valor Valor da Aposta.
     * @param previsao Previsão da aposta.
     */
    public int adicionaAposta(String apostador, int valor, String previsao) {
        int id = this.obtemTotalApostas() + 1;
        this.apostas.put(id, new Aposta(id, apostador, valor, previsao));

        return id;
    }

    /**
     * Adiciona uma aposta segurada do tipo valor no cenário.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta
     * @param previsao Previsão da aposta.
     * @param valorAssegurado Valor assegurada da aposta.
     * @return Retorna o identificador da aposta assegurada.
     */
    public int adicionaAposta(String apostador, int valor, String previsao, int valorAssegurado) {
        int id = this.obtemTotalApostas() + 1;
        this.apostasSeguras.put(id, new ApostaSegura(id, apostador, valor, previsao, valorAssegurado));

        return id;
    }

    /**
     * Adiciona uma aposta segurada do tipo taxa no cenário.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta
     * @param previsao Previsão da aposta.
     * @param taxa Taxa da aposta.
     * @return Retorna o identificador da aposta assegurada.
     */
    public int adicionaAposta(String apostador, int valor, String previsao, double taxa) {
        int id = this.obtemTotalApostas() + 1;
        this.apostasSeguras.put(id, new ApostaSegura(id, apostador, valor, previsao, taxa));

        return id;
    }

    /**
     * Calcula os valores das apostas de acordo com o resultado da previsão.
     *
     * @return Retorna o valor total das apostas perdedoras.
     */
    public int calculaApostas() {
        int valor = 0;

        if(this.finalizado()) {
            boolean ocorreu = this.estado.equals(EstadoEnum.FINALIZADO_OCORREU);

            for(Aposta aposta : this.apostas.values())
                if(aposta.getPrevisao().equals(Aposta.getPrevisaoPerdedora(ocorreu)))
                    valor += aposta.getValor();

            for(Aposta aposta : this.apostasSeguras.values())
                if(aposta.getPrevisao().equals(Aposta.getPrevisaoPerdedora(ocorreu)))
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
        int valor = this.calculaApostas();

        return valor - (int) (valor * taxa);
    }

    /**
     * Retorna os valores assegurados pelas apostas.
     *
     * @return Valor em centavos das apostas asseguradas.
     */
    public int calcularValorAssegurado() {
        int valor = 0;

        if(this.finalizado()) {
            boolean ocorreu = this.estado.equals(EstadoEnum.FINALIZADO_OCORREU);

            for(ApostaSegura aposta : this.apostasSeguras.values())
                if(aposta.getPrevisao().equals(Aposta.getPrevisaoPerdedora(ocorreu)))
                    valor += aposta.getValorAssegurado();
        }

        return valor;
    }

    /**
     * Verifica se dois objetos Cenários são iguais, através do atributo id.
     *
     * @param objeto Objeto a ser analizado.
     * @return Retorna true caso possuam o mesmo identificador.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Cenario cenario = (Cenario) objeto;

        return id == cenario.id;
    }

    /**
     * Finaliza um cenário de aposta e calcula os valores das apostas.
     *
     * @param ocorreu Valor booleano onde diz se ocorreu ou não o cenário de aposta.
     */
    public void finaliza(boolean ocorreu) {
        if(!this.finalizado()) {
            this.estado = ocorreu ? EstadoEnum.FINALIZADO_OCORREU : EstadoEnum.FINALIZADO_NAO_OCORREU;
        }
    }

    /**
     * Verifica se um cenário de aposta foi finalizado.
     *
     * @return Retorna true caso um cenário foi finalizado.
     */
    public boolean finalizado(){
        return this.estado.equals(EstadoEnum.FINALIZADO_OCORREU) || this.estado.equals(EstadoEnum.FINALIZADO_NAO_OCORREU);
    }

    /**
     * Retorna a descrição do cenário.
     * @return Descrição da aposta.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Retorna o identificador do cenário.
     *
     * @return Identificador do cenário.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gera o hash através do atributo id.
     *
     * @return Hash referente a id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     *
     * @return Total de apostas feitas.
     */
    public int obtemTotalApostas() {
        return this.apostas.size() + this.apostasSeguras.size();
    }

    /**
     * Retorna a soma do valor total das apostas.
     *
     * @return Total das apostas realizadas.
     */
    public int obtemValorTotalApostas(){
        int total = 0;
        total += this.apostas.values().stream().mapToInt(Aposta::getValor).sum();
        total += this.apostasSeguras.values().stream().mapToInt(ApostaSegura::getValor).sum();

        return total;
    }

    /**
     * Listas as apostas de um cenário.
     *
     * @return Representação textual: Apostador - Valor - Previsão.
     */
    public String listaApostas(){
        StringBuilder apostas = new StringBuilder();
        int total = this.obtemTotalApostas();

        for(int i = 1; i <= total; i++){
            if(this.apostas.containsKey(i)) {
                apostas.append(this.apostas.get(i).toString()).append(System.lineSeparator());
            } else if(this.apostasSeguras.containsKey(i)) {
                apostas.append(this.apostasSeguras.get(i).toString()).append(System.lineSeparator());
            }
        }

        return apostas.toString();
    }

    /**
     * Modifica os tipos de seguro para valor.
     *
     * @param id Identificador de aposta.
     * @param valorAssegurado Valor assegurada da aposta.
     * @return Retorna o identificador de aposta.
     */
    public int modificaTipoAposta(int id, int valorAssegurado) {
        if(this.finalizado())
            throw new IllegalArgumentException("Cenario ja esta fechado.");

        if(!this.apostasSeguras.containsKey(id))
            throw new IndexOutOfBoundsException("Aposta nao cadastrada.");

        this.apostasSeguras.get(id).modificaSeguro(valorAssegurado);

        return id;
    }

    /**
     * Modifica o tipo de seguro para taxa.
     *
     * @param id Identificador de aposta.
     * @param taxa Taxa da aposta.
     * @return Retorna o identificador de aposta.
     */
    public int modificaTipoAposta(int id, double taxa) {
        if(this.finalizado())
            throw new IllegalArgumentException("Cenario ja esta fechado.");

        if(!this.apostasSeguras.containsKey(id))
            throw new IndexOutOfBoundsException("Aposta nao cadastrada.");

        this.apostasSeguras.get(id).modificaSeguro(taxa);

        return id;
    }

    /**
     * Formatação textual do cenário de aposta.
     *
     * @return String formatada: NUMERACAO - DESCRICAO - ESTADO
     */
    public String toString(){
        return this.id + " - " + this.descricao + " - " + this.estado;
    }

    /**
     * Valida os dados de cenário.
     *
     * @param descricao Descrição não deverá ser nula ou vazia.
     * @param id Identificador não deverá ser menor ou igual a 0.
     */
    private void valida(String descricao, int id) {
        Validador.validaNaoNulo(descricao, "Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, "Descricao nao pode ser vazia");
        Validador.validaNumeroPositivo(id, "Identificador nao pode ser inferior ou igual a 0", false);
    }
}
