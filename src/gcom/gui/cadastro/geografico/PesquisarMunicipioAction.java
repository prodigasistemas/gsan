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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Realiza a pesquisa de responsavel superior de acordo com os parâmetros
 * informados
 * 
 * @author Sávio Luiz
 * @created 20 de Julho de 2005
 */

public class PesquisarMunicipioAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("listaMunicipio");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Recupera os parâmetros do form
        String nome = (String) pesquisarActionForm.get("nomeMunicipio");
        Integer idRegiaoDesenvolvimento = (Integer) pesquisarActionForm
                .get("idRegiaoDesenvolvimento");
        Integer idRegiao = (Integer) pesquisarActionForm.get("idRegiao");
        Integer idMicrorregiao = (Integer) pesquisarActionForm
                .get("idMicrorregiao");
        Integer idUnidadeFederacao = (Integer) pesquisarActionForm
                .get("idUnidadeFederacao");
        String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

        FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

        filtroMunicipio.setCampoOrderBy(FiltroCliente.NOME);

        boolean peloMenosUmParametroInformado = false;

        //Insere os parâmetros informados no filtro

        if (nome != null && !nome.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroMunicipio.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroMunicipio.NOME, nome));
			} else {
				filtroMunicipio.adicionarParametro(new ComparacaoTexto(
						FiltroMunicipio.NOME, nome));
			}
        }

        if (idRegiaoDesenvolvimento != null
                && idRegiaoDesenvolvimento.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.REGIAO_DESENVOLVOMENTO_ID,
                    idRegiaoDesenvolvimento));
        }

        if (idRegiao != null
                && idRegiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.REGIAO_ID, idRegiao));
        }

        if (idMicrorregiao != null
                && idMicrorregiao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.MICRORREGICAO_ID, idMicrorregiao));
        }

        if (idUnidadeFederacao != null
                && idUnidadeFederacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
            peloMenosUmParametroInformado = true;
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.UNIDADE_FEDERACAO_ID, idUnidadeFederacao));
        }

        if( sessao.getAttribute("indicadorUsoTodos") == null ){
	        filtroMunicipio.adicionarParametro(new ParametroSimples(
	                FiltroMunicipio.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
        }    

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        Collection municipios = null;

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        //adiciona as dependências para serem mostradas na página
        filtroMunicipio
                .adicionarCaminhoParaCarregamentoEntidade("microrregiao.regiao");

        //Faz a busca das empresas
        municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
                .getName());

      	    // Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMunicipio, Municipio.class.getName());
			municipios = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			if(municipios.isEmpty()){
				//Nenhum logradouro cadastrado de acordo com os parâmetros passados
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "município");
			}
            sessao.setAttribute("colecaoMunicipios", municipios);

        return retorno;
    }

}
