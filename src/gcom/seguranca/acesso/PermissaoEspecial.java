package gcom.seguranca.acesso;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class PermissaoEspecial extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public static final int IMOVEL_EM_SITUACAO_COBRANCA = 6;

	public static final int PARCELAR_SEM_INCLUIR_DEBITO_A_COBRAR = 14;

	public static final int PARCELAR_SEM_INCLUIR_ACRESCIMOS_POR_IMPONTUALIDADE = 18;

	public static final int USAR_PLANO_PAI_PARA_ORGAO_PUBLICO = 19;

	public static final int TESTAR_VAL_MINIMO_PRESTACAO = 21;

	public static final int TESTAR_VAL_MINIMO_ENTRADA = 22;

	public static final int CLIENTE_USUARIO_TARIFA_SOCIAL = 23;

	public static final int ALTERAR_IMOVEL = 24;

	public static final int REMOVER_CATEGORIA_NAO_RESIDENCIAL_IMOVEL = 25;

	public static final int ATUALIZAR_LOGRADOURO_BAIRRO = 28;

	public static final int INSERIR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR = 26;

	public static final int ATUALIZAR_IMOVEL_MUNICIPIO_LOGRADOURO_DIFERENTE_SETOR = 27;

	public static final int INSERIR_DEBITO_A_COBRAR_SEM_ENTRADA_SEM_JUROS = 29;

	public static final int INFORMAR_MOTIVO_NAO_COBRANCA = 30;

	public static final int GERACAO_DEBITO_OS_FISCALIZACAO = 31;

	public static final int INFORMAR_VENCIMENTO_ALTERNATIVO_NOVA_DATA = 32;

	public static final int PARCELAR_NAO_TESTAR_QTDE_DE_PRESTACAO = 33;

	public static final int INFORMAR_VENCIMENTO_ALTERNATIVO_ANTES_DO_PERIODO_VALIDO = 34;

	public static final int ALTERAR_VENCIMENTO_CONTA_SEM_RA = 35;

	public static final int INSERIR_CONTA_FATURAMENTO_ANTECIPADO = 36;

	public static final int INSERIR_DEBITO_A_COBRAR_SEM_RA = 37;

	public static final int INSERIR_GUIA_DE_PAGAMENTO_SEM_RA = 38;

	public static final int ALTERAR_NOME_CLIENTE = 39;

	public static final int INCLUIR_DEVOLUCAO_MAIOR_VALOR_MAXIMO = 40;

	public static final int INCLUIR_CREDITO_A_REALIZAR_VALOR_MAXIMO = 41;

	public static final int INCLUIR_CREDITO_A_REALIZAR_QUANTIDADE_PARCELAS_MAXIMO = 42;

	public static final int INCLUIR_ACRESCIMO_IMPONTUALIDADE_NO_EXTRATO_DE_DEBITOS_COM_DESCONTO = 43;

	public static final int RETIRAR_TAXA_COBRANCA_DO_EXTRATO_DE_DEBITOS = 44;

	public static final int CONSULTAR_DEBITOS_ATUAL_DO_IMOVEL_OU_TODOS = 45;

	public static final int INSERIR_DEBITO_A_COBRAR_IMOVEL_SITUACAO = 46;

	public static final int REINICIAR_BATCH = 47;

	public static final int RETIFICAR_CONTA_SEM_RA = 48;

	public static final int EXCLUIR_DEBITO_A_COBRAR = 49;

	public static final int GERAR_OS_SELETIVA_HIDROMETRO = 50;

	public static final int CANCELAR_CONTA_SEM_RA = 51;

	public static final int ATUALIZAR_DADOS_FISCALIZACAO = 52;

	public static final int DESFAZER_FISCALIZACAO_BOLETIM_OS_CONCLUIDA = 53;

	public static final int VISUALIZAR_DIA_VENCIMENTO_CLIENTE = 54;
    
    public static final int INSERIR_IMOVEL_COM_PERFIL_CORPORATIVO = 55;
    
    public static final int ALTERAR_PERFIL_CORPORATIVO_IMOVEL = 56;
    
    public static final int INFORMAR_DATA_ENC_OS_ANTERIOR_DATA_CORRENTE = 57;
    
    public static final int RETIFICAR_PARA_MENOR_CONTA_RETIFICADA = 58;
    
    public static final int RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO = 59;
    
    public static final int ALTERAR_VENCIMENTO_JA_ALTERADO = 60;
    
    public static final int ALTERAR_TARIFA_CONSUMO_RETIFICAR_CONTA = 61;
    
    public static final int ALTERAR_INDICADOR_USO_SISTEMA_TIPO_SOLICITACAO = 62;
    
    public static final int ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO = 64;
    
    public static final int INSERIR_IMOVEL_PARA_ORGAO_PUBLICO = 63;
    
    public static final int EFETUAR_LIGACAO_DE_AGUA_SEM_RA = 65;
    
    public static final int ATUALIZAR_LIGACAO_DE_AGUA_SEM_RA = 66;
    
    public static final int EFETUAR_LIGACAO_DE_ESGOTO_SEM_RA = 67;
    
    public static final int ATUALIZAR_LIGACAO_DE_ESGOTO_SEM_RA = 68;
    
    public static final int EFETUAR_LIGACAO_DE_AGUA_COM_INSTALACAO_DE_HIDROMETRO_SEM_RA = 69;
    
    public static final int ATUALIZAR_INSTALACAO_DO_HIDROMETRO = 70;
    
    public static final int ALTERAR_VALIDADE_EXTRATO_DEBITO = 71;
    
    public static final int VALIDAR_ACRESCIMOS_IMPONTUALIDADE = 72;
    
    public static final int EMITIR_2_VIA_SEM_DOCUMENTO_VALIDO = 73;
    
    public static final int RETIFICAR_CONJUNTO_CONTA = 74;
    
    public static final int EMITIR_CERTIDAO_NEGATIVA_COM_CLIENTE_SUPERIOR = 75;
    
    public static final int RETIRAR_CONTA_REVISAO_POR_ANTIGUIDADE = 78;

    public static final int ALTERAR_PERCENTUAL_COLETA_ESGOTO = 76;

    public static final int ATUALIZAR_RETIFICAR_CONTAS_PAGAS = 77;
    
    public static final int HABILITAR_BANCO_MANTER_CONTA = 80;
    
    public static final int EFETUAR_SUPRESSAO_DE_LIGACAO_AGUA = 79;
    
    public static final int ALTERAR_SITUACAO_LIGACAO_PARA_IMOVEL_COM_DEBITO = 81;
    
    public static final int GERAR_CERTIDAO_NEGATIVA_RESPONSABILIDAE_ATUAL_DO_IMOVEL = 82;
    
    public static final int RETIFICAR_CONTA_IMOVEL_PERFIL_BLOQUEADO  = 83;
    
    public static final int RETIFICAR_CONTA_APENAS_VOLUME_ESGOTO  = 84;
    
    public static final int EMITIR_DOCUMENTO_COBRANCA = 85;
    
    public static final int BLOQUEAR_ALTERACAO_IMOVEIS = 86;
    
    public static final int TRAMITAR_RA_ACQUAGIS = 87;
    
    public static final int ALTERAR_AREA_CONSTRUIDA_IMOVEL = 88;
    
    public static final int ALTERAR_CLIENTE_RESPONSAVEL_PARA_IMOVEIS_PUBLICOS = 89;
    
    public static final int INFORMAR_QUALQUER_SITUACAO_DE_COBRANCA = 90;
    
    public static final int ALTERAR_DADOS_CONFIRMACAO_CARTAO_CREDITO_DEBITO = 91;
    
    public static final int FINALIZAR_ARQUIVO_TEXTO_DE_LEITURA = 92;
    
    public static final int REPLICAR_VALOR_COBRANCA_SERVICO  = 93;
    
    public static final int ENVIAR_CONTA_PARA_REVISAO  = 94;
    
    public static final int INCLUIR_CLIENTE_SEM_CPF  = 96;
    
    public static final int ASSOCIAR_CLIENTE_AO_IMOVEL_SEM_CPF  = 97;
    
    public static final int ALTERAR_CLIENTE_PARA_IMOVEIS_PUBLICOS = 98;
    
    public static final int RETIFICAR_CONTA_SEM_VERIFICAR_CONSUMO_ESGOTO = 99;
    
    public static final int CONSULTAR_ARQUIVO_TEXTO_GERENCIAL = 100;
    
    public static final int INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO = 101;
    
    public static final int INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES = 102;
    
    public static final int INSERIR_NOME_CLIENTE_GENERICO = 103;

    public static final int GERAR_ARQUIVO_TEXTO_IMOVEIS_NAO_ENVIADOS = 104;
    
    public static final int RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO = 105; 
    
    public static final int ENCERRAR_ORDEM_SERVICO_SEM_ATIVIDADES = 106; 
    
    public static final int INSERIR_MANTER_CLIENTE_SEM_NEGATIVACAO = 107;
    
    public static final int TRAMITAR_RA_PARA_QUALQUER_UNIDADE = 110;
    
	public static final int ENCERRAR_COMANDO_COBRANCA_EMPRESA = 109; 
	
	public static final int ALTERAR_CLIENTE_INATIVO = 111;
	
	public static final int MANTER_CLIENTE_SEM_CPF_CNPJ = 112;

    
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.seguranca.acesso.Operacao operacao;

	/** full constructor */
	public PermissaoEspecial(String descricao, Short indicadorUso,
			Date ultimaAlteracao, gcom.seguranca.acesso.Operacao operacao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.operacao = operacao;
	}

	/** default constructor */
	public PermissaoEspecial() {
	}

	/** minimal constructor */
	public PermissaoEspecial(gcom.seguranca.acesso.Operacao operacao) {
		this.operacao = operacao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.acesso.Operacao getOperacao() {
		return this.operacao;
	}

	public void setOperacao(gcom.seguranca.acesso.Operacao operacao) {
		this.operacao = operacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof PermissaoEspecial)) {
			return false;
		}
		PermissaoEspecial castOther = (PermissaoEspecial) other;

		return (this.getId().equals(castOther.getId()));
	}

	
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}
		
	public Filtro retornaFiltro(){
			FiltroPermissaoEspecial filtroPermissaoEspecial= new FiltroPermissaoEspecial();

			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.ID,this.getId()));
			
		return filtroPermissaoEspecial;
	}
		
	@Override
	public Filtro retornaFiltroRegistroOperacao() {

		Filtro filtro = retornaFiltro();
		
		return filtro;
	}
		
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
		
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}
	
	
}
