package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TabelaColuna extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

	public static final int DATA_VENCIMENTO_CONTA = 419;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String coluna;

	/** nullable persistent field */
	private String descricaoColuna;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorPrimaryKey = new Short((short) 2);

	/** nullable persistent field */
	private String nomeAbreviado;

	/** persistent field */
	private gcom.seguranca.transacao.Tabela tabela;

	private Integer indicadorPodeRetificarConta;

	private Short indicadorAtualizacaoCadastral;

	// Constantes
	// Nome do campo + tabela correspondente
	public final static Integer CPF_CNPJ_CLIENTE_ATU_CADASTRAL = 23829;

	// public final static Integer CNPJ_CLIENTE_ATU_CADASTRAL = 275;
	public final static Integer NOME_CLIENTE_ATU_CADASTRAL = 23820;

	public final static Integer PROFISSAO_CLIENTE_ATU_CADASTRAL = 23827;

	public final static Integer RAMO_ATIVIDADE_CLIENTE_ATU_CADASTRAL = 35022;

	public final static Integer CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL = 23821;

	public final static Integer CLIENTE_RELACAO_TIPO_CLIENTE_IMOVEL_ATU_CADASTRAL = 23843;

	public final static Integer NUMERO_IMOVEL_CLIENTE_ENDERECO_ATU_CADASTRAL = 23840;

	public final static Integer COMPLEMENTO_CLIENTE_ENDERECO_ATU_CADASTRAL = 23841;
	
	public final static Integer SEXO_CLIENTE_ATUALIZACAO_CADASTRAL = 23828;

	public final static Integer NUMERO_MORADORES_IMOVEL_ATU_CADASTRAL = 23792;

	public final static Integer CODIGO_PERFIL_IMOVEL_ATU_CADASTRAL = 115087;

	public final static Integer NUMERO_IMOVEL_ATU_CADASTRAL = 35021;

	public final static Integer COMPLEMENTO_ENDERECO_IMOVEL_ATU_CADASTRAL = 23779;
	
	public final static Integer CODIGO_CADASTRO_OCORRENCIA_IMOVEL_ATU_CADASTRAL = 35024;
	
	public final static Integer ID_SITUACAO_LIGACAO_AGUA_IMOVEL_ATU_CADASTRAL = 23786;
	
	public final static Integer ID_SITUACAO_LIGACAO_ESGOTO_IMOVEL_ATU_CADASTRAL = 23787;

	public final static Integer CODIGO_SUBCATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 35027;

	public final static Integer QUANTIDADES_ECONOMIAS_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23852;

	public final static Integer CODIGO_CATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL = 23854;
	
	public final static Integer TIPO_FONE_CLIENTE_TELEFONE = 23848;
	
	public final static Integer DDD_CLIENTE_TELEFONE = 23849;
	
	public final static Integer NUMERO_FONE_CLIENTE_TELEFONE = 23850;
	
	public final static Integer INDICADOR_FONE_PADRAO_CLIENTE_TELEFONE = 115095;

	public final static Integer ID_IMOVEL = 278;

	public final static Integer ID_CLIENTE_ATU_CADASTRAL = 115096;
	
	public final static Integer CODIGO_CLIENTE_ATU_CADASTRAL = 115097;

	public final static Integer ID_CLIENTE_TELEFONE_ATU_CADASTRAL = 115092;
	
	public final static String NOME_COLUNA_HIDROMETRO = "imac_nnhidrometro";

	public final static String NOME_COLUNA_ECONOMIAS = "isac_qteconomia";

	public static final String NOME_COLUNA_ESGOTO = "lest_id";

	public static final String NOME_COLUNA_AGUA = "last_id";
	
	public static final String NOME_COLUNA_NUMERO_HUDROMETRO = "imac_nnhidrometro";

	/** full constructor */
	public TabelaColuna(String coluna, String descricaoColuna,
			Date ultimaAlteracao, gcom.seguranca.transacao.Tabela tabela,
			Short indicadorPrimaryKey, String nomeAbreviado) {
		this.coluna = coluna;
		this.descricaoColuna = descricaoColuna;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabela = tabela;
		this.indicadorPrimaryKey = indicadorPrimaryKey;
		this.nomeAbreviado = nomeAbreviado;
	}

	/** default constructor */
	public TabelaColuna() {
	}

	/** minimal constructor */
	public TabelaColuna(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColuna() {
		return this.coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getDescricaoColuna() {
		return this.descricaoColuna;
	}

	public void setDescricaoColuna(String descricaoColuna) {
		this.descricaoColuna = descricaoColuna;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.transacao.Tabela getTabela() {
		return this.tabela;
	}

	public void setTabela(gcom.seguranca.transacao.Tabela tabela) {
		this.tabela = tabela;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorPrimaryKey.
	 */
	public Short getIndicadorPrimaryKey() {
		return indicadorPrimaryKey;
	}

	/**
	 * @param indicadorPrimaryKey
	 *            O indicadorPrimaryKey a ser setado.
	 */
	public void setIndicadorPrimaryKey(Short indicadorPrimaryKey) {
		this.indicadorPrimaryKey = indicadorPrimaryKey;
	}

	/**
	 * @return Retorna o campo nomeAbreviado.
	 */
	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	/**
	 * @param nomeAbreviado
	 *            O nomeAbreviado a ser setado.
	 */
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public Integer getIndicadorPodeRetificarConta() {
		return indicadorPodeRetificarConta;
	}

	public void setIndicadorPodeRetificarConta(
			Integer indicadorPodeRetificarConta) {
		this.indicadorPodeRetificarConta = indicadorPodeRetificarConta;
	}

	public Short getIndicadorAtualizacaoCadastral() {
		return indicadorAtualizacaoCadastral;
	}

	public void setIndicadorAtualizacaoCadastral(
			Short indicadorAtualizacaoCadastral) {
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
