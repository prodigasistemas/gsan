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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasCaracteristicas");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(imovelEmissaoOrdensSeletivas.getIdImovel() != null
				&& !imovelEmissaoOrdensSeletivas.getIdImovel().equals("")){
			
			httpServletRequest.setAttribute("desabilitarCampos", "ok");
			
		}else{
			if (httpServletRequest.getAttribute("desabilitarCampos") != null){
				httpServletRequest.removeAttribute("desabilitarCampos");
			}
		}
		 
		Collection<ImovelPerfil> collectionImovelPerfil = null;
		Collection<Categoria> collectionImovelCategoria= null;
		Collection<Subcategoria> collectionImovelSubcategoria = null;
		Collection<AreaConstruidaFaixa> collectionIntervaloAreaConstruidaPredefinida = null;
		Collection<MedicaoTipo> collectionTipoMedicao = null;
		 
		Integer categoria = 0;
		
		if (imovelEmissaoOrdensSeletivas.getCategoria() != null)
			categoria = new Integer(imovelEmissaoOrdensSeletivas.getCategoria());
		
		// Lista o Perfil do Imovel
		if (imovelEmissaoOrdensSeletivas.getPerfilImovel() == null) {
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imóvel");			
			}
			
			sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
		}
		
		// Lista de Categoria
		if (imovelEmissaoOrdensSeletivas.getCategoria() == null) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Categoria");			
			}
			
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
		}
		
		// Lista de SubCategorias
		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
		
		if (categoria != 0 && !imovelEmissaoOrdensSeletivas.getCategoria().trim().
				equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
					categoria));
		}
		filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
		
		collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());		 
		if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Subcategoria");			
		}
		
		sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
		
		// Lista As Faixas das Areas Construidas
		if (imovelEmissaoOrdensSeletivas.getIntervaloAreaConstruidaPredefinida() == null) {
			FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();	
			
			filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(
					FiltroAreaConstruidaFaixa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroAreaConstruidaFaixa.setCampoOrderBy(FiltroAreaConstruidaFaixa.CODIGO);

			collectionIntervaloAreaConstruidaPredefinida = fachada.pesquisar(filtroAreaConstruidaFaixa,
					AreaConstruidaFaixa.class.getName());
			
//			if(collectionIntervaloAreaConstruidaPredefinida == null ||
//					collectionIntervaloAreaConstruidaPredefinida.isEmpty()) {
//				throw new ActionServletException("atencao.naocadastrado", null, "Faixa de Area Construida");			
//			}
			
			sessao.setAttribute("collectionIntervaloAreaConstruidaPredefinida",
					collectionIntervaloAreaConstruidaPredefinida);
		}
		
		// Tipo Medicao
		if (imovelEmissaoOrdensSeletivas.getTipoMedicao() == null) {
			FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();	
			
			filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
					FiltroMedicaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroMedicaoTipo.setCampoOrderBy(FiltroMedicaoTipo.DESCRICAO);
			collectionTipoMedicao = fachada.pesquisar(filtroMedicaoTipo, MedicaoTipo.class.getName());
			
			if(collectionTipoMedicao == null || collectionTipoMedicao.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Medição");			
			}
			
			sessao.setAttribute("collectionTipoMedicao", collectionTipoMedicao);
			imovelEmissaoOrdensSeletivas.setTipoMedicao(""+MedicaoTipo.LIGACAO_AGUA);
		}
		//Tipo Projeto
		if (imovelEmissaoOrdensSeletivas.getIdProjeto() == null) {
			FiltroProjeto filtroProjeto = new FiltroProjeto();	
			
			filtroProjeto.adicionarParametro(new ParametroNulo(FiltroProjeto.DATA_FIM));
			filtroProjeto.setCampoOrderBy(FiltroProjeto.NOME);
			
			Collection collectionProjetos = fachada.pesquisar(filtroProjeto, Projeto.class.getName());
			
			if(collectionProjetos == null || collectionProjetos.isEmpty()) {
				sessao.removeAttribute("collectionProjetos");		
			}else{
				sessao.setAttribute("collectionProjetos", collectionProjetos);
			}
			
			
		}
		
		Collection<LigacaoAguaSituacao> collectionLigacaoAguaSituacao = null;
		
		//Lista a Situacao de Ligacao de Agua
		if (imovelEmissaoOrdensSeletivas.getSituacaoLigacaoAgua() == null) {
			
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			collectionLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, 
					LigacaoAguaSituacao.class.getName());
			
			if(collectionLigacaoAguaSituacao == null || collectionLigacaoAguaSituacao.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Situação Ligação de Água");			
			}
			
			sessao.setAttribute("collectionLigacaoAguaSituacao", collectionLigacaoAguaSituacao);
			
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "CARACTERISTICAS");
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem() != null) {
			httpServletRequest.setAttribute("tipoOrdem", imovelEmissaoOrdensSeletivas.getTipoOrdem());
		}
		
		return retorno;
	}
}