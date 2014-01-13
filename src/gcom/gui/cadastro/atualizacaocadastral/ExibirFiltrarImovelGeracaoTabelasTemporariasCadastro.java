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
 * Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
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
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelGeracaoTabelasTemporariasCadastro extends
		GcomAction {

	private Collection colecaoPesquisa = null;
	private String idElo = null;
	private Integer idLocalidade = null;
	private String codigoSetorComercial = null;
	private String idSetorComercial = null;
	private String nomeQuadra = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelGeracaoTabelasTemporariasCadastro");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ImovelGeracaoTabelasTemporariasCadastroHelper helper = new ImovelGeracaoTabelasTemporariasCadastroHelper();

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			helper.setSugestao("1");
			helper.setImovelSituacao("1");
		}

		helper = this.carregarCampos(httpServletRequest, fachada, helper);

		this.carregarQuadraInicial(fachada, helper);

		this.carregarQuadraFinal(fachada, helper);

		this.carregarColecaoEmpresa(fachada, sessao);
		
		Integer idEmpresa = 0;
		if (helper.getFirma() != null && !helper.getFirma().equals("-1")) {
			idEmpresa = new Integer(helper.getFirma());
		}
		
		this.carregarLeiturista(fachada, sessao, idEmpresa);

		this.consultarObjetos(httpServletRequest, fachada, helper);

		this.carregarColecaoImovelPerfil(fachada, sessao, helper);

		this.carregarColecaoCategoria(fachada, sessao, helper);
		
		Integer idCategoria = 0;
		if (helper.getCategoria() != null && !helper.getCategoria().equals("-1")) {
			idCategoria = new Integer(helper.getCategoria());
		}
		
		this.carregarColecaoSubcategoria(fachada, sessao, idCategoria);

		this.carregarColecaoLigacaoAguaSituacao(fachada, sessao);

		this.carregarColecaoClienteRelacaoTipo(fachada, sessao);

		sessao.setAttribute("helper", helper);

		return retorno;
	}

	private void carregarColecaoClienteRelacaoTipo(Fachada fachada,
			HttpSession sessao) {
		FiltroClienteRelacaoTipo filtro = new FiltroClienteRelacaoTipo(FiltroClienteRelacaoTipo.DESCRICAO);
		Collection colecaoClienteRelacaoTipo = fachada.pesquisar(filtro, ClienteRelacaoTipo.class.getName());
		sessao.setAttribute("colecaoClienteRelacaoTipo", colecaoClienteRelacaoTipo);
	}

	private void carregarColecaoLigacaoAguaSituacao(Fachada fachada,
			HttpSession sessao) throws ActionServletException {
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao,
				LigacaoAguaSituacao.class.getName());
		
		if (colecaoLigacaoAguaSituacao == null || colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Ligação Agua Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		}
	}

	private void carregarColecaoSubcategoria(Fachada fachada,
			HttpSession sessao, Integer idCategoria)
			throws ActionServletException {
		
		Collection<Subcategoria> colecaoSubcategoria = null;
		
		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
		
		if (idCategoria > 0) {
			filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
		}
		
		filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);

		colecaoSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
		
		if (colecaoSubcategoria == null || colecaoSubcategoria.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Subcategoria");
		}

		sessao.setAttribute("collectionImovelSubcategoria", colecaoSubcategoria);
	}

	private void carregarColecaoCategoria(Fachada fachada, HttpSession sessao,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ActionServletException {
		Collection<Categoria> colecaoCategoria = null;
		
		if (helper.getCategoria() == null) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			
			if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Categoria");
			}

			sessao.setAttribute("collectionImovelCategoria", colecaoCategoria);
		}
	}

	private void carregarColecaoImovelPerfil(Fachada fachada, HttpSession sessao,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ActionServletException {
		Collection<ImovelPerfil> colecaoImovelPerfil = null;

		if (helper.getPerfilImovel() == null) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imóvel");
			}

			sessao.setAttribute("collectionImovelPerfil", colecaoImovelPerfil);
		}
	}

	private void carregarColecaoEmpresa(Fachada fachada,
			HttpSession sessao) throws ActionServletException {
		
		if (sessao.getAttribute("colecaoFirma") == null) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

			Collection<Empresa> colecaoFirma = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

			if ((colecaoFirma == null) || (colecaoFirma.size() == 0)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", 
						null, Empresa.class.getName());
			} else {
				sessao.setAttribute("colecaoFirma", colecaoFirma);
			}
		}
	}

	private void carregarLeiturista(Fachada fachada, HttpSession sessao,
			Integer idEmpresa) throws ActionServletException {
		
		Collection<DadosLeiturista> colecaoLeiturista = new ArrayList<DadosLeiturista>();
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
		
		if (idEmpresa > 0) {
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, idEmpresa));
		}
		
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

		Collection<Leiturista> colecao = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
		
		if (colecao == null || colecao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Leiturista");
		} else {
			for (Leiturista leiturista : colecao) {
				
				DadosLeiturista dadosLeiturista = null;
				
				if (leiturista.getFuncionario() != null) {
					dadosLeiturista = new DadosLeiturista(leiturista.getId(),
							leiturista.getFuncionario().getNome());
				} else {
					dadosLeiturista = new DadosLeiturista(leiturista.getId(),
							leiturista.getCliente().getNome());
				}
				
				colecaoLeiturista.add(dadosLeiturista);
			}
		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
	}
	
	private void carregarQuadraFinal(Fachada fachada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ActionServletException {
		if (helper.getIdQuadraFinal() != null && helper.getIdQuadraFinal().length() > 0) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, helper.getIdQuadraFinal()));

			Collection<Quadra> colecao = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecao == null || colecao.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra");
			}
		}
	}

	private void carregarQuadraInicial(Fachada fachada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ActionServletException {
		if (helper.getIdQuadraInicial() != null && helper.getIdQuadraInicial().length() > 0) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, helper.getIdQuadraInicial()));

			Collection<Quadra> colecao = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecao == null || colecao.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra");
			}
		}
	}

	private void consultarObjetos(HttpServletRequest httpServletRequest,
			Fachada fachada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws NumberFormatException {
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			case 1: // Localidade
				pesquisarLocalidade(inscricaoTipo, helper, fachada, httpServletRequest);
				break;

			case 2: // Setor Comercial
				pesquisarLocalidade(inscricaoTipo, helper, fachada, httpServletRequest);
				pesquisarSetorComercial(inscricaoTipo, helper, fachada, httpServletRequest);
				break;

			case 3: // Quadra
				pesquisarLocalidade(inscricaoTipo, helper, fachada, httpServletRequest);
				pesquisarSetorComercial(inscricaoTipo, helper, fachada, httpServletRequest);
				pesquisarQuadra(inscricaoTipo, helper, fachada, httpServletRequest);
				break;

			case 4: // Elo
				pesquisarElo(inscricaoTipo, helper, fachada, httpServletRequest);
				break;

			case 5: // Cliente
				pesquisarCliente(helper, fachada, httpServletRequest);
				break;

			case 6: // Imovel
				pesquisarImoveis(helper, fachada, httpServletRequest);

			default:
				break;
			}
		}
	}

	private ImovelGeracaoTabelasTemporariasCadastroHelper carregarCampos(
			HttpServletRequest httpServletRequest, Fachada fachada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ActionServletException {
		
		if (httpServletRequest.getParameter("menu") == null) {
			try {
				List itens = new DiskFileUpload().parseRequest(httpServletRequest);
				FileItem item = null;
				Iterator iteratorItens = itens.iterator();
				
				while (iteratorItens.hasNext()) {
					item = (FileItem) iteratorItens.next();

					if (item.getFieldName().equals("matricula") && !item.getString().equals("")) {
						helper.setMatricula(item.getString());
					}

					if (item.getFieldName().equals("cliente") && !item.getString().equals("")) {
						helper.setCliente(item.getString());
					}

					if (item.getFieldName().equals("sugestao") && !item.getString().equals("")) {
						helper.setSugestao(item.getString());
					}

					if (item.getFieldName().equals("firma") && !item.getString().equals("-1")) {
						
						helper.setFirma(item.getString());
						
						FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
						filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
								helper.getFirma()));
						Collection<Empresa> colecaoEmpresa = fachada.pesquisar(
								filtroEmpresa, Empresa.class.getName());
						Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

						helper.setNomeFirma(empresa.getDescricao());
					}

					if (item.getFieldName().equals("quantidadeMaxima") && !item.getString().equals("")) {
						helper.setQuantidadeMaxima(new Integer(item.getString()));
					}

					if (item.getFieldName().equals("elo") && !item.getString().equals("")) {
						helper.setElo(item.getString());
					}

					if (item.getFieldName().equals("nomeElo") && !item.getString().equals("")) {
						helper.setNomeElo(item.getString());
					}

					if (item.getFieldName().equals("localidadeInicial") && !item.getString().equals("")) {
						helper.setLocalidadeInicial(item.getString());
					}

					if (item.getFieldName().equals("codigoSetorComercialInicial") && !item.getString().equals("")) {
						helper.setCodigoSetorComercialInicial(item.getString());
					}

					if (item.getFieldName().equals("nomeLocalidadeInicial") && !item.getString().equals("")) {
						helper.setNomeLocalidadeInicial(item.getString());
					}

					if (item.getFieldName().equals("nomeSetorComercialInicial") && !item.getString().equals("")) {
						helper.setNomeSetorComercialInicial(item.getString());
					}

					if (item.getFieldName().equals("quadraInicial") && !item.getString().equals("")) {
						helper.setQuadraInicial(item.getString());
					}

					if (item.getFieldName().equals("localidadeFinal") && !item.getString().equals("")) {
						helper.setLocalidadeFinal(item.getString());
					}

					if (item.getFieldName().equals("codigoSetorComercialFinal") && !item.getString().equals("")) {
						helper.setCodigoSetorComercialFinal(item.getString());
					}

					if (item.getFieldName().equals("nomeLocalidadeFinal") && !item.getString().equals("")) {
						helper.setNomeLocalidadeFinal(item.getString());
					}

					if (item.getFieldName().equals("nomeSetorComercialFinal") && !item.getString().equals("")) {
						helper.setNomeSetorComercialFinal(item.getString());
					}

					if (item.getFieldName().equals("quadraFinal") && !item.getString().equals("")) {
						helper.setQuadraFinal(item.getString());
					}

					if (item.getFieldName().equals("localidadeFinal") && !item.getString().equals("")) {
						helper.setLocalidadeFinal(item.getString());
					}

					if (item.getFieldName().equals("rotaInicial") && !item.getString().equals("")) {
						helper.setRotaInicial(new Integer(item.getString()));
					}

					if (item.getFieldName().equals("rotaSequenciaInicial") && !item.getString().equals("")) {
						helper.setRotaSequenciaInicial(new Integer(item.getString()));
					}

					if (item.getFieldName().equals("rotaFinal") && !item.getString().equals("")) {
						helper.setRotaInicial(new Integer(item.getString()));
					}

					if (item.getFieldName().equals("rotaSequenciaFinal") && !item.getString().equals("")) {
						helper.setRotaSequenciaFinal(new Integer(item.getString()));
					}

					if (item.getFieldName().equals("perfilImovel") && !item.getString().equals("-1")) {
						helper.setPerfilImovel(item.getString());

						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,
								helper.getPerfilImovel()));
						
						Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
								ImovelPerfil.class.getName());
						ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);

						helper.setNomePerfilImovel(imovelPerfil.getDescricao());
					}

					if (item.getFieldName().equals("categoria") && !item.getString().equals("-1")) {
						helper.setCategoria(item.getString());

						FiltroCategoria filtroCategoria = new FiltroCategoria();
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
								helper.getCategoria()));
						
						Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria,
								Categoria.class.getName());
						Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);

						helper.setNomeCategoria(categoria.getDescricao());
					}

					if (item.getFieldName().equals("subCategoria") && !item.getString().equals("-1")) {
						helper.setSubCategoria(item.getString());

						FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
						filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,
								helper.getSubCategoria()));
						
						Collection<Subcategoria> colecaoSubcategoria = fachada.pesquisar(filtroSubCategoria,
								Subcategoria.class.getName());
						Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubcategoria);
						
						helper.setNomeSubCategoria(subcategoria.getDescricao());
					}

					if (item.getFieldName().equals("idSituacaoLigacaoAgua") && !item.getString().equals("-1")) {
						helper.setIdSituacaoLigacaoAgua(item.getString());

						FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID, helper.getIdSituacaoLigacaoAgua()));
						
						Collection<LigacaoAguaSituacao> colecaoligacaoAguaSituacao = fachada.pesquisar(
								filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
						LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(
								colecaoligacaoAguaSituacao);

						helper.setNomeSituacaoLigacaoAgua(ligacaoAguaSituacao.getDescricao());
					}

					if (item.getFieldName().equals("imovelSituacao") && !item.getString().equals("")) {
						helper.setImovelSituacao(item.getString());
					}
				}
			} catch (NumberFormatException ex) {
				throw new ActionServletException("erro.importacao.nao_concluida");
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		
		return helper;
	}

	private void pesquisarElo(String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		idElo = helper.getElo();

		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idElo));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			helper.setElo("");
			helper.setNomeElo("Elo Inexistente");

			httpServletRequest.setAttribute("corElo", "exception");
			httpServletRequest.setAttribute("nomeCampo", "elo");
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			helper.setElo(objetoLocalidade.getId().toString());
			helper.setNomeElo(objetoLocalidade.getDescricao());
			
			// Caso seja informado o Elo deve-se limpar os campos da Inscricao
			helper.setLocalidadeInicial("");
			helper.setLocalidadeFinal("");
			helper.setNomeLocalidadeInicial("");
			helper.setNomeLocalidadeFinal("");

			helper.setSetorComercialInicial("");
			helper.setSetorComercialFinal("");
			helper.setNomeSetorComercialInicial("");
			helper.setNomeSetorComercialFinal("");
			helper.setCodigoSetorComercialInicial("");
			helper.setCodigoSetorComercialFinal("");

			helper.setQuadraInicial("");
			helper.setQuadraFinal("");
			helper.setIdQuadraInicial("");
			helper.setIdQuadraFinal("");

			httpServletRequest.setAttribute("corElo", "valor");
		}
	}

	private void pesquisarLocalidade(String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			idLocalidade = new Integer(helper.getLocalidadeInicial());

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				helper.setLocalidadeInicial("");
				helper.setNomeLocalidadeInicial("Localidade inexistente");
				helper.setLocalidadeFinal("");
				helper.setNomeLocalidadeFinal("Localidade inexistente");

				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeInicial");

			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);

				helper.setLocalidadeInicial(String.valueOf(objetoLocalidade.getId()));
				helper.setNomeLocalidadeInicial(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");

				helper.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				helper.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			idLocalidade = new Integer(helper.getLocalidadeFinal());

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				helper.setLocalidadeFinal("");
				helper.setNomeLocalidadeFinal("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "localidadeFinal");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);

				helper.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				helper.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialFinal");
			}
		}
	}

	@SuppressWarnings("static-access")
	private void pesquisarSetorComercial(String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			idLocalidade = new Integer(helper.getLocalidadeFinal());

			if (idLocalidade != null && idLocalidade.SIZE > 0) {
				codigoSetorComercial = helper.getCodigoSetorComercialInicial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setCodigoSetorComercialInicial("");
					helper.setSetorComercialInicial("");
					helper.setNomeSetorComercialInicial("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");

					helper.setCodigoSetorComercialFinal("");
					helper.setSetorComercialFinal("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);

					helper.setCodigoSetorComercialInicial(String.valueOf(objetoSetorComercial.getCodigo()));
					helper.setSetorComercialInicial(String.valueOf(objetoSetorComercial.getId()));
					helper.setNomeSetorComercialInicial(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");

					helper.setCodigoSetorComercialFinal(String.valueOf(objetoSetorComercial.getCodigo()));
					helper.setSetorComercialFinal(String.valueOf(objetoSetorComercial.getId()));
					helper.setNomeSetorComercialFinal(objetoSetorComercial.getDescricao());

					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicial");
				}
			} else {
				helper.setCodigoSetorComercialInicial("");
				helper.setNomeSetorComercialInicial("Informe a localidade da inscrição de origem.");

				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			}
		} else {
			idLocalidade = new Integer(helper.getLocalidadeFinal());

			if (idLocalidade != null && idLocalidade.SIZE > 0) {
				codigoSetorComercial = helper.getCodigoSetorComercialFinal().toString();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setCodigoSetorComercialFinal("");
					helper.setSetorComercialFinal("");
					helper.setNomeSetorComercialFinal("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialFinal");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);

					httpServletRequest.setAttribute("codigoSetorComercialFinal",
							String.valueOf(objetoSetorComercial.getCodigo()));
					httpServletRequest.setAttribute("setorComercialFinal",
							String.valueOf(objetoSetorComercial.getId()));
					httpServletRequest.setAttribute("nomeSetorComercialFinal",
							objetoSetorComercial.getDescricao());

					helper.setCodigoSetorComercialFinal(String.valueOf(objetoSetorComercial.getCodigo()));
					helper.setSetorComercialFinal(String.valueOf(objetoSetorComercial.getId()));
					helper.setNomeSetorComercialFinal(objetoSetorComercial.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinal");
				}
			} else {
				helper.setCodigoSetorComercialFinal("");
				helper.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");

				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialFinal");
			}
		}
	}

	private void pesquisarQuadra(String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			codigoSetorComercial = helper.getCodigoSetorComercialInicial().toString();
			idSetorComercial = helper.getSetorComercialInicial().toString();
			String idLocalidadeInicial = helper.getLocalidadeInicial().toString();

			if (codigoSetorComercial != null && !codigoSetorComercial.trim().equalsIgnoreCase("")
					&& idSetorComercial != null && !idSetorComercial.trim().equalsIgnoreCase("")) {

				nomeQuadra = helper.getQuadraInicial();

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE,
						idLocalidadeInicial));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,
						idSetorComercial));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, nomeQuadra));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setQuadraInicial("");
					helper.setIdQuadraInicial("");
					helper.setMsgQuadraInicial("QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicial");

					helper.setQuadraFinal("");
					helper.setIdQuadraFinal("");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);

					helper.setQuadraInicial(String.valueOf(objetoQuadra.getNumeroQuadra()));
					helper.setIdQuadraInicial(String.valueOf(objetoQuadra.getId()));
					helper.setQuadraFinal(String.valueOf(objetoQuadra.getNumeroQuadra()));
					helper.setIdQuadraFinal(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", null);
				}
			} else {
				helper.setQuadraInicial("");
				helper.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "quadraInicial");
			}
		} else {
			codigoSetorComercial = helper.getCodigoSetorComercialFinal();
			idSetorComercial = helper.getSetorComercialFinal().toString();

			String idLocalidadeFinal = helper.getLocalidadeFinal().toString();

			if (codigoSetorComercial != null && !codigoSetorComercial.trim().equalsIgnoreCase("")
					&& idSetorComercial != null && !idSetorComercial.trim().equalsIgnoreCase("")) {

				nomeQuadra = helper.getQuadraFinal();

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, 
						idLocalidadeFinal));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,
						idSetorComercial));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,
						nomeQuadra));

				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					helper.setQuadraInicial("");
					helper.setIdQuadraInicial("");

					helper.setMsgQuadraFinal("QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinal");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);

					helper.setQuadraFinal(String.valueOf(objetoQuadra.getNumeroQuadra()));
					helper.setIdQuadraFinal(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", null);
				}
			} else {
				httpServletRequest.setAttribute("quadraFinal", "");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo", "quadraInicial");
			}
		}
	}

	public void pesquisarCliente(ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, helper.getCliente()));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente, Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			helper.setCliente("" + ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			helper.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());
			httpServletRequest.setAttribute("idClienteNaoEncontrado", "true");
			httpServletRequest.setAttribute("nomeCampo", "clienteNome");
		} else {
			helper.setCliente("");
			helper.setNomeCliente("CLIENTE INEXISTENTE.");
			httpServletRequest.setAttribute("idClienteNaoEncontrado", "exception");
			httpServletRequest.setAttribute("nomeCampo", "clienteNome");
		}
	}

	public void pesquisarImoveis(ImovelGeracaoTabelasTemporariasCadastroHelper helper,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, helper.getMatricula()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("clienteImoveis");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");

		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);

			helper.setMatricula(imovel.getId().toString());
			helper.setNomeMatricula(imovel.getInscricaoFormatada());
			httpServletRequest.setAttribute("matriculaNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "nomeImovel");

		} else {
			helper.setNomeMatricula("Matrícula Inexistente.");
			httpServletRequest.setAttribute("matriculaNaoEncontrada", "exception");
			httpServletRequest.setAttribute("nomeCampo", "nomeImovel");
		}
	}
}
