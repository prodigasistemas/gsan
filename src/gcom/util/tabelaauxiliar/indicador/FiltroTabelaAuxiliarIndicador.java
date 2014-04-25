package gcom.util.tabelaauxiliar.indicador;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author gcom
 *
 */
public class FiltroTabelaAuxiliarIndicador extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
	 /**
     * Construtor da classe FiltroTabelaAuxiliar
     */
    public FiltroTabelaAuxiliarIndicador() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTabelaAuxiliarIndicador(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
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
    public final static String INDICADOR_PESSOA_JURIDICA = "indicadorPessoaJuridica";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_BAIXA_RENDA = "indicadorBaixaRenda";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";

}
