package gcom.gui.atendimentopublico.ordemservico;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 01/11/2006
 */
public class ManterTipoRetornoOrdemServicoReferidaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoTipoRetorno;
	
    private String descricao;
	
	private String abreviatura;
   
   
    private String referenciaTipoServico;
    
    private String indicadorTipoServico;

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return Retorna o campo codigoTipoRetorno.
	 */
	public String getCodigoTipoRetorno() {
		return codigoTipoRetorno;
	}

	/**
	 * @param codigoTipoRetorno O codigoTipoRetorno a ser setado.
	 */
	public void setCodigoTipoRetorno(String codigoTipoRetorno) {
		this.codigoTipoRetorno = codigoTipoRetorno;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo indicadorTipoServico.
	 */
	public String getIndicadorTipoServico() {
		return indicadorTipoServico;
	}

	/**
	 * @param indicadorTipoServico O indicadorTipoServico a ser setado.
	 */
	public void setIndicadorTipoServico(String indicadorTipoServico) {
		this.indicadorTipoServico = indicadorTipoServico;
	}

	/**
	 * @return Retorna o campo referenciaTipoServico.
	 */
	public String getReferenciaTipoServico() {
		return referenciaTipoServico;
	}

	/**
	 * @param referenciaTipoServico O referenciaTipoServico a ser setado.
	 */
	public void setReferenciaTipoServico(String referenciaTipoServico) {
		this.referenciaTipoServico = referenciaTipoServico;
	}
    

}

	    
