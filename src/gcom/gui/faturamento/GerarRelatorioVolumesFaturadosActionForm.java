package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioVolumesFaturadosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String mesAno;

    private String opcaoRelatorio;

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
    
}
