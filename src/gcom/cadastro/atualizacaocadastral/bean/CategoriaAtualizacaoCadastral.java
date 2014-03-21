package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class CategoriaAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = -1813572811630123528L;

	private Integer idCategoria;
	
	private Integer idSubcategoria;
	
	private Integer qtdEconomias;

	public CategoriaAtualizacaoCadastral(Integer idCategoria, Integer idSubcategoria, Integer qtdEconomias) {
		this.idCategoria = idCategoria;
		this.idSubcategoria = idSubcategoria;
		this.qtdEconomias = qtdEconomias;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public Integer getQtdEconomias() {
		return qtdEconomias;
	}

	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((idSubcategoria == null) ? 0 : idSubcategoria.hashCode());
		result = prime * result + ((qtdEconomias == null) ? 0 : qtdEconomias.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaAtualizacaoCadastral other = (CategoriaAtualizacaoCadastral) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (idSubcategoria == null) {
			if (other.idSubcategoria != null)
				return false;
		} else if (!idSubcategoria.equals(other.idSubcategoria))
			return false;
		if (qtdEconomias == null) {
			if (other.qtdEconomias != null)
				return false;
		} else if (!qtdEconomias.equals(other.qtdEconomias))
			return false;
		return true;
	}

	public String toString() {
		return "CategoriaAtualizacaoCadastral [idCategoria=" + idCategoria + ", idSubcategoria=" + idSubcategoria + ", qtdEconomias=" + qtdEconomias + "]";
	}
}
