package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirAtualizarEfetuarRetiradaHidrometroAction extends GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarRetiradaHidrometro");
		EfetuarRetiradaHidrometroActionForm retiradaActionForm = (EfetuarRetiradaHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = retiradaActionForm.getIdOrdemServico();

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(
					FiltroOrdemServico.ID, idOrdemServico));

			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAguaSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoEsgotoSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");

			Collection colecaoOrdemServico = fachada.pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {

				ordemServico = (OrdemServico) colecaoOrdemServico.iterator()
						.next();

				// Inicio de codigo * Dados do Imovél
				if (ordemServico.getRegistroAtendimento().getImovel() != null) {
					sessao.setAttribute("imovel", ordemServico
							.getRegistroAtendimento().getImovel());
					String matriculaImovel = ordemServico
							.getRegistroAtendimento().getImovel().getId()
							.toString();
					retiradaActionForm.setMatriculaImovel("" + matriculaImovel);

					// Inscrição do Imovél

					String inscricaoImovel = ordemServico
							.getRegistroAtendimento().getImovel()
							.getInscricaoFormatada();

					retiradaActionForm.setMatriculaImovel(matriculaImovel);
					retiradaActionForm.setInscricaoImovel(inscricaoImovel);

				} else {
					retiradaActionForm.setMatriculaImovel("");
					retiradaActionForm.setInscricaoImovel("");
				}
				// Cliente Usuário

				/*if (ordemServico.getRegistroAtendimento().getCliente() != null) {
					String clienteUsuario = ordemServico
							.getRegistroAtendimento().getCliente().getNome();
					retiradaActionForm.setClienteUsuario(clienteUsuario);
				} else {
					retiradaActionForm.setClienteUsuario("");
				}*/

				// CPF & CNPJ
				/*if (ordemServico.getRegistroAtendimento().getCliente() != null
						&& ordemServico.getRegistroAtendimento().getCliente()
								.getCpfFormatado() != "") {

					String cpfCnpj = Util.formatarCPFApresentacao(ordemServico
							.getRegistroAtendimento().getCliente()
							.getCpfFormatado());
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
				} else {
					if (ordemServico.getRegistroAtendimento().getCliente() != null
							&& ordemServico.getRegistroAtendimento()
									.getCliente().getCnpjFormatado() != "") {
						String cpfCnpj = Util
								.formatarCPFApresentacao(ordemServico
										.getRegistroAtendimento().getCliente()
										.getCnpjFormatado());
						retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					} else {
						retiradaActionForm.setCpfCnpjCliente("");
					}
				}*/

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoAguaSituacao() != null) {
					// Situação da Ligação de Agua

					String situacaoLigacaoAgua = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoAguaSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				} else {
					retiradaActionForm.setSituacaoLigacaoAgua("");
				}
				// Situação da Ligação de Esgoto

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoEsgotoSituacao() != null) {
					String situacaoLigacaoEsgoto = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoEsgotoSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
					//
				} else {
					retiradaActionForm.setSituacaoLigacaoEsgoto("");
				}

			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				retiradaActionForm.setIdOrdemServico("");
				retiradaActionForm
						.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");
			}
			// -------Fim da Parte que trata do código quando o usuário tecla
			// enter

			if (ordemServico.getRegistroAtendimento().getImovel() != null) {

				String idImovel = ordemServico.getRegistroAtendimento()
						.getImovel().getId().toString();

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.tipoMedicao");

				Collection colecaoImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

					Imovel imovel = (Imovel) colecaoImovel.iterator().next();

					// Inicio de codigo * Dados do Imovél
					if (imovel.getHidrometroInstalacaoHistorico() != null) {
						String hidrometro = imovel
								.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumero();
						retiradaActionForm.setHidrometro(hidrometro);

						// Tipo Medição

						if (imovel.getHidrometroInstalacaoHistorico()
								.getMedicaoTipo() != null) {
							String medicaoTipo = imovel
									.getHidrometroInstalacaoHistorico()
									.getMedicaoTipo().getDescricao();
							retiradaActionForm.setMedicaoTipo(medicaoTipo);
						} else {
							retiradaActionForm.setMedicaoTipo("");
						}
						if (imovel.getHidrometroInstalacaoHistorico()
								.getDataRetirada() != null) {
							Date dataRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getDataRetirada();
							retiradaActionForm.setDataRetirada(Util
									.formatarData(dataRetirada));
						} else {
							retiradaActionForm.setDataRetirada("");
						}

						if (imovel.getHidrometroInstalacaoHistorico()
								.getNumeroLeituraRetirada() == 0) {
							Integer numeroLeituraRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getNumeroLeituraRetirada();
							retiradaActionForm
									.setNumeroLeitura(numeroLeituraRetirada
											.toString());
						} else {
							retiradaActionForm.setNumeroLeitura("");
						}

					}
				}

			}
		}

		return retorno;
	}

}
