package gcom.gui.faturamento.consumotarifa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirSubCategoriaFaixaConsumoTarifaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String limiteSuperiorFaixa;
	private String valorM3Faixa;
	
	public String getLimiteSuperiorFaixa() {
		return limiteSuperiorFaixa;
	}
	public void setLimiteSuperiorFaixa(String limiteSuperiorFaixa) {
		this.limiteSuperiorFaixa = limiteSuperiorFaixa;
	}
	public String getValorM3Faixa() {
		return valorM3Faixa;
	}
	public void setValorM3Faixa(String valorM3Faixa) {
		this.valorM3Faixa = valorM3Faixa;
	}
	
	
}
