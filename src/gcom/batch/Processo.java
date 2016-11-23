package gcom.batch;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Processo extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	public static final int FATURAR_GRUPO_FATURAMENTO = 2;
	public static final int GERAR_RELATORIO_MANTER_BAIRRO = 4;
	public static final int GERAR_RELATORIO_MANTER_SETOR_COMERCIAL = 7;
	public static final int GERAR_RELATORIO_MANTER_LOCALIDADE = 8;
	public static final int GERAR_RELATORIO_MANTER_LOGRADOURO = 9;
	public static final int GERAR_RELATORIO_MANTER_QUADRA = 10;
	public static final int GERAR_RELATORIO_MANTER_IMOVEL = 11;
	public static final int GERAR_RELATORIO_MANTER_TARIFA_SOCIAL = 12;
	public static final int GERAR_RELATORIO_MANTER_SUBCATEGORIA = 13;
	public static final int GERAR_RELATORIO_MANTER_ROTA = 14;
	public static final int GERAR_RELATORIO_MANTER_CATEGORIA = 15;
	public static final int GERAR_RELATORIO_MANTER_CRONOGRAMA_FATURAMENTO = 16;
	public static final int GERAR_RELATORIO_MANTER_CRONOGRAMA_COBRANCA = 17;
	public static final int GERAR_RELATORIO_MANTER_CRITERIO_COBRANCA = 18;
	public static final int GERAR_RELATORIO_MANTER_PERFIL_PARCELAMENTO = 19;
	public static final int GERAR_RELATORIO_MANTER_MOVIMENTO_ARRECADADOR = 20;
	public static final int GERAR_RELATORIO_MANTER_MENSAGEM_CONTA = 21;
	public static final int GERAR_RELATORIO_MANTER_AVISO_BANCARIO = 22;
	public static final int GERAR_RELATORIO_MANTER_GUIA_DEVOLUCAO = 23;
	public static final int GERAR_RELATORIO_MANTER_IMOVEL_OUTROS_CRITERIOS = 24;
	public static final int GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL = 25;
	public static final int GERAR_RELATORIO_DADOS_TARIFA_SOCIAL = 26;
	public static final int GERAR_RELATORIO_MANTER_HIDROMETRO = 27;
	public static final int GERAR_RELATORIO_MANTER_RESOLUCAO_DIRETORIA = 28;
	public static final int GERAR_RELATORIO_RESUMO_ARRECADACAO = 29;
	public static final int GERAR_RELATORIO_GERAR_RELACAO_DEBITOS = 30;
	public static final int GERAR_RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO = 31;
	public static final int GERAR_RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL = 32;
	public static final int GERAR_RELATORIO_DEVOLUCAO = 33;
	public static final int GERAR_RELATORIO_PAGAMENTO = 34;
	public static final int GERAR_RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO = 35;
	public static final int GERAR_RELATORIO_SEGUNDA_VIA_CONTA = 36;
	public static final int GERAR_RELATORIO_PARCELAMENTO = 37;
	public static final int GERAR_RELATORIO_EXTRATO_DEBITO = 38;
	public static final int GERAR_RELATORIO_EMITIR_GUIA_PAGAMENTO = 39;
	public static final int GERAR_RELATORIO_GUIA_DEVOLUCAO = 40;
	public static final int GERAR_RELATORIO_ROTEIRO_PROGRAMACAO = 41;
	public static final int GERAR_RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE = 42;
	public static final int GERAR_RELATORIO_ORDEM_SERVICO = 43;
	public static final int GERAR_RELATORIO_CONSULTAR_OPERACAO = 44;
	public static final int GERAR_RELATORIO_NUMERACAO_RA_MANUAL = 45;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS = 46;
	public static final int GERAR_RELATORIO_MANTER_CLIENTE = 47;
	public static final int GERAR_RELATORIO_MOVIMENTO_ARRECADADOR = 48;
	public static final int ENCERRAR_ARRECADACAO_MES = 50;
	public static final int GERAR_RELATORIO_MAPA_CONTROLE_CONTA = 59;
	public static final int GERAR_RELATORIO_COMPARATIVO_LEITURAS_E_ANORMALIDADE = 60;
	public static final int GERAR_RELATORIO_SEGUNDA_VIA_CONTA_TIPO_2 = 62;
	public static final int GERAR_RELATORIO_CLIENTES_ESPECIAIS = 73;
	public static final int GERAR_RELATORIO_IMOVEL_ENDERECO = 75;	
	public static final int GERAR_RESUMO_DEVEDORES_DUVIDOSOS = 79;
	public static final int GERAR_RELATORIO_HISTOGRAMA_AGUA_ECONOMIA = 81;
	public static final int GERAR_RELATORIO_HISTOGRAMA_AGUA_LIGACAO = 82;
	public static final int GERAR_RELATORIO_CONTA_TIPO_2 = 83;
	public static final int GERAR_RELATORIO_ORDEM_CORTE = 85;
	public static final int GERAR_RELATORIO_FATURA_CLIENTE_RESPONSAVEL = 87;
	public static final int GERAR_BOLETIM_CADASTRO = 90;
	public static final int GERAR_RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO = 91;
	public static final int GERAR_RELATORIO_VOLUMES_FATURADOS = 92;
	public static final int GERAR_RELATORIO_VOLUMES_FATURADOS_RESUMIDO = 93;
	public static final int GERAR_RELATORIO_CONTAS_EM_REVISAO = 95;
	public static final int GERAR_RELATORIO_CONTAS_EM_REVISAO_RESUMIDO = 96;
	public static final int GERAR_CURVA_ABC_DEBITOS = 97;
	public static final int GERAR_RELATORIO_ANORMALIDADE_CONSUMO = 99;
	public static final int GERAR_RELATORIO_HISTOGRAMA_ESGOTO_LIGACAO = 100;
	public static final int GERAR_RELATORIO_HISTOGRAMA_ESGOTO_ECONOMIA = 101;
	public static final int GERAR_EMITIR_ORDEM_SERVICO_SELETIVA = 102;
	public static final int GERAR_RELATORIO_ORCAMENTO_SINP = 103;
	public static final int GERAR_RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA = 106;
	public static final int GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADA_LOCALIZACAO = 107;
	public static final int GERAR_RELATORIO_IMOVEIS_CONSUMO_MEDIO = 111;
	public static final int GERAR_RELATORIO_IMOVEIS_ULTIMOS_CONSUMOS_AGUA = 114;
	public static final int GERAR_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO = 115;
	public static final int GERAR_RELATORIO_IMOVEIS_TIPO_CONSUMO = 117;
	public static final int GERAR_RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS = 118;
	public static final int GERAR_RELATORIO_ORDEM_FISCALIZACAO = 119;
	public static final int GERAR_RELATORIO_IMOVEIS_FATURAS_RECENTES_DIA_FATURAS_ANTIGAS_ATRASO = 121;
	public static final int GERAR_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO = 122;
	public static final int GERAR_RELATORIO_BOLETIM_CADASTRO = 123;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS = 133;
    public static final int GERAR_TXT_CONTAS_BAIXADAS_CONTABILMENTE = 134;
    public static final int GERAR_RESUMO_DIARIO_NEGATIVACAO = 135;
    public static final int GERAR_RELATORIO_AVISO_ANORMALIDADE = 140;
    public static final int GERAR_RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA = 141;
    public static final int GERAR_RELATORIO_GESTAO_SERVICOS_UPA = 143;
    public static final int GERAR_RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS = 144;
    public static final int RELATORIO_ANALISE_CONSUMO = 146;
    public static final int ATUALIZAR_DIFERENCA_ACUMULADA_NO_MES = 147;
    public static final int RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO = 148;
    public static final int GERAR_RELATORIO_DADOS_LEITURA = 149;
    public static final int GERAR_RELATORIO_FATURAS_AGRUPADAS = 150;
    public static final int GERAR_RELATORIO_ROTAS_ONLINE_POR_EMPRESA = 155;
    public static final int GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_EMPRESA = 156;
    public static final int GERAR_MOVIMENTO_RETORNO_NEGATIVACAO = 159;
    public static final int GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA = 160;
    public static final int GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA_SINTETICO = 161;
    public static final int GERAR_RELATORIO_CONTAS_CANCELADAS = 163;
    public static final int GERAR_RELATORIO_CONTAS_RETIFICADAS = 164;
    public static final int GERAR_RELATORIO_ACOMPANHAMENTO_FATURAMENTO = 165;
    public static final int GERAR_RELATORIO_IMOVEIS_COM_ACORDO = 169;
    public static final int GERAR_RELATORIO_RESUMO_DISTRITO_OPERACIONAL = 170;
    public static final int GERAR_CARTAS_CAMPANHA_SOLID_CRIANCA = 173;
    public static final int GERAR_RELATORIO_LEITURA_CONSULTAR_ARQUIVOS_TEXTOS = 174;
    public static final int EMITIR_CARTAS_CAMPANHA_SOLID_CRIANCA = 175;
    public static final int GERAR_TABELAS_TEMP_ATU_CADASTRAL = 177;
    public static final int GERAR_ARQUIVO_TEXTO_ATU_CADASTRAL = 178;
    public static final int GERAR_RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL = 179;
    public static final int GERAR_DIFERENCA_ARQUIVO_TEXTO_ATU_CADASTRAL = 180;
    public static final int GERAR_MOVIMENTO_HIDROMETRO = 176;
    public static final int ATUALIZAR_CONJUNTO_HIDROMETRO = 172;
    public static final int EMITIR_BOLETOS = 181;
    public static final int RETIFICAR_CONJUNTO_CONTAS_CONSUMO = 182;
	public static final int GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITO_CLIENTE = 183;
	public static final int GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADA_CLIENTE = 184;
	public static final int GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA = 186;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_LEITURISTA = 188;
	public static final int GERAR_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS = 187;
	public static final int GERAR_RELATORIO_ANORMALIDADE_LEITURA_PERIODO = 189;
	public static final int GERAR_CARTAS_DE_FINAL_DE_ANO = 191;
	public static final int EMITIR_CARTAS_DE_FINAL_DE_ANO = 192;
	public static final int GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS = 194;
	public static final int INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS = 195;
	public static final int RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO = 198;
	public static final int RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_SINTETICO = 199;
	public static final int RELATORIO_DOCUMENTOS_A_RECEBER = 204;
	public static final int RELATORIO_COLETA_MEDIDOR_ENERGIA = 206;
	public static final int GERAR_RELATORIO_PAGAMENTO_ENTIDADES_BENEFIC = 211;
	public static final int RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO = 212;
	public static final int GERAR_DECLARACAO_QUITACAO_DEBITOS = 208;
	public static final int RELATORIO_RESUMO_DADOS_CAS = 216;
	public static final int RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO = 231;
	public static final int RELATORIO_ACESSOS_POR_USUARIO = 232;
	public static final int RELATORIO_BOLETIM_MEDICAO = 233;
	public static final int GERAR_RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM = 234;
	public static final int RELATORIO_ALTERACOES_SISTEMA_COLUNA = 236;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO = 237;
	public static final int GERAR_RELATORIO_ATUALIZACAO_CADASTRAL_INTERNET = 238;
	public static final int PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO = 239;
	public static final int PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL = 240;
	public static final int ENCERRAR_ORDEM_SERVICO_ACAO_COBRANCA_MDB = 242;
	public static final int GERAR_RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH = 245;
	public static final int RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES = 246;
	public static final int RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT = 247;
	public static final int RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE = 249;
	public static final int PROCESSAR_CARTA_TARIFA_SOCIAL_GERAR = 250;
	public static final int PROCESSAR_CARTA_TARIFA_SOCIAL_SIMULAR = 251;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO = 252;
	public static final int RETIRAR_IMOVEL_TARIFA_SOCIAL = 253;
	public static final int ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA = 255;
	public static final int GERAR_TXT_OS_INSPECAO_ANORMALIDADE = 258;
	public static final int PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = 257;
	public static final int GERAR_RELATORIO_IMOVEIS_COM_DOACOES = 260;
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO = 261;
	public static final int ENCERRAR_COMANDO_OS_SELETIVA_INSP_ANORM = 262;
	public static final int GERAR_RELATORIO_ORDENS_SERVICO_FISCALIZACAO_ANALITICO = 265;
	public static final int GERAR_RELATORIO_ORDENS_SERVICO_FISCALIZACAO_SINTETICO = 266;
	public static final int GERAR_RELATORIO_TRANSFERENCIA_PAGAMENTO = 267;
	public static final int GERAR_RELATORIO_DOCUMENTO_VISITA_COBRANCA = 268;
	public static final int GERAR_RELATORIO_DEBITO = 1000;
	public static final int GERAR_RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO = 1001;
	public static final int ATUALIZACAO_CADASTRAL = 507;
	
	private Integer id;
	private String descricaoProcesso;
	private String descricaoAbreviada;
	private short indicadorUso;
	private short indicadorAutorizacao;
	private Date ultimaAlteracao;
	private String nomeArquivoBatch;

	@SuppressWarnings("rawtypes")
	private Set processosFuncionalidade;
	
	@SuppressWarnings("rawtypes")
	private Set processosIniciados;

	private ProcessoTipo processoTipo;
	
	@SuppressWarnings("rawtypes")
	public Processo(String descricaoProcesso, String descricaoAbreviada,
			short indicadorUso, Date ultimaAlteracao,
			ProcessoTipo processoTipo, Set processosFuncionalidade,
			Set processosIniciados) {
		this.descricaoProcesso = descricaoProcesso;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.processosFuncionalidade = processosFuncionalidade;
		this.processosIniciados = processosIniciados;
		this.processoTipo = processoTipo;
	}

	public Processo() {
	}

	@SuppressWarnings("rawtypes")
	public Processo(short indicadorUso, ProcessoTipo processoTipo,
			Set processosFuncionalidade, Set processosIniciados) {
		this.indicadorUso = indicadorUso;
		this.processosFuncionalidade = processosFuncionalidade;
		this.processosIniciados = processosIniciados;
		this.processoTipo = processoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoProcesso() {
		return this.descricaoProcesso;
	}

	public void setDescricaoProcesso(String descricaoProcesso) {
		this.descricaoProcesso = descricaoProcesso;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@SuppressWarnings("rawtypes")
	public Set getProcessosFuncionalidade() {
		return this.processosFuncionalidade;
	}

	@SuppressWarnings("rawtypes")
	public void setProcessosFuncionalidade(Set processosFuncionalidade) {
		this.processosFuncionalidade = processosFuncionalidade;
	}

	@SuppressWarnings("rawtypes")
	public Set getProcessosIniciados() {
		return this.processosIniciados;
	}

	@SuppressWarnings("rawtypes")
	public void setProcessosIniciados(Set processosIniciados) {
		this.processosIniciados = processosIniciados;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public ProcessoTipo getProcessoTipo() {
		return processoTipo;
	}

	public void setProcessoTipo(ProcessoTipo processoTipo) {
		this.processoTipo = processoTipo;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public short getIndicadorAutorizacao() {
		return indicadorAutorizacao;
	}

	public void setIndicadorAutorizacao(short indicadorAutorizacao) {
		this.indicadorAutorizacao = indicadorAutorizacao;
	}

	public String getNomeArquivoBatch() {
		return nomeArquivoBatch;
	}

	public void setNomeArquivoBatch(String nomeArquivoBatch) {
		this.nomeArquivoBatch = nomeArquivoBatch;
	}
}
