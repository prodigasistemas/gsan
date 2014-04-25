package gcom.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Negativador extends ObjetoTransacao implements Serializable {
	
	public Filtro retornaFiltro() {
		FiltroNegativador filtro = new FiltroNegativador();
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,this.getId()));		
		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public static final Integer NEGATIVADOR_SPC = new Integer(1); 
	public static final Integer NEGATIVADOR_SERASA = new Integer(2); 
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Short codigoAgente;

    /** nullable persistent field */
    private String numeroInscricaoEstadual;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Set negativacaoComandos;

    /** persistent field */
    private Set negativadorExclusaoMotivos;

    /** persistent field */
    private Set negativadorContratos;

    /** persistent field */
    private Set negativadorRegistroTipos;

    /** persistent field */
    private Set negativadorMovimentos;

    /** full constructor */
    public Negativador(Integer id, Short codigoAgente, String numeroInscricaoEstadual, Short indicadorUso, Date ultimaAlteracao, Imovel imovel, Cliente cliente, Set negativacaoComandos, Set negativadorExclusaoMotivos, Set negativadorContratos, Set negativadorRegistroTipos, Set negativadorMovimentos) {
        this.id = id;
        this.codigoAgente = codigoAgente;
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.cliente = cliente;
        this.negativacaoComandos = negativacaoComandos;
        this.negativadorExclusaoMotivos = negativadorExclusaoMotivos;
        this.negativadorContratos = negativadorContratos;
        this.negativadorRegistroTipos = negativadorRegistroTipos;
        this.negativadorMovimentos = negativadorMovimentos;
    }

    /** default constructor */
    public Negativador() {
    }

    /** minimal constructor */
    public Negativador(Integer id, Short codigoAgente, Short indicadorUso, Date ultimaAlteracao, Imovel imovel, Cliente cliente, Set negativacaoComandos, Set negativadorExclusaoMotivos, Set negativadorContratos, Set negativadorRegistroTipos, Set negativadorMovimentos) {
        this.id = id;
        this.codigoAgente = codigoAgente;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.cliente = cliente;
        this.negativacaoComandos = negativacaoComandos;
        this.negativadorExclusaoMotivos = negativadorExclusaoMotivos;
        this.negativadorContratos = negativadorContratos;
        this.negativadorRegistroTipos = negativadorRegistroTipos;
        this.negativadorMovimentos = negativadorMovimentos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCodigoAgente() {
        return this.codigoAgente;
    }

    public void setCodigoAgente(Short codigoAgente) {
        this.codigoAgente = codigoAgente;
    }

    public String getNumeroInscricaoEstadual() {
        return this.numeroInscricaoEstadual;
    }

    public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
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

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set getNegativacaoComandos() {
        return this.negativacaoComandos;
    }

    public void setNegativacaoComandos(Set negativacaoComandos) {
        this.negativacaoComandos = negativacaoComandos;
    }

    public Set getNegativadorExclusaoMotivos() {
        return this.negativadorExclusaoMotivos;
    }

    public void setNegativadorExclusaoMotivos(Set negativadorExclusaoMotivos) {
        this.negativadorExclusaoMotivos = negativadorExclusaoMotivos;
    }

    public Set getNegativadorContratos() {
        return this.negativadorContratos;
    }

    public void setNegativadorContratos(Set negativadorContratos) {
        this.negativadorContratos = negativadorContratos;
    }

    public Set getNegativadorRegistroTipos() {
        return this.negativadorRegistroTipos;
    }

    public void setNegativadorRegistroTipos(Set negativadorRegistroTipos) {
        this.negativadorRegistroTipos = negativadorRegistroTipos;
    }

    public Set getNegativadorMovimentos() {
        return this.negativadorMovimentos;
    }

    public void setNegativadorMovimentos(Set negativadorMovimentos) {
        this.negativadorMovimentos = negativadorMovimentos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Tosk
	 * @date 26/12/2007
	 *
	 * @param arg0
	 * @return
	 */
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof Negativador ) {
			Negativador n = (Negativador) obj;

			if (n.getId() != null && this.getId() != null) {
				retorno = n.getId().equals(this.getId());
			} else if (n.getId() == null && this.getId() == null) {
				retorno = true;
			}
		}
		return retorno;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Tosck
	 * @date 26/12/2007
	 *
	 * @return
	 */
	public int hashCode() {
		String retorno = "";
		if (this.getId() != null) {
			retorno = this.getId() + "asdfasd";
		}
		return (retorno + "sdfasd").hashCode();
	}
}
