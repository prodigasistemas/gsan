package gcom.arrecadacao.bean;



/**
 * [UC0803] - Manter Debito Automático
 * 
 * Classe que irá auxiliar no formato do retorno da pesquisa dos debitos automáticos
 *
 * @author Bruno Barros
 * @date 11/06/2008
 */
public class ManterDebitoAutomaticoHelper {
	
	private String matriculaImovel;
	private String matriculaImovelFormatada;
	private String idDebitoAutomatico;
	private String nomeCliente;
	private String siglaBanco;
	private String agencia;
	
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getIdDebitoAutomatico() {
		return idDebitoAutomatico;
	}
	public void setIdDebitoAutomatico(String idDebitoAutomatico) {
		this.idDebitoAutomatico = idDebitoAutomatico;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getSiglaBanco() {
		return siglaBanco;
	}
	public void setSiglaBanco(String siglaBanco) {
		this.siglaBanco = siglaBanco;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMatriculaImovelFormatada() {
		return matriculaImovelFormatada;
	}
	public void setMatriculaImovelFormatada(String matriculaImovelFormatada) {
		this.matriculaImovelFormatada = matriculaImovelFormatada;
	}	
	
}
