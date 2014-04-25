package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Tipo Prioridade
 * 
 * @author Leandro Cavalcanti
 * @since 02/08/2006
 */
public class FiltroPerfilServico extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroPerfilServico() {
    }
    
    public FiltroPerfilServico(String campoOrderBy) {
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
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}


