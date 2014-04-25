package gcom.gui.relatorio.cadastro.micromedicao;


import org.apache.struts.action.ActionForm;

/**
 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 *
 * @date 08/03/2010
 */

public class GerarRelatorioColetaMedidorEnergiaActionForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String faturamentoGrupo;
	
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String codigoSetorComercialInicial;
	private String setorComercialInicialDescricao;
	
	private String codigoSetorComercialFinal;
	private String setorComercialFinalDescricao;
	
	private String rotaInicial;
	private String sequencialRotaInicial;

	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	
	private String rotaFinal;
	private String sequencialRotaFinal;
	
	public void reset(){
		
		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;
		
		this.codigoSetorComercialInicial = null;
		this.setorComercialInicialDescricao = null;
		
		this.rotaInicial = null;
		this.sequencialRotaInicial = null;

		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;
		
		this.codigoSetorComercialFinal = null;
		this.setorComercialFinalDescricao = null;

		this.rotaFinal = null;
		this.sequencialRotaFinal = null;
		
		this.faturamentoGrupo = null;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getSetorComercialFinalDescricao() {
		return setorComercialFinalDescricao;
	}

	public void setSetorComercialFinalDescricao(String setorComercialFinalDescricao) {
		this.setorComercialFinalDescricao = setorComercialFinalDescricao;
	}

	public String getSetorComercialInicialDescricao() {
		return setorComercialInicialDescricao;
	}

	public void setSetorComercialInicialDescricao(
			String setorComercialInicialDescricao) {
		this.setorComercialInicialDescricao = setorComercialInicialDescricao;
	}
	
}
