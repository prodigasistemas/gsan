package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class AdicionarCreditoRealizadoContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

  private String creditoTipoID;
  private String mesAnoCobranca;
  private String mesAnoCredito;
  private String valorCredito;
  private String imovelID;
  private String creditoOrigemID;
  
  public String getImovelID() {
	return imovelID;
  }
  public void setImovelID(String imovelID) {
	this.imovelID = imovelID;
  }
  public String getCreditoTipoID() {
    return creditoTipoID;
  }
  public void setCreditoTipoID(String creditoTipoID) {
    this.creditoTipoID = creditoTipoID;
  }
  public String getMesAnoCobranca() {
    return mesAnoCobranca;
  }
  public void setMesAnoCobranca(String mesAnoCobranca) {
    this.mesAnoCobranca = mesAnoCobranca;
  }
  public String getMesAnoCredito() {
    return mesAnoCredito;
  }
  public void setMesAnoCredito(String mesAnoCredito) {
    this.mesAnoCredito = mesAnoCredito;
  }
  public String getValorCredito() {
    return valorCredito;
  }
  public void setValorCredito(String valorCredito) {
    this.valorCredito = valorCredito;
  }
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
  public String getCreditoOrigemID() {
	return creditoOrigemID;
  }
  public void setCreditoOrigemID(String creditoOrigemID) {
	this.creditoOrigemID = creditoOrigemID;
  }
  
}
