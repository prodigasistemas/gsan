package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class FaturamentoSituacaoHistorico extends ObjetoTransacao{
	private static final long serialVersionUID = 1L;
	public static final int ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO = 435; //Operacao.OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA
	public static final int ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO = 477; //Operacao.OPERACAO_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO

    private Integer id;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoSituacaoInicio;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoSituacaoFim;

    private Date ultimaAlteracao;

    @ControleAlteracao(value=FiltroFaturamentoSituacaoHistorico.FATURAMENTO_MOTIVO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;

    private Imovel imovel;

    @ControleAlteracao(value=FiltroFaturamentoSituacaoHistorico.FATURAMENTO_TIPO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFaturamentoRetirada;
    
    private Usuario usuario;
    private Integer numeroConsumoAguaMedido;
    private Integer numeroConsumoAguaNaoMedido;
    private Integer numeroVolumeEsgotoMedido;
    private Integer numeroVolumeEsgotoNaoMedido;
    private Date dataInclusao;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacaoInforma;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacaoRetira;
    
    private Usuario usuarioInforma;
    private Usuario usuarioRetira;
    private FaturamentoSituacaoComando faturamentoSituacaoComandoInforma;
    private FaturamentoSituacaoComando faturamentoSituacaoComandoRetirada;

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
    		Date ultimaAlteracao, FaturamentoSituacaoMotivo faturamentoSituacaoMotivo, Imovel imovel, 
    		FaturamentoSituacaoTipo faturamentoSituacaoTipo, Integer anoMesFaturamentoRetirada,
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
    		FaturamentoSituacaoMotivo faturamentoSituacaoMotivo, Imovel imovel, 
    		FaturamentoSituacaoTipo faturamentoSituacaoTipo, Integer anoMesFaturamentoRetirada,
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

	public FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
        return this.faturamentoSituacaoMotivo;
    }

    public void setFaturamentoSituacaoMotivo(FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
        this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
        return this.faturamentoSituacaoTipo;
    }

    public void setFaturamentoSituacaoTipo(FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
        this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
    }

	public Integer getAnoMesFaturamentoRetirada() {
		return anoMesFaturamentoRetirada;
	}

	public void setAnoMesFaturamentoRetirada(Integer anoMesFaturamentoRetirada) {
		this.anoMesFaturamentoRetirada = anoMesFaturamentoRetirada;
	}

	public Integer getAnoMesFaturamentoSituacaoFim() {
		return anoMesFaturamentoSituacaoFim;
	}

	public void setAnoMesFaturamentoSituacaoFim(Integer anoMesFaturamentoSituacaoFim) {
		this.anoMesFaturamentoSituacaoFim = anoMesFaturamentoSituacaoFim;
	}

	public Integer getAnoMesFaturamentoSituacaoInicio() {
		return anoMesFaturamentoSituacaoInicio;
	}

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
	
	public boolean dentroIntervaloFaturamento(Integer anoMesFaturamento){
	    return anoMesFaturamento >= this.getAnoMesFaturamentoSituacaoInicio() 
        && anoMesFaturamento <= this.getAnoMesFaturamentoSituacaoFim();
	}
}
