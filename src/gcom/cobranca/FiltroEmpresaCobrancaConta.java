package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEmpresaCobrancaConta extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroEmpresaCobrancaConta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroEmpresaCobrancaConta() {
	}

	public final static String ID = "id";

	public final static String EMPRESA = "empresa";

	public final static String EMPRESA_ID = "empresa.id";

	public final static String CONTA = "contaGeral";

	public final static String CONTA_NAO_HISTORICO = "contaGeral.conta";

	public final static String CONTA_NAO_HISTORICO_DC_SITUACAO_ATUAL = "contaGeral.conta.debitoCreditoSituacaoAtual";

	public final static String CONTA_HISTORICO_DC_SITUACAO_ATUAL = "contaGeral.contaHistorico.debitoCreditoSituacaoAtual";

	public final static String CONTA_HISTORICO = "contaGeral.contaHistorico";

	public final static String ORDEM_SERVICO = "ordemServico";

	public final static String ORDEM_SERVICO_IMOVEL = "ordemServico.imovel";

	public final static String ORDEM_SERVICO_SERVICO_TIPO = "ordemServico.servicoTipo";

	public final static String ORDEM_SERVICO_SERVICO_TIPO_ID = "ordemServico.servicoTipo.id";

	public final static String ORDEM_SERVICO_MOTIVO_ENCERRAMENTO = "ordemServico.atendimentoMotivoEncerramento";

	public final static String ORDEM_SERVICO_IMOVEL_ID = "ordemServico.imovel.id";

	public final static String CONTA_ID = "contaGeral.id";

	public final static String IMOVEL = "imovel";

	public final static String IMOVEL_ID = "imovel.id";

	public final static String COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comandoEmpresaCobrancaConta.id";
	
	public final static String DATA_ENVIO = "dataEnvio";
	
	public final static String DATA_RETIRADA = "dataRetirada";
}