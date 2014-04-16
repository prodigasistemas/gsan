package gcom.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnidadeOrganizacional extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorEsgoto;

    /** persistent field */
    private short indicadorTramite;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private String sigla;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorAberturaRa;

    /** persistent field */
    private short indicadorUso;
    
    /** nullable persistent field */
    private Short indicadorCentralAtendimento;

    /** persistent field */
    private short indicadorTarifaSocial;  
    
    /** persistent field */
    private gcom.cadastro.unidade.UnidadeTipo unidadeTipo;

    /** persistent field */
    private MeioSolicitacao meioSolicitacao;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private Localidade localidade;
    
    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora;
    
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora;    
 
    /** persistent field */
    private gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior;
    
    private Integer codigoConstante;

    /** full constructor */
    public UnidadeOrganizacional(short indicadorEsgoto, short indicadorTramite, String descricao, 
    		String sigla, Date ultimaAlteracao, Short indicadorAberturaRa, 
    		short indicadorUso, gcom.cadastro.unidade.UnidadeTipo unidadeTipo, 
    		MeioSolicitacao meioSolicitacao, Empresa empresa, Localidade localidade, 
    		GerenciaRegional gerenciaRegional, gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora, 
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora,
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior,
    		Short indicadorCentralAtendimento,short indicadorTarifaSocial) {
        
    	this.indicadorEsgoto = indicadorEsgoto;
        this.indicadorTramite = indicadorTramite;
        this.descricao = descricao;
        this.sigla = sigla;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorAberturaRa = indicadorAberturaRa;
        this.indicadorUso = indicadorUso;
        this.unidadeTipo = unidadeTipo;
        this.meioSolicitacao = meioSolicitacao;
        this.empresa = empresa;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.unidadeCentralizadora = unidadeCentralizadora;
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.unidadeSuperior = unidadeSuperior;
        this.indicadorCentralAtendimento = indicadorCentralAtendimento;
        this.indicadorTarifaSocial = indicadorTarifaSocial;

    }

    /** default constructor */
    public UnidadeOrganizacional() {
    }

    /** minimal constructor */
    public UnidadeOrganizacional(short indicadorEsgoto, short indicadorTramite, String descricao, 
    		Date ultimaAlteracao, short indicadorUso, gcom.cadastro.unidade.UnidadeTipo unidadeTipo, 
    		MeioSolicitacao meioSolicitacao, Empresa empresa, Localidade localidade, 
    		GerenciaRegional gerenciaRegional, gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora, 
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora,
    		gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior,Short indicadorCentralAtendimento, 
    		short indicadorTarifaSocial) {
        
    	this.indicadorEsgoto = indicadorEsgoto;
        this.indicadorTramite = indicadorTramite;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
        this.unidadeTipo = unidadeTipo;
        this.meioSolicitacao = meioSolicitacao;
        this.empresa = empresa;
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.unidadeCentralizadora = unidadeCentralizadora;
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.unidadeSuperior = unidadeSuperior;
        this.indicadorCentralAtendimento = indicadorCentralAtendimento;
        this.indicadorTarifaSocial = indicadorTarifaSocial;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorEsgoto() {
        return this.indicadorEsgoto;
    }

    public void setIndicadorEsgoto(short indicadorEsgoto) {
        this.indicadorEsgoto = indicadorEsgoto;
    }

    public short getIndicadorTramite() {
        return this.indicadorTramite;
    }

    public void setIndicadorTramite(short indicadorTramite) {
        this.indicadorTramite = indicadorTramite;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorAberturaRa() {
        return this.indicadorAberturaRa;
    }

    public void setIndicadorAberturaRa(Short indicadorAberturaRa) {
        this.indicadorAberturaRa = indicadorAberturaRa;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public gcom.cadastro.unidade.UnidadeTipo getUnidadeTipo() {
        return this.unidadeTipo;
    }

    public void setUnidadeTipo(gcom.cadastro.unidade.UnidadeTipo unidadeTipo) {
        this.unidadeTipo = unidadeTipo;
    }

    public MeioSolicitacao getMeioSolicitacao() {
        return this.meioSolicitacao;
    }

    public void setMeioSolicitacao(MeioSolicitacao meioSolicitacao) {
        this.meioSolicitacao = meioSolicitacao;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeCentralizadora() {
        return this.unidadeCentralizadora;
    }

    public void setUnidadeCentralizadora(gcom.cadastro.unidade.UnidadeOrganizacional unidadeCentralizadora) {
        this.unidadeCentralizadora = unidadeCentralizadora;
    }

    public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeSuperior() {
        return this.unidadeSuperior;
    }

    public void setUnidadeSuperior(gcom.cadastro.unidade.UnidadeOrganizacional unidadeSuperior) {
        this.unidadeSuperior = unidadeSuperior;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorCentralAtendimento() {
		return indicadorCentralAtendimento;
	}

	public void setIndicadorCentralAtendimento(Short indicadorCentralAtendimento) {
		this.indicadorCentralAtendimento = indicadorCentralAtendimento;
	}

	public short getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(short indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}



	/**
	 * @return Retorna o campo unidadeRepavimentadora.
	 */
	public gcom.cadastro.unidade.UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	/**
	 * @param unidadeRepavimentadora O unidadeRepavimentadora a ser setado.
	 */
	public void setUnidadeRepavimentadora(
			gcom.cadastro.unidade.UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}	
	
	public Filtro retornaFiltro(){
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("meioSolicitacao");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeCentralizadora");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeRepavimentadora");
		filtroUnidadeOrganizacional. adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");		
		filtroUnidadeOrganizacional. adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID, this.getId()));
		return filtroUnidadeOrganizacional; 
	}
	
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID,this.getId()));
	
		return filtro;
	}
	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getCodigoConstante() {
		return codigoConstante;
	}

	public void setCodigoConstante(Integer codigoConstante) {
		this.codigoConstante = codigoConstante;
	}
	
}
