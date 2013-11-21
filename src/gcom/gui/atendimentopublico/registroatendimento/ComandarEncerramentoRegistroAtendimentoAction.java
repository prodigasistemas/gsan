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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que comanda o encerramento dos registros de atendimentos 
 * [UC0735] Comandar Encerramento de Registros de Atendimento
 * 
 * @author Rafael Corrêa, Pedro Alexandre
 * @since 25/01/2008, 10/06/2008
 */
public class ComandarEncerramentoRegistroAtendimentoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();

		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		ComandarEncerramentoRegistroAtendimentoActionForm form = (ComandarEncerramentoRegistroAtendimentoActionForm)actionForm;

		//HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroUnidadeOrganizacional filtroCentralAtendimento = new FiltroUnidadeOrganizacional();
		filtroCentralAtendimento.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_CENTRAL_ATENDIMENTO, ConstantesSistema.SIM));
		
		Collection colecaoCentralAtendimento = fachada.pesquisar(filtroCentralAtendimento, UnidadeOrganizacional.class.getName());
		UnidadeOrganizacional centralAtendimento = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoCentralAtendimento);
		
		
		RaEncerramentoComando raEncerramentoComando = new RaEncerramentoComando();
		
		String periodoAtendimentoInicial = form.getDataAtendimentoInicial();
		
		if (periodoAtendimentoInicial != null && !periodoAtendimentoInicial.trim().equals("")) {
			raEncerramentoComando.setDataAtendimentoInicial(Util.converteStringParaDate(periodoAtendimentoInicial));
		}
		
		String periodoAtendimentoFinal = form.getDataAtendimentoFinal();
		
		if (periodoAtendimentoFinal != null && !periodoAtendimentoFinal.trim().equals("")) {
			raEncerramentoComando.setDataAtendimentoFinal(Util.converteStringParaDate(periodoAtendimentoFinal));
		}
		
		String idMotivoEncerramento = form.getMotivoEncerramento();
		
		if (idMotivoEncerramento != null && !idMotivoEncerramento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(new Integer(idMotivoEncerramento));
			raEncerramentoComando.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		
		Usuario usuarioResponsavelEncerramento = null;
		String idUsuario = form.getIdUsuario();
		
		if (idUsuario != null && !idUsuario.trim().equals("")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			
			Collection<Usuario> colecaoUsuarios = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuarios == null || colecaoUsuarios.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Usuário");
			}
			usuarioResponsavelEncerramento = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
			raEncerramentoComando.setUsuario(usuarioResponsavelEncerramento);
		}
		
		String idUnidadeAtendimento = form.getIdUnidadeAtendimento();
		
		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtendimento));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtendimento = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtendimento == null || colecaoUnidadesAtendimento.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade de Atendimento");
			}
			UnidadeOrganizacional unidadeAtendimento = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtendimento);
			raEncerramentoComando.setUnidadeOrganizacionalAtendimento(unidadeAtendimento);
		}
		
		String idUnidadeAtual = form.getIdUnidadeAtual();
		
		if (idUnidadeAtual != null && !idUnidadeAtual.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtual));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtual = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtual == null || colecaoUnidadesAtual.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Atual");
			}
			UnidadeOrganizacional unidadeAtual = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtual);
			raEncerramentoComando.setUnidadeOrganizacionalAtual(unidadeAtual);
		}
		
		String idUnidadeSuperior = form.getIdUnidadeSuperior();
		
		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeSuperior));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeSuperior = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeSuperior == null || colecaoUnidadeSuperior.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Superior");
			}
			UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
			raEncerramentoComando.setUnidadeOrganizacionalSuperior(unidadeSuperior);
			
			//[FS0006] - Verificar existência de unidades subordinadas
			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.limparListaParametros();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidadeSuperior));
			Collection<UnidadeOrganizacional> colecaoUnidadeSubordinadas = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if(colecaoUnidadeSubordinadas == null || colecaoUnidadeSubordinadas.isEmpty()){
				throw new ActionServletException("atencao.nao.existe.unidades.subordinadas");
			}
		}
		
		String[] idsEspecificacoes = form.getIdsSolicitacaoTipoEspecificcacoes();
		Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacoes = new ArrayList<SolicitacaoTipoEspecificacao>();
		
		if (idsEspecificacoes != null) {
			
			for (int i = 0; i < idsEspecificacoes.length; i++) {
				String idEspecificacao = idsEspecificacoes[i];
				
				if (idEspecificacao != null && !idEspecificacao.trim().equals("") && !idEspecificacao.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					SolicitacaoTipoEspecificacao especificacao = new SolicitacaoTipoEspecificacao();
					especificacao.setId(new Integer(idEspecificacao));
					
					colecaoEspecificacoes.add(especificacao);
				}
			}
		}
		
		//[SB0009 - Verificar permissão do usuário]
		this.verificarPermissaoUsuario(usuarioResponsavelEncerramento,raEncerramentoComando.getUnidadeOrganizacionalAtual(),raEncerramentoComando.getUnidadeOrganizacionalSuperior(), sistemaParametros, centralAtendimento);

		//chama o metódo principal para comandar o encerramento das RA's
		Integer id = fachada.comandarEncerramentoRA(raEncerramentoComando, colecaoEspecificacoes, usuarioResponsavelEncerramento);
		
		// Monta a página de sucesso de acordo com o padrão do sistema.
		montarPaginaSucesso(httpServletRequest, "Comando de Encerramento de Registros de Atendimento "
				+ id.toString()
				+ " comandado com sucesso.",
				"Comandar outro Encerramento de Registros de Atendimento",
				"exibirComandarEncerramentoRegistroAtendimentoAction.do?menu=sim");

		return retorno;

	}
	
	/**
	 * [FS0009] - Verificar permissão do usuário
	 *
	 * @author Pedro Alexandre
	 * @date 30/06/2008
	 *
	 * @param usuario
	 * @param unidadeAtual
	 * @param unidadeSuperior
	 * @param sistemaParametros
	 * @param centralAtendimento
	 */
	private void verificarPermissaoUsuario(Usuario usuario, UnidadeOrganizacional unidadeAtual, UnidadeOrganizacional unidadeSuperior,SistemaParametro sistemaParametros, UnidadeOrganizacional centralAtendimento){
		
		UnidadeOrganizacional presidencia = sistemaParametros.getUnidadeOrganizacionalIdPresidencia();
		UnidadeOrganizacional lotacaoUsuario = usuario.getUnidadeOrganizacional();
		boolean flagPresidencial = true;
		boolean flagCentralAtendimento = true;
		Short indicadorUnidadeSuperior = ConstantesSistema.NAO;
		
		if(lotacaoUsuario == null || !lotacaoUsuario.getId().equals(presidencia.getId())){
			flagPresidencial = false;
		}
	
		if(centralAtendimento == null || !lotacaoUsuario.getId().equals(centralAtendimento.getId())){
			flagCentralAtendimento = false;
		}
		
		if(unidadeAtual == null && unidadeSuperior == null){
			if(!flagPresidencial && !flagCentralAtendimento ){
				throw new ActionServletException("atencao.usuario.responsavel.sem.autorizacao");
			}
		}else if(unidadeAtual != null){
			if(!lotacaoUsuario.getId().equals(unidadeAtual.getId())){
				//[SB0010 - Verificar Existência de Unidade Superior]
				indicadorUnidadeSuperior = this.verificarUnidadeSuperior(unidadeAtual,lotacaoUsuario);
				
				if(indicadorUnidadeSuperior.equals(ConstantesSistema.NAO)){
					throw new ActionServletException("atencao.usuario.responsavel.sem.autorizacao.unidade.atual");
				}
			}
		}else if(unidadeSuperior != null){
			if(!lotacaoUsuario.getId().equals(unidadeSuperior.getId())){
				//[SB0010 - Verificar Existência de Unidade Superior]
				indicadorUnidadeSuperior = this.verificarUnidadeSuperior(unidadeSuperior,lotacaoUsuario);
				
				if(indicadorUnidadeSuperior.equals(ConstantesSistema.NAO)){
					throw new ActionServletException("atencao.usuario.responsavel.sem.autorizacao.unidade.superior");
				}
			}
		}
	}

	
	/**
	 * [SB0010 - Verificar Existência de Unidade Superior]
	 *
	 * @author Pedro Alexandre
	 * @date 01/07/2008
	 *
	 * @param idUnidadeOrganizacional
	 */
	private UnidadeOrganizacional verificarExistenciaUnidadeSuperior(Integer idUnidadeOrganizacional){

		Fachada fachada = Fachada.getInstancia();
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeSuperior");
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
		
		Collection<UnidadeOrganizacional> colecaoUnidadesAtual = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtual);

		if(unidadeSuperior.getUnidadeSuperior() != null && unidadeSuperior.getUnidadeSuperior().getId().intValue() != 0){
			return unidadeSuperior.getUnidadeSuperior();
		}
		return null;
		
	}
	
	/**
	 * [SB0001] - Verificar Unidade Superior
	 *
	 * @author Pedro Alexandre
	 * @date 01/07/2008
	 *
	 * @param unidadeReferencial
	 * @param lotacaoUsuario
	 * @return
	 */
	private Short verificarUnidadeSuperior(UnidadeOrganizacional unidadeReferencial, UnidadeOrganizacional lotacaoUsuario){
		Short retorno = ConstantesSistema.NAO; 
	
		UnidadeOrganizacional unidadeSuperior = unidadeReferencial.getUnidadeSuperior();
		
		if(lotacaoUsuario.getId().equals(unidadeSuperior.getId())){
			retorno = ConstantesSistema.SIM;
			return retorno;
		}
		while(true){
			unidadeSuperior = this.verificarExistenciaUnidadeSuperior(unidadeSuperior.getId());
			
			if(unidadeSuperior == null){
				return retorno;
			}
			if(lotacaoUsuario.getId().equals(unidadeSuperior.getId())){
				retorno = ConstantesSistema.SIM;
				return retorno;
			}
		}
	}
}
