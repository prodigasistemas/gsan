package gcom.gui.cadastro.tarifasocial;

import gcom.gui.ControladorAlteracaoGcomActionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class 
 *  asda
 * @author thiago toscano 
 * @created 28 de Junho de 2004
 */ 
public class AtualizarDadosTarifaSocialActionForm extends ControladorAlteracaoGcomActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String salarioMinimo = null;
	private String consumoEnergiaMaximoTarifaSocial = null;
	private String rendaMaximaTarifaSocial = null;
	private String areaMaximaTarifaSocial = null;
	
	private String id;
	private String clienteNome;
	private String complementoEndereco;
	private String numeroCartaoProgramaSocial;
	private String dataValidadeCartao;
	private String numeroMesesAdesao;
	private String consumoCelpe;
	private String numeroIPTU;
	private String numeroContratoCelpe;
	private String valorRendaFamiliar;
	private String tipoCartao;
	private String rendaTipo;
	private String areaConstruida;
	private String areaConstruidaFaixa;
	private String motivoRevisao;
	private String numeroMoradores;
	private String dataComparecimentoCarta;

	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {

    	this.numeroCartaoProgramaSocial = "";
    	this.dataValidadeCartao = "";
    	this.numeroMesesAdesao = "";
    	this.consumoCelpe = "";
    	this.numeroIPTU = "";
    	this.numeroContratoCelpe = "";
    	this.valorRendaFamiliar = "";
    	this.tipoCartao = "-1";
    	this.rendaTipo = "-1";
    	this.areaConstruida = "";
    	this.areaConstruidaFaixa = "-1";
    	this.salarioMinimo = "";
    	this.clienteNome = "";
    	this.complementoEndereco = "";
    	this.areaMaximaTarifaSocial = "";

    	this.consumoEnergiaMaximoTarifaSocial = "";
    	this.rendaMaximaTarifaSocial = "";
    }

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}

	public void setAreaConstruidaFaixa(String areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getConsumoCelpe() {
		return consumoCelpe;
	}

	public void setConsumoCelpe(String consumoCelpe) {
		this.consumoCelpe = consumoCelpe;
	}

	public String getDataValidadeCartao() {
		return dataValidadeCartao;
	}

	public void setDataValidadeCartao(String dataValidadeCartao) {
		this.dataValidadeCartao = dataValidadeCartao;
	}

	public String getId() {
		return id;
	}

	public void setId (String idTarifaSocialDadoEconomia) {
		this.id = idTarifaSocialDadoEconomia;
	}

	public String getNumeroCartaoProgramaSocial() {
		return numeroCartaoProgramaSocial;
	}

	public void setNumeroCartaoProgramaSocial(String numeroCartaoProgramaSocial) {
		this.numeroCartaoProgramaSocial = numeroCartaoProgramaSocial;
	}

	public String getNumeroContratoCelpe() {
		return numeroContratoCelpe;
	}

	public void setNumeroContratoCelpe(String numeroContratoCelpe) {
		this.numeroContratoCelpe = numeroContratoCelpe;
	}

	public String getNumeroIPTU() {
		return numeroIPTU;
	}

	public void setNumeroIPTU(String numeroIPTU) {
		this.numeroIPTU = numeroIPTU;
	}

	public String getNumeroMesesAdesao() {
		return numeroMesesAdesao;
	}

	public void setNumeroMesesAdesao(String numeroMesesAdesao) {
		this.numeroMesesAdesao = numeroMesesAdesao;
	}

	public String getRendaTipo() {
		return rendaTipo;
	}

	public void setRendaTipo(String rendaTipo) {
		this.rendaTipo = rendaTipo;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public String getValorRendaFamiliar() {
		return valorRendaFamiliar;
	}

	public void setValorRendaFamiliar(String valorRendaFamiliar) {
		this.valorRendaFamiliar = valorRendaFamiliar;
	}

	public String getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(String salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public String getConsumoEnergiaMaximoTarifaSocial() {
		return consumoEnergiaMaximoTarifaSocial;
	}

	public void setConsumoEnergiaMaximoTarifaSocial(
			String consumoEnergiaMaximoTarifaSocial) {
		this.consumoEnergiaMaximoTarifaSocial = consumoEnergiaMaximoTarifaSocial;
	}

	public String getRendaMaximaTarifaSocial() {
		return rendaMaximaTarifaSocial;
	}

	public void setRendaMaximaTarifaSocial(String rendaMaximaTarifaSocial) {
		this.rendaMaximaTarifaSocial = rendaMaximaTarifaSocial;
	}

	public String getAreaMaximaTarifaSocial() {
		return areaMaximaTarifaSocial;
	}

	public void setAreaMaximaTarifaSocial(String areaMaximaTarifaSocial) {
		this.areaMaximaTarifaSocial = areaMaximaTarifaSocial;
	}

	/**
	 * @return Retorna o campo motivoRevisao.
	 */
	public String getMotivoRevisao() {
		return motivoRevisao;
	}

	/**
	 * @param motivoRevisao O motivoRevisao a ser setado.
	 */
	public void setMotivoRevisao(String motivoRevisao) {
		this.motivoRevisao = motivoRevisao;
	}

	/**
	 * @return Retorna o campo numeroMoradores.
	 */
	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	/**
	 * @param numeroMoradores O numeroMoradores a ser setado.
	 */
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public String getDataComparecimentoCarta() {
		return dataComparecimentoCarta;
	}

	public void setDataComparecimentoCarta(String dataComparecimentoCarta) {
		this.dataComparecimentoCarta = dataComparecimentoCarta;
	}
	
	
}
