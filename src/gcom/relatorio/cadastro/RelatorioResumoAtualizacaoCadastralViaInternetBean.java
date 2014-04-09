package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 29/09/2010 
 */
public class RelatorioResumoAtualizacaoCadastralViaInternetBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private Integer quantidadeNome;
	private Integer quantidadeCPF;
	private Integer quantidadeCNPJ;
	private Integer quantidadeEmail;
	private Integer quantidadeClientesAlterados;
	
	public RelatorioResumoAtualizacaoCadastralViaInternetBean(){}
	
	public RelatorioResumoAtualizacaoCadastralViaInternetBean(
			Integer quantidadeCNPJ, Integer quantidadeCPF,
			Integer quantidadeEmail, Integer quantidadeNome) {
		
		this.quantidadeCNPJ = quantidadeCNPJ;
		this.quantidadeCPF = quantidadeCPF;
		this.quantidadeEmail = quantidadeEmail;
		this.quantidadeNome = quantidadeNome;
	}

	public RelatorioResumoAtualizacaoCadastralViaInternetBean(
			Integer quantidadeNome, Integer quantidadeCPF,
			Integer quantidadeCNPJ, Integer quantidadeEmail,
			Integer quantidadeClientesAlterados) {
		this.quantidadeNome = quantidadeNome;
		this.quantidadeCPF = quantidadeCPF;
		this.quantidadeCNPJ = quantidadeCNPJ;
		this.quantidadeEmail = quantidadeEmail;
		this.quantidadeClientesAlterados = quantidadeClientesAlterados;
	}

	public Integer getQuantidadeNome() {
		return quantidadeNome;
	}

	public void setQuantidadeNome(Integer quantidadeNome) {
		this.quantidadeNome = quantidadeNome;
	}

	public Integer getQuantidadeCPF() {
		return quantidadeCPF;
	}

	public void setQuantidadeCPF(Integer quantidadeCPF) {
		this.quantidadeCPF = quantidadeCPF;
	}

	public Integer getQuantidadeCNPJ() {
		return quantidadeCNPJ;
	}

	public void setQuantidadeCNPJ(Integer quantidadeCNPJ) {
		this.quantidadeCNPJ = quantidadeCNPJ;
	}

	public Integer getQuantidadeEmail() {
		return quantidadeEmail;
	}

	public void setQuantidadeEmail(Integer quantidadeEmail) {
		this.quantidadeEmail = quantidadeEmail;
	}

	public Integer getQuantidadeClientesAlterados() {
		return quantidadeClientesAlterados;
	}

	public void setQuantidadeClientesAlterados(Integer quantidadeClientesAlterados) {
		this.quantidadeClientesAlterados = quantidadeClientesAlterados;
	}
	


	
}
