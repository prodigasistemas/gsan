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
public class ClienteAtualizacaoCadastral extends ObjetoTransacao {
	
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

	public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
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

    public String getCpfCnpj() {
        return this.cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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
		// TODO Auto-generated method stub
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
	
	
}
