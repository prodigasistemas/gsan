package gcom.relatorio.atendimentopublico;

import java.io.Serializable;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author 
 * @date 
 */
public class RelatorioOrdemFiscalizacaoFaturasBean implements RelatorioBean, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String faturaAtraso; 
	private String dataVencimento; 
	private String valor; 
	
	public RelatorioOrdemFiscalizacaoFaturasBean(){}
	
	public RelatorioOrdemFiscalizacaoFaturasBean(String faturaAtraso,
			String dataVencimento, String valor) {
		this.faturaAtraso = faturaAtraso; 
		this.dataVencimento = dataVencimento;
		this.valor = valor;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getFaturaAtraso() {
		return faturaAtraso;
	}

	public void setFaturaAtraso(String faturaAtraso) {
		this.faturaAtraso = faturaAtraso;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
