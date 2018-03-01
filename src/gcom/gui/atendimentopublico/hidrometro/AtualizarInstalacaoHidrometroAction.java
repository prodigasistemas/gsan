package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarInstalacaoHidrometroAction extends GcomAction {

	private AtualizarInstalacaoHidrometroActionForm form;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		form = (AtualizarInstalacaoHidrometroActionForm) actionForm;
		HttpSession sessao = request.getSession(false);

		Imovel imovel = null;
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		if (form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")) {
			OrdemServico ordemServico = getFachada().recuperaOSPorId(Util.converterStringParaInteger(form.getIdOrdemServico()));

			getFachada().validarExibirAtualizarInstalacaoHidrometro(ordemServico, false);
			imovel = ordemServico.getRegistroAtendimento().getImovel();

		} else {
			imovel = pesquisarImovel();
		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

		Integer medicaoTipo = Integer.parseInt(form.getMedicaoTipo());

		if (MedicaoTipo.LIGACAO_AGUA.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
		} else if (MedicaoTipo.POCO.equals(medicaoTipo)) {
			hidrometroInstalacaoHistorico = imovel.getHidrometroInstalacaoHistorico();

			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(form.getTipoPoco()));

			imovel.setPocoTipo(pocoTipo);
		}

		getFachada().validarExistenciaHidrometro(imovel, medicaoTipo);

		form.setFormValues(hidrometroInstalacaoHistorico);
		hidrometroInstalacaoHistorico.setUsuarioInstalacao((Usuario) sessao.getAttribute("usuarioLogado"));

		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInstalacao = (Date) formatter.parse(form.getDataInstalacao());
			if (dataInstalacao.after(new Date())) {
				throw new ActionServletException("atencao.data_instalacao_maior_data_corrente");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		getFachada().atualizarInstalacaoHidrometro(imovel, Integer.parseInt(form.getMedicaoTipo()), usuario);

		montarPaginaSucesso(request, 
				"Atualização do Histórico da Instalação de Hidrômetro do imóvel " + imovel.getId() + " efetuada com sucesso.",
				"Efetuar nova Atualização do Histórico da Instalação de Hidrômetro", 
				"exibirAtualizarInstalacaoHidrometroAction.do?menu=sim");

		return retorno;
	}

	@SuppressWarnings("unchecked")
	private Imovel pesquisarImovel() {
		String idImovel = form.getIdImovel();
		String inscricao = getFachada().pesquisarInscricaoImovel(new Integer(idImovel));

		form.setMatriculaImovel(idImovel);
		form.setInscricaoImovel(inscricao);

		Filtro filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.HIDROMETRO_POCO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO);

		Collection<Imovel> colecaoImovel = getFachada().pesquisar(filtro, Imovel.class.getName());

		return (Imovel) colecaoImovel.iterator().next();
	}
}
