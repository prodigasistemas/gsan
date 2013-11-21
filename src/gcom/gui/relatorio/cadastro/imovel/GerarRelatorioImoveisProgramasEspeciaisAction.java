/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisAnalitico;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
 * 
 * @author Hugo Leonardo, Ivan Sergio
 * @date 14/01/2010
 * 		 14/09/2010 - CRC4734: Retirar do filtro o perfil do imóvel e obter as contas a partir
 * 					  de fatura item da fatura selecionada;
 */

public class GerarRelatorioImoveisProgramasEspeciaisAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioImoveisProgramasEspeciaisActionForm form = 
			(GerarRelatorioImoveisProgramasEspeciaisActionForm) actionForm;
		
		FiltrarRelatorioImoveisProgramasEspeciaisHelper helper = 
			new FiltrarRelatorioImoveisProgramasEspeciaisHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		// mesAno Referencia 
		if (form.getMesAnoReferencia() != null && 
			!form.getMesAnoReferencia().equals("") && !form.getMesAnoReferencia().equals("00/0000")) {
			
			Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			Integer anoMesFaturamento =  sistemaParametro.getAnoMesFaturamento();
				
			//Validar o Ano/Mes Referencia
			if(anoMesFaturamento.intValue() >= anoMesReferencia.intValue()){
				helper.setMesAnoReferencia(Util.formatarMesAnoComBarraParaAnoMes(
				form.getMesAnoReferencia()).toString());
			}else{
				throw new ActionServletException("atencao.anomesfaturamento.menor_igual_anomesreferencia", 
						null, Util.formatarAnoMesParaMesAno(anoMesFaturamento.toString()));
			} 
		}else {
			throw new ActionServletException("atencao.anomesreferencia.invalida", null, form.getMesAnoReferencia());
		}
		
		// Perfil dos Imoveis
		if (sistemaParametro.getPerfilProgramaEspecial() != null) {
			Integer idPerfilImovelEspecial = sistemaParametro.getPerfilProgramaEspecial().getId();
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID, idPerfilImovelEspecial));
			ImovelPerfil imovelPerfilProgramaEspecial = (ImovelPerfil) Util.retonarObjetoDeColecao(
					Fachada.getInstancia().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName()));
			
			helper.setPerfilImovel(imovelPerfilProgramaEspecial.getId().toString());
			helper.setNomePerfilImovel(imovelPerfilProgramaEspecial.getDescricao());
		}
			
		//Tipo de Relatório Analitico ou Sintetico
		if ( form.getTipo() != null && 
				!form.getTipo().equals("")){
			
			helper.setTipo(form.getTipo());
		}
	
		// opcao Totalizacao
		if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("-1")){
			helper.setOpcaoTotalizacao(form.getOpcaoTotalizacao());
		}
		
		// região Desenvolvimento
		if(form.getRegiaoDesenvolvimento() != null && !form.getRegiaoDesenvolvimento().equals("-1")){
			helper.setIdRegiaoDesenvolvimento(form.getRegiaoDesenvolvimento());
		}
		
		//Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			helper.setIdLocalidade(form.getIdLocalidade());
		}
		
		//Se = 0 Analitico  
		TarefaRelatorio relatorio = null;
		
		if( helper.getTipo().equals("0")){
			
			 relatorio = 
				new RelatorioImoveisProgramasEspeciaisAnalitico((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			 
		//Se = 1 Sintetico 	
		}else if( helper.getTipo().equals("1")){
			 relatorio = 
				new RelatorioImoveisProgramasEspeciaisSintetico((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
		}
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioImoveisProgramasEspeciaisHelper", helper);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.nao.existe.dados_relatorio_anomesreferencia", null, "");
		}
			
			return retorno;
		}
	
}