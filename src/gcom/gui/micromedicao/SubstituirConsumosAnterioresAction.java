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
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class SubstituirConsumosAnterioresAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirDadosAnalise");

		// LeituraConsumoActionForm leituraConsumoActionForm =
		// (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
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
			 *  Alterando o c�lculo da m�dia
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
				 *  Alterando o c�lculo da m�dia
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
				
				e.printStackTrace();
			}
		}

		httpServletRequest.setAttribute("sucesso",
				"Consumos substitu�dos com sucesso.");

		if (sessao.getAttribute("peloMenu") != null) {
			retorno = actionMapping.findForward("telaSucesso");

			montarPaginaSucesso(httpServletRequest,
					"Consumos anteriores substitu�dos do im�vel " + idImovel
							+ " com sucesso.",
					"Realizar outra Substitui��o de consumos anteriores",
					"exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true");
		}

		return retorno;
	}

}
