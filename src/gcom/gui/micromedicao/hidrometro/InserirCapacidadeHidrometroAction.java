package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirCapacidadeHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Capacidade de Hidrometro
	 * 
	 * [UC0515] Inserir Agência Capacidade Hidrometro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Thiago Tenório
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		InserirCapacidadeHidrometroActionForm inserirCapacidadeHidrometroActionForm = (InserirCapacidadeHidrometroActionForm) actionForm;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CAPACIDADE_HIDROMETRO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

//		String identificador = inserirCapacidadeHidrometroActionForm
//				.getIdentificador();
		String descricao = inserirCapacidadeHidrometroActionForm.getDescricao();
		String abreviatura = inserirCapacidadeHidrometroActionForm
				.getAbreviatura();
		String numMinimo = inserirCapacidadeHidrometroActionForm.getNumMinimo();
		String numMaximo = inserirCapacidadeHidrometroActionForm.getNumMaximo();
		String numOrdem = inserirCapacidadeHidrometroActionForm.getNumOrdem();
		String codigo = inserirCapacidadeHidrometroActionForm.getCodigo();

		HidrometroCapacidade hidrometroCapacidadeInserir = new HidrometroCapacidade();
		Collection colecaoPesquisa = null;

//		// O código da Capacidade do Hidrometro é obrigatório.
//		if (identificador == null || identificador.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"Identificador da capacidade de hidrômetro");
//		}

		// O código da Capacidade do Hidrometro é obrigatório.
		if (codigo == null || codigo.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Código da capacidade do hidrômetro");
		}

		// A descrição da Capacidade do Hidrômetro é obrigatório.
		if (descricao == null || descricao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Descrição da capacidade de hidrômetro");
		}

		// A descrição Abreviada da Capacidade do Hidrômetro é obrigatório.
		if (abreviatura != null && !abreviatura.equalsIgnoreCase("")) {
			hidrometroCapacidadeInserir.setDescricaoAbreviada(abreviatura);
		}

		// O numero minimo de digitos de leitura do hidrômetro é obrigatório.
		if (numMinimo == null || numMinimo.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Número mínimo de digitos de leitura do hidrômetro");
		}
		
		// O numero maximo de digitos de leitura do hidrômetro é obrigatório.
		if (numMaximo != null && !numMaximo.equalsIgnoreCase("")) {
			if (new Integer(numMaximo).intValue() < new Integer(numMinimo).intValue()) {
				throw new ActionServletException(
						"atencao.numero_minimo_nao_pode_ser_maior_que_numero_maximo",
						null, "Numero maximo de digitos de leitura do hidrômetro");
			} else {
				hidrometroCapacidadeInserir.setLeituraMaximo(new Short(numMaximo));
			}
		}

//		// O numero maximo de digitos de leitura do hidrômetro é obrigatório.
//		if (numMaximo == null || numMaximo.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"Número maximo de digitos de leitura do hidrômetro");
//		}

//		hidrometroCapacidadeInserir.setId(new Integer(identificador));
		hidrometroCapacidadeInserir.setCodigoHidrometroCapacidade(codigo);
		hidrometroCapacidadeInserir.setDescricao(descricao);
		hidrometroCapacidadeInserir.setDescricaoAbreviada(abreviatura);		
		hidrometroCapacidadeInserir.setLeituraMinimo(new Short(numMinimo));
		hidrometroCapacidadeInserir.setLeituraMaximo(new Short(numMaximo));
		hidrometroCapacidadeInserir.setNumeroOrdem(new Short(numOrdem));
		
		// Indicador de uso
		Short iu = 1;
		hidrometroCapacidadeInserir.setIndicadorUso(iu);

		// Ultima alteração
		hidrometroCapacidadeInserir.setUltimaAlteracao(new Date());

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.NUMERO_ORDEM,
				hidrometroCapacidadeInserir.getNumeroOrdem()));

		// Verificar existência do Número de ordem da capacidade do hidrômetro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Número de ordem da capacidade do hidrômetro já existe
			throw new ActionServletException(
					"atencao.pesquisa_numero_de_ordem_da_capacidade_do_hidrometro_ja_cadastrada",
					null, numOrdem);
		}

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
				hidrometroCapacidadeInserir.getCodigoHidrometroCapacidade()));

		// Verificar existência da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Capacidade de hidrometro já existe
			throw new ActionServletException(
					"atencao.pesquisa_capacidade_do_hidrometro_ja_cadastrada",
					null, codigo);
		} else {
			Integer idHidrometroCapacidade = null;

			idHidrometroCapacidade = fachada.inserirCapacidadeHidrometro(
					hidrometroCapacidadeInserir, usuarioLogado);

			montarPaginaSucesso(
					httpServletRequest,
					"Capacidade do Hidrômetro de código  "
							+ hidrometroCapacidadeInserir
									.getCodigoHidrometroCapacidade()
							+ " inserida com sucesso.",
					"Inserir outra Capacidade do Hidrômetro",
					"exibirInserirCapacidadeHidrometroAction.do?menu=sim",
					"exibirAtualizarCapacidadeHidrometroAction.do?inserir=sim&idRegistroAtualizacao="
							+ idHidrometroCapacidade,
					"Atualizar Capacidade do Hidrômetro Inserida");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
