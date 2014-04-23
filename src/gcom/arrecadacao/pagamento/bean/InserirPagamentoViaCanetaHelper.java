package gcom.arrecadacao.pagamento.bean;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.pagamento.Pagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Objeto helper utilizado para armazenar os dados necessário para inserir 
 * um pagamento por leitura optica de código de barras
 *
 * @author Pedro Alexandre
 * @date 16/02/2006
 */
public class InserirPagamentoViaCanetaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public InserirPagamentoViaCanetaHelper() {
	}

	private String codigoBarra;

	private BigDecimal valorPagamento;
	
	private Collection<Pagamento> colecaoPagamento;
	
	private Collection<Devolucao> colecaoDevolucao;
	
	//adicionado por Vivianne Sousa - 22/12/2009
	//[UC0971] Inserir Pagamentos para Faturas Especiais
	private RegistroHelperCodigoBarras registroHelperCodigoBarras;
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String cobigoBarra) {
		this.codigoBarra = cobigoBarra;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getCodigoBarraFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 44){
		  return  codigoBarra.substring(0,11) + " " + codigoBarra.substring(11,22) + " " + codigoBarra.substring(22,33) + " " + codigoBarra.substring(33,44); 
		}else{
			return "";
		}
	}
	
	public String getCodigoBarraFichaCompensacaoFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 47){
		  return  codigoBarra.substring(0,5) + "." + codigoBarra.substring(5,10) + " " 
		  + codigoBarra.substring(10,15) + "." + codigoBarra.substring(15,21) + " "
		  + codigoBarra.substring(21,26) + "." + codigoBarra.substring(26,32) + " "
		  + codigoBarra.substring(32,33) + " " + codigoBarra.substring(33,47);
		}
		else{
			return "";
		}
	}

	public Collection<Pagamento> getColecaoPagamento() {
		return colecaoPagamento;
	}

	public void setColecaoPagamento(Collection<Pagamento> colecaoPagamento) {
		this.colecaoPagamento = colecaoPagamento;
	}

	public Collection<Devolucao> getColecaoDevolucao() {
		return colecaoDevolucao;
	}

	public void setColecaoDevolucao(Collection<Devolucao> colecaoDevolucao) {
		this.colecaoDevolucao = colecaoDevolucao;
	}

	public RegistroHelperCodigoBarras getRegistroHelperCodigoBarras() {
		return registroHelperCodigoBarras;
	}

	public void setRegistroHelperCodigoBarras(
			RegistroHelperCodigoBarras registroHelperCodigoBarras) {
		this.registroHelperCodigoBarras = registroHelperCodigoBarras;
	}

		
}
