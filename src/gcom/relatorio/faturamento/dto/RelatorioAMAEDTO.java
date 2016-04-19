package gcom.relatorio.faturamento.dto;

import gcom.util.Util;

import java.math.BigDecimal;

import br.com.prodigasistemas.gsan.relatorio.ReportElementType;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class RelatorioAMAEDTO implements ReportItemDTO {

  private static final long serialVersionUID = 4309079765574906490L;
  
  @ReportElementType(description="Municipio", group=true, type=ReportElementType.TYPE_STRING)
  private String municipio;
  
  @ReportElementType(description="Localidade", type=ReportElementType.TYPE_STRING)
  private String localidade;
  
  @ReportElementType(description="Valor agua", type=ReportElementType.TYPE_MONEY, totalizer=true)
  private String valorAgua;
  
  @ReportElementType(description="Valor esgoto", type=ReportElementType.TYPE_MONEY, totalizer=true)
  private String valorEsgoto;
  
  private String situacao;
  
  public RelatorioAMAEDTO(String municipio, String localidade, BigDecimal valorAgua, BigDecimal valorEsgoto) {
    super();
    this.municipio = municipio;
    this.localidade = localidade;
    this.valorAgua = Util.formatarMoedaReal(valorAgua);
    this.valorEsgoto = Util.formatarMoedaReal(valorEsgoto);
  }

  public RelatorioAMAEDTO(){}
  
  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public String getValorAgua() {
    return valorAgua;
  }

  public void setValorAgua(String valorAgua) {
    this.valorAgua = valorAgua;
  }

  public String getValorEsgoto() {
    return valorEsgoto;
  }

  public void setValorEsgoto(String valorEsgoto) {
    this.valorEsgoto = valorEsgoto;
  }
  
  


}
