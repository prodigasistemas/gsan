package gcom.gui.cadastro.unidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Ana Maria
 * @created 22/11/2006
 */
public class AtualizarUnidadeOrganizacionalActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
    private String unidadeTipo;
    private String idLocalidade;
    private String descricaoLocalidade;
    private String gerenciaRegional;
    private String unidadeNegocio;
    private String descricao;
    private String sigla;
    private String idEmpresa;
    private String empresa;
    private String idUnidadeSuperior;
    private String descricaoUnidadeSuperior;
    private String idUnidadeCentralizadora;
    private String idUnidadeRepavimentadora;
    private String unidadeEsgoto;
    private String unidadeAbreRegistro;
    private String unidadeAceita;
    private String meioSolicitacao;
    private String indicadorUso;
    private String codigoUnidadeTipo = "";
    private String idMunicipio;
	private String descricaoMunicipio;

	public String getCodigoUnidadeTipo() {
		return codigoUnidadeTipo;
	}

	public void setCodigoUnidadeTipo(String codigoUnidadeTipo) {
		this.codigoUnidadeTipo = codigoUnidadeTipo;
	}

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidadeCentralizadora() {
		return idUnidadeCentralizadora;
	}

	public void setIdUnidadeCentralizadora(String idUnidadeCentralizadora) {
		this.idUnidadeCentralizadora = idUnidadeCentralizadora;
	}

	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUnidadeTipo() {
		return unidadeTipo;
	}

	public void setUnidadeTipo(String unidadeTipo) {
		this.unidadeTipo = unidadeTipo;
	}

	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}

	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getUnidadeAbreRegistro() {
		return unidadeAbreRegistro;
	}

	public void setUnidadeAbreRegistro(String unidadeAbreRegistro) {
		this.unidadeAbreRegistro = unidadeAbreRegistro;
	}

	public String getUnidadeAceita() {
		return unidadeAceita;
	}

	public void setUnidadeAceita(String unidadeAceita) {
		this.unidadeAceita = unidadeAceita;
	}

	public String getUnidadeEsgoto() {
		return unidadeEsgoto;
	}

	public void setUnidadeEsgoto(String unidadeEsgoto) {
		this.unidadeEsgoto = unidadeEsgoto;
	}

/*	public String getUnidadeCentralAtendimento() {
		return unidadeCentralAtendimento;
	}

	public void setUnidadeCentralAtendimento(String unidadeCentralAtendimento) {
		this.unidadeCentralAtendimento = unidadeCentralAtendimento;
	}

	public String getUnidadeTarifaSocial() {
		return unidadeTarifaSocial;
	}

	public void setUnidadeTarifaSocial(String unidadeTarifaSocial) {
		this.unidadeTarifaSocial = unidadeTarifaSocial;
	}
*/
	/**
	 * @return Retorna o campo id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
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
	 * @return Retorna o campo idUnidadeRepavimentadora.
	 */
	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	/**
	 * @param idUnidadeRepavimentadora O idUnidadeRepavimentadora a ser setado.
	 */
	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Returns the descricaoMunicipio.
	 */
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	/**
	 * @param descricaoMunicipio The descricaoMunicipio to set.
	 */
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	/**
	 * @return Returns the idMunicipio.
	 */
	public String getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio The idMunicipio to set.
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}	

}
