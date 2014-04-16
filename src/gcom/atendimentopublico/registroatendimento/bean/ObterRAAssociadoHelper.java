package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0433] Obter Registro de Atendimento Associado
 * 
 * @author Ana Maria
 * @date 08/08/2006
 * 
 */
public class ObterRAAssociadoHelper {

	private Short codigoExistenciaRAAssociado;
	
	private RegistroAtendimento registroAtendimentoAssociado;
	
	public ObterRAAssociadoHelper(){}

	public Short getCodigoExistenciaRAAssociado() {
		return codigoExistenciaRAAssociado;
	}

	public void setCodigoExistenciaRAAssociado(Short codigoExistenciaRAAssociado) {
		this.codigoExistenciaRAAssociado = codigoExistenciaRAAssociado;
	}

	public RegistroAtendimento getRegistroAtendimentoAssociado() {
		return registroAtendimentoAssociado;
	}

	public void setRegistroAtendimentoAssociado(RegistroAtendimento registroAtendimentoAssociado) {
		this.registroAtendimentoAssociado = registroAtendimentoAssociado;
	}
	
}
