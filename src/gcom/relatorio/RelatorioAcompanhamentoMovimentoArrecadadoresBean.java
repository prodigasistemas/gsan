package gcom.relatorio;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresBean implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private String dia;

	private String valorDia;

	private String qtdePagamentos;
	
	private String qtdeDocumentos;
	
	private String valorAteDia;
	
	private String qtdePagamentosAteDia;
	
	private String qtdeDocumentosAteDia;

	/**
	 * Construtor da classe RelatorioAcompanhamentoMovimentoArrecadadoresBean
	 */
	public RelatorioAcompanhamentoMovimentoArrecadadoresBean(String banco,
			String formaArrecadacao, String dia, String valorDia,
			String qtdePagamentos, String qtdeDocumentos, String valorAteDia,
			String qtdePagamentosAteDia, String qtdeDocumentosAteDia) {
		
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.dia = dia;
		this.valorDia = valorDia;
		this.qtdePagamentos = qtdePagamentos;
		this.qtdeDocumentos = qtdeDocumentos;
		this.valorAteDia = valorAteDia;
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;

	}

	/**
	 * @return Retorna o campo banco.
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco O banco a ser setado.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return Retorna o campo dia.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia O dia a ser setado.
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	/**
	 * @return Retorna o campo qtdeDocumentos.
	 */
	public String getQtdeDocumentos() {
		return qtdeDocumentos;
	}

	/**
	 * @param qtdeDocumentos O qtdeDocumentos a ser setado.
	 */
	public void setQtdeDocumentos(String qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}

	/**
	 * @return Retorna o campo qtdeDocumentosAteDia.
	 */
	public String getQtdeDocumentosAteDia() {
		return qtdeDocumentosAteDia;
	}

	/**
	 * @param qtdeDocumentosAteDia O qtdeDocumentosAteDia a ser setado.
	 */
	public void setQtdeDocumentosAteDia(String qtdeDocumentosAteDia) {
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;
	}

	/**
	 * @return Retorna o campo qtdePagamentos.
	 */
	public String getQtdePagamentos() {
		return qtdePagamentos;
	}

	/**
	 * @param qtdePagamentos O qtdePagamentos a ser setado.
	 */
	public void setQtdePagamentos(String qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	}

	/**
	 * @return Retorna o campo qtdePagamentosAteDia.
	 */
	public String getQtdePagamentosAteDia() {
		return qtdePagamentosAteDia;
	}

	/**
	 * @param qtdePagamentosAteDia O qtdePagamentosAteDia a ser setado.
	 */
	public void setQtdePagamentosAteDia(String qtdePagamentosAteDia) {
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
	}

	/**
	 * @return Retorna o campo valorAteDia.
	 */
	public String getValorAteDia() {
		return valorAteDia;
	}

	/**
	 * @param valorAteDia O valorAteDia a ser setado.
	 */
	public void setValorAteDia(String valorAteDia) {
		this.valorAteDia = valorAteDia;
	}

	/**
	 * @return Retorna o campo valorDia.
	 */
	public String getValorDia() {
		return valorDia;
	}

	/**
	 * @param valorDia O valorDia a ser setado.
	 */
	public void setValorDia(String valorDia) {
		this.valorDia = valorDia;
	}


}
