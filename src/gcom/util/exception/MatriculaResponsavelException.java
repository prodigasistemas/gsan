package gcom.util.exception;

public class MatriculaResponsavelException extends BaseRuntimeException {
	private static final long serialVersionUID = 4003933026492216447L;

	public MatriculaResponsavelException(Exception e, String... parametros) {
		super("atencao.matricula.responsavel.invalida", e, parametros);
	}
}
