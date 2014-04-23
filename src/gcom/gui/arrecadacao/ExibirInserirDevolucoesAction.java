package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Fernanda Paiva
 * @date 21/02/2006
 */
public class ExibirInserirDevolucoesAction extends GcomAction {
	/**
	 * Action responsável pela pre-exibição da pagina de inserir
	 * devolucoes
	 *
	 * [UC0271] Inserir Devolucoes
	 *
	 * @author Fernanda Paiva
	 * @date 10/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirDevolucoes");

		Fachada fachada = Fachada.getInstancia();
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		InserirDevolucoesActionForm inserirDevolucoesActionForm = (InserirDevolucoesActionForm) actionForm;

		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));


		// -------Parte que trata do código quando o usuário tecla enter
		// Código do Cliente
		String codigoDigitadoClienteEnter = (String) inserirDevolucoesActionForm
				.getCodigoCliente();

		// Matrícula do Imóvel
		String codigoDigitadoImovelEnter = (String) inserirDevolucoesActionForm
				.getCodigoImovel();

		// Matrícula do Localidade
		String codigoDigitadoLocalidadeEnter = (String) inserirDevolucoesActionForm
				.getLocalidade();

		// Matrícula do Guia Devolucao
		String codigoDigitadoGuiaEnter = (String) inserirDevolucoesActionForm
				.getGuiaDevolucao();

		// Matrícula do DebitoTipo
		String codigoDigitadoDebitoEnter = (String) inserirDevolucoesActionForm
				.getTipoDebito();
		
		/*if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			// Verifica se o tipo da consulta de imovel é de Localidade
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"guiaDevolucao")) {

				inserirDevolucoesActionForm.setGuiaDevolucao(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				inserirDevolucoesActionForm.setGuiaDevolucaoDescricao(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercial");

			}
		}*/
		
		// Verifica se o código do imóvel foi digitado
		if (codigoDigitadoImovelEnter != null
				&& !codigoDigitadoImovelEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoImovelEnter) > 0) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoDigitadoImovelEnter));

			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
				// O imovel foi encontrado
				inserirDevolucoesActionForm.setCodigoImovel(""
						+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
				inserirDevolucoesActionForm.setCodigoImovelClone(""
						+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());				
				inserirDevolucoesActionForm.setInscricaoImovel(""
						+ ((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				
				String localidadeAntes = codigoDigitadoLocalidadeEnter;
				codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
				
				if(localidadeAntes != null && !localidadeAntes.equals(""))
				{
					String localidade1 = codigoDigitadoLocalidadeEnter;
					
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.limparListaParametros();
					
					filtroLocalidade.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, localidadeAntes));
					
					Collection localidadeEncontrada = fachada.pesquisar(filtroLocalidade,
							Localidade.class.getName());
					if (localidadeEncontrada != null && !localidadeEncontrada.isEmpty()) {
						// O imovel foi encontrado
						String localidade2 = localidadeAntes;
						
						if(!localidadeAntes.equals(codigoDigitadoLocalidadeEnter))
						{
							throw new ActionServletException(
									"atencao.pesquisa.localidade.imovel.diferente", ""
											+ localidade1, localidade2);
						}
						inserirDevolucoesActionForm.setLocalidade(""
								+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId());
						inserirDevolucoesActionForm.setLocalidadeClone(""
								+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId());
						inserirDevolucoesActionForm.setLocalidadeDescricao(""
								+ ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getDescricao());
					}else{
						String localidade2 = localidadeAntes;
						throw new ActionServletException(
								"atencao.pesquisa.localidade.imovel.diferente", ""
										+ localidade1, localidade2);
					}
				}
			} else {
				httpServletRequest.setAttribute("corImovel","exception");
               	inserirDevolucoesActionForm
               			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}
		}
		// Verifica se o do cliente código foi digitado
		if (codigoDigitadoClienteEnter != null
				&& !codigoDigitadoClienteEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoClienteEnter) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, codigoDigitadoClienteEnter));
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

				inserirDevolucoesActionForm
						.setNomeCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());
				inserirDevolucoesActionForm
						.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				inserirDevolucoesActionForm
						.setCodigoClienteClone(((Cliente) ((List) clienteEncontrado)
						.get(0)).getId().toString());

			} else {
				httpServletRequest.setAttribute("corCliente","exception");
               	inserirDevolucoesActionForm
               			.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
			}
		}

		// Verifica se o do debito tipo foi digitado
		if (codigoDigitadoDebitoEnter != null
				&& !codigoDigitadoDebitoEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoDebitoEnter) > 0) {

			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, codigoDigitadoDebitoEnter));
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection debitoEncontrado = fachada.pesquisar(filtroDebitoTipo,
					DebitoTipo.class.getName());

			if (debitoEncontrado != null && !debitoEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((DebitoTipo) ((List) debitoEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.debitoTipo.inativo",
							null, ""
									+ ((DebitoTipo) ((List) debitoEncontrado)
											.get(0)).getId());
				}

				inserirDevolucoesActionForm
						.setTipoDebito(((DebitoTipo) ((List) debitoEncontrado)
								.get(0)).getId().toString());
				inserirDevolucoesActionForm
						.setTipoDebitoClone(((DebitoTipo) ((List) debitoEncontrado)
								.get(0)).getId().toString());
				inserirDevolucoesActionForm
						.setDescricaoTipoDebito(((DebitoTipo) ((List) debitoEncontrado)
								.get(0)).getDescricao());

			} else {
				httpServletRequest.setAttribute("corTipoDebito","exception");
               	inserirDevolucoesActionForm
               			.setDescricaoTipoDebito(ConstantesSistema.CODIGO_TIPO_DEBITO_INEXISTENTE);
			}
		}

		//	Verifica se o código do imóvel foi digitado
		if (codigoDigitadoGuiaEnter != null
				&& !codigoDigitadoGuiaEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoGuiaEnter) > 0) {
			FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
			
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaDevolucao
					.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
			
			filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
					FiltroGuiaDevolucao.ID, codigoDigitadoGuiaEnter));

			Collection guiaEncontrado = fachada.pesquisar(filtroGuiaDevolucao,
					GuiaDevolucao.class.getName());

			if (guiaEncontrado != null && !guiaEncontrado.isEmpty()) {
				// A Guia foi encontrada
				Iterator iterator = guiaEncontrado.iterator();

				GuiaDevolucao guiaDevolucao = (GuiaDevolucao) iterator.next();
	            
				if (guiaDevolucao.getImovel() != null)
				{
					inserirDevolucoesActionForm.setCodigoImovel(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getId());
					inserirDevolucoesActionForm.setCodigoImovelClone(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getId());
					inserirDevolucoesActionForm.setInscricaoImovel(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getInscricaoFormatada());
					inserirDevolucoesActionForm.setLocalidade(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getId());
					inserirDevolucoesActionForm.setLocalidadeClone(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getId());
					inserirDevolucoesActionForm.setLocalidadeDescricao(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getImovel().getLocalidade().getDescricao());
				}
				if (guiaDevolucao.getCliente() != null)
				{
					inserirDevolucoesActionForm.setCodigoCliente(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getId());
					inserirDevolucoesActionForm.setCodigoClienteClone(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getId());
					inserirDevolucoesActionForm.setNomeCliente(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCliente().getNome());
				}
				
				inserirDevolucoesActionForm.setValorDevolucao(""
						+ Util.formatarMoedaReal(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getValorDevolucao()));
				
				inserirDevolucoesActionForm.setValorGuiaDevolucao(""
						+ Util.formatarMoedaReal(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getValorDevolucao()));
				if (guiaDevolucao.getCreditoTipo() != null)
				{
					inserirDevolucoesActionForm.setGuiaDevolucaoDescricao(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getCreditoTipo().getDescricao());
				}
				if (guiaDevolucao.getDebitoTipo() != null)
				{
					inserirDevolucoesActionForm.setTipoDebito(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getId());
					inserirDevolucoesActionForm.setTipoDebitoClone(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getId());
					inserirDevolucoesActionForm.setDescricaoTipoDebito(""
							+ ((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getDebitoTipo().getDescricao());
				}
				if (guiaDevolucao.getAnoMesReferenciaGuiaDevolucao() != null)
				{
					inserirDevolucoesActionForm.setReferenciaDevolucao(""
							+ Util.formatarAnoMesParaMesAno(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getAnoMesReferenciaGuiaDevolucao()));
					inserirDevolucoesActionForm.setReferenciaDevolucaoClone(""
							+ Util.formatarAnoMesParaMesAno(((GuiaDevolucao) ((List) guiaEncontrado).get(0)).getAnoMesReferenciaGuiaDevolucao()));
				}
			} else {
				httpServletRequest.setAttribute("corGuia","exception");
               	inserirDevolucoesActionForm
               			.setGuiaDevolucaoDescricao(ConstantesSistema.CODIGO_GUIA_DEVOLUCAO_INEXISTENTE);
			}
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		
		Collection localidades = new HashSet();
        
		// Verifica se a localidade foi digitada
		if (codigoDigitadoLocalidadeEnter != null
				&& !codigoDigitadoLocalidadeEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoLocalidadeEnter) > 0) {
	
			filtroLocalidade.limparListaParametros();
            //coloca parametro no filtro
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, new Integer(codigoDigitadoLocalidadeEnter)));
            //pesquisa
            localidades = fachada.pesquisar(filtroLocalidade, Localidade.class
                    .getName());
            if (localidades != null && !localidades.isEmpty()) {
                //A localidade foi encontrada
            	inserirDevolucoesActionForm.setLocalidade(((Localidade) ((List) localidades)
						.get(0)).getId().toString());
                
            	inserirDevolucoesActionForm.setLocalidadeClone(((Localidade) ((List) localidades)
						.get(0)).getId().toString());
                
            	inserirDevolucoesActionForm.setLocalidadeDescricao(((Localidade) ((List) localidades)
						.get(0)).getDescricao());

            } else {
                httpServletRequest.setAttribute("corLocalidade","exception");
               	inserirDevolucoesActionForm
                		.setLocalidadeDescricao(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
            }
		}
		httpServletRequest.setAttribute("nomeCampo","guiaDevolucao");
		return retorno;
	}
}
