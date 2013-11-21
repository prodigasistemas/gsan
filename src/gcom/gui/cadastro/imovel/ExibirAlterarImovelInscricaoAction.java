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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class ExibirAlterarImovelInscricaoAction extends GcomAction {

    private Collection colecaoPesquisa = null;

    private String localidadeIDOrigem = null;
    
    private String localidadeIDDestino = null;
    
    private String setorComercialCDOrigem = null;
    
    private String setorComercialCDDestino = null;

    private String setorComercialIDOrigem = null;
    
    private String setorComercialIDDestino = null;

    private String quadraNMOrigem = null;
    
    private String quadraNMDestino = null;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAlterarImovelInscricao");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm alterarImovelInscricaoActionForm = (DynaValidatorForm) sessao
                .getAttribute("AlterarImovelInscricaoActionForm");

        String objetoConsulta = (String) httpServletRequest
                .getParameter("objetoConsulta");
        String inscricaoTipo = (String) httpServletRequest
                .getParameter("inscricaoTipo");

        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")
                && inscricaoTipo != null
                && !inscricaoTipo.trim().equalsIgnoreCase("")) {

            switch (Integer.parseInt(objetoConsulta)) {
            //Localidade
            case 1:

                pesquisarLocalidade(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                break;
            // Setor Comercial
            case 2:

                pesquisarLocalidade(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                pesquisarSetorComercial(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                break;
            // Quadra
            case 3:

                pesquisarLocalidade(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                pesquisarSetorComercial(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                pesquisarQuadra(inscricaoTipo,
                        alterarImovelInscricaoActionForm, fachada,
                        httpServletRequest);

                break;
            default:
                break;
            }
        } else {
            sessao.removeAttribute("AlterarImovelInscricaoActionForm");
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

    private void pesquisarLocalidade(String inscricaoTipo,
            DynaValidatorForm alterarImovelInscricaoActionForm,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        //Recebe o valor do campo localidadeOrigemID do formulário.
        localidadeIDOrigem = (String) alterarImovelInscricaoActionForm
                .get("localidadeOrigemID");

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeIDOrigem));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !localidadeIDOrigem.equalsIgnoreCase("")) {
            //Localidade nao encontrada
            //Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
            // formulário
        	if(alterarImovelInscricaoActionForm.get("localidadeOrigemID").equals(alterarImovelInscricaoActionForm.get("localidadeDestinoID")))
            {
            	alterarImovelInscricaoActionForm.set("localidadeDestinoID", "");
            }
        	alterarImovelInscricaoActionForm.set("localidadeOrigemID", "");
            alterarImovelInscricaoActionForm.set("nomeLocalidadeOrigem",
                    "Localidade inexistente");
            httpServletRequest.setAttribute("corLocalidadeOrigem",
            		"exception");
        } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            alterarImovelInscricaoActionForm.set("localidadeOrigemID",
                    String.valueOf(objetoLocalidade.getId()));
            alterarImovelInscricaoActionForm.set("nomeLocalidadeOrigem",
                    objetoLocalidade.getDescricao());
            if(alterarImovelInscricaoActionForm.get("localidadeDestinoID") == null || alterarImovelInscricaoActionForm.get("localidadeDestinoID").equals("") || alterarImovelInscricaoActionForm.get("localidadeOrigemID").equals(alterarImovelInscricaoActionForm.get("localidadeDestinoID")))
            {
            	alterarImovelInscricaoActionForm.set("localidadeDestinoID",
                    String.valueOf(objetoLocalidade.getId()));
            	alterarImovelInscricaoActionForm.set("nomeLocalidadeDestino",
                    objetoLocalidade.getDescricao());
            }
            httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
            httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
        }
        //Recebe o valor do campo localidadeDestinoID do formulário.
        localidadeIDDestino = (String) alterarImovelInscricaoActionForm
                .get("localidadeDestinoID");

        //Limpa os parametros do filtro
        filtroLocalidade.limparListaParametros();
        
        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeIDDestino));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Localidade nao encontrada
            //Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
            // do formulário
            alterarImovelInscricaoActionForm.set("localidadeDestinoID", "");
            alterarImovelInscricaoActionForm.set("nomeLocalidadeDestino",
                    "Localidade inexistente");
            httpServletRequest.setAttribute("corLocalidadeDestino",
                    "exception");
            httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
        } else {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            alterarImovelInscricaoActionForm.set("localidadeDestinoID",
                    String.valueOf(objetoLocalidade.getId()));
            alterarImovelInscricaoActionForm.set("nomeLocalidadeDestino",
                    objetoLocalidade.getDescricao());
            httpServletRequest
                    .setAttribute("corLocalidadeDestino", "valor");
            if(!alterarImovelInscricaoActionForm.get("setorComercialOrigemCD").equals(""))
            {
            	httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
            }
        }
    }
    
    private void pesquisarSetorComercial(String inscricaoTipo,
            DynaValidatorForm alterarImovelInscricaoActionForm,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        //Recebe o valor do campo localidadeOrigemID do formulário.
        localidadeIDOrigem = (String) alterarImovelInscricaoActionForm
                .get("localidadeOrigemID");

        // O campo localidadeOrigemID será obrigatório
        if (localidadeIDOrigem != null
                && !localidadeIDOrigem.trim().equalsIgnoreCase("")) {

            setorComercialCDOrigem = (String) alterarImovelInscricaoActionForm
                    .get("setorComercialOrigemCD");

            //Adiciona o id da localidade que está no formulário para
            // compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeIDOrigem));

            //Adiciona o código do setor comercial que esta no formulário
            // para compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCDOrigem));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !alterarImovelInscricaoActionForm.get("setorComercialOrigemCD").equals("")) {
                //Setor Comercial nao encontrado
                //Limpa os campos setorComercialOrigemCD,
                // nomeSetorComercialOrigem e setorComercialOrigemID do
                // formulário
            	if(alterarImovelInscricaoActionForm.get("setorComercialOrigemCD").equals(alterarImovelInscricaoActionForm.get("setorComercialDestinoCD")))
                {
                	alterarImovelInscricaoActionForm.set("setorComercialDestinoCD", "");
                }
            	alterarImovelInscricaoActionForm.set(
                        "setorComercialOrigemCD", "");
                alterarImovelInscricaoActionForm.set(
                        "setorComercialOrigemID", "");
                alterarImovelInscricaoActionForm.set(
                        "nomeSetorComercialOrigem",
                        "Setor comercial inexistente");
                httpServletRequest.setAttribute("corSetorComercialOrigem",
                        "exception");
            } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                alterarImovelInscricaoActionForm.set(
                        "setorComercialOrigemCD", String
                                .valueOf(objetoSetorComercial.getCodigo()));
                alterarImovelInscricaoActionForm.set(
                        "setorComercialOrigemID", String
                                .valueOf(objetoSetorComercial.getId()));
                alterarImovelInscricaoActionForm.set(
                        "nomeSetorComercialOrigem", objetoSetorComercial
                                .getDescricao());
                if(alterarImovelInscricaoActionForm.get("setorComercialDestinoCD") == null || alterarImovelInscricaoActionForm.get("setorComercialDestinoCD").equals("") || alterarImovelInscricaoActionForm.get("setorComercialDestinoCD").equals(alterarImovelInscricaoActionForm.get("setorComercialOrigemCD")))
                {
                    alterarImovelInscricaoActionForm.set(
                            "setorComercialDestinoCD", String
                                    .valueOf(objetoSetorComercial.getCodigo()));
                    alterarImovelInscricaoActionForm.set(
                            "setorComercialDestinoID", String
                                    .valueOf(objetoSetorComercial.getId()));
                    alterarImovelInscricaoActionForm.set(
                            "nomeSetorComercialDestino", objetoSetorComercial
                                    .getDescricao());
                }
                httpServletRequest.setAttribute("corSetorComercialOrigem",
                        "valor");
               	httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
            }
        } else {
            //Limpa o campo setorComercialOrigemCD do formulário
        	if (!alterarImovelInscricaoActionForm.get("setorComercialOrigemCD").equals(""))
        	{
        		alterarImovelInscricaoActionForm.set("setorComercialOrigemCD",
                    "");
        		alterarImovelInscricaoActionForm.set(
                    "nomeSetorComercialOrigem",
                    "Informe a localidade da inscrição de origem.");
        		httpServletRequest.setAttribute("corSetorComercialOrigem",
                    "exception");
        	}
        }
        //Recebe o valor do campo localidadeDestinoID do formulário.
        localidadeIDDestino = (String) alterarImovelInscricaoActionForm
                .get("localidadeDestinoID");

        // O campo localidadeOrigem será obrigatório
        if (localidadeIDDestino != null
                && !localidadeIDDestino.trim().equalsIgnoreCase("")) {

            setorComercialCDDestino = (String) alterarImovelInscricaoActionForm
                    .get("setorComercialDestinoCD");

            //limpa o filtro
            filtroSetorComercial.limparListaParametros();
            
            //Adiciona o id da localidade que está no formulário para
            // compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeIDDestino));

            //Adiciona o código do setor comercial que esta no formulário
            // para compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCDDestino));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Setor Comercial nao encontrado
                //Limpa os campos setorComercialDestinoCD,
                // nomeSetorComercialDestino e setorComercialDestinoID do
                // formulário
                alterarImovelInscricaoActionForm.set(
                        "setorComercialDestinoCD", "");
                alterarImovelInscricaoActionForm.set(
                        "setorComercialDestinoID", "");
                alterarImovelInscricaoActionForm.set(
                        "nomeSetorComercialDestino",
                        "Setor comercial inexistente");
                httpServletRequest.setAttribute("corSetorComercialDestino",
                        "exception");
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                alterarImovelInscricaoActionForm.set(
                        "setorComercialDestinoCD", String
                                .valueOf(objetoSetorComercial.getCodigo()));
                alterarImovelInscricaoActionForm.set(
                        "setorComercialDestinoID", String
                                .valueOf(objetoSetorComercial.getId()));
                alterarImovelInscricaoActionForm.set(
                        "nomeSetorComercialDestino", objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercialDestino",
                        "valor");
                if(!alterarImovelInscricaoActionForm.get("quadraOrigemNM").equals(""))
                {
                	httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
                }
            }
        } else {
            //Limpa o campo setorComercialDestinoCD do formulário
            alterarImovelInscricaoActionForm.set("setorComercialDestinoCD",
                    "");
            alterarImovelInscricaoActionForm.set(
                    "nomeSetorComercialDestino",
                    "Informe a localidade da inscrição de destino.");
            httpServletRequest.setAttribute("corSetorComercialDestino",
                    "exception");
        }
    }

    private void pesquisarQuadra(String inscricaoTipo,
            DynaValidatorForm alterarImovelInscricaoActionForm,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroQuadra filtroQuadra = new FiltroQuadra();
        
        //Objetos que serão retornados pelo hibernate.
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

        //Recebe os valores dos campos setorComercialOrigemCD e
        // setorComercialOrigemID do formulário.
        setorComercialCDOrigem = (String) alterarImovelInscricaoActionForm
                .get("setorComercialOrigemCD");

        setorComercialIDOrigem = (String) alterarImovelInscricaoActionForm
                .get("setorComercialOrigemID");

        // Os campos setorComercialOrigemCD e setorComercialID serão
        // obrigatórios
        if (setorComercialCDOrigem != null
                && !setorComercialCDOrigem.trim().equalsIgnoreCase("")
                && setorComercialIDOrigem != null
                && !setorComercialIDOrigem.trim().equalsIgnoreCase("")) {

            quadraNMOrigem = (String) alterarImovelInscricaoActionForm
                    .get("quadraOrigemNM");

            //Adiciona o id do setor comercial que está no formulário para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDOrigem));

            //Adiciona o número da quadra que esta no formulário para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, quadraNMOrigem));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna quadra
            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !alterarImovelInscricaoActionForm.get("quadraOrigemNM").equals("")) {
                //Quadra nao encontrada
                //Limpa os campos quadraOrigemNM e quadraOrigemID do
                // formulário
            	if(alterarImovelInscricaoActionForm.get("quadraOrigemNM").equals(alterarImovelInscricaoActionForm.get("quadraDestinoNM")))
                {
                	alterarImovelInscricaoActionForm.set("quadraDestinoNM", "");
                }
            	alterarImovelInscricaoActionForm.set("quadraOrigemNM", "");
                alterarImovelInscricaoActionForm.set("quadraOrigemID", "");
                //Mensagem de tela
                alterarImovelInscricaoActionForm.set(
                        "quadraMensagemOrigem", "Quadra inexistente");
                httpServletRequest.setAttribute("corQuadraOrigem",
                        "exception");
            } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
                Quadra objetoQuadra = (Quadra) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                alterarImovelInscricaoActionForm.set("quadraOrigemNM",
                        String.valueOf(objetoQuadra.getNumeroQuadra()));
                alterarImovelInscricaoActionForm.set("quadraOrigemID",
                        String.valueOf(objetoQuadra.getId()));
                if(alterarImovelInscricaoActionForm.get("quadraDestinoNM") == null || alterarImovelInscricaoActionForm.get("quadraDestinoNM").equals("") || alterarImovelInscricaoActionForm.get("quadraOrigemNM").equals(alterarImovelInscricaoActionForm.get("quadraDestinoNM")))
                {
	                alterarImovelInscricaoActionForm.set("quadraDestinoNM",
                            String.valueOf(objetoQuadra.getNumeroQuadra()));
                    alterarImovelInscricaoActionForm.set("quadraDestinoID",
                            String.valueOf(objetoQuadra.getId()));
                }
                httpServletRequest.setAttribute("corQuadraOrigem", "valor");
                httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
            }
        } else {
        	if (!alterarImovelInscricaoActionForm.get("quadraOrigemNM").equals(""))
        	{
        		//Limpa o campo quadraOrigemNM do formulário
        		alterarImovelInscricaoActionForm.set("quadraOrigemNM", "");
        		alterarImovelInscricaoActionForm.set("quadraMensagemOrigem",
                    "Informe o setor comercial da inscrição de origem.");
        		httpServletRequest.setAttribute("corQuadraOrigem", "exception");
        	}
        }
        //Recebe os valores dos campos setorComercialOrigemCD e
        // setorComercialOrigemID do formulário.
        setorComercialCDDestino = (String) alterarImovelInscricaoActionForm
                .get("setorComercialDestinoCD");
        setorComercialIDDestino = (String) alterarImovelInscricaoActionForm
                .get("setorComercialDestinoID");

        // Os campos setorComercialOrigemCD e setorComercialID serão
        // obrigatórios
        if (setorComercialCDDestino != null
                && !setorComercialCDDestino.trim().equalsIgnoreCase("")
                && setorComercialIDDestino != null
                && !setorComercialIDDestino.trim().equalsIgnoreCase("")) {

            quadraNMDestino = (String) alterarImovelInscricaoActionForm
                    .get("quadraDestinoNM");

            //Limpa os parametros do filtro
            filtroQuadra.limparListaParametros();
            
            //Adiciona o id do setor comercial que está no formulário para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDDestino));

            //Adiciona o número da quadra que esta no formulário para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, quadraNMDestino));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna quadra
            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Quadra nao encontrada
                //Limpa os campos quadraOrigemNM e quadraOrigemID do
                // formulário
                alterarImovelInscricaoActionForm.set("quadraDestinoNM", "");
                alterarImovelInscricaoActionForm.set("quadraDestinoID", "");
                //Mensagem de tela
                alterarImovelInscricaoActionForm.set(
                        "quadraMensagemDestino", "Quadra inexistente");
                httpServletRequest.setAttribute("corQuadraDestino",
                        "exception");
            } else {
                Quadra objetoQuadra = (Quadra) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                alterarImovelInscricaoActionForm.set("quadraDestinoNM",
                        String.valueOf(objetoQuadra.getNumeroQuadra()));
                alterarImovelInscricaoActionForm.set("quadraDestinoID",
                        String.valueOf(objetoQuadra.getId()));
                httpServletRequest
                        .setAttribute("corQuadraDestino", "valor");
                if(!alterarImovelInscricaoActionForm.get("loteOrigem").equals(""))
                {
                	httpServletRequest.setAttribute("nomeCampo", "loteDestino");
                }
            }
        } else {
            //Limpa o campo setorComercialOrigemCD do formulário
            alterarImovelInscricaoActionForm.set("quadraDestinoNM", "");
            //Mensagem de tela
            alterarImovelInscricaoActionForm.set("quadraMensagemDestino",
                    "Informe o setor comercial da inscrição.");
            httpServletRequest
                    .setAttribute("corQuadraDestino", "exception");
        }
    }
}
