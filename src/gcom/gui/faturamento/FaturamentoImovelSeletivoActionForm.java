package gcom.gui.faturamento;

import gcom.gui.micromedicao.DadosMovimentacao;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class FaturamentoImovelSeletivoActionForm extends ActionForm  {

	private static final long serialVersionUID = 7793346732600789615L;
	private String matriculaImovel;

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
}
