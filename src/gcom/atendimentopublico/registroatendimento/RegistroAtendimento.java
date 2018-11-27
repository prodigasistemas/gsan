package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.operacional.DivisaoEsgoto;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RegistroAtendimento extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public final static Integer CONSTANTE = new Integer("1");

	public final static int SITUACAO_TODOS = 0;

	public final static Short SITUACAO_PENDENTE = new Short("1");

	public final static String SITUACAO_DESCRICAO_PENDENTE = "Pendente";

	public final static String SITUACAO_DESC_ABREV_PENDENTE = "Pen";

	public final static Short SITUACAO_ENCERRADO = new Short("2");

	public final static String SITUACAO_DESCRICAO_ENCERRADO = "Encerrado";

	public final static String SITUACAO_DESC_ABREV_ENCERRADO = "Enc";

	public final static Short SITUACAO_BLOQUEADO = new Short("3");

	public final static String SITUACAO_DESCRICAO_BLOQUEADO = "Bloqueado";

	public final static String SITUACAO_DESC_ABREV_BLOQUEADO = "Blq";

	public final static short CODIGO_ASSOCIADO_SEM_RA = 0;

	public final static short CODIGO_ASSOCIADO_RA_REFERENCIA = 1;

	public final static short CODIGO_ASSOCIADO_RA_ATUAL = 2;

	public final static short CODIGO_ASSOCIADO_RA_ANTERIOR = 3;

	public final static String NUMERO_RA_REFERENCIA = "Número do RA de Referência:";

	public final static String NUMERO_RA_ATUAL = "Número do RA Atual:";

	public final static String NUMERO_RA_ANTERIOR = "Número do RA Anterior:";

	public final static String SITUACAO_RA_REFERENCIA = "Situação do RA de Referência:";

	public final static String SITUACAO_RA_ATUAL = "Situação do RA Atual:";

	public final static String SITUACAO_RA_ANTERIOR = "Situação do RA Anterior:";

	public final static Short INDICADOR_MANUTENCAO_RA_NAO = new Short("2");
	
	public final static Short INDICADOR_ATENDIMENTO_ON_LINE = new Short("1");
	
	public final static Short INDICADOR_ATENDIMENTO_MANUAL = new Short("2");

	private Integer id;

	private short codigoSituacao;

	private Date registroAtendimento;

	private Date dataPrevistaOriginal;

	private Date dataPrevistaAtual;

	private Date ultimaEmissao;

	private Date dataEncerramento;

	private Short quantidadeReiteracao;

	private Date ultimaReiteracao;

	private String observacao;

	private String descricaoLocalOcorrencia;

	private Date ultimaAlteracao;

	private String parecerEncerramento;

	private String pontoReferencia;

	private String complementoEndereco;

	private short indicadorAtendimentoOnline;

	private Date dataInicioEspera;

	private Date dataFimEspera;

	private String numeroImovel;

	private LogradouroBairro logradouroBairro;

	private SetorComercial setorComercial;

	private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

	private PavimentoCalcada pavimentoCalcada;

	private LocalOcorrencia localOcorrencia;

	private LogradouroCep logradouroCep;

	private MeioSolicitacao meioSolicitacao;

	private RaMotivoReativacao raMotivoReativacao;

	private RegistroAtendimento registroAtendimentoReativacao;

	private RegistroAtendimento registroAtendimentoDuplicidade;

	private Imovel imovel;

	private BairroArea bairroArea;

	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

	private Quadra quadra;

	private Localidade localidade;

	private PavimentoRua pavimentoRua;

	private DivisaoEsgoto divisaoEsgoto;
	
	private Integer manual;
	
	@SuppressWarnings("rawtypes")
	private Set tramites;
	
	@SuppressWarnings("rawtypes")
	private Set raDadosAgenciaReguladoras;
	
	@SuppressWarnings("rawtypes")
	private Set registroAtendimentoUnidades;
	
    private UnidadeOrganizacional unidadeAtual;
    
    private BigDecimal nnCoordenadaNorte;
    
    private BigDecimal nnCoordenadaLeste;
   
    private Logradouro perimetroInicial;
    
    private Logradouro perimetroFinal;
    
    private ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo;
    
    private Short indicadorCoordenadaSemLogradouro;
    
    private BigDecimal nnDiametro;

	public BigDecimal getNnDiametro() {
		return nnDiametro;
	}

	public void setNnDiametro(BigDecimal nnDiametro) {
		this.nnDiametro = nnDiametro;
	}

	public RegistroAtendimento(
			short codigoSituacao,
			Date registroAtendimento,
			Date dataPrevistaOriginal,
			Date dataPrevistaAtual,
			Date ultimaEmissao,
			Date dataEncerramento,
			Short quantidadeReiteracao,
			Date ultimaReiteracao,
			String observacao,
			String descricaoLocalOcorrencia,
			Date ultimaAlteracao,
			String parecerEncerramento,
			String pontoReferencia,
			String complementoEndereco,
			short indicadorAtendimentoOnline,
			Date dataInicioEspera,
			Date dataFimEspera,
			LogradouroBairro logradouroBairro,
			SetorComercial setorComercial,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.atendimentopublico.registroatendimento.LocalOcorrencia localOcorrencia,
			LogradouroCep logradouroCep,
			gcom.atendimentopublico.registroatendimento.MeioSolicitacao meioSolicitacao,
			gcom.atendimentopublico.registroatendimento.RaMotivoReativacao raMotivoReativacao,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoReativacao,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoDuplicidade,
			Imovel imovel,
			BairroArea bairroArea,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
			Quadra quadra, Localidade localidade,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			DivisaoEsgoto divisaoEsgoto,
			BigDecimal nnCoordenadaNorte,
			BigDecimal nnCoordenadaLeste,
			Logradouro perimetroInicial,
		    Logradouro perimetroFinal,
		    Short indicCoordenadaSemLogradouro) {
		this.codigoSituacao = codigoSituacao;
		this.registroAtendimento = registroAtendimento;
		this.dataPrevistaOriginal = dataPrevistaOriginal;
		this.dataPrevistaAtual = dataPrevistaAtual;
		this.ultimaEmissao = ultimaEmissao;
		this.dataEncerramento = dataEncerramento;
		this.quantidadeReiteracao = quantidadeReiteracao;
		this.ultimaReiteracao = ultimaReiteracao;
		this.observacao = observacao;
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
		this.ultimaAlteracao = ultimaAlteracao;
		this.parecerEncerramento = parecerEncerramento;
		this.pontoReferencia = pontoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
		this.dataInicioEspera = dataInicioEspera;
		this.dataFimEspera = dataFimEspera;
		this.logradouroBairro = logradouroBairro;
		this.setorComercial = setorComercial;
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
		this.pavimentoCalcada = pavimentoCalcada;
		this.localOcorrencia = localOcorrencia;
		this.logradouroCep = logradouroCep;
		this.meioSolicitacao = meioSolicitacao;
		this.raMotivoReativacao = raMotivoReativacao;
		this.registroAtendimentoReativacao = registroAtendimentoReativacao;
		this.registroAtendimentoDuplicidade = registroAtendimentoDuplicidade;
		this.imovel = imovel;
		this.bairroArea = bairroArea;
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
		this.quadra = quadra;
		this.localidade = localidade;
		this.pavimentoRua = pavimentoRua;
		this.divisaoEsgoto = divisaoEsgoto;
		this.nnCoordenadaNorte = nnCoordenadaNorte;
		this.nnCoordenadaLeste = nnCoordenadaLeste;
		this.perimetroInicial = perimetroInicial;
		this.perimetroFinal = perimetroFinal;
		this.indicadorCoordenadaSemLogradouro = indicCoordenadaSemLogradouro;
	}

	/** default constructor */
	public RegistroAtendimento() {
	}

	/** minimal constructor */
	public RegistroAtendimento(
			short codigoSituacao,
			Date registroAtendimento,
			Date dataPrevistaOriginal,
			Date dataPrevistaAtual,
			Date ultimaAlteracao,
			short indicadorAtendimentoOnline,
			LogradouroBairro logradouroBairro,
			SetorComercial setorComercial,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.atendimentopublico.registroatendimento.LocalOcorrencia localOcorrencia,
			LogradouroCep logradouroCep,
			gcom.atendimentopublico.registroatendimento.MeioSolicitacao meioSolicitacao,
			gcom.atendimentopublico.registroatendimento.RaMotivoReativacao raMotivoReativacao,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoReativacao,
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoDuplicidade,
			Imovel imovel,
			BairroArea bairroArea,
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
			Quadra quadra, Localidade localidade,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			DivisaoEsgoto divisaoEsgoto) {
		this.codigoSituacao = codigoSituacao;
		this.registroAtendimento = registroAtendimento;
		this.dataPrevistaOriginal = dataPrevistaOriginal;
		this.dataPrevistaAtual = dataPrevistaAtual;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
		this.logradouroBairro = logradouroBairro;
		this.setorComercial = setorComercial;
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
		this.pavimentoCalcada = pavimentoCalcada;
		this.localOcorrencia = localOcorrencia;
		this.logradouroCep = logradouroCep;
		this.meioSolicitacao = meioSolicitacao;
		this.raMotivoReativacao = raMotivoReativacao;
		this.registroAtendimentoReativacao = registroAtendimentoReativacao;
		this.registroAtendimentoDuplicidade = registroAtendimentoDuplicidade;
		this.imovel = imovel;
		this.bairroArea = bairroArea;
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
		this.quadra = quadra;
		this.localidade = localidade;
		this.pavimentoRua = pavimentoRua;
		this.divisaoEsgoto = divisaoEsgoto;
	}
	

	public Integer getManual() {
		return manual;
	}

	public void setManual(Integer manual) {
		this.manual = manual;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getCodigoSituacao() {
		return this.codigoSituacao;
	}

	public void setCodigoSituacao(short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public Date getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(Date registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getDataPrevistaOriginal() {
		return this.dataPrevistaOriginal;
	}

	public void setDataPrevistaOriginal(Date dataPrevistaOriginal) {
		this.dataPrevistaOriginal = dataPrevistaOriginal;
	}

	public Date getDataPrevistaAtual() {
		return this.dataPrevistaAtual;
	}

	public void setDataPrevistaAtual(Date dataPrevistaAtual) {
		this.dataPrevistaAtual = dataPrevistaAtual;
	}

	public Date getUltimaEmissao() {
		return this.ultimaEmissao;
	}

	public void setUltimaEmissao(Date ultimaEmissao) {
		this.ultimaEmissao = ultimaEmissao;
	}

	public Date getDataEncerramento() {
		return this.dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Short getQuantidadeReiteracao() {
		return this.quantidadeReiteracao;
	}

	public void setQuantidadeReiteracao(Short quantidadeReiteracao) {
		this.quantidadeReiteracao = quantidadeReiteracao;
	}

	public Date getUltimaReiteracao() {
		return this.ultimaReiteracao;
	}

	public void setUltimaReiteracao(Date ultimaReiteracao) {
		this.ultimaReiteracao = ultimaReiteracao;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoLocalOcorrencia() {
		return this.descricaoLocalOcorrencia;
	}

	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getParecerEncerramento() {
		return this.parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
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

	public short getIndicadorAtendimentoOnline() {
		return this.indicadorAtendimentoOnline;
	}

	public void setIndicadorAtendimentoOnline(short indicadorAtendimentoOnline) {
		this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
	}

	public Date getDataInicioEspera() {
		return this.dataInicioEspera;
	}

	public void setDataInicioEspera(Date dataInicioEspera) {
		this.dataInicioEspera = dataInicioEspera;
	}

	public Date getDataFimEspera() {
		return this.dataFimEspera;
	}

	public void setDataFimEspera(Date dataFimEspera) {
		this.dataFimEspera = dataFimEspera;
	}

	public LogradouroBairro getLogradouroBairro() {
		return this.logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return this.atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public gcom.cadastro.imovel.PavimentoCalcada getPavimentoCalcada() {
		return this.pavimentoCalcada;
	}

	public void setPavimentoCalcada(
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public gcom.atendimentopublico.registroatendimento.LocalOcorrencia getLocalOcorrencia() {
		return this.localOcorrencia;
	}

	public void setLocalOcorrencia(
			gcom.atendimentopublico.registroatendimento.LocalOcorrencia localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public LogradouroCep getLogradouroCep() {
		return this.logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public gcom.atendimentopublico.registroatendimento.MeioSolicitacao getMeioSolicitacao() {
		return this.meioSolicitacao;
	}

	public void setMeioSolicitacao(
			gcom.atendimentopublico.registroatendimento.MeioSolicitacao meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public gcom.atendimentopublico.registroatendimento.RaMotivoReativacao getRaMotivoReativacao() {
		return this.raMotivoReativacao;
	}

	public void setRaMotivoReativacao(
			gcom.atendimentopublico.registroatendimento.RaMotivoReativacao raMotivoReativacao) {
		this.raMotivoReativacao = raMotivoReativacao;
	}

	public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimentoReativacao() {
		return this.registroAtendimentoReativacao;
	}

	public void setRegistroAtendimentoReativacao(
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoReativacao) {
		this.registroAtendimentoReativacao = registroAtendimentoReativacao;
	}

	public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimentoDuplicidade() {
		return this.registroAtendimentoDuplicidade;
	}

	public void setRegistroAtendimentoDuplicidade(
			gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimentoDuplicidade) {
		this.registroAtendimentoDuplicidade = registroAtendimentoDuplicidade;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public BairroArea getBairroArea() {
		return this.bairroArea;
	}

	public void setBairroArea(BairroArea bairroArea) {
		this.bairroArea = bairroArea;
	}

	public gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return this.solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.cadastro.imovel.PavimentoRua getPavimentoRua() {
		return this.pavimentoRua;
	}

	public void setPavimentoRua(gcom.cadastro.imovel.PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public DivisaoEsgoto getDivisaoEsgoto() {
		return this.divisaoEsgoto;
	}

	public void setDivisaoEsgoto(DivisaoEsgoto divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("pavimentoCalcada");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("localOcorrencia");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("raMotivoReativacao");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimentoReativacao");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimentoDuplicidade");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("bairroArea");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("divisaoEsgoto");
		filtroRegistroAtendimento
		.adicionarCaminhoParaCarregamentoEntidade("unidadeAtual");
		
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID, this.getId()));
		return filtroRegistroAtendimento;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/**
	 * Gets the endereco attribute of the Imovel object
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

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
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do registro atendimento
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do registro atendimento é
			// diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do registro atendimento
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
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

	/**
	 * Gets the endereco attribute of the Imovel object
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

	public final String getDescricaoSituacao() {
		String descricao = "";
		if (SITUACAO_PENDENTE.equals(getCodigoSituacao())) {
			descricao = SITUACAO_DESCRICAO_PENDENTE;
		} else if (SITUACAO_ENCERRADO.equals(getCodigoSituacao())) {
			descricao = SITUACAO_DESCRICAO_ENCERRADO;
		} else if (SITUACAO_BLOQUEADO.equals(getCodigoSituacao())) {
			descricao = SITUACAO_DESCRICAO_BLOQUEADO;
		}
		return descricao;
	}

	/**
	 * @return Retorna o campo numeroImovel.
	 */
	public String getNumeroImovel() {
		return numeroImovel;
	}

	/**
	 * @param numeroImovel O numeroImovel a ser setado.
	 */
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	@SuppressWarnings("rawtypes")
	public Set getTramites() {
		return tramites;
	}

	@SuppressWarnings("rawtypes")
	public void setTramites(Set tramites) {
		this.tramites = tramites;
	}
	
	@SuppressWarnings("rawtypes")
	public Set getRaDadosAgenciaReguladoras() {
		return raDadosAgenciaReguladoras;
	}

	@SuppressWarnings("rawtypes")
	public void setRaDadosAgenciaReguladoras(Set raDadosAgenciaReguladoras) {
		this.raDadosAgenciaReguladoras = raDadosAgenciaReguladoras;
	}

	@SuppressWarnings("rawtypes")
	public Set getRegistroAtendimentoUnidades() {
		return registroAtendimentoUnidades;
	}

	@SuppressWarnings("rawtypes")
	public void setRegistroAtendimentoUnidades(Set registroAtendimentoUnidades) {
		this.registroAtendimentoUnidades = registroAtendimentoUnidades;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getId();
	}

	public BigDecimal getNnCoordenadaNorte() {
		return nnCoordenadaNorte;
	}

	public void setNnCoordenadaNorte(BigDecimal nnCoordenadaNorte) {
		this.nnCoordenadaNorte = nnCoordenadaNorte;
	}

	public BigDecimal getNnCoordenadaLeste() {
		return nnCoordenadaLeste;
	}

	public void setNnCoordenadaLeste(BigDecimal nnCoordenadaLeste) {
		this.nnCoordenadaLeste = nnCoordenadaLeste;
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

	public ServicoNaoCobrancaMotivo getServicoNaoCobrancaMotivo() {
		return servicoNaoCobrancaMotivo;
	}

	public void setServicoNaoCobrancaMotivo(
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo) {
		this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
	}

	/**
	 * @return Retorna o campo indicCoordenadaSemLogradouro.
	 */
	public Short getIndicadorCoordenadaSemLogradouro() {
		return indicadorCoordenadaSemLogradouro;
	}

	/**
	 * @param indicCoordenadaSemLogradouro O indicCoordenadaSemLogradouro a ser setado.
	 */
	public void setIndicadorCoordenadaSemLogradouro(Short indicCoordenadaSemLogradouro) {
		this.indicadorCoordenadaSemLogradouro = indicCoordenadaSemLogradouro;
	}
	
	
	/**
	 * Gets the endereco attribute of the Imovel object
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatadoIniciadoPeloBairro() {
		String endereco = "";

		
		if (this.getLogradouroBairro() != null
				&& this.getLogradouroBairro().getBairro() != null
				&& this.getLogradouroBairro().getBairro().getId()
						.intValue() != 0) {
			endereco = this.getLogradouroBairro().getBairro().getNome()
							.trim();

		}
		//verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = endereco + " - "+ this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			// concate o numero do imovel
			if ( this.getNumeroImovel() != null ) {
				endereco = endereco + " " + this.getNumeroImovel().trim();
			}
			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
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
	
}
