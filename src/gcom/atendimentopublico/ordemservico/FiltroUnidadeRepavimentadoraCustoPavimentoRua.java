package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Hugo Leonardo
 * @date 21/12/2010
 */
public class FiltroUnidadeRepavimentadoraCustoPavimentoRua extends Filtro {
	private static final long serialVersionUID = 1L;

	public FiltroUnidadeRepavimentadoraCustoPavimentoRua() {
    }

	/**
	 * Constructor for the FiltroUnidadeRepavimentadoraCustoPavimentoRua
	 * 
	 * @param campoOrderBy
	 */
	public FiltroUnidadeRepavimentadoraCustoPavimentoRua(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	public final static String VALOR_PAVIMENTO = "valorPavimento";

	public final static String UNIDADE_REPAVIMENTADORA_ID = "unidadeRepavimentadora.id";

	public final static String UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora";
	
	public final static String PAVIMENTO_RUA_ID = "pavimentoRua.id";

	public final static String PAVIMENTO_RUA = "pavimentoRua";

    public final static String DATA_VIGENCIA_INICIAL = "dataVigenciaInicial";
    
    public final static String DATA_VIGENCIA_FINAL = "dataVigenciaFinal";

	public final static String ULTIMAALTERACAO = "ultimaAlteracao";

}
