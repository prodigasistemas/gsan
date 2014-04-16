package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Bairro extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				this.getId()));
		filtroBairro
				.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		return filtroBairro;
	}
	
	
	public final static String BAIRRO_NAO_INFORMADO = "BAIRRO NAO INFORMADO";


    /** identifier field */
    private Integer id;

    /** persistent field */
    private int codigo;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private Integer codigoBairroPrefeitura;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.geografico.Municipio municipio;

    /** full constructor */
    public Bairro(int codigo, String nome, Integer codigoBairroPrefeitura,
            Short indicadorUso, Date ultimaAlteracao,
            gcom.cadastro.geografico.Municipio municipio) {
        this.codigo = codigo;
        this.nome = nome;
        this.codigoBairroPrefeitura = codigoBairroPrefeitura;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
    }

    /** default constructor */
    public Bairro() {
    }

    /** minimal constructor */
    public Bairro(int codigo, gcom.cadastro.geografico.Municipio municipio) {
        this.codigo = codigo;
        this.municipio = municipio;
    }

    public Bairro(Integer idBairro) {
    	this.id = idBairro;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoBairroPrefeitura() {
        return this.codigoBairroPrefeitura;
    }

    public void setCodigoBairroPrefeitura(Integer codigoBairroPrefeitura) {
        this.codigoBairroPrefeitura = codigoBairroPrefeitura;
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

    public gcom.cadastro.geografico.Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
        this.municipio = municipio;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    
    public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Bairro)) {
			return false;
		}
		Bairro castOther = (Bairro) other;

		return (this.getId().equals(castOther.getId()));
	}

    public boolean hasMunicipio(){
		return municipio != null && municipio.getId().intValue() != 0;
	}
}
