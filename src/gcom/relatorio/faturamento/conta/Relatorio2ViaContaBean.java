package gcom.relatorio.faturamento.conta;

import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class Relatorio2ViaContaBean implements RelatorioBean {
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorio2ViaContaDetailBean;
	
	private JRBeanCollectionDataSource arrayJRBoleto;
	private ArrayList arrayRelatorio2ViaContaBoletoBancarioBean;
	private String boleto;
	
	//Linha 1
	private String empresa;
	private String enderecoEmpresa;
	//Linha 2 
	private String descricaoLocalidade;
	//Linha 3 
	private String matriculaImovelFormatada;
	private String dataVencimento;
	//Linha 4 
	private String nomeCliente;
	//Linha 5 
	private String enderecoImovel;
	private String fatura;
	//Linha 6 
	private String inscricao;
	//Linha 7
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String enderecoClienteResponsavelLinha2;
	private String descricaoAguaSituacao;
	private String descricaoEsgotoSituacao;
	//Linha 9
	private String dadosConsumoMes1;
	private String dadosConsumoMes4;
	//Linha 10
	private String dadosConsumoMes2;
	private String dadosConsumoMes5;
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoFaturamento;
	private String diasConsumo;
	private String consumoMedioDiario;
	//Linha 11
	private String dadosConsumoMes3;
	private String dadosConsumoMes6;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	//Linha 12
	private String descricaoTipoConsumo;
	private String descricaoAnormalidadeConsumo;
	//Linha 13
	private String quantidadeEconomiaConta;
	private String consumoEconomia;
	private String codigoAuxiliarString;
	private String mesagemConsumoString;

	//Linha 17
	private String valorContaString;
	//Linha 18
	private String primeiraParte;
	//Linha 19
	private String segundaParte;
	//Linha 20
	private String terceiraParte;
	//Linha 21
	private String nomeGerenciaRegional;
	private String mesAnoFormatado;
	//Linha 22
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	private String valorMedioTurbidez;
	private String padraoTurbidez;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesTermotolerantes;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoColiformesTotais;
	private String padraoColiformesTermotolerantes;
	private String valorConformeCor;
	private String valorConformeCloro;
	private String valorConformeFluor;
	private String valorConformeColiformesTotais;
	private String valorConformeColiformesTermotolerantes;
	private String valorConformeTurbidez;
	private String valorExigidoTurbidez;
	private String valorExigidoCor;
	private String valorExigidoCloro;
	private String valorExigidoFluor;
	private String valorExigidoColiformesTotais;
	private String valorExigidoColiformesTermotolerantes;
	private String tipoDeConsumo;
	//Linha 24
	private String representacaoNumericaCodBarraFormatada;
	//Linha 25
	private String representacaoNumericaCodBarraSemDigito;
	//Linha28
	private String dataValidade;
	//Linha 31
	private String grupo;	
	private String firma;
	
	private String idConta;
	
	private String totalPaginasRelatorio;
	
	private String contaSemCodigoBarras;
	
	private String numeroDocumento;
	private String numeroCpfCnpj;
	
	private String codigoDebitoAutomatico;
	
	//só aparece na CAERN
	private String rotaEntrega;
	private String debitoCreditoSituacaoAtualConta;
	private String contaPaga;
	
	private String numeroNitrato;
	private String numeroColiformesTotais;
	private String numeroPH;
	
	



	public String getValorConformeColiformesTotais() {
		return valorConformeColiformesTotais;
	}





	private String consumoAnormalidade;
	private String leituraAnormalidade;
	
	private String leituraAnteriorInformada;
	private String leituraAtualInformada;
	private String dataLeituraAnteriorInformada;
	private String dataLeituraAtualInformada;
	/**
	 * Não imprimir o código de barras na segunda via das contas agrupadas
	 * 
	 * @author Wellington Rocha
	 * @date 25/01/2012*/
	private String icClienteFaturaAgrupada;
	
	/**
	 * Informar data de pagamento e agente arrecadador em caso de conta paga
	 * 
	 * @author Wellington Rocha
	 * @date 14/03/2012*/
	private String dataPagamentoConta;
	
	public String getDataPagamentoConta() {
		return dataPagamentoConta;
	}



	public void setDataPagamentoConta(String dataPagamentoConta) {
		this.dataPagamentoConta = dataPagamentoConta;
	}



	public String getIcClienteFaturaAgrupada() {
		return icClienteFaturaAgrupada;
	}



	public void setIcClienteFaturaAgrupada(String icClienteFaturaAgrupada) {
		this.icClienteFaturaAgrupada = icClienteFaturaAgrupada;
	}
	
	public String getValorConformeCor() {
		return valorConformeCor;
	}



	public void setValorConformeCor(String valorConformeCor) {
		this.valorConformeCor = valorConformeCor;
	}



	public String getValorConformeCloro() {
		return valorConformeCloro;
	}



	public void setValorConformeCloro(String valorConformeCloro) {
		this.valorConformeCloro = valorConformeCloro;
	}



	public String getValorConformeFluor() {
		return valorConformeFluor;
	}



	public void setValorConformeFluor(String valorConformeFluor) {
		this.valorConformeFluor = valorConformeFluor;
	}



	public String getValorCoformeColiformesTotais() {
		return valorConformeColiformesTotais;
	}



	public void setValorCoformeColiformesTotais(String valorCoformeColiformesTotais) {
		this.valorConformeColiformesTotais = valorCoformeColiformesTotais;
	}



	public String getValorConformeColiformesTermotolerantes() {
		return valorConformeColiformesTermotolerantes;
	}



	public void setValorConformeColiformesTermotolerantes(
			String valorConformeColiformesTermotolerantes) {
		this.valorConformeColiformesTermotolerantes = valorConformeColiformesTermotolerantes;
	}



	public String getValorConformeTurbidez() {
		return valorConformeTurbidez;
	}



	public void setValorConformeTurbidez(String valorConformeTurbidez) {
		this.valorConformeTurbidez = valorConformeTurbidez;
	}



	public String getValorExigidoTurbidez() {
		return valorExigidoTurbidez;
	}



	public void setValorExigidoTurbidez(String valorExigidoTurbidez) {
		this.valorExigidoTurbidez = valorExigidoTurbidez;
	}



	public String getValorExigidoCor() {
		return valorExigidoCor;
	}



	public void setValorExigidoCor(String valorExigidoCor) {
		this.valorExigidoCor = valorExigidoCor;
	}



	public String getValorExigidoCloro() {
		return valorExigidoCloro;
	}



	public void setValorExigidoCloro(String valorExigidoCloro) {
		this.valorExigidoCloro = valorExigidoCloro;
	}



	public String getValorExigidoFluor() {
		return valorExigidoFluor;
	}



	public void setValorExigidoFluor(String valorExigidoFluor) {
		this.valorExigidoFluor = valorExigidoFluor;
	}



	public String getValorExigidoColiformesTotais() {
		return valorExigidoColiformesTotais;
	}



	public void setValorExigidoColiformesTotais(String valorExigidoColiformesTotais) {
		this.valorExigidoColiformesTotais = valorExigidoColiformesTotais;
	}



	public String getValorExigidoColiformesTermotolerantes() {
		return valorExigidoColiformesTermotolerantes;
	}



	public void setValorExigidoColiformesTermotolerantes(
			String valorExigidoColiformesTermotolerantes) {
		this.valorExigidoColiformesTermotolerantes = valorExigidoColiformesTermotolerantes;
	}

	
	public String getValorMedioColiformesTermotolerantes() {
		return valorMedioColiformesTermotolerantes;
	}



	public void setValorMedioColiformesTermotolerantes(
			String valorMedioColiformesTermotolerantes) {
		this.valorMedioColiformesTermotolerantes = valorMedioColiformesTermotolerantes;
	}



	public String getPadraoColiformesTermotolerantes() {
		return padraoColiformesTermotolerantes;
	}



	public void setPadraoColiformesTermotolerantes(
			String padraoColiformesTermotolerantes) {
		this.padraoColiformesTermotolerantes = padraoColiformesTermotolerantes;
	}



	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}



	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}



	public String getLeituraAnormalidade() {
		return leituraAnormalidade;
	}



	public void setLeituraAnormalidade(String leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}



	public String getLeituraAnteriorInformada() {
		return leituraAnteriorInformada;
	}



	public void setLeituraAnteriorInformada(String leituraAnteriorInformada) {
		this.leituraAnteriorInformada = leituraAnteriorInformada;
	}



	public String getLeituraAtualInformada() {
		return leituraAtualInformada;
	}



	public void setLeituraAtualInformada(String leituraAtualInformada) {
		this.leituraAtualInformada = leituraAtualInformada;
	}



	public String getDataLeituraAnteriorInformada() {
		return dataLeituraAnteriorInformada;
	}



	public void setDataLeituraAnteriorInformada(String dataLeituraAnteriorInformada) {
		this.dataLeituraAnteriorInformada = dataLeituraAnteriorInformada;
	}



	public String getDataLeituraAtualInformada() {
		return dataLeituraAtualInformada;
	}



	public void setDataLeituraAtualInformada(String dataLeituraAtualInformada) {
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
	}



	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}



	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}



	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}



	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}


	public String getValorMedioCor() {
		return valorMedioCor;
	}



	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}



	public String getValorMedioCloro() {
		return valorMedioCloro;
	}



	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}



	public String getValorMedioFluor() {
		return valorMedioFluor;
	}



	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}


	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}


	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}



	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesTermotolerantes;
	}



	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesTermotolerantes = valorMedioColiformesfecais;
	}


	public String getPadraoCor() {
		return padraoCor;
	}



	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}



	public String getPadraoCloro() {
		return padraoCloro;
	}



	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}



	public String getPadraoFluor() {
		return padraoFluor;
	}



	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}



	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}



	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}



	public String getPadraoColiformesfecais() {
		return padraoColiformesTermotolerantes;
	}



	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesTermotolerantes = padraoColiformesfecais;
	}

	public void setValorConformeColiformesTotais(
			String valorConformeColiformesTotais) {
		this.valorConformeColiformesTotais = valorConformeColiformesTotais;
	}



	public String getTipoDeConsumo() {
		return tipoDeConsumo;
	}



	public void setTipoDeConsumo(String tipoDeConsumo) {
		this.tipoDeConsumo = tipoDeConsumo;
	}





	
	public Relatorio2ViaContaBean(
			    String indicadorPrimeiraPagina, 
			    Collection colecaoDetail,
	    		String descricaoLocalidade,
	    		String matriculaImovelFormatada,
	    		String dataVencimento,
	    		String nomeCliente,
	    		String enderecoImovel,
	    		String fatura,
	    		String inscricao,
	    		String idClienteResponsavel,
	    		String enderecoClienteResponsavel,
	    		String descricaoAguaSituacao,
	    		String descricaoEsgotoSituacao,
	    		String dadosConsumoMes1,
	    		String dadosConsumoMes4,
	    		String dadosConsumoMes2,
	    		String dadosConsumoMes5,
	    		String leituraAnterior,
	    		String leituraAtual,
	    		String consumoFaturamento,
	    		String diasConsumo,
	    		String consumoMedioDiario,
	    		String dadosConsumoMes3,
	    		String dadosConsumoMes6,
	    		String dataLeituraAnterior,
	    		String dataLeituraAtual,
	    		String descricaoTipoConsumo,
	    		String descricaoAnormalidadeConsumo,
	    		String quantidadeEconomiaConta,
	    		String consumoEconomia,
	    		String codigoAuxiliarString,
	    		String mesagemConsumoString,
	    		String valorContaString,
	    		String primeiraParte,
	    		String segundaParte,
	    		String terceiraParte,
	    		String nomeGerenciaRegional,
	    		String mesAnoFormatado,
	    		String numeroIndiceTurbidez,
	    		String numeroCloroResidual,	
	    		String numeroNitrato,
	    		String numeroColiformesTotais,
	    		String numeroPH,
	    		String representacaoNumericaCodBarraFormatada,
	    		String representacaoNumericaCodBarraSemDigito,
	    		String dataValidade,
	    		String grupo,	
	    		String firma,
	    		String totalPaginasRelatorio,
	    		String idConta,
	    		String rotaEntrega,
	    		String contaSemCodigoBarras,
	    		String debitoCreditoSituacaoAtualConta,
	    		String contaPaga,
	    		String enderecoClienteResponsavelLinha2,
	    		Collection colecaoBoleto) {
	    	
		    this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
			this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
			this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
			this.arrayJRDetail = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaDetailBean);
		  
	    	this.descricaoLocalidade = descricaoLocalidade;
	    	this.matriculaImovelFormatada = matriculaImovelFormatada;
	    	this.dataVencimento = dataVencimento;
	    	this.nomeCliente = nomeCliente;
	    	this.enderecoImovel = enderecoImovel;
	    	this.fatura = fatura;
	    	this.inscricao = inscricao;
	    	this.idClienteResponsavel = idClienteResponsavel;
	    	this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	    	this.descricaoAguaSituacao = descricaoAguaSituacao;
	    	this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	    	this.dadosConsumoMes1 = dadosConsumoMes1;
	    	this.dadosConsumoMes4 = dadosConsumoMes4;
	    	this.dadosConsumoMes2 = dadosConsumoMes2;
	    	this.dadosConsumoMes5 = dadosConsumoMes5;
	    	this.leituraAnterior = leituraAnterior;
	    	this.leituraAtual = leituraAtual;
	    	this.consumoFaturamento = consumoFaturamento;
	    	this.diasConsumo = diasConsumo;
	    	this.consumoMedioDiario = consumoMedioDiario;
	    	this.dadosConsumoMes3 = dadosConsumoMes3;
	    	this.dadosConsumoMes6 = dadosConsumoMes6;
	    	this.dataLeituraAnterior = dataLeituraAnterior;
	    	this.dataLeituraAtual = dataLeituraAtual;
	    	this.descricaoTipoConsumo = descricaoTipoConsumo;
	    	this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	    	this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	    	this.consumoEconomia = consumoEconomia;
	    	this.codigoAuxiliarString = codigoAuxiliarString;
	    	this.mesagemConsumoString = mesagemConsumoString;
	    	this.valorContaString = valorContaString;
	    	this.primeiraParte = primeiraParte;
	    	this.segundaParte = segundaParte;
	    	this.terceiraParte = terceiraParte;
	    	this.nomeGerenciaRegional = nomeGerenciaRegional;
	    	this.mesAnoFormatado = mesAnoFormatado;
	    	this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	    	this.numeroCloroResidual = numeroCloroResidual;
	    	this.numeroNitrato = numeroNitrato;
	    	this.numeroColiformesTotais = numeroColiformesTotais;
	    	this.numeroPH = numeroPH;
	    	this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	    	this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	    	this.dataValidade = dataValidade;
	    	this.grupo = grupo;	
	    	this.firma = firma;
	    	this.totalPaginasRelatorio = totalPaginasRelatorio;
	    	this.idConta = idConta;
	    	this.rotaEntrega = rotaEntrega;
	    	this.contaSemCodigoBarras = contaSemCodigoBarras;
	    	this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	    	this.contaPaga = contaPaga;
	    	this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	    	
	    	this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
			this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
			this.arrayJRBoleto = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaBoletoBancarioBean);
	    }

	

	public Relatorio2ViaContaBean(EmitirContaHelper emitirContaHelper,
			int indicadorPrimeiraPagina,
			Collection colecaoDetail,
			String dataVencimentoFormatada,
			String enderecoClienteResponsavel,
			int totalPaginasRelatorio,
			String codigoRota,
			String debitoCreditoSituacaoAtualConta,
			String contaPaga,
			String enderecoClienteResponsavelLinha2,
			Collection colecaoBoleto,
			int boleto){
		
		this.indicadorPrimeiraPagina = "" + indicadorPrimeiraPagina;
		this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
		this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaDetailBean);
		this.descricaoLocalidade = emitirContaHelper.getDescricaoLocalidade();
		this.matriculaImovelFormatada = emitirContaHelper.getIdImovel() + "";
		this.dataVencimento = dataVencimentoFormatada;
		this.nomeCliente = emitirContaHelper.getNomeCliente();
		this.enderecoImovel = emitirContaHelper.getEnderecoImovel();
		this.fatura = emitirContaHelper.getFatura();
		this.inscricao = emitirContaHelper.getInscricaoImovel();
		this.idClienteResponsavel = emitirContaHelper.getIdClienteResponsavel();
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.descricaoAguaSituacao = emitirContaHelper.getDescricaoLigacaoAguaSituacao();
		this.descricaoEsgotoSituacao = emitirContaHelper.getDescricaoLigacaoEsgotoSituacao();
		this.dadosConsumoMes1 = emitirContaHelper.getDadosConsumoMes1();
		this.dadosConsumoMes4 = emitirContaHelper.getDadosConsumoMes4();
		this.dadosConsumoMes2 = emitirContaHelper.getDadosConsumoMes2();
		this.dadosConsumoMes5 = emitirContaHelper.getDadosConsumoMes5();
		this.leituraAnterior = emitirContaHelper.getLeituraAnterior();
		this.leituraAtual = emitirContaHelper.getLeituraAtual();
		this.consumoFaturamento = emitirContaHelper.getConsumoFaturamento();
		this.diasConsumo = emitirContaHelper.getDiasConsumo();
		this.consumoMedioDiario = emitirContaHelper.getConsumoMedioDiario();
		this.dadosConsumoMes3 = emitirContaHelper.getDadosConsumoMes3();
		this.dadosConsumoMes6 = emitirContaHelper.getDadosConsumoMes6();
		this.dataLeituraAnterior = emitirContaHelper.getDataLeituraAnterior();
		this.dataLeituraAtual = emitirContaHelper.getDataLeituraAtual();
		this.descricaoTipoConsumo = emitirContaHelper.getDescricaoTipoConsumo();
		this.descricaoAnormalidadeConsumo = emitirContaHelper.getDescricaoAnormalidadeConsumo();
		this.quantidadeEconomiaConta = emitirContaHelper.getQuantidadeEconomiaConta();
		this.consumoEconomia = emitirContaHelper.getConsumoEconomia();
		this.codigoAuxiliarString = emitirContaHelper.getCodigoAuxiliarString();
		this.mesagemConsumoString = emitirContaHelper.getMensagemConsumoString();
		this.valorContaString = emitirContaHelper.getValorContaString();
		this.primeiraParte = emitirContaHelper.getPrimeiraParte();
		this.segundaParte = emitirContaHelper.getSegundaParte();
		this.terceiraParte = emitirContaHelper.getTerceiraParte();
		this.nomeGerenciaRegional = emitirContaHelper.getNomeGerenciaRegional();
		this.mesAnoFormatado = emitirContaHelper.getMesAnoFormatado();
		this.numeroIndiceTurbidez = emitirContaHelper.getNumeroIndiceTurbidez();
		this.numeroCloroResidual = emitirContaHelper.getNumeroCloroResidual();
		this.numeroNitrato = emitirContaHelper.getNumeroNitrato();
		this.numeroColiformesTotais = emitirContaHelper.getValorMedioColiformesTotais();
		this.numeroPH = emitirContaHelper.getValorMedioPh();
		this.representacaoNumericaCodBarraFormatada = emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito = emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito();
		this.dataValidade = emitirContaHelper.getDataValidade();
		this.grupo = emitirContaHelper.getIdFaturamentoGrupo().toString();
		this.firma = emitirContaHelper.getIdEmpresa().toString();
		this.totalPaginasRelatorio = "" + totalPaginasRelatorio;
		this.idConta = emitirContaHelper.getIdConta().toString();
		this.rotaEntrega = codigoRota;
		this.contaSemCodigoBarras = emitirContaHelper.getContaSemCodigoBarras();
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.contaPaga = contaPaga;
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
		this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
		this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
		this.arrayJRBoleto = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaBoletoBancarioBean);
		this.boleto = "" + boleto;
		this.numeroDocumento = (""+emitirContaHelper.getAmReferencia() ) +
							   (emitirContaHelper.getIdImovel().toString());
		this.padraoCloro = emitirContaHelper.getPadraoCloro();
		this.padraoColiformesTermotolerantes = emitirContaHelper.getPadraoColiformesfecais();
		this.padraoColiformesTotais = emitirContaHelper.getPadraoColiformesTotais();
		this.padraoCor = emitirContaHelper.getPadraoCor();
		this.padraoFluor = emitirContaHelper.getPadraoFluor();
		this.padraoTurbidez = emitirContaHelper.getPadraoTurbidez();
		this.valorMedioCloro = emitirContaHelper.getValorMedioCloro();
		this.valorMedioColiformesTermotolerantes = emitirContaHelper.getValorMedioColiformesfecais();
		this.valorMedioColiformesTotais = emitirContaHelper.getValorMedioColiformesTotais();
		this.valorMedioCor = emitirContaHelper.getValorMedioCor();
		this.valorMedioFluor = emitirContaHelper.getValorMedioFluor();
		this.valorMedioTurbidez = emitirContaHelper.getValorMedioTurbidez();
		this.leituraAnormalidade = emitirContaHelper.getDescricaoAbreviadaLeituraAnormalidade();
		this.leituraAnteriorInformada = emitirContaHelper.getLeituraAnteriorInformada();
		this.leituraAtualInformada = emitirContaHelper.getLeituraAtualInformada();
		this.consumoAnormalidade = emitirContaHelper.getConsumoAnormalidade();
		this.dataLeituraAnteriorInformada = emitirContaHelper.getDataLeituraAnteriorInformada();
		this.dataLeituraAtualInformada = emitirContaHelper.getDataLeituraAtualInformada();
		this.valorConformeCloro = emitirContaHelper.getValorConformeCloro();
		this.valorConformeColiformesTermotolerantes = emitirContaHelper.getValorConformeColiformesTermotolerantes();
		this.valorConformeColiformesTotais = emitirContaHelper.getValorConformeColiformesTotais();
		this.valorConformeCor = emitirContaHelper.getValorConformeCor();
		this.valorConformeFluor = emitirContaHelper.getValorConformeFluor();
		this.valorConformeTurbidez = emitirContaHelper.getValorConformeTurbidez();
		this.valorExigidoCloro = emitirContaHelper.getValorExigidoCloro();
		this.valorExigidoColiformesTermotolerantes = emitirContaHelper.getValorExigidoColiformesTermotolerantes();
		this.valorExigidoColiformesTotais = emitirContaHelper.getValorExigidoColiformesTotais();
		this.valorExigidoCor = emitirContaHelper.getValorExigidoCor();
		this.valorExigidoFluor = emitirContaHelper.getValorExigidoFluor();
		this.valorExigidoTurbidez = emitirContaHelper.getValorExigidoTurbidez();
		this.tipoDeConsumo = emitirContaHelper.getDescricaoTipoConsumo();
		this.icClienteFaturaAgrupada = emitirContaHelper.getClienteComFaturaAgrupada().toString();
		this.dataPagamentoConta = emitirContaHelper.getDataPagamentoConta();
		
		
		
		String documentoCpfCnpj = "";
		if (emitirContaHelper.getCpf() != null && !emitirContaHelper.getCpf().equals("")){
			documentoCpfCnpj = Util.formatarCpf(emitirContaHelper.getCpf()) ;
		}else if (emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("")){
			documentoCpfCnpj = Util.formatarCnpj(emitirContaHelper.getCnpj());
		}
		
		this.numeroCpfCnpj = documentoCpfCnpj;
		
		
	}
	
	
	
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
	}

	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}

	public String getCodigoAuxiliarString() {
		return codigoAuxiliarString;
	}

	public void setCodigoAuxiliarString(String codigoAuxiliarString) {
		this.codigoAuxiliarString = codigoAuxiliarString;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}

	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}

	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}

	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}

	public String getDadosConsumoMes1() {
		return dadosConsumoMes1;
	}

	public void setDadosConsumoMes1(String dadosConsumoMes1) {
		this.dadosConsumoMes1 = dadosConsumoMes1;
	}

	public String getDadosConsumoMes2() {
		return dadosConsumoMes2;
	}

	public void setDadosConsumoMes2(String dadosConsumoMes2) {
		this.dadosConsumoMes2 = dadosConsumoMes2;
	}

	public String getDadosConsumoMes3() {
		return dadosConsumoMes3;
	}

	public void setDadosConsumoMes3(String dadosConsumoMes3) {
		this.dadosConsumoMes3 = dadosConsumoMes3;
	}

	public String getDadosConsumoMes4() {
		return dadosConsumoMes4;
	}

	public void setDadosConsumoMes4(String dadosConsumoMes4) {
		this.dadosConsumoMes4 = dadosConsumoMes4;
	}

	public String getDadosConsumoMes5() {
		return dadosConsumoMes5;
	}

	public void setDadosConsumoMes5(String dadosConsumoMes5) {
		this.dadosConsumoMes5 = dadosConsumoMes5;
	}

	public String getDadosConsumoMes6() {
		return dadosConsumoMes6;
	}

	public void setDadosConsumoMes6(String dadosConsumoMes6) {
		this.dadosConsumoMes6 = dadosConsumoMes6;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricaoAguaSituacao() {
		return descricaoAguaSituacao;
	}

	public void setDescricaoAguaSituacao(String descricaoAguaSituacao) {
		this.descricaoAguaSituacao = descricaoAguaSituacao;
	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public String getDescricaoEsgotoSituacao() {
		return descricaoEsgotoSituacao;
	}

	public void setDescricaoEsgotoSituacao(String descricaoEsgotoSituacao) {
		this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
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

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMatriculaImovelFormatada() {
		return matriculaImovelFormatada;
	}

	public void setMatriculaImovelFormatada(String matriculaImovelFormatada) {
		this.matriculaImovelFormatada = matriculaImovelFormatada;
	}

	public String getMesagemConsumoString() {
		return mesagemConsumoString;
	}

	public void setMesagemConsumoString(String mesagemConsumoString) {
		this.mesagemConsumoString = mesagemConsumoString;
	}

	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}

	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}
	
	public String getNumeroColiformesTotais() {
		return numeroColiformesTotais;
	}
	
	public void setNumeroColiformesTotais(String numeroColiformesTotais) {
		this.numeroColiformesTotais = numeroColiformesTotais;
	}
	
	public String getNumeroNitrato() {
		return numeroNitrato;
	}
	
	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}
	
	public String getNumeroPH() {
		return numeroPH;
	}

	public void setNumeroPH(String numeroPH) {
		this.numeroPH = numeroPH;
	}

	public String getPrimeiraParte() {
		return primeiraParte;
	}

	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}

	public String getQuantidadeEconomiaConta() {
		return quantidadeEconomiaConta;
	}

	public void setQuantidadeEconomiaConta(String quantidadeEconomiaConta) {
		this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}

	public String getTerceiraParte() {
		return terceiraParte;
	}

	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}

	public String getValorContaString() {
		return valorContaString;
	}

	public void setValorContaString(String valorContaString) {
		this.valorContaString = valorContaString;
	}

	public String getTotalPaginasRelatorio() {
		return totalPaginasRelatorio;
	}

	public void setTotalPaginasRelatorio(String totalPaginasRelatorio) {
		this.totalPaginasRelatorio = totalPaginasRelatorio;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getRotaEntrega() {
		return rotaEntrega;
	}

	public void setRotaEntrega(String rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}


	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}


	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}

	public String getDebitoCreditoSituacaoAtualConta() {
		return debitoCreditoSituacaoAtualConta;
	}

	public void setDebitoCreditoSituacaoAtualConta(
			String debitoCreditoSituacaoAtualConta) {
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(String contaPaga) {
		this.contaPaga = contaPaga;
	}

	public String getEnderecoClienteResponsavelLinha2() {
		return enderecoClienteResponsavelLinha2;
	}

	public void setEnderecoClienteResponsavelLinha2(
			String enderecoClienteResponsavelLinha2) {
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	}

	public JRBeanCollectionDataSource getArrayJRBoleto() {
		return arrayJRBoleto;
	}

	public void setArrayJRBoleto(JRBeanCollectionDataSource arrayJRBoleto) {
		this.arrayJRBoleto = arrayJRBoleto;
	}

	public ArrayList getArrayRelatorio2ViaContaBoletoBancarioBean() {
		return arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public void setArrayRelatorio2ViaContaBoletoBancarioBean(
			ArrayList arrayRelatorio2ViaContaBoletoBancarioBean) {
		this.arrayRelatorio2ViaContaBoletoBancarioBean = arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}

	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}



	public String getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}



	public void setCodigoDebitoAutomatico(String codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}



	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}



	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}



	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}
}
