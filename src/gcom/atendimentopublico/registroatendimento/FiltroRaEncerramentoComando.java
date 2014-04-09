package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * Filtro para Ra Encerramento Comando
 * @author  Rafael Corrêa
 * @created 28/01/2008 
 */
public class FiltroRaEncerramentoComando extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String UNIDADE_ATENDIMENTO_ID = "unidadeOrganizacionalAtendimento.id";

    /**
     * Description of the Field
     */
    public final static String UNIDADE_ATUAL_ID = "unidadeOrganizacionalAtual.id";
    
    /**
     * Description of the Field
     */
    public final static String UNIDADE_SUPERIOR_ID = "unidadeOrganizacionalSuperior.id";

    /**
     * Description of the Field
     */
    public final static String DATA_ATENDIMENTO_INICIAL = "dataAtendimentoInicial";

    /**
     * Description of the Field
     */
    public final static String DATA_ATENDIMENTO_FINAL = "dataAtendimentoFinal";
    
    /**
     * Description of the Field
     */
    public final static String TEMPO_REALIZACAO = "tempoRealizacao";

    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroRaEncerramentoComando() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroRaEncerramentoComando(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
