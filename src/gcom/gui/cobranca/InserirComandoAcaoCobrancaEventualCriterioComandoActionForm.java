package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0243] Inserir Comando de Ação de Cobrança - Tipo de Comando Eventual -
 * Criterio Comando
 */
public class InserirComandoAcaoCobrancaEventualCriterioComandoActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String criterioAcaoCobranca;

	private String descricaoAcaoCobranca;

	private String gerenciaRegional;

	private String unidadeNegocio;

	private String localidadeOrigemID;

	private String localidadeDestinoID;

	private String inscricaoTipo;

	private String nomeLocalidadeOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String nomeSetorComercialOrigem;

	private String setorComercialDestinoCD;

	private String setorComercialDestinoID;

	private String nomeSetorComercialDestino;

	private String rotaInicial;

	private String rotaFinal;

	private String idCliente;

	private String nomeCliente;

	private String clienteRelacaoTipo;

	private String periodoInicialConta;

	private String periodoFinalConta;

	private String periodoVencimentoContaInicial;

	private String periodoVencimentoContaFinal;

	private String[] cobrancaAcao;

	private String cobrancaAtividade;

	private String cobrancaGrupo;

	private String indicador;

	private String titulo;

	private String descricaoSolicitacao;

	private String prazoExecucao;

	private String quantidadeMaximaDocumentos;

	private String indicadorImoveisDebito;

	private String indicadorGerarBoletimCadastro;

	private String codigoClienteSuperior;

	private String nomeClienteSuperior;

	private String valorLimiteObrigatoria;

	private String logradouroId;

	private String logradouroDescricao;

	private String consumoMedioInicial;

	private String consumoMedioFinal;

	private String tipoConsumo;

	private String periodoInicialFiscalizacao;

	private String periodoFinalFiscalizacao;

	private String[] situacaoFiscalizacao;

	private String numeroQuadraInicial;
	
	private String numeroQuadraFinal;

	private String[] subcategoria;

	public String getLogradouroDescricao() {
		return logradouroDescricao;
	}

	public void setLogradouroDescricao(String logradouroDescricao) {
		this.logradouroDescricao = logradouroDescricao;
	}

	public String getLogradouroId() {
		return logradouroId;
	}

	public void setLogradouroId(String logradouroId) {
		this.logradouroId = logradouroId;
	}

	public String getCriterioAcaoCobranca() {
		return criterioAcaoCobranca;
	}

	public void setCriterioAcaoCobranca(String criterioAcaoCobranca) {
		this.criterioAcaoCobranca = criterioAcaoCobranca;
	}

	public String getDescricaoAcaoCobranca() {
		return descricaoAcaoCobranca;
	}

	public void setDescricaoAcaoCobranca(String descricaoAcaoCobranca) {
		this.descricaoAcaoCobranca = descricaoAcaoCobranca;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rota) {
		this.rotaInicial = rota;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public String getPeriodoFinalConta() {
		return periodoFinalConta;
	}

	public void setPeriodoFinalConta(String periodoFinalConta) {
		this.periodoFinalConta = periodoFinalConta;
	}

	public String getPeriodoInicialConta() {
		return periodoInicialConta;
	}

	public void setPeriodoInicialConta(String periodoInicialConta) {
		this.periodoInicialConta = periodoInicialConta;
	}

	public String getPeriodoVencimentoContaFinal() {
		return periodoVencimentoContaFinal;
	}

	public void setPeriodoVencimentoContaFinal(String periodoVencimentoContaFinal) {
		this.periodoVencimentoContaFinal = periodoVencimentoContaFinal;
	}

	public String getPeriodoVencimentoContaInicial() {
		return periodoVencimentoContaInicial;
	}

	public void setPeriodoVencimentoContaInicial(String periodoVencimentoContaInicial) {
		this.periodoVencimentoContaInicial = periodoVencimentoContaInicial;
	}

	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	public void setCobrancaAtividade(String cobrancaAtividade) {
		this.cobrancaAtividade = cobrancaAtividade;
	}

	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	public String getPrazoExecucao() {
		return prazoExecucao;
	}

	public void setPrazoExecucao(String prazoExecucao) {
		this.prazoExecucao = prazoExecucao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(String[] cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public String getIndicadorGerarBoletimCadastro() {
		return indicadorGerarBoletimCadastro;
	}

	public void setIndicadorGerarBoletimCadastro(String indicadorGerarBoletimCadastro) {
		this.indicadorGerarBoletimCadastro = indicadorGerarBoletimCadastro;
	}

	public String getIndicadorImoveisDebito() {
		return indicadorImoveisDebito;
	}

	public void setIndicadorImoveisDebito(String indicadorImoveisDebito) {
		this.indicadorImoveisDebito = indicadorImoveisDebito;
	}

	public String getQuantidadeMaximaDocumentos() {
		return quantidadeMaximaDocumentos;
	}

	public void setQuantidadeMaximaDocumentos(String quantidadeMaximaDocumentos) {
		this.quantidadeMaximaDocumentos = quantidadeMaximaDocumentos;
	}

	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}

	public String getValorLimiteObrigatoria() {
		return valorLimiteObrigatoria;
	}

	public void setValorLimiteObrigatoria(String valorLimiteObrigatoria) {
		this.valorLimiteObrigatoria = valorLimiteObrigatoria;
	}

	public String getConsumoMedioInicial() {
		return consumoMedioInicial;
	}

	public void setConsumoMedioInicial(String consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}

	public String getConsumoMedioFinal() {
		return consumoMedioFinal;
	}

	public void setConsumoMedioFinal(String consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getPeriodoInicialFiscalizacao() {
		return periodoInicialFiscalizacao;
	}

	public void setPeriodoInicialFiscalizacao(String periodoInicialFiscalizacao) {
		this.periodoInicialFiscalizacao = periodoInicialFiscalizacao;
	}

	public String getPeriodoFinalFiscalizacao() {
		return periodoFinalFiscalizacao;
	}

	public void setPeriodoFinalFiscalizacao(String periodoFinalFiscalizacao) {
		this.periodoFinalFiscalizacao = periodoFinalFiscalizacao;
	}

	public String[] getSituacaoFiscalizacao() {
		return situacaoFiscalizacao;
	}

	public void setSituacaoFiscalizacao(String[] situacaoFiscalizacao) {
		this.situacaoFiscalizacao = situacaoFiscalizacao;
	}

	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public String[] getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String[] subcategoria) {
		this.subcategoria = subcategoria;
	}
}
