package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Fernanda Paiva
 * @date 21/01/2006
 */
public class InserirDevolucoesActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String codigoImovelClone;
	
	private String inscricaoImovel;
	
	private String codigoCliente;
	
	private String codigoClienteClone;
	
	private String nomeCliente;

    private String avisoBancario;
    
    private String dataDevolucao;
    
    private String guiaDevolucao;

    private String localidade;
    
    private String localidadeClone;
    
    private String localidadeDescricao;
    
    private String referenciaDevolucao;
    
    private String referenciaDevolucaoClone;
    
    private String tipoDebito;
    
    private String tipoDebitoClone;
    
    private String valorDevolucao;
    
    private String valorGuiaDevolucao;
    
    private String codigoAgenteArrecadador;
    
    private String dataLancamentoAviso;
    
    private String numeroSequencialAviso;
    
    private String guiaDevolucaoDescricao;
    
    private String descricaoTipoDebito;
    
    public String getAvisoBancario() {
		return avisoBancario;
	}
	public void setAvisoBancario(String avisoBancario) {
		this.avisoBancario = avisoBancario;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoImovel() {
		return codigoImovel;
	}
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getGuiaDevolucao() {
		return guiaDevolucao;
	}
	public void setGuiaDevolucao(String guiaDevolucao) {
		this.guiaDevolucao = guiaDevolucao;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getReferenciaDevolucao() {
		return referenciaDevolucao;
	}
	public void setReferenciaDevolucao(String referenciaDevolucao) {
		this.referenciaDevolucao = referenciaDevolucao;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) 
    {
		// alterado para não limpar o form, pois o inserir devolucao nao pode ter seus campos limpos   
    	/*codigoImovel = null;
    	codigoCliente = null;
    	nomeCliente = null;
    	inscricaoImovel = null;
    	avisoBancario = null;
    	dataDevolucao = null;
    	guiaDevolucao = null;
    	localidade = null;
    	referenciaDevolucao = null; 
    	tipoDebito = null; 
    	valorDevolucao = null;*/
    }
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
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
	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}
	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}
	public String getGuiaDevolucaoDescricao() {
		return guiaDevolucaoDescricao;
	}
	public void setGuiaDevolucaoDescricao(String guiaDevolucaoDescricao) {
		this.guiaDevolucaoDescricao = guiaDevolucaoDescricao;
	}
	public String getValorGuiaDevolucao() {
		return valorGuiaDevolucao;
	}
	public void setValorGuiaDevolucao(String valorGuiaDevolucao) {
		this.valorGuiaDevolucao = valorGuiaDevolucao;
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getCodigoImovelClone() {
		return codigoImovelClone;
	}
	public void setCodigoImovelClone(String codigoImovelClone) {
		this.codigoImovelClone = codigoImovelClone;
	}
	public String getCodigoClienteClone() {
		return codigoClienteClone;
	}
	public void setCodigoClienteClone(String codigoClienteClone) {
		this.codigoClienteClone = codigoClienteClone;
	}
	public String getLocalidadeClone() {
		return localidadeClone;
	}
	public void setLocalidadeClone(String localidadeClone) {
		this.localidadeClone = localidadeClone;
	}
	public String getReferenciaDevolucaoClone() {
		return referenciaDevolucaoClone;
	}
	public void setReferenciaDevolucaoClone(String referenciaDevolucaoClone) {
		this.referenciaDevolucaoClone = referenciaDevolucaoClone;
	}
	public String getTipoDebitoClone() {
		return tipoDebitoClone;
	}
	public void setTipoDebitoClone(String tipoDebitoClone) {
		this.tipoDebitoClone = tipoDebitoClone;
	}
	
}
