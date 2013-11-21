/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
