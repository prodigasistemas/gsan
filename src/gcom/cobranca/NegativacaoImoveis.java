package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoImoveis implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorExcluido;

    /** nullable persistent field */
    private Date dataExclusao;
    
    /** nullable persistent field */
    private Date dataConfirmacao;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private gcom.cobranca.NegativacaoComando negativacaoComando;

    /** full constructor */
    public NegativacaoImoveis(Integer id, Date ultimaAlteracao, short indicadorExcluido, Date dataExclusao, Imovel imovel, gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorExcluido = indicadorExcluido;
        this.dataExclusao = dataExclusao;
        this.imovel = imovel;
        this.negativacaoComando = negativacaoComando;
    }

    /** default constructor */
    public NegativacaoImoveis() {
    }

    /** minimal constructor */
    public NegativacaoImoveis(Integer id, Date ultimaAlteracao, short indicadorExcluido, Imovel imovel, gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorExcluido = indicadorExcluido;
        this.imovel = imovel;
        this.negativacaoComando = negativacaoComando;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorExcluido() {
        return this.indicadorExcluido;
    }

    public void setIndicadorExcluido(short indicadorExcluido) {
        this.indicadorExcluido = indicadorExcluido;
    }

    public Date getDataExclusao() {
        return this.dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

}
