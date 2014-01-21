package gcom.util.exception;

public class MatriculaProprietarioException extends BaseRuntimeException {
	private static final long serialVersionUID = 4003933026492216447L;

	public MatriculaProprietarioException(Exception e, String... parametros) {
		super("atencao.matricula.proprietario.invalida", e, parametros);
	}
}
