package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMotivoRetificacaoConta extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroMotivoRetificacaoConta() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO_MOTIVO_RETIFICACAO_CONTA = "descricao";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroMotivoRetificacaoConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}


