package gcom.gui.arrecadacao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que irá apresentar a análise do movimento dos arrecadadores
 * e os avisos bancários associados
 *
 * @author Raphael Rossiter
 * @date 07/03/2006
 */
public class ApresentarAnaliseMovimentoArrecadadoresActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoNomeArrecadador;
	private String codigoRemessa;
	private String identificacaoServico;
	private String nsa;
	private String dataGeracao;
	private String numeroRegistrosMovimento;
	private String numeroRegistrosOcorrencia;
	private String numeroRegistrosNaoAceitos;
	private String valorTotalMovimento;
	private String dataProcessamento;
	private String horaProcessamento;
	private String situacaoMovimento;
	private String valorTotalAvisosBancarios;
	private String valordiferencaVlMovimentoVlAvisos;
	
	private String ultimaAlteracao;
	
  
	
	public String getCodigoNomeArrecadador() {
		return codigoNomeArrecadador;
	}
	public void setCodigoNomeArrecadador(String codigoNomeArrecadador) {
		this.codigoNomeArrecadador = codigoNomeArrecadador;
	}
	public String getCodigoRemessa() {
		return codigoRemessa;
	}
	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public String getHoraProcessamento() {
		return horaProcessamento;
	}
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getNumeroRegistrosMovimento() {
		return numeroRegistrosMovimento;
	}
	public void setNumeroRegistrosMovimento(String numeroRegistrosMovimento) {
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
	}
	public String getNumeroRegistrosNaoAceitos() {
		return numeroRegistrosNaoAceitos;
	}
	public void setNumeroRegistrosNaoAceitos(String numeroRegistrosNaoAceitos) {
		this.numeroRegistrosNaoAceitos = numeroRegistrosNaoAceitos;
	}
	public String getNumeroRegistrosOcorrencia() {
		return numeroRegistrosOcorrencia;
	}
	public void setNumeroRegistrosOcorrencia(String numeroRegistrosOcorrencia) {
		this.numeroRegistrosOcorrencia = numeroRegistrosOcorrencia;
	}
	public String getSituacaoMovimento() {
		return situacaoMovimento;
	}
	public void setSituacaoMovimento(String situacaoMovimento) {
		this.situacaoMovimento = situacaoMovimento;
	}
	public String getValorTotalAvisosBancarios() {
		return valorTotalAvisosBancarios;
	}
	public void setValorTotalAvisosBancarios(String valorTotalAvisosBancarios) {
		this.valorTotalAvisosBancarios = valorTotalAvisosBancarios;
	}
	public String getValorTotalMovimento() {
		return valorTotalMovimento;
	}
	public void setValorTotalMovimento(String valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getValordiferencaVlMovimentoVlAvisos() {
		return valordiferencaVlMovimentoVlAvisos;
	}
	public void setValordiferencaVlMovimentoVlAvisos(
			String valordiferencaVlMovimentoVlAvisos) {
		this.valordiferencaVlMovimentoVlAvisos = valordiferencaVlMovimentoVlAvisos;
	}

	
}

