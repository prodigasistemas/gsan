package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0386]FILTRAR TIPO PERFIL SERVICO
 * 
 * @author Kássia Albuquerque
 * @date 25/10/2006
 */

public class FiltrarTipoPerfilServicoAction extends GcomAction {

	public static final String INDICADOR_TODOS = "3";

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping
					.findForward("exibirManterTipoPerfilServico");
	
			// Mudar isso quando tiver esquema de segurança
			HttpSession sessao = httpServletRequest.getSession(false);
	
			Fachada fachada = Fachada.getInstancia();
	
			FiltrarTipoPerfilServicoActionForm form = (FiltrarTipoPerfilServicoActionForm) actionForm;
	
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			String codigoPerfilServico = form.getCodigoPerfilServico();
			String descricaoPerfilServico = form.getDescricaoPerfilServico();
			String abreviaturaPerfilServico = form.getAbrevPerfilServico();
			String qtdeComponentesEquipes = form.getQtdComponentesEquipe();
			String idEquipamentoEspecial = form.getIdEquipamentoEspecial();
			String descricaoEquipamentoEspecial = form
					.getDescricaoEquipamentoEspecial();
			String indicadorVeiculoProprio = form.getIndicadorVeiculoProprio();
			String indicadorUso = form.getIndicadorUso();
	
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
	
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				sessao.removeAttribute("indicadorAtualizar");
			}
	
			boolean peloMenosUmParametroInformado = false;
	
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo(
					FiltroServicoPerfilTipo.DESCRICAO);
	
			filtroServicoPerfilTipo
					.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");
	
			// Código
			if (codigoPerfilServico != null
					&& !codigoPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.ID, codigoPerfilServico));
	
			}
	
			// Descrição
			if (descricaoPerfilServico != null
					&& !descricaoPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
						FiltroServicoPerfilTipo.DESCRICAO, descricaoPerfilServico));
	
			}
	
			// Descrição Abreviada
			if (abreviaturaPerfilServico != null
					&& !abreviaturaPerfilServico.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
						FiltroServicoPerfilTipo.DESCRICAO_ABREVIADA,
						abreviaturaPerfilServico));
	
			}
	
			// Quantidade de Componentes
			if (qtdeComponentesEquipes != null
					&& !qtdeComponentesEquipes.trim().equalsIgnoreCase("")) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.COMPONENTES_EQUIPE,
						qtdeComponentesEquipes));
	
			}
	
			// Equipamento Especial
			if (idEquipamentoEspecial != null
					&& !idEquipamentoEspecial.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
	
				if (descricaoEquipamentoEspecial == null
						|| descricaoEquipamentoEspecial.equals("")) {
					FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
	
					filtroTabelaAuxiliarAbreviada
							.adicionarParametro(new ParametroSimples(
									FiltroTabelaAuxiliarAbreviada.ID,
									idEquipamentoEspecial));
	
					Collection colecaoEquipamentosEspeciais = fachada.pesquisar(
							filtroTabelaAuxiliarAbreviada,
							EquipamentosEspeciais.class.getName());
	
					if (colecaoEquipamentosEspeciais == null
							|| colecaoEquipamentosEspeciais.isEmpty()) {
						throw new ActionServletException(
								"atencao.equipamento_especial_inexistente");
					}
				}
	
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.ID_EQUIPES_COMPONENTES,
						idEquipamentoEspecial));
	
			}
	
			// Indicador Veículo Próprio
			if (indicadorVeiculoProprio != null
					&& !indicadorVeiculoProprio
							.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.VEICULO_PROPIO,
						indicadorVeiculoProprio));
	
			}
	
			// Indicador de Uso
			if (indicadorUso != null
					&& !indicadorUso
							.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
	
				peloMenosUmParametroInformado = true;
				filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoPerfilTipo.INDICADOR_USO, indicadorUso));
	
			}
	
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}
	
			// Manda o filtro pela sessao para o
			// ExibirManterTipoPerfilServicoAction
			sessao.setAttribute("filtroServicoPerfilTipo", filtroServicoPerfilTipo);
	
			return retorno;
	}
}
