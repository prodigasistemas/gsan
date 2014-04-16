package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class AdicionarCategoriaContaActionForm extends ActionForm {
  
	private static final long serialVersionUID = 1L;

  private String categoriaID;
  private String subcategoriaID;
  private String qtdEconomia;
  
  public String getCategoriaID() {
    return categoriaID;
  }
  public void setCategoriaID(String categoriaID) {
    this.categoriaID = categoriaID;
  }
  public String getQtdEconomia() {
    return qtdEconomia;
  }
  public void setQtdEconomia(String qtdEconomia) {
    this.qtdEconomia = qtdEconomia;
  }
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  
  public String getSubcategoriaID() {
	return subcategoriaID;
  }
  public void setSubcategoriaID(String subcategoriaID) {
	this.subcategoriaID = subcategoriaID;
  }
  
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {}
}
