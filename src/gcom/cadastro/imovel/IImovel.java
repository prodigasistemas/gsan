package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;

import java.math.BigDecimal;
import java.util.Date;

public interface IImovel {
	
	public Integer getId() ;

	public void setId(Integer id);

	public short getSubLote();

	public void setSubLote(short subLote);

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

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao();

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao);

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico();

	public void setHidrometroInstalacaoHistorico(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico);

	public LigacaoAguaSituacao getLigacaoAguaSituacao();

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao);

	public LogradouroCep getLogradouroCep();

	public void setLogradouroCep(LogradouroCep logradouroCep);

	public String getNumeroMedidorEnergia();

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia);

	public String getInformacoesComplementares();

	public void setInformacoesComplementares(String informacoesComplementares);

	public FonteAbastecimento getFonteAbastecimento();

	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento);
	
	public LigacaoAgua getLigacaoAgua();

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua);
	
	public Date getUltimaAlteracao();

	public void setUltimaAlteracao(Date ultimaAlteracao);

	public int getNumeroQuadra();

	public void setNumeroQuadra(int numeroQuadra);

	public short getLote();

	public void setLote(short lote);

	public Integer getNumeroSequencialRota();

	public void setNumeroSequencialRota(Integer numeroSequencialRota);

	public Integer getIdCapacidadeHidrometro();

	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro);

	public Integer getIdMarcaHidrometro();

	public void setIdMarcaHidrometro(Integer idMarcaHidrometro);

	public Integer getIdProtecaoHidrometro();

	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro);

	public String getNumeroHidrometro();

	public void setNumeroHidrometro(String numeroHidrometro);

	public String getNomeEntrevistado();

	public void setNomeEntrevistado(String nomeEntrevistado);

	public Integer getIdMunicipio();

	public void setIdMunicipio(Integer idMunicipio);

	public String getNomeMunicipio();

	public void setNomeMunicipio(String nomeMunicipio);

	public String getDsUFSiglaMunicipio();

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio);

}
