package gcom.gui.cadastro.endereco;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.cadastro.endereco.Logradouro;
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
 * @author Sávio Luiz
 */
public class AtualizarLogradouroGrauImportanciaAction extends GcomAction {
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
		


		Logradouro logradouro = null;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		int count = 0;
		String idsAtualizados = "";
		int[] arr ;
		arr = new int[qtIds];
		for(int i =0; i < qtIds; i++){
			
			String idLogradouro = (String) ids[i];
			
			if (idLogradouro != null) {

				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, new Integer(idLogradouro)));

				filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroEncontrado = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
					logradouro = ((Logradouro) ((List) logradouroEncontrado).get(0));

				} else {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null, "logradouro");
				}
			}
		
			
			
			if (requestMap.get("grauImportancia_" + idLogradouro) != null && !((requestMap.get("grauImportancia_" + idLogradouro))[0]).equals("")){

				FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
				filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,new Integer((requestMap.get("grauImportancia_" + idLogradouro))[0])));
				Collection collOSProgramaCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
				OSProgramacaoCalibragem programaCalibragem = (OSProgramacaoCalibragem)Util.retonarObjetoDeColecao(collOSProgramaCalibragem);
				
				logradouro.setProgramaCalibragem(programaCalibragem);
				
			}else{
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", null, "Grau de Importância");
			}
			
			arr[count]  = logradouro.getId();			
			count++;
			fachada.atualizarGrauImportancia(logradouro, logradouro.getProgramaCalibragem().getId(),usuarioLogado);
				
		}
		for(int j = 0; j < arr.length; j++){
			if(j==arr.length-1){
				idsAtualizados += arr[j];
			}else{
				idsAtualizados += arr[j]+",";
			}
		}
		
		sessao.removeAttribute("indicadorImportanciaLogradouro");

		montarPaginaSucesso(httpServletRequest, "Logradouro de código "
				+ idsAtualizados + " atualizado com sucesso.",
				"Realizar outra Manutenção de Logradouro",
				"exibirManterLogradouroAction.do?menu=sim&implog=sim");

		return retorno;
	}
}
