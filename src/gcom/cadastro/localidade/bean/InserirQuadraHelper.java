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
package gcom.cadastro.localidade.bean;

import gcom.micromedicao.FiltroRota;
import gcom.util.filtro.ParametroSimples;

public class InserirQuadraHelper {

	private String localidadeID; 
	private String setorComercialCD; 
	private String quadraNM; 
	private String perfilQuadraID; 
	private String areaTipoID; 
	private String indicadorRedeAgua; 
	private String indicadorRedeEsgoto; 
	private String sistemaEsgotoID;
	private String baciaID;
	private String distritoOperacionalID;
	private String setorCensitarioID;
	private String zeisID;
	private String rotaCD;
	/*
     * TODO - COSANPA - Mantis 536 - Felipe Santos - 08/03/2012
     * 
     * Adição do id da rota no helper
     */
	private String rotaID;
    // fim da alteração	
	private String indicadorIncrementoLote;
	private String bairroCD;
	private String municipioID;
	
	private String quadraId;
	private String indicadorUso;
	
	public InserirQuadraHelper(){}
	
	public String getAreaTipoID() {
		return areaTipoID;
	}
	public void setAreaTipoID(String areaTipoID) {
		this.areaTipoID = areaTipoID;
	}
	public String getBaciaID() {
		return baciaID;
	}
	public void setBaciaID(String baciaID) {
		this.baciaID = baciaID;
	}
	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}
	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
	}
	public String getIndicadorIncrementoLote() {
		return indicadorIncrementoLote;
	}
	public void setIndicadorIncrementoLote(String indicadorIncrementoLote) {
		this.indicadorIncrementoLote = indicadorIncrementoLote;
	}
	public String getIndicadorRedeAgua() {
		return indicadorRedeAgua;
	}
	public void setIndicadorRedeAgua(String indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}
	public String getIndicadorRedeEsgoto() {
		return indicadorRedeEsgoto;
	}
	public void setIndicadorRedeEsgoto(String indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}
	public String getLocalidadeID() {
		return localidadeID;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public String getPerfilQuadraID() {
		return perfilQuadraID;
	}
	public void setPerfilQuadraID(String perfilQuadraID) {
		this.perfilQuadraID = perfilQuadraID;
	}
	public String getQuadraNM() {
		return quadraNM;
	}
	public void setQuadraNM(String quadraNM) {
		this.quadraNM = quadraNM;
	}
	public String getRotaCD() {
		return rotaCD;
	}
	public void setRotaCD(String rotaCD) {
		this.rotaCD = rotaCD;
	}
	public String getRotaID() {
		return rotaID;
	}
	public void setRotaID(String rotaID) {
		this.rotaID = rotaID;
	}
	public String getSetorCensitarioID() {
		return setorCensitarioID;
	}
	public void setSetorCensitarioID(String setorCensitarioID) {
		this.setorCensitarioID = setorCensitarioID;
	}
	public String getSetorComercialCD() {
		return setorComercialCD;
	}
	public void setSetorComercialCD(String setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}
	public String getSistemaEsgotoID() {
		return sistemaEsgotoID;
	}
	public void setSistemaEsgotoID(String sistemaEsgotoID) {
		this.sistemaEsgotoID = sistemaEsgotoID;
	}
	public String getZeisID() {
		return zeisID;
	}
	public void setZeisID(String zeisID) {
		this.zeisID = zeisID;
	}

	public String getQuadraId() {
		return quadraId;
	}

	public void setQuadraId(String quadraId) {
		this.quadraId = quadraId;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getBairroCD() {
		return bairroCD;
	}

	public void setBairroCD(String bairroCD) {
		this.bairroCD = bairroCD;
	}

	public String getMunicipioID() {
		return municipioID;
	}

	public void setMunicipioID(String municipioID) {
		this.municipioID = municipioID;
	}

}
