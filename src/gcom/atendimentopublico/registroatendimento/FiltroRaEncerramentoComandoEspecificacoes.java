package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * Filtro para Ra Encerramento Comando
 * @author  Rafael Corrêa
 * @created 28/01/2008 
 */
public class FiltroRaEncerramentoComandoEspecificacoes extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String RA_ENCERRAMENTO_COMANDO_ID = "raEncerramentoComando.id";

    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroRaEncerramentoComandoEspecificacoes() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroRaEncerramentoComandoEspecificacoes(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
