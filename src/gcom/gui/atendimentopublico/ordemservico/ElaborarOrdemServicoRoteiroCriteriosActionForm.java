package gcom.gui.atendimentopublico.ordemservico;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0456] Elaborar Roteiro de Programação de Ordens de Serviço
 * 
 * @author Rafael Pinto
 *
 * @date 04/09/2006
 */
public class ElaborarOrdemServicoRoteiroCriteriosActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String origemServicos;
	private String criterioSelecao;
	
	private Integer[] tipoServico;
	private Integer[] tipoServicoSelecionados;
	
	private Integer[] equipe;
	private Integer[] equipesSelecionadas;

	private String servicoDiagnosticado;
	private String servicoAcompanhamento;
	
	private String diaAtrasoInicial;
	private String diaAtrasoFinal;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;
	
	private String periodoClienteInicial;
	private String periodoClienteFinal;
	
	private String periodoAgenciaInicial;
	private String periodoAgenciaFinal;
	
	private String dataRoteiro;
	private String unidadeLotacao;

	//Usada na tela de programar roteiro
	private Integer[] osSelecionada;
	
	private int selecionadas;
	private int programadas;
	
	// Usada na tela de alerta
	private int idOrdemServico;
	private String descricaoOrdemServico;
	private String alertaEquipeLogradouro;
	private String alertaEquipeServico;
	

	public int getProgramadas() {
		return programadas;
	}

	public void setProgramadas(int programadas) {
		this.programadas = programadas;
	}

	public int getSelecionadas() {
		return selecionadas;
	}

	public void setSelecionadas(int selecionadas) {
		this.selecionadas = selecionadas;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		
		criterioSelecao = null;
		
		servicoDiagnosticado = null;
		servicoAcompanhamento = null;
		
		diaAtrasoInicial= null;
		diaAtrasoFinal= null;
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;

		periodoGeracaoInicial = null;
		periodoGeracaoFinal = null;

		periodoClienteInicial = null;
		periodoClienteFinal = null;
		
		periodoAgenciaInicial = null;
		periodoAgenciaFinal = null;

	}

	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}


	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}


	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}


	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}

	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}

	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}

	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}


	public Integer[] getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Integer[] tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}

	public void setTipoServicoSelecionados(Integer[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}

	public String getCriterioSelecao() {
		return criterioSelecao;
	}

	public void setCriterioSelecao(String criterioSelecao) {
		this.criterioSelecao = criterioSelecao;
	}

	public String getDiaAtrasoFinal() {
		return diaAtrasoFinal;
	}

	public void setDiaAtrasoFinal(String diaAtrasoFinal) {
		this.diaAtrasoFinal = diaAtrasoFinal;
	}

	public String getDiaAtrasoInicial() {
		return diaAtrasoInicial;
	}

	public void setDiaAtrasoInicial(String diaAtrasoInicial) {
		this.diaAtrasoInicial = diaAtrasoInicial;
	}

	public String getPeriodoAgenciaFinal() {
		return periodoAgenciaFinal;
	}

	public void setPeriodoAgenciaFinal(String periodoAgenciaFinal) {
		this.periodoAgenciaFinal = periodoAgenciaFinal;
	}

	public String getPeriodoAgenciaInicial() {
		return periodoAgenciaInicial;
	}

	public void setPeriodoAgenciaInicial(String periodoAgenciaInicial) {
		this.periodoAgenciaInicial = periodoAgenciaInicial;
	}

	public String getPeriodoClienteFinal() {
		return periodoClienteFinal;
	}

	public void setPeriodoClienteFinal(String periodoClienteFinal) {
		this.periodoClienteFinal = periodoClienteFinal;
	}

	public String getPeriodoClienteInicial() {
		return periodoClienteInicial;
	}

	public void setPeriodoClienteInicial(String periodoClienteInicial) {
		this.periodoClienteInicial = periodoClienteInicial;
	}

	public String getServicoAcompanhamento() {
		return servicoAcompanhamento;
	}

	public void setServicoAcompanhamento(String servicoAcompanhamento) {
		this.servicoAcompanhamento = servicoAcompanhamento;
	}

	public String getServicoDiagnosticado() {
		return servicoDiagnosticado;
	}

	public void setServicoDiagnosticado(String servicoDiagnosticado) {
		this.servicoDiagnosticado = servicoDiagnosticado;
	}

	public Integer[] getEquipe() {
		return equipe;
	}

	public void setEquipe(Integer[] equipe) {
		this.equipe = equipe;
	}

	public Integer[] getEquipesSelecionadas() {
		return equipesSelecionadas;
	}

	public void setEquipesSelecionadas(Integer[] equipesSelecionadas) {
		this.equipesSelecionadas = equipesSelecionadas;
	}

	public Integer[] getOsSelecionada() {
		return osSelecionada;
	}

	public void setOsSelecionada(Integer[] osSelecionada) {
		this.osSelecionada = osSelecionada;
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

	public int getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
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

	public String getOrigemServicos() {
		return origemServicos;
	}

	public void setOrigemServicos(String origemServicos) {
		this.origemServicos = origemServicos;
	}

	public String getUnidadeLotacao() {
		return unidadeLotacao;
	}

	public void setUnidadeLotacao(String unidadeLotacao) {
		this.unidadeLotacao = unidadeLotacao;
	}

}
