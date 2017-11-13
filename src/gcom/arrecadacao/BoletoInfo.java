package gcom.arrecadacao;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;

import java.util.Date;

@ControleAlteracao()
public class BoletoInfo extends ObjetoTransacao {
	
	private Integer id;
	private String idConv;
	private String refTran;
	private String cpfCnpj;
	private String nome;
	private String endereco;
	private String uf; 
	private String cep;
	private String cidade;
	private String indicadorPessoa;
	private String tpDuplicata;
	private String tpPagamento; 
	private String valor; 
	private String dtVencimento;
	private String urlRetorno;
	private String mensagemLoja; 
	private String linkBoleto;
	private Date ultimaAlteracao;
	private GuiaPagamento guiaPagamento;
	private Short indicadoGeradoPeloGsan;
	private Short indicadoRegistradoNoBanco;
	
	public BoletoInfo() {}
	
	public BoletoInfo(Integer id, String idConv, String refTran, String cpfCnpj, String nome, String endereco, String uf, String cep, String cidade,
			String indicadorPessoa, String tpDuplicata, String tpPagamento, String valor, String dtVencimento, String urlRetorno, String mensagemLoja,
			String linkBoleto, GuiaPagamento guiaPagamento, Short indicadoGeradoPeloGsan) {
		super();
		this.id = id;
		this.idConv = idConv;
		this.refTran = refTran;
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.endereco = endereco;
		this.uf = uf;
		this.cep = cep;
		this.cidade = cidade;
		this.indicadorPessoa = indicadorPessoa;
		this.tpDuplicata = tpDuplicata;
		this.tpPagamento = tpPagamento;
		this.valor = valor;
		this.dtVencimento = dtVencimento;
		this.urlRetorno = urlRetorno;
		this.mensagemLoja = mensagemLoja;
		this.linkBoleto = linkBoleto;
		this.guiaPagamento = guiaPagamento;
		this.ultimaAlteracao = new Date();
		this.indicadoGeradoPeloGsan = indicadoGeradoPeloGsan;
		this.indicadoRegistradoNoBanco = ConstantesSistema.NAO;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getIdConv() {
		return idConv;
	}



	public void setIdConv(String idConv) {
		this.idConv = idConv;
	}



	public String getRefTran() {
		return refTran;
	}



	public void setRefTran(String refTran) {
		this.refTran = refTran;
	}



	public String getCpfCnpj() {
		return cpfCnpj;
	}



	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEndereco() {
		return endereco;
	}



	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public String getUf() {
		return uf;
	}



	public void setUf(String uf) {
		this.uf = uf;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getIndicadorPessoa() {
		return indicadorPessoa;
	}



	public void setIndicadorPessoa(String indicadorPessoa) {
		this.indicadorPessoa = indicadorPessoa;
	}



	public String getTpDuplicata() {
		return tpDuplicata;
	}



	public void setTpDuplicata(String tpDuplicata) {
		this.tpDuplicata = tpDuplicata;
	}



	public String getTpPagamento() {
		return tpPagamento;
	}



	public void setTpPagamento(String tpPagamento) {
		this.tpPagamento = tpPagamento;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	public String getDtVencimento() {
		return dtVencimento;
	}



	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}



	public String getUrlRetorno() {
		return urlRetorno;
	}



	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}



	public String getMensagemLoja() {
		return mensagemLoja;
	}



	public void setMensagemLoja(String mensagemLoja) {
		this.mensagemLoja = mensagemLoja;
	}



	public String getLinkBoleto() {
		return linkBoleto;
	}



	public void setLinkBoleto(String linkBoleto) {
		this.linkBoleto = linkBoleto;
	}

	public GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public Short getIndicadoGeradoPeloGsan() {
		return indicadoGeradoPeloGsan;
	}

	public void setIndicadoGeradoPeloGsan(Short indicadoGeradoPeloGsan) {
		this.indicadoGeradoPeloGsan = indicadoGeradoPeloGsan;
	}

	public Short getIndicadoRegistradoNoBanco() {
		return indicadoRegistradoNoBanco;
	}

	public void setIndicadoRegistradoNoBanco(Short indicadoRegistradoNoBanco) {
		this.indicadoRegistradoNoBanco = indicadoRegistradoNoBanco;
	}

	

}
