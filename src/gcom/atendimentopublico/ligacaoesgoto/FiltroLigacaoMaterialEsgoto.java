package gcom.atendimentopublico.ligacaoesgoto;


import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Leandro Cavalcanti
 */
public class FiltroLigacaoMaterialEsgoto extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroLigacaoMaterialEsgoto() {
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

	public FiltroLigacaoMaterialEsgoto(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
