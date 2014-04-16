package gcom.atendimentopublico;

import gcom.util.filtro.Filtro;

public class FiltroEspecificacaoPavimentacaoServicoTipo extends Filtro{
	
	private static final long serialVersionUID = 1L;
	
	public FiltroEspecificacaoPavimentacaoServicoTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroEspecificacaoPavimentacaoServicoTipo() {
	
	}
	
	public final static String ID = "id";
	public final static String LOCALOCORRENCIA = "localOcorrencia";
	public final static String PAVIMENTOCALCADA = "pavimentoCalcada";
	public final static String PAVIMENTORUA = "pavimentoRua";
	public final static String SERVICOTIPO = "servicoTipo";
	public final static String LOCALOCORRENCIA_ID = "localOcorrencia.id";
	public final static String PAVIMENTOCALCADA_ID = "pavimentoCalcada.id";
	public final static String PAVIMENTORUA_ID = "pavimentoRua.id";
	public final static String SERVICOTIPO_ID = "servicoTipo.id";
	public final static String SOLICITACAOTIPOESPECIFICACAO = "solicitacaoTipoEspecificacao";
	public final static String SOLICITACAOTIPOESPECIFICACAO_ID = "solicitacaoTipoEspecificacao.id";
}
