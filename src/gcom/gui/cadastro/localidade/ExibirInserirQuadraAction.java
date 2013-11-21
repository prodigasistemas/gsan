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

import gcom.cadastro.dadocensitario.FiltroIbgeSetorCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.localidade.AreaTipo;
import gcom.cadastro.localidade.FiltroAreaTipo;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadraPerfil;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroZeis;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.QuadraPerfil;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.Zeis;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.Quadra;

/**
 * Classe responsável pela exibição da tela de cadastro da quadra 
 *
 * @author Rômulo Aurélio, Raphael Rossiter
 * @date 08/07/2006, 01/04/2009
 */

public class ExibirInserirQuadraAction extends GcomAction {

	private String localidadeID;

    private Collection colecaoPesquisa;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInserirQuadra");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirQuadraActionForm inserirQuadraActionForm = (InserirQuadraActionForm) actionForm;

        if(inserirQuadraActionForm.getSetorComercialCD() != null && 
        		inserirQuadraActionForm.getSetorComercialCD().equals("")){
        	
        	inserirQuadraActionForm.setMunicipioID("");
        	inserirQuadraActionForm.setIndicadorRelacionamentoQuadraBairro("2");
        }
        
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

           
            localidadeID = inserirQuadraActionForm.getLocalidadeID();
          
            switch (Integer.parseInt(objetoConsulta)) {
            
            //Localidade
            case 1:

                pesquisarLocalidade(inserirQuadraActionForm, fachada,
                httpServletRequest);

                break;
            //Setor Comercial
            case 2:

                pesquisarLocalidade(inserirQuadraActionForm, fachada,
                httpServletRequest);

                pesquisarSetorComercial(inserirQuadraActionForm, fachada,
                httpServletRequest);

                break;

            //Distrito Operacional
            case 5:

                //DISTRITO OPERACIONAL INFORMADO
                String distritoOperacionalID = inserirQuadraActionForm
                .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.ID, distritoOperacionalID));

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    
                	//DISTRITO OPERACIONAL NÃO ENCONTRADO
                	inserirQuadraActionForm.setDistritoOperacionalID("");
                    inserirQuadraActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } 
                else {
                    
                	DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirQuadraActionForm.setDistritoOperacionalID(String
                    .valueOf(objetoDistritoOperacional.getId()));
                    inserirQuadraActionForm.setDistritoOperacionalDescricao(objetoDistritoOperacional
                    .getDescricao());
                    httpServletRequest.setAttribute("corDistritoOperacional", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                }

                break;
            //Setor censitário
            case 6:

                //SETOR CENSITARIO INFORMADO
            	String setorCensitarioID = inserirQuadraActionForm
                .getSetorCensitarioID();

                FiltroIbgeSetorCensitario filtroIbgeSetorCensitario = new FiltroIbgeSetorCensitario();

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.ID, setorCensitarioID));

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroIbgeSetorCensitario,
                IbgeSetorCensitario.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    
                	//SETOR CENSITARIO NÃO ENCONTRADO
                	inserirQuadraActionForm.setSetorCensitarioID("");
                    inserirQuadraActionForm.setSetorCensitarioDescricao("Setor censitário inexistente.");
                    httpServletRequest.setAttribute("corSetorCensitario", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                } 
                else {
                    
                	IbgeSetorCensitario objetoIbgeSetorCensitario = (IbgeSetorCensitario) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirQuadraActionForm.setSetorCensitarioID(String
                    .valueOf(objetoIbgeSetorCensitario.getId()));
                    inserirQuadraActionForm
                    .setSetorCensitarioDescricao(objetoIbgeSetorCensitario.getDescricao());
                    
                    httpServletRequest.setAttribute("corSetorCensitario", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "zeisID");
                }

                break;
            
            //Bacia
            case 7:
                
            	//SISTEMA DE ESGOTO INFORMADO
            	String sistemaEsgotoID = inserirQuadraActionForm.getSistemaEsgotoID();

                if (sistemaEsgotoID != null && !sistemaEsgotoID
                    .equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

                    FiltroBacia filtroBacia = new FiltroBacia();

                    filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.SISTEMA_ESGOTO_ID, sistemaEsgotoID));

                    filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroBacia, Bacia.class.getName());

                    sessao.setAttribute("colecaoBacia", colecaoPesquisa);

                } 
                else {
                    
                	inserirQuadraActionForm.setBaciaID(String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                    
                	sessao.removeAttribute("colecaoBacia");
                }

                break;
            //Rota
            case 8:

            	//ROTA INFORMADA
            	String idRota = inserirQuadraActionForm.getRotaID();
            	
            	if (idRota != null && !idRota.trim().equals("")) {

            		FiltroRota filtroRota = new FiltroRota();
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
            		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
            		
            		Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
            		
            		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
            		
            		if(rota.getIndicadorRotaAlternativa().shortValue() != ConstantesSistema.SIM){
            			
            			inserirQuadraActionForm.setCodigoRota(String.valueOf(rota.getCodigo()));
                		inserirQuadraActionForm.setRotaMensagem(rota.getLeituraTipo().getDescricao());
                		inserirQuadraActionForm.setLocalidadeID(rota.getSetorComercial().getLocalidade().getId().toString());
                		inserirQuadraActionForm.setLocalidadeNome(rota.getSetorComercial().getLocalidade().getDescricao());
                		inserirQuadraActionForm.setSetorComercialID(rota.getSetorComercial().getId().toString());
                		inserirQuadraActionForm.setSetorComercialCD("" + rota.getSetorComercial().getCodigo());
                		inserirQuadraActionForm.setSetorComercialNome(rota.getSetorComercial().getDescricao());
                		httpServletRequest.setAttribute("corRotaMensagem", "valor");
            		}else{
            			inserirQuadraActionForm.setRotaID("");
	                    inserirQuadraActionForm.setCodigoRota("");
            			
                    	throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
            		}
            		
            	} 
            	else {
                
            		String codigoRota = inserirQuadraActionForm.getCodigoRota();
            		String setorComercialCD = inserirQuadraActionForm.getSetorComercialCD();
                
	            	if (setorComercialCD == null || setorComercialCD.equalsIgnoreCase("")) {
	                    throw new ActionServletException(
	                    "atencao.setor_comercial_nao_informado");
	                }
            	

	                FiltroRota filtroRota = new FiltroRota();
	                
	                filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
	                filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
	                
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.LOCALIDADE_ID, inserirQuadraActionForm.getLocalidadeID()));
	           
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.SETOR_COMERCIAL_CODIGO,  inserirQuadraActionForm.getSetorComercialCD()));
	                
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.CODIGO_ROTA, codigoRota));
	
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

	                colecaoPesquisa = fachada.pesquisar(filtroRota, Rota.class.getName());
	
	                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                    
	                	//ROTA NÃO ENCONTRADA
	                	inserirQuadraActionForm.setRotaID("");
	                    inserirQuadraActionForm.setCodigoRota("");
	                    inserirQuadraActionForm.setRotaMensagem("Rota inexistente.");
	                    httpServletRequest.setAttribute("corRotaMensagem", "exception");
	                    
	                    httpServletRequest.setAttribute("nomeCampo", "codigoRota");
	                } 
	                else {
	                    
	                	Rota objetoRota = (Rota) Util.retonarObjetoDeColecao(colecaoPesquisa);
	
	                	if(objetoRota.getIndicadorRotaAlternativa().shortValue() == ConstantesSistema.SIM){
	                		inserirQuadraActionForm.setRotaID("");
		                    inserirQuadraActionForm.setCodigoRota("");
	            			
	                    	throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
	                	}else{
	                		inserirQuadraActionForm.setRotaID(String
	        	                    .valueOf(objetoRota.getId()));
	        	                    inserirQuadraActionForm.setCodigoRota(String
	        	                    .valueOf(objetoRota.getCodigo()));
	        	                    inserirQuadraActionForm.setRotaMensagem(objetoRota
	        	                    .getLeituraTipo().getDescricao());
	        	                    httpServletRequest.setAttribute("corRotaMensagem", "valor");
	        	                    
	        	                    httpServletRequest.setAttribute("nomeCampo", "codigoRota");	
	                	}
	                    
	                  }
            	}
            	
                break;
                
            // Rota
            case 9:
            	
            	if(inserirQuadraActionForm.getBairroID() != null && 
            			!inserirQuadraActionForm.getBairroID().equals("")){
            		
            		pesquisarBairro(inserirQuadraActionForm, fachada,
                            httpServletRequest);
            	}
            	
            	break;
            
            default:

                break;
            }
        } 
        
        //CARREGAMENTO INICIAL DO FORMULARIO
        carregamentoInicialFormulario(fachada, httpServletRequest,inserirQuadraActionForm, sessao);
        
        //OPÇÃO DESFAZER
        desfazer(httpServletRequest, sessao, inserirQuadraActionForm);
        
        //OPÇÃO REMOVER QUADRA FACE
        removerQuadraFace(httpServletRequest, sessao);
        
        return retorno;
    }

    private void pesquisarLocalidade(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeID));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class
                .getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Localidade nao encontrada
            //Limpa o campo localidadeID do formulário
            inserirQuadraActionForm.setLocalidadeID("");
            inserirQuadraActionForm
                    .setLocalidadeNome("Localidade inexistente.");
            httpServletRequest.setAttribute("corLocalidade", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            inserirQuadraActionForm.setLocalidadeID(String
                    .valueOf(objetoLocalidade.getId()));
            inserirQuadraActionForm.setLocalidadeNome(objetoLocalidade
                    .getDescricao());
            httpServletRequest.setAttribute("corLocalidade", "valor");
            
            httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
        }
    }

    private void pesquisarSetorComercial(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos setorComercialCD e setorComercialID do formulario
            inserirQuadraActionForm.setSetorComercialCD("");
            inserirQuadraActionForm.setSetorComercialID("");
            inserirQuadraActionForm
                    .setSetorComercialNome("Informe a localidade.");
            httpServletRequest.setAttribute("corSetorComercial", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else {
            //Recebe o valor do campo setorComercialCD do formulário.
            String setorComercialCD = inserirQuadraActionForm
                    .getSetorComercialCD();

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCD));
            filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(
            		FiltroSetorComercial.MUNICIPIO);

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //setorComercial nao encontrado
                //Limpa os campos setorComercialCD e setorComercialID do
                // formulario
                inserirQuadraActionForm.setSetorComercialCD("");
                inserirQuadraActionForm.setSetorComercialID("");
                inserirQuadraActionForm
                        .setSetorComercialNome("Setor comercial inexistente.");
                httpServletRequest.setAttribute("corSetorComercial",
                        "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
                
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                inserirQuadraActionForm.setSetorComercialCD(String
                        .valueOf(objetoSetorComercial.getCodigo()));
                inserirQuadraActionForm.setSetorComercialID(String
                        .valueOf(objetoSetorComercial.getId()));
                inserirQuadraActionForm
                        .setSetorComercialNome(objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercial", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "quadraNM");
                
                inserirQuadraActionForm.setIndicadorRelacionamentoQuadraBairro(objetoSetorComercial.getMunicipio().getIndicadorRelacaoQuadraBairro().toString());
                inserirQuadraActionForm.setMunicipioID(objetoSetorComercial.getMunicipio().getId().toString());
                
                int numeroQuadra = fachada.pesquisarMaximoCodigoQuadra(objetoSetorComercial.getId());
                
                numeroQuadra = numeroQuadra + 1;
                
                inserirQuadraActionForm.setQuadraNM("" + numeroQuadra);
            }

        }

    }
    
    private void carregamentoInicialFormulario(Fachada fachada, HttpServletRequest httpServletRequest,
    	InserirQuadraActionForm inserirQuadraActionForm, HttpSession sessao){
    	
    	httpServletRequest.setAttribute("nomeCampo", "localidadeID");
    	
    	if (inserirQuadraActionForm.getIndicadorRedeAguaAux() == null ||
    		inserirQuadraActionForm.getIndicadorRedeAguaAux().equals("")){
    		
    		inserirQuadraActionForm.setIndicadorRedeAguaAux(Quadra.COM_REDE.toString());
    	}
    	
    	if (inserirQuadraActionForm.getIndicadorRedeEsgotoAux() == null ||
        	inserirQuadraActionForm.getIndicadorRedeEsgotoAux().equals("")){
        		
        	inserirQuadraActionForm.setIndicadorRedeEsgotoAux(Quadra.COM_REDE.toString());
        }
    	
    	//QUADRA_PERFIL
        FiltroQuadraPerfil filtroQuadraPerfil = new FiltroQuadraPerfil();

        filtroQuadraPerfil.adicionarParametro(new ParametroSimples(
        FiltroQuadraPerfil.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroQuadraPerfil,
        QuadraPerfil.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            
        	throw new ActionServletException(
            "atencao.pesquisa.nenhum_registro_tabela", null, "Quadra_Perfil");
        } 
        else {
            
        	httpServletRequest.setAttribute("colecaoPerfilQuadra", colecaoPesquisa);
        }

        //AREA_TIPO
        FiltroAreaTipo filtroAreaTipo = new FiltroAreaTipo();

        filtroAreaTipo.adicionarParametro(new ParametroSimples(
        FiltroAreaTipo.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroAreaTipo,
        AreaTipo.class.getName());

        httpServletRequest.setAttribute("colecaoAreaTipo", colecaoPesquisa);
        
        // Indicador de Incremento do Lote inicia com valor = 2
        //if (inserirQuadraActionForm == null || inserirQuadraActionForm.equals("")){
        	inserirQuadraActionForm.setIndicadorIncrementoLote("2");
        //}
        
        //ZEIS
        FiltroZeis filtroZeis = new FiltroZeis();

        filtroZeis.adicionarParametro(new ParametroSimples(
        FiltroZeis.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroZeis, Zeis.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            
        	throw new ActionServletException(
            "atencao.pesquisa.nenhum_registro_tabela", null, "ZEIS");
        } 
        else {
        	httpServletRequest.setAttribute("colecaoZeis", colecaoPesquisa);
        }
        
        //PERMISSÃO PARA ADICIONAR FACE(S) PARA A QUADRA
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        httpServletRequest.setAttribute("permissaoAdicionarQuadraFace", 
        sistemaParametro.getIndicadorQuadraFace().toString());
        
        if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.NAO)){
        	
        	//SISTEMA_ESGOTO
            FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();

            filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(
            FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroSistemaEsgoto,
            SistemaEsgoto.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
                "Sistema_Esgoto");
            } 
            else {
            	httpServletRequest.setAttribute("colecaoSistemaEsgoto", colecaoPesquisa);
            }
        }
    }

    private void desfazer(HttpServletRequest httpServletRequest, HttpSession sessao,
    		InserirQuadraActionForm inserirQuadraActionForm){
    	
    	if (httpServletRequest.getParameter("desfazer") != null
            && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){
            // -------------- bt DESFAZER ---------------
                
    		inserirQuadraActionForm.setIndicadorRedeAguaAux(Quadra.COM_REDE.toString());
    		inserirQuadraActionForm.setIndicadorRedeEsgotoAux(Quadra.COM_REDE.toString());

            inserirQuadraActionForm.setBaciaID("");
            inserirQuadraActionForm.setDistritoOperacionalDescricao("");
            inserirQuadraActionForm.setDistritoOperacionalID("");
            inserirQuadraActionForm.setIndicadorUso("");
            inserirQuadraActionForm.setLocalidadeID("");
            inserirQuadraActionForm.setLocalidadeNome("");
            inserirQuadraActionForm.setPerfilQuadra("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setAreaTipoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setQuadraID("");
            inserirQuadraActionForm.setQuadraNM("");
            inserirQuadraActionForm.setRotaID("");
            inserirQuadraActionForm.setCodigoRota("");
            inserirQuadraActionForm.setRotaMensagem("");
            inserirQuadraActionForm.setSetorCensitarioDescricao("");
            inserirQuadraActionForm.setSetorCensitarioID("");
            inserirQuadraActionForm.setSetorComercialCD("");
            inserirQuadraActionForm.setSetorComercialID("");
            inserirQuadraActionForm.setSetorComercialNome("");
            inserirQuadraActionForm.setSistemaEsgotoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setZeisID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            
            sessao.removeAttribute("colecaoQuadraFace");
            sessao.removeAttribute("telaRetorno");
    	}
    }
    
    private void removerQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao){
    	
    	Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");
    	
    	if (httpServletRequest.getParameter("numeroQuadraFace") != null && 
    		(colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty())){
	    	
    		Integer numeroQuadraFaceParaRemover = Integer.valueOf(
    		httpServletRequest.getParameter("numeroQuadraFace"));
    		
    		Iterator it = colecaoQuadraFace.iterator();
				
			while (it.hasNext()){
					
				QuadraFace quadraFace = (QuadraFace) it.next();
					
				if (quadraFace.getNumeroQuadraFace().equals(numeroQuadraFaceParaRemover)){
					
					colecaoQuadraFace.remove(quadraFace);
					break;
				}
			}
			
			if (colecaoQuadraFace.isEmpty()){
				sessao.removeAttribute("colecaoQuadraFace");
			}
			else{
				sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			}
    	}
    }
    
    /**
     * 
     * Pesquisar Bairro
     * 
     * @Autor Hugo Leonardo
     * @Date 14/01/2011
     */
    private void pesquisarBairro(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        FiltroBairro filtroBairro = new FiltroBairro();

        filtroBairro.adicionarParametro(new ParametroSimples(
        		FiltroBairro.CODIGO, inserirQuadraActionForm.getBairroID()));
        
        if(inserirQuadraActionForm.getMunicipioID() != null && 
        		!inserirQuadraActionForm.getMunicipioID().equals("")){
        	
        	filtroBairro.adicionarParametro(new ParametroSimples(
            		FiltroBairro.MUNICIPIO_ID, inserirQuadraActionForm.getMunicipioID()));
        }

        filtroBairro.adicionarParametro(new ParametroSimples(
        		FiltroBairro.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroBairro, Bairro.class
                .getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Bairo nao encontrado
            //Limpa o campo bairroID do formulário
            inserirQuadraActionForm.setBairroID("");
            inserirQuadraActionForm
                    .setBairroDescricao("Bairro inexistente.");
            httpServletRequest.setAttribute("corBairro", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "bairroID");
        } else {
            Bairro objetoBairro = (Bairro) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            inserirQuadraActionForm.setBairroID(String
                    .valueOf(objetoBairro.getCodigo()));
            inserirQuadraActionForm.setBairroDescricao(objetoBairro.getNome());
            httpServletRequest.setAttribute("corBairro", "valor");
            
        }
    }
}
