package gcom.relatorio.micromedicao;

import java.io.Serializable;

public class RelatorioAcompanhamentoLeituristaHorariosHelper implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String horario;

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}
