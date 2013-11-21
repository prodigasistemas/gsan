package gcom.gui.cobranca.cobrancaporresultado;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.Imovel;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * @author Mariana Victor
 * @date 11/05/2011
 */
public class MovimentarOrdemServicoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String idComandoContaCobranca;

	private String numeroOSInicial;

	private String numeroOSFinal;
	
	private String tipoDivEscolhida;
	
	private String[] idsCategoria;
	
	private String[] idsImovelPerfil;
	
	private String[] idsGerenciaRegional;
	
	private String[] idsUnidadeNegocio;
	
	private String tipoPesquisa;
	
	private String idLocalidadeOrigem;
	
	private String nomeLocalidadeOrigem;
	
	private String idSetorComercialOrigem;
	
	private String codigoSetorComercialOrigem;
	
	private String descricaoSetorComercialOrigem;
	
	private String idSetorComercialDestino;
	
	private String codigoQuadraInicial;
	
	private String descricaoQuadraInicial;
	
	private String idLocalidadeDestino;
	
	private String nomeLocalidadeDestino;
	
	private String codigoSetorComercialDestino;
	
	private String descricaoSetorComercialDestino;
	
	private String codigoQuadraFinal;
	
	private String descricaoQuadraFinal;
	
	private String valorMinimo;
	
	private String valorMaximo;
	
	private String qtdContas;
	
	private String qtdClientes;
	
	private String valorTotalDivida;
	
	private String colecaoInformada;
	
	private String totalSelecionado;

	private String idTipoServico;
	
	private String idTipoServicoRA;
	
	private String[] matriculasImoveis = new String[10];
	
	private String[] idsLigacaoAguaSituacao;
	
	private String[] numerosOS = new String[10];
	
	private String idMotivoEncerramento;
	
	private String dataEncerramento;
	
	private String horaEncerramento;
	
	private String observacaoEncerramento;
	
	private Collection<OrdemServico> colecaoOrdemServico;
	
	private Collection<Imovel> colecaoImovel;
	
	private String[] numerosOSEmpresaCobranca;
	
	private String[] numerosOSRegistroAtendimento;
	
	private String qtdeTotalClientes;
	
	public MovimentarOrdemServicoActionForm() {
		super();
	}

	public String getIdComandoContaCobranca() {
		return idComandoContaCobranca;
	}

	public void setIdComandoContaCobranca(String idComandoContaCobranca) {
		this.idComandoContaCobranca = idComandoContaCobranca;
	}

	public String getNumeroOSFinal() {
		return numeroOSFinal;
	}

	public void setNumeroOSFinal(String numeroOSFinal) {
		this.numeroOSFinal = numeroOSFinal;
	}

	public String getNumeroOSInicial() {
		return numeroOSInicial;
	}

	public void setNumeroOSInicial(String numeroOSInicial) {
		this.numeroOSInicial = numeroOSInicial;
	}

	public String getTipoDivEscolhida() {
		return tipoDivEscolhida;
	}

	public void setTipoDivEscolhida(String tipoDivEscolhida) {
		this.tipoDivEscolhida = tipoDivEscolhida;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
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

	public String getDescricaoSetorComercialDestino() {
		return descricaoSetorComercialDestino;
	}

	public void setDescricaoSetorComercialDestino(
			String descricaoSetorComercialDestino) {
		this.descricaoSetorComercialDestino = descricaoSetorComercialDestino;
	}

	public String getDescricaoSetorComercialOrigem() {
		return descricaoSetorComercialOrigem;
	}

	public void setDescricaoSetorComercialOrigem(
			String descricaoSetorComercialOrigem) {
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

	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}

	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
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

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
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

	public String getQtdClientes() {
		return qtdClientes;
	}

	public void setQtdClientes(String qtdClientes) {
		this.qtdClientes = qtdClientes;
	}

	public String getQtdContas() {
		return qtdContas;
	}

	public void setQtdContas(String qtdContas) {
		this.qtdContas = qtdContas;
	}

	public String getValorTotalDivida() {
		return valorTotalDivida;
	}

	public void setValorTotalDivida(String valorTotalDivida) {
		this.valorTotalDivida = valorTotalDivida;
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

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}

	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String[] getMatriculasImoveis() {
		return matriculasImoveis;
	}

	public void setMatriculasImoveis(String[] matriculasImoveis) {
		this.matriculasImoveis = matriculasImoveis;
	}

	public String[] getNumerosOS() {
		return numerosOS;
	}

	public void setNumerosOS(String[] numerosOS) {
		this.numerosOS = numerosOS;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getHoraEncerramento() {
		return horaEncerramento;
	}

	public void setHoraEncerramento(String horaEncerramento) {
		this.horaEncerramento = horaEncerramento;
	}

	public String getObservacaoEncerramento() {
		return observacaoEncerramento;
	}

	public void setObservacaoEncerramento(String observacaoEncerramento) {
		this.observacaoEncerramento = observacaoEncerramento;
	}

	public Collection<OrdemServico> getColecaoOrdemServico() {
		return colecaoOrdemServico;
	}

	public void setColecaoOrdemServico(Collection<OrdemServico> colecaoOrdemServico) {
		this.colecaoOrdemServico = colecaoOrdemServico;
	}

	public Collection<Imovel> getColecaoImovel() {
		return colecaoImovel;
	}

	public void setColecaoImovel(Collection<Imovel> colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	public String[] getNumerosOSEmpresaCobranca() {
		return numerosOSEmpresaCobranca;
	}

	public void setNumerosOSEmpresaCobranca(String[] numerosOSEmpresaCobranca) {
		this.numerosOSEmpresaCobranca = numerosOSEmpresaCobranca;
	}

	public String[] getNumerosOSRegistroAtendimento() {
		return numerosOSRegistroAtendimento;
	}

	public void setNumerosOSRegistroAtendimento(
			String[] numerosOSRegistroAtendimento) {
		this.numerosOSRegistroAtendimento = numerosOSRegistroAtendimento;
	}

	public String getIdTipoServicoRA() {
		return idTipoServicoRA;
	}

	public void setIdTipoServicoRA(String idTipoServicoRA) {
		this.idTipoServicoRA = idTipoServicoRA;
	}

	public String getQtdeTotalClientes() {
		return qtdeTotalClientes;
	}

	public void setQtdeTotalClientes(String qtdeTotalClientes) {
		this.qtdeTotalClientes = qtdeTotalClientes;
	}

}
