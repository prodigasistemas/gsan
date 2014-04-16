package gcom.gui.relatorio.gerencial.faturamento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00752] Gerar Quadro de Metas Acumulado
 * 
 * @author Bruno Barros
 *
 * @date 07/03/2008
 */

public class EmissaoRelatorioQuadroMetasExercicioActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao;
	
	private String anoExercicio;

	private String gerenciaRegional;
	private String unidadeNegocio;	
	private String localidade;
	private String nomeLocalidade;
	
	public String getAnoExercicio() {
	
		return anoExercicio;
	}
	
	public void setAnoExercicio(String anoExercicio) {
	
		this.anoExercicio = anoExercicio;
	}
	
	public String getGerenciaRegional() {
	
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(String gerenciaRegional) {
	
		this.gerenciaRegional = gerenciaRegional;
	}
	
	public String getLocalidade() {
	
		return localidade;
	}
	
	public void setLocalidade(String localidade) {
	
		this.localidade = localidade;
	}
	
	public String getNomeLocalidade() {
	
		return nomeLocalidade;
	}
	
	public void setNomeLocalidade(String nomeLocalidade) {
	
		this.nomeLocalidade = nomeLocalidade;
	}
	
	public String getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}
	
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
	
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	public String getUnidadeNegocio() {
	
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(String unidadeNegocio) {
	
		this.unidadeNegocio = unidadeNegocio;
	}
	
	
}
