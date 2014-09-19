package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class AnormalidadeComandoOSS extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private AnormalidadeComandoOSSPK comp_id;
	
	private Date ultimaAlteracao;
	
	private ComandoOrdemSeletiva comandoOrdemSeletiva;
	
	private LeituraAnormalidade leituraAnormalidade;

	public ComandoOrdemSeletiva getComandoOrdemSeletiva() {
		return comandoOrdemSeletiva;
	}

	public void setComandoOrdemSeletiva(ComandoOrdemSeletiva comandoOrdemSeletiva) {
		this.comandoOrdemSeletiva = comandoOrdemSeletiva;
	}

	public AnormalidadeComandoOSSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(AnormalidadeComandoOSSPK comp_id) {
		this.comp_id = comp_id;
	}

	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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
