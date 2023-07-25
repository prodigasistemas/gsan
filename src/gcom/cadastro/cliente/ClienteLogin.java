package gcom.cadastro.cliente;

import java.util.Date;

import gcom.cadastro.imovel.Imovel;
import gcom.gui.portal.CadastroLoginClienteActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.FormatoData;
import gcom.util.Util;

public class ClienteLogin {
	
	public static final short CADASTRADO = 0;
	public static final short APROVADO = 1;
	public static final short REPROVADO = 2;

	private Integer id;

	private Imovel imovel;

	private String nome;

	private String cpfOuCnpj;

	private Date dataNascimento;

	private String celular;

	private String email;

	private String senha;

	private Short indicadorEmailConfirmado;

	private Date dataCadastro;

	private Short situacao;

	private Date dataAnalise;

	private Date ultimaAlteracao;

	public ClienteLogin() {
		super();
	}

	public ClienteLogin(CadastroLoginClienteActionForm form) {
		super();
		
		Date dataAtual = new Date();

		this.imovel = new Imovel(Integer.parseInt(form.getMatriculaSelecionada()));
		this.nome = Util.removerCaractereEspecial(form.getNome()).toUpperCase();
		this.cpfOuCnpj = Util.removerSimbolosPontuacao(form.getCpfOuCnpj());
		this.dataNascimento = Util.converterStringParaData(form.getDataNascimento(), FormatoData.DIA_MES_ANO.getFormato());
		this.celular = Util.removerSimbolosPontuacao(form.getCelular().replace(" ", ""));
		this.email = form.getEmail().toLowerCase();
		this.indicadorEmailConfirmado = ConstantesSistema.NAO;
		this.dataCadastro = dataAtual;
		this.situacao = CADASTRADO;
		this.ultimaAlteracao = dataAtual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Short getIndicadorEmailConfirmado() {
		return indicadorEmailConfirmado;
	}

	public void setIndicadorEmailConfirmado(Short indicadorEmailConfirmado) {
		this.indicadorEmailConfirmado = indicadorEmailConfirmado;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Short getSituacao() {
		return situacao;
	}

	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	public Date getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(Date dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
