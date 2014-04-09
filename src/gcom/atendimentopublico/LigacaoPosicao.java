package gcom.atendimentopublico;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Date;

public class LigacaoPosicao extends TabelaAuxiliar{
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    
    public LigacaoPosicao(Integer id, 
    	String descricao, 
    	Short indicadorUso, 
    	Date ultimaAlteracao) {
    	
		this.id = id;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LigacaoPosicao() {}

	public Filtro retornaFiltro() {
		return null;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

    public Integer getId() {
        return this.id;
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
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}
