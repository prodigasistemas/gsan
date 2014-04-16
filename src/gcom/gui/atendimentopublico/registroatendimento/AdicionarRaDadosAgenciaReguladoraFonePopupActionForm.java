package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gcom.cadastro.cliente.FoneTipo;

import org.apache.struts.action.ActionForm;



/**
 * [UC0459] Adicionar Ra Dados Agencia Reguladora Fone Popup 
 * 
 * @author Kassia Albuquerque
 * @created 13/04/2006
 */


public class AdicionarRaDadosAgenciaReguladoraFonePopupActionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String foneTipo;
	private String indicadorFonePadrao;
	private String ddd;
	private String numeroTelefone;
	private String ramal;
	
	
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getIndicadorFonePadrao() {
		return indicadorFonePadrao;
	}
	public void setIndicadorFonePadrao(String indicadorFonePadrao) {
		this.indicadorFonePadrao = indicadorFonePadrao;
	}
	
	public String getFoneTipo() {
		return foneTipo;
	}
	public void setFoneTipo(String foneTipo) {
		this.foneTipo = foneTipo;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
		
	public RaDadosAgenciaReguladoraFone setFormValues(RaDadosAgenciaReguladoraFone raDadosAgenciaReguladoraFone) {
		
		
		FoneTipo foneTipo = new FoneTipo();
		foneTipo.setId(new Integer(getFoneTipo()));
		raDadosAgenciaReguladoraFone.setFoneTipo(foneTipo);
		
		raDadosAgenciaReguladoraFone.setIndicadoFonePadrao(new Short(getIndicadorFonePadrao()));
		raDadosAgenciaReguladoraFone.setDdd(new Short(getDdd()));
		raDadosAgenciaReguladoraFone.setFone(getNumeroTelefone());
		raDadosAgenciaReguladoraFone.setRamal(getRamal());		
		
		return raDadosAgenciaReguladoraFone;
	}
    
}
