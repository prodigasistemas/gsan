package gcom.cobranca.bean;

public class FiltroSupressoesReligacoesReestabelecimentoHelper {
	private String dataEmissaoInicio;
	private String dataEmissaoFim;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String idEmpresa;
	private String limititeReligacaoPosCorte;
	private Integer indicadorTipoRelatorio;
	private Integer numeroPagina;

	public FiltroSupressoesReligacoesReestabelecimentoHelper(){}
	
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
	public String getLimititeReligacaoPosCorte() {
		return limititeReligacaoPosCorte;
	}
	public void setLimititeReligacaoPosCorte(String limititeReligacaoPosCorte) {
		this.limititeReligacaoPosCorte = limititeReligacaoPosCorte;
	}
	
	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Integer getIndicadorTipoRelatorio() {
		return indicadorTipoRelatorio;
	}

	public void setIndicadorTipoRelatorio(Integer indicadorTipoRelatorio) {
		this.indicadorTipoRelatorio = indicadorTipoRelatorio;
	}
}
