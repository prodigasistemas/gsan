package gcom.atendimentopublico.ligacaoagua;


import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Leandro Cavalcanti
 */
public class FiltroPerfilLigacao extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroPerfilLigacao() {
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

	public FiltroPerfilLigacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
