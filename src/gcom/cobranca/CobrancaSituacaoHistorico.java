package gcom.cobranca;

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
public class CobrancaSituacaoHistorico extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;
	public static final int ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA = 468; //Operacao.OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA
	public static final int ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_COBRANCA = 470; //Operacao.OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA
	/** identifier field */
	private Integer id;

    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA})
    private Integer anoMesCobrancaSituacaoInicio;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA})
    private Integer anoMesCobrancaSituacaoFim;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaSituacaoHistorico.COBRANCA_TIPO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA})
    private gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaSituacaoHistorico.COBRANCA_MOTIVO,funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA})
    private gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_COBRANCA})
    private Integer anoMesCobrancaRetirada;

    /** nullable persistent field */
    private Usuario usuario;
    
    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INFORMAR_SITUACAO_ESPECIAL_COBRANCA})
    private String observacaoInforma;
    
    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_RETIRAR_SITUACAO_ESPECIAL_COBRANCA})
    private String observacaoRetira;
    
    /** nullable persistent field */
    private Usuario usuarioInforma;
    
    /** nullable persistent field */
    private Usuario usuarioRetira;
    
    /** nullable persistent field */
    private Date dataFimSituacao;
    
    /** nullable persistent field */
    private CobrancaSituacaoComando cobrancaSituacaoComandoInforma;
    
    /** nullable persistent field */
    private CobrancaSituacaoComando cobrancaSituacaoComandoRetirada;
    
    public Date getDataFimSituacao() {
		return dataFimSituacao;
	}

	public void setDataFimSituacao(Date dataFimSituacao) {
		this.dataFimSituacao = dataFimSituacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo anoMesCobrancaRetirada.
	 */
	public Integer getAnoMesCobrancaRetirada() {
		return anoMesCobrancaRetirada;
	}

	/** full constructor */
    public CobrancaSituacaoHistorico(Integer anoMesCobrancaSituacaoInicio, Integer anoMesCobrancaSituacaoFim, 
    		Date ultimaAlteracao, Imovel imovel, gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo, 
    		gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo, Integer anoMesCobrancaRetirada,
    		Usuario usuario) {
        this.anoMesCobrancaSituacaoInicio = anoMesCobrancaSituacaoInicio;
        this.anoMesCobrancaSituacaoFim = anoMesCobrancaSituacaoFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
        this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
        this.anoMesCobrancaRetirada = anoMesCobrancaRetirada;
        this.usuario = usuario;
    }

    /** default constructor */
    public CobrancaSituacaoHistorico() {
    }

    /** minimal constructor */
    public CobrancaSituacaoHistorico(Integer anoMesCobrancaSituacaoInicio, Imovel imovel, 
    		gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo, 
    		gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo,Usuario usuario) {
        this.anoMesCobrancaSituacaoInicio = anoMesCobrancaSituacaoInicio;
        this.imovel = imovel;
        this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
        this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
        this.usuario = usuario;
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

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.cobranca.CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
        return this.cobrancaSituacaoTipo;
    }

    public void setCobrancaSituacaoTipo(gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo) {
        this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
    }

    public gcom.cobranca.CobrancaSituacaoMotivo getCobrancaSituacaoMotivo() {
        return this.cobrancaSituacaoMotivo;
    }

    public void setCobrancaSituacaoMotivo(gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
        this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
    }

	/**
	 * @param anoMesCobrancaRetirada O anoMesCobrancaRetirada a ser setado.
	 */
	public void setAnoMesCobrancaRetirada(Integer anoMesCobrancaRetirada) {
		this.anoMesCobrancaRetirada = anoMesCobrancaRetirada;
	}

	/**
	 * @return Retorna o campo anoMesCobrancaSituacaoFim.
	 */
	public Integer getAnoMesCobrancaSituacaoFim() {
		return anoMesCobrancaSituacaoFim;
	}

	/**
	 * @param anoMesCobrancaSituacaoFim O anoMesCobrancaSituacaoFim a ser setado.
	 */
	public void setAnoMesCobrancaSituacaoFim(Integer anoMesCobrancaSituacaoFim) {
		this.anoMesCobrancaSituacaoFim = anoMesCobrancaSituacaoFim;
	}

	/**
	 * @return Retorna o campo anoMesCobrancaSituacaoInicio.
	 */
	public Integer getAnoMesCobrancaSituacaoInicio() {
		return anoMesCobrancaSituacaoInicio;
	}

	/**
	 * @param anoMesCobrancaSituacaoInicio O anoMesCobrancaSituacaoInicio a ser setado.
	 */
	public void setAnoMesCobrancaSituacaoInicio(Integer anoMesCobrancaSituacaoInicio) {
		this.anoMesCobrancaSituacaoInicio = anoMesCobrancaSituacaoInicio;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
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

	@Override
	public Filtro retornaFiltro() {
		FiltroCobrancaSituacaoHistorico filtro = new FiltroCobrancaSituacaoHistorico();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacaoHistorico.ID,this.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoMotivo");
		
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
		String []labels = {"cobrancaSituacaoTipo.descricao"};
		return labels;	
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"TIPO DA SITUACAO COBRANCA"};
		return labels;		
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}	


	public CobrancaSituacaoComando getCobrancaSituacaoComandoInforma() {
		return cobrancaSituacaoComandoInforma;
	}

	public void setCobrancaSituacaoComandoInforma(
			CobrancaSituacaoComando cobrancaSituacaoComandoInforma) {
		this.cobrancaSituacaoComandoInforma = cobrancaSituacaoComandoInforma;
	}

	public CobrancaSituacaoComando getCobrancaSituacaoComandoRetirada() {
		return cobrancaSituacaoComandoRetirada;
	}

	public void setCobrancaSituacaoComandoRetirada(
			CobrancaSituacaoComando cobrancaSituacaoComandoRetirada) {
		this.cobrancaSituacaoComandoRetirada = cobrancaSituacaoComandoRetirada;
	}
	
	

}
