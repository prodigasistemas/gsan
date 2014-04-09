package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TarifaSocialRevisaoMotivo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Integer indicadorPermiteRecadastramento;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set tarifaSocialDadoEconomias;
    
    /**
	 * Indicador de permissão de recadastramento 
	 */
    public final static Integer INDICADOR_PERMITE_RECADASTRAMENTO_SIM = new Integer("1");
	public final static Integer INDICADOR_PERMITE_RECADASTRAMENTO_NAO = new Integer("2");

    public String toString() {
        return new ToStringBuilder(this)
            .append("rtsmId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
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
	 * @return Retorna o campo indicadorPermiteRecadastramento.
	 */
	public Integer getIndicadorPermiteRecadastramento() {
		return indicadorPermiteRecadastramento;
	}

	/**
	 * @param indicadorPermiteRecadastramento O indicadorPermiteRecadastramento a ser setado.
	 */
	public void setIndicadorPermiteRecadastramento(
			Integer indicadorPermiteRecadastramento) {
		this.indicadorPermiteRecadastramento = indicadorPermiteRecadastramento;
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
	 * @return Retorna o campo tarifaSocialDadoEconomias.
	 */
	public Set getTarifaSocialDadoEconomias() {
		return tarifaSocialDadoEconomias;
	}

	/**
	 * @param tarifaSocialDadoEconomias O tarifaSocialDadoEconomias a ser setado.
	 */
	public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
		this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
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
	
	public String getDescricaoParaRegistroTransacao(){
		return this.descricao;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] key = {FiltroTarifaSocialRevisaoMotivo.ID};
		return key;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}
    
	@Override
	public Filtro retornaFiltro() {
		FiltroTarifaSocialRevisaoMotivo filtro = new FiltroTarifaSocialRevisaoMotivo();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialRevisaoMotivo.ID, this.getId()));
		return filtro;
	}	
}
