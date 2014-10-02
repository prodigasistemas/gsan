package gcom.cadastro;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * @author Hugo Leonardo
 * @created 02/03/2011
 */
@ControleAlteracao()
public class ContaBraile extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	private String nomeCliente;
	private String cpfCliente;
	private String cnpjCliente;
	private String email;
	private String nomeSolicitante;
	private String cpfSolicitante;
	private String rg;
	private OrgaoExpedidorRg orgaoExpeditor;
	private UnidadeFederacao unidadeFederacao;
	private Date dataExpedicao;
	private String telefoneContato;
	private RegistroAtendimento registroAtendimento;
	private Date ultimaAlteracao;
	
	public ContaBraile(){
		
	}

	public ContaBraile(Imovel imovel, String nomeCliente, String cpfCliente, String cnpjCliente,
			String email, String nomeSolicitante, String cpfSolicitante, 
			String rg, OrgaoExpedidorRg orgaoExpeditor, UnidadeFederacao unidadeFederacao, 
			Date dataExpedicao) {
		
		super();
		this.imovel = imovel;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.email = email;
		this.nomeSolicitante = nomeSolicitante;
		this.cpfSolicitante = cpfSolicitante;
		this.rg = rg;
		this.orgaoExpeditor = orgaoExpeditor;
		this.unidadeFederacao = unidadeFederacao;
		this.dataExpedicao = dataExpedicao;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	@Override
	public Date getUltimaAlteracao() {
		
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		
		return null;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public OrgaoExpedidorRg getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(OrgaoExpedidorRg orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

}
