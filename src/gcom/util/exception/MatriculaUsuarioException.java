package gcom.util.exception;

public class MatriculaUsuarioException extends BaseRuntimeException {
	private static final long serialVersionUID = 4003933026492216447L;

	public MatriculaUsuarioException(Exception e, String... parametros) {
		super("atencao.matricula.usuario.invalida", e, parametros);
	}
}
