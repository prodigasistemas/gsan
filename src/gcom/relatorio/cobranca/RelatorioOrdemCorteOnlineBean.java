package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioOrdemCorteOnlineBean implements RelatorioBean {
	
	private String qtdeEconResidencial;
	private String qtdeEconComercial;
	private String qtdeEconIndustrial;
	private String qtdeEconPublica;
	private String qtdeEconTotal;
	private JRBeanCollectionDataSource arrayContasJRDetail;
	
	public RelatorioOrdemCorteOnlineBean(String qtdeEconResidencial,
			String qtdeEconComercial, String qtdeEconIndustrial,
			String qtdeEconPublica, String qtdeEconTotal,
			JRBeanCollectionDataSource arrayContasJRDetail) {
		this.qtdeEconResidencial = qtdeEconResidencial;
		this.qtdeEconComercial = qtdeEconComercial;
		this.qtdeEconIndustrial = qtdeEconIndustrial;
		this.qtdeEconPublica = qtdeEconPublica;
		this.qtdeEconTotal = qtdeEconTotal;
		this.arrayContasJRDetail = arrayContasJRDetail;
	}
	public String getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}
	public void setQtdeEconResidencial(String qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}
	public String getQtdeEconComercial() {
		return qtdeEconComercial;
	}
	public void setQtdeEconComercial(String qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}
	public String getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}
	public void setQtdeEconIndustrial(String qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}
	public String getQtdeEconPublica() {
		return qtdeEconPublica;
	}
	public void setQtdeEconPublica(String qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}
	public String getQtdeEconTotal() {
		return qtdeEconTotal;
	}
	public void setQtdeEconTotal(String qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}
	public JRBeanCollectionDataSource getArrayContasJRDetail() {
		return arrayContasJRDetail;
	}
	public void setArrayContasJRDetail(
			JRBeanCollectionDataSource arrayContasJRDetail) {
		this.arrayContasJRDetail = arrayContasJRDetail;
	}
	
	
}
