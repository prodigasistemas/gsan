package gcom.cobranca.cobrancaporresultado;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroNegociacaoCobrancaEmpresa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroNegociacaoCobrancaEmpresa() {
	}

	public FiltroNegociacaoCobrancaEmpresa(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String PARCELAMENTO = "parcelamento";
	public final static String COBRANCA_DOCUMENTO = "cobrancaDocumento";
	public final static String GUIA_PAGAMENTO_GERAL = "guiaPagamentoGeral";
	public final static String EMPRESA = "empresa";
	public final static String CONTAS_NEGOCIADAS = "contasNegociadas";

}
