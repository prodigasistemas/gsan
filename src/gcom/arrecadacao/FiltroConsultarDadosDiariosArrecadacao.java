package gcom.arrecadacao;

/**
 * Classe Helper para guardar os parametros de consulta dos Dados Diarios
 * de Arrecadacao e os tipos de agrupamentos para cada tela
 * 
 * @author Francisco do Nascimento
 * @created 07 de novembro de 2008
**/
public class FiltroConsultarDadosDiariosArrecadacao {

	public static enum GROUP_BY {
		GERENCIA_REGIONAL, UNIDADE_NEGOCIO, ELO, LOCALIDADE, 
		ARRECADADOR, ANO_MES, FORMA_ARRECADACAO, CATEGORIA, 
		PERFIL, TIPO_DOCUMENTO_AGREGADOR, TIPO_DOCUMENTO, DATA;
	} 
	 
	private GROUP_BY agrupamento = GROUP_BY.DATA;
	
	private String anoMesArrecadacao;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idElo;
	
	private String idLocalidade;
	
	private String idArrecadador;
	
	private String idFormaArrecadacao;
	
	private String idsImovelPerfil[];
	
	private String idsSituacaoLigacaoAgua[];
	
	private String idsSituacaoLigacaoEsgoto[];
	
	private String idsCategoria[];
	
	private String idsEsferaPoder[];
	
	private String idsDocumentoTipoAgregador[];
	
	private String idDocumentoTipo;
	
	private boolean isRelatorioValoresDiariosAnalitico;
	

	public GROUP_BY getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(GROUP_BY agrupamento) {
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

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public String[] getIdsEsferaPoder() {
		return idsEsferaPoder;
	}

	public void setIdsEsferaPoder(String idsEsferaPoder[]) {
		this.idsEsferaPoder = idsEsferaPoder;
	}

	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public String[] getIdsSituacaoLigacaoAgua() {
		return idsSituacaoLigacaoAgua;
	}

	public void setIdsSituacaoLigacaoAgua(String[] idsSituacaoLigacaoAgua) {
		this.idsSituacaoLigacaoAgua = idsSituacaoLigacaoAgua;
	}

	public String[] getIdsSituacaoLigacaoEsgoto() {
		return idsSituacaoLigacaoEsgoto;
	}

	public void setIdsSituacaoLigacaoEsgoto(String[] idsSituacaoLigacaoEsgoto) {
		this.idsSituacaoLigacaoEsgoto = idsSituacaoLigacaoEsgoto;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	
	public boolean isRelatorioValoresDiariosAnalitico() {
		return isRelatorioValoresDiariosAnalitico;
	}

	public void setRelatorioValoresDiariosAnalitico(
			boolean isRelatorioValoresDiariosAnalitico) {
		this.isRelatorioValoresDiariosAnalitico = isRelatorioValoresDiariosAnalitico;
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

	@Override
	public FiltroConsultarDadosDiariosArrecadacao clone() {
		FiltroConsultarDadosDiariosArrecadacao filtro = new FiltroConsultarDadosDiariosArrecadacao();
		filtro.setAgrupamento(getAgrupamento());
		filtro.setAnoMesArrecadacao(getAnoMesArrecadacao());
		filtro.setIdArrecadador(getIdArrecadador());
		filtro.setIdDocumentoTipo(getIdDocumentoTipo());
		filtro.setIdElo(getIdElo());
		filtro.setIdFormaArrecadacao(getIdFormaArrecadacao());
		filtro.setIdGerenciaRegional(getIdGerenciaRegional());
		filtro.setIdLocalidade(getIdLocalidade());
		filtro.setIdsCategoria(getIdsCategoria());
		filtro.setIdsDocumentoTipoAgregador(getIdsDocumentoTipoAgregador());
		filtro.setIdsEsferaPoder(getIdsEsferaPoder());
		filtro.setIdsImovelPerfil(getIdsImovelPerfil());
		filtro.setIdsSituacaoLigacaoAgua(getIdsSituacaoLigacaoAgua());
		filtro.setIdsSituacaoLigacaoEsgoto(getIdsSituacaoLigacaoEsgoto());
		filtro.setIdUnidadeNegocio(getIdUnidadeNegocio());
		filtro.setRelatorioValoresDiariosAnalitico(isRelatorioValoresDiariosAnalitico());
		return filtro;
	}
	
}
