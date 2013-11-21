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
package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioReligacaoClientesInadiplentes;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
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

/**
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 25/01/2011
 */

public class GerarRelatorioReligacaoClientesInadiplentesAction extends ExibidorProcessamentoTarefaRelatorio {

	private Collection colecaoPesquisa = null;
	private String localidadeID = null;
	private String setorComercialCD = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		   
		// Form
		GerarRelatorioReligacaoClientesInadiplentesForm form = (GerarRelatorioReligacaoClientesInadiplentesForm) actionForm;
		
		FiltrarRelatorioReligacaoClientesInadiplentesHelper helper = new FiltrarRelatorioReligacaoClientesInadiplentesHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;
		
		// Gerência Regional
		String gerenciaRegional = " -- ";
		if( form.getGerenciaRegionalID() != null && !form.getGerenciaRegionalID().equals("-1")){
			
			helper.setGerenciaRegional( new Integer(form.getGerenciaRegionalID()));
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, form.getGerenciaRegionalID()));
			
			filtroGerenciaRegional.adicionarParametro(
					new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna gerenciaRegional
			colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ActionServletException("atencao.gerenciaRegional_inexistente");
			}
			
			GerenciaRegional objetoGerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoPesquisa);
			gerenciaRegional = objetoGerenciaRegional.getNome();
			
			peloMenosUmParametroInformado = true;
		}
		
		// Unidade de Negócio
		String unidadeNegocio = " -- ";
		if( form.getUnidadeNegocioID() != null && !form.getUnidadeNegocioID().equals("-1")){
			
			helper.setUnidadeNegocio( new Integer(form.getUnidadeNegocioID()));
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, form.getUnidadeNegocioID()));
			
			filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Unidade de Negócio
			colecaoPesquisa = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ActionServletException("atencao.unidade_negocio.inexistente");
			}
			
			UnidadeNegocio objetoUnidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoPesquisa);
			unidadeNegocio = objetoUnidadeNegocio.getNome();
			
			peloMenosUmParametroInformado = true;
		}
		
		// Localidade
		String localidade = " -- ";
		if(form.getLocalidadeID() != null && !form.getLocalidadeID().equals("")){
			
			helper.setLocalidade(new Integer(form.getLocalidadeID()));
			peloMenosUmParametroInformado = true;
			
			if(	form.getNomeLocalidade() == null || form.getNomeLocalidade().equals("")){
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getLocalidadeID()));
				
				filtroLocalidade.adicionarParametro(
						new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Localidade
				colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("pesquisa.localidade.inexistente");
				}
				
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				localidade = objetoLocalidade.getDescricao();
			
			}else{
				
				localidade = form.getNomeLocalidade();
			}
		}
		
		// Setor Comercial
		String setorComercial = " -- ";
		if((form.getSetorComercialID() != null && !form.getSetorComercialID().equals("")) || 
				form.getSetorComercialCD() != null && !form.getSetorComercialCD().equals("")){
			
			if(form.getSetorComercialID() != null && 
					!form.getSetorComercialID().equals("")){
			
				setorComercial = form.getNomeSetorComercial();
				helper.setSetorComercial(new Integer(form.getSetorComercialID()));
				peloMenosUmParametroInformado = true;
			}else{
				
				localidadeID = (String) form.getLocalidadeID();
				
				if(localidadeID == null || localidadeID.equals("")){
					
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
				setorComercialCD = (String) form.getSetorComercialCD();
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
				}
				
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				setorComercial = objetoSetorComercial.getDescricao();
				helper.setSetorComercial(objetoSetorComercial.getId());
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Cliente
		String cliente = " -- ";
		if( form.getClienteID() != null && !form.getClienteID().equals("")){
			
			if(	form.getNomeCliente() == null || form.getNomeCliente().equals("")){
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, form.getClienteID()));
				
				filtroCliente.adicionarParametro(
						new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Cliente
				colecaoPesquisa = fachada.pesquisar(filtroCliente, Cliente.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.cliente.inexistente");
				}
				
				Cliente objetoCliente = (Cliente) Util.retonarObjetoDeColecao(colecaoPesquisa);
				cliente = objetoCliente.getDescricao();
			
			}else{
				
				cliente = form.getNomeCliente();
			}
			
			helper.setCliente( new Integer(form.getClienteID()));
			peloMenosUmParametroInformado = true;
		}
		
		// Usuário
		String nomeUsuario = " -- ";
		if( form.getUsuarioID() != null && !form.getUsuarioID().equals("")){
			
			if(	form.getNomeUsuario() == null || form.getNomeUsuario().equals("")){
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(
						FiltroUsuario.ID, form.getUsuarioID()));
				
				// Retorna Usuário
				colecaoPesquisa = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
				}
				
				Usuario objetoUsuario = (Usuario) Util.retonarObjetoDeColecao(colecaoPesquisa);
				nomeUsuario = objetoUsuario.getLogin();
			
			}else{
				
				nomeUsuario = form.getNomeUsuario();
			}
			
			helper.setUsuario( new Integer(form.getUsuarioID()));
			peloMenosUmParametroInformado = true;
		}
		
		// Período Encerramento
		String periodoEncerramento = "";
		if (form.getDataInicioEncerramento() != null && !form.getDataInicioEncerramento().equals("")){
			
			if (form.getDataFimEncerramento() == null || form.getDataFimEncerramento().trim().equals("")) {
				
				form.setDataFimEncerramento(form.getDataInicioEncerramento());
			}
			
			if (!Util.validarDiaMesAno(form.getDataInicioEncerramento())) {
				
				periodoEncerramento += form.getDataInicioEncerramento() + " a ";
				helper.setDataInicioEncerramento(Util.formatarDataInicial( Util.converteStringParaDate(form.getDataInicioEncerramento())));
				
				if (helper.getDataInicioEncerramento().after(new Date())) {
					
					throw new ActionServletException( "atencao.periodo_inicio_alteracao_invalida");
				}
				
				if (!Util.validarDiaMesAno(form.getDataFimEncerramento())) {
					
					periodoEncerramento += form.getDataFimEncerramento();
					helper.setDataFimEncerramento(Util.formatarDataFinal( Util.converteStringParaDate(form.getDataFimEncerramento())));
					
					if (helper.getDataFimEncerramento().after(new Date())) {
						
						throw new ActionServletException( "atencao.periodo_final_alteracao_invalida");
					}
					
					if(Util.compararData(helper.getDataInicioEncerramento(), helper.getDataFimEncerramento()) == 1){
						
						throw new ActionServletException("atencao.data_inicio_maior_final");
					}
					
					// Lilita o intevalo a um mês.
					if ((helper.getDataFimEncerramento().getTime() - helper.getDataInicioEncerramento().getTime()) > 1000L * 60L * 60L * 24L * 31L) {
						
						throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "Período de Encerramento da S.O");
					}
					
					peloMenosUmParametroInformado = true;
					
				}else{
					throw new ActionServletException("atencao.periodo_final_alteracao_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.periodo_inicio_alteracao_invalida");
			}
		}
		
		// Período Recorrência
		String periodoRecorrencia = "";
		if (form.getDataInicioRecorrencia() != null && !form.getDataInicioRecorrencia().equals("")){
			if (!Util.validarDiaMesAno(form.getDataInicioRecorrencia())) {
				
				periodoRecorrencia += form.getDataInicioRecorrencia() + " a ";
				helper.setDataInicioRecorrencia(Util.formatarDataInicial( Util.converteStringParaDate(form.getDataInicioRecorrencia())));
				
				if (helper.getDataInicioRecorrencia().after(new Date())) {
					
					throw new ActionServletException( "atencao.periodo_inicio_alteracao_invalida");
				}
				
				if (!Util.validarDiaMesAno(form.getDataFimRecorrencia())) {
					
					periodoRecorrencia += form.getDataFimRecorrencia();
					helper.setDataFimRecorrencia(Util.formatarDataFinal( Util.converteStringParaDate(form.getDataFimRecorrencia())));
					
					if (helper.getDataFimRecorrencia().after(new Date())) {
						
						throw new ActionServletException( "atencao.periodo_final_alteracao_invalida");
					}
					
					if(Util.compararData(helper.getDataInicioRecorrencia(), helper.getDataFimRecorrencia()) == 1){
						
						throw new ActionServletException("atencao.data_inicio_maior_final");
					}
					
					peloMenosUmParametroInformado = true;
					
				}else{
					throw new ActionServletException("atencao.periodo_final_alteracao_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.periodo_inicio_alteracao_invalida");
			}
		}else{
			
			Date dt = Util.adcionarOuSubtrairMesesAData(new Date(), -6, 0);
			
			helper.setDataInicioRecorrencia( dt);
			helper.setDataFimRecorrencia( new Date());
			periodoRecorrencia = Util.formatarData(dt) + " a " + Util.formatarData(new Date());
		}
			
		// Escolha Relatório
		if ( form.getEscolhaRelatorio() != null && 
				!form.getEscolhaRelatorio().equals("-1")) {
			
			helper.setEscolhaRelatorio(new Integer(form.getEscolhaRelatorio()));
			peloMenosUmParametroInformado = true;
		}else{
			
			throw new ActionServletException( "atencao.tipo_relatorio_nao_informado");
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			
			throw new ActionServletException( "atencao.filtro.nenhum_parametro_informado");
		}
		
		TarefaRelatorio relatorio = new RelatorioReligacaoClientesInadiplentes((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("filtrarRelatorioReligacaoClientesInadiplentesHelper", helper);
		relatorio.addParametro("usuario", usuario);
		
		relatorio.addParametro("gerenciaRegional", gerenciaRegional);
		relatorio.addParametro("unidadeNegocio", unidadeNegocio);
		relatorio.addParametro("localidade", localidade);
		relatorio.addParametro("setorComercial", setorComercial);
		relatorio.addParametro("cliente", cliente);
		relatorio.addParametro("nomeUsuario", nomeUsuario);
		relatorio.addParametro("periodoEncerramento", periodoEncerramento);
		relatorio.addParametro("periodoRecorrencia", periodoRecorrencia);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}
