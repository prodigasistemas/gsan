package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ServicoTipoPrioridade extends ObjetoTransacao implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private short indicadorUso;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Short prazoExecucaoInicio;

	/** persistent field */
	private Short prazoExecucaoFim;

	/** persistent field */
	private Date tmCadastramento;

	/** persistent field */
	// private Date dataInicioVigencia;
	/** persistent field */
	// private Date dataFimVigencia;
	/** full constructor */
	public ServicoTipoPrioridade(String descricao, String descricaoAbreviada,
			short indicadorUso, Date ultimaAlteracao,
			short prazoExecucaoInicio, short prazoExecucaoFim,
			Date tmCadastramento) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.prazoExecucaoInicio = prazoExecucaoInicio;
		this.prazoExecucaoFim = prazoExecucaoFim;
		this.tmCadastramento = tmCadastramento;

		// this.dataInicioVigencia = dataInicioVigencia;
		// this.dataFimVigencia = dataFimVigencia;
	}

	/** default constructor */
	public ServicoTipoPrioridade() {
	}

	/** minimal constructor */
	public ServicoTipoPrioridade(String descricao, short indicadorUso,
			Date ultimaAlteracao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		// this.dataInicioVigencia = dataInicioVigencia;
		// this.dataFimVigencia = dataFimVigencia;
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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	// public Date getDataInicioVigencia() {
	// return this.dataInicioVigencia;
	// }

	// public void setDataInicioVigencia(Date dataInicioVigencia) {
	// this.dataInicioVigencia = dataInicioVigencia;
	// }
	//
	// public Date getDataFimVigencia() {
	// return this.dataFimVigencia;
	// }
	//
	// public void setDataFimVigencia(Date dataFimVigencia) {
	// this.dataFimVigencia = dataFimVigencia;
	// }

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo prazoExecucaoFim.
	 */
	public Short getPrazoExecucaoFim() {
		return prazoExecucaoFim;
	}

	/**
	 * @param prazoExecucaoFim
	 *            O prazoExecucaoFim a ser setado.
	 */
	public void setPrazoExecucaoFim(Short prazoExecucaoFim) {
		this.prazoExecucaoFim = prazoExecucaoFim;
	}

	/**
	 * @return Retorna o campo prazoExecucaoInicio.
	 */
	public Short getPrazoExecucaoInicio() {
		return prazoExecucaoInicio;
	}

	/**
	 * @param prazoExecucaoInicio
	 *            O prazoExecucaoInicio a ser setado.
	 */
	public void setPrazoExecucaoInicio(Short prazoExecucaoInicio) {
		this.prazoExecucaoInicio = prazoExecucaoInicio;
	}

	/**
	 * @return Retorna o campo tmCadastramento.
	 */
	public Date getTmCadastramento() {
		return tmCadastramento;
	}

	/**
	 * @param tmCadastramento
	 *            O tmCadastramento a ser setado.
	 */
	public void setTmCadastramento(Date tmCadastramento) {
		this.tmCadastramento = tmCadastramento;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, this.getId()));
		return filtroServicoTipoPrioridade;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
