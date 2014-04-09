package gcom.gui.faturamento.consumotarifa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirCategoriaConsumoTarifaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String slcCategoria;
	private String slcSubCategoria;
	private String consumoMinimo;
	private String valorTarifaMinima;

	public String getConsumoMinimo() {
		return consumoMinimo;
	}
	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}
	public String getSlcCategoria() {
		return slcCategoria;
	}
	public void setSlcCategoria(String slcCategoria) {
		this.slcCategoria = slcCategoria;
	}
	public String getValorTarifaMinima() {
		return valorTarifaMinima;
	}
	public void setValorTarifaMinima(String valorTarifaMinima) {
		this.valorTarifaMinima = valorTarifaMinima;
	}
	
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }
	public String getSlcSubCategoria() {
		return slcSubCategoria;
	}
	public void setSlcSubCategoria(String slcSubCategoria) {
		this.slcSubCategoria = slcSubCategoria;
	}

	
}
