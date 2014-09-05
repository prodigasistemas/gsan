package gcom.cadastro.cliente;

import gcom.atualizacaocadastral.ICliente;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class Cliente extends ObjetoTransacao implements ICliente {

	public static final int ATRIBUTOS_CLIENTE_INSERIR = 28;
	public static final int ATRIBUTOS_CLIENTE_ATUALIZAR = 38;
	public static final int ATRIBUTOS_CLIENTE_REMOVER = 39;
	public static final int ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS = 1662;
	public static final int OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	public static final Short INDICADOR_NOME_FANTASIA = 1;
	public static final Short INDICADOR_NOME_RECEITA = 2;

	private static final long serialVersionUID = 1L;

	private Integer id;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private String nome;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private String nomeAbreviado;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private String cpf;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private String rg;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Date dataEmissaoRg;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short dataVencimento;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Date dataNascimento;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private String cnpj;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private String email;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorUso;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Date ultimaAlteracao;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorAcaoCobranca;

	@ControleAlteracao(value = FiltroCliente.ORGAO_EXPEDIDOR_RG, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg;

	private gcom.cadastro.cliente.Cliente cliente;

	@ControleAlteracao(value = FiltroCliente.SEXO, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private gcom.cadastro.cliente.PessoaSexo pessoaSexo;

	@ControleAlteracao(value = FiltroCliente.PROFISSAO, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private gcom.cadastro.cliente.Profissao profissao;

	@ControleAlteracao(value = FiltroCliente.UNIDADE_FEDERACAO, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private UnidadeFederacao unidadeFederacao;

	@ControleAlteracao(value = FiltroCliente.CLIENTE_TIPO, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private gcom.cadastro.cliente.ClienteTipo clienteTipo;

	@ControleAlteracao(value = FiltroCliente.RAMO_ATIVIDADE, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private gcom.cadastro.cliente.RamoAtividade ramoAtividade;

	@ControleAlteracao(value = FiltroCliente.CLIENTE_TELEFONES, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL })
	private Set clienteFones;

	private Set clienteImoveis;

	@ControleAlteracao(value = FiltroCliente.CLIENTE_ENDERECOS, funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Set clienteEnderecos;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short diaVencimento;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private String nomeMae;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorAcrescimos;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorGeraArquivoTexto;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorGeraFaturaAntecipada;

	@ControleAlteracao(funcionalidade = { ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR })
	private Short indicadorVencimentoMesSeguinte;

	private Short indicadorUsoNomeFantasiaConta;

	private Short indicadorPermiteNegativacao;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorNegativacaoPeriodo;

	public static final Short GERA_ARQUIVO_TEXTO_SIM = 1;

	public Cliente(String nome, String nomeAbreviado, String cpf, String rg, Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Date ultimaAlteracao, gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg, gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo, gcom.cadastro.cliente.Profissao profissao, UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo, gcom.cadastro.cliente.RamoAtividade ramoAtividade, Short diaVencimento) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
		this.diaVencimento = diaVencimento;
		this.indicadorNegativacaoPeriodo = ConstantesSistema.NAO;
	}

	/**
	 * full constructor
	 */
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg, Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Date ultimaAlteracao, gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg, gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo, gcom.cadastro.cliente.Profissao profissao, UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo, gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
	}

	public Cliente(String nome, String nomeAbreviado, String cpf, String rg, Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Date ultimaAlteracao, gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg, gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo, gcom.cadastro.cliente.Profissao profissao, UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo, Short indicadorUsoNomeFantasiaConta, gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
		this.ramoAtividade = ramoAtividade;

	}

	/**
	 * full constructor
	 */
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg, Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Short indicadorAcrescimos, Date ultimaAlteracao, gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente, gcom.cadastro.cliente.PessoaSexo pessoaSexo, gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao, gcom.cadastro.cliente.ClienteTipo clienteTipo, gcom.cadastro.cliente.RamoAtividade ramoAtividade,
			Short indicadorUsoNomeFantasiaConta) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.indicadorAcrescimos = indicadorAcrescimos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
	}

	/**
	 * full constructor para atualização cadastral
	 */
	public Cliente(Cliente clienteCadastrado) {
		this.nome = clienteCadastrado.getNome();
		this.cpf = clienteCadastrado.getCpf();
		this.cnpj = clienteCadastrado.getCnpj();
		this.indicadorUso = clienteCadastrado.getIndicadorUso();
		this.indicadorAcrescimos = clienteCadastrado.getIndicadorAcrescimos();
		this.ultimaAlteracao = clienteCadastrado.getUltimaAlteracao();
		this.orgaoExpedidorRg = clienteCadastrado.getOrgaoExpedidorRg();
		this.cliente = clienteCadastrado.getCliente();
		this.pessoaSexo = clienteCadastrado.getPessoaSexo();
		this.profissao = clienteCadastrado.getProfissao();
		this.unidadeFederacao = clienteCadastrado.getUnidadeFederacao();
		this.clienteTipo = clienteCadastrado.getClienteTipo();
		this.ramoAtividade = clienteCadastrado.getRamoAtividade();
		this.indicadorUsoNomeFantasiaConta = clienteCadastrado.getIndicadorUsoNomeFantasiaConta();
		this.indicadorAcaoCobranca = ConstantesSistema.NAO;
		this.indicadorGeraArquivoTexto = ConstantesSistema.NAO;
		this.indicadorGeraFaturaAntecipada = ConstantesSistema.NAO;
		this.indicadorPermiteNegativacao = ConstantesSistema.NAO;
		this.indicadorVencimentoMesSeguinte = ConstantesSistema.NAO;
	}

	public Cliente(String nome) {
		this.nome = nome;

	}

	public Cliente(String nome, Integer id) {
		this.nome = nome;
		this.id = id;
	}

	public Cliente(Integer id, String nome, gcom.cadastro.cliente.ClienteTipo clienteTipo, String cpf, String cnpj) {
		this.id = id;
		this.nome = nome;
		this.clienteTipo = clienteTipo;
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	public Cliente() {}

	public Cliente(gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg, gcom.cadastro.cliente.Cliente cliente, gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao, UnidadeFederacao unidadeFederacao, gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
	}

	public Cliente(Integer id, String nome, Short dataVencimento) {
		this.id = id;
		this.nome = nome;
		this.dataVencimento = dataVencimento;
	}

	public Cliente(Integer id, String nome, Short dataVencimento, Short indicadorVencimentoMesSeguinte) {
		this.id = id;
		this.nome = nome;
		this.dataVencimento = dataVencimento;
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public Cliente(Integer idCliente) {
		this.id = idCliente;
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

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public gcom.cadastro.cliente.OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return this.orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	public gcom.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gcom.cadastro.cliente.PessoaSexo getPessoaSexo() {
		return this.pessoaSexo;
	}

	public void setPessoaSexo(gcom.cadastro.cliente.PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	public gcom.cadastro.cliente.Profissao getProfissao() {
		return this.profissao;
	}

	public void setProfissao(gcom.cadastro.cliente.Profissao profissao) {
		this.profissao = profissao;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return this.unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public gcom.cadastro.cliente.ClienteTipo getClienteTipo() {
		return this.clienteTipo;
	}

	public void setClienteTipo(gcom.cadastro.cliente.ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public gcom.cadastro.cliente.RamoAtividade getRamoAtividade() {
		return this.ramoAtividade;
	}

	public void setRamoAtividade(gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Cliente)) {
			return false;
		}
		Cliente castOther = (Cliente) other;

		return (this.getId().equals(castOther.getId()));
	}

	public String getCpfFormatado() {
		String cpfFormatado = this.cpf;

		if (cpfFormatado != null && cpfFormatado.length() == 11) {

			cpfFormatado = cpfFormatado.substring(0, 3) + "." + cpfFormatado.substring(3, 6) + "." + cpfFormatado.substring(6, 9) + "-"
					+ cpfFormatado.substring(9, 11);
		}

		return cpfFormatado;
	}

	public String getCnpjFormatado() {
		String cnpjFormatado = this.cnpj;
		String zeros = "";

		if (cnpjFormatado != null) {

			for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero caso o numero seja diferente de nulo
			cnpjFormatado = zeros.concat(cnpjFormatado);
			
			cnpjFormatado = cnpjFormatado.substring(0, 2) + "." + cnpjFormatado.substring(2, 5) + "." + cnpjFormatado.substring(5, 8) + "/"
					+ cnpjFormatado.substring(8, 12) + "-" + cnpjFormatado.substring(12, 14);
		}

		return cnpjFormatado;
	}

	public Set getClienteEnderecos() {
		return clienteEnderecos;
	}

	public void setClienteEnderecos(Set clienteEnderecos) {
		this.clienteEnderecos = clienteEnderecos;
	}

	public Set getClienteFones() {
		return clienteFones;
	}

	public void setClienteFones(Set clienteFones) {
		this.clienteFones = clienteFones;
	}

	public Set getClienteImoveis() {
		return clienteImoveis;
	}

	public void setClienteImoveis(Set clienteImoveis) {
		this.clienteImoveis = clienteImoveis;
	}

	public Short getIndicadorAcaoCobranca() {
		return indicadorAcaoCobranca;
	}

	public void setIndicadorAcaoCobranca(Short indicadorAcaoCobranca) {
		this.indicadorAcaoCobranca = indicadorAcaoCobranca;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Filtro retornaFiltro() {
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, this.getId()));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("pessoaSexo");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteEnderecos");
		return filtroCliente;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Short getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Short dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Short getIndicadorAcrescimos() {
		return indicadorAcrescimos;
	}

	public void setIndicadorAcrescimos(Short indicadorCobrancaAcrescimos) {
		this.indicadorAcrescimos = indicadorCobrancaAcrescimos;
	}

	public Short getIndicadorGeraArquivoTexto() {
		return indicadorGeraArquivoTexto;
	}

	public void setIndicadorGeraArquivoTexto(Short indicadorGeraArquivoTexto) {
		this.indicadorGeraArquivoTexto = indicadorGeraArquivoTexto;
	}

	public String getDescricao() {
		return this.nome;
	}

	public Short getIndicadorGeraFaturaAntecipada() {
		return indicadorGeraFaturaAntecipada;
	}

	public void setIndicadorGeraFaturaAntecipada(Short indicadorGeraFaturaAntecipada) {
		this.indicadorGeraFaturaAntecipada = indicadorGeraFaturaAntecipada;
	}

	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getCodigoNome() {
		return this.id + " - " + this.nome;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getNome();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = { "cpf", "cnpj", "nome" };
		return labels;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = { "CPF", "CNPJ", "Nome" };
		return labels;
	}

	public Short getIndicadorPermiteNegativacao() {
		return indicadorPermiteNegativacao;
	}

	public void setIndicadorPermiteNegativacao(Short indicadorPermiteNegativacao) {
		this.indicadorPermiteNegativacao = indicadorPermiteNegativacao;
	}

	public Integer getIdCliente() {
		return null;
	}

	public void setIdCliente(Integer idCliente) {
	}

	public Integer getIdClienteTipo() {
		return null;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
	}

	public Integer getTipoOperacao() {
		return null;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
	}

	public Short getIndicadorUsoNomeFantasiaConta() {
		return indicadorUsoNomeFantasiaConta;
	}

	public void setIndicadorUsoNomeFantasiaConta(Short indicadorUsoNomeFantasiaConta) {
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
	}
	
	public Short getIndicadorNegativacaoPeriodo() {
		return indicadorNegativacaoPeriodo;
	}

	public void setIndicadorNegativacaoPeriodo(Short indicadorNegativacaoPeriodo) {
		this.indicadorNegativacaoPeriodo = indicadorNegativacaoPeriodo;
	}
}
