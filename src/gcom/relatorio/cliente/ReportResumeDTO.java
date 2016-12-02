package gcom.relatorio.cliente;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ReportResumeDTO implements Serializable{
    private static final long serialVersionUID = 3942162017291437736L;
    
    private List<ReportField> cabecalho;
    
    private List<ReportItemDTO> dados = new LinkedList<ReportItemDTO>();
    
    public ReportResumeDTO() {}
    
    public ReportResumeDTO(Class dataClass, List<ReportItemDTO> dados) {
        super();
        this.cabecalho = new ReportUtil().headerFieldsFromClass(dataClass);
        this.dados = dados;
    }

    public List<ReportField> getCabecalho() {
        return cabecalho;
    }

    public List<ReportItemDTO> getDados() {
        return dados;
    }

    public void addLinhas(List<ReportItemDTO> linhas) {
        this.dados.addAll(linhas);
    }
    
    public void setDados(List<ReportItemDTO> dados) {
        this.dados = dados;
    }
}
