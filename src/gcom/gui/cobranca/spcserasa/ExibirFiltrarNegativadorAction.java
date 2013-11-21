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
package gcom.gui.cobranca.spcserasa;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a página para inserir um Negativador
 * 
 * @author Thiago Vieira
 */
public class ExibirFiltrarNegativadorAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("filtrarNegativador");
        FiltrarNegativadorActionForm form = (FiltrarNegativadorActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        String idDigitadoEnterCliente = (String) form.getCodigoCliente();
        String idDigitadoEnterImovel = (String) form.getCodigoImovel();
        String idDigitadoEnterAgente = (String) form.getCodigoAgente();
        
        form.setOkAgente("false");
        form.setOkCliente("false");
        form.setOkImovel("false");
        form.setAtivo("1");
        
        // verifica se o codigo do cliente foi digitado
        if (idDigitadoEnterCliente != null
                && !idDigitadoEnterCliente.trim().equals("")
                && Integer.parseInt(idDigitadoEnterCliente) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				form.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());
				form.setOkCliente("true");
			} else {
				httpServletRequest.setAttribute("corCliente","exception");
               	form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
               	form.setCodigoCliente("");
               	form.setOkCliente("false");
			}
        }
        
        if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idDigitadoEnterImovel));
        	
			if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
				
				// O imovel foi encontrado
				form.setCodigoImovel(idDigitadoEnterImovel);
				form.setInscricaoImovel(imovelEncontrado);
				form.setOkImovel("true");
			} else {
				httpServletRequest.setAttribute("corImovel","exception");
           		form.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		form.setCodigoImovel("");
           		form.setOkImovel("false");
			}
        }
        
        if (idDigitadoEnterAgente != null && !idDigitadoEnterAgente.trim().equals("")){
        	FiltroNegativador fNegativador = new FiltroNegativador();
        	fNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, idDigitadoEnterAgente));
        	
        	Collection codigoAgenteEncontrado = Fachada.getInstancia().pesquisar(fNegativador, Negativador.class.getName());
        	        	
        	if (codigoAgenteEncontrado != null && !codigoAgenteEncontrado.isEmpty()) {
        		
        		if (((Negativador) ((List) codigoAgenteEncontrado).get(0)).getIndicadorUso() 
        				== ConstantesSistema.INDICADOR_USO_DESATIVO.shortValue()) {
					throw new ActionServletException("atencao.negativador.inativo",
							null, ""
									+ ((Negativador) ((List) codigoAgenteEncontrado)
											.get(0)).getId());
				}
        		
           		form.setOkAgente("true");
        	} else {
        		form.setCodigoAgente("");
        		httpServletRequest.setAttribute("corAgente","exception");
           		form.setMensagemAgente(ConstantesSistema.CODIGO_AGENTE_NAO_CADASTRADO);
        		form.setOkAgente("false");
        	}
        }
        return retorno;
    }
}
