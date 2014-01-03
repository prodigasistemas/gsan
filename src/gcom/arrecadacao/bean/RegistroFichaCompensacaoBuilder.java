package gcom.arrecadacao.bean;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.util.ControladorException;

public class RegistroFichaCompensacaoBuilder {

	public static RegistroFichaCompensacaoHeaderHelper getHeader(String linha, ArrecadadorContrato arrecadadorContrato) throws ControladorException{
		return new RegistroFichaCompensacaoHeaderHelper(linha, arrecadadorContrato);
	}
	
	public static RegistroFichaCompensacaoTipo7Helper getTipo7(String linha) throws ControladorException{
		return new RegistroFichaCompensacaoTipo7Helper(linha);
	}
	
	public static RegistroFichaCompensacaoTrailerHelper getTrailer(String linha) throws ControladorException{
		return new RegistroFichaCompensacaoTrailerHelper(linha);
	}
}
