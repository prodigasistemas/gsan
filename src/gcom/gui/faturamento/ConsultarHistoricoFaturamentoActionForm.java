package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class ConsultarHistoricoFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String conta;
  private String idImovel;
  private String descricaoImovel;
  private String inscricaoImovel;
  private String nomeClienteUsuario;
  private String situacaoAguaImovel;
  private String situacaoEsgotoImovel;
  
  public String getConta() {
    return conta;
  }
  public void setConta(String conta) {
    this.conta = conta;
  }
  public String getIdImovel() {
    return idImovel;
  }
  public void setIdImovel(String idImovel) {
    this.idImovel = idImovel;
  }
  public String getInscricaoImovel() {
    return inscricaoImovel;
  }
  public void setInscricaoImovel(String inscricaoImovel) {
    this.inscricaoImovel = inscricaoImovel;
  }
  public String getNomeClienteUsuario() {
    return nomeClienteUsuario;
  }
  public void setNomeClienteUsuario(String nomeClienteUsuario) {
    this.nomeClienteUsuario = nomeClienteUsuario;
  }
  public String getSituacaoAguaImovel() {
    return situacaoAguaImovel;
  }
  public void setSituacaoAguaImovel(String situacaoAguaImovel) {
    this.situacaoAguaImovel = situacaoAguaImovel;
  }
  public String getSituacaoEsgotoImovel() {
    return situacaoEsgotoImovel;
  }
  public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
    this.situacaoEsgotoImovel = situacaoEsgotoImovel;
  }
public String getDescricaoImovel() {
	return descricaoImovel;
}
public void setDescricaoImovel(String descricaoImovel) {
	this.descricaoImovel = descricaoImovel;
}
   
}
