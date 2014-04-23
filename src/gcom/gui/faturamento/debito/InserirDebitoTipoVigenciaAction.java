package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
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
 * Inserção de Valor de Tipo Débito
 * 
 * @author Josenildo Neves
 * @date 11/02/2010
 */
public class InserirDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirDebitoTipoVigenciaActionForm debitoTipoVigenciaActionForm = (InserirDebitoTipoVigenciaActionForm) actionForm;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// Filtra Tipo de Debito
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, debitoTipoVigenciaActionForm
						.getTipoDebito()));
		//filtroDebitoTipo
				//.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		// Recupera Tipo de Serviço
		Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
				filtroDebitoTipo, DebitoTipo.class.getName());
		
		// [FS0001] ? Verificar tipo de Débito.
		if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
			throw new ActionServletException("atencao.tipo_Debito_inexistente");

		}

		DebitoTipo debitoTipo = new DebitoTipo();

		debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();

		// Seta Objeto Servico Cobrança Valor
		DebitoTipoVigencia debitoTipoVigencia = setDebitoTipoVigencia(debitoTipoVigenciaActionForm);

		// FS0008 - Verificar pré-existência de vigência para o débito tipo
		Fachada.getInstancia().verificarExistenciaVigenciaDebito(debitoTipoVigenciaActionForm.getDataVigenciaInicial(), 
				debitoTipoVigenciaActionForm.getDataVigenciaFinal(), new Integer(debitoTipoVigenciaActionForm.getTipoDebito()),
				new Integer("1"));
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_DEBITO_TIPO_VIGENCIA, debitoTipoVigencia.getDebitoTipo().getId(),
				debitoTipoVigencia.getDebitoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		debitoTipoVigencia.setDebitoTipo(debitoTipo);
		registradorOperacao.registrarOperacao(debitoTipoVigencia);

		// [FS0001] Verificar Serviço Geral Débito.
		Fachada.getInstancia()
				.inserir(debitoTipoVigencia);

		// [FS008] Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Débito Tipo Vigência "
				+ debitoTipo.getDescricao() + " inserido com sucesso.",
				"Inserir outro Débito Tipo Vigência",
				"exibirInserirDebitoTipoVigenciaAction.do?menu=sim");
		return retorno;
	}

	/**
	 * Preenche objeto com informações vindas da tela
	 * 
	 * @author Josenildo Neves
	 * @date 11/02/2010
	 * 
	 * @param form
	 * @return debitoTipoVigencia
	 */
	private DebitoTipoVigencia setDebitoTipoVigencia(
			InserirDebitoTipoVigenciaActionForm form) {		
		
		DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();

		// Tipo do Serviço
		DebitoTipo tipoDebito = new DebitoTipo();

		tipoDebito.setId(new Integer(form.getTipoDebito()));
		debitoTipoVigencia.setDebitoTipo(tipoDebito);

		// Valor do Serviço
		String valorSemPontos = form.getValorDebito().replace(".", "");
		debitoTipoVigencia.setValorDebito(new BigDecimal(valorSemPontos.replace(",", ".")));
		
		//Vigência do valor do Debito
		//[FS0004] – Validar data da vigência inicial
		if (form.getDataVigenciaInicial() != null && !form.getDataVigenciaInicial().equals("")){
			if (!Util.validarDiaMesAno(form.getDataVigenciaInicial())) {
				debitoTipoVigencia.setDataVigenciaInicial(Util.converteStringParaDate(form.getDataVigenciaInicial()));
				//[FS0005] – Validar data da vigência final
				if (!Util.validarDiaMesAno(form.getDataVigenciaFinal())) {
					debitoTipoVigencia.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
					if(Util.compararData(debitoTipoVigencia.getDataVigenciaInicial(),debitoTipoVigencia.getDataVigenciaFinal()) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
		}else{
			debitoTipoVigencia.setDataVigenciaInicial(null);
			debitoTipoVigencia.setDataVigenciaFinal(null);
			
		}
		
		debitoTipoVigencia.setUltimaAlteracao(new Date());

		return debitoTipoVigencia;

	}

}
