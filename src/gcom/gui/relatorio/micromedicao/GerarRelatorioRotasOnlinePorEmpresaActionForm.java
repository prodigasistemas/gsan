package gcom.gui.relatorio.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarRelatorioRotasOnlinePorEmpresaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String idFaturamentoGrupo;
	private String idEmpresa;
	private String idLeiturista;
	
	public GerarRelatorioRotasOnlinePorEmpresaActionForm() { }

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String anoMesReferencia) {
		this.mesAnoReferencia = anoMesReferencia;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

}
