package gcom.gui.faturamento;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	21/08/2008
 */

public class AtualizarFaturamentoSituacaoTipoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	private String indicadorParalisacaoFaturamento;
	private String indicadorParalisacaoLeitura;
	private String indicadorValidoAgua;
	private String indicadorValidoEsgoto;
	private String indicadorInformarConsumo;
	private String indicadorInformarVolume;
	private String indicadorUso;
	private String leituraAnormalidadeLeituraComLeitura;
	private String leituraAnormalidadeLeituraSemLeitura;
	private String leituraAnormalidadeConsumoComLeitura;
	private String leituraAnormalidadeConsumoSemLeitura;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}
	public void setIndicadorInformarConsumo(String indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}
	public String getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}
	public void setIndicadorInformarVolume(String indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}
	public String getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}
	public void setIndicadorParalisacaoFaturamento(
			String indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}
	public String getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}
	public void setIndicadorParalisacaoLeitura(String indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}
	public void setIndicadorValidoAgua(String indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}
	public String getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}
	public void setIndicadorValidoEsgoto(String indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}
	public String getLeituraAnormalidadeConsumoComLeitura() {
		return leituraAnormalidadeConsumoComLeitura;
	}
	public void setLeituraAnormalidadeConsumoComLeitura(
			String leituraAnormalidadeConsumoComLeitura) {
		this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
	}
	public String getLeituraAnormalidadeConsumoSemLeitura() {
		return leituraAnormalidadeConsumoSemLeitura;
	}
	public void setLeituraAnormalidadeConsumoSemLeitura(
			String leituraAnormalidadeConsumoSemLeitura) {
		this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
	}
	public String getLeituraAnormalidadeLeituraComLeitura() {
		return leituraAnormalidadeLeituraComLeitura;
	}
	public void setLeituraAnormalidadeLeituraComLeitura(
			String leituraAnormalidadeLeituraComLeitura) {
		this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
	}
	public String getLeituraAnormalidadeLeituraSemLeitura() {
		return leituraAnormalidadeLeituraSemLeitura;
	}
	public void setLeituraAnormalidadeLeituraSemLeitura(
			String leituraAnormalidadeLeituraSemLeitura) {
		this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
	}
		
}
