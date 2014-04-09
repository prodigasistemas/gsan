package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

/**
 * Gerar e Emitir Extrato de Débito por Cliente
 * @author Ana Maria
 * @date 09/04/2007
 */

public class RelatorioExtratoDebitoClienteBean implements RelatorioBean {
		
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;

	private ArrayList arrayRelatorioExtratoDebitoClienteDetailBean;

	public RelatorioExtratoDebitoClienteBean(String indicadorPrimeiraPagina, Collection colecaoDetail) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
		this.arrayRelatorioExtratoDebitoClienteDetailBean = new ArrayList();
		this.arrayRelatorioExtratoDebitoClienteDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioExtratoDebitoClienteDetailBean);
	}

	/**
	 * @return Retorna o campo arrayJRDetail.
	 */
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	/**
	 * @param arrayJRDetail O arrayJRDetail a ser setado.
	 */
	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	/**
	 * @return Retorna o campo arrayRelatorioExtratoDebitoClienteDetailBean.
	 */
	public ArrayList getArrayRelatorioExtratoDebitoClienteDetailBean() {
		return arrayRelatorioExtratoDebitoClienteDetailBean;
	}

	/**
	 * @param arrayRelatorioExtratoDebitoClienteDetailBean O arrayRelatorioExtratoDebitoClienteDetailBean a ser setado.
	 */
	public void setArrayRelatorioExtratoDebitoClienteDetailBean(
			ArrayList arrayRelatorioExtratoDebitoClienteDetailBean) {
		this.arrayRelatorioExtratoDebitoClienteDetailBean = arrayRelatorioExtratoDebitoClienteDetailBean;
	}

	/**
	 * @return Retorna o campo indicadorPrimeiraPagina.
	 */
	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	/**
	 * @param indicadorPrimeiraPagina O indicadorPrimeiraPagina a ser setado.
	 */
	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}
	

	
}
