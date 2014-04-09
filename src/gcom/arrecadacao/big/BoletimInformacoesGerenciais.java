package gcom.arrecadacao.big;

import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class BoletimInformacoesGerenciais extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer anoMesReferencia; 
	
	private Localidade localidade; 
	
	private Integer quantidadeContas; // C
	
	private BigDecimal valorFaturamento; // D
	
	private BigDecimal valorArrecadacao; // E
	
	private BigDecimal eficienciaArrecadacao; // F
	
	private BigDecimal valorMedioFaturamento; // G
	
	private BigDecimal indicadorQuantidadeErrosContas; // H
	
	private BigDecimal indicadorValorErrosContas; // I 
	
	private BigDecimal indicadorRecebimentoMedio; // J
	
	private BigDecimal indicadorQuantidadeInadimplenciaAte30; // K
	
	private BigDecimal indicadorValorInadimplenciaAte30; // L
	
	private BigDecimal indicadorQuantidadeInadimplenciaAte90; // M
	
	private BigDecimal indicadorValorInadimplenciaAte90; // N
	
	private BigDecimal indicadorQuantidadeInadimplenciaMaior90; // O
	
	private BigDecimal indicadorValorInadimplenciaMaior90; // P
	
	private BigDecimal quantidadeFaturamentosComprometidos; // Q
	
	private BigDecimal indiceHidrometracao; // R
	
	private Integer quantidadeHidrometrosInstalados; // S
	
	private Integer quantidadeHidrometrosSubstituidos; // T
	
	private BigDecimal prazoMedioAtendimentoOS; // U
	
	private Integer quantidadeNovasLigacoesEsgoto; // V
	
	private Integer economiasNovasLigacoesEsgoto; // W
	
	private Integer quantidadeNovasLigacoesAgua; // X
	
	private Integer economiasNovasLigacoesAgua; // Y
	
	private Integer quantidadeConsumidoresLigados; // Z
	
	private Integer quantidadeConsumidoresCortados; // AA
	
	private Integer quantidadeConsumidoresSuprimidos; // AB
	
	private Integer quantidadeConsumidoresFactiveis; // AC
	
	private Integer quantidadeConsumidoresTotal; // AD
	
	private BigDecimal indiceCortados; // AE
	
	private BigDecimal indiceSuprimidos; // AF
	
	private BigDecimal indiceFactiveis; // AG
	
	private Date ultimaAlteracao;

	public BoletimInformacoesGerenciais() {
		super();
	}

	public BoletimInformacoesGerenciais(Integer id, Integer anoMesReferencia,
			Localidade localidade, Integer quantidadeContas,
			BigDecimal valorFaturamento, BigDecimal valorArrecadacao,
			BigDecimal eficienciaArrecadacao, BigDecimal valorMedioFaturamento,
			BigDecimal indicadorQuantidadeErrosContas,
			BigDecimal indicadorValorErrosContas,
			BigDecimal indicadorRecebimentoMedio,
			BigDecimal indicadorQuantidadeInadimplenciaAte30,
			BigDecimal indicadorValorInadimplenciaAte30,
			BigDecimal indicadorQuantidadeInadimplenciaAte90,
			BigDecimal indicadorValorInadimplenciaAte90,
			BigDecimal indicadorQuantidadeInadimplenciaMaior90,
			BigDecimal indicadorValorInadimplenciaMaior90,
			BigDecimal quantidadeFaturamentosComprometidos,
			BigDecimal indiceHidrometracao,
			Integer quantidadeHidrometrosInstalados,
			Integer quantidadeHidrometrosSubstituidos,
			BigDecimal prazoMedioAtendimentoOS,
			Integer quantidadeNovasLigacoesEsgoto,
			Integer economiasNovasLigacoesEsgoto,
			Integer quantidadeNovasLigacoesAgua,
			Integer economiasNovasLigacoesAgua,
			Integer quantidadeConsumidoresLigados,
			Integer quantidadeConsumidoresCortados,
			Integer quantidadeConsumidoresSuprimidos,
			Integer quantidadeConsumidoresFactiveis,
			Integer quantidadeConsumidoresTotal, BigDecimal indiceCortados,
			BigDecimal indiceSuprimidos, BigDecimal indiceFactiveis,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.localidade = localidade;
		this.quantidadeContas = quantidadeContas;
		this.valorFaturamento = valorFaturamento;
		this.valorArrecadacao = valorArrecadacao;
		this.eficienciaArrecadacao = eficienciaArrecadacao;
		this.valorMedioFaturamento = valorMedioFaturamento;
		this.indicadorQuantidadeErrosContas = indicadorQuantidadeErrosContas;
		this.indicadorValorErrosContas = indicadorValorErrosContas;
		this.indicadorRecebimentoMedio = indicadorRecebimentoMedio;
		this.indicadorQuantidadeInadimplenciaAte30 = indicadorQuantidadeInadimplenciaAte30;
		this.indicadorValorInadimplenciaAte30 = indicadorValorInadimplenciaAte30;
		this.indicadorQuantidadeInadimplenciaAte90 = indicadorQuantidadeInadimplenciaAte90;
		this.indicadorValorInadimplenciaAte90 = indicadorValorInadimplenciaAte90;
		this.indicadorQuantidadeInadimplenciaMaior90 = indicadorQuantidadeInadimplenciaMaior90;
		this.indicadorValorInadimplenciaMaior90 = indicadorValorInadimplenciaMaior90;
		this.quantidadeFaturamentosComprometidos = quantidadeFaturamentosComprometidos;
		this.indiceHidrometracao = indiceHidrometracao;
		this.quantidadeHidrometrosInstalados = quantidadeHidrometrosInstalados;
		this.quantidadeHidrometrosSubstituidos = quantidadeHidrometrosSubstituidos;
		this.prazoMedioAtendimentoOS = prazoMedioAtendimentoOS;
		this.quantidadeNovasLigacoesEsgoto = quantidadeNovasLigacoesEsgoto;
		this.economiasNovasLigacoesEsgoto = economiasNovasLigacoesEsgoto;
		this.quantidadeNovasLigacoesAgua = quantidadeNovasLigacoesAgua;
		this.economiasNovasLigacoesAgua = economiasNovasLigacoesAgua;
		this.quantidadeConsumidoresLigados = quantidadeConsumidoresLigados;
		this.quantidadeConsumidoresCortados = quantidadeConsumidoresCortados;
		this.quantidadeConsumidoresSuprimidos = quantidadeConsumidoresSuprimidos;
		this.quantidadeConsumidoresFactiveis = quantidadeConsumidoresFactiveis;
		this.quantidadeConsumidoresTotal = quantidadeConsumidoresTotal;
		this.indiceCortados = indiceCortados;
		this.indiceSuprimidos = indiceSuprimidos;
		this.indiceFactiveis = indiceFactiveis;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
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

	public void setIndicadorQuantidadeErrosContas(BigDecimal indicadorQuantidadeErrosContas) {
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
	
	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroBoletimInformacoesGerenciais filtroBig = new FiltroBoletimInformacoesGerenciais();
		filtroBig.adicionarParametro(new ParametroSimples(FiltroBoletimInformacoesGerenciais.ID, this.getId()));
		
		return filtroBig;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	
}
