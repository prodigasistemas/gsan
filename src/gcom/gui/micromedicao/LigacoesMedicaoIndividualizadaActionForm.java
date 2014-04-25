package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class LigacoesMedicaoIndividualizadaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idImovel;
	private String inscricaoImovel;
	private String qtdEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String consumoMedia;
	private String consumoMedido;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String idLeituraAnormalidade;
	private String consumoAnormalidade;
	private String consumoTipo;
	private String idMedicaoHistorico;
	private String idConsumoHistorico;
	private String dsAbreviadaAnormalidadeConsumo;
	private String dsAbreviadaTipoConsumo; 
	
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
    	idImovel = null;
    	inscricaoImovel = null;
    	qtdEconomias = null;
    	leituraAnterior = null;
    	dataLeituraAnterior = null;
    	leituraAtual = null;
    	dataLeituraAtual = null;
    	consumoMedia = null;
    	consumoMedido = null;
    	consumoFaturado = null;
    	rateio = null;
    	consumoEsgoto = null;
    	idLeituraAnormalidade = null;
    	consumoAnormalidade = null;
    	consumoTipo = null;
    	idMedicaoHistorico = null;
    	idConsumoHistorico = null;
    	dsAbreviadaAnormalidadeConsumo = null;
    	dsAbreviadaTipoConsumo = null; 
    }

	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public String getConsumoMedia() {
		return consumoMedia;
	}

	public void setConsumoMedia(String consumoMedia) {
		this.consumoMedia = consumoMedia;
	}

	public String getConsumoMedido() {
		return consumoMedido;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public String getConsumoTipo() {
		return consumoTipo;
	}

	public void setConsumoTipo(String consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getDsAbreviadaAnormalidadeConsumo() {
		return dsAbreviadaAnormalidadeConsumo;
	}

	public void setDsAbreviadaAnormalidadeConsumo(
			String dsAbreviadaAnormalidadeConsumo) {
		this.dsAbreviadaAnormalidadeConsumo = dsAbreviadaAnormalidadeConsumo;
	}

	public String getDsAbreviadaTipoConsumo() {
		return dsAbreviadaTipoConsumo;
	}

	public void setDsAbreviadaTipoConsumo(String dsAbreviadaTipoConsumo) {
		this.dsAbreviadaTipoConsumo = dsAbreviadaTipoConsumo;
	}

	public String getIdConsumoHistorico() {
		return idConsumoHistorico;
	}

	public void setIdConsumoHistorico(String idConsumoHistorico) {
		this.idConsumoHistorico = idConsumoHistorico;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}

	public void setIdLeituraAnormalidade(String idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}

	public String getIdMedicaoHistorico() {
		return idMedicaoHistorico;
	}

	public void setIdMedicaoHistorico(String idMedicaoHistorico) {
		this.idMedicaoHistorico = idMedicaoHistorico;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(String qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

}
