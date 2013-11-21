package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroImovelInscricaoAlterada extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroImovelInscricaoAlterada() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroImovelInscricaoAlterada(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ATUALIZADO = "indicadorAtualizado";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ALTERACAO_EXCLUIDA = "indicadorAtualizacaoExcluida";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_ERRO_ALTERACAO = "indicadorErroAlteracao";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ATUAL = "localidadeAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_ATUAL = "setorComercialAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ATUAL = "quadraAtual.id";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA_FACE_ATUAL = "quadraFaceAtual";
	
	/**
	 * Description of the Field
	 */
	public final static String LOTE_ATUAL = "loteAtual";
	/**
	 * Description of the Field
	 */
	public final static String SUBLOTE_ATUAL = "subLoteAtual";
	
	public final static String INDICADOR_AUTORIZADO = "indicadorAutorizado";
	
	public final static String SETOR_COMERCIAL_ATUAL_CODIGO = "setorComercialAtual.codigo";
	
	
}
