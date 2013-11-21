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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
 * @author Tiago Moreno
 * @date 10/04/2008
 * 
 * @param ArquivoTextoLigacoesHidrometroHelper
 * 
 * @return
 * @throws ControladorException
 */

public class GerarArquivoTextoLigacoesHidrometroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Form
		GerarArquivoTextoLigacoesHidrometroActionForm form = 
			(GerarArquivoTextoLigacoesHidrometroActionForm) actionForm;
		
		ArquivoTextoLigacoesHidrometroHelper objetoArquivoTexto = 
			new ArquivoTextoLigacoesHidrometroHelper();
		
		// Verifica se pelo menos 1 campo foi preenchido
		boolean peloMenosUm = false;
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
			peloMenosUm = true;
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
			peloMenosUm = true;
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
			peloMenosUm = true;
		}
		
		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
			peloMenosUm = true;
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setRotaInicial(new Integer(form.getRotaInicial()));
			peloMenosUm = true;
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
			peloMenosUm = true;
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
			peloMenosUm = true;
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null && 
			!form.getSetorComercialFinal().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
			peloMenosUm = true;
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setRotaFinal(new Integer(form.getRotaFinal()));
			peloMenosUm = true;
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
			peloMenosUm = true;
		}			
		
		//verifica se pelo menos 1 campo foi preenchido no formulário
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
		}
		
		//chama o controlador para gerar o arquivo texto
		Fachada.getInstancia().gerarArquivoTextoLigacoesHidrometro(objetoArquivoTexto);
		
		montarPaginaSucesso(httpServletRequest, "Arquivo de Texto de Ligacoes com Hidrometro gerado com sucesso.",
				"Gerar outro Arquivo de Texto de Ligacoes com Hidrometro",
				"exibirGerarArquivoTextoLigacoesHidrometroAction.do?menu=sim" );
		

		return retorno;
	}
	
}