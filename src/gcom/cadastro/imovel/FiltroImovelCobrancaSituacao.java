package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroImovelCobrancaSituacao extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroImovelCobrancaSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroImovelCobrancaSituacao() {}

	public final static String IMOVEL_ID = "imovel.id";

	public final static String ID = "id";

	public final static String DATA_RETIRADA_COBRANCA = "dataRetiradaCobranca";

	public final static String DATA_IMPLANTACAO_COBRANCA = "dataImplantacaoCobranca";

	public final static String ID_COBRANCA_SITUACAO = "cobrancaSituacao.id";
}
