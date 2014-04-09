package gcom.gui.micromedicao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Atualizar Tipo de RateioPopup
 * 
 * @author Rafael Santos
 * @since 11/01/2006 [UC00098] Manter Vinculos Imoveis Rateio Consumo
 */
public class ConsultarHistoricoMedicaoIndividualizadaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String descricaoImovel;
	
	private String inscricaoImovel;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String tipoRateio;
	
	private String quantidadeImoveisVinculados;
	
	private String mesAno;
	
	private String endereco; 
	
	private String idTipoLigacao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		codigoImovel = null;
		
			
	}

	/**
	 * @return Returns the codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel The codigoImovel to set.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the quantidadeImoveisVinculados.
	 */
	public String getQuantidadeImoveisVinculados() {
		return quantidadeImoveisVinculados;
	}

	/**
	 * @param quantidadeImoveisVinculados The quantidadeImoveisVinculados to set.
	 */
	public void setQuantidadeImoveisVinculados(String quantidadeImoveisVinculados) {
		this.quantidadeImoveisVinculados = quantidadeImoveisVinculados;
	}

	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Returns the tipoRateio.
	 */
	public String getTipoRateio() {
		return tipoRateio;
	}

	/**
	 * @param tipoRateio The tipoRateio to set.
	 */
	public void setTipoRateio(String tipoRateio) {
		this.tipoRateio = tipoRateio;
	}

	/**
	 * @return Returns the mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno The mesAno to set.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdTipoLigacao() {
		return idTipoLigacao;
	}

	public void setIdTipoLigacao(String idTipoLigacao) {
		this.idTipoLigacao = idTipoLigacao;
	}


}
