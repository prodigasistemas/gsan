package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;
import gcom.relatorio.atendimentopublico.bean.RelatorioOSFiscalizacaoHelper;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Vivianne Sousa
 * @date 23/05/2011
 * 
 */
public class RelatorioOSFiscalizacaoBean implements RelatorioBean {
	
	private String inscricao;
	private String nomeClienteUsuario;
	private String grupo;
	private String matricula;
	private String dataEmissao;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String sequencial;
	private String ordemServico;
	private String dataValidade;
	private String rota;
	private String sequencialRota;
	private String mesAno;
	private String enderecoImovel;

	private String numeroOrdemExecutada;
	private String tipoServico;
	private String dataExecucao;
	private String motivoEncerramento;
	private String tipoPavimento;
	private String qtdeRecomposta;
	private String numeroHidrometro;
	private String leituraHidrometro;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioSituacaoEncontradaDetailBean;
	
	private JRBeanCollectionDataSource arrayJRDetail2;
	private ArrayList arrayRelatorioSituacaoEncontradaDetailBean2;
	
	private String inscricao2;
	private String nomeClienteUsuario2;
	private String grupo2;
	private String matricula2;
	private String dataEmissao2;
	private String categoriaRES2;
	private String categoriaPUB2;
	private String categoriaIND2;
	private String categoriaCOM2;
	private String situacaoLigacaoAgua2;
	private String situacaoLigacaoEsgoto2;
	private String sequencial2;
	private String ordemServico2;
	private String dataValidade2;
	private String rota2;
	private String sequencialRota2;
	private String mesAno2;
	private String enderecoImovel2;

	private String numeroOrdemExecutada2;
	private String tipoServico2;
	private String dataExecucao2;
	private String motivoEncerramento2;
	private String tipoPavimento2;
	private String qtdeRecomposta2;
	private String numeroHidrometro2;
	private String leituraHidrometro2;
	
	public RelatorioOSFiscalizacaoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RelatorioOSFiscalizacaoBean(String inscricao, String nomeClienteUsuario, String grupo, String matricula, String dataEmissao, String categoriaRES, String categoriaPUB, String categoriaIND, String categoriaCOM, String situacaoLigacaoAgua, String situacaoLigacaoEsgoto, String sequencial, String ordemServico, String dataValidade, String rota, String sequencialRota, String mesAno, String enderecoImovel, String numeroOrdemExecutada, String tipoServico, String dataExecucao, String motivoEncerramento, String tipoPavimento, String qtdeRecomposta, String numeroHidrometro, String leituraHidrometro, JRBeanCollectionDataSource arrayJRDetail, ArrayList arrayRelatorioSituacaoEncontradaDetailBean, JRBeanCollectionDataSource arrayJRDetail2, ArrayList arrayRelatorioSituacaoEncontradaDetailBean2, String inscricao2, String nomeClienteUsuario2, String grupo2, String matricula2, String dataEmissao2, String categoriaRES2, String categoriaPUB2, String categoriaIND2, String categoriaCOM2, String situacaoLigacaoAgua2, String situacaoLigacaoEsgoto2, String sequencial2, String ordemServico2, String dataValidade2, String rota2, String sequencialRota2, String mesAno2, String enderecoImovel2, String numeroOrdemExecutada2, String tipoServico2, String dataExecucao2, String motivoEncerramento2, String tipoPavimento2, String qtdeRecomposta2, String numeroHidrometro2, String leituraHidrometro2,
			Collection colecaoDetail, Collection colecaoDetail2) {
		super();
		// TODO Auto-generated constructor stub
		this.inscricao = inscricao;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.grupo = grupo;
		this.matricula = matricula;
		this.dataEmissao = dataEmissao;
		this.categoriaRES = categoriaRES;
		this.categoriaPUB = categoriaPUB;
		this.categoriaIND = categoriaIND;
		this.categoriaCOM = categoriaCOM;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.sequencial = sequencial;
		this.ordemServico = ordemServico;
		this.dataValidade = dataValidade;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		this.mesAno = mesAno;
		this.enderecoImovel = enderecoImovel;
		this.numeroOrdemExecutada = numeroOrdemExecutada;
		this.tipoServico = tipoServico;
		this.dataExecucao = dataExecucao;
		this.motivoEncerramento = motivoEncerramento;
		this.tipoPavimento = tipoPavimento;
		this.qtdeRecomposta = qtdeRecomposta;
		this.numeroHidrometro = numeroHidrometro;
		this.leituraHidrometro = leituraHidrometro;
		this.inscricao2 = inscricao2;
		this.nomeClienteUsuario2 = nomeClienteUsuario2;
		this.grupo2 = grupo2;
		this.matricula2 = matricula2;
		this.dataEmissao2 = dataEmissao2;
		this.categoriaRES2 = categoriaRES2;
		this.categoriaPUB2 = categoriaPUB2;
		this.categoriaIND2 = categoriaIND2;
		this.categoriaCOM2 = categoriaCOM2;
		this.situacaoLigacaoAgua2 = situacaoLigacaoAgua2;
		this.situacaoLigacaoEsgoto2 = situacaoLigacaoEsgoto2;
		this.sequencial2 = sequencial2;
		this.ordemServico2 = ordemServico2;
		this.dataValidade2 = dataValidade2;
		this.rota2 = rota2;
		this.sequencialRota2 = sequencialRota2;
		this.mesAno2 = mesAno2;
		this.enderecoImovel2 = enderecoImovel2;
		this.numeroOrdemExecutada2 = numeroOrdemExecutada2;
		this.tipoServico2 = tipoServico2;
		this.dataExecucao2 = dataExecucao2;
		this.motivoEncerramento2 = motivoEncerramento2;
		this.tipoPavimento2 = tipoPavimento2;
		this.qtdeRecomposta2 = qtdeRecomposta2;
		this.numeroHidrometro2 = numeroHidrometro2;
		this.leituraHidrometro2 = leituraHidrometro2;
		
		this.arrayRelatorioSituacaoEncontradaDetailBean = new ArrayList();
		this.arrayRelatorioSituacaoEncontradaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioSituacaoEncontradaDetailBean);
		
		this.arrayRelatorioSituacaoEncontradaDetailBean2 = new ArrayList();
		this.arrayRelatorioSituacaoEncontradaDetailBean2.addAll(colecaoDetail2);
		this.arrayJRDetail2 = new JRBeanCollectionDataSource(
				this.arrayRelatorioSituacaoEncontradaDetailBean2);
		
	}


	public RelatorioOSFiscalizacaoBean(RelatorioOSFiscalizacaoHelper helper,
			RelatorioOSFiscalizacaoHelper helper2,Collection colecaoDetailBean) {
		super();
		// TODO Auto-generated constructor stub
		this.inscricao = helper.getInscricao();
		this.nomeClienteUsuario = helper.getNomeClienteUsuario();
		this.grupo = helper.getGrupo();
		this.matricula = helper.getMatricula();
		this.dataEmissao = helper.getDataEmissao();
		this.categoriaRES = helper.getCategoriaRES();
		this.categoriaPUB = helper.getCategoriaPUB();
		this.categoriaIND = helper.getCategoriaIND();
		this.categoriaCOM = helper.getCategoriaCOM();
		this.situacaoLigacaoAgua = helper.getSituacaoLigacaoAgua();
		this.situacaoLigacaoEsgoto = helper.getSituacaoLigacaoEsgoto();
		this.sequencial = helper.getSequencial();
		this.ordemServico = helper.getOrdemServico();
		this.dataValidade = helper.getDataValidade();
		this.rota = helper.getRota();
		this.sequencialRota = helper.getSequencialRota();
		this.mesAno = helper.getMesAno();
		this.enderecoImovel = helper.getEnderecoImovel();
		this.numeroOrdemExecutada = helper.getNumeroOrdemExecutada();
		this.tipoServico = helper.getTipoServico();
		this.dataExecucao = helper.getDataExecucao();
		this.motivoEncerramento = helper.getMotivoEncerramento();
		this.tipoPavimento = helper.getTipoPavimento();
		this.qtdeRecomposta = helper.getQtdeRecomposta();
		this.numeroHidrometro = helper.getNumeroHidrometro();
		this.leituraHidrometro = helper.getLeituraHidrometro();
		
		this.arrayRelatorioSituacaoEncontradaDetailBean = new ArrayList();
		this.arrayRelatorioSituacaoEncontradaDetailBean.addAll(colecaoDetailBean);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioSituacaoEncontradaDetailBean);
		
		if(helper2 != null){
			this.inscricao2 = helper2.getInscricao();
			this.nomeClienteUsuario2 = helper2.getNomeClienteUsuario();
			this.grupo2 = helper2.getGrupo();
			this.matricula2 = helper2.getMatricula();
			this.dataEmissao2 = helper2.getDataEmissao();
			this.categoriaRES2 = helper2.getCategoriaRES();
			this.categoriaPUB2 = helper2.getCategoriaPUB();
			this.categoriaIND2 = helper2.getCategoriaIND();
			this.categoriaCOM2 = helper2.getCategoriaCOM();
			this.situacaoLigacaoAgua2 = helper2.getSituacaoLigacaoAgua();
			this.situacaoLigacaoEsgoto2 = helper2.getSituacaoLigacaoEsgoto();
			this.sequencial2 = helper2.getSequencial();
			this.ordemServico2 = helper2.getOrdemServico();
			this.dataValidade2 = helper2.getDataValidade();
			this.rota2 = helper2.getRota();
			this.sequencialRota2 = helper2.getSequencialRota();
			this.mesAno2 = helper2.getMesAno();
			this.enderecoImovel2 = helper2.getEnderecoImovel();
			this.numeroOrdemExecutada2 = helper2.getNumeroOrdemExecutada();
			this.tipoServico2 = helper2.getTipoServico();
			this.dataExecucao2 = helper2.getDataExecucao();
			this.motivoEncerramento2 = helper2.getMotivoEncerramento();
			this.tipoPavimento2 = helper2.getTipoPavimento();
			this.qtdeRecomposta2 = helper2.getQtdeRecomposta();
			this.numeroHidrometro2 = helper2.getNumeroHidrometro();
			this.leituraHidrometro2 = helper2.getLeituraHidrometro();
			
			this.arrayRelatorioSituacaoEncontradaDetailBean2 = new ArrayList();
			this.arrayRelatorioSituacaoEncontradaDetailBean2.addAll(colecaoDetailBean);
			this.arrayJRDetail2 = new JRBeanCollectionDataSource(
					this.arrayRelatorioSituacaoEncontradaDetailBean2);
		}
		
		
//		this.arrayRelatorioSituacaoEncontradaDetailBean = new ArrayList();
//		this.arrayRelatorioSituacaoEncontradaDetailBean.addAll(colecaoDetail);
//		this.arrayJRDetail = new JRBeanCollectionDataSource(
//				this.arrayRelatorioSituacaoEncontradaDetailBean);
//		
//		this.arrayRelatorioSituacaoEncontradaDetailBean2 = new ArrayList();
//		this.arrayRelatorioSituacaoEncontradaDetailBean2.addAll(colecaoDetail2);
//		this.arrayJRDetail2 = new JRBeanCollectionDataSource(
//				this.arrayRelatorioSituacaoEncontradaDetailBean2);
		
	}

	
	
	public String getCategoriaCOM() {
		return categoriaCOM;
	}
	public void setCategoriaCOM(String categoriaCOM) {
		this.categoriaCOM = categoriaCOM;
	}
	public String getCategoriaCOM2() {
		return categoriaCOM2;
	}
	public void setCategoriaCOM2(String categoriaCOM2) {
		this.categoriaCOM2 = categoriaCOM2;
	}
	public String getCategoriaIND() {
		return categoriaIND;
	}
	public void setCategoriaIND(String categoriaIND) {
		this.categoriaIND = categoriaIND;
	}
	public String getCategoriaIND2() {
		return categoriaIND2;
	}
	public void setCategoriaIND2(String categoriaIND2) {
		this.categoriaIND2 = categoriaIND2;
	}
	public String getCategoriaPUB() {
		return categoriaPUB;
	}
	public void setCategoriaPUB(String categoriaPUB) {
		this.categoriaPUB = categoriaPUB;
	}
	public String getCategoriaPUB2() {
		return categoriaPUB2;
	}
	public void setCategoriaPUB2(String categoriaPUB2) {
		this.categoriaPUB2 = categoriaPUB2;
	}
	public String getCategoriaRES() {
		return categoriaRES;
	}
	public void setCategoriaRES(String categoriaRES) {
		this.categoriaRES = categoriaRES;
	}
	public String getCategoriaRES2() {
		return categoriaRES2;
	}
	public void setCategoriaRES2(String categoriaRES2) {
		this.categoriaRES2 = categoriaRES2;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataEmissao2() {
		return dataEmissao2;
	}
	public void setDataEmissao2(String dataEmissao2) {
		this.dataEmissao2 = dataEmissao2;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public String getDataExecucao2() {
		return dataExecucao2;
	}
	public void setDataExecucao2(String dataExecucao2) {
		this.dataExecucao2 = dataExecucao2;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getDataValidade2() {
		return dataValidade2;
	}
	public void setDataValidade2(String dataValidade2) {
		this.dataValidade2 = dataValidade2;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getEnderecoImovel2() {
		return enderecoImovel2;
	}
	public void setEnderecoImovel2(String enderecoImovel2) {
		this.enderecoImovel2 = enderecoImovel2;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getGrupo2() {
		return grupo2;
	}
	public void setGrupo2(String grupo2) {
		this.grupo2 = grupo2;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getInscricao2() {
		return inscricao2;
	}
	public void setInscricao2(String inscricao2) {
		this.inscricao2 = inscricao2;
	}
	public String getLeituraHidrometro() {
		return leituraHidrometro;
	}
	public void setLeituraHidrometro(String leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}
	public String getLeituraHidrometro2() {
		return leituraHidrometro2;
	}
	public void setLeituraHidrometro2(String leituraHidrometro2) {
		this.leituraHidrometro2 = leituraHidrometro2;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMatricula2() {
		return matricula2;
	}
	public void setMatricula2(String matricula2) {
		this.matricula2 = matricula2;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getMesAno2() {
		return mesAno2;
	}
	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getMotivoEncerramento2() {
		return motivoEncerramento2;
	}
	public void setMotivoEncerramento2(String motivoEncerramento2) {
		this.motivoEncerramento2 = motivoEncerramento2;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNomeClienteUsuario2() {
		return nomeClienteUsuario2;
	}
	public void setNomeClienteUsuario2(String nomeClienteUsuario2) {
		this.nomeClienteUsuario2 = nomeClienteUsuario2;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getNumeroHidrometro2() {
		return numeroHidrometro2;
	}
	public void setNumeroHidrometro2(String numeroHidrometro2) {
		this.numeroHidrometro2 = numeroHidrometro2;
	}
	public String getNumeroOrdemExecutada() {
		return numeroOrdemExecutada;
	}
	public void setNumeroOrdemExecutada(String numeroOrdemExecutada) {
		this.numeroOrdemExecutada = numeroOrdemExecutada;
	}
	public String getNumeroOrdemExecutada2() {
		return numeroOrdemExecutada2;
	}
	public void setNumeroOrdemExecutada2(String numeroOrdemExecutada2) {
		this.numeroOrdemExecutada2 = numeroOrdemExecutada2;
	}
	public String getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getOrdemServico2() {
		return ordemServico2;
	}
	public void setOrdemServico2(String ordemServico2) {
		this.ordemServico2 = ordemServico2;
	}
	public String getQtdeRecomposta() {
		return qtdeRecomposta;
	}
	public void setQtdeRecomposta(String qtdeRecomposta) {
		this.qtdeRecomposta = qtdeRecomposta;
	}
	public String getQtdeRecomposta2() {
		return qtdeRecomposta2;
	}
	public void setQtdeRecomposta2(String qtdeRecomposta2) {
		this.qtdeRecomposta2 = qtdeRecomposta2;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getRota2() {
		return rota2;
	}
	public void setRota2(String rota2) {
		this.rota2 = rota2;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSequencial2() {
		return sequencial2;
	}
	public void setSequencial2(String sequencial2) {
		this.sequencial2 = sequencial2;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public String getSequencialRota2() {
		return sequencialRota2;
	}
	public void setSequencialRota2(String sequencialRota2) {
		this.sequencialRota2 = sequencialRota2;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoAgua2() {
		return situacaoLigacaoAgua2;
	}
	public void setSituacaoLigacaoAgua2(String situacaoLigacaoAgua2) {
		this.situacaoLigacaoAgua2 = situacaoLigacaoAgua2;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getSituacaoLigacaoEsgoto2() {
		return situacaoLigacaoEsgoto2;
	}
	public void setSituacaoLigacaoEsgoto2(String situacaoLigacaoEsgoto2) {
		this.situacaoLigacaoEsgoto2 = situacaoLigacaoEsgoto2;
	}
	public String getTipoPavimento() {
		return tipoPavimento;
	}
	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}
	public String getTipoPavimento2() {
		return tipoPavimento2;
	}
	public void setTipoPavimento2(String tipoPavimento2) {
		this.tipoPavimento2 = tipoPavimento2;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getTipoServico2() {
		return tipoServico2;
	}
	public void setTipoServico2(String tipoServico2) {
		this.tipoServico2 = tipoServico2;
	}
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}
	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}
	public JRBeanCollectionDataSource getArrayJRDetail2() {
		return arrayJRDetail2;
	}
	public void setArrayJRDetail2(JRBeanCollectionDataSource arrayJRDetail2) {
		this.arrayJRDetail2 = arrayJRDetail2;
	}
	public ArrayList getArrayRelatorioSituacaoEncontradaDetailBean() {
		return arrayRelatorioSituacaoEncontradaDetailBean;
	}
	public void setArrayRelatorioSituacaoEncontradaDetailBean(
			ArrayList arrayRelatorioSituacaoEncontradaDetailBean) {
		this.arrayRelatorioSituacaoEncontradaDetailBean = arrayRelatorioSituacaoEncontradaDetailBean;
	}
	public ArrayList getArrayRelatorioSituacaoEncontradaDetailBean2() {
		return arrayRelatorioSituacaoEncontradaDetailBean2;
	}
	public void setArrayRelatorioSituacaoEncontradaDetailBean2(
			ArrayList arrayRelatorioSituacaoEncontradaDetailBean2) {
		this.arrayRelatorioSituacaoEncontradaDetailBean2 = arrayRelatorioSituacaoEncontradaDetailBean2;
	}
	
}
