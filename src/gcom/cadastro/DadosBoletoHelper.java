package gcom.cadastro;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;

/**
 * [UC0925] Emitir Boletos
 *
 * @author Vivianne Sousa
 * @date 10/07/2009
 */
public class DadosBoletoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Imovel imovel;
	private String nomeCliente;
	private Integer idGrupoFaturamento;
	private Integer idEmpresa;
	private Short codigoRota;
	private Integer sequencialRota;
	
	public Short getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
}
