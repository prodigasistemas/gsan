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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirGerarArquivoTextoAtualizacaoCadastralDispositivoMovel");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection colecaoImovelFiltrado = (Collection)sessao.getAttribute("colecaoImovelFiltrado");
        
        if ( colecaoImovelFiltrado != null && !colecaoImovelFiltrado.isEmpty() ) {
        	form.setTamanhoColecaoImovel(colecaoImovelFiltrado.size());
        
        } else {
        	Integer quantidadeImovel = fachada.pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount();
        	
        	if ( quantidadeImovel == null || quantidadeImovel <= 0 ){
        		//Nenhum Imovel cadastrado
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
        	form.setTamanhoColecaoImovel(quantidadeImovel);
        }
        
        //Pesquisar Leiturista
   	 	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
   	 	Integer idEmpresa = usuario.getEmpresa().getId();
   	 	
		Collection colecaoLeiturista = new ArrayList();
		
		// Leiturista da Empresa
		if (idEmpresa != null && !idEmpresa.equals("")) {

			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
					FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID, idEmpresa));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			}

		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
   	 	
        //Pesquisar Situacão Transmissão Leitura
        Collection colecaoSituacaoTransmissaoLeitura = (Collection)sessao.getAttribute("colecaoSituacaoTransmissaoLeitura");
        if(colecaoSituacaoTransmissaoLeitura == null || colecaoSituacaoTransmissaoLeitura.isEmpty()){

			FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = new FiltroSituacaoTransmissaoLeitura();
			filtroSituacaoTransmissaoLeitura
					.setCampoOrderBy(FiltroSituacaoTransmissaoLeitura.DESCRICAO);
			filtroSituacaoTransmissaoLeitura
					.adicionarParametro(new ParametroSimples(
							FiltroSituacaoTransmissaoLeitura.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
	
			colecaoSituacaoTransmissaoLeitura = Fachada.getInstancia()
					.pesquisar(filtroSituacaoTransmissaoLeitura,
							SituacaoTransmissaoLeitura.class.getName());
	
			sessao.setAttribute("colecaoSituacaoTransmissaoLeitura",
					colecaoSituacaoTransmissaoLeitura);
        }
        
        return retorno;
    }
    
}
