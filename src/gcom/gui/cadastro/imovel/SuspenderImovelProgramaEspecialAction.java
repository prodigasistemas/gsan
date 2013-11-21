package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SuspenderImovelProgramaEspecialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirImovelProgramaEspecialActionForm form = (InserirImovelProgramaEspecialActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Obter Parametros do sistema
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ImovelProgramaEspecial imovelProgramaEspecial = new ImovelProgramaEspecial();

		if (form.getMatriculaImovel() != null
				&& !form.getMatriculaImovel().equals("")) {

			FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();

			filtroImovelProgramaEspecial
					.adicionarParametro(new ParametroSimples(
							FiltroImovelProgramaEspecial.IMOVEL_ID, form
									.getMatriculaImovel()));
			filtroImovelProgramaEspecial
			.adicionarParametro(new ParametroNulo(
					FiltroImovelProgramaEspecial.DATA_SUSPENSAO));
			
			filtroImovelProgramaEspecial
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.IMOVEL);
			filtroImovelProgramaEspecial
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.IMOVEL_FATURAMENTO_GRUPO);
			
			Collection<ImovelProgramaEspecial> colecaoImovelProgramaEspecial = 
				fachada.pesquisar(filtroImovelProgramaEspecial,
					ImovelProgramaEspecial.class.getName());
			
			imovelProgramaEspecial = 
				(ImovelProgramaEspecial) Util.retonarObjetoDeColecao(colecaoImovelProgramaEspecial);		
		}
		
		Date dataAtual = new Date();

		if (form.getMesAnoSuspensaoPrograma() != null
				&& !form.getMesAnoSuspensaoPrograma().equals("")) {
			Integer mesAnoSuspencaoPrograma = Util
					.formatarMesAnoComBarraParaAnoMes(form
							.getMesAnoSuspensaoPrograma());
			Integer mesAnoAtual = Util.formataAnoMes(dataAtual);

			if (mesAnoSuspencaoPrograma.compareTo(mesAnoAtual) > 0) {
				throw new ActionServletException(
						"atencao.mes.ano.suspensao.anterior.atual");
			}
			imovelProgramaEspecial
					.setMesAnoSaidaPrograma(mesAnoSuspencaoPrograma);
		}

		if (sistemaParametro.getClienteResponsavelProgramaEspecial() == null) {
			throw new ActionServletException(
					"atencao.nao.existe.cliente.reposponsavel.cadastrado");
		}
		if (sistemaParametro.getPerfilProgramaEspecial() == null) {
			throw new ActionServletException(
					"atencao.nao.existe.perfil.programa.cadastrado");
		}
		
		// [FS0004] Valida dados da suspensao do imovel no programa especial
		//fachada.validarDadosSuspensaoImovelProgramaEspecial(imovelProgramaEspecial);
		
		Short formaSuspensao = ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR;
			
		fachada.suspenderImovelEmProgramaEspecial(imovelProgramaEspecial,usuarioLogado,formaSuspensao);

		montarPaginaSucesso(httpServletRequest, "Matrícula do imóvel "
				+ imovelProgramaEspecial.getImovel().getId()
				+ " suspensa com sucesso no programa especial.",
				"Suspender outro imóvel no programa.",
				"exibirSuspenderImovelProgramaEspecialAction.do?menu=sim");

		return retorno;

	}
}
