/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.efi;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 */
public class Split implements Serializable {

    private String divisaoTarifa;
    private MinhaParte minhaParte;
    private List<Repasse> repasses;

    public Split() {
    }

    public Split(String divisaoTarifa, MinhaParte minhaParte, List<Repasse> repasses) {
        this.divisaoTarifa = divisaoTarifa;
        this.minhaParte = minhaParte;
        this.repasses = repasses;
    }

    public String getDivisaoTarifa() {
        return divisaoTarifa;
    }

    public void setDivisaoTarifa(String divisaoTarifa) {
        this.divisaoTarifa = divisaoTarifa;
    }

    public MinhaParte getMinhaParte() {
        return minhaParte;
    }

    public void setMinhaParte(MinhaParte minhaParte) {
        this.minhaParte = minhaParte;
    }

    public List<Repasse> getRepasses() {
        return repasses;
    }

    public void setRepasses(List<Repasse> repasses) {
        this.repasses = repasses;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String divisaoTarifa;
        private MinhaParte minhaParte;
        private List<Repasse> repasses;

        private Builder() {
        }

        public Builder divisaoTarifa(String divisaoTarifa) {
            this.divisaoTarifa = divisaoTarifa;
            return this;
        }

        public Builder minhaParte(MinhaParte minhaParte) {
            this.minhaParte = minhaParte;
            return this;
        }

        public Builder repasses(List<Repasse> repasses) {
            this.repasses = repasses;
            return this;
        }

        public Split build() {
            return new Split(divisaoTarifa, minhaParte, repasses);
        }

    }
}
