package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Flávio Cordeiro
 */
public class RelatorioContasCanceladasRetificadasActionForm extends ValidatorActionForm {
    
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String localidadeFiltro;
	private String nomeLocalidade;
	private String[] idMotivo;
	private String[] idCategoria;
	private String[] idPerfil;
	private String[] idEsferaPoder;
	private String relatorioTipo;
	private String ordenacaoTipo;
	private String valor;
	private String responsavelFiltro;
	private String nomeResponsavel;
	private String idUnidadeNegocio;
	private String periodoInicial;
	private String periodoFinal;
	private String situacao;	
	//tipoConta = 1 - canceladas / 2 para retificadas
	private String tipoConta;
	private String grupo;
	private String idGerenciaRegional;
	
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getOrdenacaoTipo() {
		return ordenacaoTipo;
	}

	public void setOrdenacaoTipo(String ordenacaoTipo) {
		this.ordenacaoTipo = ordenacaoTipo;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getRelatorioTipo() {
		return relatorioTipo;
	}

	public void setRelatorioTipo(String relatorioTipo) {
		this.relatorioTipo = relatorioTipo;
	}

	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String[] getIdPerfil() {
		return idPerfil;
	}
	
	public void setIdPerfil(String[] idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	public String[] getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(String[] idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	public String getResponsavelFiltro() {
		return responsavelFiltro;
	}
	
	public void setResponsavelFiltro(String responsavelFiltro) {
		this.responsavelFiltro = responsavelFiltro;
	}
	
	public String[] getIdMotivo() {
		return idMotivo;
	}
	
	public void setIdMotivo(String[] idMotivo) {
		this.idMotivo = idMotivo;
	}
	
	public String getLocalidadeFiltro() {
		return localidadeFiltro;
	}
	
	public void setLocalidadeFiltro(String localidadeFiltro) {
		this.localidadeFiltro = localidadeFiltro;
	}
	
	public String getMesAno() {
		return mesAno;
	}
	
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	
	public String[] getIdEsferaPoder() {
		return idEsferaPoder;
	}
	
	public void setIdEsferaPoder(String[] idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	
}
