package gcom.gui.cadastro.endereco;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da seleção dos CEPs 
 *
 * @author Raphael Rossiter
 * @date 03/05/2006
 */
public class SelecionarCepActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idMunicipio;
	
	private String nomeMunicipio;
	
	private String nomeLogradouro;
	
	private String[] idCepSelecao;
	
	
	public String[] getIdCepSelecao() {
		return idCepSelecao;
	}

	public void setIdCepSelecao(String[] idCepSelecao) {
		this.idCepSelecao = idCepSelecao;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
}
