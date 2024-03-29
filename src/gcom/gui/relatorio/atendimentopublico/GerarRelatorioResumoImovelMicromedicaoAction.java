package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioResumoImovelMicromedicao;
import gcom.relatorio.atendimentopublico.RelatorioResumoImovelMicromedicaoSemLeituraInformada;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 13/02/2007
 */
public class GerarRelatorioResumoImovelMicromedicaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		RelatorioResumoImovelMicromedicaoSemLeituraInformada relatorioResumoImovelMicromedicaoSemLeituraInformada = null;

			relatorioResumoImovelMicromedicaoSemLeituraInformada = new RelatorioResumoImovelMicromedicaoSemLeituraInformada(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
//		Collection<Integer> anosMeses = new ArrayList<Integer>(); 
//		
//		Collection colecaoMedicaoHistorico = fachada.carregarDadosMedicaoResumo(new Integer(
//				consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()), true);
//
//		Collection imoveisMicromedicaoCarregamento = null;
//		Collection colecaoImovelMicromedicao = new ArrayList();
//		
//		if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
//			Iterator iteratorMedicaoHistorico = colecaoMedicaoHistorico
//					.iterator();
//
//			while (iteratorMedicaoHistorico.hasNext()) {
//				MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico) iteratorMedicaoHistorico
//						.next();
//				if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {
//					
//					
//					imoveisMicromedicaoCarregamento = fachada
//							.carregarDadosConsumo(
//									new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()),
//									medicaoHistoricoConsumo
//											.getAnoMesReferencia(), true);
//
//					if (imoveisMicromedicaoCarregamento != null) {
//						ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();
//						if(imovelMicromedicao.getMedicaoHistorico() != null 
//								&& imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes() != null){
//							medicaoHistoricoConsumo.setNumeroConsumoMes(imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes());
//							
//						}
//						
//						imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
//						
//						colecaoImovelMicromedicao.add(imovelMicromedicao);
//					}
//				}
//			}
//			
//			// Organizar a cole��o de Conta
//			if (colecaoImovelMicromedicao != null
//					&& !colecaoImovelMicromedicao.isEmpty()) {
//
//				Collections.sort((List) colecaoImovelMicromedicao,
//						new Comparator() {
//							public int compare(Object a, Object b) {
//
//								int retorno = 0;
//								Integer anoMesReferencia1 = ((ImovelMicromedicao) a)
//										.getMedicaoHistorico()
//										.getAnoMesReferencia();
//								Integer anoMesReferencia2 = ((ImovelMicromedicao) b)
//										.getMedicaoHistorico()
//										.getAnoMesReferencia();
//
//								if (anoMesReferencia1
//										.compareTo(anoMesReferencia2) == 1) {
//									retorno = -1;
//								} else if (anoMesReferencia1
//										.compareTo(anoMesReferencia2) == -1) {
//									retorno = 1;
//								}
//
//								return retorno;
//
//							}
//						});
//			}
//			
//		} 
		 
		 String endereco = fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
			
		 Cliente cliente = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("matricula", consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo());
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("inscricao", consultarImovelActionForm.getMatriculaImovelAnaliseMedicaoConsumo());
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("sitLigacaoAgua", consultarImovelActionForm.getSituacaoAguaAnaliseMedicaoConsumo());
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("sitLigacaoEsgoto", consultarImovelActionForm.getSituacaoEsgotoAnaliseMedicaoConsumo());
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("numeroHidrometro",consultarImovelActionForm.getNumeroHidrometro());
			 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("endereco", endereco);
			 

			 /** [MA2011061013] Incluir no cabe�alho o N�mero do Hidr�metro Retirado e a Data da Retirada
			  *  Autor: Paulo Diniz
			  *  Data: 13/07/2011
			  * */
			 List<HidrometroInstalacaoHistorico> hidrometrosHistorico = fachada.pesquisarHidrometroPeloIdImovel(new Integer(consultarImovelActionForm.getIdImovelAnaliseMedicaoConsumo()));
			 
			 if(hidrometrosHistorico != null && !hidrometrosHistorico.isEmpty()){
				 
				 Date dataInstalacao = hidrometrosHistorico.get(0).getDataInstalacao();
				 String numeroRetirado = "";
				 Date dataRetirada = hidrometrosHistorico.get(0).getDataRetirada();
				 if(hidrometrosHistorico.get(0).getNumeroLeituraRetirada() != null){
					 numeroRetirado = hidrometrosHistorico.get(0).getNumeroLeituraRetirada().intValue()+"";
				 }
				 
				 for (HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico : hidrometrosHistorico) {
					 
					 if(dataInstalacao == null || (hidrometroInstalacaoHistorico.getDataInstalacao() != null && dataInstalacao.before(hidrometroInstalacaoHistorico.getDataInstalacao()))){
						 dataInstalacao = hidrometroInstalacaoHistorico.getDataInstalacao();
					 }

					 if(dataRetirada == null || (hidrometroInstalacaoHistorico.getDataRetirada() != null && dataRetirada.before(hidrometroInstalacaoHistorico.getDataRetirada()))){
						 dataRetirada = hidrometroInstalacaoHistorico.getDataRetirada();
						 if(hidrometroInstalacaoHistorico.getNumeroLeituraRetirada() != null){
							 numeroRetirado = hidrometroInstalacaoHistorico.getNumeroLeituraRetirada().intValue()+"";
						 }else{
							 numeroRetirado = "";
						 }
					 }
				 }
				 
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("dataInstalacao",Util.formatarData(dataInstalacao));
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("numeroRetirado",numeroRetirado);
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("dataRetirada",Util.formatarData(dataRetirada));
			 } else{
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("dataInstalacao","");
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("numeroRetirado","");
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("dataRetirada","");
			 }
			 
			 if (cliente != null) {
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("clienteUsuario", cliente.getNome());
			 } else {
				 relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("clienteUsuario", "");
			 }

			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioResumoImovelMicromedicaoSemLeituraInformada.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));
			 
			retorno = processarExibicaoRelatorio(relatorioResumoImovelMicromedicaoSemLeituraInformada,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);	 
		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
