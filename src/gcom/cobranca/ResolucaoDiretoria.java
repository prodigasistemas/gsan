package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResolucaoDiretoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro(){
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

		filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO,
				this.getId()));

		
		return filtroResolucaoDiretoria;
	}
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String numeroResolucaoDiretoria;

    /** nullable persistent field */
    private String descricaoAssunto;

    /** nullable persistent field */
    private Date dataVigenciaInicio;

    /** nullable persistent field */
    private Date dataVigenciaFim;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorParcelamentoUnico;
    private Short indicadorUtilizacaoLivre;
    private Short indicadorDescontoSancoes;
    private Short indicadorParcelamentoLojaVirtual;
    private Short indicadorParcelasEmAtraso;
    private Short indicadorParcelamentoEmAndamento;
    private ResolucaoDiretoria rdParcelasEmAtraso;
    private ResolucaoDiretoria rdParcelamentoEmAndamento;
    private Short indicadorNegociacaoSoAVista;
    private Short indicadorDescontoSoEmContaAVista;
    private BigDecimal percentualDoacao;
    
	public BigDecimal getPercentualDoacao() {
		return percentualDoacao;
	}

	public void setPercentualDoacao(BigDecimal percentualDoacao) {
		this.percentualDoacao = percentualDoacao;
	}

	public Short getIndicadorDescontoSoEmContaAVista() {
		return indicadorDescontoSoEmContaAVista;
	}

	public void setIndicadorDescontoSoEmContaAVista(
			Short indicadorDescontoSoEmContaAVista) {
		this.indicadorDescontoSoEmContaAVista = indicadorDescontoSoEmContaAVista;
	}

	public Short getIndicadorNegociacaoSoAVista() {
		return indicadorNegociacaoSoAVista;
	}

	public void setIndicadorNegociacaoSoAVista(Short indicadorNegociacaoSoAVista) {
		this.indicadorNegociacaoSoAVista = indicadorNegociacaoSoAVista;
	}

	public Short getIndicadorParcelamentoUnico() {
		return indicadorParcelamentoUnico;
	}

	public void setIndicadorParcelamentoUnico(Short indicadorParcelamentoUnico) {
		this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;
	}

	public Short getIndicadorUtilizacaoLivre() {
		return indicadorUtilizacaoLivre;
	}

	public void setIndicadorUtilizacaoLivre(Short indicadorUtilizacaoLivre) {
		this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    /** full constructor */
    public ResolucaoDiretoria(String numeroResolucaoDiretoria, String descricaoAssunto, Date dataVigenciaInicio, Date dataVigenciaFim, Date ultimaAlteracao,Short indicadorParcelamentoUnico, Short indicadorUtilizacaoLivre,Short indicadorDescontoSancoes) {
        this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
        this.descricaoAssunto = descricaoAssunto;
        this.dataVigenciaInicio = dataVigenciaInicio;
        this.dataVigenciaFim = dataVigenciaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;          
        this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
        this.indicadorDescontoSancoes = indicadorDescontoSancoes;
    }

    /** default constructor */
    public ResolucaoDiretoria() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroResolucaoDiretoria() {
        return this.numeroResolucaoDiretoria;
    }

    public void setNumeroResolucaoDiretoria(String numeroResolucaoDiretoria) {
        this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
    }

    public String getDescricaoAssunto() {
        return this.descricaoAssunto;
    }

    public void setDescricaoAssunto(String descricaoAssunto) {
        this.descricaoAssunto = descricaoAssunto;
    }

    public Date getDataVigenciaInicio() {
        return this.dataVigenciaInicio;
    }

    public void setDataVigenciaInicio(Date dataVigenciaInicio) {
        this.dataVigenciaInicio = dataVigenciaInicio;
    }

    public Date getDataVigenciaFim() {
        return this.dataVigenciaFim;
    }

    public void setDataVigenciaFim(Date dataVigenciaFim) {
        this.dataVigenciaFim = dataVigenciaFim;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo indicadorDescontoSancoes.
	 */
	public Short getIndicadorDescontoSancoes() {
		return indicadorDescontoSancoes;
	}

	/**
	 * @param indicadorDescontoSancoes O indicadorDescontoSancoes a ser setado.
	 */
	public void setIndicadorDescontoSancoes(Short indicadorDescontoSancoes) {
		this.indicadorDescontoSancoes = indicadorDescontoSancoes;
	}

	public Short getIndicadorParcelamentoEmAndamento() {
		return indicadorParcelamentoEmAndamento;
	}

	public void setIndicadorParcelamentoEmAndamento(
			Short indicadorParcelamentoEmAndamento) {
		this.indicadorParcelamentoEmAndamento = indicadorParcelamentoEmAndamento;
	}

	public Short getIndicadorParcelasEmAtraso() {
		return indicadorParcelasEmAtraso;
	}

	public void setIndicadorParcelasEmAtraso(Short indicadorParcelasEmAtraso) {
		this.indicadorParcelasEmAtraso = indicadorParcelasEmAtraso;
	}

	public ResolucaoDiretoria getRdParcelamentoEmAndamento() {
		return rdParcelamentoEmAndamento;
	}

	public void setRdParcelamentoEmAndamento(
			ResolucaoDiretoria rdParcelamentoEmAndamento) {
		this.rdParcelamentoEmAndamento = rdParcelamentoEmAndamento;
	}

	public ResolucaoDiretoria getRdParcelasEmAtraso() {
		return rdParcelasEmAtraso;
	}

	public void setRdParcelasEmAtraso(ResolucaoDiretoria rdParcelasEmAtraso) {
		this.rdParcelasEmAtraso = rdParcelasEmAtraso;
	}

	public Short getIndicadorParcelamentoLojaVirtual() {
		return indicadorParcelamentoLojaVirtual;
	}

	public void setIndicadorParcelamentoLojaVirtual(
			Short indicadorParcelamentoLojaVirtual) {
		this.indicadorParcelamentoLojaVirtual = indicadorParcelamentoLojaVirtual;
	}	
}
