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

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        PesquisarAtualizarSetorComercialActionForm form = (PesquisarAtualizarSetorComercialActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        //------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SETOR_COMERCIAL_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_SETOR_COMERCIAL_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        String localidadeID = form.getLocalidadeID();
        String setorComercialCD = form.getSetorComercialCD();
        String setorComercialNome = form.getSetorComercialNome();
        String municipioID = form.getMunicipioID();
        String indicadorSetorAlternativo = "";
        
        //Indicador Setor Alternativo
        if ( form.getIndicadorSetorAlternativo() != null && !form.getIndicadorSetorAlternativo().equals("") ) {
    		indicadorSetorAlternativo = form.getIndicadorSetorAlternativo();	
    	}

        if (Util.verificarNaoVazio(localidadeID)) {

        	Collection colecaoPesquisa = null;

            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

            filtroLocalidade.adicionarParametro(
            	new ParametroSimples(
            		FiltroLocalidade.ID, 
                	localidadeID));

            filtroLocalidade.adicionarParametro(
            	new ParametroSimples(
            		FiltroLocalidade.INDICADORUSO,
            		ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna localidade
            colecaoPesquisa = 
            	this.getFachada().pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Localidade inexistente
                throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
            } else {
                Localidade localidade = 
                	(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);

                if (setorComercialCD == null || setorComercialCD.equalsIgnoreCase("")) {
                    //Código do setor comercial não informado.
                	throw new ActionServletException("atencao.codigo_setor_comercial_nao_informado");
                } else if (setorComercialNome == null
                        || setorComercialNome.equalsIgnoreCase("")) {
                    //Nome do setor comercial não informado.
                    throw new ActionServletException("atencao.nome_setor_comercial_nao_informado");
                } else if (municipioID == null || municipioID.equalsIgnoreCase("")) {
                     //Código do município não informado.
                    throw new ActionServletException("atencao.municipio_nao_informado");
                } else {
                	
                	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

                    filtroMunicipio.adicionarParametro(
                    	 new ParametroSimples(
                    		FiltroMunicipio.ID, 
                    		municipioID));

                    filtroMunicipio.adicionarParametro(
                    	new ParametroSimples(
                			FiltroMunicipio.INDICADOR_USO,
                			ConstantesSistema.INDICADOR_USO_ATIVO));

                    //Retorna municipio
                    colecaoPesquisa = this.getFachada().pesquisar(
                    		filtroMunicipio, 
                    		Municipio.class.getName());

                    if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    	throw new ActionServletException("atencao.pesquisa.municipio_inexistente");
                    } else {
                    	Municipio municipio = 
                    		(Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
                          
                    	//Cria o objeto setorComercial que será
                    	// inserido na base
                    	SetorComercial setorComercial = new SetorComercial();
                    	
                    	setorComercial.setCodigo(Integer.parseInt(setorComercialCD));
                    	setorComercial.setDescricao(setorComercialNome);
                    	setorComercial.setLocalidade(localidade);
                    	setorComercial.setMunicipio(municipio);
                    	setorComercial.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
                    	setorComercial.setUltimaAlteracao(new Date());
                    	setorComercial.setIndicadorSetorAlternativo(new Short (indicadorSetorAlternativo) );
                          
                        //------------ REGISTRAR TRANSAÇÃO ----------------
                        setorComercial.setOperacaoEfetuada(operacaoEfetuada);
                        setorComercial.adicionarUsuario(usuario, 
                      			UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
                      	registradorOperacao.registrarOperacao(setorComercial);
                        //------------ REGISTRAR TRANSAÇÃO ----------------  

                      	Collection colecaoFontes = (Collection)
                      		this.getSessao(httpServletRequest).getAttribute("colecaoFonteCaptacao");
                      	
                      	Integer codigoSetorComercialInserido = 
                      		(Integer) this.getFachada().inserirSetorComercial(setorComercial,colecaoFontes);
                                
                        montarPaginaSucesso(httpServletRequest,
                        		"Setor Comercial de código " + setorComercial.getCodigo()
                                	+ " da localidade "  + localidade.getId() 
                                	+ " - "  + localidade.getDescricao().toUpperCase() 
                                	+ " inserido com sucesso.",
                                	"Inserir outro Setor Comercial",
                                	"exibirInserirSetorComercialAction.do?menu=sim",
                                	"exibirAtualizarSetorComercialAction.do?menu=sim&setorComercialID=" + 
                                	codigoSetorComercialInserido, "Atualizar Setor Comercial Inserido");
                     }
                }
            }
        } else {
            // Campo localidadeID não informado.
            throw new ActionServletException("atencao.localidade_nao_informada");
        }

        //devolve o mapeamento de retorno
        return retorno;
    }

}
