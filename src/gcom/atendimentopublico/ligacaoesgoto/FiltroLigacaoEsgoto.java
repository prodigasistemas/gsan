package gcom.atendimentopublico.ligacaoesgoto;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoEsgoto extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoEsgoto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoEsgoto() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String  LIGACAO_ESGOTO_DIAMETRO = "ligacaoEsgotoDiametro";
    
    public final static String  LIGACAO_ESGOTO_MATERIAL = "ligacaoEsgotoMaterial";
    
    public final static String  LIGACAO_ESGOTO_PERFIL = "ligacaoEsgotoPerfil";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_EXISTENCIA_REDE = "indicadorExistenciaRede";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_EXISTENCIA_LIGACAO = "indicadorExistenciaLigacao";

}
