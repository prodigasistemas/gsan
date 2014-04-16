package gcom.gui.cobranca;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	05/09/2008
 */

public class AtualizarCobrancaSituacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;	
	private String contaMotivoRevisao;
	private Short indicadorExigenciaAdvogado;
	private Short indicadorBloqueioParcelamento;
	private Short indicadorUso;
	private Short indicadorBloqueioRetirada;
	private String profissao;
	private String ramoAtividade;
	private Short indicadorBloqueioInclusao;
	private Short indicadorSelecaoApenasComPermissao;
	private Integer indicadorPrescricaoImoveisParticulares;
	private Integer indicadorNaoIncluirImoveisEmCobrancaResultado;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}
	public void setContaMotivoRevisao(String contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Short getIndicadorBloqueioParcelamento() {
		return indicadorBloqueioParcelamento;
	}
	public void setIndicadorBloqueioParcelamento(Short indicadorBloqueioParcelamento) {
		this.indicadorBloqueioParcelamento = indicadorBloqueioParcelamento;
	}
	public Short getIndicadorExigenciaAdvogado() {
		return indicadorExigenciaAdvogado;
	}
	public void setIndicadorExigenciaAdvogado(Short indicadorExigenciaAdvogado) {
		this.indicadorExigenciaAdvogado = indicadorExigenciaAdvogado;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Short getIndicadorBloqueioRetirada() {
		return indicadorBloqueioRetirada;
	}
	public void setIndicadorBloqueioRetirada(Short indicadorBloqueioRetirada) {
		this.indicadorBloqueioRetirada = indicadorBloqueioRetirada;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public Short getIndicadorBloqueioInclusao() {
		return indicadorBloqueioInclusao;
	}
	public void setIndicadorBloqueioInclusao(Short indicadorBloqueioInclusao) {
		this.indicadorBloqueioInclusao = indicadorBloqueioInclusao;
	}
	public Short getIndicadorSelecaoApenasComPermissao() {
		return indicadorSelecaoApenasComPermissao;
	}
	public void setIndicadorSelecaoApenasComPermissao(
			Short indicadorSelecaoApenasComPermissao) {
		this.indicadorSelecaoApenasComPermissao = indicadorSelecaoApenasComPermissao;
	}
	public Integer getIndicadorPrescricaoImoveisParticulares() {
		return indicadorPrescricaoImoveisParticulares;
	}
	public void setIndicadorPrescricaoImoveisParticulares(
			Integer indicadorPrescricaoImoveisParticulares) {
		this.indicadorPrescricaoImoveisParticulares = indicadorPrescricaoImoveisParticulares;
	}
	public Integer getIndicadorNaoIncluirImoveisEmCobrancaResultado() {
		return indicadorNaoIncluirImoveisEmCobrancaResultado;
	}
	public void setIndicadorNaoIncluirImoveisEmCobrancaResultado(
			Integer indicadorNaoIncluirImoveisEmCobrancaResultado) {
		this.indicadorNaoIncluirImoveisEmCobrancaResultado = indicadorNaoIncluirImoveisEmCobrancaResultado;
	}
		
}
