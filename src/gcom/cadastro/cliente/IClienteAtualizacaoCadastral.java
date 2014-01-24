package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.util.Date;

public interface IClienteAtualizacaoCadastral {

	public abstract Integer getIdCliente();

	public abstract void setIdCliente(Integer idCliente);

	public abstract Integer getIdImovel();

	public abstract void setIdImovel(Integer idImovel);

	public abstract String getNomeCliente();

	public abstract void setNomeCliente(String nomeCliente);

	public abstract Integer getIdClienteTipo();

	public abstract void setIdClienteTipo(Integer idClienteTipo);

	public abstract String getRg();

	public abstract void setRg(String rg);

	public abstract Date getDataEmissaoRg();

	public abstract void setDataEmissaoRg(Date dataEmissaoRg);

	public abstract Date getDataNascimento();

	public abstract void setDataNascimento(Date dataNascimento);

	public abstract Integer getIdProfissao();

	public abstract void setIdProfissao(Integer idProfissao);

	public abstract Integer getIdPessoaSexo();

	public abstract void setIdPessoaSexo(Integer idPessoaSexo);

	public abstract String getCpfCnpj();

	public abstract void setCpfCnpj(String cpfCnpj);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract Short getIndicadorUso();

	public abstract void setIndicadorUso(Short indicadorUso);

	public abstract Date getUltimaAlteracao();

	public abstract void setUltimaAlteracao(Date ultimaAlteracao);

	public abstract String getNomeMae();

	public abstract void setNomeMae(String nomeMae);

	public abstract Integer getIdEnderecoTipo();

	public abstract void setIdEnderecoTipo(Integer idEnderecoTipo);

	public abstract Integer getIdLogradouro();

	public abstract void setIdLogradouro(Integer idLogradouro);

	public abstract String getDescricaoLogradouro();

	public abstract void setDescricaoLogradouro(String descricaoLogradouro);

	public abstract Integer getCodigoCep();

	public abstract void setCodigoCep(Integer codigoCep);

	public abstract Integer getIdBairro();

	public abstract void setIdBairro(Integer idBairro);

	public abstract String getNomeBairro();

	public abstract void setNomeBairro(String nomeBairro);

	public abstract Integer getIdEnderecoReferencia();

	public abstract void setIdEnderecoReferencia(Integer idEnderecoReferencia);

	public abstract String getNumeroImovel();

	public abstract void setNumeroImovel(String numeroImovel);

	public abstract String getComplementoEndereco();

	public abstract void setComplementoEndereco(String complementoEndereco);

	public abstract Integer getCnae();

	public abstract void setCnae(Integer cnae);

	public abstract Integer getIdClienteRelacaoTipo();

	public abstract void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo);

	public abstract String getDsLogradouroTipo();

	public abstract void setDsLogradouroTipo(String dsLogradouroTipo);

	public abstract String getDsLogradouroTitulo();

	public abstract void setDsLogradouroTitulo(String dsLogradouroTitulo);

	public abstract Integer getIdLogradouroTipo();

	public abstract void setIdLogradouroTipo(Integer idLogradouroTipo);

	public abstract Integer getIdLogradouroTitulo();

	public abstract void setIdLogradouroTitulo(Integer idLogradouroTitulo);

	public abstract Filtro retornaFiltro();

	public abstract String[] retornaCamposChavePrimaria();

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getDsAbreviadaOrgaoExpedidorRg();

	public abstract void setDsAbreviadaOrgaoExpedidorRg(String dsAbreviadaOrgaoExpedidorRg);

	public abstract String getDsUFSiglaMunicipio();

	public abstract void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio);

	public abstract String getDsUFSiglaOrgaoExpedidorRg();

	public abstract void setDsUFSiglaOrgaoExpedidorRg(String dsUFSiglaOrgaoExpedidorRg);

	public abstract Integer getIdMunicipio();

	public abstract void setIdMunicipio(Integer idMunicipio);

	public abstract Integer getIdUinidadeFederacao();

	public abstract void setIdUinidadeFederacao(Integer idUinidadeFederacao);

	public abstract String getNomeMunicipio();

	public abstract void setNomeMunicipio(String nomeMunicipio);

	public abstract Integer getIdRamoAtividade();

	public abstract void setIdRamoAtividade(Integer idRamoAtividade);

}