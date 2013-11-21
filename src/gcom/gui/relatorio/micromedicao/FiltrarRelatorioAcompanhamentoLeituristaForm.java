package gcom.gui.relatorio.micromedicao;

import org.apache.struts.action.ActionForm;

public class FiltrarRelatorioAcompanhamentoLeituristaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mesAno;

	private String idGrupoFaturamento;

	private String idEmpresa;

	private String idLeiturista;

	private Integer[] rotas;

	private Integer[] rotasSelecionadas;
	
	private String indicadorHorario;

	public String getIndicadorHorario() {
		return indicadorHorario;
	}

	public void setIndicadorHorario(String indicadorHorario) {
		this.indicadorHorario = indicadorHorario;
	}

	public Integer[] getRotasSelecionadas() {
		return rotasSelecionadas;
	}

	public void setRotasSelecionadas(Integer[] rotasSelecionadas) {
		this.rotasSelecionadas = rotasSelecionadas;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public void setRotas(Integer[] rotas) {
		this.rotas = rotas;
	}

	public Integer[] getRotas() {
		return rotas;
	}
	
	

}
