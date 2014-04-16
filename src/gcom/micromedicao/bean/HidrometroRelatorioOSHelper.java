package gcom.micromedicao.bean;

import java.io.Serializable;

/**
 * Relatorio Ordem de Serviço
 * 
 * @author Vivianne Sousa
 * @date 08/06/2007
 */
public class HidrometroRelatorioOSHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String hidrometroNumero;
	private String hidrometroFixo;
	private String hidrometroMarca;
	private String hidrometroCapacidade;
	private String hidrometroDiametro;
	private String hidrometroLocal;
	private String hidrometroLeitura;
	private String hidrometroNumeroDigitos;
	
	public String getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}
	public void setHidrometroCapacidade(String hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}
	public String getHidrometroDiametro() {
		return hidrometroDiametro;
	}
	public void setHidrometroDiametro(String hidrometroDiametro) {
		this.hidrometroDiametro = hidrometroDiametro;
	}
	public String getHidrometroFixo() {
		return hidrometroFixo;
	}
	public void setHidrometroFixo(String hidrometroFixo) {
		this.hidrometroFixo = hidrometroFixo;
	}
	public String getHidrometroLeitura() {
		return hidrometroLeitura;
	}
	public void setHidrometroLeitura(String hidrometroLeitura) {
		this.hidrometroLeitura = hidrometroLeitura;
	}
	public String getHidrometroLocal() {
		return hidrometroLocal;
	}
	public void setHidrometroLocal(String hidrometroLocal) {
		this.hidrometroLocal = hidrometroLocal;
	}
	public String getHidrometroMarca() {
		return hidrometroMarca;
	}
	public void setHidrometroMarca(String hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}
	public String getHidrometroNumero() {
		return hidrometroNumero;
	}
	public void setHidrometroNumero(String hidrometroNumero) {
		this.hidrometroNumero = hidrometroNumero;
	}
	public String getHidrometroNumeroDigitos() {
		return hidrometroNumeroDigitos;
	}
	public void setHidrometroNumeroDigitos(String hidrometroNumeroDigitos) {
		this.hidrometroNumeroDigitos = hidrometroNumeroDigitos;
	}
	
	
}
