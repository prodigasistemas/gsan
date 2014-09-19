package gcom.cobranca;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class CobrancaAcaoOrdemServicoNaoAceitas extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitasPK comp_id;
	
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private OrdemServico ordemServico;

    /** nullable persistent field */
    private CobrancaAcao cobrancaAcao;
    
    /** nullable persistent field */
    private MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao;
    
    /** nullable persistent field */
    private Short indicadorNaoAceitacao;
    
    /** nullable persistent field */
    private Short indicadorDescontoEfetuado;

    /** nullable persistent field */
    private String observacao;

	public CobrancaAcaoOrdemServicoNaoAceitas(CobrancaAcaoOrdemServicoNaoAceitasPK comp_id,
			Date ultimaAlteracao, OrdemServico ordemServico, CobrancaAcao cobrancaAcao, Short indicadorNaoAceitacao,
			MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao, Short indicadorDescontoEfetuado, String observacao) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.ordemServico = ordemServico;
		this.cobrancaAcao = cobrancaAcao;
		this.indicadorNaoAceitacao = indicadorNaoAceitacao;
		this.motivoNaoAceitacao = motivoNaoAceitacao;
		this.indicadorDescontoEfetuado = indicadorDescontoEfetuado;
		this.observacao = observacao;
	}

	public CobrancaAcaoOrdemServicoNaoAceitas() {
		super();
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitasPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(
			gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitasPK comp_id) {
		this.comp_id = comp_id;
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public Short getIndicadorDescontoEfetuado() {
		return indicadorDescontoEfetuado;
	}

	public void setIndicadorDescontoEfetuado(Short indicadorDescontoEfetuado) {
		this.indicadorDescontoEfetuado = indicadorDescontoEfetuado;
	}

	public Short getIndicadorNaoAceitacao() {
		return indicadorNaoAceitacao;
	}

	public void setIndicadorNaoAceitacao(Short indicadorNaoAceitacao) {
		this.indicadorNaoAceitacao = indicadorNaoAceitacao;
	}

	public MotivoNaoAceitacaoEncerramentoOS getMotivoNaoAceitacao() {
		return motivoNaoAceitacao;
	}

	public void setMotivoNaoAceitacao(MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao) {
		this.motivoNaoAceitacao = motivoNaoAceitacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}
	
	
}
