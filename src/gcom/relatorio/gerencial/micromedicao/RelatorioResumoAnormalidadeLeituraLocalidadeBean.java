package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraLocalidadeBean implements
		RelatorioBean {

	private String localidade;

	private String setorComercial;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;

	private JRBeanCollectionDataSource arrayJRSetorComercial = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList arraySetorComercial = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraLocalidadeBean(
			String localidade, String setorComercial, String tipoLigacao,
			Collection colecaoSetorComercial, Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.tipoLigacao = tipoLigacao;
		this.arraySetorComercial = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.arraySetorComercial.addAll(colecaoSetorComercial);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRSetorComercial = new JRBeanCollectionDataSource(arraySetorComercial);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraLocalidadeBean(
			String localidade, String setorComercial, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.tipoLigacao = tipoLigacao;
		this.arrayAnormalidade = new ArrayList();
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

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

	public JRBeanCollectionDataSource getArrayJRSetorComercial() {
		return arrayJRSetorComercial;
	}

	public void setArrayJRSetorComercial(
			JRBeanCollectionDataSource arrayJRSetorComercial) {
		this.arrayJRSetorComercial = arrayJRSetorComercial;
	}

	public ArrayList getArraySetorComercial() {
		return arraySetorComercial;
	}

	public void setArraySetorComercial(ArrayList arraySetorComercial) {
		this.arraySetorComercial = arraySetorComercial;
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

}
