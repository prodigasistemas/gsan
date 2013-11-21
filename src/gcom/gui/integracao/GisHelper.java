package gcom.gui.integracao;

import gcom.cadastro.imovel.Imovel;

public class GisHelper {
	
	private Imovel imovel;
	
	//Coordenada norte da ocorrência
	private String nnCoordenadaNorte;	
	
	//Coordenada leste da ocorrência
	private String nnCoordenadaLeste;	 
	
	//Identificador da referência do endereço ? valor fixo=1
	private String dsPontoReferencia;
	
	private String informarCep;
	
	private String logradouro;
	
	private String localidade;
	
	private String setorComercial;
	
	private String login;
	
	private String hashValidacao;
	
	private	String nnCriticidade;
	
	private Short indicadorCoordenadaSemLogradouro;
	
	private String nnDiametro;
	

	public String getNnDiametro() {
		return nnDiametro;
	}

	public void setNnDiametro(String nnDiametro) {
		this.nnDiametro = nnDiametro;
	}

	public String getDsPontoReferencia() {
		return dsPontoReferencia;
	}

	public void setDsPontoReferencia(String dsPontoReferencia) {
		this.dsPontoReferencia = dsPontoReferencia;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getNnCriticidade() {
		return nnCriticidade;
	}

	public void setNnCriticidade(String nnCriticidade) {
		this.nnCriticidade = nnCriticidade;
	}

	public String getNnCoordenadaLeste() {
		return nnCoordenadaLeste;
	}

	public void setNnCoordenadaLeste(String nnCoordenadaLeste) {
		this.nnCoordenadaLeste = nnCoordenadaLeste;
	}

	public String getNnCoordenadaNorte() {
		return nnCoordenadaNorte;
	}

	public void setNnCoordenadaNorte(String nnCoordenadaNorte) {
		this.nnCoordenadaNorte = nnCoordenadaNorte;
	}

	public String getInformarCep() {
		return informarCep;
	}

	public void setInformarCep(String informarCep) {
		this.informarCep = informarCep;
	}

	/**
	 * @return Retorna o campo indicadorCoordenadaSemLogradouro.
	 */
	public short getIndicadorCoordenadaSemLogradouro() {
		return indicadorCoordenadaSemLogradouro;
	}

	/**
	 * @param indicadorCoordenadaSemLogradouro O indicadorCoordenadaSemLogradouro a ser setado.
	 */
	public void setIndicadorCoordenadaSemLogradouro(
			Short indicadorCoordenadaSemLogradouro) {
		this.indicadorCoordenadaSemLogradouro = indicadorCoordenadaSemLogradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getHashValidacao() {
		return hashValidacao;
	}

	public void setHashValidacao(String hashValidacao) {
		this.hashValidacao = hashValidacao;
	}
	
	/**
	 * Método para gerar uma url de chamada GIS
	 * @return Url da chamada do GIS
	 */
	public String gerarURLChamada(){
		String retorno = "";
		if(this.getImovel().getId() != null && !this.getImovel().getId().equals(""))
			retorno = "processarRequisicaoGisAction.do?"+
			"usur_nmlogin="+this.getLogin()+
			"&lgbr_id="+
			"&rgat_nncoordenadanorte="+this.getNnCoordenadaNorte()+
			"&rgat_nncoordenadaleste="+this.getNnCoordenadaNorte()+
			"&rgat_nncriticidade="+
			"&imov_id="+this.getImovel().getId();
		else
			retorno = "processarRequisicaoGisAction.do?"+
			"usur_nmlogin="+this.getLogin()+
			"&lgbr_id="+ this.getImovel().getLogradouroBairro().getId()+
			"&rgat_nncoordenadanorte="+this.getNnCoordenadaNorte()+
			"&rgat_nncoordenadaleste="+this.getNnCoordenadaNorte()+
			"&rgat_nncriticidade="+
			"&imov_id=";
		
			
		if(this.getLocalidade() != null && !this.getLocalidade().equals(""))
			retorno = retorno + "&loca_id="+this.getLocalidade();
		else
			retorno = retorno + "&loca_id=";
		
		if(this.getSetorComercial() != null && !this.getSetorComercial().equals(""))
			retorno = retorno +"&stcm_id="+this.getSetorComercial();
		else
			retorno = retorno +"&stcm_id=";
		
		retorno = retorno+"&sign="+this.getHashValidacao();
		
		return retorno;

	}


}
