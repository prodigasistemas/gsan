package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;

import java.math.BigDecimal;
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

	public BigDecimal getNumeroIptu();
	
	public void setNumeroIptu(BigDecimal numeroIptu);

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

	public String getNomeEntrevistado();

	public void setNomeEntrevistado(String nomeEntrevistado);

	public Integer getIdLigacaoAguaSituacao();
	
	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao);
	
	public Integer getIdFonteAbastecimento();

    public void setIdFonteAbastecimento(Integer idFonteAbastecimento);
    
    public Integer getIdImovel();
    
    public void setIdImovel(Integer idImovel);
    
    public HidrometroProtecao getHidrometroProtecao();

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao);
    
}
