package gcom.atendimentopublico.ligacaoesgoto;



import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Leandro Cavalcanti
 */
public class FiltroDiametroLigacaoEsgoto extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroDiametroLigacaoEsgoto() {
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

	public FiltroDiametroLigacaoEsgoto(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}

