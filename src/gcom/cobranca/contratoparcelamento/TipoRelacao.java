package gcom.cobranca.contratoparcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

public class TipoRelacao extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	private String descricao;
	
    private Short indicadorUso;
	
	private Date ultimaAlteracao;
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroTipoRelacao filtro = new FiltroTipoRelacao();
		filtro.adicionarParametro(
				new ParametroSimples(FiltroTipoRelacao.ID, this.getId()));
			return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "numero" };
		return retorno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
}
