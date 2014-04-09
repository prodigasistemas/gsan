package gcom.cadastro.endereco.bean;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;

/**
 * [UC0033] Manter Logradouro 
 * 
 * @author Raphael Rossiter
 * @date 09/11/2006
 */
public class AtualizarLogradouroBairroHelper {

	private LogradouroBairro logradouroBairro;
	
	private Bairro bairro;
	
	public AtualizarLogradouroBairroHelper(){}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}
	
	
}
