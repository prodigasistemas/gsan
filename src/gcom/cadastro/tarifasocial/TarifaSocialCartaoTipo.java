package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialCartaoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] key = {FiltroTarifaSocialCartaoTipo.ID};
		return key;
	}

    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private String descricao;

    /**
     * nullable persistent field
     */
    private String descricaoAbreviada;

    /**
     * nullable persistent field
     */
    private Short indicadorValidade;

    /**
     * nullable persistent field
     */
    private Short numeroMesesAdesao;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    /**
     * Description of the Field
     */
    public final static Short INDICADOR_EXISTENCIA_VALIDADE_SIM = new Short("1");

    /**
     * Description of the Field
     */
    public final static Short INDICADOR_EXISTENCIA_VALIDADE_NAO = new Short("2");

    /**
     * Description of the Field
     */
    public final static Short NUMERO_MAXIMO_MESES_ADESAO_ZERO = new Short("0");

    /**
     * full constructor
     * 
     * @param descricao
     *            Descrição do parâmetro
     * @param descricaoAbreviada
     *            Descrição do parâmetro
     * @param indicadorValidade
     *            Descrição do parâmetro
     * @param numeroMesesAdesao
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public TarifaSocialCartaoTipo(String descricao, String descricaoAbreviada,
            Short indicadorValidade, Short numeroMesesAdesao,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorValidade = indicadorValidade;
        this.numeroMesesAdesao = numeroMesesAdesao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public TarifaSocialCartaoTipo() {
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de descricaoAbreviada
     * 
     * @return O valor de descricaoAbreviada
     */
    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    /**
     * Seta o valor de descricaoAbreviada
     * 
     * @param descricaoAbreviada
     *            O novo valor de descricaoAbreviada
     */
    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    /**
     * Retorna o valor de indicadorValidade
     * 
     * @return O valor de indicadorValidade
     */
    public Short getIndicadorValidade() {
        return this.indicadorValidade;
    }

    /**
     * Seta o valor de indicadorValidade
     * 
     * @param indicadorValidade
     *            O novo valor de indicadorValidade
     */
    public void setIndicadorValidade(Short indicadorValidade) {
        this.indicadorValidade = indicadorValidade;
    }

    /**
     * Retorna o valor de numeroMesesAdesao
     * 
     * @return O valor de numeroMesesAdesao
     */
    public Short getNumeroMesesAdesao() {
        return this.numeroMesesAdesao;
    }

    /**
     * Seta o valor de numeroMesesAdesao
     * 
     * @param numeroMesesAdesao
     *            O novo valor de numeroMesesAdesao
     */
    public void setNumeroMesesAdesao(Short numeroMesesAdesao) {
        this.numeroMesesAdesao = numeroMesesAdesao;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public String getDescricaoParaRegistroTransacao(){
		return this.descricao;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}
    
	@Override
	public Filtro retornaFiltro() {
		FiltroTarifaSocialCartaoTipo filtro = new FiltroTarifaSocialCartaoTipo();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialCartaoTipo.ID, this.getId()));
		return filtro;
	}	
}
