package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class CobrancaDebitoSituacao extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set resumoCobrancaAcaos;

    /** persistent field */
    private Set cobrancaDocumentoItems;

    /** persistent field */
    private Set cobrancaDocumentos;

    public static final Integer PENDENTE = new Integer("1");
    
    public static final Integer PAGO = new Integer("2");
    
    public static final Integer PARCELADO = new Integer("3");
    
    public static final Integer CANCELADO = new Integer("4");
    
    public static final Integer SEM_DEBITOS = new Integer("5");
    
    
    
    /** default constructor */
    public CobrancaDebitoSituacao() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdstId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo cobrancaDocumentoItems.
	 */
	public Set getCobrancaDocumentoItems() {
		return cobrancaDocumentoItems;
	}

	/**
	 * @param cobrancaDocumentoItems O cobrancaDocumentoItems a ser setado.
	 */
	public void setCobrancaDocumentoItems(Set cobrancaDocumentoItems) {
		this.cobrancaDocumentoItems = cobrancaDocumentoItems;
	}

	/**
	 * @return Retorna o campo cobrancaDocumentos.
	 */
	public Set getCobrancaDocumentos() {
		return cobrancaDocumentos;
	}

	/**
	 * @param cobrancaDocumentos O cobrancaDocumentos a ser setado.
	 */
	public void setCobrancaDocumentos(Set cobrancaDocumentos) {
		this.cobrancaDocumentos = cobrancaDocumentos;
	}

	/**
	 * @return Retorna o campo desacricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param desacricao O desacricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo resumoCobrancaAcaos.
	 */
	public Set getResumoCobrancaAcaos() {
		return resumoCobrancaAcaos;
	}

	/**
	 * @param resumoCobrancaAcaos O resumoCobrancaAcaos a ser setado.
	 */
	public void setResumoCobrancaAcaos(Set resumoCobrancaAcaos) {
		this.resumoCobrancaAcaos = resumoCobrancaAcaos;
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
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		getDescricao();
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroCobrancaDebitoSituacao filtroCobrancaDebitoSituacao = new FiltroCobrancaDebitoSituacao();
		filtroCobrancaDebitoSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaDebitoSituacao.ID, this.getId()));		
		return filtroCobrancaDebitoSituacao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return new String[] { "id" };
	}

}
