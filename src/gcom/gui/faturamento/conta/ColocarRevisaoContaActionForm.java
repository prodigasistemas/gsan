package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class ColocarRevisaoContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String contaSelected;
  private String motivoRevisaoContaID;
  public String getContaSelected() {
    return contaSelected;
  }
  public void setContaSelected(String contaSelected) {
    this.contaSelected = contaSelected;
  }
  public String getMotivoRevisaoContaID() {
    return motivoRevisaoContaID;
  }
  public void setMotivoRevisaoContaID(String motivoRevisaoContaID) {
    this.motivoRevisaoContaID = motivoRevisaoContaID;
  }
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
}
