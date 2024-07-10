/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.efi;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 */
public class Repasse implements java.io.Serializable {

    private String tipo;
    private String valor;
    private Favorecido favorecido;

    public Repasse() {

    }

    public Repasse(String tipo, String valor, Favorecido favorecido) {
        this.tipo = tipo;
        this.valor = valor;
        this.favorecido = favorecido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Favorecido getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(Favorecido favorecido) {
        this.favorecido = favorecido;
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private String tipo;
        private String valor;
        private Favorecido favorecido;

        private Builder(){

        }

        public Builder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public Builder valor(String valor){
            this.valor = valor;
            return this;
        }

        public Builder favorecido(Favorecido favorecido){
            this.favorecido = favorecido;
            return this;
        }

        public Repasse build(){
            return new Repasse(tipo, valor, favorecido);
        }

    }
}
