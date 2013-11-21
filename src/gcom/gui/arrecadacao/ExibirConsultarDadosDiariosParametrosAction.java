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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

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
 * @created 19 de Maio de 2006
 */
public class ExibirConsultarDadosDiariosParametrosAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosDiariosParametros");
		
		//	obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;
		
		sessao.removeAttribute("colecaoLigacaoAgua");
		sessao.removeAttribute("colecaoImoveisPerfil");
		sessao.removeAttribute("colecaoLigacaoEsgoto");
		sessao.removeAttribute("colecaoCategoria");
		sessao.removeAttribute("colecaoEsferaPoder");
		sessao.removeAttribute("colecaoDocumentoTipo");
        
        String idGerenciaRegional = filtrarDadosDiariosArrecadacaoActionForm.getIdGerenciaRegional();
        String idElo = filtrarDadosDiariosArrecadacaoActionForm.getIdElo();
        String idLocalidade = filtrarDadosDiariosArrecadacaoActionForm.getLocalidade();
        String idArrecadador = filtrarDadosDiariosArrecadacaoActionForm.getIdArrecadador();
        String[] idsImovelPerfil = filtrarDadosDiariosArrecadacaoActionForm.getImovelPerfil();
		String[] idsLigacaoAgua = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoAgua();
		String[] idsLigacaoEsgoto = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoEsgoto();
		String[] idsCategoria = filtrarDadosDiariosArrecadacaoActionForm.getCategoria();
		String[] idsEsferaPoder = filtrarDadosDiariosArrecadacaoActionForm.getEsferaPoder();
		String[] idsDocumentosTipos = filtrarDadosDiariosArrecadacaoActionForm.getDocumentoTipo();
        
        if(idGerenciaRegional != null && !idGerenciaRegional.equals("") && !(idGerenciaRegional
						.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) 
        {
        	FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
        	filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
        			FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
				GerenciaRegional gerenciaRegional = colecaoGerenciaRegional.iterator().next();
				String gerencia = gerenciaRegional.getNomeAbreviado()+" - "+gerenciaRegional.getNome();
				filtrarDadosDiariosArrecadacaoActionForm.setNomeGerenciaRegional(gerencia);
			}
        }
        
        if(idElo != null && !idElo.equals("") && (!(idElo
				.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) 
        {
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarParametro(new ParametroSimples(
        			FiltroLocalidade.ID_ELO, idElo));

			Collection<Localidade> colecaoElo = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoElo != null && !colecaoElo.isEmpty()) {
				Localidade elo = colecaoElo.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setNomeElo(elo.getDescricao());
			}
        }
        
        if(idLocalidade != null && !idLocalidade.equals("") && !(idLocalidade
				.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) 
        {
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarParametro(new ParametroSimples(
        			FiltroLocalidade.ID, idLocalidade));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = colecaoLocalidade.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade(localidade.getDescricao());
			}
        }
        if(idArrecadador != null && !idArrecadador.equals("") && !(idArrecadador
				.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) 
        {
        	FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
        	filtroArrecadador.adicionarParametro(new ParametroSimples(
        			FiltroArrecadador.ID, idArrecadador));
        	filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {
				Arrecadador arrecadador = colecaoArrecadador.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador(arrecadador.getCliente().getNome());
			}
        }
       
        sessao.setAttribute("filtrarDadosDiariosArrecadacaoActionForm", filtrarDadosDiariosArrecadacaoActionForm);
        
        ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList<ImovelPerfil>();

		if (idsImovelPerfil != null) {
			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			int quantidadeImovelPerfilSelecionado = idsImovelPerfil.length;
			if (idsImovelPerfil[0].equals("")
					|| idsImovelPerfil[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeImovelPerfilSelecionado = quantidadeImovelPerfilSelecionado -1;  
			}
			if (quantidadeImovelPerfilSelecionado > 0){
				for (int i = 0; i < idsImovelPerfil.length; i++) {
					if (!idsImovelPerfil[i].equals("")
							&& !idsImovelPerfil[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsImovelPerfil.length) {
							filtroImovelPerfil
									.adicionarParametro(new ParametroSimples(
											FiltroImovelPerfil.ID, idsImovelPerfil[i],
											ConectorOr.CONECTOR_OR,
											quantidadeImovelPerfilSelecionado));
	
						} else {
							filtroImovelPerfil
									.adicionarParametro(new ParametroSimples(
											FiltroImovelPerfil.ID, idsImovelPerfil[i]));
						}
					}
				}
	
				filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
	
				Collection<ImovelPerfil> colecaoImovelPerfilPesquisa = fachada.pesquisar(
						filtroImovelPerfil, ImovelPerfil.class.getName());
	
				if (colecaoImovelPerfilPesquisa != null
						&& !colecaoImovelPerfilPesquisa.isEmpty()) {
					colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
				}
			} else {
				imovelPerfilColecao.setDescricao("TODOS");			
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		sessao.setAttribute("colecaoImovelPerfilResultado", colecaoImovelPerfil);
		
        //Collection colecaoLigacaoAgua = new ArrayList();
		
        LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = new ArrayList<LigacaoAguaSituacao>();

		if (idsLigacaoAgua != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			int quantidadeLigacaoAguaSelecionado = idsLigacaoAgua.length;
			if (idsLigacaoAgua[0].equals("")
					|| idsLigacaoAgua[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeLigacaoAguaSelecionado = quantidadeLigacaoAguaSelecionado -1;  
			}
			
			if (quantidadeLigacaoAguaSelecionado > 0) {
			
				for (int i = 0; i < idsLigacaoAgua.length; i++) {
					if (!idsLigacaoAgua[i].equals("")
							&& !idsLigacaoAgua[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsLigacaoAgua.length) {
							filtroLigacaoAguaSituacao
									.adicionarParametro(new ParametroSimples(
											FiltroLigacaoAguaSituacao.ID, idsLigacaoAgua[i],
											ConectorOr.CONECTOR_OR,
											quantidadeLigacaoAguaSelecionado));
	
						} else {
							filtroLigacaoAguaSituacao
									.adicionarParametro(new ParametroSimples(
											FiltroLigacaoAguaSituacao.ID, idsLigacaoAgua[i]));
						}
					}
				}
	
				filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
	
				Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
	
				if (colecaoLigacaoAguaSituacaoPesquisa != null
						&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
					colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
				}
			} else {
				ligacaoAguaSituacaoColecao.setDescricao("TODOS");	
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado", colecaoLigacaoAguaSituacao);

       // Collection colecaoLigacaoEsgoto = new ArrayList();
		
        LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = new ArrayList<LigacaoEsgotoSituacao>();

		if (idsLigacaoEsgoto != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

			int quantidadeLigacaoEsgotoSelecionado = idsLigacaoEsgoto.length;
			if (idsLigacaoEsgoto[0].equals("")
					|| idsLigacaoEsgoto[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeLigacaoEsgotoSelecionado = quantidadeLigacaoEsgotoSelecionado -1;  
			}			
			
			if (quantidadeLigacaoEsgotoSelecionado > 0){
				
				for (int i = 0; i < idsLigacaoEsgoto.length; i++) {
					if (!idsLigacaoEsgoto[i].equals("")
							&& !idsLigacaoEsgoto[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsLigacaoEsgoto.length) {
							filtroLigacaoEsgotoSituacao
									.adicionarParametro(new ParametroSimples(
											FiltroLigacaoEsgotoSituacao.ID, idsLigacaoEsgoto[i],
											ConectorOr.CONECTOR_OR,
											quantidadeLigacaoEsgotoSelecionado));
	
						} else {
							filtroLigacaoEsgotoSituacao
									.adicionarParametro(new ParametroSimples(
											FiltroLigacaoEsgotoSituacao.ID, idsLigacaoEsgoto[i]));
						}
					}
				}
	
				filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
	
				Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
	
				if (colecaoLigacaoEsgotoSituacaoPesquisa != null
						&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
					colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
				}
			} else {
				ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");	
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}

		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado", colecaoLigacaoEsgotoSituacao);
		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection<Categoria> colecaoCategoria = new ArrayList<Categoria>();

		if (idsCategoria != null) {
			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			int quantidadeCategoriaSelecionado = idsCategoria.length;
			if (idsCategoria[0].equals("")
					|| idsCategoria[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeCategoriaSelecionado = quantidadeCategoriaSelecionado -1;  
			}				
			
			if(quantidadeCategoriaSelecionado > 0){
				for (int i = 0; i < idsCategoria.length; i++) {
					if (!idsCategoria[i].equals("")
							&& !idsCategoria[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsCategoria.length) {
							filtroCategoria
									.adicionarParametro(new ParametroSimples(
											FiltroCategoria.CODIGO, idsCategoria[i],
											ConectorOr.CONECTOR_OR,
											quantidadeCategoriaSelecionado));
	
						} else {
							filtroCategoria
									.adicionarParametro(new ParametroSimples(
											FiltroCategoria.CODIGO, idsCategoria[i]));
						}
					}
				}
	
				filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
	
				Collection<Categoria> colecaoCategoriaPesquisa = fachada.pesquisar(
						filtroCategoria, Categoria.class.getName());
	
				if (colecaoCategoriaPesquisa != null
						&& !colecaoCategoriaPesquisa.isEmpty()) {
					colecaoCategoria.addAll(colecaoCategoriaPesquisa);
				}
			} else {
				categoriaColecao.setDescricao("TODOS");	
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}

		sessao.setAttribute("colecaoCategoriaResultado", colecaoCategoria);
		
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection<EsferaPoder> colecaoEsferaPoder = new ArrayList<EsferaPoder>();

		if (idsEsferaPoder != null) {
			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			int quantidadeEsferaPoderSelecionado = idsEsferaPoder.length;
			if (idsEsferaPoder[0].equals("")
					|| idsEsferaPoder[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeEsferaPoderSelecionado = quantidadeEsferaPoderSelecionado -1;  
			}			
			
			if (quantidadeEsferaPoderSelecionado > 0){
				for (int i = 0; i < idsEsferaPoder.length; i++) {
					if (!idsEsferaPoder[i].equals("")
							&& !idsEsferaPoder[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsEsferaPoder.length) {
							filtroEsferaPoder
									.adicionarParametro(new ParametroSimples(
											FiltroEsferaPoder.ID, idsEsferaPoder[i],
											ConectorOr.CONECTOR_OR,
											quantidadeEsferaPoderSelecionado));
	
						} else {
							filtroEsferaPoder
									.adicionarParametro(new ParametroSimples(
											FiltroEsferaPoder.ID, idsEsferaPoder[i]));
						}
					}
				}
	
				filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
	
				Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
						filtroEsferaPoder, EsferaPoder.class.getName());
	
				if (colecaoEsferaPoderPesquisa != null
						&& !colecaoEsferaPoderPesquisa.isEmpty()) {
					colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
				}
			} else {
				esferaPoderColecao.setDescricao("TODOS");	
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}

		sessao.setAttribute("colecaoEsferaPoderResultado", colecaoEsferaPoder);

		DocumentoTipo documentoTipoColecao = new DocumentoTipo();
		documentoTipoColecao.setId(-1);

		Collection colecaoDocumentoTipo = new ArrayList();

		if (idsDocumentosTipos != null) {
			documentoTipoColecao.setDescricaoDocumentoTipo("OPÇÕES SELECIONADAS");
			colecaoDocumentoTipo.add(documentoTipoColecao);
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();

			int quantidadeDocumentoTipoSelecionado = idsDocumentosTipos.length;
			if (idsDocumentosTipos[0].equals("")
					|| idsDocumentosTipos[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				quantidadeDocumentoTipoSelecionado = quantidadeDocumentoTipoSelecionado -1;  
			}				
			
			if (quantidadeDocumentoTipoSelecionado > 0){
				for (int i = 0; i < idsDocumentosTipos.length; i++) {
					if (!idsDocumentosTipos[i].equals("")
							&& !idsDocumentosTipos[i].equals(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (i + 1 < idsDocumentosTipos.length) {
							filtroDocumentoTipo
									.adicionarParametro(new ParametroSimples(
											FiltroDocumentoTipo.ID, idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											quantidadeDocumentoTipoSelecionado));
	
						} else {
							filtroDocumentoTipo
									.adicionarParametro(new ParametroSimples(
											FiltroDocumentoTipo.ID, idsDocumentosTipos[i]));
						}
					}
				}
	
				filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
	
				Collection colecaoDocumentoTipoPesquisa = fachada.pesquisar(
						filtroDocumentoTipo, DocumentoTipo.class.getName());
	
				if (colecaoDocumentoTipoPesquisa != null
						&& !colecaoDocumentoTipoPesquisa.isEmpty()) {
					colecaoDocumentoTipo.addAll(colecaoDocumentoTipoPesquisa);
				}
			} else {
				documentoTipoColecao.setDescricaoDocumentoTipo("TODOS");	
			}
		} else {
			documentoTipoColecao.setDescricaoDocumentoTipo("TODOS");
			colecaoDocumentoTipo.add(documentoTipoColecao);
		}

		sessao.setAttribute("colecaoDocumentoTipoResultado", colecaoDocumentoTipo);
		
 		return retorno;
	}
}