package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarInformacoesOSBoletimMedicaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	

	private String idOrdemServico;
	private String nomeOrdemServico;
	private String idImovel;
	private String inscricaoImovel;
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String situacaoLigAgua;
	private String situacaoLigEsgoto;
    private String indicadorExistePavimento;
    private String qtdeReposicaoAsfalto;
    private String qtdeReposicaoParalelo;
    private String qtdeReposicaoCalcada;
    //hidden
    private String exibeIndicadorExistePavimento;
    private String exibeQtdeReposicaoAsfalto;
    private String exibeQtdeReposicaoCalcada;
    private String exibeQtdeReposicaoParalelo;
    
	public String getExibeIndicadorExistePavimento() {
		return exibeIndicadorExistePavimento;
	}

	public void setExibeIndicadorExistePavimento(
			String exibeIndicadorExistePavimento) {
		this.exibeIndicadorExistePavimento = exibeIndicadorExistePavimento;
	}

	public String getExibeQtdeReposicaoAsfalto() {
		return exibeQtdeReposicaoAsfalto;
	}

	public void setExibeQtdeReposicaoAsfalto(String exibeQtdeReposicaoAsfalto) {
		this.exibeQtdeReposicaoAsfalto = exibeQtdeReposicaoAsfalto;
	}

	public String getExibeQtdeReposicaoCalcada() {
		return exibeQtdeReposicaoCalcada;
	}

	public void setExibeQtdeReposicaoCalcada(String exibeQtdeReposicaoCalcada) {
		this.exibeQtdeReposicaoCalcada = exibeQtdeReposicaoCalcada;
	}

	public String getExibeQtdeReposicaoParalelo() {
		return exibeQtdeReposicaoParalelo;
	}

	public void setExibeQtdeReposicaoParalelo(String exibeQtdeReposicaoParalelo) {
		this.exibeQtdeReposicaoParalelo = exibeQtdeReposicaoParalelo;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getIndicadorExistePavimento() {
		return indicadorExistePavimento;
	}

	public void setIndicadorExistePavimento(String indicadorExistePavimento) {
		this.indicadorExistePavimento = indicadorExistePavimento;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	public String getQtdeReposicaoAsfalto() {
		return qtdeReposicaoAsfalto;
	}

	public void setQtdeReposicaoAsfalto(String qtdeReposicaoAsfalto) {
		this.qtdeReposicaoAsfalto = qtdeReposicaoAsfalto;
	}

	public String getQtdeReposicaoCalcada() {
		return qtdeReposicaoCalcada;
	}

	public void setQtdeReposicaoCalcada(String qtdeReposicaoCalcada) {
		this.qtdeReposicaoCalcada = qtdeReposicaoCalcada;
	}

	public String getQtdeReposicaoParalelo() {
		return qtdeReposicaoParalelo;
	}

	public void setQtdeReposicaoParalelo(String qtdeReposicaoParalelo) {
		this.qtdeReposicaoParalelo = qtdeReposicaoParalelo;
	}

	public String getSituacaoLigAgua() {
		return situacaoLigAgua;
	}

	public void setSituacaoLigAgua(String situacaoLigAgua) {
		this.situacaoLigAgua = situacaoLigAgua;
	}

	public String getSituacaoLigEsgoto() {
		return situacaoLigEsgoto;
	}

	public void setSituacaoLigEsgoto(String situacaoLigEsgoto) {
		this.situacaoLigEsgoto = situacaoLigEsgoto;
	}	
	
}
