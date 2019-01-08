package gcom.gui.cadastro.atualizacaocadastral;

import gcom.batch.Processo;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelGeracaoTabelasTemporariasCadastroAction extends GcomAction {

	private static Fachada fachada = Fachada.getInstancia();
	private ImovelGeracaoTabelasTemporariasCadastroHelper helper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		helper = new ImovelGeracaoTabelasTemporariasCadastroHelper();

		try {
			DiskFileUpload upload = new DiskFileUpload();
			List itens = upload.parseRequest(request);
			FileItem item = null;

			Iterator iterator = itens.iterator();
			while (iterator.hasNext()) {
				item = (FileItem) iterator.next();

				this.carregarCampos(request, item);

			}
		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}

		Collection<Integer> colecaoIdsImoveis = fachada.obterIdsImovelGeracaoTabelasTemporarias(helper);

		if (colecaoIdsImoveis.size() > 0) {
			if (helper.getSugestao().equals("1")) {
				throw new ActionServletException("atencao.quantidade_imoveis_sugestao_sim", String.valueOf(colecaoIdsImoveis.size()));
			} else {
				helper.setColecaoIdsImoveis(colecaoIdsImoveis);

				Map parametros = new HashMap();
				parametros.put("imovelGeracaoTabelasTemporariasCadastroHelper", helper);

				Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, Processo.GERAR_TABELAS_TEMP_ATU_CADASTRAL, this.getUsuarioLogado(request));

				montarPaginaSucesso(request, "Geração de tabelas encaminhada para Batch.", "", "");
			}
		} else {
			throw new ActionServletException("atencao.sem_imoveis_disponiveis");
		}

		return mapping.findForward("telaSucesso");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarCampos(HttpServletRequest httpServletRequest, FileItem item) throws NumberFormatException {
		// Matricula do imovel
		if (item.getFieldName().equals("matricula") && !item.getString().equals("")) {
			helper.setMatricula(item.getString());
		}

		// Cliente
		if (item.getFieldName().equals("cliente") && !item.getString().equals("")) {
			helper.setCliente(item.getString());
		}

		// Sugestao
		if (item.getFieldName().equals("sugestao") && !item.getString().equals("")) {
			helper.setSugestao(item.getString());
		}

		// Firma(empresa)
		if (item.getFieldName().equals("firma") && !item.getString().equals("-1")) {
			helper.setFirma(item.getString());
		}

		// Leiturista(Agente Cadastral)
		if (item.getFieldName().equals("leiturista") && !item.getString().equals("-1")) {
			helper.setLeiturista(item.getString());
		}

		// Quantidade Maxima
		if (item.getFieldName().equals("quantidadeMaxima") && !item.getString().equals("")) {
			helper.setQuantidadeMaxima(new Integer(item.getString()));
		}

		// Agencia
		if (item.getFieldName().equals("elo") && !item.getString().equals("")) {
			helper.setElo(item.getString());
		}

		// Localidade Inicial
		if (item.getFieldName().equals("localidadeInicial") && !item.getString().equals("")) {
			helper.setLocalidadeInicial(item.getString());
			this.pesquisarLocalidade("origem", httpServletRequest);
		}

		// Setor Comercial Inicial
		if (item.getFieldName().equals("codigoSetorComercialInicial") && !item.getString().equals("")) {
			helper.setCodigoSetorComercialInicial(item.getString());

			if (helper.getLocalidadeInicial() != null) {
				Filtro filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, helper.getLocalidadeInicial()));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercialInicial()));

				Collection pesquisa = fachada.pesquisar(filtro, SetorComercial.class.getName());

				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					helper.setSetorComercialInicial(setorComercialInicial.getId().toString());
					this.pesquisarSetorComercial("origem", httpServletRequest);
				}
			}
		}

		// Quadra Inicial
		if (item.getFieldName().equals("quadraInicial") && !item.getString().equals("")) {
			helper.setQuadraInicial(item.getString());
			this.pesquisarQuadra("origem", httpServletRequest);
		}

		// Localidade Final
		if (item.getFieldName().equals("localidadeFinal") && !item.getString().equals("")) {
			helper.setLocalidadeFinal(item.getString());
			this.pesquisarLocalidade("destino", httpServletRequest);
		}

		// Setor Comercial Final
		if (item.getFieldName().equals("codigoSetorComercialFinal") && !item.getString().equals("")) {
			helper.setCodigoSetorComercialFinal(item.getString());

			if (helper.getLocalidadeFinal() != null) {
				Filtro filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, helper.getLocalidadeFinal()));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercialFinal()));

				Collection pesquisa = fachada.pesquisar(filtro, SetorComercial.class.getName());

				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					helper.setSetorComercialFinal(setorComercialFinal.getId().toString());
					this.pesquisarSetorComercial("destino", httpServletRequest);
				}
			}
		}

		// Quadra Final
		if (item.getFieldName().equals("quadraFinal") && !item.getString().equals("")) {
			helper.setQuadraFinal(item.getString());
			this.pesquisarQuadra("destino", httpServletRequest);
		}

		// Rota Inicial
		if (item.getFieldName().equals("rotaInicial") && !item.getString().equals("")) {

			helper.setRotaInicial(new Integer(item.getString()));

			if (helper.getSetorComercialInicial() != null) {
				Filtro filtro = new FiltroRota();
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, helper.getSetorComercialInicial()));
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, helper.getRotaInicial()));
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));

				Collection colecao = fachada.pesquisar(filtro, Rota.class.getName());

				if (colecao != null && !colecao.isEmpty()) {
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecao);
					helper.setRotaInicial(rota.getId());
				}
			}
		}

		// Sequencial Rota Inicial
		if (item.getFieldName().equals("rotaSequenciaInicial") && !item.getString().equals("")) {
			helper.setRotaSequenciaInicial(new Integer(item.getString()));
		}

		// Rota Final
		if (item.getFieldName().equals("rotaFinal") && !item.getString().equals("")) {
			helper.setRotaFinal(new Integer(item.getString()));

			if (helper.getSetorComercialFinal() != null) {
				Filtro filtro = new FiltroRota();
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, helper.getSetorComercialFinal()));
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, helper.getRotaFinal()));
				filtro.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));

				Collection colecaoRotaFinal = fachada.pesquisar(filtro, Rota.class.getName());

				if (colecaoRotaFinal != null && !colecaoRotaFinal.isEmpty()) {
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotaFinal);
					helper.setRotaFinal(rota.getId());
				}
			}
		}

		// Sequencial Rota Final
		if (item.getFieldName().equals("rotaSequenciaFinal") && !item.getString().equals("")) {
			helper.setRotaSequenciaFinal(new Integer(item.getString()));
		}

		// Perfil do Imovel
		if (item.getFieldName().equals("perfilImovel") && !item.getString().equals("-1")) {
			helper.setPerfilImovel(item.getString());
		}

		// Categoria
		if (item.getFieldName().equals("categoria") && !item.getString().equals("-1")) {
			helper.setCategoria(item.getString());
		}

		// Subcategoria
		if (item.getFieldName().equals("subCategoria") && !item.getString().equals("-1")) {
			helper.setSubCategoria(item.getString());
		}

		// Situacao Ligacao Agua
		if (item.getFieldName().equals("idSituacaoLigacaoAgua") && !item.getString().equals("-1")) {
			helper.setIdSituacaoLigacaoAgua(item.getString());
		}

		// Situacao do Imovel
		if (item.getFieldName().equals("imovelSituacao") && !item.getString().equals("")) {
			helper.setImovelSituacao(item.getString());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarLocalidade(String inscricaoTipo, HttpServletRequest request) {
		Filtro filtro = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, helper.getLocalidadeInicial()));
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection pesquisa = fachada.pesquisar(filtro, Localidade.class.getName());

			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");
			} else {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
				helper.setLocalidadeInicial(localidade.getId().toString());
				request.setAttribute("corLocalidadeOrigem", "valor");
				request.setAttribute("nomeCampo", "codigoSetorComercialInicial");
				// destino
				helper.setLocalidadeFinal(localidade.getId().toString());
				request.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, helper.getLocalidadeFinal()));
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection pesquisa = fachada.pesquisar(filtro, Localidade.class.getName());

			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");
			} else {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
				helper.setLocalidadeFinal(localidade.getId().toString());
				request.setAttribute("corLocalidadeDestino", "valor");
				request.setAttribute("nomeCampo", "codigoSetorComercialFinal");
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarSetorComercial(String inscricaoTipo, HttpServletRequest request) {
		Filtro filtro = new FiltroSetorComercial();
		String codigo = helper.getCodigoSetorComercialInicial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			String idLocalidade = helper.getLocalidadeInicial().toString();

			if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
				codigo = helper.getCodigoSetorComercialInicial();

				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigo));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection pesquisa = fachada.pesquisar(filtro, SetorComercial.class.getName());

				if (pesquisa == null || pesquisa.isEmpty()) {
					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");
				} else {
					SetorComercial setor = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);

					helper.setCodigoSetorComercialInicial(String.valueOf(setor.getCodigo()));
					request.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				request.setAttribute("codigoSetorComercialInicial", "");
				helper.setCodigoSetorComercialInicial("");
				helper.setNomeSetorComercialInicial("Informe a localidade da inscrição de origem.");
				request.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			String idLocalidade = helper.getLocalidadeFinal().toString();

			if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
				codigo = helper.getCodigoSetorComercialFinal();

				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigo));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection pesquisa = fachada.pesquisar(filtro, SetorComercial.class.getName());

				if (pesquisa == null || pesquisa.isEmpty()) {
					helper.setCodigoSetorComercialFinal("");
					helper.setSetorComercialFinal("");
					helper.setNomeSetorComercialFinal("Setor comercial inexistente.");
					request.setAttribute("corSetorComercialDestino", "exception");
					request.setAttribute("nomeCampo", "codigoSetorComercialFinal");

					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");

				} else {
					SetorComercial setor = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					helper.setCodigoSetorComercialFinal(String.valueOf(setor.getCodigo()));
					request.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				request.setAttribute("codigoSetorComercialFinal", "");
				helper.setCodigoSetorComercialFinal("");
				helper.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");
				request.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarQuadra(String inscricaoTipo, HttpServletRequest request) {
		Filtro filtro = new FiltroQuadra();

		String codigoSetor = helper.getCodigoSetorComercialInicial();
		String idSetor = helper.getSetorComercialInicial().toString();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			String idLocalidadeInicial = helper.getLocalidadeInicial().toString();

			if (codigoSetor != null && !codigoSetor.trim().equalsIgnoreCase("") && idSetor != null && !idSetor.trim().equalsIgnoreCase("")) {
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, idSetor));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, helper.getQuadraInicial()));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection pesquisa = fachada.pesquisar(filtro, Quadra.class.getName());

				if (pesquisa == null || pesquisa.isEmpty()) {
					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
				} else {
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(pesquisa);
					helper.setQuadraInicial(String.valueOf(quadra.getNumeroQuadra()));
					helper.setIdQuadraInicial(quadra.getId().toString());
					request.setAttribute("corQuadraOrigem", null);
					request.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				helper.setQuadraInicial("");
				request.setAttribute("corQuadraOrigem", "exception");
			}
		} else { // QUADRA FINAL
			codigoSetor = (String) helper.getCodigoSetorComercialFinal();
			idSetor = (String) helper.getSetorComercialFinal();

			String idLocalidadeFinal = helper.getLocalidadeFinal().toString();

			if (codigoSetor != null && !codigoSetor.trim().equalsIgnoreCase("") && idSetor != null && !idSetor.trim().equalsIgnoreCase("")) {
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, idSetor));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, helper.getQuadraFinal()));
				filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection pesquisa = fachada.pesquisar(filtro, Quadra.class.getName());

				if (pesquisa == null || pesquisa.isEmpty()) {
					helper.setQuadraFinal("");
					helper.setIdQuadraFinal("");
					request.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");
					request.setAttribute("corQuadraDestino", "exception");
					request.setAttribute("nomeCampo", "quadraDestinoNM");

					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
				} else {
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(pesquisa);
					helper.setQuadraFinal(String.valueOf(quadra.getNumeroQuadra()));
					helper.setIdQuadraFinal(quadra.getId().toString());
					request.setAttribute("corQuadraDestino", null);
				}
			} else {
				helper.setQuadraFinal("");
				request.setAttribute("corQuadraDestino", "exception");
			}
		}
	}
}
