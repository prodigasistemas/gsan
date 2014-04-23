package gcom.gui.operacional.abastecimento;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.util.ConstantesSistema;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC0524]	INSERIR SISTEMA ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 08/03/2007
 */


public class InserirSistemaEsgotoActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricaoSistemaEsgoto;
	
	private String descricaoAbreviada;
	
	private String divisaoEsgoto;
	
	private String tipoTratamento;
	


	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	public String getDescricaoSistemaEsgoto() {
		return descricaoSistemaEsgoto;
	}



	public void setDescricaoSistemaEsgoto(String descricaoSistemaEsgoto) {
		this.descricaoSistemaEsgoto = descricaoSistemaEsgoto;
	}



	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}



	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}



	public String getTipoTratamento() {
		return tipoTratamento;
	}



	public void setTipoTratamento(String tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}



	public SistemaEsgoto setFormValues(SistemaEsgoto sistemaEsgoto) {
		
		
		// Metodo usado para setar todos os valores do Form na base de dados

		sistemaEsgoto.setDescricao(getDescricaoSistemaEsgoto());		
		sistemaEsgoto.setDescricaoAbreviada(getDescricaoAbreviada());
		
		if(getDivisaoEsgoto() != null && !getDivisaoEsgoto().equals("")){
			
			  DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
			  divisaoEsgoto.setId(Integer.parseInt(getDivisaoEsgoto()));
			  sistemaEsgoto.setDivisaoEsgoto(divisaoEsgoto);
			}
		
		if(getDescricaoSistemaEsgoto() != null && !getDescricaoSistemaEsgoto().equals("")){
			
			  SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo = new SistemaEsgotoTratamentoTipo();
			  sistemaEsgotoTratamentoTipo.setId(Integer.parseInt(getTipoTratamento()));
			  sistemaEsgoto.setSistemaEsgotoTratamentoTipo(sistemaEsgotoTratamentoTipo);
			}
		
		sistemaEsgoto.setUltimaAlteracao(new Date());
		sistemaEsgoto.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		return sistemaEsgoto;
	}

}
