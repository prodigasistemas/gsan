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
package gcom.gui.seguranca.acesso;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirManterPesquisaOperacaoActionForm extends ValidatorActionForm {
    private String codigoOperacao;

    private String nomeOperacao;
    private static final long serialVersionUID = 1L;
    private String tipoOperacao;

    private String idFuncionalidade;
    
    private String descricaoFuncionalidade;
  
    public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}




	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}




	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	codigoOperacao = null;
    	nomeOperacao = null;
    	tipoOperacao = null;
    	idFuncionalidade = null;

    }




	/**
	 * @return Retorna o campo codigoOperacao.
	 */
	public String getCodigoOperacao() {
		return codigoOperacao;
	}




	/**
	 * @param codigoOperacao O codigoOperacao a ser setado.
	 */
	public void setCodigoOperacao(String codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
	}


	/**
	 * @return Retorna o campo idfuncionalidade.
	 */
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}




	/**
	 * @param idfuncionalidade O idfuncionalidade a ser setado.
	 */
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}



	/**
	 * @return Retorna o campo nomeOperacao.
	 */
	public String getNomeOperacao() {
		return nomeOperacao;
	}




	/**
	 * @param nomeOperacao O nomeOperacao a ser setado.
	 */
	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}




	/**
	 * @return Retorna o campo tipoOperacao.
	 */
	public String getTipoOperacao() {
		return tipoOperacao;
	}




	/**
	 * @param tipoOperacao O tipoOperacao a ser setado.
	 */
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}




}
