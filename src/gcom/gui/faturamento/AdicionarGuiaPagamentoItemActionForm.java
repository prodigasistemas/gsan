package gcom.gui.faturamento;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Inserir Débtio a Cobrar Popup
 * 
 * @author Vivianne Sousa
 * @since 28/08/2007
 */
public class AdicionarGuiaPagamentoItemActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idTipoDebito;
	private String descricaoTipoDebito;
	private String valorTotalServico;
	
	private String matriculaImovel;
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	/**@todo: finish this method, this is just the skeleton.*/
	  return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getValorTotalServico() {
		return valorTotalServico;
	}
	public void setValorTotalServico(String valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
}
