package gcom.gui.cadastro.localidade;

import org.apache.struts.action.ActionForm;

public class AdicionarQuadraFaceActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String acao;
	
	private String numeroFace;
	
	private String baciaID;

    private String distritoOperacionalDescricao;

    private String distritoOperacionalID;

    private String indicadorRedeAguaAux;

    private String indicadorRedeEsgotoAux;
    
    private String sistemaEsgotoID;
    
    private String grauDificuldadeExecucaoID;
    
    private String grauRiscoSegurancaFisicaID;
    
    private String nivelPressaoID;
    
    private String grauIntermitenciaID;

    public String getBaciaID() {
		return baciaID;
	}

	public void setBaciaID(String baciaID) {
		this.baciaID = baciaID;
	}

	public String getDistritoOperacionalDescricao() {
		return distritoOperacionalDescricao;
	}

	public void setDistritoOperacionalDescricao(String distritoOperacionalDescricao) {
		this.distritoOperacionalDescricao = distritoOperacionalDescricao;
	}

	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}

	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
	}

	public String getIndicadorRedeAguaAux() {
		return indicadorRedeAguaAux;
	}

	public void setIndicadorRedeAguaAux(String indicadorRedeAguaAux) {
		this.indicadorRedeAguaAux = indicadorRedeAguaAux;
	}

	public String getIndicadorRedeEsgotoAux() {
		return indicadorRedeEsgotoAux;
	}

	public void setIndicadorRedeEsgotoAux(String indicadorRedeEsgotoAux) {
		this.indicadorRedeEsgotoAux = indicadorRedeEsgotoAux;
	}

	public String getSistemaEsgotoID() {
		return sistemaEsgotoID;
	}

	public void setSistemaEsgotoID(String sistemaEsgotoID) {
		this.sistemaEsgotoID = sistemaEsgotoID;
	}

	public String getNumeroFace() {
		return numeroFace;
	}

	public void setNumeroFace(String numeroFace) {
		this.numeroFace = numeroFace;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getGrauDificuldadeExecucaoID() {
		return grauDificuldadeExecucaoID;
	}

	public void setGrauDificuldadeExecucaoID(String grauDificuldadeExecucaoID) {
		this.grauDificuldadeExecucaoID = grauDificuldadeExecucaoID;
	}

	public String getGrauIntermitenciaID() {
		return grauIntermitenciaID;
	}

	public void setGrauIntermitenciaID(String grauIntermitenciaID) {
		this.grauIntermitenciaID = grauIntermitenciaID;
	}

	public String getGrauRiscoSegurancaFisicaID() {
		return grauRiscoSegurancaFisicaID;
	}

	public void setGrauRiscoSegurancaFisicaID(String grauRiscoSegurancaFisicaID) {
		this.grauRiscoSegurancaFisicaID = grauRiscoSegurancaFisicaID;
	}

	public String getNivelPressaoID() {
		return nivelPressaoID;
	}

	public void setNivelPressaoID(String nivelPressaoID) {
		this.nivelPressaoID = nivelPressaoID;
	}
	
}
