package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0452] Obter Dados do Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 14/08/2006
 * 
 */
public class ObterDadosRegistroAtendimentoHelper {
	
	private RegistroAtendimento registroAtendimento;
	
	private String descricaoSituacaoRA;
	
	private String enderecoOcorrencia;
	
	private UnidadeOrganizacional unidadeAtendimento;
	
	private UnidadeOrganizacional unidadeAtual;
	
	private RegistroAtendimentoSolicitante solicitante;
	
	private Short codigoExistenciaRAAssociado;
	
	private RegistroAtendimento RAAssociado;
	
	private String tituloNumeroRAAssociado;
	
	private String tituloSituacaoRAAssociado;
	
	private String descricaoSituacaoRAAssociado;
	
	private Short codigoRota;
	
	private Integer sequencialRota;
	
	private Collection colecaoRegistroAtendimentoAnexo;
	
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getDescricaoSituacaoRAAssociado() {
		return descricaoSituacaoRAAssociado;
	}

	public void setDescricaoSituacaoRAAssociado(String descricaoSituacaoRAAssociado) {
		this.descricaoSituacaoRAAssociado = descricaoSituacaoRAAssociado;
	}

	public ObterDadosRegistroAtendimentoHelper(){}	

	public UnidadeOrganizacional getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeOrganizacional unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public Short getCodigoExistenciaRAAssociado() {
		return codigoExistenciaRAAssociado;
	}

	public void setCodigoExistenciaRAAssociado(Short codigoExistenciaRAAssociado) {
		this.codigoExistenciaRAAssociado = codigoExistenciaRAAssociado;
	}

	public String getDescricaoSituacaoRA() {
		return descricaoSituacaoRA;
	}

	public void setDescricaoSituacaoRA(String descricaoSituacaoRA) {
		this.descricaoSituacaoRA = descricaoSituacaoRA;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public RegistroAtendimento getRAAssociado() {
		return RAAssociado;
	}

	public void setRAAssociado(RegistroAtendimento associado) {
		RAAssociado = associado;
	}

	public String getTituloNumeroRAAssociado() {
		return tituloNumeroRAAssociado;
	}

	public void setTituloNumeroRAAssociado(String tituloNumeroRAAssociado) {
		this.tituloNumeroRAAssociado = tituloNumeroRAAssociado;
	}

	public String getTituloSituacaoRAAssociado() {
		return tituloSituacaoRAAssociado;
	}

	public void setTituloSituacaoRAAssociado(String tituloSituacaoRAAssociado) {
		this.tituloSituacaoRAAssociado = tituloSituacaoRAAssociado;
	}

	public RegistroAtendimentoSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(RegistroAtendimentoSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	public Collection getColecaoRegistroAtendimentoAnexo() {
		return colecaoRegistroAtendimentoAnexo;
	}

	public void setColecaoRegistroAtendimentoAnexo(
			Collection colecaoRegistroAtendimentoAnexo) {
		this.colecaoRegistroAtendimentoAnexo = colecaoRegistroAtendimentoAnexo;
	}	
}
