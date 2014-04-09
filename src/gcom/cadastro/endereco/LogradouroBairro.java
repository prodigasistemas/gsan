package gcom.cadastro.endereco;

import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LogradouroBairro extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro() {
		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, bairro.getId()));
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		return filtroLogradouroBairro;
	}

	/** nullable persistent field */
    private Integer id;
    
    /** nullable persistent field */
    private Logradouro logradouro;

    /** nullable persistent field */
    private Bairro bairro;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public LogradouroBairro(Integer id, Logradouro logradouro, Bairro bairro,
            Date ultimaAlteracao) {
    	this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public LogradouroBairro() {
    }

    public LogradouroBairro(Integer idLogradouro, Integer idBairro) {
    	this.logradouro = new Logradouro(idLogradouro);
    	this.bairro = new Bairro(idBairro);
	}

	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                .toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof LogradouroBairro))
            return false;
        LogradouroBairro castOther = (LogradouroBairro) other;
        return new EqualsBuilder().append(this.getId(),
                castOther.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.bairro.getNome();
	}
	
	public boolean hasBairro(){
		return bairro != null && bairro.getId().intValue() != 0;
	}
	
	public boolean hasUnidadeFederacao(){
		return hasBairro() && bairro.getMunicipio() != null && bairro.getMunicipio().hasUnidadeFederacao();
	}
	
	public boolean hasMunicipio(){
		return hasBairro() && bairro.getMunicipio() != null && bairro.getMunicipio().getId().intValue() != 0;
	}
}
