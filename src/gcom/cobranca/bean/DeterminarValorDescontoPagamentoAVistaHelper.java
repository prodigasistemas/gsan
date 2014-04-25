package gcom.cobranca.bean;

import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeterminarValorDescontoPagamentoAVistaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper;
	
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorDescontoAcrecismosImpotualidade;
	
	private BigDecimal valorDescontoInatividade;
	
	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal valorDescontoSancoes;
	
	private BigDecimal valorDescontoTarifaSocial;
	
	private Integer anoMesLimiteMaximo;
	
	private ResolucaoDiretoria resolucaoDiretoria;
	
	private BigDecimal valorCreditoARealizar;
	
	private BigDecimal valorDescontoInatividadeAVista;
	   
    public DeterminarValorDescontoPagamentoAVistaHelper(ObterOpcoesDeParcelamentoHelper helper, ParcelamentoPerfil parcelamentoPerfil,
        BigDecimal valorDescontoAcrecismosImpotualidade, BigDecimal valorDescontoInatividade,
        BigDecimal valorDescontoAntiguidade, BigDecimal valorDescontoSancoes,
        BigDecimal valorDescontoTarifaSocial, Integer anoMesLimiteMaximo,
        ResolucaoDiretoria resolucaoDiretoria, BigDecimal valorCreditoARealizar,
        BigDecimal valorDescontoInatividadeAVista){
       
        this.setObterOpcoesDeParcelamentoHelper(helper);
        this.setParcelamentoPerfil(parcelamentoPerfil);
        this.setValorDescontoAcrecismosImpotualidade(valorDescontoAcrecismosImpotualidade);
        this.setValorDescontoInatividade(valorDescontoInatividade);
        this.setValorDescontoAntiguidade(valorDescontoAntiguidade);
        this.setValorDescontoSancoes(valorDescontoSancoes);
        this.setValorDescontoTarifaSocial(valorDescontoTarifaSocial);
        this.setAnoMesLimiteMaximo(anoMesLimiteMaximo);
        this.setResolucaoDiretoria(resolucaoDiretoria);
        this.setValorCreditoARealizar(valorCreditoARealizar);
        this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
    }
	
	public Integer getAnoMesLimiteMaximo() {
		return anoMesLimiteMaximo;
	}

	public void setAnoMesLimiteMaximo(Integer anoMesLimiteMaximo) {
		this.anoMesLimiteMaximo = anoMesLimiteMaximo;
	}

	public ObterOpcoesDeParcelamentoHelper getObterOpcoesDeParcelamentoHelper() {
		return obterOpcoesDeParcelamentoHelper;
	}

	public void setObterOpcoesDeParcelamentoHelper(
			ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper) {
		this.obterOpcoesDeParcelamentoHelper = obterOpcoesDeParcelamentoHelper;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	public void setValorDescontoAcrecismosImpotualidade(
			BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getValorDescontoSancoes() {
		return valorDescontoSancoes;
	}

	public void setValorDescontoSancoes(BigDecimal valorDescontoSancoes) {
		this.valorDescontoSancoes = valorDescontoSancoes;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public BigDecimal getValorCreditoARealizar() {
		return valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public BigDecimal getValorDescontoInatividadeAVista() {
		return valorDescontoInatividadeAVista;
	}

	public void setValorDescontoInatividadeAVista(
			BigDecimal valorDescontoInatividadeAVista) {
		this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
	}
	
	
}
