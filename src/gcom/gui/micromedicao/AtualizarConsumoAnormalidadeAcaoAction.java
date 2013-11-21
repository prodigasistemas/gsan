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
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarConsumoAnormalidadeAcaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		

		AtualizarConsumoAnormalidadeAcaoActionForm form = (AtualizarConsumoAnormalidadeAcaoActionForm) actionForm;
		
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		//Consumo Anormalidade não informado
		if(form.getConsumoAnormalidade() == null || form.getConsumoAnormalidade().equals("-1")){
        	
	        throw new ActionServletException("atencao.consumo_anormalidade_nao_informado");
	        } 
		
		//Consumo a Cobrar para o 1º Mês não informado
		if(form.getLeituraAnormalidadeConsumoMes1() == null || form.getLeituraAnormalidadeConsumoMes1().equals("-1")){
			
			throw new ActionServletException("atencao.consumo_a_cobrar_mes1");
		}
		
		//Consumo a Cobrar para o 2º Mês não informado
		if(form.getLeituraAnormalidadeConsumoMes2() == null || form.getLeituraAnormalidadeConsumoMes2().equals("-1")){
			
			throw new ActionServletException("atencao.consumo_a_cobrar_mes2");
		}
		
		//Consumo a Cobrar para o 3º Mês não informado
		if(form.getLeituraAnormalidadeConsumoMes3() == null || form.getLeituraAnormalidadeConsumoMes3().equals("-1")){
			
			throw new ActionServletException("atencao.consumo_a_cobrar_mes3");
		}
		
		//Fator de Consumo Cálculo do 1º Mes não informado
		if(form.getNumerofatorConsumoMes1() == null || form.getNumerofatorConsumoMes1().equals("")){
			
			throw new ActionServletException("atencao.fator_consumo_cobrar_mes1");
		}
		
		//Fator de Consumo Cálculo do 2º Mes não informado
		if(form.getNumerofatorConsumoMes2() == null || form.getNumerofatorConsumoMes2().equals("")){
			
			throw new ActionServletException("atencao.fator_consumo_cobrar_mes2");
		}
		
		//Fator de Consumo Cálculo do 3º Mes não informado
		if(form.getNumerofatorConsumoMes3() == null || form.getNumerofatorConsumoMes3().equals("")){
			
			throw new ActionServletException("atencao.fator_consumo_cobrar_mes3");
		}
		
		//Indicador de Geração de Carta do 1º Mês não informado
		if(form.getIndicadorGeracaoCartaMes1() == null || form.getIndicadorGeracaoCartaMes1().equals("")){
			
			throw new ActionServletException("atencao.indicador_geracao_carta_mes1");
		}
		
		//Indicador de Geração de Carta do 2º Mês não informado
		if(form.getIndicadorGeracaoCartaMes2() == null || form.getIndicadorGeracaoCartaMes2().equals("")){
			
			throw new ActionServletException("atencao.indicador_geracao_carta_mes2");
		}
		
		//Indicador de Geração de Carta do 3º Mês não informado
		if(form.getIndicadorGeracaoCartaMes3() == null || form.getIndicadorGeracaoCartaMes3().equals("")){
			
			throw new ActionServletException("atencao.indicador_geracao_carta_mes3");
		}
		
		//Indicador de Uso não informado
		if(form.getIndicadorUso() == null || form.getIndicadorUso().equals("")){
			
			throw new ActionServletException("atencao.indicador_uso_nao_informado");
		}
		
		//O Tipo de Solicitação para o 1º Mês não informado
		if((form.getIdServicoTipoMes1() != null && !form.getIdServicoTipoMes1().equals(""))
		&& (form.getSolicitacaoTipoMes1() == null || form.getSolicitacaoTipoMes1().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_mes1");
		}
		
		//O Tipo de Solicitação para o 2º Mês não informado
		if((form.getIdServicoTipoMes2() != null && !form.getIdServicoTipoMes2().equals(""))
		&& (form.getSolicitacaoTipoMes2() == null || form.getSolicitacaoTipoMes2().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_mes2");
		}
		
		//O Tipo de Solicitação para o 3º Mês não informado
		if((form.getIdServicoTipoMes3() != null && !form.getIdServicoTipoMes3().equals(""))
		&& (form.getSolicitacaoTipoMes3() == null || form.getSolicitacaoTipoMes3().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_mes3");
		}
		
		//Tipo de Especificação para o 1º Mês não informado
		if((form.getSolicitacaoTipoMes1() != null && !form.getSolicitacaoTipoMes1().equals("-1"))
		&& (form.getSolicitacaoTipoEspecificacaoMes1() == null || form.getSolicitacaoTipoEspecificacaoMes1().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_especificacao_mes1");
		}
		
		//Tipo de Especificação para o 2º Mês não informado
		if((form.getSolicitacaoTipoMes2() != null && !form.getSolicitacaoTipoMes2().equals("-1"))
		&& (form.getSolicitacaoTipoEspecificacaoMes2() == null || form.getSolicitacaoTipoEspecificacaoMes2().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_especificacao_mes2");
		}
		
		//Tipo de Especificação para o 3º Mês não informado
		if((form.getSolicitacaoTipoMes3() != null && !form.getSolicitacaoTipoMes3().equals("-1"))
		&& (form.getSolicitacaoTipoEspecificacaoMes3() == null || form.getSolicitacaoTipoEspecificacaoMes3().equals("-1"))){
			
			throw new ActionServletException("atencao.solicitacao_tipo_especificacao_mes3");
		}
		


		
		ConsumoAnormalidadeAcao consumoAnormalidadeAcao = new ConsumoAnormalidadeAcao();
		consumoAnormalidadeAcao.setId(new Integer(form.getConsumoAnormalidadeAcaoId()));
		
		ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();
		consumoAnormalidade.setId(new Integer(form.getConsumoAnormalidade()));
		consumoAnormalidadeAcao.setConsumoAnormalidade(consumoAnormalidade);
		
		if (form.getCategoria() != null && !form.getCategoria().equals("-1")){
			Categoria categoria = new Categoria();
			categoria.setId(new Integer(form.getCategoria()));
			consumoAnormalidadeAcao.setCategoria(categoria);
		}
		
		if (form.getImovelPerfil() != null && !form.getImovelPerfil().equals("-1")){
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(form.getImovelPerfil()));
			consumoAnormalidadeAcao.setImovelPerfil(imovelPerfil);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes1() != null && !form.getLeituraAnormalidadeConsumoMes1().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo1 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo1.setId(new Integer(form.getLeituraAnormalidadeConsumoMes1()));
			consumoAnormalidadeAcao.setLeituraAnormalidadeConsumoMes1(leituraAnormalidadeConsumo1);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes2() != null && !form.getLeituraAnormalidadeConsumoMes2().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo2 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo2.setId(new Integer(form.getLeituraAnormalidadeConsumoMes2()));
			consumoAnormalidadeAcao.setLeituraAnormalidadeConsumoMes2(leituraAnormalidadeConsumo2);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes3() != null && !form.getLeituraAnormalidadeConsumoMes3().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo3 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo3.setId(new Integer(form.getLeituraAnormalidadeConsumoMes3()));
			consumoAnormalidadeAcao.setLeituraAnormalidadeConsumoMes3(leituraAnormalidadeConsumo3);
		}
		
		consumoAnormalidadeAcao.setNumerofatorConsumoMes1(new BigDecimal(form.getNumerofatorConsumoMes1()));
		consumoAnormalidadeAcao.setNumerofatorConsumoMes2(new BigDecimal(form.getNumerofatorConsumoMes2()));
		consumoAnormalidadeAcao.setNumerofatorConsumoMes3(new BigDecimal(form.getNumerofatorConsumoMes3()));
		consumoAnormalidadeAcao.setIndicadorGeracaoCartaMes1(new Short(form.getIndicadorGeracaoCartaMes1()));
		consumoAnormalidadeAcao.setIndicadorGeracaoCartaMes2(new Short(form.getIndicadorGeracaoCartaMes2()));
		consumoAnormalidadeAcao.setIndicadorGeracaoCartaMes3(new Short(form.getIndicadorGeracaoCartaMes3()));

		if (form.getIdServicoTipoMes1() != null && !form.getIdServicoTipoMes1().equals("")){
			ServicoTipo servicoTipo1 = new ServicoTipo();
			servicoTipo1.setId(new Integer(form.getIdServicoTipoMes1()));
			consumoAnormalidadeAcao.setServicoTipoMes1(servicoTipo1);
		}
		
		if (form.getIdServicoTipoMes2() != null && !form.getIdServicoTipoMes2().equals("")){
			ServicoTipo servicoTipo2 = new ServicoTipo();
			servicoTipo2.setId(new Integer(form.getIdServicoTipoMes2()));
			consumoAnormalidadeAcao.setServicoTipoMes2(servicoTipo2);
		}
		
		if (form.getIdServicoTipoMes3() != null && !form.getIdServicoTipoMes3().equals("")){
			ServicoTipo servicoTipo3 = new ServicoTipo();
			servicoTipo3.setId(new Integer(form.getIdServicoTipoMes3()));
			consumoAnormalidadeAcao.setServicoTipoMes3(servicoTipo3);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes1() != null && !form.getSolicitacaoTipoEspecificacaoMes1().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao1 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao1.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes1()));
			consumoAnormalidadeAcao.setSolicitacaoTipoEspecificacaoMes1(solicitacaoTipoEspecificacao1);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes2() != null && !form.getSolicitacaoTipoEspecificacaoMes2().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao2 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao2.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes2()));
			consumoAnormalidadeAcao.setSolicitacaoTipoEspecificacaoMes2(solicitacaoTipoEspecificacao2);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes3() != null && !form.getSolicitacaoTipoEspecificacaoMes3().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao3 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao3.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes3()));
			consumoAnormalidadeAcao.setSolicitacaoTipoEspecificacaoMes3(solicitacaoTipoEspecificacao3);
		}
		
		consumoAnormalidadeAcao.setDescricaoContaMensagemMes1(form.getDescricaoContaMensagemMes1());
		consumoAnormalidadeAcao.setDescricaoContaMensagemMes2(form.getDescricaoContaMensagemMes2());
		consumoAnormalidadeAcao.setDescricaoContaMensagemMes3(form.getDescricaoContaMensagemMes3());
		consumoAnormalidadeAcao.setIndicadorUso(new Short(form.getIndicadorUso()));
		consumoAnormalidadeAcao.setUltimaAlteracao(new Date());
		
		
		FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();

		filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
				FiltroConsumoAnormalidadeAcao.CONSUMO_ANORMALIDADE_ID, consumoAnormalidadeAcao.
				getConsumoAnormalidade().getId()));
		
		if (form.getCategoria() != null /*&& !form.getCategoria().equals("-1")*/){
			
			if (form.getCategoria().equals("-1")){
				
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroNulo(
						FiltroConsumoAnormalidadeAcao.CATEGORIA));
				
			}else{
			filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidadeAcao.CATEGORIA, consumoAnormalidadeAcao.
					getCategoria()));
			}
		}
		
		if (form.getImovelPerfil() != null /*&& !form.getImovelPerfil().equals("-1")*/){
			
			if ( form.getImovelPerfil().equals("-1") ){
				
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroNulo(
						FiltroConsumoAnormalidadeAcao.IMOVEL_PERFIL));
				
			}else{
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidadeAcao.IMOVEL_PERFIL, consumoAnormalidadeAcao.
					getImovelPerfil()));
		
			}
		}
		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroConsumoAnormalidadeAcao, ConsumoAnormalidadeAcao.class.getName()); 

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			 
			Iterator iteratorColecaoPesquisa = colecaoPesquisa.iterator();
			while(iteratorColecaoPesquisa.hasNext()) {
				
				ConsumoAnormalidadeAcao consumoAnormalidadeAcaoJaCadastrado =(ConsumoAnormalidadeAcao) iteratorColecaoPesquisa.next();
				
				if ( !consumoAnormalidadeAcaoJaCadastrado.getId().toString().equals(
						consumoAnormalidadeAcao.getId().toString()) ) {
			
					throw new ActionServletException("atencao.consumo_anormalidade_acao_ja_existe");
				}
			
			}
		
		} 


			fachada.atualizar(consumoAnormalidadeAcao);

			montarPaginaSucesso(httpServletRequest, "Consumo Anormalidade e Ação de código "
					+ consumoAnormalidadeAcao.getId().toString() + " atualizada com sucesso.",
					"Realizar outra Manutenção de Consumo Anormalidade e Ação ",
					"exibirFiltrarConsumoAnormalidadeAcaoAction.do?menu=sim");
			
		
		
        
		// devolve o mapeamento de retorno
		return retorno;
	}

		
}
