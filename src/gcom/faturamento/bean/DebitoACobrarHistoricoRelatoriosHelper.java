package gcom.faturamento.bean;

import java.math.BigDecimal;

import gcom.faturamento.debito.DebitoACobrarHistorico;
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
public class DebitoACobrarHistoricoRelatoriosHelper {
	
	public DebitoACobrarHistoricoRelatoriosHelper() {
	}
	
	public DebitoACobrarHistoricoRelatoriosHelper(DebitoACobrarHistorico c) {
		this.debitoCobrarHistorico = c;
	}

	private DebitoACobrarHistorico debitoCobrarHistorico;

	public DebitoACobrarHistorico getDebitoCobrarHistorico() {
		return debitoCobrarHistorico;
	}

	public void setDebitoCobrarHistorico(DebitoACobrarHistorico c) {
		this.debitoCobrarHistorico = c;
	}
	
	public String getDescricaoTipoDebito(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getDebitoTipo()!=null){
			return this.debitoCobrarHistorico.getDebitoTipo().getDescricao();
		}
		
		return "";
	}

	public String getAnoMesReferenciaDebito(){
		if(this.debitoCobrarHistorico!=null){
			if (this.debitoCobrarHistorico.getAnoMesReferenciaDebito() != null &&
					!this.debitoCobrarHistorico.getAnoMesReferenciaDebito().equals("")) {
				return Util.formatarAnoMesParaMesAno(this.debitoCobrarHistorico.getAnoMesReferenciaDebito());
			}
		}
		
		return "";
	}

	public String getAnoMesCobrancaDebito(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getAnoMesCobrancaDebito() != null){
			return Util.formatarAnoMesParaMesAno(this.debitoCobrarHistorico.getAnoMesCobrancaDebito());
		}
		
		return "";
	}

	public Short getPrestacaoCobradas(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getPrestacaoCobradas();
		}
		
		return null;
	}

	public Short getPrestacaoDebito(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getPrestacaoDebito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorDebito(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getValorDebito();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getDebitoCreditoSituacaoAtual()!=null){
			return this.debitoCobrarHistorico.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}

}
