package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.FiltrarNegativadorActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioManterNegativador;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de Negativador manter
 * 
 * @author Yara Taciane
 * @created 26 de Fevereiro de 2008
 */
public class GerarRelatorioNegativadorManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosConsultaNegativacao");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarNegativadorActionForm form = (FiltrarNegativadorActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroNegativador filtro = (FiltroNegativador) sessao.getAttribute("filtroNegativador");

		// Inicio da parte que vai mandar os parametros para o relatório

		Negativador parametros = new Negativador();

		Short codigoAgente = null;
		if (form.getCodigoAgente() != null && !form.getCodigoAgente().equals("")){
			codigoAgente = Short.parseShort(form.getCodigoAgente());
		}
		
		String codigoCliente = "";
		if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("")){
			codigoCliente = form.getCodigoCliente();
		}
		
		String codigoImovel = "";
		if (form.getCodigoImovel() != null && !form.getCodigoImovel().equals("")){
			codigoImovel = form.getCodigoImovel();
		}
		
		
		String numeroInscricaoEstadual = "";
		if (form.getInscricaoEstadual() != null && !form.getInscricaoEstadual().equals("")){
			numeroInscricaoEstadual = form.getInscricaoEstadual();
		}
		
		Short indicadorUso = null;
		if (form.getAtivo() != null && !form.getAtivo().equals("")){
			indicadorUso = Short.parseShort(form.getAtivo());
		}else if(form.getInativo() != null && !form.getInativo().equals("")){
			indicadorUso = Short.parseShort(form.getInativo());
		}
		
		
		Negativador negativador = new Negativador();
		Cliente cliente = new Cliente();
		Imovel imovel = new Imovel();

		if (codigoCliente != null && !codigoCliente.equals("")) {
				FiltroNegativador filtroNegativador = new FiltroNegativador();

				filtroNegativador.adicionarParametro(new ParametroSimples(
						FiltroNegativador.NEGATIVADOR_CLIENTE, codigoCliente));
				
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");

				Collection collNegativador = fachada.pesquisar(filtroNegativador,
						Negativador.class.getName());

				if (collNegativador != null && !collNegativador.isEmpty()) {
					// A Negativador foi encontrado
					Iterator it = collNegativador.iterator();

					Negativador negativadorPesquisa = (Negativador) it.next();

					negativador.setId(negativadorPesquisa.getId());
					
					cliente.setId(negativadorPesquisa.getCliente().getId());
					cliente.setNome(negativadorPesquisa.getCliente().getNome());
			
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Negativador");
				}
			

		}
		
		
		if (codigoImovel != null && !codigoImovel.equals("")) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarParametro(new ParametroSimples(
					FiltroNegativador.NEGATIVADOR_IMOVEL, codigoImovel));
			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel");

			Collection collNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (collNegativador != null && !collNegativador.isEmpty()) {
				// A Negativador foi encontrado
				Iterator it = collNegativador.iterator();

				Negativador negativadorPesquisa = (Negativador) it.next();

				negativador.setId(negativadorPesquisa.getId());		
				
				imovel.setId(negativadorPesquisa.getImovel().getId());
		
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Negativador");
			}
		

	}
		
		
		// seta os parametros que serão mostrados no relatório

	
		parametros.setCodigoAgente(codigoAgente);	
		parametros.setCliente(cliente);	
		parametros.setImovel(imovel);	
		parametros.setNumeroInscricaoEstadual(numeroInscricaoEstadual);	
		parametros.setIndicadorUso(indicadorUso);
		
		
		
		
		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterNegativador relatorioManterNegativador = new RelatorioManterNegativador(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterNegativador.addParametro("filtroNegativador", filtro);
			relatorioManterNegativador.addParametro("negativadorParametros",parametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterNegativador.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterNegativador,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}



}
