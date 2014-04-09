package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * <p>
 * <b>[UC0190]</b> Inserir Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite inserir uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Tenório, Magno Gouveia
 * @since 07/02/2007, 23/08/2011
 */
public class InserirAnormalidadeLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

		InserirAnormalidadeLeituraActionForm form = (InserirAnormalidadeLeituraActionForm) actionForm;

		String descricao = form.getDescricao();
		String abreviatura = form.getAbreviatura();
		String indicadorRelativoHidrometro = form.getIndicadorRelativoHidrometro();
		String indicadorImovelSemHidrometro = form.getIndicadorImovelSemHidrometro();
		String usoRestritoSistema = form.getUsoRestritoSistema();
		String perdaTarifaSocial = form.getPerdaTarifaSocial();
		String osAutomatico = form.getOsAutomatico();
		String tipoServico = form.getTipoServico();
		String consumoLeituraNaoInformado = form.getConsumoLeituraNaoInformado();
		String consumoLeituraInformado = form.getConsumoLeituraInformado();
		String leituraLeituraNaoturaInformado = form.getLeituraLeituraNaoturaInformado();
		String leituraLeituraInformado = form.getLeituraLeituraInformado();
		String fatorSemLeitura = form.getNumeroFatorSemLeitura();
		String fatorComLeitura = form.getNumeroFatorComLeitura();
		String indicadorLeitura = form.getIndicadorLeitura();

		LeituraAnormalidade anormalidadeLeituraInserir = new LeituraAnormalidade();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		if (Util.validarNumeroMaiorQueZERO(form.getTipoServico())) {
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.ID_TIPO_SERVICO,
																		form.getTipoServico()));
		}

		if (form.getConsumoLeituraNaoInformado() != null) {

			Integer idConsumoLeituraNaoInformado = new Integer(form.getConsumoLeituraNaoInformado());

			if (idConsumoLeituraNaoInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura(null);
			} else {
				FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
				filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeConsumo.ID,
																							form.getConsumoLeituraNaoInformado().toString()));
				Collection colecaoConsumoLeituraNaoInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraNaoInformado.iterator().next());
			}
		}

		if (form.getConsumoLeituraInformado() != null) {

			Integer idConsumoLeituraInformado = new Integer(form.getConsumoLeituraInformado());

			if (idConsumoLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura(null);
			} else {
				FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
				filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeConsumo.ID,
																							form.getConsumoLeituraInformado().toString()));
				Collection colecaoConsumoLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraInformado.iterator().next());
			}
		}

		if (form.getLeituraLeituraNaoturaInformado() != null) {

			Integer idLeituraLeituraNaoturaInformado = new Integer(form.getLeituraLeituraNaoturaInformado());

			if (idLeituraLeituraNaoturaInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura(null);
			} else {
				FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
				filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeLeitura.ID,
																							form.getLeituraLeituraNaoturaInformado().toString()));
				Collection colecaoLeituraLeituraNaoturaInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

				// setando
				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraNaoturaInformado.iterator().next());
			}
		}

		if (form.getLeituraLeituraInformado() != null) {

			Integer idLeituraLeituraInformado = new Integer(form.getLeituraLeituraInformado());

			if (idLeituraLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura(null);
			} else {
				FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
				filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeLeitura.ID,
																							form.getLeituraLeituraInformado().toString()));
				Collection colecaoLeituraLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraInformado.iterator().next());
			}
		}

		// A Descrição é obrigatório.
		if (descricao == null || descricao.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Descrição");
		}

		// A Abreviatura é obrigatório.
		if (abreviatura == null || abreviatura.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Abreviatura");
		}

		// A Anormalidade Relativa a Hidrômetro é obrigatório.
		if (indicadorRelativoHidrometro == null || indicadorRelativoHidrometro.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Relativa a Hidrômetro");
		}

		// Anormalidade Aceita para Ligação sem Hidrômetro é obrigatório.
		if (indicadorImovelSemHidrometro == null || indicadorImovelSemHidrometro.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Aceita para Ligação sem Hidrômetro");
		}
		// Anormalidade de Uso Restrito do Sistema é obrigatório.
		if (usoRestritoSistema == null || usoRestritoSistema.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade de Uso Restrito do Sistema");
		}

		// Anormalidade Acarreta Perda Tarifa Social é obrigatório.
		if (perdaTarifaSocial == null || perdaTarifaSocial.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Acarreta Perda Tarifa Social");
		}
		// Anormalidade Emite OS Automática é obrigatório.
		if (osAutomatico == null || osAutomatico.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Emite OS Automática");
		}

		anormalidadeLeituraInserir.setDescricao(descricao);
		anormalidadeLeituraInserir.setDescricaoAbreviada(abreviatura);
		anormalidadeLeituraInserir.setIndicadorRelativoHidrometro(new Short(indicadorRelativoHidrometro));
		anormalidadeLeituraInserir.setIndicadorImovelSemHidrometro(new Short(indicadorImovelSemHidrometro));
		anormalidadeLeituraInserir.setIndicadorEmissaoOrdemServico(new Short(usoRestritoSistema));
		anormalidadeLeituraInserir.setIndicadorPerdaTarifaSocial(new Short(perdaTarifaSocial));
		anormalidadeLeituraInserir.setIndicadorSistema(new Short(usoRestritoSistema));
		anormalidadeLeituraInserir.setIndicadorEmissaoOrdemServico(new Short(osAutomatico));

		if (tipoServico != null && !tipoServico.equals("")) {
			ServicoTipo servicoTipo = new ServicoTipo();
			servicoTipo.setId(new Integer(tipoServico));
			anormalidadeLeituraInserir.setServicoTipo(servicoTipo);
		} else {
			anormalidadeLeituraInserir.setServicoTipo(null);
		}

		LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitua = new LeituraAnormalidadeConsumo();

		leituraAnormalidadeConsumoSemLeitua.setId(new Integer(consumoLeituraNaoInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura(leituraAnormalidadeConsumoSemLeitua);

		LeituraAnormalidadeConsumo leituraAnormalidadeConsumo = new LeituraAnormalidadeConsumo();

		leituraAnormalidadeConsumo.setId(new Integer(consumoLeituraInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura(leituraAnormalidadeConsumo);

		LeituraAnormalidadeLeitura leituraAnormalidadeNaoLeitura = new LeituraAnormalidadeLeitura();

		leituraAnormalidadeNaoLeitura.setId(new Integer(leituraLeituraNaoturaInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura(leituraAnormalidadeNaoLeitura);

		LeituraAnormalidadeLeitura leituraAnormalidadeLeitura = new LeituraAnormalidadeLeitura();

		leituraAnormalidadeLeitura.setId(new Integer(leituraLeituraInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura(leituraAnormalidadeLeitura);

		// Ultima alteração
		anormalidadeLeituraInserir.setUltimaAlteracao(new Date());
		// Indicador de uso
		Short iu = 1;
		anormalidadeLeituraInserir.setIndicadorUso(iu);

		anormalidadeLeituraInserir.setNumeroFatorComLeitura(Util.formatarMoedaRealparaBigDecimal(fatorComLeitura));
		anormalidadeLeituraInserir.setNumeroFatorSemLeitura(Util.formatarMoedaRealparaBigDecimal(fatorSemLeitura));
		anormalidadeLeituraInserir.setIndicadorLeitura(new Short(indicadorLeitura));
		
		if (!Util.verificarNaoVazio(form.getNumeroVezesSuspendeLeitura())) {
			form.setNumeroVezesSuspendeLeitura("0");
		}
		anormalidadeLeituraInserir.setNumeroVezesSuspendeLeitura(new Integer(form.getNumeroVezesSuspendeLeitura()));

		if (!Util.verificarNaoVazio(form.getNumeroMesesLeituraSuspensa())) {
			form.setNumeroMesesLeituraSuspensa("0");
		}
		anormalidadeLeituraInserir.setNumeroMesesLeituraSuspensa(new Integer(form.getNumeroMesesLeituraSuspensa()));

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.DESCRICAO,
																			anormalidadeLeituraInserir.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(	"atencao.leitura_anormalidade_ja_cadastrada",
												null,
												anormalidadeLeituraInserir.getDescricao());
		} else {
			Integer idAnormalidadeLeitura = null;

			idAnormalidadeLeitura = fachada.inserirAnormalidadeLeitura(anormalidadeLeituraInserir, usuarioLogado);

			montarPaginaSucesso(httpServletRequest, "Anormalidade de Leitura de código  "
					+ anormalidadeLeituraInserir.getId() + " inserida com sucesso.", "Inserir outra Anormalidade de Leitura", "exibirInserirAnormalidadeLeituraAction.do?menu=sim", "exibirAtualizarAnormalidadeLeituraAction.do?idRegistroAtualizacao="
					+ idAnormalidadeLeitura, "Atualizar Anormalidade de Leitura");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}
}
