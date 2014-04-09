package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Francisco do Nascimento
 * @created 23 de abril de 2009
 */

public class FiltroCicloMetaGrupo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCicloMetaGrupo object
	 */
	public FiltroCicloMetaGrupo() {
	}

	/**
	 * Constructor for the FiltroCicloMetaGrupo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCicloMetaGrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "localidade.id";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String COBRANCA_GRUPO_ID = "cobrancaGrupo.id";
	
	public final static String COBRANCA_GRUPO = "cobrancaGrupo";
	
	public final static String CICLO_META_ID = "cicloMeta.id";
	
	public final static String CICLO_META = "cicloMeta";
	
	public final static String QUANTIDADE_REALIZADA = "quantidadeRealizada";
	
	public final static String QUANTIDADE_DOCUMENTOS_RESTANTES = "quantidadeDocumentosRestantes";
	
	public final static String VALOR_TOTAL_DOCUMENTOS_RESTANTES = "valorTotalDocumentosRestantes";	

}
