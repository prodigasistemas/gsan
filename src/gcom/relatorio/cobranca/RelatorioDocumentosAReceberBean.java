package gcom.relatorio.cobranca;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioDocumentosAReceberBean implements RelatorioBean {
	
	private String mesAno;
	private Integer idCategoriaTipo;	
	private Integer idCategoria;	
	private Integer idGerencia;
	private String nomeGerencia;
	private Integer idUnidade;
	private String nomeUnidade;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idOpcaoTotalizacao;
	private Integer idDocumentoTipo;
	private String nomeDocumentoTipo;
	private Integer idSituacao;
	private String descricaoSituacao;
	private Integer qtdDocumentosRes;
	private Integer qtdDocumentosResTotal = 0;
	private BigDecimal valorDocumentosRes;
	private BigDecimal valorDocumentosResTotal = new BigDecimal(0);
	private Integer qtdDocumentosCom;
	private Integer qtdDocumentosComTotal = 0;
	private BigDecimal valorDocumentosCom;
	private BigDecimal valorDocumentosComTotal = new BigDecimal(0);
	private Integer qtdDocumentosInd;
	private Integer qtdDocumentosIndTotal = 0;
	private BigDecimal valorDocumentosInd;
	private BigDecimal valorDocumentosIndTotal = new BigDecimal(0);
	private Integer qtdDocumentosTot;
	private Integer qtdDocumentosTotTotal = 0;
	private BigDecimal valorDocumentosTot;
	private BigDecimal valorDocumentosTotTotal = new BigDecimal(0);
	private Integer qtdDocumentosPub;
	private Integer qtdDocumentosPubTotal = 0;
	private BigDecimal valorDocumentosPub;
	private BigDecimal valorDocumentosPubTotal = new BigDecimal(0);
	private String descricaTotalizacao;
	private String indicadorTotal;
	private String faixa;
	
	public RelatorioDocumentosAReceberBean(){
		
	}

 	public RelatorioDocumentosAReceberBean(String mesAno,
			Integer idCategoriaTipo, Integer idCategoria, Integer idGerencia,
			String nomeGerencia, Integer idUnidade, String nomeUnidade,
			Integer idLocalidade, String nomeLocalidade,
			Integer idOpcaoTotalizacao, Integer idDocumentoTipo,
			String nomeDocumentoTipo, Integer idSituacao,
			String descricaoSituacao, Integer qtdDocumentosRes,
			BigDecimal valorDocumentosRes, Integer qtdDocumentosCom,
			BigDecimal valorDocumentosCom, Integer qtdDocumentosInd,
			BigDecimal valorDocumentosInd, Integer qtdDocumentosTot,
			BigDecimal valorDocumentosTot, Integer qtdDocumentosPub,
			BigDecimal valorDocumentosPub, String descricaTotalizacao,
			String indicadorTotal, String faixa) {
		this.mesAno = mesAno;
		this.idCategoriaTipo = idCategoriaTipo;
		this.idCategoria = idCategoria;
		this.idGerencia = idGerencia;
		this.nomeGerencia = nomeGerencia;
		this.idUnidade = idUnidade;
		this.nomeUnidade = nomeUnidade;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.nomeDocumentoTipo = nomeDocumentoTipo;
		this.idSituacao = idSituacao;
		this.descricaoSituacao = descricaoSituacao;
		this.qtdDocumentosRes = qtdDocumentosRes;
		this.valorDocumentosRes = valorDocumentosRes;
		this.qtdDocumentosCom = qtdDocumentosCom;
		this.valorDocumentosCom = valorDocumentosCom;
		this.qtdDocumentosInd = qtdDocumentosInd;
		this.valorDocumentosInd = valorDocumentosInd;
		this.qtdDocumentosTot = qtdDocumentosTot;
		this.valorDocumentosTot = valorDocumentosTot;
		this.qtdDocumentosPub = qtdDocumentosPub;
		this.valorDocumentosPub = valorDocumentosPub;
		this.descricaTotalizacao = descricaTotalizacao;
		this.indicadorTotal = indicadorTotal;
		this.faixa = faixa;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Integer getIdCategoriaTipo() {
		return idCategoriaTipo;
	}

	public void setIdCategoriaTipo(Integer idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Integer getIdOpcaoTotalizacao() {
		return idOpcaoTotalizacao;
	}

	public void setIdOpcaoTotalizacao(Integer idOpcaoTotalizacao) {
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
	}

	public Integer getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(Integer idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public String getNomeDocumentoTipo() {
		return nomeDocumentoTipo;
	}

	public void setNomeDocumentoTipo(String nomeDocumentoTipo) {
		this.nomeDocumentoTipo = nomeDocumentoTipo;
	}

	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public Integer getQtdDocumentosRes() {
		return qtdDocumentosRes;
	}

	public void setQtdDocumentosRes(Integer qtdDocumentosRes) {
		this.qtdDocumentosRes = qtdDocumentosRes;
	}

	public Integer getQtdDocumentosResTotal() {
		return qtdDocumentosResTotal;
	}

	public void setQtdDocumentosResTotal(Integer qtdDocumentosResTotal) {
		this.qtdDocumentosResTotal = qtdDocumentosResTotal;
	}

	public BigDecimal getValorDocumentosRes() {
		return valorDocumentosRes;
	}

	public void setValorDocumentosRes(BigDecimal valorDocumentosRes) {
		this.valorDocumentosRes = valorDocumentosRes;
	}

	public BigDecimal getValorDocumentosResTotal() {
		return valorDocumentosResTotal;
	}

	public void setValorDocumentosResTotal(BigDecimal valorDocumentosResTotal) {
		this.valorDocumentosResTotal = valorDocumentosResTotal;
	}

	public Integer getQtdDocumentosCom() {
		return qtdDocumentosCom;
	}

	public void setQtdDocumentosCom(Integer qtdDocumentosCom) {
		this.qtdDocumentosCom = qtdDocumentosCom;
	}

	public Integer getQtdDocumentosComTotal() {
		return qtdDocumentosComTotal;
	}

	public void setQtdDocumentosComTotal(Integer qtdDocumentosComTotal) {
		this.qtdDocumentosComTotal = qtdDocumentosComTotal;
	}

	public BigDecimal getValorDocumentosCom() {
		return valorDocumentosCom;
	}

	public void setValorDocumentosCom(BigDecimal valorDocumentosCom) {
		this.valorDocumentosCom = valorDocumentosCom;
	}

	public BigDecimal getValorDocumentosComTotal() {
		return valorDocumentosComTotal;
	}

	public void setValorDocumentosComTotal(BigDecimal valorDocumentosComTotal) {
		this.valorDocumentosComTotal = valorDocumentosComTotal;
	}

	public Integer getQtdDocumentosInd() {
		return qtdDocumentosInd;
	}

	public void setQtdDocumentosInd(Integer qtdDocumentosInd) {
		this.qtdDocumentosInd = qtdDocumentosInd;
	}

	public Integer getQtdDocumentosIndTotal() {
		return qtdDocumentosIndTotal;
	}

	public void setQtdDocumentosIndTotal(Integer qtdDocumentosIndTotal) {
		this.qtdDocumentosIndTotal = qtdDocumentosIndTotal;
	}

	public BigDecimal getValorDocumentosInd() {
		return valorDocumentosInd;
	}

	public void setValorDocumentosInd(BigDecimal valorDocumentosInd) {
		this.valorDocumentosInd = valorDocumentosInd;
	}

	public BigDecimal getValorDocumentosIndTotal() {
		return valorDocumentosIndTotal;
	}

	public void setValorDocumentosIndTotal(BigDecimal valorDocumentosIndTotal) {
		this.valorDocumentosIndTotal = valorDocumentosIndTotal;
	}

	public Integer getQtdDocumentosTot() {
		return qtdDocumentosTot;
	}

	public void setQtdDocumentosTot(Integer qtdDocumentosTot) {
		this.qtdDocumentosTot = qtdDocumentosTot;
	}

	public Integer getQtdDocumentosTotTotal() {
		return qtdDocumentosTotTotal;
	}

	public void setQtdDocumentosTotTotal(Integer qtdDocumentosTotTotal) {
		this.qtdDocumentosTotTotal = qtdDocumentosTotTotal;
	}

	public BigDecimal getValorDocumentosTot() {
		return valorDocumentosTot;
	}

	public void setValorDocumentosTot(BigDecimal valorDocumentosTot) {
		this.valorDocumentosTot = valorDocumentosTot;
	}

	public BigDecimal getValorDocumentosTotTotal() {
		return valorDocumentosTotTotal;
	}

	public void setValorDocumentosTotTotal(BigDecimal valorDocumentosTotTotal) {
		this.valorDocumentosTotTotal = valorDocumentosTotTotal;
	}

	public Integer getQtdDocumentosPub() {
		return qtdDocumentosPub;
	}

	public void setQtdDocumentosPub(Integer qtdDocumentosPub) {
		this.qtdDocumentosPub = qtdDocumentosPub;
	}

	public Integer getQtdDocumentosPubTotal() {
		return qtdDocumentosPubTotal;
	}

	public void setQtdDocumentosPubTotal(Integer qtdDocumentosPubTotal) {
		this.qtdDocumentosPubTotal = qtdDocumentosPubTotal;
	}

	public BigDecimal getValorDocumentosPub() {
		return valorDocumentosPub;
	}

	public void setValorDocumentosPub(BigDecimal valorDocumentosPub) {
		this.valorDocumentosPub = valorDocumentosPub;
	}

	public BigDecimal getValorDocumentosPubTotal() {
		return valorDocumentosPubTotal;
	}

	public void setValorDocumentosPubTotal(BigDecimal valorDocumentosPubTotal) {
		this.valorDocumentosPubTotal = valorDocumentosPubTotal;
	}

	public String getDescricaTotalizacao() {
		return descricaTotalizacao;
	}

	public void setDescricaTotalizacao(String descricaTotalizacao) {
		this.descricaTotalizacao = descricaTotalizacao;
	}

	public String getIndicadorTotal() {
		return indicadorTotal;
	}

	public void setIndicadorTotal(String indicadorTotal) {
		this.indicadorTotal = indicadorTotal;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}
	
}
