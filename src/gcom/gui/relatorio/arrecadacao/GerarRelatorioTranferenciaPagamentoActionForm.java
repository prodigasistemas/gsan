package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC ] – (Desc)
 * 
 * @author Raimundo Martins
 *
 * @date 17/08/2011
 */
public class GerarRelatorioTranferenciaPagamentoActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String periodoInicial;
	private String periodoFinal;
	private String arrecadadorCodigo;
	private String arrecadadorDescricao;
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	private String idTipoDebito;
	private String idTipoDocumento;
	private String idFormaArrecadacao;
	
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getArrecadadorCodigo() {
		return arrecadadorCodigo;
	}
	public void setArrecadadorCodigo(String arrecadadorCodigo) {
		this.arrecadadorCodigo = arrecadadorCodigo;
	}
	public String getArrecadadorDescricao() {
		return arrecadadorDescricao;
	}
	public void setArrecadadorDescricao(String arrecadadorDescricao) {
		this.arrecadadorDescricao = arrecadadorDescricao;
	}
	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}
	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}
	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}
	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}
	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}
	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	

}
