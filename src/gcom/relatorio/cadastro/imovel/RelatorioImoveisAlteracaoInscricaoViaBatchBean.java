package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de Boletim de Medição
 * 
 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */
public class RelatorioImoveisAlteracaoInscricaoViaBatchBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoAtual;
    private String inscricaoAnterior;
    private String dataAlteracao;
    private String matricula;
    private String clienteUsuario;
    private String autorizado;

    public RelatorioImoveisAlteracaoInscricaoViaBatchBean(){
    	
    }
    
    public RelatorioImoveisAlteracaoInscricaoViaBatchBean(String inscricaoAnterior, String inscricaoAtual, 
    		String dataAlteracao, String matricula, String clienteUsuario, String autorizado) {
		
		this.inscricaoAtual = inscricaoAtual;
		this.matricula = matricula;
		this.inscricaoAnterior = inscricaoAnterior;
		this.dataAlteracao = dataAlteracao;
		this.clienteUsuario = clienteUsuario;
		this.autorizado = autorizado;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getInscricaoAnterior() {
		return inscricaoAnterior;
	}

	public void setInscricaoAnterior(String inscricaoAnterior) {
		this.inscricaoAnterior = inscricaoAnterior;
	}

	public String getInscricaoAtual() {
		return inscricaoAtual;
	}

	public void setInscricaoAtual(String inscricaoAtual) {
		this.inscricaoAtual = inscricaoAtual;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	
}
