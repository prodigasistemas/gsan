package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class CapacidHidrComandoOSS extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private CapacidHidrComandoOSSPK comp_id;
	
	private Date ultimaAlteracao;
	
	private ComandoOrdemSeletiva comandoOrdemSeletiva;
	
	private HidrometroCapacidade hidrometroCapacidade;

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

	public CapacidHidrComandoOSSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(CapacidHidrComandoOSSPK comp_id) {
		this.comp_id = comp_id;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
}
