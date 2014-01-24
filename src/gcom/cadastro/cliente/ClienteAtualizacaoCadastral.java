package gcom.cadastro.cliente;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ClienteAtualizacaoCadastral extends ObjetoTransacao implements IClienteAtualizacaoCadastral {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;
	
    private Integer id;
	
    private Integer idCliente;
    
    private Integer idImovel;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeCliente;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idClienteRelacaoTipo;

    /** nullable persistent field */
//	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idClienteTipo;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String cpfCnpj;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String rg;
	
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
	private String dsAbreviadaOrgaoExpedidorRg;
	
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
	private String dsUFSiglaOrgaoExpedidorRg;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Date dataEmissaoRg;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Date dataNascimento;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idProfissao;
	
//	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idRamoAtividade;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idPessoaSexo;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String email;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeMae;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idEnderecoTipo;
	
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouroTipo;
    
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String dsLogradouroTipo;
	
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouroTitulo;
    
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String dsLogradouroTitulo;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouro;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoLogradouro;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer codigoCep;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idBairro;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeBairro;

    ///** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroImovel;

    ///** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String complementoEndereco;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idEnderecoReferencia;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer cnae;
    
	private Integer idMunicipio;
	
	private String nomeMunicipio;
	
	private Integer idUinidadeFederacao;
	
	private String dsUFSiglaMunicipio;

	public ClienteAtualizacaoCadastral(){		
	}

	public ClienteAtualizacaoCadastral(Integer id, Integer idCliente, Integer idImovel, String nomeCliente, Integer idClienteRelacaoTipo, Integer idClienteTipo, String cpfCnpj, String rg, String dsAbreviadaOrgaoExpedidorRg, String dsUFSiglaOrgaoExpedidorRg, Date dataEmissaoRg, Date dataNascimento, Integer idProfissao, Integer idRamoAtividade, Integer idPessoaSexo, String email, String nomeMae, Short indicadorUso, Date ultimaAlteracao, Integer idEnderecoTipo, Integer idLogradouroTipo, String dsLogradouroTipo, Integer idLogradouroTitulo, String dsLogradouroTitulo, Integer idLogradouro, String descricaoLogradouro, Integer codigoCep, Integer idBairro, String nomeBairro, String numeroImovel, String complementoEndereco, Integer idEnderecoReferencia, Integer cnae, Integer idMunicipio, String nomeMunicipio, Integer idUinidadeFederacao, String dsUFSiglaMunicipio) {
		this.id = id;
		this.idCliente = idCliente;
		this.idImovel = idImovel;
		this.nomeCliente = nomeCliente;
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
		this.idClienteTipo = idClienteTipo;
		this.cpfCnpj = cpfCnpj;
		this.rg = rg;
		this.dsAbreviadaOrgaoExpedidorRg = dsAbreviadaOrgaoExpedidorRg;
		this.dsUFSiglaOrgaoExpedidorRg = dsUFSiglaOrgaoExpedidorRg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.idProfissao = idProfissao;
		this.idRamoAtividade = idRamoAtividade;
		this.idPessoaSexo = idPessoaSexo;
		this.email = email;
		this.nomeMae = nomeMae;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.idEnderecoTipo = idEnderecoTipo;
		this.idLogradouroTipo = idLogradouroTipo;
		this.dsLogradouroTipo = dsLogradouroTipo;
		this.idLogradouroTitulo = idLogradouroTitulo;
		this.dsLogradouroTitulo = dsLogradouroTitulo;
		this.idLogradouro = idLogradouro;
		this.descricaoLogradouro = descricaoLogradouro;
		this.codigoCep = codigoCep;
		this.idBairro = idBairro;
		this.nomeBairro = nomeBairro;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.idEnderecoReferencia = idEnderecoReferencia;
		this.cnae = cnae;
		this.idMunicipio = idMunicipio;
		this.nomeMunicipio = nomeMunicipio;
		this.idUinidadeFederacao = idUinidadeFederacao;
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdCliente()
	 */
	public Integer getIdCliente() {
        return this.idCliente;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdCliente(java.lang.Integer)
	 */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdImovel()
	 */
    public Integer getIdImovel() {
		return idImovel;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdImovel(java.lang.Integer)
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getNomeCliente()
	 */
	public String getNomeCliente() {
        return this.nomeCliente;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setNomeCliente(java.lang.String)
	 */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdClienteTipo()
	 */
    public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdClienteTipo(java.lang.Integer)
	 */
	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getRg()
	 */
	public String getRg() {
        return this.rg;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setRg(java.lang.String)
	 */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDataEmissaoRg()
	 */
    public Date getDataEmissaoRg() {
        return this.dataEmissaoRg;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDataEmissaoRg(java.util.Date)
	 */
    public void setDataEmissaoRg(Date dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDataNascimento()
	 */
	public Date getDataNascimento() {
        return this.dataNascimento;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDataNascimento(java.util.Date)
	 */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdProfissao()
	 */
    public Integer getIdProfissao() {
        return this.idProfissao;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdProfissao(java.lang.Integer)
	 */
    public void setIdProfissao(Integer idProfissao) {
        this.idProfissao = idProfissao;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdPessoaSexo()
	 */
    public Integer getIdPessoaSexo() {
        return this.idPessoaSexo;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdPessoaSexo(java.lang.Integer)
	 */
    public void setIdPessoaSexo(Integer idPessoaSexo) {
        this.idPessoaSexo = idPessoaSexo;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getCpfCnpj()
	 */
    public String getCpfCnpj() {
        return this.cpfCnpj;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setCpfCnpj(java.lang.String)
	 */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getEmail()
	 */
    public String getEmail() {
        return this.email;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setEmail(java.lang.String)
	 */
    public void setEmail(String email) {
        this.email = email;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIndicadorUso()
	 */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIndicadorUso(java.lang.Short)
	 */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getUltimaAlteracao()
	 */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setUltimaAlteracao(java.util.Date)
	 */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getNomeMae()
	 */
    public String getNomeMae() {
        return this.nomeMae;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setNomeMae(java.lang.String)
	 */
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdEnderecoTipo()
	 */
    public Integer getIdEnderecoTipo() {
        return this.idEnderecoTipo;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdEnderecoTipo(java.lang.Integer)
	 */
    public void setIdEnderecoTipo(Integer idEnderecoTipo) {
        this.idEnderecoTipo = idEnderecoTipo;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdLogradouro()
	 */
    public Integer getIdLogradouro() {
        return this.idLogradouro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdLogradouro(java.lang.Integer)
	 */
    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDescricaoLogradouro()
	 */
    public String getDescricaoLogradouro() {
        return this.descricaoLogradouro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDescricaoLogradouro(java.lang.String)
	 */
    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getCodigoCep()
	 */
    public Integer getCodigoCep() {
		return codigoCep;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setCodigoCep(java.lang.Integer)
	 */
	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdBairro()
	 */
	public Integer getIdBairro() {
        return this.idBairro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdBairro(java.lang.Integer)
	 */
    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getNomeBairro()
	 */
    public String getNomeBairro() {
        return this.nomeBairro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setNomeBairro(java.lang.String)
	 */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdEnderecoReferencia()
	 */
    public Integer getIdEnderecoReferencia() {
        return this.idEnderecoReferencia;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdEnderecoReferencia(java.lang.Integer)
	 */
    public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
        this.idEnderecoReferencia = idEnderecoReferencia;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getNumeroImovel()
	 */
    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setNumeroImovel(java.lang.String)
	 */
    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getComplementoEndereco()
	 */
    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setComplementoEndereco(java.lang.String)
	 */
    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getCnae()
	 */
    public Integer getCnae() {
        return this.cnae;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setCnae(java.lang.Integer)
	 */
    public void setCnae(Integer cnae) {
        this.cnae = cnae;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdClienteRelacaoTipo()
	 */
    public Integer getIdClienteRelacaoTipo() {
        return this.idClienteRelacaoTipo;
    }

    /* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdClienteRelacaoTipo(java.lang.Integer)
	 */
    public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
        this.idClienteRelacaoTipo = idClienteRelacaoTipo;
    }

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDsLogradouroTipo()
	 */
	public String getDsLogradouroTipo() {
		return dsLogradouroTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDsLogradouroTipo(java.lang.String)
	 */
	public void setDsLogradouroTipo(String dsLogradouroTipo) {
		this.dsLogradouroTipo = dsLogradouroTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDsLogradouroTitulo()
	 */
	public String getDsLogradouroTitulo() {
		return dsLogradouroTitulo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDsLogradouroTitulo(java.lang.String)
	 */
	public void setDsLogradouroTitulo(String dsLogradouroTitulo) {
		this.dsLogradouroTitulo = dsLogradouroTitulo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdLogradouroTipo()
	 */
	public Integer getIdLogradouroTipo() {
		return idLogradouroTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdLogradouroTipo(java.lang.Integer)
	 */
	public void setIdLogradouroTipo(Integer idLogradouroTipo) {
		this.idLogradouroTipo = idLogradouroTipo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdLogradouroTitulo()
	 */
	public Integer getIdLogradouroTitulo() {
		return idLogradouroTitulo;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdLogradouroTitulo(java.lang.Integer)
	 */
	public void setIdLogradouroTitulo(Integer idLogradouroTitulo) {
		this.idLogradouroTitulo = idLogradouroTitulo;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("idCliente", getIdCliente())
            .toString();
    }

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#retornaFiltro()
	 */
	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getIdImovel()));
		return filtro;

	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#retornaCamposChavePrimaria()
	 */
	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getId()
	 */
	public Integer getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDsAbreviadaOrgaoExpedidorRg()
	 */
	public String getDsAbreviadaOrgaoExpedidorRg() {
		return dsAbreviadaOrgaoExpedidorRg;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDsAbreviadaOrgaoExpedidorRg(java.lang.String)
	 */
	public void setDsAbreviadaOrgaoExpedidorRg(String dsAbreviadaOrgaoExpedidorRg) {
		this.dsAbreviadaOrgaoExpedidorRg = dsAbreviadaOrgaoExpedidorRg;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDsUFSiglaMunicipio()
	 */
	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDsUFSiglaMunicipio(java.lang.String)
	 */
	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getDsUFSiglaOrgaoExpedidorRg()
	 */
	public String getDsUFSiglaOrgaoExpedidorRg() {
		return dsUFSiglaOrgaoExpedidorRg;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setDsUFSiglaOrgaoExpedidorRg(java.lang.String)
	 */
	public void setDsUFSiglaOrgaoExpedidorRg(String dsUFSiglaOrgaoExpedidorRg) {
		this.dsUFSiglaOrgaoExpedidorRg = dsUFSiglaOrgaoExpedidorRg;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdMunicipio()
	 */
	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdMunicipio(java.lang.Integer)
	 */
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdUinidadeFederacao()
	 */
	public Integer getIdUinidadeFederacao() {
		return idUinidadeFederacao;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdUinidadeFederacao(java.lang.Integer)
	 */
	public void setIdUinidadeFederacao(Integer idUinidadeFederacao) {
		this.idUinidadeFederacao = idUinidadeFederacao;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getNomeMunicipio()
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setNomeMunicipio(java.lang.String)
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#getIdRamoAtividade()
	 */
	public Integer getIdRamoAtividade() {
		return idRamoAtividade;
	}

	/* (non-Javadoc)
	 * @see gcom.cadastro.cliente.IClienteAtualizacaoCadastral#setIdRamoAtividade(java.lang.Integer)
	 */
	public void setIdRamoAtividade(Integer idRamoAtividade) {
		this.idRamoAtividade = idRamoAtividade;
	}
	
	
}
