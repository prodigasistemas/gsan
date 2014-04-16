package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 * @date 12/01/2007
 */
public class ConjuntoTramitacaoRaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idUnidadeDestino;
	private String descricaoUnidadeDestino;
	private String idUsuarioResponsavel;
	private String descricaoUsuarioResponsavel;
	private String dataTramitacao;
	private String horaTramitacao;
	private String parecer;
    private String[] idRegistrosTramitacao;	
    
    
    private String idOrdemServico;
    private String descricaoTipoServico;
    private String idPavimentoRua;
    private String metragemPavimentoRua;
    private String idPavimentoCalcada;
    private String metragemPavimentoCalcada;
    
	/**
	 * @return Retorna o campo dataTramitacao.
	 */
	public String getDataTramitacao() {
		return dataTramitacao;
	}
	/**
	 * @param dataTramitacao O dataTramitacao a ser setado.
	 */
	public void setDataTramitacao(String dataTramitacao) {
		this.dataTramitacao = dataTramitacao;
	}
	/**
	 * @return Retorna o campo descricaoUnidadeDestino.
	 */
	public String getDescricaoUnidadeDestino() {
		return descricaoUnidadeDestino;
	}
	/**
	 * @param descricaoUnidadeDestino O descricaoUnidadeDestino a ser setado.
	 */
	public void setDescricaoUnidadeDestino(String descricaoUnidadeDestino) {
		this.descricaoUnidadeDestino = descricaoUnidadeDestino;
	}
	/**
	 * @return Retorna o campo descricaoUsuarioResponsavel.
	 */
	public String getDescricaoUsuarioResponsavel() {
		return descricaoUsuarioResponsavel;
	}
	/**
	 * @param descricaoUsuarioResponsavel O descricaoUsuarioResponsavel a ser setado.
	 */
	public void setDescricaoUsuarioResponsavel(String descricaoUsuarioResponsavel) {
		this.descricaoUsuarioResponsavel = descricaoUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo horaTramitacao.
	 */
	public String getHoraTramitacao() {
		return horaTramitacao;
	}
	/**
	 * @param horaTramitacao O horaTramitacao a ser setado.
	 */
	public void setHoraTramitacao(String horaTramitacao) {
		this.horaTramitacao = horaTramitacao;
	}
	/**
	 * @return Retorna o campo idUnidadeDestino.
	 */
	public String getIdUnidadeDestino() {
		return idUnidadeDestino;
	}
	/**
	 * @param idUnidadeDestino O idUnidadeDestino a ser setado.
	 */
	public void setIdUnidadeDestino(String idUnidadeDestino) {
		this.idUnidadeDestino = idUnidadeDestino;
	}
	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}
	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo parecer.
	 */
	public String getParecer() {
		return parecer;
	}
	/**
	 * @param parecer O parecer a ser setado.
	 */
	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	/**
	 * @return Retorna o campo idRegistrosTramitacao.
	 */
	public String[] getIdRegistrosTramitacao() {
		return idRegistrosTramitacao;
	}
	/**
	 * @param idRegistrosTramitacao O idRegistrosTramitacao a ser setado.
	 */
	public void setIdRegistrosTramitacao(String[] idRegistrosTramitacao) {
		this.idRegistrosTramitacao = idRegistrosTramitacao;
	}
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	public String getMetragemPavimentoCalcada() {
		return metragemPavimentoCalcada;
	}
	public void setMetragemPavimentoCalcada(String metragemPavimentoCalcada) {
		this.metragemPavimentoCalcada = metragemPavimentoCalcada;
	}
	public String getMetragemPavimentoRua() {
		return metragemPavimentoRua;
	}
	public void setMetragemPavimentoRua(String metragemPavimentoRua) {
		this.metragemPavimentoRua = metragemPavimentoRua;
	}
		

}
