package gcom.relatorio.atendimentopublico;

import java.util.Collection;
import java.util.List;

/**
 * Classe responsável por auxiliar a geração do relatório
 * de ordem de serviço por situação. Esta classe armazena
 * os dados do relatório (beans) e os filtros do usuário.
 * 
 * @author Diogo Peixoto
 *
 */

public class RelatorioOSSituacaoHelper {

	private Collection<RelatorioOSSituacaoBean> beans;
	private String gerenciaRegional;
	private String setorComercial;
	private String unidadeNegocio;
	private String quadra;
	private String localidadeEloPolo;
	private String localidade;
	private String tipoServico;
	
	public Collection<RelatorioOSSituacaoBean> getBeans() {
		return beans;
	}
	public void setBeans(Collection<RelatorioOSSituacaoBean> beans) {
		this.beans = beans;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getLocalidadeEloPolo() {
		return localidadeEloPolo;
	}
	public void setLocalidadeEloPolo(String localidadeEloPolo) {
		this.localidadeEloPolo = localidadeEloPolo;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
}