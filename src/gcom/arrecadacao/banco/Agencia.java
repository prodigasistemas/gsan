package gcom.arrecadacao.banco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Agencia implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigoAgencia;
    private String nomeAgencia;
    private String numeroTelefone;
    private String numeroRamal;
    private String numeroImovel;
    private String complementoEndereco;
    private Date ultimaAlteracao;
    private String numeroFax;
    private String email;
    private Integer percentualRepasse;
    private Date dataInicioVigencia;
    private Date dataFimVigencia;
    private Integer referenciaInicialRepasse;
    
    private Bairro bairro;
    private LogradouroCep logradouroCep;
 	private LogradouroBairro logradouroBairro;
    private Banco banco;
    private Logradouro logradouro;
    private EnderecoReferencia enderecoReferencia;
    private Cep cep;

    public Agencia(String codigoAgencia, String nomeAgencia, String numeroTelefone, String numeroRamal, String numeroImovel, String complementoEndereco, Date ultimaAlteracao, String numeroFax, String email, Bairro bairro, LogradouroCep logradouroCep, LogradouroBairro logradouroBairro, gcom.arrecadacao.banco.Banco banco, Logradouro logradouro, EnderecoReferencia enderecoReferencia, Cep cep) {
        this.codigoAgencia = codigoAgencia;
        this.nomeAgencia = nomeAgencia;
        this.numeroTelefone = numeroTelefone;
        this.numeroRamal = numeroRamal;
        this.numeroImovel = numeroImovel;
        this.complementoEndereco = complementoEndereco;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroFax = numeroFax;
        this.email = email;
        this.bairro = bairro;
        this.logradouroCep = logradouroCep;
        this.logradouroBairro = logradouroBairro;
        this.banco = banco;
        this.logradouro = logradouro;
        this.enderecoReferencia = enderecoReferencia;
        this.cep = cep;
    }

    public Agencia() {
    }

    public Agencia(String nomeAgencia, String numeroTelefone, String numeroRamal, Bairro bairro, gcom.arrecadacao.banco.Banco banco, Logradouro logradouro, EnderecoReferencia enderecoReferencia, Cep cep) {
        this.nomeAgencia = nomeAgencia;
        this.numeroTelefone = numeroTelefone;
        this.numeroRamal = numeroRamal;
        this.bairro = bairro;
        this.banco = banco;
        this.logradouro = logradouro;
        this.enderecoReferencia = enderecoReferencia;
        this.cep = cep;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoAgencia() {
        return this.codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNomeAgencia() {
        return this.nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public String getNumeroTelefone() {
        return this.numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getNumeroRamal() {
        return this.numeroRamal;
    }

    public void setNumeroRamal(String numeroRamal) {
        this.numeroRamal = numeroRamal;
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

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getNumeroFax() {
        return this.numeroFax;
    }

    public void setNumeroFax(String numeroFax) {
        this.numeroFax = numeroFax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public gcom.arrecadacao.banco.Banco getBanco() {
        return this.banco;
    }

    public void setBanco(gcom.arrecadacao.banco.Banco banco) {
        this.banco = banco;
    }

    public Logradouro getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
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

		//verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null && 
			!this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
				&& !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
				&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro().getLogradouroTitulo()
								.getDescricao();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia()
								.getDescricao().equals("")) {
					endereco = endereco
							+ " - "
							+ this.getEnderecoReferencia()
									.getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
				&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
					endereco = endereco + " - "
							+ this.getLogradouroBairro().getBairro().getNome();
					
					if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
						endereco = endereco + " "
								+ this.getLogradouroBairro().getBairro().getMunicipio().getNome();
					}

					if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao()
						.getId().intValue() != 0) {
						endereco = endereco
								+ " "
								+ this.getLogradouroBairro().getBairro().getMunicipio()
										.getUnidadeFederacao().getSigla();
					}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado();
			}

		} 

		return endereco;
	}
	
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroAgencia filtroAgencia = new FiltroAgencia();
		
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("banco");
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroAgencia. adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroAgencia. adicionarParametro(
				new ParametroSimples(FiltroAgencia .ID, this.getId()));
		return filtroAgencia; 
	}
}    