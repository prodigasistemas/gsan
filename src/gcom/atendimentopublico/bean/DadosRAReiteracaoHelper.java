package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.util.Util;

import java.util.Collection;


/**
 * [UC0424] Consultar Registro de Atendimento
 * 
 * @author Vivianne Sousa
 * @date 16/05/2011
 */
public class DadosRAReiteracaoHelper {
	
	private String nomeSolicitante;
	private Integer idClienteSolicitante;
	private Integer idUnidadeSolicitante;
	private RAReiteracao raReiteracao;
	private Collection colecaoRAReiteracaoFone;
	private String fonePrincipal;
	private String nomeClienteSolicitante;
	private String nomeUnidadeSolicitante;
	private String enderecoSolicitante;
	
	public String getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(String enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public Collection getColecaoRAReiteracaoFone() {
		return colecaoRAReiteracaoFone;
	}

	public void setColecaoRAReiteracaoFone(Collection colecaoRAReiteracaoFone) {
		this.colecaoRAReiteracaoFone = colecaoRAReiteracaoFone;
	}

	public String getFonePrincipal() {
		return fonePrincipal;
	}

	public void setFonePrincipal(String fonePrincipal) {
		this.fonePrincipal = fonePrincipal;
	}

	public Integer getIdClienteSolicitante() {
		return idClienteSolicitante;
	}

	public void setIdClienteSolicitante(Integer idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}

	public Integer getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}

	public void setIdUnidadeSolicitante(Integer idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public RAReiteracao getRaReiteracao() {
		return raReiteracao;
	}

	public void setRaReiteracao(RAReiteracao raReiteracao) {
		this.raReiteracao = raReiteracao;
	}

	public String getDataReiteracaoFormatada() {
		return Util.formatarDataComHora(this.raReiteracao.getUltimaAlteracao());
	}

	public String getNomeClienteSolicitante() {
		return nomeClienteSolicitante;
	}

	public void setNomeClienteSolicitante(String nomeClienteSolicitante) {
		this.nomeClienteSolicitante = nomeClienteSolicitante;
	}

	public String getNomeUnidadeSolicitante() {
		return nomeUnidadeSolicitante;
	}

	public void setNomeUnidadeSolicitante(String nomeUnidadeSolicitante) {
		this.nomeUnidadeSolicitante = nomeUnidadeSolicitante;
	}

}
