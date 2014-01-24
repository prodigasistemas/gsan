package gcom.util.exception;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -5334054081788545824L;
	
	private ArrayList<String> parametros = new ArrayList<String>();

	public BaseRuntimeException(String msg, String... parametroMensagem){
		super(msg);
		parametros.addAll(Arrays.asList(parametroMensagem));
	}
	
	public BaseRuntimeException(String msg, Throwable e, String... parametroMensagem){
		super(msg, e);
		parametros.addAll(Arrays.asList(parametroMensagem));		
	}

	public ArrayList<String> getParametros() {
		return parametros;
	}
}
