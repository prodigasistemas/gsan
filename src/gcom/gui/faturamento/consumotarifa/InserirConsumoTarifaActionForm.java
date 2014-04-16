package gcom.gui.faturamento.consumotarifa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirConsumoTarifaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descTarifa;

	private String slcDescTarifa;

	private String dataVigencia;
	
	private String idLigacaoAguaPerfil;
	
	private String idTarifaTipoCalculo;
	

	public String getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public String getDescTarifa() {
		return descTarifa;
	}

	public void setDescTarifa(String descTarifa) {
		this.descTarifa = descTarifa;
	}

	public String getSlcDescTarifa() {
		return slcDescTarifa;
	}

	public void setSlcDescTarifa(String slcDescTarifa) {
		this.slcDescTarifa = slcDescTarifa;
	}
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		descTarifa = null;
		dataVigencia = null;
	}

	/**
	 * @return Retorna o campo idLigacaoAguaPerfil.
	 */
	public String getIdLigacaoAguaPerfil() {
		return idLigacaoAguaPerfil;
	}

	/**
	 * @param idLigacaoAguaPerfil O idLigacaoAguaPerfil a ser setado.
	 */
	public void setIdLigacaoAguaPerfil(String idLigacaoAguaPerfil) {
		this.idLigacaoAguaPerfil = idLigacaoAguaPerfil;
	}

	public String getIdTarifaTipoCalculo() {
		return idTarifaTipoCalculo;
	}

	public void setIdTarifaTipoCalculo(String idTarifaTipoCalculo) {
		this.idTarifaTipoCalculo = idTarifaTipoCalculo;
	}
	
	

}
