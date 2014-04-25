package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

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
public class InserirHidrometroAction extends GcomAction {
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
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		
//		 Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_HIDROMETRO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_HIDROMETRO_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------


		Fachada fachada = Fachada.getInstancia();

		// Define a ação de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Filtro para obter o local de armazenagem ativo de id informado
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

		filtroHidrometroLocalArmazenagem
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalArmazenagem.ID, new Integer(
								hidrometroActionForm.getIdLocalArmazenagem()),
						ParametroSimples.CONECTOR_AND));
		filtroHidrometroLocalArmazenagem
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
				filtroHidrometroLocalArmazenagem,
				HidrometroLocalArmazenagem.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoHidrometroLocalArmazenagem == null
				|| colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.hidrometro_local_armazenagem.inexistente");
		}

		// Cria o objeto classe metrológica e seta o id
		HidrometroClasseMetrologica hidrometroClasseMetrologica = new HidrometroClasseMetrologica();

		hidrometroClasseMetrologica.setId(new Integer(hidrometroActionForm
				.getIdHidrometroClasseMetrologica()));

		// Cria o objeto hidrômetro marca e seta o id
		HidrometroMarca hidrometroMarca = new HidrometroMarca();

		hidrometroMarca.setId(new Integer(hidrometroActionForm
				.getIdHidrometroMarca()));
		
		/**
		 * [FS0004]- Verificar Preenchimento dos campos
		 * Caso 3
		 */
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMarca.ID,hidrometroMarca.getId().toString()));
		
		
		Collection colecaoHidrometroMarcaBase = fachada.pesquisar(
				filtroHidrometroMarca,HidrometroMarca.class.getName());
		
		HidrometroMarca hidrometroMarcaBase = (HidrometroMarca) 
				colecaoHidrometroMarcaBase.iterator().next(); 
		
		if(!hidrometroMarcaBase.getCodigoHidrometroMarca().
				equalsIgnoreCase(hidrometroActionForm.getFixo().substring(3))){
			throw new ActionServletException("atencao.marca_incompativel_numero_fixo");
		}
		
		
		String fixo = hidrometroActionForm.getFixo();
		/*
		/**
		 * [FS0008]-Montar ano de fabricacao
		
		String anoFabricacaoForm = hidrometroActionForm.getAnoFabricacao();
		
		String aux1 = fixo.substring(2,3);
		
		Integer aux2= new Integer(aux1);
		
		if (anoFabricacaoForm.equalsIgnoreCase("") || anoFabricacaoForm == null) {
			throw new ActionServletException("");
		} else {
			
			if (aux2 >= 85) {
				char[] anoFabricacaoChar = anoFabricacaoForm.toCharArray();
				anoFabricacaoChar[0] = '1';
				anoFabricacaoChar[1] = '9';
				anoFabricacaoForm = (new String(anoFabricacaoChar) + aux2);
			} else if (aux2 >= 00) {
				char[] anoFabricacaoChar = anoFabricacaoForm.toCharArray();
				anoFabricacaoChar[0] = '2';
				anoFabricacaoChar[1] = '0';
				anoFabricacaoForm = (new String(anoFabricacaoChar) + aux2);
			}

		}*/
		
		
		/**
		 * [FS0004]- Verificar Preenchimento dos campos
		 * Caso 2
		 */
		//Cria o objeto hidrômetro capacidade e seta o id
		HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();

		hidrometroCapacidade.setId(new Integer(hidrometroActionForm
				.getIdHidrometroCapacidade()));
		
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.ID,hidrometroCapacidade.getId().toString()));
			
		Collection colecaoHidrometroCapacidadeBase = fachada.pesquisar(
				filtroHidrometroCapacidade,HidrometroCapacidade.class.getName());
		
		HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade) 
				colecaoHidrometroCapacidadeBase.iterator().next(); 
		
		if(!hidrometroCapacidadeBase.getCodigoHidrometroCapacidade().
				equalsIgnoreCase(hidrometroActionForm.getFixo().substring(0,1))){
			throw new ActionServletException("atencao.capacidade_incompativel_numero_fixo");
		}
		

		// Cria o objeto hidrômetro diâmetro e seta o id
		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();

		hidrometroDiametro.setId(new Integer(hidrometroActionForm
				.getIdHidrometroDiametro()));

		// Cria o objeto hidrômetro tipo e seta o id
		HidrometroTipo hidrometroTipo = new HidrometroTipo();

		hidrometroTipo.setId(new Integer(hidrometroActionForm
				.getIdHidrometroTipo()));
		
		// Cria o objeto hidrômetro tipo e seta o id
		HidrometroRelojoaria hidrometroRelojoaria = null;

		if(hidrometroActionForm
				.getIdHidrometroRelojoaria() != null && Integer.parseInt(hidrometroActionForm
						.getIdHidrometroRelojoaria()) > ConstantesSistema.NUMERO_NAO_INFORMADO){
			
			hidrometroRelojoaria = new HidrometroRelojoaria();
			
			hidrometroRelojoaria.setId(new Integer(hidrometroActionForm
					.getIdHidrometroRelojoaria()));
			
		}
		

		// Cria o objeto hidrômetro local armazenagem e seta o id
		HidrometroLocalArmazenagem hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();

		hidrometroLocalArmazenagem.setId(new Integer(hidrometroActionForm
				.getIdLocalArmazenagem()));

		// Cria o objeto hidrômetro situacao e seta o id
		HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();

		hidrometroSituacao.setId(HidrometroSituacao.DISPONIVEL);

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataAquisicao = null;
		try {
			dataAquisicao = formatoData.parse(hidrometroActionForm
					.getDataAquisicao());
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		Date dataAquisicaoAnterior = null;
		try {
			dataAquisicaoAnterior = formatoData.parse("01/01/1985");
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}
		Calendar dataAtual = new GregorianCalendar();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		// caso a data de aquisição seja menor que a data atual
		if (dataAquisicao.after(new Date())) {
			throw new ActionServletException(
					"atencao.data.aquisicao.nao.superior.data.corrente");
		}
		// caso a data de aquisição seja menor que 01/01/1985
		if (dataAquisicao.before(dataAquisicaoAnterior)) {
			throw new ActionServletException(
					"atencao.data.aquisicao.nao.inferior.1985");

		}
		Integer anoFabricacao = new Integer(hidrometroActionForm
				.getAnoFabricacao());
		// caso o ano de fabricação seja maior que o atual
		if (anoFabricacao > anoAtual) {
			throw new ActionServletException(
					"atencao.ano.fabricacao.nao.superior.data.corrente");

		}
		// caso o ano de fabricação seja menor que 1985
		if (anoFabricacao < 1985) {
			throw new ActionServletException(
					"atencao.ano.fabricacao.nao.inferior.1985");

		}

		Integer anoDataAquisicao = Util.getAno(dataAquisicao);
		// caso a data de aquisição seja menor que o ano fabricação
		if (anoDataAquisicao < anoFabricacao) {
			throw new ActionServletException(
					"atencao.ano.aquisicao.menor.ano.fabricacao");

		}
		
		BigDecimal vazaoTransicao = null;
		//Vazao Transacao
		if (hidrometroActionForm.getVazaoTransicao() != null
				&& !"".equals(hidrometroActionForm.getVazaoTransicao() ) ) {
			vazaoTransicao =  Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoTransicao() ) ;
		}
		
		BigDecimal vazaoNominal = null;
		//Vazao Nominal
		if (hidrometroActionForm.getVazaoNominal() != null
				&& !"".equals(hidrometroActionForm.getVazaoNominal() ) ) {
			vazaoNominal = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoNominal() );
		}
		
		BigDecimal vazaoMinima = null;
		//Vazao Minima
		if (hidrometroActionForm.getVazaoMinima() != null
				&& !"".equals(hidrometroActionForm.getVazaoMinima() ) ) {
			vazaoMinima = Util.formatarMoedaRealparaBigDecimal( hidrometroActionForm.getVazaoMinima() );
		}
		
		Integer notaFiscal = null;
		if ( hidrometroActionForm.getNotaFiscal() != null
				&& !"".equals(hidrometroActionForm.getNotaFiscal() ) ) {
			notaFiscal = new Integer( hidrometroActionForm.getNotaFiscal() ) ;
		}
		
		Short tempoGarantiaAnos = null;
		if ( hidrometroActionForm.getTempoGarantiaAnos() != null
				&& !"".equals(hidrometroActionForm.getTempoGarantiaAnos() ) ) {
			tempoGarantiaAnos = new Short( hidrometroActionForm.getTempoGarantiaAnos());
		}

		// Cria o objeto hidromêtro
		Hidrometro hidrometro = null;

		try {
			hidrometro = new Hidrometro(
					null,
					// numero
					formatoData.parse(hidrometroActionForm.getDataAquisicao()),
					// dataAquisicao
					new Short(hidrometroActionForm.getAnoFabricacao()),
					// anoFabricacao
					new Short(hidrometroActionForm.getIndicadorMacromedidor()),
					// indicadorMacromedidor
					null,
					// dataUltimaRevisao
					null,
					// dataBaixa
					new Integer("0"),
					// numeroLeituraAcumulada
					new Short(hidrometroActionForm.getIdNumeroDigitosLeitura()),
					// numeroDigitosLeitura
					new Date(),
					// ultimaAlteracao
					hidrometroTipo,
					// hidrômetroTipo
					hidrometroSituacao,
					// hidrometroSituacao
					hidrometroMarca,
					// hidrometroMarca
					hidrometroCapacidade,
					// hidrometroCapacidade
					null,
					// hidrometroMotivoBaixa
					hidrometroLocalArmazenagem,
					// hidrometroLocalArmazenagem
					hidrometroClasseMetrologica,
					// hidrometroClasseMetrologica
					hidrometroDiametro,
					//hidrometro Relojoaria
					hidrometroRelojoaria,
					//vazao transicao
					vazaoTransicao,
					//vazao nominal
					vazaoNominal,
					//vazao minima
					vazaoMinima,
					//nota fiscal
					notaFiscal,
					//tempo garantia em anos
					tempoGarantiaAnos);
			// hidrometroDiametro
		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		
		// Obtém a faixa inicial
		Integer faixaInicial = new Integer(hidrometroActionForm
				.getFaixaInicial());
		// Obtém a faixa final
		Integer faixaFinal = new Integer(hidrometroActionForm.getFaixaFinal());

		Integer intervalo = new Integer((faixaFinal.intValue() - faixaInicial
				.intValue()) + 1);
		
		//------------ REGISTRAR TRANSAÇÃO ----------------
        hidrometro.setOperacaoEfetuada(operacaoEfetuada);
        hidrometro.adicionarUsuario(usuarioLogado, 
    				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    		registradorOperacao.registrarOperacao(hidrometro);
        //------------ REGISTRAR TRANSAÇÃO ----------------

		// Inseri hidrômetro
		fachada.inserirHidrometro(hidrometro, fixo, faixaInicial, faixaFinal);

		// Método utilizado para montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, 
				intervalo.toString() + " Hidrômetro(s) inserido(s) com sucesso.",
				"Inserir outro Hidrômetro", "exibirInserirHidrometroAction.do?menu=sim");

		// Remove objetos da sessão
		sessao.removeAttribute("HidrometroActionForm");
		sessao.removeAttribute("colecaoIntervalo");
		sessao.removeAttribute("colecaoHidrometroClasseMetrologica");
		sessao.removeAttribute("colecaoHidrometroMarca");
		sessao.removeAttribute("colecaoHidrometroDiametro");
		sessao.removeAttribute("colecaoHidrometroCapacidade");
		sessao.removeAttribute("colecaoHidrometroTipo");

		return retorno;
	}
}
