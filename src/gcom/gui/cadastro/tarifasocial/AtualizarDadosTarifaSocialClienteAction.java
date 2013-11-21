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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.ControladorGcomAction;
import gcom.gui.ControladorGcomActionForm;
import gcom.gui.ControladorInclusaoGcomAction;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author thiago toscano
 */
public class AtualizarDadosTarifaSocialClienteAction extends ControladorInclusaoGcomAction {

	public static final String CASO_USO = AtualizarDadosTarifaSocialClienteAction.class.getSimpleName()  + ".do";

	public static final String ACAO_EXIBIR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;

	public static final String ACAO_PROCESSAR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;

	/**
	 * 
	 */
	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm; 

		MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");
		
		AtualizarDadosTarifaSocialActionForm f2 = (AtualizarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"AtualizarDadosTarifaSocialActionForm");

		form.setIdImovel(f.getIdRegistroAtualizacao());
		form.setIdImovelEconomia(f2.getId());

		Collection coll = form.getCollObjeto();

		OTDManterDadosClienteImovelEconomia otd  = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);

		if (otd.getCollectionCliente(new Integer(f2.getId())) == null || otd.getCollectionCliente(new Integer(f2.getId())).isEmpty()) {

			if (otd.getQuantidadeEconomia() == 1) {
				FiltroClienteImovel filtroClienteImovel = (FiltroClienteImovel) this.gerarFiltro(form);

				coll = Fachada.getInstancia().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());

				Iterator it = coll.iterator();
				while (it.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) it.next(); 
					OTDClienteImovelEconomia otdCliente = new OTDClienteImovelEconomia();
					otdCliente.setCliente(clienteImovel.getCliente());
					otdCliente.setClienteImovelFimRelacaoMotivo(clienteImovel.getClienteImovelFimRelacaoMotivo());
					otdCliente.setClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo());
					otdCliente.setDataFimRelacao(clienteImovel.getDataFimRelacao());
					otdCliente.setDataInicioRelacao(clienteImovel.getDataInicioRelacao());
					otdCliente.setId(clienteImovel.getId());
					otd.putIDEconomiaCliente(new Integer(f2.getId()), otdCliente);
					
					form.setClienteNome(clienteImovel.getCliente().getNome());
					form.setComplementoEndereco(f2.getComplementoEndereco());
				}
			} else {

				// pegando a imovel economia selecionado
		        coll = otd.getOtdClienteEconomia();
		        if(coll != null) {
		        	Iterator it = coll.iterator();
		        	while (it.hasNext()) {
		        		OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
		        		if (f2.getId().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) {

		        			// pesquisando os clientes do imovel economia selecionado
		    				FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
		    		        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
		    		        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente");
		    		        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
		    		        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");
		    		        filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		    		        filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID, clienteEconomia.getImovelEconomia().getId()));

		    				coll = Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia,ClienteImovelEconomia.class.getName());

		    				Iterator itt = coll.iterator();
		    				while (itt.hasNext()) {
		    					ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) itt.next(); 
		    					OTDClienteImovelEconomia otdCliente = new OTDClienteImovelEconomia();
		    					otdCliente.setCliente(clienteImovelEconomia.getCliente());
		    					otdCliente.setClienteImovelFimRelacaoMotivo(clienteImovelEconomia.getClienteImovelFimRelacaoMotivo());
		    					otdCliente.setClienteRelacaoTipo(clienteImovelEconomia.getClienteRelacaoTipo());
		    					otdCliente.setDataFimRelacao(clienteImovelEconomia.getDataFimRelacao());
		    					otdCliente.setDataInicioRelacao(clienteImovelEconomia.getDataInicioRelacao());
		    					otdCliente.setId(clienteImovelEconomia.getId());
		    					otd.putIDEconomiaCliente(new Integer(f2.getId()), otdCliente);

		    					if (clienteImovelEconomia.getClienteRelacaoTipo().getId().equals(ClienteRelacaoTipo.USUARIO) ){
		    						form.setClienteNome(clienteEconomia.getCliente().getNome());
		    						form.setComplementoEndereco(f2.getComplementoEndereco());
		    					}
		    				}
		        		}
		        	}
		        }			
			}
		}

		/**
		 * Criando uma outro objeto (collecao dos cliente a ser manipulados por essa tela)
		 */
		coll = otd.getCollectionCliente(new Integer(f2.getId()));
		if (coll != null) {			
			Vector collObjeto = new Vector();
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				collObjeto.add(it.next());
			}
			form.setCollObjeto(collObjeto);	
		}

		form.setDataInicioRelacao(Util.formatarData(new Date(System.currentTimeMillis())));

		return null;
	}

	public ActionForward concluir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		processar(actionMapping, actionForm, request, response);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_CONCLUIR);
	}
	
	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		AtualizarDadosTarifaSocialActionForm f2 = (AtualizarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"AtualizarDadosTarifaSocialActionForm");

		Cliente clienteUsuario = null;
		/**
		 * para todos os clientes manipulados pela tela
		 * verifica se tem usuario e se tem proprietario
		 */
		Collection clientes = form.getCollObjeto();
		if (clientes != null) {
			Iterator it = clientes.iterator();
			boolean temUsuario = false;
			boolean temProprietario = false;			
			while (it.hasNext()) {
				// ve
				OTDClienteImovelEconomia clienteImovelEconomia = (OTDClienteImovelEconomia) it.next();
				if (clienteImovelEconomia.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()
						&& clienteImovelEconomia.getDataFimRelacao() == null){
					temUsuario = true;
					clienteUsuario = clienteImovelEconomia.getCliente();
				}
				if (clienteImovelEconomia.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.PROPRIETARIO.shortValue()
						&& clienteImovelEconomia.getDataFimRelacao() == null){
					temProprietario = true;
				}
			}
			if (!temUsuario) {
				throw new ActionServletException("atencao.tarifasocial.cliente_usuario_obrigatorio");
			}
			if (!temProprietario) {
				throw new ActionServletException("atencao.tarifasocial.cliente_proprietario_obrigatorio");
			}
		}

		// pega todo os usuarios manipulados na tela e adiciona no otd 
		OTDManterDadosClienteImovelEconomia OTD = (OTDManterDadosClienteImovelEconomia)form.getOTD(request);
		OTD.resetCollectionCliente(new Integer(f2.getId()));
		if (clientes != null) {
			Iterator it = clientes.iterator();
			while (it.hasNext()) {
				OTDClienteImovelEconomia clienteImovelEconomia = (OTDClienteImovelEconomia) it.next();
				OTD.putIDEconomiaCliente(new Integer(f2.getId()),clienteImovelEconomia);
			}
		}
		
		// se o usuario mudou entao atualiza o usuario da tela de manter
		if (clienteUsuario != null) {
			
	        OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);
	        
	        Collection coll = otd.getOtdClienteEconomia();

	        if(coll != null) {
	        	Iterator it = coll.iterator();
	        	while (it.hasNext()) {
	        		OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
//	        		TarifaSocialDadoEconomia tarifa = (TarifaSocialDadoEconomia)it.next();
	        		if ((form.getIdImovelEconomia().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) && !clienteUsuario.getId().equals(clienteEconomia.getCliente().getId()))  {
	        			clienteEconomia.setCliente(clienteUsuario);
	        			break;
	        		}
	        	}
	        }
			
		}
		
		

		form.setCollObjeto(null);

		return actionMapping.findForward(form.getForward());
	}

	public ActionForward removerCliente(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

		Collection clientes = form.getCollObjeto();
		Collection novo = new Vector();

		int[] posicoes = new int[0];

		
		ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
		clienteImovelFimRelacaoMotivo.setId(new Integer(form.getClienteImovelFimRelacaoMotivo()));
		
		if (form.getPosicaoParaRemover() != null) {
			String[] posicaoParaRemover = form.getPosicaoParaRemover();
			posicoes = new int[posicaoParaRemover.length];
			for (int i = 0; i < posicaoParaRemover.length; i++) {
				try {
					posicoes[i] = new Integer(posicaoParaRemover[i]).intValue();
				} catch (Exception e) {}
			}
		}

		if (clientes != null) {
			Iterator it = clientes.iterator();
			int i = 0;
			//para todos as relacoes cliente imovel
			while (it.hasNext()) {
				
				boolean tem = false;
				// pega todas as posicoes a serem removidas
				for (int j = 0; j < posicoes.length; j++) {
					// o i e incrementado em baixo seleiona a posicao a ser removida
					if (posicoes[j] == i) {
						tem = true;
					}
				}
				// se nao tem cliente para remover
				if (!tem) {
					novo.add(it.next());
				} else {
//					 se tem cliente para removoer
					OTDClienteImovelEconomia clienteImovel = (OTDClienteImovelEconomia)it.next();

					if (!"".equals(form.getDataFimRelacao())) {
						Date data = Util.converteStringParaDate(form.getDataFimRelacao());
						if (data == null) {
							throw new ActionServletException("atencao.tarifasocial.data_final_relacao_invalida");
						}
						if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
							throw new ActionServletException("atencao.tarifasocial.data_final_relacao_maior_que_hoje");
						}

						if (data.getTime() < clienteImovel.getDataInicioRelacao().getTime()) {
							throw new ActionServletException("atencao.tarifasocial.data_final_relacao_menor_inicio_relacao");
						}
						clienteImovel.setDataFimRelacao(data);
					} else {
						clienteImovel.setDataFimRelacao(new Date(System.currentTimeMillis()));
					}

					clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);

					novo.add(clienteImovel);
				}
				i++;
			}
		}
		
		this.carregarColecao(form);

		form.setCollObjeto(novo);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

	public ActionForward adicionarCliente(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		

		
		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia)form.getOTD(request);
		
//		 	pesquisa o tipo de relacionamento
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, form.getClienteRelacaoTipo()));
		Collection coll = Fachada.getInstancia().pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());
		ClienteRelacaoTipo clienteRelacaoTipo = null;
		if (coll != null && !coll.isEmpty()) {
			clienteRelacaoTipo = (ClienteRelacaoTipo) coll.iterator().next();
		}

		if ( ClienteRelacaoTipo.USUARIO.equals( new Short(form.getClienteRelacaoTipo()))) {
			Fachada.getInstancia().verificarClienteUsuarioEmOutroEconomia(null, new Integer(form.getIdImovelEconomia()), new Integer(form.getIdCliente()));		
		}

		Cliente cliente = null;

		try {
			new Integer(form.getIdCliente());

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,form.getIdCliente()));
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);

			coll = Fachada.getInstancia().pesquisarCliente(filtroCliente);
			
			if (coll != null && !coll.isEmpty()) 
				cliente = (Cliente) coll.iterator().next();

		} catch (NumberFormatException e) {
			// nao foi um numero passado
		}
			
		if (cliente == null) {
			return pesquisaCliente(actionMapping, actionForm, request, response);
		}
		
		if ((cliente.getCpf() == null || "".equals(cliente.getCpf())) && (cliente.getRg() == null || "".equals(cliente.getRg()))) {
			if (otd.getQuantidadeEconomia() == 1) {
				if (clienteRelacaoTipo != null && clienteRelacaoTipo.getId().equals(ClienteRelacaoTipo.PROPRIETARIO)) {
					throw new ActionServletException("atencao.tarifasocial.imovel.rg_cpf_nao_cofigurado_proprietario");
				}
				throw new ActionServletException("atencao.tarifasocial.imovel.rg_cpf_nao_cofigurado_usuario");
			}
			if (clienteRelacaoTipo != null && clienteRelacaoTipo.getId().equals(ClienteRelacaoTipo.PROPRIETARIO)) {
				throw new ActionServletException("atencao.tarifasocial.economia.rg_cpf_nao_cofigurado_proprietario");
			}
			throw new ActionServletException("atencao.tarifasocial.economia.rg_cpf_nao_cofigurado_usuario");
		}

		OTDClienteImovelEconomia ci = new OTDClienteImovelEconomia();
		ci.setCliente(cliente);
		//ci.setImovel(imovel);
		ci.setClienteRelacaoTipo(clienteRelacaoTipo);
		if (!"".equals(form.getDataInicioRelacao())) {
			Date data = Util.converteStringParaDate(form.getDataInicioRelacao());
			if (data == null) {
				throw new ActionServletException("atencao.tarifasocial.data_inicio_relacao_invalida");
			}
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException("atencao.tarifasocial.data_inicio_relacao_maior_que_hoje");
			}
			if (cliente.getDataNascimento()!= null && data.getTime() < cliente.getDataNascimento().getTime()) {
				throw new ActionServletException("atencao.tarifasocial.data_inicio_relacao_menor_que_data_nascimento");
			}
			ci.setDataInicioRelacao(data);
		} else {
			ci.setDataInicioRelacao(new Date(System.currentTimeMillis()));
		}

		Collection clientes = form.getCollObjeto();
		if (clientes != null) {
			Iterator it = clientes.iterator();
			// verificando se o usuario ja foi inserido com o mesmo tipo
			while (it.hasNext()) {
				OTDClienteImovelEconomia clienteImovel = (OTDClienteImovelEconomia) it.next();
				
				if (ClienteRelacaoTipo.USUARIO.equals(clienteRelacaoTipo.getId()) && ClienteRelacaoTipo.USUARIO.equals(clienteImovel.getClienteRelacaoTipo().getId())  && clienteImovel.getDataFimRelacao() == null) {
					throw new ActionServletException("atencao.tarifasocial.cliente_usuario_duplicidade");
				}
				
				if (clienteImovel.getCliente().getId().intValue() == 
					ci.getCliente().getId().intValue() && clienteImovel.getDataFimRelacao() == null && clienteImovel.getClienteRelacaoTipo().getId().equals(clienteRelacaoTipo.getId())) {
					if (ClienteRelacaoTipo.USUARIO.equals(clienteRelacaoTipo.getId())) {
						throw new ActionServletException("atencao.tarifasocial.cliente_usuario_duplicidade");
					} else if (ClienteRelacaoTipo.PROPRIETARIO.equals(clienteRelacaoTipo.getId())) {
						throw new ActionServletException("atencao.tarifasocial.cliente_proprietario_duplicidade");
					}
				}
				
			}
			clientes.add(ci);
		}
		

		this.carregarColecao(form);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

	public ActionForward pesquisaCliente(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		

		Collection coll = null;
		try {
			Integer idCliente = new Integer(form.getIdCliente());

			if (idCliente.intValue() == 0)
				throw new ActionServletException("atencao.tarifasocial.codigo_cliente_iqual_zero");

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,form.getIdCliente()));

			coll = Fachada.getInstancia().pesquisarCliente(filtroCliente);	
		} catch (NumberFormatException e) {
			// nao foi um numero passado
		}

		if (coll != null && !coll.isEmpty()) {
			Cliente cliente = (Cliente) coll.iterator().next();
			form.setNomeCliente(cliente.getNome());
			request.setAttribute("codigoClienteNaoEncontrada",null);
		} else {
			form.setNomeCliente("Cliente inexistente.");
			request.setAttribute("codigoClienteNaoEncontrada","");
			form.setIdCliente("");
		}

		this.carregarColecao(form);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}


	public ActionForward mostraMotivo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		form.setDataFimRelacao(Util.formatarData(new Date(System.currentTimeMillis())));

		Collection collParaRemover = new Vector();

		int[] posicoes = new int[0];
		if (form.getPosicaoParaRemover() != null) {
			String[] posicaoParaRemover = form.getPosicaoParaRemover();
			posicoes = new int[posicaoParaRemover.length];
			for (int i = 0; i < posicaoParaRemover.length; i++) {
				try {
					posicoes[i] = new Integer(posicaoParaRemover[i]).intValue();
				} catch (Exception e) {}
			}
		}

		// colecao de clientes que fora solicitados para remover e que ainda noa foram persistido no banco
		Collection clientesParaRemoverNaoPersistido = new Vector();
		Collection clientes = form.getCollObjeto();

		if (clientes != null) {
			Iterator it = clientes.iterator();
			int i = 0;
			//para todos as relacoes cliente imovel
			while (it.hasNext()) {
				OTDClienteImovelEconomia clienteImovelEconomia = (OTDClienteImovelEconomia)it.next();

				// pega todas as posicoes a serem removidas
				for (int j = 0; j < posicoes.length; j++) {
					// o i e incrementado em baixo seleiona a posicao a ser removida
					if (posicoes[j] == i) {
						if (clienteImovelEconomia.getId() == null) {
							clientesParaRemoverNaoPersistido.add(clienteImovelEconomia);
						} else {
							collParaRemover.add(clienteImovelEconomia);
						}
						break;
					}
				}
				i++;
			}
		}
		
		Iterator it = clientesParaRemoverNaoPersistido.iterator();
		while (it.hasNext()) {
			clientes.remove(it.next());
		}

		if (collParaRemover.isEmpty()) {
			return this.exibir(actionMapping, actionForm, request, response);
		}
		getSessao(request).setAttribute("clienteImovelEconomia",collParaRemover);

		form.setCollClienteImovelFimRelacaoMotivo(Fachada.getInstancia().pesquisar(new FiltroClienteImovelFimRelacaoMotivo(),ClienteImovelFimRelacaoMotivo.class.getName()));
		return actionMapping.findForward(ControladorGcomAction.FORWARD_POPUP);
	}
	/*

	public ActionForward mostraMotivo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		String[] posicaoParaRemover = form.getPosicaoParaRemover();
		
		

		form.setCollClienteImovelFimRelacaoMotivo(Fachada.getInstancia().pesquisar(new FiltroClienteImovelFimRelacaoMotivo(),ClienteImovelFimRelacaoMotivo.class.getName()));
		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}
*/
	

	
	

	public Object gerarObject(ControladorGcomActionForm actionForm, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void inserirObjeto(Object obj, HttpServletRequest request, ControladorGcomActionForm actionForm) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
		
	
	
	
	
	
	public void carregarColecao(ControladorGcomActionForm actionForm) {
		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

        Collection collClienteRelacaoTipo = Fachada.getInstancia().pesquisar(new FiltroClienteRelacaoTipo(FiltroClienteRelacaoTipo.DESCRICAO),ClienteRelacaoTipo.class.getSimpleName());
        Collection collClienteRelacaoTipoSemResponsavel = new Vector(); 
        if (collClienteRelacaoTipo != null) {
        	Iterator it = collClienteRelacaoTipo.iterator();
        	while (it.hasNext()) {
        		ClienteRelacaoTipo relacao = (ClienteRelacaoTipo) it.next();
        		if (!relacao.getId().equals(ClienteRelacaoTipo.RESPONSAVEL)) {
        			collClienteRelacaoTipoSemResponsavel.add(relacao);
        		}
        	}
        }

        form.setCollClienteRelacaoTipo(collClienteRelacaoTipoSemResponsavel);

	}
	public Filtro gerarFiltro(ControladorGcomActionForm actionForm) {
		
		AtualizarDadosTarifaSocialClienteActionForm form = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;
		
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

        if (form.getIdImovel() != null && !"".equals(form.getIdImovel())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getIdImovel()));
        }
		return filtroClienteImovel;
	}
}	