package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraEstadoBean implements
		RelatorioBean {

	private String estado;

	private String gerenciaRegional;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;

	private JRBeanCollectionDataSource arrayJRGerenciaRegional = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList arrayGerencialRegional = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraEstadoBean(
			String estado, String gerenciaRegional, String tipoLigacao,
			Collection colecaoGerenciaRegional, Collection colecaoAnormalidade) {

		this.gerenciaRegional = gerenciaRegional;
		this.estado = estado;
		this.tipoLigacao = tipoLigacao;
		this.arrayGerencialRegional = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.arrayGerencialRegional.addAll(colecaoGerenciaRegional);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRGerenciaRegional = new JRBeanCollectionDataSource(arrayGerencialRegional);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraEstadoBean(
			String estado, String gerenciaRegional, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.gerenciaRegional = gerenciaRegional;
		this.estado = estado;
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

	public ArrayList getArrayGerencialRegional() {
		return arrayGerencialRegional;
	}

	public void setArrayGerencialRegional(ArrayList arrayGerencialRegional) {
		this.arrayGerencialRegional = arrayGerencialRegional;
	}

	public JRBeanCollectionDataSource getArrayJRAnormalidade() {
		return arrayJRAnormalidade;
	}

	public void setArrayJRAnormalidade(
			JRBeanCollectionDataSource arrayJRAnormalidade) {
		this.arrayJRAnormalidade = arrayJRAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRGerenciaRegional() {
		return arrayJRGerenciaRegional;
	}

	public void setArrayJRGerenciaRegional(
			JRBeanCollectionDataSource arrayJRGerenciaRegional) {
		this.arrayJRGerenciaRegional = arrayJRGerenciaRegional;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

}
