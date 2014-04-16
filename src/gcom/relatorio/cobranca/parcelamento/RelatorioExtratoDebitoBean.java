package gcom.relatorio.cobranca.parcelamento;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 * @author Vivianne Sousa
 * @date 07/09/2006
 */
public class RelatorioExtratoDebitoBean implements RelatorioBean {
	
	
	private String pagina1;
	
	//Linha 1
	private String empresa;
	private String dataEmissao;
	private String usuarioEmissao;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioExtratoDebitoDetailBean;
	
	public RelatorioExtratoDebitoBean(Collection colecaoDetail,String pagina1) {

		this.arrayRelatorioExtratoDebitoDetailBean = new ArrayList();
		this.arrayRelatorioExtratoDebitoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioExtratoDebitoDetailBean);
		
		this.pagina1 = pagina1;
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}


	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}


	public ArrayList getArrayRelatorioExtratoDebitoDetailBean() {
		return arrayRelatorioExtratoDebitoDetailBean;
	}


	public void setArrayRelatorioExtratoDebitoDetailBean(
			ArrayList arrayRelatorioExtratoDebitoDetailBean) {
		this.arrayRelatorioExtratoDebitoDetailBean = arrayRelatorioExtratoDebitoDetailBean;
	}


	public String getPagina1() {
		return pagina1;
	}


	public void setPagina1(String pagina1) {
		this.pagina1 = pagina1;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getUsuarioEmissao() {
		return usuarioEmissao;
	}

	public void setUsuarioEmissao(String usuarioEmissao) {
		this.usuarioEmissao = usuarioEmissao;
	}



	
}
