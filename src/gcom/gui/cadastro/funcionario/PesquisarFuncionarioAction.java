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
package gcom.gui.cadastro.funcionario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
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

/**
 * Action que define o pré-processamento da página de pesquisa de funcionário
 * 
 * @author Vivianne Sousa
 * @created 02/03/2006
 */

public class PesquisarFuncionarioAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
  
  //Inicializacoes de variaveis
  ActionForward retorno = actionMapping.findForward("retornoPesquisa");
  HttpSession sessao = httpServletRequest.getSession(false);
  //Fachada fachada = Fachada.getInstancia();  
  boolean peloMenosUmParametroInformado = false;
  PesquisarFuncionarioActionForm form = (PesquisarFuncionarioActionForm) actionForm;
  
  String id = form.getId();
  String nome = form.getNome();
  String idUnidadeEmpresa = form.getIdUnidadeEmpresa();
  String tipoPesquisa = form.getTipoPesquisa();
  
  //Matrícula mair q zero 
  if (id.equals("0")){
  	throw new ActionServletException("atencao.matricula_usuario_maior_zero");
  }
  
  FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
  filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
  filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
  
  
  
  
  
  
  
  //Pesquisa id  
  if(id != null && !id.equals("")){
   filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, id));
   peloMenosUmParametroInformado = true;
  }
  
  //Pesquisa nome  
  if(nome != null && !nome.equals("")){
	  
	  if (tipoPesquisa != null
				&& tipoPesquisa
						.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
								.toString())) {

			filtroFuncionario
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroFuncionario.NOME, nome));
		} else {

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NOME, nome));
		}
   //filtroFuncionario.adicionarParametro(new ComparacaoTexto(FiltroFuncionario.NOME, nome));
   peloMenosUmParametroInformado = true;
  }
  
  //Pesquisa idUnidadeEmpresa
  if(idUnidadeEmpresa != null && !idUnidadeEmpresa.equals("")){
   filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeEmpresa));
   peloMenosUmParametroInformado = true;
  }
  
  
  
  
 
  // [FS0002] Verificar preenchimento dos campos
  if (!peloMenosUmParametroInformado) {
   throw new ActionServletException(
     "atencao.filtro.nenhum_parametro_informado");
	}
  
   //
 // Collection<Funcionario> collectionFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
  
  	filtroFuncionario.setCampoOrderBy(FiltroFuncionario.NOME, FiltroFuncionario.ID);
  
  	Map resultado = controlarPaginacao(httpServletRequest, retorno,
		  filtroFuncionario, Funcionario.class.getName());

	Collection collectionFuncionario = (Collection) resultado
			.get("colecaoRetorno");

	retorno = (ActionForward) resultado.get("destinoActionForward");
  
  
  
  //Validacoes 
  if (collectionFuncionario == null || collectionFuncionario.isEmpty()) {
  // [FS0004] Nenhum registro encontrado
   throw new ActionServletException(
     "atencao.pesquisa.nenhumresultado", null, "funcionario");
  } else if (collectionFuncionario.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
  // [FS0003] Muitos registros encontrados
   throw new ActionServletException("atencao.pesquisa.muitosregistros");
  } else {
   sessao.setAttribute("collectionFuncionario", collectionFuncionario);
  }    
  
  

  return retorno;
 }
}