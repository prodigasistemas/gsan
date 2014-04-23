package gcom.atendimentopublico.ligacaoesgoto;

import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Date;

public class LigacaoEsgotoEsgotamento extends TabelaAuxiliar{
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    
    private FaturamentoSituacaoTipo faturamentoSituacaoTipo;
    
    private FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;
    
    private Integer quantidadeMesesSituacaoEspecial;

    
    public LigacaoEsgotoEsgotamento(Integer id, 
    	String descricao, 
    	Short indicadorUso, 
    	Date ultimaAlteracao) {
    	
		this.id = id;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LigacaoEsgotoEsgotamento() {}

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

	public FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public Integer getQuantidadeMesesSituacaoEspecial() {
		return quantidadeMesesSituacaoEspecial;
	}

	public void setQuantidadeMesesSituacaoEspecial(
			Integer quantidadeMesesSituacaoEspecial) {
		this.quantidadeMesesSituacaoEspecial = quantidadeMesesSituacaoEspecial;
	}
    
    
}
