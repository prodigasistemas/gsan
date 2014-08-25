package gcom.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaGrupo;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class Rota extends ObjetoTransacao {
	
	public static final int TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA = 1559;
	
	private static final long serialVersionUID = 1L;

	private Integer id;
    private Short codigo;
    private Short indicadorAjusteConsumo;
    private Short indicadorRotaAlternativa = new Short("2");
    private Date dataAjusteLeitura;
    private Short indicadorFiscalizarCortado;
    private Short indicadorFiscalizarSuprimido;
    private Short indicadorGerarFalsaFaixa;
    private BigDecimal percentualGeracaoFaixaFalsa;
    private Short indicadorGerarFiscalizacao;
    private BigDecimal percentualGeracaoFiscalizacao;
    private Short indicadorUso;
    private Short indicadorTransmissaoOffline;

    @ControleAlteracao(funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA})
    private Date ultimaAlteracao;

    @ControleAlteracao(value=FiltroRota.EMPRESA,funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA})
    private Empresa empresa;

    @ControleAlteracao(value=FiltroRota.FATURAMENTO_GRUPO, funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA})
    private FaturamentoGrupo faturamentoGrupo;

    private LeituraTipo leituraTipo;
    private SetorComercial setorComercial;

    @ControleAlteracao(value=FiltroRota.COBRANCA_GRUPO, funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA})
    private CobrancaGrupo cobrancaGrupo;
    
    @ControleAlteracao(value=FiltroRota.EMPRESA, funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA})
    private Empresa empresaCobranca;
    
    private Leiturista leiturista;
    private Integer numeroSequenciaLeitura;
    private Empresa empresaEntregaContas;
    private Integer numeroLimiteImoveis;
    private Integer indicadorSequencialLeitura;
    private Integer numeroDiasConsumoAjuste;
    private Short indicadorImpressaoTermicaFinalGrupo;
    
     public final static int INDICADOR_AJUSTE_MENSAL = 1;
     public final static int INDICADOR_SUPRIMIDO_ATIVO = 1;
     public final static int INDICADOR_CORTADO_ATIVO = 1;
     public final static int LEITURA_TIPO_CONVENCIONAL = 1;
     public final static int LEITURA_TIPO_RELACAO = 2;
     
     public final static Short  INDICADOR_GERAR_FISCALIZACAO = new Short("1");
     public final static Short INDICADOR_NAO_GERAR_FAIXA_FALSA = new Short("2");


    public Rota(Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }
    
    public Rota(Short codigo, Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.codigo = codigo;
    	this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }
    
    public Rota(Short codigo, Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo, Short indicadorTransmissaoOffline) {
        this.codigo = codigo;
    	this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
        this.indicadorTransmissaoOffline = indicadorTransmissaoOffline; 
    }

    public Rota() {
    }
    
    public Rota(Integer id) {
    	this.id = id;
    }

    public Rota(Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    
    public Short getIndicadorRotaAlternativa() {
		return indicadorRotaAlternativa;
	}

	public void setIndicadorRotaAlternativa(Short indicadorRotaAlternativa) {
		this.indicadorRotaAlternativa = indicadorRotaAlternativa;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getIndicadorAjusteConsumo() {
        return this.indicadorAjusteConsumo;
    }

    public void setIndicadorAjusteConsumo(Short indicadorAjusteConsumo) {
        this.indicadorAjusteConsumo = indicadorAjusteConsumo;
    }

    public Date getDataAjusteLeitura() {
        return this.dataAjusteLeitura;
    }

    public void setDataAjusteLeitura(Date dataAjusteLeitura) {
        this.dataAjusteLeitura = dataAjusteLeitura;
    }

    public Short getIndicadorFiscalizarCortado() {
        return this.indicadorFiscalizarCortado;
    }

    public void setIndicadorFiscalizarCortado(Short indicadorFiscalizarCortado) {
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
    }

    public Short getIndicadorFiscalizarSuprimido() {
        return this.indicadorFiscalizarSuprimido;
    }

    public void setIndicadorFiscalizarSuprimido(Short indicadorFiscalizarSuprimido) {
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
    }

    public Short getIndicadorGerarFalsaFaixa() {
        return this.indicadorGerarFalsaFaixa;
    }

    public void setIndicadorGerarFalsaFaixa(Short indicadorGerarFalsaFaixa) {
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
    }

    public BigDecimal getPercentualGeracaoFaixaFalsa() {
        return this.percentualGeracaoFaixaFalsa;
    }

    public void setPercentualGeracaoFaixaFalsa(BigDecimal percentualGeracaoFaixaFalsa) {
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
    }

    public Short getIndicadorGerarFiscalizacao() {
        return this.indicadorGerarFiscalizacao;
    }

    public void setIndicadorGerarFiscalizacao(Short indicadorGerarFiscalizacao) {
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
    }

    public BigDecimal getPercentualGeracaoFiscalizacao() {
        return this.percentualGeracaoFiscalizacao;
    }

    public void setPercentualGeracaoFiscalizacao(BigDecimal percentualGeracaoFiscalizacao) {
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
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

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public LeituraTipo getLeituraTipo() {
        return this.leituraTipo;
    }

    public void setLeituraTipo(LeituraTipo leituraTipo) {
        this.leituraTipo = leituraTipo;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public Empresa getEmpresaCobranca() {
		return empresaCobranca;
	}

	public void setEmpresaCobranca(Empresa empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Integer getNumeroSequenciaLeitura() {
		return numeroSequenciaLeitura;
	}

	public void setNumeroSequenciaLeitura(Integer leituraSequencia) {
		this.numeroSequenciaLeitura = leituraSequencia;
	}

	public Empresa getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}

	public void setEmpresaEntregaContas(Empresa empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
	}
		
	public Short getIndicadorTransmissaoOffline() {
		return indicadorTransmissaoOffline;
	}

	public void setIndicadorTransmissaoOffline(Short indicadorTransmissaoOffline) {
		this.indicadorTransmissaoOffline = indicadorTransmissaoOffline;
	}
	
	public Short getIndicadorImpressaoTermicaFinalGrupo() {
		return indicadorImpressaoTermicaFinalGrupo;
	}

	public void setIndicadorImpressaoTermicaFinalGrupo(
			Short indicadorImpressaoTermicaFinalGrupo) {
		this.indicadorImpressaoTermicaFinalGrupo = indicadorImpressaoTermicaFinalGrupo;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroRota filtroRota = new FiltroRota();
		
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA_COBRANCA);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.COBRANCA_GRUPO);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtroRota. adicionarParametro(
				new ParametroSimples(FiltroRota.ID_ROTA, this.getId()));
		return filtroRota; 
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA_COBRANCA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.COBRANCA_GRUPO);
		
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"setorComercial.localidade.descricao","setorComercial.descricao", "codigo"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Localidade","Setor Comercial", "Código da Rota"};
		return labels;		
	}


	public Integer getNumeroLimiteImoveis() {
		return numeroLimiteImoveis;
	}

	public void setNumeroLimiteImoveis(Integer numeroLimiteImoveis) {
		this.numeroLimiteImoveis = numeroLimiteImoveis;
	}

    public Integer getIndicadorSequencialLeitura() {
        return indicadorSequencialLeitura;
    }

    public void setIndicadorSequencialLeitura(Integer indicadorSequencialLeitura) {
        this.indicadorSequencialLeitura = indicadorSequencialLeitura;
    }

    public Integer getNumeroDiasConsumoAjuste() {
        return numeroDiasConsumoAjuste;
    }

    public void setNumeroDiasConsumoAjuste(Integer numeroDiasConsumoAjuste) {
        this.numeroDiasConsumoAjuste = numeroDiasConsumoAjuste;
    }
    
    public boolean isRotaImpressaoSimultanea() {
    	return leituraTipo.getId() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA;
    }
}
