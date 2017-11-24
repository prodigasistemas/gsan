package gcom.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Municipio;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import javax.jms.Session;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Localidade extends ObjetoTransacao implements DescriptorEntity {

	public final static int AURORA = 347;
	public final static int CABANGA = 339;
	public final static int ALTO_DO_CEU = 360;
	public final static int JENIPAPO = 735;

	public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private String numeroImovel;
	private String complementoEndereco;
	private String fone;
	private String ramalfone;
	private String fax;
	private String email;
	private int consumoGrandeUsuario;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private LocalidadePorte localidadePorte;
	private Localidade localidade;
	private GerenciaRegional gerenciaRegional;
	private EnderecoReferencia enderecoReferencia;
	private LocalidadeClasse localidadeClasse;
	private LogradouroCep logradouroCep;
	private LogradouroBairro logradouroBairro;
	private Integer codigoICMS;
	private String codigoCentroCusto;
	private Short indicadorLocalidadeInformatizada;
	private Cliente cliente;
	private HidrometroLocalArmazenagem hidrometroLocalArmazenagem;
	private UnidadeNegocio unidadeNegocio;
	private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;
	private Short indicadorLocalidadeSede;
	private String codigoCentroCustoEsgoto;
	private Municipio municipio;
	private ConsumoTarifa consumoTarifa;

	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public Integer getCodigoICMS() {
		return codigoICMS;
	}

	public void setCodigoICMS(Integer codigoICMS) {
		this.codigoICMS = codigoICMS;
	}

	public Localidade(Integer id, String descricao, String numeroImovel, String complementoEndereco, String fone, String ramalfone, String fax, String email,
			int consumoGrandeUsuario, Short indicadorUso, Date ultimaAlteracao, gcom.cadastro.localidade.LocalidadePorte localidadePorte,
			gcom.cadastro.localidade.Localidade localidade, gcom.cadastro.localidade.GerenciaRegional gerenciaRegional, EnderecoReferencia enderecoReferencia,
			gcom.cadastro.localidade.LocalidadeClasse localidadeClasse, LogradouroCep logradouroCep, LogradouroBairro logradouroBairro) {
		this.id = id;
		this.descricao = descricao;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.fone = fone;
		this.ramalfone = ramalfone;
		this.fax = fax;
		this.email = email;
		this.consumoGrandeUsuario = consumoGrandeUsuario;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.localidadePorte = localidadePorte;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.enderecoReferencia = enderecoReferencia;
		this.localidadeClasse = localidadeClasse;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}

	public Localidade() {
	}

	public Localidade(Integer id) {
		this.id = id;
	}

	public Localidade(Integer id, String descricao, String fone, String ramalfone, int consumoGrandeUsuario,
			gcom.cadastro.localidade.LocalidadePorte localidadePorte, gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.GerenciaRegional gerenciaRegional, EnderecoReferencia enderecoReferencia,
			gcom.cadastro.localidade.LocalidadeClasse localidadeClasse, LogradouroCep logradouroCep, LogradouroBairro logradouroBairro) {
		this.id = id;
		this.descricao = descricao;
		this.fone = fone;
		this.ramalfone = ramalfone;
		this.consumoGrandeUsuario = consumoGrandeUsuario;
		this.localidadePorte = localidadePorte;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.enderecoReferencia = enderecoReferencia;
		this.localidadeClasse = localidadeClasse;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}

	public Localidade(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public String getRamalfone() {
		return this.ramalfone;
	}

	public void setRamalfone(String ramalfone) {
		this.ramalfone = ramalfone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public gcom.cadastro.localidade.LocalidadePorte getLocalidadePorte() {
		return this.localidadePorte;
	}

	public void setLocalidadePorte(gcom.cadastro.localidade.LocalidadePorte localidadePorte) {
		this.localidadePorte = localidadePorte;
	}

	public gcom.cadastro.localidade.Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.cadastro.localidade.GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(gcom.cadastro.localidade.GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public gcom.cadastro.localidade.LocalidadeClasse getLocalidadeClasse() {
		return this.localidadeClasse;
	}

	public void setLocalidadeClasse(gcom.cadastro.localidade.LocalidadeClasse localidadeClasse) {
		this.localidadeClasse = localidadeClasse;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean onSave(Session session) {
		if (this.localidade == null) {
			Localidade localidadeElo = new Localidade();

			localidadeElo.setId(this.id);
			this.localidade = localidadeElo;
		}
		return false;
	}

	public boolean onUpdate(Session session) {
		return false;
	}

	public boolean onDelete(Session session) {
		return false;
	}

	public void onLoad(Session session, Serializable serializable) {
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
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null && !this.getEnderecoReferencia().getDescricao().equals("")) {
					endereco = endereco + " - " + this.getEnderecoReferencia().getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null && !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = endereco + " - " + this.getLogradouroBairro().getBairro().getNome();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoTituloAbreviado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null && !this.getEnderecoReferencia().getDescricao().equals("")) {
					endereco = endereco + " - " + this.getEnderecoReferencia().getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null && !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = endereco + " - " + this.getLogradouroBairro().getBairro().getNome();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado();
			}

		}

		return endereco;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidadePorte");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidadeClasse");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, this.getId()));
		return filtroLocalidade;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public int getConsumoGrandeUsuario() {
		return consumoGrandeUsuario;
	}

	public void setConsumoGrandeUsuario(int consumoGrandeUsuario) {
		this.consumoGrandeUsuario = consumoGrandeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Short getIndicadorLocalidadeInformatizada() {
		return indicadorLocalidadeInformatizada;
	}

	public void setIndicadorLocalidadeInformatizada(Short indicadorLocalidadeInformatizada) {
		this.indicadorLocalidadeInformatizada = indicadorLocalidadeInformatizada;
	}

	public HidrometroLocalArmazenagem getHidrometroLocalArmazenagem() {
		return hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(HidrometroLocalArmazenagem hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		if (getDescricao() != null) {
			return this.id + " - " + this.descricao;
		} else {
			return null;
		}
	}

	@Override
	public void initializeLazy() {
		getDescricao();
		retornaCamposChavePrimaria();
	}

	public Short getIndicadorLocalidadeSede() {
		return indicadorLocalidadeSede;
	}

	public void setIndicadorLocalidadeSede(Short indicadorLocalidadeSede) {
		this.indicadorLocalidadeSede = indicadorLocalidadeSede;
	}

	public String getCodigoCentroCustoEsgoto() {
		return codigoCentroCustoEsgoto;
	}

	public void setCodigoCentroCustoEsgoto(String codigoCentroCustoEsgoto) {
		this.codigoCentroCustoEsgoto = codigoCentroCustoEsgoto;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

}
