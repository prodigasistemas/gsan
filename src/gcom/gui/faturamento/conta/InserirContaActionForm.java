package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class InserirContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
  private String consumoAgua;
  private String consumoEsgoto;
  private String idImovel;
  private String inscricaoImovel;
  private String mesAnoConta;
  private String motivoInclusaoID;
  private String nomeClienteUsuario;
  private String percentualEsgoto;
  private String situacaoAguaConta;
  private String situacaoAguaImovel;
  private String situacaoEsgotoConta;
  private String situacaoEsgotoImovel;
  private String valorAgua;
  private String valorEsgoto;
  private String vencimentoConta;
  private String valorDebito;
  private String valorTotal;
  
  private String leituraAnteriorAgua;
  private String leituraAtualAgua;
  
  public String getValorDebito() {
	return valorDebito;
}
public void setValorDebito(String valorDebito) {
	this.valorDebito = valorDebito;
}
public String getValorTotal() {
	return valorTotal;
}
public void setValorTotal(String valorTotal) {
	this.valorTotal = valorTotal;
}
public String getConsumoAgua() {
    return consumoAgua;
  }
  public void setConsumoAgua(String consumoAgua) {
    this.consumoAgua = consumoAgua;
  }
  public String getConsumoEsgoto() {
    return consumoEsgoto;
  }
  public void setConsumoEsgoto(String consumoEsgoto) {
    this.consumoEsgoto = consumoEsgoto;
  }
  public String getIdImovel() {
    return idImovel;
  }
  public void setIdImovel(String idImovel) {
    this.idImovel = idImovel;
  }
  public String getMesAnoConta() {
    return mesAnoConta;
  }
  public void setMesAnoConta(String mesAnoConta) {
    this.mesAnoConta = mesAnoConta;
  }
  public String getMotivoInclusaoID() {
    return motivoInclusaoID;
  }
  public void setMotivoInclusaoID(String motivoInclusaoID) {
    this.motivoInclusaoID = motivoInclusaoID;
  }
  public String getNomeClienteUsuario() {
    return nomeClienteUsuario;
  }
  public void setNomeClienteUsuario(String nomeClienteUsuario) {
    this.nomeClienteUsuario = nomeClienteUsuario;
  }
  public String getPercentualEsgoto() {
    return percentualEsgoto;
  }
  public void setPercentualEsgoto(String percentualEsgoto) {
    this.percentualEsgoto = percentualEsgoto;
  }
  public String getSituacaoAguaConta() {
    return situacaoAguaConta;
  }
  public void setSituacaoAguaConta(String situacaoAguaConta) {
    this.situacaoAguaConta = situacaoAguaConta;
  }
  public String getSituacaoAguaImovel() {
    return situacaoAguaImovel;
  }
  public void setSituacaoAguaImovel(String situacaoAguaImovel) {
    this.situacaoAguaImovel = situacaoAguaImovel;
  }
  public String getSituacaoEsgotoConta() {
    return situacaoEsgotoConta;
  }
  public void setSituacaoEsgotoConta(String situacaoEsgotoConta) {
    this.situacaoEsgotoConta = situacaoEsgotoConta;
  }
  public String getSituacaoEsgotoImovel() {
    return situacaoEsgotoImovel;
  }
  public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
    this.situacaoEsgotoImovel = situacaoEsgotoImovel;
  }
  public String getValorAgua() {
    return valorAgua;
  }
  public void setValorAgua(String valorAgua) {
    this.valorAgua = valorAgua;
  }
  public String getValorEsgoto() {
    return valorEsgoto;
  }
  public void setValorEsgoto(String valorEsgoto) {
    this.valorEsgoto = valorEsgoto;
  }
  public String getVencimentoConta() {
    return vencimentoConta;
  }
  public void setVencimentoConta(String vencimentoConta) {
    this.vencimentoConta = vencimentoConta;
  }
  
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}
	public void setLeituraAnteriorAgua(String leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}
	public String getLeituraAtualAgua() {
		return leituraAtualAgua;
	}
	public void setLeituraAtualAgua(String leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}


}
