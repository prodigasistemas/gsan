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
* Thiago Silva Toscano de Brito
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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da inserção de um Geração da Movimentacao da Negativacao
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class GerarMovimentoExclusaoNegativacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String opcao;
	private String[] negativadores;
	private Collection collNegativadores;

	private String codigoMovimento;
	private String descricaoMovimento;
	private Collection collMovimento;
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author thiago
	 * @date 19/12/2007
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		
		opcao = "";
		negativadores = new String[0];
		collNegativadores = new ArrayList();

		codigoMovimento = "";
		descricaoMovimento = "";		
		collMovimento= new ArrayList();

	}
	
	/**
	 * @return Retorna o campo collMovimento.
	 */
	public Collection getCollMovimento() {
		return collMovimento;
	}

	/**
	 * @param collMovimento O collMovimento a ser setado.
	 */
	public void setCollMovimento(Collection collMovimento) {
		this.collMovimento = collMovimento;
	}


	/**
	 * @return Retorna o campo codigoMovimento.
	 */
	public String getCodigoMovimento() {
		return codigoMovimento;
	}
	/**
	 * @param codigoMovimento O codigoMovimento a ser setado.
	 */
	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}
	/**
	 * @return Retorna o campo collNegativadores.
	 */
	public Collection getCollNegativadores() {
		return collNegativadores;
	}
	/**
	 * @param collNegativadores O collNegativadores a ser setado.
	 */
	public void setCollNegativadores(Collection collNegativadores) {
		this.collNegativadores = collNegativadores;
	}
	/**
	 * @return Retorna o campo descricaoMovimento.
	 */
	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}
	/**
	 * @param descricaoMovimento O descricaoMovimento a ser setado.
	 */
	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}
	/**
	 * @return Retorna o campo negativadores.
	 */
	public String[] getNegativadores() {
		return negativadores;
	}
	/**
	 * @param negativadores O negativadores a ser setado.
	 */
	public void setNegativadores(String[] negativadores) {
		this.negativadores = negativadores;
	}
	/**
	 * @return Retorna o campo opcao.
	 */
	public String getOpcao() {
		return opcao;
	}
	/**
	 * @param opcao O opcao a ser setado.
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}