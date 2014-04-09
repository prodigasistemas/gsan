package gcom.cadastro.tarifasocial;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTarifaSocialRevisaoMotivo extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroRendaTipo
	 */
	public FiltroTarifaSocialRevisaoMotivo() {
	}

	/**
	 * Construtor da classe FiltroRendaTipo
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroTarifaSocialRevisaoMotivo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String INDICADOR_PERMITE_RECADASTRAMENTO = "indicadorPermiteRecadastramento";

}
