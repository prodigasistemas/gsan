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
package gcom.gerencial.bean;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio para quadro de metas por Exercicio
 *
 * @author Bruno Barros
 * @date 07/03/2008
 */
public class FiltrarRelatorioQuadroMetasExercicioHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer opcaoTotalizacao;
	
	private Integer anoExercicio;
	private Integer localidade;
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;

	private String groupBy = "";

	
	public Integer getAnoExercicio() {
	
		return anoExercicio;
	}

	
	public void setAnoExercicio(Integer anoExercicio) {
	
		this.anoExercicio = anoExercicio;
	}

	
	public Integer getGerenciaRegional() {
	
		return gerenciaRegional;
	}

	
	public void setGerenciaRegional(Integer gerenciaRegional) {
	
		this.gerenciaRegional = gerenciaRegional;
	}

	
	public String getGroupBy() {
	
		return groupBy;
	}

	
	public void setGroupBy(String groupBy) {
	
		this.groupBy = groupBy;
	}

	
	public Integer getLocalidade() {
	
		return localidade;
	}

	
	public void setLocalidade(Integer localidade) {
	
		this.localidade = localidade;
	}

	
	public Integer getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}

	
	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
	
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	
	public Integer getUnidadeNegocio() {
	
		return unidadeNegocio;
	}

	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
	
		this.unidadeNegocio = unidadeNegocio;
	}
	
}