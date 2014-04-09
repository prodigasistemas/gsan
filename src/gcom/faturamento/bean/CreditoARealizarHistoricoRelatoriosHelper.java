package gcom.faturamento.bean;

import java.math.BigDecimal;

import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.util.Util;



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
public class CreditoARealizarHistoricoRelatoriosHelper {
	
	public CreditoARealizarHistoricoRelatoriosHelper() {
	}
	
	public CreditoARealizarHistoricoRelatoriosHelper(CreditoARealizarHistorico c) {
		this.creditoRealizarHistorico = c;
	}

	private CreditoARealizarHistorico creditoRealizarHistorico;

	public CreditoARealizarHistorico getCreditoRealizarHistorico() {
		return creditoRealizarHistorico;
	}

	public void setCreditoRealizarHistorico(CreditoARealizarHistorico c) {
		this.creditoRealizarHistorico = c;
	}	
	
	public String getDescricaoTipoCredito(){
		if(this.creditoRealizarHistorico!=null && this.creditoRealizarHistorico.getCreditoTipo()!=null){
			return this.creditoRealizarHistorico.getCreditoTipo().getDescricao();
		}
		
		return "";
	}
	
	public String getAnoMesReferenciaCredito(){
		if(this.creditoRealizarHistorico!=null && this.creditoRealizarHistorico.getAnoMesReferenciaCredito() != null){
			return Util.formatarAnoMesParaMesAno(this.creditoRealizarHistorico.getAnoMesReferenciaCredito());
		}
		
		return "";
	}

	public String getAnoMesCobrancaCredito(){
		if(this.creditoRealizarHistorico!=null && this.creditoRealizarHistorico.getAnoMesCobrancaCredito() != null){
			return Util.formatarAnoMesParaMesAno(this.creditoRealizarHistorico.getAnoMesCobrancaCredito());
		}
		
		return "";
	}

	public Short getPrestacaoRealizadas(){
		if(this.creditoRealizarHistorico!=null){
			return this.creditoRealizarHistorico.getPrestacaoRealizadas();
		}
		
		return null;
	}

	public Short getPrestacaoCredito(){
		if(this.creditoRealizarHistorico!=null){
			return this.creditoRealizarHistorico.getPrestacaoCredito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.creditoRealizarHistorico!=null){
			return this.creditoRealizarHistorico.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorCredito(){
		if(this.creditoRealizarHistorico!=null){
			return this.creditoRealizarHistorico.getValorCredito();
		}
		
		return null;
	}

	public String getDescricaoAbreviadaDebitoCreditoSituacaoAtual(){
		if(this.creditoRealizarHistorico!=null && this.creditoRealizarHistorico.getDebitoCreditoSituacaoAtual()!=null){
			return this.creditoRealizarHistorico.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}

}
