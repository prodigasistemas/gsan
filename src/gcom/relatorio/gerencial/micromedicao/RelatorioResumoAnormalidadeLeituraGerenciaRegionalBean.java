package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean implements
		RelatorioBean {

	private String gerenciaRegional;

	private String elo;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;

	private JRBeanCollectionDataSource arrayJRElo = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList arrayElo = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean(
			String gerenciaRegional, String elo, String tipoLigacao,
			Collection colecaoElo, Collection colecaoAnormalidade) {

		this.gerenciaRegional = gerenciaRegional;
		this.elo = elo;
		this.tipoLigacao = tipoLigacao;
		this.arrayElo = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.arrayElo.addAll(colecaoElo);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRElo = new JRBeanCollectionDataSource(arrayElo);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraGerenciaRegionalBean(
			String gerenciaRegional, String elo, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.gerenciaRegional = gerenciaRegional;
		this.elo = elo;
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

	public ArrayList getArrayElo() {
		return arrayElo;
	}

	public void setArrayElo(ArrayList arrayElo) {
		this.arrayElo = arrayElo;
	}

	public JRBeanCollectionDataSource getArrayJRAnormalidade() {
		return arrayJRAnormalidade;
	}

	public void setArrayJRAnormalidade(
			JRBeanCollectionDataSource arrayJRAnormalidade) {
		this.arrayJRAnormalidade = arrayJRAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRElo() {
		return arrayJRElo;
	}

	public void setArrayJRElo(JRBeanCollectionDataSource arrayJRElo) {
		this.arrayJRElo = arrayJRElo;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
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
