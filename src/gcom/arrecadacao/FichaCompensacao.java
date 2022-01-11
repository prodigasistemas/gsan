package src.gcom.arrecadacao;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import src.gcom.faturamento.StringBuilder;

import java.util.Date;

public class FichaCompensacao {
	
	private Integer id;
	private Integer idConta;
	private Integer idConv;
	private Integer numeroCarteira;
	private Integer numeroVariacaoCarteira;
	private Short codigoModalidade;
	private Date dataEmissao;
	private Date dataVencimento; 
	private String valorOriginal;
	private String codigoAceite;
	private Short codigoTipoTitulo;
	private String indicadorPermissaoRecebimentoParcial;
	private String numeroTituloCliente; //nosso numero
	
	//Object pagador
	private Pagador pagador;	
	
    public FichaCompensacao() {}
	
	public FichaCompensacao() {
		super();
		this.idConta = idConta;
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
		this.pagador = pagador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
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

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(String valorOriginal) {
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

	public Pagador getPagador() {
		return pagador;
	}

	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}
	
}
