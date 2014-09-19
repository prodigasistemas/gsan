package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * @author Vivianne Sousa
 * @date 18/04/2011
 */
public class RelatorioOSExecutadasPrestadoraServicoSinteticoBean implements RelatorioBean {
	
	private String descricaoServicos; 
	private String idServicos; 
	private Integer qtdeOs;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String qtdeOsLocalidade;
	
	public String getQtdeOsLocalidade() {
		return qtdeOsLocalidade;
	}

	public void setQtdeOsLocalidade(String qtdeOsLocalidade) {
		this.qtdeOsLocalidade = qtdeOsLocalidade;
	}

	public RelatorioOSExecutadasPrestadoraServicoSinteticoBean() {
		super();
		
	}
	
	public RelatorioOSExecutadasPrestadoraServicoSinteticoBean(String descricaoServicos, String idServicos, Integer qtdeOs, String idLocalidade, String descricaoLocalidade, String qtdeOsLocalidade) {
		super();
		
		this.descricaoServicos = descricaoServicos;
		this.idServicos = idServicos;
		this.qtdeOs = qtdeOs;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.qtdeOsLocalidade = qtdeOsLocalidade;
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
	public Integer getQtdeOs() {
		return qtdeOs;
	}
	public void setQtdeOs(Integer qtdeOs) {
		this.qtdeOs = qtdeOs;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	} 
	
}
