package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroNegativador extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public FiltroNegativador() {
    }

    public FiltroNegativador(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String NEGATIVADOR_CLIENTE = "cliente.id";
    
    public final static String CLIENTE = "cliente.nome";
    
    public final static String CODIGO_AGENTE = "codigoAgente";
    
    public final static String INSCRICAO_ESTADUAL = "numeroInscricaoEstadual";
    
    public final static String NEGATIVADOR_IMOVEL = "imovel.id";
    
    public final static String INDICADOR_USO = "indicadorUso";
}
