package gcom.cadastro.descricaogenerica;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class DescricaoGenerica extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer degeId;

	private String nomeGenerico;
	
	private Date ultimaAlteracao;
	

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "degeId" };
		return retorno;
	}

	public Integer getDegeId() {
		return degeId;
	}

	public void setDegeId(Integer degeId) {
		this.degeId = degeId;
	}

	public String getNomeGenerico() {
		return nomeGenerico;
	}

	public void setNomeGenerico(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


}
