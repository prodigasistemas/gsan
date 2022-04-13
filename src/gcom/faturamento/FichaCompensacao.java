package gcom.faturamento;

import java.util.Date;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.interceptor.ControleAlteracao;

@ControleAlteracao()
public class FichaCompensacao {
	
	private Integer id;
	private Conta conta;
	private Integer idConv;
	private Integer numeroCarteira;
	private Integer numeroVariacaoCarteira;
	private Short codigoModalidade;
	private String dataEmissao;
	private String dataVencimento; 
	private Double valorOriginal;
	private String codigoAceite;
	private Short codigoTipoTitulo;
	private String indicadorPermissaoRecebimentoParcial;
	private String numeroTituloCliente; //nosso numero
	private Date ultimaAlteracao;
	
	//Object pagador
	private Cliente cliente;
	private Imovel imovel;
	
	
	public FichaCompensacao(Integer idConv, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, Double valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, Imovel imovel, Cliente cliente, Conta conta) {
		super();
		this.idConv = idConv;
		this.numeroCarteira = numeroCarteira;
		this.numeroVariacaoCarteira = numeroVariacaoCarteira;
		this.codigoModalidade = codigoModalidade;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.valorOriginal = valorOriginal;
		this.codigoAceite = codigoAceite;
		this.codigoTipoTitulo = codigoTipoTitulo;
		this.indicadorPermissaoRecebimentoParcial = indicadorPermissaoRecebimentoParcial;
		this.numeroTituloCliente = numeroTituloCliente;
        this.imovel = imovel;
		this.cliente = cliente;
		this.conta = conta;
		this.ultimaAlteracao = new Date();
	}


	public FichaCompensacao() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}


	public Integer getIdConv() {
		return idConv;
	}


	public void setIdConv(Integer idConv) {
		this.idConv = idConv;
	}


	public Integer getNumeroCarteira() {
		return numeroCarteira;
	}


	public void setNumeroCarteira(Integer numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}


	public Integer getNumeroVariacaoCarteira() {
		return numeroVariacaoCarteira;
	}


	public void setNumeroVariacaoCarteira(Integer numeroVariacaoCarteira) {
		this.numeroVariacaoCarteira = numeroVariacaoCarteira;
	}


	public Short getCodigoModalidade() {
		return codigoModalidade;
	}


	public void setCodigoModalidade(Short codigoModalidade) {
		this.codigoModalidade = codigoModalidade;
	}


	public String getDataEmissao() {
		return dataEmissao;
	}


	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	public String getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Double getValorOriginal() {
		return valorOriginal;
	}


	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}


	public String getCodigoAceite() {
		return codigoAceite;
	}


	public void setCodigoAceite(String codigoAceite) {
		this.codigoAceite = codigoAceite;
	}


	public Short getCodigoTipoTitulo() {
		return codigoTipoTitulo;
	}


	public void setCodigoTipoTitulo(Short codigoTipoTitulo) {
		this.codigoTipoTitulo = codigoTipoTitulo;
	}


	public String getIndicadorPermissaoRecebimentoParcial() {
		return indicadorPermissaoRecebimentoParcial;
	}


	public void setIndicadorPermissaoRecebimentoParcial(String indicadorPermissaoRecebimentoParcial) {
		this.indicadorPermissaoRecebimentoParcial = indicadorPermissaoRecebimentoParcial;
	}


	public String getNumeroTituloCliente() {
		return numeroTituloCliente;
	}


	public void setNumeroTituloCliente(String numeroTituloCliente) {
		this.numeroTituloCliente = numeroTituloCliente;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
}
