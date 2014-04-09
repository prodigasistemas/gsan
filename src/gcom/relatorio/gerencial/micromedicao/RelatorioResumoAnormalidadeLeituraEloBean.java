package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraEloBean implements
		RelatorioBean {

	private String localidade;

	private String elo;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;

	private JRBeanCollectionDataSource arrayJRLocalidade = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList arrayLocalidade = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraEloBean(
			String elo, String localidade, String tipoLigacao,
			Collection colecaoLocalidade, Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.elo = elo;
		this.tipoLigacao = tipoLigacao;
		this.arrayLocalidade = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.arrayLocalidade.addAll(colecaoLocalidade);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRLocalidade = new JRBeanCollectionDataSource(arrayLocalidade);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraEloBean(
			String elo, String localidade, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.localidade = localidade;
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

	public JRBeanCollectionDataSource getArrayJRAnormalidade() {
		return arrayJRAnormalidade;
	}

	public void setArrayJRAnormalidade(
			JRBeanCollectionDataSource arrayJRAnormalidade) {
		this.arrayJRAnormalidade = arrayJRAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRLocalidade() {
		return arrayJRLocalidade;
	}

	public void setArrayJRLocalidade(JRBeanCollectionDataSource arrayJRLocalidade) {
		this.arrayJRLocalidade = arrayJRLocalidade;
	}

	public ArrayList getArrayLocalidade() {
		return arrayLocalidade;
	}

	public void setArrayLocalidade(ArrayList arrayLocalidade) {
		this.arrayLocalidade = arrayLocalidade;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
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

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

	

}
