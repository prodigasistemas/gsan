package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;

import gcom.cadastro.imovel.Subcategoria;

public class CobrancaAcaoAtividadeComandoSubcategoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	private Subcategoria subcategoria;
	private Date ultimaAlteracao;
	
	public CobrancaAcaoAtividadeComandoSubcategoria() {}

	public CobrancaAcaoAtividadeComandoSubcategoria(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, 
			Subcategoria subcategoria,
			Date ultimaAlteracao) {
		
		super();
		
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.subcategoria = subcategoria;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
