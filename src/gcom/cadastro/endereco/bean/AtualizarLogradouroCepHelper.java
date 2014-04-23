package gcom.cadastro.endereco.bean;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.LogradouroCep;

/**
 * [UC0033] Manter Logradouro 
 * 
 * @author Raphael Rossiter
 * @date 13/11/2006
 */
public class AtualizarLogradouroCepHelper {

	private LogradouroCep logradouroCep;
	
	private Cep cep;
	
	public AtualizarLogradouroCepHelper(){}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}
	
	
}
