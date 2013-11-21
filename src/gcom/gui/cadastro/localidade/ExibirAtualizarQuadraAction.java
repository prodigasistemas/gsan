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
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.FiltroQuadraPerfil;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroZeis;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
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
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
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

/***
 * @author Administrador, Ivan Sergio
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 * @alteracao 28/04/2010 - CRC4066 - Adicionado o Grau de Dificuladade de Execução, o Grau de Risco Segurança Física, 
 * 									o Nível de Pressão e o Grau de Intermitência. 
 */
public class ExibirAtualizarQuadraAction extends GcomAction {

    private String localidadeID;

    private Collection colecaoPesquisa;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarQuadra");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarQuadraActionForm atualizarQuadraActionForm = (AtualizarQuadraActionForm) actionForm;

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        String retornoQuadraFace = (String) httpServletRequest.getParameter("retornoQuadraFace");

        
        //OBTENDO O ID DA QUADRA QUE SERÁ ATUALIZADA
        String quadraID = obterQuadraParaAtualizacao(objetoConsulta, httpServletRequest, sessao);
        
        //PERMISSÃO PARA ADICIONAR FACE(S) PARA A QUADRA
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        httpServletRequest.setAttribute("permissaoAdicionarQuadraFace", 
        sistemaParametro.getIndicadorQuadraFace().toString());
        
        //PERMISSÃO PARA BLOQUEIO ALTERAÇÃO DE IMÓVEIS
        boolean permissaoEspecialBloqueio = fachada.verificarPermissaoEspecial(PermissaoEspecial.BLOQUEAR_ALTERACAO_IMOVEIS,(Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
        
        if (permissaoEspecialBloqueio){
       	 	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", Quadra.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue());
        }else{
        	httpServletRequest.setAttribute("pemissaoIndicadorBloqueio", Quadra.BLOQUEIO_INSERIR_IMOVEL_NAO.intValue());
        	if (atualizarQuadraActionForm != null){
        		if (atualizarQuadraActionForm.getIndicadorBloqueio() != null 
        				&& atualizarQuadraActionForm.getIndicadorBloqueio().equals("1")){
        			httpServletRequest.setAttribute("bloqueio", true);
        		}else{
        			httpServletRequest.setAttribute("bloqueio", false);
        		}
        	}
        }
        
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

            //Recebe o valor do campo localidadeID do formulário.
            localidadeID = atualizarQuadraActionForm.getLocalidadeID();

            switch (Integer.parseInt(objetoConsulta)) {
            //Localidade
            case 1:

                pesquisarLocalidade(atualizarQuadraActionForm, fachada,
                        httpServletRequest);

                break;
            //Setor Comercial
            case 2:

                pesquisarLocalidade(atualizarQuadraActionForm, fachada,
                        httpServletRequest);

                pesquisarSetorComercial(atualizarQuadraActionForm, fachada,
                        httpServletRequest);

                break;

            //Distrito Operacional
            case 5:

                String distritoOperacionalID = atualizarQuadraActionForm
                .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.ID, distritoOperacionalID));

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    
                	//Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formulário
                    atualizarQuadraActionForm.setDistritoOperacionalID("");
                    atualizarQuadraActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } 
                else {
                    
                	DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	atualizarQuadraActionForm.setDistritoOperacionalID(String
                    .valueOf(objetoDistritoOperacional.getId()));
                    atualizarQuadraActionForm
                    .setDistritoOperacionalDescricao(objetoDistritoOperacional
                    .getDescricao());
                    
                    httpServletRequest.setAttribute("corDistritoOperacional", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                }

                break;
            //Setor censitário
            case 6:

                String setorCensitarioID = atualizarQuadraActionForm
                .getSetorCensitarioID();

                FiltroIbgeSetorCensitario filtroIbgeSetorCensitario = new FiltroIbgeSetorCensitario();

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.ID, setorCensitarioID));

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroIbgeSetorCensitario,
                IbgeSetorCensitario.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Setor censitario nao encontrado
                    //Limpa o campo setorCensitarioID do formulário
                    atualizarQuadraActionForm.setSetorCensitarioID("");
                    atualizarQuadraActionForm.setSetorCensitarioDescricao("Setor censitário inexistente.");
                    httpServletRequest.setAttribute("corSetorCensitario", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                } 
                else {
                    
                	IbgeSetorCensitario objetoIbgeSetorCensitario = (IbgeSetorCensitario) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	atualizarQuadraActionForm.setSetorCensitarioID(String
                    .valueOf(objetoIbgeSetorCensitario.getId()));
                    atualizarQuadraActionForm
                    .setSetorCensitarioDescricao(objetoIbgeSetorCensitario
                                    .getDescricao());
                    httpServletRequest.setAttribute("corSetorCensitario", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "zeisID");
                }

                break;
            //Bacia
            case 7:
                
            	String sistemaEsgotoID = atualizarQuadraActionForm
                .getSistemaEsgotoID();

                if (sistemaEsgotoID != null && !sistemaEsgotoID
                    .equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
                	
                	FiltroBacia filtroBacia = new FiltroBacia();
                    
                	filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.SISTEMA_ESGOTO_ID, sistemaEsgotoID));

                    filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroBacia,
                    Bacia.class.getName());

                    sessao.setAttribute("colecaoBacia", colecaoPesquisa);

                } 
                else {
                    atualizarQuadraActionForm.setBaciaID(String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                    sessao.removeAttribute("colecaoBacia");
                }

                break;
                
            //Rota
            case 8:

            	String idRota = atualizarQuadraActionForm.getRotaID();
            	
            	if (idRota != null && !idRota.trim().equals("")) {

            		FiltroRota filtroRota = new FiltroRota();
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
            		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
            		
            		Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
            		
            		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
            		
            		atualizarQuadraActionForm.setCodigoRota(String
                            .valueOf(rota.getCodigo()));
            		atualizarQuadraActionForm.setRotaMensagem(rota
                            .getLeituraTipo().getDescricao());
            		atualizarQuadraActionForm.setLocalidadeID(rota.getSetorComercial().getLocalidade().getId().toString());
            		atualizarQuadraActionForm.setLocalidadeNome(rota.getSetorComercial().getLocalidade().getDescricao());
            		atualizarQuadraActionForm.setSetorComercialID(rota.getSetorComercial().getId().toString());
            		atualizarQuadraActionForm.setSetorComercialCD("" + rota.getSetorComercial().getCodigo());
            		atualizarQuadraActionForm.setSetorComercialNome(rota.getSetorComercial().getDescricao());
            		httpServletRequest.setAttribute("corRotaMensagem",
                            "valor");
            		
            	} 
            	else {
            	
            		//Recebe o valor do campo rotaID do formulário e o código do setor comercial selecionado.
                String codigoRota = atualizarQuadraActionForm.getCodigoRota();
                String setorComercialCD = atualizarQuadraActionForm.getSetorComercialCD();
                
            	if (setorComercialCD == null || setorComercialCD.equalsIgnoreCase("")) {
                    throw new ActionServletException(
                    "atencao.setor_comercial_nao_informado");
                }
            	

                FiltroRota filtroRota = new FiltroRota();
                
                filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
                filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");

                
                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.LOCALIDADE_ID, atualizarQuadraActionForm
                        .getLocalidadeID()));
           
                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.SETOR_COMERCIAL_CODIGO, setorComercialCD));
                
                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.CODIGO_ROTA, codigoRota));
                    
                filtroRota.adicionarParametro(new ParametroSimples(
                        FiltroRota.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Rota
                colecaoPesquisa = fachada.pesquisar(filtroRota, Rota.class
                        .getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Rota nao encontrada
                    //Limpa o campo rotaID do formulário
                    atualizarQuadraActionForm.setRotaID("");
                    atualizarQuadraActionForm.setCodigoRota("");
                    atualizarQuadraActionForm
                            .setRotaMensagem("Rota inexistente.");
                    httpServletRequest.setAttribute("corRotaMensagem",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "codigoRota");
                } else {
                    Rota objetoRota = (Rota) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);

                    atualizarQuadraActionForm.setRotaID(String
                            .valueOf(objetoRota.getId()));
                    atualizarQuadraActionForm.setCodigoRota(String
                                .valueOf(objetoRota.getCodigo()));
                    atualizarQuadraActionForm.setRotaMensagem(objetoRota
                                .getLeituraTipo().getDescricao());
                    httpServletRequest.setAttribute("corRotaMensagem",
                                "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "botaoInserir");
                  }
                
            	}

                break;
                
            //Caso especial (armazena os valores recebidos dos filtros)
            case 9:
            	
            	if(atualizarQuadraActionForm.getBairroID() != null && 
            			!atualizarQuadraActionForm.getBairroID().equals("")){
            		
            		pesquisarBairro(atualizarQuadraActionForm, fachada,
                            httpServletRequest);
            	}
            	
                break;
            default:

                break;
            }
        }
        
            
        //CARREGAMENTO INICIAL DO FORMULARIO
        carregamentoInicialFormulario(fachada, sessao, httpServletRequest, 
        atualizarQuadraActionForm, quadraID, sistemaParametro);
        
        if ((httpServletRequest.getParameter("desfazer") != null
            && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) ||
            ((objetoConsulta == null || objetoConsulta.trim().equalsIgnoreCase("")) &&
            (retornoQuadraFace == null || retornoQuadraFace.trim().equalsIgnoreCase("")) &&
            (httpServletRequest.getParameter("numeroQuadraFace") == null))) {
		 
        	//OPÇÃO DESFAZER 
        	exibirQuadra(quadraID, atualizarQuadraActionForm,fachada, sessao,httpServletRequest,
        	sistemaParametro);
        }
        
        //OPÇÃO REMOVER QUADRA FACE
        removerQuadraFace(httpServletRequest, sessao);
        
        return retorno;
    }

    private void pesquisarLocalidade(
            AtualizarQuadraActionForm atualizarQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos localidadeID e setorComercialID do formulario
            atualizarQuadraActionForm.setLocalidadeNome("Informe Localidade");
            httpServletRequest.setAttribute("corLocalidade", "exception");
        } else {

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, localidadeID));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna localidade
            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Localidade nao encontrada
                //Limpa o campo localidadeID do formulário
                atualizarQuadraActionForm.setLocalidadeID("");
                atualizarQuadraActionForm
                        .setLocalidadeNome("Localidade inexistente.");
                httpServletRequest.setAttribute("corLocalidade", "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "localidadeID");
            } else {
                Localidade objetoLocalidade = (Localidade) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                atualizarQuadraActionForm.setLocalidadeID(String
                        .valueOf(objetoLocalidade.getId()));
                atualizarQuadraActionForm.setLocalidadeNome(objetoLocalidade
                        .getDescricao());
                httpServletRequest.setAttribute("corLocalidade", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
            }
        }

    }

    private void pesquisarSetorComercial(
            AtualizarQuadraActionForm atualizarQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos setorComercialCD e setorComercialID do formulario
            atualizarQuadraActionForm.setSetorComercialCD("");
            atualizarQuadraActionForm.setSetorComercialID("");
            atualizarQuadraActionForm
                    .setSetorComercialNome("Informe Localidade.");
            httpServletRequest.setAttribute("corSetorComercial", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else {
            //Recebe o valor do campo setorComercialCD do formulário.
            String setorComercialCD = atualizarQuadraActionForm
                    .getSetorComercialCD();

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCD));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            
            filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(
            		FiltroSetorComercial.MUNICIPIO);

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //setorComercial nao encontrado
                //Limpa os campos setorComercialCD e setorComercialID do
                // formulario
                atualizarQuadraActionForm.setSetorComercialCD("");
                atualizarQuadraActionForm.setSetorComercialID("");
                atualizarQuadraActionForm
                        .setSetorComercialNome("Setor comercial inexistente.");
                httpServletRequest.setAttribute("corSetorComercial",
                        "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                atualizarQuadraActionForm.setSetorComercialCD(String
                        .valueOf(objetoSetorComercial.getCodigo()));
                atualizarQuadraActionForm.setSetorComercialID(String
                        .valueOf(objetoSetorComercial.getId()));
                atualizarQuadraActionForm
                        .setSetorComercialNome(objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercial", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "quadraNM");
                
                atualizarQuadraActionForm.setIndicadorRelacionamentoQuadraBairro(
                		objetoSetorComercial.getMunicipio().getIndicadorRelacaoQuadraBairro().toString());
                atualizarQuadraActionForm.setMunicipioID(objetoSetorComercial.getMunicipio().getId().toString());
                
            }

        }
    }
    
    private void exibirQuadra(String quadraID, 
    		AtualizarQuadraActionForm atualizarQuadraActionForm,
			Fachada fachada,HttpSession sessao,
			HttpServletRequest httpServletRequest, SistemaParametro sistemaParametro){
    	
    	 
    	 FiltroQuadra filtroQuadra = new FiltroQuadra();
         
         //Objetos que serão retornados pelo hibernate
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("ibgeSetorCensitario");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("zeis");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota.leituraTipo");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio");
         filtroQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroQuadra.BAIRRO);
         
         //NÃO TRABALHA COM FACE DA QUADRA
         if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.NAO)){
         	
        	 filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bacia.sistemaEsgoto");
             filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
         }
    	 
         
         filtroQuadra.adicionarParametro(new ParametroSimples(
         FiltroQuadra.ID, quadraID));

         colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

         //QUADRA PARA ATUALIZAÇÃO
         Quadra quadraSelect = (Quadra) Util
         .retonarObjetoDeColecao(colecaoPesquisa);
         
         sessao.setAttribute("quadraManter", quadraSelect);

         //CARREGAMENTO DO FORMULÁRIO
         
         //ID da quadra
         atualizarQuadraActionForm.setQuadraID(String.valueOf(quadraSelect
         .getId().intValue()));

         //Localidade
         atualizarQuadraActionForm.setLocalidadeID(String.valueOf(quadraSelect
         .getSetorComercial().getLocalidade().getId()));

         atualizarQuadraActionForm.setLocalidadeNome(quadraSelect
         .getSetorComercial().getLocalidade().getDescricao());

         //Setor comercial
         atualizarQuadraActionForm.setSetorComercialID(String
         .valueOf(quadraSelect.getSetorComercial().getId()));

         atualizarQuadraActionForm.setSetorComercialCD(String
         .valueOf(quadraSelect.getSetorComercial().getCodigo()));

         atualizarQuadraActionForm.setSetorComercialNome(quadraSelect
         .getSetorComercial().getDescricao());

         //Número da quadra
         atualizarQuadraActionForm.setQuadraNM(String.valueOf(quadraSelect
         .getNumeroQuadra()));

         //Perfil da quadra
         if (quadraSelect.getQuadraPerfil() != null 
        	&& !quadraSelect.getQuadraPerfil().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
            
        	 atualizarQuadraActionForm.setPerfilQuadra(String
             .valueOf(quadraSelect.getQuadraPerfil().getId()));
         }
         
         //Area Tipo/Localização
         if (quadraSelect.getAreaTipo() != null 
        	&& !quadraSelect.getAreaTipo().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
        	 
        	 atualizarQuadraActionForm.setAreaTipoID(String
             .valueOf(quadraSelect.getAreaTipo().getId()));
        	 
         } 
         else {
        	 atualizarQuadraActionForm.setAreaTipoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
         }

         //NÃO TRABALHA COM FACE DA QUADRA
         if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.NAO)){
        	 
        	 //Indicador de rede de água
             if (quadraSelect.getIndicadorRedeAgua() != null &&
            	 quadraSelect.getIndicadorRedeAgua().intValue() != 0) {
                 atualizarQuadraActionForm.setIndicadorRedeAguaAux(String
                         .valueOf(quadraSelect.getIndicadorRedeAgua()));
                 httpServletRequest.setAttribute("indicadorPossuiRedeAgua", String
                         .valueOf(quadraSelect.getIndicadorRedeAgua()));
             } else {
                 //atualizarQuadraActionForm.setIndicadorRedeAguaAux(String
                   //      .valueOf(Quadra.SEM_REDE));
            	 atualizarQuadraActionForm.setIndicadorRedeAguaAux("");
             }

             //Indicador de rede de esgoto
             if (quadraSelect.getIndicadorRedeEsgoto() != null &&
            	 quadraSelect.getIndicadorRedeEsgoto().intValue() != 0) {
                 atualizarQuadraActionForm.setIndicadorRedeEsgotoAux(String
                         .valueOf(quadraSelect.getIndicadorRedeEsgoto()));
                 httpServletRequest.setAttribute("indicadorPossuiRedeEsgoto", String
                         .valueOf(quadraSelect.getIndicadorRedeEsgoto()));
             } else {
                // atualizarQuadraActionForm.setIndicadorRedeEsgotoAux(String
                  //       .valueOf(Quadra.SEM_REDE));
            	atualizarQuadraActionForm.setIndicadorRedeEsgotoAux("");
             }


             //Bacia e Sistema de esgoto
             if (quadraSelect.getBacia() != null) {
                 atualizarQuadraActionForm.setBaciaID(String.valueOf(quadraSelect
                         .getBacia().getId()));

                 atualizarQuadraActionForm.setSistemaEsgotoID(String
                         .valueOf(quadraSelect.getBacia().getSistemaEsgoto()
                                 .getId()));
                 FiltroBacia filtroBacia = new FiltroBacia();
                 filtroBacia.adicionarParametro(new ParametroSimples(
                         FiltroBacia.SISTEMA_ESGOTO_ID, quadraSelect.getBacia()
                                 .getSistemaEsgoto().getId()));

                 filtroBacia.adicionarParametro(new ParametroSimples(
                         FiltroBacia.INDICADORUSO,
                         ConstantesSistema.INDICADOR_USO_ATIVO));

                 //Retorna uma coleção de bacias
                 colecaoPesquisa = fachada.pesquisar(filtroBacia, Bacia.class
                         .getName());

                 sessao.setAttribute("colecaoBacia", colecaoPesquisa);
                 atualizarQuadraActionForm.setSistemaEsgotoID(quadraSelect.getBacia()
                                 .getSistemaEsgoto().getId().toString());//teste
             }else{
            	 atualizarQuadraActionForm.setSistemaEsgotoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            	 sessao.removeAttribute("colecaoBacia");
             }

             //Distrito operacional
             if (quadraSelect.getDistritoOperacional() != null) {
                 atualizarQuadraActionForm
                         .setDistritoOperacionalID(String.valueOf(quadraSelect
                                 .getDistritoOperacional().getId()));

                 atualizarQuadraActionForm
                         .setDistritoOperacionalDescricao(quadraSelect
                                 .getDistritoOperacional()
                                 .getDescricaoAbreviada());
             } else {
            	 atualizarQuadraActionForm
                 .setDistritoOperacionalID("");
            	 atualizarQuadraActionForm
                 .setDistritoOperacionalDescricao("");
             }
         }

         //Setor censitário
         if (quadraSelect.getIbgeSetorCensitario() != null) {
             atualizarQuadraActionForm
                     .setSetorCensitarioID(String.valueOf(quadraSelect
                             .getIbgeSetorCensitario().getId()));

             atualizarQuadraActionForm
                     .setSetorCensitarioDescricao(quadraSelect
                             .getIbgeSetorCensitario().getDescricao());
         } else {
        	 atualizarQuadraActionForm
             .setSetorCensitarioID("");
        	 atualizarQuadraActionForm
             .setSetorCensitarioDescricao("");
         }

         //ZEIS
         if (quadraSelect.getZeis() != null &&
        		 !quadraSelect.getZeis().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
             atualizarQuadraActionForm.setZeisID(String.valueOf(quadraSelect
                     .getZeis().getId()));
         }else {
        	 atualizarQuadraActionForm.setZeisID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
         }

         //Rota
         if (quadraSelect.getRota() != null) {
             atualizarQuadraActionForm.setRotaID(String.valueOf(quadraSelect
                     .getRota().getId()));
             atualizarQuadraActionForm.setCodigoRota(String.valueOf(quadraSelect
                     .getRota().getCodigo()));
             atualizarQuadraActionForm.setRotaMensagem(quadraSelect.getRota()
                     .getLeituraTipo().getDescricao());
         }

         //Indicador uso
         if (quadraSelect.getIndicadorUso() != null) {
             atualizarQuadraActionForm.setIndicadorUso(String
                     .valueOf(quadraSelect.getIndicadorUso()));
         }
         
		 //Indicador Bloqueio
		 if (quadraSelect.getIndicadorBloqueio() != null) {
		     atualizarQuadraActionForm.setIndicadorBloqueio(String
		             .valueOf(quadraSelect.getIndicadorBloqueio()));
		     if (quadraSelect.getIndicadorBloqueio().intValue() == Quadra.BLOQUEIO_INSERIR_IMOVEL_SIM.intValue()){
					httpServletRequest.setAttribute("bloqueio", true);
			}
		 }else{
				httpServletRequest.setAttribute("bloqueio", false);
		}
         
		
         
         //Indicador de Incremento Lote
         if (quadraSelect.getIndicadorIncrementoLote() != null) {
             atualizarQuadraActionForm.setIndicadorIncrementoLote(String
                     .valueOf(quadraSelect.getIndicadorIncrementoLote()));
         }

         //UTILIZA O CONCEITO DE FACE DA QUADRA
         if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
        	 
        	 //QUADRA_FACE
             FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
             
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("bacia.sistemaEsgoto");
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("grauDificuldadeExecucao");
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("grauRiscoSegurancaFisica");
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("condicaoAbastecimentoAgua.nivelPressao");
             filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("condicaoAbastecimentoAgua.grauIntermitencia");
             
             filtroQuadraFace.adicionarParametro(new ParametroSimples(
             FiltroQuadraFace.ID_QUADRA, quadraSelect.getId()));

             Collection colecaoQuadraFace = null;
             colecaoQuadraFace = fachada.pesquisar(filtroQuadraFace, QuadraFace.class.getName());
             
             if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {
                 
            	 sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
             }
             else{
            	 sessao.removeAttribute("colecaoQuadraFace");
             }
         }
         
         // Bairro
         if(quadraSelect.getBairro() != null){
        	 
             atualizarQuadraActionForm.setBairroID(
            		 String.valueOf(quadraSelect.getBairro().getCodigo()));

             atualizarQuadraActionForm.setBairroDescricao(
            		 quadraSelect.getBairro().getNome());
         }
         
         // Municipio
         if(quadraSelect.getSetorComercial() != null && 
        		 quadraSelect.getSetorComercial().getMunicipio() != null){
        	 
        	 atualizarQuadraActionForm.setMunicipioID(
        			 String.valueOf(quadraSelect.getSetorComercial().getMunicipio().getId()));
        	 
        	 atualizarQuadraActionForm.setIndicadorRelacionamentoQuadraBairro(
        			 quadraSelect.getSetorComercial().getMunicipio().getIndicadorRelacaoQuadraBairro().toString());
         }else{
        	 atualizarQuadraActionForm.setIndicadorRelacionamentoQuadraBairro("2");
         }
         
    }
    
    
    private void carregamentoInicialFormulario(Fachada fachada, HttpSession sessao, 
    		HttpServletRequest httpServletRequest, AtualizarQuadraActionForm atualizarQuadraActionForm,
    		String quadraID, SistemaParametro sistemaParametro){
    	
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
    
    
    private String obterQuadraParaAtualizacao(String objetoConsulta, HttpServletRequest httpServletRequest,
    	HttpSession sessao){
    	
    	String quadraID = null;
		
        if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
	    	&& (httpServletRequest.getParameter("desfazer") == null)){
	           
			 //Recupera o id da Quadra que vai ser atualizada
			
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
				quadraID = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
	           	
				//Definindo a volta do botão Voltar p Filtrar Quadra
				sessao.setAttribute("voltar", "filtrar");
	   	    	sessao.setAttribute("idRegistroAtualizar",quadraID);
	   	    	sessao.setAttribute("indicadorUso", "3");
	   	    	
	        }
			else if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
	        	
	        	quadraID = (String)sessao.getAttribute("idRegistroAtualizar");
	   			//Definindo a volta do botão Voltar p Filtrar Quadra
	        	sessao.setAttribute("voltar", "filtrar");
	        	
	        }
			else if (httpServletRequest.getParameter("idRegistroAtualizar")!= null) {
	        	
	        	quadraID = httpServletRequest.getParameter("idRegistroAtualizar");
		        //Definindo a volta do botão Voltar p Manter Quadra
	        	sessao.setAttribute("voltar", "manter");
		        sessao.setAttribute("idRegistroAtualizar",quadraID);
	        }
		
		}
		 else{
			quadraID = (String)sessao.getAttribute("idRegistroAtualizar");
		}
        
        return quadraID;
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
     * @Date 17/01/2011
     */
    private void pesquisarBairro(
    		AtualizarQuadraActionForm atualizarQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        FiltroBairro filtroBairro = new FiltroBairro();

        filtroBairro.adicionarParametro(new ParametroSimples(
        		FiltroBairro.CODIGO, atualizarQuadraActionForm.getBairroID()));
        
        if(atualizarQuadraActionForm.getMunicipioID() != null && 
        		!atualizarQuadraActionForm.getMunicipioID().equals("")){
        	
        	filtroBairro.adicionarParametro(new ParametroSimples(
            		FiltroBairro.MUNICIPIO_ID, atualizarQuadraActionForm.getMunicipioID()));
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
        	atualizarQuadraActionForm.setBairroID("");
        	atualizarQuadraActionForm
                    .setBairroDescricao("Bairro inexistente.");
            httpServletRequest.setAttribute("corBairro", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "bairroID");
        } else {
            Bairro objetoBairro = (Bairro) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            atualizarQuadraActionForm.setBairroID(String
                    .valueOf(objetoBairro.getCodigo()));
            atualizarQuadraActionForm.setBairroDescricao(objetoBairro.getNome());
            httpServletRequest.setAttribute("corBairro", "valor");
            
        }
    }

}
