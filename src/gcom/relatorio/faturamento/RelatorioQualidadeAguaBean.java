package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioQualidadeAguaBean implements RelatorioBean {

	private String mesAno;

	private String idLocalidade;

	private String codigoSetorComercial;

	private String fonteCaptacao;

	private String turbidezIndice;
	
	private String cloroIndice;
	private String phIndice;
	private String corIndice;
	private String fluorIndice;
	private String ferroIndice;
	private String coliformesFecaisIndice;
	private String coliformesTotaisIndice;
	private String coliformesTermotolerantesIndice;
	private String nitratoIndice;
	
	private String municipio;
	
	//flávio leonardo
	private String quantidadeTurbidezExigidas;
    
    private String quantidadeTurbidezAnalisadas;
    
    private String quantidadeTurbidezConforme;
    
    private String quantidadeCorExigidas;
    
    private String quantidadeCorAnalisadas;
    
    private String quantidadeCorConforme;
    
    private String quantidadeCloroExigidas;
    
    private String quantidadeCloroAnalisadas;
    
    private String quantidadeCloroConforme;
    
    private String quantidadeFluorExigidas;
    
    private String quantidadeFluorAnalisadas;
    
    private String quantidadeFluorConforme;
    
    private String quantidadeColiformesTotaisExigidas;
    
    private String quantidadeColiformesTotaisAnalisadas;
    
    private String quantidadeColiformesTotaisConforme;
    
    private String quantidadeColiformesFecaisExigidas;
    
    private String quantidadeColiformesFecaisAnalisadas;
    
    private String quantidadeColiformesFecaisConforme;
    
    private String quantidadeColiformesTermotolerantesExigidas;
    
    private String quantidadeColiformesTermotolerantesAnalisadas;
    
    private String quantidadeColiformesTermotolerantesConforme;
    
    private RelatorioQualidadeAguaBean relatorioQualidadeAguaBeanSub;
    
    private JRBeanCollectionDataSource arrayJrItens;
	private ArrayList arrayRelatorioQualidadeAguaBean;	
	
	//Fernando Fontelles Filho
	private String alcalinidadeIndice;
	
	private String quantidadeAlcalinidadeExigidas;
	
	private String quantidadeAlcalinidadeAnalisadas;
	
	private String quantidadeAlcalinidadeConforme;
	
	private String sistemaAbastecimento;

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
	public RelatorioQualidadeAguaBean(String municipio, String mesAno,
			String idLocalidade,
			String codigoSetorComercial,
			String fonteCaptacao,
			String turbidezIndice,
			String cloroIndice,
			String phIndice,
			String corIndice,
			String fluorIndice,
			String ferroIndice,
			String coliformesFecaisIndice,
			String coliformesTotaisIndice,
			String nitratoIndice,
			String alcalinidadeIndice) {
		
		this.municipio = municipio;
		this.mesAno = mesAno;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.fonteCaptacao = fonteCaptacao;
		this.turbidezIndice = turbidezIndice;
		this.cloroIndice = cloroIndice;
		this.phIndice = phIndice;
		this.corIndice = corIndice;
		this.fluorIndice = fluorIndice;
		this.ferroIndice = ferroIndice;
		this.coliformesFecaisIndice = coliformesFecaisIndice;
		this.coliformesTotaisIndice = coliformesTotaisIndice;
		this.nitratoIndice = nitratoIndice;
		this.alcalinidadeIndice = alcalinidadeIndice;
		
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
	public RelatorioQualidadeAguaBean(String municipio, String mesAno,
			String idLocalidade,
			String codigoSetorComercial,
			String fonteCaptacao,
			String turbidezIndice,
			String cloroIndice,
			String phIndice,
			String corIndice,
			String fluorIndice,
			String ferroIndice,
			String coliformesFecaisIndice,
			String coliformesTotaisIndice,
			String nitratoIndice,
			String quantidadeTurbidezExigidas,
			String quantidadeTurbidezAnalisadas,
			String quantidadeTurbidezConforme,
			String quantidadeCorExigidas,
			String quantidadeCorAnalisadas,
			String quantidadeCorConforme,
			String quantidadeCloroExigidas,
			String quantidadeCloroAnalisadas,
			String quantidadeCloroConforme,
			String quantidadeFluorExigidas,
			String quantidadeFluorAnalisadas,
			String quantidadeFluorConforme,
			String quantidadeColiformesTotaisExigidas,
			String quantidadeColiformesTotaisAnalisadas,
			String quantidadeColiformesTotaisConforme,
			String quantidadeColiformesFecaisExigidas,
			String quantidadeColiformesFecaisAnalisadas,
			String quantidadeColiformesFecaisConforme,
			String quantidadeColiformesTermotolerantesExigidas,
			String quantidadeColiformesTermotolerantesAnalisadas,
			String quantidadeColiformesTermotolerantesConforme,
			String coliformesTermotolerantesIndice,
			boolean aparecerSubRelatorio,
			String alcalinidadeIndice,
			String quantidadeAlcalinidadeExigidas,
			String quantidadeAlcalinidadeAnalisadas,
			String quantidadeAlcalinidadeConforme,
			String sistemaAbastecimento){
		
		this.municipio = municipio;
		this.mesAno = mesAno;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.fonteCaptacao = fonteCaptacao;
		this.turbidezIndice = turbidezIndice;
		this.cloroIndice = cloroIndice;
		this.phIndice = phIndice;
		this.corIndice = corIndice;
		this.fluorIndice = fluorIndice;
		this.ferroIndice = ferroIndice;
		this.coliformesFecaisIndice = coliformesFecaisIndice;
		this.coliformesTotaisIndice = coliformesTotaisIndice;
		this.nitratoIndice = nitratoIndice;
		this.coliformesTermotolerantesIndice = coliformesTermotolerantesIndice;
		this.alcalinidadeIndice = alcalinidadeIndice;
		this.sistemaAbastecimento = sistemaAbastecimento;
		
		if(aparecerSubRelatorio){
			RelatorioQualidadeAguaBean relatorioQualidadeAguaBeanSub = new RelatorioQualidadeAguaBean(
			quantidadeTurbidezExigidas,
			quantidadeTurbidezAnalisadas,
			quantidadeTurbidezConforme,
			quantidadeCorExigidas,
			quantidadeCorAnalisadas,
			quantidadeCorConforme,
			quantidadeCloroExigidas,
			quantidadeCloroAnalisadas,
			quantidadeCloroConforme,
			quantidadeFluorExigidas,
			quantidadeFluorAnalisadas,
			quantidadeFluorConforme,
			quantidadeColiformesTotaisExigidas,
			quantidadeColiformesTotaisAnalisadas,
			quantidadeColiformesTotaisConforme,
			quantidadeColiformesFecaisExigidas,
			quantidadeColiformesFecaisAnalisadas,
			quantidadeColiformesFecaisConforme,
			quantidadeColiformesTermotolerantesExigidas,
			quantidadeColiformesTermotolerantesAnalisadas,
			quantidadeColiformesTermotolerantesConforme,
			quantidadeAlcalinidadeAnalisadas,
			quantidadeAlcalinidadeConforme,
			quantidadeAlcalinidadeExigidas);
			
			this.relatorioQualidadeAguaBeanSub = relatorioQualidadeAguaBeanSub;
			
			this.arrayRelatorioQualidadeAguaBean = new ArrayList();
			this.arrayRelatorioQualidadeAguaBean
					.add(relatorioQualidadeAguaBeanSub);
			this.arrayJrItens = new JRBeanCollectionDataSource(
					this.arrayRelatorioQualidadeAguaBean);
		}
	}
	
	public RelatorioQualidadeAguaBean(
			String quantidadeTurbidezExigidas,
			String quantidadeTurbidezAnalisadas,
			String quantidadeTurbidezConforme,
			String quantidadeCorExigidas,
			String quantidadeCorAnalisadas,
			String quantidadeCorConforme,
			String quantidadeCloroExigidas,
			String quantidadeCloroAnalisadas,
			String quantidadeCloroConforme,
			String quantidadeFluorExigidas,
			String quantidadeFluorAnalisadas,
			String quantidadeFluorConforme,
			String quantidadeColiformesTotaisExigidas,
			String quantidadeColiformesTotaisAnalisadas,
			String quantidadeColiformesTotaisConforme,
			String quantidadeColiformesFecaisExigidas,
			String quantidadeColiformesFecaisAnalisadas,
			String quantidadeColiformesFecaisConforme,
			String quantidadeColiformesTermotolerantesExigidas,
			String quantidadeColiformesTermotolerantesAnalisadas,
			String quantidadeColiformesTermotolerantesConforme,
			String quantidadeAlcalinidadeAnalisadas,
			String quantidadeAlcalinidadeConforme,
			String quantidadeAlcalinidadeExigidas){
		
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
		this.quantidadeCorExigidas = quantidadeCorExigidas;
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
		this.quantidadeCorConforme = quantidadeCorConforme;
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
		this.quantidadeCloroConforme = quantidadeCloroConforme;
		this.quantidadeFluorExigidas = quantidadeFluorExigidas;
		this.quantidadeFluorAnalisadas = quantidadeFluorAnalisadas;
		this.quantidadeFluorConforme = quantidadeFluorConforme;
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
		this.quantidadeColiformesFecaisExigidas = quantidadeColiformesFecaisExigidas;
		this.quantidadeColiformesFecaisAnalisadas = quantidadeColiformesFecaisAnalisadas;
		this.quantidadeColiformesFecaisConforme = quantidadeColiformesFecaisConforme;
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
		this.quantidadeAlcalinidadeAnalisadas = quantidadeAlcalinidadeAnalisadas;
		this.quantidadeAlcalinidadeConforme = quantidadeAlcalinidadeConforme;
		this.quantidadeAlcalinidadeExigidas = quantidadeAlcalinidadeExigidas;
		
	}

	public String getCloroIndice() {
		return cloroIndice;
	}

	public void setCloroIndice(String cloroIndice) {
		this.cloroIndice = cloroIndice;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getColiformesFecaisIndice() {
		return coliformesFecaisIndice;
	}

	public void setColiformesFecaisIndice(String coliformesFecaisIndice) {
		this.coliformesFecaisIndice = coliformesFecaisIndice;
	}

	public String getColiformesTotaisIndice() {
		return coliformesTotaisIndice;
	}

	public void setColiformesTotaisIndice(String coliformesTotaisIndice) {
		this.coliformesTotaisIndice = coliformesTotaisIndice;
	}

	public String getCorIndice() {
		return corIndice;
	}

	public void setCorIndice(String corIndice) {
		this.corIndice = corIndice;
	}

	public String getFerroIndice() {
		return ferroIndice;
	}

	public void setFerroIndice(String ferroIndice) {
		this.ferroIndice = ferroIndice;
	}

	public String getFluorIndice() {
		return fluorIndice;
	}

	public void setFluorIndice(String fluorIndice) {
		this.fluorIndice = fluorIndice;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNitratoIndice() {
		return nitratoIndice;
	}

	public void setNitratoIndice(String nitratoIndice) {
		this.nitratoIndice = nitratoIndice;
	}

	public String getPhIndice() {
		return phIndice;
	}

	public void setPhIndice(String phIndice) {
		this.phIndice = phIndice;
	}

	public String getTurbidezIndice() {
		return turbidezIndice;
	}

	public void setTurbidezIndice(String turbidezIndice) {
		this.turbidezIndice = turbidezIndice;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}

	public void setQuantidadeCloroAnalisadas(String quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}

	public String getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}

	public void setQuantidadeCloroConforme(String quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}

	public String getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}

	public void setQuantidadeCloroExigidas(String quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}

	public String getQuantidadeColiformesFecaisAnalisadas() {
		return quantidadeColiformesFecaisAnalisadas;
	}

	public void setQuantidadeColiformesFecaisAnalisadas(
			String quantidadeColiformesFecaisAnalisadas) {
		this.quantidadeColiformesFecaisAnalisadas = quantidadeColiformesFecaisAnalisadas;
	}

	public String getQuantidadeColiformesFecaisConforme() {
		return quantidadeColiformesFecaisConforme;
	}

	public void setQuantidadeColiformesFecaisConforme(
			String quantidadeColiformesFecaisConforme) {
		this.quantidadeColiformesFecaisConforme = quantidadeColiformesFecaisConforme;
	}

	public String getQuantidadeColiformesFecaisExigidas() {
		return quantidadeColiformesFecaisExigidas;
	}

	public void setQuantidadeColiformesFecaisExigidas(
			String quantidadeColiformesFecaisExigidas) {
		this.quantidadeColiformesFecaisExigidas = quantidadeColiformesFecaisExigidas;
	}

	public String getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}

	public void setQuantidadeColiformesTermotolerantesAnalisadas(
			String quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}

	public String getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}

	public void setQuantidadeColiformesTermotolerantesConforme(
			String quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}

	public String getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}

	public void setQuantidadeColiformesTermotolerantesExigidas(
			String quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}

	public String getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}

	public void setQuantidadeColiformesTotaisAnalisadas(
			String quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}

	public String getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}

	public void setQuantidadeColiformesTotaisConforme(
			String quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}

	public String getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}

	public void setQuantidadeColiformesTotaisExigidas(
			String quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}

	public String getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}

	public void setQuantidadeCorAnalisadas(String quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}

	public String getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}

	public void setQuantidadeCorConforme(String quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}

	public String getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}

	public void setQuantidadeCorExigidas(String quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}

	public String getQuantidadeFluorAnalisadas() {
		return quantidadeFluorAnalisadas;
	}

	public void setQuantidadeFluorAnalisadas(String quantidadeFluorAnalisadas) {
		this.quantidadeFluorAnalisadas = quantidadeFluorAnalisadas;
	}

	public String getQuantidadeFluorConforme() {
		return quantidadeFluorConforme;
	}

	public void setQuantidadeFluorConforme(String quantidadeFluorConforme) {
		this.quantidadeFluorConforme = quantidadeFluorConforme;
	}

	public String getQuantidadeFluorExigidas() {
		return quantidadeFluorExigidas;
	}

	public void setQuantidadeFluorExigidas(String quantidadeFluorExigidas) {
		this.quantidadeFluorExigidas = quantidadeFluorExigidas;
	}

	public String getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}

	public void setQuantidadeTurbidezAnalisadas(String quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}

	public String getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}

	public void setQuantidadeTurbidezConforme(String quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}

	public String getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}

	public void setQuantidadeTurbidezExigidas(String quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}

	public String getColiformesTermotolerantesIndice() {
		return coliformesTermotolerantesIndice;
	}

	public void setColiformesTermotolerantesIndice(
			String coliformesTermotolerantesIndice) {
		this.coliformesTermotolerantesIndice = coliformesTermotolerantesIndice;
	}

	public RelatorioQualidadeAguaBean getRelatorioQualidadeAguaBeanSub() {
		return relatorioQualidadeAguaBeanSub;
	}

	public void setRelatorioQualidadeAguaBeanSub(
			RelatorioQualidadeAguaBean relatorioQualidadeAguaBeanSub) {
		this.relatorioQualidadeAguaBeanSub = relatorioQualidadeAguaBeanSub;
	}

	public JRBeanCollectionDataSource getArrayJrItens() {
		return arrayJrItens;
	}

	public void setArrayJrItens(JRBeanCollectionDataSource arrayJrItens) {
		this.arrayJrItens = arrayJrItens;
	}

	public ArrayList getArrayRelatorioQualidadeAguaBean() {
		return arrayRelatorioQualidadeAguaBean;
	}

	public void setArrayRelatorioQualidadeAguaBean(
			ArrayList arrayRelatorioQualidadeAguaBean) {
		this.arrayRelatorioQualidadeAguaBean = arrayRelatorioQualidadeAguaBean;
	}

	public String getQuantidadeAlcalinidadeAnalisadas() {
		return quantidadeAlcalinidadeAnalisadas;
	}

	public void setQuantidadeAlcalinidadeAnalisadas(
			String quantidadeAlcalinidadeAnalisadas) {
		this.quantidadeAlcalinidadeAnalisadas = quantidadeAlcalinidadeAnalisadas;
	}

	public String getQuantidadeAlcalinidadeConforme() {
		return quantidadeAlcalinidadeConforme;
	}

	public void setQuantidadeAlcalinidadeConforme(
			String quantidadeAlcalinidadeConforme) {
		this.quantidadeAlcalinidadeConforme = quantidadeAlcalinidadeConforme;
	}

	public String getQuantidadeAlcalinidadeExigidas() {
		return quantidadeAlcalinidadeExigidas;
	}

	public void setQuantidadeAlcalinidadeExigidas(
			String quantidadeAlcalinidadeExigidas) {
		this.quantidadeAlcalinidadeExigidas = quantidadeAlcalinidadeExigidas;
	}

	public String getAlcalinidadeIndice() {
		return alcalinidadeIndice;
	}

	public void setAlcalinidadeIndice(String alcalinidadeIndice) {
		this.alcalinidadeIndice = alcalinidadeIndice;
	}
	
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
	

}
