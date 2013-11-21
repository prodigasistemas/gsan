package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaMotivoRevisao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroContaMotivoRevisao
     */
    public FiltroContaMotivoRevisao() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoMotivoRevisaoConta";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
  
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaMotivoRevisao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

