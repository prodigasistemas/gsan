package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class SolicitacaoTipoEspecificacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private String descricao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer diasPrazo;

    /** persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorPavimentoCalcada;

    /** persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorPavimentoRua;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer indicadorCobrancaMaterial;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer indicadorMatricula;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer indicadorParecerEncerramento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer indicadorGeracaoDebito;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Integer indicadorGeracaoCredito;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorGeracaoOrdemServico;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Short indicadorCliente;
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Short indicadorLigacaoAgua;
    
    private Short indicadorSolicitante;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Short indicadorVerificarDebito;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.SolicitacaoTipo solicitacaoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroSolicitacaoTipoEspecificacao.UNIDADE_ORGANIZACIONAL,funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private UnidadeOrganizacional unidadeOrganizacional;

    /** persistent field */
    @ControleAlteracao(value=FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO,funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroSolicitacaoTipoEspecificacao.ESPECIFICACAO_IMOVEL_SITUACAO,funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private EspecificacaoImovelSituacao especificacaoImovelSituacao;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorUso;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private BigDecimal valorDebito;
    
    @ControleAlteracao(value=FiltroSolicitacaoTipoEspecificacao.DEBITO_TIPO, funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private DebitoTipo debitoTipo;
    
    private Set especificacaoServicoTipos;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorPermiteAlterarValor;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorCobrarJuros;
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorDocumentoObrigatorio;
        
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorUrgencia;    
    
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private short indicadorEncerramentoAutomatico;
    
    @ControleAlteracao(value=FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA,funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,
    		SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER,
    		SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA;    
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER
    		,SolicitacaoTipo.ATRIBUTO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR})
    private Short indicadorValidarDocResponsavel;
    
    private Short indicadorInformarContaRA;
    
    private Short indicadorInformarPagamentoDuplicidade;
    
    private Integer codigoConstante;
    
    private Short indicadorAlterarVencimentoAlternativo;
    
    private Short indicadorLojaVirtual;

    /**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_MATRICULA_OBRIGATORIO = 1;
	 /**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_MATRICULA_NAO_OBRIGATORIO = 2;
	
    /**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_COM_ENCERRAMENTO_AUTOMATICO = 1;
	 /**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_SEM_ENCERRAMENTO_AUTOMATICO = 2;
	
	 /**
	 * Description of the Field
	 */
	public final static Integer CODIGO_CONSTANTE_ATUALIZACAO_CADASTRAL = 1004;

    /** full constructor */
    public SolicitacaoTipoEspecificacao(String descricao, Integer diasPrazo, short indicadorPavimentoCalcada, short indicadorPavimentoRua, Integer indicadorCobrancaMaterial, Integer indicadorMatricula, Integer indicadorParecerEncerramento, Integer indicadorGeracaoDebito, Integer indicadorGeracaoCredito, Date ultimaAlteracao, short indicadorGeracaoOrdemServico, Short indicadorCliente, gcom.atendimentopublico.registroatendimento.SolicitacaoTipo solicitacaoTipo, UnidadeOrganizacional unidadeOrganizacional, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo, EspecificacaoImovelSituacao especificacaoImovelSituacao) {
        this.descricao = descricao;
        this.diasPrazo = diasPrazo;
        this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
        this.indicadorPavimentoRua = indicadorPavimentoRua;
        this.indicadorCobrancaMaterial = indicadorCobrancaMaterial;
        this.indicadorMatricula = indicadorMatricula;
        this.indicadorParecerEncerramento = indicadorParecerEncerramento;
        this.indicadorGeracaoDebito = indicadorGeracaoDebito;
        this.indicadorGeracaoCredito = indicadorGeracaoCredito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorGeracaoOrdemServico = indicadorGeracaoOrdemServico;
        this.indicadorCliente = indicadorCliente;
        this.solicitacaoTipo = solicitacaoTipo;
        this.unidadeOrganizacional = unidadeOrganizacional;
        this.servicoTipo = servicoTipo;
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
        this.indicadorLojaVirtual = 2;
    }
    
    public EspecificacaoImovelSituacao getEspecificacaoImovelSituacao() {
		return especificacaoImovelSituacao;
	}

	public void setEspecificacaoImovelSituacao(
			EspecificacaoImovelSituacao especificacaoImovelSituacao) {
		this.especificacaoImovelSituacao = especificacaoImovelSituacao;
	}

	/** default constructor */
    public SolicitacaoTipoEspecificacao() {
    	this.indicadorLojaVirtual = 2;
    }

    /** minimal constructor */
    public SolicitacaoTipoEspecificacao(short indicadorPavimentoCalcada, short indicadorPavimentoRua, Date ultimaAlteracao, short indicadorGeracaoOrdemServico, gcom.atendimentopublico.registroatendimento.SolicitacaoTipo solicitacaoTipo, UnidadeOrganizacional unidadeOrganizacional, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
        this.indicadorPavimentoRua = indicadorPavimentoRua;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorGeracaoOrdemServico = indicadorGeracaoOrdemServico;
        this.solicitacaoTipo = solicitacaoTipo;
        this.unidadeOrganizacional = unidadeOrganizacional;
        this.servicoTipo = servicoTipo;
        this.indicadorLojaVirtual = 2;
    }
    
    public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
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

    public Integer getDiasPrazo() {
        return this.diasPrazo;
    }

    public void setDiasPrazo(Integer diasPrazo) {
        this.diasPrazo = diasPrazo;
    }

    public short getIndicadorPavimentoCalcada() {
        return this.indicadorPavimentoCalcada;
    }

    public void setIndicadorPavimentoCalcada(short indicadorPavimentoCalcada) {
        this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
    }

    public short getIndicadorPavimentoRua() {
        return this.indicadorPavimentoRua;
    }

    public void setIndicadorPavimentoRua(short indicadorPavimentoRua) {
        this.indicadorPavimentoRua = indicadorPavimentoRua;
    }

    public Integer getIndicadorCobrancaMaterial() {
        return this.indicadorCobrancaMaterial;
    }

    public void setIndicadorCobrancaMaterial(Integer indicadorCobrancaMaterial) {
        this.indicadorCobrancaMaterial = indicadorCobrancaMaterial;
    }

    public Integer getIndicadorMatricula() {
        return this.indicadorMatricula;
    }

    public void setIndicadorMatricula(Integer indicadorMatricula) {
        this.indicadorMatricula = indicadorMatricula;
    }

    public Integer getIndicadorParecerEncerramento() {
        return this.indicadorParecerEncerramento;
    }

    public void setIndicadorParecerEncerramento(Integer indicadorParecerEncerramento) {
        this.indicadorParecerEncerramento = indicadorParecerEncerramento;
    }

    public Integer getIndicadorGeracaoDebito() {
        return this.indicadorGeracaoDebito;
    }

    public void setIndicadorGeracaoDebito(Integer indicadorGeracaoDebito) {
        this.indicadorGeracaoDebito = indicadorGeracaoDebito;
    }

    public Integer getIndicadorGeracaoCredito() {
        return this.indicadorGeracaoCredito;
    }

    public void setIndicadorGeracaoCredito(Integer indicadorGeracaoCredito) {
        this.indicadorGeracaoCredito = indicadorGeracaoCredito;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public short getIndicadorGeracaoOrdemServico() {
        return this.indicadorGeracaoOrdemServico;
    }

    public void setIndicadorGeracaoOrdemServico(short indicadorGeracaoOrdemServico) {
        this.indicadorGeracaoOrdemServico = indicadorGeracaoOrdemServico;
    }

    public Short getIndicadorCliente() {
        return this.indicadorCliente;
    }

    public void setIndicadorCliente(Short indicadorCliente) {
        this.indicadorCliente = indicadorCliente;
    }

    public gcom.atendimentopublico.registroatendimento.SolicitacaoTipo getSolicitacaoTipo() {
        return this.solicitacaoTipo;
    }

    public void setSolicitacaoTipo(gcom.atendimentopublico.registroatendimento.SolicitacaoTipo solicitacaoTipo) {
        this.solicitacaoTipo = solicitacaoTipo;
    }

    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return this.unidadeOrganizacional;
    }

    public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }
    
    public Set getEspecificacaoServicoTipos() {
		return especificacaoServicoTipos;
	}

	public void setEspecificacaoServicoTipos(Set especificacaoServicoTipos) {
		this.especificacaoServicoTipos = especificacaoServicoTipos;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorLigacaoAgua() {
		return indicadorLigacaoAgua;
	}

	public void setIndicadorLigacaoAgua(Short indicadorLigacaoAgua) {
		this.indicadorLigacaoAgua = indicadorLigacaoAgua;
	}

	
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof SolicitacaoTipoEspecificacao)) {
			return false;
		}
		SolicitacaoTipoEspecificacao castOther = (SolicitacaoTipoEspecificacao) other;

		return (this.getDescricao().equalsIgnoreCase(castOther.getDescricao()));
	}

	public Short getIndicadorSolicitante() {
		return indicadorSolicitante;
	}

	public void setIndicadorSolicitante(Short indicadorSolicitante) {
		this.indicadorSolicitante = indicadorSolicitante;
	}

	public Short getIndicadorVerificarDebito() {
		return indicadorVerificarDebito;
	}

	public void setIndicadorVerificarDebito(Short indicadorVerificarDebito) {
		this.indicadorVerificarDebito = indicadorVerificarDebito;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public short getIndicadorCobrarJuros() {
		return indicadorCobrarJuros;
	}

	public void setIndicadorCobrarJuros(short indicadorCobrarJuros) {
		this.indicadorCobrarJuros = indicadorCobrarJuros;
	}

	public short getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(short indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public short getIndicadorDocumentoObrigatorio() {
		return indicadorDocumentoObrigatorio;
	}

	public void setIndicadorDocumentoObrigatorio(short indicadorDocumentoObrigatorio) {
		this.indicadorDocumentoObrigatorio = indicadorDocumentoObrigatorio;
	}

	public short getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(short indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

	/**
	 * @return Retorna o campo indicadorEncerramentoAutomatico.
	 */
	public short getIndicadorEncerramentoAutomatico() {
		return indicadorEncerramentoAutomatico;
	}

	/**
	 * @param indicadorEncerramentoAutomatico O indicadorEncerramentoAutomatico a ser setado.
	 */
	public void setIndicadorEncerramentoAutomatico(
			short indicadorEncerramentoAutomatico) {
		this.indicadorEncerramentoAutomatico = indicadorEncerramentoAutomatico;
	}
	
	public Filtro retornaFiltro() {
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
				this.getId()));
		filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
		filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.UNIDADE_ORGANIZACIONAL);
		filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.ESPECIFICACAO_IMOVEL_SITUACAO);
		filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.DEBITO_TIPO);
		return filtroSolicitacaoTipoEspecificacao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"solicitacaoTipo.descricao","descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao do Tipo de Solicitacao","Descricao da Especificacao"};
		return labels;		
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacaoNovoRA() {
		return solicitacaoTipoEspecificacaoNovoRA;
	}

	public void setSolicitacaoTipoEspecificacaoNovoRA(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA) {
		this.solicitacaoTipoEspecificacaoNovoRA = solicitacaoTipoEspecificacaoNovoRA;
	}

	public Short getIndicadorValidarDocResponsavel() {
		return indicadorValidarDocResponsavel;
	}

	public void setIndicadorValidarDocResponsavel(
			Short indicadorValidarDocResponsavel) {
		this.indicadorValidarDocResponsavel = indicadorValidarDocResponsavel;
	}

	public Short getIndicadorInformarContaRA() {
		return indicadorInformarContaRA;
	}

	public void setIndicadorInformarContaRA(Short indicadorInformarContaRA) {
		this.indicadorInformarContaRA = indicadorInformarContaRA;
	}

	public Integer getCodigoConstante() {
		return codigoConstante;
	}

	public void setCodigoConstante(Integer codigoConstante) {
		this.codigoConstante = codigoConstante;
	}

	public Short getIndicadorInformarPagamentoDuplicidade() {
		return indicadorInformarPagamentoDuplicidade;
	}

	public void setIndicadorInformarPagamentoDuplicidade(
			Short indicadorInformarPagamentoDuplicidade) {
		this.indicadorInformarPagamentoDuplicidade = indicadorInformarPagamentoDuplicidade;
	}

	public Short getIndicadorAlterarVencimentoAlternativo() {
		return indicadorAlterarVencimentoAlternativo;
	}

	public void setIndicadorAlterarVencimentoAlternativo(
			Short indicadorAlterarVencimentoAlternativo) {
		this.indicadorAlterarVencimentoAlternativo = indicadorAlterarVencimentoAlternativo;
	}

	public Short getIndicadorLojaVirtual() {
		return indicadorLojaVirtual;
	}

	public void setIndicadorLojaVirtual(Short indicadorLojaVirtual) {
		this.indicadorLojaVirtual = indicadorLojaVirtual;
	}
}
