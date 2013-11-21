package gcom.gui.relatorio.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1011] Gerar Relatorio Emitir Boletim Cadastro Individual.
 * 
 * @author Hugo Leonardo
 *
 * @date 23/03/2010
 */

public class GerarRelatorioEmitirBoletimCadastroIndividualActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String matriculaImovel;
	private String inscricaoImovel;

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

}
