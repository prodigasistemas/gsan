package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraFiscalizacao;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class ExibirInformarLeituraFiscalizacaoAction extends GcomAction {

	/**
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

		ActionForward retorno = actionMapping
				.findForward("informarLeituraFiscalizacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarLeituraFiscalizacaoActionForm form = (InformarLeituraFiscalizacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

		String matricula;

		
		String idMedicaoTipo = null;

		if (httpServletRequest.getAttribute("idMedicaoTipo") != null) {
			idMedicaoTipo = (String) httpServletRequest
					.getAttribute("idMedicaoTipo");
		} else {
				idMedicaoTipo = form.getMedicaoTipo();
		}

		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.MEDICAO_TIPO_ID, idMedicaoTipo));
		
		
		if (httpServletRequest.getAttribute("matricula") != null) {
			matricula = (String) httpServletRequest.getAttribute("matricula");
		} else {
			matricula = form.getMatricula();
		}

		sessao.setAttribute("matricula", matricula);

		Imovel imovel = this.pesquisarImovel(httpServletRequest, form,
				matricula, filtroMedicaoHistorico, sessao);

		form.setInscricaoImovel("" + imovel.getInscricaoFormatada());
		
		
		
		if((new Integer (idMedicaoTipo)).intValue() == MedicaoTipo.LIGACAO_AGUA.intValue()){
		
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.LIGACAO_AGUA_ID, imovel.getId()));
			
		} else{
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.IMOVEL_ID, imovel.getId()));
		}

		
		String mesAnoReferencia;

		if (httpServletRequest.getAttribute("mesAnoReferencia") != null) {
			mesAnoReferencia = (String) httpServletRequest
					.getAttribute("mesAnoReferencia");
		} else {
			mesAnoReferencia = form.getMesAnoReferencia();
		}
		

		String mes;

		String ano;

		String anoMesReferencia;

		/**
		 * [FS0001]- Validar mês e ano de referencia do faturamento
		 */

		boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);

		/**
		 * Caso 1
		 */

		if (mesAnoValido == false) {

			throw new ActionServletException(
					"atencao.mes_ano_referencia_faturamento_invalido", null,
					mesAnoReferencia);
		}

		mes = mesAnoReferencia.substring(0, 2);
		ano = mesAnoReferencia.substring(3, 7);

		anoMesReferencia = ano + mes;

		//Integer anoMesReferenciaInteiro

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		/**
		 * Caso 2
		 */

		if ((new Integer(anoMesReferencia)).intValue() > sistemaParametro
				.getAnoMesFaturamento().intValue()) {

			throw new ActionServletException(
					"atencao.mes_ano_referencia_faturamento_posterior_ano_mes_faturamento_sistema_parametro",
					null, mesAnoReferencia);
		}

		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
				anoMesReferencia));

		filtroMedicaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("leituraFiscalizacao");
		filtroMedicaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroMedicaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeInformada");
		filtroMedicaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
		filtroMedicaoHistorico
		.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		
		this.carregarCombos(httpServletRequest, form);

		//      Obtendo dados do imovel

		Collection colecaoMedicaoHistorico = fachada.pesquisar(
				filtroMedicaoHistorico, MedicaoHistorico.class.getName());

		if (colecaoMedicaoHistorico != null
				&& !colecaoMedicaoHistorico.isEmpty()) {

			MedicaoHistorico medicaoHistorico = (MedicaoHistorico) colecaoMedicaoHistorico
					.iterator().next();

			sessao.setAttribute("medicaoHistorico", medicaoHistorico);
			/**
			 * [FS0012] Verificar existencia de hidrômetro
			 * Caso 1
			 */

			form.setMedicaoTipo("" + medicaoHistorico.getMedicaoTipo().getId().toString());
			
			if (medicaoHistorico.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA
					.intValue()) {

				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();

				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAgua.ID, imovel.getId()));
				
				/*filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade(
						FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO);*/

				filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");

				Collection colecaoLigacaoAgua = fachada.pesquisar(
						filtroLigacaoAgua, LigacaoAgua.class.getName());

				LigacaoAgua ligacaoAgua = (LigacaoAgua) colecaoLigacaoAgua
						.iterator().next();

				if (ligacaoAgua.getHidrometroInstalacaoHistorico() == null) {

					throw new ActionServletException(
							"atencao.fiscalizao_ligacao_agua_sem_hidrometro");

				}else{
					sessao.setAttribute("numeroDigitosLeitura",
							ligacaoAgua.getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura());
				}

				if(medicaoHistorico.getLeituraAnormalidadeInformada() != null){
				
				form.setAnormalidadeNormal(""
						+ medicaoHistorico.getLeituraAnormalidadeInformada()
								.getDescricao());
				} else{
					form.setAnormalidadeNormal("");
				}

				form.setDataLeituraNormal(""
						+ Util.formatarData(medicaoHistorico
								.getDataLeituraAtualInformada()));

				if(medicaoHistorico.getLeituraAtualInformada() != null){
				
				form.setLeituraNormal(""
						+ medicaoHistorico.getLeituraAtualInformada());
				}else{
					form.setLeituraNormal("");
				}
				if(medicaoHistorico.getFuncionario() != null){
					form.setMatriculaLeituristaNormal(""
						+ medicaoHistorico.getFuncionario().getId());
				} else{
					form.setMatriculaLeituristaNormal("");
				}

			} else {
				/**
				 * [FS0012] Verificar existencia de hidrômetro
				 * Caso 2
				 */
				if ((medicaoHistorico.getMedicaoTipo().getId().intValue() == MedicaoTipo.POCO
						.intValue())
						&& (imovel.getHidrometroInstalacaoHistorico() == null)) {

					throw new ActionServletException(
							"atencao.fiscalizao_poco_sem_hidrometro");

				}else{
						sessao.setAttribute("numeroDigitosLeitura",
									imovel.getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura());
				}
				
				if (medicaoHistorico.getLeituraAnormalidadeInformada() != null)
					form.setAnormalidadeNormal(""
							+ medicaoHistorico
									.getLeituraAnormalidadeInformada()
									.getDescricao());
				else {
					form.setAnormalidadeNormal("");
				}

				form.setDataLeituraNormal(""
						+ Util.formatarData(medicaoHistorico
								.getDataLeituraAtualInformada()));

				if (medicaoHistorico.getNumeroConsumoInformado() != null)
					form.setLeituraNormal(""
							+ medicaoHistorico.getNumeroConsumoInformado());
				else
					form.setLeituraNormal("");

				if (medicaoHistorico.getFuncionario() != null) {
					if (medicaoHistorico.getFuncionario().getId().intValue() != 0)
						form.setMatriculaLeituristaNormal(""
								+ medicaoHistorico.getFuncionario().getId());
					else
						form.setMatriculaLeituristaNormal("");
				}
			}

			FiltroLeituraFiscalizacao filtroLeituraFiscalizacao = new FiltroLeituraFiscalizacao();

			filtroLeituraFiscalizacao.adicionarParametro(new ParametroSimples(
					FiltroLeituraFiscalizacao.ID, medicaoHistorico.getId()
							.toString()));
			Collection colecaoLeituraFiscalizacao = fachada.pesquisar(
					filtroLeituraFiscalizacao, LeituraFiscalizacao.class
							.getName());

			if (colecaoLeituraFiscalizacao != null
					&& !colecaoLeituraFiscalizacao.isEmpty()) {

				LeituraFiscalizacao leituraFiscalizacao = (LeituraFiscalizacao) colecaoLeituraFiscalizacao
						.iterator().next();

				form.setMatriculaLeituristaFiscalizacao(leituraFiscalizacao
						.getFuncionario().getId().toString());

				form.setDataLeituraFiscalizacao(Util
						.formatarData(leituraFiscalizacao
								.getdataLeituraEmpresa()));

				form.setAnormalidadeFiscalizacao(leituraFiscalizacao
						.getLeituraAnormalidade().getId().toString());

				form.setLeituraFiscalizacao(""
						+ leituraFiscalizacao.getNumeroLeituraEmpresa());

			} else {

				form.setMatriculaLeituristaFiscalizacao("");

				form.setDataLeituraFiscalizacao("");

				form.setAnormalidadeFiscalizacao("");

				form.setLeituraFiscalizacao("");
			}

		} else {
			/**
			 * [FS0011]-Verificar existência do histórico de medição
			 */
			throw new ActionServletException(
					"atencao.imovel_sem_dados_faturamento", null,
					mesAnoReferencia);
		}

		return retorno;

	}

	/* 
	 * Carregamento dos combos
	 */

	public void carregarCombos(HttpServletRequest httpServletRequest,
			InformarLeituraFiscalizacaoActionForm form) {

		//Tipo de Medicao
		Collection colecaoMedicaoTipo = new ArrayList();
		FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();

		filtroMedicaoTipo.setCampoOrderBy(FiltroMedicaoTipo.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		colecaoMedicaoTipo = Fachada.getInstancia().pesquisar(
				filtroMedicaoTipo, MedicaoTipo.class.getName());

		if (colecaoMedicaoTipo == null || colecaoMedicaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"MedicaoTipo");
		}

		httpServletRequest.setAttribute("colecaoMedicaoTipo",
				colecaoMedicaoTipo);

		//Anormalidade

		Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = new ArrayList();

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade
				.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		colecaoLeituraAnormalidade = Fachada.getInstancia().pesquisar(
				filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoLeituraAnormalidade == null
				|| colecaoLeituraAnormalidade.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"LeituraAnormalidade");
		}

		httpServletRequest.setAttribute("colecaoLeituraAnormalidade",
				colecaoLeituraAnormalidade);

	}

	public Imovel pesquisarImovel(HttpServletRequest httpServletRequest,
			InformarLeituraFiscalizacaoActionForm form, String matricula,
			FiltroMedicaoHistorico filtroMedicaoHistorico, HttpSession sessao) {

		Imovel imovel = null;

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				matricula));

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		
		filtroImovel
		.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");

		Collection colecaoImovel = Fachada.getInstancia().pesquisar(
				filtroImovel, Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			imovel = (Imovel) colecaoImovel.iterator().next();
			

		} else {
			throw new ActionServletException("matriculaNaoEncontrado");
		}
		return imovel;
	}

	public void pesquisarDadosLeituraFiscalizacao(
			InformarLeituraFiscalizacaoActionForm form,
			MedicaoHistorico medicaoHistorico) {

		FiltroLeituraFiscalizacao filtroLeituraFiscalizacao = new FiltroLeituraFiscalizacao();

		filtroLeituraFiscalizacao.adicionarParametro(new ParametroSimples(
				FiltroLeituraFiscalizacao.MEDICAO_HISTORICO_ID,
				medicaoHistorico.getId()));

		filtroLeituraFiscalizacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroLeituraFiscalizacao.FUNCIONARIO);

		filtroLeituraFiscalizacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroLeituraFiscalizacao.LEITURA_ANORMALIDADE);

		Collection colecaoLeituraFiscalizacao = Fachada.getInstancia()
				.pesquisar(filtroLeituraFiscalizacao,
						LeituraFiscalizacao.class.getName());

		if (colecaoLeituraFiscalizacao != null
				&& !colecaoLeituraFiscalizacao.isEmpty()) {

			LeituraFiscalizacao leituraFiscalizacao = (LeituraFiscalizacao) colecaoLeituraFiscalizacao
					.iterator().next();

			form.setAnormalidadeFiscalizacao(""
					+ leituraFiscalizacao.getLeituraAnormalidade()
							.getDescricao());

			form.setDataLeituraFiscalizacao(""
					+ Util.formatarData(leituraFiscalizacao
							.getdataLeituraEmpresa()));

			form.setLeituraFiscalizacao(""
					+ leituraFiscalizacao.getNumeroLeituraEmpresa());

			form.setMatriculaLeituristaFiscalizacao(""
					+ leituraFiscalizacao.getFuncionario().getId());

		} else {
			form.setAnormalidadeFiscalizacao("");

			form.setDataLeituraFiscalizacao("");

			form.setLeituraFiscalizacao("");

			form.setMatriculaLeituristaFiscalizacao("");
		}

	}
}
