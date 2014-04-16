package gcom.gui.arrecadacao.pagamento;


import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado no manter pagamentos para remover ou atualizar pagamentos
 *
 * @author Pedro Alexandre
 * @date 21/03/2006
 */
public class ManterPagamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	
	private String dataPagamento;
	private String idFormaArrecadacao;
	private String descricaoFormaArrecadacao;
	private String valorPagamento;
	private String idTipoDocumento;
	
	private String idImovel;
	private String codigoImovel;
	private String descricaoImovel;
						
	private String idGuiaPagamento;
	private String codigoGuiaPagamento;
	private String descricaoGuiaPagamento;
	private String valorGuiaPagamento;
	
	private String idDebitoACobrar;
	private String codigoDebitoACobrar;
	private String descricaoDebitoACobrar;
	private String valorDebitoACobrar;
				
	private String idLocalidade;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	
	private String referenciaConta;
	private String descricaoReferenciaConta;
	
	private String idCliente;			
	private String codigoCliente;
	private String nomeCliente;
	
	private String idTipoDebito;
	private String descricaoTipoDebito;
	
	private String nomeClienteArrecadador;	
	private String ultimaAlteracaoPagamento;
	
	
	private String[] idRegistrosRemocao;

	private String idSituacaoAtualPagamento;
	
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public String getIdSituacaoAtualPagamento() {
		return idSituacaoAtualPagamento;
	}

	public void setIdSituacaoAtualPagamento(String idSituacaoAtualPagamento) {
		this.idSituacaoAtualPagamento = idSituacaoAtualPagamento;
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

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricaoDebitoACobrar() {
		return descricaoDebitoACobrar;
	}

	public void setDescricaoDebitoACobrar(String descricaoDebitoACobrar) {
		this.descricaoDebitoACobrar = descricaoDebitoACobrar;
	}

	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	public String getDescricaoGuiaPagamento() {
		return descricaoGuiaPagamento;
	}

	public void setDescricaoGuiaPagamento(String descricaoGuiaPagamento) {
		this.descricaoGuiaPagamento = descricaoGuiaPagamento;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoReferenciaConta() {
		return descricaoReferenciaConta;
	}

	public void setDescricaoReferenciaConta(String descricaoReferenciaConta) {
		this.descricaoReferenciaConta = descricaoReferenciaConta;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}

	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}

	public String getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public String getValorDebitoACobrar() {
		return valorDebitoACobrar;
	}

	public void setValorDebitoACobrar(String valorDebitoACobrar) {
		this.valorDebitoACobrar = valorDebitoACobrar;
	}

	public String getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}

	public void setValorGuiaPagamento(String valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoDebitoACobrar() {
		return codigoDebitoACobrar;
	}

	public void setCodigoDebitoACobrar(String codigoDebitoACobrar) {
		this.codigoDebitoACobrar = codigoDebitoACobrar;
	}

	public String getCodigoGuiaPagamento() {
		return codigoGuiaPagamento;
	}

	public void setCodigoGuiaPagamento(String codigoGuiaPagamento) {
		this.codigoGuiaPagamento = codigoGuiaPagamento;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getNomeClienteArrecadador() {
		return nomeClienteArrecadador;
	}

	public void setNomeClienteArrecadador(String nomeClienteArrecadador) {
		this.nomeClienteArrecadador = nomeClienteArrecadador;
	}

	public String getUltimaAlteracaoPagamento() {
		return ultimaAlteracaoPagamento;
	}

	public void setUltimaAlteracaoPagamento(String ultimaAlteracaoPagamento) {
		this.ultimaAlteracaoPagamento = ultimaAlteracaoPagamento;
	}

			
}
