package gcom.api.ordemservico.dto;

import java.math.BigDecimal;
import java.util.Date;

import gcom.util.FormatoData;
import gcom.util.Util;

public class ProgramadasDTO {

	private Integer id;
	private Integer situacao;
	private String dataGeracao;
	private String servicoTipoDescricao;
	private BigDecimal servicoTipoValor;
	private Integer operacao;
	private String observacao;
	private String dataProgramacao;
	private String equipeProgramacao;
	private ImovelDTO imovel;
	private HidrometroDTO hidrometro;

	public ProgramadasDTO(
			Integer id,
			Integer situacao,
			Date dataGeracao,
			String servicoTipoDescricao,
			BigDecimal servicoTipoValor,
			String observacao,
			Date dataProgramacao,
			String equipeProgramacao) {
		
		super();
		
		this.id = id;
		this.situacao = situacao;
		this.dataGeracao = Util.formatarData(dataGeracao, FormatoData.AMERICANO_COM_TRACO);
		this.servicoTipoDescricao = servicoTipoDescricao;
		this.servicoTipoValor = servicoTipoValor;
		this.observacao = observacao;
		this.dataProgramacao = Util.formatarData(dataProgramacao, FormatoData.AMERICANO_COM_TRACO);
		this.equipeProgramacao = equipeProgramacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getServicoTipoDescricao() {
		return servicoTipoDescricao;
	}

	public void setServicoTipoDescricao(String servicoTipoDescricao) {
		this.servicoTipoDescricao = servicoTipoDescricao;
	}

	public BigDecimal getServicoTipoValor() {
		return servicoTipoValor;
	}

	public void setServicoTipoValor(BigDecimal servicoTipoValor) {
		this.servicoTipoValor = servicoTipoValor;
	}

	public Integer getOperacao() {
		return operacao;
	}

	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public String getEquipeProgramacao() {
		return equipeProgramacao;
	}

	public void setEquipeProgramacao(String equipeProgramacao) {
		this.equipeProgramacao = equipeProgramacao;
	}

	public ImovelDTO getImovel() {
		return imovel;
	}

	public void setImovel(ImovelDTO imovel) {
		this.imovel = imovel;
	}

	public HidrometroDTO getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(HidrometroDTO hidrometro) {
		this.hidrometro = hidrometro;
	}

//	private Integer imovel_id;
//	private Integer imovel_localidade_id;
//	private int imovel_setor_comercial;
//	private int imovel_quadra;
//	private short imovel_lote;
//	private short imovel_sublote;
//	private String imovel_ligacao_agua;
//	private String imovel_ligacao_esgoto;
//	private String cliente_nome;
//	private String numero_documento_cliente;
//	private String endereco_formatado;
//	
//	
//	public ProgramadasDTO(Integer ordem_servico_id, short situacao,	Date data_geracao, String servico_tipo_descricao,
//			BigDecimal servico_tipo_valor, String observacao, String equipe_programacao, Integer imovel_id,
//			Integer imovel_localidade_id, int imovel_setor_comercial, int imovel_quadra, short imovel_lote,
//			short imovel_sublote, String imovel_ligacao_agua, String imovel_ligacao_esgoto, String cliente_nome,
//			String cnpj, String cpf, LogradouroCep logradouroCep, String imovNumero, LogradouroBairro logradouroBairro, String complemento) {
//		super();
//		this.id = ordem_servico_id;
//		this.imovel_id = imovel_id;
//		this.imovel_localidade_id = imovel_localidade_id;
//		this.situacao = situacao;
//		this.dataGeracao = data_geracao;
//		this.servicoTipoDescricao = servico_tipo_descricao;
//		this.servicoTipoValor = servico_tipo_valor;
//		this.observacao = observacao;
//		this.equipeProgramacao = equipe_programacao;
//		this.imovel_setor_comercial = imovel_setor_comercial;
//		this.imovel_quadra = imovel_quadra;
//		this.imovel_lote = imovel_lote;
//		this.imovel_sublote = imovel_sublote;
//		this.imovel_ligacao_agua = imovel_ligacao_agua;
//		this.imovel_ligacao_esgoto = imovel_ligacao_esgoto;
//		this.cliente_nome = cliente_nome;
//		this.numero_documento_cliente = (cpf == null || cpf.isEmpty() ? Util.formatarCnpj(cnpj) : Util.formatarCpf(cpf));
//		this.endereco_formatado = logradouroBairro.getLogradouro().getLogradouroTipo().getDescricao().trim() + 
//								  " " + logradouroBairro.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada().trim() +
//								  " " + logradouroBairro.getLogradouro().getNome().trim() + ", " + imovNumero + " " + complemento.trim() +
//								  " " + logradouroBairro.getBairro().getNome().trim() + 
//								  " - " + logradouroBairro.getBairro().getMunicipio().getNome().trim() + 
//								  "-" + logradouroBairro.getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim() + 
//								  " CEP: " +  Util.formatarCEP(logradouroCep.getCep().getCodigo().toString());
//		
//	} 

}
