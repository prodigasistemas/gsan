package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class FaturamentoSituacaoHistorico extends ObjetoTransacao{
	private static final long serialVersionUID = 1L;
	public static final int ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO = 435; //Operacao.OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA
	public static final int ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO = 477; //Operacao.OPERACAO_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO
	/** identifier field */

    private Integer id;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoSituacaoInicio;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoSituacaoFim;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroFaturamentoSituacaoHistorico.FATURAMENTO_MOTIVO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    @ControleAlteracao(value=FiltroFaturamentoSituacaoHistorico.FATURAMENTO_TIPO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoRetirada;
    
    /** nullable persistent field */
    private Usuario usuario;
    
    /** persistent field */
    private Integer numeroConsumoAguaMedido;
    
    /** persistent field */
    private Integer numeroConsumoAguaNaoMedido;
    
    /** persistent field */
    private Integer numeroVolumeEsgotoMedido;
    
    /** persistent field */
    private Integer numeroVolumeEsgotoNaoMedido;
    
    /** persistent field */
    private Date dataInclusao;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacaoInforma;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacaoRetira;
    
    /** nullable persistent field */
    private Usuario usuarioInforma;
    
    /** nullable persistent field */
    private Usuario usuarioRetira;
    
    /** nullable persistent field */
    private gcom.faturamento.FaturamentoSituacaoComando faturamentoSituacaoComandoInforma;
    
    /** nullable persistent field */
    private gcom.faturamento.FaturamentoSituacaoComando faturamentoSituacaoComandoRetirada;
    
    

    public FaturamentoSituacaoComando getFaturamentoSituacaoComandoInforma() {
		return faturamentoSituacaoComandoInforma;
	}

	public void setFaturamentoSituacaoComandoInforma(
			FaturamentoSituacaoComando faturamentoSituacaoComandoInforma) {
		this.faturamentoSituacaoComandoInforma = faturamentoSituacaoComandoInforma;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/** full constructor */
    public FaturamentoSituacaoHistorico(Integer anoMesFaturamentoSituacaoInicio, Integer anoMesFaturamentoSituacaoFim, 
    		Date ultimaAlteracao, gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo, Imovel imovel, 
    		gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo, Integer anoMesFaturamentoRetirada,
    		Usuario usuario) {
        this.anoMesFaturamentoSituacaoInicio = anoMesFaturamentoSituacaoInicio;
        this.anoMesFaturamentoSituacaoFim = anoMesFaturamentoSituacaoFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
        this.imovel = imovel;
        this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
        this.anoMesFaturamentoRetirada = anoMesFaturamentoRetirada;
        this.usuario = usuario;
    }

    /** default constructor */
    public FaturamentoSituacaoHistorico() {
    }

    /** minimal constructor */
    public FaturamentoSituacaoHistorico(Integer anoMesFaturamentoSituacaoInicio, Integer anoMesFaturamentoSituacaoFim, 
    		gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo, Imovel imovel, 
    		gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo, Integer anoMesFaturamentoRetirada,
    		Usuario usuario) {
        this.anoMesFaturamentoSituacaoInicio = anoMesFaturamentoSituacaoInicio;
        this.anoMesFaturamentoSituacaoFim = anoMesFaturamentoSituacaoFim;
        this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
        this.imovel = imovel;
        this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
        this.anoMesFaturamentoRetirada = anoMesFaturamentoRetirada;
        this.anoMesFaturamentoRetirada = anoMesFaturamentoRetirada;
        this.usuario = usuario;
    }
    
    /** minimal constructor */
    public FaturamentoSituacaoHistorico(Integer id, Integer numeroConsumoAguaMedido, 
    		Integer numeroConsumoAguaNaoMedido, Integer numeroVolumeEsgotoMedido, 
    		Integer numeroVolumeEsgotoNaoMedido) {
        
    	this.id = id;
    	this.numeroConsumoAguaMedido = numeroConsumoAguaMedido;
        this.numeroConsumoAguaNaoMedido = numeroConsumoAguaNaoMedido;
        this.numeroVolumeEsgotoMedido = numeroVolumeEsgotoMedido;
        this.numeroVolumeEsgotoNaoMedido = numeroVolumeEsgotoNaoMedido;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
    public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public gcom.faturamento.FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
        return this.faturamentoSituacaoMotivo;
    }

    public void setFaturamentoSituacaoMotivo(gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
        this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.faturamento.FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
        return this.faturamentoSituacaoTipo;
    }

    public void setFaturamentoSituacaoTipo(gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
        this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
    }

	/**
	 * @return Retorna o campo anoMesFaturamentoRetirada.
	 */
	public Integer getAnoMesFaturamentoRetirada() {
		return anoMesFaturamentoRetirada;
	}

	/**
	 * @param anoMesFaturamentoRetirada O anoMesFaturamentoRetirada a ser setado.
	 */
	public void setAnoMesFaturamentoRetirada(Integer anoMesFaturamentoRetirada) {
		this.anoMesFaturamentoRetirada = anoMesFaturamentoRetirada;
	}

	/**
	 * @return Retorna o campo anoMesFaturamentoSituacaoFim.
	 */
	public Integer getAnoMesFaturamentoSituacaoFim() {
		return anoMesFaturamentoSituacaoFim;
	}

	/**
	 * @param anoMesFaturamentoSituacaoFim O anoMesFaturamentoSituacaoFim a ser setado.
	 */
	public void setAnoMesFaturamentoSituacaoFim(Integer anoMesFaturamentoSituacaoFim) {
		this.anoMesFaturamentoSituacaoFim = anoMesFaturamentoSituacaoFim;
	}

	/**
	 * @return Retorna o campo anoMesFaturamentoSituacaoInicio.
	 */
	public Integer getAnoMesFaturamentoSituacaoInicio() {
		return anoMesFaturamentoSituacaoInicio;
	}

	/**
	 * @param anoMesFaturamentoSituacaoInicio O anoMesFaturamentoSituacaoInicio a ser setado.
	 */
	public void setAnoMesFaturamentoSituacaoInicio(
			Integer anoMesFaturamentoSituacaoInicio) {
		this.anoMesFaturamentoSituacaoInicio = anoMesFaturamentoSituacaoInicio;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getNumeroConsumoAguaMedido() {
		return numeroConsumoAguaMedido;
	}

	public void setNumeroConsumoAguaMedido(Integer numeroConsumoAguaMedido) {
		this.numeroConsumoAguaMedido = numeroConsumoAguaMedido;
	}

	public Integer getNumeroConsumoAguaNaoMedido() {
		return numeroConsumoAguaNaoMedido;
	}

	public void setNumeroConsumoAguaNaoMedido(Integer numeroConsumoAguaNaoMedido) {
		this.numeroConsumoAguaNaoMedido = numeroConsumoAguaNaoMedido;
	}

	public Integer getNumeroVolumeEsgotoMedido() {
		return numeroVolumeEsgotoMedido;
	}

	public void setNumeroVolumeEsgotoMedido(Integer numeroVolumeEsgotoMedido) {
		this.numeroVolumeEsgotoMedido = numeroVolumeEsgotoMedido;
	}

	public Integer getNumeroVolumeEsgotoNaoMedido() {
		return numeroVolumeEsgotoNaoMedido;
	}

	public void setNumeroVolumeEsgotoNaoMedido(Integer numeroVolumeEsgotoNaoMedido) {
		this.numeroVolumeEsgotoNaoMedido = numeroVolumeEsgotoNaoMedido;
	}

	public String getObservacaoInforma() {
		return observacaoInforma;
	}

	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}

	public String getObservacaoRetira() {
		return observacaoRetira;
	}

	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}

	public Usuario getUsuarioInforma() {
		return usuarioInforma;
	}

	public void setUsuarioInforma(Usuario usuarioInforma) {
		this.usuarioInforma = usuarioInforma;
	}

	public Usuario getUsuarioRetira() {
		return usuarioRetira;
	}

	public void setUsuarioRetira(Usuario usuarioRetira) {
		this.usuarioRetira = usuarioRetira;
	}

	public FaturamentoSituacaoComando getFaturamentoSituacaoComandoRetirada() {
		return faturamentoSituacaoComandoRetirada;
	}

	public void setFaturamentoSituacaoComandoRetirada(
			FaturamentoSituacaoComando faturamentoSituacaoComandoRetirada) {
		this.faturamentoSituacaoComandoRetirada = faturamentoSituacaoComandoRetirada;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroFaturamentoSituacaoHistorico filtro = new FiltroFaturamentoSituacaoHistorico();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID,this.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getId() + "";
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"faturamentoSituacaoTipo.descricao"};
		return labels;	
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"TIPO DA SITUACAO DE FATURAMENTO"};
		return labels;		
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}	
}
