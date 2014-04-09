package gcom.faturamento.bean;

import gcom.faturamento.conta.ContaHistorico;
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
public class ContaHistoricoRelatoriosHelper {
	
	public ContaHistoricoRelatoriosHelper() {
	}
	
	public ContaHistoricoRelatoriosHelper(ContaHistorico c) {
		this.contaHistorico = c;
	}

	private ContaHistorico contaHistorico;

	public ContaHistorico getContaHistorico() {
		return contaHistorico;
	}

	public void setContaHistorico(ContaHistorico c) {
		this.contaHistorico = c;
	}
	
	public String getAnoMesReferenciaContabil(){
		if(this.contaHistorico!=null){
			return Util.formatarMesAnoReferencia(this.contaHistorico.getAnoMesReferenciaContabil());			
		}
		
		return "";
	}

	public Date getDataVencimentoConta(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getDataVencimentoConta();
		}
		
		return null;
	}

	public BigDecimal getValorAgua(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorAgua();
		}
		
		return null;
	}

	public BigDecimal getValorEsgoto(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorEsgoto();
		}
		
		return null;
	}

	public BigDecimal getValorDebitos(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorDebitos();
		}
		
		return null;
	}

	public BigDecimal getValorCreditos(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorCreditos();
		}
		
		return null;
	}

	public BigDecimal getValorImposto(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorImposto();
		}
		
		return null;
	}
	
	public BigDecimal getValorTotal(){
		if(this.contaHistorico!=null){
			return this.contaHistorico.getValorTotal();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.contaHistorico!=null && this.contaHistorico.getDebitoCreditoSituacaoAtual()!=null){
			return this.contaHistorico.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}
}
