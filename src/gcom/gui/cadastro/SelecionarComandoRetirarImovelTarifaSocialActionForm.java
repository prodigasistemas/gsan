package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class SelecionarComandoRetirarImovelTarifaSocialActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String indicadorSituacao;
	private String indicadorTipoCarta;
	
	public String getIndicadorSituacao() {
		return indicadorSituacao;
	}
	public void setIndicadorSituacao(String indicadorSituacao) {
		this.indicadorSituacao = indicadorSituacao;
	}
	public String getIndicadorTipoCarta() {
		return indicadorTipoCarta;
	}
	public void setIndicadorTipoCarta(String indicadorTipoCarta) {
		this.indicadorTipoCarta = indicadorTipoCarta;
	}
	
	
		
	
}
