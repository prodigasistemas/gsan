package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMotivoInclusaoConta extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroMotivoInclusaoConta() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "cmicId";

    /**
     * Description of the Field
     */
    public final static String MOTIVO_INCLUSAO_CONTA = "descricaoMotivoInclusaoConta";
    
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
    public FiltroMotivoInclusaoConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

