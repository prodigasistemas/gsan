package gcom.relatorio.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioOSExecutadasPrestadoraServicoBean implements RelatorioBean {

	private String numeroOS;
	private String codigoServico;
	private String descServico;
	private String descTipoPavimento;
	private String materialrede;
	private String diametroRede;
	private String dataConclusao;
	private String codigoExcedente;
//	private String codigoMaterial;
	private String descMaterial;
	private String qtdeExcedente;
	private String profundRede;
	private String dimenBuraco;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String enderecoRA;
	
	private String descricaoGerencia;
	private String idGerencia;
	private String descricaoUnidadeNegocio;
	private String idUnidadeNegocio;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	
	
	public RelatorioOSExecutadasPrestadoraServicoBean(String numeroOS, String codigoServico, String descServico, String descTipoPavimento, String materialrede, String diametroRede, String dataConclusao, String codigoExcedente, String descMaterial, String qtdeExcedente, String profundRede, String dimenBuraco, String idLocalidade, String descricaoLocalidade, String enderecoRA, String descricaoGerencia, String idGerencia, String descricaoUnidadeNegocio, String idUnidadeNegocio, Collection colecaoSubrelatorio) {
		super();
		// TODO Auto-generated constructor stub
		this.numeroOS = numeroOS;
		this.codigoServico = codigoServico;
		this.descServico = descServico;
		this.descTipoPavimento = descTipoPavimento;
		this.materialrede = materialrede;
		this.diametroRede = diametroRede;
		this.dataConclusao = dataConclusao;
		this.codigoExcedente = codigoExcedente;
//		this.codigoMaterial = codigoMaterial;
		this.descMaterial = descMaterial;
		this.qtdeExcedente = qtdeExcedente;
		this.profundRede = profundRede;
		this.dimenBuraco = dimenBuraco;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.enderecoRA = enderecoRA;
		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean = new ArrayList();
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean.addAll(colecaoSubrelatorio);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean);
		
	}
	public RelatorioOSExecutadasPrestadoraServicoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoExcedente() {
		return codigoExcedente;
	}
	public void setCodigoExcedente(String codigoExcedente) {
		this.codigoExcedente = codigoExcedente;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getDescMaterial() {
		return descMaterial;
	}
	public void setDescMaterial(String descMaterial) {
		this.descMaterial = descMaterial;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}
	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}
	public String getDescServico() {
		return descServico;
	}
	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}
	public String getDescTipoPavimento() {
		return descTipoPavimento;
	}
	public void setDescTipoPavimento(String descTipoPavimento) {
		this.descTipoPavimento = descTipoPavimento;
	}
	public String getDiametroRede() {
		return diametroRede;
	}
	public void setDiametroRede(String diametroRede) {
		this.diametroRede = diametroRede;
	}
	public String getDimenBuraco() {
		return dimenBuraco;
	}
	public void setDimenBuraco(String dimenBuraco) {
		this.dimenBuraco = dimenBuraco;
	}
	public String getEnderecoRA() {
		return enderecoRA;
	}
	public void setEnderecoRA(String enderecoRA) {
		this.enderecoRA = enderecoRA;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getMaterialrede() {
		return materialrede;
	}
	public void setMaterialrede(String materialrede) {
		this.materialrede = materialrede;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getProfundRede() {
		return profundRede;
	}
	public void setProfundRede(String profundRede) {
		this.profundRede = profundRede;
	}
	public String getQtdeExcedente() {
		return qtdeExcedente;
	}
	public void setQtdeExcedente(String qtdeExcedente) {
		this.qtdeExcedente = qtdeExcedente;
	}
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}
	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}
	public ArrayList getArrayRelatorioOSExecutadasPrestadoraServicoDetailBean() {
		return arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	}
	public void setArrayRelatorioOSExecutadasPrestadoraServicoDetailBean(
			ArrayList arrayRelatorioOSExecutadasPrestadoraServicoDetailBean) {
		this.arrayRelatorioOSExecutadasPrestadoraServicoDetailBean = arrayRelatorioOSExecutadasPrestadoraServicoDetailBean;
	}
	
	
	
	

}
