package gcom.gui.atendimentopublico.ordemservico;

import java.io.Serializable;

/**
 * [UC1192] Movimentar OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @date 18/07/2011
 */
public class OSSeletivaInspecaoAnormalidadeGeradaHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numeroOS;
	
	private String tipoServico;
	
	private String matriculaImovel;
	
	private String cliente;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}
