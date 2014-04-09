package gcom.atendimentopublico.ligacaoesgoto;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoEsgotoSituacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoEsgotoSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoEsgotoSituacao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String DESCRICAOABREVIADA = "descricaoAbreviado";
    
    public final static String INDICADORFATURAMENTOSITUACAO = "indicadorFaturamentoSituacao";
    
    public final static String INDICADOREXISTENCIAREDE = "indicadorExistenciaRede";
    
    public final static String INDICADOREXISTENCIALIGACAO = "indicadorExistenciaLigacao";
    
    public final static String INDICADORUSO = "indicadorUso";
    
    public final static String VOLUMEMINIMOFATURAMENTO = "volumeMinimoFaturamento";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

}
