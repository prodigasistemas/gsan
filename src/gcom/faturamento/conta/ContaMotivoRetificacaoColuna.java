package gcom.faturamento.conta;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.filtro.Filtro;

public class ContaMotivoRetificacaoColuna extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private ContaMotivoRetificacaoColunaPK comp_id;
	
    private Date ultimaAlteracao;

	private ContaMotivoRetificacao contaMotivoRetificacao;
	
	private TabelaColuna tabelaColuna;

	public ContaMotivoRetificacaoColunaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ContaMotivoRetificacaoColunaPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	public void setTabelaColuna(TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

}
