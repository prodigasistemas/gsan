package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Mariana Vcitor
 * @date 25/01/2011
 * 
 */
public class RelatorioDocumentoCobrancaOrdemFiscalizacaoSubBean implements RelatorioBean {
	
	private String mesAno;
	private String dataVencimento;
	private String valor;

	public RelatorioDocumentoCobrancaOrdemFiscalizacaoSubBean() {
		super();
	}

	public RelatorioDocumentoCobrancaOrdemFiscalizacaoSubBean(String mesAno, String dataVencimento, String valor) {
		super();
		this.mesAno = mesAno;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
