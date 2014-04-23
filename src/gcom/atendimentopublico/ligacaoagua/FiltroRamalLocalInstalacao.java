package gcom.atendimentopublico.ligacaoagua;



import gcom.util.filtro.Filtro;

public class FiltroRamalLocalInstalacao extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroRamalLocalInstalacao() {
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

	public FiltroRamalLocalInstalacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}

