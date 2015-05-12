package gcom.cobranca.bean;

import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class ObterDebitoImovelOuClienteHelper {
	
	private Collection<ContaValoresHelper> colecaoContasValores;
	private Collection<ContaValoresHelper> colecaoContasValoresPreteritos;
	private Collection<ContaValoresHelper> colecaoContasValoresImovel;
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	private Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper;
	private Integer anoMesReferenciaInicioDebito;
	private Integer anoMesReferenciaFinalDebito;

	public ObterDebitoImovelOuClienteHelper() {
		
	}
	
	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<ContaValoresHelper> colecaoContasValoresImovel,
		Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores,
			Collection<ContaValoresHelper> colecaoContasValoresImovel,
			Collection<DebitoACobrar> colecaoDebitoACobrar, 
			Collection<CreditoARealizar> colecaoCreditoARealizar, 
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores,
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	
	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}
	
	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValoresSemFichaCompensacao() {
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaSemFicha = new ArrayList<GuiaPagamentoValoresHelper>();

		for (GuiaPagamentoValoresHelper guia : colecaoGuiasPagamentoValores) {
			if (guia.getGuiaPagamento().getDocumentoTipo() == null
					|| !guia.getGuiaPagamento().getDocumentoTipo().getId().equals(DocumentoTipo.ENTRADA_DE_PARCELAMENTO)) {
				colecaoGuiaSemFicha.add(guia);
			}
		}
		return colecaoGuiaSemFicha;
	}

	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public Collection<ContaValoresHelper> getColecaoContasValoresImovel() {
		return colecaoContasValoresImovel;
	}

	public void setColecaoContasValoresImovel(
			Collection<ContaValoresHelper> colecaoContasValoresImovel) {
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
	}

	public Collection<DebitoCreditoParcelamentoHelper> getColecaoDebitoCreditoParcelamentoHelper() {
		return colecaoDebitoCreditoParcelamentoHelper;
	}

	public void setColecaoDebitoCreditoParcelamentoHelper(
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	public BigDecimal obterValorImpostosDasContas(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}

	public Integer getAnoMesReferenciaInicioDebito() {
		return anoMesReferenciaInicioDebito;
	}

	public void setAnoMesReferenciaInicioDebito(Integer anoMesReferenciaInicioDebito) {
		this.anoMesReferenciaInicioDebito = anoMesReferenciaInicioDebito;
	}

	public Integer getAnoMesReferenciaFinalDebito() {
		return anoMesReferenciaFinalDebito;
	}

	public void setAnoMesReferenciaFinalDebito(Integer anoMesReferenciaFinalDebito) {
		this.anoMesReferenciaFinalDebito = anoMesReferenciaFinalDebito;
	}

	public Collection<ContaValoresHelper> getColecaoContasValoresPreteritos() {
		return colecaoContasValoresPreteritos;
	}

	public void setColecaoContasValoresPreteritos(Collection<ContaValoresHelper> colecaoContasValoresPreteritos) {
		this.colecaoContasValoresPreteritos = colecaoContasValoresPreteritos;
	}
	
	
}
