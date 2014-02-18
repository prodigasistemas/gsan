package gcom.atendimentopublico.registroatendimento;

import java.util.Collection;

public class RABuilder {
	
	private RADadosGeraisHelper raDadosGeraisHelper; 	
	private RALocalOcorrenciaHelper raLocalOcorrenciaHelper;
	private RASolicitanteHelper raSolicitanteHelper;
	
	Collection colecaoRegistroAtendimentoAnexo;
	
	public RADadosGeraisHelper getRaDadosGeraisHelper() {
		return raDadosGeraisHelper;
	}
	public void setRaDadosGeraisHelper(RADadosGeraisHelper raDadosGeraisHelper) {
		this.raDadosGeraisHelper = raDadosGeraisHelper;
	}
	public RALocalOcorrenciaHelper getRaLocalOcorrenciaHelper() {
		return raLocalOcorrenciaHelper;
	}
	public void setRaLocalOcorrenciaHelper(
			RALocalOcorrenciaHelper raLocalOcorrenciaHelper) {
		this.raLocalOcorrenciaHelper = raLocalOcorrenciaHelper;
	}
	public RASolicitanteHelper getRaSolicitanteHelper() {
		return raSolicitanteHelper;
	}
	public void setRaSolicitanteHelper(RASolicitanteHelper raSolicitanteHelper) {
		this.raSolicitanteHelper = raSolicitanteHelper;
	}
	public Collection getColecaoRegistroAtendimentoAnexo() {
		return colecaoRegistroAtendimentoAnexo;
	}
	public void setColecaoRegistroAtendimentoAnexo(
			Collection colecaoRegistroAtendimentoAnexo) {
		this.colecaoRegistroAtendimentoAnexo = colecaoRegistroAtendimentoAnexo;
	} 
	
	
}
