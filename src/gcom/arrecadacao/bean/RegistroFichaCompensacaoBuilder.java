package gcom.arrecadacao.bean;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.util.ControladorException;

public class RegistroFichaCompensacaoBuilder {

	public RegistroFichaCompensacaoHeaderHelper getHeader(String linha, ArrecadadorContrato arrecadadorContrato) throws ControladorException{
		return new RegistroFichaCompensacaoHeaderHelper(linha, arrecadadorContrato);
	}
	
	public RegistroFichaCompensacaoTipo7Helper getTipo7(String linha) throws ControladorException{
		return new RegistroFichaCompensacaoTipo7Helper(linha);
	}
	
	public RegistroFichaCompensacaoTrailerHelper getTrailer(String linha) throws ControladorException{
		return new RegistroFichaCompensacaoTrailerHelper(linha);
	}
}
