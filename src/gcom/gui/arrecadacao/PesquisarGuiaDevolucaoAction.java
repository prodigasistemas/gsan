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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @create 02/03/2006
 * 
 */
public class PesquisarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarGuiaDevolucaoActionForm pesquisarGuiaDevolucaoActionForm = (PesquisarGuiaDevolucaoActionForm) actionForm;

		String codigoImovel = pesquisarGuiaDevolucaoActionForm
				.getCodigoImovel();
		String codigoCliente = pesquisarGuiaDevolucaoActionForm
				.getCodigoCliente();

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno;

		if (codigoImovel != null && !codigoImovel.equals("")) {
			retorno = actionMapping
					.findForward("pesquisarGuiaDevolucaoResultadoImovel");
		} else {
			retorno = actionMapping
					.findForward("pesquisarGuiaDevolucaoResultadoCliente");
		}

		String dataEmissaoGuiaInicio =  pesquisarGuiaDevolucaoActionForm
				.getDataEmissaoGuiaInicio();
		String dataEmissaoGuiaFim =  pesquisarGuiaDevolucaoActionForm
				.getDataEmissaoGuiaFim();
		String dataValidadeGuiaInicio =  pesquisarGuiaDevolucaoActionForm
				.getDataValidadeGuiaInicio();
		String dataValidadeGuiaFim =  pesquisarGuiaDevolucaoActionForm
				.getDataValidadeGuiaFim();
		String[] idsSituacaoGuia =  pesquisarGuiaDevolucaoActionForm
				.getSituacaoGuia();
		String[] idsTipoCredito =  pesquisarGuiaDevolucaoActionForm
				.getTipoCredito();
		String[] idsTipoDocumento =  pesquisarGuiaDevolucaoActionForm
				.getTipoDocumento();

		// Validando datas dos campos dataEmissaoGuia Inicio e Fim
		if ((dataEmissaoGuiaInicio.trim().length() == 10)
				&& (dataEmissaoGuiaFim.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(
					dataEmissaoGuiaInicio.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(
					dataEmissaoGuiaInicio.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataEmissaoGuiaInicio
					.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(
					dataEmissaoGuiaFim.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataEmissaoGuiaFim
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataEmissaoGuiaFim
					.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		// Validando datas dos campos dataValidadeGuia Inicio e Fim
		if ((dataValidadeGuiaInicio.trim().length() == 10)
				&& (dataValidadeGuiaFim.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(
					dataValidadeGuiaInicio.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(
					dataValidadeGuiaInicio.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(
					dataValidadeGuiaInicio.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(
					dataValidadeGuiaFim.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataValidadeGuiaFim
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataValidadeGuiaFim
					.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		// Passando os Parametros pros filtros...
		if ((codigoImovel != null) && (!codigoImovel.trim().equals(""))) {
			// para filtrar os dados da guia do imovel
			filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
					FiltroGuiaDevolucao.IMOVEL_ID, codigoImovel));

			// para filtrar os dados do imovel
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, codigoImovel));

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
					ClienteRelacaoTipo.USUARIO));

			filtroClienteImovel.adicionarParametro(new ParametroNulo(
					FiltroClienteImovel.DATA_FIM_RELACAO));

			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");

		} else if ((codigoCliente != null)
				&& (!codigoCliente.trim().equals(""))) {
			// para filtrar os dados da guia do cliente
			filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
					FiltroGuiaDevolucao.CLIENTE_ID, codigoCliente));

			// para filtrar os dados do cliente
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_ID, codigoCliente));

			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
			
			//  Verifica se o código do cliente foi digitado
			if (pesquisarGuiaDevolucaoActionForm.getEnderecoCliente() == null
					|| pesquisarGuiaDevolucaoActionForm.getEnderecoCliente().equalsIgnoreCase("")) 
			{
	
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
	
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(
						FiltroClienteEndereco.CLIENTE_ID, codigoCliente));
	
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
	
				Collection<ClienteEndereco> clienteEncontrado = fachada.pesquisar(filtroClienteEndereco,
						ClienteEndereco.class.getName());
	
				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				
					ClienteEndereco clienteEndereco =((List<ClienteEndereco>) clienteEncontrado).get(0);
					// O endereço do cliente foi encontrado
					if (clienteEndereco.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
						if (clienteEndereco.getCliente().getCpfFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCpfFormatado());
						}
					}
					else
					{
						if (clienteEndereco.getCliente().getCnpjFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCnpjFormatado());
						}
					}
					if (clienteEndereco.getCliente().getProfissao() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setProfissao(""
								+ clienteEndereco.getCliente().getProfissao().getDescricao());
					}
					if (clienteEndereco.getCliente().getRamoAtividade() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setRamoAtividade(""
								+ clienteEndereco.getCliente().getRamoAtividade().getDescricao());
					}
					pesquisarGuiaDevolucaoActionForm.setEnderecoCliente(""
							+ ((List<ClienteEndereco>) clienteEncontrado).get(0).getEnderecoFormatado());
					
					if (pesquisarGuiaDevolucaoActionForm.getClienteFone() == null
							|| pesquisarGuiaDevolucaoActionForm.getClienteFone().equalsIgnoreCase("")) 
					{
						FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
					
						filtroClienteFone.adicionarParametro(new ParametroSimples(
							FiltroClienteFone.CLIENTE_ID, codigoCliente));
	
					
						Collection<ClienteFone> colecaoClienteFone = fachada.pesquisar(
								filtroClienteFone, ClienteFone.class.getName());
	
						if (colecaoClienteFone != null
							&& !colecaoClienteFone.isEmpty()) {
							// O telefone foi encontrado
							pesquisarGuiaDevolucaoActionForm.setClienteFone(""
									+ (((List<ClienteFone>) colecaoClienteFone).get(0)).getTelefone());
						}
					}
				} 
			}
		}
		
		if (dataEmissaoGuiaFim.trim().equals("")) 
		{
			dataEmissaoGuiaFim = dataEmissaoGuiaInicio;
		}
		
		if (dataValidadeGuiaFim.trim().equals("")) 
		{
			dataValidadeGuiaFim = dataValidadeGuiaInicio;
		}
		
		if ((dataEmissaoGuiaInicio != null)
				&& (!dataEmissaoGuiaInicio.trim().equals(""))) {
			filtroGuiaDevolucao.adicionarParametro(new Intervalo(
					FiltroGuiaDevolucao.DATA_EMISSAO, dataEmissaoGuiaInicio,
					dataEmissaoGuiaFim));
		}
		if ((dataValidadeGuiaInicio != null)
				&& (!dataValidadeGuiaInicio.trim().equals(""))) {
			filtroGuiaDevolucao.adicionarParametro(new Intervalo(
					FiltroGuiaDevolucao.DATA_VALIDADE, dataValidadeGuiaInicio,
					dataValidadeGuiaFim));
		}
		int i = 0;
		if (idsTipoCredito != null) {

			while (i < idsTipoCredito.length) {

				if (!idsTipoCredito[i].equals("")
						&& new Integer(idsTipoCredito[i]) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					if (i + 1 < idsTipoCredito.length) {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.CREDITO_TIPO_ID,
										idsTipoCredito[i],
										ParametroSimples.CONECTOR_OR, 
										idsTipoCredito.length));
					} else {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.CREDITO_TIPO_ID,
										idsTipoCredito[i]));
					}
				}
				i++;
			}
		}

		i = 0;
		if (idsTipoDocumento != null) {

			while (i < idsTipoDocumento.length) {

				if (!idsTipoDocumento[i].equals("")
						&& new Integer(idsTipoDocumento[i]) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					if (i + 1 < idsTipoDocumento.length) {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
										idsTipoDocumento[i],
										ParametroSimples.CONECTOR_OR, 
										idsTipoDocumento.length));
					} else {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID,
										idsTipoDocumento[i]));
					}
				}
				i++;
			}
		}

		i = 0;
		if (idsSituacaoGuia != null) {

			while (i < idsSituacaoGuia.length) {

				if (!idsSituacaoGuia[i].equals("")
						&& new Integer(idsSituacaoGuia[i]) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					if (i + 1 < idsSituacaoGuia.length) {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
										idsSituacaoGuia[i],
										ParametroSimples.CONECTOR_OR,
										idsSituacaoGuia.length));
					} else {
						filtroGuiaDevolucao
								.adicionarParametro(new ParametroSimples(
										FiltroGuiaDevolucao.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
										idsSituacaoGuia[i]));
					}
				}
				i++;
			}
		}

		// adicionando caminho para o filtro da guia
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");

		// Filtrando os dados...
		Collection<GuiaDevolucao> colecaoGuiaDevolucao = null;//fachada.pesquisar(filtroGuiaDevolucao, GuiaDevolucao.class.getName());
		
		Map<String,Object> resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroGuiaDevolucao, GuiaDevolucao.class.getName());
		colecaoGuiaDevolucao = (Collection<GuiaDevolucao>) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// valida se a colecao esta vazia
		if (colecaoGuiaDevolucao == null) {
			throw new ActionServletException("atencao.colecao_vazia");
		}
		if (!colecaoGuiaDevolucao.isEmpty()) {

			// adicionando caminho para os dados do cliente/imovel
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			// Filtrando os dados...
			Collection<ClienteImovel> colecaoClienteImovel = fachada
					.pesquisar(filtroClienteImovel, ClienteImovel.class
							.getName());

			ClienteImovel dadosClienteImovel =((List<ClienteImovel>) colecaoClienteImovel).get(0);

			if ((codigoImovel != null) && (!codigoImovel.trim().equals(""))) {
				if (dadosClienteImovel.getImovel().getId() != null
						&& !dadosClienteImovel.getImovel().getId().equals(
								"")) {
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel(""
							+ dadosClienteImovel.getImovel().getId());
				}
				pesquisarGuiaDevolucaoActionForm.setInscricao(""
						+ fachada.pesquisarInscricaoImovel(new Integer(codigoImovel)));
				if (dadosClienteImovel.getImovel().getLigacaoAguaSituacao() != null) {
					pesquisarGuiaDevolucaoActionForm.setSituacaoAgua(""
							+ dadosClienteImovel.getImovel()
									.getLigacaoAguaSituacao()
									.getDescricao());
				}
				if (dadosClienteImovel.getImovel()
						.getLigacaoEsgotoSituacao() != null) {
					pesquisarGuiaDevolucaoActionForm.setSituacaoEsgoto(""
							+ dadosClienteImovel.getImovel()
									.getLigacaoEsgotoSituacao()
									.getDescricao());
				}
			}
			if ((codigoCliente != null)
					&& (!codigoCliente.trim().equals(""))) {
				if (dadosClienteImovel.getCliente() != null) {
					pesquisarGuiaDevolucaoActionForm.setNomeCliente(""
							+ dadosClienteImovel.getCliente().getNome());
					pesquisarGuiaDevolucaoActionForm.setCodigoCliente(""
							+ dadosClienteImovel.getCliente().getId());
				}
				if (dadosClienteImovel.getCliente().getClienteTipo()
						.getIndicadorPessoaFisicaJuridica() == 1) {
					if (dadosClienteImovel.getCliente().getCpfFormatado() != null) {
						pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
								+ dadosClienteImovel.getCliente()
										.getCpfFormatado());
					}
				} else {
					if (dadosClienteImovel.getCliente().getCnpjFormatado() != null) {
						pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
								+ dadosClienteImovel.getCliente()
										.getCnpjFormatado());
					}
				}
				if (dadosClienteImovel.getCliente().getProfissao() != null) {
					pesquisarGuiaDevolucaoActionForm.setProfissao(""
							+ dadosClienteImovel.getCliente()
									.getProfissao().getDescricao());
				}
				if (dadosClienteImovel.getCliente().getRamoAtividade() != null) {
					pesquisarGuiaDevolucaoActionForm.setRamoAtividade(""
							+ dadosClienteImovel.getCliente()
									.getRamoAtividade().getDescricao());
				}
			}
			if (dadosClienteImovel.getCliente() != null) {
				pesquisarGuiaDevolucaoActionForm.setNomeCliente(""
						+ dadosClienteImovel.getCliente().getNome());
			}
			// joga a colecao da Guia na sessão
			sessao.setAttribute("colecaoGuiaDevolucao",
					colecaoGuiaDevolucao);
		} else {
			throw new ActionServletException("atencao.colecao_vazia");
		}
		return retorno;
	}
}