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
	private ImovelGeracaoTabelasTemporariasCadastroHelper helper = new ImovelGeracaoTabelasTemporariasCadastroHelper();
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		String linha = null;

		try {
			DiskFileUpload upload = new DiskFileUpload();
			List itens = upload.parseRequest(httpServletRequest);
			FileItem item = null;
			
			Iterator iterator = itens.iterator();
			while (iterator.hasNext()) {
				item = (FileItem) iterator.next();
				
				this.carregarCampos(httpServletRequest, item);
				
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
				
				Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros,
						Processo.GERAR_TABELAS_TEMP_ATU_CADASTRAL, this.getUsuarioLogado(httpServletRequest));
				
				montarPaginaSucesso(httpServletRequest, "Geração de tabelas encaminhada para Batch.", "", "");
			}
		} else {
			throw new ActionServletException("atencao.sem_imoveis_disponiveis");
		}

		return retorno;
	}

	private void carregarCampos(HttpServletRequest httpServletRequest,
			FileItem item) throws NumberFormatException {
		
		//Matricula do imovel
		if(item.getFieldName().equals("matricula") && !item.getString().equals("")) {
			helper.setMatricula(item.getString());
		}
		
		//Cliente
		if (item.getFieldName().equals("cliente") && !item.getString().equals("")) {
			helper.setCliente(item.getString());
		}
		
		//Sugestao
		if (item.getFieldName().equals("sugestao") && !item.getString().equals("")) {
			helper.setSugestao(item.getString());
		}
		
		//Firma(empresa)
		if (item.getFieldName().equals("firma") && !item.getString().equals("-1")) {
			helper.setFirma(item.getString());
		}
		
		//Leiturista(Agente Cadastral)
		if (item.getFieldName().equals("leiturista") && !item.getString().equals("-1")) {
			helper.setLeiturista(item.getString());
		} 
		
		//Quantidade Maxima
		if (item.getFieldName().equals("quantidadeMaxima") && !item.getString().equals("")) {
			helper.setQuantidadeMaxima(new Integer(item.getString()));
		}
		
		//Agencia
		if (item.getFieldName().equals("elo") && !item.getString().equals("")) {
			helper.setElo(item.getString());
		}
		
		//Localidade Inicial
		if (item.getFieldName().equals("localidadeInicial") && !item.getString().equals("")) {
			helper.setLocalidadeInicial(item.getString());
			this.pesquisarLocalidade("origem", httpServletRequest);
		}
		
		//Setor Comercial Inicial
		if (item.getFieldName().equals("codigoSetorComercialInicial") && !item.getString().equals("")) {
			helper.setCodigoSetorComercialInicial(item.getString());
			
			if(helper.getLocalidadeInicial() != null) {

				FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
				filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, helper.getLocalidadeInicial()));		
				filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercialInicial()));		

				Collection colecaoSetorComercialInicial = fachada.pesquisar(
						filtroSetorComercialInicial, SetorComercial.class.getName());		
				
				if(colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()) {
					SetorComercial setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(
							colecaoSetorComercialInicial);
					
					helper.setSetorComercialInicial(setorComercialInicial.getId().toString());
					
					this.pesquisarSetorComercial("origem", httpServletRequest);
				}
			}
		}

		//Quadra Inicial
		if (item.getFieldName().equals("quadraInicial") && !item.getString().equals("")) {
			helper.setQuadraInicial(item.getString());
			this.pesquisarQuadra("origem", httpServletRequest);
		}
		
		//Localidade Final
		if (item.getFieldName().equals("localidadeFinal") && !item.getString().equals("")) {
			helper.setLocalidadeFinal(item.getString());
			this.pesquisarLocalidade("destino", httpServletRequest);
		}
		
		//Setor Comercial Final
		if (item.getFieldName().equals("codigoSetorComercialFinal") && !item.getString().equals("")) {
			helper.setCodigoSetorComercialFinal(item.getString()) ;
			
			if(helper.getLocalidadeFinal() != null) {

				FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
				filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, helper.getLocalidadeFinal()));		
				filtroSetorComercialFinal.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercialFinal()));		

				Collection colecaoSetorComercialFinal = fachada.pesquisar(filtroSetorComercialFinal, 
						SetorComercial.class.getName());		
				
				if(colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()) {
					SetorComercial setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(
							colecaoSetorComercialFinal);

					helper.setSetorComercialFinal(setorComercialFinal.getId().toString());
					
					this.pesquisarSetorComercial("destino", httpServletRequest);
				}
			}
		}
		
		//Quadra Final
		if (item.getFieldName().equals("quadraFinal") && !item.getString().equals("")) {
			helper.setQuadraFinal(item.getString());
			this.pesquisarQuadra("destino", httpServletRequest);
		}
		
		//Rota Inicial
		if (item.getFieldName().equals("rotaInicial") && !item.getString().equals("")) {
			
			helper.setRotaInicial(new Integer(item.getString()));
			
			if(helper.getSetorComercialInicial() != null) {

				FiltroRota filtroRotaInicial = new FiltroRota();
				filtroRotaInicial.adicionarParametro(new ParametroSimples(
						FiltroRota.SETOR_COMERCIAL_ID, helper.getSetorComercialInicial()));		
				filtroRotaInicial.adicionarParametro(new ParametroSimples(
						FiltroRota.CODIGO_ROTA, helper.getRotaInicial()));		

				Collection colecaoRotaInicial = fachada.pesquisar(filtroRotaInicial, Rota.class.getName());		
				
				if(colecaoRotaInicial != null && !colecaoRotaInicial.isEmpty()){
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotaInicial);
					helper.setRotaInicial(rota.getId());
				}					
			}
		}
		
		//Sequencial Rota Inicial
		if (item.getFieldName().equals("rotaSequenciaInicial") && !item.getString().equals("")) {
			helper.setRotaSequenciaInicial(new Integer(item.getString()));
		}
		
		//Rota Final
		if (item.getFieldName().equals("rotaFinal") && !item.getString().equals("")) {
			helper.setRotaFinal(new Integer(item.getString()));
			
			if (helper.getSetorComercialFinal() != null) {

				FiltroRota filtroRotaFinal = new FiltroRota();
				filtroRotaFinal.adicionarParametro(new ParametroSimples(
						FiltroRota.SETOR_COMERCIAL_ID, helper.getSetorComercialFinal()));		
				filtroRotaFinal.adicionarParametro(new ParametroSimples(
						FiltroRota.CODIGO_ROTA, helper.getRotaFinal()));		

				Collection colecaoRotaFinal = fachada.pesquisar(filtroRotaFinal, Rota.class.getName());		
				
				if (colecaoRotaFinal != null && !colecaoRotaFinal.isEmpty()) {
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotaFinal);
					helper.setRotaFinal(rota.getId());
				}					
			}
		}
		
		//Sequencial Rota Final
		if (item.getFieldName().equals("rotaSequenciaFinal") && !item.getString().equals("")) {
			helper.setRotaSequenciaFinal(new Integer(item.getString()));
		}
		
		//Perfil do Imovel
		if (item.getFieldName().equals("perfilImovel") && !item.getString().equals("-1")) {
			helper.setPerfilImovel(item.getString());
		} 
		
		//Categoria 
		if (item.getFieldName().equals("categoria") && !item.getString().equals("-1")) {
			helper.setCategoria(item.getString());
		} 
		
		//Subcategoria
		if (item.getFieldName().equals("subCategoria") && !item.getString().equals("-1")) {
			helper.setSubCategoria(item.getString());
		} 
		
		//Situacao Ligacao Agua
		if (item.getFieldName().equals("idSituacaoLigacaoAgua") && !item.getString().equals("-1")) {
			helper.setIdSituacaoLigacaoAgua(item.getString());
		}
		
		//Situacao do Imovel
		if (item.getFieldName().equals("imovelSituacao") && !item.getString().equals("")) {
			helper.setImovelSituacao(item.getString());
		}
	}
	
	private void pesquisarLocalidade(String inscricaoTipo,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			String localidadeID = helper.getLocalidadeInicial().toString();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");					
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				helper.setLocalidadeInicial(objetoLocalidade.getId().toString());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				//destino
				helper.setLocalidadeFinal(objetoLocalidade.getId().toString());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			String localidadeID = helper.getLocalidadeFinal().toString();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				helper.setLocalidadeFinal(objetoLocalidade.getId().toString());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	
	private void pesquisarSetorComercial(String inscricaoTipo,
			HttpServletRequest httpServletRequest) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		String setorComercialCD = helper.getCodigoSetorComercialInicial();
		
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			String localidadeID = helper.getLocalidadeInicial().toString();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = helper.getCodigoSetorComercialInicial();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");	
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					helper.setCodigoSetorComercialInicial(String.valueOf(objetoSetorComercial.getCodigo()));
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				httpServletRequest.setAttribute("codigoSetorComercialInicial", "");
				helper.setCodigoSetorComercialInicial("");
				helper.setNomeSetorComercialInicial("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			String localidadeID = helper.getLocalidadeFinal().toString();

			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = helper.getCodigoSetorComercialFinal();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setCodigoSetorComercialFinal("");
					helper.setSetorComercialFinal("");
					helper.setNomeSetorComercialFinal("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
					
					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");
					
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					helper.setCodigoSetorComercialFinal(String.valueOf(objetoSetorComercial.getCodigo()));
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				httpServletRequest.setAttribute("codigoSetorComercialFinal", "");
				helper.setCodigoSetorComercialFinal("");
				helper.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	private void pesquisarQuadra(String inscricaoTipo,
			HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		String setorComercialCD = helper.getCodigoSetorComercialInicial();
		String setorComercialID = helper.getSetorComercialInicial().toString();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			String idLocalidadeInicial = helper.getLocalidadeInicial().toString();
			
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				String quadraNM = (String) helper.getQuadraInicial();
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					helper.setQuadraInicial(String.valueOf(objetoQuadra.getNumeroQuadra()));
					helper.setIdQuadraInicial(objetoQuadra.getId().toString());
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo","loteOrigem");
				}
			} else {
				helper.setQuadraInicial("");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else { //QUADRA FINAL
			setorComercialCD = (String) helper.getCodigoSetorComercialFinal();
			setorComercialID = (String) helper.getSetorComercialFinal();

			String idLocalidadeFinal = helper.getLocalidadeFinal().toString();			
			
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				String quadraNM = (String) helper.getQuadraFinal();

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setQuadraFinal("");
					helper.setIdQuadraFinal("");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
					
					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					helper.setQuadraFinal(String.valueOf(objetoQuadra.getNumeroQuadra()));
					helper.setIdQuadraFinal(objetoQuadra.getId().toString());
					httpServletRequest.setAttribute("corQuadraDestino", null);
				}
			} else {
				helper.setQuadraFinal("");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}
	}
}
