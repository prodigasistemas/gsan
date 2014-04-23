package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização de um logradouro na base
 * 
 * @author Thúlio Araújo
 */
public class AtualizarTipoServicoGrauImportanciaAction extends GcomAction {
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

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		//		 Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		Integer qtIds = ids.length;
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        //      mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados_atualizacao");
		}
		
		// ===========================================================================

		ServicoTipo servicoTipo = null;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		int count = 0;
		String idsAtualizados = "";
		int[] arr ;
		arr = new int[qtIds];
		for(int i =0; i < qtIds; i++){
			
			String idTipoServico = (String) ids[i];
			
			if (idTipoServico != null) {

				FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

				filtroTipoServico.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, new Integer(idTipoServico)));

				filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
				filtroTipoServico.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection tipoServicoEncontrado = fachada.pesquisar(filtroTipoServico,
						ServicoTipo.class.getName());

				if (tipoServicoEncontrado != null && !tipoServicoEncontrado.isEmpty()) {
					servicoTipo = ((ServicoTipo) ((List) tipoServicoEncontrado).get(0));

				} else {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null, "Serviço Tipo");
				}
			}
			
			if (requestMap.get("grauImportancia_" + idTipoServico) != null && !((requestMap.get("grauImportancia_" + idTipoServico))[0]).equals("")) {

				FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
				filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,new Integer((requestMap.get("grauImportancia_" + idTipoServico))[0])));
				Collection collOSProgramaCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
				OSProgramacaoCalibragem programaCalibragem = (OSProgramacaoCalibragem)Util.retonarObjetoDeColecao(collOSProgramaCalibragem);
				
				servicoTipo.setProgramaCalibragem(programaCalibragem);
				
			}else{
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", null, "Grau de Importância");
			}
			
			arr[count]  = servicoTipo.getId();			
			count++;
			fachada.atualizarGrauImportanciaServicoTipo(servicoTipo, usuarioLogado);
				
		}
		for(int j = 0; j < arr.length; j++){
			if(j==arr.length-1){
				idsAtualizados += arr[j];
			}else{
				idsAtualizados += arr[j]+",";
			}
		}
		
		sessao.removeAttribute("indicadorGrauImportancia");

		montarPaginaSucesso(httpServletRequest, "Tipo de Serviço de código "
				+ idsAtualizados + " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo de Servico",
				"exibirFiltrarTipoServicoAction.do?menu=sim&imp=sim");

		return retorno;
	}
}
