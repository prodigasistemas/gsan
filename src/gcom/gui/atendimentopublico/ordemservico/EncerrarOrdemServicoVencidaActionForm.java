package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0734] Encerrar Ordem de Servico Vencida
 * 
 * @author Ivan Sérgio
 *
 * @date 11/01/2008
 */
public class EncerrarOrdemServicoVencidaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipoOrdem;
	private String quantidadeDias;
	private String origemOrdemServico;
	
	public static final String TIPO_ORDEM_INSTALACAO = "INSTALACAO";
	public static final String TIPO_ORDEM_SUBSTITUICAO = "SUBSTITUICAO";
	public static final String TIPO_ORDEM_CORTE = "CORTE";

	public String getQuantidadeDias() {
		return quantidadeDias;
	}
	public void setQuantidadeDias(String quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}
	public String getTipoOrdem() {
		return tipoOrdem;
	}
	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}
	public String getOrigemOrdemServico() {
		return origemOrdemServico;
	}
	public void setOrigemOrdemServico(String origemOrigemServico) {
		this.origemOrdemServico = origemOrdemServico;
	}
}
