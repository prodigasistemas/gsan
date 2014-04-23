package gcom.faturamento;

import gcom.util.filtro.Filtro;

public class FiltroFaturamentoSituacaoHistorico extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroFaturamentoSituacaoHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroFaturamentoSituacaoHistorico() {}
	
	
	public final static String ANO_MES_FATURAMENTO_RETIRADA = "anoMesFaturamentoRetirada";
	
	public final static String ID_IMOVEL = "imovel.id";
	
	public final static String ID = "id";
	
	public final static String FATURAMENTO_MOTIVO = "faturamentoSituacaoMotivo";
	
	public final static String FATURAMENTO_TIPO = "faturamentoSituacaoTipo";
	
	public final static String FATURAMENTO_RETIRA = "faturamentoSituacaoComandoRetirada";

	public final static String FATURAMENTO_COMANDO_INFORMA = "faturamentoSituacaoComandoInforma";

	public final static String FATURAMENTO_COMANDO_INFORMA_ID = "faturamentoSituacaoComandoInforma.id";

	public final static String IMOVEL = "imovel";

	public final static String USUARIO_INFORMA = "usuarioInforma";

	public final static String USUARIO_RETIRA = "usuarioRetira";
	
	public final static String USUARIO = "usuario";
	
	
}
