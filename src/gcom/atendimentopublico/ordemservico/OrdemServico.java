package gcom.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaDocumento;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class OrdemServico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public final static Short SITUACAO_PENDENTE = new Short("1");
	public final static String SITUACAO_DESCRICAO_PENDENTE = "Pendente";
	public final static String SITUACAO_DESC_ABREV_PENDENTE = "Pen";
	public final static String SITUACAO_LETRA_IDENTIFICADORA_PENDENTE = "P";
	
	public final static Short SITUACAO_ENCERRADO = new Short("2");
	public final static String SITUACAO_DESCRICAO_ENCERRADO = "Encerrada";	
	public final static String SITUACAO_DESCRICAO_ENCERRADO_NAO_EXECUTADA = "encerrada porém não foi executada";
	public final static String SITUACAO_DESC_ABREV_ENCERRADO = "Enc";
	public final static String SITUACAO_LETRA_IDENTIFICADORA_ENCERRADO = "E";	
	
	public final static Short SITUACAO_EXECUCAO_EM_ANDAMENTO = new Short("3");
	public final static String SITUACAO_DESCRICAO_EXECUCAO_EM_ANDAMENTO = "Execução em andamento";
	public final static String SITUACAO_DESC_ABREV_EXECUCAO_EM_ANDAMENTO = "Exec em And";
	public final static String SITUACAO_LETRA_IDENTIFICADORA_EXECUCAO_EM_ANDAMENTO = "A";
	
	public final static Short SITUACAO_AGUARDANDO_LIBERACAO = new Short("4");
	public final static String SITUACAO_DESCRICAO_AGUARDANDO_LIBERACAO = "Aguardando liberação para execução";
	public final static String SITUACAO_DESC_ABREV_AGUARDANDO_LIBERACAO = "Aguardo Liber";
	public final static String SITUACAO_LETRA_IDENTIFICADORA_AGUARDANDO_LIBERACAO = "L";
	
	public final static Short PROGRAMADA = new Short("1");
	public final static Short NAO_PROGRAMADA = new Short("2");
	
	public static final int ATRIBUTOS_MANTER_ORDEM_SERVICO = 260; //Operacao.OPERACAO_ORDEM_SERVICO_ATUALIZAR
	public static final int ATRIBUTOS_ENCERRAR_ORDEM_SERVICO = 297; //Operacao.OPERACAO_ORDEM_SERVICO_ENCERRAR
	
	public final static String SOLICITADAS = "1";
	public final static String SELETIVAS_COBRANCA = "2";
	public final static String SELETIVAS_HIDROMETRO = "3";
	public final static String TODAS = "4";

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short situacao;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private Date dataEmissao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_ENCERRAR_ORDEM_SERVICO)
    private Date dataEncerramento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_ENCERRAR_ORDEM_SERVICO)
    private String descricaoParecerEncerramento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_MANTER_ORDEM_SERVICO)
    private String observacao;

    /** nullable persistent field */
    private BigDecimal areaPavimento;

    /** nullable persistent field */
    private BigDecimal valorOriginal;

    /** nullable persistent field */
    private BigDecimal valorAtual;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private short indicadorComercialAtualizado;
    
    /** persistent field */
    private Integer numeroFatorPrioridade;
    

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO)
    private BigDecimal percentualCobranca;

    /** persistent field */
    @ControleAlteracao(value=FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO, funcionalidade=ATRIBUTOS_ENCERRAR_ORDEM_SERVICO)
    private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServico osReferencia;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal;

    /** persistent field */
    @ControleAlteracao(value=FiltroOrdemServico.SERVICO_TIPO_PRIORIDADE_ATUAL,
    		funcionalidade=ATRIBUTOS_MANTER_ORDEM_SERVICO)
    private gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual;

    /** persistent field */
    @ControleAlteracao(value=FiltroOrdemServico.OS_REFERIDA_RETORNO_TIPO,
    		funcionalidade=ATRIBUTOS_ENCERRAR_ORDEM_SERVICO)    
    private gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroOrdemServico.SERVICO_TIPO,
    		funcionalidade=ATRIBUTOS_MANTER_ORDEM_SERVICO)
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia;
    
    private short indicadorServicoDiagnosticado;

    /** persistent field */
    private Imovel imovel; 
    
    /** persistent field */
    private Date dataFiscalizacaoSituacao;

    /** persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    /** persistent field */
    private Short codigoRetornoVistoria;
    
    /** persistent field */
    private Short codigoTipoRecebimento;
    
    /** persistent field */
    private Short codigoRetornoFiscalizacaoColetiva;

    /** persistent field */
    private String parecerFiscalizacaoColetiva;
    
    private short indicadorProgramada;
    
    private Set ordemServicoUnidades;
    
    private Short indicadorEncerramentoAutomatico;
    
    private BoletimOsConcluida boletimOsConcluida;
    
    private UnidadeOrganizacional unidadeAtual;
    
    private Projeto projeto;

    private Short indicadorAtualizaAgua;
    
    private Short indicadorAtualizaEsgoto;
    
    private Short indicadorBoletim;
    
    private ComandoOrdemSeletiva comandoOrdemSeletiva;
    
	public ComandoOrdemSeletiva getComandoOrdemSeletiva() {
		return comandoOrdemSeletiva;
	}

	public void setComandoOrdemSeletiva(ComandoOrdemSeletiva comandoOrdemSeletiva) {
		this.comandoOrdemSeletiva = comandoOrdemSeletiva;
	}

	/**
	 * @return Retorna o campo unidadeAtual.
	 */
	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	/**
	 * @param unidadeAtual O unidadeAtual a ser setado.
	 */
	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	/** full constructor */
    public OrdemServico(short situacao, Date dataGeracao, Date dataEmissao, Date dataEncerramento, 
    		String descricaoParecerEncerramento, String observacao, BigDecimal areaPavimento, 
    		BigDecimal valorOriginal, BigDecimal valorAtual, Date ultimaAlteracao, 
    		short indicadorComercialAtualizado, BigDecimal percentualCobranca, 
    		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, 
    		gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo, 
    		RegistroAtendimento registroAtendimento, CobrancaDocumento cobrancaDocumento, 
    		gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva, 
    		gcom.atendimentopublico.ordemservico.OrdemServico osReferencia, 
    		gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal, 
    		gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual,
    		gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo, 
    		gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo, 
    		gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia, Short codigoRetornoFiscalizacaoColetiva, String parecerFiscalizacaoColetiva) {
    	
        this.situacao = situacao;
        this.dataGeracao = dataGeracao;
        this.dataEmissao = dataEmissao;
        this.dataEncerramento = dataEncerramento;
        this.descricaoParecerEncerramento = descricaoParecerEncerramento;
        this.observacao = observacao;
        this.areaPavimento = areaPavimento;
        this.valorOriginal = valorOriginal;
        this.valorAtual = valorAtual;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorComercialAtualizado = indicadorComercialAtualizado;
        this.percentualCobranca = percentualCobranca;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
        this.registroAtendimento = registroAtendimento;
        this.cobrancaDocumento = cobrancaDocumento;
        this.fiscalizacaoColetiva = fiscalizacaoColetiva;
        this.osReferencia = osReferencia;
        this.servicoTipoPrioridadeOriginal = servicoTipoPrioridadeOriginal;
        this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
        this.osReferidaRetornoTipo = osReferidaRetornoTipo;
        this.servicoTipo = servicoTipo;
        this.servicoTipoReferencia = servicoTipoReferencia;
        this.codigoRetornoFiscalizacaoColetiva = codigoRetornoFiscalizacaoColetiva;
        this.parecerFiscalizacaoColetiva = parecerFiscalizacaoColetiva;
    }

    /** default constructor */
    public OrdemServico() {
    }

    /** minimal constructor */
    public OrdemServico(short situacao, Date dataGeracao, Date ultimaAlteracao, short indicadorComercialAtualizado, 
    		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, 
    		gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo, 
    		RegistroAtendimento registroAtendimento, CobrancaDocumento cobrancaDocumento, 
    		gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva, 
    		gcom.atendimentopublico.ordemservico.OrdemServico osReferencia, 
    		gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal, 
    		gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual, 
    		gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo, 
    		gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo,
    		gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia) {
    	
        this.situacao = situacao;
        this.dataGeracao = dataGeracao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorComercialAtualizado = indicadorComercialAtualizado;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
        this.registroAtendimento = registroAtendimento;
        this.cobrancaDocumento = cobrancaDocumento;
        this.fiscalizacaoColetiva = fiscalizacaoColetiva;
        this.osReferencia = osReferencia;
        this.servicoTipoPrioridadeOriginal = servicoTipoPrioridadeOriginal;
        this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
        this.osReferidaRetornoTipo = osReferidaRetornoTipo;
        this.servicoTipo = servicoTipo;
        this.servicoTipoReferencia = servicoTipoReferencia;
    }
    
    public OrdemServico(Date ultimaAlteracao, RegistroAtendimento registroAtendimento, 
    		gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo, 
    		CobrancaDocumento cobrancaDocumento) {
    	
        this.ultimaAlteracao = ultimaAlteracao;
        this.registroAtendimento = registroAtendimento;
        this.servicoTipo = servicoTipo;
        this.cobrancaDocumento = cobrancaDocumento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getSituacao() {
        return this.situacao;
    }

    public void setSituacao(short situacao) {
        this.situacao = situacao;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Date getDataEmissao() {
        return this.dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEncerramento() {
        return this.dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getDescricaoParecerEncerramento() {
        return this.descricaoParecerEncerramento;
    }

    public void setDescricaoParecerEncerramento(String descricaoParecerEncerramento) {
        this.descricaoParecerEncerramento = descricaoParecerEncerramento;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getAreaPavimento() {
        return this.areaPavimento;
    }

    public void setAreaPavimento(BigDecimal areaPavimento) {
        this.areaPavimento = areaPavimento;
    }

    public BigDecimal getValorOriginal() {
        return this.valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorAtual() {
        return this.valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorComercialAtualizado() {
        return this.indicadorComercialAtualizado;
    }

    public void setIndicadorComercialAtualizado(short indicadorComercialAtualizado) {
        this.indicadorComercialAtualizado = indicadorComercialAtualizado;
    }

    public BigDecimal getPercentualCobranca() {
        return this.percentualCobranca;
    }

    public void setPercentualCobranca(BigDecimal percentualCobranca) {
        this.percentualCobranca = percentualCobranca;
    }

    public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
        return this.atendimentoMotivoEncerramento;
    }

    public void setAtendimentoMotivoEncerramento(AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
    }

    public gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo getServicoNaoCobrancaMotivo() {
        return this.servicoNaoCobrancaMotivo;
    }

    public void setServicoNaoCobrancaMotivo(gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo) {
        this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
    }

    public RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public CobrancaDocumento getCobrancaDocumento() {
        return this.cobrancaDocumento;
    }

    public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
        this.cobrancaDocumento = cobrancaDocumento;
    }

    public gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva getFiscalizacaoColetiva() {
        return this.fiscalizacaoColetiva;
    }

    public void setFiscalizacaoColetiva(gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva fiscalizacaoColetiva) {
        this.fiscalizacaoColetiva = fiscalizacaoColetiva;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServico getOsReferencia() {
        return this.osReferencia;
    }

    public void setOsReferencia(gcom.atendimentopublico.ordemservico.OrdemServico osReferencia) {
        this.osReferencia = osReferencia;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridadeOriginal() {
        return this.servicoTipoPrioridadeOriginal;
    }

    public void setServicoTipoPrioridadeOriginal(gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeOriginal) {
        this.servicoTipoPrioridadeOriginal = servicoTipoPrioridadeOriginal;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridadeAtual() {
        return this.servicoTipoPrioridadeAtual;
    }

    public void setServicoTipoPrioridadeAtual(gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridadeAtual) {
        this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
    }

    public gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo getOsReferidaRetornoTipo() {
        return this.osReferidaRetornoTipo;
    }

    public void setOsReferidaRetornoTipo(gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo osReferidaRetornoTipo) {
        this.osReferidaRetornoTipo = osReferidaRetornoTipo;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipoReferencia() {
        return this.servicoTipoReferencia;
    }

    public void setServicoTipoReferencia(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipoReferencia) {
        this.servicoTipoReferencia = servicoTipoReferencia;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String getDescricaoSituacao() {
    	String descricao = "";
    	short situacao = getSituacao();
    	if (SITUACAO_PENDENTE == situacao) {
    		descricao = SITUACAO_DESCRICAO_PENDENTE;
    	} else if (SITUACAO_ENCERRADO == situacao) {
    		descricao = SITUACAO_DESCRICAO_ENCERRADO;
    	} else if (SITUACAO_EXECUCAO_EM_ANDAMENTO == situacao) {
    		descricao = SITUACAO_DESCRICAO_EXECUCAO_EM_ANDAMENTO;
    	} else if (SITUACAO_AGUARDANDO_LIBERACAO == situacao) {
    		descricao = SITUACAO_DESCRICAO_AGUARDANDO_LIBERACAO;
    	}
    	return descricao;
    }
    
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}    

	public Filtro retornaFiltro() {
		FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();

//		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("dataEncerramento");
		filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");

		filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID, this.getId()));
		return filtroOrdemServico;
	}

	/**
	 * @return Retorna o campo dataFiscalizacaoSituacao.
	 */
	public Date getDataFiscalizacaoSituacao() {
		return dataFiscalizacaoSituacao;
	}

	/**
	 * @param dataFiscalizacaoSituacao O dataFiscalizacaoSituacao a ser setado.
	 */
	public void setDataFiscalizacaoSituacao(Date dataFiscalizacaoSituacao) {
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Short getCodigoRetornoVistoria() {
		return codigoRetornoVistoria;
	}

	public void setCodigoRetornoVistoria(Short codigoRetornoVistoria) {
		this.codigoRetornoVistoria = codigoRetornoVistoria;
	}

	public Short getCodigoTipoRecebimento() {
		return codigoTipoRecebimento;
	}

	public void setCodigoTipoRecebimento(Short codigoTipoRecebimento) {
		this.codigoTipoRecebimento = codigoTipoRecebimento;
	}

	/**
	 * @return Retorna o campo codigoRetornoFiscalizacaoColetiva.
	 */
	public Short getCodigoRetornoFiscalizacaoColetiva() {
		return codigoRetornoFiscalizacaoColetiva;
	}

	/**
	 * @param codigoRetornoFiscalizacaoColetiva O codigoRetornoFiscalizacaoColetiva a ser setado.
	 */
	public void setCodigoRetornoFiscalizacaoColetiva(
			Short codigoRetornoFiscalizacaoColetiva) {
		this.codigoRetornoFiscalizacaoColetiva = codigoRetornoFiscalizacaoColetiva;
	}

	/**
	 * @return Retorna o campo parecerFiscalizacaoColetiva.
	 */
	public String getParecerFiscalizacaoColetiva() {
		return parecerFiscalizacaoColetiva;
	}

	/**
	 * @param parecerFiscalizacaoColetiva O parecerFiscalizacaoColetiva a ser setado.
	 */
	public void setParecerFiscalizacaoColetiva(String parecerFiscalizacaoColetiva) {
		this.parecerFiscalizacaoColetiva = parecerFiscalizacaoColetiva;
	}
    public Set getOrdemServicoUnidades() {
		return ordemServicoUnidades;
	}

	public void setOrdemServicoUnidades(Set ordemServicoUnidades) {
		this.ordemServicoUnidades = ordemServicoUnidades;
	}

	public short getIndicadorProgramada() {
		return indicadorProgramada;
	}

	public void setIndicadorProgramada(short indicadorProgramada) {
		this.indicadorProgramada = indicadorProgramada;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public short getIndicadorServicoDiagnosticado() {
		return indicadorServicoDiagnosticado;
	}

	public void setIndicadorServicoDiagnosticado(short indicadorServicoDiagnosticado) {
		this.indicadorServicoDiagnosticado = indicadorServicoDiagnosticado;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof OrdemServico)) {
			return false;
		}
		OrdemServico castOther = (OrdemServico) other;

		return (this.getId().equals(castOther.getId()));
	}

	public Short getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	public void setIndicadorEncerramentoAutomatico(
			Short indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}

	public BoletimOsConcluida getBoletimOsConcluida() {
		return boletimOsConcluida;
	}

	public void setBoletimOsConcluida(BoletimOsConcluida boletimOsConcluida) {
		this.boletimOsConcluida = boletimOsConcluida;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	} 
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"servicoTipo.descricao"
				};
			return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Tipo de Servico"
				};
			return labels;		
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Short getIndicadorAtualizaAgua() {
		return indicadorAtualizaAgua;
	}

	public void setIndicadorAtualizaAgua(Short indicadorAtualizaAgua) {
		this.indicadorAtualizaAgua = indicadorAtualizaAgua;
	}

	public Short getIndicadorAtualizaEsgoto() {
		return indicadorAtualizaEsgoto;
	}

	public void setIndicadorAtualizaEsgoto(Short indicadorAtualizaEsgoto) {
		this.indicadorAtualizaEsgoto = indicadorAtualizaEsgoto;
	}

	public Short getIndicadorBoletim() {
		return indicadorBoletim;
	}

	public void setIndicadorBoletim(Short indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	public Integer getNumeroFatorPrioridade() {
		return numeroFatorPrioridade;
	}

	public void setNumeroFatorPrioridade(Integer numeroFatorPrioridade) {
		this.numeroFatorPrioridade = numeroFatorPrioridade;
	}
	
}
