package gcom.micromedicao.bean;

public class NumeroQuadraMinimoMaximoDaRotaHelper {
	
	private Integer idRota;
	private Integer numeroQuadraMinimo;
	private Integer numeroQuadraMaximo;
	private Integer quantidadeQuadras;

	public NumeroQuadraMinimoMaximoDaRotaHelper() { }
	
	public String toString() {
		return "NumeroQuadraMinimoMaximoDaRotaHelper[idRota="+idRota+",numeroQuadraMinino="
			+numeroQuadraMinimo+",numeroQuadraMaximo="+numeroQuadraMaximo+",quantidadeQuadras="
			+quantidadeQuadras+"]";
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getNumeroQuadraMaximo() {
		return numeroQuadraMaximo;
	}

	public void setNumeroQuadraMaximo(Integer numeroQuadraMaximo) {
		this.numeroQuadraMaximo = numeroQuadraMaximo;
	}

	public Integer getNumeroQuadraMinimo() {
		return numeroQuadraMinimo;
	}

	public void setNumeroQuadraMinimo(Integer numeroQuadraMinimo) {
		this.numeroQuadraMinimo = numeroQuadraMinimo;
	}
	
	public Integer getQuantidadeQuadras() {
		return quantidadeQuadras;
	}

	public void setQuantidadeQuadras(Integer quantidadeQuadras) {
		this.quantidadeQuadras = quantidadeQuadras;
	}

}
