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
package gcom.gui.atendimentopublico;

import gcom.cadastro.EnvioEmail;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para recuperar os dados da pagina de melhorias do gcom e
 * mandada para o e-mail
 * 
 * @author Sávio Luiz
 * @created 15 de Fevereiro de 2007
 */
public class InformarMelhoriasGcomAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();

		MelhoriasGcomActionForm melhoriasGcomActionForm = (MelhoriasGcomActionForm) actionForm;

		StringBuilder mensagemEmail = new StringBuilder();

		String descricaoAcesso = "ASSUNTO: ";
		if (melhoriasGcomActionForm.getIdAssunto() != null) {
			if (melhoriasGcomActionForm.getIdAssunto().equals("1")) {
				descricaoAcesso += "CADASTRO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("2")) {
				descricaoAcesso += "COBRANÇA";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("3")) {
				descricaoAcesso += "GERENCIAL";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("4")) {
				descricaoAcesso += "MICROMEDIÇÃO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("5")) {
				descricaoAcesso += "ARRECADAçÂO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("6")) {
				descricaoAcesso += "FATURAMENTO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("7")) {
				descricaoAcesso += "ATENDIMENTO";
			}
		}

		mensagemEmail.append(descricaoAcesso);
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append(System.getProperty("line.separator"));

		mensagemEmail.append("DADOS DO USUÁRIO:");
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("LOGIN: ");
		if (melhoriasGcomActionForm.getLoginUsuario() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getLoginUsuario().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NOME: ");
		if (melhoriasGcomActionForm.getNomeUsuario() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNomeUsuario().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("UNIDADE LOTAÇÃO: ");
		if (melhoriasGcomActionForm.getUnidadeLotacao() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getUnidadeLotacao().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("TELEFONE: ");
		if (melhoriasGcomActionForm.getTelefone() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getTelefone().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("EMAIL: ");
		if (melhoriasGcomActionForm.getEmail() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getEmail());
		}

		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append(System.getProperty("line.separator"));

		mensagemEmail.append("DADOS ESPECÍFICOS:");
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("MATRÍCULA DO IMÓVEL: ");
		if (melhoriasGcomActionForm.getMatirculaImovel() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getMatirculaImovel().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("CÓDIGO DO CLIENTE: ");
		if (melhoriasGcomActionForm.getCodigoCliente() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getCodigoCliente().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NÚMERO RA: ");
		if (melhoriasGcomActionForm.getNumeroRA() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNumeroRA().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NÙMERO OS: ");
		if (melhoriasGcomActionForm.getNumeroOS() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNumeroOS().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("DETALHAMENTO: ");
		if (melhoriasGcomActionForm.getDetalhamento() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getDetalhamento().toUpperCase());
		}
		
		EnvioEmail envioEmail = fachada.pesquisarEnvioEmail(EnvioEmail.ENTRE_EM_CONTATO);

		try {
			ServicosEmail.enviarMensagem(melhoriasGcomActionForm.getEmail(),
					envioEmail.getEmailReceptor(),envioEmail.getTituloMensagem(),
					mensagemEmail.toString());
		} catch (ErroEmailException e) {
			throw new ActionServletException("erro.envio.mensagem");
		}

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Mensagem enviada com sucesso.", "", "");
		return retorno;
	}
}
