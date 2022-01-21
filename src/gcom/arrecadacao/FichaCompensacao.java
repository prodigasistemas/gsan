package gcom.arrecadacao;

import gcom.faturamento.bean.PagadorDTO;


public class FichaCompensacao {
	
	private Integer id;
	private Integer idConta;
	private Integer idConv;
	private Integer numeroCarteira;
	private Integer numeroVariacaoCarteira;
	private Short codigoModalidade;
	private String dataEmissao;
	private String dataVencimento; 
	private String valorOriginal;
	private String codigoAceite;
	private Short codigoTipoTitulo;
	private String indicadorPermissaoRecebimentoParcial;
	private String numeroTituloCliente; //nosso numero
	
	//Object pagador
	private Integer idCliente;
	private Integer idImovel;
	
	private PagadorDTO pagadorDTO;
	
    public FichaCompensacao() {}

	public FichaCompensacao(Integer idConta, Integer idConv, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, String valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, PagadorDTO pagadorDTO) {
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
		this.pagadorDTO = pagadorDTO;
	}
	
	public FichaCompensacao(Integer idConta, Integer idConv, Integer numeroCarteira, Integer numeroVariacaoCarteira,
			Short codigoModalidade, String dataEmissao, String dataVencimento, String valorOriginal,
			String codigoAceite, Short codigoTipoTitulo, String indicadorPermissaoRecebimentoParcial,
			String numeroTituloCliente, Integer idImovel,Integer idCliente) {
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
        this.idImovel = idImovel;
		this.idCliente = idCliente;
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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
}
