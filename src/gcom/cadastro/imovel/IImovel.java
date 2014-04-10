package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;

import java.util.Date;

public interface IImovel {
	
	public Integer getId() ;

	public void setId(Integer id);

	public String getNumeroImovel();

	public void setNumeroImovel(String numeroImovel);

	public String getComplementoEndereco();

	public void setComplementoEndereco(String complementoEndereco);

	public Short getNumeroPontosUtilizacao();

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao);

	public Short getNumeroMorador();

	public void setNumeroMorador(Short numeroMorador);

	public String getNumeroIptu();
	
	public void setNumeroIptu(String numeroIptu);

	public String getCoordenadaX();

	public void setCoordenadaX(String coordenadaX);

	public String getCoordenadaY();

	public void setCoordenadaY(String coordenadaY);

	public LigacaoAguaSituacao getLigacaoAguaSituacao();

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao);

	public String getNumeroMedidorEnergia();

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia);

	public String getInformacoesComplementares();

	public void setInformacoesComplementares(String informacoesComplementares);

	public FonteAbastecimento getFonteAbastecimento();

	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento);
	
	public Date getUltimaAlteracao();

	public void setUltimaAlteracao(Date ultimaAlteracao);

	public Integer getIdProtecaoHidrometro();

	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro);

	public String getNumeroHidrometro();

	public void setNumeroHidrometro(String numeroHidrometro);

	public String getTipoEntrevistado();

	public void setTipoEntrevistado(String tipoEntrevistado);

	public Integer getIdLigacaoAguaSituacao();
	
	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao);
	
	public Integer getIdFonteAbastecimento();

    public void setIdFonteAbastecimento(Integer idFonteAbastecimento);
    
    public Integer getIdImovel();
    
    public void setIdImovel(Integer idImovel);
    
    public HidrometroProtecao getHidrometroProtecao();

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao);

	public Integer getTipoOperacao();
	
	public void setTipoOperacao(Integer tipoOperacao);
	
	public Integer getCodigoMunicipio();
	
	public void setCodigoMunicipio(Integer codigoMunicipio);
	
	public String getNomeMunicipio();
	
	public void setNomeMunicipio(String nomeMunicipio);
	
	public Integer getLogradouroTipo();
	
	public void setLogradouroTipo(Integer logradouroTipo);
	
	public String getCodigoLogradouro();
	
	public void setCodigoLogradouro(String codigoLogradouro);
	
	public String getDescricaoLogradouro();
	
	public void setDescricaoLogradouro(String descricaoLogradouro);
	
	public String getNomeBairro();
	
	public void setNomeBairro(String nomeBairro);
	
	public Integer getCodigoCep();
	
	public void setCodigoCep(Integer codigoCep);
	
	public Integer getIdLocalidade();
	
	public void setIdLocalidade(Integer idLocalidade);
	
	public int getCodigoSetorComercial();
	
	public void setCodigoSetorComercial(int codigoSetorComercial);
	
	public int getNumeroQuadra();
	
	public void setNumeroQuadra(int numeroQuadra);
}
