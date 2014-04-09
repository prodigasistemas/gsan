package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarRegistroAtendimentoDevolucaoValoresActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String idImovel;
	
	private String idImovelHidden;

	private String descricaoImovel;

	private String dataAtendimentoInicio;

	private String dataAtendimentoFim;
	
	private String numeroRA;
	
	private String[] perfilImovel;
	
	private String idRAConsulta;
	private String idImovelSelecionado;
	private String nomeClienteUsuarioImovelSelecionado;
	private String idsConta;
	private String totalPagamentoSelecionado = "0,00";
	private String totalCreditoAbatido = "0,00";
		
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	public String getDataAtendimentoFim() {
		return dataAtendimentoFim;
	}
	public void setDataAtendimentoFim(String dataAtendimentoFim) {
		this.dataAtendimentoFim = dataAtendimentoFim;
	}
	public String getDataAtendimentoInicio() {
		return dataAtendimentoInicio;
	}
	public void setDataAtendimentoInicio(String dataAtendimentoInicio) {
		this.dataAtendimentoInicio = dataAtendimentoInicio;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdImovelHidden() {
		return idImovelHidden;
	}
	public void setIdImovelHidden(String idImovelHidden) {
		this.idImovelHidden = idImovelHidden;
	}
	public String getIdImovelSelecionado() {
		return idImovelSelecionado;
	}
	public void setIdImovelSelecionado(String idImovelSelecionado) {
		this.idImovelSelecionado = idImovelSelecionado;
	}
	public String getIdRAConsulta() {
		return idRAConsulta;
	}
	public void setIdRAConsulta(String idRAConsulta) {
		this.idRAConsulta = idRAConsulta;
	}
	public String getIdsConta() {
		return idsConta;
	}
	public void setIdsConta(String idsConta) {
		this.idsConta = idsConta;
	}
	public String getNomeClienteUsuarioImovelSelecionado() {
		return nomeClienteUsuarioImovelSelecionado;
	}
	public void setNomeClienteUsuarioImovelSelecionado(
			String nomeClienteUsuarioImovelSelecionado) {
		this.nomeClienteUsuarioImovelSelecionado = nomeClienteUsuarioImovelSelecionado;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getTotalPagamentoSelecionado() {
		return totalPagamentoSelecionado;
	}
	public void setTotalPagamentoSelecionado(String totalPagamentoSelecionado) {
		this.totalPagamentoSelecionado = totalPagamentoSelecionado;
	}
	public String getTotalCreditoAbatido() {
		return totalCreditoAbatido;
	}
	public void setTotalCreditoAbatido(String totalCreditoAbatido) {
		this.totalCreditoAbatido = totalCreditoAbatido;
	}
	
	
}
