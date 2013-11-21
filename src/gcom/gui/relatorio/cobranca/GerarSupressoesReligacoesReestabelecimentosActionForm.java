package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

public class GerarSupressoesReligacoesReestabelecimentosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String dataEmissaoInicio;
	private String dataEmissaoFim;
	private String indicadorEstado;
	private String indicadorGerenciaRegional;
	private String idGerenciaRegional;
	private String indicadorUnidadeNegocio;
	private String idUnidadeNegocio;
	private String indicadorLocalidade;
	private String idLocalidade;
	private String idEmpresa;
	private String limititeReligacaoPosCorte;
	private String indicadorTipoRelatorio;
	
	public String getIndicadorTipoRelatorio() {
		return indicadorTipoRelatorio;
	}
	public void setIndicadorTipoRelatorio(String indicadorTipoRelatorio) {
		this.indicadorTipoRelatorio = indicadorTipoRelatorio;
	}
	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}
	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}
	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}
	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getIndicadorEstado() {
		return indicadorEstado;
	}
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
	public String getIndicadorGerenciaRegional() {
		return indicadorGerenciaRegional;
	}
	public void setIndicadorGerenciaRegional(String indicadorGerenciaRegional) {
		this.indicadorGerenciaRegional = indicadorGerenciaRegional;
	}
	public String getIndicadorLocalidade() {
		return indicadorLocalidade;
	}
	public void setIndicadorLocalidade(String indicadorLocalidade) {
		this.indicadorLocalidade = indicadorLocalidade;
	}
	public String getIndicadorUnidadeNegocio() {
		return indicadorUnidadeNegocio;
	}
	public void setIndicadorUnidadeNegocio(String indicadorUnidadeNegocio) {
		this.indicadorUnidadeNegocio = indicadorUnidadeNegocio;
	}
	public String getLimititeReligacaoPosCorte() {
		return limititeReligacaoPosCorte;
	}
	public void setLimititeReligacaoPosCorte(String limititeReligacaoPosCorte) {
		this.limititeReligacaoPosCorte = limititeReligacaoPosCorte;
	}
}
