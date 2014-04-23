package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;
import gcom.relatorio.atendimentopublico.OSExecutadasRelatorioHelper;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * @author Vivianne Sousa
 * @date 15/04/2011
 */
public class RelatorioOSExecutadasPrestadoraServicoDetailBean implements RelatorioBean {
	
	private String descricaoServicos; 
	private String idServicos; 
	private String qtdeOs;
	
	public RelatorioOSExecutadasPrestadoraServicoDetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RelatorioOSExecutadasPrestadoraServicoDetailBean(OSExecutadasRelatorioHelper helper) {
		super();
		// TODO Auto-generated constructor stub
		this.descricaoServicos = helper.getDescServicosOSExecutadas();
		this.idServicos = helper.getCodigoServicosOSExecutadas();
		this.qtdeOs = helper.getQtdeServicosOSExecutadas().toString();
	}
	public String getDescricaoServicos() {
		return idServicos.trim() + "-" + descricaoServicos;
	}
	public void setDescricaoServicos(String descricaoServicos) {
		this.descricaoServicos = descricaoServicos;
	}
	public String getIdServicos() {
		return idServicos;
	}
	public void setIdServicos(String idServicos) {
		this.idServicos = idServicos;
	}
	public String getQtdeOs() {
		return qtdeOs;
	}
	public void setQtdeOs(String qtdeOs) {
		this.qtdeOs = qtdeOs;
	} 
	
}
