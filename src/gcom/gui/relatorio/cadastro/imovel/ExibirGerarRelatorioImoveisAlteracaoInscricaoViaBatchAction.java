package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
 * 
 * @author Hugo Leonardo
 *
 * @date 30/12/2010
 */

public class ExibirGerarRelatorioImoveisAlteracaoInscricaoViaBatchAction extends GcomAction {
	
	private String localidadeID = null;
	private String setorComercialCD = null;
	private String setorComercialID = null;
	private Collection colecaoPesquisa = null;
	private String quadraNM = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioImoveisAlteracaoInscricaoViaBatch");
		
		GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form = 
			(GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String inscricaoTipo = (String) httpServletRequest
			.getParameter("inscricaoTipo");
		
		String objetoConsulta = (String) httpServletRequest
			.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") && 
			inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada,	httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarSetorComercial(inscricaoTipo, form, fachada, httpServletRequest);

				break;
			// Quadra
			case 3:

				this.pesquisarLocalidade(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarSetorComercial(inscricaoTipo, form, fachada, httpServletRequest);

				this.pesquisarQuadra(inscricaoTipo, form, fachada, httpServletRequest);

				break;
			default:
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarLocalidade(String inscricaoTipo, 
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
	
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				form.setLocalidadeOrigemID("");
				form.setNomeLocalidadeOrigem("Localidade Inexistente");
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("");
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeOrigemID");
			} 
			else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
				//destino
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} 
			else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}
	}
	
	/**
	 * Pesquisar Setor Comercial 
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarSetorComercial(String inscricaoTipo,
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					form.setSetorComercialOrigemCD("");
					form.setSetorComercialOrigemID("");
					form.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
					//destino
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
				} 
				else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					form.setSetorComercialOrigemCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialOrigemID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialOrigem(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
					
					//setorComercialDestino
					form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					//setorComercialDestino					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setSetorComercialOrigemCD("");
				form.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					form.setSetorComercialDestinoCD("");
					form.setSetorComercialDestinoID("");
					form.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
				} 
				else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
					form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
					form.setNomeSetorComercialDestino(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				form.setSetorComercialDestinoCD("");
				form.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	/**
	 * Pesquisar Quadra
	 *
	 * @author Hugo Leonardo
	 * @date 18/01/2011
	 */
	private void pesquisarQuadra(String inscricaoTipo,
			GerarRelatorioImoveisAlteracaoInscricaoViaBatchForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
	
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) form.getSetorComercialOrigemCD();
			
			setorComercialID = (String) form.getSetorComercialOrigemID();

			String idLocalidadeInicial = (String) form.getLocalidadeOrigemID();
			
			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") && 
				setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraOrigemNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples( FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					form.setQuadraOrigemNM("");
					form.setQuadraOrigemID("");
					// Mensagem de tela
					httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
					
					//destino
					form.setQuadraDestinoNM("");
					form.setQuadraDestinoID("");
				} 
				else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
										
					form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
	
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} 
			else {
				// Limpa o campo quadraOrigemNM do formulário
				form.setQuadraOrigemNM("");
				form.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {
			//QUADRA FINAL
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) form.getSetorComercialDestinoCD();
			setorComercialID = (String) form.getSetorComercialDestinoID();

			String idLocalidadeFinal = (String) form.getLocalidadeDestinoID();			
			
			// Os campos setorComercialOrigemCD e setorComercialID serão obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") && 
				setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraDestinoNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer( idLocalidadeFinal)));
				
				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					form.setQuadraDestinoNM("");
					form.setQuadraDestinoID("");
					// Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", null);
					httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setQuadraDestinoNM("");
				// Mensagem de tela
				form.setQuadraMensagemDestino("Informe o setor comercial da inscrição.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}
	}
	
}
