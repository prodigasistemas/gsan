package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelInserirManterContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirManterContaConjuntoImovel");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		FiltrarImovelContaActionForm filtrarImovelContaActionForm = (FiltrarImovelContaActionForm) actionForm;

		Collection colecaoImovel = null;

		Integer codigoCliente = null;
		Integer codigoClienteSuperior = null;
		String nomeCliente = "";
		Integer relacaoTipo = null;
		String[] bancos = null;
		Integer[] esferasPoder = null;

		if (filtrarImovelContaActionForm.getBanco() != null && !filtrarImovelContaActionForm.getBanco().equals("-1")) {
			bancos = filtrarImovelContaActionForm.getBanco();
			sessao.setAttribute("bancos", bancos);
		} else {
			sessao.removeAttribute("bancos");
		}

		/**
		 * TODO:COSANPA
		 * 
		 * @autor: Adriana Muniz
		 * @date: 24/11/2011 Acréscimo de atributo esfera de poder no filtro da
		 *        consulta
		 * */
		if (filtrarImovelContaActionForm.getEsferaPoder() != null && !filtrarImovelContaActionForm.getEsferaPoder().equals("-1")) {
			esferasPoder = filtrarImovelContaActionForm.getEsferaPoder();
			sessao.setAttribute("esferasPoder", esferasPoder);
		} else {
			sessao.removeAttribute("esferasPoder");
		}

		Boolean verificarImovelPerfilBloqueado = false;

		if ((filtrarImovelContaActionForm.getCodigoClienteSuperior() != null && !filtrarImovelContaActionForm.getCodigoClienteSuperior()
				.equals(""))
				|| (filtrarImovelContaActionForm.getCodigoCliente() != null && !filtrarImovelContaActionForm.getCodigoCliente().equals(""))) {

			if (filtrarImovelContaActionForm.getCodigoClienteSuperior() != null
					&& !filtrarImovelContaActionForm.getCodigoClienteSuperior().equals("")) {
				codigoClienteSuperior = Integer.parseInt(filtrarImovelContaActionForm.getCodigoClienteSuperior());
				relacaoTipo = new Integer("99");
			} else {
				codigoCliente = Integer.parseInt(filtrarImovelContaActionForm.getCodigoCliente());
				if (filtrarImovelContaActionForm.getTipoRelacao() != null
						&& !filtrarImovelContaActionForm.getTipoRelacao().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					relacaoTipo = Integer.parseInt(filtrarImovelContaActionForm.getTipoRelacao());
				}
			}

			// PESQUISAR CLIENTE SUPERIOR
			if (codigoClienteSuperior != null && !codigoClienteSuperior.equals("")) {
				sessao.setAttribute("codigoClienteSuperior", codigoClienteSuperior.toString());
				sessao.removeAttribute("codigoCliente");
				nomeCliente = this.pesquisarCliente(codigoClienteSuperior.toString(), fachada);

				/*
				 * Colocado por Ana Maria em 20/04/2009
				 */
				if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO))) {
					Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelClienteBloqueadoPerfil(codigoClienteSuperior, relacaoTipo);
					if (qtdImovelBloqueado != null && !qtdImovelBloqueado.equals(0)) {
						String confirmado = httpServletRequest.getParameter("confirmado");
						if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
							httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/filtrarImovelInserirManterContaAction.do");
							return montarPaginaConfirmacao("atencao.existe_imovel_perfil_bloqueado", httpServletRequest, actionMapping,
									qtdImovelBloqueado.toString());
						} else {
							verificarImovelPerfilBloqueado = true;
						}
					}
				}
				colecaoImovel = fachada.pesquisarColecaoImovelCliente(codigoClienteSuperior, relacaoTipo, verificarImovelPerfilBloqueado);
			}

			// PESQUISAR CLIENTE
			if (codigoCliente != null && !codigoCliente.equals("")) {
				sessao.setAttribute("codigoCliente", codigoCliente.toString());
				sessao.removeAttribute("codigoClienteSuperior");
				nomeCliente = this.pesquisarCliente(codigoCliente.toString(), fachada);
				/*
				 * Colocado por Ana Maria em 20/04/2009
				 */
				if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO))) {
					Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelClienteBloqueadoPerfil(codigoCliente, relacaoTipo);
					if (!qtdImovelBloqueado.equals(0)) {
						String confirmado = httpServletRequest.getParameter("confirmado");
						if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
							httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/filtrarImovelInserirManterContaAction.do");
							return montarPaginaConfirmacao("atencao.existe_imovel_perfil_bloqueado", httpServletRequest, actionMapping,
									qtdImovelBloqueado.toString());
						} else {
							verificarImovelPerfilBloqueado = true;
						}
					}
				}
				colecaoImovel = fachada.pesquisarColecaoImovelCliente(codigoCliente, relacaoTipo, verificarImovelPerfilBloqueado);
			}

			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel_inexistente_criterio_pesquisa");
			} else {
				sessao.setAttribute("colecaoImovel", colecaoImovel);
			}

			sessao.setAttribute("nomeCliente", nomeCliente);
			sessao.setAttribute("relacaoTipo", relacaoTipo);
			sessao.removeAttribute("inscricaoInicialImovel");
			sessao.removeAttribute("inscricaoDestinoImovel");

			if (sessao.getAttribute("quadraSelecionada") != null) {
				sessao.removeAttribute("quadraSelecionada");
			}

		} else {

			Imovel imovelInscricaoOrigem = new Imovel();

			// Dados da inscrição de origem
			Integer localidadeOrigemID = null;
			if (filtrarImovelContaActionForm.getLocalidadeOrigemID() != null
					&& !filtrarImovelContaActionForm.getLocalidadeOrigemID().equals("")) {
				localidadeOrigemID = Integer.parseInt(filtrarImovelContaActionForm.getLocalidadeOrigemID());

				Localidade localidadeInscricaoOrigem = new Localidade();
				localidadeInscricaoOrigem.setId(localidadeOrigemID);

				imovelInscricaoOrigem.setLocalidade(localidadeInscricaoOrigem);
			}

			Integer setorComercialOrigemCD = null;
			SetorComercial setorComercialInscricaoOrigem = new SetorComercial();
			if (filtrarImovelContaActionForm.getSetorComercialOrigemCD() != null
					&& !filtrarImovelContaActionForm.getSetorComercialOrigemCD().equals("")) {
				setorComercialOrigemCD = Integer.parseInt(filtrarImovelContaActionForm.getSetorComercialOrigemCD());

				setorComercialInscricaoOrigem.setCodigo(setorComercialOrigemCD);

				imovelInscricaoOrigem.setSetorComercial(setorComercialInscricaoOrigem);
			} else {

				setorComercialInscricaoOrigem.setCodigo(0);

				imovelInscricaoOrigem.setSetorComercial(setorComercialInscricaoOrigem);
			}

			Integer quadraOrigemNM = null;
			Quadra quadraInscricaoOrigem = new Quadra();
			if (filtrarImovelContaActionForm.getQuadraOrigemNM() != null && !filtrarImovelContaActionForm.getQuadraOrigemNM().equals("")) {
				quadraOrigemNM = Integer.parseInt(filtrarImovelContaActionForm.getQuadraOrigemNM());

				quadraInscricaoOrigem.setNumeroQuadra(quadraOrigemNM);

				imovelInscricaoOrigem.setQuadra(quadraInscricaoOrigem);
			} else {

				quadraInscricaoOrigem.setNumeroQuadra(0);

				imovelInscricaoOrigem.setQuadra(quadraInscricaoOrigem);
			}

			short loteOrigem = 0;
			if (filtrarImovelContaActionForm.getLoteOrigem() != null && !filtrarImovelContaActionForm.getLoteOrigem().equalsIgnoreCase("")) {
				loteOrigem = Short.parseShort(filtrarImovelContaActionForm.getLoteOrigem());

				imovelInscricaoOrigem.setLote(loteOrigem);

			}

			short subLoteOrigem = 0;
			if (filtrarImovelContaActionForm.getSubLoteOrigem() != null
					&& !filtrarImovelContaActionForm.getSubLoteOrigem().equalsIgnoreCase("")) {
				subLoteOrigem = Short.parseShort(filtrarImovelContaActionForm.getSubLoteOrigem());

				imovelInscricaoOrigem.setSubLote(subLoteOrigem);
			}

			Integer codigoRotaOrigem = null;
			if (filtrarImovelContaActionForm.getCodigoRotaOrigem() != null
					&& !filtrarImovelContaActionForm.getCodigoRotaOrigem().equalsIgnoreCase("")) {

				codigoRotaOrigem = new Integer(filtrarImovelContaActionForm.getCodigoRotaOrigem());
			}

			Integer sequencialRotaOrigem = null;
			if (filtrarImovelContaActionForm.getSequencialRotaOrigem() != null
					&& !filtrarImovelContaActionForm.getSequencialRotaOrigem().equalsIgnoreCase("")) {

				sequencialRotaOrigem = new Integer(filtrarImovelContaActionForm.getSequencialRotaOrigem());
			}

			Localidade localidadeOrigem = (Localidade) validarCampo(localidadeOrigemID, null, 1);
			SetorComercial setorComercialOrigem = null;
			Quadra quadraOrigem = null;

			// Validação dos campos da inscrição de origem (FS0002, FS0003,
			// FS0004)
			if (localidadeOrigem != null) {

				if (setorComercialOrigemCD != null) {

					setorComercialOrigem = (SetorComercial) validarCampo(localidadeOrigem.getId(), setorComercialOrigemCD.toString(), 2);

					if (setorComercialOrigem == null) {
						throw new ActionServletException("atencao.pesquisa.setor_origem_inexistente");
					} else {
						if (quadraOrigemNM != null) {

							quadraOrigem = (Quadra) validarCampo(setorComercialOrigem.getId(), quadraOrigemNM.toString(), 3);

							if (quadraOrigem == null) {
								throw new ActionServletException("atencao.pesquisa.quadra_origem_inexistente");
							}
						}
					}
				}
			}

			// Dados da inscrição de destino
			Imovel imovelInscricaoDestino = new Imovel();

			Integer localidadeDestinoID = null;
			if (filtrarImovelContaActionForm.getLocalidadeDestinoID() != null
					&& !filtrarImovelContaActionForm.getLocalidadeDestinoID().equals("")) {
				localidadeDestinoID = Integer.parseInt(filtrarImovelContaActionForm.getLocalidadeDestinoID());

				Localidade localidadeInscricaoDestino = new Localidade();
				localidadeInscricaoDestino.setId(localidadeDestinoID);

				imovelInscricaoDestino.setLocalidade(localidadeInscricaoDestino);
			}

			Integer setorComercialDestinoCD = null;
			SetorComercial setorComercialInscricaoDestino = new SetorComercial();
			if (filtrarImovelContaActionForm.getSetorComercialDestinoCD() != null
					&& !filtrarImovelContaActionForm.getSetorComercialDestinoCD().equals("")) {
				setorComercialDestinoCD = Integer.parseInt(filtrarImovelContaActionForm.getSetorComercialDestinoCD());

				setorComercialInscricaoDestino.setCodigo(setorComercialDestinoCD);

				imovelInscricaoDestino.setSetorComercial(setorComercialInscricaoDestino);
			} else {

				setorComercialInscricaoDestino.setCodigo(0);

				imovelInscricaoDestino.setSetorComercial(setorComercialInscricaoDestino);
			}

			// Remove as quadras selecionadas, caso não seja o mesmo setor
			// comercial das inscrições
			if (setorComercialOrigemCD != null && setorComercialDestinoCD != null
					&& !setorComercialOrigemCD.equals(setorComercialDestinoCD)) {
				if (sessao.getAttribute("quadraSelecionada") != null) {
					sessao.removeAttribute("quadraSelecionada");
				}
			}

			Integer quadraDestinoNM = null;
			Quadra quadraInscricaoDestino = new Quadra();
			if (filtrarImovelContaActionForm.getQuadraDestinoNM() != null && !filtrarImovelContaActionForm.getQuadraDestinoNM().equals("")) {
				quadraDestinoNM = Integer.parseInt(filtrarImovelContaActionForm.getQuadraDestinoNM());

				quadraInscricaoDestino.setNumeroQuadra(quadraDestinoNM);

				imovelInscricaoDestino.setQuadra(quadraInscricaoDestino);
			} else {

				quadraInscricaoDestino.setNumeroQuadra(0);

				imovelInscricaoDestino.setQuadra(quadraInscricaoDestino);
			}

			short loteDestino = 0;
			if (filtrarImovelContaActionForm.getLoteDestino() != null
					&& !filtrarImovelContaActionForm.getLoteDestino().equalsIgnoreCase("")) {
				loteDestino = Short.parseShort(filtrarImovelContaActionForm.getLoteDestino());

				imovelInscricaoDestino.setLote(loteDestino);
			}

			short subLoteDestino = 0;
			if (filtrarImovelContaActionForm.getSubLoteDestino() != null
					&& !filtrarImovelContaActionForm.getSubLoteDestino().equalsIgnoreCase("")) {
				subLoteDestino = Short.parseShort(filtrarImovelContaActionForm.getSubLoteDestino());

				imovelInscricaoDestino.setSubLote(subLoteDestino);
			}

			Integer codigoRotaDestino = null;
			if (filtrarImovelContaActionForm.getCodigoRotaDestino() != null
					&& !filtrarImovelContaActionForm.getCodigoRotaDestino().equalsIgnoreCase("")) {

				codigoRotaDestino = new Integer(filtrarImovelContaActionForm.getCodigoRotaDestino());
			}

			Integer sequencialRotaDestino = null;
			if (filtrarImovelContaActionForm.getSequencialRotaDestino() != null
					&& !filtrarImovelContaActionForm.getSequencialRotaDestino().equalsIgnoreCase("")) {

				sequencialRotaDestino = new Integer(filtrarImovelContaActionForm.getSequencialRotaDestino());
			}

			Localidade localidadeDestino = (Localidade) validarCampo(localidadeDestinoID, null, 1);
			SetorComercial setorComercialDestino = null;
			Quadra quadraDestino = null;

			// Validação dos campos da inscrição de destino (FS0002, FS0003,
			// FS0004)
			if (localidadeDestino != null) {

				if (setorComercialDestinoCD != null) {

					setorComercialDestino = (SetorComercial) validarCampo(localidadeDestino.getId(), setorComercialDestinoCD.toString(), 2);

					if (setorComercialDestino == null) {
						// Nenhum Setor Comercial encontrado
						throw new ActionServletException("atencao.pesquisa.setor_destino_inexistente");
					} else {
						if (quadraDestinoNM != null) {

							quadraDestino = (Quadra) validarCampo(setorComercialDestino.getId(), quadraDestinoNM.toString(), 3);

							if (quadraDestino == null) {
								// Nenhuma Quadra encontrada
								throw new ActionServletException("atencao.pesquisa.quadra_destino_inexistente");
							}
						}
					}
				}
			}

			/*
			 * Prepara os objetos para a pesquisa dos imoveis que estão
			 * localizados na inscrição de origem
			 */
			Integer numeroQuadraOrigem = null;
			if (quadraOrigem != null) {
				numeroQuadraOrigem = quadraOrigem.getNumeroQuadra();
			}

			Integer numeroQuadraDestino = null;
			if (quadraDestino != null) {
				numeroQuadraDestino = quadraDestino.getNumeroQuadra();
			}

			Integer setorComercialOrigemID = null;
			if (setorComercialOrigem != null) {
				setorComercialOrigemID = setorComercialOrigem.getId();
			}

			Integer setorComercialDestinoID = null;
			if (setorComercialDestino != null) {
				setorComercialDestinoID = setorComercialDestino.getId();
			}

			String[] quadras = null;
			if (sessao.getAttribute("quadraSelecionada") != null) {
				quadras = (String[]) sessao.getAttribute("quadraSelecionada");
			}

			/*
			 * Colocado por Raphael Rossiter em 02/08/2007
			 * 
			 * OBJETIVO: Acrescentar o parâmetro grupo de faturamento para o
			 * filtro de manutenção de várias contas.
			 */
			Integer idGrupoFaturamento = null;

			if (filtrarImovelContaActionForm.getIdFaturamentoGrupo() != null
					&& !filtrarImovelContaActionForm.getIdFaturamentoGrupo().equals("")) {

				idGrupoFaturamento = Integer.parseInt(filtrarImovelContaActionForm.getIdFaturamentoGrupo());
			}

			FiltrarImovelInserirManterContaHelper filtro = new FiltrarImovelInserirManterContaHelper();

			if (localidadeOrigem != null) {
				filtro.setLocalidadeOrigemID(localidadeOrigem.getId());
			}

			if (localidadeDestino != null) {
				filtro.setLocalidadeDestinoID(localidadeDestino.getId());
			}

			filtro.setSetorComercialOrigemID(setorComercialOrigemID);
			filtro.setSetorComercialDestinoID(setorComercialDestinoID);

			filtro.setQuadraOrigemID(numeroQuadraOrigem);
			filtro.setQuadraDestinoID(numeroQuadraDestino);

			filtro.setLoteOrigem(loteOrigem);
			filtro.setLoteDestino(loteDestino);

			filtro.setSubLoteOrigem(subLoteOrigem);
			filtro.setSubLoteDestino(subLoteDestino);

			filtro.setQuadras(quadras);

			filtro.setCodigoRotaOrigem(codigoRotaOrigem);
			filtro.setCodigoRotaDestino(codigoRotaDestino);

			filtro.setSequencialRotaOrigem(sequencialRotaOrigem);
			filtro.setSequencialRotaDestino(sequencialRotaDestino);

			/**
			 * TODO:COSANPA
			 * 
			 * @author: Adriana Muniz
			 * @date:24/11/2011
			 * 
			 *                  Adicionando a esfera de poder ao filtro da
			 *                  consulta
			 * */
			filtro.setEsferasPoder(esferasPoder);

			/*
			 * Colocado por Raphael Rossiter em 02/08/2007
			 * 
			 * OBJETIVO: Acrescentar o parâmetro grupo de faturamento para o
			 * filtro de manutenção de várias contas.
			 */
			filtro.setIdGrupoFaturamento(idGrupoFaturamento);

			// [FS0011] - Verificar a abrangência do usuário
			// adicionado por Vivianne Sousa
			verificarAbrangenciaUsuario(usuarioLogado, filtro, filtrarImovelContaActionForm);

			/*
			 * Colocado por Ana Maria em 20/04/2009
			 */
			if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO))) {
				Integer qtdImovelBloqueado = fachada.pesquisarColecaoImovelPerfilBloqueado(filtro);
				if (!qtdImovelBloqueado.equals(0)) {
					String confirmado = httpServletRequest.getParameter("confirmado");
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
						httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/filtrarImovelInserirManterContaAction.do");
						return montarPaginaConfirmacao("atencao.existe_imovel_perfil_bloqueado", httpServletRequest, actionMapping,
								qtdImovelBloqueado.toString());
					} else {
						filtro.setVerificarImovelPerfilBloqueado(true);
					}
				}
			}

			/**
			 * TODO:COSANPA Data: 08/04/2011
			 * 
			 * Condição acrescentada para permitir consulta por banco e pelo
			 * grupo de faturamento
			 * */
			if (bancos != null && idGrupoFaturamento != null) {
				colecaoImovel = fachada.pesquisarImoveisBancoDebitoAutomaticoEPorGrupoFaturamento(bancos, idGrupoFaturamento);
			} else if (bancos != null) {
				colecaoImovel = fachada.pesquisarImoveisBancoDebitoAutomatico(bancos);
			} else {
				colecaoImovel = fachada.pesquisarColecaoImovel(filtro);
			}

			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel_inexistente_criterio_pesquisa");
			} else {
				sessao.setAttribute("colecaoImovel", colecaoImovel);
			}

			if (imovelInscricaoOrigem.getLocalidade() != null) {
				sessao.setAttribute("inscricaoInicialImovel", imovelInscricaoOrigem.getInscricaoFormatada());
			} else {
				sessao.removeAttribute("inscricaoInicialImovel");
			}

			if (imovelInscricaoDestino.getLocalidade() != null) {
				sessao.setAttribute("inscricaoDestinoImovel", imovelInscricaoDestino.getInscricaoFormatada());
			} else {
				sessao.removeAttribute("inscricaoDestinoImovel");
			}

			if (idGrupoFaturamento != null) {
				sessao.setAttribute("grupoFaturamento", idGrupoFaturamento.toString());
			} else {
				sessao.removeAttribute("grupoFaturamento");
			}

			sessao.removeAttribute("codigoCliente");
			sessao.removeAttribute("nomeCliente");
		}

		/*
		 * Colocado por Bruno Barros em 05 de Janeiro de 2009 Verificamos se o
		 * usuário possue permissão especial para atualizar ou retificar contas
		 * pagas
		 */

		boolean usuarioPodeAtualizarRetificarContasPagas = fachada.verificarPermissaoEspecial(
				PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO));

		httpServletRequest.setAttribute("usuarioPodeAtualizarRetificarContasPagas", usuarioPodeAtualizarRetificarContasPagas);

		// *****************************************************************

		// devolve o mapeamento de retorno
		return retorno;
	}

	/**
	 * Valida os valores digitados pelo usuário
	 * 
	 * @param campoDependencia
	 * @param dependente
	 * @param tipoObjeto
	 * @return Object
	 * @throws RemoteException
	 * @throws ErroRepositorioException
	 */

	private Object validarCampo(Integer campoDependencia, String dependente, int tipoObjeto) {

		Object objeto = null;
		Collection colecaoPesquisa;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if (campoDependencia != null) {

			if (dependente == null || tipoObjeto == 1) {
				// Localidade
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, campoDependencia));

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

				if (!Util.isVazioOrNulo(colecaoPesquisa)) {
					objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
				}
			} else if (dependente != null && !dependente.equalsIgnoreCase("") && tipoObjeto > 1) {
				switch (tipoObjeto) {
				// Setor Comercial
				case 2:
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, campoDependencia));

					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
							dependente)));

					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

					if (!Util.isVazioOrNulo(colecaoPesquisa)) {
						objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
					}

					break;
				// Quadra
				case 3:
					FiltroQuadra filtroQuadra = new FiltroQuadra();

					// Objetos que serão retornados pelo hibernate
					filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");

					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, campoDependencia));

					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(dependente)));

					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

					if (!Util.isVazioOrNulo(colecaoPesquisa)) {
						objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
					}

					break;
				default:
					break;
				}
			}
		}

		return objeto;
	}

	/**
	 * Verifica a existência de imóveis no objeto passado como parâmetro Os
	 * objetos passados podem ser do tipo SetorComercial = 1 e Quadra = 2
	 * 
	 * @param objetoCondicao
	 * @param tipoObjeto
	 * @return um boleano
	 */
	/*
	 * private boolean existeImovel(Object objetoCondicao, int tipoObjeto) {
	 * 
	 * boolean retorno = false; Collection colecaoPesquisa;
	 * 
	 * //Obtém a instância da fachada Fachada fachada = Fachada.getInstancia();
	 * 
	 * if (tipoObjeto == 1) { SetorComercial setorComercial = (SetorComercial)
	 * objetoCondicao;
	 * 
	 * colecaoPesquisa = fachada.pesquisarImovel(null, setorComercial .getId(),
	 * null, null);
	 * 
	 * if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) { retorno =
	 * true; } } else { Quadra quadra = (Quadra) objetoCondicao;
	 * 
	 * colecaoPesquisa = fachada.pesquisarImovel(null, null, quadra .getId(),
	 * null);
	 * 
	 * if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) { retorno =
	 * true; } }
	 * 
	 * return retorno; }
	 */
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	public String pesquisarCliente(String idCliente, Fachada fachada) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente, Cliente.class.getName());

		Cliente cliente = null;
		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			cliente = ((Cliente) ((List) clienteEncontrado).get(0));
			// O Cliente foi encontrado
			if (cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				throw new ActionServletException("atencao.cliente.inativo", null, ""
						+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			}

		} else {
			throw new ActionServletException("atencao.cliente.inexistente");
		}

		return cliente.getNome();
	}

	/**
	 * [FS0011] - Verificar a abrangência do usuário Vivianne Sousa - 28/07/2009
	 * analista:Nelson Carvalho
	 */
	private void verificarAbrangenciaUsuario(Usuario usuarioLogado, FiltrarImovelInserirManterContaHelper filtro,
			FiltrarImovelContaActionForm form) {

		Fachada fachada = Fachada.getInstancia();
		Integer nivelAbrangencia = usuarioLogado.getUsuarioAbrangencia().getId();

		UnidadeNegocio unidadeNegocioUsuario = fachada.pesquisarUnidadeNegocioUsuario(usuarioLogado.getId());
		usuarioLogado.setUnidadeNegocio(unidadeNegocioUsuario);

		if ((form.getCodigoClienteSuperior() != null && !form.getCodigoClienteSuperior().equals(""))
				|| (form.getCodigoCliente() != null && !form.getCodigoCliente().equals(""))
				|| (form.getBanco() != null && !form.getBanco().equals("-1"))
				|| (form.getIdFaturamentoGrupo() != null && !form.getIdFaturamentoGrupo().equals(""))) {

			// caso tenha sido informado um Cliente, ou um Grupo de Faturamento,
			// ou um Banco de Débito Automático como criterio de filtro
			// o usuário deverá necessariamente possuir abrangência de Estado
			// caso contrário, exibir a mensagem
			// "Acesso a operação negado devido a abrangência do usuário."

			if (nivelAbrangencia.intValue() != UsuarioAbrangencia.ESTADO_INT) {
				throw new ActionServletException("atencao.acesso.negado.abrangencia");
			}

		}

		if ((filtro.getLocalidadeOrigemID() != null && !filtro.getLocalidadeOrigemID().equals(""))
				|| (filtro.getSetorComercialOrigemID() != null && !filtro.getSetorComercialOrigemID().equals(""))
				|| (filtro.getQuadraOrigemID() != null && !filtro.getQuadraOrigemID().equals("")) || (filtro.getQuadras() != null)
				|| (filtro.getLoteOrigem() != null && !filtro.getLoteOrigem().equals(""))
				|| (filtro.getSubLoteOrigem() != null && !filtro.getSubLoteOrigem().equals(""))
				|| (filtro.getCodigoRotaOrigem() != null && !filtro.getCodigoRotaOrigem().equals(""))) {

			if (nivelAbrangencia.intValue() != UsuarioAbrangencia.ESTADO_INT) {

				boolean existe = fachada.existeLocalidadeForaDaAbrangenciaUsuario(filtro, nivelAbrangencia, usuarioLogado);

				if (existe) {
					throw new ActionServletException("atencao.acesso.negado.abrangencia");
				}

			}

		}

	}
}
