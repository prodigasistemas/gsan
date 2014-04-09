package gcom.seguranca.acesso;

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
 * @author Thiago Toscano
 * @date 03/05/2006
 * @version 1.0
 */

public class FiltroGrupo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    public FiltroGrupo() {
    }
    
    public FiltroGrupo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada"; 

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
    
    public final static String NUM_DIAS_EXPIRACAO = "numDiasExpiracaoSenha";
    
    public final static String  MENSAGEM = "mensagem";
    
	public final static String INDICADOR_SUPERINTENDENCIA = "indicadorSuperintendencia";
	
}
