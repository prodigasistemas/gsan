package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class ManterContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;	
  private String conta;
  private String idImovel;
  private String inscricaoImovel;
  private String nomeClienteUsuario;
  private String situacaoAguaImovel;
  private String situacaoEsgotoImovel;
  
  private String  idRA;
  
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
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
	public String getIdRA() {
		return idRA;
	}
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}
  
  
}
