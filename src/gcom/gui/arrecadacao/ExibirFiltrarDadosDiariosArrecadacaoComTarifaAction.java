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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**TODO:COSANPA
 * 
 * Descrição da classe 
 *
 * @author Adriana Muniz
 * @date 11/12/2012
 */
public class ExibirFiltrarDadosDiariosArrecadacaoComTarifaAction extends GcomAction {

    public ActionForward execute(
    		ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("filtrarDadosDiariosArrecadacaoComTarifa");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // Verificamos se o processo de dados diários da arrecadação está rodando
        fachada.verificarBatchDadosDiariosArracadacaoRodando();
        
        FiltrarDadosDiariosArrecadacaoComTarifaActionForm filtrarDadosDiariosArrecadacaoComTarifaActionForm = (FiltrarDadosDiariosArrecadacaoComTarifaActionForm) actionForm;
        
        String idLocalidade = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getLocalidade();
        String idElo = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getIdElo();
        String idArrecadador = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getIdArrecadador();
        
        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        boolean entrar = false;
        // Pesquisando a localidade
        if (idLocalidade != null && !idLocalidade.trim().equals("")) {
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setLocalidade(idLocalidade);
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setDescricaoLocalidade(localidade.getDescricao());
				
				if(idElo == null || idElo.equals("")) {
					filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdElo(localidade.getLocalidade().getId().toString());
					filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeElo(localidade.getLocalidade().getDescricao());
				}
				entrar = true;
			} else {
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setLocalidade("");
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setDescricaoLocalidade("");
		}
        
        if (idArrecadador != null && !idArrecadador.trim().equals("") && Integer.parseInt(idArrecadador) > 0) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection<Arrecadador> arrecadadorEncontrado = fachada.pesquisar(filtroArrecadador,Arrecadador.class.getName());

			if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
				Arrecadador arrecadador = (Arrecadador) arrecadadorEncontrado.iterator().next();

				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdArrecadador(idArrecadador);
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeArrecadador(arrecadador.getCliente().getNome());

			} else {
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdArrecadador("");
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeArrecadador("ARRECADADOR INEXISTENTE");
				httpServletRequest.setAttribute("arrecadadorInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeArrecadador("");
		}

        // Pesquisando a Elo Polo
        if (idElo != null && !idElo.trim().equals("")) {
        
        	filtroLocalidade.limparListaParametros();
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idElo));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				boolean flag = false;
				Localidade elo = (Localidade) colecaoLocalidade.iterator().next();

				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdElo(idElo);
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeElo(elo.getLocalidade().getDescricao());
				
				Iterator iteratorColecaoLocalidade = colecaoLocalidade.iterator();
				
				while (iteratorColecaoLocalidade.hasNext()) {
					
					// Obtém os dados do crédito realizado
					Localidade elo2 = (Localidade) iteratorColecaoLocalidade.next();

					if(idLocalidade != null && !idLocalidade.equals("")) {
						if(elo2.getId().toString().equals(idLocalidade)) {
							flag = true;
							String eloDiferente = "NAO";
							filtrarDadosDiariosArrecadacaoComTarifaActionForm.setEloDiferente(eloDiferente);
						}
					}
				}
				if(!flag && idLocalidade != null && !idLocalidade.equals("")){
					filtrarDadosDiariosArrecadacaoComTarifaActionForm.setLocalidade("");
					filtrarDadosDiariosArrecadacaoComTarifaActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInexistente", true);
				}
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setLocalidadeDoElo(elo.getId().toString());
			} else {
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdElo("");
				filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeElo("ELO PÓLO INEXISTENTE");
				httpServletRequest.setAttribute("eloInexistente", true);
			}
		} else if (!entrar){
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeElo("");
		}
        
        // Pesquisando Gerência Regional
        if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
    		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME_ABREVIADO);
    		filtroGerenciaRegional.setConsultaSemLimites(true);
    		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
    		Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
    		sessao.setAttribute("colecaoGerenciasRegionais",colecaoGerenciasRegionais);
        }
        
        // Pesquisando o Tipo de Documento
        if (sessao.getAttribute("colecaoDocumentoTipo") == null) {
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
			Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			sessao.setAttribute("colecaoDocumentoTipo",colecaoDocumentoTipo);
		}
        
        // Pesquisando Sistema Parâmetros
        if (sessao.getAttribute("sistemaParametro") == null) {
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        	sessao.setAttribute("sistemaParametro", sistemaParametro);
        }
        
        // devolve o mapeamento de retorno
        return retorno;
    }

}
