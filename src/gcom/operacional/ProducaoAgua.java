package gcom.operacional;

import gcom.cadastro.localidade.Localidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ProducaoAgua implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesReferencia;

    /** persistent field */
    private BigDecimal volumeProduzido;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Localidade localidade;

    /** full constructor */
    public ProducaoAgua(Integer id, int anoMesReferencia, BigDecimal volumeProduzido, Date ultimaAlteracao, Localidade localidade) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.volumeProduzido = volumeProduzido;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
    }

    /** default constructor */
    public ProducaoAgua() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public BigDecimal getVolumeProduzido() {
        return this.volumeProduzido;
    }

    public void setVolumeProduzido(BigDecimal volumeProduzido) {
        this.volumeProduzido = volumeProduzido;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	/**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        String mesAno = null;
        String mes = null;
        String ano = null;

        if (this.anoMesReferencia != null
                && !this.anoMesReferencia.toString().trim()
                        .equalsIgnoreCase("")) {
            String anoMes = this.anoMesReferencia.toString();

            mes = anoMes.substring(4, 6);
            ano = anoMes.substring(0, 4);
            mesAno = mes + "/" + ano;
        }
        return mesAno;
    }

}
