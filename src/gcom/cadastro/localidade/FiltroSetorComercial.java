package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroSetorComercial extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroSetorComercial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroSetorComercial() {
    }

    public final static String CODIGO_SETOR_COMERCIAL = "codigo";

    public final static String DESCRICAO = "descricao";

    public final static String DESCRICAO_MUNICIPIO = "municipio.nome";

    public final static String ID_LOCALIDADE = "localidade.id";

    public final static String DESCRICAO_LOCALIDADE = "localidade.descricao";

    public final static String ID_MUNICIPIO = "municipio.id";

    public final static String INDICADOR_SETOR_ALTERNATIVO = "indicadorSetorAlternativo";
    
    public final static String MUNICIPIO = "municipio";
    
    public final static String LOCALIDADE = "localidade";
    
    public final static String LOCALIDADE_ID = "localidade.id";
}
