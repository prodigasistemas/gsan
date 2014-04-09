package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class FiltrarConsumoAnormalidadeAcaoActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String consumoAnormalidade;	
	
	private String categoria;
	
	private String imovelPerfil;
	
    private String leituraAnormalidadeConsumoMes1;

    private String leituraAnormalidadeConsumoMes2;

    private String leituraAnormalidadeConsumoMes3;
	
    private String numerofatorConsumoMes1;

    private String numerofatorConsumoMes2;

    private String numerofatorConsumoMes3;

    private String indicadorGeracaoCartaMes1;

    private String indicadorGeracaoCartaMes2;

    private String indicadorGeracaoCartaMes3;

    private String idServicoTipoMes1;
    
    private String idServicoTipoMes2;
    
    private String idServicoTipoMes3;
    
    private String desServicoTipoMes1;

    private String desServicoTipoMes2;

    private String desServicoTipoMes3;
    
    private String solicitacaoTipoMes1;
    
    private String solicitacaoTipoMes2;
    
    private String solicitacaoTipoMes3;

    private String solicitacaoTipoEspecificacaoMes1;

    private String solicitacaoTipoEspecificacaoMes2;

    private String solicitacaoTipoEspecificacaoMes3;
    
    private String descricaoContaMensagemMes1;
    
    private String descricaoContaMensagemMes2;
    
    private String descricaoContaMensagemMes3;
    
    private String indicadorUso;
    
    private String tipoPesquisa;
    
    private String indicadorAtualizar;

	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getLeituraAnormalidadeConsumoMes1() {
		return leituraAnormalidadeConsumoMes1;
	}

	public void setLeituraAnormalidadeConsumoMes1(
			String leituraAnormalidadeConsumoMes1) {
		this.leituraAnormalidadeConsumoMes1 = leituraAnormalidadeConsumoMes1;
	}

	public String getLeituraAnormalidadeConsumoMes2() {
		return leituraAnormalidadeConsumoMes2;
	}

	public void setLeituraAnormalidadeConsumoMes2(
			String leituraAnormalidadeConsumoMes2) {
		this.leituraAnormalidadeConsumoMes2 = leituraAnormalidadeConsumoMes2;
	}

	public String getLeituraAnormalidadeConsumoMes3() {
		return leituraAnormalidadeConsumoMes3;
	}

	public void setLeituraAnormalidadeConsumoMes3(
			String leituraAnormalidadeConsumoMes3) {
		this.leituraAnormalidadeConsumoMes3 = leituraAnormalidadeConsumoMes3;
	}

	public String getNumerofatorConsumoMes1() {
		return numerofatorConsumoMes1;
	}

	public void setNumerofatorConsumoMes1(String numerofatorConsumoMes1) {
		this.numerofatorConsumoMes1 = numerofatorConsumoMes1;
	}

	public String getNumerofatorConsumoMes2() {
		return numerofatorConsumoMes2;
	}

	public void setNumerofatorConsumoMes2(String numerofatorConsumoMes2) {
		this.numerofatorConsumoMes2 = numerofatorConsumoMes2;
	}

	public String getNumerofatorConsumoMes3() {
		return numerofatorConsumoMes3;
	}

	public void setNumerofatorConsumoMes3(String numerofatorConsumoMes3) {
		this.numerofatorConsumoMes3 = numerofatorConsumoMes3;
	}

	public String getIndicadorGeracaoCartaMes1() {
		return indicadorGeracaoCartaMes1;
	}

	public void setIndicadorGeracaoCartaMes1(String indicadorGeracaoCartaMes1) {
		this.indicadorGeracaoCartaMes1 = indicadorGeracaoCartaMes1;
	}

	public String getIndicadorGeracaoCartaMes2() {
		return indicadorGeracaoCartaMes2;
	}

	public void setIndicadorGeracaoCartaMes2(String indicadorGeracaoCartaMes2) {
		this.indicadorGeracaoCartaMes2 = indicadorGeracaoCartaMes2;
	}

	public String getIndicadorGeracaoCartaMes3() {
		return indicadorGeracaoCartaMes3;
	}

	public void setIndicadorGeracaoCartaMes3(String indicadorGeracaoCartaMes3) {
		this.indicadorGeracaoCartaMes3 = indicadorGeracaoCartaMes3;
	}

	public String getIdServicoTipoMes1() {
		return idServicoTipoMes1;
	}

	public void setIdServicoTipoMes1(String idServicoTipoMes1) {
		this.idServicoTipoMes1 = idServicoTipoMes1;
	}

	public String getIdServicoTipoMes2() {
		return idServicoTipoMes2;
	}

	public void setIdServicoTipoMes2(String idServicoTipoMes2) {
		this.idServicoTipoMes2 = idServicoTipoMes2;
	}

	public String getIdServicoTipoMes3() {
		return idServicoTipoMes3;
	}

	public void setIdServicoTipoMes3(String idServicoTipoMes3) {
		this.idServicoTipoMes3 = idServicoTipoMes3;
	}

	public String getDesServicoTipoMes1() {
		return desServicoTipoMes1;
	}

	public void setDesServicoTipoMes1(String desServicoTipoMes1) {
		this.desServicoTipoMes1 = desServicoTipoMes1;
	}

	public String getDesServicoTipoMes2() {
		return desServicoTipoMes2;
	}

	public void setDesServicoTipoMes2(String desServicoTipoMes2) {
		this.desServicoTipoMes2 = desServicoTipoMes2;
	}

	public String getDesServicoTipoMes3() {
		return desServicoTipoMes3;
	}

	public void setDesServicoTipoMes3(String desServicoTipoMes3) {
		this.desServicoTipoMes3 = desServicoTipoMes3;
	}
	
	public String getSolicitacaoTipoMes1() {
		return solicitacaoTipoMes1;
	}

	public void setSolicitacaoTipoMes1(String solicitacaoTipoMes1) {
		this.solicitacaoTipoMes1 = solicitacaoTipoMes1;
	}

	public String getSolicitacaoTipoMes2() {
		return solicitacaoTipoMes2;
	}

	public void setSolicitacaoTipoMes2(String solicitacaoTipoMes2) {
		this.solicitacaoTipoMes2 = solicitacaoTipoMes2;
	}

	public String getSolicitacaoTipoMes3() {
		return solicitacaoTipoMes3;
	}

	public void setSolicitacaoTipoMes3(String solicitacaoTipoMes3) {
		this.solicitacaoTipoMes3 = solicitacaoTipoMes3;
	}

	public String getSolicitacaoTipoEspecificacaoMes1() {
		return solicitacaoTipoEspecificacaoMes1;
	}

	public void setSolicitacaoTipoEspecificacaoMes1(
			String solicitacaoTipoEspecificacaoMes1) {
		this.solicitacaoTipoEspecificacaoMes1 = solicitacaoTipoEspecificacaoMes1;
	}

	public String getSolicitacaoTipoEspecificacaoMes2() {
		return solicitacaoTipoEspecificacaoMes2;
	}

	public void setSolicitacaoTipoEspecificacaoMes2(
			String solicitacaoTipoEspecificacaoMes2) {
		this.solicitacaoTipoEspecificacaoMes2 = solicitacaoTipoEspecificacaoMes2;
	}

	public String getSolicitacaoTipoEspecificacaoMes3() {
		return solicitacaoTipoEspecificacaoMes3;
	}

	public void setSolicitacaoTipoEspecificacaoMes3(
			String solicitacaoTipoEspecificacaoMes3) {
		this.solicitacaoTipoEspecificacaoMes3 = solicitacaoTipoEspecificacaoMes3;
	}

	public String getDescricaoContaMensagemMes1() {
		return descricaoContaMensagemMes1;
	}

	public void setDescricaoContaMensagemMes1(String descricaoContaMensagemMes1) {
		this.descricaoContaMensagemMes1 = descricaoContaMensagemMes1;
	}

	public String getDescricaoContaMensagemMes2() {
		return descricaoContaMensagemMes2;
	}

	public void setDescricaoContaMensagemMes2(String descricaoContaMensagemMes2) {
		this.descricaoContaMensagemMes2 = descricaoContaMensagemMes2;
	}

	public String getDescricaoContaMensagemMes3() {
		return descricaoContaMensagemMes3;
	}

	public void setDescricaoContaMensagemMes3(String descricaoContaMensagemMes3) {
		this.descricaoContaMensagemMes3 = descricaoContaMensagemMes3;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	
	
}
