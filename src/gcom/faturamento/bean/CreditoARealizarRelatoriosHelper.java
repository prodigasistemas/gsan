package gcom.faturamento.bean;

import java.math.BigDecimal;

import gcom.faturamento.credito.CreditoARealizar;
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
public class CreditoARealizarRelatoriosHelper {
	
	public CreditoARealizarRelatoriosHelper() {
	}
	
	public CreditoARealizarRelatoriosHelper(CreditoARealizar c) {
		this.creditoRealizar = c;
	}

	private CreditoARealizar creditoRealizar;

	public CreditoARealizar getCreditoRealizar() {
		return creditoRealizar;
	}

	public void setCreditoRealizar(CreditoARealizar c) {
		this.creditoRealizar = c;
	}
	
	public String getDescricaoTipoCredito(){
		if(this.creditoRealizar!=null && this.creditoRealizar.getCreditoTipo()!=null){
			return this.creditoRealizar.getCreditoTipo().getDescricao();
		}
		
		return "";
	}
	
	public String getAnoMesReferenciaCredito(){
		if(this.creditoRealizar!=null && this.creditoRealizar.getAnoMesReferenciaCredito() != null){
			return Util.formatarAnoMesParaMesAno(this.creditoRealizar.getAnoMesReferenciaCredito());
		}
		
		return "";
	}

	public String getAnoMesCobrancaCredito(){
		if(this.creditoRealizar!=null && this.creditoRealizar.getAnoMesCobrancaCredito() != null){
			return Util.formatarAnoMesParaMesAno(this.creditoRealizar.getAnoMesCobrancaCredito());
		}
		
		return "";
	}

	public Short getNumeroPrestacaoRealizada(){
		if(this.creditoRealizar!=null){
			return this.creditoRealizar.getNumeroPrestacaoRealizada();
		}
		
		return null;
	}

	public Short getNumeroPrestacaoCredito(){
		if(this.creditoRealizar!=null){
			return this.creditoRealizar.getNumeroPrestacaoCredito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.creditoRealizar!=null){
			return this.creditoRealizar.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorCredito(){
		if(this.creditoRealizar!=null){
			return this.creditoRealizar.getValorCredito();
		}
		
		return null;
	}

	public String getDescricaoAbreviadaDebitoCreditoSituacaoAtual(){
		if(this.creditoRealizar!=null && this.creditoRealizar.getDebitoCreditoSituacaoAtual()!=null){
			return this.creditoRealizar.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}

}
