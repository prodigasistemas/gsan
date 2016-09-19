package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirResolucaoDiretoriaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String numero;

	private String assunto;

	private String dataInicio;

	private String dataFim;

	private String indicadorParcelamentoUnico;

	private String indicadorUtilizacaoLivre;
	
	private String indicadorDescontoFaixaReferenciaConta;

	private String indicadorDescontoSancoes;

	private String indicadorParcelasEmAtraso;

	private String idParcelasEmAtraso;

	private String indicadorParcelamentoEmAndamento;

	private String idParcelamentoEmAndamento;

	private String indicadorNegociacaoSoAVista;

	private String indicadorDescontoSoEmContaAVista;

	private String indicadorParcelamentoLojaVirtual;
	
	public String getIndicadorDescontoSoEmContaAVista() {
		return indicadorDescontoSoEmContaAVista;
	}

	public void setIndicadorDescontoSoEmContaAVista(String indicadorDescontoSoEmContaAVista) {
		this.indicadorDescontoSoEmContaAVista = indicadorDescontoSoEmContaAVista;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
		numero = null;
		assunto = null;
		dataInicio = null;
		dataFim = null;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIndicadorDescontoSancoes() {
		return indicadorDescontoSancoes;
	}

	public void setIndicadorDescontoSancoes(String indicadorDescontoSancoes) {
		this.indicadorDescontoSancoes = indicadorDescontoSancoes;
	}

	public String getIndicadorParcelamentoUnico() {
		return indicadorParcelamentoUnico;
	}

	public void setIndicadorParcelamentoUnico(String indicadorParcelamentoUnico) {
		this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;
	}

	public String getIndicadorUtilizacaoLivre() {
		return indicadorUtilizacaoLivre;
	}

	public void setIndicadorUtilizacaoLivre(String indicadorUtilizacaoLivre) {
		this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
	}

	public String getIdParcelamentoEmAndamento() {
		return idParcelamentoEmAndamento;
	}

	public void setIdParcelamentoEmAndamento(String idParcelamentoEmAndamento) {
		this.idParcelamentoEmAndamento = idParcelamentoEmAndamento;
	}

	public String getIdParcelasEmAtraso() {
		return idParcelasEmAtraso;
	}

	public void setIdParcelasEmAtraso(String idParcelasEmAtraso) {
		this.idParcelasEmAtraso = idParcelasEmAtraso;
	}

	public String getIndicadorParcelamentoEmAndamento() {
		return indicadorParcelamentoEmAndamento;
	}

	public void setIndicadorParcelamentoEmAndamento(String indicadorParcelamentoEmAndamento) {
		this.indicadorParcelamentoEmAndamento = indicadorParcelamentoEmAndamento;
	}

	public String getIndicadorParcelasEmAtraso() {
		return indicadorParcelasEmAtraso;
	}

	public void setIndicadorParcelasEmAtraso(String indicadorParcelasEmAtraso) {
		this.indicadorParcelasEmAtraso = indicadorParcelasEmAtraso;
	}

	public String getIndicadorNegociacaoSoAVista() {
		return indicadorNegociacaoSoAVista;
	}

	public void setIndicadorNegociacaoSoAVista(String indicadorNegociacaoSoAVista) {
		this.indicadorNegociacaoSoAVista = indicadorNegociacaoSoAVista;
	}

	public String getIndicadorParcelamentoLojaVirtual() {
		return indicadorParcelamentoLojaVirtual;
	}

	public void setIndicadorParcelamentoLojaVirtual(String indicadorParcelamentoLojaVirtual) {
		this.indicadorParcelamentoLojaVirtual = indicadorParcelamentoLojaVirtual;
	}

	public String getIndicadorDescontoFaixaReferenciaConta() {
		return indicadorDescontoFaixaReferenciaConta;
	}

	public void setIndicadorDescontoFaixaReferenciaConta(String indicadorDescontoFaixaReferenciaConta) {
		this.indicadorDescontoFaixaReferenciaConta = indicadorDescontoFaixaReferenciaConta;
	}
}
