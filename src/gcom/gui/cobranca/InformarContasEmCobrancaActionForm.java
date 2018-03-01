package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class InformarContasEmCobrancaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String[] idsCategoria;

	private String idUnidadeNegocio;

	private String idImovel;

	private String inscricaoImovel;

	private String idCliente;

	private String nomeCliente;

	private String idLocalidadeOrigem;

	private String nomeLocalidadeOrigem;

	private String idLocalidadeDestino;

	private String nomeLocalidadeDestino;

	private String idSetorComercialOrigem;

	private String codigoSetorComercialOrigem;

	private String descricaoSetorComercialOrigem;

	private String idSetorComercialDestino;

	private String codigoSetorComercialDestino;

	private String descricaoSetorComercialDestino;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;

	private String valorMinimo;

	private String valorMaximo;

	private String qtdContas;

	private String qtdClientes;

	private String valorTotalDivida;

	private String[] idsImovelPerfil;

	private String[] idsUnidadeNegocio;

	private String[] idsGerenciaRegional;

	private String codigoQuadraInicial;

	private String descricaoQuadraInicial;

	private String codigoQuadraFinal;

	private String descricaoQuadraFinal;

	private String dataInicioCiclo;

	private String quantidadeDiasCiclo;

	private String idServicoTipo;

	private String descricaoServicoTipo;

	private String colecaoInformada;

	private String totalSelecionado;

	private String[] idsLigacaoAguaSituacao;

	private String quantidadeContasInicial;

	private String quantidadeContasFinal;

	private String quantidadeDiasVencimento;

	private String indicadorCobrancaTelemarketing;

	private String quantidadeMaximaClientes;

	private String indicadorGerarComDebitoPreterito;

	private String indicadorPossuiCpfCnpj;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getCodigoSetorComercialDestino() {
		return codigoSetorComercialDestino;
	}

	public void setCodigoSetorComercialDestino(String codigoSetorComercialDestino) {
		this.codigoSetorComercialDestino = codigoSetorComercialDestino;
	}

	public String getCodigoSetorComercialOrigem() {
		return codigoSetorComercialOrigem;
	}

	public void setCodigoSetorComercialOrigem(String codigoSetorComercialOrigem) {
		this.codigoSetorComercialOrigem = codigoSetorComercialOrigem;
	}

	public String getDescricaoSetorComercialDestino() {
		return descricaoSetorComercialDestino;
	}

	public void setDescricaoSetorComercialDestino(String descricaoSetorComercialDestino) {
		this.descricaoSetorComercialDestino = descricaoSetorComercialDestino;
	}

	public String getDescricaoSetorComercialOrigem() {
		return descricaoSetorComercialOrigem;
	}

	public void setDescricaoSetorComercialOrigem(String descricaoSetorComercialOrigem) {
		this.descricaoSetorComercialOrigem = descricaoSetorComercialOrigem;
	}

	public String getIdLocalidadeDestino() {
		return idLocalidadeDestino;
	}

	public void setIdLocalidadeDestino(String idLocalidadeDestino) {
		this.idLocalidadeDestino = idLocalidadeDestino;
	}

	public String getIdLocalidadeOrigem() {
		return idLocalidadeOrigem;
	}

	public void setIdLocalidadeOrigem(String idLocalidadeOrigem) {
		this.idLocalidadeOrigem = idLocalidadeOrigem;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getQtdContas() {
		return qtdContas;
	}

	public void setQtdContas(String qtdContas) {
		this.qtdContas = qtdContas;
	}

	public String getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(String valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getQtdClientes() {
		return qtdClientes;
	}

	public void setQtdClientes(String qtdClientes) {
		this.qtdClientes = qtdClientes;
	}

	public String getValorTotalDivida() {
		return valorTotalDivida;
	}

	public void setValorTotalDivida(String valorTotalDivida) {
		this.valorTotalDivida = valorTotalDivida;
	}

	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}

	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}

	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}

	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}

	public String getCodigoQuadraFinal() {
		return codigoQuadraFinal;
	}

	public void setCodigoQuadraFinal(String codigoQuadraFinal) {
		this.codigoQuadraFinal = codigoQuadraFinal;
	}

	public String getCodigoQuadraInicial() {
		return codigoQuadraInicial;
	}

	public void setCodigoQuadraInicial(String codigoQuadraInicial) {
		this.codigoQuadraInicial = codigoQuadraInicial;
	}

	public String getDescricaoQuadraFinal() {
		return descricaoQuadraFinal;
	}

	public void setDescricaoQuadraFinal(String descricaoQuadraFinal) {
		this.descricaoQuadraFinal = descricaoQuadraFinal;
	}

	public String getDescricaoQuadraInicial() {
		return descricaoQuadraInicial;
	}

	public void setDescricaoQuadraInicial(String descricaoQuadraInicial) {
		this.descricaoQuadraInicial = descricaoQuadraInicial;
	}

	public String getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public void setDataInicioCiclo(String dataInicioCiclo) {
		this.dataInicioCiclo = dataInicioCiclo;
	}

	public String getQuantidadeDiasCiclo() {
		return quantidadeDiasCiclo;
	}

	public void setQuantidadeDiasCiclo(String quantidadeDiasCiclo) {
		this.quantidadeDiasCiclo = quantidadeDiasCiclo;
	}

	public String getIdSetorComercialDestino() {
		return idSetorComercialDestino;
	}

	public void setIdSetorComercialDestino(String idSetorComercialDestino) {
		this.idSetorComercialDestino = idSetorComercialDestino;
	}

	public String getIdSetorComercialOrigem() {
		return idSetorComercialOrigem;
	}

	public void setIdSetorComercialOrigem(String idSetorComercialOrigem) {
		this.idSetorComercialOrigem = idSetorComercialOrigem;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getColecaoInformada() {
		return colecaoInformada;
	}

	public void setColecaoInformada(String colecaoInformada) {
		this.colecaoInformada = colecaoInformada;
	}

	public String getTotalSelecionado() {
		return totalSelecionado;
	}

	public void setTotalSelecionado(String totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}

	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}

	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}

	public String getQuantidadeContasFinal() {
		return quantidadeContasFinal;
	}

	public void setQuantidadeContasFinal(String quantidadeContasFinal) {
		this.quantidadeContasFinal = quantidadeContasFinal;
	}

	public String getQuantidadeContasInicial() {
		return quantidadeContasInicial;
	}

	public void setQuantidadeContasInicial(String quantidadeContasInicial) {
		this.quantidadeContasInicial = quantidadeContasInicial;
	}

	public String getQuantidadeDiasVencimento() {
		return quantidadeDiasVencimento;
	}

	public void setQuantidadeDiasVencimento(String quantidadeDiasVencimento) {
		this.quantidadeDiasVencimento = quantidadeDiasVencimento;
	}

	public String getIndicadorCobrancaTelemarketing() {
		return indicadorCobrancaTelemarketing;
	}

	public void setIndicadorCobrancaTelemarketing(String indicadorCobrancaTelemarketing) {
		this.indicadorCobrancaTelemarketing = indicadorCobrancaTelemarketing;
	}

	public String getQuantidadeMaximaClientes() {
		return quantidadeMaximaClientes;
	}

	public void setQuantidadeMaximaClientes(String quantidadeMaximaClientes) {
		this.quantidadeMaximaClientes = quantidadeMaximaClientes;
	}

	public String getIndicadorGerarComDebitoPreterito() {
		return indicadorGerarComDebitoPreterito;
	}

	public void setIndicadorGerarComDebitoPreterito(String indicadorGerarComDebitoPreterito) {
		this.indicadorGerarComDebitoPreterito = indicadorGerarComDebitoPreterito;
	}

	public String getIndicadorPossuiCpfCnpj() {
		return indicadorPossuiCpfCnpj;
	}

	public void setIndicadorPossuiCpfCnpj(String indicadorPossuiCpfCnpj) {
		this.indicadorPossuiCpfCnpj = indicadorPossuiCpfCnpj;
	}
}
