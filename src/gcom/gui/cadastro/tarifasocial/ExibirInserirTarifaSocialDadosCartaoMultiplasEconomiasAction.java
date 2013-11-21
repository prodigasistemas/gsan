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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosCartaoMultiplasEconomiasAction
        extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("inserirTarifaSocialDadosCartao");

        //Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        TarifaSocialCartaoActionForm tarifaSocialCartaoActionForm = (TarifaSocialCartaoActionForm) actionForm;

        FiltroTarifaSocialCartaoTipo filtro = new FiltroTarifaSocialCartaoTipo();

        filtro.setCampoOrderBy(FiltroTarifaSocialCartaoTipo.DESCRICAO);

        FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();

        filtroRendaTipo.setCampoOrderBy(FiltroRendaTipo.DESCRICAO);

        Collection colecaoTarifaSocialCartaoTipo = fachada.pesquisar(filtro,
                TarifaSocialCartaoTipo.class.getName());

        Collection colecaoRendaTipo = fachada.pesquisar(filtroRendaTipo,
                RendaTipo.class.getName());

        //A coleção de tipos do cartão é obrigatória na página
        //[FS0004]
        if (colecaoTarifaSocialCartaoTipo == null
                || colecaoTarifaSocialCartaoTipo.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,
                    "tipo do cartão");
        }

        //A coleção de tipos de renda é obrigatória na página
        //[FS0004]
        if (colecaoRendaTipo == null || colecaoRendaTipo.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,
                    "tipo de renda");
        }

        httpServletRequest.setAttribute("colecaoTarifaSocialCartaoTipo",
                colecaoTarifaSocialCartaoTipo);

        httpServletRequest.setAttribute("colecaoRendaTipo", colecaoRendaTipo);
        
        FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
		filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(
				FiltroAreaConstruidaFaixa.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoAreaConstruidaFaixa = fachada.pesquisar(
				filtroAreaConstruidaFaixa,
				AreaConstruidaFaixa.class.getName());
		
		sessao.setAttribute("colecaoAreaConstruidaFaixa", colecaoAreaConstruidaFaixa);

        String clienteImovelEconomiaID = httpServletRequest
                .getParameter("clienteImovelEconomia");
        
        Integer idCliente = fachada.pesquisarClienteImovelEconomia(new Integer(clienteImovelEconomiaID));
        
        Collection colecaotarifaSocialDadoEconomia = fachada
		.pesquisarClientesUsuarioExistenteTarifaSocial(idCliente);

		if (colecaotarifaSocialDadoEconomia != null
				&& !colecaotarifaSocialDadoEconomia.isEmpty()) {
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaotarifaSocialDadoEconomia
					.iterator().next();

			sessao.setAttribute("idTarifaSocialDadoEconomia",
					tarifaSocialDadoEconomia.getId().toString());

			if (tarifaSocialDadoEconomia.getImovel() != null) {
				tarifaSocialCartaoActionForm
						.setIdImovel(tarifaSocialDadoEconomia.getImovel()
								.getId().toString());
			}

			if (tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo() != null) {
				tarifaSocialCartaoActionForm
						.setMotivoRevisao(tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo().getDescricao());
			}

			FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
			filtroTarifaSocialExclusaoMotivo
					.adicionarParametro(new ParametroSimples(
							FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroTarifaSocialExclusaoMotivo
					.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);

			Collection colecaoTarifaSocialExclusaoMotivo = fachada.pesquisar(
					filtroTarifaSocialExclusaoMotivo,
					TarifaSocialExclusaoMotivo.class.getName());

			httpServletRequest.setAttribute(
					"colecaoTarifaSocialExclusaoMotivo",
					colecaoTarifaSocialExclusaoMotivo);

		}
		
		ImovelEconomia imovelEconomia = null;
        
        boolean encontrouImovelEconomia = false;

        if (sessao.getAttribute("colecaoClienteImovelEconomia") != null) {
            Collection colecaoClienteImovelEconomiaMemoria = (Collection) sessao
                    .getAttribute("colecaoClienteImovelEconomia");

            TarifaSocialDadoEconomia tarifaSocialDadoEconomia = buscarTarifaSocialDadoEconomiaSelecionada(
                    colecaoClienteImovelEconomiaMemoria,
                    clienteImovelEconomiaID);

            if (tarifaSocialDadoEconomia != null) {
                httpServletRequest.setAttribute("tarifaSocialDadoEconomia",
                        tarifaSocialDadoEconomia);
                
                if (sessao.getAttribute("colecaoImovelEconomiaAtualizados") != null) {
                	Collection colecaoImovelEconomiaAtualizados = (Collection) sessao.getAttribute("colecaoImovelEconomiaAtualizados");
                	
                	Iterator colecaoImovelEconomiaAtualizadosIterator = colecaoImovelEconomiaAtualizados.iterator();
                	
                	while(colecaoImovelEconomiaAtualizadosIterator.hasNext()) {
                		imovelEconomia = (ImovelEconomia) colecaoImovelEconomiaAtualizadosIterator.next();
                		
                		if (imovelEconomia.getId().equals(tarifaSocialDadoEconomia.getImovelEconomia().getId())) {
                			encontrouImovelEconomia = true;
                			break;
                		}
                	}
                }
                
            }
            
        }
        
        if (!encontrouImovelEconomia) {
        	 imovelEconomia = fachada.pesquisarImovelEconomiaPeloCliente(new Integer(clienteImovelEconomiaID));
        }
        
		if (imovelEconomia != null) {
			
			// Número de Contrato da Companhia Elétrica
			if (imovelEconomia.getNumeroCelpe() != null) {
				tarifaSocialCartaoActionForm
						.setNumeroContratoCelpe(imovelEconomia
								.getNumeroCelpe().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroContratoCelpe("");
			}

			// Número do IPTU
			if (imovelEconomia.getNumeroIptu() != null) {
				tarifaSocialCartaoActionForm
						.setNumeroIPTU(imovelEconomia.getNumeroIptu().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroIPTU("");
			}

			// Área Construída
			if (imovelEconomia.getAreaConstruida() != null) {
				String areaConstruidaFormatada = Util
						.formatarMoedaReal(imovelEconomia
								.getAreaConstruida());
				tarifaSocialCartaoActionForm
						.setAreaConstruida(areaConstruidaFormatada);
			} else {
				tarifaSocialCartaoActionForm.setAreaConstruida("");
			}

			// Área Construída Faixa
			if (imovelEconomia.getAreaConstruidaFaixa() != null) {
				tarifaSocialCartaoActionForm
						.setAreaConstruidaFaixa(imovelEconomia
								.getAreaConstruidaFaixa().getId().toString());
			} else {
				tarifaSocialCartaoActionForm.setAreaConstruidaFaixa(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}
			
			// Número de Moradores
			if (imovelEconomia.getNumeroMorador() != null) {
				tarifaSocialCartaoActionForm.setNumeroMoradores(imovelEconomia.getNumeroMorador().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroMoradores("");
			}
			
			sessao.setAttribute("imovelEconomiaAtualizado", imovelEconomia);
			
		}
        
        // Seleciona em qual clienteImovelEconomia será inserida a tarifa social
        httpServletRequest.setAttribute("clienteImovelEconomiaID",
                clienteImovelEconomiaID);
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));

        
        return retorno;
    }

    /**
     * Retorno o objeto completo selecionado por um usuário
     * 
     * @param colecao
     * @param idObjeto
     * @return
     */
    private TarifaSocialDadoEconomia buscarTarifaSocialDadoEconomiaSelecionada(
            Collection colecao, String idObjeto) {
        TarifaSocialDadoEconomia retorno = null;
        ClienteImovelEconomia clienteImovelEconomia = null;
        Collection colecaoTarifaSocialDadoEconomia = null;

        if (colecao != null && !colecao.isEmpty() && idObjeto != null
                && !idObjeto.equals("")) {
            Iterator colecaoIterator = colecao.iterator();

            while (colecaoIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) colecaoIterator
                        .next();

                if (clienteImovelEconomia.getId().intValue() == new Integer(
                        idObjeto).intValue()) {
                    colecaoTarifaSocialDadoEconomia = clienteImovelEconomia
                            .getImovelEconomia().getTarifaSocialDadoEconomias();

                    if (colecaoTarifaSocialDadoEconomia != null
                            && !colecaoTarifaSocialDadoEconomia.isEmpty()) {
                        retorno = (TarifaSocialDadoEconomia) Util
                                .retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomia);
                    }

                    break;
                }
            }
        }

        return retorno;
    }
}
