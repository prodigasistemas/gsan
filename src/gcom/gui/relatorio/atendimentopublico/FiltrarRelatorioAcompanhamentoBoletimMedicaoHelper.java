package gcom.gui.relatorio.atendimentopublico;

import gcom.util.Util;

import java.io.Serializable;

/**
 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
 * 
 * Filtro responsável por auxiliar na geração do relatório de 
 * Acompanhamento dos Boletins de Medição
 * 
 * @author Diogo Peixoto
 * @since 17/06/2011
 * 
 */
public class FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idEmpresa;
	private Integer idContratoEmpresaServico;
	private Integer mesAnoReferencia;

	public FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper(){
		
	}
	
	public FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper(Integer idEmpresa, Integer idContratoEmpresaServico, Integer mesAno){
		this.idEmpresa = idEmpresa;
		this.idContratoEmpresaServico = idContratoEmpresaServico;
		this.mesAnoReferencia = mesAno;
	}
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdContratoEmpresaServico() {
		return idContratoEmpresaServico;
	}

	public void setIdContratoEmpresaServico(Integer idContratoEmpresaServico) {
		this.idContratoEmpresaServico = idContratoEmpresaServico;
	}

	public Integer getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String strDataReferencia) throws NumberFormatException {
		this.mesAnoReferencia = Util.formatarMesAnoComBarraParaAnoMes(strDataReferencia);
	}

	public void setMesAnoReferencia(Integer mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
}