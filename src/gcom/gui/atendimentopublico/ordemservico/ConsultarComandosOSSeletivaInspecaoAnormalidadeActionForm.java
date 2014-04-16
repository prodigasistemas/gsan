package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @since 11/07/2011
 */
public class ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoExecucaoInicial;

	private String periodoExecucaoFinal;

	private Integer[] idRegistros;
	
	private String idRegistro;
	
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

	public String getPeriodoExecucaoFinal() {
		return periodoExecucaoFinal;
	}

	public void setPeriodoExecucaoFinal(String periodoExecucaoFinal) {
		this.periodoExecucaoFinal = periodoExecucaoFinal;
	}

	public String getPeriodoExecucaoInicial() {
		return periodoExecucaoInicial;
	}

	public void setPeriodoExecucaoInicial(String periodoExecucaoInicial) {
		this.periodoExecucaoInicial = periodoExecucaoInicial;
	}

	public ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm(String idEmpresa, String nomeEmpresa, String periodoExecucaoInicial, String periodoExecucaoFinal) {
		super();
		// TODO Auto-generated constructor stub
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.periodoExecucaoInicial = periodoExecucaoInicial;
		this.periodoExecucaoFinal = periodoExecucaoFinal;
	}

	public ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Integer[] getIdRegistros() {
		return idRegistros;
	}

	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}

}
