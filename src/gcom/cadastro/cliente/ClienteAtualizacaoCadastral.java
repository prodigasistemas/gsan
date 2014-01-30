package gcom.cadastro.cliente;

import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
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

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nome;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    public Integer idClienteRelacaoTipo;

    private Integer idClienteTipo;

    private String cpf;
    
    private String cnpj;

    private String rg;
	
	private String dsAbreviadaOrgaoExpedidorRg;
	
	private String dsUFSiglaOrgaoExpedidorRg;

    private Date dataEmissaoRg;
	
    private Date dataNascimento;

    private Integer idProfissao;
	
    private Integer idRamoAtividade;

    private Integer idPessoaSexo;

    private String email;
	
    private String nomeMae;

    private Short indicadorUso;

    private Date ultimaAlteracao;

    private Integer idEnderecoTipo;
	
    private Integer idLogradouroTipo;
    
    private String dsLogradouroTipo;
	
    private Integer idLogradouroTitulo;
    
    private String dsLogradouroTitulo;
	
    private Integer idLogradouro;

    private String descricaoLogradouro;

    private Integer codigoCep;

    private Integer idBairro;

    private String nomeBairro;

    private String numeroImovel;

    private String complementoEndereco;
	
    private Integer idEnderecoReferencia;

    private Integer cnae;
    
	private Integer idMunicipio;
	
	private String nomeMunicipio;
	
	private Integer idUinidadeFederacao;
	
	private String dsUFSiglaMunicipio;
	
	private Short indicadorNomeConta;
	
	private ClienteTipo clienteTipo;
	
	private UnidadeFederacao unidadeFederacao;
	
	private PessoaSexo pessoaSexo;
	
	private Imovel imovel;
	
	private Cliente cliente;
	
	private ClienteRelacaoTipo clienteRelacaoTipo;

	public ClienteAtualizacaoCadastral(){
		clienteTipo = new ClienteTipo();
		unidadeFederacao = new UnidadeFederacao();
		pessoaSexo = new PessoaSexo();
	}

	public ClienteAtualizacaoCadastral(Integer id, Integer idCliente, Integer idImovel, String nomeCliente, Integer idClienteRelacaoTipo, Integer idClienteTipo, String cpfCnpj, String rg, String dsAbreviadaOrgaoExpedidorRg, String dsUFSiglaOrgaoExpedidorRg, Date dataEmissaoRg, Date dataNascimento, Integer idProfissao, Integer idRamoAtividade, Integer idPessoaSexo, String email, String nomeMae, Short indicadorUso, Date ultimaAlteracao, Integer idEnderecoTipo, Integer idLogradouroTipo, String dsLogradouroTipo, Integer idLogradouroTitulo, String dsLogradouroTitulo, Integer idLogradouro, String descricaoLogradouro, Integer codigoCep, Integer idBairro, String nomeBairro, String numeroImovel, String complementoEndereco, Integer idEnderecoReferencia, Integer cnae, Integer idMunicipio, String nomeMunicipio, Integer idUinidadeFederacao, String dsUFSiglaMunicipio) {
		this.id = id;
		this.idCliente = idCliente;
		this.idImovel = idImovel;
		this.nome = nomeCliente;
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
		this.idClienteTipo = idClienteTipo;
		this.cpf = cpfCnpj;
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

	public Integer getIdCliente() {
        return this.idCliente;
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

	public String getNome() {
        return this.nome;
    }

    public void setNome(String nomeCliente) {
        this.nome = nomeCliente;
    }

	public String getRg() {
        return this.rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataEmissaoRg() {
        return this.dataEmissaoRg;
    }

    public void setDataEmissaoRg(Date dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

	public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdProfissao() {
        return this.idProfissao;
    }

    public void setIdProfissao(Integer idProfissao) {
        this.idProfissao = idProfissao;
    }

    public Integer getIdPessoaSexo() {
        return this.idPessoaSexo;
    }

    public void setIdPessoaSexo(Integer idPessoaSexo) {
        this.idPessoaSexo = idPessoaSexo;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNomeMae() {
        return this.nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Integer getIdEnderecoTipo() {
        return this.idEnderecoTipo;
    }

    public void setIdEnderecoTipo(Integer idEnderecoTipo) {
        this.idEnderecoTipo = idEnderecoTipo;
    }

    public Integer getIdLogradouro() {
        return this.idLogradouro;
    }

    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getDescricaoLogradouro() {
        return this.descricaoLogradouro;
    }

    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    public Integer getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	public Integer getIdBairro() {
        return this.idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return this.nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getIdEnderecoReferencia() {
        return this.idEnderecoReferencia;
    }

    public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
        this.idEnderecoReferencia = idEnderecoReferencia;
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

    public Integer getCnae() {
        return this.cnae;
    }

    public void setCnae(Integer cnae) {
        this.cnae = cnae;
    }

    public Integer getIdClienteRelacaoTipo() {
        return this.idClienteRelacaoTipo;
    }

    public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
        this.idClienteRelacaoTipo = idClienteRelacaoTipo;
    }

	public String getDsLogradouroTipo() {
		return dsLogradouroTipo;
	}

	public void setDsLogradouroTipo(String dsLogradouroTipo) {
		this.dsLogradouroTipo = dsLogradouroTipo;
	}

	public String getDsLogradouroTitulo() {
		return dsLogradouroTitulo;
	}

	public void setDsLogradouroTitulo(String dsLogradouroTitulo) {
		this.dsLogradouroTitulo = dsLogradouroTitulo;
	}

	public Integer getIdLogradouroTipo() {
		return idLogradouroTipo;
	}

	public void setIdLogradouroTipo(Integer idLogradouroTipo) {
		this.idLogradouroTipo = idLogradouroTipo;
	}

	public Integer getIdLogradouroTitulo() {
		return idLogradouroTitulo;
	}

	public void setIdLogradouroTitulo(Integer idLogradouroTitulo) {
		this.idLogradouroTitulo = idLogradouroTitulo;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("idCliente", getIdCliente())
            .toString();
    }

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getIdImovel()));
		return filtro;

	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsAbreviadaOrgaoExpedidorRg() {
		return dsAbreviadaOrgaoExpedidorRg;
	}

	public void setDsAbreviadaOrgaoExpedidorRg(String dsAbreviadaOrgaoExpedidorRg) {
		this.dsAbreviadaOrgaoExpedidorRg = dsAbreviadaOrgaoExpedidorRg;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public String getDsUFSiglaOrgaoExpedidorRg() {
		return dsUFSiglaOrgaoExpedidorRg;
	}

	public void setDsUFSiglaOrgaoExpedidorRg(String dsUFSiglaOrgaoExpedidorRg) {
		this.dsUFSiglaOrgaoExpedidorRg = dsUFSiglaOrgaoExpedidorRg;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdUinidadeFederacao() {
		return idUinidadeFederacao;
	}

	public void setIdUinidadeFederacao(Integer idUinidadeFederacao) {
		this.idUinidadeFederacao = idUinidadeFederacao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Integer getIdRamoAtividade() {
		return idRamoAtividade;
	}

	public void setIdRamoAtividade(Integer idRamoAtividade) {
		this.idRamoAtividade = idRamoAtividade;
	}
	
	public ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}


	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}


	public PessoaSexo getPessoaSexo() {
		return pessoaSexo;
	}


	public void setPessoaSexo(PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}
	
	public Imovel getImovel() {
		return imovel;
	}
	
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ClienteRelacaoTipo getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}
	
	public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}
	
	public Short getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(Short indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}
	
}
