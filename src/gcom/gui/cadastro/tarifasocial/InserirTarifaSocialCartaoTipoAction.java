package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirTarifaSocialCartaoTipoAction extends GcomAction {
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

		// Obtém o action form
		TarifaSocialCartaoTipoActionForm tarifaSocialCartaoTipoActionForm = (TarifaSocialCartaoTipoActionForm) actionForm;

		// Seta o retorno para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Seta o indicador de uso como ativo
		Short indicadorDeUsoAtivo = ConstantesSistema.INDICADOR_USO_ATIVO;

		Short numeroMaximoMeses = null;

		if (!tarifaSocialCartaoTipoActionForm.getNumeroMaximoMeses().equals("")) {
			numeroMaximoMeses = new Short(tarifaSocialCartaoTipoActionForm
					.getNumeroMaximoMeses());
			
			if (numeroMaximoMeses.intValue() == 0
					|| numeroMaximoMeses.intValue() > 99) {
				throw new ActionServletException(
						"atencao.numero.meses.fora.faixa.permitido");
			}
			
		}

		// Cria o objeto com os valores setados
		TarifaSocialCartaoTipo tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo(
				tarifaSocialCartaoTipoActionForm.getDescricao(),
				tarifaSocialCartaoTipoActionForm.getDescricaoAbreviada(),
				new Short(tarifaSocialCartaoTipoActionForm.getValidade()),
				numeroMaximoMeses, indicadorDeUsoAtivo, new Date());

		// Inseri o objeto
		fachada.inserir(tarifaSocialCartaoTipo);

		// Método utilizado para montar a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Tipo de Cartão da Tarifa Social de código "
						+ tarifaSocialCartaoTipo.getId()
						+ " inserido com sucesso.",
				"Inserir outro Tipo de Cartão da Tarifa Social",
				"exibirInserirTarifaSocialCartaoTipoAction.do",
				"exibirAtualizarTarifaSocialCartaoTipoAction.do?idRegistroAtualizacao="+tarifaSocialCartaoTipo.getId(),
				"Atualizar Tipo de Cartão da Tarifa Social Inserido");

		return retorno;
	}
}
