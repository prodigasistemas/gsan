package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarGuiaDevolucaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String atualizar;
	
	private String idImovel;
	
	private String idImovelHidden;

	private String descricaoImovel;

	private String idCliente;
	
	private String idClienteHidden;

	private String nomeCliente;

	private String clienteRelacaoTipo;
	
	private String clienteRelacaoTipoHidden;

	private String[] idTipoDebito;

	private String[] idTipoDebitoSelecionados;

	private String[] creditoTipo;

	private String dataEmissaoInicio;

	private String dataEmissaoFim;

	private String dataValidadeInicio;

	private String dataValidadeFim;

	private String periodoArrecadacaoInicio;

	private String periodoArrecadacaoFim;

	private String periodoGuiaInicio;

	private String periodoGuiaFim;

	private String[] documentoTipo;

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public String[] getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(String[] creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}

	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}

	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}

	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}

	public String getDataValidadeFim() {
		return dataValidadeFim;
	}

	public void setDataValidadeFim(String dataValidadeFim) {
		this.dataValidadeFim = dataValidadeFim;
	}

	public String getDataValidadeInicio() {
		return dataValidadeInicio;
	}

	public void setDataValidadeInicio(String dataValidadeInicio) {
		this.dataValidadeInicio = dataValidadeInicio;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String[] getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(String[] documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String[] getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String[] idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String[] getIdTipoDebitoSelecionados() {
		return idTipoDebitoSelecionados;
	}

	public void setIdTipoDebitoSelecionados(String[] idTipoDebitoSelecionados) {
		this.idTipoDebitoSelecionados = idTipoDebitoSelecionados;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}

	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}

	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}

	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}

	public String getPeriodoGuiaFim() {
		return periodoGuiaFim;
	}

	public void setPeriodoGuiaFim(String periodoGuiaFim) {
		this.periodoGuiaFim = periodoGuiaFim;
	}

	public String getPeriodoGuiaInicio() {
		return periodoGuiaInicio;
	}

	public void setPeriodoGuiaInicio(String periodoGuiaInicio) {
		this.periodoGuiaInicio = periodoGuiaInicio;
	}
	
//	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
//		
//	}

	public String getClienteRelacaoTipoHidden() {
		return clienteRelacaoTipoHidden;
	}

	public void setClienteRelacaoTipoHidden(String clienteRelacaoTipoHidden) {
		this.clienteRelacaoTipoHidden = clienteRelacaoTipoHidden;
	}

	public String getIdClienteHidden() {
		return idClienteHidden;
	}

	public void setIdClienteHidden(String idClienteHidden) {
		this.idClienteHidden = idClienteHidden;
	}

	public String getIdImovelHidden() {
		return idImovelHidden;
	}

	public void setIdImovelHidden(String idImovelHidden) {
		this.idImovelHidden = idImovelHidden;
	}

}
