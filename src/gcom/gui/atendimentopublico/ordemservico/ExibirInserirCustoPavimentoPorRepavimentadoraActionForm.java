/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.action.ActionForm;

/**
 * [UC1106] Inserir Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 20/12/2010
 */

public class ExibirInserirCustoPavimentoPorRepavimentadoraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeRepavimentadora; 
	
	// Dados Pavimento Rua
	private String idTipoPavimentoRua; 
	private String valorPavimentoRua;
	private String dataVigenciaInicialPavimentoRua;
	private String dataVigenciaFinalPavimentoRua;
	
	// Dados Pavimento Calçada
	private String idTipoPavimentoCalcada; 
	private String valorPavimentoCalcada;
	private String dataVigenciaInicialPavimentoCalcada;
	private String dataVigenciaFinalPavimentoCalcada;

	public void reset(){

		this.idUnidadeRepavimentadora = null;
		this.idTipoPavimentoRua = null;
		this.valorPavimentoRua = null;
		this.dataVigenciaInicialPavimentoRua = null;
		this.dataVigenciaFinalPavimentoRua = null;
		this.idTipoPavimentoCalcada = null;
		this.valorPavimentoCalcada = null;
		this.dataVigenciaInicialPavimentoCalcada = null;
		this.dataVigenciaFinalPavimentoCalcada = null;
	}

	public String getDataVigenciaFinalPavimentoCalcada() {
		return dataVigenciaFinalPavimentoCalcada;
	}

	public void setDataVigenciaFinalPavimentoCalcada(
			String dataVigenciaFinalPavimentoCalcada) {
		this.dataVigenciaFinalPavimentoCalcada = dataVigenciaFinalPavimentoCalcada;
	}

	public String getDataVigenciaFinalPavimentoRua() {
		return dataVigenciaFinalPavimentoRua;
	}

	public void setDataVigenciaFinalPavimentoRua(
			String dataVigenciaFinalPavimentoRua) {
		this.dataVigenciaFinalPavimentoRua = dataVigenciaFinalPavimentoRua;
	}

	public String getDataVigenciaInicialPavimentoCalcada() {
		return dataVigenciaInicialPavimentoCalcada;
	}

	public void setDataVigenciaInicialPavimentoCalcada(
			String dataVigenciaInicialPavimentoCalcada) {
		this.dataVigenciaInicialPavimentoCalcada = dataVigenciaInicialPavimentoCalcada;
	}

	public String getDataVigenciaInicialPavimentoRua() {
		return dataVigenciaInicialPavimentoRua;
	}

	public void setDataVigenciaInicialPavimentoRua(
			String dataVigenciaInicialPavimentoRua) {
		this.dataVigenciaInicialPavimentoRua = dataVigenciaInicialPavimentoRua;
	}

	public String getIdTipoPavimentoCalcada() {
		return idTipoPavimentoCalcada;
	}

	public void setIdTipoPavimentoCalcada(String idTipoPavimentoCalcada) {
		this.idTipoPavimentoCalcada = idTipoPavimentoCalcada;
	}

	public String getIdTipoPavimentoRua() {
		return idTipoPavimentoRua;
	}

	public void setIdTipoPavimentoRua(String idTipoPavimentoRua) {
		this.idTipoPavimentoRua = idTipoPavimentoRua;
	}

	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}

	public String getValorPavimentoCalcada() {
		return valorPavimentoCalcada;
	}

	public void setValorPavimentoCalcada(String valorPavimentoCalcada) {
		this.valorPavimentoCalcada = valorPavimentoCalcada;
	}

	public String getValorPavimentoRua() {
		return valorPavimentoRua;
	}

	public void setValorPavimentoRua(String valorPavimentoRua) {
		this.valorPavimentoRua = valorPavimentoRua;
	}

}