package gcom.relatorio.gerencial.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioDemonstrativoSinteticoLigacoesBean implements RelatorioBean{
	private static final long serialVersionUID = 1L;
	
	private Long agua_movLig_existentes;
	private Long agua_movLig_funcionando;
	private Long agua_movLig_cortadas;
	private Long agua_movLig_suprimidas;	
	private Long agua_movLig_corteMes;
	private Long agua_movLig_ligacaoMes;
	private Long agua_movLig_religacaoMes;
	private Long agua_eco_existentes;
	private Long agua_eco_funcionando;
	private Long agua_eco_cortadas;
	private Long agua_eco_suprimidas;
	private Long agua_eco_ecoExist_residencial;
	private Long agua_eco_ecoExist_comercial;
	private Long agua_eco_ecoExist_publica;
	private Long agua_eco_ecoExist_industrial;	
	private Long agua_eco_ecoFunc_residencial;
	private Long agua_eco_ecoFunc_comercial;
	private Long agua_eco_ecoFunc_publica;
	private Long agua_eco_ecoFunc_industrial;	
	private Long agua_hid_funcionando;
	private Long agua_hid_cortados;
	private Long agua_hid_lidos;
	private Long agua_hid_instalados;
	private Long agua_hid_parados;	
	private Long agua_cons_estNHidrometrado;
	private Long agua_cons_estHidrometrado;
	private Long agua_cons_realHidrometrado;
	private Long agua_cons_faturado;	
	
	private Long esgoto_movLig_existente;
	private Long esgoto_movLig_funcionando; 
	private Long esgoto_movLig_cortadas;
	private Long esgoto_movLig_suprimidas;	
	private Long esgoto_eco_existentes;
	private Long esgoto_eco_funcionando;
	private Long esgoto_eco_cortadas;
	private Long esgoto_eco_supridas;	
	private Long esgoto_eco_ecoExist_residencial;
	private Long esgoto_eco_ecoExist_comercial;
	private Long esgoto_eco_ecoExist_publica;
	private Long esgoto_eco_ecoExist_industrial;	
	private Long esgoto_eco_ecoFunc_residencial;
	private Long esgoto_eco_ecoFunc_comercial;
	private Long esgoto_eco_ecoFunc_publica;
	private Long esgoto_eco_ecoFunc_industrial;
	
	private String gerenciaRegionalID;
	private String gerenciaRegional;
	private String unidadeNegocioID;
	private String unidadeNegocio;
	private String localidadeID;
	private String localidade;
	private String municipioID;
	private String municipio;
		
	
	public Long getAgua_movLig_existentes() {
		return agua_movLig_existentes;
	}
	public void setAgua_movLig_existentes(Long agua_movLig_existentes) {
		this.agua_movLig_existentes = agua_movLig_existentes;
	}
	public Long getAgua_movLig_funcionando() {
		return agua_movLig_funcionando;
	}
	public void setAgua_movLig_funcionando(Long agua_movLig_funcionando) {
		this.agua_movLig_funcionando = agua_movLig_funcionando;
	}
	public Long getAgua_movLig_cortadas() {
		return agua_movLig_cortadas;
	}
	public void setAgua_movLig_cortadas(Long agua_movLig_cortadas) {
		this.agua_movLig_cortadas = agua_movLig_cortadas;
	}
	public Long getAgua_movLig_suprimidas() {
		return agua_movLig_suprimidas;
	}
	public void setAgua_movLig_suprimidas(Long agua_movLig_suprimidas) {
		this.agua_movLig_suprimidas = agua_movLig_suprimidas;
	}
	public Long getAgua_movLig_corteMes() {
		return agua_movLig_corteMes;
	}
	public void setAgua_movLig_corteMes(Long agua_movLig_corteMes) {
		this.agua_movLig_corteMes = agua_movLig_corteMes;
	}
	public Long getAgua_movLig_ligacaoMes() {
		return agua_movLig_ligacaoMes;
	}
	public void setAgua_movLig_ligacaoMes(Long agua_movLig_ligacaoMes) {
		this.agua_movLig_ligacaoMes = agua_movLig_ligacaoMes;
	}
	public Long getAgua_movLig_religacaoMes() {
		return agua_movLig_religacaoMes;
	}
	public void setAgua_movLig_religacaoMes(Long agua_movLig_religacaoMes) {
		this.agua_movLig_religacaoMes = agua_movLig_religacaoMes;
	}
	public Long getAgua_eco_existentes() {
		return agua_eco_existentes;
	}
	public void setAgua_eco_existentes(Long agua_eco_existentes) {
		this.agua_eco_existentes = agua_eco_existentes;
	}
	public Long getAgua_eco_funcionando() {
		return agua_eco_funcionando;
	}
	public void setAgua_eco_funcionando(Long agua_eco_funcionando) {
		this.agua_eco_funcionando = agua_eco_funcionando;
	}
	public Long getAgua_eco_cortadas() {
		return agua_eco_cortadas;
	}
	public void setAgua_eco_cortadas(Long agua_eco_cortadas) {
		this.agua_eco_cortadas = agua_eco_cortadas;
	}
	public Long getAgua_eco_suprimidas() {
		return agua_eco_suprimidas;
	}
	public void setAgua_eco_suprimidas(Long agua_eco_suprimidas) {
		this.agua_eco_suprimidas = agua_eco_suprimidas;
	}
	public Long getAgua_eco_ecoExist_residencial() {
		return agua_eco_ecoExist_residencial;
	}
	public void setAgua_eco_ecoExist_residencial(
			Long agua_eco_ecoExist_residencial) {
		this.agua_eco_ecoExist_residencial = agua_eco_ecoExist_residencial;
	}
	public Long getAgua_eco_ecoExist_comercial() {
		return agua_eco_ecoExist_comercial;
	}
	public void setAgua_eco_ecoExist_comercial(Long agua_eco_ecoExist_comercial) {
		this.agua_eco_ecoExist_comercial = agua_eco_ecoExist_comercial;
	}
	public Long getAgua_eco_ecoExist_publica() {
		return agua_eco_ecoExist_publica;
	}
	public void setAgua_eco_ecoExist_publica(Long agua_eco_ecoExist_publica) {
		this.agua_eco_ecoExist_publica = agua_eco_ecoExist_publica;
	}
	public Long getAgua_eco_ecoExist_industrial() {
		return agua_eco_ecoExist_industrial;
	}
	public void setAgua_eco_ecoExist_industrial(Long agua_eco_ecoExist_industrial) {
		this.agua_eco_ecoExist_industrial = agua_eco_ecoExist_industrial;
	}
	public Long getAgua_eco_ecoFunc_residencial() {
		return agua_eco_ecoFunc_residencial;
	}
	public void setAgua_eco_ecoFunc_residencial(Long agua_eco_ecoFunc_residencial) {
		this.agua_eco_ecoFunc_residencial = agua_eco_ecoFunc_residencial;
	}
	public Long getAgua_eco_ecoFunc_comercial() {
		return agua_eco_ecoFunc_comercial;
	}
	public void setAgua_eco_ecoFunc_comercial(Long agua_eco_ecoFunc_comercial) {
		this.agua_eco_ecoFunc_comercial = agua_eco_ecoFunc_comercial;
	}
	public Long getAgua_eco_ecoFunc_publica() {
		return agua_eco_ecoFunc_publica;
	}
	public void setAgua_eco_ecoFunc_publica(Long agua_eco_ecoFunc_publica) {
		this.agua_eco_ecoFunc_publica = agua_eco_ecoFunc_publica;
	}
	public Long getAgua_eco_ecoFunc_industrial() {
		return agua_eco_ecoFunc_industrial;
	}
	public void setAgua_eco_ecoFunc_industrial(Long agua_eco_ecoFunc_industrial) {
		this.agua_eco_ecoFunc_industrial = agua_eco_ecoFunc_industrial;
	}
	public Long getAgua_hid_funcionando() {
		return agua_hid_funcionando;
	}
	public void setAgua_hid_funcionando(Long agua_hid_funcionando) {
		this.agua_hid_funcionando = agua_hid_funcionando;
	}
	public Long getAgua_hid_cortados() {
		return agua_hid_cortados;
	}
	public void setAgua_hid_cortados(Long agua_hid_cortados) {
		this.agua_hid_cortados = agua_hid_cortados;
	}
	public Long getAgua_hid_lidos() {
		return agua_hid_lidos;
	}
	public void setAgua_hid_lidos(Long agua_hid_lidos) {
		this.agua_hid_lidos = agua_hid_lidos;
	}
	public Long getAgua_hid_instalados() {
		return agua_hid_instalados;
	}
	public void setAgua_hid_instalados(Long agua_hid_instalados) {
		this.agua_hid_instalados = agua_hid_instalados;
	}
	public Long getAgua_hid_parados() {
		return agua_hid_parados;
	}
	public void setAgua_hid_parados(Long agua_hid_parados) {
		this.agua_hid_parados = agua_hid_parados;
	}
	public Long getAgua_cons_estNHidrometrado() {
		return agua_cons_estNHidrometrado;
	}
	public void setAgua_cons_estNHidrometrado(Long agua_cons_estNHidrometrado) {
		this.agua_cons_estNHidrometrado = agua_cons_estNHidrometrado;
	}
	public Long getAgua_cons_estHidrometrado() {
		return agua_cons_estHidrometrado;
	}
	public void setAgua_cons_estHidrometrado(Long agua_cons_estHidrometrado) {
		this.agua_cons_estHidrometrado = agua_cons_estHidrometrado;
	}
	public Long getAgua_cons_realHidrometrado() {
		return agua_cons_realHidrometrado;
	}
	public void setAgua_cons_realHidrometrado(Long agua_cons_realHidrometrado) {
		this.agua_cons_realHidrometrado = agua_cons_realHidrometrado;
	}
	public Long getAgua_cons_faturado() {
		return agua_cons_faturado;
	}
	public void setAgua_cons_faturado(Long agua_cons_faturado) {
		this.agua_cons_faturado = agua_cons_faturado;
	}
	public Long getEsgoto_movLig_existente() {
		return esgoto_movLig_existente;
	}
	public void setEsgoto_movLig_existente(Long esgoto_movLig_existente) {
		this.esgoto_movLig_existente = esgoto_movLig_existente;
	}
	public Long getEsgoto_movLig_funcionando() {
		return esgoto_movLig_funcionando;
	}
	public void setEsgoto_movLig_funcionando(Long esgoto_movLig_funcionando) {
		this.esgoto_movLig_funcionando = esgoto_movLig_funcionando;
	}
	public Long getEsgoto_movLig_cortadas() {
		return esgoto_movLig_cortadas;
	}
	public void setEsgoto_movLig_cortadas(Long esgoto_movLig_cortadas) {
		this.esgoto_movLig_cortadas = esgoto_movLig_cortadas;
	}
	public Long getEsgoto_movLig_suprimidas() {
		return esgoto_movLig_suprimidas;
	}
	public void setEsgoto_movLig_suprimidas(Long esgoto_movLig_suprimidas) {
		this.esgoto_movLig_suprimidas = esgoto_movLig_suprimidas;
	}
	public Long getEsgoto_eco_existentes() {
		return esgoto_eco_existentes;
	}
	public void setEsgoto_eco_existentes(Long esgoto_eco_existentes) {
		this.esgoto_eco_existentes = esgoto_eco_existentes;
	}
	public Long getEsgoto_eco_funcionando() {
		return esgoto_eco_funcionando;
	}
	public void setEsgoto_eco_funcionando(Long esgoto_eco_funcionando) {
		this.esgoto_eco_funcionando = esgoto_eco_funcionando;
	}
	public Long getEsgoto_eco_cortadas() {
		return esgoto_eco_cortadas;
	}
	public void setEsgoto_eco_cortadas(Long esgoto_eco_cortadas) {
		this.esgoto_eco_cortadas = esgoto_eco_cortadas;
	}
	public Long getEsgoto_eco_supridas() {
		return esgoto_eco_supridas;
	}
	public void setEsgoto_eco_supridas(Long esgoto_eco_supridas) {
		this.esgoto_eco_supridas = esgoto_eco_supridas;
	}
	public Long getEsgoto_eco_ecoExist_residencial() {
		return esgoto_eco_ecoExist_residencial;
	}
	public void setEsgoto_eco_ecoExist_residencial(
			Long esgoto_eco_ecoExist_residencial) {
		this.esgoto_eco_ecoExist_residencial = esgoto_eco_ecoExist_residencial;
	}
	public Long getEsgoto_eco_ecoExist_comercial() {
		return esgoto_eco_ecoExist_comercial;
	}
	public void setEsgoto_eco_ecoExist_comercial(
			Long esgoto_eco_ecoExist_comercial) {
		this.esgoto_eco_ecoExist_comercial = esgoto_eco_ecoExist_comercial;
	}
	public Long getEsgoto_eco_ecoExist_publica() {
		return esgoto_eco_ecoExist_publica;
	}
	public void setEsgoto_eco_ecoExist_publica(Long esgoto_eco_ecoExist_publica) {
		this.esgoto_eco_ecoExist_publica = esgoto_eco_ecoExist_publica;
	}
	public Long getEsgoto_eco_ecoExist_industrial() {
		return esgoto_eco_ecoExist_industrial;
	}
	public void setEsgoto_eco_ecoExist_industrial(
			Long esgoto_eco_ecoExist_industrial) {
		this.esgoto_eco_ecoExist_industrial = esgoto_eco_ecoExist_industrial;
	}
	public Long getEsgoto_eco_ecoFunc_residencial() {
		return esgoto_eco_ecoFunc_residencial;
	}
	public void setEsgoto_eco_ecoFunc_residencial(
			Long esgoto_eco_ecoFunc_residencial) {
		this.esgoto_eco_ecoFunc_residencial = esgoto_eco_ecoFunc_residencial;
	}
	public Long getEsgoto_eco_ecoFunc_comercial() {
		return esgoto_eco_ecoFunc_comercial;
	}
	public void setEsgoto_eco_ecoFunc_comercial(Long esgoto_eco_ecoFunc_comercial) {
		this.esgoto_eco_ecoFunc_comercial = esgoto_eco_ecoFunc_comercial;
	}
	public Long getEsgoto_eco_ecoFunc_publica() {
		return esgoto_eco_ecoFunc_publica;
	}
	public void setEsgoto_eco_ecoFunc_publica(Long esgoto_eco_ecoFunc_publica) {
		this.esgoto_eco_ecoFunc_publica = esgoto_eco_ecoFunc_publica;
	}
	public Long getEsgoto_eco_ecoFunc_industrial() {
		return esgoto_eco_ecoFunc_industrial;
	}
	public void setEsgoto_eco_ecoFunc_industrial(
			Long esgoto_eco_ecoFunc_industrial) {
		this.esgoto_eco_ecoFunc_industrial = esgoto_eco_ecoFunc_industrial;
	}
	public String getGerenciaRegionalID() {
		return gerenciaRegionalID;
	}
	public void setGerenciaRegionalID(String gerenciaRegionalID) {
		this.gerenciaRegionalID = gerenciaRegionalID;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getUnidadeNegocioID() {
		return unidadeNegocioID;
	}
	public void setUnidadeNegocioID(String unidadeNegocioID) {
		this.unidadeNegocioID = unidadeNegocioID;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getLocalidadeID() {
		return localidadeID;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getMunicipioID() {
		return municipioID;
	}
	public void setMunicipioID(String municipioID) {
		this.municipioID = municipioID;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}		
		
}
