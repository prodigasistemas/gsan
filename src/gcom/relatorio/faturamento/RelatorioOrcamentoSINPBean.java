package gcom.relatorio.faturamento;

import gcom.gerencial.bean.OrcamentoSINPHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de orçamento e SINP
 * 
 * @author Rafael Pinto
 * @created 22/11/2007
 */
public class RelatorioOrcamentoSINPBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao;
	private String opcaoAgrupamento;
	
	private String aguaTotalLigacoesCadastradas;
	private String aguaTotalLigacoesAtivas;
	private String aguaTotalLigacoesMedidas;
	private String aguaTotalLigacoesComHidrometro;
	private String aguaTotalLigacoesResidencialCadastradas;
	private String aguaTotalLigacoesDesligadas;
	
	private String aguaTotalEconomiasCadastradas;
	private String aguaTotalEconomiasAtivas;
	private String aguaTotalEconomiasAtivasMedidas;
	private String aguaTotalEconomiasResidencialCadastradas;
	private String aguaTotalEconomiasResidencialAtivasMicromedidas;
	private String aguaTotalEconomiasResidencialAtivas;
	private String aguaTotalEconomiasComercialAtivas;
	private String aguaTotalEconomiasIndustrialAtivas;
	private String aguaTotalEconomiasPublicoAtivas;
	private String aguaTotalEconomiasRuralAtivas;
	
	private String esgotoTotalLigacoesCadastradas;
	private String esgotoTotalLigacoesCadastradasConvencional;
	private String esgotoTotalLigacoesCadastradasCondominial;
	private String esgotoTotalLigacoesAtivasConvencional;
	private String esgotoTotalLigacoesAtivasCondominial;
	private String esgotoTotalLigacoesResidencialCadastradas;

	private String esgotoTotalEconomiasCadastradasConvencional;
	private String esgotoTotalEconomiasCadastradasCondominial;
	private String esgotoTotalEconomiasAtivasConvencional;
	private String esgotoTotalEconomiasAtivasCondominial;
	private String esgotoTotalEconomiasResidencialCadastradas;
	private String esgotoTotalEconomiasResidencialAtivas;
	private String esgotoTotalEconomiasComercialAtivas;

	private String esgotoTotalEconomiasIndustrialAtivas;
	private String esgotoTotalEconomiasPublicoAtivas;
	private String esgotoTotalEconomiasRuralAtivas;

	private String aguaTotalVolumeFaturadoResidencial;
	private String aguaTotalVolumeFaturadoComercial;
	private String aguaTotalVolumeFaturadoIndustrial;
	private String aguaTotalVolumeFaturadoPublico;
	private String aguaTotalVolumeFaturadoRural;
	private String aguaTotalVolumeFaturadoGeral;
	private String aguaTotalVolumeFaturadoMedido;
	private String aguaTotalVolumeFaturadoEstimado;

	private String esgotoTotalVolumeFaturadoIndustrial;
	private String esgotoTotalVolumeFaturadoPublico;
	private String esgotoTotalVolumeFaturadoResidencial;
	private String esgotoTotalVolumeFaturadoComercial;
	private String esgotoTotalVolumeFaturadoGeral;
	

	private String aguaTotalFaturadoResidencial;
	private String aguaTotalFaturadoComercial;
	private String aguaTotalFaturadoIndustrial;
	private String aguaTotalFaturadoPublico;
	private String aguaTotalFaturadoDireto;
	private String aguaTotalFaturadoIndireto;
	
	private String esgotoTotalFaturadoResidencial;
	private String esgotoTotalFaturadoComercial;
	private String esgotoTotalFaturadoIndustrial;
	private String esgotoTotalFaturadoPublico;
	private String esgotoTotalFaturadoDireto;
	private String esgotoTotalFaturadoIndireto;
	
	private String devolucao;
	
	private String aguaTotalLigacoesNovas;
	private String esgotoTotalLigacoesNovas;
	
	private String aguaTotalVolumeFaturadoMicroMedido;
	private String aguaTotalVolumeFaturadoEconomiasResidenciasAtivas;
	
	private String totalArrecadacaoMesAtual;
	private String totalArrecadacaoMesAnterior;
	
	private String totalFaturamentoLiquido;
	private String totalFaturamentoGeral;
	private String totalArrecadacaoGeral;
	
	private String aguaTotalFaturamentoGeralDI;
	private String esgotoTotalFaturamentoGeralDI;
	
	private String aguaTotalLigacoesSuprimidas;
	private String aguaTotalVolumeFaturadoConsumido;
	private String aguaTotalVolumeFaturado;

	private String esgotoTotalLigacoesAtivas;
	private String esgotoTotalEconomiasAtivas;
	private String esgotoTotalEconomiasCadastradas;
	
	private String receitaOperacionalTotal;
	private String receitaOperacionalDireta;
	private String receitaOperacionalIndireta;
	
	private String saldoContasReceber;
	private String saldoContasReceberParticular;
	private String saldoContasReceberPublico;
	
	private String aguaTotalLigacoesFaturadasMedidas;
	private String esgotoTotalLigacoesFaturadasMedidas;
	private String aguaTotalLigacoesFaturadasNaoMedidas;
	private String esgotoTotalLigacoesFaturadasNaoMedidas;
	private String aguaTotalEconomiasFaturadasMedidas;
	private String esgotoTotalEconomiasFaturadasMedidas;
	private String aguaTotalEconomiasFaturadasNaoMedidas;
	private String esgotoTotalEconomiasFaturadasNaoMedidas;
	private String aguaTotalFaturadoMedido;
	private String esgotoTotalFaturadoMedido;
	private String aguaTotalFaturadoNaoMedido;
	private String esgotoTotalFaturadoNaoMedido;
	
	public RelatorioOrcamentoSINPBean(OrcamentoSINPHelper orcamento) {
		
		this.opcaoTotalizacao = orcamento.getOpcaoTotalizacao();
		this.aguaTotalLigacoesCadastradas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesCadastradas());
		this.esgotoTotalLigacoesCadastradas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesCadastradas());
		this.esgotoTotalLigacoesCadastradasConvencional = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesCadastradasConvencional());
		this.aguaTotalLigacoesAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesAtivas());
		this.esgotoTotalLigacoesCadastradasCondominial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesCadastradasCondominial());
		this.aguaTotalLigacoesMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesMedidas());
		this.esgotoTotalLigacoesAtivasConvencional = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesAtivasConvencional());
		this.aguaTotalLigacoesComHidrometro = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesComHidrometro());
		this.esgotoTotalLigacoesAtivasCondominial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesAtivasCondominial());
		this.aguaTotalLigacoesResidencialCadastradas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesResidencialCadastradas());
		this.esgotoTotalLigacoesResidencialCadastradas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesResidencialCadastradas());
		this.aguaTotalLigacoesDesligadas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesDesligadas());
		this.aguaTotalEconomiasCadastradas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasCadastradas());
		this.esgotoTotalEconomiasCadastradasConvencional = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasCadastradasConvencional());
		this.esgotoTotalEconomiasCadastradasCondominial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasCadastradasCondominial());
		this.aguaTotalEconomiasAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasAtivas());
		this.aguaTotalEconomiasAtivasMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasAtivasMedidas());
		this.esgotoTotalEconomiasAtivasConvencional = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasAtivasConvencional());
		this.aguaTotalEconomiasResidencialCadastradas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasResidencialCadastradas());
		this.esgotoTotalEconomiasAtivasCondominial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasAtivasCondominial());
		this.aguaTotalEconomiasResidencialAtivasMicromedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasResidencialAtivasMicromedidas());
		this.esgotoTotalEconomiasResidencialCadastradas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasResidencialCadastradas());
		this.aguaTotalEconomiasResidencialAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasResidencialAtivas());
		this.esgotoTotalEconomiasResidencialAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasResidencialAtivas());
		this.aguaTotalEconomiasComercialAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasComercialAtivas());
		this.esgotoTotalEconomiasComercialAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasComercialAtivas());
		this.aguaTotalEconomiasIndustrialAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasIndustrialAtivas());
		this.esgotoTotalEconomiasIndustrialAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasIndustrialAtivas());
		this.aguaTotalEconomiasPublicoAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasPublicoAtivas());
		this.esgotoTotalEconomiasPublicoAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasPublicoAtivas());
		this.aguaTotalEconomiasRuralAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasRuralAtivas());
		this.aguaTotalVolumeFaturadoMedido = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoMedido());
		this.esgotoTotalVolumeFaturadoResidencial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalVolumeFaturadoResidencial());
		this.esgotoTotalVolumeFaturadoComercial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalVolumeFaturadoComercial());
		this.aguaTotalVolumeFaturadoEstimado = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoEstimado());
		this.esgotoTotalVolumeFaturadoIndustrial = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalVolumeFaturadoIndustrial());
		this.esgotoTotalVolumeFaturadoPublico = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalVolumeFaturadoPublico());
		this.aguaTotalVolumeFaturadoResidencial = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoResidencial());
		this.esgotoTotalVolumeFaturadoGeral = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalVolumeFaturadoGeral());
		this.aguaTotalVolumeFaturadoComercial = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoComercial());
		this.aguaTotalVolumeFaturadoIndustrial = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoIndustrial());
		this.aguaTotalVolumeFaturadoPublico = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoPublico());
		this.aguaTotalVolumeFaturadoRural = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoRural());
		this.aguaTotalVolumeFaturadoGeral = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoGeral());
		
		this.aguaTotalFaturadoResidencial = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoResidencial());
		this.esgotoTotalFaturadoResidencial = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoResidencial());
		this.aguaTotalFaturadoComercial = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoComercial());
		this.esgotoTotalFaturadoComercial = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoComercial());
		this.aguaTotalFaturadoIndustrial = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoIndustrial());
		this.esgotoTotalFaturadoIndustrial = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoIndustrial());
		this.aguaTotalFaturadoPublico = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoPublico());
		this.esgotoTotalFaturadoPublico = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoPublico());
		this.aguaTotalFaturadoDireto = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoDireto());
		this.esgotoTotalFaturadoDireto = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoDireto());
		this.aguaTotalFaturadoIndireto = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoIndireto());
		//this.esgotoTotalFaturadoIndireto = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoIndireto());
		//this.devolucao = Util.formatarMoedaReal(orcamento.getDevolucao());
		
		this.aguaTotalLigacoesNovas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesNovas());
		this.esgotoTotalLigacoesNovas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesNovas());
		this.aguaTotalVolumeFaturadoMicroMedido = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoMicroMedido());
		this.aguaTotalVolumeFaturadoEconomiasResidenciasAtivas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoEconomiasResidenciasAtivas());
		
		this.totalArrecadacaoMesAtual = Util.formatarMoedaReal(orcamento.getTotalArrecadacaoMesAtual());
		this.totalArrecadacaoMesAnterior = Util.formatarMoedaReal(orcamento.getTotalArrecadacaoMesAnterior());
		
		this.totalFaturamentoLiquido = Util.formatarMoedaReal(orcamento.getTotalFaturamentoLiquido());
		this.totalArrecadacaoGeral = Util.formatarMoedaReal(orcamento.getTotalArrecadacaoGeral());
		
		this.aguaTotalFaturamentoGeralDI = Util.formatarMoedaReal(orcamento.getAguaTotalFaturamentoGeralDI());
		//this.esgotoTotalFaturamentoGeralDI = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturamentoGeralDI());
		
		this.aguaTotalLigacoesSuprimidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesSuprimidas());

		this.esgotoTotalLigacoesAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesAtivas());
		this.esgotoTotalEconomiasAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasAtivas());
		this.esgotoTotalEconomiasCadastradas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasCadastradas());
		
		this.aguaTotalVolumeFaturadoConsumido = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturadoConsumido());
		this.aguaTotalVolumeFaturado = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalVolumeFaturado());
		
		
		this.receitaOperacionalTotal = Util.formatarMoedaReal(orcamento.getReceitaOperacionalTotal());
		this.receitaOperacionalDireta = Util.formatarMoedaReal(orcamento.getReceitaOperacionalDireta());
		this.receitaOperacionalIndireta = Util.formatarMoedaReal(orcamento.getReceitaOperacionalIndireta());
		
		this.saldoContasReceber = Util.formatarMoedaReal(orcamento.getSaldoContasReceber());
		this.saldoContasReceberParticular = Util.formatarMoedaReal(orcamento.getSaldoContasReceberParticular());
		this.saldoContasReceberPublico = Util.formatarMoedaReal(orcamento.getSaldoContasReceberPublico());
		
		this.esgotoTotalEconomiasRuralAtivas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasRuralAtivas());
		this.opcaoAgrupamento = orcamento.getOpcaoAgrupamento();
		
		this.aguaTotalLigacoesFaturadasMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesFaturadasMedidas());
		this.esgotoTotalLigacoesFaturadasMedidas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesFaturadasMedidas());
		this.aguaTotalLigacoesFaturadasNaoMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalLigacoesFaturadasNaoMedidas());
		this.esgotoTotalLigacoesFaturadasNaoMedidas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalLigacoesFaturadasNaoMedidas());
		this.aguaTotalEconomiasFaturadasMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasFaturadasMedidas());
		this.esgotoTotalEconomiasFaturadasMedidas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasFaturadasMedidas());
		this.aguaTotalEconomiasFaturadasNaoMedidas = Util.agruparNumeroEmMilhares(orcamento.getAguaTotalEconomiasFaturadasNaoMedidas());
		this.esgotoTotalEconomiasFaturadasNaoMedidas = Util.agruparNumeroEmMilhares(orcamento.getEsgotoTotalEconomiasFaturadasNaoMedidas());
		//this.aguaTotalFaturadoMedido = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoMedido());
		//this.esgotoTotalFaturadoMedido = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoMedido());
		//this.aguaTotalFaturadoNaoMedido = Util.formatarMoedaReal(orcamento.getAguaTotalFaturadoNaoMedido());
		//this.esgotoTotalFaturadoNaoMedido = Util.formatarMoedaReal(orcamento.getEsgotoTotalFaturadoNaoMedido());
	}
	
	public String getAguaTotalEconomiasAtivas() {
		return aguaTotalEconomiasAtivas;
	}
	
	public String getAguaTotalEconomiasAtivasMedidas() {
		return aguaTotalEconomiasAtivasMedidas;
	}

	public String getAguaTotalEconomiasCadastradas() {
		return aguaTotalEconomiasCadastradas;
	}

	public String getAguaTotalEconomiasComercialAtivas() {
		return aguaTotalEconomiasComercialAtivas;
	}

	public String getAguaTotalEconomiasIndustrialAtivas() {
		return aguaTotalEconomiasIndustrialAtivas;
	}
	
	public String getAguaTotalEconomiasPublicoAtivas() {
		return aguaTotalEconomiasPublicoAtivas;
	}

	public String getAguaTotalEconomiasResidencialAtivas() {
		return aguaTotalEconomiasResidencialAtivas;
	}

	public String getAguaTotalEconomiasResidencialAtivasMicromedidas() {
		return aguaTotalEconomiasResidencialAtivasMicromedidas;
	}

	public String getAguaTotalEconomiasResidencialCadastradas() {
		return aguaTotalEconomiasResidencialCadastradas;
	}
	
	public String getAguaTotalEconomiasRuralAtivas() {
		return aguaTotalEconomiasRuralAtivas;
	}

	public String getAguaTotalFaturadoComercial() {
		return aguaTotalFaturadoComercial;
	}

	public String getAguaTotalFaturadoDireto() {
		return aguaTotalFaturadoDireto;
	}

	public String getAguaTotalFaturadoIndireto() {
		return aguaTotalFaturadoIndireto;
	}

	public String getAguaTotalFaturadoIndustrial() {
		return aguaTotalFaturadoIndustrial;
	}

	public String getAguaTotalFaturadoPublico() {
		return aguaTotalFaturadoPublico;
	}

	public String getAguaTotalFaturadoResidencial() {
		return aguaTotalFaturadoResidencial;
	}
	
	public String getAguaTotalLigacoesAtivas() {
		return aguaTotalLigacoesAtivas;
	}

	public String getAguaTotalLigacoesCadastradas() {
		return aguaTotalLigacoesCadastradas;
	}

	public String getAguaTotalLigacoesComHidrometro() {
		return aguaTotalLigacoesComHidrometro;
	}

	public String getAguaTotalLigacoesDesligadas() {
		return aguaTotalLigacoesDesligadas;
	}

	public String getAguaTotalLigacoesMedidas() {
		return aguaTotalLigacoesMedidas;
	}

	public String getAguaTotalLigacoesNovas() {
		return aguaTotalLigacoesNovas;
	}

	public String getAguaTotalLigacoesResidencialCadastradas() {
		return aguaTotalLigacoesResidencialCadastradas;
	}

	public String getAguaTotalVolumeFaturadoComercial() {
		return aguaTotalVolumeFaturadoComercial;
	}

	public String getAguaTotalVolumeFaturadoEconomiasResidenciasAtivas() {
		return aguaTotalVolumeFaturadoEconomiasResidenciasAtivas;
	}

	public String getAguaTotalVolumeFaturadoEstimado() {
		return aguaTotalVolumeFaturadoEstimado;
	}
	
	public String getAguaTotalVolumeFaturadoGeral() {
		return aguaTotalVolumeFaturadoGeral;
	}
	
	public String getAguaTotalVolumeFaturadoIndustrial() {
		return aguaTotalVolumeFaturadoIndustrial;
	}
	
	public String getAguaTotalVolumeFaturadoMedido() {
		return aguaTotalVolumeFaturadoMedido;
	}
	
	public String getAguaTotalVolumeFaturadoMicroMedido() {
		return aguaTotalVolumeFaturadoMicroMedido;
	}
	
	public String getAguaTotalVolumeFaturadoPublico() {
		return aguaTotalVolumeFaturadoPublico;
	}
	
	public String getAguaTotalVolumeFaturadoResidencial() {
		return aguaTotalVolumeFaturadoResidencial;
	}
	
	public String getAguaTotalVolumeFaturadoRural() {
		return aguaTotalVolumeFaturadoRural;
	}
	
	public String getDevolucao() {
		return devolucao;
	}
	
	public String getEsgotoTotalEconomiasAtivasCondominial() {
		return esgotoTotalEconomiasAtivasCondominial;
	}
	
	public String getEsgotoTotalEconomiasAtivasConvencional() {
		return esgotoTotalEconomiasAtivasConvencional;
	}
	
	public String getEsgotoTotalEconomiasCadastradasCondominial() {
		return esgotoTotalEconomiasCadastradasCondominial;
	}
	
	public String getEsgotoTotalEconomiasCadastradasConvencional() {
		return esgotoTotalEconomiasCadastradasConvencional;
	}
	
	public String getEsgotoTotalEconomiasComercialAtivas() {
		return esgotoTotalEconomiasComercialAtivas;
	}
	
	public String getEsgotoTotalEconomiasIndustrialAtivas() {
		return esgotoTotalEconomiasIndustrialAtivas;
	}
	
	public String getEsgotoTotalEconomiasPublicoAtivas() {
		return esgotoTotalEconomiasPublicoAtivas;
	}
	
	public String getEsgotoTotalEconomiasResidencialAtivas() {
		return esgotoTotalEconomiasResidencialAtivas;
	}
	
	public String getEsgotoTotalEconomiasResidencialCadastradas() {
		return esgotoTotalEconomiasResidencialCadastradas;
	}
	
	public String getEsgotoTotalFaturadoComercial() {
		return esgotoTotalFaturadoComercial;
	}
	
	public String getEsgotoTotalFaturadoDireto() {
		return esgotoTotalFaturadoDireto;
	}
	
	public String getEsgotoTotalFaturadoIndireto() {
		return esgotoTotalFaturadoIndireto;
	}
	
	public String getEsgotoTotalFaturadoIndustrial() {
		return esgotoTotalFaturadoIndustrial;
	}
	
	public String getEsgotoTotalFaturadoPublico() {
		return esgotoTotalFaturadoPublico;
	}
	
	public String getEsgotoTotalFaturadoResidencial() {
		return esgotoTotalFaturadoResidencial;
	}
	
	public String getEsgotoTotalLigacoesAtivasCondominial() {
		return esgotoTotalLigacoesAtivasCondominial;
	}
	
	public String getEsgotoTotalLigacoesAtivasConvencional() {
		return esgotoTotalLigacoesAtivasConvencional;
	}
	
	public String getEsgotoTotalLigacoesCadastradas() {
		return esgotoTotalLigacoesCadastradas;
	}
	
	public String getEsgotoTotalLigacoesCadastradasCondominial() {
		return esgotoTotalLigacoesCadastradasCondominial;
	}
	
	public String getEsgotoTotalLigacoesCadastradasConvencional() {
		return esgotoTotalLigacoesCadastradasConvencional;
	}
	
	public String getEsgotoTotalLigacoesNovas() {
		return esgotoTotalLigacoesNovas;
	}
	
	public String getEsgotoTotalLigacoesResidencialCadastradas() {
		return esgotoTotalLigacoesResidencialCadastradas;
	}
	
	public String getEsgotoTotalVolumeFaturadoComercial() {
		return esgotoTotalVolumeFaturadoComercial;
	}
	
	public String getEsgotoTotalVolumeFaturadoGeral() {
		return esgotoTotalVolumeFaturadoGeral;
	}
	
	public String getEsgotoTotalVolumeFaturadoIndustrial() {
		return esgotoTotalVolumeFaturadoIndustrial;
	}
	
	public String getEsgotoTotalVolumeFaturadoPublico() {
		return esgotoTotalVolumeFaturadoPublico;
	}
	
	public String getEsgotoTotalVolumeFaturadoResidencial() {
		return esgotoTotalVolumeFaturadoResidencial;
	}
	
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	
	public String getTotalArrecadacaoMesAnterior() {
		return totalArrecadacaoMesAnterior;
	}
	
	public String getTotalArrecadacaoMesAtual() {
		return totalArrecadacaoMesAtual;
	}

	public String getTotalArrecadacaoGeral() {
		return totalArrecadacaoGeral;
	}

	public String getTotalFaturamentoGeral() {
		return totalFaturamentoGeral;
	}

	public String getTotalFaturamentoLiquido() {
		return totalFaturamentoLiquido;
	}

	public String getAguaTotalFaturamentoGeralDI() {
		return aguaTotalFaturamentoGeralDI;
	}

	public String getEsgotoTotalFaturamentoGeralDI() {
		return esgotoTotalFaturamentoGeralDI;
	}

	public String getAguaTotalLigacoesSuprimidas() {
		return aguaTotalLigacoesSuprimidas;
	}

	public String getAguaTotalVolumeFaturado() {
		return aguaTotalVolumeFaturado;
	}

	public String getAguaTotalVolumeFaturadoConsumido() {
		return aguaTotalVolumeFaturadoConsumido;
	}

	public String getEsgotoTotalEconomiasAtivas() {
		return esgotoTotalEconomiasAtivas;
	}

	public String getEsgotoTotalEconomiasCadastradas() {
		return esgotoTotalEconomiasCadastradas;
	}

	public String getEsgotoTotalLigacoesAtivas() {
		return esgotoTotalLigacoesAtivas;
	}

	public String getReceitaOperacionalDireta() {
		return receitaOperacionalDireta;
	}

	public String getReceitaOperacionalIndireta() {
		return receitaOperacionalIndireta;
	}

	public String getReceitaOperacionalTotal() {
		return receitaOperacionalTotal;
	}

	public String getSaldoContasReceber() {
		return saldoContasReceber;
	}

	public String getEsgotoTotalEconomiasRuralAtivas() {
		return esgotoTotalEconomiasRuralAtivas;
	}


	public String getOpcaoAgrupamento() {
		return opcaoAgrupamento;
	}

	public String getSaldoContasReceberParticular() {
		return saldoContasReceberParticular;
	}

	public String getSaldoContasReceberPublico() {
		return saldoContasReceberPublico;
	}

	public String getAguaTotalLigacoesFaturadasMedidas() {
		return aguaTotalLigacoesFaturadasMedidas;
	}

	public void setAguaTotalLigacoesFaturadasMedidas(
			String aguaTotalLigacoesFaturadasMedidas) {
		this.aguaTotalLigacoesFaturadasMedidas = aguaTotalLigacoesFaturadasMedidas;
	}

	public String getEsgotoTotalLigacoesFaturadasMedidas() {
		return esgotoTotalLigacoesFaturadasMedidas;
	}

	public void setEsgotoTotalLigacoesFaturadasMedidas(
			String esgotoTotalLigacoesFaturadasMedidas) {
		this.esgotoTotalLigacoesFaturadasMedidas = esgotoTotalLigacoesFaturadasMedidas;
	}

	public String getAguaTotalLigacoesFaturadasNaoMedidas() {
		return aguaTotalLigacoesFaturadasNaoMedidas;
	}

	public void setAguaTotalLigacoesFaturadasNaoMedidas(
			String aguaTotalLigacoesFaturadasNaoMedidas) {
		this.aguaTotalLigacoesFaturadasNaoMedidas = aguaTotalLigacoesFaturadasNaoMedidas;
	}

	public String getEsgotoTotalLigacoesFaturadasNaoMedidas() {
		return esgotoTotalLigacoesFaturadasNaoMedidas;
	}

	public void setEsgotoTotalLigacoesFaturadasNaoMedidas(
			String esgotoTotalLigacoesFaturadasNaoMedidas) {
		this.esgotoTotalLigacoesFaturadasNaoMedidas = esgotoTotalLigacoesFaturadasNaoMedidas;
	}

	public String getAguaTotalEconomiasFaturadasMedidas() {
		return aguaTotalEconomiasFaturadasMedidas;
	}

	public void setAguaTotalEconomiasFaturadasMedidas(
			String aguaTotalEconomiasFaturadasMedidas) {
		this.aguaTotalEconomiasFaturadasMedidas = aguaTotalEconomiasFaturadasMedidas;
	}

	public String getEsgotoTotalEconomiasFaturadasMedidas() {
		return esgotoTotalEconomiasFaturadasMedidas;
	}

	public void setEsgotoTotalEconomiasFaturadasMedidas(
			String esgotoTotalEconomiasFaturadasMedidas) {
		this.esgotoTotalEconomiasFaturadasMedidas = esgotoTotalEconomiasFaturadasMedidas;
	}

	public String getAguaTotalEconomiasFaturadasNaoMedidas() {
		return aguaTotalEconomiasFaturadasNaoMedidas;
	}

	public void setAguaTotalEconomiasFaturadasNaoMedidas(
			String aguaTotalEconomiasFaturadasNaoMedidas) {
		this.aguaTotalEconomiasFaturadasNaoMedidas = aguaTotalEconomiasFaturadasNaoMedidas;
	}

	public String getEsgotoTotalEconomiasFaturadasNaoMedidas() {
		return esgotoTotalEconomiasFaturadasNaoMedidas;
	}

	public void setEsgotoTotalEconomiasFaturadasNaoMedidas(
			String esgotoTotalEconomiasFaturadasNaoMedidas) {
		this.esgotoTotalEconomiasFaturadasNaoMedidas = esgotoTotalEconomiasFaturadasNaoMedidas;
	}

	public String getAguaTotalFaturadoMedido() {
		return aguaTotalFaturadoMedido;
	}

	public void setAguaTotalFaturadoMedido(String aguaTotalFaturadoMedido) {
		this.aguaTotalFaturadoMedido = aguaTotalFaturadoMedido;
	}

	public String getEsgotoTotalFaturadoMedido() {
		return esgotoTotalFaturadoMedido;
	}

	public void setEsgotoTotalFaturadoMedido(String esgotoTotalFaturadoMedido) {
		this.esgotoTotalFaturadoMedido = esgotoTotalFaturadoMedido;
	}

	public String getAguaTotalFaturadoNaoMedido() {
		return aguaTotalFaturadoNaoMedido;
	}

	public void setAguaTotalFaturadoNaoMedido(String aguaTotalFaturadoNaoMedido) {
		this.aguaTotalFaturadoNaoMedido = aguaTotalFaturadoNaoMedido;
	}

	public String getEsgotoTotalFaturadoNaoMedido() {
		return esgotoTotalFaturadoNaoMedido;
	}

	public void setEsgotoTotalFaturadoNaoMedido(String esgotoTotalFaturadoNaoMedido) {
		this.esgotoTotalFaturadoNaoMedido = esgotoTotalFaturadoNaoMedido;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public void setOpcaoAgrupamento(String opcaoAgrupamento) {
		this.opcaoAgrupamento = opcaoAgrupamento;
	}

	public void setAguaTotalLigacoesCadastradas(String aguaTotalLigacoesCadastradas) {
		this.aguaTotalLigacoesCadastradas = aguaTotalLigacoesCadastradas;
	}

	public void setAguaTotalLigacoesAtivas(String aguaTotalLigacoesAtivas) {
		this.aguaTotalLigacoesAtivas = aguaTotalLigacoesAtivas;
	}

	public void setAguaTotalLigacoesMedidas(String aguaTotalLigacoesMedidas) {
		this.aguaTotalLigacoesMedidas = aguaTotalLigacoesMedidas;
	}

	public void setAguaTotalLigacoesComHidrometro(
			String aguaTotalLigacoesComHidrometro) {
		this.aguaTotalLigacoesComHidrometro = aguaTotalLigacoesComHidrometro;
	}

	public void setAguaTotalLigacoesResidencialCadastradas(
			String aguaTotalLigacoesResidencialCadastradas) {
		this.aguaTotalLigacoesResidencialCadastradas = aguaTotalLigacoesResidencialCadastradas;
	}

	public void setAguaTotalLigacoesDesligadas(String aguaTotalLigacoesDesligadas) {
		this.aguaTotalLigacoesDesligadas = aguaTotalLigacoesDesligadas;
	}

	public void setAguaTotalEconomiasCadastradas(
			String aguaTotalEconomiasCadastradas) {
		this.aguaTotalEconomiasCadastradas = aguaTotalEconomiasCadastradas;
	}

	public void setAguaTotalEconomiasAtivas(String aguaTotalEconomiasAtivas) {
		this.aguaTotalEconomiasAtivas = aguaTotalEconomiasAtivas;
	}

	public void setAguaTotalEconomiasAtivasMedidas(
			String aguaTotalEconomiasAtivasMedidas) {
		this.aguaTotalEconomiasAtivasMedidas = aguaTotalEconomiasAtivasMedidas;
	}

	public void setAguaTotalEconomiasResidencialCadastradas(
			String aguaTotalEconomiasResidencialCadastradas) {
		this.aguaTotalEconomiasResidencialCadastradas = aguaTotalEconomiasResidencialCadastradas;
	}

	public void setAguaTotalEconomiasResidencialAtivasMicromedidas(
			String aguaTotalEconomiasResidencialAtivasMicromedidas) {
		this.aguaTotalEconomiasResidencialAtivasMicromedidas = aguaTotalEconomiasResidencialAtivasMicromedidas;
	}

	public void setAguaTotalEconomiasResidencialAtivas(
			String aguaTotalEconomiasResidencialAtivas) {
		this.aguaTotalEconomiasResidencialAtivas = aguaTotalEconomiasResidencialAtivas;
	}

	public void setAguaTotalEconomiasComercialAtivas(
			String aguaTotalEconomiasComercialAtivas) {
		this.aguaTotalEconomiasComercialAtivas = aguaTotalEconomiasComercialAtivas;
	}

	public void setAguaTotalEconomiasIndustrialAtivas(
			String aguaTotalEconomiasIndustrialAtivas) {
		this.aguaTotalEconomiasIndustrialAtivas = aguaTotalEconomiasIndustrialAtivas;
	}

	public void setAguaTotalEconomiasPublicoAtivas(
			String aguaTotalEconomiasPublicoAtivas) {
		this.aguaTotalEconomiasPublicoAtivas = aguaTotalEconomiasPublicoAtivas;
	}

	public void setAguaTotalEconomiasRuralAtivas(
			String aguaTotalEconomiasRuralAtivas) {
		this.aguaTotalEconomiasRuralAtivas = aguaTotalEconomiasRuralAtivas;
	}

	public void setEsgotoTotalLigacoesCadastradas(
			String esgotoTotalLigacoesCadastradas) {
		this.esgotoTotalLigacoesCadastradas = esgotoTotalLigacoesCadastradas;
	}

	public void setEsgotoTotalLigacoesCadastradasConvencional(
			String esgotoTotalLigacoesCadastradasConvencional) {
		this.esgotoTotalLigacoesCadastradasConvencional = esgotoTotalLigacoesCadastradasConvencional;
	}

	public void setEsgotoTotalLigacoesCadastradasCondominial(
			String esgotoTotalLigacoesCadastradasCondominial) {
		this.esgotoTotalLigacoesCadastradasCondominial = esgotoTotalLigacoesCadastradasCondominial;
	}

	public void setEsgotoTotalLigacoesAtivasConvencional(
			String esgotoTotalLigacoesAtivasConvencional) {
		this.esgotoTotalLigacoesAtivasConvencional = esgotoTotalLigacoesAtivasConvencional;
	}

	public void setEsgotoTotalLigacoesAtivasCondominial(
			String esgotoTotalLigacoesAtivasCondominial) {
		this.esgotoTotalLigacoesAtivasCondominial = esgotoTotalLigacoesAtivasCondominial;
	}

	public void setEsgotoTotalLigacoesResidencialCadastradas(
			String esgotoTotalLigacoesResidencialCadastradas) {
		this.esgotoTotalLigacoesResidencialCadastradas = esgotoTotalLigacoesResidencialCadastradas;
	}

	public void setEsgotoTotalEconomiasCadastradasConvencional(
			String esgotoTotalEconomiasCadastradasConvencional) {
		this.esgotoTotalEconomiasCadastradasConvencional = esgotoTotalEconomiasCadastradasConvencional;
	}

	public void setEsgotoTotalEconomiasCadastradasCondominial(
			String esgotoTotalEconomiasCadastradasCondominial) {
		this.esgotoTotalEconomiasCadastradasCondominial = esgotoTotalEconomiasCadastradasCondominial;
	}

	public void setEsgotoTotalEconomiasAtivasConvencional(
			String esgotoTotalEconomiasAtivasConvencional) {
		this.esgotoTotalEconomiasAtivasConvencional = esgotoTotalEconomiasAtivasConvencional;
	}

	public void setEsgotoTotalEconomiasAtivasCondominial(
			String esgotoTotalEconomiasAtivasCondominial) {
		this.esgotoTotalEconomiasAtivasCondominial = esgotoTotalEconomiasAtivasCondominial;
	}

	public void setEsgotoTotalEconomiasResidencialCadastradas(
			String esgotoTotalEconomiasResidencialCadastradas) {
		this.esgotoTotalEconomiasResidencialCadastradas = esgotoTotalEconomiasResidencialCadastradas;
	}

	public void setEsgotoTotalEconomiasResidencialAtivas(
			String esgotoTotalEconomiasResidencialAtivas) {
		this.esgotoTotalEconomiasResidencialAtivas = esgotoTotalEconomiasResidencialAtivas;
	}

	public void setEsgotoTotalEconomiasComercialAtivas(
			String esgotoTotalEconomiasComercialAtivas) {
		this.esgotoTotalEconomiasComercialAtivas = esgotoTotalEconomiasComercialAtivas;
	}

	public void setEsgotoTotalEconomiasIndustrialAtivas(
			String esgotoTotalEconomiasIndustrialAtivas) {
		this.esgotoTotalEconomiasIndustrialAtivas = esgotoTotalEconomiasIndustrialAtivas;
	}

	public void setEsgotoTotalEconomiasPublicoAtivas(
			String esgotoTotalEconomiasPublicoAtivas) {
		this.esgotoTotalEconomiasPublicoAtivas = esgotoTotalEconomiasPublicoAtivas;
	}

	public void setEsgotoTotalEconomiasRuralAtivas(
			String esgotoTotalEconomiasRuralAtivas) {
		this.esgotoTotalEconomiasRuralAtivas = esgotoTotalEconomiasRuralAtivas;
	}

	public void setAguaTotalVolumeFaturadoResidencial(
			String aguaTotalVolumeFaturadoResidencial) {
		this.aguaTotalVolumeFaturadoResidencial = aguaTotalVolumeFaturadoResidencial;
	}

	public void setAguaTotalVolumeFaturadoComercial(
			String aguaTotalVolumeFaturadoComercial) {
		this.aguaTotalVolumeFaturadoComercial = aguaTotalVolumeFaturadoComercial;
	}

	public void setAguaTotalVolumeFaturadoIndustrial(
			String aguaTotalVolumeFaturadoIndustrial) {
		this.aguaTotalVolumeFaturadoIndustrial = aguaTotalVolumeFaturadoIndustrial;
	}

	public void setAguaTotalVolumeFaturadoPublico(
			String aguaTotalVolumeFaturadoPublico) {
		this.aguaTotalVolumeFaturadoPublico = aguaTotalVolumeFaturadoPublico;
	}

	public void setAguaTotalVolumeFaturadoRural(String aguaTotalVolumeFaturadoRural) {
		this.aguaTotalVolumeFaturadoRural = aguaTotalVolumeFaturadoRural;
	}

	public void setAguaTotalVolumeFaturadoGeral(String aguaTotalVolumeFaturadoGeral) {
		this.aguaTotalVolumeFaturadoGeral = aguaTotalVolumeFaturadoGeral;
	}

	public void setAguaTotalVolumeFaturadoMedido(
			String aguaTotalVolumeFaturadoMedido) {
		this.aguaTotalVolumeFaturadoMedido = aguaTotalVolumeFaturadoMedido;
	}

	public void setAguaTotalVolumeFaturadoEstimado(
			String aguaTotalVolumeFaturadoEstimado) {
		this.aguaTotalVolumeFaturadoEstimado = aguaTotalVolumeFaturadoEstimado;
	}

	public void setEsgotoTotalVolumeFaturadoIndustrial(
			String esgotoTotalVolumeFaturadoIndustrial) {
		this.esgotoTotalVolumeFaturadoIndustrial = esgotoTotalVolumeFaturadoIndustrial;
	}

	public void setEsgotoTotalVolumeFaturadoPublico(
			String esgotoTotalVolumeFaturadoPublico) {
		this.esgotoTotalVolumeFaturadoPublico = esgotoTotalVolumeFaturadoPublico;
	}

	public void setEsgotoTotalVolumeFaturadoResidencial(
			String esgotoTotalVolumeFaturadoResidencial) {
		this.esgotoTotalVolumeFaturadoResidencial = esgotoTotalVolumeFaturadoResidencial;
	}

	public void setEsgotoTotalVolumeFaturadoComercial(
			String esgotoTotalVolumeFaturadoComercial) {
		this.esgotoTotalVolumeFaturadoComercial = esgotoTotalVolumeFaturadoComercial;
	}

	public void setEsgotoTotalVolumeFaturadoGeral(
			String esgotoTotalVolumeFaturadoGeral) {
		this.esgotoTotalVolumeFaturadoGeral = esgotoTotalVolumeFaturadoGeral;
	}

	public void setAguaTotalFaturadoResidencial(String aguaTotalFaturadoResidencial) {
		this.aguaTotalFaturadoResidencial = aguaTotalFaturadoResidencial;
	}

	public void setAguaTotalFaturadoComercial(String aguaTotalFaturadoComercial) {
		this.aguaTotalFaturadoComercial = aguaTotalFaturadoComercial;
	}

	public void setAguaTotalFaturadoIndustrial(String aguaTotalFaturadoIndustrial) {
		this.aguaTotalFaturadoIndustrial = aguaTotalFaturadoIndustrial;
	}

	public void setAguaTotalFaturadoPublico(String aguaTotalFaturadoPublico) {
		this.aguaTotalFaturadoPublico = aguaTotalFaturadoPublico;
	}

	public void setAguaTotalFaturadoDireto(String aguaTotalFaturadoDireto) {
		this.aguaTotalFaturadoDireto = aguaTotalFaturadoDireto;
	}

	public void setAguaTotalFaturadoIndireto(String aguaTotalFaturadoIndireto) {
		this.aguaTotalFaturadoIndireto = aguaTotalFaturadoIndireto;
	}

	public void setEsgotoTotalFaturadoResidencial(
			String esgotoTotalFaturadoResidencial) {
		this.esgotoTotalFaturadoResidencial = esgotoTotalFaturadoResidencial;
	}

	public void setEsgotoTotalFaturadoComercial(String esgotoTotalFaturadoComercial) {
		this.esgotoTotalFaturadoComercial = esgotoTotalFaturadoComercial;
	}

	public void setEsgotoTotalFaturadoIndustrial(
			String esgotoTotalFaturadoIndustrial) {
		this.esgotoTotalFaturadoIndustrial = esgotoTotalFaturadoIndustrial;
	}

	public void setEsgotoTotalFaturadoPublico(String esgotoTotalFaturadoPublico) {
		this.esgotoTotalFaturadoPublico = esgotoTotalFaturadoPublico;
	}

	public void setEsgotoTotalFaturadoDireto(String esgotoTotalFaturadoDireto) {
		this.esgotoTotalFaturadoDireto = esgotoTotalFaturadoDireto;
	}

	public void setEsgotoTotalFaturadoIndireto(String esgotoTotalFaturadoIndireto) {
		this.esgotoTotalFaturadoIndireto = esgotoTotalFaturadoIndireto;
	}

	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}

	public void setAguaTotalLigacoesNovas(String aguaTotalLigacoesNovas) {
		this.aguaTotalLigacoesNovas = aguaTotalLigacoesNovas;
	}

	public void setEsgotoTotalLigacoesNovas(String esgotoTotalLigacoesNovas) {
		this.esgotoTotalLigacoesNovas = esgotoTotalLigacoesNovas;
	}

	public void setAguaTotalVolumeFaturadoMicroMedido(
			String aguaTotalVolumeFaturadoMicroMedido) {
		this.aguaTotalVolumeFaturadoMicroMedido = aguaTotalVolumeFaturadoMicroMedido;
	}

	public void setAguaTotalVolumeFaturadoEconomiasResidenciasAtivas(
			String aguaTotalVolumeFaturadoEconomiasResidenciasAtivas) {
		this.aguaTotalVolumeFaturadoEconomiasResidenciasAtivas = aguaTotalVolumeFaturadoEconomiasResidenciasAtivas;
	}

	public void setTotalArrecadacaoMesAtual(String totalArrecadacaoMesAtual) {
		this.totalArrecadacaoMesAtual = totalArrecadacaoMesAtual;
	}

	public void setTotalArrecadacaoMesAnterior(String totalArrecadacaoMesAnterior) {
		this.totalArrecadacaoMesAnterior = totalArrecadacaoMesAnterior;
	}

	public void setTotalFaturamentoLiquido(String totalFaturamentoLiquido) {
		this.totalFaturamentoLiquido = totalFaturamentoLiquido;
	}

	public void setTotalFaturamentoGeral(String totalFaturamentoGeral) {
		this.totalFaturamentoGeral = totalFaturamentoGeral;
	}

	public void setTotalArrecadacaoGeral(String totalArrecadacaoGeral) {
		this.totalArrecadacaoGeral = totalArrecadacaoGeral;
	}

	public void setAguaTotalFaturamentoGeralDI(String aguaTotalFaturamentoGeralDI) {
		this.aguaTotalFaturamentoGeralDI = aguaTotalFaturamentoGeralDI;
	}

	public void setEsgotoTotalFaturamentoGeralDI(
			String esgotoTotalFaturamentoGeralDI) {
		this.esgotoTotalFaturamentoGeralDI = esgotoTotalFaturamentoGeralDI;
	}

	public void setAguaTotalLigacoesSuprimidas(String aguaTotalLigacoesSuprimidas) {
		this.aguaTotalLigacoesSuprimidas = aguaTotalLigacoesSuprimidas;
	}

	public void setAguaTotalVolumeFaturadoConsumido(
			String aguaTotalVolumeFaturadoConsumido) {
		this.aguaTotalVolumeFaturadoConsumido = aguaTotalVolumeFaturadoConsumido;
	}

	public void setAguaTotalVolumeFaturado(String aguaTotalVolumeFaturado) {
		this.aguaTotalVolumeFaturado = aguaTotalVolumeFaturado;
	}

	public void setEsgotoTotalLigacoesAtivas(String esgotoTotalLigacoesAtivas) {
		this.esgotoTotalLigacoesAtivas = esgotoTotalLigacoesAtivas;
	}

	public void setEsgotoTotalEconomiasAtivas(String esgotoTotalEconomiasAtivas) {
		this.esgotoTotalEconomiasAtivas = esgotoTotalEconomiasAtivas;
	}

	public void setEsgotoTotalEconomiasCadastradas(
			String esgotoTotalEconomiasCadastradas) {
		this.esgotoTotalEconomiasCadastradas = esgotoTotalEconomiasCadastradas;
	}

	public void setReceitaOperacionalTotal(String receitaOperacionalTotal) {
		this.receitaOperacionalTotal = receitaOperacionalTotal;
	}

	public void setReceitaOperacionalDireta(String receitaOperacionalDireta) {
		this.receitaOperacionalDireta = receitaOperacionalDireta;
	}

	public void setReceitaOperacionalIndireta(String receitaOperacionalIndireta) {
		this.receitaOperacionalIndireta = receitaOperacionalIndireta;
	}

	public void setSaldoContasReceber(String saldoContasReceber) {
		this.saldoContasReceber = saldoContasReceber;
	}

	public void setSaldoContasReceberParticular(String saldoContasReceberParticular) {
		this.saldoContasReceberParticular = saldoContasReceberParticular;
	}

	public void setSaldoContasReceberPublico(String saldoContasReceberPublico) {
		this.saldoContasReceberPublico = saldoContasReceberPublico;
	}	
	

	
}
