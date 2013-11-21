package gcom.cadastro.imovel.bean;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.SetorComercial;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

public class ImovelAbaEnderecoHelper {
	
	private Collection<Imovel> imovelEnderecos;
	private SetorComercial setorComercial;
	private Usuario usuarioLogado;
	private Integer idFuncionalidade;
	private Integer idQuadraAnterior;
	/**
	 * @return Retorna o campo imovelEnderecos.
	 */
	public Collection<Imovel> getImovelEnderecos() {
		return imovelEnderecos;
	}
	/**
	 * @param imovelEnderecos O imovelEnderecos a ser setado.
	 */
	public void setImovelEnderecos(Collection<Imovel> imovelEnderecos) {
		this.imovelEnderecos = imovelEnderecos;
	}
	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	/**
	 * @return Retorna o campo usuarioLogado.
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	/**
	 * @param usuarioLogado O usuarioLogado a ser setado.
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public Integer getIdFuncionalidade() {
		return idFuncionalidade;
	}
	public void setIdFuncionalidade(Integer idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	
	public Integer getIdQuadraAnterior() {
		return idQuadraAnterior;
	}
	
	public void setIdQuadraAnterior(Integer idQuadraAnterior) {
		this.idQuadraAnterior = idQuadraAnterior;
	}
	
}
