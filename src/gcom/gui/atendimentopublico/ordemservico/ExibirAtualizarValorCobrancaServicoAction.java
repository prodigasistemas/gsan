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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio - Hugo Leonardo
 * @date 30/10/2006 - 22/04/2010
 */
public class ExibirAtualizarValorCobrancaServicoAction extends GcomAction {
	/**
	 * [UC0393] Atualizar Valor de Cobrança do Serviço
	 * 
	 * Este caso de uso permite alterar um valor de cobrança de serviço
	 * 
	 * @author Rômulo Aurélio - Hugo Loenardo
	 * @date 31/10/2006 - 22/04/2010
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
		ActionForward retorno = actionMapping
				.findForward("atualizarValorCobrancaServico");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarValorCobrancaServicoActionForm form = (AtualizarValorCobrancaServicoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
	
		ServicoCobrancaValor servicoCobrancaValor = null;

		String idServicoCobrancaValor = (String) sessao.getAttribute("idRegistroAtualizar");

		if(idServicoCobrancaValor != null && !idServicoCobrancaValor.equals("")){
			
			FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();
			
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoCobrancaValor.CATEGORIA_ENTIDADE);
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoCobrancaValor.SUBCATEGORIA_ENTIDADE);
			filtroServicoCobrancaValor.adicionarParametro(
						new ParametroSimples(FiltroServicoCobrancaValor.ID, new Integer(idServicoCobrancaValor)));
			
			servicoCobrancaValor = (ServicoCobrancaValor) fachada.pesquisar(filtroServicoCobrancaValor, 
					ServicoCobrancaValor.class.getName()).iterator().next();
			
			idServicoCobrancaValor = servicoCobrancaValor.getId().toString();
			
		}
		
		if(servicoCobrancaValor == null){
			
			FiltroServicoCobrancaValor filtroServicoCobrancaValor = new FiltroServicoCobrancaValor();
			
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoCobrancaValor.CATEGORIA_ENTIDADE);
			filtroServicoCobrancaValor.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoCobrancaValor.SUBCATEGORIA_ENTIDADE);
			filtroServicoCobrancaValor.adicionarParametro(
						new ParametroSimples(FiltroServicoCobrancaValor.ID, new Integer(form.getIdServicoCobrancaValor())));
			
			servicoCobrancaValor = (ServicoCobrancaValor) fachada.pesquisar(filtroServicoCobrancaValor, 
					ServicoCobrancaValor.class.getName()).iterator().next();
		}
		
		if((httpServletRequest.getParameter("manter")!=null &&
				httpServletRequest.getParameter("manter").toString().equals("sim"))
					||(httpServletRequest.getAttribute("manter")!=null &&
							httpServletRequest.getAttribute("manter").toString().equals("sim"))){	

			form.setIdServicoCobrancaValor(servicoCobrancaValor.getId().toString());
			form.setTipoServico(servicoCobrancaValor.getServicoTipo().getId().toString());	
			form.setNomeTipoServico(servicoCobrancaValor.getServicoTipo().getDescricao());
			form.setIndicadorMedido(""+ servicoCobrancaValor.getIndicadorMedido());
			form.setIndicativoTipoSevicoEconomias(servicoCobrancaValor.getIndicadorConsideraEconomias().toString());
		
			String dataVigenciaInicial = Util.formatarData(servicoCobrancaValor.getDataVigenciaInicial());
			String dataVigenciaFinal = Util.formatarData(servicoCobrancaValor.getDataVigenciaFinal());		
			String valorServico = Util.formatarMoedaReal(servicoCobrancaValor.getValor());		
			
			
			if(servicoCobrancaValor.getId() != null && !servicoCobrancaValor.getId().equals("")){
				form.setIdServicoCobrancaValor(servicoCobrancaValor.getId().toString());
			}
			
			if(servicoCobrancaValor.getServicoTipo().getId() != null && !servicoCobrancaValor.getServicoTipo().getId().equals("")){
				form.setTipoServico(servicoCobrancaValor.getServicoTipo().getId().toString());
			}
			
			if(servicoCobrancaValor.getServicoTipo().getDescricao() != null 
					&& !servicoCobrancaValor.getServicoTipo().getDescricao().equals("")){
				
				form.setNomeTipoServico(servicoCobrancaValor.getServicoTipo().getDescricao());
			}
			
			if(servicoCobrancaValor.getSubCategoria() != null 
					&& !servicoCobrancaValor.getSubCategoria().getId().equals("-1")
						&& form.getIdSubCategoria()==null ||
								(httpServletRequest.getParameter("desfazer")!=null &&
										httpServletRequest.getParameter("desfazer").toString().equalsIgnoreCase("S"))){
				
				form.setIdSubCategoria(servicoCobrancaValor.getSubCategoria().getId().toString());
			}
			
			if(servicoCobrancaValor.getCategoria() != null 
					&& !servicoCobrancaValor.getCategoria().getId().equals("-1")
						&& form.getIdCategoria()==null ||
								(httpServletRequest.getParameter("desfazer")!=null &&
										httpServletRequest.getParameter("desfazer").toString().equalsIgnoreCase("S"))){
				
				form.setIdCategoria(servicoCobrancaValor.getCategoria().getId().toString());
			}
			
			if(servicoCobrancaValor.getHidrometroCapacidade() != null && servicoCobrancaValor.getHidrometroCapacidade().getId() != null 
					&& !servicoCobrancaValor.getHidrometroCapacidade().getId().equals("")){
				
				form.setCapacidadeHidrometro(servicoCobrancaValor.getHidrometroCapacidade().getId().toString());
			}
			
			if(servicoCobrancaValor.getImovelPerfil() != null && servicoCobrancaValor.getImovelPerfil().getId() != null 
					&& !servicoCobrancaValor.getImovelPerfil().getId().equals("-1")){
				
				form.setPerfilImovel(servicoCobrancaValor.getImovelPerfil().getId().toString());
				form.setIndicadorMedido(""+ servicoCobrancaValor.getIndicadorMedido());
			}
			
			if(servicoCobrancaValor.getSubCategoria() != null && servicoCobrancaValor.getSubCategoria().getId() != null 
					&& !servicoCobrancaValor.getSubCategoria().getId().equals("-1")){
				
				form.setIdSubCategoria(servicoCobrancaValor.getSubCategoria().getId().toString());
			}
			
			if(servicoCobrancaValor.getValor() != null && !servicoCobrancaValor.getValor().equals("")){
				
				form.setValorServico(valorServico);
			}
			
			if(servicoCobrancaValor.getDataVigenciaInicial() != null 
					&& !servicoCobrancaValor.getDataVigenciaInicial().equals("")){
				
				form.setDataVigenciaInicial(dataVigenciaInicial);
			}
			
			if(servicoCobrancaValor.getDataVigenciaFinal() != null 
					&& !servicoCobrancaValor.getDataVigenciaFinal().equals("")){
				
				form.setDataVigenciaFinal(dataVigenciaFinal);
			}
			
			if(servicoCobrancaValor.getIndicadorConsideraEconomias() != null
					&& !servicoCobrancaValor.getIndicadorConsideraEconomias().equals("")){
				
				form.setIndicativoTipoSevicoEconomias(servicoCobrancaValor.getIndicadorConsideraEconomias().toString());
			}
				
			if(servicoCobrancaValor.getQuantidadeEconomiasInicial() != null 
					&& !servicoCobrancaValor.getQuantidadeEconomiasInicial().equals("")){
				
				form.setQuantidadeEconomiasInicial(servicoCobrancaValor.getQuantidadeEconomiasInicial().toString());
			}
			
			if(servicoCobrancaValor.getQuantidadeEconomiasFinal() != null 
					&& !servicoCobrancaValor.getQuantidadeEconomiasFinal().equals("")){
				
				form.setQuantidadeEconomiasFinal(servicoCobrancaValor.getQuantidadeEconomiasFinal().toString());
			}
			if(servicoCobrancaValor.getIndicadorGeracaoDebito()!=null
					&& !servicoCobrancaValor.getIndicadorConsideraEconomias().toString().equals("")){
				form.setIndicadorGeracaoDebito(servicoCobrancaValor.getIndicadorGeracaoDebito().toString());
			}
			
		}

		Date timeStamp = servicoCobrancaValor.getUltimaAlteracao();
		
		sessao.setAttribute("idServicoCobrancaValor", idServicoCobrancaValor);
		sessao.setAttribute("servicoCobrancaValor", servicoCobrancaValor);
		sessao.setAttribute("timeStamp", timeStamp);
		sessao.setAttribute("servicoTipo", servicoCobrancaValor.getServicoTipo());
		sessao.setAttribute("subCategoria", servicoCobrancaValor.getSubCategoria());
		sessao.setAttribute("imovelPerfil", servicoCobrancaValor.getImovelPerfil());
		sessao.setAttribute("indicadorMedido", servicoCobrancaValor.getIndicadorMedido());
		sessao.setAttribute("hidrometroCapacidade", servicoCobrancaValor.getHidrometroCapacidade());
		sessao.setAttribute("valor", servicoCobrancaValor.getValor());
		sessao.setAttribute("dataVigenciaInicial", servicoCobrancaValor.getDataVigenciaInicial());
		sessao.setAttribute("dataVigenciaFinal", servicoCobrancaValor.getDataVigenciaFinal());
		sessao.setAttribute("indicadorConsideraEconomias", servicoCobrancaValor.getIndicadorConsideraEconomias());
		sessao.setAttribute("quantidadeEconomiasInicial", servicoCobrancaValor.getQuantidadeEconomiasInicial());
		sessao.setAttribute("quantidadeEconomiasFinal", servicoCobrancaValor.getQuantidadeEconomiasFinal());
		
		httpServletRequest.setAttribute("idServicoCobrancaValor", form.getIdServicoCobrancaValor());
		
		// Pesquisar Categoria
		this.pesquisarCategoria(httpServletRequest, form);
		
		// pesquisar SubCategoria
		this.pesquisarSubCategoria(httpServletRequest, form);
		
		
		if(form.getIndicadorMedido()!=null && form.getIndicadorMedido().equals(ConstantesSistema.SIM.toString())){
			if((form.getIdCategoria()!=null 
					&& !form.getIdCategoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+""))
						|| (form.getIdSubCategoria()!=null 
							&& !form.getIdSubCategoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+""))){
				sessao.setAttribute("capacidadeObrigatoria","sim");
			}else{
				sessao.setAttribute("capacidadeObrigatoria","nao");
			}
		}else{
			sessao.setAttribute("capacidadeObrigatoria","nao");
		}
		
		if(form.getIndicadorGeracaoDebito()!=null && form.getIndicadorGeracaoDebito().equals(ConstantesSistema.SIM.toString())){
			sessao.setAttribute("valorObrigatorio","sim");
		}else{
			sessao.setAttribute("valorObrigatorio","nao");
		}
		
		return retorno;

	}
	
	
	// Verificar existência de dados (CATEGORIA)
	private void pesquisarCategoria(HttpServletRequest httpServletRequest, 
			AtualizarValorCobrancaServicoActionForm form) {
	
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
		
		if(colecaoCategoria == null || colecaoCategoria.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Categorias");
		}else{
			httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
	
    // Verificar existência de dados (SUBCATEGORIA)
	private void pesquisarSubCategoria(HttpServletRequest httpServletRequest, 
			AtualizarValorCobrancaServicoActionForm form) {
	
		String idCategoria = form.getIdCategoria();
		Collection colecaoSubCategoria  = null;
		
		if(idCategoria != null && !idCategoria.equalsIgnoreCase("-1")){
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(FiltroSubCategoria.DESCRICAO);
			
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSubCategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(colecaoSubCategoria == null || colecaoSubCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "SubCategorias");
			}else{
				httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			}
		} 
	}
	
	
}
