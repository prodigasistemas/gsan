package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe 
 *
 * @author Rafael Corrêa
 * @date 18/09/2008
 */
public class ConsultarSituacaoEspecialFaturamentoPopupActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String tipo;
	private String consumoFixoNaoMedido;	
	private String consumoFixoMedido;
	private String volumeFixoNaoMedido;	
	private String volumeFixoMedido;
	private String motivo;
	private String mesAnoReferenciaFaturamentoInicial;
	private String mesAnoReferenciaFaturamentoFinal;
	private String mesAnoReferenciaRetirada;
	private String observacaoInforma;
	private String observacaoRetira;
	
	/**
	 * @return Retorna o campo consumoFixoMedido.
	 */
	public String getConsumoFixoMedido() {
		return consumoFixoMedido;
	}
	/**
	 * @param consumoFixoMedido O consumoFixoMedido a ser setado.
	 */
	public void setConsumoFixoMedido(String consumoFixoMedido) {
		this.consumoFixoMedido = consumoFixoMedido;
	}
	/**
	 * @return Retorna o campo consumoFixoNaoMedido.
	 */
	public String getConsumoFixoNaoMedido() {
		return consumoFixoNaoMedido;
	}
	/**
	 * @param consumoFixoNaoMedido O consumoFixoNaoMedido a ser setado.
	 */
	public void setConsumoFixoNaoMedido(String consumoFixoNaoMedido) {
		this.consumoFixoNaoMedido = consumoFixoNaoMedido;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoFinal.
	 */
	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoFinal O mesAnoReferenciaFaturamentoFinal a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoInicial.
	 */
	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoInicial O mesAnoReferenciaFaturamentoInicial a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
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
	 * @return Retorna o campo volumeFixoMedido.
	 */
	public String getVolumeFixoMedido() {
		return volumeFixoMedido;
	}
	/**
	 * @param volumeFixoMedido O volumeFixoMedido a ser setado.
	 */
	public void setVolumeFixoMedido(String volumeFixoMedido) {
		this.volumeFixoMedido = volumeFixoMedido;
	}
	/**
	 * @return Retorna o campo volumeFixoNaoMedido.
	 */
	public String getVolumeFixoNaoMedido() {
		return volumeFixoNaoMedido;
	}
	/**
	 * @param volumeFixoNaoMedido O volumeFixoNaoMedido a ser setado.
	 */
	public void setVolumeFixoNaoMedido(String volumeFixoNaoMedido) {
		this.volumeFixoNaoMedido = volumeFixoNaoMedido;
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
