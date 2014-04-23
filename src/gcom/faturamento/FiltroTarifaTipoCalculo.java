package gcom.faturamento;


import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 */
public class FiltroTarifaTipoCalculo extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroTarifaTipoCalculo() {
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricaoTarifaTipoCalculo";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";

	public FiltroTarifaTipoCalculo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
