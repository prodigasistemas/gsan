package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 * @author Vivianne Sousa
 * @date 23/04/2010
 */
public class RelatorioExtratoDebitoDetailBean implements RelatorioBean {

	
	private JRBeanCollectionDataSource arrayContasJRDetail;
	private ArrayList arrayRelatorioExtratoDebitoContasDetailBean;
	
	private JRBeanCollectionDataSource arrayServicosJRDetail;
	private ArrayList arrayRelatorioExtratoDebitoServicosDetailBean;
	
	private String existeContas;
	private String existeServicos;
	
	public RelatorioExtratoDebitoDetailBean(
			Collection<RelatorioExtratoDebitoContasDetailBean> colecaoContasDetailBean,
			Collection<RelatorioExtratoDebitoServicosDetailBean> colecaoServicosDetailBean,
			String existeContas,String existeServicos) {
		
		this.arrayRelatorioExtratoDebitoContasDetailBean = new ArrayList();
		this.arrayRelatorioExtratoDebitoContasDetailBean.addAll(colecaoContasDetailBean);
		this.arrayContasJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioExtratoDebitoContasDetailBean);
		
		this.arrayRelatorioExtratoDebitoServicosDetailBean = new ArrayList();
		this.arrayRelatorioExtratoDebitoServicosDetailBean.addAll(colecaoServicosDetailBean);
		this.arrayServicosJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioExtratoDebitoServicosDetailBean);
		
		this.existeContas = existeContas;
		this.existeServicos = existeServicos;
		
	}
	public JRBeanCollectionDataSource getArrayContasJRDetail() {
		return arrayContasJRDetail;
	}
	public void setArrayContasJRDetail(
			JRBeanCollectionDataSource arrayContasJRDetail) {
		this.arrayContasJRDetail = arrayContasJRDetail;
	}
	public ArrayList getArrayRelatorioExtratoDebitoContasDetailBean() {
		return arrayRelatorioExtratoDebitoContasDetailBean;
	}
	public void setArrayRelatorioExtratoDebitoContasDetailBean(
			ArrayList arrayRelatorioExtratoDebitoContasDetailBean) {
		this.arrayRelatorioExtratoDebitoContasDetailBean = arrayRelatorioExtratoDebitoContasDetailBean;
	}
	public ArrayList getArrayRelatorioExtratoDebitoServicosDetailBean() {
		return arrayRelatorioExtratoDebitoServicosDetailBean;
	}
	public void setArrayRelatorioExtratoDebitoServicosDetailBean(
			ArrayList arrayRelatorioExtratoDebitoServicosDetailBean) {
		this.arrayRelatorioExtratoDebitoServicosDetailBean = arrayRelatorioExtratoDebitoServicosDetailBean;
	}
	public JRBeanCollectionDataSource getArrayServicosJRDetail() {
		return arrayServicosJRDetail;
	}
	public void setArrayServicosJRDetail(
			JRBeanCollectionDataSource arrayServicosJRDetail) {
		this.arrayServicosJRDetail = arrayServicosJRDetail;
	}
	public String getExisteContas() {
		return existeContas;
	}
	public void setExisteContas(String existeContas) {
		this.existeContas = existeContas;
	}
	public String getExisteServicos() {
		return existeServicos;
	}
	public void setExisteServicos(String existeServicos) {
		this.existeServicos = existeServicos;
	}
	
}
