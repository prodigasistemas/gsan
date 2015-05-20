package gcom.faturamento.conta;

import gcom.util.Util;

import java.util.Date;

public class ContaImpressaoTermicaQtde {
	
	private Integer id;
	private Integer idGrupoFaturamento;
	private Integer referencia;
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer qtdeContas;
	private Date dataGeracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public Integer getReferencia() {
		return referencia;
	}
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public Integer getQtdeContas() {
		return qtdeContas;
	}
	public void setQtdeContas(Integer qtdeContas) {
		this.qtdeContas = qtdeContas;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getReferenciaFormatada() {
		return Util.formatarAnoMesParaMesAno(this.referencia);
	}
}