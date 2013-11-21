package gcom.gui.relatorio.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * 
 * @author Daniel Alves
 *
 * @date 24/09/2010
 */

public class GerarRelatorioAtualizacaoCadastralViaInternetActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String periodoReferenciaInicial;
	private String periodoReferenciaFinal;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	private String opcaoRelatorio;
	
	public String getPeriodoReferenciaInicial() {
		return periodoReferenciaInicial;
	}
	public void setPeriodoReferenciaInicial(String periodoReferenciaInicial) {
		this.periodoReferenciaInicial = periodoReferenciaInicial;
	}
	public String getPeriodoReferenciaFinal() {
		return periodoReferenciaFinal;
	}
	public void setPeriodoReferenciaFinal(String periodoReferenciaFinal) {
		this.periodoReferenciaFinal = periodoReferenciaFinal;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
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
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

}
