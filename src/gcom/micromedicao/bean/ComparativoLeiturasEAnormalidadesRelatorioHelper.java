package gcom.micromedicao.bean;



/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class ComparativoLeiturasEAnormalidadesRelatorioHelper {
	
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Short codigoRota;
	//private Integer idGrupoFaturamento;
	//private Integer idEmpresa;
	//private String nomeEmpresa;
	private Integer registrosEnviados;
	private Integer registrosRecebidos;
	private Integer registrosComLeitura;
	private Integer registrosComAnormalidade;
	private Integer registrosComLeituraEAnormalidade;
	
	private Integer registrosRecebidosConvencional;
	private Integer registrosRecebidosSimultaneo;
	
	public ComparativoLeiturasEAnormalidadesRelatorioHelper(){}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo registroComAnormalidade.
	 */
	public Integer getRegistrosComAnormalidade() {
		return registrosComAnormalidade;
	}

	/**
	 * @param registroComAnormalidade O registroComAnormalidade a ser setado.
	 */
	public void setRegistrosComAnormalidade(Integer registrosComAnormalidade) {
		this.registrosComAnormalidade = registrosComAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosComLeitura.
	 */
	public Integer getRegistrosComLeitura() {
		return registrosComLeitura;
	}

	/**
	 * @param registrosComLeitura O registrosComLeitura a ser setado.
	 */
	public void setRegistrosComLeitura(Integer registrosComLeitura) {
		this.registrosComLeitura = registrosComLeitura;
	}

	/**
	 * @return Retorna o campo registrosComLeituraEAnormalidade.
	 */
	public Integer getRegistrosComLeituraEAnormalidade() {
		return registrosComLeituraEAnormalidade;
	}

	/**
	 * @param registrosComLeituraEAnormalidade O registrosComLeituraEAnormalidade a ser setado.
	 */
	public void setRegistrosComLeituraEAnormalidade(
			Integer registrosComLeituraEAnormalidade) {
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosRecebidos.
	 */
	public Integer getRegistrosRecebidos() {
		return registrosRecebidos;
	}

	/**
	 * @param registrosRecebidos O registrosRecebidos a ser setado.
	 */
	public void setRegistrosRecebidos(Integer registrosRecebidos) {
		this.registrosRecebidos = registrosRecebidos;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getRegistrosEnviados() {
		return registrosEnviados;
	}

	public void setRegistrosEnviados(Integer registrosEnviados) {
		this.registrosEnviados = registrosEnviados;
	}

	public Integer getRegistrosRecebidosConvencional() {
		return registrosRecebidosConvencional;
	}

	public void setRegistrosRecebidosConvencional(
			Integer registrosRecebidosConvencional) {
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
	}

	public Integer getRegistrosRecebidosSimultaneo() {
		return registrosRecebidosSimultaneo;
	}

	public void setRegistrosRecebidosSimultaneo(Integer registrosRecebidosSimultaneo) {
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
	}

	
}
