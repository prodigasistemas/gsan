package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 07/08/2006
 */
public class ConsultarRegistroAtendimentoPendentesImovelActionForm extends
		ActionForm {
	private static final long serialVersionUID = 1L;
	private String idOrdemServico;
	// Dados do Imóvel
	private String matriculaImovel;
	private String inscricaoImovel;
	private String localidade;
	private String setorComercial;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String enderecoImovel;
  
	//Dados da Retirada de Hidrômetro
	
	private String numeroRa;
	private String solicitacaoTipoEspecificacao;
	private String dataRegistroAtendimento;
	private String solicitacaoTipo;
	
	public String getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	public void setSolicitacaoTipo(String solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	public String getDataRegistroAtendimento() {
		return dataRegistroAtendimento;
	}

	public void setDataRegistroAtendimento(String dataRegistroAtendimento) {
		this.dataRegistroAtendimento = dataRegistroAtendimento;
	}

	public String getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(String solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getNumeroRa() {
		return numeroRa;
	}

	public void setNumeroRa(String numeroRa) {
		this.numeroRa = numeroRa;
	}
}
