package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

public class FiltroContaHistorico extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroContaHistorico() {
	}

	public final static String ID = "id";

	public final static String IMOVEL_ID = "imovel.id";

	public final static String ANO_MES_REFERENCIA = "anoMesReferenciaConta";

	public final static String IMOVEL = "imovel";

	public final static String IMOVEL_PERFIL = "imovel.imovelPerfil";

	public final static String DATA_RETIFICACAO = "dataRetificacao";

	public final static String DEBITO_CREDITO_SITUACAO_ATUAL = "debitoCreditoSituacaoAtual";

	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID = "debitoCreditoSituacaoAtual.id";

	public final static String IMOVEL_COBRANCA_SITUACAO_TIPO = "imovel.cobrancaSituacaoTipo";

	public final static String IMOVEL_LIGACAO_AGUA = "imovel.ligacaoAgua";

	public final static String HIDROMETRO_INSTALACAO_HISTORICO = "imovel.hidrometroInstalacaoHistorico";

	public FiltroContaHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
