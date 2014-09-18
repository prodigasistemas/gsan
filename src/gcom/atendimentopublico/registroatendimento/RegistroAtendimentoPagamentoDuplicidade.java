package gcom.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.ContaGeral;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Date;

public class RegistroAtendimentoPagamentoDuplicidade extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private RegistroAtendimentoPagamentoDuplicidadePK comp_id;
	private Date ultimaAlteracao;
	private RegistroAtendimento registroAtendimento;
	private Pagamento pagamento;
	private ContaGeral contaGeral;
	private int referenciaConta;
	private Imovel imovel;
	private Short indicadorPagamentoDevolvido;
	
	private Date dataPagamento;
	private Integer anoMesReferenciaPagamento;
	private BigDecimal valorPagamento;
	
	public RegistroAtendimentoPagamentoDuplicidadePK getComp_id() {
		return comp_id;
	}

	public void setComp_id(RegistroAtendimentoPagamentoDuplicidadePK comp_id) {
		this.comp_id = comp_id;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public int getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(int referenciaConta) {
		this.referenciaConta = referenciaConta;
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

	public Short getIndicadorPagamentoDevolvido() {
		return indicadorPagamentoDevolvido;
	}

	public void setIndicadorPagamentoDevolvido(Short indicadorPagamentoDevolvido) {
		this.indicadorPagamentoDevolvido = indicadorPagamentoDevolvido;
	}

	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	public String getFormatarAnoMesPagamentoParaMesAno() {
		String anoMesFormatado = "";
		
		if (this.getAnoMesReferenciaPagamento() != null && !this.getAnoMesReferenciaPagamento().equals("")) {
			anoMesFormatado = Util.formatarAnoMesParaMesAno(this.getAnoMesReferenciaPagamento());
		}
		
		return anoMesFormatado;

	}
}
