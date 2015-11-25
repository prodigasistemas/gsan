package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction extends
		GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm form = (EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		LigacaoAgua ligacaoAgua = this.setDadosLigacaoAgua(form, fachada, form.getMatriculaImovel());
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(form.getMatriculaImovel()));
		imovel.setUltimaAlteracao(new Date());
		ligacaoAgua.setImovel(imovel);
		ligacaoAgua.setUltimaAlteracao(new Date());
		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(LigacaoAguaSituacao.LIGADO);
		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		ligacaoAgua.setId(imovel.getId());

		/*
		 * [UC0107] Registrar Transação
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LIGACAO_AGUA_SEM_RA_EFETUAR,
				imovel.getId(), imovel.getId(),
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// [UC0107] -Fim- Registrar Transação	
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
		
		if (form.getNumeroHidrometro() != null
				&& !form.getNumeroHidrometro().trim().equals("")) {
			
			hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
			hidrometroInstalacaoHistorico = this.setDadosHidrometroInstalacaoHistorico(
							hidrometroInstalacaoHistorico, form);
			
		} 
	
		registradorOperacao.registrarOperacao(ligacaoAgua);
		if(hidrometroInstalacaoHistorico != null){
			registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);
		}
		
			fachada.efetuarLigacaoAguaComInstalacaoHidrometroSemRA(
					imovel.getId(), ligacaoAgua, hidrometroInstalacaoHistorico);
			
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// Monta a página de sucesso
			montarPaginaSucesso(
					httpServletRequest,
					"Ligação de Água com Instalação de Hidrômetro sem RA efetuada com Sucesso",
					"Efetuar outra Ligação de Água com Instalação de Hidrômetro sem RA",
					"exibirEfetuarLigacaoAguaComInstalacaoHidrometroSemRAAction.do?menu=sim");
		}

		return retorno;
		// } else {
		// throw new ActionServletException("atencao.informe_campo", null,
		// "Ordem de Serviço");
		// }
	}

	// [SB0001] - Gerar Ligação de Água
	//
	// Método responsável por setar os dados da ligação de água
	// de acordo com os dados selecionados pelo usuário e pelas exigências do
	// caso de uso
	public LigacaoAgua setDadosLigacaoAgua(
			EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Fachada fachada, String matriculaImovel) {

		String diametroLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDiametroLigacao();
		String materialLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getMaterialLigacao();
		String idPerfilLigacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getPerfilLigacao();
		
		//verificar tarifa de consumo associada. 
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
				FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
				idPerfilLigacao));
		
		Collection pesquisa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if (!pesquisa.isEmpty()){
			Boolean existeTarifaIgual = false;
			Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
			Imovel imovelConsulta=null;
			
			if(matriculaImovel!=null && matriculaImovel!=""){
				imovelConsulta= fachada.pesquisarImovel(new Integer(matriculaImovel));
			}
			
			while(iteratorColecaoConsumoTarifa.hasNext()){
				ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
				if (consumoTarifa.getLigacaoAguaPerfil() != null){
					if(imovelConsulta != null){
						if (imovelConsulta.getConsumoTarifa().getId().intValue() ==  consumoTarifa.getId().intValue()){
							existeTarifaIgual = true;
						}
					}
				}
			}
			
			if (!existeTarifaIgual){
				throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Ligação");
			}
		}
		
		String ramalLocalInstalacao = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getRamalLocalInstalacao();
		String numeroLacre = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacre();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataLigacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataLigacao().equals("")) {
			Date data = Util
					.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataLigacao());
			ligacaoAgua.setDataLigacao(data);
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					" Data da Ligação");
		}

		if (diametroLigacao != null
				&& !diametroLigacao.equals("")
				&& !diametroLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
			ligacaoAguaDiametro.setId(new Integer(diametroLigacao));
			ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Diametro da Ligação");
		}

		if (materialLigacao != null
				&& !materialLigacao.equals("")
				&& !materialLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaMaterial ligacaoAguaMaterialMaterial = new LigacaoAguaMaterial();
			ligacaoAguaMaterialMaterial.setId(new Integer(materialLigacao));
			ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterialMaterial);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Material da Ligação");
		}

		if (idPerfilLigacao != null
				&& !idPerfilLigacao.equals("")
				&& !idPerfilLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
			ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
			ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
		} else {
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio", null,
					"Perfil da Ligação");
		}

		if (ramalLocalInstalacao != null
				&& !ramalLocalInstalacao.equals("")
				&& !ramalLocalInstalacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			RamalLocalInstalacao ramalLocal = new RamalLocalInstalacao();
			ramalLocal.setId(new Integer(ramalLocalInstalacao));

			ligacaoAgua.setRamalLocalInstalacao(ramalLocal);
		}

		if (numeroLacre != null && !numeroLacre.equals("")) {
			ligacaoAgua.setNumeroLacre(numeroLacre);
		}

		return ligacaoAgua;
	}

	// [SB0004] - Gerar Histórico de Instalação do Hidrômetro
	//
	// Método responsável por setar os dados do hidrômetro instalação histórico
	// de acordo com os dados selecionados pelo usuário e pelas exigências do
	// caso de uso
	public HidrometroInstalacaoHistorico setDadosHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			EfetuarLigacaoAguaComInstalacaoHidrometroSemRAActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();
		
		String numeroLacreHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroLacreHidrometro();
		
		// Número do Lacre
		if (numeroLacreHidrometro != null
				   	   && !numeroLacreHidrometro.trim().equals("")) {
					hidrometroInstalacaoHistorico.setNumeroLacre(numeroLacreHidrometro);
				} else {
						hidrometroInstalacaoHistorico.setNumeroLacre(null);
				}


		if (numeroHidrometro != null) {
			// Pesquisa o Hidrômetro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			} else if (!hidrometro.getHidrometroSituacao().getId().equals(
					HidrometroSituacao.DISPONIVEL)) {
				throw new ActionServletException(
						"atencao.hidrometro_situacao_indisponivel", null,
						hidrometro.getHidrometroSituacao().getDescricao());
			}

			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);

		}

		// Data instalação
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}

		// Medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instalação
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getSituacaoCavalete()));

		/*
		 * Campos opcionais
		 */

		// data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		// leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		// leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		// leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		// numero selo
		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instalação substituição
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		
		/*
		 *
		 * Adição dos sets para os atributos indicadorTrocaProtecao e indicadorTrocaRegistro.
		 */
		// indicador troca proteção
		hidrometroInstalacaoHistorico.setIndicadorTrocaProtecao(ConstantesSistema.NAO);
		// indicador troca registro
		hidrometroInstalacaoHistorico.setIndicadorTrocaRegistro(ConstantesSistema.NAO);

		return hidrometroInstalacaoHistorico;

	}
}
