package gcom.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialCarta extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TarifaSocialCartaPK comp_id;
	private Integer codigoMotivo;
	private Short indicadorExcluidoTarifaSocial;
	private Date dataComparecimento;
	private Date ultimaAlteracao;
	private Imovel imovel;
	private Cliente cliente;
	private TarifaSocialComandoCarta tarifaSocialComandoCarta;
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	
    public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public TarifaSocialCartaPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(TarifaSocialCartaPK comp_id) {
		this.comp_id = comp_id;
	}
	public TarifaSocialComandoCarta getTarifaSocialComandoCarta() {
		return tarifaSocialComandoCarta;
	}
	public void setTarifaSocialComandoCarta(
			TarifaSocialComandoCarta tarifaSocialComandoCarta) {
		this.tarifaSocialComandoCarta = tarifaSocialComandoCarta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Integer getCodigoMotivo() {
		return codigoMotivo;
	}
	public void setCodigoMotivo(Integer codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	public Date getDataComparecimento() {
		return dataComparecimento;
	}
	public void setDataComparecimento(Date dataComparecimento) {
		this.dataComparecimento = dataComparecimento;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Short getIndicadorExcluidoTarifaSocial() {
		return indicadorExcluidoTarifaSocial;
	}
	public void setIndicadorExcluidoTarifaSocial(Short indicadorExcluidoTarifaSocial) {
		this.indicadorExcluidoTarifaSocial = indicadorExcluidoTarifaSocial;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
