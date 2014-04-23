package gcom.gui.micromedicao;

import gcom.batch.micromedicao.TarefaBatchConsistirLeiturasCalcularConsumos;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;

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
public class ConsistirLeiturasCalcularConsumosAction extends GcomAction {
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

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("consistirLeiturasCalcularConsumos");

		// Obtém a sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		//ConsistirLeiturasCalcularConsumosActionForm consistirLeiturasCalcularConsumosActionForm = (ConsistirLeiturasCalcularConsumosActionForm) sessao
		//		.getAttribute("actionForm");

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		// Criação das coleções
		//Collection imoveis = null;

		// Cria o objeto faturamento grupo
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		SistemaParametro sistemaParametro = new SistemaParametro();

		// SETA VALORES PARA REALIZAÇÃO DE TESTES

		// sistemaParametro.setAnoMesFaturamento(new
		// Integer(200601).intValue());
		// sistemaParametro.setMesesMediaConsumo(new Short("6"));
		//
		// faturamentoGrupo.setId(new Integer(22));

		// Collection colecaoRotas = new ArrayList();
		//        
		// Rota rota = new Rota();
		// rota.setId(1342);
		// rota.setIndicadorAjusteConsumo(new Short("0"));

		// SETA VALORES PARA REALIZAÇÃO DE TESTES
		sistemaParametro.setAnoMesFaturamento(new Integer(200607).intValue());
		sistemaParametro.setMesesMediaConsumo(new Short("6"));

		faturamentoGrupo.setId(new Integer(20));

		Collection colecaoRotas = new ArrayList();

		Rota rota = new Rota();
		rota.setId(1127);
		rota.setIndicadorAjusteConsumo(new Short("0"));
		colecaoRotas.add(rota);

		Rota rota1 = new Rota();
		rota1.setId(1152);
		rota1.setIndicadorAjusteConsumo(new Short("0"));
		colecaoRotas.add(rota1);

		Rota rota2 = new Rota();
		rota2.setId(1153);
		rota2.setIndicadorAjusteConsumo(new Short("0"));
		colecaoRotas.add(rota2);

		// faturamentoGrupo.setId(new
		// Integer(consistirLeiturasCalcularConsumosActionForm.getIdFaturamentoGrupo()));
		// Chama o método da fachada para consistir leituras e calcular consumos
		// -------------------------------------------------------------------------------
		// fachada.consistirLeiturasCalcularConsumos(faturamentoGrupo,sistemaParametro,colecaoRotas);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		TarefaBatchConsistirLeiturasCalcularConsumos batch = new TarefaBatchConsistirLeiturasCalcularConsumos(
				usuarioLogado, 1);
		batch.addParametro("faturamentoGrupo", faturamentoGrupo);
		batch.addParametro("sistemaParametro", sistemaParametro);
		batch.addParametro("colecaoRota", colecaoRotas);
		batch.agendarTarefaBatch();
		// -------------------------------------------------------------------------------

		return retorno;
	}
}
