package gcom.gui.atendimentopublico;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	25/08/2008
 */

public class AtualizarLigacaoEsgotoEsgotamentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;	
	private String quantidadeMesesSituacaoEspecial;
	private String faturamentoSituacaoTipo;
	private String faturamentoSituacaoMotivo;
	private Short indicadorUso;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getQuantidadeMesesSituacaoEspecial() {
		return quantidadeMesesSituacaoEspecial;
	}
	public void setQuantidadeMesesSituacaoEspecial(
			String quantidadeMesesSituacaoEspecial) {
		this.quantidadeMesesSituacaoEspecial = quantidadeMesesSituacaoEspecial;
	}
	public String getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}
	public void setFaturamentoSituacaoMotivo(String faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}
	public String getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}
	public void setFaturamentoSituacaoTipo(String faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}
	 
	
  
		
}
