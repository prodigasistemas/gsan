package gcom.faturamento.bean;

import gcom.faturamento.conta.Conta;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;



/**
 * [CRC:1710] - Botões de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servirá para exibir os dados dos Debitos Automáticos
 * no RelatorioDadosComplementaresImovel.
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que não mude o que já existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class ContaRelatoriosHelper {
	
	public ContaRelatoriosHelper() {
	}
	
	public ContaRelatoriosHelper(Conta c) {
		this.conta = c;
	}

	private Conta conta;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta c) {
		this.conta = c;
	}	
	
	public String getReferencia(){
		if(this.conta!=null){
			return Util.formatarMesAnoReferencia(this.conta.getReferencia());			
		}
		
		return "";
	}

	public Date getDataVencimentoConta(){
		if(this.conta!=null){
			return this.conta.getDataVencimentoConta();
		}
		
		return null;
	}

	public BigDecimal getValorAgua(){
		if(this.conta!=null){
			return this.conta.getValorAgua();
		}
		
		return null;
	}

	public BigDecimal getValorEsgoto(){
		if(this.conta!=null){
			return this.conta.getValorEsgoto();
		}
		
		return null;
	}

	public BigDecimal getDebitos(){
		if(this.conta!=null){
			return this.conta.getDebitos();
		}
		
		return null;
	}

	public BigDecimal getValorCreditos(){
		if(this.conta!=null){
			return this.conta.getValorCreditos();
		}
		
		return null;
	}

	public BigDecimal getValorImposto(){
		if(this.conta!=null){
			return this.conta.getValorImposto();
		}
		
		return null;
	}
	
	public BigDecimal getValorTotal(){
		if(this.conta!=null){
			return this.conta.getValorTotal();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.conta!=null && this.conta.getDebitoCreditoSituacaoAtual()!=null){
			return this.conta.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}

}
