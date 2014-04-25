package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de religação de clientes inadimplentes
 * 
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 25/01/2011
 */
public class RelatorioReligacaoClientesInadiplentesBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String endereco;
	private String perfil;
	private String numeroOS;
	private String usuarioAberturaOS;
	private String nomeUsuarioAberturaOS;
	private String dataEncerramento;
	private String usuarioEncerramentoOS;
	private String nomeUsuarioEncerramentoOS;
	private String valorDebito;
	
	private String nomeCliente;
	private String cpf;
	private String tipoRelacao;

    public RelatorioReligacaoClientesInadiplentesBean(){
    	
    }
    
	public RelatorioReligacaoClientesInadiplentesBean(String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}
	
	public RelatorioReligacaoClientesInadiplentesBean(
			String nomeCliente, String cpf, String tipoRelacao, 
			String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.tipoRelacao = tipoRelacao;
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuarioAberturaOS() {
		return usuarioAberturaOS;
	}

	public void setUsuarioAberturaOS(String usuarioAberturaOS) {
		this.usuarioAberturaOS = usuarioAberturaOS;
	}

	public String getUsuarioEncerramentoOS() {
		return usuarioEncerramentoOS;
	}

	public void setUsuarioEncerramentoOS(String usuarioEncerramentoOS) {
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeUsuarioAberturaOS() {
		return nomeUsuarioAberturaOS;
	}

	public void setNomeUsuarioAberturaOS(String nomeUsuarioAberturaOS) {
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
	}

	public String getNomeUsuarioEncerramentoOS() {
		return nomeUsuarioEncerramentoOS;
	}

	public void setNomeUsuarioEncerramentoOS(String nomeUsuarioEncerramentoOS) {
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

}
