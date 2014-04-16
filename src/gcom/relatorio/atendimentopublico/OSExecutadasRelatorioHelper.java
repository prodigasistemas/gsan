package gcom.relatorio.atendimentopublico;

import java.util.Date;

public class OSExecutadasRelatorioHelper {
	
	private String numeroOS;
	private String codigoServico;
	private String descServico;
	private String descTipoPavimento;
	private String materialrede;
	private String diametroRede;
	private Date dataConclusao;
	private String codigoExcedente;
	private String codigoMaterial;
	private String descMaterial;
	private String qtdeExcedente;
	private String profundRede;
	private String dimenBuraco;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private String enderecoRA;
	private String nomeUnidade;
	private String nomeGerencia;
	
	//subrelatorio
	private Integer qtdeServicosOSExecutadas;
	private String codigoServicosOSExecutadas;
	private String descServicosOSExecutadas;
	
	
	public OSExecutadasRelatorioHelper(Integer idLocalidade, String nomeLocalidade, Integer qtdeServicosOSExecutadas, String codigoServicosOSExecutadas, String descServicosOSExecutadas) {
		super();
		// TODO Auto-generated constructor stub
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.qtdeServicosOSExecutadas = qtdeServicosOSExecutadas;
		this.codigoServicosOSExecutadas = codigoServicosOSExecutadas;
		this.descServicosOSExecutadas = descServicosOSExecutadas;
	}

	public OSExecutadasRelatorioHelper(String numeroOS, String codigoServico, String descServico, String descTipoPavimento, String materialrede, String diametroRede, Date dataConclusao, String codigoExcedente, String codigoMaterial, String descMaterial, String qtdeExcedente, String profundRede, String dimenBuraco, Integer idLocalidade, String nomeLocalidade, String enderecoRA, String nomeUnidade, String nomeGerencia) {
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
		this.codigoMaterial = codigoMaterial;
		this.descMaterial = descMaterial;
		this.qtdeExcedente = qtdeExcedente;
		this.profundRede = profundRede;
		this.dimenBuraco = dimenBuraco;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.enderecoRA = enderecoRA;
		this.nomeUnidade = nomeUnidade;
		this.nomeGerencia = nomeGerencia;
	}
	
	public OSExecutadasRelatorioHelper(Integer qtdeServicosOSExecutadas, String codigoServicosOSExecutadas, String descServicosOSExecutadas) {
		super();
		// TODO Auto-generated constructor stub
		this.qtdeServicosOSExecutadas = qtdeServicosOSExecutadas;
		this.codigoServicosOSExecutadas = codigoServicosOSExecutadas;
		this.descServicosOSExecutadas = descServicosOSExecutadas;
	}



	public OSExecutadasRelatorioHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoExcedente() {
		return codigoExcedente;
	}
	public void setCodigoExcedente(String codigoExcedente) {
		this.codigoExcedente = codigoExcedente;
	}
	public String getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public Date getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getDescMaterial() {
		return descMaterial;
	}
	public void setDescMaterial(String descMaterial) {
		this.descMaterial = descMaterial;
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
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getMaterialrede() {
		return materialrede;
	}
	public void setMaterialrede(String materialrede) {
		this.materialrede = materialrede;
	}
	public String getNomeGerencia() {
		return nomeGerencia;
	}
	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
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

	public String getCodigoServicosOSExecutadas() {
		return codigoServicosOSExecutadas;
	}

	public void setCodigoServicosOSExecutadas(String codigoServicosOSExecutadas) {
		this.codigoServicosOSExecutadas = codigoServicosOSExecutadas;
	}

	public String getDescServicosOSExecutadas() {
		return descServicosOSExecutadas;
	}

	public void setDescServicosOSExecutadas(String descServicosOSExecutadas) {
		this.descServicosOSExecutadas = descServicosOSExecutadas;
	}

	public Integer getQtdeServicosOSExecutadas() {
		return qtdeServicosOSExecutadas;
	}

	public void setQtdeServicosOSExecutadas(Integer qtdeServicosOSExecutadas) {
		this.qtdeServicosOSExecutadas = qtdeServicosOSExecutadas;
	}
	
	
}
