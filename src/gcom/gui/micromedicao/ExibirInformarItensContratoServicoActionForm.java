package gcom.gui.micromedicao;

import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ItemServicoContrato;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0xxx] Informar Valor de Item de Servico Por Contrato
 * 
 * @author Hugo Leonardo, Diogo Peixoto
 * @date 22/07/2010, 19/05/2011
 */

public class ExibirInformarItensContratoServicoActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String idContrato;

	private String idNumeroContrato;

	private String dtInicioContrato;
	
	private String dtFimContrato;
	
	private String idItemContrato;
	
	private String valorItemContrato;

	private String valorGlobalContrato;
	
	private String percentualTaxaSucesso;
	
	private String dataInicioAditivo;
	
	private String dataFimAditivo;
	
	private String valorAditivoContrato;
	
	private String percentualTaxaSucessoAdivito;
	
	private String idContEmp;
	
	private Collection<ItemServicoContrato> colecaoItensContrato = new ArrayList<ItemServicoContrato>();

	private Collection<ContratoEmpresaAditivo> colecaoAditivo = new ArrayList<ContratoEmpresaAditivo>();
	
	private String observacao;

	private String idServicoTipo;

	private String descricaoServicoTipo;
	
	private String percentualServicosNaoExecutados;
	
	private String percentualMultaAplicar;
	
	private String quantidadeOrcadaItemContrato;
	
	private String valorOrcadoItemContrato;

	public void getReset(){
		this.idEmpresa = null;
		this.idContrato = null;
		this.idNumeroContrato = null;
		this.dtInicioContrato = null;
		this.dtFimContrato = null;
		this.idItemContrato = null;
		this.valorItemContrato = null;
	}
	
	public String getDtFimContrato() {
		return dtFimContrato;
	}

	public void setDtFimContrato(String dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	
	public String getIdNumeroContrato() {
		return idNumeroContrato;
	}

	public void setIdNumeroContrato(String idNumeroContrato) {
		this.idNumeroContrato = idNumeroContrato;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Collection<ItemServicoContrato> getColecaoItensContrato() {
		return colecaoItensContrato;
	}

	public void setColecaoItensContrato(
			Collection<ItemServicoContrato> colecaoItensContrato) {
		this.colecaoItensContrato = colecaoItensContrato;
	}

	public String getIdItemContrato() {
		return idItemContrato;
	}

	public void setIdItemContrato(String idItemContrato) {
		this.idItemContrato = idItemContrato;
	}

	public String getValorItemContrato() {
		return valorItemContrato;
	}

	public void setValorItemContrato(String valorItemContrato) {
		this.valorItemContrato = valorItemContrato;
	}

	public String getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(String percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}

	public String getValorGlobalContrato() {
		return valorGlobalContrato;
	}

	public void setValorGlobalContrato(String valorGlobalContrato) {
		this.valorGlobalContrato = valorGlobalContrato;
	}

	public String getDataFimAditivo() {
		return dataFimAditivo;
	}

	public void setDataFimAditivo(String dataFimAditivo) {
		this.dataFimAditivo = dataFimAditivo;
	}

	public String getDataInicioAditivo() {
		return dataInicioAditivo;
	}

	public void setDataInicioAditivo(String dataInicioAditivo) {
		this.dataInicioAditivo = dataInicioAditivo;
	}

	public String getPercentualTaxaSucessoAditivo() {
		return percentualTaxaSucessoAdivito;
	}

	public void setPercentualTaxaSucessoAditivo(String percentualTaxaSucessoAdivito) {
		this.percentualTaxaSucessoAdivito = percentualTaxaSucessoAdivito;
	}

	public String getValorAditivoContrato() {
		return valorAditivoContrato;
	}

	public void setValorAditivoContrato(String valorAditivoContrato) {
		this.valorAditivoContrato = valorAditivoContrato;
	}

	public String getIdContEmp() {
		return idContEmp;
	}

	public void setIdContEmp(String idContEmp) {
		this.idContEmp = idContEmp;
	}

	public Collection<ContratoEmpresaAditivo> getColecaoAditivo() {
		return colecaoAditivo;
	}

	public void setColecaoAditivo(Collection<ContratoEmpresaAditivo> colecaoAditivo) {
		this.colecaoAditivo = colecaoAditivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getPercentualTaxaSucessoAdivito() {
		return percentualTaxaSucessoAdivito;
	}

	public void setPercentualTaxaSucessoAdivito(String percentualTaxaSucessoAdivito) {
		this.percentualTaxaSucessoAdivito = percentualTaxaSucessoAdivito;
	}

	public String getPercentualServicosNaoExecutados() {
		return percentualServicosNaoExecutados;
	}

	public void setPercentualServicosNaoExecutados(
			String percentualServicosNaoExecutados) {
		this.percentualServicosNaoExecutados = percentualServicosNaoExecutados;
	}

	public String getPercentualMultaAplicar() {
		return percentualMultaAplicar;
	}

	public void setPercentualMultaAplicar(String percentualMultaAplicar) {
		this.percentualMultaAplicar = percentualMultaAplicar;
	}

	public String getQuantidadeOrcadaItemContrato() {
		return quantidadeOrcadaItemContrato;
	}

	public void setQuantidadeOrcadaItemContrato(String quantidadeOrcadaItemContrato) {
		this.quantidadeOrcadaItemContrato = quantidadeOrcadaItemContrato;
	}

	public String getValorOrcadoItemContrato() {
		return valorOrcadoItemContrato;
	}

	public void setValorOrcadoItemContrato(String valorOrcadoItemContrato) {
		this.valorOrcadoItemContrato = valorOrcadoItemContrato;
	}
}
