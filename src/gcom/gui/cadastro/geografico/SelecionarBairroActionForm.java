package gcom.gui.cadastro.geografico;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da seleção dos bairros 
 *
 * @author Raphael Rossiter
 * @date 29/04/2006
 */
public class SelecionarBairroActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idMunicipio;
	
	private String nomeMunicipio;
	
	private String nomeBairro;
	
	private String[] idBairroSelecao;
	
	
	public String[] getIdBairroSelecao() {
		return idBairroSelecao;
	}

	public void setIdBairroSelecao(String[] idBairroSelecao) {
		this.idBairroSelecao = idBairroSelecao;
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

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
}


