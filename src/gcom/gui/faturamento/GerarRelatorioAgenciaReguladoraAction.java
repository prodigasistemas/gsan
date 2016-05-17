package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioUtil;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.faturamento.dto.RelatorioAgenciaReguladoraDTO;
import gcom.util.IoUtil;
import gcom.util.Util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class GerarRelatorioAgenciaReguladoraAction extends ExibidorProcessamentoTarefaRelatorio {

  public ActionForward execute(ActionMapping actionMapping,
      ActionForm actionForm, HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {

    ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAgenciaReguladora");
    
    GerarRelatorioAgenciaReguladoraActionForm form = (GerarRelatorioAgenciaReguladoraActionForm) actionForm;
    Integer idAgencia = form.getIdAgenciaReguladora() == 0 ? null : form.getIdAgenciaReguladora();
    String mesAno = form.getMesAno().replace("/", "");
    String anoMes = mesAno.substring(2) + mesAno.substring(0,2);

    Fachada fachada = Fachada.getInstancia();
    List<RelatorioAgenciaReguladoraDTO> dtos = fachada.pesquisarContasParaRelatorioAgenciaReguladora(Integer.parseInt(anoMes), idAgencia);

    if (dtos == null || dtos.isEmpty()) {
      throw new ActionServletException("atencao.relatorio.vazio");
    }
    
    
    RelatorioUtil relatorioUtil = null;
    if (form.getTipoRelatorio() == 1) {
      relatorioUtil = new RelatorioUtil(
          "Relatório faturamento para agência reguladora",
          getNomeRelatorio(anoMes) + ".pdf",
          RelatorioAgenciaReguladoraDTO.class, 
          FormatoRelatorio.PDF);
    } else {
      relatorioUtil = new RelatorioUtil(
          "Relatório faturamento para agência reguladora",
          getNomeRelatorio(anoMes) + ".xls",
          RelatorioAgenciaReguladoraDTO.class, 
          FormatoRelatorio.XLS);
    }
    relatorioUtil.setOmitirTotalGeral(true);
    

    List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
    itens.addAll(dtos);

    File relatorio = gerar(relatorioUtil, itens);
    
    downloadRelatorio(httpServletResponse, relatorio);

    
    return retorno;
  }
  
  private File gerar(RelatorioUtil relatorioUtil, List<ReportItemDTO> itens) {
    File relatorio = null;
    
    try {
      relatorio = relatorioUtil.gerarRelatorio(itens);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new ActionServletException("atencao.erro_baixar_relatorio");
    } catch (IOException e) {
      e.printStackTrace();
      throw new ActionServletException("atencao.erro_baixar_relatorio");
    }
    
    return relatorio;
  }

  private void downloadRelatorio(HttpServletResponse response, File relatorio) {
    try {
      response.setContentType(FormatoRelatorio.PDF.getContentType());
      response.addHeader("Content-Disposition", "attachment; filename=" + relatorio.getName());

      ServletOutputStream sos = response.getOutputStream();
      sos.write(IoUtil.getBytesFromFile(relatorio));
      sos.flush();
      sos.close();

      relatorio.delete();
    } catch (IOException e) {
      e.printStackTrace();
      throw new ActionServletException("atencao.erro_baixar_relatorio");
    }
  }


  private String getNomeRelatorio(String mesAno) {
    return "relatorio_faturamento_agencia_reguladora" + mesAno;
  }
}
