package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Tiago Moreno
 */
public class PesquisarMovimentoArrecadadorActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idBanco;
	private String tipoRemessa;
	private String identificacaoServico;
	private String numeroSequencialArquivo;
	private String dataMovimentoInicio;
	private String dataMovimentoFim;
	private String arrecadadorNome;
	
	private String movimentoItemOcorrencia;
	private String movimentoItemAceito;
	
	public String getDataMovimentoInicio() {
		return dataMovimentoInicio;
	}
	public void setDataMovimentoInicio(String dataMovimentoInicio) {
		this.dataMovimentoInicio = dataMovimentoInicio;
	}
	public String getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}
	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}
	public String getTipoRemessa() {
		return tipoRemessa;
	}
	public void setTipoRemessa(String tipoRemessa) {
		this.tipoRemessa = tipoRemessa;
	}
	public String getDataMovimentoFim() {
		return dataMovimentoFim;
	}
	public void setDataMovimentoFim(String dataMovimentoFim) {
		this.dataMovimentoFim = dataMovimentoFim;
	}
	public String getArrecadadorNome() {
		return arrecadadorNome;
	}
	public void setArrecadadorNome(String arrecadadorNome) {
		this.arrecadadorNome = arrecadadorNome;
	}
	public String getMovimentoItemAceito() {
		return movimentoItemAceito;
	}
	public void setMovimentoItemAceito(String movimentoItemAceito) {
		this.movimentoItemAceito = movimentoItemAceito;
	}
	public String getMovimentoItemOcorrencia() {
		return movimentoItemOcorrencia;
	}
	public void setMovimentoItemOcorrencia(String movimentoItemOcorrencia) {
		this.movimentoItemOcorrencia = movimentoItemOcorrencia;
	}
	
}
