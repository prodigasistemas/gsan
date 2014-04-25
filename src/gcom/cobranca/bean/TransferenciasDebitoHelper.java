package gcom.cobranca.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class TransferenciasDebitoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Conta conta;
	
	private DebitoACobrar debitoACobrar;
	
	private CreditoARealizar creditoARealizar;
	
	private GuiaPagamento guiaPagamento;
	
	private Imovel imovelOrigem;
	
	private Imovel imovelDestino;
	
	private Usuario usuario;
	
	private Date dataTransferencia;
	
	public TransferenciasDebitoHelper() {}
	
	/**
	 * @return Retorna o campo dataTransferencia.
	 */
	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia O dataTransferencia a ser setado.
	 */
	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo imovelDestino.
	 */
	public Imovel getImovelDestino() {
		return imovelDestino;
	}

	/**
	 * @param imovelDestino O imovelDestino a ser setado.
	 */
	public void setImovelDestino(Imovel imovelDestino) {
		this.imovelDestino = imovelDestino;
	}

	/**
	 * @return Retorna o campo imovelOrigem.
	 */
	public Imovel getImovelOrigem() {
		return imovelOrigem;
	}

	/**
	 * @param imovelOrigem O imovelOrigem a ser setado.
	 */
	public void setImovelOrigem(Imovel imovelOrigem) {
		this.imovelOrigem = imovelOrigem;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * @return Retorna o campo creditoARealizar.
	 */
	public CreditoARealizar getCreditoARealizar() {
		return creditoARealizar;
	}

	/**
	 * @param creditoARealizar O creditoARealizar a ser setado.
	 */
	public void setCreditoARealizar(CreditoARealizar creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	/**
	 * @return Retorna o campo debitoACobrar.
	 */
	public DebitoACobrar getDebitoACobrar() {
		return debitoACobrar;
	}

	/**
	 * @param debitoACobrar O debitoACobrar a ser setado.
	 */
	public void setDebitoACobrar(DebitoACobrar debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	/**
	 * @return Retorna o campo guiaPagamento.
	 */
	public GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	/**
	 * @param guiaPagamento O guiaPagamento a ser setado.
	 */
	public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

}
