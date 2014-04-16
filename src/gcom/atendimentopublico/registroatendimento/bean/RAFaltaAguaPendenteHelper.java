package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o
 * resultado do [UC0408] Atualizar Registro de Atendimento 
 * 
 * @author Sávio Luiz
 * @date 21/08/2006
 * 
 */
public class RAFaltaAguaPendenteHelper {

	// -------------------------------------------

	// parte do [SB0019] - Exibe Registros de Atendimento de Falta de A´gua no
	// imóvel da Área do Bairro

	private Integer idSolicitacaoTipo;

	private String descricaoSolicitacaoTipo;

	private Integer idSolicitacaoTipoEspecificacao;

	private String descricaoSolicitacaoTipoEspecificacao;

	private Integer codigoBairro;

	private String nomeBairro;

	private Integer idBairroArea;

	private String nomeBairroArea;

	private Collection colecaoExibirRAFaltaAguaHelper;

	public Integer getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(Integer codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public Collection getColecaoExibirRAFaltaAguaHelper() {
		return colecaoExibirRAFaltaAguaHelper;
	}

	public void setColecaoExibirRAFaltaAguaHelper(
			Collection colecaoExibirRAFaltaAguaHelper) {
		this.colecaoExibirRAFaltaAguaHelper = colecaoExibirRAFaltaAguaHelper;
	}

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public Integer getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(Integer idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeBairroArea() {
		return nomeBairroArea;
	}

	public void setNomeBairroArea(String nomeBairroArea) {
		this.nomeBairroArea = nomeBairroArea;
	}

	// -------------------------------------------

}
