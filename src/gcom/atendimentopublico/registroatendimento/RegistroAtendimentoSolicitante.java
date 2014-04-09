package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RegistroAtendimentoSolicitante implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer ID;

	/** nullable persistent field */
	private String numeroImovel;

	/** nullable persistent field */
	private String pontoReferencia;

	/** nullable persistent field */
	private String complementoEndereco;

	/** nullable persistent field */
	private String solicitante;

	/** persistent field */
	private short indicadorSolicitantePrincipal;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private LogradouroBairro logradouroBairro;

	/** persistent field */
	private Cliente cliente;

	/** persistent field */
	private LogradouroCep logradouroCep;

	/** persistent field */
	private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** persistent field */
	private Funcionario funcionario;

    private Logradouro perimetroInicial;
    
    private Logradouro perimetroFinal;
    
    private String numeroProtocoloAtendimento;

	private Set solicitanteFones;
	
	private Short indicadorEnvioEmailPesquisa;
	
	private String enderecoEmail;
	
	public final static Integer INDICADOR_PRINCIPAL = 1;

	/** full constructor */
	public RegistroAtendimentoSolicitante(
			String numeroImovel,
			String pontoReferencia,
			String complementoEndereco,
			String solicitante,
			short indicadorSolicitantePrincipal,
			Date ultimaAlteracao,
			LogradouroBairro logradouroBairro,
			Cliente cliente,
			LogradouroCep logradouroCep,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento,
			UnidadeOrganizacional unidadeOrganizacional, Funcionario funcionario,
		    Logradouro perimetroInicial,
		    Logradouro perimetroFinal) {
		this.numeroImovel = numeroImovel;
		this.pontoReferencia = pontoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.solicitante = solicitante;
		this.indicadorSolicitantePrincipal = indicadorSolicitantePrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroBairro = logradouroBairro;
		this.cliente = cliente;
		this.logradouroCep = logradouroCep;
		this.registroAtendimento = registroAtendimento;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.funcionario = funcionario;
		this.perimetroInicial = perimetroInicial;
		this.perimetroFinal = perimetroFinal;
	}

	/** default constructor */
	public RegistroAtendimentoSolicitante() {
	}

	/** minimal constructor */
	public RegistroAtendimentoSolicitante(
			short indicadorSolicitantePrincipal,
			Date ultimaAlteracao,
			LogradouroBairro logradouroBairro,
			Cliente cliente,
			LogradouroCep logradouroCep,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento,
			UnidadeOrganizacional unidadeOrganizacional, Funcionario funcionario) {
		this.indicadorSolicitantePrincipal = indicadorSolicitantePrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroBairro = logradouroBairro;
		this.cliente = cliente;
		this.logradouroCep = logradouroCep;
		this.registroAtendimento = registroAtendimento;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.funcionario = funcionario;
	}

	public Integer getID() {
		return this.ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getPontoReferencia() {
		return this.pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public short getIndicadorSolicitantePrincipal() {
		return this.indicadorSolicitantePrincipal;
	}

	public void setIndicadorSolicitantePrincipal(
			short indicadorSolicitantePrincipal) {
		this.indicadorSolicitantePrincipal = indicadorSolicitantePrincipal;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LogradouroBairro getLogradouroBairro() {
		return this.logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LogradouroCep getLogradouroCep() {
		return this.logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return this.unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("ID", getID()).toString();
	}

	/**
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		// verifica se o logradouro do registro atendimento solicitante é
		// diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do registro atendimento solicitante
			// é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do registro atendimento
					// solicitante
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do registro atendimento
			// solicitante é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do registro atendimento
					// solicitante
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro do registro atendimento solicitante
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
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
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do registro atendimento solicitante
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}

	/**
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatado() {
		String endereco = "";

		// verifica se o logradouro do registro atendimento é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do registro atendimento é diferente
			// de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricao().equals("")) {
					// concatena o logradouro tipo do registro atendimento
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricao().trim();
				}
			}
			// verifica se o logradouro titulo do registro atendimento é
			// diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao()
								.equals("")) {
					// concatena o logradouro titulo do registro atendimento
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo().getDescricao()
									.trim();
				}
			}

			// concatena o logradouro do registro atendimento
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();
			
			// concate o numero do imovel
			if (this.getNumeroImovel() != null && !this.getNumeroImovel().equals("")) {
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
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
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do registro atendimento
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}

	public Set getSolicitanteFones() {
		return solicitanteFones;
	}

	public void setSolicitanteFones(Set solicitanteFones) {
		this.solicitanteFones = solicitanteFones;
	}

	/**
	 * @return Retorna o campo perimetroFinal.
	 */
	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}

	/**
	 * @param perimetroFinal O perimetroFinal a ser setado.
	 */
	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}

	/**
	 * @return Retorna o campo perimetroInicial.
	 */
	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}

	/**
	 * @param perimetroInicial O perimetroInicial a ser setado.
	 */
	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}

	public String getNumeroProtocoloAtendimento() {
		return numeroProtocoloAtendimento;
	}

	public void setNumeroProtocoloAtendimento(String numeroProtocoloAtendimento) {
		this.numeroProtocoloAtendimento = numeroProtocoloAtendimento;
	}

	public Short getIndicadorEnvioEmailPesquisa() {
		return indicadorEnvioEmailPesquisa;
	}

	public void setIndicadorEnvioEmailPesquisa(Short indicadorEnvioEmailPesquisa) {
		this.indicadorEnvioEmailPesquisa = indicadorEnvioEmailPesquisa;
	}

	public String getEnderecoEmail() {
		return enderecoEmail;
	}

	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}
}
