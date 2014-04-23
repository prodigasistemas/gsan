package gcom.cadastro.endereco;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class Logradouro extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	public static final int ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	
	public Filtro retornaFiltro() {
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID,this.getId()));
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtroLogradouro;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}


    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;
    
    /** persistent field */
    private String nomePopular;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private gcom.cadastro.endereco.LogradouroTitulo logradouroTitulo;

    /** persistent field */
    private gcom.cadastro.endereco.LogradouroTipo logradouroTipo;
    
    /** persistent field */
    private Set<LogradouroBairro> logradouroBairros;
    
    /** persistent field */
    private Set<LogradouroCep> logradouroCeps;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroLogradouro.OS_PROGRAMA_CALIBRAGEM,funcionalidade={ATUALIZAR_IMPORTANCIA_LOGRADOURO})
    private OSProgramacaoCalibragem programaCalibragem;

    public OSProgramacaoCalibragem getProgramaCalibragem() {
		return programaCalibragem;
	}

	public void setProgramaCalibragem(OSProgramacaoCalibragem programaCalibragem) {
		this.programaCalibragem = programaCalibragem;
	}

	/** full constructor */
    public Logradouro(String nome,String nomePopular, Short indicadorUso, Date ultimaAlteracao,
            Municipio municipio,
            gcom.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gcom.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.nomePopular = nomePopular;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }

    /** full constructor */
    public Logradouro(String nome, Short indicadorUso, Date ultimaAlteracao,
            Municipio municipio,
            gcom.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gcom.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }
    
    

    public Set<LogradouroBairro> getLogradouroBairros() {
		return logradouroBairros;
	}

	public void setLogradouroBairros(Set<LogradouroBairro> logradouroBairros) {
		this.logradouroBairros = logradouroBairros;
	}

	public Set<LogradouroCep> getLogradouroCeps() {
		return logradouroCeps;
	}

	public void setLogradouroCeps(Set<LogradouroCep> logradouroCeps) {
		this.logradouroCeps = logradouroCeps;
	}

	/**
	 * @return Retorna o campo nomePopular.
	 */
	public String getNomePopular() {
		return nomePopular;
	}

	/**
	 * @param nomePopular O nomePopular a ser setado.
	 */
	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	/** default constructor */
    public Logradouro() {
    }

    /** minimal constructor */
    public Logradouro(String nome, Municipio municipio,
            gcom.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gcom.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }

    public Logradouro(Integer idLogradouro) {
    	this.id = idLogradouro;
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

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public gcom.cadastro.endereco.LogradouroTitulo getLogradouroTitulo() {
        return this.logradouroTitulo;
    }

    public void setLogradouroTitulo(
            gcom.cadastro.endereco.LogradouroTitulo logradouroTitulo) {
        this.logradouroTitulo = logradouroTitulo;
    }

    public gcom.cadastro.endereco.LogradouroTipo getLogradouroTipo() {
        return this.logradouroTipo;
    }

    public void setLogradouroTipo(
            gcom.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.logradouroTipo = logradouroTipo;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Author: Raphael Rossiter
     * Data: 04/02/2006
     * @param logradouro
     * @return Descrição completa do logradouro (Tipo + Titulo + Nome)
     */
    public String getDescricaoFormatada(){
    	
    	String retorno = "";
    	
    	if (this.getLogradouroTipo() != null){
    		if (this.getLogradouroTipo().getDescricaoAbreviada() != null)
    		retorno = this.getLogradouroTipo().getDescricaoAbreviada();
    	}
    	
    	if (this.getLogradouroTitulo() != null){
    		if (this.getLogradouroTitulo().getDescricaoAbreviada() != null) {
    			if (retorno.length() > 0) {
    				retorno = retorno + " " + this.getLogradouroTitulo().getDescricaoAbreviada();
    			}
    			else {
    				retorno = this.getLogradouroTitulo().getDescricaoAbreviada();
    			}
    		}
    	}
    	
    	if (this.getNome() != null){
    		if (retorno.length() > 0) {
    			retorno = retorno + " " + this.getNome();
    		}
    		else {
    			retorno = this.getNome();
    		}
    	}
    	
    	return retorno;
    }

    @Override
    public void initializeLazy() {
    	getDescricaoFormatada();
    }
    
    @Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID,getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtro;
	}
    
    public boolean hasMunicipio() {
		return this.municipio != null;
	}
    
    public boolean hasLogradouroTitulo(){
    	return logradouroTitulo != null && !logradouroTitulo.equals("");
    }
    
    public boolean hasLogradouroTipo(){
    	return logradouroTipo != null && !logradouroTipo.equals("");
    }
}



