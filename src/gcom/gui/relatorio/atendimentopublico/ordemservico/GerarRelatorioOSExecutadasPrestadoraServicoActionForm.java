package gcom.gui.relatorio.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class GerarRelatorioOSExecutadasPrestadoraServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String empresa;
	private String idGerencia;
	private String idUnidadeNegocio;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	private String[] colecaoRegiao;
	private String[] colecaoMicrorregiao;
	private String[] colecaoMunicipio;
	private String opcaoRelatorio;
	
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm(String periodoEncerramentoInicial, String periodoEncerramentoFinal, String empresa, String idGerencia, String idUnidadeNegocio, String codigoLocalidade, String descricaoLocalidade, String[] colecaoRegiao, String[] colecaoMicrorregiao, String[] colecaoMunicipio, String opcaoRelatorio) {
		super();
		
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
		this.empresa = empresa;
		this.idGerencia = idGerencia;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.codigoLocalidade = codigoLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.colecaoRegiao = colecaoRegiao;
		this.colecaoMicrorregiao = colecaoMicrorregiao;
		this.colecaoMunicipio = colecaoMunicipio;
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm() {
		super();
		
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String[] getColecaoMicrorregiao() {
		return colecaoMicrorregiao;
	}
	public void setColecaoMicrorregiao(String[] colecaoMicrorregiao) {
		this.colecaoMicrorregiao = colecaoMicrorregiao;
	}
	public String[] getColecaoMunicipio() {
		return colecaoMunicipio;
	}
	public void setColecaoMunicipio(String[] colecaoMunicipio) {
		this.colecaoMunicipio = colecaoMunicipio;
	}
	public String[] getColecaoRegiao() {
		return colecaoRegiao;
	}
	public void setColecaoRegiao(String[] colecaoRegiao) {
		this.colecaoRegiao = colecaoRegiao;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}
	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}
	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}
	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}
	
}
