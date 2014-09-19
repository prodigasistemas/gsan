package gcom.cadastro.endereco;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class LogradouroCep extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro() {
		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, cep.getCepId()));
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
		filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.LOGRADOURO);
		return filtroLogradouroCep;
	}

	/** nullable persistent field */
    private Integer id;
    
    /** nullable persistent field */
    private Logradouro logradouro;

    /** nullable persistent field */
    private Cep cep;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;



    /** default constructor */
    public LogradouroCep() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LogradouroCep) ) return false;
        LogradouroCep castOther = (LogradouroCep) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

	public LogradouroCep(Integer id, Logradouro logradouro, Cep cep, Short indicadorUso, Date ultimaAlteracao) {
		super();
		
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public LogradouroCep(Integer idLogradouro, Integer codigoCep) {
		this.logradouro = new Logradouro(idLogradouro);
		this.cep = new Cep(codigoCep);
	}

	public LogradouroCep(Integer id) {
		this.id = id;
	}

	public LogradouroCep(Integer idLogradouro, Cep cep) {
		this.id = idLogradouro;
		this.cep = cep;
	}

	/**
	 * @return Retorna o campo cep.
	 */
	public Cep getCep() {
		return cep;
	}

	/**
	 * @param cep O cep a ser setado.
	 */
	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo logradouro.
	 */
	public Logradouro getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro O logradouro a ser setado.
	 */
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
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

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
		if (getLogradouro() != null){
			getLogradouro().initializeLazy();
		}
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.getLogradouro().getDescricaoParaRegistroTransacao()
		 + " - " + this.cep.getCepFormatado();
	}
	
	public boolean hasLogradouro(){
		return logradouro != null && !logradouro.getId().equals(new Integer("0"));
	}
	
	public boolean hasMunicipio(){
		return logradouro.getMunicipio()!= null;
	}
	
	public boolean hasTitulo(){
		return logradouro.getLogradouroTitulo() != null && !logradouro.getLogradouroTitulo().equals("");
	}
	
	public boolean hasDescricaoAbreviada(){
		return logradouro.getLogradouroTitulo().hasDescricaoAbreviada();
	}
	
	public boolean hasTituloDescricao(){
		return logradouro.getLogradouroTitulo().hasDescricao();
	}

	public boolean hasTituloDescricaoAbreviada(){
		return logradouro.getLogradouroTitulo().hasDescricaoAbreviada();
	}
	
	public boolean hasTipoDescricao(){
		return logradouro.getLogradouroTipo().hasDescricao();
	}

	public boolean hasTipoDescricaoAbreviada(){
		return logradouro.getLogradouroTipo().hasDescricaoAbreviada();
	}

	public boolean hasLogradouroTipo(){
		return logradouro.getLogradouroTipo() != null && !logradouro.getLogradouroTipo().equals("");
	}

	public boolean hasCep() {
		return this.getCep() != null && this.getCep().getCepFormatado() != null;
	}

	public boolean hasUnidadeFederacao() {
		return hasLogradouro() && logradouro.getMunicipio() != null && logradouro.getMunicipio().hasUnidadeFederacao();
	}
}
