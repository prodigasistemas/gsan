package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class CobrancaSituacaoTipo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

    /** identifier field */
	private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorInformarDataFinal;
    
    private Short indicadorEmitirDocumentoCobranca;
    
    
    //Indicador usado para a empresa Terceirizada da CAEMA
    public final static Integer COBRANCA_EMPRESA_TERCEIRIZADA = new Integer(1);

    public Short getIndicadorInformarDataFinal() {
		return indicadorInformarDataFinal;
	}

	public void setIndicadorInformarDataFinal(Short indicadorInformarDataFinal) {
		this.indicadorInformarDataFinal = indicadorInformarDataFinal;
	}

	/** full constructor */
    public CobrancaSituacaoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public CobrancaSituacaoTipo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    public Short getIndicadorEmitirDocumentoCobranca() {
		return indicadorEmitirDocumentoCobranca;
	}

	public void setIndicadorEmitirDocumentoCobranca(
			Short indicadorEmitirDocumentoCobranca) {
		this.indicadorEmitirDocumentoCobranca = indicadorEmitirDocumentoCobranca;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao()+"";
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []atributos = {"descricao"};
		return atributos;	

	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []atributos = {"Descrição"};
		return atributos;
	}	
	@Override
	public Filtro retornaFiltro() {
		FiltroCobrancaSituacaoTipo filtro = new FiltroCobrancaSituacaoTipo();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoTipo.ID,this.getId()));
		
		return filtro;
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}
}
