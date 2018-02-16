package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroParcelamento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroParcelamento() {
	}

	public FiltroParcelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String IMOVEL_ID = "imovel.id";

	public final static String COBRANCA_FORMA = "cobrancaForma.id";

	public final static String PARCELAMENTO_SITUACAO = "parcelamentoSituacao.id";

	public final static String FUNCIONARIO = "funcionario.id";

	public final static String ANO_MES_REFERENCIA_FATURAMENTO = "anoMesReferenciaFaturamento";

	public final static String PARCELAMENTO_PERFIL_ID = "parcelamentoPerfil.id";
}
