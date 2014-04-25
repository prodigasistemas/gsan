package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.FiltroPerfilServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe a pesquisa de equipe
 * 
 * @author Leonardo Regis, Pedro Alexandre
 * @date 31/07/2006, 12/12/2007
 */
public class ExibirPesquisarTipoServicoAction extends GcomAction {

	/**
	 * Efetua pesquisa de Tipo de Serviço
	 * 
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 04/08/2006
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

		PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm = (PesquisarTipoServicoActionForm) actionForm;
		ActionForward retorno = actionMapping.findForward("tipoServicoPesquisar");
		Fachada fachada = Fachada.getInstancia();
//		 Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("limpar") != null){
			pesquisarTipoServicoActionForm.setDsTipoServico("");
			pesquisarTipoServicoActionForm.setTipoPesquisa(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
			pesquisarTipoServicoActionForm.setDsAbreviadaTipoServico("");
			pesquisarTipoServicoActionForm.setTipoPesquisaAbreviada(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
			pesquisarTipoServicoActionForm.setSubgrupoTipoServico("");
			//pesquisarTipoServicoActionForm.setIndicadorPavimento("3");
			
			pesquisarTipoServicoActionForm.setIndicadorPavimentoRua("3");
			pesquisarTipoServicoActionForm.setIndicadorPavimentoCalcada("3");
			
			pesquisarTipoServicoActionForm.setValorServicoInicial("");
			pesquisarTipoServicoActionForm.setValorServicoFinal("");
//			pesquisarTipoServicoActionForm.setIndicadorAtualizacaoComercio("3");
			pesquisarTipoServicoActionForm.setIndicadorAtualizacaoComercio("0");
			pesquisarTipoServicoActionForm.setIndicadorServicoTerceirizado("3");
			pesquisarTipoServicoActionForm.setCodigoServico("3");
			pesquisarTipoServicoActionForm.setTempoMedioExecucaoInicial("");
			pesquisarTipoServicoActionForm.setTempoMedioExecucaoFinal("");
			pesquisarTipoServicoActionForm.setTipoDebito("");
			pesquisarTipoServicoActionForm.setDsTipoDebito("");
			pesquisarTipoServicoActionForm.setTipoCredito("");
			pesquisarTipoServicoActionForm.setPrioridadeServico("");
			pesquisarTipoServicoActionForm.setPerfilServco("");
			pesquisarTipoServicoActionForm.setDsPerfilServco("");
			pesquisarTipoServicoActionForm.setTipoServicoReferencia("");
			pesquisarTipoServicoActionForm.setDsTipoServicoReferencia("");
			pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
			pesquisarTipoServicoActionForm.setDsAtividadesTipoServico("");
			pesquisarTipoServicoActionForm.setArrayAtividade(new ArrayList());
			pesquisarTipoServicoActionForm.setArrayMateriais(new ArrayList());
			
			
		}
		
		// Resultado da Pesquisa pelo popup
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Perfil do Servico
			if (httpServletRequest.getParameter("tipoConsulta").equals("servicoPerfilTipo")) {
				pesquisarTipoServicoActionForm.setPerfilServco(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsPerfilServco(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Tipo de Debito
			else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"tipoDebito")) {
				pesquisarTipoServicoActionForm.setTipoDebito(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoDebito(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Tipo de Serviço Referência
			else if (httpServletRequest.getParameter("tipoConsulta").equals("servicoTipoReferencia")) {
				pesquisarTipoServicoActionForm.setTipoServicoReferencia(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoServicoReferencia(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Atividade do tipo Servico
			else if (httpServletRequest.getParameter("tipoConsulta").equals("atividade")) {
				pesquisarTipoServicoActionForm.setAtividadesTipoServico(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsAtividadesTipoServico(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Material do Tipo de Servico
			else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"material")) {
				pesquisarTipoServicoActionForm.setTipoServicoMaterial(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoServicoMaterial(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}
		
		// Inicio do código para carregamento das campos 
		//Subgrupo do Tipo de Servico
		getSubgrupoTipoServico(httpServletRequest,pesquisarTipoServicoActionForm, fachada);
		//Tipo de Crédito
		getTipoCredito(httpServletRequest, pesquisarTipoServicoActionForm,fachada);
		//Prioridade Serviço
		getPrioridadeServico(httpServletRequest,pesquisarTipoServicoActionForm, fachada);
		// Tipo de Tipo de Débito
		getTipoDebito(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Tipo de Débito
		getTipoDebito(pesquisarTipoServicoActionForm, fachada);
		// Perfil do Serviço
		getPerfilServico(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Serviço de Referência
		getTipoServicoReferencia(pesquisarTipoServicoActionForm, fachada);
		// Atividades do tipo de Serviço
		getAtividadesTipoServico(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Material
		getTipoServicoMaterial(pesquisarTipoServicoActionForm, fachada);
		
		// Remove componete na coleção Atividade
		if ("removeServicoTipoAtividade".equals(pesquisarTipoServicoActionForm
				.getMethod())) {
				Integer id = new Integer(pesquisarTipoServicoActionForm.getTipoServicoAtividadeId());
			if (id!= null){
				pesquisarTipoServicoActionForm.removeServicoTipoAtividade(id,pesquisarTipoServicoActionForm.getAtividades(),pesquisarTipoServicoActionForm.getArrayAtividade());
				pesquisarTipoServicoActionForm.setMethod("");
			}
		}
		// Remove componete na coleção Material
		if ("removeServicoTipoMaterial".equals(pesquisarTipoServicoActionForm
				.getMethod())) {
			Integer id = new Integer(pesquisarTipoServicoActionForm.getTipoServicoMaterialId());
			if (id!= null){
				pesquisarTipoServicoActionForm.removeServicoTipoMateriais(id,pesquisarTipoServicoActionForm.getMateriais(),pesquisarTipoServicoActionForm.getArrayMateriais());
				
				pesquisarTipoServicoActionForm.setMethod("");
			}
		}
		
		if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico") != null){
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoServico", httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico"));
		}
		if(httpServletRequest.getParameter("tipo") != null){
			sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
		}
		// Inicio da Validação dos Campos 
		// Tempo Medio
		//this.isValidarTempoMedio(pesquisarTipoServicoActionForm);
		// Valor do Tipo de Serviço
		//this.isValidarValorTipoServico(pesquisarTipoServicoActionForm);

		return retorno;
	}

	/**
	 * Seta novo Componente na Coleção
	 * 
	 * @author Leandro Cavlcanti
	 * @date 10/08/2006
	 * 
	 * @param inserirAtividadeActionForm
	 * @param atividades
	 */
	private void setColecaoAtividades(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Atividade atividade) {
		if(!pesquisarTipoServicoActionForm.getAtividades().contains(atividade)){
			pesquisarTipoServicoActionForm.getArrayAtividade().add(atividade);
			pesquisarTipoServicoActionForm.getAtividades().add(atividade.getId());
		}
	}

	/**
	 * Seta novo Componente na Coleção
	 * 
	 * @author Leandro Cavlcanti
	 * @date 10/08/2006
	 * 
	 * @param inserirMateriaisActionForm
	 * @param materiais
	 */
	private void setColecaoMateriais(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			MaterialUnidade servicoTipoMaterial) {
		if(!pesquisarTipoServicoActionForm.getMateriais().contains(servicoTipoMaterial)){
			pesquisarTipoServicoActionForm.getArrayMateriais().add(servicoTipoMaterial);
			pesquisarTipoServicoActionForm.getMateriais().add(servicoTipoMaterial.getId());
		}
	}

	/**
	 * Recupera Subgrupo do Tipo de Serviço
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getSubgrupoTipoServico(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
		filtroServicoTipoSubgrupo.adicionarParametro(new ParametroSimples(FiltroServicoTipoSubgrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Recupera
		Collection colecaoServicoTipoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
		
		if (colecaoServicoTipoSubgrupo != null
				&& !colecaoServicoTipoSubgrupo.isEmpty()) {
			sessao.setAttribute("colecaoServicoTipoSubgrupo",colecaoServicoTipoSubgrupo);
		}else{
			//pesquisarTipoServicoActionForm.setSubgrupoTipoServico("Subgrupo do Tipo Serviço Inexistente!");
			pesquisarTipoServicoActionForm.setSubgrupoTipoServico("");
		}

	}


	/**
	 * Recupera Tipo de Débito [FS0003] Validar tipo de Débito
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getTipoDebito(PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		String idTipoDebito = pesquisarTipoServicoActionForm.getTipoDebito();

		if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.ID, idTipoDebito));
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

				DebitoTipo debitoTipo = (DebitoTipo) Util
						.retonarObjetoDeColecao(colecaoDebitoTipo);

				pesquisarTipoServicoActionForm.setTipoDebito(debitoTipo.getId()
						.toString());
				pesquisarTipoServicoActionForm.setDsTipoDebito(debitoTipo
						.getDescricao());

			} else {
				// [FS0003] Validar tipo de Débito
				pesquisarTipoServicoActionForm.setTipoDebito("");
				pesquisarTipoServicoActionForm
						.setDsTipoDebito("Tipo de Débito Inexistente!");

			}
		}
	}

	/**
	 * Recupera Tipo de Credito
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getTipoCredito(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		// Filtra
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(
				FiltroDiametroLigacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO.toString()));

		// Recupera
		Collection colecaoTipoCredito = fachada.pesquisar(filtroCreditoTipo,
				CreditoTipo.class.getName());
		if (colecaoTipoCredito != null && !colecaoTipoCredito.isEmpty()) {
			sessao.setAttribute("colecaoTipoCredito", colecaoTipoCredito);
		}

	}

	/**
	 * Recupera Prioridade Do Serviço
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param PesquisarTipoServicoActionForm
	 * @param fachada
	 * 
	 */

	private void getPrioridadeServico(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		// Filtra
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroDiametroLigacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Recupera
		Collection colecaoPrioridadeServico = fachada.pesquisar(
				filtroServicoTipoPrioridade, ServicoTipoPrioridade.class
						.getName());
		if (colecaoPrioridadeServico != null
				&& !colecaoPrioridadeServico.isEmpty()) {
			sessao.setAttribute("colecaoPrioridadeServico",
					colecaoPrioridadeServico);
		}

	}

	/**
	 * Recupera Perfil do Serviço [FS0005] Validar Perfil do Servico
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * @param fachada
	 * 
	 */

	private void getPerfilServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idPerfilServico = pesquisarTipoServicoActionForm
				.getPerfilServco();

		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroPerfilServico filtroPerfilServico = new FiltroPerfilServico();

			filtroPerfilServico.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.ID, idPerfilServico));
			filtroPerfilServico.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoServicoPerfilTipo = Fachada.getInstancia()
					.pesquisar(filtroPerfilServico,
							ServicoPerfilTipo.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoPerfilTipo != null
					&& !colecaoServicoPerfilTipo.isEmpty()) {

				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) Util
						.retonarObjetoDeColecao(colecaoServicoPerfilTipo);
				pesquisarTipoServicoActionForm
						.setPerfilServco(servicoPerfilTipo.getId().toString());
				pesquisarTipoServicoActionForm
						.setDsPerfilServco(servicoPerfilTipo.getDescricao());
				pesquisarTipoServicoActionForm.setDsPerfilServicoCor("#000000");
			} else {
				// [FS0005] Validar Perfil do Servico
				pesquisarTipoServicoActionForm.setPerfilServco("");
				pesquisarTipoServicoActionForm
						.setDsPerfilServco("Perfil do Serviço Inexistente");
				pesquisarTipoServicoActionForm.setDsPerfilServicoCor("#FF0000");

			}

		}

	}

	/**
	 * Validar atividade [FS0006] Validar atividade
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getAtividadesTipoServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idAtividadesTipoServico = pesquisarTipoServicoActionForm
				.getAtividadesTipoServico();

		if (idAtividadesTipoServico != null
				&& !idAtividadesTipoServico.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroAtividade filtroAtividade = new FiltroAtividade();

			filtroAtividade.adicionarParametro(new ParametroSimples(
					FiltroAtividade.ID, idAtividadesTipoServico));
			
			// Pesquisa de acordo com os parâmetros informados no filtro

			Collection colecaoServicoTipoAtividade = Fachada.getInstancia()
					.pesquisar(filtroAtividade, Atividade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoTipoAtividade != null
					&& !colecaoServicoTipoAtividade.isEmpty()) {

				Atividade servicoTipoAtividade = (Atividade) Util
						.retonarObjetoDeColecao(colecaoServicoTipoAtividade);

				if ((servicoTipoAtividade != null && !servicoTipoAtividade
						.equals(""))) {
					pesquisarTipoServicoActionForm
							.setAtividadesTipoServico(servicoTipoAtividade
									.getId().toString());
					pesquisarTipoServicoActionForm
							.setDsAtividadesTipoServico(servicoTipoAtividade
									.getDescricao());

					// add aividades na coleção
					setColecaoAtividades(pesquisarTipoServicoActionForm,
							servicoTipoAtividade);
					
					pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
					pesquisarTipoServicoActionForm
							.setDsAtividadesTipoServico("");
					pesquisarTipoServicoActionForm.setDsAtividadeCor("#000000");
				}
				
			} else {
				// [FS0006] Validar atividade
				pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
				pesquisarTipoServicoActionForm
						.setDsAtividadesTipoServico("Atividada Inexistente!");
				pesquisarTipoServicoActionForm.setDsAtividadeCor("#FF0000");

			}
		}
	}

	/**
	 * Recupera Tipo de Servico Referência [FS0004] Validar Tipo de Servico
	 * Referência
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getTipoServicoReferencia(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idTipoServicoReferencia = pesquisarTipoServicoActionForm
				.getTipoServicoReferencia();

		if (idTipoServicoReferencia != null
				&& !idTipoServicoReferencia.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();

			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID,
							idTipoServicoReferencia));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoServicoTipoReferencia = Fachada.getInstancia()
					.pesquisar(filtroServicoTipoReferencia,
							ServicoTipoReferencia.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoTipoReferencia != null
					&& !colecaoServicoTipoReferencia.isEmpty()) {

				ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) Util
						.retonarObjetoDeColecao(colecaoServicoTipoReferencia);
				
				pesquisarTipoServicoActionForm
						.setTipoServicoReferencia(servicoTipoReferencia.getId()
								.toString());
				pesquisarTipoServicoActionForm
						.setDsTipoServicoReferencia(servicoTipoReferencia
								.getDescricao());
				pesquisarTipoServicoActionForm.setDsTipoServicoReferenciaCor("#000000");
			} else {
				// [FS0004] Validar tipo de Servico Referência
				pesquisarTipoServicoActionForm.setTipoServicoReferencia("");
				pesquisarTipoServicoActionForm
						.setDsTipoServicoReferencia("Tipo de Serviço Referência inexistente");
				pesquisarTipoServicoActionForm.setDsTipoServicoReferenciaCor("#FF0000");

			}
		}
	}

	/**
	 * Validar Valor Tipo de Servico [FS0001] - Validar Valor Tipo de Servico
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */


	/*private void isValidarValorTipoServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm) {

		Integer valorTipoServicoInicial = new Integer(
				pesquisarTipoServicoActionForm.getValorServicoInicial());
		Integer valorTipoServicoFinal = new Integer(
				pesquisarTipoServicoActionForm.getValorServicoFinal());

		if (valorTipoServicoInicial != null
				&& !valorTipoServicoInicial.equals("")
				&& valorTipoServicoFinal != null
				&& !valorTipoServicoFinal.equals("")) {
			if (valorTipoServicoFinal.intValue() < valorTipoServicoInicial
					.intValue()) {
				throw new ActionServletException(
						"atencao.situacao.valor.servico.invalida", null, "");
			}
		}
	}*/

	/**
	 * Validar Tempo Médio informado [FS0001] - Validar Tempo Médio
	 * 
	 * @author Leondro Cavalcanti
	 * @date 04/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */


	/*private void isValidarTempoMedio(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm) {

		Integer valorTempoMedioInicial = new Integer(
				pesquisarTipoServicoActionForm.getTempoMedioExecucaoInicial());
		Integer valorTempoMedioFinal = new Integer(
				pesquisarTipoServicoActionForm.getTempoMedioExecucaoFinal());

		if (valorTempoMedioInicial != null
				&& !valorTempoMedioInicial.equals("")
				&& valorTempoMedioFinal != null
				&& !valorTempoMedioFinal.equals("")) {
			if (valorTempoMedioFinal.intValue() < valorTempoMedioInicial
					.intValue()) {
				throw new ActionServletException(
						"atencao.situacao.tempo.execucao.invalida", null, "");
			}
		}
	}*/

	/**
	 * Recupera Materiais do Tipo de Servico [FS0007] Validar Material
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getTipoServicoMaterial(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		
		String idTipoServicoMaterial = pesquisarTipoServicoActionForm
				.getTipoServicoMaterial();

		if (idTipoServicoMaterial != null
				&& !idTipoServicoMaterial.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();

			filtroMaterialUnidade.adicionarParametro(new ParametroSimples(
					FiltroMaterialUnidade.ID, idTipoServicoMaterial));
			
			// Pesquisa de acordo com os parâmetros informados no filtro

			Collection colecaoServicoTipoMaterial = Fachada.getInstancia()
					.pesquisar(filtroMaterialUnidade,
							MaterialUnidade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoTipoMaterial != null
					&& !colecaoServicoTipoMaterial.isEmpty()) {

				MaterialUnidade servicoTipoMaterial = (MaterialUnidade) Util
						.retonarObjetoDeColecao(colecaoServicoTipoMaterial);
				if ((servicoTipoMaterial != null && !servicoTipoMaterial
						.equals(""))) {
					pesquisarTipoServicoActionForm.setTipoServicoMaterial(servicoTipoMaterial.getId().toString());
					pesquisarTipoServicoActionForm.setDsTipoServicoMaterial(servicoTipoMaterial.getDescricao());
					
					// add componente material da coleção
					setColecaoMateriais(pesquisarTipoServicoActionForm,servicoTipoMaterial);
					//	sessao.setAttribute("materiais",servicoTipoMaterial );
					}
					pesquisarTipoServicoActionForm.setTipoServicoMaterial("");
					pesquisarTipoServicoActionForm.setDsTipoServicoMaterial("");
					pesquisarTipoServicoActionForm.setDsMaterialCor("#000000");
				}

			 else {
				//[FS0004] Validar tipo de Servico Material
				pesquisarTipoServicoActionForm.setTipoServicoMaterial("");
				pesquisarTipoServicoActionForm.setDsTipoServicoMaterial("Material do Tipo de Serviço Inexistente");
				pesquisarTipoServicoActionForm.setDsMaterialCor("#FF0000");
			}
		}
	}
}
