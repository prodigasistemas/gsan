package gcom.cadastro.imovel.bean;

import gcom.util.Util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Esta classe tem a finalidade para facilitar a visualização dos dados na tela 
 * [UC0472] Consultar Imovel
 * 
 * @author Rafael Santos
 * @date 25/09/2006
 */
public class ConsultarImovelRegistroAtendimentoHelper {
	
	private String idRegistroAtendimento;

	private String tipoSolicitacao;
	
	private String especificacao;
	
	private String dataAtendimento;
	
	private String situacao;
	
	private String numeroProtocolo;
	
	private String dataEncerramento;
	
	private String motivoEncerramento;

	/**
	 * @return Retorna o campo dataAtendimento.
	 */
	public String getDataAtendimento() {
		return dataAtendimento;
	}

	/**
	 * @param dataAtendimento O dataAtendimento a ser setado.
	 */
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	/**
	 * @return Retorna o campo especificacao.
	 */
	public String getEspecificacao() {
		return especificacao;
	}

	/**
	 * @param especificacao O especificacao a ser setado.
	 */
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	/**
	 * @return Retorna o campo idRegistroAtendimento.
	 */
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	/**
	 * @param idRegistroAtendimento O idRegistroAtendimento a ser setado.
	 */
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo tipoSolicitacao.
	 */
	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	/**
	 * @param tipoSolicitacao O tipoSolicitacao a ser setado.
	 */
	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	
	public String getDataEncerramentoFormatada(){
		if(dataEncerramento == null){
			return "";
		}
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(Util.converteStringParaDate(dataEncerramento));
		String mes = data.get(Calendar.MONTH)+"";
		if(mes.length() == 1){
			mes = "0" + mes;
		}
		String dia = data.get(Calendar.DAY_OF_MONTH)+"";
		if(dia.length() == 1){
			dia = "0" + dia;
		}
		return data.get(Calendar.YEAR) + "-" + mes + "-" + dia;
	}
	
	public String getDataAtendimentoFormatada(){
		if(dataAtendimento == null){
			return "";
		}
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(Util.converteStringParaDate(dataAtendimento));
		String mes = data.get(Calendar.MONTH)+"";
		if(mes.length() == 1){
			mes = "0" + mes;
		}
		String dia = data.get(Calendar.DAY_OF_MONTH)+"";
		if(dia.length() == 1){
			dia = "0" + dia;
		}
		return data.get(Calendar.YEAR) + "-" + mes + "-" + dia;
	}
	
	
	
}
