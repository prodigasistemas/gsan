package gcom.gui;

import javax.servlet.http.HttpServletRequest;

public class ObjetoTransmissaoDados {

	public static final String OBJETO_TRANSMISSAO_DADOS = "ObjetoTransmissaoDados"; 

	public ObjetoTransmissaoDados(HttpServletRequest request){
		save(request);
	}

	private void save(HttpServletRequest request) {
		request.getSession(false).setAttribute(ObjetoTransmissaoDados.OBJETO_TRANSMISSAO_DADOS,this);
	}
}
