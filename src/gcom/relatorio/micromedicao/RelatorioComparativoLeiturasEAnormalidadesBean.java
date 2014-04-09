package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioComparativoLeiturasEAnormalidadesBean implements RelatorioBean {

	private String grupo;

	private String empresa;

	private String idSetor;

	private String localidade;

	private String setor;
	
	private String rota;
	
	private String registrosRecebidos;

	private String registrosComLeitura;

	private String registrosComAnormalidade;

	private String registrosComLeituraEAnormalidade;
	
	private String anormalidadeLeitura;
	
	private String qtdeAnormalidadeLeitura;
	
	private String registrosEnviados;
	
	private String registrosRecebidosConvencional;
	
	private String registrosRecebidosSimultaneo;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioComparativoLeiturasEAnormalidadesBean(
			String grupo,
			String empresa, 
			String idSetor, 
			String localidade, 
			String setor,
			String registrosRecebidos, 
			String registrosComLeitura,
			String registrosComAnormalidade,
			String registrosComLeituraEAnormalidade,
			String anormalidadeLeitura, 
			String qtdeAnormalidadeLeitura, 
			String registrosEnviados,
			String rota,
			String registrosRecebidosConvencional,
			String registrosRecebidosSimultaneo) {
		
		this.grupo = grupo;
		this.empresa = empresa;
		this.idSetor = idSetor;
		this.localidade = localidade;
		this.setor = setor;
		this.registrosRecebidos = registrosRecebidos;
		this.registrosComLeitura = registrosComLeitura;
		this.registrosComAnormalidade = registrosComAnormalidade;
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.qtdeAnormalidadeLeitura = qtdeAnormalidadeLeitura;
		this.registrosEnviados = registrosEnviados;
		this.rota = rota;
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
		
	}

	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo localidadeSetor.
	 */
	public String getIdSetor() {
		return idSetor;
	}

	/**
	 * @param localidadeSetor O localidadeSetor a ser setado.
	 */
	public void setIdSetor(String idSetor) {
		this.idSetor = idSetor;
	}

	/**
	 * @return Retorna o campo qtdeAnormalidadeLeitura.
	 */
	public String getQtdeAnormalidadeLeitura() {
		return qtdeAnormalidadeLeitura;
	}

	/**
	 * @param qtdeAnormalidadeLeitura O qtdeAnormalidadeLeitura a ser setado.
	 */
	public void setQtdeAnormalidadeLeitura(String qtdeAnormalidadeLeitura) {
		this.qtdeAnormalidadeLeitura = qtdeAnormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo registrosComAnormalidade.
	 */
	public String getRegistrosComAnormalidade() {
		return registrosComAnormalidade;
	}

	/**
	 * @param registrosComAnormalidade O registrosComAnormalidade a ser setado.
	 */
	public void setRegistrosComAnormalidade(String registrosComAnormalidade) {
		this.registrosComAnormalidade = registrosComAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosComLeitura.
	 */
	public String getRegistrosComLeitura() {
		return registrosComLeitura;
	}

	/**
	 * @param registrosComLeitura O registrosComLeitura a ser setado.
	 */
	public void setRegistrosComLeitura(String registrosComLeitura) {
		this.registrosComLeitura = registrosComLeitura;
	}

	/**
	 * @return Retorna o campo registrosComLeituraEAnormalidade.
	 */
	public String getRegistrosComLeituraEAnormalidade() {
		return registrosComLeituraEAnormalidade;
	}

	/**
	 * @param registrosComLeituraEAnormalidade O registrosComLeituraEAnormalidade a ser setado.
	 */
	public void setRegistrosComLeituraEAnormalidade(
			String registrosComLeituraEAnormalidade) {
		this.registrosComLeituraEAnormalidade = registrosComLeituraEAnormalidade;
	}

	/**
	 * @return Retorna o campo registrosRecebidos.
	 */
	public String getRegistrosRecebidos() {
		return registrosRecebidos;
	}

	/**
	 * @param registrosRecebidos O registrosRecebidos a ser setado.
	 */
	public void setRegistrosRecebidos(String registrosRecebidos) {
		this.registrosRecebidos = registrosRecebidos;
	}

	/**
	 * @return Retorna o campo setor.
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor O setor a ser setado.
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * @return Retorna o campo totalRegistrosComLeituraEnviados.
	 */
	public String getRegistrosEnviados() {
		return registrosEnviados;
	}

	/**
	 * @param totalRegistrosComLeituraEnviados O totalRegistrosComLeituraEnviados a ser setado.
	 */
	public void setRegistrosEnviados(
			String registrosEnviados) {
		this.registrosEnviados = registrosEnviados;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getRegistrosRecebidosConvencional() {
		return registrosRecebidosConvencional;
	}

	public void setRegistrosRecebidosConvencional(
			String registrosRecebidosConvencional) {
		this.registrosRecebidosConvencional = registrosRecebidosConvencional;
	}

	public String getRegistrosRecebidosSimultaneo() {
		return registrosRecebidosSimultaneo;
	}

	public void setRegistrosRecebidosSimultaneo(String registrosRecebidosSimultaneo) {
		this.registrosRecebidosSimultaneo = registrosRecebidosSimultaneo;
	}

	
}
