package gcom.faturamento.bean;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * Encapsula as informações necessárias para Prescrever Débitos de Imóveis
 *
 * @author Hugo Leonardo
 * 
 * @date 15/10/2010
 */
public class PrescreverDebitosImovelHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idCliente;
	
	private String idImovel;
	
	private String esferaPoder;
	
	private Usuario usuarioLogado;
	
	private String dataInicio; 
	
	private String dataFim;
	
	private String formaPrescricao;
	
	private Integer idProcesso;
	
	private String anoMesReferencia;

	public PrescreverDebitosImovelHelper(){
		
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getFormaPrescricao() {
		return formaPrescricao;
	}

	public void setFormaPrescricao(String formaPrescricao) {
		this.formaPrescricao = formaPrescricao;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	
}
