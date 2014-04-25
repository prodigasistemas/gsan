package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 27/10/2006
 */

public class FiltrarValorCobrancaServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoServico;

	private String nomeTipoServico;

	private String perfilImovel;

	private String indicadorMedido;

	private String capacidadeHidrometro;
	
	private String indicadorGeracaoDebito;
	
	private String valorServicoInicial;

	private String valorServicoFinal;

	private String atualizar;
	
	private String idCategoria;
	
	private String idSubCategoria;
	
	private String indicativoTipoSevicoEconomias;
	
	private String dataVigenciaInicial;
	
	private String dataVigenciaFinal;
	
	private String quantidadeEconomiasInicial;
	
	private String quantidadeEconomiasFinal;

	/**
	 * @return Retorna o campo valorServicoFinal.
	 */
	public String getValorServicoFinal() {
		return valorServicoFinal;
	}

	/**
	 * @param valorServicoFinal
	 *            O valorServicoFinal a ser setado.
	 */
	public void setValorServicoFinal(String valorServicoFinal) {
		this.valorServicoFinal = valorServicoFinal;
	}

	/**
	 * @return Retorna o campo valorServicoInicial.
	 */
	public String getValorServicoInicial() {
		return valorServicoInicial;
	}

	/**
	 * @param valorServicoInicial
	 *            O valorServicoInicial a ser setado.
	 */
	public void setValorServicoInicial(String valorServicoInicial) {
		this.valorServicoInicial = valorServicoInicial;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getIndicadorMedido() {
		return indicadorMedido;
	}

	public void setIndicadorMedido(String indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar
	 *            O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public String getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public String getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}

	public void setIndicativoTipoSevicoEconomias(
			String indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}

	public String getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(String quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public String getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(String quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIndicadorGeracaoDebito() {
		return indicadorGeracaoDebito;
	}

	public void setIndicadorGeracaoDebito(String indicadorGeracaoDebito) {
		this.indicadorGeracaoDebito = indicadorGeracaoDebito;
	}
	
}
