package gcom.gui.cobranca;
/**
 * 
 */
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


import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.bean.DocumentosReceberFaixaDiasVencidosHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hugo Leonardo
 *
 */
public class InserirFaixaDiasVencidosDocumentosReceberAction extends GcomAction{
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
	 * @exception RemoteException
	 *                Descrição da exceção
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws RemoteException,
			ErroRepositorioException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Prepara o retorno da Ação
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém o action form
		InserirFaixaDiasVencidosDocumentosReceberActionForm form = (InserirFaixaDiasVencidosDocumentosReceberActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos = 
				new DocumentosReceberFaixaDiasVencidos();
		
		DocumentosReceberFaixaDiasVencidosHelper helper = new DocumentosReceberFaixaDiasVencidosHelper();
		
		String descricaoFaixa = null;
		if(form.getDescricaoFaixa() != null 
				&& !form.getDescricaoFaixa().trim().equalsIgnoreCase("")){
			
			descricaoFaixa = form.getDescricaoFaixa();
		}
		
		
		Integer valorInicialFaixa = null;
		
		if (form.getValorInicialFaixa() != null
				&& !form.getValorInicialFaixa().trim().equalsIgnoreCase("")) {
			
			valorInicialFaixa = new Integer(0);

			String valorAux = form.getValorInicialFaixa().toString();

			valorInicialFaixa = new Integer(valorAux);
		}
		
		Integer valorFinalFaixa = null;
		
		if (form.getValorFinalFaixa() != null
				&& !form.getValorFinalFaixa().trim().equalsIgnoreCase("")) {
			
			valorFinalFaixa = new Integer(0);

			String valorAux = form.getValorFinalFaixa().toString();

			valorFinalFaixa = new Integer(valorAux);
			
			if (valorFinalFaixa != null					
					&& valorFinalFaixa != null) {
				if (valorInicialFaixa.compareTo(valorFinalFaixa) > 0) {
					throw new ActionServletException("atencao.valor_faixa_final_menor_valor_inicial_faixa");
				}
			}

		}
		
		// Seta dados
		documentosReceberFaixaDiasVencidos.setDescricaoFaixa(descricaoFaixa);
		documentosReceberFaixaDiasVencidos.setValorInicialFaixa(valorInicialFaixa);
		documentosReceberFaixaDiasVencidos.setValorFinalFaixa(valorFinalFaixa);
		documentosReceberFaixaDiasVencidos.setUltimaAlteracao(new Date());
		documentosReceberFaixaDiasVencidos.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		// Filtro da Descrição
		FiltroDocumentosReceberFaixaDiasVencidos filtroDescricao = 
			new FiltroDocumentosReceberFaixaDiasVencidos();

		filtroDescricao
				.adicionarParametro(new ParametroSimples(
						FiltroDocumentosReceberFaixaDiasVencidos.DESCRICAO_FAIXA,
						documentosReceberFaixaDiasVencidos.getDescricaoFaixa()));

		// Collection por descrição
		Collection colecaoDocumentosDescricao = fachada.pesquisar(
				filtroDescricao, DocumentosReceberFaixaDiasVencidos.class.getName());
		
		// Verificar se descricao ja esta cadastrada
		if (colecaoDocumentosDescricao != null && !colecaoDocumentosDescricao.isEmpty()) {
			DocumentosReceberFaixaDiasVencidos doc = (DocumentosReceberFaixaDiasVencidos) Util.retonarObjetoDeColecao(colecaoDocumentosDescricao);
			helper.setDescricaoFaixa(doc.getDescricaoFaixa());
			
			throw new ActionServletException(
					"atencao.verificar_descricao_faixa",helper.getDescricaoFaixa());
		} 
		
		// FS0003 - Verificar pré-existência de faixa contendo o valor
		fachada.verificarExistenciaFaixaInicial(valorInicialFaixa);
		fachada.verificarExistenciaFaixaFinal(valorFinalFaixa);
		
		// Insere objeto
		fachada.inserirDocumentosReceberFaixaDiasVencidos(documentosReceberFaixaDiasVencidos,usuario);
	
		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest, " Faixa de Dias Vencidos para Documentos a Receber inserido(a) com sucesso.",
					"Inserir outro(a) Faixa de Dias Vencidos para Documentos a Receber.", 
					"exibirInserirFaixaDiasVencidosDocumentosReceberAction.do?menu=sim");
		}

		return retorno;
	}
}