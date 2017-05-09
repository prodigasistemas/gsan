package gcom.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class NegociacaoCobrancaEmpresa extends ObjetoTransacao {

	private static final long serialVersionUID = 3972366406241035377L;

	private Integer id;
	private Parcelamento parcelamento;
	private CobrancaDocumento cobrancaDocumento;
	private GuiaPagamentoGeral guiaPagamentoGeral;
	private Empresa empresa;
	private Date ultimaAlteracao;
	
	private List<NegociacaoContaCobrancaEmpresa> contasNegociadas;
	
	public NegociacaoCobrancaEmpresa () {
	}
	
	public NegociacaoCobrancaEmpresa (Parcelamento parcelamento, Empresa empresa, Date ultimaAlteracao) {
		this.parcelamento = parcelamento;
		this.empresa = empresa;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public NegociacaoCobrancaEmpresa (CobrancaDocumento documento, Empresa empresa, Date ultimaAlteracao) {
		this.cobrancaDocumento = documento;
		this.empresa = empresa;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public NegociacaoCobrancaEmpresa (GuiaPagamentoGeral guia, Empresa empresa, Date ultimaAlteracao) {
		this.guiaPagamentoGeral = guia;
		this.empresa = empresa;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guia) {
		this.guiaPagamentoGeral = guia;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<NegociacaoContaCobrancaEmpresa> getContasNegociadas() {
		return contasNegociadas;
	}

	public void setContasNegociadas(List<NegociacaoContaCobrancaEmpresa> contasNegociadas) {
		this.contasNegociadas = contasNegociadas;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}
}
