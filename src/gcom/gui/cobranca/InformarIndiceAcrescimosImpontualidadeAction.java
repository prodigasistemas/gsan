package gcom.gui.cobranca;

import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 18/09/2007
 */
public class InformarIndiceAcrescimosImpontualidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		IndiceAcrescimosImpontualidadeForm indiceAcrescimosImpontualidadeForm = (IndiceAcrescimosImpontualidadeForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		// Validar campos
		if (indiceAcrescimosImpontualidadeForm.getPercentualMulta() != null) {
			BigDecimal percentualMulta = new BigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualMulta().replace(",", "."));
			if (percentualMulta.compareTo(new BigDecimal("0.0000")) <= 0
					|| percentualMulta.compareTo(new BigDecimal("100.0000")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Multa");
			}
		}
		
		if (indiceAcrescimosImpontualidadeForm.getPercentualJurosMora() != null) {
			BigDecimal percentualJurosMora = new BigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualJurosMora().replace(",", "."));
			if (percentualJurosMora.compareTo(new BigDecimal("0.0000")) <= 0
					|| percentualJurosMora.compareTo(new BigDecimal("100.0000")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Juros Mora");
			}
		}
		
		if (indiceAcrescimosImpontualidadeForm.getPercentualLimiteJuros() != null) {
			BigDecimal percentualLimiteJuros = Util
					.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualLimiteJuros());
			if (percentualLimiteJuros.compareTo(new BigDecimal("0.00")) <= 0
					|| percentualLimiteJuros.compareTo(new BigDecimal("999.00")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Limite dos Juros");
			}
		}

		if (indiceAcrescimosImpontualidadeForm.getPercentualLimiteMulta() != null) {
			BigDecimal percentualLimiteMulta = Util
					.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualLimiteMulta());
			if (percentualLimiteMulta.compareTo(new BigDecimal("0.00")) <= 0
					|| percentualLimiteMulta.compareTo(new BigDecimal("999.00")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Limite da Multa");
			}
		}
		
		Fachada fachada = Fachada.getInstancia();

		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
		Integer anoMesReferencia = null;
		anoMesReferencia = Util
				.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeForm
						.getMesAnoReferencia());
		filtroIndicesAcrescimosImpontualidade
				.adicionarParametro(new ParametroSimples(
						FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
						anoMesReferencia));

		Collection colecaoIndicesAcrescimosImpontualidade = fachada.pesquisar(
				filtroIndicesAcrescimosImpontualidade,
				IndicesAcrescimosImpontualidade.class.getName());

		String mensagem = "";

		IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade = null;

		if (colecaoIndicesAcrescimosImpontualidade != null
				&& !colecaoIndicesAcrescimosImpontualidade.isEmpty()) {
			
			indicesAcrescimosImpontualidade = (IndicesAcrescimosImpontualidade) Util
					.retonarObjetoDeColecao(colecaoIndicesAcrescimosImpontualidade);
			
			indicesAcrescimosImpontualidade
					.setPercentualMulta(new BigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualMulta().replace(",", ".")));
			indicesAcrescimosImpontualidade
					.setPercentualJurosMora(new BigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualJurosMora().replace(",", ".")));

			String valorComPonto = indiceAcrescimosImpontualidadeForm
					.getFatorCorrecao().replace(",", ".");
			indicesAcrescimosImpontualidade
					.setFatorAtualizacaoMonetaria(new BigDecimal(valorComPonto));
			
			// Percentual Limite dos Juros
			indicesAcrescimosImpontualidade
					.setPercentualLimiteJuros(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteJuros()));
			
			// Percentual Limite da Multa
			indicesAcrescimosImpontualidade
					.setPercentualLimiteMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteMulta()));

			// Indicador Juros Mensal
			indicesAcrescimosImpontualidade.setIndicadorJurosMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorJurosMensal()));

			// Indicador Multa Mensal
			indicesAcrescimosImpontualidade.setIndicadorMultaMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorMultaMensal()));
			
			indicesAcrescimosImpontualidade.setUltimaAlteracao(new Date());

			fachada.atualizar(indicesAcrescimosImpontualidade);
			mensagem = "atualizada";
			
		} else {
			indicesAcrescimosImpontualidade = new IndicesAcrescimosImpontualidade();
			indicesAcrescimosImpontualidade
					.setAnoMesReferencia(Util
							.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeForm
									.getMesAnoReferencia()));
			indicesAcrescimosImpontualidade
					.setPercentualMulta(new BigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualMulta().replace(",", ".")));
			indicesAcrescimosImpontualidade
					.setPercentualJurosMora(new BigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualJurosMora().replace(",", ".")));

			String valorComPonto = indiceAcrescimosImpontualidadeForm
					.getFatorCorrecao().replace(",", ".");
			indicesAcrescimosImpontualidade
					.setFatorAtualizacaoMonetaria(new BigDecimal(valorComPonto));
			
			// Percentual Limite dos Juros
			indicesAcrescimosImpontualidade
					.setPercentualLimiteJuros(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteJuros()));
			
			// Percentual Limite da Multa
			indicesAcrescimosImpontualidade
					.setPercentualLimiteMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteMulta()));

			// Indicador Juros Mensal
			indicesAcrescimosImpontualidade.setIndicadorJurosMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorJurosMensal()));

			// Indicador Multa Mensal
			indicesAcrescimosImpontualidade.setIndicadorMultaMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorMultaMensal()));
			
			indicesAcrescimosImpontualidade.setUltimaAlteracao(new Date());

			fachada.inserir(indicesAcrescimosImpontualidade);

			mensagem = "inserida";
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INFORMAR_INDICES_ACRESCIMOS_IMPONTUALIDADE,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_COBRANCA_ACAO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		indicesAcrescimosImpontualidade.setOperacaoEfetuada(operacaoEfetuada);
		indicesAcrescimosImpontualidade.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(indicesAcrescimosImpontualidade);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		montarPaginaSucesso(httpServletRequest,
				"Índices dos acréscimos de impontualidade de "
						+ indiceAcrescimosImpontualidadeForm
								.getMesAnoReferencia() + " " + mensagem
						+ " com sucesso.", "", "");
		return retorno;
	}

}
