package gcom.cadastro;


import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;


public class Dmc extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String descricao;
    private gcom.cadastro.localidade.Localidade localidade;
	private gcom.cadastro.localidade.SetorComercial setorComercial;
	private Short indicadorUso;
	
	public Dmc() {
		
	}
	
	public Dmc(int id, String descricao,
			gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.SetorComercial setorComercial) {
		this.id = id;
		this.descricao = descricao;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
	}
	
	public Integer getId() {
		return id;
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
	public gcom.cadastro.localidade.Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
		this.localidade = localidade;
	}
	public gcom.cadastro.localidade.SetorComercial getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(gcom.cadastro.localidade.SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Filtro retornaFiltro() {
		
		FiltroDmc filtroDmc = new FiltroDmc();

		filtroDmc.adicionarParametro(new ParametroSimples(FiltroDmc.ID, this.getId()));
		return filtroDmc;
		
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		
	}
	
	


    
}
