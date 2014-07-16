package gcom.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class GerenciaRegional extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String nomeAbreviado;
	private String numeroImovel;
	private String complementoEndereco;
	private String fone;
	private String ramalFone;
	private String fax;
	private String email;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private String cnpjGerenciaRegional;

	private Logradouro logradouro;
	private Bairro bairro;
	private EnderecoReferencia enderecoReferencia;
	private Cliente cliente;
	private Cep cep;
	private LogradouroCep logradouroCep;
	private LogradouroBairro logradouroBairro;

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public GerenciaRegional() {
	}
	
	public GerenciaRegional(Integer id) {
		this.id = id;
	}

	public GerenciaRegional(String nome, String nomeAbreviado,
			String numeroImovel, String complementoEndereco, String fone,
			String ramalFone, String fax, String email, Short indicadorUso,
			Date ultimaAlteracao, Logradouro logradouro, Bairro bairro,
			EnderecoReferencia enderecoReferencia, Cep cep,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.fone = fone;
		this.ramalFone = ramalFone;
		this.fax = fax;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.enderecoReferencia = enderecoReferencia;
		this.cep = cep;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}


	public GerenciaRegional(String nome, String nomeAbreviado, String fone,
			Logradouro logradouro, Bairro bairro,
			EnderecoReferencia enderecoReferencia, Cep cep) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.fone = fone;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.enderecoReferencia = enderecoReferencia;
		this.cep = cep;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return this.nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getFone() {
		return this.fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getRamalFone() {
		return this.ramalFone;
	}

	public void setRamalFone(String ramalFone) {
		this.ramalFone = ramalFone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public Cep getCep() {
		return this.cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public String getEnderecoFormatado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro()
						.getLogradouroTipo().getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + " - "
							+ this.getEnderecoReferencia().getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco + " - "
						+ this.getLogradouroBairro().getBairro().getNome();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " "
						+ this.getLogradouroCep().getCep().getCepFormatado();
			}

		}

		return endereco;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroGerenciaRegional
				.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroGerenciaRegional
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.ID, this.getId()));
		return filtroGerenciaRegional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getNome();
	}
	
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroGerenciaRegional.ID,this.getId()));
		return filtro;
	}
	
	public String getCnpjGerenciaRegional() {
		return this.cnpjGerenciaRegional;
	}

	public void setCnpjGerenciaRegional(String cnpjGerenciaRegional) {
		this.cnpjGerenciaRegional = cnpjGerenciaRegional;
	}


	
	

}
