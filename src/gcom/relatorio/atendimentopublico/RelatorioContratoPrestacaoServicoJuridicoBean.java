package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Corrêa
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioContratoPrestacaoServicoJuridicoBean implements RelatorioBean {

	public String nomePresidente;
	public String cpfPresidente;
	public String rgPresidente;
	public String profissaoPresidente;
	public String nomeDiretor;
	public String cpfDiretor;
	public String rgDiretor;
	public String profissaoDiretor;
	public String nomeUsuario;
	public String cnpjUsuario;
	public String enderecoImovel;
	public String matriculaImovel;
	public String nomeRepresentante;
	public String cpfRepresentante;
	public String rgRepresentante;
	public String municipio;
	public String data;
	public String numeroPagina;
	public String esferaPoder;
	public String numeroContrato;

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getCnpjUsuario() {
		return cnpjUsuario;
	}

	public void setCnpjUsuario(String cnpjUsuario) {
		this.cnpjUsuario = cnpjUsuario;
	}

	public String getCpfDiretor() {
		return cpfDiretor;
	}

	public void setCpfDiretor(String cpfDiretor) {
		this.cpfDiretor = cpfDiretor;
	}

	public String getCpfPresidente() {
		return cpfPresidente;
	}

	public void setCpfPresidente(String cpfPresidente) {
		this.cpfPresidente = cpfPresidente;
	}

	public String getCpfRepresentante() {
		return cpfRepresentante;
	}

	public void setCpfRepresentante(String cpfRepresentante) {
		this.cpfRepresentante = cpfRepresentante;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public String getNomeRepresentante() {
		return nomeRepresentante;
	}

	public void setNomeRepresentante(String nomeRepresentante) {
		this.nomeRepresentante = nomeRepresentante;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getProfissaoDiretor() {
		return profissaoDiretor;
	}

	public void setProfissaoDiretor(String profissaoDiretor) {
		this.profissaoDiretor = profissaoDiretor;
	}

	public String getProfissaoPresidente() {
		return profissaoPresidente;
	}

	public void setProfissaoPresidente(String profissaoPresidente) {
		this.profissaoPresidente = profissaoPresidente;
	}

	public String getRgDiretor() {
		return rgDiretor;
	}

	public void setRgDiretor(String rgDiretor) {
		this.rgDiretor = rgDiretor;
	}

	public String getRgPresidente() {
		return rgPresidente;
	}

	public void setRgPresidente(String rgPresidente) {
		this.rgPresidente = rgPresidente;
	}

	public String getRgRepresentante() {
		return rgRepresentante;
	}

	public void setRgRepresentante(String rgRepresentante) {
		this.rgRepresentante = rgRepresentante;
	}

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
	public RelatorioContratoPrestacaoServicoJuridicoBean(
			String nomePresidente,
			String cpfPresidente,
			String rgPresidente,
			String profissaoPresidente,
			String nomeDiretor,
			String cpfDiretor,
			String rgDiretor,
			String profissaoDiretor,
			String nomeUsuario,
			String cnpjUsuario,
			String enderecoImovel,
			String matriculaImovel,
			String nomeRepresentante,
			String cpfRepresentante,
			String rgRepresentante,
			String municipio,
			String data, 
			String esferaPoder,
			String numeroContrato){
		
		this.nomePresidente = nomePresidente;
		this.cpfPresidente = cpfPresidente;
		this.rgPresidente = rgPresidente;
		this.profissaoPresidente = profissaoPresidente;
		this.nomeDiretor = nomeDiretor;
		this.cpfDiretor = cpfDiretor;
		this.rgDiretor = rgDiretor;
		this.profissaoDiretor = profissaoDiretor;
		this.nomeUsuario = nomeUsuario;
		this.cnpjUsuario = cnpjUsuario;
		this.enderecoImovel = enderecoImovel;
		this.matriculaImovel = matriculaImovel;
		this.nomeRepresentante = nomeRepresentante;
		this.cpfRepresentante = cpfRepresentante;
		this.rgRepresentante  = rgRepresentante;
		this.municipio = municipio;
		this.data = data;
		this.esferaPoder = esferaPoder;
		this.numeroContrato = numeroContrato;
	}
	
	public RelatorioContratoPrestacaoServicoJuridicoBean(String numeroPagina){
		this.numeroPagina = numeroPagina;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
}
