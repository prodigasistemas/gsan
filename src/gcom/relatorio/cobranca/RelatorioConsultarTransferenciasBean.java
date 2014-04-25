package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioConsultarTransferenciasBean implements RelatorioBean {

	private String idImovelOrigem;

	private String idImovelDestino;

	private String dataTransferencia;
	
	private String usuario;

	private String idConta;
	
	private String mesAnoConta;
	
	private String idDebitoACobrar;
	
	private String tipoDebito;
	
	private String idGuiaPagamento;
	
	private String idCreditoARealizar;
	
	private String tipoCredito;
	
	public RelatorioConsultarTransferenciasBean(String idImovelOrigem,
			String idImovelDestino, String dataTransferencia, String usuario,
			String idConta, String mesAnoConta, String idDebitoACobrar, String idGuiaPagamento,
			String tipoDebito, String idCreditoARealizar, String tipoCredito) {
		this.idImovelOrigem = idImovelOrigem;
		this.idImovelDestino = idImovelDestino;
		this.dataTransferencia = dataTransferencia;
		this.usuario = usuario;
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebito = tipoDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @return Retorna o campo dataTransferencia.
	 */
	public String getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia O dataTransferencia a ser setado.
	 */
	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	/**
	 * @return Retorna o campo idConta.
	 */
	public String getIdConta() {
		return idConta;
	}

	/**
	 * @param idConta O idConta a ser setado.
	 */
	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	/**
	 * @return Retorna o campo idCreditoARealizar.
	 */
	public String getIdCreditoARealizar() {
		return idCreditoARealizar;
	}

	/**
	 * @param idCreditoARealizar O idCreditoARealizar a ser setado.
	 */
	public void setIdCreditoARealizar(String idCreditoARealizar) {
		this.idCreditoARealizar = idCreditoARealizar;
	}

	/**
	 * @return Retorna o campo idDebitoACobrar.
	 */
	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	/**
	 * @param idDebitoACobrar O idDebitoACobrar a ser setado.
	 */
	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	/**
	 * @return Retorna o campo idGuiaPagamento.
	 */
	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	/**
	 * @param idGuiaPagamento O idGuiaPagamento a ser setado.
	 */
	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public String getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(String idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public String getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(String idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}

	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	/**
	 * @return Retorna o campo tipoCredito.
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @param tipoCredito O tipoCredito a ser setado.
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}

	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	

}
