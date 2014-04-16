package gcom.faturamento.autoinfracao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAutosInfracao extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAutosInfracao() {}

	public FiltroAutosInfracao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String DATA_EMISSAO = "dataEmissao";
	
	public final static String DATA_INICIO_RECURSO = "dataInicioRecurso";
	
	public final static String DATA_TERMINO_RECURSO = "dataTerminoRecurso";
	
	public final static String ANO_MES_REFERENCIA_GERADA = "anoMesReferenciaGerada";
	
	public final static String OBSERVACAO = "observacao";
	
	public final static String IMOVEL = "imovel";
	
	public final static String IMOVEL_ID = "imovel.id";
	
	public final static String FUNCIONARIO = "funcionario";
	
	public final static String FUNCIONARIO_ID = "funcionario.id";
	
	public final static String ORDEM_SERVICO = "ordemServico";
	
	public final static String ORDEM_SERVICO_ID = "ordemServico.id";
	
	public final static String FISCALIZACAO_SITUACAO = "fiscalizacaoSituacao";
	
	public final static String FISCALIZACAO_SITUACAO_ID = "fiscalizacaoSituacao.id";
	
	public final static String AUTO_INFRACAO_SITUACAO = "autoInfracaoSituacao";
	
	public final static String AUTO_INFRACAO_SITUACAO_ID = "autoInfracaoSituacao.id";
	
	public final static String DEBITO_TIPO = "debitoTipo";
	
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	
	
	
	
	
	
	
}
