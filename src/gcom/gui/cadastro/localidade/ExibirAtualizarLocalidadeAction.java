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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroLocalidadeClasse;
import gcom.cadastro.localidade.FiltroLocalidadePorte;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoCampos;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("atualizarLocalidade");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLocalidadeActionForm atualizarLocalidadeActionForm = (AtualizarLocalidadeActionForm) actionForm;

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String removerEndereco = (String) httpServletRequest.getParameter("removerEndereco");
		
		//PERMISSÃO PARA BLOQUEIO ALTERAÇÃO DE IMÓVEIS
        boolean permissaoEspecialBloqueio = this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS,(Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
        
        if (permissaoEspecialBloqueio){
       	 	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", Localidade.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue());
        }else{
        	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", Localidade.BLOQUEIO_INSERIR_IMOVEL_NAO.intValue());
        	if (atualizarLocalidadeActionForm !=null){
        		if (atualizarLocalidadeActionForm.getIndicadorBloqueio() != null
        				&& atualizarLocalidadeActionForm.getIndicadorBloqueio().equals("1")){
        			httpServletRequest.setAttribute("bloqueio", true);
        		}else{
        			httpServletRequest.setAttribute("bloqueio", false);
        		}
        	}
        }
		
		// variavel criada para identificar voltou do Adicionar Endereço
		String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
		
		String localidadeID = null;
		
		if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
	    	 && (removerEndereco == null || removerEndereco.equalsIgnoreCase(""))
		 	 && (httpServletRequest.getParameter("desfazer") == null)
		 	 && (tipoRetorno == null || !tipoRetorno.equalsIgnoreCase("localidade"))){
	           //Recupera o id da Localidade que vai ser atualizada
			
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
				localidadeID = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
	           	//Definindo a volta do botão Voltar p Filtrar Localidade
				sessao.setAttribute("voltar", "filtrar");
	   	    	sessao.setAttribute("idRegistroAtualizar",localidadeID);
	   	    	sessao.setAttribute("tipoPesquisa",ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
	        }else if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
	        	localidadeID = (String)sessao.getAttribute("idRegistroAtualizar");
	   			//Definindo a volta do botão Voltar p Filtrar Localidade
	        	sessao.setAttribute("voltar", "filtrar");
	        }else if (httpServletRequest.getParameter("idRegistroAtualizar")!= null) {
	        	localidadeID = httpServletRequest.getParameter("idRegistroAtualizar");
		        //Definindo a volta do botão Voltar p Manter Localidade
	        	sessao.setAttribute("voltar", "manter");
		        sessao.setAttribute("idRegistroAtualizar",localidadeID);
	        }
		
		}else{
			localidadeID = (String)sessao.getAttribute("idRegistroAtualizar");
		}
		 
		httpServletRequest.setAttribute("voltar",sessao.getAttribute("voltar"));

		String atualizarEndereco = (String) httpServletRequest.getParameter("limparCampos");

		Collection colecaoPesquisa = null;

		if ((objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase(""))
			 || (removerEndereco != null && !removerEndereco.trim().equalsIgnoreCase(""))
			 || (atualizarEndereco != null && !atualizarEndereco.trim().equalsIgnoreCase(""))) {

			
			if(objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")){
				switch (Integer.parseInt(objetoConsulta)) {
		            
		        	//Elo - Localidade
		            case 1:
		            	
						// Recebe o valor do campo IdUnidadeNegocio do formulário.
						String IdUnidadeNegocio = atualizarLocalidadeActionForm
								.getIdUnidadeNegocio();
						
						/*
						 * OBS - O elo informado deve pertencer à mesma gerência regional da
						 * localidade que está sendo inserida
						 */
			
						FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
						filtroLocalidade.setConsultaSemLimites(true);
			            filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
						filtroLocalidade.adicionarParametro(new ParametroSimples(
								FiltroLocalidade.ID_UNIDADE_NEGOCIO, IdUnidadeNegocio));
						filtroLocalidade.adicionarParametro(new ParametroSimples(
								FiltroLocalidade.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroLocalidade.adicionarParametro(new ComparacaoCampos(
								FiltroLocalidade.ID, "localidade"));
			
						// Retorna localidade - Elo
						colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,
								Localidade.class.getName());
			
						httpServletRequest.setAttribute("colecaoElo", colecaoPesquisa);
						
						break;
	
		            //Gerente da Localidade
		            case 2:
		            	
		            	this.pesquisarCliente(atualizarLocalidadeActionForm);
		            	
		            	break;
		            
		            case 3:
		            	
		            	this.pesquisarMunicipioPrincipal(atualizarLocalidadeActionForm, httpServletRequest);
		            	break;
		            default:
		                break;
		        }
			} 
			
			// Remove o endereco informado.
			if (removerEndereco != null
					&& !removerEndereco.trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoEnderecos") != null) {

					Collection enderecos = (Collection) sessao
							.getAttribute("colecaoEnderecos");
					if (!enderecos.isEmpty()) {
						enderecos.remove(enderecos.iterator().next());
					}
				}
			}

		} else if (httpServletRequest.getParameter("desfazer") == null) {
			
			String localidadeIDForm = atualizarLocalidadeActionForm
					.getLocalidadeID();
			if ((localidadeID == null || localidadeID.equalsIgnoreCase(""))
					&& (localidadeIDForm == null
					|| localidadeIDForm.equalsIgnoreCase(""))) {
				// ID da localidade não informado
				throw new ActionServletException(
						"atencao.codigo_localidade_nao_informado");
			}
			if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoUnidadeNegocio") == null
						|| sessao.getAttribute("colecaoClasse") == null
						|| sessao.getAttribute("colecaoPorte") == null) {

					// Unidade de Negocio
					// ==============================================
			    	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();	
			        filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO,FiltroUnidadeNegocio.NOME);
			        
			    	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
			                FiltroUnidadeNegocio.INDICADOR_USO,
			                ConstantesSistema.INDICADOR_USO_ATIVO));

			        //Retorna Localidade_Classe
			        Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio,
			                UnidadeNegocio.class.getName());

			        if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			            //Nenhum registro na tabela gerencia_regional foi
			            // encontrada
			            throw new ActionServletException(
			                    "atencao.pesquisa.nenhum_registro_tabela", null,
			                    "Unidade de Negócio");
			        } else {
			        	
			        	UnidadeNegocio unidadeNegocio = null;
						Iterator iterator = colecaoUnidadeNegocio.iterator();
					
						while (iterator.hasNext()) {
							unidadeNegocio = (UnidadeNegocio) iterator.next();
							
							String descUnidadeNegocio = unidadeNegocio.getNomeAbreviado() + "-" + unidadeNegocio.getNome();
							unidadeNegocio.setNome(descUnidadeNegocio);
							
						}
			        	
			            sessao.setAttribute("colecaoUnidadeNegocio",
			            		colecaoUnidadeNegocio);
			        }
					// ================================================================

					// Localidade_Classe
					// ==============================================
					FiltroLocalidadeClasse filtroLocalidadeClasse = new FiltroLocalidadeClasse();

					filtroLocalidadeClasse.setCampoOrderBy(FiltroLocalidadeClasse.DESCRICAO);
					
					filtroLocalidadeClasse
							.adicionarParametro(new ParametroSimples(
									FiltroLocalidadeClasse.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					// Retorna Localidade_Classe
					colecaoPesquisa = this.getFachada().pesquisar(
							filtroLocalidadeClasse, LocalidadeClasse.class
									.getName());

					if (colecaoPesquisa == null
							|| colecaoPesquisa.isEmpty()) {
						// Nenhum registro na tabela localidade_classe foi
						// encontrado
						throw new ActionServletException(
								"atencao.pesquisa.nenhum_registro_tabela",
								null, "Localidade_Classe");
					} else {
						sessao.setAttribute("colecaoClasse",
								colecaoPesquisa);
					}
					// ================================================================

					// Localidade_Porte
					// ===============================================
					FiltroLocalidadePorte filtroLocalidadePorte = new FiltroLocalidadePorte();

			        filtroLocalidadePorte.setCampoOrderBy(FiltroLocalidadePorte.DESCRICAO);
			        
					filtroLocalidadePorte
							.adicionarParametro(new ParametroSimples(
									FiltroLocalidadePorte.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					// Retorna Localidade_Porte
					colecaoPesquisa = this.getFachada().pesquisar(
							filtroLocalidadePorte, LocalidadePorte.class
									.getName());

					if (colecaoPesquisa == null
							|| colecaoPesquisa.isEmpty()) {
						// Nenhum registro na tabela localidade_porte foi
						// encontrado
						throw new ActionServletException(
								"atencao.pesquisa.nenhum_registro_tabela",
								null, "Localidade_Porte");
					} else {
						sessao
								.setAttribute("colecaoPorte",
										colecaoPesquisa);
					}
				}
				// ==================================================================
				
				//Local de Armazenagem do Hidrometro					 
				// ===============================================
				FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

				filtroHidrometroLocalArmazenagem.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
			    
				filtroHidrometroLocalArmazenagem
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Local de Armazenagem do Hidrometro
				colecaoPesquisa = this.getFachada().pesquisar(
						filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class
								.getName());

				if (colecaoPesquisa == null
						|| colecaoPesquisa.isEmpty()) {
					// Nenhum registro na tabela HIDROMETRO_LOCAL_ARMAZENAGEM foi encontrado
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela",
							null, "Local de Armazenagem do Hidrometro");
				} else {
					sessao
							.setAttribute("colecaoHidrometroLocalArmazenagem",
									colecaoPesquisa);
				}
			

			// ==================================================================

				// Pesquisa os dados da localidade selecionada
				// ==================================================================
				if ( httpServletRequest.getParameter("verificarLocalidadeSede")== null ||
		        		!httpServletRequest.getParameter("verificarLocalidadeSede").equals("sim")){
					
					exibirLocalidade( localidadeID, 
					    		 atualizarLocalidadeActionForm,sessao,
								 httpServletRequest);
					
				}
				
				//Município
				if(atualizarLocalidadeActionForm.getMunicipio() != null && 
						!atualizarLocalidadeActionForm.getMunicipio().equals("") && 
						atualizarLocalidadeActionForm.getDescricaoMunicipio() != null && 
						!atualizarLocalidadeActionForm.getDescricaoMunicipio().equals("")){
									
					httpServletRequest.setAttribute("corMunicipio","true");
				}
			}
		}

		
		 if (httpServletRequest.getParameter("desfazer") != null
	                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			 
			 sessao.removeAttribute("tipoPesquisaRetorno");
			 sessao.removeAttribute("colecaoEnderecos");
			 exibirLocalidade( localidadeID, atualizarLocalidadeActionForm,sessao, httpServletRequest);
		 }
	        	
			 
	        //Codigo Cliente
			if(atualizarLocalidadeActionForm.getGerenteLocalidade() != null && 
				!atualizarLocalidadeActionForm.getGerenteLocalidade().equals("") && 
				atualizarLocalidadeActionForm.getNomeGerente() != null && 
				!atualizarLocalidadeActionForm.getNomeGerente().equals("")){
								
				httpServletRequest.setAttribute("gerenteLocalidadeEncontrado","true");
			}
			
			if ( httpServletRequest.getParameter("verificarLocalidadeSede")!= null &&
	        		httpServletRequest.getParameter("verificarLocalidadeSede").equals("sim")){
	        	
	        	if ( atualizarLocalidadeActionForm.getSede() != null && 
	        			atualizarLocalidadeActionForm.getSede().equals("1")){
	            	
	            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	            	            	
	            	Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
	            	
	            	if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	            		
	            		Iterator colecaoLocalidadeIterator = colecaoLocalidade.iterator();
	                	
	                	Localidade localidade = null;
	                	
	                	while ( colecaoLocalidadeIterator.hasNext() ){
	                		
	                		localidade = (Localidade) colecaoLocalidadeIterator.next();
	                		
	                		if ( localidade.getIndicadorLocalidadeSede() == 1){
	                			
	                			String localidadeSede = ""+localidade.getId();
	                			
	                			throw new ActionServletException(
	                                    "atencao.ja_existe_localidade_sede", null, localidadeSede);
	                			
	                		}
	                		
	                	}
	            		
	            	}
	            	
	            }
	        	
	        }
		
		// devolve o mapeamento de retorno
		return retorno;
	}

	private void exibirLocalidade(String localidadeID, 
    		AtualizarLocalidadeActionForm atualizarLocalidadeActionForm,HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		Collection colecaoPesquisa = null;
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Objetos que serão retornados pelo hibernate
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
		filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("cliente");		
		filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagem");
		filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, localidadeID));

		// Retorna Localidade
		colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,
				Localidade.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Localidade não cadastrada
			throw new ActionServletException(
					"atencao.processo.localidadeNaoCadastrada");
		}
		Localidade localidade = (Localidade) Util
				.retonarObjetoDeColecao(colecaoPesquisa);
		
		//Coloca o objeto selecionado na sessão
		sessao.setAttribute("localidadeManter", localidade);

		atualizarLocalidadeActionForm
				.setLocalidadeID(localidadeID);

		atualizarLocalidadeActionForm
				.setLocalidadeNome(localidade.getDescricao().trim());

		if (localidade.getFone() != null) {
			atualizarLocalidadeActionForm
					.setTelefone(String.valueOf(localidade
							.getFone()).trim());
		}else{
			atualizarLocalidadeActionForm
			.setTelefone("");
		}

		if (localidade.getRamalfone() != null) {
			atualizarLocalidadeActionForm
					.setRamal(String.valueOf(localidade
							.getRamalfone()).trim());
		}else{
			atualizarLocalidadeActionForm
			.setRamal("");
		}
			
			atualizarLocalidadeActionForm
			.setIndicadorBloqueio(String
                    .valueOf(localidade.getIndicadorBloqueio()));
			if (localidade.getIndicadorBloqueio().intValue() == Localidade.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
				httpServletRequest.setAttribute("bloqueio", true);
			}else{
				httpServletRequest.setAttribute("bloqueio", false);
			}

		if (localidade.getFax() != null) {
			atualizarLocalidadeActionForm
					.setFax(String.valueOf(localidade.getFax()).trim());
		} else {
			atualizarLocalidadeActionForm.setFax("");
		}

		if (localidade.getConsumoGrandeUsuario() != 0) {
			atualizarLocalidadeActionForm
					.setMenorConsumo(String.valueOf(localidade
							.getConsumoGrandeUsuario()).trim());
		}else{
			atualizarLocalidadeActionForm
			.setMenorConsumo("");
		}

		if (localidade.getEmail() != null){
			atualizarLocalidadeActionForm
			.setEmail(localidade.getEmail().trim());
		}
		else{
			atualizarLocalidadeActionForm.setEmail("");
		}
		

		//ICMS
		if (localidade.getCodigoICMS() != null){
			atualizarLocalidadeActionForm
			.setIcms(localidade.getCodigoICMS().toString());
		}else{
			atualizarLocalidadeActionForm.setIcms("");
		}

		// local de armazenagem do hidrometro
		if (localidade.getHidrometroLocalArmazenagem() != null){
			atualizarLocalidadeActionForm
			.setHidrometroLocalArmazenagem(localidade.getHidrometroLocalArmazenagem().toString());
		}else{
			atualizarLocalidadeActionForm.setHidrometroLocalArmazenagem("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		//Centro de Custo
		if (localidade.getCodigoCentroCusto() != null){
			atualizarLocalidadeActionForm
			.setCentroCusto(localidade.getCodigoCentroCusto().toString());
		}else{
			atualizarLocalidadeActionForm.setCentroCusto("");
		}
		
		//Centro de Custo Esgoto
		if (localidade.getCodigoCentroCustoEsgoto() != null){
			atualizarLocalidadeActionForm
				.setCentroCustoEsgoto(localidade.getCodigoCentroCustoEsgoto().toString());
		}else{
			atualizarLocalidadeActionForm.setCentroCustoEsgoto("");
		}
		
		//Informatizada
		atualizarLocalidadeActionForm.setInformatizada(localidade.getIndicadorLocalidadeInformatizada().toString());
		
		//Sede
		atualizarLocalidadeActionForm.setSede(localidade.getIndicadorLocalidadeSede().toString());

		//Cliente
		if (localidade.getCliente() != null){
			atualizarLocalidadeActionForm.setGerenteLocalidade(localidade.getCliente().getId().toString());
			atualizarLocalidadeActionForm.setNomeGerente(localidade.getCliente().getNome().toString());
		}else{
			atualizarLocalidadeActionForm.setGerenteLocalidade("");
			atualizarLocalidadeActionForm.setNomeGerente("");
		}
		
//			atualizarLocalidadeActionForm
//				.setGerenciaID(String.valueOf(localidade
//					.getGerenciaRegional().getId()));
		
		//Unidade de Negócio
		atualizarLocalidadeActionForm
						.setIdUnidadeNegocio(String.valueOf(localidade
								.getUnidadeNegocio().getId()));
		
//			 Pesquisa os elos realacionados com a gerência
		// regional da localidade
		// ================================================================

		FiltroLocalidade filtroElo = new FiltroLocalidade();
		
		filtroElo.setConsultaSemLimites(true);

		filtroElo.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		
		filtroElo.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID_UNIDADE_NEGOCIO, localidade
						.getUnidadeNegocio().getId()));

		filtroElo.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroElo.adicionarParametro(new ComparacaoCampos(FiltroLocalidade.ID, "localidade"));

		// Retorna uma coleção de Elos
		colecaoPesquisa = this.getFachada().pesquisar(filtroElo,
				Localidade.class.getName());

		if (colecaoPesquisa != null
				&& !colecaoPesquisa.isEmpty()) {
			httpServletRequest.setAttribute("colecaoElo",
					colecaoPesquisa);
		}
		// ================================================================

		if (localidade.getLocalidade().getId() != null) {
			atualizarLocalidadeActionForm
					.setEloID(String.valueOf(localidade
							.getLocalidade().getId()));
		} else {
			atualizarLocalidadeActionForm
					.setEloID(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		}

		atualizarLocalidadeActionForm
				.setClasseID(String.valueOf(localidade
						.getLocalidadeClasse().getId()));

		atualizarLocalidadeActionForm
				.setPorteID(String.valueOf(localidade
						.getLocalidadePorte().getId()));
		
		if (localidade.getHidrometroLocalArmazenagem() != null 
				&& !localidade.getHidrometroLocalArmazenagem().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
		atualizarLocalidadeActionForm
				.setHidrometroLocalArmazenagem(String.valueOf(localidade
						.getHidrometroLocalArmazenagem().getId()));
		}
		atualizarLocalidadeActionForm
				.setIndicadorUso(String.valueOf(localidade
						.getIndicadorUso()));

		Collection endereco = new Vector();
		endereco.add(localidade);

		// Coloca o atual endereço na sessao
		if (localidade.getLogradouroCep() != null){
			sessao.setAttribute("colecaoEnderecos", endereco);
		}else{
			sessao.removeAttribute("colecaoEnderecos");
		}
		
		//Se a localidade possuir município principal
		if(localidade.getMunicipio() != null){
			atualizarLocalidadeActionForm.setMunicipio(String.valueOf(localidade.getMunicipio().getId()));
			atualizarLocalidadeActionForm.setDescricaoMunicipio(String.valueOf(
					localidade.getMunicipio().getNome()));
			httpServletRequest.setAttribute("corMunicipio","true");
		}
	}
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(AtualizarLocalidadeActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getGerenteLocalidade())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = 
			this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setGerenteLocalidade(cliente.getId().toString());
			form.setNomeGerente(cliente.getNome());
			

		} else {
			form.setGerenteLocalidade("");
			form.setNomeGerente("Cliente inexistente");
		}
	}    	
	
	/**
	 * Pesquisa Município Principal 
	 *
	 * @author Diogo Peixoto
	 * @date 29/03/2011
	 */
	private void pesquisarMunicipioPrincipal(AtualizarLocalidadeActionForm form, 
			HttpServletRequest httpServletRequest) {
			
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(
				new ParametroSimples(FiltroMunicipio.ID, 
				new Integer(form.getMunicipio())));

		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			httpServletRequest.setAttribute("corMunicipio", "true");

		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Município inexistente");
		}
	}
}