package gcom.atendimentopublico.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Vivianne Sousa
 * @date 14/03/2011
 */
public class RegistroAtendimentoDevolucaoValoresHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idImovel;
	private String[] idPerfilImovel;
	private Date dataAtendimentoInicioFormatada;
	private Date dataAtendimentoFimFormatada;
	private Integer numeroRA;
	private String nomeClienteUsuario;
	
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public RegistroAtendimentoDevolucaoValoresHelper() {
		super();
		
	}
	public RegistroAtendimentoDevolucaoValoresHelper(Integer idImovel, String[] idPerfilImovel, Date dataAtendimentoInicioFormatada, Date dataAtendimentoFimFormatada, Integer numeroRA) {
		super();
		
		this.idImovel = idImovel;
		this.idPerfilImovel = idPerfilImovel;
		this.dataAtendimentoInicioFormatada = dataAtendimentoInicioFormatada;
		this.dataAtendimentoFimFormatada = dataAtendimentoFimFormatada;
		this.numeroRA = numeroRA;
	}
	
	public RegistroAtendimentoDevolucaoValoresHelper(Integer idImovel, Integer numeroRA,String nomeClienteUsuario) {
		super();
		
		this.idImovel = idImovel;
		this.numeroRA = numeroRA;
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	
	public Date getDataAtendimentoFimFormatada() {
		return dataAtendimentoFimFormatada;
	}
	public void setDataAtendimentoFimFormatada(Date dataAtendimentoFimFormatada) {
		this.dataAtendimentoFimFormatada = dataAtendimentoFimFormatada;
	}
	public Date getDataAtendimentoInicioFormatada() {
		return dataAtendimentoInicioFormatada;
	}
	public void setDataAtendimentoInicioFormatada(
			Date dataAtendimentoInicioFormatada) {
		this.dataAtendimentoInicioFormatada = dataAtendimentoInicioFormatada;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public String[] getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(String[] idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public Integer getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(Integer numeroRA) {
		this.numeroRA = numeroRA;
	}
	
	

}
