package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class NegativadorExclusaoMotivo extends ObjetoTransacao implements Serializable {

	public static final String SPC_PAGAMENTO_DA_DIVIDA = new String("PAGAMENTO DA DIVIDA");
	public static final String SPC_RENEGOCIACAO_DA_DIVIDA = new String("RENEGOCIAÇÃO DA DIVIDA");
	public static final String SPC_MOTIVO_NAO_IDENTIFICADO = new String("MOTIVO NÃO IDENTIFICADO");
	
	public static final short SERASA_PAGAMENTO_DIVIDA = 1;
	public static final short SERASA_RENEGOCIACAO_DIVIDA = 2;
	public static final short SERASA_SOLICITACAO_CLIENTE = 3;
	public static final short SERASA_ORDEM_JUDICIAL = 4;
	public static final short SERASA_CORRECAO_ENDERECO = 5;
	public static final short SERASA_VALORIZACAO = 6;
	public static final short SERASA_PAGAMENTO_PARCIAL = 7;
	public static final short SERASA_ATUALIZACAO_DATA = 8;
	public static final short SERASA_CORRECAO_NOME = 9;
	public static final short SERASA_CORRECAO_NUMERO_CONTRATO = 10;
	public static final short SERASA_CORRECAO_VARIOS_DADOS = 11;
	public static final short SERASA_BAIXA_PERDA_CONTROLE_BASE = 12;
	public static final short SERASA_MOTIVO_NAO_IDENTIFICADO = 13;
	public static final short SERASA_PONTUALIZACAO_DA_DIVIDA = 14;
	public static final short SERASA_BAIXA_CONCESSAO_CREDITO = 15;
	public static final short SERASA_INCORPORACAO_MUDANCA_TITULARIDADE = 16;
	public static final short SERASA_COMUNICADO_DEVOLVIDO_CORREIOS = 17;
	public static final short SERASA_CORRECAO_DADOS_COOBRIGADO_AVALISTA = 18;
	public static final short SERASA_RENEGOCIACAO_DIVIDA_POR_ACORDO = 19;
	public static final short SERASA_PGTO_DIVIDA_DEPOSITO_BANCARIO = 20;
	public static final short SERASA_ANALISE_DOCUMENTOS = 21;
	public static final short SERASA_CORRECAO_DADOS_LOJA_FILIAL = 22;
	public static final short SERASA_PGTO_DIVIDA_EMISSAO_NOTA_PROMISSORIA = 23;
	public static final short SERASA_ANALISE_DOCUMENTO_SEGURO = 24;
	public static final short SERASA_DEVOLUCAO_TROCA_BEM_FINANCIADO = 25;

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoExclusaoMotivo();
	}

	@Override
	public void initializeLazy() {
		getDescricaoExclusaoMotivo();
	}

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Short codigoExclusaoMotivo;

	private String descricaoExclusaoMotivo;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private Negativador negativador;

	private CobrancaDebitoSituacao cobrancaDebitoSituacao;

	private Set negativadorMovimentoReg;

	public NegativadorExclusaoMotivo(Integer id, short codigoExclusaoMotivo, String descricaoExclusaoMotivo, short indicadorUso,
			Date ultimaAlteracao, Negativador negativador, Set negativadorMovimentoReg) {
		this.id = id;
		this.codigoExclusaoMotivo = codigoExclusaoMotivo;
		this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.negativador = negativador;
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}

	public NegativadorExclusaoMotivo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoExclusaoMotivo() {
		return this.descricaoExclusaoMotivo;
	}

	public void setDescricaoExclusaoMotivo(String descricaoExclusaoMotivo) {
		this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
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

	public Negativador getNegativador() {
		return this.negativador;
	}

	public void setNegativador(Negativador negativador) {
		this.negativador = negativador;
	}

	public Set getNegativadorMovimentoReg() {
		return this.negativadorMovimentoReg;
	}

	public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}

	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	public Short getCodigoExclusaoMotivo() {
		return codigoExclusaoMotivo;
	}

	public void setCodigoExclusaoMotivo(Short codigoExclusaoMotivo) {
		this.codigoExclusaoMotivo = codigoExclusaoMotivo;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Filtro retornaFiltro() {
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID, this.getId()));
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
