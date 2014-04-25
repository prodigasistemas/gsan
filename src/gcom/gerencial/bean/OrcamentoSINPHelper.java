package gcom.gerencial.bean;

import gcom.util.Util;

import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de relatorio
 *
 * @author Rafael Pinto
 * @date 20/11/2007
 */
public class OrcamentoSINPHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao = "";
	
	private String opcaoAgrupamento = "";
	
	private Integer aguaTotalLigacoesCadastradas = 0;
	private Integer esgotoTotalLigacoesCadastradas = 0;
	private Integer esgotoTotalLigacoesCadastradasConvencional = 0;
	private Integer aguaTotalLigacoesAtivas = 0;
	private Integer esgotoTotalLigacoesCadastradasCondominial = 0;
	private Integer aguaTotalLigacoesMedidas = 0;
	private Integer esgotoTotalLigacoesAtivasConvencional = 0;
	private Integer aguaTotalLigacoesComHidrometro = 0;
	private Integer esgotoTotalLigacoesAtivasCondominial = 0;
	private Integer aguaTotalLigacoesResidencialCadastradas = 0;
	private Integer esgotoTotalLigacoesResidencialCadastradas = 0;
	private Integer aguaTotalLigacoesDesligadas = 0;
	private Integer aguaTotalEconomiasCadastradas = 0;
	private Integer esgotoTotalEconomiasCadastradasConvencional = 0;
	private Integer esgotoTotalEconomiasCadastradasCondominial = 0;
	private Integer aguaTotalLigacoesFaturadasMedidas = 0;
	private Integer esgotoTotalLigacoesFaturadasMedidas = 0;
	private Integer aguaTotalLigacoesFaturadasNaoMedidas = 0;
	private Integer esgotoTotalLigacoesFaturadasNaoMedidas = 0;
	
	private Integer aguaTotalEconomiasAtivas = 0;
	private Integer aguaTotalEconomiasAtivasMedidas = 0;
	private Integer esgotoTotalEconomiasAtivasConvencional = 0;
	private Integer aguaTotalEconomiasResidencialCadastradas = 0;
	private Integer esgotoTotalEconomiasAtivasCondominial = 0;
	private Integer aguaTotalEconomiasResidencialAtivasMicromedidas = 0;
	private Integer esgotoTotalEconomiasResidencialCadastradas = 0;
	private Integer aguaTotalEconomiasResidencialAtivas = 0;
	private Integer esgotoTotalEconomiasResidencialAtivas = 0;
	private Integer aguaTotalEconomiasComercialAtivas = 0;
	private Integer esgotoTotalEconomiasComercialAtivas = 0;
	private Integer aguaTotalEconomiasIndustrialAtivas = 0;
	private Integer esgotoTotalEconomiasIndustrialAtivas = 0;
	private Integer aguaTotalEconomiasPublicoAtivas = 0;
	private Integer esgotoTotalEconomiasPublicoAtivas = 0;
	private Integer aguaTotalEconomiasRuralAtivas = 0;
	private Integer esgotoTotalEconomiasRuralAtivas = 0;
	private Integer aguaTotalEconomiasFaturadasMedidas = 0;
	private Integer esgotoTotalEconomiasFaturadasMedidas = 0;
	private Integer aguaTotalEconomiasFaturadasNaoMedidas = 0;
	private Integer esgotoTotalEconomiasFaturadasNaoMedidas = 0;
	
	private Integer aguaTotalVolumeFaturadoMedido = 0;
	private Integer esgotoTotalVolumeFaturadoResidencial = 0;
	private Integer esgotoTotalVolumeFaturadoComercial = 0;
	private Integer aguaTotalVolumeFaturadoEstimado = 0;
	private Integer esgotoTotalVolumeFaturadoIndustrial = 0;
	private Integer esgotoTotalVolumeFaturadoPublico = 0;
	private Integer aguaTotalVolumeFaturadoResidencial = 0;
	private Integer esgotoTotalVolumeFaturadoGeral = 0;
	private Integer aguaTotalVolumeFaturadoComercial = 0;
	private Integer aguaTotalVolumeFaturadoIndustrial = 0;
	private Integer aguaTotalVolumeFaturadoPublico = 0;
	private Integer aguaTotalVolumeFaturadoRural = 0;
	private Integer aguaTotalVolumeFaturadoGeral = 0;

	private BigDecimal aguaTotalFaturadoResidencial = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoResidencial = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoComercial = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoComercial = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoIndustrial = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoIndustrial = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoPublico = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoPublico = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoDireto = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoDireto = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoIndireto = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoIndireto = BigDecimal.ZERO;
	private BigDecimal devolucao = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoMedido = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoMedido = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturadoNaoMedido = BigDecimal.ZERO;
	private BigDecimal esgotoTotalFaturadoNaoMedido = BigDecimal.ZERO;
	
	
	private Integer aguaTotalLigacoesNovas = 0;
	private Integer esgotoTotalLigacoesNovas = 0;
	
	private Integer aguaTotalVolumeFaturadoMicroMedido = 0;
	private Integer aguaTotalVolumeFaturadoEconomiasResidenciasAtivas = 0;
	
	private BigDecimal totalArrecadacaoMesAtual = BigDecimal.ZERO;
	private BigDecimal totalArrecadacaoMesAnterior = BigDecimal.ZERO;
	
	private Integer aguaTotalLigacoesSuprimidas = 0;
	private BigDecimal saldoContasReceber = BigDecimal.ZERO;
	private BigDecimal saldoContasReceberParticular = BigDecimal.ZERO;
	private BigDecimal saldoContasReceberPublico = BigDecimal.ZERO;
	
	private BigDecimal receitaOperacionalTotal = BigDecimal.ZERO;
	private BigDecimal receitaOperacionalDireta = BigDecimal.ZERO;
	private BigDecimal receitaOperacionalIndireta = BigDecimal.ZERO;
	private BigDecimal aguaTotalFaturamentoGeralDI = BigDecimal.ZERO;
	private BigDecimal totalFaturamentoLiquido = BigDecimal.ZERO;
	
	
	public Integer getAguaTotalEconomiasAtivas() {
		return aguaTotalEconomiasAtivas;
	}
	
	public void setAguaTotalEconomiasAtivas(Integer aguaTotalEconomiasAtivas) {
		this.aguaTotalEconomiasAtivas = aguaTotalEconomiasAtivas;
	}
	
	public Integer getAguaTotalEconomiasAtivasMedidas() {
		return aguaTotalEconomiasAtivasMedidas;
	}
	
	public void setAguaTotalEconomiasAtivasMedidas(
			Integer aguaTotalEconomiasAtivasMedidas) {
		this.aguaTotalEconomiasAtivasMedidas = aguaTotalEconomiasAtivasMedidas;
	}
	
	public Integer getAguaTotalEconomiasCadastradas() {
		return aguaTotalEconomiasCadastradas;
	}
	
	public void setAguaTotalEconomiasCadastradas(
			Integer aguaTotalEconomiasCadastradas) {
		this.aguaTotalEconomiasCadastradas = aguaTotalEconomiasCadastradas;
	}
	
	public Integer getAguaTotalEconomiasComercialAtivas() {
		return aguaTotalEconomiasComercialAtivas;
	}
	
	public void setAguaTotalEconomiasComercialAtivas(
			Integer aguaTotalEconomiasComercialAtivas) {
		this.aguaTotalEconomiasComercialAtivas = aguaTotalEconomiasComercialAtivas;
	}
	
	public Integer getAguaTotalEconomiasIndustrialAtivas() {
		return aguaTotalEconomiasIndustrialAtivas;
	}
	
	public void setAguaTotalEconomiasIndustrialAtivas(
			Integer aguaTotalEconomiasIndustrialAtivas) {
		this.aguaTotalEconomiasIndustrialAtivas = aguaTotalEconomiasIndustrialAtivas;
	}
	
	public Integer getAguaTotalEconomiasPublicoAtivas() {
		return aguaTotalEconomiasPublicoAtivas;
	}
	
	public void setAguaTotalEconomiasPublicoAtivas(
			Integer aguaTotalEconomiasPublicoAtivas) {
		this.aguaTotalEconomiasPublicoAtivas = aguaTotalEconomiasPublicoAtivas;
	}
	
	public Integer getAguaTotalEconomiasResidencialAtivas() {
		return aguaTotalEconomiasResidencialAtivas;
	}
	
	public void setAguaTotalEconomiasResidencialAtivas(
			Integer aguaTotalEconomiasResidencialAtivas) {
		this.aguaTotalEconomiasResidencialAtivas = aguaTotalEconomiasResidencialAtivas;
	}
	
	public Integer getAguaTotalEconomiasResidencialAtivasMicromedidas() {
		return aguaTotalEconomiasResidencialAtivasMicromedidas;
	}
	
	public void setAguaTotalEconomiasResidencialAtivasMicromedidas(
			Integer aguaTotalEconomiasResidencialAtivasMicromedidas) {
		this.aguaTotalEconomiasResidencialAtivasMicromedidas = aguaTotalEconomiasResidencialAtivasMicromedidas;
	}
	
	public Integer getAguaTotalEconomiasResidencialCadastradas() {
		return aguaTotalEconomiasResidencialCadastradas;
	}
	
	public void setAguaTotalEconomiasResidencialCadastradas(
			Integer aguaTotalEconomiasResidencialCadastradas) {
		this.aguaTotalEconomiasResidencialCadastradas = aguaTotalEconomiasResidencialCadastradas;
	}
	
	public Integer getAguaTotalEconomiasRuralAtivas() {
		return aguaTotalEconomiasRuralAtivas;
	}
	
	public void setAguaTotalEconomiasRuralAtivas(
			Integer aguaTotalEconomiasRuralAtivas) {
		this.aguaTotalEconomiasRuralAtivas = aguaTotalEconomiasRuralAtivas;
	}
	
	public BigDecimal getAguaTotalFaturadoComercial() {
		return aguaTotalFaturadoComercial;
	}
	
	public void setAguaTotalFaturadoComercial(BigDecimal aguaTotalFaturadoComercial) {
		this.aguaTotalFaturadoComercial = aguaTotalFaturadoComercial;
	}
	
	public BigDecimal getAguaTotalFaturadoDireto() {
		return aguaTotalFaturadoDireto;
	}
	
	public void setAguaTotalFaturadoDireto(BigDecimal aguaTotalFaturadoDireto) {
		this.aguaTotalFaturadoDireto = aguaTotalFaturadoDireto;
	}
	
	public BigDecimal getAguaTotalFaturadoIndireto() {
		return aguaTotalFaturadoIndireto;
	}
	
	public void setAguaTotalFaturadoIndireto(BigDecimal aguaTotalFaturadoIndireto) {
		this.aguaTotalFaturadoIndireto = aguaTotalFaturadoIndireto;
	}
	
	public BigDecimal getAguaTotalFaturadoIndustrial() {
		return aguaTotalFaturadoIndustrial;
	}
	
	public void setAguaTotalFaturadoIndustrial(
			BigDecimal aguaTotalFaturadoIndustrial) {
		this.aguaTotalFaturadoIndustrial = aguaTotalFaturadoIndustrial;
	}
	
	public BigDecimal getAguaTotalFaturadoPublico() {
		return aguaTotalFaturadoPublico;
	}
	
	public void setAguaTotalFaturadoPublico(BigDecimal aguaTotalFaturadoPublico) {
		this.aguaTotalFaturadoPublico = aguaTotalFaturadoPublico;
	}
	
	public BigDecimal getAguaTotalFaturadoResidencial() {
		return aguaTotalFaturadoResidencial;
	}
	
	public void setAguaTotalFaturadoResidencial(
			BigDecimal aguaTotalFaturadoResidencial) {
		this.aguaTotalFaturadoResidencial = aguaTotalFaturadoResidencial;
	}
	
	public BigDecimal getAguaTotalFaturamentoGeralDI() {
		return aguaTotalFaturamentoGeralDI;
	}
	
	public Integer getAguaTotalLigacoesAtivas() {
		return aguaTotalLigacoesAtivas;
	}
	
	public void setAguaTotalLigacoesAtivas(Integer aguaTotalLigacoesAtivas) {
		this.aguaTotalLigacoesAtivas = aguaTotalLigacoesAtivas;
	}
	
	public Integer getAguaTotalLigacoesCadastradas() {
		return aguaTotalLigacoesCadastradas;
	}
	
	public void setAguaTotalLigacoesCadastradas(Integer aguaTotalLigacoesCadastradas) {
		this.aguaTotalLigacoesCadastradas = aguaTotalLigacoesCadastradas;
	}
	
	public Integer getAguaTotalLigacoesComHidrometro() {
		return aguaTotalLigacoesComHidrometro;
	}
	
	public void setAguaTotalLigacoesComHidrometro(
			Integer aguaTotalLigacoesComHidrometro) {
		this.aguaTotalLigacoesComHidrometro = aguaTotalLigacoesComHidrometro;
	}
	
	public Integer getAguaTotalLigacoesDesligadas() {
		return aguaTotalLigacoesDesligadas;
	}
	
	public void setAguaTotalLigacoesDesligadas(Integer aguaTotalLigacoesDesligadas) {
		this.aguaTotalLigacoesDesligadas = aguaTotalLigacoesDesligadas;
	}
	
	public Integer getAguaTotalLigacoesMedidas() {
		return aguaTotalLigacoesMedidas;
	}
	
	public void setAguaTotalLigacoesMedidas(Integer aguaTotalLigacoesMedidas) {
		this.aguaTotalLigacoesMedidas = aguaTotalLigacoesMedidas;
	}
	
	public Integer getAguaTotalLigacoesNovas() {
		return aguaTotalLigacoesNovas;
	}
	
	public void setAguaTotalLigacoesNovas(Integer aguaTotalLigacoesNovas) {
		this.aguaTotalLigacoesNovas = aguaTotalLigacoesNovas;
	}
	
	public Integer getAguaTotalLigacoesResidencialCadastradas() {
		return aguaTotalLigacoesResidencialCadastradas;
	}
	
	public void setAguaTotalLigacoesResidencialCadastradas(
			Integer aguaTotalLigacoesResidencialCadastradas) {
		this.aguaTotalLigacoesResidencialCadastradas = aguaTotalLigacoesResidencialCadastradas;
	}
	
	public Integer getAguaTotalVolumeFaturadoComercial() {
		return aguaTotalVolumeFaturadoComercial;
	}
	
	public void setAguaTotalVolumeFaturadoComercial(
			Integer aguaTotalVolumeFaturadoComercial) {
		this.aguaTotalVolumeFaturadoComercial = aguaTotalVolumeFaturadoComercial;
	}
	
	public Integer getAguaTotalVolumeFaturadoEconomiasResidenciasAtivas() {
		return aguaTotalVolumeFaturadoEconomiasResidenciasAtivas;
	}
	
	public void setAguaTotalVolumeFaturadoEconomiasResidenciasAtivas(
			Integer aguaTotalVolumeFaturadoEconomiasResidenciasAtivas) {
		this.aguaTotalVolumeFaturadoEconomiasResidenciasAtivas = aguaTotalVolumeFaturadoEconomiasResidenciasAtivas;
	}
	
	public Integer getAguaTotalVolumeFaturadoEstimado() {
		return aguaTotalVolumeFaturadoEstimado;
	}
	
	public void setAguaTotalVolumeFaturadoEstimado(
			Integer aguaTotalVolumeFaturadoEstimado) {
		this.aguaTotalVolumeFaturadoEstimado = aguaTotalVolumeFaturadoEstimado;
	}
	
	public Integer getAguaTotalVolumeFaturadoGeral() {
		return aguaTotalVolumeFaturadoGeral;
	}
	
	public void setAguaTotalVolumeFaturadoGeral(Integer aguaTotalVolumeFaturadoGeral) {
		this.aguaTotalVolumeFaturadoGeral = aguaTotalVolumeFaturadoGeral;
	}
	
	public Integer getAguaTotalVolumeFaturadoIndustrial() {
		return aguaTotalVolumeFaturadoIndustrial;
	}
	
	public void setAguaTotalVolumeFaturadoIndustrial(
			Integer aguaTotalVolumeFaturadoIndustrial) {
		this.aguaTotalVolumeFaturadoIndustrial = aguaTotalVolumeFaturadoIndustrial;
	}
	
	public Integer getAguaTotalVolumeFaturadoMedido() {
		return aguaTotalVolumeFaturadoMedido;
	}
	
	public void setAguaTotalVolumeFaturadoMedido(
			Integer aguaTotalVolumeFaturadoMedido) {
		this.aguaTotalVolumeFaturadoMedido = aguaTotalVolumeFaturadoMedido;
	}
	
	public Integer getAguaTotalVolumeFaturadoMicroMedido() {
		return aguaTotalVolumeFaturadoMicroMedido;
	}
	
	public void setAguaTotalVolumeFaturadoMicroMedido(
			Integer aguaTotalVolumeFaturadoMicroMedido) {
		this.aguaTotalVolumeFaturadoMicroMedido = aguaTotalVolumeFaturadoMicroMedido;
	}
	
	public Integer getAguaTotalVolumeFaturadoPublico() {
		return aguaTotalVolumeFaturadoPublico;
	}
	
	public void setAguaTotalVolumeFaturadoPublico(
			Integer aguaTotalVolumeFaturadoPublico) {
		this.aguaTotalVolumeFaturadoPublico = aguaTotalVolumeFaturadoPublico;
	}
	
	public Integer getAguaTotalVolumeFaturadoResidencial() {
		return aguaTotalVolumeFaturadoResidencial;
	}

	public void setAguaTotalVolumeFaturadoResidencial(
			Integer aguaTotalVolumeFaturadoResidencial) {
		this.aguaTotalVolumeFaturadoResidencial = aguaTotalVolumeFaturadoResidencial;
	}
	
	public Integer getAguaTotalVolumeFaturadoRural() {
		return aguaTotalVolumeFaturadoRural;
	}
	
	public void setAguaTotalVolumeFaturadoRural(Integer aguaTotalVolumeFaturadoRural) {
		this.aguaTotalVolumeFaturadoRural = aguaTotalVolumeFaturadoRural;
	}
	
	public BigDecimal getDevolucao() {
		return devolucao;
	}
	
	public void setDevolucao(BigDecimal devolucao) {
		this.devolucao = devolucao;
	}
	
	public Integer getEsgotoTotalEconomiasAtivasCondominial() {
		return esgotoTotalEconomiasAtivasCondominial;
	}
	
	public void setEsgotoTotalEconomiasAtivasCondominial(
			Integer esgotoTotalEconomiasAtivasCondominial) {
		this.esgotoTotalEconomiasAtivasCondominial = esgotoTotalEconomiasAtivasCondominial;
	}
	
	public Integer getEsgotoTotalEconomiasAtivasConvencional() {
		return esgotoTotalEconomiasAtivasConvencional;
	}
	
	public void setEsgotoTotalEconomiasAtivasConvencional(
			Integer esgotoTotalEconomiasAtivasConvencional) {
		this.esgotoTotalEconomiasAtivasConvencional = esgotoTotalEconomiasAtivasConvencional;
	}
	
	public Integer getEsgotoTotalEconomiasCadastradasConvencional() {
		return esgotoTotalEconomiasCadastradasConvencional;
	}
	
	public void setEsgotoTotalEconomiasCadastradasConvencional(
			Integer esgotoTotalEconomiasCadastradasConvencional) {
		this.esgotoTotalEconomiasCadastradasConvencional = esgotoTotalEconomiasCadastradasConvencional;
	}
	
	public Integer getEsgotoTotalEconomiasComercialAtivas() {
		return esgotoTotalEconomiasComercialAtivas;
	}
	
	public void setEsgotoTotalEconomiasComercialAtivas(
			Integer esgotoTotalEconomiasComercialAtivas) {
		this.esgotoTotalEconomiasComercialAtivas = esgotoTotalEconomiasComercialAtivas;
	}
	
	public Integer getEsgotoTotalEconomiasIndustrialAtivas() {
		return esgotoTotalEconomiasIndustrialAtivas;
	}
	
	public void setEsgotoTotalEconomiasIndustrialAtivas(
			Integer esgotoTotalEconomiasIndustrialAtivas) {
		this.esgotoTotalEconomiasIndustrialAtivas = esgotoTotalEconomiasIndustrialAtivas;
	}
	
	public Integer getEsgotoTotalEconomiasPublicoAtivas() {
		return esgotoTotalEconomiasPublicoAtivas;
	}
	
	public void setEsgotoTotalEconomiasPublicoAtivas(
			Integer esgotoTotalEconomiasPublicoAtivas) {
		this.esgotoTotalEconomiasPublicoAtivas = esgotoTotalEconomiasPublicoAtivas;
	}
	
	public Integer getEsgotoTotalEconomiasResidencialAtivas() {
		return esgotoTotalEconomiasResidencialAtivas;
	}
	
	public void setEsgotoTotalEconomiasResidencialAtivas(
			Integer esgotoTotalEconomiasResidencialAtivas) {
		this.esgotoTotalEconomiasResidencialAtivas = esgotoTotalEconomiasResidencialAtivas;
	}
	
	public Integer getEsgotoTotalEconomiasResidencialCadastradas() {
		return esgotoTotalEconomiasResidencialCadastradas;
	}
	
	public void setEsgotoTotalEconomiasResidencialCadastradas(
			Integer esgotoTotalEconomiasResidencialCadastradas) {
		this.esgotoTotalEconomiasResidencialCadastradas = esgotoTotalEconomiasResidencialCadastradas;
	}
	
	public BigDecimal getEsgotoTotalFaturadoComercial() {
		return esgotoTotalFaturadoComercial;
	}
	
	public void setEsgotoTotalFaturadoComercial(
			BigDecimal esgotoTotalFaturadoComercial) {
		this.esgotoTotalFaturadoComercial = esgotoTotalFaturadoComercial;
	}
	
	public BigDecimal getEsgotoTotalFaturadoDireto() {
		return esgotoTotalFaturadoDireto;
	}
	
	public void setEsgotoTotalFaturadoDireto(BigDecimal esgotoTotalFaturadoDireto) {
		this.esgotoTotalFaturadoDireto = esgotoTotalFaturadoDireto;
	}
	
	public BigDecimal getEsgotoTotalFaturadoIndireto() {
		return esgotoTotalFaturadoIndireto;
	}
	
	public void setEsgotoTotalFaturadoIndireto(
			BigDecimal esgotoTotalFaturadoIndireto) {
		this.esgotoTotalFaturadoIndireto = esgotoTotalFaturadoIndireto;
	}
	
	public BigDecimal getEsgotoTotalFaturadoIndustrial() {
		return esgotoTotalFaturadoIndustrial;
	}
	
	public void setEsgotoTotalFaturadoIndustrial(
			BigDecimal esgotoTotalFaturadoIndustrial) {
		this.esgotoTotalFaturadoIndustrial = esgotoTotalFaturadoIndustrial;
	}
	
	public BigDecimal getEsgotoTotalFaturadoPublico() {
		return esgotoTotalFaturadoPublico;
	}
	
	public void setEsgotoTotalFaturadoPublico(BigDecimal esgotoTotalFaturadoPublico) {
		this.esgotoTotalFaturadoPublico = esgotoTotalFaturadoPublico;
	}
	
	public BigDecimal getEsgotoTotalFaturadoResidencial() {
		return esgotoTotalFaturadoResidencial;
	}
	
	public void setEsgotoTotalFaturadoResidencial(
			BigDecimal esgotoTotalFaturadoResidencial) {
		this.esgotoTotalFaturadoResidencial = esgotoTotalFaturadoResidencial;
	}
	
	public BigDecimal getEsgotoTotalFaturamentoGeralDI() {
		return Util.somaBigDecimal(esgotoTotalFaturadoDireto,esgotoTotalFaturadoIndireto);
	}
	
	public Integer getEsgotoTotalLigacoesAtivasCondominial() {
		return esgotoTotalLigacoesAtivasCondominial;
	}
	
	public void setEsgotoTotalLigacoesAtivasCondominial(
			Integer esgotoTotalLigacoesAtivasCondominial) {
		this.esgotoTotalLigacoesAtivasCondominial = esgotoTotalLigacoesAtivasCondominial;
	}
	
	public Integer getEsgotoTotalLigacoesAtivasConvencional() {
		return esgotoTotalLigacoesAtivasConvencional;
	}
	
	public void setEsgotoTotalLigacoesAtivasConvencional(
			Integer esgotoTotalLigacoesAtivasConvencional) {
		this.esgotoTotalLigacoesAtivasConvencional = esgotoTotalLigacoesAtivasConvencional;
	}
	
	public Integer getEsgotoTotalLigacoesCadastradas() {
		return esgotoTotalLigacoesCadastradas;
	}
	
	public void setEsgotoTotalLigacoesCadastradas(
			Integer esgotoTotalLigacoesCadastradas) {
		this.esgotoTotalLigacoesCadastradas = esgotoTotalLigacoesCadastradas;
	}
	
	public Integer getEsgotoTotalLigacoesCadastradasCondominial() {
		return esgotoTotalLigacoesCadastradasCondominial;
	}
	
	public void setEsgotoTotalLigacoesCadastradasCondominial(
			Integer esgotoTotalLigacoesCadastradasCondominial) {
		this.esgotoTotalLigacoesCadastradasCondominial = esgotoTotalLigacoesCadastradasCondominial;
	}
	
	public Integer getEsgotoTotalLigacoesCadastradasConvencional() {
		return esgotoTotalLigacoesCadastradasConvencional;
	}
	
	public void setEsgotoTotalLigacoesCadastradasConvencional(
			Integer esgotoTotalLigacoesCadastradasConvencional) {
		this.esgotoTotalLigacoesCadastradasConvencional = esgotoTotalLigacoesCadastradasConvencional;
	}
	
	public Integer getEsgotoTotalLigacoesNovas() {
		return esgotoTotalLigacoesNovas;
	}
	
	public void setEsgotoTotalLigacoesNovas(Integer esgotoTotalLigacoesNovas) {
		this.esgotoTotalLigacoesNovas = esgotoTotalLigacoesNovas;
	}
	
	public Integer getEsgotoTotalLigacoesResidencialCadastradas() {
		return esgotoTotalLigacoesResidencialCadastradas;
	}
	
	public void setEsgotoTotalLigacoesResidencialCadastradas(
			Integer esgotoTotalLigacoesResidencialCadastradas) {
		this.esgotoTotalLigacoesResidencialCadastradas = esgotoTotalLigacoesResidencialCadastradas;
	}
	
	public Integer getEsgotoTotalVolumeFaturadoComercial() {
		return esgotoTotalVolumeFaturadoComercial;
	}
	
	public void setEsgotoTotalVolumeFaturadoComercial(
			Integer esgotoTotalVolumeFaturadoComercial) {
		this.esgotoTotalVolumeFaturadoComercial = esgotoTotalVolumeFaturadoComercial;
	}
	
	public Integer getEsgotoTotalVolumeFaturadoGeral() {
		return esgotoTotalVolumeFaturadoGeral;
	}
	
	public void setEsgotoTotalVolumeFaturadoGeral(
			Integer esgotoTotalVolumeFaturadoGeral) {
		this.esgotoTotalVolumeFaturadoGeral = esgotoTotalVolumeFaturadoGeral;
	}
	
	public Integer getEsgotoTotalVolumeFaturadoIndustrial() {
		return esgotoTotalVolumeFaturadoIndustrial;
	}
	
	public void setEsgotoTotalVolumeFaturadoIndustrial(
			Integer esgotoTotalVolumeFaturadoIndustrial) {
		this.esgotoTotalVolumeFaturadoIndustrial = esgotoTotalVolumeFaturadoIndustrial;
	}
	
	public Integer getEsgotoTotalVolumeFaturadoPublico() {
		return esgotoTotalVolumeFaturadoPublico;
	}
	
	public void setEsgotoTotalVolumeFaturadoPublico(
			Integer esgotoTotalVolumeFaturadoPublico) {
		this.esgotoTotalVolumeFaturadoPublico = esgotoTotalVolumeFaturadoPublico;
	}
	
	public Integer getEsgotoTotalVolumeFaturadoResidencial() {
		return esgotoTotalVolumeFaturadoResidencial;
	}
	
	public void setEsgotoTotalVolumeFaturadoResidencial(
			Integer esgotoTotalVolumeFaturadoResidencial) {
		this.esgotoTotalVolumeFaturadoResidencial = esgotoTotalVolumeFaturadoResidencial;
	}
	
	public BigDecimal getTotalArrecadacaoGeral() {
		return Util.somaBigDecimal(totalArrecadacaoMesAtual,totalArrecadacaoMesAnterior);
	}
	
	public BigDecimal getTotalArrecadacaoMesAnterior() {
		return totalArrecadacaoMesAnterior;
	}
	
	public void setTotalArrecadacaoMesAnterior(
			BigDecimal totalArrecadacaoMesAnterior) {
		this.totalArrecadacaoMesAnterior = totalArrecadacaoMesAnterior;
	}
	
	public BigDecimal getTotalArrecadacaoMesAtual() {
		return totalArrecadacaoMesAtual;
	}
	
	public void setTotalArrecadacaoMesAtual(BigDecimal totalArrecadacaoMesAtual) {
		this.totalArrecadacaoMesAtual = totalArrecadacaoMesAtual;
	}
	
	public BigDecimal getTotalFaturamentoLiquido() {
		return totalFaturamentoLiquido;
	}

	public Integer getEsgotoTotalEconomiasCadastradasCondominial() {
		return esgotoTotalEconomiasCadastradasCondominial;
	}

	public void setEsgotoTotalEconomiasCadastradasCondominial(
			Integer esgotoTotalEconomiasCadastradasCondominial) {
		this.esgotoTotalEconomiasCadastradasCondominial = esgotoTotalEconomiasCadastradasCondominial;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Integer getAguaTotalLigacoesSuprimidas() {
		return aguaTotalLigacoesSuprimidas;
	}

	public void setAguaTotalLigacoesSuprimidas(Integer aguaTotalLigacoesSuprimidas) {
		this.aguaTotalLigacoesSuprimidas = aguaTotalLigacoesSuprimidas;
	}

	public Integer getEsgotoTotalEconomiasAtivas() {
		return Util.somaInteiros(this.getEsgotoTotalEconomiasAtivasCondominial(),this.getEsgotoTotalEconomiasAtivasConvencional());
	}

	public Integer getEsgotoTotalEconomiasCadastradas() {
		return Util.somaInteiros(this.getEsgotoTotalEconomiasCadastradasCondominial(),this.getEsgotoTotalEconomiasCadastradasConvencional());
	}

	public Integer getEsgotoTotalLigacoesAtivas() {
		return Util.somaInteiros(this.getEsgotoTotalLigacoesAtivasCondominial(),this.getEsgotoTotalLigacoesAtivasConvencional());
	}

	public Integer getAguaTotalVolumeFaturadoConsumido() {
		return Util.somaInteiros(this.getAguaTotalVolumeFaturadoMicroMedido(),this.getAguaTotalVolumeFaturadoEstimado());
	}

	public Integer getAguaTotalVolumeFaturado() {
		return Util.somaInteiros(this.getAguaTotalVolumeFaturadoMedido(),this.getAguaTotalVolumeFaturadoEstimado()); 
	}

	public BigDecimal getReceitaOperacionalDireta() {
		return receitaOperacionalDireta;
	}

	public BigDecimal getReceitaOperacionalIndireta() {
		return receitaOperacionalIndireta;
	}

	public BigDecimal getReceitaOperacionalTotal() {
		return receitaOperacionalTotal;
	}

	public BigDecimal getSaldoContasReceber() {
		return saldoContasReceber;
	}

	public void setSaldoContasReceber(BigDecimal saldoContasReceber) {
		this.saldoContasReceber = saldoContasReceber;
	}

	public Integer getEsgotoTotalEconomiasRuralAtivas() {
		return esgotoTotalEconomiasRuralAtivas;
	}

	public void setEsgotoTotalEconomiasRuralAtivas(
			Integer esgotoTotalEconomiasRuralAtivas) {
		this.esgotoTotalEconomiasRuralAtivas = esgotoTotalEconomiasRuralAtivas;
	}

	public String getOpcaoAgrupamento() {
		return opcaoAgrupamento;
	}

	public void setOpcaoAgrupamento(String opcaoAgrupamento) {
		this.opcaoAgrupamento = opcaoAgrupamento;
	}

	public BigDecimal getSaldoContasReceberParticular() {
		return saldoContasReceberParticular;
	}

	public void setSaldoContasReceberParticular(
			BigDecimal saldoContasReceberParticular) {
		this.saldoContasReceberParticular = saldoContasReceberParticular;
	}

	public BigDecimal getSaldoContasReceberPublico() {
		return saldoContasReceberPublico;
	}

	public void setSaldoContasReceberPublico(BigDecimal saldoContasReceberPublico) {
		this.saldoContasReceberPublico = saldoContasReceberPublico;
	}

	public Integer getAguaTotalLigacoesFaturadasMedidas() {
		return aguaTotalLigacoesFaturadasMedidas;
	}

	public void setAguaTotalLigacoesFaturadasMedidas(
			Integer aguaTotalLigacoesFaturadasMedidas) {
		this.aguaTotalLigacoesFaturadasMedidas = aguaTotalLigacoesFaturadasMedidas;
	}

	public Integer getEsgotoTotalLigacoesFaturadasMedidas() {
		return esgotoTotalLigacoesFaturadasMedidas;
	}

	public void setEsgotoTotalLigacoesFaturadasMedidas(
			Integer esgotoTotalLigacoesFaturadasMedidas) {
		this.esgotoTotalLigacoesFaturadasMedidas = esgotoTotalLigacoesFaturadasMedidas;
	}

	public Integer getAguaTotalLigacoesFaturadasNaoMedidas() {
		return aguaTotalLigacoesFaturadasNaoMedidas;
	}

	public void setAguaTotalLigacoesFaturadasNaoMedidas(
			Integer aguaTotalLigacoesFaturadasNaoMedidas) {
		this.aguaTotalLigacoesFaturadasNaoMedidas = aguaTotalLigacoesFaturadasNaoMedidas;
	}

	public Integer getEsgotoTotalLigacoesFaturadasNaoMedidas() {
		return esgotoTotalLigacoesFaturadasNaoMedidas;
	}

	public void setEsgotoTotalLigacoesFaturadasNaoMedidas(
			Integer esgotoTotalLigacoesFaturadasNaoMedidas) {
		this.esgotoTotalLigacoesFaturadasNaoMedidas = esgotoTotalLigacoesFaturadasNaoMedidas;
	}

	public Integer getAguaTotalEconomiasFaturadasMedidas() {
		return aguaTotalEconomiasFaturadasMedidas;
	}

	public void setAguaTotalEconomiasFaturadasMedidas(
			Integer aguaTotalEconomiasFaturadasMedidas) {
		this.aguaTotalEconomiasFaturadasMedidas = aguaTotalEconomiasFaturadasMedidas;
	}

	public Integer getEsgotoTotalEconomiasFaturadasMedidas() {
		return esgotoTotalEconomiasFaturadasMedidas;
	}

	public void setEsgotoTotalEconomiasFaturadasMedidas(
			Integer esgotoTotalEconomiasFaturadasMedidas) {
		this.esgotoTotalEconomiasFaturadasMedidas = esgotoTotalEconomiasFaturadasMedidas;
	}

	public Integer getAguaTotalEconomiasFaturadasNaoMedidas() {
		return aguaTotalEconomiasFaturadasNaoMedidas;
	}

	public void setAguaTotalEconomiasFaturadasNaoMedidas(
			Integer aguaTotalEconomiasFaturadasNaoMedidas) {
		this.aguaTotalEconomiasFaturadasNaoMedidas = aguaTotalEconomiasFaturadasNaoMedidas;
	}

	public Integer getEsgotoTotalEconomiasFaturadasNaoMedidas() {
		return esgotoTotalEconomiasFaturadasNaoMedidas;
	}

	public void setEsgotoTotalEconomiasFaturadasNaoMedidas(
			Integer esgotoTotalEconomiasFaturadasNaoMedidas) {
		this.esgotoTotalEconomiasFaturadasNaoMedidas = esgotoTotalEconomiasFaturadasNaoMedidas;
	}

	public BigDecimal getAguaTotalFaturadoMedido() {
		return aguaTotalFaturadoMedido;
	}

	public void setAguaTotalFaturadoMedido(BigDecimal aguaTotalFaturadoMedido) {
		this.aguaTotalFaturadoMedido = aguaTotalFaturadoMedido;
	}

	public BigDecimal getEsgotoTotalFaturadoMedido() {
		return esgotoTotalFaturadoMedido;
	}

	public void setEsgotoTotalFaturadoMedido(BigDecimal esgotoTotalFaturadoMedido) {
		this.esgotoTotalFaturadoMedido = esgotoTotalFaturadoMedido;
	}

	public BigDecimal getAguaTotalFaturadoNaoMedido() {
		return aguaTotalFaturadoNaoMedido;
	}

	public void setAguaTotalFaturadoNaoMedido(BigDecimal aguaTotalFaturadoNaoMedido) {
		this.aguaTotalFaturadoNaoMedido = aguaTotalFaturadoNaoMedido;
	}

	public BigDecimal getEsgotoTotalFaturadoNaoMedido() {
		return esgotoTotalFaturadoNaoMedido;
	}

	public void setEsgotoTotalFaturadoNaoMedido(
			BigDecimal esgotoTotalFaturadoNaoMedido) {
		this.esgotoTotalFaturadoNaoMedido = esgotoTotalFaturadoNaoMedido;
	}

	public void setReceitaOperacionalTotal(BigDecimal receitaOperacionalTotal) {
		this.receitaOperacionalTotal = receitaOperacionalTotal;
	}

	public void setReceitaOperacionalDireta(BigDecimal receitaOperacionalDireta) {
		this.receitaOperacionalDireta = receitaOperacionalDireta;
	}

	public void setReceitaOperacionalIndireta(BigDecimal receitaOperacionalIndireta) {
		this.receitaOperacionalIndireta = receitaOperacionalIndireta;
	}

	public void setAguaTotalFaturamentoGeralDI(
			BigDecimal aguaTotalFaturamentoGeralDI) {
		this.aguaTotalFaturamentoGeralDI = aguaTotalFaturamentoGeralDI;
	}

	public void setTotalFaturamentoLiquido(BigDecimal totalFaturamentoLiquido) {
		this.totalFaturamentoLiquido = totalFaturamentoLiquido;
	}
	
}
