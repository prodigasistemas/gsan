package gcom.relatorio.arrecadacao.pagamento;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GuiaPagamentoRelatorioHelper {

	private Integer idImovel;
	private Integer idCliente;
	private Date dataVencimento;
	private Integer idLocalidade;
	private String descLocalidade;
	private BigDecimal valorDebito;
	private Date dataEmissao;
	private String nomeCliente;
	private Integer idTipoDebito;
	private String descTipoDebito;
	private Short numeroPrestacaoDebito;
	private Short numeroPrestacaoTotal;
	private String observacao;
	private Short indicadorEmitirObservacao;

	private String matricula;
	private String inscricao;
	private String enderecoImovel;
	private String enderecoClienteResponsavel;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String idGuiaPagamento;

	private String cpfCliente;
	private String cnpjCliente;

	private String nossoNumero;
	private String sacadoParte01;
	private String sacadoParte02;
	private String enderecoImovelSacado;

	private String nomeImovel;
	private String subRelatorio;

	public GuiaPagamentoRelatorioHelper(Integer idImovel, Date dataVencimento, Integer idLocalidade, String descLocalidade, BigDecimal valorDebito, Date dataEmissao, Integer idTipoDebito,
			String descTipoDebito, Short numeroPrestacaoDebito, Short numeroPrestacaoTotal, String observacao, Short indicadorEmitirObservacao) {
		this.idImovel = idImovel;
		this.dataVencimento = dataVencimento;
		this.idLocalidade = idLocalidade;
		this.descLocalidade = descLocalidade;
		this.valorDebito = valorDebito;
		this.dataEmissao = dataEmissao;
		this.idTipoDebito = idTipoDebito;
		this.descTipoDebito = descTipoDebito;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
		this.observacao = observacao;
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;

	}

	public GuiaPagamentoRelatorioHelper(Integer idImovel, Date dataVencimento, Integer idLocalidade, String descLocalidade, BigDecimal valorDebito, Date dataEmissao, Integer idTipoDebito,
			String descTipoDebito, Short numeroPrestacaoDebito, Short numeroPrestacaoTotal, String observacao, Short indicadorEmitirObservacao, String nomeImovel) {
		this.idImovel = idImovel;
		this.dataVencimento = dataVencimento;
		this.idLocalidade = idLocalidade;
		this.descLocalidade = descLocalidade;
		this.valorDebito = valorDebito;
		this.dataEmissao = dataEmissao;
		this.idTipoDebito = idTipoDebito;
		this.descTipoDebito = descTipoDebito;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
		this.observacao = observacao;
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
		this.nomeImovel = nomeImovel;
	}

	public GuiaPagamentoRelatorioHelper() {
	}

	public String getDescTipoDebito() {
		return descTipoDebito;
	}

	public void setDescTipoDebito(String descTipoDebito) {
		this.descTipoDebito = descTipoDebito;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescLocalidade() {
		return descLocalidade;
	}

	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}

	public Integer getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(Integer idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	// Data de Validade da Guia de Pagamento último dia do proximo mês do mês/ano da data de vencimento da guia de pagamento
	public String getDataValidade() {
		int anoVencimento = Util.getAno(getDataVencimento());
		int mesVencimento = Util.getMes(getDataVencimento());

		Calendar calendarUltimoDiaMesAnoDataVencimento = new GregorianCalendar();

		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.YEAR, anoVencimento);
		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.MONTH, mesVencimento - 1);
		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.DAY_OF_MONTH, calendarUltimoDiaMesAnoDataVencimento.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dateDataVencimentoMais3Dias = Util.adicionarNumeroDiasDeUmaData(getDataVencimento(), 3);
		Date dateDataCorrenteMais3Dias = Util.adicionarNumeroDiasDeUmaData(new Date(), 3);
		Date dateMaiorData = null;

		if (Util.compararData(dateDataVencimentoMais3Dias, dateDataCorrenteMais3Dias) >= 0) {

			if (Util.compararData(dateDataVencimentoMais3Dias, calendarUltimoDiaMesAnoDataVencimento.getTime()) >= 0) {
				dateMaiorData = dateDataVencimentoMais3Dias;
			} else {
				dateMaiorData = calendarUltimoDiaMesAnoDataVencimento.getTime();
			}

		} else {
			if (Util.compararData(dateDataCorrenteMais3Dias, calendarUltimoDiaMesAnoDataVencimento.getTime()) >= 0) {
				dateMaiorData = dateDataCorrenteMais3Dias;
			} else {
				dateMaiorData = calendarUltimoDiaMesAnoDataVencimento.getTime();
			}
		}

		return Util.formatarData(dateMaiorData);
	}

	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Short getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}

	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}

	public String getPrestacaoFormatada() {
		String prestacaoFormatada = "";

		if (getNumeroPrestacaoDebito() != null && getNumeroPrestacaoTotal() != null) {
			prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + "/" + getNumeroPrestacaoTotal();
		}

		return prestacaoFormatada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getEnderecoImovelSacado() {
		return enderecoImovelSacado;
	}

	public void setEnderecoImovelSacado(String enderecoImovelSacado) {
		this.enderecoImovelSacado = enderecoImovelSacado;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte02() {
		return sacadoParte02;
	}

	public void setSacadoParte02(String sacadoParte02) {
		this.sacadoParte02 = sacadoParte02;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}
}
