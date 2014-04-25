package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class SubstituirConsumosAnterioresAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("exibirDadosAnalise");

		// LeituraConsumoActionForm leituraConsumoActionForm =
		// (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// String codigoImovel =
		// leituraConsumoActionForm.getIdImovelSubstituirConsumo();

		Collection colecaoImovelMicromedicao = (Collection) sessao
				.getAttribute("colecaoConsumoHistorico");

		Iterator iteratorImovelMicromedicao = colecaoImovelMicromedicao
				.iterator();
		String idImovel = "";

		while (iteratorImovelMicromedicao.hasNext()) {

			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iteratorImovelMicromedicao
					.next();
			if (sessao.getAttribute("idImovelSelecionado") == null) {
				idImovel = imovelMicromedicao.getImovel().getId().toString();
			} else {
				idImovel = ((Integer) sessao
						.getAttribute("idImovelSelecionado")).toString();
			}
			if (imovelMicromedicao.getConsumoHistorico() != null) {
				ConsumoHistorico consumoHistorico = imovelMicromedicao
						.getConsumoHistorico();
				String consumoFaturadoMesAgua = (String) httpServletRequest
						.getParameter("agua"
								+ imovelMicromedicao.getConsumoHistorico()
										.getId().toString());
				if (!consumoFaturadoMesAgua.trim().equalsIgnoreCase(
						consumoHistorico.getNumeroConsumoCalculoMedia()
								.toString())) {
					consumoHistorico.setNumeroConsumoCalculoMedia(new Integer(
							consumoFaturadoMesAgua));
					fachada.atualizarConsumosAnteriores(consumoHistorico);
				}
			}
			if (imovelMicromedicao.getConsumoHistoricoEsgoto() != null) {
				ConsumoHistorico consumoHistoricoEsgoto = imovelMicromedicao
						.getConsumoHistoricoEsgoto();

				String consumoFaturadoMesEsgoto = (String) httpServletRequest
						.getParameter("esgoto"
								+ imovelMicromedicao
										.getConsumoHistoricoEsgoto().getId()
										.toString());
				if (!consumoFaturadoMesEsgoto.trim().equalsIgnoreCase(
						consumoHistoricoEsgoto.getNumeroConsumoCalculoMedia()
								.toString())) {
					consumoHistoricoEsgoto
							.setNumeroConsumoCalculoMedia(new Integer(
									consumoFaturadoMesEsgoto));
					fachada.atualizarConsumosAnteriores(consumoHistoricoEsgoto);
				}
			}

			Imovel imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			MedicaoTipo medicaoTipo = new MedicaoTipo();
			medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);

			/**
			 * TODO : COSANPA Alterando o cálculo da média
			 */
			try {
				boolean houveIntslacaoHidrometro = fachada
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicaoTipo);

				int[] consumoMedioHidrometroAgua = fachada
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								imovelMicromedicao.getAnoMesGrupoFaturamento(),
								medicaoTipo.getId(), houveIntslacaoHidrometro);

				/**
				 * TODO : COSANPA Alterando o cálculo da média
				 */
				medicaoTipo.setId(MedicaoTipo.POCO);
				houveIntslacaoHidrometro = fachada
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicaoTipo);

				int[] consumoMedioHidrometroEsgoto = fachada
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								imovelMicromedicao.getAnoMesGrupoFaturamento(),
								medicaoTipo.getId(), houveIntslacaoHidrometro);

				fachada.atualizarConsumosMedio(new Integer(idImovel),
						imovelMicromedicao.getAnoMesGrupoFaturamento(),
						consumoMedioHidrometroAgua[0],
						consumoMedioHidrometroEsgoto[0]);

			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		httpServletRequest.setAttribute("sucesso",
				"Consumos substituídos com sucesso.");

		if (sessao.getAttribute("peloMenu") != null) {
			retorno = actionMapping.findForward("telaSucesso");

			montarPaginaSucesso(httpServletRequest,
					"Consumos anteriores substituídos do imóvel " + idImovel
							+ " com sucesso.",
					"Realizar outra Substituição de consumos anteriores",
					"exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true");
		}

		return retorno;
	}

}
