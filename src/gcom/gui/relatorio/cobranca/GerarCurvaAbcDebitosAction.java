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
package gcom.gui.relatorio.cobranca;

import gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarCurvaAbcDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCurvaAbcDebitosAction  extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * @author Ivan Sérgio
	 * @created 07/08/2007
	 * 
	 * <<Descrição do método>>
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

		// cria a variável de retorno
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;

		RelatorioGerarCurvaAbcDebitos relatorioGerarCurvaAbcDebitos = new RelatorioGerarCurvaAbcDebitos(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("classificacao",
				imovelCurvaAbcDebitosActionForm.getClassificacao());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaInicial",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaFinal",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelMedicaoIndividualizada",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelMedicaoIndividualizada());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelParalizacaoFaturamentoCobranca",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelParalizacaoFaturamentoCobranca());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("gerenciaRegional",
				imovelCurvaAbcDebitosActionForm.getGerenciaRegional());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio", 
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio", 
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoAgua",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoAgua());
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoEsgoto",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoEsgoto());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorMedicao",
				imovelCurvaAbcDebitosActionForm.getIndicadorMedicao());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoMedicao",
				imovelCurvaAbcDebitosActionForm.getIdTipoMedicao());
		
		relatorioGerarCurvaAbcDebitos.addParametro("idPerfilImovel",
				imovelCurvaAbcDebitosActionForm.getIdPerfilImovel());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoCategoria",
				imovelCurvaAbcDebitosActionForm.getIdTipoCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("categoria",
				imovelCurvaAbcDebitosActionForm.getCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("idSubCategoria",
				imovelCurvaAbcDebitosActionForm.getIdSubCategoria());
		
		relatorioGerarCurvaAbcDebitos.addParametro("valorMinimoDebito",
				imovelCurvaAbcDebitosActionForm.getValorMinimoDebito());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorPagamentosNaoClassificados",
				imovelCurvaAbcDebitosActionForm.getIndicadorPagamentosNaoClassificados());
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("colecaoGerenciasRegionais",
				sessao.getAttribute("colecaoGerenciasRegionais"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionsLigacaoAguaSituacao",
				sessao.getAttribute("collectionsLigacaoAguaSituacao"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionLigacaoEsgotoSituacao",
				sessao.getAttribute("collectionLigacaoEsgotoSituacao"));
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelPerfil",
				sessao.getAttribute("collectionImovelPerfil"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionCategoriaTipo",
				sessao.getAttribute("collectionCategoriaTipo"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelCategoria",
				sessao.getAttribute("collectionImovelCategoria"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelSubcategoria",
				sessao.getAttribute("collectionImovelSubcategoria"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeInicial",
				sessao.getAttribute("localidadeInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeFinal",
				sessao.getAttribute("localidadeFinal"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialInicial",
				sessao.getAttribute("setorComercialInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialFinal",
				sessao.getAttribute("setorComercialFinal"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				sessao.getAttribute("idMunicipio"));
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				sessao.getAttribute("nomeMunicipio"));
		
        // Flag para tela de sucesso apos a tela de espera de processamento de relatorio
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioGerarCurvaAbcDebitos.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorioGerarCurvaAbcDebitos,
				tipoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
}
