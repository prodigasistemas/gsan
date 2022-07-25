package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.List;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.util.Util;

public class EntradaParcelamentoDTO {

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
	
	private String valorTotal;

	private String agenciaNome;
	private String agenciaTelefone;
	private String agenciaEmail;

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

	private String nossoNumeroSemDV;

	private String numeroCarteira;

	private Integer tipoDocumento;

	private String banco;

	private String numeroReferencia;

	private String digitosIniciaisCodigoBarras;

	public EntradaParcelamentoDTO() {
		super();
	}

	public EntradaParcelamentoDTO(EmitirContaHelper helper, SistemaParametro parametros, String usuario, 
			String nossoNumeroSemDV, String numeroCarteira, String banco, String numeroReferencia) {
		super();

		this.setDadosEmpresa(parametros);

		this.matricula = helper.getIdImovel();
		this.cliente = helper.getNomeCliente();
		if(helper.getCpf() != null) {
			this.cpfCnpj = helper.getCpf();
		}else {
			this.cpfCnpj = helper.getCnpj();
		}
		this.vencimento = Util.formatarData(helper.getDataVencimentoConta());

		this.enderecoImovel = helper.getEnderecoImovel();

		this.valorTotal = helper.getValorContaString();

		this.agenciaNome = helper.getAgenciaReguladora();
		this.agenciaTelefone = helper.getTelefoneAgenciaReguladora();
		this.agenciaEmail = helper.getEmailAgenciaReguladora();

		this.usuario = usuario;
		
		this.nossoNumeroSemDV = nossoNumeroSemDV;
		this.numeroCarteira = numeroCarteira;
		this.banco = banco;
		this.numeroReferencia = numeroReferencia;
		
		if(helper.getRepresentacaoNumericaCodBarraFormatada() != null) {
			this.digitosIniciaisCodigoBarras = helper.getRepresentacaoNumericaCodBarraFormatada().substring(0,4);
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