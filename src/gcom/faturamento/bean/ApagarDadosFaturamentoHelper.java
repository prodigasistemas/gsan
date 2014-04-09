package gcom.faturamento.bean;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;

import java.util.Date;

/**
 * Parâmetros para apagar as contas geradas pelo faturamento
 *
 * @author Raphael Rossiter
 * 
 * @date 25/03/2008
 */
public class ApagarDadosFaturamentoHelper {

	private FaturamentoGrupo faturamentoGrupo;
	
	private Rota rota;
	
	private Integer anoMesFaturamento;
	
	private Integer idDebitoCreditoSituacaoAtual;
	
	private Date dataEmissaoInicial;
	
	private Date dataEmissaoFinal;
	
	private Integer idImovel;
	
	/*
	 * Utilizado apenas para o batch [UC0876] - Gerar Crédito Situação Especial Faturamento que faz parte do
	 * processo de faturamento da CAERN
	 */
	private Integer idCreditoTipo;
	
	public ApagarDadosFaturamentoHelper(){}

	public Integer getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(Integer anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Integer getIdDebitoCreditoSituacaoAtual() {
		return idDebitoCreditoSituacaoAtual;
	}

	public void setIdDebitoCreditoSituacaoAtual(Integer idDebitoCreditoSituacaoAtual) {
		this.idDebitoCreditoSituacaoAtual = idDebitoCreditoSituacaoAtual;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Integer getIdCreditoTipo() {
		return idCreditoTipo;
	}

	public void setIdCreditoTipo(Integer idCreditoTipo) {
		this.idCreditoTipo = idCreditoTipo;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	
}
