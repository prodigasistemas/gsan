package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class NegativadorExclusaoMotivo extends ObjetoTransacao implements Serializable {
	
	public static final String PAGAMENTO_DA_DIVIDA  = new String("PAGAMENTO DA DIVIDA");
	public static final String RENEGOCIACAO_DA_DIVIDA  = new String("RENEGOCIAÇÃO DA DIVIDA");
	public static final String MOTIVO_NAO_IDENTIFICADO  = new String("MOTIVO NÃO IDENTIFICADO");

	public Filtro retornaFiltro() {
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoExclusaoMotivo();
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoExclusaoMotivo();
	}

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    private Short codigoExclusaoMotivo;
    
    /** persistent field */
    private String descricaoExclusaoMotivo;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;
    
    /** persistent field */
    private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorExclusaoMotivo(Integer id, short codigoExclusaoMotivo,String descricaoExclusaoMotivo, short indicadorUso, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoExclusaoMotivo = codigoExclusaoMotivo;
        this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorExclusaoMotivo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoExclusaoMotivo() {
        return this.descricaoExclusaoMotivo;
    }

    public void setDescricaoExclusaoMotivo(String descricaoExclusaoMotivo) {
        this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(
			gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo codigoExclusaoMotivo.
	 */
	public Short getCodigoExclusaoMotivo() {
		return codigoExclusaoMotivo;
	}

	/**
	 * @param codigoExclusaoMotivo O codigoExclusaoMotivo a ser setado.
	 */
	public void setCodigoExclusaoMotivo(Short codigoExclusaoMotivo) {
		this.codigoExclusaoMotivo = codigoExclusaoMotivo;
	}

	

	
	
	
	

}
