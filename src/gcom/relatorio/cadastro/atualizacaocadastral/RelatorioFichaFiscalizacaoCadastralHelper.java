package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioFichaFiscalizacaoCadastralHelper {
	
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
	private Integer cepImovel;
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
	private Integer cepCliente;
	private String emailCliente;
	private String ddd;
	private String telefone;
	private String celular;
	private String contratoEnergia;
	private Integer fonteAbastecimento;
	private Integer ligacaoAguaSituacao;
	private Integer ligacaoEsgotoSituacao;
	private String numeroHidrometro;
	private String hidrometroCapacidade;
	private Integer hidrometroProtecao;
	private String hidrometroMarca;
	private String outrasInformacoes;
	private BigDecimal areaConstruida;
	private Integer pontosUtilizacao;
	private Integer moradores;
	
	private List<ImovelSubcategoriaRetorno> subcategorias;
	
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

	public Integer getCepImovel() {
		return cepImovel;
	}

	public void setCepImovel(Integer cepImovel) {
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

	public Integer getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(Integer cepCliente) {
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

	public String getContratoEnergia() {
		return contratoEnergia;
	}

	public void setContratoEnergia(String contratoEnergia) {
		this.contratoEnergia = contratoEnergia;
	}

	public Integer getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(Integer fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public Integer getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(Integer ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Integer getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(Integer ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(String hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public Integer getHidrometroProtecao() {
		return hidrometroProtecao;
	}

	public void setHidrometroProtecao(Integer hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}

	public String getHidrometroMarca() {
		return hidrometroMarca;
	}

	public void setHidrometroMarca(String hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public Integer getPontosUtilizacao() {
		return pontosUtilizacao;
	}

	public void setPontosUtilizacao(Integer pontosUtilizacao) {
		this.pontosUtilizacao = pontosUtilizacao;
	}

	public Integer getMoradores() {
		return moradores;
	}

	public void setMoradores(Integer moradores) {
		this.moradores = moradores;
	}
	
	public String getDescricaoAreaConstruida() {
		if (this.areaConstruida != null)
			return Util.formatarBigDecimalParaStringComVirgula(this.areaConstruida);
		else
			return null;
	}

	public List<ImovelSubcategoriaRetorno> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<ImovelSubcategoriaRetorno> subcategorias) {
		this.subcategorias = subcategorias;
	}
}
