package gcom.gui.cobranca.cobrancaporresultado;

import java.io.Serializable;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class OrdemServicoGeradaEmpresaCobrancaHelper implements
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
