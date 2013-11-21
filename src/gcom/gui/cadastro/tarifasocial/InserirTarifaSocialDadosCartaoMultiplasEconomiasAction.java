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
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
public class InserirTarifaSocialDadosCartaoMultiplasEconomiasAction extends
        GcomAction {
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
                .findForward("inserirTarifaSocialDadosCartaoMultiplasEconomias");

        //Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        TarifaSocialCartaoActionForm form = (TarifaSocialCartaoActionForm) actionForm;

        //Descobrir se é um recadastramento
        Collection colecaoClienteImovelEconomia = (Collection) sessao
                .getAttribute("colecaoClienteImovelEconomia");
        
//       Imovel imovelSelecionado = (Imovel) sessao.getAttribute("imovelTarifa");

        //Codigo do objeto clienteImovelEconomia selecionado
        String clienteImovelEconomiaID = httpServletRequest
                .getParameter("clienteImovelEconomiaID");
        
//        FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
//
//        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia");
//        
//        filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.ID,
//        clienteImovelEconomiaID));
//        
//        Collection carregarClienteImovelEconomia = fachada.pesquisar(filtroClienteImovelEconomia, 
//        ClienteImovelEconomia.class.getName());
//        
//        ClienteImovelEconomia objetoClienteImovelEconomia = (ClienteImovelEconomia) Util.retonarObjetoDeColecao(carregarClienteImovelEconomia);
//
//        Long numeroCelpe = objetoClienteImovelEconomia.getImovelEconomia().getNumeroCelpe();
//		BigDecimal areaConstruida = objetoClienteImovelEconomia.getImovelEconomia().getAreaConstruida();
//		BigDecimal numeroIPTU = objetoClienteImovelEconomia.getImovelEconomia().getNumeroIptu();
        
        String numeroIptu = form.getNumeroIPTU();
        String numeroContratoCompanhiaEletrica = form.getNumeroContratoCelpe();
        String areaConstruida = form.getAreaConstruida();
        String idAreaConstruidaFaixa = form.getAreaConstruidaFaixa();
        String numeroMoradores = form.getNumeroMoradores();
        
        Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
        
        Collection colecaoImovelEconomiaAtualizar = null;
		
		if (sessao.getAttribute("colecaoImovelEconomiaAtualizados") != null) {
			colecaoImovelEconomiaAtualizar = (Collection) sessao.getAttribute("colecaoImovelEconomiaAtualizados");
		} else {
			colecaoImovelEconomiaAtualizar = new ArrayList();
		}
        
		Long numeroCelpeFormatado = null;
		
		if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
			numeroCelpeFormatado = new Long(numeroContratoCompanhiaEletrica);
		}
		
		BigDecimal areaConstruidaFormatada = null;
		
		if (areaConstruida != null && !areaConstruida.trim().equals("")) {
			areaConstruidaFormatada = Util.formatarMoedaRealparaBigDecimal(areaConstruida);
		}
		
		BigDecimal numeroIptuFormatado = null;
		
		if (numeroIptu != null && !numeroIptu.trim().equals("")) {
			numeroIptuFormatado = Util.formatarMoedaRealparaBigDecimal(numeroIptu);
		}
		
		ImovelEconomia imovelEconomiaAtualizado = (ImovelEconomia) sessao.getAttribute("imovelEconomiaAtualizado");

        String[] dado = fachada.verificarPreenchimentoInserirDadosTarifaSocialMultiplas(
        		numeroCelpeFormatado, areaConstruidaFormatada, numeroIptuFormatado,
        		imovelEconomiaAtualizado.getId(), form
						.getNumero(), form.getDataValidade(), form
						.getNumeroParcelas(), new Integer(form.getNumeroCelpe()
						.trim().equals("") ? "0" : form.getNumeroCelpe()), form
						.getValorRendaFamiliar().trim().equals("") ? Util
						.formatarMoedaRealparaBigDecimal("0") : Util
						.formatarMoedaRealparaBigDecimal(form
								.getValorRendaFamiliar()),
				form.getTipoCartao(), form.getTipoRenda());
        
        if(dado != null){
        	if(dado[0].equals("9")){
        		sessao.setAttribute("codigo","9");
        		
        	}else if(dado[0].equals("10")){
        		
        		sessao.setAttribute("codigo","10");
        		sessao.setAttribute("valor",dado[1]);
        		
                httpServletRequest.setAttribute("operacaoConcluida", "true");

                return retorno;        		
        		
        		
        	}else if(dado[0].equals("11")){
        		
        		sessao.setAttribute("codigo","11");
        		sessao.setAttribute("valor",dado[1]);
        		
                httpServletRequest.setAttribute("operacaoConcluida", "true");

                return retorno;        		
        		
        	} else if (dado[0].equals("12")) {
        		
        		sessao.setAttribute("codigo","12");
        		sessao.setAttribute("valor",dado[1]);
        		
        		
                httpServletRequest.setAttribute("operacaoConcluida", "true");

                return retorno;   
        		
        	}
        	
        }
        
        sessao.removeAttribute("codigo");
        sessao.removeAttribute("valor");
        
        
        //Esse parametro indica se o registro que está sendo avaliado já está na base
        //O código é a chava primária
        String chave = httpServletRequest.getParameter("codigo");
        
        Collection colecaoPesquisa;
        TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

        //tarifaSocialDadoEconomia.setTarifaSocialDado(tarifaSocialDado);
        tarifaSocialDadoEconomia.setUltimaAlteracao(new Date());

        if (form.getNumero() != null && !form.getNumero().equals("")) {
            tarifaSocialDadoEconomia.setNumeroCartaoProgramaSocial(new Long(
                    form.getNumero()));

            TarifaSocialCartaoTipo tarifaSocialCartaoTipo;
            FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

            filtroTarifaSocialCartaoTipo
                    .adicionarParametro(new ParametroSimples(
                            FiltroTarifaSocialCartaoTipo.ID, form
                                    .getTipoCartao()));

            colecaoPesquisa = fachada.pesquisar(filtroTarifaSocialCartaoTipo,
                    TarifaSocialCartaoTipo.class.getName());

            tarifaSocialCartaoTipo = (TarifaSocialCartaoTipo) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);

            tarifaSocialDadoEconomia
                    .setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
        }

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataValidade = null;

        try {
            dataValidade = formatoData.parse(form.getDataValidade());
        } catch (ParseException ex) {
            dataValidade = null;
        }

        tarifaSocialDadoEconomia.setDataValidadeCartao(dataValidade);
        
        
        //Consumo Celpe
        if (form.getNumeroCelpe() != null
                && !form.getNumeroCelpe().equals("")) {
        	tarifaSocialDadoEconomia.setConsumoCelpe(new Integer(form.getNumeroCelpe()));
        }

        //Número de parcelas
        if (form.getNumeroParcelas() != null
                && !form.getNumeroParcelas().equals("")) {
        	tarifaSocialDadoEconomia.setNumeroMesesAdesao(new Short(form.getNumeroParcelas()));
        }
        

        if (form.getValorRendaFamiliar() != null
                && !form.getValorRendaFamiliar().equals("")) {
            tarifaSocialDadoEconomia.setValorRendaFamiliar(new BigDecimal(form
                    .getValorRendaFamiliar()));

            RendaTipo rendaTipo;
            FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();

            filtroRendaTipo.adicionarParametro(new ParametroSimples(
                    FiltroRendaTipo.ID, form.getTipoRenda()));

            colecaoPesquisa = fachada.pesquisar(filtroRendaTipo,
                    RendaTipo.class.getName());

            rendaTipo = (RendaTipo) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);

            tarifaSocialDadoEconomia.setRendaTipo(rendaTipo);
        }
        
        Integer idImovel = null;
        
		// Número do IPTU
		if (numeroIptu != null && !numeroIptu.trim().equals("")) {
			
			if (colecaoImovelEconomiaAtualizar != null && !colecaoImovelEconomiaAtualizar.isEmpty()) {
				Iterator colecaoImovelEconomiaAtualizarIterator = colecaoImovelEconomiaAtualizar.iterator();
				
				while (colecaoImovelEconomiaAtualizarIterator.hasNext()) {
					
					ImovelEconomia imovelEconomia = (ImovelEconomia) colecaoImovelEconomiaAtualizarIterator
							.next();

					// Verifica se o número de contrato da companhia já foi usado em outra economia do imóvel
					if (imovelEconomia
							.getNumeroIptu() != null
							&& imovelEconomia
									.getNumeroIptu()
									.equals(
											numeroIptuFormatado)
							&& !imovelEconomia
									.getId()
									.equals(
											imovelEconomiaAtualizado
													.getId())) {
						throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, imovelSessao.getId().toString());
					}
				}
			}
			
			idImovel = fachada.verificarNumeroIptu(numeroIptuFormatado, null, imovelEconomiaAtualizado.getId(), imovelEconomiaAtualizado.getImovelSubcategoria().getComp_id()
					.getImovel().getSetorComercial().getMunicipio().getId());
			
			if (idImovel != null) {
				throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, idImovel.toString());
			}
			
			imovelEconomiaAtualizado.setNumeroIptu(numeroIptuFormatado);
		} else {
			imovelEconomiaAtualizado.setNumeroIptu(null);
		}
		
		// Número do Contrato da Companhia Elétrica
		if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
			
			Long numeroContratoCompanhiaEletricaFormatado = new Long(numeroContratoCompanhiaEletrica);
			
			if (colecaoImovelEconomiaAtualizar != null && !colecaoImovelEconomiaAtualizar.isEmpty()) {
				Iterator colecaoImovelEconomiaAtualizarIterator = colecaoImovelEconomiaAtualizar.iterator();
				
				while (colecaoImovelEconomiaAtualizarIterator.hasNext()) {
					
					ImovelEconomia imovelEconomia = (ImovelEconomia) colecaoImovelEconomiaAtualizarIterator
							.next();

					// Verifica se o número de contrato da companhia já foi usado em outra economia do imóvel
					if (imovelEconomia
							.getNumeroCelpe() != null
							&& imovelEconomia
									.getNumeroCelpe()
									.equals(
											numeroContratoCompanhiaEletricaFormatado)
							&& !imovelEconomia
									.getId()
									.equals(
											imovelEconomiaAtualizado
													.getId())) {
						throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, imovelSessao.getId().toString());
					}
				}
			}
			
			idImovel = fachada.verificarNumeroCompanhiaEletrica(numeroContratoCompanhiaEletricaFormatado, null, imovelEconomiaAtualizado.getId());
			
			if (idImovel != null) {
				throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, idImovel.toString());
			}
			
			imovelEconomiaAtualizado.setNumeroCelpe(numeroContratoCompanhiaEletricaFormatado);
		} else {
			imovelEconomiaAtualizado.setNumeroCelpe(null);
		}
		
		// Área Construída
		if (areaConstruida != null && !areaConstruida.trim().equals("")) {
			imovelEconomiaAtualizado.setAreaConstruida(areaConstruidaFormatada);
		} else {
			imovelEconomiaAtualizado.setAreaConstruida(null);
		}
		
		// Área Construída Faixa
		if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
			areaConstruidaFaixa.setId(new Integer(idAreaConstruidaFaixa));
			imovelEconomiaAtualizado.setAreaConstruidaFaixa(areaConstruidaFaixa);
		} else {
			imovelEconomiaAtualizado.setAreaConstruidaFaixa(null);
		}
		
		// Número de Moradores
		if (numeroMoradores != null && !numeroMoradores.trim().equals("")) {
			imovelEconomiaAtualizado.setNumeroMorador(new Short(numeroMoradores));
		} else {
			imovelEconomiaAtualizado.setNumeroMorador(null);
		}
		
		if (form.getIdImovel() != null && !form.getIdImovel().equals("")) {
        	if (form.getMotivoExclusao() != null && !form.getMotivoExclusao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        		Integer idTarifaSocialExclusaoMotivo = new Integer(form.getMotivoExclusao());
        		
        		// Seta o motivo da exclusão na tarifa social antiga para ser
				// recuperado posteriormente
        		TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
        		tarifaSocialExclusaoMotivo.setId(idTarifaSocialExclusaoMotivo);
        		tarifaSocialDadoEconomia.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
        		
        		// Recupera o id da tarifa social que será excluída quando o
				// usuário concluir o inserção e coloca-o no objeto a ser
				// inserido para ser recuperado posteriormente
//        		String idTarifaSocialDadoEconomia = (String) sessao.getAttribute("idTarifaSocialDadoEconomia");
//        		tarifaSocialDadoEconomia.setId(new Integer(idTarifaSocialDadoEconomia));
        		
        	} else {
        		throw new ActionServletException("atencao.tarifa_social_motivo_exclusao_nao_informado");
        	}
        }
		
		boolean encontrouImovelEconomia = false;
		
		ImovelEconomia imovelEconomia = null;
		
		if (colecaoImovelEconomiaAtualizar != null && !colecaoImovelEconomiaAtualizar.isEmpty()) {
		
		Iterator colecaoImovelEconomiaAtualizadosIterator = colecaoImovelEconomiaAtualizar.iterator();
    	
    	while(colecaoImovelEconomiaAtualizadosIterator.hasNext()) {
    		imovelEconomia = (ImovelEconomia) colecaoImovelEconomiaAtualizadosIterator.next();
    		
    		if (imovelEconomia.getId().equals(imovelEconomiaAtualizado.getId())) {
    			encontrouImovelEconomia = true;
    			break;
    		}
    	}
    	
		}
		
		if (!encontrouImovelEconomia) {
			colecaoImovelEconomiaAtualizar.add(imovelEconomiaAtualizado);
		} else {
			colecaoImovelEconomiaAtualizar.remove(imovelEconomia);
			colecaoImovelEconomiaAtualizar.add(imovelEconomiaAtualizado);
		}
		
		sessao.setAttribute("colecaoImovelEconomiaAtualizados", colecaoImovelEconomiaAtualizar);

        /*
         * Alterado para só realizar a persistência dos dados na conclusão do
         * processo de inserção da tarifa social - Raphael Rossiter 14/09/2005
         */

        //Prepara os objetos que ficarão na sessão
        if (chave.trim().equals("")) {
            //Insere na base

            retornarColecaoAtualizada(colecaoClienteImovelEconomia,
                    clienteImovelEconomiaID, tarifaSocialDadoEconomia);

        } else {
            //Atualiza o registro na base
            tarifaSocialDadoEconomia.setId(new Integer(chave));
            tarifaSocialDadoEconomia.setImovelEconomia(imovelEconomiaAtualizado);

            retornarColecaoAtualizada(colecaoClienteImovelEconomia,
                    clienteImovelEconomiaID, tarifaSocialDadoEconomia);

        }

        // Coloca os dados da tarifa social na sessão
        sessao.removeAttribute("colecaoClienteImovelEconomia");
        sessao.setAttribute("colecaoClienteImovelEconomia",
                colecaoClienteImovelEconomia);

        //Manda o parametro pelo request para que a página de popup saiba a
        // hora de fechar
        httpServletRequest.setAttribute("operacaoConcluida", "true");

        return retorno;
    }

    /**
     * Retorno o objeto completo selecionado por um usuário
     * 
     * @param colecao
     * @param idObjeto
     * @return
     */
    private void retornarColecaoAtualizada(Collection colecao, String idObjeto,
            TarifaSocialDadoEconomia tarifaSocial) {
        ClienteImovelEconomia clienteImovelEconomia = null;
        Set colecaoTarifaSocialDadoEconomia;

        if (colecao != null && !colecao.isEmpty() && idObjeto != null
                && !idObjeto.equals("")) {
            Iterator colecaoIterator = colecao.iterator();

            while (colecaoIterator.hasNext()) {
                clienteImovelEconomia = (ClienteImovelEconomia) colecaoIterator
                        .next();
                
                if (clienteImovelEconomia.getId().intValue() == new Integer(
                        idObjeto).intValue()) {
                    colecaoTarifaSocialDadoEconomia = new HashSet();
                    colecaoTarifaSocialDadoEconomia.add(tarifaSocial);

                    clienteImovelEconomia.getImovelEconomia()
                            .setTarifaSocialDadoEconomias(
                                    colecaoTarifaSocialDadoEconomia);
                } else {
                	
                	
                	// Verificar Cartão em duplicidade informado em outra economia
                	Collection colecaoTarifasSociais = clienteImovelEconomia.getImovelEconomia().getTarifaSocialDadoEconomias();
                	
                	if (colecaoTarifasSociais != null && !colecaoTarifasSociais.isEmpty()) {
                    	
                    	Iterator colecaoTarifasSociaisIterator = colecaoTarifasSociais.iterator();
                    	
                    	while (colecaoTarifasSociaisIterator.hasNext()) {
                    		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifasSociaisIterator
									.next();
                    		
                    		if (tarifaSocialDadoEconomia
									.getNumeroCartaoProgramaSocial() != null
									&& tarifaSocial
											.getNumeroCartaoProgramaSocial() != null
									&& tarifaSocialDadoEconomia
											.getNumeroCartaoProgramaSocial()
											.longValue() == tarifaSocial
											.getNumeroCartaoProgramaSocial()
											.longValue()) {

								if (tarifaSocialDadoEconomia
										.getTarifaSocialCartaoTipo() != null
										&& tarifaSocial
												.getTarifaSocialCartaoTipo() != null
										&& tarifaSocialDadoEconomia
												.getTarifaSocialCartaoTipo()
												.getId()
												.equals(
														tarifaSocial
																.getTarifaSocialCartaoTipo()
																.getId())) {
									throw new ActionServletException(
											"atencao.cartao_programa_social_duplicidade");
								}
                    		}
                    	}
                    	
                    }
                }
            }
        }

    }

}
