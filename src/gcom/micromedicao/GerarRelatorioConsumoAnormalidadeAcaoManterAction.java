package gcom.micromedicao;


import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.micromedicao.FiltrarConsumoAnormalidadeAcaoActionForm;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.RelatorioManterConsumoAnormalidadeAcao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rodrigo de Abreu Cabral
 * @version 1.0
 */

public class GerarRelatorioConsumoAnormalidadeAcaoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarConsumoAnormalidadeAcaoActionForm form = (FiltrarConsumoAnormalidadeAcaoActionForm) actionForm;

		FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = (FiltroConsumoAnormalidadeAcao) sessao
				.getAttribute("filtroConsumoAnormalidadeAcao");

		// Inicio da parte que vai mandar os parametros para o relatório

		ConsumoAnormalidadeAcao consumoAnormalidadeAcaoParametros = new ConsumoAnormalidadeAcao();

		Short indicadordeUso = null;
		
		if(form.getIndicadorUso()!= null && !form.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + form.getIndicadorUso());
			consumoAnormalidadeAcaoParametros.setIndicadorUso(indicadordeUso);
		}
		
		
		// seta os parametros que serão mostrados no relatório
		
		if(form.getConsumoAnormalidade() != null 
				&& !form.getConsumoAnormalidade().equals("-1")){
			
			ConsumoAnormalidade consumoAnormalidade = new ConsumoAnormalidade();
			consumoAnormalidade.setId(new Integer(form.getConsumoAnormalidade()));
			consumoAnormalidadeAcaoParametros.setConsumoAnormalidade(consumoAnormalidade);
			
		}
		
		if (form.getCategoria() != null 
				&& !form.getCategoria().equals("-1")){
			Categoria categoria = new Categoria();
			categoria.setId(new Integer(form.getCategoria()));
			consumoAnormalidadeAcaoParametros.setCategoria(categoria);
		}
		
		if (form.getImovelPerfil() != null 
				&& !form.getImovelPerfil().equals("-1")){
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(form.getImovelPerfil()));
			consumoAnormalidadeAcaoParametros.setImovelPerfil(imovelPerfil);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes1() != null 
				&& !form.getLeituraAnormalidadeConsumoMes1().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo1 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo1.setId(new Integer(form.getLeituraAnormalidadeConsumoMes1()));
			consumoAnormalidadeAcaoParametros.setLeituraAnormalidadeConsumoMes1(leituraAnormalidadeConsumo1);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes2() != null 
				&& !form.getLeituraAnormalidadeConsumoMes2().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo2 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo2.setId(new Integer(form.getLeituraAnormalidadeConsumoMes2()));
			consumoAnormalidadeAcaoParametros.setLeituraAnormalidadeConsumoMes2(leituraAnormalidadeConsumo2);
		}
		
		if (form.getLeituraAnormalidadeConsumoMes3() != null 
				&& !form.getLeituraAnormalidadeConsumoMes3().equals("-1")){
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumo3 = new LeituraAnormalidadeConsumo();
			leituraAnormalidadeConsumo3.setId(new Integer(form.getLeituraAnormalidadeConsumoMes3()));
			consumoAnormalidadeAcaoParametros.setLeituraAnormalidadeConsumoMes3(leituraAnormalidadeConsumo3);
		}
		
		if (form.getNumerofatorConsumoMes1() != null 
				&& !form.getNumerofatorConsumoMes1().equals("")){
			consumoAnormalidadeAcaoParametros.setNumerofatorConsumoMes1(new BigDecimal(form.getNumerofatorConsumoMes1()));
			
		}
		
		if (form.getNumerofatorConsumoMes2() != null 
				&& !form.getNumerofatorConsumoMes2().equals("")){
			consumoAnormalidadeAcaoParametros.setNumerofatorConsumoMes2(new BigDecimal(form.getNumerofatorConsumoMes2()));
			
		}
		
		if (form.getNumerofatorConsumoMes3() != null 
				&& !form.getNumerofatorConsumoMes3().equals("")){
			consumoAnormalidadeAcaoParametros.setNumerofatorConsumoMes3(new BigDecimal(form.getNumerofatorConsumoMes3()));
			
		}
		
		if (form.getIndicadorGeracaoCartaMes1() != null 
				&& !form.getIndicadorGeracaoCartaMes1().equals("")){
			consumoAnormalidadeAcaoParametros.setIndicadorGeracaoCartaMes1(new Short(form.getIndicadorGeracaoCartaMes1()));
			
		}
		
		if (form.getIndicadorGeracaoCartaMes2() != null 
				&& !form.getIndicadorGeracaoCartaMes2().equals("")){
			consumoAnormalidadeAcaoParametros.setIndicadorGeracaoCartaMes2(new Short(form.getIndicadorGeracaoCartaMes2()));
			
		}
		
		if (form.getIndicadorGeracaoCartaMes3() != null 
				&& !form.getIndicadorGeracaoCartaMes3().equals("")){
			consumoAnormalidadeAcaoParametros.setIndicadorGeracaoCartaMes3(new Short(form.getIndicadorGeracaoCartaMes3()));
			
		}
		
		if (form.getIdServicoTipoMes1() != null 
				&& !form.getIdServicoTipoMes1().equals("")){
			ServicoTipo servicoTipo1 = new ServicoTipo();
			servicoTipo1.setId(new Integer(form.getIdServicoTipoMes1()));
			consumoAnormalidadeAcaoParametros.setServicoTipoMes1(servicoTipo1);
		}
		
		if (form.getIdServicoTipoMes2() != null 
				&& !form.getIdServicoTipoMes2().equals("")){
			ServicoTipo servicoTipo2 = new ServicoTipo();
			servicoTipo2.setId(new Integer(form.getIdServicoTipoMes2()));
			consumoAnormalidadeAcaoParametros.setServicoTipoMes2(servicoTipo2);
		}
		
		if (form.getIdServicoTipoMes3() != null 
				&& !form.getIdServicoTipoMes3().equals("")){
			ServicoTipo servicoTipo3 = new ServicoTipo();
			servicoTipo3.setId(new Integer(form.getIdServicoTipoMes3()));
			consumoAnormalidadeAcaoParametros.setServicoTipoMes3(servicoTipo3);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes1() != null 
				&& !form.getSolicitacaoTipoEspecificacaoMes1().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao1 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao1.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes1()));
			consumoAnormalidadeAcaoParametros.setSolicitacaoTipoEspecificacaoMes1(solicitacaoTipoEspecificacao1);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes2() != null 
				&& !form.getSolicitacaoTipoEspecificacaoMes2().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao2 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao2.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes2()));
			consumoAnormalidadeAcaoParametros.setSolicitacaoTipoEspecificacaoMes2(solicitacaoTipoEspecificacao2);
		}
		
		if (form.getSolicitacaoTipoEspecificacaoMes3() != null 
				&& !form.getSolicitacaoTipoEspecificacaoMes3().equals("-1")){
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao3 = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacao3.setId(new Integer(form.getSolicitacaoTipoEspecificacaoMes3()));
			consumoAnormalidadeAcaoParametros.setSolicitacaoTipoEspecificacaoMes3(solicitacaoTipoEspecificacao3);
		}
		
		if (form.getDescricaoContaMensagemMes1() != null 
				&& !form.getDescricaoContaMensagemMes1().equals("")){
			consumoAnormalidadeAcaoParametros.setDescricaoContaMensagemMes1(form.getDescricaoContaMensagemMes1());
			
		}
		
		if (form.getDescricaoContaMensagemMes2() != null 
				&& !form.getDescricaoContaMensagemMes2().equals("")){
			consumoAnormalidadeAcaoParametros.setDescricaoContaMensagemMes2(form.getDescricaoContaMensagemMes2());
			
		}
		
		if (form.getDescricaoContaMensagemMes3() != null 
				&& !form.getDescricaoContaMensagemMes3().equals("")){
			consumoAnormalidadeAcaoParametros.setDescricaoContaMensagemMes3(form.getDescricaoContaMensagemMes3());
			
		}
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterConsumoAnormalidadeAcao relatorioManterConsumoAnormalidadeAcao = new RelatorioManterConsumoAnormalidadeAcao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterConsumoAnormalidadeAcao.addParametro("filtroConsumoAnormalidadeAcao",
				filtroConsumoAnormalidadeAcao);
		relatorioManterConsumoAnormalidadeAcao.addParametro("consumoAnormalidadeAcaoParametros",
				consumoAnormalidadeAcaoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterConsumoAnormalidadeAcao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterConsumoAnormalidadeAcao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
