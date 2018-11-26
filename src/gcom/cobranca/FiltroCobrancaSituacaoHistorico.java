package gcom.cobranca;

import gcom.util.filtro.Filtro;

public class FiltroCobrancaSituacaoHistorico extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroCobrancaSituacaoHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroCobrancaSituacaoHistorico() {}


	public final static String ID = "id";

	public final static String IMOVEL = "imovel";

	public final static String IMOVEL_ID = "imovel.id";

	public final static String COBRANCA_TIPO = "cobrancaSituacaoTipo";
	
	public final static String COBRANCA_TIPO_ID = "cobrancaSituacaoTipo.id";

	public final static String ANO_MES_COBRANCA_RETIRADA = "anoMesCobrancaRetirada";
	
	public final static String COBRANCA_SITUACAO_MOTIVO = "cobrancaSituacaoMotivo";
	
	public final static String COBRANCA_SITUACAO_MOTIVO_ID = "cobrancaSituacaoMotivo.id";
}
