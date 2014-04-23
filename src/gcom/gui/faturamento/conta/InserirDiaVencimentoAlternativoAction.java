package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroVencimentoAlternativo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirDiaVencimentoAlternativoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// Instância do formulário que está sendo utilizado
		InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm = (InformarVencimentoAlternativoActionForm) actionForm;

		Imovel imovel = (Imovel) sessao.getAttribute("imovel");
		VencimentoAlternativo vencimentoAlternativo = (VencimentoAlternativo) sessao
				.getAttribute("vencimentoAlternativo");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection vencimentosAlternativos = new ArrayList();
		
		if(vencimentoAlternativo == null || vencimentoAlternativo.equals(""))
		{
			FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);
			
			filtroVencimentoAlternativo
					.adicionarParametro(new ParametroSimples(
							FiltroVencimentoAlternativo.IMOVEL_ID, imovel
									.getId()));
	
			vencimentosAlternativos = fachada.pesquisar(
					filtroVencimentoAlternativo, VencimentoAlternativo.class
							.getName());
	
			if (vencimentosAlternativos != null
					&& !vencimentosAlternativos.isEmpty()) {
	
				vencimentoAlternativo = (VencimentoAlternativo) Util
						.retonarObjetoDeColecao(vencimentosAlternativos);
	
				Date dataVencimento = vencimentoAlternativo
						.getDataImplantacao();
	
				SimpleDateFormat dataFormato = new SimpleDateFormat(
						"dd/MM/yyyy");
				String dataVencimentoString = null;
				if (dataVencimento != null) {
					dataVencimentoString = dataFormato.format(dataVencimento);
				}
	
				informarVencimentoAlternativoActionForm
						.setDataAlteracaoVencimento(dataVencimentoString == null ? ""
								: "" + dataVencimentoString);
	
			}
		}else{
		
			Short numeroMesesMinimoVencimentoAlternativo = sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento();
		

			/*Calendar calendarVencimentoAlternativo = new GregorianCalendar();
			calendarVencimentoAlternativo.setTime(vencimentoAlternativo
					.getDataImplantacao());

			int mesVencimentoAlternativo = calendarVencimentoAlternativo
					.get(Calendar.MONTH);

			Calendar calendarAtual = new GregorianCalendar();

			int mesAtual = calendarAtual.get(Calendar.MONTH);*/
			
			//verifica  se usuario possue permissão especial para informar 
			//o vencimento alternativo antes do período válido
			boolean temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido = fachada
				.verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(usuarioLogado);
			
			Date dataImplantacao = vencimentoAlternativo.getDataImplantacao();
			Date dataAtual = new Date();

			int diferencaMes = Util.dataDiff(dataAtual,dataImplantacao);

			if (diferencaMes < numeroMesesMinimoVencimentoAlternativo &&
					!temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido) {
				throw new ActionServletException(
						"atencao.imovel.vencimento.alterado", null,
						numeroMesesMinimoVencimentoAlternativo.toString());
			} else {

				vencimentoAlternativo.setDateExclusao(new Date());
				vencimentoAlternativo.setUltimaAlteracao(new Date());
				fachada.atualizar(vencimentoAlternativo);
			}
		}

		Short novoDiaVencimento = null;
		novoDiaVencimento = new Short(informarVencimentoAlternativoActionForm
				.getNovoDiaVencimento());
		
		//Short indicadorVencimentoMesSeguinte = null;
		//indicadorVencimentoMesSeguinte = null ;
		

		if (imovel != null && !imovel.equals("")) {
			imovel.setDiaVencimento(novoDiaVencimento);
			imovel.setUltimaAlteracao(new Date());

			//Alterado por Rômulo Aurélio Data: 24/08/2010
			//Analista: Ana Cristina
			//Alteração: Retirada do layout da funcionalidade o indicador “Vencimento é para mês seguinte: SIM ou NÃO”.
			
			FaturamentoGrupo faturamentoGrupo = fachada.recuperaGrupoFaturamentoDoImovel(imovel.getId());
			
			if(faturamentoGrupo.getDiaVencimento() > novoDiaVencimento){
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_ATIVO);
			}else{
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
			fachada.atualizar(imovel);
		}

		VencimentoAlternativo vencimentoAlternativoInserir = new VencimentoAlternativo();

		if (imovel != null && !imovel.equals("")) {
			vencimentoAlternativoInserir.setImovel(imovel);
		}

		vencimentoAlternativoInserir.setDataImplantacao(new Date());
		vencimentoAlternativoInserir.setDateVencimento(novoDiaVencimento);
		vencimentoAlternativoInserir.setDateExclusao(null);
		vencimentoAlternativoInserir.setUltimaAlteracao(new Date());
		
				
    	// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO, vencimentoAlternativoInserir.getImovel().getId(),
				vencimentoAlternativoInserir.getImovel().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        
    		
		registradorOperacao.registrarOperacao(vencimentoAlternativoInserir);
		fachada.inserir(vencimentoAlternativoInserir);

		sessao.removeAttribute("colecaoNovoDiaVencimento");
		sessao.removeAttribute("imovel");
		sessao.removeAttribute("vencimentoAlternativo");
		montarPaginaSucesso(httpServletRequest,
				"Vencimento Alternativo para o imóvel "
						+ informarVencimentoAlternativoActionForm.getIdImovel()
						+ " inserido com sucesso.",
				"Informar outro Vencimento Alternativo",
				"exibirInformarVencimentoAlternativoAction.do?menu=sim");

		return retorno;

	}
	
}
