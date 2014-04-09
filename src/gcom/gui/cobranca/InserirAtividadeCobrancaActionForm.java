package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirAtividadeCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoAtividadeCobranca;
	private String ordemCronograma;
	private String idAtividadePredecessora;
	private String idProcessoAssociado;
	private String descricaoProcessoAssociado;
	private String opcaoCompoeCronograda;
	private String opcaoAtividadeObrigatoria;
	private String opcaoPodeSerComandada;
	private String opcaoPodeSerExecutada;
	private String quantidadeDiasExecucao;
	private String cobrancaAcao;
	

	public String getIdAtividadePredecessora() {
		return idAtividadePredecessora;
	}
	public void setIdAtividadePredecessora(String idAtividadePredecessora) {
		this.idAtividadePredecessora = idAtividadePredecessora;
	}
	public String getDescricaoAtividadeCobranca() {
		return descricaoAtividadeCobranca;
	}
	public void setDescricaoAtividadeCobranca(String descricaoAtividadeCobranca) {
		this.descricaoAtividadeCobranca = descricaoAtividadeCobranca;
	}
	public String getDescricaoProcessoAssociado() {
		return descricaoProcessoAssociado;
	}
	public void setDescricaoProcessoAssociado(String descricaoProcessoAssociado) {
		this.descricaoProcessoAssociado = descricaoProcessoAssociado;
	}
	public String getIdProcessoAssociado() {
		return idProcessoAssociado;
	}
	public void setIdProcessoAssociado(String idProcessoAssociado) {
		this.idProcessoAssociado = idProcessoAssociado;
	}
	public String getOpcaoAtividadeObrigatoria() {
		return opcaoAtividadeObrigatoria;
	}
	public void setOpcaoAtividadeObrigatoria(String opcaoAtividadeObrigatoria) {
		this.opcaoAtividadeObrigatoria = opcaoAtividadeObrigatoria;
	}
	public String getOpcaoCompoeCronograda() {
		return opcaoCompoeCronograda;
	}
	public void setOpcaoCompoeCronograda(String opcaoCompoeCronograda) {
		this.opcaoCompoeCronograda = opcaoCompoeCronograda;
	}
	public String getOpcaoPodeSerComandada() {
		return opcaoPodeSerComandada;
	}
	public void setOpcaoPodeSerComandada(String opcaoPodeSerComandada) {
		this.opcaoPodeSerComandada = opcaoPodeSerComandada;
	}
	public String getOpcaoPodeSerExecutada() {
		return opcaoPodeSerExecutada;
	}
	public void setOpcaoPodeSerExecutada(String opcaoPodeSerExecutada) {
		this.opcaoPodeSerExecutada = opcaoPodeSerExecutada;
	}
	public String getOrdemCronograma() {
		return ordemCronograma;
	}
	public void setOrdemCronograma(String ordemCronograma) {
		this.ordemCronograma = ordemCronograma;
	}
	public String getQuantidadeDiasExecucao() {
		return quantidadeDiasExecucao;
	}
	public void setQuantidadeDiasExecucao(String quantidadeDiasExecucao) {
		this.quantidadeDiasExecucao = quantidadeDiasExecucao;
	}
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}
	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	

}
