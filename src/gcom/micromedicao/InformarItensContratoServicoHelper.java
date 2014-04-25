package gcom.micromedicao;

import java.util.List;

/**
 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
 * 
 * @author Hugo Leonardo, Mariana Victor
 * @date 30/07/2010, 25/11/2010
 */

public class InformarItensContratoServicoHelper {
    
    private ContratoEmpresaServico contratoEmpresaServico;
    private List<ItemServicoContrato> itemServicoContrato;
    private List<ContratoEmpresaAditivo> contratoEmpresaAditivo;

    public InformarItensContratoServicoHelper(){
    	
    }

	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public List<ItemServicoContrato> getItemServicoContrato() {
		return itemServicoContrato;
	}

	public void setItemServicoContrato(List<ItemServicoContrato> itemServicoContrato) {
		this.itemServicoContrato = itemServicoContrato;
	}

	public List<ContratoEmpresaAditivo> getContratoEmpresaAditivo() {
		return contratoEmpresaAditivo;
	}

	public void setContratoEmpresaAditivo(
			List<ContratoEmpresaAditivo> contratoEmpresaAditivo) {
		this.contratoEmpresaAditivo = contratoEmpresaAditivo;
	}
    
}
