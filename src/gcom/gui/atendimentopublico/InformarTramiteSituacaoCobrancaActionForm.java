package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1159] Informar Tramite por Situação de Cobrança
 * 
 * @author Mariana Victor
 * @date 14/04/2011
 */
public class InformarTramiteSituacaoCobrancaActionForm extends
		ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idSituacaoCobranca;
	
	public String getIdSituacaoCobranca() {
		return idSituacaoCobranca;
	}

	public void setIdSituacaoCobranca(String idSituacaoCobranca) {
		this.idSituacaoCobranca = idSituacaoCobranca;
	}

}
