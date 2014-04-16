package gcom.arrecadacao;

/**TODO:COSANPA
 * @author Adriana Muniz
 * @created 10 de dezembro de 2012
 * 
 * Classe Helper para guardar os parametros de consulta dos Dados Diarios
 * de Arrecadacao e os tipos de agrupamentos para cada tela
 * 
**/
public class FiltroConsultarDadosDiariosArrecadacaoAuxiliar {

	public static enum GROUP_BY_AUX {
		GERENCIA_REGIONAL, UNIDADE_NEGOCIO, ELO, LOCALIDADE, 
		ARRECADADOR, ANO_MES, FORMA_ARRECADACAO, CATEGORIA, 
		PERFIL, TIPO_DOCUMENTO_AGREGADOR, TIPO_DOCUMENTO, DATA;
	} 
	 
	private GROUP_BY_AUX agrupamento = GROUP_BY_AUX.DATA;
	
	private String anoMesArrecadacao;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idElo;
	
	private String idLocalidade;
	
	private String idArrecadador;
	
	private String idFormaArrecadacao;
	
	private String idsDocumentoTipoAgregador[];
	
	private String idDocumentoTipo;
	
	private boolean isRelatorioValoresDiariosAnalitico;
	
	public GROUP_BY_AUX getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(GROUP_BY_AUX agrupamento) {
		this.agrupamento = agrupamento;
	}

	public String getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(String anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
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

	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idsDocumentoTipoAgregador.
	 */
	public String[] getIdsDocumentoTipoAgregador() {
		return idsDocumentoTipoAgregador;
	}

	/**
	 * @param idsDocumentoTipoAgregador O idsDocumentoTipoAgregador a ser setado.
	 */
	public void setIdsDocumentoTipoAgregador(String[] idsDocumentoTipoAgregador) {
		this.idsDocumentoTipoAgregador = idsDocumentoTipoAgregador;
	}

	public boolean isRelatorioValoresDiariosAnalitico() {
		return isRelatorioValoresDiariosAnalitico;
	}

	public void setRelatorioValoresDiariosAnalitico(
			boolean isRelatorioValoresDiariosAnalitico) {
		this.isRelatorioValoresDiariosAnalitico = isRelatorioValoresDiariosAnalitico;
	}

	@Override
	public FiltroConsultarDadosDiariosArrecadacaoAuxiliar clone() {
		FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro = new FiltroConsultarDadosDiariosArrecadacaoAuxiliar();
		filtro.setAgrupamento(getAgrupamento());
		filtro.setAnoMesArrecadacao(getAnoMesArrecadacao());
		filtro.setIdArrecadador(getIdArrecadador());
		filtro.setIdDocumentoTipo(getIdDocumentoTipo());
		filtro.setIdElo(getIdElo());
		filtro.setIdFormaArrecadacao(getIdFormaArrecadacao());
		filtro.setIdGerenciaRegional(getIdGerenciaRegional());
		filtro.setIdLocalidade(getIdLocalidade());
		filtro.setIdsDocumentoTipoAgregador(getIdsDocumentoTipoAgregador());
		filtro.setIdUnidadeNegocio(getIdUnidadeNegocio());
		return filtro;
	}
	
}
