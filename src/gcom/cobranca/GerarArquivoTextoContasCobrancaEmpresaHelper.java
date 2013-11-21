/**
 * 
 */
/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rômulo Aurélio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */

package gcom.cobranca;

import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GerarArquivoTextoContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idComandoEmpresaCobrancaConta;

	private Integer idEmpresa;

	private String nomeEmpresa;

	private Integer codigoSetorComercialInicial;

	private Integer codigoSetorComercialFinal;

	private BigDecimal valorMinimoConta;

	/** nullable persistent field */
	private BigDecimal valorMaximoConta;

	/** nullable persistent field */
	private Integer referenciaContaInicial;

	/** nullable persistent field */
	private Integer referenciaContaFinal;

	/** nullable persistent field */
	private Date dataVencimentoContaInicial;

	/** nullable persistent field */
	private Date dataVencimentoContaFinal;

	/** nullable persistent field */
	private Date dataExecucao;

	/** nullable persistent field */
	private Integer idImovel;

	/** nullable persistent field */
	private Integer idCliente;

	/** nullable persistent field */
	private String nomeCliente;

	/** nullable persistent field */
	private Integer idLocalidadeInicial;

	/** nullable persistent field */
	private Integer idLocalidadeFinal;

	/** nullable persistent field */
	private Integer idUnidadeNegocio;

	/** nullable persistent field */
	private String nomeUnidadeNegocio;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	private Integer qtdeTotalContasCobranca;

	private BigDecimal valorTotalContasCobranca;

	private Integer qtdeContasCriterioComando;

	private BigDecimal valorContasCriterioComando;

	private Integer idCobrancaConta;

	private Integer idFaturamentoGrupo;

	private Integer idLocalidade;

	private String nomeLocalidade;

	private Short codigoRota;

	private Integer numeroSequencialRota;

	private String nomeClienteConta;

	private String nomeLogradouro;

	private String numeroImovel;

	private String complementoEndereco;

	private String codigoCep;

	private String nomeBairro;

	private String telefone;

	private String cpf;

	private String cnpj;

	private String rg;

	private Integer anoMesReferencia;

	private Date dataVencimento;

	private BigDecimal valorLigacaoAgua;

	private BigDecimal valorLigacaoEsgoto;

	private BigDecimal valorServicos;

	private BigDecimal valorCreditos;

	private BigDecimal valorFatura;

	private Integer anoControle;

	private Integer controle;

	private String codigoSetorComercial;

	private int numeroQuadra;

	private Short numeroLote;

	private Short numeroSublote;

	private Integer idClienteTipo;

	private Conta conta;

	private String nomeAbreviadoCliente;
	
	private String tipoLogradouro;
	
	// Campos adicionados para o layout 2

	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;

	private Integer quantidadeContas;
	
	private Short codigoLayoutTxt;

	private List<Conta> colecaoConta;

	private Integer idOrdemServico;
	
	private String nomeMunicipio;
	
	public String getNomeAbreviadoCliente() {
		return nomeAbreviadoCliente;
	}

	public void setNomeAbreviadoCliente(String nomeAbreviadoCliente) {
		this.nomeAbreviadoCliente = nomeAbreviadoCliente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Construtor de GerarArquivoTextoContasCobrancaEmpresaHelper
	 * 
	 * @param idComandoEmpresaCobrancaConta
	 * @param empresa
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @param valorMinimoConta
	 * @param valorMaximoConta
	 * @param referenciaContaInicial
	 * @param referenciaContaFinal
	 * @param dataVencimentoContaInicial
	 * @param dataVencimentoContaFinal
	 * @param dataExecucao
	 * @param imovel
	 * @param cliente
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param unidadeNegocio
	 * @param ultimaAlteracao
	 * @param qtdeTotalContasCobranca
	 * @param valorTotalContasCobranca
	 */
	public GerarArquivoTextoContasCobrancaEmpresaHelper(
			Integer idComandoEmpresaCobrancaConta, Integer idEmpresa,
			String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta,
			BigDecimal valorMaximoConta, Integer referenciaContaInicial,
			Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel,
			Integer idCliente, String nomeCliente, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idUnidadeNegocio,
			String nomeUnidadeNegocio, Integer qtdeTotalContasCobranca,
			BigDecimal valorTotalContasCobranca, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
		this.valorTotalContasCobranca = valorTotalContasCobranca;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Construtor de GerarArquivoTextoContasCobrancaEmpresaHelper
	 * 
	 * @param empresa
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @param valorMinimoConta
	 * @param valorMaximoConta
	 * @param referenciaContaInicial
	 * @param referenciaContaFinal
	 * @param dataVencimentoContaInicial
	 * @param dataVencimentoContaFinal
	 * @param dataExecucao
	 * @param imovel
	 * @param cliente
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param unidadeNegocio
	 * @param ultimaAlteracao
	 * @param qtdeTotalContasCobranca
	 * @param valorTotalContasCobranca
	 * @param qtdeContasCriterioComando
	 * @param valorContasCriterioComando
	 */
	public GerarArquivoTextoContasCobrancaEmpresaHelper(
			Integer idComandoEmpresaCobrancaConta, Integer idEmpresa,
			String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta,
			BigDecimal valorMaximoConta, Integer referenciaContaInicial,
			Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel,
			Integer idCliente, String nomeCliente, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idUnidadeNegocio,
			String nomeUnidadeNegocio, Integer qtdeTotalContasCobranca,
			BigDecimal valorTotalContasCobranca,
			Integer qtdeContasCriterioComando,
			BigDecimal valorContasCriterioComando, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
		this.valorTotalContasCobranca = valorTotalContasCobranca;
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
		this.valorContasCriterioComando = valorContasCriterioComando;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public GerarArquivoTextoContasCobrancaEmpresaHelper() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(
			Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataVencimentoContaFinal() {
		return dataVencimentoContaFinal;
	}

	public void setDataVencimentoContaFinal(Date dataVencimentoContaFinal) {
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
	}

	public Date getDataVencimentoContaInicial() {
		return dataVencimentoContaInicial;
	}

	public void setDataVencimentoContaInicial(Date dataVencimentoContaInicial) {
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
	}

	public Integer getQtdeContasCriterioComando() {
		return qtdeContasCriterioComando;
	}

	public void setQtdeContasCriterioComando(Integer qtdeContasCriterioComando) {
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
	}

	public Integer getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}

	public void setQtdeTotalContasCobranca(Integer qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}

	public Integer getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	public void setReferenciaContaFinal(Integer referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	public Integer getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	public void setReferenciaContaInicial(Integer referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorContasCriterioComando() {
		return valorContasCriterioComando;
	}

	public void setValorContasCriterioComando(
			BigDecimal valorContasCriterioComando) {
		this.valorContasCriterioComando = valorContasCriterioComando;
	}

	public BigDecimal getValorMaximoConta() {
		return valorMaximoConta;
	}

	public void setValorMaximoConta(BigDecimal valorMaximoConta) {
		this.valorMaximoConta = valorMaximoConta;
	}

	public BigDecimal getValorMinimoConta() {
		return valorMinimoConta;
	}

	public void setValorMinimoConta(BigDecimal valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public BigDecimal getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}

	public void setValorTotalContasCobranca(BigDecimal valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public Integer getIdComandoEmpresaCobrancaConta() {
		return idComandoEmpresaCobrancaConta;
	}

	public void setIdComandoEmpresaCobrancaConta(
			Integer idComandoEmpresaCobrancaConta) {
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Integer getAnoControle() {
		return anoControle;
	}

	public void setAnoControle(Integer anoControle) {
		this.anoControle = anoControle;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Integer getControle() {
		return controle;
	}

	public void setControle(Integer controle) {
		this.controle = controle;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	public Integer getIdCobrancaConta() {
		return idCobrancaConta;
	}

	public void setIdCobrancaConta(Integer idCobrancaConta) {
		this.idCobrancaConta = idCobrancaConta;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeClienteConta() {
		return nomeClienteConta;
	}

	public void setNomeClienteConta(String nomeClienteConta) {
		this.nomeClienteConta = nomeClienteConta;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public Short getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public Short getNumeroSublote() {
		return numeroSublote;
	}

	public void setNumeroSublote(Short numeroSublote) {
		this.numeroSublote = numeroSublote;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public BigDecimal getValorLigacaoAgua() {
		return valorLigacaoAgua;
	}

	public void setValorLigacaoAgua(BigDecimal valorLigacaoAgua) {
		this.valorLigacaoAgua = valorLigacaoAgua;
	}

	public BigDecimal getValorLigacaoEsgoto() {
		return valorLigacaoEsgoto;
	}

	public void setValorLigacaoEsgoto(BigDecimal valorLigacaoEsgoto) {
		this.valorLigacaoEsgoto = valorLigacaoEsgoto;
	}

	public BigDecimal getValorServicos() {
		return valorServicos;
	}

	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = valorServicos;
	}
	
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Short getCodigoLayoutTxt() {
		return codigoLayoutTxt;
	}

	public void setCodigoLayoutTxt(Short codigoLayoutTxt) {
		this.codigoLayoutTxt = codigoLayoutTxt;
	}

	public List<Conta> getColecaoConta() {
		return colecaoConta;
	}

	public void setColecaoConta(List<Conta> colecaoConta) {
		this.colecaoConta = colecaoConta;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	

	/**
	 * Construtor de GerarArquivoTextoContasCobrancaEmpresaHelper
	 * 
	 * @param idComandoEmpresaCobrancaConta
	 * @param empresa
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @param valorMinimoConta
	 * @param valorMaximoConta
	 * @param referenciaContaInicial
	 * @param referenciaContaFinal
	 * @param dataVencimentoContaInicial
	 * @param dataVencimentoContaFinal
	 * @param dataExecucao
	 * @param imovel
	 * @param cliente
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param unidadeNegocio
	 * @param ultimaAlteracao
	 * @param qtdeTotalContasCobranca
	 * @param valorTotalContasCobranca
	 */
	public GerarArquivoTextoContasCobrancaEmpresaHelper(
			Integer idComandoEmpresaCobrancaConta, Integer idEmpresa,
			String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta,
			BigDecimal valorMaximoConta, Integer referenciaContaInicial,
			Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel,
			Integer idCliente, String nomeCliente, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idUnidadeNegocio,
			String nomeUnidadeNegocio,  Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
}
