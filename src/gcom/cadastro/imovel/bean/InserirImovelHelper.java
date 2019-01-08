package gcom.cadastro.imovel.bean;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class InserirImovelHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Imovel imovel;

	private Collection<ImovelSubcategoria> subcategorias;

	private Collection<ImovelRamoAtividade> ramosAtividades;

	@SuppressWarnings("rawtypes")
	private Collection endereco;

	private Collection<ClienteImovel> clientes;

	private Usuario usuario;

	private LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento;

	private Date dataInicioRelacaoUsuario;

	private Collection<ClienteImovel> colecaoClientesImoveisRemovidos;

	@SuppressWarnings("rawtypes")
	private Collection colecaoImovelSubcategoriasRemovidas;

	@SuppressWarnings("rawtypes")
	private Collection colecaoRamoAtividadesRemovidas;

	public InserirImovelHelper() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InserirImovelHelper(Imovel imovel, Collection subcategorias, Collection ramosAtividades, Collection endereco, Collection clientes,
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento, Usuario usuario) {

		this.ramosAtividades = ramosAtividades;
		this.imovel = imovel;
		this.subcategorias = subcategorias;
		this.endereco = endereco;
		this.clientes = clientes;
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
		this.usuario = usuario;

	}

	public Collection<ClienteImovel> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<ClienteImovel> clientes) {
		this.clientes = clientes;
	}

	@SuppressWarnings("rawtypes")
	public Collection getEndereco() {
		return endereco;
	}

	@SuppressWarnings("rawtypes")
	public void setEndereco(Collection endereco) {
		this.endereco = endereco;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LigacaoEsgotoEsgotamento getLigacaoEsgotoEsgotamento() {
		return ligacaoEsgotoEsgotamento;
	}

	public void setLigacaoEsgotoEsgotamento(LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento) {
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
	}

	public Date getDataInicioRelacaoUsuario() {
		return dataInicioRelacaoUsuario;
	}

	public void setDataInicioRelacaoUsuario(Date dataInicioRelacaoUsuario) {
		this.dataInicioRelacaoUsuario = dataInicioRelacaoUsuario;
	}

	public Collection<ImovelSubcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(Collection<ImovelSubcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<ClienteImovel> getColecaoClientesImoveisRemovidos() {
		return colecaoClientesImoveisRemovidos;
	}

	public void setColecaoClientesImoveisRemovidos(Collection<ClienteImovel> colecaoClientesImoveisRemovidos) {
		this.colecaoClientesImoveisRemovidos = colecaoClientesImoveisRemovidos;
	}

	@SuppressWarnings("rawtypes")
	public Collection getColecaoImovelSubcategoriasRemovidas() {
		return colecaoImovelSubcategoriasRemovidas;
	}

	@SuppressWarnings("rawtypes")
	public void setColecaoImovelSubcategoriasRemovidas(Collection colecaoImovelSubcategoriasRemovidas) {
		this.colecaoImovelSubcategoriasRemovidas = colecaoImovelSubcategoriasRemovidas;
	}

	public Collection<ImovelRamoAtividade> getRamosAtividades() {
		return ramosAtividades;
	}

	public void setRamosAtividades(Collection<ImovelRamoAtividade> ramosAtividades) {
		this.ramosAtividades = ramosAtividades;
	}

	@SuppressWarnings("rawtypes")
	public Collection getColecaoRamoAtividadesRemovidas() {
		return colecaoRamoAtividadesRemovidas;
	}

	@SuppressWarnings("rawtypes")
	public void setColecaoRamoAtividadesRemovidas(Collection colecaoRamoAtividadesRemovidas) {
		this.colecaoRamoAtividadesRemovidas = colecaoRamoAtividadesRemovidas;
	}

}
