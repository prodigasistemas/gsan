package gcom.faturamento.conta;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.io.Serializable;
import java.util.Date;

public class ContaMensagemTipo extends ObjetoTransacao implements Serializable{

	private static final long serialVersionUID = 2122647399722417826L;

	private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	@Override
	public Filtro retornaFiltro() {
		return null;
	}
	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}
}
