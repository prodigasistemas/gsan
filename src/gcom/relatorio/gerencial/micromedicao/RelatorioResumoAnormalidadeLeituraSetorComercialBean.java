package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraSetorComercialBean implements
		RelatorioBean {

	private String localidade;

	private String setorComercial;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;
	
	private String numeroQuadra;

	private JRBeanCollectionDataSource arrayJR = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList array = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraSetorComercialBean(
			String localidade, String setorComercial, String numeroQuadra, String tipoLigacao,
			Collection colecaoQuadra, Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.numeroQuadra = numeroQuadra;
		this.tipoLigacao = tipoLigacao;
		this.array = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.array.addAll(colecaoQuadra);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJR = new JRBeanCollectionDataSource(array);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraSetorComercialBean(
			String localidade, String setorComercial, String numeroQuadra, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.numeroQuadra = numeroQuadra;
		this.tipoLigacao = tipoLigacao;
		this.arrayAnormalidade = new ArrayList();
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}

	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

	public ArrayList getArray() {
		return array;
	}

	public void setArray(ArrayList array) {
		this.array = array;
	}

	public JRBeanCollectionDataSource getArrayJR() {
		return arrayJR;
	}

	public void setArrayJR(JRBeanCollectionDataSource arrayJR) {
		this.arrayJR = arrayJR;
	}

	public ArrayList getArrayAnormalidade() {
		return arrayAnormalidade;
	}

	public void setArrayAnormalidade(ArrayList arrayAnormalidade) {
		this.arrayAnormalidade = arrayAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRAnormalidade() {
		return arrayJRAnormalidade;
	}

	public void setArrayJRAnormalidade(
			JRBeanCollectionDataSource arrayJRAnormalidade) {
		this.arrayJRAnormalidade = arrayJRAnormalidade;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

}
