package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.operacional.DistritoOperacional;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class BairroArea extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Short codigoBairroArea;
    
    /** persistent field */
    private Short codigoMunicipio;

    /** persistent field */
    private gcom.cadastro.geografico.Bairro bairro;

    /** persistent field */
    private DistritoOperacional distritoOperacional;

    /** full constructor */
    public BairroArea(String nome, Date ultimaAlteracao, Short codigoBairroArea, Short codigoMunicipio, gcom.cadastro.geografico.Bairro bairro,  DistritoOperacional distritoOperacional) {
        this.nome = nome;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoBairroArea = codigoBairroArea;
        this.codigoMunicipio = codigoMunicipio;
        this.bairro = bairro;
        this.distritoOperacional = distritoOperacional;
    }

    /** default constructor */
    public BairroArea() {
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

    public gcom.cadastro.geografico.Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(gcom.cadastro.geografico.Bairro bairro) {
        this.bairro = bairro;
    }

    public DistritoOperacional getDistritoOperacional() {
        return this.distritoOperacional;
    }

    public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
        this.distritoOperacional = distritoOperacional;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @return Retorna o campo codigoBairroArea.
	 */
	public Short getCodigoBairroArea() {
		return codigoBairroArea;
	}

	/**
	 * @param codigoBairroArea O codigoBairroArea a ser setado.
	 */
	public void setCodigoBairroArea(Short codigoBairroArea) {
		this.codigoBairroArea = codigoBairroArea;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo codigoMunicipio.
	 */
	public Short getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * @param codigoMunicipio O codigoMunicipio a ser setado.
	 */
	public void setCodigoMunicipio(Short codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	
	public Filtro retornaFiltro(){
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		
		filtroBairroArea. adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroBairroArea. adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroBairroArea. adicionarParametro(
				new ParametroSimples(FiltroBairroArea.ID, this.getId()));
		return filtroBairroArea; 
	}

}
