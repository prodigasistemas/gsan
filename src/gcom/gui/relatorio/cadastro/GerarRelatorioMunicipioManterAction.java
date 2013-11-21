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
package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.geografico.FiltrarMunicipioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioManterMunicipio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioMunicipioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarMunicipioActionForm filtrarMunicipioActionForm = (FiltrarMunicipioActionForm) actionForm;

		FiltroMunicipio filtroMunicipio = (FiltroMunicipio) sessao
				.getAttribute("filtroMunicipio");
		

		// Inicio da parte que vai mandar os parametros para o relatório
		Fachada fachada = Fachada.getInstancia();
		
		Municipio municipioParametros = new Municipio();
		Regiao regiaoParametros = new Regiao();
		RegiaoDesenvolvimento regiaoDesenvolvimentoParametros = new RegiaoDesenvolvimento();
		UnidadeFederacao unidadeFederacaoParametros = new UnidadeFederacao();
		Microrregiao microrregiaoParametros = new Microrregiao();
		
		if(filtrarMunicipioActionForm.getUnidadeFederacao() !=null && !filtrarMunicipioActionForm.getUnidadeFederacao().equals("")){
			
			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
			filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.ID, filtrarMunicipioActionForm.getUnidadeFederacao()));
			
			Collection colecaoUnidadeFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
			if(colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty()){
				UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidadeFederacao);
				unidadeFederacaoParametros = unidadeFederacao;
			}
		}
		
		String id = null;

		String idMunicipioPesquisar = (String) filtrarMunicipioActionForm.getCodigoMunicipio();

		if (idMunicipioPesquisar != null && !idMunicipioPesquisar.equals("")) {
			id = idMunicipioPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarMunicipioActionForm.getIndicadorUso()!= null && !filtrarMunicipioActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarMunicipioActionForm.getIndicadorUso());
		}

		//regiao de desenvolvimento
		if (filtrarMunicipioActionForm.getRegiaoDesenv() != null && !filtrarMunicipioActionForm.getRegiaoDesenv().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();
			filtroRegiaoDesenvolvimento.adicionarParametro(new ParametroSimples(FiltroRegiaoDesenvolvimento.ID, filtrarMunicipioActionForm.getRegiaoDesenv()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				RegiaoDesenvolvimento regiaoDesenvolvimento = (RegiaoDesenvolvimento) Util.retonarObjetoDeColecao(colecaoMunicipio);
				regiaoDesenvolvimentoParametros = regiaoDesenvolvimento;
			}
			
		}
		//regiao
		if (filtrarMunicipioActionForm.getRegiao() != null && !filtrarMunicipioActionForm.getRegiao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroRegiao filtroRegiao = new FiltroRegiao();
			filtroRegiao.adicionarParametro(new ParametroSimples(FiltroRegiao.ID, filtrarMunicipioActionForm.getRegiao()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroRegiao, Regiao.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Regiao regiao = (Regiao) Util.retonarObjetoDeColecao(colecaoMunicipio);
				regiaoParametros = regiao;
			}
			
		}
		
		//microrregiao
		if (filtrarMunicipioActionForm.getMicroregiao() != null && !filtrarMunicipioActionForm.getMicroregiao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
			filtroMicrorregiao.adicionarParametro(new ParametroSimples(FiltroMicrorregiao.ID, filtrarMunicipioActionForm.getMicroregiao()));
			
			Collection colecaoMunicipio = fachada.pesquisar(filtroMicrorregiao, Microrregiao.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Microrregiao microrregiao = (Microrregiao) Util.retonarObjetoDeColecao(colecaoMunicipio);
				microrregiaoParametros = microrregiao;
			}
			
		}
		
		String nome = null;
		if(filtrarMunicipioActionForm.getNomeMunicipio() != null && !filtrarMunicipioActionForm.getNomeMunicipio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			nome = filtrarMunicipioActionForm.getNomeMunicipio();
		}
		
		
		// seta os parametros que serão mostrados no relatório
		municipioParametros.setNome(nome);
		municipioParametros.setId(id == null ? null : new Integer(
				id));
		municipioParametros.setIndicadorUso(indicadorUso);
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
	
		RelatorioManterMunicipio relatorioManterMunicipio = new RelatorioManterMunicipio(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterMunicipio.addParametro("filtroMunicipio",
				filtroMunicipio);
		relatorioManterMunicipio.addParametro("municipioParametros",
				municipioParametros);
		relatorioManterMunicipio.addParametro("regiaoParametros",
				regiaoParametros);
		relatorioManterMunicipio.addParametro("regiaoDesenvolvimentoParametros",
				regiaoDesenvolvimentoParametros);
		relatorioManterMunicipio.addParametro("unidadeFederacaoParametros",
				unidadeFederacaoParametros);
		relatorioManterMunicipio.addParametro("microrregiaoParametros",
				microrregiaoParametros);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterMunicipio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterMunicipio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}