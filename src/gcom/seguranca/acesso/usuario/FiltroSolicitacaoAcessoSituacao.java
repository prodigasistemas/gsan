package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroSolicitacaoAcessoSituacao extends Filtro implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroSolicitacaoAcessoSituacao() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String CODIGO_SITUACAO = "codigoSituacao";

}
