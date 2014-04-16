package gcom.gui.cadastro.entidadebeneficente;

import org.apache.struts.validator.ValidatorActionForm;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.faturamento.debito.DebitoTipo;

/**
 * Carrega os dados necessários para o [UC0915] Inserir Entidade Beneficente.
 *  
 * @author Samuel Valerio
 * @date 11/06/2009
 * @since 4.1.6.4
 *
 */
public class EntidadeBeneficenteActionForm extends ValidatorActionForm {	private static final long serialVersionUID = 1L;

	private EntidadeBeneficente entidadeBeneficente;
	
	public EntidadeBeneficenteActionForm() {
		entidadeBeneficente = new EntidadeBeneficente();
		entidadeBeneficente.setCliente(new Cliente());
		entidadeBeneficente.setDebitoTipo(new DebitoTipo());
		entidadeBeneficente.setEmpresa(new Empresa());
	}

	public EntidadeBeneficente getEntidadeBeneficente() {
		return entidadeBeneficente;
	}

	public void setEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) {
		this.entidadeBeneficente = entidadeBeneficente;
	}
	
}
