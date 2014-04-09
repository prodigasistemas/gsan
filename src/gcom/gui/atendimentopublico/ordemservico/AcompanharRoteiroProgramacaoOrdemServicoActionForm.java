package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0470] Acompanhar Roteiro de Programação de Ordens de Serviço
 * 
 * @author Rafael Pinto
 * @date 20/09/2006
 */
public class AcompanharRoteiroProgramacaoOrdemServicoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	// Atributos usados para filtrar as equipes e suas programações
	private String dataProgramacao;
	private String idEmpresa;
	private String idSituacao;
	private String[] idsRegistros;
	private String idAcompanhamentoArquivosRoteiro;
	private String idOsProgramacaoAcompanhamentoServico;

	private String dataRoteiro;
	private String unidadeLotacao;

	// Usado na tela de incluir os	
	private String idEquipe;
	private String nomeEquipe;
	private String placaVeiculo;
	private String cargaTrabalhoDia;
	private String idUnidade;
	private String descricaoUnidade;
	private String idTipoPerfilServico;
	private String descricaoTipoPerfilServico;
	private Collection<EquipeComponentes> equipeComponentes = new ArrayList<EquipeComponentes>();
	
	private String numeroRA;
	private String descricaoRA;
	private String sequencialProgramacaoPrevisto;
	private String sequencialProgramacao;
	
	// Usada na tela de alerta
	private String idOrdemServico;
	private String descricaoOrdemServico;
	private String alertaEquipeLogradouro;
	private String alertaEquipeServico;
	
	// Usado na tela de aloca equipes
	private String[] equipeSelecionada;
	private String[] equipeSelecionadaAtual;
	private String idEquipePrincipal;
	private Collection<Equipe> equipes = new ArrayList<Equipe>();
	
	// Usado na tela de remaneja equipe
	private String idEquipeAtual;
	private String nomeEquipeAtual;
	private String situacaoOrdemServico;
	private String motivoNaoEncerramento;
	private String mensagemRetorno;
	
	// Usado no relatorio de imprimir Roteiro
	private String chavesRelatorio;
	
	// Usado na tela de situacao ordem Servico
	private String situacaoAtual;
	
	// Usado na tela de reoderna Sequecial
	private String chaveEquipe;
	private String chaveArquivo;

	public String getChaveArquivo() {
		return chaveArquivo;
	}

	public void setChaveArquivo(String chaveArquivo) {
		this.chaveArquivo = chaveArquivo;
	}

	public String getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(String idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public String getIdAcompanhamentoArquivosRoteiro() {
		return idAcompanhamentoArquivosRoteiro;
	}

	public void setIdAcompanhamentoArquivosRoteiro(
			String idAcompanhamentoArquivosRoteiro) {
		this.idAcompanhamentoArquivosRoteiro = idAcompanhamentoArquivosRoteiro;
	}

	public String getIdOsProgramacaoAcompanhamentoServico() {
		return idOsProgramacaoAcompanhamentoServico;
	}

	public void setIdOsProgramacaoAcompanhamentoServico(
			String idOsProgramacaoAcompanhamentoServico) {
		this.idOsProgramacaoAcompanhamentoServico = idOsProgramacaoAcompanhamentoServico;
	}

	public String getChaveEquipe() {
		return chaveEquipe;
	}

	public void setChaveEquipe(String chaveEquipe) {
		this.chaveEquipe = chaveEquipe;
	}

	public String getIdEquipeAtual() {
		return idEquipeAtual;
	}

	public void setIdEquipeAtual(String idEquipeAtual) {
		this.idEquipeAtual = idEquipeAtual;
	}

	public String getNomeEquipeAtual() {
		return nomeEquipeAtual;
	}

	public void setNomeEquipeAtual(String nomeEquipeAtual) {
		this.nomeEquipeAtual = nomeEquipeAtual;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

		numeroRA = null;
		descricaoRA = null;
		descricaoOrdemServico = null;
		sequencialProgramacao = null;
		equipeSelecionada = null;
		idEquipePrincipal = null;
		
		idEquipeAtual = null;
		nomeEquipeAtual = null;
		situacaoOrdemServico = null;
		motivoNaoEncerramento = null;

		situacaoAtual = null;
		chaveEquipe = null;
		mensagemRetorno = null;
	}

	public String getAlertaEquipeServico() {
		return alertaEquipeServico;
	}

	public void setAlertaEquipeServico(String alertaEquipeServico) {
		this.alertaEquipeServico = alertaEquipeServico;
	}

	public String getDescricaoOrdemServico() {
		return descricaoOrdemServico;
	}

	public void setDescricaoOrdemServico(String descricaoOrdemServico) {
		this.descricaoOrdemServico = descricaoOrdemServico;
	}

	public String getAlertaEquipeLogradouro() {
		return alertaEquipeLogradouro;
	}

	public void setAlertaEquipeLogradouro(String alertaEquipeLogradouro) {
		this.alertaEquipeLogradouro = alertaEquipeLogradouro;
	}

	public String getDataRoteiro() {
		return dataRoteiro;
	}

	public void setDataRoteiro(String dataRoteiro) {
		this.dataRoteiro = dataRoteiro;
	}

	public String getUnidadeLotacao() {
		return unidadeLotacao;
	}

	public void setUnidadeLotacao(String unidadeLotacao) {
		this.unidadeLotacao = unidadeLotacao;
	}

	public String getDescricaoTipoPerfilServico() {
		return descricaoTipoPerfilServico;
	}

	public void setDescricaoTipoPerfilServico(String descricaoTipoPerfilServico) {
		this.descricaoTipoPerfilServico = descricaoTipoPerfilServico;
	}

	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	public String getIdTipoPerfilServico() {
		return idTipoPerfilServico;
	}

	public void setIdTipoPerfilServico(String idTipoPerfilServico) {
		this.idTipoPerfilServico = idTipoPerfilServico;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getCargaTrabalhoDia() {
		return cargaTrabalhoDia;
	}

	public void setCargaTrabalhoDia(String cargaTrabalhoDia) {
		this.cargaTrabalhoDia = cargaTrabalhoDia;
	}

	public Collection<EquipeComponentes> getEquipeComponentes() {
		return equipeComponentes;
	}

	public void setEquipeComponentes(Collection<EquipeComponentes> equipeComponentes) {
		this.equipeComponentes = equipeComponentes;
	}

	public String getDescricaoRA() {
		return descricaoRA;
	}

	public void setDescricaoRA(String descricaoRA) {
		this.descricaoRA = descricaoRA;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getSequencialProgramacao() {
		return sequencialProgramacao;
	}

	public void setSequencialProgramacao(String sequencialProgramacao) {
		this.sequencialProgramacao = sequencialProgramacao;
	}

	public String getSequencialProgramacaoPrevisto() {
		return sequencialProgramacaoPrevisto;
	}

	public void setSequencialProgramacaoPrevisto(
			String sequencialProgramacaoPrevisto) {
		this.sequencialProgramacaoPrevisto = sequencialProgramacaoPrevisto;
	}

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getIdEquipePrincipal() {
		return idEquipePrincipal;
	}

	public void setIdEquipePrincipal(String idEquipePrincipal) {
		this.idEquipePrincipal = idEquipePrincipal;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public Collection<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(Collection<Equipe> equipes) {
		this.equipes = equipes;
	}

	public String[] getEquipeSelecionada() {
		return equipeSelecionada;
	}

	public void setEquipeSelecionada(String[] equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}

	public String[] getEquipeSelecionadaAtual() {
		return equipeSelecionadaAtual;
	}

	public void setEquipeSelecionadaAtual(String[] equipeSelecionadaAtual) {
		this.equipeSelecionadaAtual = equipeSelecionadaAtual;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public String getMotivoNaoEncerramento() {
		return motivoNaoEncerramento;
	}

	public void setMotivoNaoEncerramento(String motivoNaoEncerramento) {
		this.motivoNaoEncerramento = motivoNaoEncerramento;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}

	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}

	public String getChavesRelatorio() {
		return chavesRelatorio;
	}

	public void setChavesRelatorio(String chavesRelatorio) {
		this.chavesRelatorio = chavesRelatorio;
	}

}
