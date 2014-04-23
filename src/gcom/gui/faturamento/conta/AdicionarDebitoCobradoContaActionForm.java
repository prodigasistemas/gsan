package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class AdicionarDebitoCobradoContaActionForm extends ActionForm {
  
	private static final long serialVersionUID = 1L;

	private String debitoTipoID;
  private String mesAnoCobranca;
  private String mesAnoDebito;
  private String valorDebito;
  private String imovelID;
  
  public String getImovelID() {
	return imovelID;
  }
  public void setImovelID(String imovelID) {
	this.imovelID = imovelID;
  }
  public String getDebitoTipoID() {
    return debitoTipoID;
  }
  public void setDebitoTipoID(String debitoTipoID) {
    this.debitoTipoID = debitoTipoID;
  }
  public String getMesAnoCobranca() {
    return mesAnoCobranca;
  }
  public void setMesAnoCobranca(String mesAnoCobranca) {
    this.mesAnoCobranca = mesAnoCobranca;
  }
  public String getMesAnoDebito() {
    return mesAnoDebito;
  }
  public void setMesAnoDebito(String mesAnoDebito) {
    this.mesAnoDebito = mesAnoDebito;
  }
  public String getValorDebito() {
    return valorDebito;
  }
  public void setValorDebito(String valorDebito) {
    this.valorDebito = valorDebito;
  }
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
}
