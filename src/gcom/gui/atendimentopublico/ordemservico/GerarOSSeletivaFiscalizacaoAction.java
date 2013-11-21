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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * @author Vivianne Sousa
 * @date 20/05/2011
 */
public class GerarOSSeletivaFiscalizacaoAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        Fachada fachada = Fachada.getInstancia();
		GerarOSSeletivaFiscalizacaoActionForm form = (GerarOSSeletivaFiscalizacaoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		pesquisarordemServico(fachada, form, sistemaParametro);
		
		Integer qtdeDiasEncerramentoOS = new Integer(form.getQtdeDiasEncerramentoOS());
		if(qtdeDiasEncerramentoOS.equals(new Integer("0")) || 
		qtdeDiasEncerramentoOS.compareTo(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao()) == 1){
			throw new ActionServletException("atencao.quantidade_dias_encerramento_os_maior_zero", 
					null, sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
		}
		
		//botão pesquisar
		Integer idOrdemServico = null;
		Integer idGrupoCobranca = null;
		Integer idGerencia = null;
		Integer idUnidade = null; 
		Integer idLocalidadeInicial = null;
		Integer idLocalidadeFinal = null; 
		Integer idTipoServico = null; 
		BigDecimal percentualOSgeradas = null;
		Collection colecaoOSFiscalizacao = null;
		
		if(form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")){
			//se usuario selecionou Ordem de Serviço

			idOrdemServico = new Integer(form.getIdOrdemServico());
			
			Collection colecaoOs = new ArrayList();
			colecaoOs.add(idOrdemServico);
			
			ServicoTipo servicoTipo = null;
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, ServicoTipo.TIPO_FISCALIZACAO));
			Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo,
                    ServicoTipo.class.getName());
            
            if(colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()){
            	servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next(); 
            }
            //[SB0004 – Gerar Ordem de Fiscalização]
            colecaoOSFiscalizacao = fachada.gerarOrdemServicoFiscalizacao(colecaoOs,servicoTipo,usuarioLogado);
			
		}else{
			
			if(form.getGrupoCobranca() != null && !form.getGrupoCobranca().equals("")
					&& !form.getGrupoCobranca().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				idGrupoCobranca = new Integer(form.getGrupoCobranca());
			}else{
				
				throw new ActionServletException("atencao.campo.informado", null, "Ordem de Serviço ou Grupo de Cobrança");
				
			}
			if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("")
					&& !form.getGerenciaRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				idGerencia = new Integer(form.getGerenciaRegional());
			}
			if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("")
				&& !form.getUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				idUnidade = new Integer(form.getUnidadeNegocio());
			}
			if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")){
				idLocalidadeInicial = new Integer(form.getIdLocalidadeInicial());
				
				if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")){
					idLocalidadeFinal = new Integer(form.getIdLocalidadeFinal());
				}else{
					idLocalidadeFinal = idLocalidadeInicial;
				}
			}
			if(form.getIdServicoTipo() != null && !form.getIdServicoTipo().equals("")){
				idTipoServico = new Integer(form.getIdServicoTipo());
			}
			if(form.getQtdeDiasEncerramentoOS() != null && !form.getQtdeDiasEncerramentoOS().equals("")){
				qtdeDiasEncerramentoOS = new Integer(form.getQtdeDiasEncerramentoOS());
			}
			
			if(form.getPercentualOSgeradas() != null && !form.getPercentualOSgeradas().equals("")){
				percentualOSgeradas = Util.formatarMoedaRealparaBigDecimal(form.getPercentualOSgeradas());
			}else{
				throw new ActionServletException("atencao.campo.informado", null, "Percentual de OS Geradas para Fiscalização");
			}
			//[SB0003 – Gerar Várias Ordens de Fiscalização] 
			colecaoOSFiscalizacao = fachada.gerarVariasOsFiscalizacao(idGrupoCobranca,idGerencia, 
				idUnidade, idLocalidadeInicial,idLocalidadeFinal,idTipoServico, 
				qtdeDiasEncerramentoOS, percentualOSgeradas,usuarioLogado);
			
		}
        
		sessao.setAttribute("colecaoOSFiscalizacao",colecaoOSFiscalizacao);
		
        montarPaginaSucesso(httpServletRequest, "Ordem de Serviço Seletiva de Fiscalização inserida com sucesso.", 
        		"Inserir outra Ordem de Serviço Seletiva de Fiscalização",
				"exibirGerarOSSeletivaFiscalizacaoAction.do?menu=sim",
				"gerarRelatorioOSFiscalizacaoAction.do?idGrupoCobranca=" + idGrupoCobranca,"Gerar formulário");
        
        return retorno;
	}
	
	private void pesquisarordemServico(Fachada fachada, GerarOSSeletivaFiscalizacaoActionForm form,SistemaParametro sistemaParametro) {
		
		if (form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID,new Integer(form.getIdOrdemServico())));
//			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SOLICITACAO_TIPO_ESPECIFICACAO);
			
			Collection colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico,OrdemServico.class.getName());
			if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
			
				OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico.iterator().next();
//				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				form.setNomeOrdemServico("");
				
				//[FS0001] – Validar Ordem de Serviço.
				if (ordemServico.getAtendimentoMotivoEncerramento() != null &&
					ordemServico.getAtendimentoMotivoEncerramento().getId()
					.equals(new Integer(AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO))) {
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.os.cancelada.decurso.prazo");
				}
				
				if(ordemServico.getDataEncerramento() != null){
					Integer qtdeDias = 0;
					if(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null){
						qtdeDias = sistemaParametro.getQtdeDiasEncerraOSFiscalizacao();
					}
					Date dataEncerramentoMaisDiasLimite = Util.adicionarNumeroDiasDeUmaData(ordemServico.getDataEncerramento(),qtdeDias);
					if(dataEncerramentoMaisDiasLimite.compareTo(new Date()) == -1){
						form.setIdOrdemServico("");
						throw new ActionServletException("atencao.quantidade_dias_os_encerrada_maior");
					}
				}
				
				if (ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getId() != null 
						&& ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_FISCALIZACAO)){
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.tipo_servico_associado_os_fiscalizacao");
				}
				
				Integer idOsPendente = fachada.pesquisarOSFiscalizacaoPendente(ordemServico.getId());
				if(idOsPendente != null){
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.existe_os_fiscalizacao_pendente");
				}
				
			} else {
				form.setIdOrdemServico("");
				form.setNomeOrdemServico("Ordem de Serviço não cadastrada");
			}
		}
	}
}
