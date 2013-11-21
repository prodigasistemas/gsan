package gcom.cobranca;

import java.util.Date;

public class MotivoNaoGeracaoDocCobranca {



//	public static final Integer IMOVEL_MARIO = 1;

	public static final Integer IMOVEL_EXCLUIDO = 2;

	public static final Integer NAO_EXISTE_DOCUMENTO_PRECEDENTE_VALIDO = 3;

	public static final Integer NAO_EXISTE_ORDEM_SERVICO_ACAO_PRECEDENTE_EXECUTADA_PRAZO = 4;

	public static final Integer EXISTE_DOCUMENTO_VALIDO_ACAO_SUCESSORA = 5;
	
	public static final Integer EXISTE_DOCUMENTO_VALIDO_MESMO_TIPO  = 6;
	
	public static final Integer SITUACAO_LIGACAO_AGUA_INVALIDA = 7;
	
	public static final Integer SITUACAO_LIGACAO_ESGOTO_INVALIDA = 8;
	
	public static final Integer IMOVEL_EM_SITUACAO_ESPECIAL_COBRANCA = 9;
	
	public static final Integer IMOVEL_EM_SITUACAO_COBRANCA_NAO_PERMITIDA = 10;
	
	public static final Integer CRITERIO_NAO_PERMITE_SITUACAO_COBRANCA = 11;
	
	public static final Integer NAO_HA_CRITERIO_PARA_O_PERFIL = 12;
	
	public static final Integer IMOVEL_SEM_DEBITOS = 13;

	public static final Integer CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_MES = 14;
	
	public static final Integer CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_ANTIGA = 15;
	
	public static final Integer CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_MES_E_ANTIGA = 16;
	
	public static final Integer VALOR_DEBITO_FORA_INTERVALO_DEFINIDO_CRITERIO = 17;
	
	public static final Integer QTD_ITENS_FORA_INTERVALO_DEFINIDO_CRITERIO = 18;

	public static final Integer VALOR_DEBITO_AUTOMATICO_MENOR_QUE_MINIMO = 19;
	
	public static final Integer QTD_ITENS_MENOR_MINIMA_PARA_DEBITO_AUTOMATICO = 20;
	
	public static final Integer VALOR_DEBITO_MENOR_QUE_MINIMO_CASO_INQUILINO = 21;

	public static final Integer VALOR_DEBITO_MENOR_QUE_MINIMO_CASO_INQUILINO_CONTA_MES = 22;	
	
	public static final Integer QTD_CONTAS_PARCELADAS_MENOR_QUE_MINIMA = 23;
	
	public static final Integer EXCEDEU_METAS = 24;
	
	public static final Integer CONSUMO_MEDIO_FORA_INTERVALO_DEFINIDO_CRITERIO = 25;
	
	public static final Integer SITUACAO_FISCALIZACAO_NAO_ATENDE_CRITERIO = 26;
	
	public static final Integer QTD_CONTAS_INVALIDA_PARA_PARCELAMENTO = 27;
	
	private Integer id;
	
	private Date ultimaAlteracao;

	private Short indicadorUso;

	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
