package gcom.arrecadacao.debitoautomatico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um debito automatico retorno codigo
 * 
 * @author Raphael Rossiter
 * @date 21/03/2006
 */

public class FiltroDebitoAutomaticoRetornoCodigo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String COD_BANCO = "codigoBanco";
	
	public static final String INDICADOR_USO = "indicadorUso";

	
	public FiltroDebitoAutomaticoRetornoCodigo() {}

	
	public FiltroDebitoAutomaticoRetornoCodigo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
}

