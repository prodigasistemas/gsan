package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class LigacaoSitComandoOSS extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private LigacaoSitComandoOSSPK comp_id;
	
	private Date ultimaAlteracao;
	
	private ComandoOrdemSeletiva comandoOrdemSeletiva;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	public ComandoOrdemSeletiva getComandoOrdemSeletiva() {
		return comandoOrdemSeletiva;
	}

	public void setComandoOrdemSeletiva(ComandoOrdemSeletiva comandoOrdemSeletiva) {
		this.comandoOrdemSeletiva = comandoOrdemSeletiva;
	}

	public LigacaoSitComandoOSSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(LigacaoSitComandoOSSPK comp_id) {
		this.comp_id = comp_id;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
