package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCobrancaDocumentoItem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroCobrancaDocumentoItem() {
    }

    public FiltroCobrancaDocumentoItem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    public final static String COBRANCA_DOCUMENTO_ID = "cobrancaDocumento.id";
    public final static String DEBITO_A_COBRAR_GERAL_ID = "debitoACobrarGeral.id";
    public final static String CONTA_GERAL_ID = "contaGeral.id";
    public final static String PRESTACAO_CONTRATO_PARCELAMENTO_ID = "prestacaoContratoParcelamento.contratoParcelamento.id";
	
}
