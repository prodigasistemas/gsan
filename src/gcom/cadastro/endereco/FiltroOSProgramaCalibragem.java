package gcom.cadastro.endereco;

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
 * @author not attributable
 * @version 1.0
 */

public class FiltroOSProgramaCalibragem extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ID = "id";

	public static final String PRIORIZACAO_TIPO_ID = "priorizacaoTipo.id";

	public static final String NUMERO_GRAU_IMPORTANCIA = "grauImportancia";

	public static final String FAIXA_INICIAL = "faixaInicial";

	public static final String FAIXA_FINAL = "faixaFinal";

	public static final String PESO = "peso";

	public static final String FATOR = "fator";

	public FiltroOSProgramaCalibragem() {
	}

	public FiltroOSProgramaCalibragem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
