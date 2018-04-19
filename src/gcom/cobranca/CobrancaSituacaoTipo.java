package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class CobrancaSituacaoTipo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Short indicadorInformarDataFinal;
    private Short indicadorEmitirDocumentoCobranca;
    
    public final static Integer COBRANCA_EMPRESA_TERCEIRIZADA = new Integer(1);
    public final static Integer PARALISAR_ACOES_DE_COBRANÇA = new Integer(5);
    public final static Integer PARALISAR_ARRASTO = new Integer(6);
    public final static Integer PARALISAR_ARRASTO_TODAS_AS_ACOES_DE_COBRANÇA = new Integer(7);
    public final static Integer PARALISAR_ACOES_DE_COBRANCA_E_ACRESCIMOS_IMPONT = new Integer(8);
    public final static Integer PARALISAR_ORDENS_DE_CORTE_E_SUPRESSAO = new Integer(9);
    public final static Integer PARALISAR_ORDENS_DE_SUPRESSAO_TOTAL_E_PARCIAL = new Integer(10);
    public final static Integer SUSPENDER_PAGAMENTO_DA_CONTA = new Integer(11);

    public CobrancaSituacaoTipo() {
    }

    public CobrancaSituacaoTipo(Integer id) {
    	this.id = id;
    }
    
    public CobrancaSituacaoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
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
    
    public Short getIndicadorInformarDataFinal() {
		return indicadorInformarDataFinal;
	}

	public void setIndicadorInformarDataFinal(Short indicadorInformarDataFinal) {
		this.indicadorInformarDataFinal = indicadorInformarDataFinal;
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
