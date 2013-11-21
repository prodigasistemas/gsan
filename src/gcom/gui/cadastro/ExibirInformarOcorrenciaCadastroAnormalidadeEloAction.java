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
package gcom.gui.cadastro;

import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroEloAnormalidade;
import gcom.cadastro.imovel.FiltroImovelCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroImovelEloAnormalidade;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.bean.ImovelDadosGeraisHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

/**
 * Exibe o caso de uso [UC0491] Informar Ocorrência de Cadastro e/ou Anormalidade de Elo
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class ExibirInformarOcorrenciaCadastroAnormalidadeEloAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarOcorrenciaCadastroAnormalidadeElo");

		Fachada fachada = Fachada.getInstancia();
				
		//Mudar Isso Quando Tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idDigitadoEnterImovel = null;
		
		Collection ocorrenciaRemovidas = new ArrayList();
		Collection anormalidadesRemovidas = new ArrayList();
		
		//essa rotina recupera os dados da Jsp inicial sem usar ActionForm
		if (httpServletRequest.getParameter("objetoConsulta") != null) {
			try {
				DiskFileUpload upload = new DiskFileUpload();

				//Parse the Request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					FileItem item = null;

					//Pega uma Lista de Itens do Form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();

						if (item.getFieldName().equals("idImovel")) {
							idDigitadoEnterImovel = item.getString();
						}
					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		
		//Instaciando o Objeto Imovel
		Imovel imovel = new Imovel();
		if(idDigitadoEnterImovel == null || idDigitadoEnterImovel.trim().equals("")){
			idDigitadoEnterImovel = httpServletRequest.getParameter("idImovel");
		}
		
		if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.equalsIgnoreCase("")){

			//Recuperando os dados do imovel
			imovel = (Imovel) fachada.pesquisarImovelDigitado(new Integer (idDigitadoEnterImovel));
			
			if (imovel != null && !imovel.equals("")){
				
				//Recuperando a Inscricao do Imovel Formatada
				String matriculaImovel = fachada.pesquisarInscricaoImovel(new Integer (idDigitadoEnterImovel));
				
				//Recuperando o Endereco do imovel Formatado
				String enderecoImovel = fachada.pesquisarEndereco(new Integer (idDigitadoEnterImovel));
				
				//recuperando a situacao de agua do imovel
				String situacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				
				//recuperando a situacao de esgoto do imovel
				String situacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				
				//Setando no Objeto Helper dos Valores a Serem Recuperados na JSP
				ImovelDadosGeraisHelper imovelDadosGeraisHelper = new ImovelDadosGeraisHelper
					(new Integer (idDigitadoEnterImovel), matriculaImovel, enderecoImovel, situacaoAgua, situacaoEsgoto);
				//Enviando o Objeto Helper Via Request
				httpServletRequest.setAttribute("imovel", imovelDadosGeraisHelper);
				/**
				 * Alterado por Arthur Carvalho
				 * @date 28/12/2009
				 */
				//Fluxo necessario no reload da pesquisa do imovel
				if ( httpServletRequest.getParameter("objetoConsulta") != null 
						&& httpServletRequest.getParameter("objetoConsulta").equals("1") ) {
					//resgatando as ocorrencias de cadastro do imovel digitado
					FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = new FiltroImovelCadastroOcorrencia();
					filtroImovelCadastroOcorrencia.adicionarParametro(
							new ParametroSimples(FiltroImovelCadastroOcorrencia.IMOVEL_ID, idDigitadoEnterImovel));
					filtroImovelCadastroOcorrencia.setCampoOrderBy(FiltroImovelCadastroOcorrencia.DATA_OCORRENCIA);
					filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
					Collection colecaoImovelCadastroOcorrencia = fachada.pesquisar(
							filtroImovelCadastroOcorrencia, ImovelCadastroOcorrencia.class.getName());
					
					//setando no request
					sessao.setAttribute("cadastroOcorrencia", colecaoImovelCadastroOcorrencia);
					
					//resgatando as anormalidades de elo do imovel digitado
					FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
					filtroImovelEloAnormalidade.adicionarParametro(
							new ParametroSimples(FiltroImovelEloAnormalidade.IMOVEL_ID, idDigitadoEnterImovel));
					filtroImovelEloAnormalidade.setCampoOrderBy(FiltroImovelEloAnormalidade.DATA_ANORMALIDADE);
					filtroImovelEloAnormalidade.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroImovelEloAnormalidade.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
					Collection colecaoImovelEloAnormalidade = fachada.pesquisar(
							filtroImovelEloAnormalidade, ImovelEloAnormalidade.class.getName());
					
					//setando no request
					sessao.setAttribute("eloAnormalidade", colecaoImovelEloAnormalidade);
				} else {
					//Fluxo necessario no reload para remoção da ocorrencia/anormalidade do imovel.
					//Remove Imovel Ocorrencia
					ArrayList colecaoImovelCadastroOcorrencia = (ArrayList) sessao.getAttribute("cadastroOcorrencia");
					
					if ( httpServletRequest.getParameter("acao") != null && 
							httpServletRequest.getParameter("acao").equals("remover") ){
						
						int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
						
						if ( colecaoImovelCadastroOcorrencia.size() >= ( obj - 1) ) {
						
							if ( sessao.getAttribute("imovelCadastroOcorrenciaRemover") != null ) {
								ocorrenciaRemovidas = (Collection) sessao.getAttribute("imovelCadastroOcorrenciaRemover");  
							} 
							ocorrenciaRemovidas.add(colecaoImovelCadastroOcorrencia.get(obj-1));
							
							colecaoImovelCadastroOcorrencia.remove(obj-1);
							sessao.setAttribute("imovelCadastroOcorrenciaRemover", ocorrenciaRemovidas);
						}
				
					}
					//setando no request
					sessao.setAttribute("cadastroOcorrencia", colecaoImovelCadastroOcorrencia);
					
					//Remove Anormalidade Elo
					ArrayList colecaoImovelEloAnormalidade = (ArrayList) sessao.getAttribute("eloAnormalidade");
					
					if (httpServletRequest.getParameter("acao") != null && 
							httpServletRequest.getParameter("acao").equals("removerAnormalidade") ){
						
						int objElo = new Integer(httpServletRequest.getParameter("idElo")).intValue();
						
						if ( colecaoImovelEloAnormalidade.size() >= objElo ) {
						
							if ( sessao.getAttribute("imovelAnormalidadeRemover") != null ) {
								anormalidadesRemovidas = (Collection) sessao.getAttribute("imovelAnormalidadeRemover");  
							} 
							anormalidadesRemovidas.add(colecaoImovelEloAnormalidade.get(objElo-1));
							
							colecaoImovelEloAnormalidade.remove(objElo-1);
							sessao.setAttribute("imovelAnormalidadeRemover", anormalidadesRemovidas);
						}
	                    
					}
					//setando no request
					sessao.setAttribute("eloAnormalidade", colecaoImovelEloAnormalidade);
					
					/**
					 * Fim da Alteração
					 * @author Arthur Carvalho
					 */
				}
				
			} else{
				httpServletRequest.setAttribute("inexistente", "1");
			}
				
		}
		//recuperando lista de tipos de Ocorrencias de Cadastro
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		Collection colecaoCadastroOcorrencia = fachada.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
		
		//recuperando lista de tipos de Anormalidades de Elo
		FiltroEloAnormalidade FiltroEloAnormalidade = new FiltroEloAnormalidade();
		Collection colecaoEloAnormalidade = fachada.pesquisar(FiltroEloAnormalidade, EloAnormalidade.class.getName());
		
		//setando os tipos de Ocorrencias de Cadastro na sessao para ser recuperado na jsp
		sessao.setAttribute("cadastro", colecaoCadastroOcorrencia);
		
		//setando tipos de Anormalidades de Elo na sessao para ser recuperado na jsp
		sessao.setAttribute("anormalidade", colecaoEloAnormalidade);

		return retorno;
	}

}
