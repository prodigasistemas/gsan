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
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
	private String eloID = null;
	private Integer localidadeID = null;
	private String setorComercialCD = null;
	private String setorComercialID = null;
	private String quadraNM = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelGeracaoTabelasTemporariasCadastro");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper =
			new ImovelGeracaoTabelasTemporariasCadastroHelper();
		
		//Primeira vez que entra na pagina
		if ( httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
			
			imovelGeracaoTabelasTemporariasCadastroHelper.setSugestao("1");
			imovelGeracaoTabelasTemporariasCadastroHelper.setImovelSituacao("1");
		}
		
		//
		if ( httpServletRequest.getParameter("menu") == null ) {
			
			try {
				//Classe utilizada para processamento de uploads de arquivos
				DiskFileUpload upload = new DiskFileUpload();
	
				// Parse the request
				List items = upload.parseRequest(httpServletRequest);
	
				FileItem item = null;
				
				// pega uma lista de itens do form
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					item = (FileItem) iter.next();
					
					//Matricula do imovel
					if ( item.getFieldName().equals("matricula") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setMatricula(item.getString());
					}
					
					//Cliente
					if ( item.getFieldName().equals("cliente") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setCliente(item.getString());
					}
					
					//Sugestao
					if ( item.getFieldName().equals("sugestao") && !item.getString().equals("") ) {
							imovelGeracaoTabelasTemporariasCadastroHelper.setSugestao(item.getString());
					}
					
					if ( item.getFieldName().equals("firma") && !item.getString().equals("-1") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setFirma( item.getString() );
						//Pesquisa a Descricao da Empresa
						FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
						filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getFirma()));
						Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
						Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
						
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeFirma(empresa.getDescricao());
					}
					
					if ( item.getFieldName().equals("quantidadeMaxima") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setQuantidadeMaxima( new Integer( item.getString() ));
					}
					
					//Agencia
					if ( item.getFieldName().equals("elo") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setElo(item.getString());
					}
					
					if ( item.getFieldName().equals("nomeElo") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeElo( item.getString() ); 
					}
					
					if ( item.getFieldName().equals("localidadeInicial") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial( item.getString() );
					}
					
					if ( item.getFieldName().equals("codigoSetorComercialInicial") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("nomeLocalidadeInicial") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeInicial(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("nomeSetorComercialInicial") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("quadraInicial") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial(item.getString());
					}
					
					if ( item.getFieldName().equals("localidadeFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal( item.getString() );
					}
					
					if ( item.getFieldName().equals("codigoSetorComercialFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("nomeLocalidadeFinal") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("nomeSetorComercialFinal") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal(item.getString() ) ;
					}
					
					if ( item.getFieldName().equals("quadraFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal(item.getString());
					}
					
					if ( item.getFieldName().equals("localidadeFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal(item.getString());
					}
					
					if ( item.getFieldName().equals("rotaInicial") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setRotaInicial(new Integer(item.getString()));
					}
					
					if ( item.getFieldName().equals("rotaSequenciaInicial") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setRotaSequenciaInicial(new Integer(item.getString()));
					}
					
					if ( item.getFieldName().equals("rotaFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setRotaInicial(new Integer(item.getString()));
					}
					
					if ( item.getFieldName().equals("rotaSequenciaFinal") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setRotaSequenciaFinal(new Integer(item.getString()));
					}
					
					if ( item.getFieldName().equals("perfilImovel") && !item.getString().equals("-1") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setPerfilImovel(item.getString());
						//Pesquisa a Descricao do Perfil
						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro( new ParametroSimples(FiltroImovelPerfil.ID,
								imovelGeracaoTabelasTemporariasCadastroHelper.getPerfilImovel()));
						Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
						ImovelPerfil imovelPerfil = (ImovelPerfil)  Util.retonarObjetoDeColecao(colecaoImovelPerfil);
						
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomePerfilImovel(imovelPerfil.getDescricao());
					}
					
					if ( item.getFieldName().equals("categoria") && !item.getString().equals("-1") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setCategoria(item.getString());
						//Pesquisa a Descricao da Categoria
						FiltroCategoria filtroCategoria = new FiltroCategoria();
						filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getCategoria()));
						Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
						Categoria categoria = (Categoria)Util.retonarObjetoDeColecao(colecaoCategoria);
						
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeCategoria(categoria.getDescricao());
					}
					
					if ( item.getFieldName().equals("subCategoria") && !item.getString().equals("-1") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setSubCategoria(item.getString());
						//Pesquisa a Descricao da subcategoria 
						FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
						filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getSubCategoria()));
						Collection<Subcategoria> colecaoSubCategoria = fachada.pesquisar(filtroSubCategoria, 
								Subcategoria.class.getName());
						Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubCategoria);
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSubCategoria(subcategoria.getDescricao());
					}
					
					if ( item.getFieldName().equals("idSituacaoLigacaoAgua") && !item.getString().equals("-1") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setIdSituacaoLigacaoAgua(item.getString());
						//Pesquisa a Descricao da Ligacao Situacao agua 
						FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID,
								imovelGeracaoTabelasTemporariasCadastroHelper.getIdSituacaoLigacaoAgua()));
						Collection<LigacaoAguaSituacao> colecaoligacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, 
								LigacaoAguaSituacao.class.getName());
						LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoligacaoAguaSituacao);
						
						imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSituacaoLigacaoAgua(ligacaoAguaSituacao.getDescricao());
					}
					
					if ( item.getFieldName().equals("imovelSituacao") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setImovelSituacao(item.getString());
					}
				}
			} catch (NumberFormatException ex) {
				throw new ActionServletException("erro.importacao.nao_concluida");
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}	
		}
		
		
		Collection<ImovelPerfil> collectionImovelPerfil = null;
		Collection<Categoria> collectionImovelCategoria= null;
		Collection<Subcategoria> collectionImovelSubcategoria = null;


		//Levanta exceção quando a Quadra Inicial informada é inexistente
		if ( imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraInicial() !=null  && 
			 imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraInicial().length() > 0 ) {
					
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID, imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraInicial() ));
				
			Collection<Quadra> colecao = fachada.pesquisar(filtroQuadra, Quadra.class.getName());		
			
			if(colecao == null || colecao.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra");			
			}
		}
		
		//levanta exceção quando a Quadra Final é inexistente
		if ( imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraFinal() !=null  && 
			 imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraFinal().length() > 0 ) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID, imovelGeracaoTabelasTemporariasCadastroHelper.getIdQuadraFinal() ));
			
			Collection<Quadra> colecao = fachada.pesquisar(filtroQuadra, Quadra.class.getName());		
			
			if(colecao == null || colecao.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra");			
			}
		}
		
		if(sessao.getAttribute("colecaoFirma") == null){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			
			Collection<Empresa> colecaoFirma = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			// [FS0001 - Verificar Existencia de dados]
			if ( (colecaoFirma == null) || (colecaoFirma.size() == 0) ) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			}else {
				sessao.setAttribute("colecaoFirma", colecaoFirma);
			}
		}
		
		//OBS: Procurar necessidade disso.
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");
		
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") &&
				inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {
		
			switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					pesquisarLocalidade(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, 
							fachada, httpServletRequest);
					break;
	
				// Setor Comercial
				case 2:
					pesquisarLocalidade(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					break;
				
				// Quadra
				case 3:
					pesquisarLocalidade(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					pesquisarQuadra(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					break;

				// ELO
				case 4:
					pesquisarElo(inscricaoTipo, imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
					break;
					
				//Cliente	
				case 5:
					pesquisarCliente(imovelGeracaoTabelasTemporariasCadastroHelper,fachada,httpServletRequest);
					break;
				
				//Imovel
				case 6:
					pesquisarImoveis(imovelGeracaoTabelasTemporariasCadastroHelper, fachada, httpServletRequest);
										
				default:
					break;
			}
		} 
		
		Integer categoria = 0;
		
		if (imovelGeracaoTabelasTemporariasCadastroHelper.getCategoria() != null && 
				!imovelGeracaoTabelasTemporariasCadastroHelper.getCategoria().equals("-1") ) {
			categoria = new Integer(imovelGeracaoTabelasTemporariasCadastroHelper.getCategoria());
		}
		
		// Lista o Perfil do Imovel
		if (imovelGeracaoTabelasTemporariasCadastroHelper.getPerfilImovel() == null) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imóvel");			
			}
			
			sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
		}
		
		// Lista de Categoria
		if (imovelGeracaoTabelasTemporariasCadastroHelper.getCategoria() == null) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Categoria");			
			}
			
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
		}
		
		// Lista de SubCategorias
		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
		
		if (categoria != 0 && !categoria.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
					categoria));
		}
		filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
		
		collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());		 
		if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Subcategoria");			
		}
		
		sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);

		// pesquisa as situações de ligações de agua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if (colecaoLigacaoAguaSituacao == null
				|| colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Ligação Agua Situação");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		}
		
		// pesquisar cliente relação tipo
		FiltroClienteRelacaoTipo filtro = new FiltroClienteRelacaoTipo(
				FiltroClienteRelacaoTipo.DESCRICAO);
		Collection colecaoClienteRelacaoTipo = fachada
			.pesquisar(filtro, ClienteRelacaoTipo.class.getName());
		sessao.setAttribute("colecaoClienteRelacaoTipo", colecaoClienteRelacaoTipo);
		
		sessao.setAttribute("helper", imovelGeracaoTabelasTemporariasCadastroHelper);
		
		return retorno;
	}
	
	
	private void pesquisarElo(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo elo do formulário.
		eloID =  imovelGeracaoTabelasTemporariasCadastroHelper.getElo();
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID_ELO, eloID));
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Agencia nao encontrada
			imovelGeracaoTabelasTemporariasCadastroHelper.setElo("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeElo("Agência Inexistente");
			
			httpServletRequest.setAttribute("corElo", "exception");
			httpServletRequest.setAttribute("nomeCampo","elo");
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
			objetoLocalidade = objetoLocalidade.getLocalidade();
			
			imovelGeracaoTabelasTemporariasCadastroHelper.setElo(objetoLocalidade.getId().toString());
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeElo(objetoLocalidade.getDescricao());
			
			/*********************************************************************
			 * Caso o Usuario informe o Elo deve-se limpar os campos da Inscricao
			 *********************************************************************/ 
			// Localidade
			imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal("");
			
			// Setor Comercial
			imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");

			// Quadra
			imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraInicial("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal("");
			
			httpServletRequest.setAttribute("corElo", "valor");
		}
	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarLocalidade(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			//CORRIGIR
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = new Integer(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial());
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeInicial("Localidade inexistente");
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal("Localidade inexistente");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeInicial");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial(String.valueOf(objetoLocalidade.getId()));
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeInicial(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				
				//destino
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = new Integer(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal());
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			//FALTA CORRIGIR
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("destino");
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino do formulário
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal("Localidade inexistente.");
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeFinal");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
		

	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	@SuppressWarnings("static-access")
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = new Integer(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal());

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && localidadeID.SIZE > 0 ) {
				setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial();
				
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
					// nomeSetorComercialOrigem e setorComercialOrigemID do formulário
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialInicial("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");

					//destino
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//inicio setorComercialOrigem
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getId()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial(
							objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//fim setorComercialOrigem
					
					//inicio setorComercialDestino
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					//fim setorComercialDestino
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo","quadraInicial");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial(
						"Informe a localidade da inscrição de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
			}
		} else {
//CORRIGIR			
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID =  new Integer(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal());
			//localidadeID = (String) imovelGeracaoTabelasTemporariasCadastro.getLocalidadeFinal();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && localidadeID.SIZE > 0) {
				setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialFinal().toString();

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
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					
					httpServletRequest.setAttribute("codigoSetorComercialFinal", String.valueOf(objetoSetorComercial.getCodigo()));
					httpServletRequest.setAttribute("setorComercialFinal", String.valueOf(objetoSetorComercial.getId()));
					httpServletRequest.setAttribute("nomeSetorComercialFinal", objetoSetorComercial.getDescricao());
					
				
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo","quadraFinal");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	
	private void pesquisarQuadra(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial().toString();
			setorComercialID = imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialInicial().toString();
			
			String idLocalidadeInicial = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial().toString();
			
			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				quadraNM = imovelGeracaoTabelasTemporariasCadastroHelper.getQuadraInicial();
				
				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				
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
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraInicial("");
					
					// Mensagem de tela
					imovelGeracaoTabelasTemporariasCadastroHelper.setMsgQuadraInicial("QUADRA INEXISTENTE");
					
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraInicial");
					
					//destino
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal("");

				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial(String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraInicial(String.valueOf(objetoQuadra.getId()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal(String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal(String.valueOf(objetoQuadra.getId()));
					
					httpServletRequest.setAttribute("corQuadraOrigem", null);
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","quadraInicial");
			}
		} else {//QUADRA FINAL
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialFinal();
			setorComercialID = imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialFinal().toString();

			String idLocalidadeFinal = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal().toString();			
			
			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				quadraNM = imovelGeracaoTabelasTemporariasCadastroHelper.getQuadraFinal();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				
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
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraInicial("");
					
					// Mensagem de tela
					imovelGeracaoTabelasTemporariasCadastroHelper.setMsgQuadraFinal("QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraFinal");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal(String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal(String.valueOf(objetoQuadra.getId()));

					httpServletRequest.setAttribute("corQuadraDestino", null);
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				httpServletRequest.setAttribute("quadraFinal", "");
				

				// Mensagem de tela
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","quadraInicial");
			}
		}

	}
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente( ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, imovelGeracaoTabelasTemporariasCadastroHelper.getCliente()));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			imovelGeracaoTabelasTemporariasCadastroHelper.setCliente(""
					+ ((Cliente) ((List) clienteEncontrado).get(0))
					.getId());

			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeCliente(""
					+ ((Cliente) ((List) clienteEncontrado).get(0))
					.getNome());
			
			httpServletRequest.setAttribute("idClienteNaoEncontrado","true");
			httpServletRequest.setAttribute("nomeCampo", "clienteNome");
		} else {
			imovelGeracaoTabelasTemporariasCadastroHelper.setCliente("");
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeCliente("CLIENTE INEXISTENTE.");
			
			httpServletRequest.setAttribute("idClienteNaoEncontrado","exception");
			httpServletRequest.setAttribute("nomeCampo", "clienteNome");
		}
	}
	/**
	 * Pesquisar Imoveis
	 * 
	 * @param filtroImovel
	 * @param matricula
	 * @param imoveis
	 * @param imovelGeracaoTabelasTemporariasCadastroActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarImoveis( ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,imovelGeracaoTabelasTemporariasCadastroHelper.getMatricula()));
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
		
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel,Imovel.class.getName());

		if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
		
			Imovel imovel = (Imovel)Util.retonarObjetoDeColecao(colecaoImoveis);
			
			imovelGeracaoTabelasTemporariasCadastroHelper.setMatricula(imovel.getId().toString());
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeMatricula("" + imovel.getInscricaoFormatada());
			
			httpServletRequest.setAttribute("matriculaNaoEncontrada",
					"true");
			httpServletRequest.setAttribute("nomeCampo", "nomeImovel");

		} else {
			
			imovelGeracaoTabelasTemporariasCadastroHelper.setNomeMatricula("Matrícula Inexistente.");
			httpServletRequest.setAttribute("matriculaNaoEncontrada",
					"exception");


			httpServletRequest.setAttribute("nomeCampo", "nomeImovel");

		}
	}
}
