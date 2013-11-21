package gcom.gui.relatorio.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * 
 * @author Daniel Alves
 * 
 * @date 24/09/2010
 */

public class GerarRelatorioImoveisDoacoesActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String imovel;

	private String nomeImovel;

	private String opcaoRelatorio;

	private String periodoAdesaoInicial;

	private String periodoAdesaoFinal;

	private String periodoCancelamentoInicial;

	private String periodoCancelamentoFinal;

	private String entidadeBeneficente;

	private String referenciaInicioDoacaoInicial;

	private String referenciaInicioDoacaoFinal;

	private String referenciaFimDoacaoInicial;

	private String referenciaFimDoacaoFinal;
	
	private String usuarioAdesao;
	
	private String nomeUsuarioAdesao;
	
	private String usuarioCancelamento;
	
	private String nomeUsuarioCancelamento;

	public String getNomeUsuarioCancelamento() {
		return nomeUsuarioCancelamento;
	}

	public void setNomeUsuarioCancelamento(String nomeUsuarioCancelamento) {
		this.nomeUsuarioCancelamento = nomeUsuarioCancelamento;
	}

	public String getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(String usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public String getNomeUsuarioAdesao() {
		return nomeUsuarioAdesao;
	}

	public void setNomeUsuarioAdesao(String nomeUsuarioAdesao) {
		this.nomeUsuarioAdesao = nomeUsuarioAdesao;
	}

	public String getUsuarioAdesao() {
		return usuarioAdesao;
	}

	public void setUsuarioAdesao(String usuarioAdesao) {
		this.usuarioAdesao = usuarioAdesao;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public String getEntidadeBeneficente() {
		return entidadeBeneficente;
	}

	public void setEntidadeBeneficente(String entidadeBeneficente) {
		this.entidadeBeneficente = entidadeBeneficente;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String getPeriodoAdesaoFinal() {
		return periodoAdesaoFinal;
	}

	public void setPeriodoAdesaoFinal(String periodoAdesaoFinal) {
		this.periodoAdesaoFinal = periodoAdesaoFinal;
	}

	public String getPeriodoAdesaoInicial() {
		return periodoAdesaoInicial;
	}

	public void setPeriodoAdesaoInicial(String periodoAdesaoInicial) {
		this.periodoAdesaoInicial = periodoAdesaoInicial;
	}

	public String getPeriodoCancelamentoFinal() {
		return periodoCancelamentoFinal;
	}

	public void setPeriodoCancelamentoFinal(String periodoCancelamentoFinal) {
		this.periodoCancelamentoFinal = periodoCancelamentoFinal;
	}

	public String getPeriodoCancelamentoInicial() {
		return periodoCancelamentoInicial;
	}

	public void setPeriodoCancelamentoInicial(String periodoCancelamentoInicial) {
		this.periodoCancelamentoInicial = periodoCancelamentoInicial;
	}

	public String getReferenciaFimDoacaoFinal() {
		return referenciaFimDoacaoFinal;
	}

	public void setReferenciaFimDoacaoFinal(String referenciaFimDoacaoFinal) {
		this.referenciaFimDoacaoFinal = referenciaFimDoacaoFinal;
	}

	public String getReferenciaFimDoacaoInicial() {
		return referenciaFimDoacaoInicial;
	}

	public void setReferenciaFimDoacaoInicial(String referenciaFimDoacaoInicial) {
		this.referenciaFimDoacaoInicial = referenciaFimDoacaoInicial;
	}

	public String getReferenciaInicioDoacaoFinal() {
		return referenciaInicioDoacaoFinal;
	}

	public void setReferenciaInicioDoacaoFinal(
			String referenciaInicioDoacaoFinal) {
		this.referenciaInicioDoacaoFinal = referenciaInicioDoacaoFinal;
	}

	public String getReferenciaInicioDoacaoInicial() {
		return referenciaInicioDoacaoInicial;
	}

	public void setReferenciaInicioDoacaoInicial(
			String referenciaInicioDoacaoInicial) {
		this.referenciaInicioDoacaoInicial = referenciaInicioDoacaoInicial;
	}

}
