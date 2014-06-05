package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DocumentoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricaoDocumentoTipo;
	private String descricaoAbreviado;
	private Short indicadorPagavel;
	private Short indicadorUso;
	private Short indicadorAgregador;
	private Date ultimaAlteracao;


	//constantes
	public final static Integer CONTA = new Integer("1");
	public final static Integer ENTRADA_DE_PARCELAMENTO = new Integer("2");
	public final static Integer DOCUMENTO_COBRANCA = new Integer("3");
	public final static Integer FATURA_CLIENTE = new Integer("5");
	public final static Integer DEBITO_A_COBRAR = new Integer("6");
	public final static Integer GUIA_PAGAMENTO = new Integer("7");
	public final static Integer DEVOLUCAO_VALOR = new Integer("8");
	public final static Integer CREDITO_A_REALIZAR = new Integer("10");
	public final static Integer EXTRATO_DE_DEBITO = new Integer("14");
	public final static Integer CARTA_COBRANCA_SUPRIMIDO = new Integer("21");
	public final static Integer CARTA_COBRANCA_CORTADO = new Integer("22");
	public final static Integer CARTA_COBRANCA_TARIFA_SOCIAL_LIGADO = new Integer("23");
	public final static Integer CARTA_COBRANCA_TARIFA_SOCIAL_CORTADO = new Integer("24");
	public final static Integer CARTA_COBRANCA_LIGADO = new Integer("25");
	
	public final static int ORDEM_FISCALIZACAO_FACTIVEL = 27;
	public final static int ORDEM_FISCALIZACAO_SUPRIMIDO = 16;
	public final static int ORDEM_FISCALIZACAO_CORTADO = 17;
	public final static int ORDEM_FISCALIZACAO_TOTAL = 30;
	public final static int ORDEM_FISCALIZACAO_POTENCIAL = 28;
	public final static int ORDEM_FISCALIZACAO_LIGADO = 29;
	
	public final static int AVISO_CORTE = 12;
	public final static int CORTE_FISICO = 13;
	public final static int CORTE_ADMINISTRATIVO = 11; 
	
	public final static Integer CARTA_SOLIDARIEDADE_DA_CRIANCA = 31;
	public final static Integer INSPECAO_DE_LIGACOES = 32;

	public final static Integer CARTA_DE_FINAL_DE_ANO_2009 = 33;
	
	public final static int VISITA_COBRANCA = 17; 
	public final static int ORDEM_CORTE = 18; 
	public final static int ORDEM_FISCALIZACAO = 16;
	
	public final static Integer EXTRATO_CONTRATO_PARCELAMENTO = 34;
	
	public final static int[] DOCUMENTO_TIPO_AGREGADOR = {
		GUIA_PAGAMENTO, FATURA_CLIENTE, EXTRATO_DE_DEBITO, CARTA_COBRANCA_SUPRIMIDO,
		CARTA_COBRANCA_CORTADO, CARTA_COBRANCA_LIGADO, DOCUMENTO_COBRANCA,
		CARTA_COBRANCA_TARIFA_SOCIAL_CORTADO, CARTA_COBRANCA_TARIFA_SOCIAL_LIGADO,
		AVISO_CORTE
	};
	
	public DocumentoTipo(String descricaoDocumentoTipo,
			String descricaoAbreviado, Short indicadorPagavel,
			Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorPagavel = indicadorPagavel;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DocumentoTipo() {
	}

	public DocumentoTipo(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoDocumentoTipo() {
		return this.descricaoDocumentoTipo;
	}

	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}

	public String getDescricaoAbreviado() {
		return this.descricaoAbreviado;
	}

	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public Short getIndicadorPagavel() {
		return this.indicadorPagavel;
	}

	public void setIndicadorPagavel(Short indicadorPagavel) {
		this.indicadorPagavel = indicadorPagavel;
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
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorAgregador.
	 */
	public Short getIndicadorAgregador() {
		return indicadorAgregador;
	}

	/**
	 * @param indicadorAgregador O indicadorAgregador a ser setado.
	 */
	public void setIndicadorAgregador(Short indicadorAgregador) {
		this.indicadorAgregador = indicadorAgregador;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoDocumentoTipo();
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroDocumentoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID, this.getId()));		
		return filtro;
	}

}
