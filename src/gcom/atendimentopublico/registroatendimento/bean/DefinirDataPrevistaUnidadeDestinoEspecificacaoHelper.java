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
package gcom.atendimentopublico.registroatendimento.bean;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Date;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0003] - Define Data Prevista e Unidade Destino da Especificação do caso de uso
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper {
	
	private Date dataPrevista;
	
	private UnidadeOrganizacional unidadeOrganizacional;
	
	private String imovelObrigatorio;
	
	private String pavimentoRuaObrigatorio;
	
	private String pavimentoCalcadaObrigatorio;
	
	private String indFaltaAgua;
	
	private String indMatricula;
	
	
	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper(){}

	
	
	public String getPavimentoCalcadaObrigatorio() {
		return pavimentoCalcadaObrigatorio;
	}

	public void setPavimentoCalcadaObrigatorio(String pavimentoCalcadaObrigatorio) {
		this.pavimentoCalcadaObrigatorio = pavimentoCalcadaObrigatorio;
	}

	public String getPavimentoRuaObrigatorio() {
		return pavimentoRuaObrigatorio;
	}

	public void setPavimentoRuaObrigatorio(String pavimentoRuaObrigatorio) {
		this.pavimentoRuaObrigatorio = pavimentoRuaObrigatorio;
	}

	public String getImovelObrigatorio() {
		return imovelObrigatorio;
	}

	public void setImovelObrigatorio(String imovelObrigatorio) {
		this.imovelObrigatorio = imovelObrigatorio;
	}



	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}



	public String getIndFaltaAgua() {
		return indFaltaAgua;
	}



	public void setIndFaltaAgua(String indFaltaAgua) {
		this.indFaltaAgua = indFaltaAgua;
	}



	public String getIndMatricula() {
		return indMatricula;
	}



	public void setIndMatricula(String indMatricula) {
		this.indMatricula = indMatricula;
	}
	
	

}
