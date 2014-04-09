package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe 
 *
 * @author Rafael Corrêa
 * @date 18/09/2008
 */
public class ConsultarSituacaoEspecialCobrancaPopupActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String tipo;
	private String motivo;
	private String mesAnoReferenciaCobrancaInicial;
	private String mesAnoReferenciaCobrancaFinal;
	private String mesAnoReferenciaRetirada;
	private String observacaoInforma;
	private String observacaoRetira;
	
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoFinal.
	 */
	public String getMesAnoReferenciaCobrancaFinal() {
		return mesAnoReferenciaCobrancaFinal;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoFinal O mesAnoReferenciaFaturamentoFinal a ser setado.
	 */
	public void setMesAnoReferenciaCobrancaFinal(
			String mesAnoReferenciaCobrancaFinal) {
		this.mesAnoReferenciaCobrancaFinal = mesAnoReferenciaCobrancaFinal;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoInicial.
	 */
	public String getMesAnoReferenciaCobrancaInicial() {
		return mesAnoReferenciaCobrancaInicial;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoInicial O mesAnoReferenciaFaturamentoInicial a ser setado.
	 */
	public void setMesAnoReferenciaCobrancaInicial(
			String mesAnoReferenciaCobrancaInicial) {
		this.mesAnoReferenciaCobrancaInicial = mesAnoReferenciaCobrancaInicial;
	}
	/**
	 * @return Retorna o campo motivo.
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo O motivo a ser setado.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoInforma() {
		return observacaoInforma;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoRetira() {
		return observacaoRetira;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}
	/**
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaRetirada.
	 */
	public String getMesAnoReferenciaRetirada() {
		return mesAnoReferenciaRetirada;
	}
	/**
	 * @param mesAnoReferenciaRetirada O mesAnoReferenciaRetirada a ser setado.
	 */
	public void setMesAnoReferenciaRetirada(String mesAnoReferenciaRetirada) {
		this.mesAnoReferenciaRetirada = mesAnoReferenciaRetirada;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

}
