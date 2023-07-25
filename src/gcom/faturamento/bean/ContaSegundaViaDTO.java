package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.List;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.util.Util;

public class ContaSegundaViaDTO {

	private Integer codigoDebitoAutomatico;

	private Integer matricula;
	private String cliente;
	private String clienteResponsavel;
	private String cpfCnpj;
	private String referencia;
	private String vencimento;
	private Integer grupo;

	private String enderecoImovel;
	private String enderecoEntrega;

	private String inscricao;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String economias;
	private String hidrometro;

	private String consumoTipo;
	private String consumoFaturado;
	private String consumoDias;
	private String consumoMedia;

	private String consumoMes1;
	private String consumoMes2;
	private String consumoMes3;
	private String consumoMes4;
	private String consumoMes5;
	private String consumoMes6;

	private String leituraAtual;
	private String dataLeituraAtual;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraInformadaAtual;
	private String dataLeituraInformadaAtual;
	private String leituraInformadaAnterior;
	private String dataLeituraInformadaAnterior;
	private String dataProximaLeitura;

	private String mensagemFixa1;
	private String mensagemFixa2;
	private String mensagemFixa3;

	private String mensagem1;
	private String mensagem2;
	private String mensagem3;

	private String mensagemAnormalidade;
	private String mensagemDebitos;
	private String mensagemQuitacao;
	private String mensagemBolsaAgua;

	private List<ContaLinhasDescricaoServicosTarifasTotalHelper> servicos;
	private String valorTotal;

	private String agenciaNome;
	private String agenciaTelefone;
	private String agenciaEmail;

	private String impostosDescricao;
	private String impostosPercentual;
	private String impostosValorBase;
	private String impostosValor;
	
	private String impostosDescricaoAgenciaReguladora;
	private String impostosPercentualAgenciaReguladora;
	private String impostosValorAgenciaReguladora;

	private String padraoTurbidez;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoColiformesTotais;
	private String padraoColiformesTermotolerantes;

	private String valorMedioTurbidez;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesTermotolerantes;

	private String valorConformeTurbidez;
	private String valorConformeCor;
	private String valorConformeCloro;
	private String valorConformeFluor;
	private String valorConformeColiformesTotais;
	private String valorConformeColiformesTermotolerantes;

	private String empresaNome;
	private String empresaNomeAbreviado;
	private String empresaCnpj;
	private String empresaSite;
	private String empresaDescricaoAplicativo;
	private String empresaTelefoneAplicativo;
	private String empresaCallcenter;

	private String usuario;

	private String codigoBarras;
	private String codigoBarrasComDigitos;

	private boolean contaPaga;
	private String dataPagamento;
	private String situacaoConta;

	private Short indicadorMensagemBolsaAgua;

	private String nossoNumeroSemDV;

	private String numeroCarteira;

	private Integer tipoDocumento;

	private String banco;

	private String numeroReferencia;

	private String digitosIniciaisCodigoBarras;

	public ContaSegundaViaDTO() {
		super();
	}

	public ContaSegundaViaDTO(EmitirContaHelper helper, SistemaParametro parametros, String clienteResponsavel, String economias, String hidrometro, String usuario, String situacaoConta
			, String nossoNumeroSemDV, String numeroCarteira, Integer tipoDocumento, String banco, String numeroReferencia) {
		super();

		this.setDadosEmpresa(parametros);

		this.codigoDebitoAutomatico = helper.getCodigoDebitoAutomatico();

		this.matricula = helper.getIdImovel();
		this.cliente = helper.getNomeCliente();
		this.clienteResponsavel = clienteResponsavel;
		this.setCpfCnpj(helper);
		this.referencia = Util.formatarAnoMesParaMesAno(helper.getAmReferencia());
		this.vencimento = Util.formatarData(helper.getDataVencimentoConta());
		this.grupo = helper.getIdFaturamentoGrupo();
		this.inscricao = helper.getInscricaoImovel();

		this.enderecoImovel = helper.getEnderecoImovel();
		this.enderecoEntrega = helper.getEnderecoClienteResponsavel();

		this.leituraAtual = helper.getLeituraAtual();
		this.dataLeituraAtual = helper.getDataLeituraAtual();
		this.leituraAnterior = helper.getLeituraAnterior();
		this.dataLeituraAnterior = helper.getDataLeituraAnterior();
		this.leituraInformadaAtual = helper.getLeituraAtualInformada();
		this.dataLeituraInformadaAtual = helper.getDataLeituraAtualInformada();
		this.leituraInformadaAnterior = helper.getLeituraAnteriorInformada();
		this.dataLeituraInformadaAnterior = helper.getDataLeituraAnteriorInformada();
		this.dataProximaLeitura = "-";

		this.consumoFaturado = existeConsumoFaturado(helper) ? helper.getConsumoFaturamento() : "-";
		this.consumoDias = helper.getDiasConsumo();
		this.setConsumoMedia();

		this.consumoMes1 = helper.getDadosConsumoMes1().trim();
		this.consumoMes2 = helper.getDadosConsumoMes2().trim();
		this.consumoMes3 = helper.getDadosConsumoMes3().trim();
		this.consumoMes4 = helper.getDadosConsumoMes4().trim();
		this.consumoMes5 = helper.getDadosConsumoMes5().trim();
		this.consumoMes6 = helper.getDadosConsumoMes6().trim();
		this.consumoTipo = helper.getDescricaoTipoConsumo();

		this.situacaoAgua = helper.getDescricaoLigacaoAguaSituacao();
		this.situacaoEsgoto = helper.getDescricaoLigacaoEsgotoSituacao();
		this.hidrometro = hidrometro;
		this.economias = economias;

		setMensagensFixas(helper);

		if (helper.getPrimeiraParte() != null && !helper.getPrimeiraParte().trim().equals(""))
			this.mensagem1 = helper.getPrimeiraParte();
		else
			this.mensagem1 = null;
		
		if (helper.getSegundaParte() != null && !helper.getSegundaParte().trim().equals("")  )
			this.mensagem2 = helper.getSegundaParte();
		else
			this.mensagem2 = null;
		
		if (helper.getTerceiraParte() != null && !helper.getTerceiraParte().trim().equals("") )
			this.mensagem3 = helper.getTerceiraParte();
		else
			this.mensagem3 = null;

		this.mensagemAnormalidade = helper.getMensagemAnormalidade();
		this.mensagemDebitos = helper.getMensagemDebitos();
		this.mensagemQuitacao = helper.getMensagemQuitacao().equals("") ? null : helper.getMensagemQuitacao();
		this.mensagemBolsaAgua = helper.getMensagemBolsaAgua().equals("") ? null : helper.getMensagemBolsaAgua();
		//this.indicadorMensagemBolsaAgua = null;
		this.indicadorMensagemBolsaAgua = helper.getIndicadorMensagemBolsaAgua() != null ? helper.getIndicadorMensagemBolsaAgua() : null;

		this.setServicos(helper);
		this.valorTotal = helper.getValorContaString();

		this.agenciaNome = helper.getAgenciaReguladora();
		this.agenciaTelefone = helper.getTelefoneAgenciaReguladora();
		this.agenciaEmail = helper.getEmailAgenciaReguladora();

		this.impostosDescricao = helper.getDescricaoImpostosEAliquotas();
		this.impostosPercentual = Util.formatarMoedaReal(helper.getPercentualImpostosEAliquotas());
		this.impostosValorBase = Util.formatarMoedaReal(helper.getValorBaseCalculoImpostos());
		this.impostosValor = Util.formatarMoedaReal(helper.getValorImpostosEAliquotas());
		
		this.impostosDescricaoAgenciaReguladora = helper.getDescricaoAgenciaReguladora();
		this.impostosPercentualAgenciaReguladora = Util.formatarMoedaReal(helper.getPercentualAgenciaReguladora());
		this.impostosValorAgenciaReguladora = Util.formatarMoedaReal(helper.getValorAgenciaReguladora());

		this.padraoTurbidez = helper.getPadraoTurbidez();
		this.padraoCor = helper.getPadraoCor();
		this.padraoCloro = helper.getPadraoCloro();
		this.padraoFluor = helper.getPadraoFluor();
		this.padraoColiformesTotais = helper.getPadraoColiformesTotais();
		this.padraoColiformesTermotolerantes = helper.getPadraoColiformesfecais();

		this.valorMedioTurbidez = helper.getValorMedioTurbidez();
		this.valorMedioCor = helper.getValorMedioCor();
		this.valorMedioCloro = helper.getValorMedioCloro();
		this.valorMedioFluor = helper.getValorMedioFluor();
		this.valorMedioColiformesTotais = helper.getValorMedioColiformesTotais();
		this.valorMedioColiformesTermotolerantes = helper.getValorMedioColiformesfecais();

		this.valorConformeTurbidez = helper.getValorConformeTurbidez();
		this.valorConformeCor = helper.getValorConformeCor();
		this.valorConformeCloro = helper.getValorConformeCloro();
		this.valorConformeFluor = helper.getValorConformeFluor();
		this.valorConformeColiformesTotais = helper.getValorConformeColiformesTotais();
		this.valorConformeColiformesTermotolerantes = helper.getValorConformeColiformesTermotolerantes();

		this.usuario = usuario;

		this.codigoBarras = helper.getRepresentacaoNumericaCodBarraSemDigito();
		this.codigoBarrasComDigitos = helper.getRepresentacaoNumericaCodBarraFormatada();

		this.contaPaga = helper.getContaPaga().equals("1") ? true : false;
		this.dataPagamento = helper.getDataPagamentoConta();
		this.situacaoConta = situacaoConta;
		
		this.nossoNumeroSemDV = nossoNumeroSemDV;
		this.numeroCarteira = numeroCarteira;
		this.tipoDocumento = tipoDocumento;
		this.banco = banco;
		this.numeroReferencia = numeroReferencia;
		
		if(helper.getRepresentacaoNumericaCodBarraFormatada() != null) {
			this.digitosIniciaisCodigoBarras = helper.getRepresentacaoNumericaCodBarraFormatada().substring(0,4);
		}
	}

	private void setMensagensFixas(EmitirContaHelper helper) {
		Object[] mensagensFixas = helper.getMensagensFixas();
		mensagemFixa1 = mensagensFixas != null ? (String) mensagensFixas[0] : null;
		mensagemFixa2 = mensagensFixas != null ? (String) mensagensFixas[1] : null;
		mensagemFixa3 = mensagensFixas != null ? (String) mensagensFixas[2] : null;
	}

	@SuppressWarnings("unchecked")
	private void setServicos(EmitirContaHelper helper) {
		this.servicos = (List<ContaLinhasDescricaoServicosTarifasTotalHelper>) helper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();
	}

	private boolean existeConsumoFaturado(EmitirContaHelper helper) {
		return helper.getConsumoFaturamento() != null && !helper.getConsumoFaturamento().equals("");
	}

	private void setCpfCnpj(EmitirContaHelper helper) {
		if (helper.getCpf() != null && !helper.getCpf().equals("")) {
			this.cpfCnpj = Util.formatarCpf(helper.getCpf());
		} else if (helper.getCnpj() != null && !helper.getCnpj().equals("")) {
			this.cpfCnpj = Util.formatarCnpj(helper.getCnpj());
		} else {
			this.cpfCnpj = "";
		}
	}

	private void setDadosEmpresa(SistemaParametro parametros) {
		this.empresaNome = parametros.getNomeEmpresa();
		this.empresaNomeAbreviado = parametros.getNomeAbreviadoEmpresa();
		this.empresaCnpj = Util.formatarCnpj(parametros.getCnpjEmpresa());
		this.empresaSite = parametros.getNomeSiteEmpresa();
		this.empresaDescricaoAplicativo = parametros.getDescricaoAplicativoAtendimento();
		this.empresaTelefoneAplicativo = Util.formatarTelefone(parametros.getDddTelefone(), parametros.getTelefoneAplicativoAtendimento());
		this.empresaCallcenter = parametros.getNumero0800Empresa();

		if (empresaCallcenter != null && empresaCallcenter.length() == 11 && empresaCallcenter.startsWith("0800")) {
			this.empresaCallcenter = empresaCallcenter.substring(0, 4) + " " + empresaCallcenter.substring(4, 7) + " " + empresaCallcenter.substring(7, 11);
		}
	}

	private void setConsumoMedia() {
		if (existeConsumoFaturado() && existeConsumoDias()) {
			BigDecimal consumo = new BigDecimal(consumoFaturado.trim());
			BigDecimal dias = new BigDecimal(consumoDias.trim());
			BigDecimal media = consumo.divide(dias, 2, BigDecimal.ROUND_HALF_UP);

			this.consumoMedia = Util.converterDecimalParaString(media);
		} else {
			this.consumoMedia = "";
		}
	}

	private boolean existeConsumoFaturado() {
		return consumoFaturado != null && !consumoFaturado.trim().equals("") && !consumoFaturado.trim().equals("0");
	}

	private boolean existeConsumoDias() {
		return consumoDias != null && !consumoDias.trim().equals("") && !consumoDias.trim().equals("0");
	}

	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public String getCliente() {
		return cliente;
	}

	public String getClienteResponsavel() {
		return clienteResponsavel;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getReferencia() {
		return referencia;
	}

	public String getVencimento() {
		return vencimento;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public String getInscricao() {
		return inscricao;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public String getEconomias() {
		return economias;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public String getConsumoTipo() {
		return consumoTipo;
	}

	public String getConsumoFaturado() {
		return consumoFaturado;
	}

	public String getConsumoDias() {
		return consumoDias;
	}

	public String getConsumoMedia() {
		return consumoMedia;
	}

	public String getConsumoMes1() {
		return consumoMes1;
	}

	public String getConsumoMes2() {
		return consumoMes2;
	}

	public String getConsumoMes3() {
		return consumoMes3;
	}

	public String getConsumoMes4() {
		return consumoMes4;
	}

	public String getConsumoMes5() {
		return consumoMes5;
	}

	public String getConsumoMes6() {
		return consumoMes6;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public String getLeituraInformadaAtual() {
		return leituraInformadaAtual;
	}

	public String getDataLeituraInformadaAtual() {
		return dataLeituraInformadaAtual;
	}

	public String getLeituraInformadaAnterior() {
		return leituraInformadaAnterior;
	}

	public String getDataLeituraInformadaAnterior() {
		return dataLeituraInformadaAnterior;
	}

	public String getDataProximaLeitura() {
		return dataProximaLeitura;
	}

	public String getMensagemFixa1() {
		return mensagemFixa1;
	}

	public String getMensagemFixa2() {
		return mensagemFixa2;
	}

	public String getMensagemFixa3() {
		return mensagemFixa3;
	}

	public String getMensagem1() {
		return mensagem1;
	}

	public String getMensagem2() {
		return mensagem2;
	}

	public String getMensagem3() {
		return mensagem3;
	}

	public String getMensagemAnormalidade() {
		return mensagemAnormalidade;
	}

	public String getMensagemDebitos() {
		return mensagemDebitos;
	}

	public String getMensagemQuitacao() {
		return mensagemQuitacao;
	}

	public String getMensagemBolsaAgua() {
		return mensagemBolsaAgua;
	}
	public Short getIndicadorMensagemBolsaAgua() {
		return indicadorMensagemBolsaAgua;
	}

	public List<ContaLinhasDescricaoServicosTarifasTotalHelper> getServicos() {
		return servicos;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public String getAgenciaNome() {
		return agenciaNome;
	}

	public String getAgenciaTelefone() {
		return agenciaTelefone;
	}

	public String getAgenciaEmail() {
		return agenciaEmail;
	}

	public String getImpostosDescricao() {
		return impostosDescricao;
	}

	public String getImpostosPercentual() {
		return impostosPercentual;
	}

	public String getImpostosValorBase() {
		return impostosValorBase;
	}

	public String getImpostosValor() {
		return impostosValor;
	}
	
	public String getImpostosDescricaoAgenciaReguladora() {
		return impostosDescricaoAgenciaReguladora;
	}

	public String getImpostosPercentualAgenciaReguladora() {
		return impostosPercentualAgenciaReguladora;
	}

	public String getImpostosValorAgenciaReguladora() {
		return impostosValorAgenciaReguladora;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public String getPadraoCloro() {
		return padraoCloro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public String getPadraoColiformesTermotolerantes() {
		return padraoColiformesTermotolerantes;
	}

	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}

	public String getValorMedioCor() {
		return valorMedioCor;
	}

	public String getValorMedioCloro() {
		return valorMedioCloro;
	}

	public String getValorMedioFluor() {
		return valorMedioFluor;
	}

	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}

	public String getValorMedioColiformesTermotolerantes() {
		return valorMedioColiformesTermotolerantes;
	}

	public String getValorConformeTurbidez() {
		return valorConformeTurbidez;
	}

	public String getValorConformeCor() {
		return valorConformeCor;
	}

	public String getValorConformeCloro() {
		return valorConformeCloro;
	}

	public String getValorConformeFluor() {
		return valorConformeFluor;
	}

	public String getValorConformeColiformesTotais() {
		return valorConformeColiformesTotais;
	}

	public String getValorConformeColiformesTermotolerantes() {
		return valorConformeColiformesTermotolerantes;
	}

	public String getEmpresaNome() {
		return empresaNome;
	}

	public String getEmpresaNomeAbreviado() {
		return empresaNomeAbreviado;
	}

	public String getEmpresaCnpj() {
		return empresaCnpj;
	}

	public String getEmpresaSite() {
		return empresaSite;
	}

	public String getEmpresaDescricaoAplicativo() {
		return empresaDescricaoAplicativo;
	}

	public String getEmpresaTelefoneAplicativo() {
		return empresaTelefoneAplicativo;
	}

	public String getEmpresaCallcenter() {
		return empresaCallcenter;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public String getCodigoBarrasComDigitos() {
		return codigoBarrasComDigitos;
	}

	public boolean isContaPaga() {
		return contaPaga;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public String getNossoNumeroSemDV() {
		return nossoNumeroSemDV;
	}

	public String getNumeroCarteira() {
		return numeroCarteira;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public String getBanco() {
		return banco;
	}

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public String getDigitosIniciaisCodigoBarras() {
		return digitosIniciaisCodigoBarras;
	}

}