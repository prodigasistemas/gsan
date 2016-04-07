package gcom.faturamento.bean;

import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Retornar valores para faturamento de água e esgoto
 *
 * @author Raphael Rossiter
 * 
 * @date 27/03/2008
 */
public class DeterminarValoresFaturamentoAguaEsgotoHelper {

	private Short indicadorFaturamentoAgua;
	
	private Integer consumoFaturadoAgua;
	
	private Short indicadorFaturamentoEsgoto;
	
	private Integer consumoFaturadoEsgoto;
	
	private int consumoMinimoLigacao;
	
	private Date dataLeituraAnterior;
	
	private Date dataLeituraAtual;
	
	private BigDecimal percentualEsgoto;
	
	private BigDecimal valorTotalAgua;
	
	private BigDecimal valorTotalEsgoto;
	
	private Integer consumoRateioAgua;
	
	private Integer consumoRateioEsgoto;
	
	private ConsumoHistorico consumoHistoricoAgua;
	
	private ConsumoHistorico consumoHistoricoEsgoto;
	
	private Collection colecaoCalcularValoresAguaEsgotoHelper;
	
	private ConsumoTipo consumoTipoAgua;
	
	private ConsumoTipo consumoTipoEsgoto;
	
	private BigDecimal percentualColetaEsgoto;
	
	public DeterminarValoresFaturamentoAguaEsgotoHelper(){
		
		this.consumoFaturadoAgua = 0;
		this.indicadorFaturamentoAgua = ConstantesSistema.SIM;
		this.consumoRateioAgua = 0;
		this.valorTotalAgua = BigDecimal.ZERO;
		
		this.consumoFaturadoEsgoto = 0;
		this.indicadorFaturamentoEsgoto = ConstantesSistema.SIM;
		this.consumoRateioEsgoto = 0;
		this.valorTotalEsgoto = BigDecimal.ZERO;
		this.percentualEsgoto = BigDecimal.ZERO;
		this.percentualColetaEsgoto = BigDecimal.ZERO;
	}

	public Integer getConsumoFaturadoAgua() {
		return consumoFaturadoAgua;
	}

	public void setConsumoFaturadoAgua(Integer consumoFaturadoAgua) {
		this.consumoFaturadoAgua = consumoFaturadoAgua;
	}

	public Integer getConsumoFaturadoEsgoto() {
		return consumoFaturadoEsgoto;
	}

	public void setConsumoFaturadoEsgoto(Integer consumoFaturadoEsgoto) {
		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}

	public Short getIndicadorFaturamentoAgua() {
		return indicadorFaturamentoAgua;
	}

	public void setIndicadorFaturamentoAgua(Short indicadorFaturamentoAgua) {
		this.indicadorFaturamentoAgua = indicadorFaturamentoAgua;
	}

	public Short getIndicadorFaturamentoEsgoto() {
		return indicadorFaturamentoEsgoto;
	}

	public void setIndicadorFaturamentoEsgoto(Short indicadorFaturamentoEsgoto) {
		this.indicadorFaturamentoEsgoto = indicadorFaturamentoEsgoto;
	}

	public int getConsumoMinimoLigacao() {
		return consumoMinimoLigacao;
	}

	public void setConsumoMinimoLigacao(int consumoMinimoLigacao) {
		this.consumoMinimoLigacao = consumoMinimoLigacao;
	}

	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public Date getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(Date dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public BigDecimal getValorTotalAgua() {
		return valorTotalAgua;
	}

	public void setValorTotalAgua(BigDecimal valorTotalAgua) {
		this.valorTotalAgua = valorTotalAgua;
	}

	public BigDecimal getValorTotalEsgoto() {
		return valorTotalEsgoto;
	}

	public void setValorTotalEsgoto(BigDecimal valorTotalEsgoto) {
		this.valorTotalEsgoto = valorTotalEsgoto;
	}

	public ConsumoHistorico getConsumoHistoricoAgua() {
		return consumoHistoricoAgua;
	}

	public void setConsumoHistoricoAgua(ConsumoHistorico consumoHistoricoAgua) {
		this.consumoHistoricoAgua = consumoHistoricoAgua;
	}

	public ConsumoHistorico getConsumoHistoricoEsgoto() {
		return consumoHistoricoEsgoto;
	}

	public void setConsumoHistoricoEsgoto(ConsumoHistorico consumoHistoricoEsgoto) {
		this.consumoHistoricoEsgoto = consumoHistoricoEsgoto;
	}

	public Integer getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(Integer consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public Integer getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public Collection getColecaoCalcularValoresAguaEsgotoHelper() {
		return colecaoCalcularValoresAguaEsgotoHelper;
	}

	public void setColecaoCalcularValoresAguaEsgotoHelper(
			Collection colecaoCalcularValoresAguaEsgotoHelper) {
		this.colecaoCalcularValoresAguaEsgotoHelper = colecaoCalcularValoresAguaEsgotoHelper;
	}

	public ConsumoTipo getConsumoTipoAgua() {
		return consumoTipoAgua;
	}

	public void setConsumoTipoAgua(ConsumoTipo consumoTipoAgua) {
		this.consumoTipoAgua = consumoTipoAgua;
	}

	public ConsumoTipo getConsumoTipoEsgoto() {
		return consumoTipoEsgoto;
	}

	public void setConsumoTipoEsgoto(ConsumoTipo consumoTipoEsgoto) {
		this.consumoTipoEsgoto = consumoTipoEsgoto;
	}

	public BigDecimal getPercentualColetaEsgoto() {
		return percentualColetaEsgoto;
	}

	public void setPercentualColetaEsgoto(BigDecimal percentualColetaEsgoto) {
		this.percentualColetaEsgoto = percentualColetaEsgoto;
	}

    public void atribuirConsumoHistoricoAgua(ConsumoHistorico consumoHistoricoAgua2) {
        if (consumoHistoricoAgua != null) {
            this.setConsumoHistoricoAgua(consumoHistoricoAgua);
            this.setIndicadorFaturamentoAgua(consumoHistoricoAgua.getIndicadorFaturamento());
            this.setConsumoFaturadoAgua(consumoHistoricoAgua.getNumeroConsumoFaturadoMes());
            this.setConsumoRateioAgua(consumoHistoricoAgua.getConsumoRateio());
            this.setConsumoTipoAgua(consumoHistoricoAgua.getConsumoTipo());
        }        
    }
    

    public void atribuirConsumoHistoricoEsgoto(ConsumoHistorico consumoHistoricoEsgoto) {
        if (consumoHistoricoEsgoto != null) {
            this.setConsumoHistoricoEsgoto(consumoHistoricoEsgoto);
            this.setIndicadorFaturamentoEsgoto(consumoHistoricoEsgoto.getIndicadorFaturamento());
            this.setConsumoFaturadoEsgoto(consumoHistoricoEsgoto.getNumeroConsumoFaturadoMes());
            this.setConsumoRateioEsgoto(consumoHistoricoEsgoto.getConsumoRateio());
            this.setConsumoTipoEsgoto(consumoHistoricoEsgoto.getConsumoTipo());
            
        }        
    }
}
