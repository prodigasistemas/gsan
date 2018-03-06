package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaConta extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroComandoEmpresaCobrancaConta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroComandoEmpresaCobrancaConta() {
	}

	public final static String ID = "id";

	public final static String EMPRESA = "empresa";
	public final static String EMPRESA_ID = "empresa.id";
	public final static String DATA_EXECUCAO = "dataExecucao";
	public final static String COBRANCA_SITUACAO = "cobrancaSituacao";
	public final static String COBRANCA_SITUACAO_ID = "cobrancaSituacao.id";
	public final static String DATA_FIM_CICLO = "dataFimCiclo";
	
}
