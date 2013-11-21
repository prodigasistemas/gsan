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
package gcom.gui.cadastro.localidade;

import org.apache.struts.action.ActionForm;

public class AdicionarQuadraFaceActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String acao;
	
	private String numeroFace;
	
	private String baciaID;

    private String distritoOperacionalDescricao;

    private String distritoOperacionalID;

    private String indicadorRedeAguaAux;

    private String indicadorRedeEsgotoAux;
    
    private String sistemaEsgotoID;
    
    private String grauDificuldadeExecucaoID;
    
    private String grauRiscoSegurancaFisicaID;
    
    private String nivelPressaoID;
    
    private String grauIntermitenciaID;

    public String getBaciaID() {
		return baciaID;
	}

	public void setBaciaID(String baciaID) {
		this.baciaID = baciaID;
	}

	public String getDistritoOperacionalDescricao() {
		return distritoOperacionalDescricao;
	}

	public void setDistritoOperacionalDescricao(String distritoOperacionalDescricao) {
		this.distritoOperacionalDescricao = distritoOperacionalDescricao;
	}

	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}

	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
	}

	public String getIndicadorRedeAguaAux() {
		return indicadorRedeAguaAux;
	}

	public void setIndicadorRedeAguaAux(String indicadorRedeAguaAux) {
		this.indicadorRedeAguaAux = indicadorRedeAguaAux;
	}

	public String getIndicadorRedeEsgotoAux() {
		return indicadorRedeEsgotoAux;
	}

	public void setIndicadorRedeEsgotoAux(String indicadorRedeEsgotoAux) {
		this.indicadorRedeEsgotoAux = indicadorRedeEsgotoAux;
	}

	public String getSistemaEsgotoID() {
		return sistemaEsgotoID;
	}

	public void setSistemaEsgotoID(String sistemaEsgotoID) {
		this.sistemaEsgotoID = sistemaEsgotoID;
	}

	public String getNumeroFace() {
		return numeroFace;
	}

	public void setNumeroFace(String numeroFace) {
		this.numeroFace = numeroFace;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getGrauDificuldadeExecucaoID() {
		return grauDificuldadeExecucaoID;
	}

	public void setGrauDificuldadeExecucaoID(String grauDificuldadeExecucaoID) {
		this.grauDificuldadeExecucaoID = grauDificuldadeExecucaoID;
	}

	public String getGrauIntermitenciaID() {
		return grauIntermitenciaID;
	}

	public void setGrauIntermitenciaID(String grauIntermitenciaID) {
		this.grauIntermitenciaID = grauIntermitenciaID;
	}

	public String getGrauRiscoSegurancaFisicaID() {
		return grauRiscoSegurancaFisicaID;
	}

	public void setGrauRiscoSegurancaFisicaID(String grauRiscoSegurancaFisicaID) {
		this.grauRiscoSegurancaFisicaID = grauRiscoSegurancaFisicaID;
	}

	public String getNivelPressaoID() {
		return nivelPressaoID;
	}

	public void setNivelPressaoID(String nivelPressaoID) {
		this.nivelPressaoID = nivelPressaoID;
	}
	
}
