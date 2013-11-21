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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelContaEnvio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.FiltroNomeConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.GisHelper;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelConclusaoAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("inserirImovelConclusao");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        

        DynaValidatorForm inserirImovelConclusaoActionForm = 
        	(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");

        //Cria Filtros
        FiltroImovelContaEnvio filtroImovelContaEnvio = new FiltroImovelContaEnvio();

        //Cria coleçao
        Collection colecaoImovelEnnvioConta = null;


        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();   
        
        /*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
		   carregarDadosGis(gisHelper,inserirImovelConclusaoActionForm,sessao,fachada);
		}		
		
		
        String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
        if(informacoesComplementares != null && informacoesComplementares.equals("")){
        	inserirImovelConclusaoActionForm.set("informacoesComplementares","");        	
        }else {
        	inserirImovelConclusaoActionForm.set("informacoesComplementares",informacoesComplementares);
        }
		/*
		 * Carregamento inicial da tela responsável pelo redebimento das
		 * informações referentes ao local da ocorrência (ABA Nº 02)
		 * ============================================================================================================
		 */
        
//        //Faz a pesquisa de Ocupacao Tipo
//        filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(FiltroNomeConta.INDICADOR_USO,
//        		ConstantesSistema.INDICADOR_USO_ATIVO));
//        
//        filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
//        
//        colecaoImovelEnnvioConta = 
//        	fachada.pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
//        
//        if (!colecaoImovelEnnvioConta.isEmpty()) {
//            
//        	//Coloca a coleçao da pesquisa na sessao
//            sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
//            
//            /*
//             * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
//             * OBJ: Marcar o indicador de emissão de extrato de faturamento para NAO EMITIR
//             */
//            inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
//            
//        } else {
//            throw new ActionServletException("atencao.naocadastrado",null, "Imovel Conta Envio");
//        }        
        
        //Verifica se existe cliente responsavel
		boolean eResponsavel = false;

        //Testa se já existe um clinte proprietário
        if (sessao.getAttribute("imovelClientesNovos") == null) {
            httpServletRequest.setAttribute("envioContaListar", "naoListar");
        } else {
            Collection imovelClientes = (Collection) sessao.getAttribute("imovelClientesNovos");
            ClienteImovel clienteImovel = new ClienteImovel();
            Iterator iteratorClienteImovel = imovelClientes.iterator();

            while (iteratorClienteImovel.hasNext()) {
                
            	clienteImovel = null;
                clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
                
                if (clienteImovel.getClienteRelacaoTipo().getDescricao().equalsIgnoreCase(ConstantesSistema.IMOVEL_ENVIO_CONTA)) {
                    httpServletRequest.setAttribute("envioContaListar","listar");
                }
                
				if (clienteImovel.getClienteRelacaoTipo() != null &&
						clienteImovel.getClienteRelacaoTipo().getId() == 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","obrigatorio");
					
					eResponsavel = true;
					
				}else if ( clienteImovel.getClienteRelacaoTipo() != null && !eResponsavel &&
						clienteImovel.getClienteRelacaoTipo().getId() != 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","opcional");
					
				}
				
				if ( sessao.getAttribute("imovelContaEnvio") != null && !sessao.getAttribute("imovelContaEnvio").equals("") ){
					
					String envioConta = (String) sessao.getAttribute("imovelContaEnvio");
					
					if ( ( envioConta.equals("4") || envioConta.equals("5") )
							&& clienteImovel.getIndicadorNomeConta().compareTo(new Short("1")) == 0
							&& ( clienteImovel.getCliente().getEmail() == null
							|| clienteImovel.getCliente().getEmail().equals("") )){
	
							throw new ActionServletException("atencao.email.nao.cadastrado");
						
						
					}
					
				}
                
            }
        }
        
        //Faz a pesquisa de Ocupacao Tipo
		filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
				FiltroNomeConta.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Se existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 1
		if ( eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "1" ));
			
		}//Se não existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 2
		else if( !eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "2" ));
			
		}
		
		filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
		
		colecaoImovelEnnvioConta = 
			this.getFachada().pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
		
		if (!colecaoImovelEnnvioConta.isEmpty()) {
			httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "false");
			//Coloca a coleçao da pesquisa na sessao
			sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
			
			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
			 * OBJ: Marcar o indicador de emissão de extrato de faturamento para NAO EMITIR
			 */
			if (inserirImovelConclusaoActionForm.get("extratoResponsavel") != null &&
					!inserirImovelConclusaoActionForm.get("extratoResponsavel").equals("") &&
					inserirImovelConclusaoActionForm.get("extratoResponsavel").equals(ConstantesSistema.SIM.toString())){
				
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.SIM.toString());
			}else{
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
			}
			
			
		} else {
			
			/*throw new ActionServletException("atencao.naocadastrado",null,"Imóvel Conta Envio");*/
			if ( !eResponsavel ){
				
				httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "true");
			
			}else if ( eResponsavel ){
			
				throw new ActionServletException("atencao.naocadastrado",null,"Imóvel Conta Envio");
				
			}
			
		}

        
        //Cria variaveis
        String idImovel = (String) inserirImovelConclusaoActionForm.get("idImovel");
        String idFuncionario = (String) inserirImovelConclusaoActionForm.get("idFuncionario");
        String idRota = (String) inserirImovelConclusaoActionForm.get("idRota");
        String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");

        //Cria Filtros
        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que serão retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
        	FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

        filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));

        Collection imoveis = null;

        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
            
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel.trim())));

            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (imoveis != null && !imoveis.isEmpty()) {

            	filtroImovel = new FiltroImovel();
            	filtroImovel.limparListaParametros();

            	//Cria Variaveis
                String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
                String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
                String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
                
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel.trim()));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID,new Integer(idLocalidade)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO,new Integer(idSetorComercial)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO,new Integer(idQuadra)));
            	
            	Collection colecaoImovel = null;
            	colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
            	
            	if(colecaoImovel == null || colecaoImovel.isEmpty()){
                    throw new ActionServletException("atencao.imovel_principal.inexistente.quadra");        		
            	}
            	
                sessao.setAttribute("imoveisPrincipal", imoveis);
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
                	fachada.pesquisarInscricaoImovel(new Integer(idImovel.trim())));
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", null);                
            }else {
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", "true");
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", "IMOVEL INEXISTENTE");
            }
        }
        
        
        if(idFuncionario != null && !idFuncionario.trim().equals("")){
        	
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = 
				this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getSimpleName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);

				httpServletRequest.setAttribute("idImovelPrincipalEncontrado","true");
				
				inserirImovelConclusaoActionForm.set("idFuncionario",funcionario.getId().toString());
				inserirImovelConclusaoActionForm.set("nomeFuncionario",funcionario.getNome());

			}else{
				
				inserirImovelConclusaoActionForm.set("idFuncionario","");
				inserirImovelConclusaoActionForm.set("nomeFuncionario","Funcionário Inexistente");
				
				
			}
        }
        
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRota", rota.getCodigo().toString());
        	}
        }
        
        if (idRotaAlternativa != null && !idRotaAlternativa.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaAlternativa));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRotaAlternativa", rota.getCodigo().toString());
        	}
        }
        
        
        
        
        return retorno;
    }
	//=================================================================================================================
	
	
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			DynaValidatorForm inserirImovelConclusaoActionForm,
			HttpSession sessao, Fachada fachada) {
	
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		inserirImovelConclusaoActionForm.set("cordenadasUtmX",nnCoordenadaLeste);
		inserirImovelConclusaoActionForm.set("cordenadasUtmY",nnCoordenadaNorte);
		
	     sessao.removeAttribute("gisHelper");	
	
	}

}
