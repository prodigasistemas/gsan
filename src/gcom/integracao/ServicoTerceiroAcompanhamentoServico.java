package gcom.integracao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTerceiroAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String codigoServico;

    /** nullable persistent field */
    private String descricaoServico;

    /** nullable persistent field */
    private short indicadorUso;

    /** persistent field */
    private short indicadorExcedente;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private ServicoTerceiroAcompanhamentoServico ServicoTerceiroExcedente;
    

    /** default constructor */
    public ServicoTerceiroAcompanhamentoServico() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public short getIndicadorExcedente() {
		return indicadorExcedente;
	}

	public void setIndicadorExcedente(short indicadorExcedente) {
		this.indicadorExcedente = indicadorExcedente;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ServicoTerceiroAcompanhamentoServico getServicoTerceiroExcedente() {
		return ServicoTerceiroExcedente;
	}

	public void setServicoTerceiroExcedente(
			ServicoTerceiroAcompanhamentoServico servicoTerceiroExcedente) {
		ServicoTerceiroExcedente = servicoTerceiroExcedente;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
