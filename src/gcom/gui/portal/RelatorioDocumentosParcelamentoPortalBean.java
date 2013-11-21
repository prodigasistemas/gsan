package gcom.gui.portal;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos
 * 
 * @author Diogo Peixoto
 * @date 08/07/2011
 */
public class RelatorioDocumentosParcelamentoPortalBean implements RelatorioBean {

	//------------------------------Campos do relatório referente ao termo------------------------------//
	private String matriculaImovel;
	private String nomeCliente;
	private String endereco;
	private String cpfCnpj;
	private String telefone;
	private String dataParcelamento;
	private String faturasEmAberto;
	private String servicosACobrar;
	private String atualizacaoMonetaria;
	private String jurosMora;
	private String multa;
	private String guiaPagamento;
	private String parcelamentoACobrar;
	private String totalDebitos;
	private String descontoAcrescimo;
	private String descontosAntiguidade;
	private String descontoInatividade;
	private String creditosARealizar;
	private String totalDescontos;
	private String valorASerNegociado;
	private String valorEntrada;
	private String numeroParcelas;
	private String valorParcela;
	private String valorASerParcelado;
	private String solicitacaoRestabelecimento;
	private String municipioData;
	private String idParcelamento;
	private String unidadeUsuario;
	private String idFuncionario;
	private String nomeClienteParcelamento;
	private String cpfClienteParcelamento;
	private String pagina;
	private JRBeanCollectionDataSource arrayJRDetalhamento;
	private ArrayList arrayRelatorioParcelamentoDetalhamentoBean;
	private String codigoEmpresaFebraban;
	private String rgCliente;
	private String nomeUsuario;
	private String matriculaUsuario;
	private String municipio;
	private String mesAnoInicioParcelamento;
	private String mesAnoFinalParcelamento;
	private String valorJuros;
	private String valorDescontoSancoesRegulamentares;
	private String valorDescontoTarifaSocial;
	private String cpfUsuario;
	private String nomeDiretorComercial;
	private String cpfDiretorComercial;
	private String profissao;
	private String nomeDevedor;
	private String cnpjDevedor;
	private String enderecoDevedor;
	private String telefoneDevedor;
	private Short indicadorPessoaJuridica;
	private Integer maiorAnoMesReferenciaDocumentos = new Integer(0);
	private Integer menorAnoMesReferenciaDocumentos = new Integer(0);    
	private String nomeUsuarioParcelamento;
	private String bairro;
	private String codigoRota;
	//------------------------------Fim dos campos do relatório referente ao termo------------------------------//


	//------------------------------Campos do relatório referente à guia de pagamento------------------------------//
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean;
	private String matricula ;
	private String dataVencimento ;
	private String inscricao ; 
	private String enderecoImovel ; 
	private String enderecoClienteResponsavel ;
	private String representacaoNumericaCodBarraFormatada ;
	private String representacaoNumericaCodBarraSemDigito ;
	private String dataValidade ;
	private String valorDebito;
	private String idGuiaPagamento;
	private String observacao;
	private String cpfCnpjCliente;

	/* Ficha de Compensação */
	private String idImovel;
	private String nossoNumero;
	private String sacadoParte01;
	private String sacadoParte02;
	private String enderecoImovelSacado;
	private JRBeanCollectionDataSource arrayJRDetailSub;

	private String subRelatorio;
	//------------------------------Fim do relatório referente à guia de pagamento------------------------------//


	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean
	 */
	public RelatorioDocumentosParcelamentoPortalBean(String matriculaImovel,
			String nomeCliente,
			String endereco, 
			String cpfCnpj,
			String telefone, 
			String dataParcelamento, 
			String faturasEmAberto,
			String servicosACobrar, 
			String atualizacaoMonetaria,
			String jurosMora, 
			String multa, 
			String guiaPagamento,
			String parcelamentoACobrar, 
			String totalDebitos,
			String descontoAcrescimo, 
			String descontosAntiguidade,
			String descontoInatividade, 
			String creditosARealizar,
			String totalDescontos, 
			String valorASerNegociado,
			String valorEntrada, 
			String numeroParcelas, 
			String valorParcela,
			String valorASerParcelado, 
			String solicitacaoRestabelecimento,
			String municipioData, 
			String idParcelamento, 
			String unidadeUsuario,
			String idFuncionario, 
			String nomeClienteParcelamento,
			String cpfClienteParcelamento, 
			String pagina,
			Collection colecaoRelatorioParcelamentoDetalhamentosBean,
			String codigoEmpresaFebraban,
			String rgCliente,
			String nomeUsuario,
			String matriculaUsuario,
			String municipio,
			String mesAnoInicioParcelamento,
			String mesAnoFinalParcelamento,
			String valorJuros,
			String valorDescontoSancoesRegulamentares,
			String valorDescontoTarifaSocial,
			String cpfUsuario,
			String nomeDiretorComercial,
			String cpfDiretorComercial,
			String profissao,
			String nomeDevedor,    
			String cnpjDevedor,    
			String enderecoDevedor,    
			String telefoneDevedor,
			Short indicadorPessoaJuridica,
			Integer maiorAnoMesReferenciaDocumentos,
			Integer menorAnoMesReferenciaDocumentos,
			String nomeUsuarioParcelamento){
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipioData = municipioData;
		this.idParcelamento = idParcelamento;
		this.unidadeUsuario = unidadeUsuario;
		this.idFuncionario = idFuncionario;
		this.nomeClienteParcelamento = nomeClienteParcelamento;
		this.cpfClienteParcelamento = cpfClienteParcelamento;
		this.pagina = pagina;
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
		this.rgCliente = rgCliente;
		this.nomeUsuario = nomeUsuario;
		this.matriculaUsuario = matriculaUsuario;
		this.municipio = municipio;
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;

		this.arrayRelatorioParcelamentoDetalhamentoBean = new ArrayList();
		this.arrayRelatorioParcelamentoDetalhamentoBean
		.addAll(colecaoRelatorioParcelamentoDetalhamentosBean);
		this.arrayJRDetalhamento = new JRBeanCollectionDataSource(
				this.arrayRelatorioParcelamentoDetalhamentoBean);

		this.valorJuros = valorJuros;

		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
		this.cpfUsuario = cpfUsuario;
		this.nomeDiretorComercial = nomeDiretorComercial;
		this.cpfDiretorComercial = cpfDiretorComercial;
		this.profissao = profissao;
		this.nomeDevedor = nomeDevedor;        
		this.cnpjDevedor = cnpjDevedor;        
		this.enderecoDevedor = enderecoDevedor;        
		this.telefoneDevedor = telefoneDevedor;    
		this.indicadorPessoaJuridica = indicadorPessoaJuridica;
		if(maiorAnoMesReferenciaDocumentos == null){
			this.maiorAnoMesReferenciaDocumentos = new Integer(0);
		}else{
			this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
		}

		if(menorAnoMesReferenciaDocumentos == null){
			this.menorAnoMesReferenciaDocumentos = new Integer(0);
		}else{
			this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
		}
		this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
	}

	public void inserirDadosRelatorioEmitirGuia(Collection colecaoDetail,
			String matricula,
			String nomeCliente,
			String dataVencimento,
			String inscricao,
			String enderecoImovel, 
			String enderecoClienteResponsavel,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,
			String dataValidade,
			String valorDebito,
			String idGuiaPagamento,
			String observacao,
			String cpfCnpjCliente,
			String idImovel,
			String nossoNumero,
			String sacadoParte01,
			String sacadoParte02,
			String subRelatorio,
			Collection colecaoDetailSub) {

		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);

		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.valorDebito = valorDebito;
		this.idGuiaPagamento = idGuiaPagamento;
		this.observacao = observacao;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.idImovel = idImovel;
		this.nossoNumero = nossoNumero;
		this.sacadoParte01 = sacadoParte01;
		this.sacadoParte02 = sacadoParte02;
		this.subRelatorio = subRelatorio;

		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetailSub);
		this.arrayJRDetailSub = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public JRBeanCollectionDataSource getArrayJRDetalhamento() {
		return arrayJRDetalhamento;
	}

	public void setArrayJRDetalhamento(
			JRBeanCollectionDataSource arrayJRDetalhamento) {
		this.arrayJRDetalhamento = arrayJRDetalhamento;
	}

	public ArrayList getArrayRelatorioParcelamentoDetalhamentoBean() {
		return arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public void setArrayRelatorioEfetuarAnaliseParcelamentoDetalhamentoBean(
			ArrayList arrayRelatorioParcelamentoDetalhamentoBean) {
		this.arrayRelatorioParcelamentoDetalhamentoBean = arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public String getAtualizacaoMonetaria() {
		return atualizacaoMonetaria;
	}

	public void setAtualizacaoMonetaria(String atualizacaoMonetaria) {
		this.atualizacaoMonetaria = atualizacaoMonetaria;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCreditosARealizar() {
		return creditosARealizar;
	}

	public void setCreditosARealizar(String creditosARealizar) {
		this.creditosARealizar = creditosARealizar;
	}

	public String getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getDescontoAcrescimo() {
		return descontoAcrescimo;
	}

	public void setDescontoAcrescimo(String descontoAcrescimos) {
		this.descontoAcrescimo = descontoAcrescimos;
	}

	public String getDescontoInatividade() {
		return descontoInatividade;
	}

	public void setDescontoInatividade(String descontoInatividade) {
		this.descontoInatividade = descontoInatividade;
	}

	public String getDescontosAntiguidade() {
		return descontosAntiguidade;
	}

	public void setDescontosAntiguidade(String descontosAntiguidade) {
		this.descontosAntiguidade = descontosAntiguidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFaturasEmAberto() {
		return faturasEmAberto;
	}

	public void setFaturasEmAberto(String faturasEmAberto) {
		this.faturasEmAberto = faturasEmAberto;
	}

	public String getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(String guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getJurosMora() {
		return jurosMora;
	}

	public void setJurosMora(String jurosMora) {
		this.jurosMora = jurosMora;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getMunicipioData() {
		return municipioData;
	}

	public void setMunicipioData(String municipioData) {
		this.municipioData = municipioData;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getParcelamentoACobrar() {
		return parcelamentoACobrar;
	}

	public void setParcelamentoACobrar(String parcelamentoACobrar) {
		this.parcelamentoACobrar = parcelamentoACobrar;
	}

	public String getServicosACobrar() {
		return servicosACobrar;
	}

	public void setServicosACobrar(String servicosACobrar) {
		this.servicosACobrar = servicosACobrar;
	}

	public String getSolicitacaoRestabelecimento() {
		return solicitacaoRestabelecimento;
	}

	public void setSolicitacaoRestabelecimento(
			String solicitacaoRestabelecimento) {
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(String totalDebitos) {
		this.totalDebitos = totalDebitos;
	}

	public String getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(String totalDescontos) {
		this.totalDescontos = totalDescontos;
	}

	public String getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(String unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

	public String getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(String valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public String getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(String valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public String getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	/**
	 * @return Retorna o campo cpfClienteParcelamento.
	 */
	public String getCpfClienteParcelamento() {
		return cpfClienteParcelamento;
	}

	/**
	 * @param cpfClienteParcelamento O cpfClienteParcelamento a ser setado.
	 */
	public void setCpfClienteParcelamento(String cpfClienteParcelamento) {
		this.cpfClienteParcelamento = cpfClienteParcelamento;
	}

	/**
	 * @return Retorna o campo nomeClienteParcelamento.
	 */
	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	/**
	 * @param nomeClienteParcelamento O nomeClienteParcelamento a ser setado.
	 */
	public void setNomeClienteParcelamento(String nomeClienteParcelamento) {
		this.nomeClienteParcelamento = nomeClienteParcelamento;
	}

	/**
	 * @param arrayRelatorioParcelamentoDetalhamentoBean O arrayRelatorioParcelamentoDetalhamentoBean a ser setado.
	 */
	public void setArrayRelatorioParcelamentoDetalhamentoBean(
			ArrayList arrayRelatorioParcelamentoDetalhamentoBean) {
		this.arrayRelatorioParcelamentoDetalhamentoBean = arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public String getCodigoEmpresaFebraban() {
		return codigoEmpresaFebraban;
	}

	public void setCodigoEmpresaFebraban(String codigoEmpresaFebraban) {
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getMatriculaUsuario() {
		return matriculaUsuario;
	}

	public void setMatriculaUsuario(String matriculaUsuario) {
		this.matriculaUsuario = matriculaUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMesAnoFinalParcelamento() {
		return mesAnoFinalParcelamento;
	}

	public void setMesAnoFinalParcelamento(String mesAnoFinalParcelamento) {
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
	}

	public String getMesAnoInicioParcelamento() {
		return mesAnoInicioParcelamento;
	}

	public void setMesAnoInicioParcelamento(String mesAnoInicioParcelamento) {
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
	}

	public String getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(String valorJuros) {
		this.valorJuros = valorJuros;
	}

	public String getValorDescontoSancoesRegulamentares() {
		return valorDescontoSancoesRegulamentares;
	}

	public void setValorDescontoSancoesRegulamentares(
			String valorDescontoSancoesRegulamentares) {
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
	}

	public String getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(String valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public String getParagrafoDevedor(){

		String retorno = "";

		if ( indicadorPessoaJuridica == 1 ){
			if ( this.getNomeCliente() != null && !this.getNomeCliente().equals( "" ) ){
				retorno = "          <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DEVEDOR(a): " + 
				this.getNomeCliente() + "</style>";
			}

			if ( this.getProfissao() != null  && !this.getProfissao().equals( "" )){
				retorno += "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">, " + this.getProfissao() + "</style>";
			}

			if ( this.getRgCliente() != null  && !this.getRgCliente().equals( "" )){
				retorno += " Carteira de Identidade n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getRgCliente() + "</style>,";

			}

			if ( this.getCpfClienteParcelamento() != null  && !this.getCpfClienteParcelamento().equals( "" )){
				retorno += " C.P.F. n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getCpfClienteParcelamento() + "</style>";
			}

			if ( this.getEndereco() != null  && !this.getEndereco().equals( "" )){
				retorno += ", capaz, residente e domiciliado na " +
				"<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEndereco() + "</style>,";    			
			}

			if ( this.getMunicipio() != null && !this.getMunicipio().equals( "" ) ){
				retorno += " Município de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getMunicipio() +"</style>";
			}

			if ( this.getTelefone() != null && !this.getTelefone().equals( "" ) ){
				retorno += ", telefone: <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getTelefone() + "</style>";    			
			}

			retorno += ", Estado de Roraima.";

			return retorno;

		} else {

			retorno = "          <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DEVEDOR: " + this.getNomeDevedor() + ",</style>" +
			"Pessoa Jurídica de Direito Privado, inscrita no CNPJ/MF so o N°.<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" +
			this.getCnpjDevedor() + "</style>";

			if ( this.getMunicipio() != null ){
				retorno += " com sede na cidade de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getMunicipio() +
				" - RR</style>";
			}

			if ( this.getEndereco() != null ){
				retorno += " na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEnderecoDevedor() + "</style>"; 
			}

			if ( this.getNomeDevedor() != null ){
				retorno +=  
					" representada neste ato por seu legítimo procurador ou representante legal, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getNomeCliente() + "</style>";
			}    		

			if ( this.getProfissao() != null  && !this.getProfissao().equals( "" )){
				retorno += "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">, " + this.getProfissao() + "</style>";
			}

			if ( this.getEndereco() != null ){
				retorno +=
					" ,capaz, residente e domiciliado na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEndereco() + "</style>";
			}

			if ( this.getRgCliente() != null  && !this.getRgCliente().equals( "" )){
				retorno += ", portador Carteira de Identidade n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getRgCliente() + "</style>";

			}

			if ( this.getCpfClienteParcelamento() != null  && !this.getCpfClienteParcelamento().equals( "" )){
				retorno += ", C.P.F. n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getCpfClienteParcelamento() + "</style>";
			}

			if ( this.getTelefone() != null && !this.getTelefone().equals( "" ) ){
				retorno += ", telefone: <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getTelefone() + "</style>";    			
			}

			retorno += "."; 

			return retorno;  		
		}
	}

	public Short getIndicadorPessoaJuridica() {
		return indicadorPessoaJuridica;
	}

	public void setIndicadorPessoaJuridica(Short indicadorPessoaJuridica) {
		this.indicadorPessoaJuridica = indicadorPessoaJuridica;
	}

	public String getMaiorAnoMesReferenciaDocumentos() {
		return Util.formatarAnoMesParaMesAno( maiorAnoMesReferenciaDocumentos );
	}

	public void setMaiorAnoMesReferenciaDocumentos(
			Integer maiorAnoMesReferenciaDocumentos) {
		this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
	}

	public Integer getMenorAnoMesReferenciaDocumentos() {
		return menorAnoMesReferenciaDocumentos;
	}

	public void setMenorAnoMesReferenciaDocumentos(
			Integer menorAnoMesReferenciaDocumentos) {
		this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
	}

	public String getNomeUsuarioParcelamento() {
		return nomeUsuarioParcelamento;
	}

	public void setNomeUsuarioParcelamento(String nomeUsuarioParcelamento) {
		this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorioEmitirGuiaPagamentoDetailBean() {
		return arrayRelatorioEmitirGuiaPagamentoDetailBean;
	}

	public void setArrayRelatorioEmitirGuiaPagamentoDetailBean(
			ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean) {
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = arrayRelatorioEmitirGuiaPagamentoDetailBean;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte02() {
		return sacadoParte02;
	}

	public void setSacadoParte02(String sacadoParte02) {
		this.sacadoParte02 = sacadoParte02;
	}

	public String getEnderecoImovelSacado() {
		return enderecoImovelSacado;
	}

	public void setEnderecoImovelSacado(String enderecoImovelSacado) {
		this.enderecoImovelSacado = enderecoImovelSacado;
	}

	public JRBeanCollectionDataSource getArrayJRDetailSub() {
		return arrayJRDetailSub;
	}

	public void setArrayJRDetailSub(JRBeanCollectionDataSource arrayJRDetailSub) {
		this.arrayJRDetailSub = arrayJRDetailSub;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}
	public String getCnpjDevedor() {
		return cnpjDevedor;
	}

	public void setCnpjDevedor(String cnpjDevedor) {
		this.cnpjDevedor = cnpjDevedor;
	}

	public String getEnderecoDevedor() {
		return enderecoDevedor;
	}

	public void setEnderecoDevedor(String enderecoDevedor) {
		this.enderecoDevedor = enderecoDevedor;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getTelefoneDevedor() {
		return telefoneDevedor;
	}

	public void setTelefoneDevedor(String telefoneDevedor) {
		this.telefoneDevedor = telefoneDevedor;
	}

	public String getCpfDiretorComercial() {
		return cpfDiretorComercial;
	}

	public void setCpfDiretorComercial(String cpfDiretorComercial) {
		this.cpfDiretorComercial = cpfDiretorComercial;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
}