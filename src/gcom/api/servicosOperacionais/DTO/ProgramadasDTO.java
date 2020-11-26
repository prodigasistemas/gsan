package gcom.api.servicosOperacionais.DTO;

import java.math.BigDecimal;
import java.util.Date;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.util.Util;

public class ProgramadasDTO {

	private Integer ordem_servico_id;
	private Integer imovel_id;
	private Integer imovel_localidade_id;
	private short situacao;
	private Date data_geracao;
	private String servico_tipo_descricao;
	private BigDecimal servico_tipo_valor;
	private String observacao;
	private String equipe_programacao;
	private int imovel_setor_comercial;
	private int imovel_quadra;
	private short imovel_lote;
	private short imovel_sublote;
	private String imovel_ligacao_agua;
	private String imovel_ligacao_esgoto;
	private String cliente_nome;
	private String numero_documento_cliente;
	private String endereco_formatado;
	
	public ProgramadasDTO(Integer ordem_servico_id, short situacao,	Date data_geracao, String servico_tipo_descricao,
			BigDecimal servico_tipo_valor, String observacao, String equipe_programacao, Integer imovel_id,
			Integer imovel_localidade_id, int imovel_setor_comercial, int imovel_quadra, short imovel_lote,
			short imovel_sublote, String imovel_ligacao_agua, String imovel_ligacao_esgoto, String cliente_nome,
			String cnpj, String cpf, LogradouroCep logradouroCep, String imovNumero, LogradouroBairro logradouroBairro, String complemento) {
		super();
		this.ordem_servico_id = ordem_servico_id;
		this.imovel_id = imovel_id;
		this.imovel_localidade_id = imovel_localidade_id;
		this.situacao = situacao;
		this.data_geracao = data_geracao;
		this.servico_tipo_descricao = servico_tipo_descricao;
		this.servico_tipo_valor = servico_tipo_valor;
		this.observacao = observacao;
		this.equipe_programacao = equipe_programacao;
		this.imovel_setor_comercial = imovel_setor_comercial;
		this.imovel_quadra = imovel_quadra;
		this.imovel_lote = imovel_lote;
		this.imovel_sublote = imovel_sublote;
		this.imovel_ligacao_agua = imovel_ligacao_agua;
		this.imovel_ligacao_esgoto = imovel_ligacao_esgoto;
		this.cliente_nome = cliente_nome;
		this.numero_documento_cliente = (cpf == null || cpf.isEmpty() ? Util.formatarCnpj(cnpj) : Util.formatarCpf(cpf));
		this.endereco_formatado = logradouroBairro.getLogradouro().getLogradouroTipo().getDescricao().trim() + 
								  " " + logradouroBairro.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada().trim() +
								  " " + logradouroBairro.getLogradouro().getNome().trim() + ", " + imovNumero + " " + complemento.trim() +
								  " " + logradouroBairro.getBairro().getNome().trim() + 
								  " - " + logradouroBairro.getBairro().getMunicipio().getNome().trim() + 
								  "-" + logradouroBairro.getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim() + 
								  " CEP: " +  Util.formatarCEP(logradouroCep.getCep().getCodigo().toString());
		
	} 
	
	public Integer getOrdem_servico_id() {
		return ordem_servico_id;
	}
	public void setOrdem_servico_id(Integer ordem_servico_id) {
		this.ordem_servico_id = ordem_servico_id;
	}
	
	public Integer getImovel_id() {
		return imovel_id;
	}
	public void setImovel_id(Integer imovel_id) {
		this.imovel_id = imovel_id;
	}
	public Integer getImovel_localidade_id() {
		return imovel_localidade_id;
	}
	public void setImovel_localidade_id(Integer imovel_localidade_id) {
		this.imovel_localidade_id = imovel_localidade_id;
	}
	public short getSituacao() {
		return situacao;
	}
	public void setSituacao(short situacao) {
		this.situacao = situacao;
	}
	public Date getData_geracao() {
		return data_geracao;
	}
	public void setData_geracao(Date data_geracao) {
		this.data_geracao = data_geracao;
	}
	public String getServico_tipo_descricao() {
		return servico_tipo_descricao;
	}
	public void setServico_tipo_descricao(String servico_tipo_descricao) {
		this.servico_tipo_descricao = servico_tipo_descricao;
	}
	public BigDecimal getServico_tipo_valor() {
		return servico_tipo_valor;
	}
	public void setServico_tipo_valor(BigDecimal servico_tipo_valor) {
		this.servico_tipo_valor = servico_tipo_valor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getEquipe_programacao() {
		return equipe_programacao;
	}
	public void setEquipe_programacao(String equipe_programacao) {
		this.equipe_programacao = equipe_programacao;
	}
	public int getImovel_setor_comercial() {
		return imovel_setor_comercial;
	}
	public void setImovel_setor_comercial(int imovel_setor_comercial) {
		this.imovel_setor_comercial = imovel_setor_comercial;
	}
	public int getImovel_quadra() {
		return imovel_quadra;
	}
	public void setImovel_quadra(int imovel_quadra) {
		this.imovel_quadra = imovel_quadra;
	}
	public short getImovel_lote() {
		return imovel_lote;
	}
	public void setImovel_lote(short imovel_lote) {
		this.imovel_lote = imovel_lote;
	}
	public short getImovel_sublote() {
		return imovel_sublote;
	}
	public void setImovel_sublote(short imovel_sublote) {
		this.imovel_sublote = imovel_sublote;
	}
	public String getImovel_ligacao_agua() {
		return imovel_ligacao_agua;
	}
	public void setImovel_ligacao_agua(String imovel_ligacao_agua) {
		this.imovel_ligacao_agua = imovel_ligacao_agua;
	}
	public String getImovel_ligacao_esgoto() {
		return imovel_ligacao_esgoto;
	}
	public void setImovel_ligacao_esgoto(String imovel_ligacao_esgoto) {
		this.imovel_ligacao_esgoto = imovel_ligacao_esgoto;
	}
	public String getCliente_nome() {
		return cliente_nome;
	}
	public void setCliente_nome(String cliente_nome) {
		this.cliente_nome = cliente_nome;
	}
	public String getNumero_documento_cliente() {
		return numero_documento_cliente;
	}
	public void setNumero_documento_cliente(String numero_documento_cliente) {
		this.numero_documento_cliente = numero_documento_cliente;
	}
	public String getEndereco_formatado() {
		return endereco_formatado;
	}
	public void setEndereco_formatado(String endereco_formatado) {
		this.endereco_formatado = endereco_formatado;
	}
	

}
