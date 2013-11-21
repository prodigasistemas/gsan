package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @date 14/07/2011
 */
public class MovimentarOSSeletivaInspecaoAnormalidadeActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String idComando;

	private String numeroOSInicial;

	private String numeroOSFinal;
	
	private String tipoPesquisa;
	


	private String idTipoServico;
	
	private String idTipoServicoRA;
	
	private String[] matriculasImoveis = new String[10];
		
	private String[] numerosOS = new String[10];
	
	private String idMotivoEncerramento;
	
	private String dataEncerramento;
	
	private String horaEncerramento;
	
	private String observacaoEncerramento;
	
	private Collection<OrdemServico> colecaoOrdemServico;
	
	private Collection<Integer> colecaoImovel;
	
	private String[] numerosOSEmpresaCobranca;
	
	private String[] numerosOSRegistroAtendimento;
	
	
	private String numeroOSInicialEmitir;

	private String numeroOSFinalEmitir;
	
	private String[] numerosOSEmitir = new String[10];
	
	private String[] numerosOSRegistroAtendimentoEmitir;

	public Collection<Integer> getColecaoImovel() {
		return colecaoImovel;
	}

	public void setColecaoImovel(Collection<Integer> colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	public Collection<OrdemServico> getColecaoOrdemServico() {
		return colecaoOrdemServico;
	}

	public void setColecaoOrdemServico(Collection<OrdemServico> colecaoOrdemServico) {
		this.colecaoOrdemServico = colecaoOrdemServico;
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

	public String getIdComando() {
		return idComando;
	}

	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getIdTipoServicoRA() {
		return idTipoServicoRA;
	}

	public void setIdTipoServicoRA(String idTipoServicoRA) {
		this.idTipoServicoRA = idTipoServicoRA;
	}

	public String[] getMatriculasImoveis() {
		return matriculasImoveis;
	}

	public void setMatriculasImoveis(String[] matriculasImoveis) {
		this.matriculasImoveis = matriculasImoveis;
	}

	public String getNumeroOSFinal() {
		return numeroOSFinal;
	}

	public void setNumeroOSFinal(String numeroOSFinal) {
		this.numeroOSFinal = numeroOSFinal;
	}

	public String getNumeroOSFinalEmitir() {
		return numeroOSFinalEmitir;
	}

	public void setNumeroOSFinalEmitir(String numeroOSFinalEmitir) {
		this.numeroOSFinalEmitir = numeroOSFinalEmitir;
	}

	public String getNumeroOSInicial() {
		return numeroOSInicial;
	}

	public void setNumeroOSInicial(String numeroOSInicial) {
		this.numeroOSInicial = numeroOSInicial;
	}

	public String getNumeroOSInicialEmitir() {
		return numeroOSInicialEmitir;
	}

	public void setNumeroOSInicialEmitir(String numeroOSInicialEmitir) {
		this.numeroOSInicialEmitir = numeroOSInicialEmitir;
	}

	public String[] getNumerosOS() {
		return numerosOS;
	}

	public void setNumerosOS(String[] numerosOS) {
		this.numerosOS = numerosOS;
	}

	public String[] getNumerosOSEmitir() {
		return numerosOSEmitir;
	}

	public void setNumerosOSEmitir(String[] numerosOSEmitir) {
		this.numerosOSEmitir = numerosOSEmitir;
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

	public String[] getNumerosOSRegistroAtendimentoEmitir() {
		return numerosOSRegistroAtendimentoEmitir;
	}

	public void setNumerosOSRegistroAtendimentoEmitir(
			String[] numerosOSRegistroAtendimentoEmitir) {
		this.numerosOSRegistroAtendimentoEmitir = numerosOSRegistroAtendimentoEmitir;
	}

	public String getObservacaoEncerramento() {
		return observacaoEncerramento;
	}

	public void setObservacaoEncerramento(String observacaoEncerramento) {
		this.observacaoEncerramento = observacaoEncerramento;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
	
}
