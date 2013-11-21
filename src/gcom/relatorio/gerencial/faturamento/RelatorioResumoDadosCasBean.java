package gcom.relatorio.gerencial.faturamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioResumoDadosCasBean implements RelatorioBean{
	private static final long serialVersionUID = 1L;
	
	private Long totalLigacoesAtivasAgua;
	private Long totalLigacoesInativasAgua;
	private Long totalLigacoesAtvInatAgua;
	private Long totalLigacoesCortadasAgua;	
	private Long totalLigacoesAnaliseAgua;
	private Long totalLigacoesRamaisLigAgua;
	private Long totalLigacoesMedidasAgua;
	private Long totalLigacoesNaoMedidasAgua;
	
	private Long totalLigacoesAtivasEsgoto;
	private Long totalLigacoesInativasEsgoto;
	private Long totalLigacoesAtivInatEsgoto;
	private Long totalLigacoesTamponatoEsgoto;
	private Long totalLigacoesAnaliseEsgoto;
	private Long totalLigacoesRamaisLigEsgoto;
	private Long totalLigacoesMedidasEsgoto;	
	private Long totalLigacoesNaoMedidasEsgoto;
	
	private Long totalLigacoesAguaSuprimidas;
	
	private Long numeroEconomiasAgua;
	private Long numeroEconomiasEsgoto;
	
	private BigDecimal totalArrecadacaoAtual;	
	private BigDecimal totalArrecadacaoAnterior;	
	
	private Long contasEmitidas;
	private BigDecimal totalVolumeFaturadoAgua;
	private BigDecimal totalValorFaturadoAgua;
	private BigDecimal totalVolumeFaturadoEsgoto;
	private BigDecimal totalValorFaturadoEsgoto;
	
	private BigDecimal indiceHidrometracaoAgua;	
	
	private BigDecimal valorPendencia;	
	
	private BigDecimal totalFaturamentoLiquidoAtual;
	private BigDecimal totalFaturamentoLiquidoAnterior;
	
	private BigDecimal indiceArrecadacao;
	private BigDecimal indiceContasAReceber;
	
	private Long totalLigacoesFaturadasAgua;
	private Long totalLigacoesFaturadasEsgoto;
	
	private Long totalHidrometrosInstaladosAgua;
	private Long totalHidrometrosInstaladosEsgoto;
	private Long totalHidrometrosSubstituidosAgua;
	private Long totalHidrometrosSubstituidosEsgoto;
	
	private String unidadeNegocioID;
	private String unidadeNegocio;
	private String localidadeID;
	private String localidade;
	private String gerenciaRegionalID;
	private String gerenciaRegional;
	private String municipioId;
	private String municipio;
			
	public Long getTotalLigacoesAtivasAgua() {
		return totalLigacoesAtivasAgua;
	}
	public void setTotalLigacoesAtivasAgua(Long totalLigacoesAtivasAgua) {
		this.totalLigacoesAtivasAgua = totalLigacoesAtivasAgua;
	}
	public Long getTotalLigacoesInativasAgua() {
		return totalLigacoesInativasAgua;
	}
	public void setTotalLigacoesInativasAgua(Long totalLigacoesInativasAgua) {
		this.totalLigacoesInativasAgua = totalLigacoesInativasAgua;
	}
	public Long getTotalLigacoesAtvInatAgua() {
		return totalLigacoesAtvInatAgua;
	}
	public void setTotalLigacoesAtvInatAgua(Long totalLigacoesAtvInatAgua) {
		this.totalLigacoesAtvInatAgua = totalLigacoesAtvInatAgua;
	}
	public Long getTotalLigacoesCortadasAgua() {
		return totalLigacoesCortadasAgua;
	}
	public void setTotalLigacoesCortadasAgua(Long totalLigacoesCortadasAgua) {
		this.totalLigacoesCortadasAgua = totalLigacoesCortadasAgua;
	}
	public Long getTotalLigacoesAnaliseAgua() {
		return totalLigacoesAnaliseAgua;
	}
	public void setTotalLigacoesAnaliseAgua(Long totalLigacoesAnaliseAgua) {
		this.totalLigacoesAnaliseAgua = totalLigacoesAnaliseAgua;
	}
	public Long getTotalLigacoesRamaisLigAgua() {
		return totalLigacoesRamaisLigAgua;
	}
	public void setTotalLigacoesRamaisLigAgua(Long totalLigacoesRamaisLigAgua) {
		this.totalLigacoesRamaisLigAgua = totalLigacoesRamaisLigAgua;
	}
	public Long getTotalLigacoesMedidasAgua() {
		return totalLigacoesMedidasAgua;
	}
	public void setTotalLigacoesMedidasAgua(Long totalLigacoesMedidasAgua) {
		this.totalLigacoesMedidasAgua = totalLigacoesMedidasAgua;
	}
	public Long getTotalLigacoesNaoMedidasAgua() {
		return totalLigacoesNaoMedidasAgua;
	}
	public void setTotalLigacoesNaoMedidasAgua(Long totalLigacoesNaoMedidasAgua) {
		this.totalLigacoesNaoMedidasAgua = totalLigacoesNaoMedidasAgua;
	}
	public Long getTotalLigacoesAtivasEsgoto() {
		return totalLigacoesAtivasEsgoto;
	}
	public void setTotalLigacoesAtivasEsgoto(Long totalLigacoesAtivasEsgoto) {
		this.totalLigacoesAtivasEsgoto = totalLigacoesAtivasEsgoto;
	}
	public Long getTotalLigacoesInativasEsgoto() {
		return totalLigacoesInativasEsgoto;
	}
	public void setTotalLigacoesInativasEsgoto(Long totalLigacoesInativasEsgoto) {
		this.totalLigacoesInativasEsgoto = totalLigacoesInativasEsgoto;
	}
	public Long getTotalLigacoesAtivInatEsgoto() {
		return totalLigacoesAtivInatEsgoto;
	}
	public void setTotalLigacoesAtivInatEsgoto(Long totalLigacoesAtivInatEsgoto) {
		this.totalLigacoesAtivInatEsgoto = totalLigacoesAtivInatEsgoto;
	}
	public Long getTotalLigacoesTamponatoEsgoto() {
		return totalLigacoesTamponatoEsgoto;
	}
	public void setTotalLigacoesTamponatoEsgoto(Long totalLigacoesTamponatoEsgoto) {
		this.totalLigacoesTamponatoEsgoto = totalLigacoesTamponatoEsgoto;
	}
	public Long getTotalLigacoesAnaliseEsgoto() {
		return totalLigacoesAnaliseEsgoto;
	}
	public void setTotalLigacoesAnaliseEsgoto(Long totalLigacoesAnaliseEsgoto) {
		this.totalLigacoesAnaliseEsgoto = totalLigacoesAnaliseEsgoto;
	}
	public Long getTotalLigacoesRamaisLigEsgoto() {
		return totalLigacoesRamaisLigEsgoto;
	}
	public void setTotalLigacoesRamaisLigEsgoto(Long totalLigacoesRamaisLigEsgoto) {
		this.totalLigacoesRamaisLigEsgoto = totalLigacoesRamaisLigEsgoto;
	}
	public Long getTotalLigacoesMedidasEsgoto() {
		return totalLigacoesMedidasEsgoto;
	}
	public void setTotalLigacoesMedidasEsgoto(Long totalLigacoesMedidasEsgoto) {
		this.totalLigacoesMedidasEsgoto = totalLigacoesMedidasEsgoto;
	}
	public Long getTotalLigacoesNaoMedidasEsgoto() {
		return totalLigacoesNaoMedidasEsgoto;
	}
	public void setTotalLigacoesNaoMedidasEsgoto(Long totalLigacoesNaoMedidasEsgoto) {
		this.totalLigacoesNaoMedidasEsgoto = totalLigacoesNaoMedidasEsgoto;
	}
	public Long getNumeroEconomiasAgua() {
		return numeroEconomiasAgua;
	}
	public void setNumeroEconomiasAgua(Long numeroEconomiasAgua) {
		this.numeroEconomiasAgua = numeroEconomiasAgua;
	}
	public Long getNumeroEconomiasEsgoto() {
		return numeroEconomiasEsgoto;
	}
	public void setNumeroEconomiasEsgoto(Long numeroEconomiasEsgoto) {
		this.numeroEconomiasEsgoto = numeroEconomiasEsgoto;
	}
	public BigDecimal getTotalArrecadacaoAtual() {
		return totalArrecadacaoAtual;
	}
	public void setTotalArrecadacaoAtual(BigDecimal totalArrecadacaoAtual) {
		this.totalArrecadacaoAtual = totalArrecadacaoAtual;
	}	
	public BigDecimal getTotalArrecadacaoAnterior() {
		return totalArrecadacaoAnterior;
	}
	public void setTotalArrecadacaoAnterior(BigDecimal totalArrecadacaoAnterior) {
		this.totalArrecadacaoAnterior = totalArrecadacaoAnterior;
	}
	public Long getContasEmitidas() {
		return contasEmitidas;
	}
	public void setContasEmitidas(Long contasEmitidas) {
		this.contasEmitidas = contasEmitidas;
	}
	public BigDecimal getTotalVolumeFaturadoAgua() {
		return totalVolumeFaturadoAgua;
	}
	public void setTotalVolumeFaturadoAgua(BigDecimal totalVolumeFaturadoAgua) {
		this.totalVolumeFaturadoAgua = totalVolumeFaturadoAgua;
	}
	public BigDecimal getTotalVolumeFaturadoEsgoto() {
		return totalVolumeFaturadoEsgoto;
	}
	public void setTotalVolumeFaturadoEsgoto(BigDecimal totalVolumeFaturadoEsgoto) {
		this.totalVolumeFaturadoEsgoto = totalVolumeFaturadoEsgoto;
	}
	public BigDecimal getTotalValorFaturadoAgua() {
		return totalValorFaturadoAgua;
	}
	public void setTotalValorFaturadoAgua(BigDecimal totalValorFaturadoAgua) {
		this.totalValorFaturadoAgua = totalValorFaturadoAgua;
	}
	public BigDecimal getTotalValorFaturadoEsgoto() {
		return totalValorFaturadoEsgoto;
	}
	public void setTotalValorFaturadoEsgoto(BigDecimal totalValorFaturadoEsgoto) {
		this.totalValorFaturadoEsgoto = totalValorFaturadoEsgoto;
	}
	public BigDecimal getIndiceHidrometracaoAgua() {
		return indiceHidrometracaoAgua;
	}
	public void setIndiceHidrometracaoAgua(BigDecimal indiceHidrometracaoAgua) {
		this.indiceHidrometracaoAgua = indiceHidrometracaoAgua;
	}		
	public BigDecimal getValorPendencia() {
		return valorPendencia;
	}
	public void setValorPendencia(BigDecimal valorPendencia) {
		this.valorPendencia = valorPendencia;
	}	
	public Long getTotalLigacoesAguaSuprimidas() {
		return totalLigacoesAguaSuprimidas;
	}
	public void setTotalLigacoesAguaSuprimidas(Long totalLigacoesAguaSuprimidas) {
		this.totalLigacoesAguaSuprimidas = totalLigacoesAguaSuprimidas;
	}	
	public BigDecimal getTotalFaturamentoLiquidoAtual() {
		return totalFaturamentoLiquidoAtual;
	}
	public void setTotalFaturamentoLiquidoAtual(
			BigDecimal totalFaturamentoLiquidoAtual) {
		this.totalFaturamentoLiquidoAtual = totalFaturamentoLiquidoAtual;
	}
	public BigDecimal getTotalFaturamentoLiquidoAnterior() {
		return totalFaturamentoLiquidoAnterior;
	}
	public void setTotalFaturamentoLiquidoAnterior(
			BigDecimal totalFaturamentoLiquidoAnterior) {
		this.totalFaturamentoLiquidoAnterior = totalFaturamentoLiquidoAnterior;
	}	
	public BigDecimal getIndiceArrecadacao() {
		return indiceArrecadacao;
	}
	public void setIndiceArrecadacao(BigDecimal indiceArrecadacao) {
		this.indiceArrecadacao = indiceArrecadacao;
	}
	public BigDecimal getIndiceContasAReceber() {
		return indiceContasAReceber;
	}
	public void setIndiceContasAReceber(BigDecimal indiceContasAReceber) {
		this.indiceContasAReceber = indiceContasAReceber;
	}
	public String getUnidadeNegocioID() {
		return unidadeNegocioID;
	}
	public void setUnidadeNegocioID(String unidadeNegocioID) {
		this.unidadeNegocioID = unidadeNegocioID;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidadeID() {
		return localidadeID;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getGerenciaRegionalID() {
		return gerenciaRegionalID;
	}
	public void setGerenciaRegionalID(String gerenciaRegionalID) {
		this.gerenciaRegionalID = gerenciaRegionalID;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Long getTotalLigacoesFaturadasAgua() {
		return totalLigacoesFaturadasAgua;
	}
	public void setTotalLigacoesFaturadasAgua(Long totalLigacoesFaturadasAgua) {
		this.totalLigacoesFaturadasAgua = totalLigacoesFaturadasAgua;
	}
	public Long getTotalLigacoesFaturadasEsgoto() {
		return totalLigacoesFaturadasEsgoto;
	}
	public void setTotalLigacoesFaturadasEsgoto(Long totalLigacoesFaturadasEsgoto) {
		this.totalLigacoesFaturadasEsgoto = totalLigacoesFaturadasEsgoto;
	}
	public Long getTotalHidrometrosInstaladosAgua() {
		return totalHidrometrosInstaladosAgua;
	}
	public void setTotalHidrometrosInstaladosAgua(
			Long totalHidrometrosInstaladosAgua) {
		this.totalHidrometrosInstaladosAgua = totalHidrometrosInstaladosAgua;
	}
	public Long getTotalHidrometrosInstaladosEsgoto() {
		return totalHidrometrosInstaladosEsgoto;
	}
	public void setTotalHidrometrosInstaladosEsgoto(
			Long totalHidrometrosInstaladosEsgoto) {
		this.totalHidrometrosInstaladosEsgoto = totalHidrometrosInstaladosEsgoto;
	}
	public Long getTotalHidrometrosSubstituidosAgua() {
		return totalHidrometrosSubstituidosAgua;
	}
	public void setTotalHidrometrosSubstituidosAgua(
			Long totalHidrometrosSubstituidosAgua) {
		this.totalHidrometrosSubstituidosAgua = totalHidrometrosSubstituidosAgua;
	}
	public Long getTotalHidrometrosSubstituidosEsgoto() {
		return totalHidrometrosSubstituidosEsgoto;
	}
	public void setTotalHidrometrosSubstituidosEsgoto(
			Long totalHidrometrosSubstituidosEsgoto) {
		this.totalHidrometrosSubstituidosEsgoto = totalHidrometrosSubstituidosEsgoto;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getMunicipioId() {
		return municipioId;
	}
	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}
		
}