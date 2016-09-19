package gcom.cobranca;

import gcom.util.filtro.Filtro;

public class FiltroResolucaoDiretoria extends Filtro {
	private static final long serialVersionUID = 1L;

	public final static String CODIGO = "id";

	public final static String NUMERO = "numeroResolucaoDiretoria";

	public final static String DESCRICAO = "descricaoAssunto";

	public final static String DATA_VIGENCIA_INICIO = "dataVigenciaInicio";

	public final static String DATA_VIGENCIA_FIM = "dataVigenciaFim";

	public final static String INDICADOR_PARCELAMENTO_UNICO = "indicadorParcelamentoUnico";

	public final static String INDICADOR_UTILIZACAO_LIVRE = "indicadorUtilizacaoLivre";
	
	public final static String INDICADOR_DESCONTO_FAIXA_REFERENCIA_CONTA = "indicadorDescontoFaixaReferenciaConta";

	public final static String INDICADOR_DESCONTOS_SANCOES = "indicadorDescontoSancoes";

	public final static String INDICADOR_PARCELAS_EM_ATRASO = "indicadorParcelasEmAtraso";

	public final static String ID_RD_PARCELAS_EM_ATRASO = "idParcelasEmAtraso";
	
	public final static String INDICADOR_PARCELAMENTO_EM_ANDAMENTO = "indicadorParcelamentoEmAndamento";
	
	public final static String ID_RD_PARCELAMENTO_EM_ANDAMENTO = "idParcelamentoEmAndamento";

	public final static String INDICADOR_NEGOCIACAO_SO_A_VISTA = "indicadorNegociacaoSoAVista";
	
	public final static String INDICADOR_DESCONTO_SO_EM_CONTA_A_VISTA = "indicadorDescontoSoEmContaAVista";
	
	public final static String INDICADOR_PARCELAMENTO_LOJA_VIRTUAL = "indicadorParcelamentoLojaVirtual";

	public FiltroResolucaoDiretoria() {
	}

	public FiltroResolucaoDiretoria(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
