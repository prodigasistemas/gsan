package gcom.gui.cobranca.spcserasa;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir o
 * tipo do registro do negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 07/01/2008
 */
public class RegistrarNegativadorMovimentoRetornoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String negativador;

	private String dataProcessamento;

	private String horaProcessamento;

	private String numeroSequencialArquivo;

	private String totalRegistrosArquivo;


	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Yara Taciane
	 * @date 02/01/2008
	 * 
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.negativador="";

		this.dataProcessamento="";

		this.horaProcessamento="";

		this.numeroSequencialArquivo="";

		this.totalRegistrosArquivo="";

	}


	/**
	 * @return Retorna o campo dataProcessamento.
	 */
	public String getDataProcessamento() {
		return dataProcessamento;
	}


	/**
	 * @param dataProcessamento O dataProcessamento a ser setado.
	 */
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}


	/**
	 * @return Retorna o campo horaProcessamento.
	 */
	public String getHoraProcessamento() {
		return horaProcessamento;
	}


	/**
	 * @param horaProcessamento O horaProcessamento a ser setado.
	 */
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}


	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}


	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}


	/**
	 * @return Retorna o campo numeroSequencialArquivo.
	 */
	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}


	/**
	 * @param numeroSequencialArquivo O numeroSequencialArquivo a ser setado.
	 */
	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}


	/**
	 * @return Retorna o campo totalRegistrosArquivo.
	 */
	public String getTotalRegistrosArquivo() {
		return totalRegistrosArquivo;
	}


	/**
	 * @param totalRegistrosArquivo O totalRegistrosArquivo a ser setado.
	 */
	public void setTotalRegistrosArquivo(String totalRegistrosArquivo) {
		this.totalRegistrosArquivo = totalRegistrosArquivo;
	}

	
}
