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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável pela pesquisa de guias de pagamento de clientes ou imóvel
 * de acordo com os parâmetros informados pelo usuário 
 *
 * @author Pedro Alexandre
 * @date 07/03/2006
 */
public class PesquisarGuiaPagamentoAction extends GcomAction {
	
	/**
	 * Consiste em pesquisar as guias de pagamento de um cliente ou um imóvel
	 * de acordo com os parâmetros informados pelo usuário
	 *
	 * [UC0249] Pesquisar Guia de Pagamento
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Pedro Alexandre
	 * @date 07/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {
		
		//Cria a variável que vai armazenar o mapeamento de retorno 
		ActionForward retorno = null;
		
		//Cria uma instância da fachada 		
		Fachada fachada = Fachada.getInstancia();
		
		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de pesqusiar guia de pagamento 
		PesquisarGuiaPagamentoActionForm pesquisarGuiaPagamentoActionForm = (PesquisarGuiaPagamentoActionForm) actionForm;
		
		//Recupera os parâmetros informados pelo usuário
		String idImovel = (String) pesquisarGuiaPagamentoActionForm.getIdImovel();
		String idCliente = (String) pesquisarGuiaPagamentoActionForm.getIdCliente();
		String dataGeracaoGuiaPagamentoInicialEmString = (String) pesquisarGuiaPagamentoActionForm.getDataEmissaoGuiaPagamentoInicial();
		String dataGeracaoGuiaPagamentoFinalEmString = (String) pesquisarGuiaPagamentoActionForm.getDataEmissaoGuiaPagamentoFinal();
		String dataVencimentoGuiaPagamentoInicialEmString = (String) pesquisarGuiaPagamentoActionForm.getDataVencimentoGuiaPagamentoInicial();
		String dataVencimentoGuiaPagamentoFinalEmString = (String) pesquisarGuiaPagamentoActionForm.getDataVencimentoGuiaPagamentoFinal();
		String[] idSituacaoGuiaPagamento = (String[]) pesquisarGuiaPagamentoActionForm.getIdSituacaoGuiaPagamento();
		String[] idTipoDebito = (String[]) pesquisarGuiaPagamentoActionForm.getIdTipoDebitoSelecionados();

		//Cria o filtro de guia de pagamento que vai armazenar os parâmetros da pesquisa 
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();

		//Seta a ordenação do resultado da pesquisa, pelo tipo de débito
        filtroGuiaPagamento.setCampoOrderBy(FiltroGuiaPagamento.DEBITO_TIPO_ID, FiltroGuiaPagamento.NUMERO_PRESTACAO_DEBITO);
        
		//Flag que vai indicar se o usuário informou ao menos um parâmetro para pesquisa
		boolean peloMenosUmParametroInformado = false;

		
		//Caso o código do imóvel for informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			//Seta o mapeamento de retorno para a tela de resultado da pesquisa de guia de pagamento 
			//de imóveis
			retorno = actionMapping.findForward("listaGuiaPagamentoImovel");
			
			//Indica que o usuário informou um parâmetro para pesquisar
			//peloMenosUmParametroInformado = true;
			
			//Cria o filtro do imóvel e seta no filtro as entidades necessárias para 
			//retornar do imóvel
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");        	
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
        	
        	//Seta no filtro o código do imóvel informado 
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	//Pesquisa o imóvel de acordo com os parâmetros setados no filtro
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o imóvel não foi encontrado, com os parâmetros informados
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        	}
        	
        	//Recupera o objeto imóvel da coleção
        	Imovel objetoImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);        	
        	
        	
        	//Cria o filtro para pesquisar ocliente do imóvel pesquisado
        	FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
        	filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.FIM_RELACAO_MOTIVO));
        	
        	//Pesquisa o relacionamento entre o imóvel e o cliente
        	Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
        	
        	//Caso nenhum cliente foi encontrado relacionado com o imóvel
        	if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
        		throw new ActionServletException(
                        "atencao.naocadastrado", null, "cliente do tipo usuário foi");
        	}
        	
        	//Recupera o relacionamento entre cliente e imóvel
        	ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
        	
        	//Seta no form todos os dados necessários para exibição do imóvel
        	pesquisarGuiaPagamentoActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
        	pesquisarGuiaPagamentoActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());
        	pesquisarGuiaPagamentoActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());        	
        	pesquisarGuiaPagamentoActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
        	
			//Seta no filtro de guia de pagamento o código do imóvel
        	filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, idImovel));
        	
        	//Caso o código do cliente for informado
		}else if(Util.verificarNaoVazio(idCliente)){
			
			//Seta o mapeamento de retorno para a tela de resultado da pesquisa de guia de pagamento 
			//de clientes
			retorno = actionMapping.findForward("listaGuiaPagamentoCliente");
			
			//Indica que o usuário informou um parâmetro para pesquisar
			//peloMenosUmParametroInformado = true;
			
			//Cria o filtro de cliente e seta as entidades necessárias para o retornar na pesquisa de cliente
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
			
			//Seta o código do cliente informado no filtro
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idCliente));
			
			//Pesquisa o cliente de acordo com os parâmetros setados no filtro 
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
        	
			//Caso o cliente não foi encontrado, com os parâmetros informados
        	if (colecaoCliente == null || colecaoCliente.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
        	}else{
        		httpServletRequest.setAttribute("idCliente",idCliente);
        	}
        	
        	//Recupera o objeto cliente da coleção
        	Cliente objetoCliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);        	
        	
        	//Seta o nome do cliente no form para exibição na página de resultado de guias de pagamento de cliente
        	pesquisarGuiaPagamentoActionForm.setNomeCliente(objetoCliente.getNome());
        	
        	//Caso o cliente for pessoa física 
        	if(objetoCliente.getCpf() != null && !objetoCliente.getCpf().equalsIgnoreCase("")){
        		//seta os dados de pessoa física no form para exibição
        		pesquisarGuiaPagamentoActionForm.setCpf_cnpj(objetoCliente.getCpfFormatado());
        		if(objetoCliente.getProfissao() != null){
        			pesquisarGuiaPagamentoActionForm.setProfissao(objetoCliente.getProfissao().getDescricao());	
        		}
        		
        		pesquisarGuiaPagamentoActionForm.setRamoAtividade("");
        	}else{
        		//Caso o cliente for pessoa jurídica 
        		//seta os dados de pessoa jurídica no form para exibição
        		pesquisarGuiaPagamentoActionForm.setCpf_cnpj(objetoCliente.getCnpjFormatado());
        		pesquisarGuiaPagamentoActionForm.setProfissao("");
        		
        		//Caso a pessoa jurídica tiver ramo de atividade informado
        		if(objetoCliente.getRamoAtividade() !=null){
        		  //seta a descrição do ramo de atividade no form para exibição
        		  pesquisarGuiaPagamentoActionForm.setRamoAtividade(objetoCliente.getRamoAtividade().getDescricao());
        		}else{
        		  //Caso a pessoa jurídica não tiver ramo de atividade informado 	
        		  //seta para vazio o ramo de atividade 	
        		  pesquisarGuiaPagamentoActionForm.setRamoAtividade("");	
        		}
        	}
        	
        	
        	//Cria o filtro para pesquisar o relacionamento entre cliente e endereço
        	//e seta todas as entidades necessárias para retornar na pesquisa
        	FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
        	
        	filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
        	filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        	filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			//Seta o código do cliente para recuperar o endereço
			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, idCliente));
			
			//Pesquisa o relacionamento entre cliente e endereço 
			Collection colecaoEnderecoCliente = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());

			//Caso existir um endereço relacionado com o cliente informado
			if (colecaoEnderecoCliente != null && !colecaoEnderecoCliente.isEmpty()) {
				//Recupera o relacionamento entre cliente e endereço da coleção retornada
				ClienteEndereco clienteEndereco = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoEnderecoCliente);
				
				//Seta no form o endereço do cliente para exibição na página de resultado da pesquisa de guias de pagamento de cliente
				pesquisarGuiaPagamentoActionForm.setEnderecoCliente(clienteEndereco.getEnderecoFormatado());
				
				//Caso não existir nenhum endereço relacionado com o cliente informado
			}else{
				//Seta para vazio o endereço de cliente para exibição
				pesquisarGuiaPagamentoActionForm.setEnderecoCliente("");
			}

			
			//Cria o filtro para pesquisar o telefone de contato do cliente informado
			FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
			filtroClienteFone.adicionarParametro(new ParametroSimples(FiltroClienteFone.CLIENTE_ID, idCliente));
			
			//Pesquisa o telefone de contato do cliente
			Collection colecaoClienteFone = fachada.pesquisar(filtroClienteFone, ClienteFone.class.getName());

			//Caso o telefone for encontrado para o cliente informado 
			if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {
				//Recupera o relacionamento entre cliente e telefone
				IClienteFone clienteFone = (IClienteFone)Util.retonarObjetoDeColecao(colecaoClienteFone);
				
				//Seta no form o nº do telefone do cliente para exibição
				pesquisarGuiaPagamentoActionForm.setTelefoneCliente(clienteFone.getTelefone());
				
				//Caso nenhum telefone for encontrado para o cliente 
			}else{
				//Seta o telefone para vazio do cliente
				pesquisarGuiaPagamentoActionForm.setTelefoneCliente("");
			}
			
			//Seta no filtro de guia de pagamento o código do cliente
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.CLIENTE_ID, idCliente));
		}else{
			//Caso não for informado nem o cliente nem o imóvel
			//levanta a exceção indicando que o usuário tem que informar o cliente ou o imóvel
			throw new ActionServletException("atencao.naoinformado", null, "Imóvel ou Cliente");
		}
		
		//Cria a uma variável que vai armazenar oformato para datas
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		//Cria a variáveis que vai armazenar as datas de geração de pagamento das guias 
		Date dataGeracaoGuiaPagamentoInicial = null;
		Date dataGeracaoGuiaPagamentoFinal = null;
		
		//Caso o usuário informou a data inicial da geração da guia de pagamento
		if (dataGeracaoGuiaPagamentoInicialEmString != null && !dataGeracaoGuiaPagamentoInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			
			//[FS0001]converte a data inicial informada no formato string para um objeto do tipo date
			try {
				dataGeracaoGuiaPagamentoInicial = formato.parse(dataGeracaoGuiaPagamentoInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.datageracaoinicial.invalida");
			}

			
			//Caso o usuário não informar a data final da geração da guia 
			if(dataGeracaoGuiaPagamentoFinalEmString == null || dataGeracaoGuiaPagamentoFinalEmString.trim().equalsIgnoreCase("")){
				
				//Replica a data inicial na data final
				dataGeracaoGuiaPagamentoFinal = dataGeracaoGuiaPagamentoInicial;
			}else{
				
				//[FS0001] Converte a data final informada no formato string para um objeto do tipo date
				try {
					dataGeracaoGuiaPagamentoFinal = formato.parse(dataGeracaoGuiaPagamentoFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.datageracaofinal.invalida");
				}
				
				//[FS0002] Caso a data final de geração for anterior que a data inicial
				if(dataGeracaoGuiaPagamentoFinal.before(dataGeracaoGuiaPagamentoInicial)){
					throw new ActionServletException("atencao.datageracaofinal.menorque");
				}
			}
		
		} else{
			//Caso a data de geração inicial não for informada, seta a data final para nula 
			dataGeracaoGuiaPagamentoFinalEmString = null;
		}

		
		//Caso existir data final de geração de guia 
		if (dataGeracaoGuiaPagamentoFinalEmString != null && !dataGeracaoGuiaPagamentoFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar
			peloMenosUmParametroInformado = true;
			
			//Seta no filtro para retornar as guias de pagamento que estiver entre a data de geração inicial e final
			filtroGuiaPagamento.adicionarParametro(new Intervalo(FiltroGuiaPagamento.EMISSAO_GUIA_PAGAMENTO, dataGeracaoGuiaPagamentoInicial, dataGeracaoGuiaPagamentoFinal));
		}
		
		
		
		//Cria a variáveis que vai armazenar as datas de geração de pagamento das guias
		Date dataVencimentoGuiaPagamentoInicial = null;
		Date dataVencimentoGuiaPagamentoFinal = null;
		
		//Caso o usuário informou a data inicial do vencimento da guia de pagamento
		if (dataVencimentoGuiaPagamentoInicialEmString != null && !dataVencimentoGuiaPagamentoInicialEmString.toString().trim().equalsIgnoreCase("")) {
			
			//[FS0001] Converte a data de vencimento inicial informada no formato string para um objeto do tipo date
			try {
				dataVencimentoGuiaPagamentoInicial = formato.parse(dataVencimentoGuiaPagamentoInicialEmString);
			} catch (ParseException e) {
				throw new ActionServletException("atencao.datavencimentoinicial.invalida");
			}

			//Caso o usuário não informar a data final do vencimento da guia 
			if(dataVencimentoGuiaPagamentoFinalEmString == null || dataVencimentoGuiaPagamentoFinalEmString.trim().equalsIgnoreCase("")){
				//a data inicial vai ser replicada na data final
				dataVencimentoGuiaPagamentoFinal = dataVencimentoGuiaPagamentoInicial;
			}else{
				
				//[FS0001] Converte a data de vencimento final informada no formato string para um objeto do tipo date
				try {
					dataVencimentoGuiaPagamentoFinal = formato.parse(dataVencimentoGuiaPagamentoFinalEmString);
				} catch (ParseException e) {
					throw new ActionServletException("atencao.datavencimentofinal.invalida");
				}
				
				//[FS0002] Caso a data final de vencimento for anterior que a data inicial
				if(dataVencimentoGuiaPagamentoFinal.before(dataVencimentoGuiaPagamentoInicial)){
					throw new ActionServletException("atencao.datavencimentofinal.menorque");
				}
			}
		
		} else{
			//Caso a data de vencimento inicial não for informada, seta a data final para nula
			dataVencimentoGuiaPagamentoFinalEmString = null;
		}

		
		//Caso existir data final de vencimento de guia 
		if (dataVencimentoGuiaPagamentoFinalEmString != null && !dataVencimentoGuiaPagamentoFinalEmString.toString().trim().equalsIgnoreCase("")) {
			//Indica que o usuário informou um parâmetro para pesquisar
			peloMenosUmParametroInformado = true;
			filtroGuiaPagamento.adicionarParametro(new Intervalo(FiltroGuiaPagamento.DATA_VENCIMENTO, dataVencimentoGuiaPagamentoInicial, dataVencimentoGuiaPagamentoFinal));
		}
		
		
		//Caso o usuário indicar os tipos de débitos para pesquisar as guias 
		if(idTipoDebito != null 
				&& !idTipoDebito[0].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& idTipoDebito.length >0){
		  //Indica que o usuário informou um parâmetro para pesquisar
		  peloMenosUmParametroInformado = true;
			
		  //Laço para setar no filtro de guia todos os tipos de débitos selecionados
		  for(int i=0; i< idTipoDebito.length; i++ ){
		    if(! (new Integer(idTipoDebito[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				  
			  if(idTipoDebito.length == 1){
			    filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID,idTipoDebito[i]));
			  }else{
			    if( i == 0 ){
				  filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID,idTipoDebito[i], ParametroSimples.CONECTOR_OR,idTipoDebito.length ));	
				}else{
				  if( i  == (idTipoDebito.length - 1) ){
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID,idTipoDebito[i]));
				  }else{
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID,idTipoDebito[i], ParametroSimples.CONECTOR_OR));
				  }
				}
			  }
			}
		  }
		}
		
		//Caso o usuário indicar as situações para pesquisar as guias 
		if(idSituacaoGuiaPagamento != null  
				&& !idSituacaoGuiaPagamento[0].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& idSituacaoGuiaPagamento.length >0){
			//Indica que o usuário informou um parâmetro para pesquisar
			peloMenosUmParametroInformado = true;
			
			//Laço para inserir no filtro todas as situações de guia de pagamento informadas 
			for(int i=0; i< idSituacaoGuiaPagamento.length; i++ ){
			  if(! (new Integer(idSituacaoGuiaPagamento[i]).equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)))){
				  
				if(idSituacaoGuiaPagamento.length==1){
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoGuiaPagamento[i]));	
				}else{
				  if( i == 0 ){
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoGuiaPagamento[i], ParametroSimples.CONECTOR_OR,idSituacaoGuiaPagamento.length ));	
				  }else{
				    if( i  == (idSituacaoGuiaPagamento.length - 1) ){
				      filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoGuiaPagamento[i]));
				    }else{
				      filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL_ID,idSituacaoGuiaPagamento[i], ParametroSimples.CONECTOR_OR));
				    }
				  }
			    }
			  }
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Seta as entidades necessárias na pesquisa de guias de pagamento
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");

		//Pesquisa a coleção de guias de pagamento de acordo com os parâmetros informados no filtro
		Collection colecaoGuiasPagamento = null;//fachada.pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroGuiaPagamento, GuiaPagamento.class.getName());
		colecaoGuiasPagamento = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		//Caso a pesquisa não retornar nenhuma guia de pagamento 
		if (colecaoGuiasPagamento == null || colecaoGuiasPagamento.isEmpty()) {
			//[FS0008]Nenhuma guia de pagamento cadastrada para o imóvel
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Guia Pagamento");
			
			//Caso a pesquisa retornar um nº de guias maior que o permitido
			//levanta a exceção para o usuário 
		} else if (colecaoGuiasPagamento.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			//[FS0007]Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			//Coloca na sessão a coleção de guias de pagamento de imóveis ou cliente 
			//pesquisadas de acordo com os dados informados no filtro
			sessao.setAttribute("colecaoGuiasPagamento", colecaoGuiasPagamento);
		}

		//retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
