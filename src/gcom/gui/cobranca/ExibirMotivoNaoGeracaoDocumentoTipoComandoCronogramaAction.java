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
* Anderson Italo Felinto de Lima
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

package gcom.gui.cobranca;

import java.util.Collection;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoCronograma;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroImovelNaoGerado;
import gcom.cobranca.ImovelNaoGerado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Anderson Italo
 * @date 20/11/2009
 * Classe responsável pela exibição do filtro de UC9999 Consultar Motivo da não Geração de Documento de Cobrança
 */
public class ExibirMotivoNaoGeracaoDocumentoTipoComandoCronogramaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("motivoNaoGeracaoDocumentoTipoComandoCronograma");
		
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		//4.	Caso a opção por imóvel tenha sido escolhida 
		if (httpServletRequest.getParameter("filtroPorImovel") != null 
				&& httpServletRequest.getParameter("filtroPorImovel").equals("true")){
			
			form.setIndicadorTipoPesquisa("2");
			
			/*[FS0001] - Validar mês/ano de referência
			 * Caso o mês/ano de referência não seja menor que o mês/ano do faturamento 
			 * corrente (PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS), 
			 * exibir a mensagem “Mês/Ano de Referência deve ser anterior a << mês/ano 
			 * do faturamento corrente >>” e retornar para o passo correspondente no 
			 * fluxo principal. */
			FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
			
			SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
			String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getAnoMesReferencia());
			String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
			
			Integer resultado = anoMesReferencia.compareTo(anoMesFaturamentoCorrente);
			
			if (resultado >= 0){
				
				throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
			}
			
			/*2.	O sistema obtém a identificação do comando (CAAC_ID da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG 
			 * com CBAT_ID = 2"CBAT_ID da atividade informada" e CBCR_ID = CBCR_ID da tabela COBRANCA_ACAO_CRONOGRAMA com CBAC_ID = CBAC_ID 
			 * da Ação informada e CBCM_ID = CBCM_ID da tabela COBRANCA_GRUPO_CRONOGRAMA_MES com CBCM_AMREFERENCIA= ano/mês de referencia informado 
			 * e CBGR_ID = CBGR_ID do grupo informado.*/
			FiltroCobrancaGrupoCronogramaMes filtroCobrancaGrupoCronogramaMes = new FiltroCobrancaGrupoCronogramaMes();
			filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupoCronogramaMes.ANO_MES_REFERENCIA, anoMesReferencia));
			filtroCobrancaGrupoCronogramaMes.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupoCronogramaMes.ID_COBRANCA_GRUPO, new Integer(form.getIdCobrancaGrupo())));
			
			Collection colecaoCobrancaGrupoCronogramaMes = fachada.pesquisar(filtroCobrancaGrupoCronogramaMes, CobrancaGrupoCronogramaMes.class.getName());
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes) Util.retonarObjetoDeColecao(colecaoCobrancaGrupoCronogramaMes);
			
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = null;
			if (colecaoCobrancaGrupoCronogramaMes != null && !colecaoCobrancaGrupoCronogramaMes.isEmpty()){
				FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
				filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAcaoCronograma.ID_COBRANCA_ACAO, new Integer(form.getIdCobrancaAcao())));
				filtroCobrancaAcaoCronograma.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAcaoCronograma.ID_COBRANCA_GRUPO_CRONOGRAMA_MES, cobrancaGrupoCronogramaMes.getId()));
				
				Collection colecaoCobrancaAcaoCronograma = fachada.pesquisar(filtroCobrancaAcaoCronograma, CobrancaAcaoCronograma.class.getName());
				CobrancaAcaoCronograma cobrancaAcaoCronograma = (CobrancaAcaoCronograma) Util.retonarObjetoDeColecao(colecaoCobrancaAcaoCronograma);
						
				if (colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()){
					FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
					filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE, new Integer(form.getIdCobrancaAtividade())));
					filtroCobrancaAcaoAtividadeCronograma.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeCronograma.ID_COBRANCA_ACAO_CRONOGRAMA, cobrancaAcaoCronograma.getId()));
					
					Collection colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
					cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) Util.retonarObjetoDeColecao(colecaoCobrancaAcaoAtividadeCronograma);
				}
			}
			
			if (cobrancaAcaoAtividadeCronograma == null){
				throw new ActionServletException("atencao.comando_inexistente_parametros_informados");
			}
			
			Imovel imovel = fachada.pesquisarImovel(new Integer(form.getMatriculaImovel()));
			
			if (imovel != null){
				FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_IMOVEL, imovel.getId()));
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA, cobrancaAcaoAtividadeCronograma.getId()));
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
				Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
				
				/*[FS0004] - Motivo não encontrado
				 * . Caso a matrícula do imóvel informada não exista na tabela 
				 * IMOVEL_NAO_GERADO para o CAAC_ID ou CACM_ID em questão, exibir 
				 * a mensagem “Imóvel não pertence ao universo do comando ou teve 
				 * documento gerado” e retornar para o passo correspondente 
				 * no fluxo principal.
				*/
				if (colecaoImovelNaoGerado == null || colecaoImovelNaoGerado.isEmpty()){
					throw new ActionServletException("atencao.imovel_nao_pertence_comando_documento_gerado");
				}else{
					/*4.2.1.	O sistema exibe o motivo de não Geração 
					 * (MNGD_DSMOTIVO com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO)*/
					ImovelNaoGerado imovelNaogerado = (ImovelNaoGerado)Util.retonarObjetoDeColecao(colecaoImovelNaoGerado);
					form.setDescricaoMotivo(imovelNaogerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
				}
			}else{
				throw new ActionServletException("atencao.imovel.inexistente");
			}
		}else{
			form.setIndicadorTipoPesquisa("1");
			form.setIndicadorTipoRelatorio("1");
		}
		
		
		//grupo de cobrança
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
				FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		
		Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaGrupo == null || colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaGrupo");
		}
		
		httpServletRequest.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);
		
		//ação de cobrança
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaAcao == null || colecaoCobrancaAcao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaAcao");
		}
		
		httpServletRequest.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
		
		//atividade de cobrança
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroCobrancaAtividade.ID, CobrancaAtividade.ENCERRAR));
		
		Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaAtividade == null || colecaoCobrancaAtividade.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaAtividade");
		}
		
		

		//Gerência regional
		Collection colecaoGerenciaRegional = fachada.obterColecaoGerenciaRegional();
		sessao.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		
		//Unidade negócio
		Collection colecaoUnidadeNegocio = fachada.obterColecaoUnidadeNegocio();
		sessao.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		
		//Tratamento das buscas através do enter
		//=================================================
				
		//Localidade
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){
			Integer idLocalidade = new Integer(form.getIdLocalidade());
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(idLocalidade);
			
			if(localidade != null){
				form.setDescricaoLocalidade(localidade.getDescricao());
			}
			else{
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				form.setIdLocalidade("");
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("");
				form.setIdQuadra("");
				form.setDescricaoQuadra("");
				httpServletRequest.setAttribute("localidadeException","ok");
			}
			
		}
		
		String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
		if(pesquisarSetorComercial != null && !"".equals(pesquisarSetorComercial)){
			
			String idSetorComercial = form.getIdSetorComercial();
			//Localidade localidadeInicial = (Localidade) sessao.getAttribute("localidadeInicial");
			String idLocalidadeInicial = form.getIdLocalidade();
				
			SetorComercial setorComercial = fachada.obterSetorComercialLocalidade(idLocalidadeInicial,idSetorComercial);
			
			if(setorComercial != null){
				form.setDescricaoSetorComercial(setorComercial.getDescricao());
				sessao.setAttribute("setorComercialMotivoNaoGeracao", setorComercial);
			}
			else{
				form.setDescricaoSetorComercial("SETOR COMERCIAL INEXISTENTE");
				form.setIdSetorComercial("");
				form.setIdQuadra("");
				form.setDescricaoQuadra("");
				httpServletRequest.setAttribute("setorComercialException","ok");
				sessao.removeAttribute("setorComercialMotivoNaoGeracao");
			}
			
		}
		
		//Quadra
		String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");
		if(pesquisarQuadra != null && !"".equals(pesquisarQuadra)){
			
			SetorComercial setorComercial = (SetorComercial)sessao.getAttribute("setorComercialMotivoNaoGeracao");
			
			int idQuadra = Integer.parseInt(form.getIdQuadra());
			
			Quadra quadra = null;
			if(setorComercial != null)
				quadra = fachada.obterQuadraSetorComercial(setorComercial.getId(),idQuadra);
			
			
			
			if(quadra != null){
				form.setDescricaoQuadra(quadra.getDescricao());
				sessao.setAttribute("quadra",quadra);
			}
			else{
				form.setDescricaoQuadra("QUADRA INEXISTENTE");
				form.setIdQuadra("");
				sessao.removeAttribute("quadra");
			}
			
		}
		
		
		httpServletRequest.setAttribute("colecaoCobrancaAtividade", colecaoCobrancaAtividade);
		
		return retorno;

	}

}
