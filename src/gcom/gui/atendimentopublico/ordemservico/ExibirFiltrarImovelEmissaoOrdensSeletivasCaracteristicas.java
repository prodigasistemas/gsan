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
