package gcom.relatorio.big;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioBIGBean implements RelatorioBean {

	private String gerenciaRegional;
	
	private String localidade;
	
	private Integer quantidadeContas;
	
	private BigDecimal valorFaturamento;
	
	private BigDecimal valorArrecadacao;
	
	private BigDecimal eficienciaArrecadacao;
	
	private BigDecimal valorMedioFaturamento;
	
	private BigDecimal indicadorQuantidadeErrosContas;
	
	private BigDecimal indicadorValorErrosContas;
	
	private BigDecimal indicadorRecebimentoMedio;
	
	private BigDecimal indicadorQuantidadeInadimplenciaAte30;
	
	private BigDecimal indicadorValorInadimplenciaAte30;
	
	private BigDecimal indicadorQuantidadeInadimplenciaAte90;
	
	private BigDecimal indicadorValorInadimplenciaAte90;
	
	private BigDecimal indicadorQuantidadeInadimplenciaMaior90;
	
	private BigDecimal indicadorValorInadimplenciaMaior90;
	
	private BigDecimal quantidadeFaturamentosComprometidos;
	
	private BigDecimal indiceHidrometracao;
	
	private Integer quantidadeHidrometrosInstalados;
	
	private Integer quantidadeHidrometrosSubstituidos;
	
	private BigDecimal prazoMedioAtendimentoOS;
	
	private Integer quantidadeNovasLigacoesEsgoto;
	
	private Integer economiasNovasLigacoesEsgoto;
	
	private Integer quantidadeNovasLigacoesAgua;
	
	private Integer economiasNovasLigacoesAgua;
	
	private Integer quantidadeConsumidoresLigados;
	
	private Integer quantidadeConsumidoresCortados;
	
	private Integer quantidadeConsumidoresSuprimidos;
	
	private Integer quantidadeConsumidoresFactiveis;
	
	private Integer quantidadeConsumidoresTotal;
	
	private BigDecimal indiceCortados;
	
	private BigDecimal indiceSuprimidos;
	
	private BigDecimal indiceFactiveis;
	
	private Integer idLocalidade;
	
	public RelatorioBIGBean() {
	}

	public RelatorioBIGBean(RelatorioBIGHelper helper) {
		
		this.gerenciaRegional = helper.getGerenciaRegional();
		this.localidade = helper.getLocalidade();
		this.quantidadeContas = helper.getQuantidadeContas();
		this.valorFaturamento = helper.getValorFaturamento();
		this.valorArrecadacao = helper.getValorArrecadacao();
		this.eficienciaArrecadacao = helper.getEficienciaArrecadacao();
		this.valorMedioFaturamento = helper.getValorMedioFaturamento();
		this.indicadorQuantidadeErrosContas = helper.getIndicadorQuantidadeErrosContas();
		this.indicadorValorErrosContas = helper.getIndicadorValorErrosContas();
		this.indicadorRecebimentoMedio = helper.getIndicadorRecebimentoMedio();
		this.indicadorQuantidadeInadimplenciaAte30 = helper.getIndicadorQuantidadeInadimplenciaAte30();
		this.indicadorValorInadimplenciaAte30 = helper.getIndicadorValorInadimplenciaAte30();
		this.indicadorQuantidadeInadimplenciaAte90 = helper.getIndicadorQuantidadeInadimplenciaAte90();
		this.indicadorValorInadimplenciaAte90 = helper.getIndicadorValorInadimplenciaAte90();
		this.indicadorQuantidadeInadimplenciaMaior90 = helper.getIndicadorQuantidadeInadimplenciaMaior90();
		this.indicadorValorInadimplenciaMaior90 = helper.getIndicadorValorInadimplenciaMaior90();
		this.quantidadeFaturamentosComprometidos = helper.getQuantidadeFaturamentosComprometidos();
		this.indiceHidrometracao = helper.getIndiceHidrometracao();
		this.quantidadeHidrometrosInstalados = helper.getQuantidadeHidrometrosInstalados();
		this.quantidadeHidrometrosSubstituidos = helper.getQuantidadeHidrometrosSubstituidos();
		this.prazoMedioAtendimentoOS = helper.getPrazoMedioAtendimentoOS();
		this.quantidadeNovasLigacoesEsgoto = helper.getQuantidadeNovasLigacoesEsgoto();
		this.economiasNovasLigacoesEsgoto = helper.getEconomiasNovasLigacoesEsgoto();
		this.quantidadeNovasLigacoesAgua = helper.getQuantidadeNovasLigacoesAgua();
		this.economiasNovasLigacoesAgua = helper.getEconomiasNovasLigacoesAgua();
		this.quantidadeConsumidoresLigados = helper.getQuantidadeConsumidoresLigados();
		this.quantidadeConsumidoresCortados = helper.getQuantidadeConsumidoresCortados();
		this.quantidadeConsumidoresSuprimidos = helper.getQuantidadeConsumidoresSuprimidos();
		this.quantidadeConsumidoresFactiveis = helper.getQuantidadeConsumidoresFactiveis();
		this.quantidadeConsumidoresTotal = helper.getQuantidadeConsumidoresTotal();
		this.indiceCortados = helper.getIndiceCortados();
		this.indiceSuprimidos = helper.getIndiceSuprimidos();
		this.indiceFactiveis = helper.getIndiceFactiveis();
		this.idLocalidade = helper.getIdLocalidade();
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public BigDecimal getValorFaturamento() {
		return valorFaturamento;
	}

	public void setValorFaturamento(BigDecimal valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}

	public BigDecimal getValorArrecadacao() {
		return valorArrecadacao;
	}

	public void setValorArrecadacao(BigDecimal valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	public BigDecimal getEficienciaArrecadacao() {
		return eficienciaArrecadacao;
	}

	public void setEficienciaArrecadacao(BigDecimal eficienciaArrecadacao) {
		this.eficienciaArrecadacao = eficienciaArrecadacao;
	}

	public BigDecimal getValorMedioFaturamento() {
		return valorMedioFaturamento;
	}

	public void setValorMedioFaturamento(BigDecimal valorMedioFaturamento) {
		this.valorMedioFaturamento = valorMedioFaturamento;
	}

	public BigDecimal getIndicadorQuantidadeErrosContas() {
		return indicadorQuantidadeErrosContas;
	}

	public void setIndicadorQuantidadeErrosContas(
			BigDecimal indicadorQuantidadeErrosContas) {
		this.indicadorQuantidadeErrosContas = indicadorQuantidadeErrosContas;
	}

	public BigDecimal getIndicadorValorErrosContas() {
		return indicadorValorErrosContas;
	}

	public void setIndicadorValorErrosContas(BigDecimal indicadorValorErrosContas) {
		this.indicadorValorErrosContas = indicadorValorErrosContas;
	}

	public BigDecimal getIndicadorRecebimentoMedio() {
		return indicadorRecebimentoMedio;
	}

	public void setIndicadorRecebimentoMedio(BigDecimal indicadorRecebimentoMedio) {
		this.indicadorRecebimentoMedio = indicadorRecebimentoMedio;
	}

	public BigDecimal getIndicadorQuantidadeInadimplenciaAte30() {
		return indicadorQuantidadeInadimplenciaAte30;
	}

	public void setIndicadorQuantidadeInadimplenciaAte30(
			BigDecimal indicadorQuantidadeInadimplenciaAte30) {
		this.indicadorQuantidadeInadimplenciaAte30 = indicadorQuantidadeInadimplenciaAte30;
	}

	public BigDecimal getIndicadorValorInadimplenciaAte30() {
		return indicadorValorInadimplenciaAte30;
	}

	public void setIndicadorValorInadimplenciaAte30(
			BigDecimal indicadorValorInadimplenciaAte30) {
		this.indicadorValorInadimplenciaAte30 = indicadorValorInadimplenciaAte30;
	}

	public BigDecimal getIndicadorQuantidadeInadimplenciaAte90() {
		return indicadorQuantidadeInadimplenciaAte90;
	}

	public void setIndicadorQuantidadeInadimplenciaAte90(
			BigDecimal indicadorQuantidadeInadimplenciaAte90) {
		this.indicadorQuantidadeInadimplenciaAte90 = indicadorQuantidadeInadimplenciaAte90;
	}

	public BigDecimal getIndicadorValorInadimplenciaAte90() {
		return indicadorValorInadimplenciaAte90;
	}

	public void setIndicadorValorInadimplenciaAte90(
			BigDecimal indicadorValorInadimplenciaAte90) {
		this.indicadorValorInadimplenciaAte90 = indicadorValorInadimplenciaAte90;
	}

	public BigDecimal getIndicadorQuantidadeInadimplenciaMaior90() {
		return indicadorQuantidadeInadimplenciaMaior90;
	}

	public void setIndicadorQuantidadeInadimplenciaMaior90(
			BigDecimal indicadorQuantidadeInadimplenciaMaior90) {
		this.indicadorQuantidadeInadimplenciaMaior90 = indicadorQuantidadeInadimplenciaMaior90;
	}

	public BigDecimal getIndicadorValorInadimplenciaMaior90() {
		return indicadorValorInadimplenciaMaior90;
	}

	public void setIndicadorValorInadimplenciaMaior90(
			BigDecimal indicadorValorInadimplenciaMaior90) {
		this.indicadorValorInadimplenciaMaior90 = indicadorValorInadimplenciaMaior90;
	}

	public BigDecimal getQuantidadeFaturamentosComprometidos() {
		return quantidadeFaturamentosComprometidos;
	}

	public void setQuantidadeFaturamentosComprometidos(
			BigDecimal quantidadeFaturamentosComprometidos) {
		this.quantidadeFaturamentosComprometidos = quantidadeFaturamentosComprometidos;
	}

	public BigDecimal getIndiceHidrometracao() {
		return indiceHidrometracao;
	}

	public void setIndiceHidrometracao(BigDecimal indiceHidrometracao) {
		this.indiceHidrometracao = indiceHidrometracao;
	}

	public Integer getQuantidadeHidrometrosInstalados() {
		return quantidadeHidrometrosInstalados;
	}

	public void setQuantidadeHidrometrosInstalados(
			Integer quantidadeHidrometrosInstalados) {
		this.quantidadeHidrometrosInstalados = quantidadeHidrometrosInstalados;
	}

	public Integer getQuantidadeHidrometrosSubstituidos() {
		return quantidadeHidrometrosSubstituidos;
	}

	public void setQuantidadeHidrometrosSubstituidos(
			Integer quantidadeHidrometrosSubstituidos) {
		this.quantidadeHidrometrosSubstituidos = quantidadeHidrometrosSubstituidos;
	}

	public BigDecimal getPrazoMedioAtendimentoOS() {
		return prazoMedioAtendimentoOS;
	}

	public void setPrazoMedioAtendimentoOS(BigDecimal prazoMedioAtendimentoOS) {
		this.prazoMedioAtendimentoOS = prazoMedioAtendimentoOS;
	}

	public Integer getQuantidadeNovasLigacoesEsgoto() {
		return quantidadeNovasLigacoesEsgoto;
	}

	public void setQuantidadeNovasLigacoesEsgoto(
			Integer quantidadeNovasLigacoesEsgoto) {
		this.quantidadeNovasLigacoesEsgoto = quantidadeNovasLigacoesEsgoto;
	}

	public Integer getEconomiasNovasLigacoesEsgoto() {
		return economiasNovasLigacoesEsgoto;
	}

	public void setEconomiasNovasLigacoesEsgoto(Integer economiasNovasLigacoesEsgoto) {
		this.economiasNovasLigacoesEsgoto = economiasNovasLigacoesEsgoto;
	}

	public Integer getQuantidadeNovasLigacoesAgua() {
		return quantidadeNovasLigacoesAgua;
	}

	public void setQuantidadeNovasLigacoesAgua(Integer quantidadeNovasLigacoesAgua) {
		this.quantidadeNovasLigacoesAgua = quantidadeNovasLigacoesAgua;
	}

	public Integer getEconomiasNovasLigacoesAgua() {
		return economiasNovasLigacoesAgua;
	}

	public void setEconomiasNovasLigacoesAgua(Integer economiasNovasLigacoesAgua) {
		this.economiasNovasLigacoesAgua = economiasNovasLigacoesAgua;
	}

	public Integer getQuantidadeConsumidoresLigados() {
		return quantidadeConsumidoresLigados;
	}

	public void setQuantidadeConsumidoresLigados(
			Integer quantidadeConsumidoresLigados) {
		this.quantidadeConsumidoresLigados = quantidadeConsumidoresLigados;
	}

	public Integer getQuantidadeConsumidoresCortados() {
		return quantidadeConsumidoresCortados;
	}

	public void setQuantidadeConsumidoresCortados(
			Integer quantidadeConsumidoresCortados) {
		this.quantidadeConsumidoresCortados = quantidadeConsumidoresCortados;
	}

	public Integer getQuantidadeConsumidoresSuprimidos() {
		return quantidadeConsumidoresSuprimidos;
	}

	public void setQuantidadeConsumidoresSuprimidos(
			Integer quantidadeConsumidoresSuprimidos) {
		this.quantidadeConsumidoresSuprimidos = quantidadeConsumidoresSuprimidos;
	}

	public Integer getQuantidadeConsumidoresFactiveis() {
		return quantidadeConsumidoresFactiveis;
	}

	public void setQuantidadeConsumidoresFactiveis(
			Integer quantidadeConsumidoresFactiveis) {
		this.quantidadeConsumidoresFactiveis = quantidadeConsumidoresFactiveis;
	}

	public Integer getQuantidadeConsumidoresTotal() {
		return quantidadeConsumidoresTotal;
	}

	public void setQuantidadeConsumidoresTotal(Integer quantidadeConsumidoresTotal) {
		this.quantidadeConsumidoresTotal = quantidadeConsumidoresTotal;
	}

	public BigDecimal getIndiceCortados() {
		return indiceCortados;
	}

	public void setIndiceCortados(BigDecimal indiceCortados) {
		this.indiceCortados = indiceCortados;
	}

	public BigDecimal getIndiceSuprimidos() {
		return indiceSuprimidos;
	}

	public void setIndiceSuprimidos(BigDecimal indiceSuprimidos) {
		this.indiceSuprimidos = indiceSuprimidos;
	}

	public BigDecimal getIndiceFactiveis() {
		return indiceFactiveis;
	}

	public void setIndiceFactiveis(BigDecimal indiceFactiveis) {
		this.indiceFactiveis = indiceFactiveis;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
}
