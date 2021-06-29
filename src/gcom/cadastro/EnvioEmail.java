package gcom.cadastro;import gcom.seguranca.acesso.Funcionalidade;import java.io.Serializable;import java.util.Date;import org.apache.commons.lang.builder.ToStringBuilder;public class EnvioEmail implements Serializable {	private static final long serialVersionUID = 1L;		public final static Integer GERAR_MOVIMENTO_AUTOMATICO_BANCO = 1;	public final static Integer GERAR_MOVIMENTO_AUTOMATICO_BANCO_COM_ERRO = 2;	public final static Integer REGERAR_MOVIMENTO_AUTOMATICO_BANCO = 6;	public final static Integer REGERAR_MOVIMENTO_AUTOMATICO_BANCO_COM_ERRO = 7;	public final static Integer REGISTRAR_MOVIMENTO_ARRECADADORES = 3;	public final static Integer REGISTRAR_MOVIMENTO_ARRECADADORES_COM_ERRO = 4;	public final static Integer REGISTRAR_MOVIMENTO_ARRECADADORES_PDF = 5;	public final static Integer GERAR_DADOS_PARA_LEITURA_MICRO_COLETOR = 8;	public final static Integer REGISTRAR_LEITURAS_ANORMALIDADES = 9;	public final static Integer REGISTRAR_LEITURAS_ANORMALIDADES_COM_ERRO = 10;	public final static Integer GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA = 11;	public final static Integer GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA_FALHA = 12;	public final static Integer GERAR_RESUMO_ANORMALIDADE_CONSUMO = 13;	public final static Integer GERAR_RESUMO_ANORMALIDADE_CONSUMO_FALHA = 14;	public final static Integer GERAR_RESUMO_ANORMALIDADE_LEITURA = 15;	public final static Integer GERAR_RESUMO_ANORMALIDADE_LEITURA_FALHA = 16;	public final static Integer PESQUISAR_IDS_TODAS_CONTAS = 17;	public final static Integer PESQUISAR_IDS_TODAS_CONTAS_FALHA = 18;	public final static Integer CLASSIFICAR_PAGAMENTOS_DEVOLUCOES = 19;	public final static Integer CLASSIFICAR_PAGAMENTOS_DEVOLUCOES_FALHA = 20;	public final static Integer GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL = 21;	public final static Integer GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL_FALHA = 22;	public final static Integer ENCERRAR_ARRECADACAO_MES = 23;	public final static Integer ENCERRAR_ARRECADACAO_MES_FALHA = 24;	public final static Integer GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO = 25;	public final static Integer GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO_FALHA = 26;	public final static Integer DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA = 27;	public final static Integer DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_FALHA = 28;	public final static Integer GERAR_RESUMO_DEVEDORES_DUVIDOSOS = 29;	public final static Integer GERAR_RESUMO_DEVEDORES_DUVIDOSOS_FALHA = 30;	public final static Integer INSERIR_USUARIO = 31;	public final static Integer EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO = 32;	public final static Integer ENTRE_EM_CONTATO = 33;	public final static Integer GERAR_ARQUIVO_TEXTO_FATURAMENTO = 37;	public final static Integer GERAR_RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES = 35;	public final static Integer GERAR_RELATORIO_MAPA_CONTROLE_CONTA = 36;	public final static Integer AVISO_CONCLUSAO_BATCH_AVULSO = 34;	public final static Integer SPC_SERASA = 38;	public final static Integer SPC_SERASA_MOV_RETORNO = 39;	public final static Integer SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL_EMAIL = 40;	public final static Integer GERAR_INTEGRACAO_PARA_CONTABILIDADE = 41;	public final static Integer REGISTRAR_MOVIMENTO_CARTAO_CREDITO = 42;	public final static Integer REGISTRAR_MOVIMENTO_CARTAO_CREDITO_COM_ERRO = 43;	public final static Integer ENVIO_EMAIL_CONTA_PARA_CLIENTE = 44;	public final static Integer INSERIR_CADASTRO_EMAIL_CLIENTE = 45;	public final static Integer INSERIR_SOLICITACAO_ACESSO = 46;	public final static Integer RETIRAR_IMOVEL_TARIFA_SOCIAL = 47;	public final static Integer GERAR_TXT_OS_PRESTADORA_SERVICO = 48;	public final static Integer PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = 49;	public final static Integer PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA_ERRO = 50;	public final static Integer ENVIAR_QUESTIONARIO_SATISFACAO_CLIENTE = 51;	public final static Integer GERAR_TXT_OS_CONTAS_PAGAS_PARCELADAS = 52;	public final static Integer GERAR_TXT_OS_CONTAS_PAGAS_PARCELADAS_SEM_DADOS = 53;	public final static Integer COBRANCA_EMPRESA = 54;	public final static Integer ENVIO_EMAIL_VENCIMENTO = 55;	private Integer id;	private String emailRemetente;	private String emailReceptor;	private String tituloMensagem;	private String corpoMensagem;	private Date ultimaAlteracao;	private Funcionalidade funcionalidade;	public EnvioEmail(Integer id, String emailRemetente, String emailReceptor, String tituloMensagem, String corpoMensagem, Date ultimaAlteracao, Funcionalidade funcionalidade) {		this.id = id;		this.emailRemetente = emailRemetente;		this.emailReceptor = emailReceptor;		this.tituloMensagem = tituloMensagem;		this.corpoMensagem = corpoMensagem;		this.ultimaAlteracao = ultimaAlteracao;		this.funcionalidade = funcionalidade;	}	public EnvioEmail() {}	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public String getEmailRemetente() {		return emailRemetente;	}	public void setEmailRemetente(String emailRemetente) {		this.emailRemetente = emailRemetente;	}	public String getEmailReceptor() {		return emailReceptor;	}	public void setEmailReceptor(String emailReceptor) {		this.emailReceptor = emailReceptor;	}	public String getTituloMensagem() {		return tituloMensagem;	}	public void setTituloMensagem(String tituloMensagem) {		this.tituloMensagem = tituloMensagem;	}	public String getCorpoMensagem() {		return corpoMensagem;	}	public void setCorpoMensagem(String corpoMensagem) {		this.corpoMensagem = corpoMensagem;	}	public Date getUltimaAlteracao() {		return ultimaAlteracao;	}	public void setUltimaAlteracao(Date ultimaAlteracao) {		this.ultimaAlteracao = ultimaAlteracao;	}	public Funcionalidade getFuncionalidade() {		return funcionalidade;	}	public void setFuncionalidade(Funcionalidade funcionalidade) {		this.funcionalidade = funcionalidade;	}	public String toString() {		return new ToStringBuilder(this).append("id", getId()).toString();	}}