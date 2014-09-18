package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ServicoTipoReferencia extends ObjetoTransacao implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Integer situacaoOsReferenciaAntes;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private Integer situacaoOsReferenciaApos;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorExistenciaOsReferencia;
	
	private short indicadorDiagnostico;
	
	private short indicadorFiscalizacao;
	
	/*
	 * Mantis 402 - Felipe Santos - 16/03/2012
	 * 
	 * Adição dos códigos de Diagnóstico e Fiscalização
	 */
	public final static Integer DIAGONISTICO_ID = new Integer("1");
	public final static Integer FISCALIZACAO_ID = new Integer("2");
	// fim da alteração
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_FISCALIZACAO_SIM = new Short(
			"1");

	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_EXISTENCIA_OS_REFERENCIA_ATIVO = new Short(
			"1");
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_DIAGNOSTICO_ATIVO = new Short(
			"1");

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

	/** full constructor */
	public ServicoTipoReferencia(String descricao, String descricaoAbreviada,
			short indicadorUso, Integer situacaoOsReferenciaApos,
			Integer situacaoOsReferenciaAntes, Date ultimaAlteracao,
			short indicadorExistenciaOsReferencia,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.situacaoOsReferenciaAntes = situacaoOsReferenciaAntes;
		this.indicadorUso = indicadorUso;
		this.situacaoOsReferenciaApos = situacaoOsReferenciaApos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
		this.servicoTipo = servicoTipo;
	}

	/** default constructor */
	public ServicoTipoReferencia() {
	}

	/** minimal constructor */
	public ServicoTipoReferencia(short indicadorUso, Date ultimaAlteracao,
			short indicadorExistenciaOsReferencia,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
		this.servicoTipo = servicoTipo;
	}

	/**
	 * @return Retorna o campo situacaoOsReferenciaAntes.
	 */
	public Integer getSituacaoOsReferenciaAntes() {
		return situacaoOsReferenciaAntes;
	}

	/**
	 * @param situacaoOsReferenciaAntes
	 *            O situacaoOsReferenciaAntes a ser setado.
	 */
	public void setSituacaoOsReferenciaAntes(Integer situacaoOsReferenciaAntes) {
		this.situacaoOsReferenciaAntes = situacaoOsReferenciaAntes;
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

	public Integer getSituacaoOsReferenciaApos() {
		return this.situacaoOsReferenciaApos;
	}

	public void setSituacaoOsReferenciaApos(Integer situacaoOsReferenciaApos) {
		this.situacaoOsReferenciaApos = situacaoOsReferenciaApos;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorExistenciaOsReferencia() {
		return this.indicadorExistenciaOsReferencia;
	}

	public void setIndicadorExistenciaOsReferencia(
			short indicadorExistenciaOsReferencia) {
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return this.servicoTipo;
	}

	public void setServicoTipo(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	
	

	public short getIndicadorDiagnostico() {
		return indicadorDiagnostico;
	}

	public void setIndicadorDiagnostico(short indicadorDiagnostico) {
		this.indicadorDiagnostico = indicadorDiagnostico;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, this.getId()));
		// filtroImovel.adicionarCaminhoParaCarregamentoEntidade
		// ("ligacaoEsgoto");
		filtroServicoTipoReferencia
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		return filtroServicoTipoReferencia;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public short getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(short indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}
	
	

}
