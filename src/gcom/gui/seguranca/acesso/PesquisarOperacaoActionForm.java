package gcom.gui.seguranca.acesso;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class PesquisarOperacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoOperacao;

    private String nomeOperacao;

    private String tipoOperacao;

    private String idFuncionalidade;
    
    private String descricaoFuncionalidade;
  
    public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}




	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}




	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	codigoOperacao = null;
    	nomeOperacao = null;
    	tipoOperacao = null;
    	idFuncionalidade = null;

    }




	/**
	 * @return Retorna o campo codigoOperacao.
	 */
	public String getCodigoOperacao() {
		return codigoOperacao;
	}




	/**
	 * @param codigoOperacao O codigoOperacao a ser setado.
	 */
	public void setCodigoOperacao(String codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
	}


	/**
	 * @return Retorna o campo idfuncionalidade.
	 */
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}




	/**
	 * @param idfuncionalidade O idfuncionalidade a ser setado.
	 */
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}



	/**
	 * @return Retorna o campo nomeOperacao.
	 */
	public String getNomeOperacao() {
		return nomeOperacao;
	}




	/**
	 * @param nomeOperacao O nomeOperacao a ser setado.
	 */
	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}




	/**
	 * @return Retorna o campo tipoOperacao.
	 */
	public String getTipoOperacao() {
		return tipoOperacao;
	}




	/**
	 * @param tipoOperacao O tipoOperacao a ser setado.
	 */
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}




}
