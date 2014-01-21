package gcom.util.exception;

public class ArquivoAtualizacaoInexistenteException extends BaseRuntimeException {
	private static final long serialVersionUID = -9187509476114954994L;

	
	public ArquivoAtualizacaoInexistenteException(){ 
		super("atencao.arquivo.texto.atualizacao.inexistente");
	}
}
