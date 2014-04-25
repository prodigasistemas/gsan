package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLigacaoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 28 de Dezembro de 2005
 */
public class ExibirFiltrarExcecoesLeiturasConsumosCaracteristicasAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("filtrarExcecoesLeiturasConsumosCaracteristica");

        //HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        
        //Perfil Imovel
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<ImovelPerfil> imovelPerfis = fachada.pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());
		if (imovelPerfis.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("imovelPerfis", imovelPerfis);
		}
		
//		 Categoria Imovel
		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<Categoria> categorias = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		if (categorias.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("categorias", categorias);
		}

		// Tipo Medicao
		FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
		filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
				FiltroMedicaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<MedicaoTipo> medicaoTipos = fachada.pesquisar(
				filtroMedicaoTipo, MedicaoTipo.class.getName());
		if (medicaoTipos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("medicaoTipos", medicaoTipos);
		}

		// Tipo Ligacao
		FiltroLigacaoTipo filtroLigacaoTipo = new FiltroLigacaoTipo();
		filtroLigacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<LigacaoTipo> ligacaoTipos = fachada.pesquisar(
				filtroLigacaoTipo, LigacaoTipo.class.getName());
		if (ligacaoTipos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("ligacaoTipos", ligacaoTipos);
		}

        return retorno;
    }
}
