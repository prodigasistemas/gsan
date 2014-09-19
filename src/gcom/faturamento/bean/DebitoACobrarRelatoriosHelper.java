package gcom.faturamento.bean;

import java.math.BigDecimal;

import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.Util;



/**
 * [CRC:1710] - Bot�es de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servir� para exibir os dados dos Debitos Autom�ticos
 * no RelatorioDadosComplementaresImovel.
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que n�o mude o que j� existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class DebitoACobrarRelatoriosHelper {
	
	public DebitoACobrarRelatoriosHelper() {
	}
	
	public DebitoACobrarRelatoriosHelper(DebitoACobrar c) {
		this.debitoCobrar = c;
	}

	private DebitoACobrar debitoCobrar;

	public DebitoACobrar getDebitoCobrar() {
		return debitoCobrar;
	}

	public void setDebitoCobrar(DebitoACobrar c) {
		this.debitoCobrar = c;
	}	
	
	public String getDescricaoTipoDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoTipo()!=null){
			return this.debitoCobrar.getDebitoTipo().getDescricao();
		}
		
		return "";
	}

	public String getAnoMesReferenciaDebito(){
		
		/**
		 * @author Adriana Muniz
		 * @date 21/12/2011
		 * 
		 * Altera��o para evitar erro de atributo nulo quando o debito n�o possui referencia, j� que esse campo 
		 * n�o� de preenchimento obrigatorio no momento de inser��o do debito. 
		 * */
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesReferenciaDebito() != null){
			return Util.formatarAnoMesParaMesAno(this.debitoCobrar.getAnoMesReferenciaDebito());
		}
		
		return "";
	}

	public String getAnoMesCobrancaDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesCobrancaDebito() != null){
			return this.debitoCobrar.getAnoMesCobrancaDebito() !=null ? 
					Util.formatarAnoMesParaMesAno(this.debitoCobrar.getAnoMesCobrancaDebito()) : null;
		}
		
		return "";
	}

	public Short getNumeroPrestacaoCobradas(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoCobradas();
		}
		
		return null;
	}

	public Short getNumeroPrestacaoDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoDebito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getValorDebito();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoCreditoSituacaoAtual()!=null){
			return this.debitoCobrar.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}
}

