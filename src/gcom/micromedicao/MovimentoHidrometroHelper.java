package gcom.micromedicao;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * Helper contendo as informações necessárias para inserir
 * o movimento de hidrometro no relatorio batch
 *
 * @author Arthur
 * @date 17/06/2009
 */

public class MovimentoHidrometroHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection colecaoHidrometroSelecionado;

	private String dataMovimentacao;
	
	private String horaMovimentacao;
	
	private String idLocalArmazenagemDestino;
	
	private String idMotivoMovimentacao;
	
	private String parecer;
	
	private Usuario usuario;

	public Collection getColecaoHidrometroSelecionado() {
		return colecaoHidrometroSelecionado;
	}

	public void setColecaoHidrometroSelecionado(
			Collection colecaoHidrometroSelecionado) {
		this.colecaoHidrometroSelecionado = colecaoHidrometroSelecionado;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getHoraMovimentacao() {
		return horaMovimentacao;
	}

	public void setHoraMovimentacao(String horaMovimentacao) {
		this.horaMovimentacao = horaMovimentacao;
	}

	public String getIdLocalArmazenagemDestino() {
		return idLocalArmazenagemDestino;
	}

	public void setIdLocalArmazenagemDestino(String idLocalArmazenagemDestino) {
		this.idLocalArmazenagemDestino = idLocalArmazenagemDestino;
	}

	public String getIdMotivoMovimentacao() {
		return idMotivoMovimentacao;
	}

	public void setIdMotivoMovimentacao(String idMotivoMovimentacao) {
		this.idMotivoMovimentacao = idMotivoMovimentacao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
