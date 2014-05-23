package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

public class RelatorioFichaFiscalizacaoCadastralBean implements RelatorioBean {

	private Integer idImovel;
	
	private String nomeLocalidade;
	
	private Integer codigoSetor;
	
	private Integer numeroQuadra;
	
	private Integer numeroLote;
	
	private Integer numeroSublote;
	
	private String descricaoLogradouroImovel;
	
	private Integer idLogradouroImovel;
	
	private String numeroImovel;
	
	private String complementoEnderecoImovel;
	
	private String bairroImovel;
	
	private String cepImovel;
	
	private Integer codigoRota;
	
	private Integer numeroFace;
	
	private String nomeMunicipioImovel;
	
	private Integer idMunicipioImovel;
	
	private Integer idCliente;
	
	private String nomeCliente;
	
	private String cpfCnpj;
	
	private String rg;
	
	private String uf;
	
	private Integer sexo;
	
	private String descricaoLogradouroCliente;
	
	private String numeroImovelCliente;
	
	private Integer enderecoTipoCliente;
	
	private String nomeMunicipioCliente;
	
	private String complementoEnderecoCliente;
	
	private String bairroCliente;
	
	private String cepCliente;
	
	private String emailCliente;
	
	private String ddd;
	
	private String telefone;
	
	private String celular;
	
	public RelatorioFichaFiscalizacaoCadastralBean(RelatorioFichaFiscalizacaoCadastralHelper helper) {
		this.idImovel = helper.getIdImovel();
		this.nomeLocalidade = helper.getNomeLocalidade();
		this.codigoSetor = helper.getCodigoSetor();
		this.numeroQuadra = helper.getNumeroQuadra();
		this.numeroLote = helper.getNumeroLote();
		this.numeroSublote = helper.getNumeroSublote();
		this.descricaoLogradouroImovel = helper.getDescricaoLogradouroImovel();
		this.idLogradouroImovel = helper.getIdLogradouroImovel();
		this.numeroImovel = helper.getNumeroImovel();
		this.complementoEnderecoImovel = helper.getComplementoEnderecoImovel();
		this.bairroImovel = helper.getBairroImovel();
		this.cepImovel = Util.formatarCEP(helper.getCepImovel().toString());
		this.codigoRota = helper.getCodigoRota();
		this.numeroFace = helper.getNumeroFace();
		this.nomeMunicipioImovel = helper.getNomeMunicipioImovel();
		this.idMunicipioImovel = helper.getIdMunicipioImovel();
		this.idCliente = helper.getIdCliente();
		this.nomeCliente = helper.getNomeCliente();
		
		if (helper.getCpfCnpj() != null) {
			String cpfCnpj = helper.getCpfCnpj();
			if (cpfCnpj.length()==11) {
				this.cpfCnpj = Util.formatarCpf(cpfCnpj);
			} else if (cpfCnpj.length()==14) {
				this.cpfCnpj = Util.formatarCnpj(cpfCnpj);
			}
		}
		
		this.rg = helper.getRg();
		this.uf = helper.getUf();
		this.sexo = helper.getSexo();
		this.descricaoLogradouroCliente = helper.getDescricaoLogradouroCliente();
		this.numeroImovelCliente = helper.getNumeroImovelCliente();
		this.enderecoTipoCliente = helper.getEnderecoTipoCliente();
		this.nomeMunicipioCliente = helper.getNomeMunicipioCliente();
		this.complementoEnderecoCliente = helper.getComplementoEnderecoCliente();
		this.bairroCliente = helper.getBairroCliente();
		this.cepCliente = Util.formatarCEP(helper.getCepCliente().toString());
		this.emailCliente = helper.getEmailCliente();
		this.ddd = helper.getDdd();
		this.telefone = Util.formatarTelefone(helper.getTelefone());
		this.celular = Util.formatarTelefone(helper.getCelular());
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroSublote() {
		return numeroSublote;
	}

	public void setNumeroSublote(Integer numeroSublote) {
		this.numeroSublote = numeroSublote;
	}

	public String getDescricaoLogradouroImovel() {
		return descricaoLogradouroImovel;
	}

	public void setDescricaoLogradouroImovel(String descricaoLogradouroImovel) {
		this.descricaoLogradouroImovel = descricaoLogradouroImovel;
	}

	public Integer getIdLogradouroImovel() {
		return idLogradouroImovel;
	}

	public void setIdLogradouroImovel(Integer idLogradouroImovel) {
		this.idLogradouroImovel = idLogradouroImovel;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEnderecoImovel() {
		return complementoEnderecoImovel;
	}

	public void setComplementoEnderecoImovel(String complementoEnderecoImovel) {
		this.complementoEnderecoImovel = complementoEnderecoImovel;
	}

	public String getBairroImovel() {
		return bairroImovel;
	}

	public void setBairroImovel(String bairroImovel) {
		this.bairroImovel = bairroImovel;
	}

	public String getCepImovel() {
		return cepImovel;
	}

	public void setCepImovel(String cepImovel) {
		this.cepImovel = cepImovel;
	}

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroFace() {
		return numeroFace;
	}

	public void setNumeroFace(Integer numeroFace) {
		this.numeroFace = numeroFace;
	}

	public String getNomeMunicipioImovel() {
		return nomeMunicipioImovel;
	}

	public void setNomeMunicipioImovel(String nomeMunicipioImovel) {
		this.nomeMunicipioImovel = nomeMunicipioImovel;
	}

	public Integer getIdMunicipioImovel() {
		return idMunicipioImovel;
	}

	public void setIdMunicipioImovel(Integer idMunicipioImovel) {
		this.idMunicipioImovel = idMunicipioImovel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getDescricaoLogradouroCliente() {
		return descricaoLogradouroCliente;
	}

	public void setDescricaoLogradouroCliente(String descricaoLogradouroCliente) {
		this.descricaoLogradouroCliente = descricaoLogradouroCliente;
	}

	public String getNumeroImovelCliente() {
		return numeroImovelCliente;
	}

	public void setNumeroImovelCliente(String numeroImovelCliente) {
		this.numeroImovelCliente = numeroImovelCliente;
	}

	public Integer getEnderecoTipoCliente() {
		return enderecoTipoCliente;
	}

	public void setEnderecoTipoCliente(Integer enderecoTipoCliente) {
		this.enderecoTipoCliente = enderecoTipoCliente;
	}

	public String getNomeMunicipioCliente() {
		return nomeMunicipioCliente;
	}

	public void setNomeMunicipioCliente(String nomeMunicipioCliente) {
		this.nomeMunicipioCliente = nomeMunicipioCliente;
	}

	public String getComplementoEnderecoCliente() {
		return complementoEnderecoCliente;
	}

	public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
		this.complementoEnderecoCliente = complementoEnderecoCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
