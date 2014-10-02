package gcom.cadastro;

import java.io.Serializable;


/**
 * @author Vivianne Sousa
 * @date 14/03/2011
 */
public class ImovelInscricaoAlteradaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idLocalidade;
	private String desLocalidade;
	private Integer codigoSetorComercial;
	private String desSetorComercial;
	private Integer totalImoveis;
	private Integer idQuadra;
	private Integer numeroQuadra;
	private Integer indicadorAutorizar;
	
	
	public ImovelInscricaoAlteradaHelper() {
		super();
		
	}


	
	public ImovelInscricaoAlteradaHelper(Integer indicadorAutorizar, Integer totalImoveis, Integer idQuadra) {
		super();
		
		this.indicadorAutorizar = indicadorAutorizar;
		this.totalImoveis = totalImoveis;
		this.idQuadra = idQuadra;
	}



	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}


	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}


	public String getDesLocalidade() {
		return desLocalidade;
	}


	public void setDesLocalidade(String desLocalidade) {
		this.desLocalidade = desLocalidade;
	}


	public String getDesSetorComercial() {
		return desSetorComercial;
	}


	public void setDesSetorComercial(String desSetorComercial) {
		this.desSetorComercial = desSetorComercial;
	}


	public Integer getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public Integer getIdQuadra() {
		return idQuadra;
	}


	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}


	public Integer getTotalImoveis() {
		return totalImoveis;
	}


	public void setTotalImoveis(Integer totalImoveis) {
		this.totalImoveis = totalImoveis;
	}

	public Integer getIndicadorAutorizar() {
		return indicadorAutorizar;
	}



	public void setIndicadorAutorizar(Integer indicadorAutorizar) {
		this.indicadorAutorizar = indicadorAutorizar;
	}



	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}



	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	
	
	

}
