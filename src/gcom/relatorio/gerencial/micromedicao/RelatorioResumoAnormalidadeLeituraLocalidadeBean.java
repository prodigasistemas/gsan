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
package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoAnormalidadeLeituraLocalidadeBean implements
		RelatorioBean {

	private String localidade;

	private String setorComercial;

	private String tipoLigacao;

	private String ligacoes;

	private String percentual;

	private JRBeanCollectionDataSource arrayJRSetorComercial = null;

	private JRBeanCollectionDataSource arrayJRAnormalidade = null;

	private ArrayList arraySetorComercial = null;

	private ArrayList arrayAnormalidade = null;

	public RelatorioResumoAnormalidadeLeituraLocalidadeBean(
			String localidade, String setorComercial, String tipoLigacao,
			Collection colecaoSetorComercial, Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.tipoLigacao = tipoLigacao;
		this.arraySetorComercial = new ArrayList();
		this.arrayAnormalidade = new ArrayList();
		this.arraySetorComercial.addAll(colecaoSetorComercial);
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRSetorComercial = new JRBeanCollectionDataSource(arraySetorComercial);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}
	
	public RelatorioResumoAnormalidadeLeituraLocalidadeBean(
			String localidade, String setorComercial, String tipoLigacao,
			Collection colecaoAnormalidade) {

		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.tipoLigacao = tipoLigacao;
		this.arrayAnormalidade = new ArrayList();
		this.arrayAnormalidade.addAll(colecaoAnormalidade);
		this.arrayJRAnormalidade = new JRBeanCollectionDataSource(
				arrayAnormalidade);

	}

	public ArrayList getArrayAnormalidade() {
		return arrayAnormalidade;
	}

	public void setArrayAnormalidade(ArrayList arrayAnormalidade) {
		this.arrayAnormalidade = arrayAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRAnormalidade() {
		return arrayJRAnormalidade;
	}

	public void setArrayJRAnormalidade(
			JRBeanCollectionDataSource arrayJRAnormalidade) {
		this.arrayJRAnormalidade = arrayJRAnormalidade;
	}

	public JRBeanCollectionDataSource getArrayJRSetorComercial() {
		return arrayJRSetorComercial;
	}

	public void setArrayJRSetorComercial(
			JRBeanCollectionDataSource arrayJRSetorComercial) {
		this.arrayJRSetorComercial = arrayJRSetorComercial;
	}

	public ArrayList getArraySetorComercial() {
		return arraySetorComercial;
	}

	public void setArraySetorComercial(ArrayList arraySetorComercial) {
		this.arraySetorComercial = arraySetorComercial;
	}

	public String getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(String ligacoes) {
		this.ligacoes = ligacoes;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getPercentual() {
		return percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

}
