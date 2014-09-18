package gcom.gui.micromedicao.hidrometro;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ConsultarHistoricoInstalacaoHidrometroAction extends GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = null;
		ConsultarHistoricoInstalacaoHidrometroActionForm form = (ConsultarHistoricoInstalacaoHidrometroActionForm) actionForm;
        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

		String codigoImovel = null;
		if(httpServletRequest.getParameter("codigoImovel")!=null){
			codigoImovel = httpServletRequest.getParameter("codigoImovel");
		}else{
			if (form != null) {
				codigoImovel = form.getCodigoImovel();
			}
		}
		
		String codigoHidrometro = null;
		
		if(httpServletRequest.getParameter("codigoHidrometro")!=null){
			codigoHidrometro = httpServletRequest
				.getParameter("codigoHidrometro");
		}else{
			if (form != null) {
				codigoHidrometro =form.getCodigoHidrometro();
			}
		}

		// Verifica se o usuário digitou os dois códigos
		if ((codigoImovel != null && !codigoImovel.equals(""))
				&& (codigoHidrometro != null && !codigoHidrometro.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_hidrometro.ambos");
		}

		// Verifica se o usuário não digitou código
		if ((codigoImovel != null && codigoImovel.equals(""))
				&& (codigoHidrometro != null && codigoHidrometro.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_hidrometro");
		}

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			codigoImovel = codigoImovel.trim();
			// Seta o retorno para a página que vai detalhar o imovel
			retorno = actionMapping.findForward("consultarImovel");

			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel
					.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
							codigoImovel, FiltroParametro.CONECTOR_OR));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LIGACAO_AGUA_ID, codigoImovel));

			
	
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			/**
			 * @author Adriana Muniz
			 * @data 17/07/2013
			 * 
			 * Adição dos filtros para perimetro inicial e perimetro final do endereço do imóvel
			 * Mantis 816
			 * */
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
						
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				// Manda o Imovel pelo request
				httpServletRequest.setAttribute("imovel", imovel);

				// Faz uma busca do historico de instalacao do hidrometro para
				// mostrar na pagina
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

				if (imovel.getLigacaoAgua() != null) {
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
									imovel.getId(), FiltroParametro.CONECTOR_OR));
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
									imovel.getLigacaoAgua().getId()));
				} else {
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
									imovel.getId()));
				}

											
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometro");

				Collection<HidrometroInstalacaoHistorico> hidrometrosInstalacaoHistorico = fachada
						.pesquisar(filtroHidrometroInstalacaoHistorico,
								HidrometroInstalacaoHistorico.class.getName());
							
				// Manda a colecao do historico de instalacao do hidrometro para
				// a pagina
				httpServletRequest.setAttribute(
						"hidrometrosInstalacaoHistorico",
						hidrometrosInstalacaoHistorico);
				//((HidrometroInstalacaoHistorico) hidrometrosInstalacaoHistorico.iterator().next()).getHidrometro().getNumero();
				//((HidrometroInstalacaoHistorico) hidrometrosInstalacaoHistorico.iterator().next()).getHidrometro().getId();
				sessao.setAttribute("codigoImovel",codigoImovel);
				sessao.setAttribute("codigoHidrometro","");
			}

			// Se nenhum imovel for encontrado a mensagem é enviada para a
			// página
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Matrícula");

			}

		}

		if (codigoHidrometro != null && !codigoHidrometro.trim().equals("")) {
			codigoHidrometro = codigoHidrometro.trim();
			codigoHidrometro = codigoHidrometro.toUpperCase();
			// Seta o retorno para a página que vai detalhar o hidrometro
			retorno = actionMapping.findForward("consultarHidrometro");

			// Pesquisa o hidrometro na base
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoBaixa");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroClasseMetrologica");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroTipo");

			filtroHidrometro.adicionarParametro(new ParametroSimples(
					FiltroHidrometro.NUMERO_HIDROMETRO, codigoHidrometro));

			Collection<Hidrometro> hidrometroPesquisado = fachada.pesquisar(
					filtroHidrometro, Hidrometro.class.getName());

			// Se nenhum hidrometro for encontrado a mensagem é enviada para a
			// página
			if (hidrometroPesquisado != null && hidrometroPesquisado.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Hidrômetro");

			}

			// obtem o hidrometro pesquisado
			if (hidrometroPesquisado != null && !hidrometroPesquisado.isEmpty()) {
				Hidrometro hidrometro = hidrometroPesquisado.iterator().next();

				// Manda o Hidrometro pelo request
				httpServletRequest.setAttribute("hidrometro", hidrometro);

				// Faz uma busca do historico de instalacao do hidrometro para
				// mostrar na pagina
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				filtroHidrometroInstalacaoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroInstalacaoHistorico.HIDROMETRO_ID,
								hidrometro.getId()));
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

				Collection<HidrometroInstalacaoHistorico> hidrometrosInstalacaoHistorico = fachada
						.pesquisar(filtroHidrometroInstalacaoHistorico,
								HidrometroInstalacaoHistorico.class.getName());
				// Manda a colecao do historico de instalacao do hidrometro para
				// a pagina
				httpServletRequest.setAttribute(
						"hidrometrosInstalacaoHistorico",
						hidrometrosInstalacaoHistorico);
				sessao.setAttribute("codigoHidrometro",codigoHidrometro);
				sessao.setAttribute("codigoImovel","");
				
				/** [MA2011061011]
				 * 	Autor: Paulo Diniz
				 * 	Data: 12/06/2011
				 *  InclusÃ£o do campo Data da Ãšltima MovimentaÃ§Ã£o
				 **/
				Date dataHidrometroMovimentado = fachada.pesquisarMaiorDataHidrometroMovimentado(hidrometro.getId());
				if(dataHidrometroMovimentado != null){
					httpServletRequest.setAttribute(
							"dataHidrometroMovimentacao",
							dataHidrometroMovimentado);
				}
				
			}

		}

		return retorno;

	}
}
