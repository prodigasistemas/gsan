package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * Descrição da classe 
 *
 * @author Thiago Tenório
 * @date 11/05/2006
 */
public class FiltroAbrangenciaUsuario extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    public FiltroAbrangenciaUsuario() {
    }
    
    public FiltroAbrangenciaUsuario(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String DESCRICAO = "descricao";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    public final static String ABRANGENCIA_SUPERIOR = "usuarioAbrangenciaSuperior";
    
    public final static String INDICADOR_USO = "indicadorUso";
}
