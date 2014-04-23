package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("manterRegistroAtendimento");
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm = 
			(FiltrarRegistroAtendimentoActionForm) actionForm;
		
		boolean parametroInformado = false;

		// Validação solicitada por Leonardo Vieira sem caso de uso.
		// Executor: Marcio Roberto
		if ((filtrarRegistroAtendimentoActionForm.getNumeroRA() == null ||
			filtrarRegistroAtendimentoActionForm.getNumeroRA().equals("")) &&
            (filtrarRegistroAtendimentoActionForm.getMatriculaImovel() == null ||
            filtrarRegistroAtendimentoActionForm.getMatriculaImovel().equals(""))&&
            (filtrarRegistroAtendimentoActionForm.getNumeroRAManual() == null ||
            filtrarRegistroAtendimentoActionForm.getNumeroRAManual().equals("")) &&
            (filtrarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento() == null ||
            filtrarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento().equals(""))) {

			String dtAtendimentoIni = filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial();
			String dtAtendimentoFinal = filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal();

			String dtEncerramentoIni = filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial();
			String dtEncerramentoFinal = filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal();

			String dtTramitacaoIni = filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoInicial();
			String dtTramitacaoFinal = filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoFinal();
			
			if((dtAtendimentoIni==null || dtAtendimentoIni.equals("")) 
					&& (dtAtendimentoFinal==null || dtAtendimentoFinal.equals(""))){
				if((dtEncerramentoIni==null || dtEncerramentoIni.equals(""))
						&& (dtEncerramentoFinal==null || dtEncerramentoFinal.equals(""))){
					if((dtTramitacaoIni==null || dtTramitacaoIni.equals("")) 
							&& (dtTramitacaoFinal==null || dtTramitacaoFinal.equals(""))){
						throw new ActionServletException("atencao.filtrar_intervalo_datas_obrigatorio");
					}
				}
			}
					
			if(dtAtendimentoIni != null && !dtAtendimentoIni.equals("")){
				if(dtAtendimentoFinal==null || dtAtendimentoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"atendimento");
				}else{
					Date ini = Util.converteStringParaDate(dtAtendimentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtAtendimentoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
					}
				}
			}
			
			if(dtEncerramentoIni != null && !dtEncerramentoIni.equals("")){
				if(dtEncerramentoFinal==null || dtEncerramentoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"encerramento");
				}else{
					Date ini = Util.converteStringParaDate(dtEncerramentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtEncerramentoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
					}
				}
			}
			
			if(dtTramitacaoIni != null && !dtTramitacaoIni.equals("")){
				if(dtTramitacaoFinal==null || dtTramitacaoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"tramitação");
				}else{
					Date ini = Util.converteStringParaDate(dtTramitacaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtTramitacaoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"tramitação");
					}
				}
			}
		}
		
		RegistroAtendimento ra = new RegistroAtendimento();
		FiltrarRegistroAtendimentoHelper filtroRA = new FiltrarRegistroAtendimentoHelper();
		// Numero RA
		if (filtrarRegistroAtendimentoActionForm.getNumeroRA() != null &&
				!filtrarRegistroAtendimentoActionForm.getNumeroRA().equals("")) {
			ra.setId(new Integer(filtrarRegistroAtendimentoActionForm.getNumeroRA()));
			parametroInformado = true;
		}
		
		//Numero Protocolo Atendimento
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
		if (filtrarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento() != null &&
			!filtrarRegistroAtendimentoActionForm.getNumeroProtocoloAtendimento().equals("")) {
			
			registroAtendimentoSolicitante = new RegistroAtendimentoSolicitante();
			
			registroAtendimentoSolicitante.setNumeroProtocoloAtendimento(filtrarRegistroAtendimentoActionForm
			.getNumeroProtocoloAtendimento());
			
			parametroInformado = true;
		}
		
		//Número Manual
		if (filtrarRegistroAtendimentoActionForm.getNumeroRAManual() != null &&
				!filtrarRegistroAtendimentoActionForm.getNumeroRAManual().equals("")) {
			
			ra.setManual(Util.obterNumeracaoRAManual(filtrarRegistroAtendimentoActionForm.getNumeroRAManual()));
			parametroInformado = true;
		}
		
		// Quantidade RA Reiteradas
		Integer qtdeRAReiteradasInicial = null;
		Integer qtdeRAReiteradasFinal = null;
		
		if (filtrarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial() != null &&
				!filtrarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial().equals("")) {
			qtdeRAReiteradasInicial = new Integer(filtrarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasInicial());
			qtdeRAReiteradasFinal = new Integer(filtrarRegistroAtendimentoActionForm.getQuantidadeRAReiteradasFinal());
			parametroInformado = true;
		}
		
		// Imovel
		if (filtrarRegistroAtendimentoActionForm.getMatriculaImovel() != null &&
				!filtrarRegistroAtendimentoActionForm.getMatriculaImovel().equals("")) {
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(filtrarRegistroAtendimentoActionForm.getMatriculaImovel()));
			ra.setImovel(imovel);
			parametroInformado = true;
		}
		// Situação
		if (filtrarRegistroAtendimentoActionForm.getSituacao() != null &&
				!filtrarRegistroAtendimentoActionForm.getSituacao().equals("")) {
			ra.setCodigoSituacao(new Short(filtrarRegistroAtendimentoActionForm.getSituacao()));		
			parametroInformado = true;
		}
		
		// RAs com coordenadas sem logradouro identificado
		if (filtrarRegistroAtendimentoActionForm.getIndicCoordSemLogr() != null &&
				!filtrarRegistroAtendimentoActionForm.getIndicCoordSemLogr().equals("")) {
			ra.setIndicadorCoordenadaSemLogradouro(new Short(filtrarRegistroAtendimentoActionForm.getIndicCoordSemLogr()));		
			parametroInformado = true;
		}
		
		//Tipo Solicitação
		Collection<Integer> colecaoSolicitacaoTipoSolicitacao = new ArrayList();
		if (filtrarRegistroAtendimentoActionForm.getTipoSolicitacao() != null &&
				filtrarRegistroAtendimentoActionForm.getTipoSolicitacao().length > 0) {
			String[] tipoSolicitacao = filtrarRegistroAtendimentoActionForm.getTipoSolicitacao();
			for (int i = 0; i < tipoSolicitacao.length; i++) {
				if (new Integer(tipoSolicitacao[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecaoSolicitacaoTipoSolicitacao.add(new Integer(tipoSolicitacao[i]));
					// passar a coleção de especificação por parâmetro		
					parametroInformado = true;
				}
			}
		}
		
		// Tipo Especificação
		Collection<Integer> colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		if (colecaoSolicitacaoTipoSolicitacao.size() < 2 && 
				filtrarRegistroAtendimentoActionForm.getEspecificacao() != null &&
				filtrarRegistroAtendimentoActionForm.getEspecificacao().length > 0) {
			String[] tipoSolicitacaoEspecificacao = filtrarRegistroAtendimentoActionForm.getEspecificacao();
			for (int i = 0; i < tipoSolicitacaoEspecificacao.length; i++) {
				if (new Integer(tipoSolicitacaoEspecificacao[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecaoSolicitacaoTipoEspecificacao.add(new Integer(tipoSolicitacaoEspecificacao[i]));
					// passar a coleção de especificação por parâmetro		
					parametroInformado = true;
				}
			}
		}
		
		// Perfil Imovel
		Collection<Integer> colecaoPerfilImovel = null;
		if (filtrarRegistroAtendimentoActionForm.getColecaoPerfilImovel() != null) {
			colecaoPerfilImovel = new ArrayList<Integer>();
			
			for (String id : filtrarRegistroAtendimentoActionForm.getColecaoPerfilImovel()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoPerfilImovel.add(new Integer(id));
				}
			}
			if (colecaoPerfilImovel.size() == 0) colecaoPerfilImovel = null;
		}
		
		// Atendimento Motivo Encerramento
		Collection<Integer> colecaoAtendimentoMotivoEncerramento = null;
		if (filtrarRegistroAtendimentoActionForm.getColecaoAtendimentoMotivoEncerramento() != null) {
			colecaoAtendimentoMotivoEncerramento = new ArrayList<Integer>();
			
			for (String id : filtrarRegistroAtendimentoActionForm.getColecaoAtendimentoMotivoEncerramento()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoAtendimentoMotivoEncerramento.add(new Integer(id));
				}
			}
			if (colecaoAtendimentoMotivoEncerramento.size() == 0) colecaoAtendimentoMotivoEncerramento = null;
		}
		
		// Data de Atendimento
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		if (filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial() != null &&
				!filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial().equals("")) {
			dataAtendimentoInicial = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoInicial());
			dataAtendimentoInicial = Util.formatarDataInicial(dataAtendimentoInicial);
			
			dataAtendimentoFinal = null;
			if (filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal() != null &&
					!filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal().equals("")) {
				dataAtendimentoFinal = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoAtendimentoFinal());
				dataAtendimentoFinal = Util.adaptarDataFinalComparacaoBetween(dataAtendimentoFinal);
			} else {
				dataAtendimentoFinal = new Date();
				dataAtendimentoFinal = Util.formatarDataFinal(dataAtendimentoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataAtendimentoInicial, dataAtendimentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			// passar as datas de atendimento por parâmetro
			parametroInformado = true;
		}  else {

			if (filtrarRegistroAtendimentoActionForm
					.getPeriodoAtendimentoFinal() != null
					&& !filtrarRegistroAtendimentoActionForm
							.getPeriodoAtendimentoFinal().equals("")) {

				dataAtendimentoFinal = Util
						.converteStringParaDate(filtrarRegistroAtendimentoActionForm
								.getPeriodoAtendimentoFinal());
				dataAtendimentoFinal = Util
						.formatarDataFinal(dataAtendimentoFinal);

				dataAtendimentoInicial = Util
						.converteStringParaDate("01/01/1900");
				dataAtendimentoInicial = Util
						.formatarDataInicial(dataAtendimentoInicial);

				// passar as datas de atendimento por parâmetro
				parametroInformado = true;
			}

		}

		// Data de Encerramento
		Date dataEncerramentoInicial = null;
		Date dataEncerramentoFinal = null;
		if (filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial() != null &&
				!filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial().equals("")){
			
			dataEncerramentoInicial = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoInicial());
			dataEncerramentoInicial = Util.formatarDataInicial(dataEncerramentoInicial);
			
			dataEncerramentoFinal = null;
			if (filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal() != null &&
					!filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal().equals("") ) {
				dataEncerramentoFinal = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoEncerramentoFinal());
				dataEncerramentoFinal = Util.adaptarDataFinalComparacaoBetween(dataEncerramentoFinal);
			} else {
				dataEncerramentoFinal = new Date();
				dataEncerramentoFinal = Util.formatarDataInicial(dataEncerramentoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataEncerramentoInicial, dataEncerramentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			// passar as datas de encerramento por parâmetro
			parametroInformado = true;
		} else {

			if (filtrarRegistroAtendimentoActionForm
					.getPeriodoEncerramentoFinal() != null
					&& !filtrarRegistroAtendimentoActionForm
							.getPeriodoEncerramentoFinal().equals("")) {

				dataEncerramentoFinal = Util
						.converteStringParaDate(filtrarRegistroAtendimentoActionForm
								.getPeriodoEncerramentoFinal());
				dataAtendimentoFinal = Util
						.formatarDataFinal(dataEncerramentoFinal);

				dataEncerramentoInicial = Util
						.converteStringParaDate("01/01/1900");
				dataEncerramentoInicial = Util
						.formatarDataInicial(dataAtendimentoInicial);

				// passar as datas de atendimento por parâmetro
				parametroInformado = true;
			}

		}
		
		// Unidade de Atendimento
		UnidadeOrganizacional unidadeAtendimento = null;
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId().equals("")) {
			unidadeAtendimento = new UnidadeOrganizacional();
			unidadeAtendimento.setId(new Integer(filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId()));
			// passar coleção de unidades por parâmetro
			parametroInformado = true;
		}
		
		//Registro Atendimento Unidade
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;
		Usuario usuario = null;
		if (filtrarRegistroAtendimentoActionForm.getLoginUsuario() != null &&
			!filtrarRegistroAtendimentoActionForm.getLoginUsuario().equals("")) {
			
			usuario = new Usuario();
			
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, filtrarRegistroAtendimentoActionForm.getLoginUsuario()));
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				
				Usuario usuarioColecao = colecaoUsuario.iterator().next();
				usuario.setLogin(usuarioColecao.getLogin());
				usuario.setId(new Integer(usuarioColecao.getId()));
						
						registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
						registroAtendimentoUnidade.setUsuario(usuario);
						
						// passar coleção de registro atendimento unidades(usuário) por parâmetro
						parametroInformado = true;
			}
			else{
				throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
			}
			
		}
		
		// Unidade de Atual
		UnidadeOrganizacional unidadeAtual = null;
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAtualId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAtualId().equals("")) {
			unidadeAtual = new UnidadeOrganizacional();
			unidadeAtual.setId(new Integer(filtrarRegistroAtendimentoActionForm.getUnidadeAtualId()));
			// passar coleção de unidades por parâmetro			
			parametroInformado = true;
		}
		
		//Unidade de Anterior
		UnidadeOrganizacional unidadeAnterior = null;
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId().equals("")) {
			unidadeAnterior = new UnidadeOrganizacional();
			unidadeAnterior.setId(new Integer(filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId()));
			// passar coleção de unidades por parâmetro			
			parametroInformado = true;
		}
		
		
		// Unidade de Atual
		UnidadeOrganizacional unidadeSuperior = null;
		if (filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId().equals("")) {
			unidadeSuperior = new UnidadeOrganizacional();
			unidadeSuperior.setId(new Integer(filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId()));
			// passar coleção de unidades por parâmetro			
			parametroInformado = true;
		}
		// Município
		String municipioId = null;
		if (filtrarRegistroAtendimentoActionForm.getMunicipioId() != null &&
				!filtrarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
			municipioId = filtrarRegistroAtendimentoActionForm.getMunicipioId(); 
			parametroInformado = true;
		}
		// Bairro
		String bairroId = null;
		String bairroCodigo = null;
		if (filtrarRegistroAtendimentoActionForm.getBairroCodigo() != null &&
			!filtrarRegistroAtendimentoActionForm.getBairroCodigo().equals("")) {
			
			//[FS009] Verificar informação do município
			if (filtrarRegistroAtendimentoActionForm.getMunicipioId() == null ||
				filtrarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
				
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
			
			bairroCodigo = filtrarRegistroAtendimentoActionForm.getBairroCodigo();
			
			if (filtrarRegistroAtendimentoActionForm.getBairroId() != null &&
				!filtrarRegistroAtendimentoActionForm.getBairroId().equals("")){
				
				bairroId = filtrarRegistroAtendimentoActionForm.getBairroId();
			}
			
			parametroInformado = true;
		}
		// Bairro Área
		if (new Integer(filtrarRegistroAtendimentoActionForm.getAreaBairroId()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			BairroArea bairroArea = new BairroArea();
			bairroArea.setId(new Integer(filtrarRegistroAtendimentoActionForm.getAreaBairroId()));
			ra.setBairroArea(bairroArea);
			parametroInformado = true;
		}
		// Logradouro
		String logradouroId = null;
		if (filtrarRegistroAtendimentoActionForm.getLogradouroId() != null &&
				!filtrarRegistroAtendimentoActionForm.getLogradouroId().equals("")) {
			logradouroId = filtrarRegistroAtendimentoActionForm.getLogradouroId();
			parametroInformado = true;
		}
		
		//colocado por Flávio a pedido de Cláudio Lira - 17/03/2008
		Date dataTramitacaoInicial = null;
		Date dataTramitacaoFinal = null;
		if (filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoInicial() != null &&
				!filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoInicial().equals("")){
			
			dataTramitacaoInicial = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoInicial());
			dataTramitacaoInicial = Util.formatarDataInicial(dataTramitacaoInicial);
			
			dataTramitacaoFinal = null;
			if (filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoFinal() != null &&
					!filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoFinal().equals("") ) {
				dataTramitacaoFinal = Util.converteStringParaDate(filtrarRegistroAtendimentoActionForm.getPeriodoTramitacaoFinal());
				dataTramitacaoFinal = Util.adaptarDataFinalComparacaoBetween(dataTramitacaoFinal);
			} else {
				dataTramitacaoFinal = new Date();
				dataTramitacaoFinal = Util.formatarDataInicial(dataTramitacaoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataTramitacaoInicial, dataTramitacaoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			// passar as datas de encerramento por parâmetro
			parametroInformado = true;
		} else {

			if (filtrarRegistroAtendimentoActionForm
					.getPeriodoTramitacaoFinal() != null
					&& !filtrarRegistroAtendimentoActionForm
							.getPeriodoTramitacaoFinal().equals("")) {

				dataEncerramentoFinal = Util
						.converteStringParaDate(filtrarRegistroAtendimentoActionForm
								.getPeriodoTramitacaoFinal());
				dataAtendimentoFinal = Util
						.formatarDataFinal(dataEncerramentoFinal);

				dataTramitacaoInicial = Util
						.converteStringParaDate("01/01/1900");
				dataTramitacaoInicial = Util
						.formatarDataInicial(dataAtendimentoInicial);

				// passar as datas de atendimento por parâmetro
				parametroInformado = true;
			}

		}
		
		// Filtra Registro Atendimento
		if (parametroInformado) {
			Collection<RegistroAtendimento> colecaoRegistroAtendimento = new ArrayList();
			
			filtroRA.setRegistroAtendimento(ra);
			filtroRA.setUnidadeAtendimento(unidadeAtendimento);
			filtroRA.setUnidadeAtual(unidadeAtual);
			filtroRA.setUnidadeSuperior(unidadeSuperior);
			filtroRA.setDataAtendimentoInicial(dataAtendimentoInicial);
			filtroRA.setDataAtendimentoFinal(dataAtendimentoFinal);
			filtroRA.setDataEncerramentoInicial(dataEncerramentoInicial);
			filtroRA.setDataEncerramentoFinal(dataEncerramentoFinal);
			filtroRA.setDataTramitacaoInicial(dataTramitacaoInicial);
			filtroRA.setDataTramitacaoFinal(dataTramitacaoFinal);
			filtroRA.setColecaoTipoSolicitacaoEspecificacao(colecaoSolicitacaoTipoEspecificacao);
			filtroRA.setColecaoTipoSolicitacao(colecaoSolicitacaoTipoSolicitacao);
			filtroRA.setColecaoPerfilImovel(colecaoPerfilImovel);
			filtroRA.setColecaoAtendimentoMotivoEncerramento(colecaoAtendimentoMotivoEncerramento);			
			filtroRA.setMunicipioId(municipioId);
			filtroRA.setBairroId(bairroId);
			filtroRA.setBairroCodigo(bairroCodigo);
			filtroRA.setLogradouroId(logradouroId);
			filtroRA.setQuantidadeRAReiteradasIncial(qtdeRAReiteradasInicial);
			filtroRA.setQuantidadeRAReiteradasFinal(qtdeRAReiteradasFinal);
			
			filtroRA.setRegistroAtendimentoUnidade(registroAtendimentoUnidade);
			filtroRA.setNumeroPagina(ConstantesSistema.NUMERO_NAO_INFORMADO);
			
			filtroRA.setRegistroAtendimentoSolicitante(registroAtendimentoSolicitante);
			filtroRA.setUnidadeAnterior(unidadeAnterior);
			
			sessao.setAttribute("filtroRA", filtroRA);
			
			int totalRegistros = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Integer tamanho = null;
			if(httpServletRequest.getParameter("page.offset") != null){
				tamanho = (Integer) sessao.getAttribute("totalRegistros");
			}else{
				tamanho = fachada.filtrarRegistroAtendimentoTamanho(filtroRA);
//				Collection colecaoRAHelperCompleta = fachada.filtrarRegistroAtendimento(filtroRA);
//				tamanho = colecaoRAHelperCompleta.size();

				// sessao.setAttribute("colecaoCompleta", colecaoRAHelperCompleta);
				
			}
			
			if (tamanho == null || tamanho == 0) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
			} else if(tamanho.intValue() == 1){

				retorno = actionMapping.findForward("consultarRegistroAtendimento");
				
				colecaoRegistroAtendimento = fachada.filtrarRegistroAtendimento(filtroRA);
				Collection colecaoRAHelper = loadColecaoRAHelper(colecaoRegistroAtendimento);
				
				RAFiltroHelper raFiltroHelper = (RAFiltroHelper) Util.retonarObjetoDeColecao(colecaoRAHelper); 
				sessao.setAttribute("numeroOS", raFiltroHelper.getRegistroAtendimento().getId());
			
				sessao.removeAttribute("colecaoCompleta");
				sessao.removeAttribute("colecaoRAHelper");
				
				
			} else {

				totalRegistros = tamanho.intValue();
				
				retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);				
				
				int numeroPaginasPesquisa = 
					((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")).intValue();
				
				filtroRA.setNumeroPagina(numeroPaginasPesquisa);
				
				colecaoRegistroAtendimento = fachada.filtrarRegistroAtendimento(filtroRA);
				Collection colecaoRAHelper = loadColecaoRAHelper(colecaoRegistroAtendimento);
				
				sessao.removeAttribute("numeroOS");
				sessao.setAttribute("filtroRA", filtroRA);
				sessao.setAttribute("numeroPaginasPesquisa", numeroPaginasPesquisa);
				sessao.setAttribute("page.offset", httpServletRequest.getAttribute("page.offset")); 
				sessao.setAttribute("colecaoRAHelper", colecaoRAHelper);
			}			

			
			
			
			
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}

		obterDadosReiteracaoRa(filtrarRegistroAtendimentoActionForm.getNumeroRA(),fachada,sessao);
		
		
		return retorno;
	}
	
	/**
	 * Carrega coleção de registro atendimento, situação abreviada e unidade atual no 
	 * objeto facilitador 
	 *
	 * @author Leonardo Regis
	 * @date 10/08/2006
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
	private Collection loadColecaoRAHelper(Collection<RegistroAtendimento> colecaoRegistroAtendimento) {
		Fachada fachada = Fachada.getInstancia();
		Collection colecaoRAHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoRAHelper situacao = null;
		RAFiltroHelper helper = null;
		for (Iterator iter = colecaoRegistroAtendimento.iterator(); iter.hasNext();) {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
			unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
			helper = new RAFiltroHelper();
			helper.setRegistroAtendimento(registroAtendimento);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			if(registroAtendimento.getImovel()!= null){
				helper.setPerfilImovel(registroAtendimento.getImovel().getImovelPerfil());				
			}			
			
			//Caso o RegistroAtendimento for urgente, indicadorUrgencia = 1, senao = 2.
//			if(this.getFachada().verificarRegistroAtendimentoUrgencia(registroAtendimento.getId()) > 0){
//				helper.setIndicadorUrgencia(1);
//			}else{
//				helper.setIndicadorUrgencia(2);
//			}
			verificarUrgencia(helper);
			
			colecaoRAHelper.add(helper);
		}
		return colecaoRAHelper;
	}	
	

	/**
	 * @author Vivianne Sousa
	 * @date 16/05/2011
	 */	
	private void obterDadosReiteracaoRa(String numeroRA, 
			Fachada fachada,HttpSession sessao) {
		
		sessao.removeAttribute("colecaoDadosReiteracao");
		
		if(numeroRA != null && !numeroRA.equals("")){
			Collection colecaoDadosReiteracao = fachada.pesquisarDadosReiteracaoRA(
					new Integer(numeroRA));

			if(colecaoDadosReiteracao != null && !colecaoDadosReiteracao.isEmpty()){
				sessao.setAttribute("colecaoDadosReiteracao",colecaoDadosReiteracao);
			}
		}
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 18/05/2011
	 */
	private void verificarUrgencia(RAFiltroHelper helper) {
		
		Integer indicadorUrgencia = 2;
		
		if( helper.getRegistroAtendimento() != null){
			
			Integer qtdeRAUrgencia = this.getFachada().verificarRegistroAtendimentoUrgencia(helper.getRegistroAtendimento().getId());
			Short qtdeReiteracoes = this.getFachada().pesquisarQtdeReiteracaoRA(helper.getRegistroAtendimento().getId());
			
			StringBuilder hint1 = new StringBuilder();
			
			if(qtdeRAUrgencia.intValue() > 0 && qtdeReiteracoes != null &&  qtdeReiteracoes.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em caráter de urgência");
				hint1.append(" <br> Registro de Atendimento reiterado");
			}else if(qtdeRAUrgencia.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em caráter de urgência");
			}else if(qtdeReiteracoes != null && qtdeReiteracoes.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento reiterado");
			}
			helper.setHint1(hint1.toString());
		}
		helper.setIndicadorUrgencia(indicadorUrgencia);
	}
}
