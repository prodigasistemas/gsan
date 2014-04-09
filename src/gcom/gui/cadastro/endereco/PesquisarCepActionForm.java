package gcom.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da pesquisa de CEPs 
 *
 * @author Raphael Rossiter
 * @date 05/05/2006
 */
public class PesquisarCepActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idMunicipio;
	
	private String nomeMunicipio;
	
	private String nomeLogradouro;
	
	private String tipoPesquisaLogradouro;
	
	private Long idCepInicial;
	
	private Long idCepFinal;
	
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
	
	public String getTipoPesquisaLogradouro() {
		return tipoPesquisaLogradouro;
	}

	public void setTipoPesquisaLogradouro(String tipoPesquisaLogradouro) {
		this.tipoPesquisaLogradouro = tipoPesquisaLogradouro;
	}

	public Long getIdCepFinal() {
		return idCepFinal;
	}

	public void setIdCepFinal(Long idCepFinal) {
		this.idCepFinal = idCepFinal;
	}

	public Long getIdCepInicial() {
		return idCepInicial;
	}

	public void setIdCepInicial(Long idCepInicial) {
		this.idCepInicial = idCepInicial;
	}

	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }
}
