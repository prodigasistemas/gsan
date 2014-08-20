package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class CobrancaDebitoSituacao extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private short indicadorUso;
    private Date ultimaAlteracao;
    private Set resumoCobrancaAcaos;
    private Set cobrancaDocumentoItems;
    private Set cobrancaDocumentos;

    public static final Integer PENDENTE = new Integer("1");
    public static final Integer PAGO = new Integer("2");
    public static final Integer PARCELADO = new Integer("3");
    public static final Integer CANCELADO = new Integer("4");
    public static final Integer SEM_DEBITOS = new Integer("5");
    
    public CobrancaDebitoSituacao() {
    }
    
    public CobrancaDebitoSituacao(Integer id) {
    	this.id = id;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdstId", getId())
            .toString();
    }

	public Set getCobrancaDocumentoItems() {
		return cobrancaDocumentoItems;
	}

	public void setCobrancaDocumentoItems(Set cobrancaDocumentoItems) {
		this.cobrancaDocumentoItems = cobrancaDocumentoItems;
	}

	public Set getCobrancaDocumentos() {
		return cobrancaDocumentos;
	}

	public void setCobrancaDocumentos(Set cobrancaDocumentos) {
		this.cobrancaDocumentos = cobrancaDocumentos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Set getResumoCobrancaAcaos() {
		return resumoCobrancaAcaos;
	}

	public void setResumoCobrancaAcaos(Set resumoCobrancaAcaos) {
		this.resumoCobrancaAcaos = resumoCobrancaAcaos;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

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
