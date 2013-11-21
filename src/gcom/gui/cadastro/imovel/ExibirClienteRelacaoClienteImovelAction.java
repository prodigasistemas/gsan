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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.bean.ConsultarClienteRelacaoClienteImovelHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action ExibirClienteRelacaoClienteImovelActionForm
 * 
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirClienteRelacaoClienteImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;

		HttpSession sessao = request.getSession(false);

		removerAtributosSessao(sessao);

		if ( !Util.verificarNaoVazio(form.getIdCliente()) ) {
			throw new ActionServletException("erro.parametro.nao.informado",
					null, "idCliente");
		}

		Cliente cliente = obterClienteInformadoESetarSessao(form,sessao);

		setarClienteEnderecoSessaoERequest(cliente, sessao, request);

		setarColecaoRelacaoClienteImovelHelperSessao(form, sessao);

		setarColecaoClienteImovelEconomiaSessao(form, sessao);

		return actionMapping.findForward("exibir");
	}

	/**
	 * Método retorna o objeto Cliente que presenta
	 * o cliente que o usuário informou na tela.
	 * Ele também seta esse cliente na sessão.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private Cliente obterClienteInformadoESetarSessao(ConsultarRelacaoClienteImovelActionForm form,
			HttpSession sessao) {

		FiltroCliente filtroCliente = criarFiltroConsultarClienteInformadoUsuario(form);

		Collection<Cliente> colecaoCliente = Fachada.getInstancia()
			.pesquisarClienteDadosClienteEnderecoRelatorio(filtroCliente);

		if(Util.isVazioOrNulo(colecaoCliente)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		Cliente cliente = colecaoCliente.iterator().next();
		sessao.setAttribute("cliente", cliente);

		return cliente;
	}

	/**
	 *Método consulta ClienteImovelEconomia do cliente informado
	 *e então seta essa coleção na sessão.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoClienteImovelEconomiaSessao(ConsultarRelacaoClienteImovelActionForm form, HttpSession sessao) {

		FiltroClienteImovelEconomia filtroClienteImovelEconomia = criarFiltroClienteImovelEconomia(form);

		Collection<ClienteImovelEconomia> coelcaoClienteImovelEconomia = Fachada.getInstancia()
			.pesquisar(filtroClienteImovelEconomia,ClienteImovelEconomia.class.getSimpleName());

		sessao.setAttribute("collClienteImovelEconomia",coelcaoClienteImovelEconomia);
	}

	/**
	 * O método consulta os ClienteImovel do cliente informado
	 * e a partir dessa coleção cria e seta na sessão uma coleção de
	 * ConsultarClienteRelacaoClienteImovelHelper.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoRelacaoClienteImovelHelperSessao(ConsultarRelacaoClienteImovelActionForm form, HttpSession sessao) {

		FiltroClienteImovel filtroClienteImovel = criarFiltroClienteImovel(form);

		Collection<ClienteImovel> colecaoClienteImovel = Fachada.getInstancia().pesquisar(filtroClienteImovel,
				ClienteImovel.class.getName());

		if(Util.isVazioOrNulo(colecaoClienteImovel)){
			throw new ActionServletException( "atencao.cliente_relacao_imovel_nao_encontrado" );				
		}

		criarColecaoRelacaoClienteImovelHelperESetarSessao(colecaoClienteImovel, sessao);
	}

	/**
	 * Esse método cria e retorna o objeto Filtro responsável
	 * por consultar os ClienteImovelEconomia
	 * baseado nos parametros informado pelo usuario na tela de consulta.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroClienteImovelEconomia criarFiltroClienteImovelEconomia(ConsultarRelacaoClienteImovelActionForm form) {
		
		FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
		
		filtroClienteImovelEconomia.setConsultaSemLimites(true);
		
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.CLIENTE);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_SUB_CATEGORIA);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_IMOVEL_CATEGORIA);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL);
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
		filtroClienteImovelEconomia
			.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		filtroClienteImovelEconomia
				.adicionarParametro(new ParametroSimples(
						FiltroClienteImovelEconomia.CLIENTE_ID, form.getIdCliente()));

		if ( Util.verificarNaoVazio(form.getIdClienteRelacaoTipo())) {
			
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,form.getIdClienteRelacaoTipo()));
		}
		if ( Util.verificarNaoVazio(form.getIdClienteImovelFimRelacaoMotivo())) {
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO,form.getIdClienteImovelFimRelacaoMotivo()));
		}
		if ( Util.verificarNaoVazio(form.getPeriodoInicialDataInicioRelacao())) {

			Date periodoInicialDataInicioRelacao = Util
					.converteStringParaDate(form.getPeriodoInicialDataInicioRelacao());

			Date periodoFinalDataInicioRelacao = null;

			if ( !Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao())) {
				periodoFinalDataInicioRelacao = periodoInicialDataInicioRelacao;

			} else {
				periodoFinalDataInicioRelacao = Util
						.converteStringParaDate(form.getPeriodoFinalDataInicioRelacao());
			}
			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataInicioRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);

			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataInicioRelacao);
			diFim.set(Calendar.HOUR, 23);
			diFim.set(Calendar.MINUTE, 59);
			diFim.set(Calendar.SECOND, 59);

			filtroClienteImovelEconomia.adicionarParametro(new Intervalo(
					FiltroClienteImovelEconomia.DATA_INICIO_RELACAO,diInicio.getTime(), diFim.getTime()));
		}

		if ( Util.verificarNaoVazio(form.getPeriodoInicialDataFimRelacao())) {

			Date periodoInicialDataFimRelacao = Util
					.converteStringParaDate(form.getPeriodoInicialDataFimRelacao());

			Date periodoFinalDataFimRelacao = null;

			if ( !Util.verificarNaoVazio(form.getPeriodoFinalDataFimRelacao())) {
				periodoFinalDataFimRelacao = periodoInicialDataFimRelacao;

			} else {
				periodoFinalDataFimRelacao = Util
						.converteStringParaDate(form.getPeriodoFinalDataFimRelacao());
			}

			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataFimRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);

			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataFimRelacao);
			diFim.set(Calendar.HOUR, 23);
			diFim.set(Calendar.MINUTE, 59);
			diFim.set(Calendar.SECOND, 59);

			filtroClienteImovelEconomia.adicionarParametro(new Intervalo(
					FiltroClienteImovelEconomia.DATA_FIM_RELACAO, diInicio.getTime(), diFim.getTime()));
		}

		if (Util.verificarNaoVazio(form.getSituacaoRelacao())) {
			
			if(form.getSituacaoRelacao().equalsIgnoreCase("1")){
				filtroClienteImovelEconomia
				.adicionarParametro(new ParametroNulo(FiltroClienteImovelEconomia.DATA_FIM_RELACAO));
				
			}else if (form.getSituacaoRelacao().equalsIgnoreCase("2")) {
				filtroClienteImovelEconomia
						.adicionarParametro(new ParametroNaoNulo(FiltroClienteImovelEconomia.DATA_FIM_RELACAO));
			} 		
		} 

		return filtroClienteImovelEconomia;
	}

	/**
	 * Método cria coleção de ConsultarClienteRelacaoClienteImovelHelper
	 * a partir da coleção de ClienteImovel passado como parametro
	 * e então seta essa coleção na Sessão.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 *
	 * Alteração que contabiliza a quantidade de imovéis  
	 * diferentes que o cliente possui.
	 * 
	 *@since 29/07/2011
	 *@author Davi Menezes
	 */
	private void criarColecaoRelacaoClienteImovelHelperESetarSessao(Collection<ClienteImovel> colecaoClienteImovel, 
			HttpSession sessao) {
		
		Collection<ConsultarClienteRelacaoClienteImovelHelper> colecaoHelper = new ArrayList<ConsultarClienteRelacaoClienteImovelHelper>();
		Iterator<ClienteImovel> iteClienteImovel = colecaoClienteImovel.iterator();
		
		ClienteImovel clienteAnterior = null;
		
		long contador = 0;
		while(iteClienteImovel.hasNext()){
			ClienteImovel clienteImovel = iteClienteImovel.next();
			
			if(clienteAnterior == null || !clienteImovel.getImovel().equals(clienteAnterior.getImovel())){
				contador++;
			}

			String enderecoImovel = Fachada.getInstancia().pesquisarEndereco(clienteImovel.getImovel().getId());

			ConsultarClienteRelacaoClienteImovelHelper consultarClienteRelacaoClienteImovelHelper = new ConsultarClienteRelacaoClienteImovelHelper();
			consultarClienteRelacaoClienteImovelHelper.setClienteImovel(clienteImovel);
			consultarClienteRelacaoClienteImovelHelper.setEnderecoImovel(enderecoImovel);

			colecaoHelper.add(consultarClienteRelacaoClienteImovelHelper);
			
			clienteAnterior = clienteImovel;			
		}
		
		sessao.setAttribute("qtdImoveis", contador);

		sessao.setAttribute("collClienteImovel", colecaoHelper);
	}

	/**
	 * Esse método cria e retorna o objeto Filtro responsável
	 * por consultar os ClienteImovel 
	 * baseado nos parametros informado pelo usuario na tela de consulta.
	 * 
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroClienteImovel criarFiltroClienteImovel(ConsultarRelacaoClienteImovelActionForm form) {

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.setConsultaSemLimites(true);
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_ID, form.getIdCliente()));
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.LOCALIDADE);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.SETOR_COMERCIAL);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.QUADRA);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.LOGRADOURO);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.BAIRRO);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.MUNICIPIO);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CEP);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovel."
						+ FiltroImovel.UNIDADE_FEDERACAO);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovel."
						+ FiltroImovel.ENDERECO_REFERENCIA);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovel."
						+ FiltroImovel.LOGRADOURO_TIPO);
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovel."
						+ FiltroImovel.LOGRADOURO_TITULO);

		if ( Util.verificarNaoVazio(form.getIdClienteRelacaoTipo())) {
			
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO, form.getIdClienteRelacaoTipo()));
		}
		
		if (Util.verificarNaoVazio(form.getIdClienteImovelFimRelacaoMotivo())) {

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.FIM_RELACAO_MOTIVO, form.getIdClienteImovelFimRelacaoMotivo()));
		}
		if (Util.verificarNaoVazio(form.getPeriodoInicialDataInicioRelacao())) {

			Date periodoInicialDataInicioRelacao = Util.converteStringParaDate(form
							.getPeriodoInicialDataInicioRelacao());

			Date periodoFinalDataInicioRelacao = null;

			if ( !Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao())) {
				periodoFinalDataInicioRelacao = periodoInicialDataInicioRelacao;
			
			} else {
				periodoFinalDataInicioRelacao = Util.converteStringParaDate(form
								.getPeriodoFinalDataInicioRelacao());
			}
			
			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataInicioRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);

			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataInicioRelacao);
			diFim.set(Calendar.HOUR, 23);
			diFim.set(Calendar.MINUTE, 59);
			diFim.set(Calendar.SECOND, 59);

			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.DATA_INICIO_RELACAO, diInicio.getTime(), diFim.getTime()));
		}
		if ( Util.verificarNaoVazio(form.getPeriodoInicialDataFimRelacao())) {

			Date periodoInicialDataFimRelacao = Util
					.converteStringParaDate(form.getPeriodoInicialDataFimRelacao());

			Date periodoFinalDataFimRelacao = null;

			if ( !Util.verificarNaoVazio(form.getPeriodoFinalDataFimRelacao())) {
				periodoFinalDataFimRelacao = periodoInicialDataFimRelacao;

			} else {
				periodoFinalDataFimRelacao = Util.converteStringParaDate(form
								.getPeriodoFinalDataFimRelacao());
			}
			
			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataFimRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);

			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataFimRelacao);
			diFim.set(Calendar.HOUR, 23);
			diFim.set(Calendar.MINUTE, 59);
			diFim.set(Calendar.SECOND, 59);

			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.DATA_FIM_RELACAO, diInicio.getTime(), diFim.getTime()));
		}

		if ( Util.verificarNaoVazio(form.getSituacaoRelacao())) {
			
			if(form.getSituacaoRelacao().equalsIgnoreCase("1")){
				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.DATA_FIM_RELACAO));	
				
			} else if (form.getSituacaoRelacao().equalsIgnoreCase("2")) {
				filtroClienteImovel.adicionarParametro(new ParametroNaoNulo(
						FiltroClienteImovel.DATA_FIM_RELACAO));
			
			}		
		}
				
		filtroClienteImovel.adicionarParametro(new ParametroSimplesDiferenteDe("imovel.indicadorExclusao", 
		            ConstantesSistema.SIM));
		
		return filtroClienteImovel;
	}

	/**
	 * Esse método obtem o ClienteEndereco do Cliente
	 * passo como parametro e seta esse objeto na sessão
	 * e na requisição.
	 * 
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void setarClienteEnderecoSessaoERequest(Cliente cliente,
			HttpSession sessao, HttpServletRequest request) {

		FiltroClienteEndereco filtroClienteEndereco = criarFiltroClienteEndereco(cliente);
		
		Collection<ClienteEndereco> colecaoClienteEnderecos = Fachada.getInstancia()
				.pesquisar(filtroClienteEndereco,ClienteEndereco.class.getName());

		ClienteEndereco clienteEndereco = new ClienteEndereco();

		if ( !Util.isVazioOrNulo(colecaoClienteEnderecos)) {
			Iterator<ClienteEndereco> colecaoClienteEnderecosIterator = colecaoClienteEnderecos.iterator();

			while (colecaoClienteEnderecosIterator.hasNext()) {
				
				clienteEndereco = colecaoClienteEnderecosIterator.next();
				if (clienteEndereco.getIndicadorEnderecoCorrespondencia()
						.equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)) {
					
					request.setAttribute("enderecoCorrespondencia",clienteEndereco);
					break;
				}
			}
		}

		sessao.setAttribute("clienteEndereco", clienteEndereco);
	}

	/**
	 * Cria o filtro responsavel por consultar os endereços do cliente.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroClienteEndereco criarFiltroClienteEndereco(Cliente cliente) {
		
		FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
		filtroClienteEndereco.adicionarParametro(new ParametroSimples(
				FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
		
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		return filtroClienteEndereco;
	}

	/**
	 * Esse método cria o filtro
	 * responsável por consultar o cliente informado pelo usuário.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroCliente criarFiltroConsultarClienteInformadoUsuario(ConsultarRelacaoClienteImovelActionForm form) {

		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,form.getIdCliente()));

		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_ENDERECOS);
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.PROFISSAO);
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.RAMO_ATIVIDADE);
		
		return filtroCliente;
	}

	/**
	 * Remove alguns atributos da sessão que são usados na lógica 
	 * deste action.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void removerAtributosSessao(HttpSession sessao) {
		sessao.removeAttribute("imovel");
		sessao.removeAttribute("collClienteImovel");
		sessao.removeAttribute("collImovelSubCategoriaHelper");
		sessao.removeAttribute("cliente");
		sessao.removeAttribute("collClienteImovelEconomia");
	}
}