package gcom.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialDado extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Método que retorna o filtro com as chaves primarias preenchidas
	 *
	 * @return - Filtro
	public Filtro retornaFiltro(){
		FiltroTarifaSocialDado filtro = new FiltroTarifaSocialDado();
		filtro.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDado.ID,this.getId()));
		return filtro;
	}
	/**
	 * Método que retorna os campos das chaves primarias
	 *
	 * @return
	
	public String[] retornaCamposChavePrimaria(){
		String[] array = {"id"};
		return array;
	}
	 */

    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private Date dataImplantacao;

    /**
     * nullable persistent field
     */
    private Date dataExclusao;

    /**
     * nullable persistent field
     */
    private Short quantidadeRecadastramento;

    /**
     * nullable persistent field
     */
    private Date dataRecadastramento;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    /**
     * nullable persistent field
     */
    private Imovel imovel;

    /**
     * persistent field
     */
    private gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo;

    private Set tarifaSocialDadoEconomias;

    /**
     * full constructor
     * 
     * @param dataImplantacao
     *            Descrição do parâmetro
     * @param dataExclusao
     *            Descrição do parâmetro
     * @param quantidadeRecadastramento
     *            Descrição do parâmetro
     * @param dataRecadastramento
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     * @param imovel
     *            Descrição do parâmetro
     * @param tarifaSocialExclusaoMotivo
     *            Descrição do parâmetro
     */
    public TarifaSocialDado(
            Date dataImplantacao,
            Date dataExclusao,
            Short quantidadeRecadastramento,
            Date dataRecadastramento,
            Date ultimaAlteracao,
            Imovel imovel,
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.dataImplantacao = dataImplantacao;
        this.dataExclusao = dataExclusao;
        this.quantidadeRecadastramento = quantidadeRecadastramento;
        this.dataRecadastramento = dataRecadastramento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
    }

    /**
     * default constructor
     */
    public TarifaSocialDado() {
    }

    /**
     * minimal constructor
     * 
     * @param tarifaSocialExclusaoMotivo
     *            Descrição do parâmetro
     */
    public TarifaSocialDado(
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
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
     * Retorna o valor de dataImplantacao
     * 
     * @return O valor de dataImplantacao
     */
    public Date getDataImplantacao() {
        return this.dataImplantacao;
    }

    /**
     * Seta o valor de dataImplantacao
     * 
     * @param dataImplantacao
     *            O novo valor de dataImplantacao
     */
    public void setDataImplantacao(Date dataImplantacao) {
        this.dataImplantacao = dataImplantacao;
    }

    /**
     * Retorna o valor de dataExclusao
     * 
     * @return O valor de dataExclusao
     */
    public Date getDataExclusao() {
        return this.dataExclusao;
    }

    /**
     * Seta o valor de dataExclusao
     * 
     * @param dataExclusao
     *            O novo valor de dataExclusao
     */
    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    /**
     * Retorna o valor de quantidadeRecadastramento
     * 
     * @return O valor de quantidadeRecadastramento
     */
    public Short getQuantidadeRecadastramento() {
        return this.quantidadeRecadastramento;
    }

    /**
     * Seta o valor de quantidadeRecadastramento
     * 
     * @param quantidadeRecadastramento
     *            O novo valor de quantidadeRecadastramento
     */
    public void setQuantidadeRecadastramento(Short quantidadeRecadastramento) {
        this.quantidadeRecadastramento = quantidadeRecadastramento;
    }

    /**
     * Retorna o valor de dataRecadastramento
     * 
     * @return O valor de dataRecadastramento
     */
    public Date getDataRecadastramento() {
        return this.dataRecadastramento;
    }

    /**
     * Seta o valor de dataRecadastramento
     * 
     * @param dataRecadastramento
     *            O novo valor de dataRecadastramento
     */
    public void setDataRecadastramento(Date dataRecadastramento) {
        this.dataRecadastramento = dataRecadastramento;
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
     * Retorna o valor de imovel
     * 
     * @return O valor de imovel
     */
    public Imovel getImovel() {
        return this.imovel;
    }

    /**
     * Seta o valor de imovel
     * 
     * @param imovel
     *            O novo valor de imovel
     */
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    /**
     * Retorna o valor de tarifaSocialExclusaoMotivo
     * 
     * @return O valor de tarifaSocialExclusaoMotivo
     */
    public gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo getTarifaSocialExclusaoMotivo() {
        return this.tarifaSocialExclusaoMotivo;
    }

    /**
     * Seta o valor de tarifaSocialExclusaoMotivo
     * 
     * @param tarifaSocialExclusaoMotivo
     *            O novo valor de tarifaSocialExclusaoMotivo
     */
    public void setTarifaSocialExclusaoMotivo(
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Retorna o valor de tarifaSocialDadoEconomias
     * 
     * @return O valor de tarifaSocialDadoEconomias
     */
    public Set getTarifaSocialDadoEconomias() {
        return tarifaSocialDadoEconomias;
    }

    /**
     * Seta o valor de tarifaSocialDadoEconomias
     * 
     * @param tarifaSocialDadoEconomias
     *            O novo valor de tarifaSocialDadoEconomias
     */
    public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
    }
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}
