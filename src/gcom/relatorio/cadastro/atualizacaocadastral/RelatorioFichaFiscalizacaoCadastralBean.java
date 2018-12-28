package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.List;

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
	
	private short economiasR1;
	private short economiasR2;
	private short economiasR3;
	private short economiasR4;
	
	private short economiasC1;
	private short economiasC2;
	private short economiasC3;
	private short economiasC4;
	
	private short economiasI1;
	private short economiasI2;
	private short economiasI3;
	private short economiasI4;
	
	private short economiasP1;
	private short economiasP2;
	private short economiasP3;
	private short economiasP4;
	
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
		
		this.contratoEnergia = helper.getContratoEnergia();
		this.fonteAbastecimento = helper.getFonteAbastecimento();
		this.ligacaoAguaSituacao = helper.getLigacaoAguaSituacao();
		this.ligacaoEsgotoSituacao = helper.getLigacaoEsgotoSituacao();
		this.numeroHidrometro = helper.getNumeroHidrometro();
		this.hidrometroCapacidade = helper.getHidrometroCapacidade();
		this.hidrometroProtecao = helper.getHidrometroProtecao();
		this.hidrometroMarca = helper.getHidrometroMarca();
		this.outrasInformacoes = helper.getOutrasInformacoes();
		this.areaConstruida = helper.getAreaConstruida();
		this.pontosUtilizacao = helper.getPontosUtilizacao();
		this.moradores = helper.getMoradores();
		
		this.preencherSubcategorias(helper.getSubcategorias());
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
			return "";
	}
	
	public short getEconomiasR1() {
		return economiasR1;
	}

	public void setEconomiasR1(short economiasR1) {
		this.economiasR1 = economiasR1;
	}

	public short getEconomiasR2() {
		return economiasR2;
	}

	public void setEconomiasR2(short economiasR2) {
		this.economiasR2 = economiasR2;
	}

	public short getEconomiasR3() {
		return economiasR3;
	}

	public void setEconomiasR3(short economiasR3) {
		this.economiasR3 = economiasR3;
	}

	public short getEconomiasR4() {
		return economiasR4;
	}

	public void setEconomiasR4(short economiasR4) {
		this.economiasR4 = economiasR4;
	}

	public short getEconomiasC1() {
		return economiasC1;
	}

	public void setEconomiasC1(short economiasC1) {
		this.economiasC1 = economiasC1;
	}

	public short getEconomiasC2() {
		return economiasC2;
	}

	public void setEconomiasC2(short economiasC2) {
		this.economiasC2 = economiasC2;
	}

	public short getEconomiasC3() {
		return economiasC3;
	}

	public void setEconomiasC3(short economiasC3) {
		this.economiasC3 = economiasC3;
	}

	public short getEconomiasC4() {
		return economiasC4;
	}

	public void setEconomiasC4(short economiasC4) {
		this.economiasC4 = economiasC4;
	}

	public short getEconomiasI1() {
		return economiasI1;
	}

	public void setEconomiasI1(short economiasI1) {
		this.economiasI1 = economiasI1;
	}

	public short getEconomiasI2() {
		return economiasI2;
	}

	public void setEconomiasI2(short economiasI2) {
		this.economiasI2 = economiasI2;
	}

	public short getEconomiasI3() {
		return economiasI3;
	}

	public void setEconomiasI3(short economiasI3) {
		this.economiasI3 = economiasI3;
	}

	public short getEconomiasI4() {
		return economiasI4;
	}

	public void setEconomiasI4(short economiasI4) {
		this.economiasI4 = economiasI4;
	}
	
	public short getEconomiasP1() {
		return economiasP1;
	}

	public void setEconomiasP1(short economiasP1) {
		this.economiasP1 = economiasP1;
	}

	public short getEconomiasP2() {
		return economiasP2;
	}

	public void setEconomiasP2(short economiasP2) {
		this.economiasP2 = economiasP2;
	}

	public short getEconomiasP3() {
		return economiasP3;
	}

	public void setEconomiasP3(short economiasP3) {
		this.economiasP3 = economiasP3;
	}

	public short getEconomiasP4() {
		return economiasP4;
	}

	public void setEconomiasP4(short economiasP4) {
		this.economiasP4 = economiasP4;
	}

	public void preencherSubcategorias(List<ImovelSubcategoriaRetorno> subcategorias) {
		
		if (subcategorias != null) {
			for (ImovelSubcategoriaRetorno subcategoria : subcategorias) {
				if (subcategoria.getSubcategoria().isR1()) this.economiasR1 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isR2()) this.economiasR2 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isR3()) this.economiasR3 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isR4()) this.economiasR4 = subcategoria.getQuantidadeEconomias();
				
				else if (subcategoria.getSubcategoria().isC1()) this.economiasC1 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isC2()) this.economiasC2 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isC3()) this.economiasC3 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isC4()) this.economiasC4 = subcategoria.getQuantidadeEconomias();
				
				else if (subcategoria.getSubcategoria().isI1()) this.economiasI1 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isI2()) this.economiasI2 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isI3()) this.economiasI3 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isI4()) this.economiasI4 = subcategoria.getQuantidadeEconomias();
				
				else if (subcategoria.getSubcategoria().isP1()) this.economiasP1 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isP2()) this.economiasP2 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isP3()) this.economiasP3 = subcategoria.getQuantidadeEconomias();
				else if (subcategoria.getSubcategoria().isP4()) this.economiasP4 = subcategoria.getQuantidadeEconomias();
			}
		}
	}
	
	public boolean getIsResidencial() {
		return (this.economiasR1 > 0 || this.economiasR2 > 0 || this.economiasR3 > 0 || this.economiasR4 > 0);
	}
	
	public boolean getIsComercial() {
		return (this.economiasC1 > 0 || this.economiasC2 > 0 || this.economiasC3 > 0 || this.economiasC4 > 0);
	}
	
	public boolean getIsPublico() {
		return (this.economiasP1 > 0 || this.economiasP2 > 0 || this.economiasP3 > 0 || this.economiasP4 > 0);
	}
	
	public boolean getIsIndustrial() {
		return (this.economiasI1 > 0 || this.economiasI2 > 0 || this.economiasI3 > 0 || this.economiasI4 > 0);
	}
}
