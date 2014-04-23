package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipeComponentes;
import gcom.atendimentopublico.ordemservico.FiltroEquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarEquipeAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterEquipe");

		Fachada fachada = Fachada.getInstancia();

		FiltrarEquipeActionForm filtrarEquipeActionForm = (FiltrarEquipeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formulário para evitar "sujeiras" na tela
		String idEquipe = filtrarEquipeActionForm.getCodigo();
		String nome = filtrarEquipeActionForm.getNome();
		String placa = filtrarEquipeActionForm.getPlaca();
		String cargaTrabalho = filtrarEquipeActionForm.getCargaTrabalho();
		String idUnidade = filtrarEquipeActionForm.getIdUnidade();
		String idFuncionario = filtrarEquipeActionForm.getIdFuncionario();
		String idPerfilServico = filtrarEquipeActionForm.getIdPerfilServico();
		String indicadorUso = filtrarEquipeActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarEquipeActionForm.getTipoPesquisa();
		String codigoDdd = filtrarEquipeActionForm.getCodigoDdd();
		String numeroTelefone = filtrarEquipeActionForm.getNumeroTelefone();
		String numeroImei = filtrarEquipeActionForm.getNumeroImei();
		String equipamentosEspeciasId = filtrarEquipeActionForm.getEquipamentosEspeciasId();
		String cdUsuarioRespExcServico = filtrarEquipeActionForm.getCdUsuarioRespExecServico();
		
		// Cria o filtro
		FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();

		// Ordena a pesquisa por um parâmetro pré-definido
		filtroEquipeComponentes.setCampoOrderBy("equipe.id");

		boolean peloMenosUmParametroInformado = false;
		
		// Cria o filtro de equipamentos especiais
		FiltroEquipeEquipamentosEspeciais filtroEquipeEquipamentosEspeciais = new FiltroEquipeEquipamentosEspeciais();
		
		// Ordena a pesquisa por um parâmetro pré-definido
		filtroEquipeEquipamentosEspeciais.setCampoOrderBy("EquipamentosEspeciais.id");


		// Neste ponto o filtro é criado com os parâmetros informados na página
		// de filtrar equipe para ser executada a pesquisa no
		// ExibirManterEquipeAction

		// Verifica se a unidade organizacional existe e em caso afirmativo
		// seta-a no filtro
		if (idUnidade != null && !idUnidade.trim().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade Organizacional");
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

		// Verifica se o funcionário existe e em caso afirmativo seta-o no
		// filtro
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcionário");
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

		// Verifica se o serviço perfil tipo existe e em caso afirmativo seta-o
		// no filtro
		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {

			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoPerfilTipo.ID, idPerfilServico));

			Collection colecaoPerfilServico = fachada.pesquisar(
					filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			if (colecaoPerfilServico == null || colecaoPerfilServico.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Serviço Perfil Tipo");
			} else {
				peloMenosUmParametroInformado = true;
			}

		}

		if (idEquipe != null && !idEquipe.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		// Nome da Equipe
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if(tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				
				filtroEquipeComponentes.adicionarParametro( 
						new ComparacaoTextoCompleto(FiltroEquipe.NOME, nome ));
				
			} else {
				
				filtroEquipeComponentes.adicionarParametro( 
						new ComparacaoTexto(FiltroEquipe.NOME, nome ));
			}
		}

		if (placa != null && !placa.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}

		if (cargaTrabalho != null && !cargaTrabalho.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (codigoDdd != null && !codigoDdd.trim().equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
		}
		
		if (numeroTelefone != null && !numeroTelefone.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (numeroImei != null && !numeroImei.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
		}
		
		if(equipamentosEspeciasId != null && !equipamentosEspeciasId.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
		}
		if(cdUsuarioRespExcServico != null && !cdUsuarioRespExcServico.equals("")){
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda os parâmetros da pesquisa pela sessao para o
		// ExibirManterEquipeAction
		sessao.setAttribute("idEquipe", idEquipe);
		sessao.setAttribute("nome", nome);
		sessao.setAttribute("placa", placa);
		sessao.setAttribute("cargaTrabalho", cargaTrabalho);
		sessao.setAttribute("idUnidade", idUnidade);
		sessao.setAttribute("idFuncionario", idFuncionario);
		sessao.setAttribute("idPerfilServico", idPerfilServico);
		sessao.setAttribute("indicadorUso", indicadorUso);
		sessao.setAttribute("indicadorProgramacaoAutomatica", filtrarEquipeActionForm.getIndicadorProgramacaoAutomatica());
		sessao.setAttribute("codigoDdd", codigoDdd);
		sessao.setAttribute("numeroTelefone", numeroTelefone);
		sessao.setAttribute("numeroImei", numeroImei);
		sessao.setAttribute("equipamentosEspeciasId", equipamentosEspeciasId);
		if(cdUsuarioRespExcServico != null && !cdUsuarioRespExcServico.equals("")){
			sessao.setAttribute("cdUsuarioRespExcServico", cdUsuarioRespExcServico);
		}

		return retorno;
	}
}
