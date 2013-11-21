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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de 
 * consultar dados registro SPC ou SERASA.
 * 
 * @author Yara Taciane de Souza
 * @date 23/01/2008
 */
public class ConsultarDadosRegistroActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativadorRegistroTipo;
	private String idNegativadorMovimentoReg;
	
	//---------------------------------------
	//SPC
	//---------------------------------------
	
	//Tipo Header
	private String negativador;	
	private String tipoMovimento;	
	//conteudo do registro
	private String tipoRegistroCodigo;
	private String tipoRegistroDescricao;
	private String operacao;
	private String dataMovimento;
	private String sequencialRemessa;
	private String entidade;
	private String associado;
	private String dataMovimentoArquivo;
	private String unidadeNegocio;
	private String numeroVersao;
	private String codigoRetorno;
	private String sequencialRetorno;	
	//dados do retorno do registro
	private String indicadorAceitacao;
	private String ocorrenciasRetorno;
	private String codigo;
	private String descricaoMotivoRetorno;	
   //-----------------------------------------
   //Tipo Detalhe-Consumidor
	private String pracaConcessao;	
	private String nomeRazao;	
	private String tipoDocumentoCodigo;	
	private String tipoDocumentoDescricao;
	private String cpfCnpj;	
	private String rg;	
	private String dataNascimento;	
	private String filiacao;	
	private String endereco;	
	private String numero;	
	private String complemento;	
	private String bairro;	
	private String cep;	
	private String cidade;	
	private String uf;	
	private String foneDDD;	
	private String foneNumero;	
	private String sequencialRegistro;	
   //------------------------------------------
   //Tipo Detalhe-SPC
	private String codigoOperacao;	
	private String compradorFiadorAvalista;	
	private String dataVencimentoDebito;	
	private String dataRegistro;	
	private String valorDebito;	
	private String contrato; //matrícula do imóvel
	private String naturezaInclusao;
	private String motivoExclusao;
   //------------------------------------------	
	//Tipo Trailler
	private String totalRegistros;
	//-----------------------------------------
	private Collection collNegativadorMovimentoRegRetMot;
	
	
	//-------------------------------------------
	//SERASA
	//-------------------------------------------
	private String cnpj;
	private String dddTelefoneContato;
	private String telefoneContato;
	private String ramalTelefoneContato;
	private String nomeContato;
	private String identificacaoArquivo;
	private String diferencialRemessa;
	private String codigoEnvio;
	private String descricaoCodigoEnvio;
	private String cpfContratante;
	private String dataOcorrencia;
	private String dataTerminoContrato;
	private String codigoNaturezaOperacao;
	private String codigoPracaEmbratel;
	private String tipoPessoaPrincipal;
	private String cpfCnpjPrincipal;
	private String tipoPrimeiroDocumentoPrincipal;
	private String motivoBaixa;
	private String tipoSegundoDocumentoPrincipal;
	private String rgPrincipal;
	private String UfDoRgPrincipal;
	private String tipoPessoaCoobrigado;
	private String tipoPrimeiroDocumentoCoobrigado;
	private String cpfCnpjCoobrigado;
	private String tipoSegundoDocumentoCoobrigado;
	private String rgCoobrigado;
	private String UfDoRgCoobrigado;
	private String nomeDevedor;
	private String nomePai;
	private String nomeMae;
	private String municipio;
	private String nossoNumero;
	private String dddTelefoneDevedor;
	private String telefoneDevedor;
	private String dataVencimentoDebitoMaisAntigo;
	private String valorToralCompromisso;
	
	private String indicadorCorrecao;
	
	
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);

		this.idNegativadorRegistroTipo = "";	
		this.idNegativadorMovimentoReg = "";
//		-------------------------------------------------------
		//SERASA
		//-----------------------------------------------------		
		//Tipo Header
		this.negativador= "";	
		this.tipoMovimento= "";	
		//conteudo do registro
		this.tipoRegistroCodigo="";
		this.tipoRegistroDescricao="";
		this.operacao= "";
		this.dataMovimento= "";
		this.sequencialRemessa= "";
		this.entidade= "";
		this.associado= "";
		this.dataMovimentoArquivo= "";
		this.unidadeNegocio= "";
		this.numeroVersao= "";
		this.codigoRetorno= "";
		this.sequencialRetorno= "";	
		//dados do retorno do registro
		this.indicadorAceitacao= "";
		this.ocorrenciasRetorno= "";
		this.codigo= "";
		this.descricaoMotivoRetorno= "";	
	   //-----------------------------------------
	   //Tipo Detalhe-Consumidor
		this.pracaConcessao= "";	
		this.nomeRazao= "";	
		this.tipoDocumentoCodigo= "";	
		this.tipoDocumentoDescricao= "";	
		this.cpfCnpj= "";	
		this.rg= "";	
		this.dataNascimento= "";	
		this.filiacao= "";	
		this.endereco= "";	
		this.numero= "";	
		this.complemento= "";	
		this.bairro= "";	
		this.cep= "";	
		this.cidade= "";	
		this.uf= "";	
		this.foneDDD= "";	
		this.foneNumero= "";	
		this.sequencialRegistro = "";
	   //------------------------------------------
	   //Tipo Detalhe-SPC
		this.codigoOperacao= "";	
		this.compradorFiadorAvalista= "";	
		this.dataVencimentoDebito= "";	
		this.dataRegistro= "";	
		this.valorDebito= "";	
		this.contrato= ""; //matrícula do imóvel
		this.naturezaInclusao= "";
		this.motivoExclusao = "";
	   //------------------------------
		//Tipo Trailler
		this.totalRegistros="";
	   //-------------------------------	

		this.collNegativadorMovimentoRegRetMot = new ArrayList();
		//-------------------------------------------------------
		//SERASA
		//-------------------------------------------------------
		this.cnpj= "";
		this.dddTelefoneContato= "";
		this.telefoneContato= "";
		this.ramalTelefoneContato= "";
		this.nomeContato= "";
		this.identificacaoArquivo= "";
		this.diferencialRemessa= "";
		this.codigoEnvio ="";
		this.dataOcorrencia = "";
		
		//--------------------------------------------------------
		//Detalhe
		this.cpfContratante= "";
		this.dataOcorrencia= "";
		this.dataTerminoContrato= "";
		this.codigoNaturezaOperacao= "";
		this.codigoPracaEmbratel= "";
		this.tipoPessoaPrincipal= "";
		this.cpfCnpjPrincipal= "";
		this.tipoPrimeiroDocumentoPrincipal= "";
		this.motivoBaixa= "";
		this.tipoSegundoDocumentoPrincipal= "";
		this.rgPrincipal= "";
		this.UfDoRgPrincipal= "";
		this.tipoPessoaCoobrigado= "";
		this.tipoPrimeiroDocumentoCoobrigado= "";
		this.cpfCnpjCoobrigado= "";
		this.tipoSegundoDocumentoCoobrigado= "";
		this.rgCoobrigado= "";
		this.UfDoRgCoobrigado= "";
		this.nomeDevedor= "";
		this.nomePai= "";
		this.nomeMae= "";
		this.municipio= "";
		this.nossoNumero= "";
		this.dddTelefoneDevedor= "";
		this.telefoneDevedor= "";
		this.dataVencimentoDebitoMaisAntigo= "";
		this.valorToralCompromisso= "";
		this.descricaoCodigoEnvio= "";
		
		this.indicadorCorrecao = "";

	}
	/**
	 * @return Retorna o campo associado.
	 */
	public String getAssociado() {
		return associado;
	}
	/**
	 * @param associado O associado a ser setado.
	 */
	public void setAssociado(String associado) {
		this.associado = associado;
	}
	/**
	 * @return Retorna o campo bairro.
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro O bairro a ser setado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return Retorna o campo cep.
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * @param cep O cep a ser setado.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * @return Retorna o campo cidade.
	 */
	public String getCidade() {
		return cidade;
	}
	/**
	 * @param cidade O cidade a ser setado.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	/**
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Retorna o campo codigoOperacao.
	 */
	public String getCodigoOperacao() {
		return codigoOperacao;
	}
	/**
	 * @param codigoOperacao O codigoOperacao a ser setado.
	 */
	public void setCodigoOperacao(String codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
	}
	/**
	 * @return Retorna o campo codigoRetorno.
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	/**
	 * @param codigoRetorno O codigoRetorno a ser setado.
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	/**
	 * @return Retorna o campo complemento.
	 */
	public String getComplemento() {
		return complemento;
	}
	/**
	 * @param complemento O complemento a ser setado.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	/**
	 * @return Retorna o campo compradorFiadorAvalista.
	 */
	public String getCompradorFiadorAvalista() {
		return compradorFiadorAvalista;
	}
	/**
	 * @param compradorFiadorAvalista O compradorFiadorAvalista a ser setado.
	 */
	public void setCompradorFiadorAvalista(String compradorFiadorAvalista) {
		this.compradorFiadorAvalista = compradorFiadorAvalista;
	}
	/**
	 * @return Retorna o campo contrato.
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato O contrato a ser setado.
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * @param cpfCnpj O cpfCnpj a ser setado.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	/**
	 * @return Retorna o campo dataMovimento.
	 */
	public String getDataMovimento() {
		return dataMovimento;
	}
	/**
	 * @param dataMovimento O dataMovimento a ser setado.
	 */
	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	/**
	 * @return Retorna o campo dataMovimentoArquivo.
	 */
	public String getDataMovimentoArquivo() {
		return dataMovimentoArquivo;
	}
	/**
	 * @param dataMovimentoArquivo O dataMovimentoArquivo a ser setado.
	 */
	public void setDataMovimentoArquivo(String dataMovimentoArquivo) {
		this.dataMovimentoArquivo = dataMovimentoArquivo;
	}
	/**
	 * @return Retorna o campo dataNascimento.
	 */
	public String getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento O dataNascimento a ser setado.
	 */
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	/**
	 * @return Retorna o campo dataRegistro.
	 */
	public String getDataRegistro() {
		return dataRegistro;
	}
	/**
	 * @param dataRegistro O dataRegistro a ser setado.
	 */
	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	/**
	 * @return Retorna o campo dataVencimentoDebito.
	 */
	public String getDataVencimentoDebito() {
		return dataVencimentoDebito;
	}
	/**
	 * @param dataVencimentoDebito O dataVencimentoDebito a ser setado.
	 */
	public void setDataVencimentoDebito(String dataVencimentoDebito) {
		this.dataVencimentoDebito = dataVencimentoDebito;
	}
	/**
	 * @return Retorna o campo descricaoMotivoRetorno.
	 */
	public String getDescricaoMotivoRetorno() {
		return descricaoMotivoRetorno;
	}
	/**
	 * @param descricaoMotivoRetorno O descricaoMotivoRetorno a ser setado.
	 */
	public void setDescricaoMotivoRetorno(String descricaoMotivoRetorno) {
		this.descricaoMotivoRetorno = descricaoMotivoRetorno;
	}
	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return Retorna o campo entidade.
	 */
	public String getEntidade() {
		return entidade;
	}
	/**
	 * @param entidade O entidade a ser setado.
	 */
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}
	/**
	 * @return Retorna o campo filiacao.
	 */
	public String getFiliacao() {
		return filiacao;
	}
	/**
	 * @param filiacao O filiacao a ser setado.
	 */
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}
	/**
	 * @return Retorna o campo foneDDD.
	 */
	public String getFoneDDD() {
		return foneDDD;
	}
	/**
	 * @param foneDDD O foneDDD a ser setado.
	 */
	public void setFoneDDD(String foneDDD) {
		this.foneDDD = foneDDD;
	}
	/**
	 * @return Retorna o campo foneNumero.
	 */
	public String getFoneNumero() {
		return foneNumero;
	}
	/**
	 * @param foneNumero O foneNumero a ser setado.
	 */
	public void setFoneNumero(String foneNumero) {
		this.foneNumero = foneNumero;
	}
	/**
	 * @return Retorna o campo idNegativadorRegistroTipo.
	 */
	public String getIdNegativadorRegistroTipo() {
		return idNegativadorRegistroTipo;
	}
	/**
	 * @param idNegativadorRegistroTipo O idNegativadorRegistroTipo a ser setado.
	 */
	public void setIdNegativadorRegistroTipo(String idNegativadorRegistroTipo) {
		this.idNegativadorRegistroTipo = idNegativadorRegistroTipo;
	}
	/**
	 * @return Retorna o campo indicadorAceitacao.
	 */
	public String getIndicadorAceitacao() {
		return indicadorAceitacao;
	}
	/**
	 * @param indicadorAceitacao O indicadorAceitacao a ser setado.
	 */
	public void setIndicadorAceitacao(String indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}
	/**
	 * @return Retorna o campo naturezaInclusao.
	 */
	public String getNaturezaInclusao() {
		return naturezaInclusao;
	}
	/**
	 * @param naturezaInclusao O naturezaInclusao a ser setado.
	 */
	public void setNaturezaInclusao(String naturezaInclusao) {
		this.naturezaInclusao = naturezaInclusao;
	}
	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}
	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}
	/**
	 * @return Retorna o campo nomeRazao.
	 */
	public String getNomeRazao() {
		return nomeRazao;
	}
	/**
	 * @param nomeRazao O nomeRazao a ser setado.
	 */
	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}
	/**
	 * @return Retorna o campo numero.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero O numero a ser setado.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return Retorna o campo numeroVersao.
	 */
	public String getNumeroVersao() {
		return numeroVersao;
	}
	/**
	 * @param numeroVersao O numeroVersao a ser setado.
	 */
	public void setNumeroVersao(String numeroVersao) {
		this.numeroVersao = numeroVersao;
	}
	/**
	 * @return Retorna o campo ocorrenciasRetorno.
	 */
	public String getOcorrenciasRetorno() {
		return ocorrenciasRetorno;
	}
	/**
	 * @param ocorrenciasRetorno O ocorrenciasRetorno a ser setado.
	 */
	public void setOcorrenciasRetorno(String ocorrenciasRetorno) {
		this.ocorrenciasRetorno = ocorrenciasRetorno;
	}
	/**
	 * @return Retorna o campo operacao.
	 */
	public String getOperacao() {
		return operacao;
	}
	/**
	 * @param operacao O operacao a ser setado.
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	/**
	 * @return Retorna o campo pracaConcessao.
	 */
	public String getPracaConcessao() {
		return pracaConcessao;
	}
	/**
	 * @param pracaConcessao O pracaConcessao a ser setado.
	 */
	public void setPracaConcessao(String pracaConcessao) {
		this.pracaConcessao = pracaConcessao;
	}
	/**
	 * @return Retorna o campo rg.
	 */
	public String getRg() {
		return rg;
	}
	/**
	 * @param rg O rg a ser setado.
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}
	/**
	 * @return Retorna o campo sequencialRemessa.
	 */
	public String getSequencialRemessa() {
		return sequencialRemessa;
	}
	/**
	 * @param sequencialRemessa O sequencialRemessa a ser setado.
	 */
	public void setSequencialRemessa(String sequencialRemessa) {
		this.sequencialRemessa = sequencialRemessa;
	}
	/**
	 * @return Retorna o campo sequencialRetorno.
	 */
	public String getSequencialRetorno() {
		return sequencialRetorno;
	}
	/**
	 * @param sequencialRetorno O sequencialRetorno a ser setado.
	 */
	public void setSequencialRetorno(String sequencialRetorno) {
		this.sequencialRetorno = sequencialRetorno;
	}	

	/**
	 * @return Retorna o campo tipoDocumentoCodigo.
	 */
	public String getTipoDocumentoCodigo() {
		return tipoDocumentoCodigo;
	}
	/**
	 * @param tipoDocumentoCodigo O tipoDocumentoCodigo a ser setado.
	 */
	public void setTipoDocumentoCodigo(String tipoDocumentoCodigo) {
		this.tipoDocumentoCodigo = tipoDocumentoCodigo;
	}
	/**
	 * @return Retorna o campo tipoDocumentoDescricao.
	 */
	public String getTipoDocumentoDescricao() {
		return tipoDocumentoDescricao;
	}
	/**
	 * @param tipoDocumentoDescricao O tipoDocumentoDescricao a ser setado.
	 */
	public void setTipoDocumentoDescricao(String tipoDocumentoDescricao) {
		this.tipoDocumentoDescricao = tipoDocumentoDescricao;
	}
	/**
	 * @return Retorna o campo tipoMovimento.
	 */
	public String getTipoMovimento() {
		return tipoMovimento;
	}
	/**
	 * @param tipoMovimento O tipoMovimento a ser setado.
	 */
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	
	/**
	 * @return Retorna o campo tipoRegistroCodigo.
	 */
	public String getTipoRegistroCodigo() {
		return tipoRegistroCodigo;
	}
	/**
	 * @param tipoRegistroCodigo O tipoRegistroCodigo a ser setado.
	 */
	public void setTipoRegistroCodigo(String tipoRegistroCodigo) {
		this.tipoRegistroCodigo = tipoRegistroCodigo;
	}
	/**
	 * @return Retorna o campo tipoRegistroDescricao.
	 */
	public String getTipoRegistroDescricao() {
		return tipoRegistroDescricao;
	}
	/**
	 * @param tipoRegistroDescricao O tipoRegistroDescricao a ser setado.
	 */
	public void setTipoRegistroDescricao(String tipoRegistroDescricao) {
		this.tipoRegistroDescricao = tipoRegistroDescricao;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}
	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	/**
	 * @return Retorna o campo sequencialRegistro.
	 */
	public String getSequencialRegistro() {
		return sequencialRegistro;
	}
	/**
	 * @param sequencialRegistro O sequencialRegistro a ser setado.
	 */
	public void setSequencialRegistro(String sequencialRegistro) {
		this.sequencialRegistro = sequencialRegistro;
	}
	/**
	 * @return Retorna o campo uf.
	 */
	public String getUf() {
		return uf;
	}
	/**
	 * @param uf O uf a ser setado.
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	/**
	 * @return Retorna o campo collNegativadorMovimentoRegRetMot.
	 */
	public Collection getCollNegativadorMovimentoRegRetMot() {
		return collNegativadorMovimentoRegRetMot;
	}
	/**
	 * @param collNegativadorMovimentoRegRetMot O collNegativadorMovimentoRegRetMot a ser setado.
	 */
	public void setCollNegativadorMovimentoRegRetMot(
			Collection collNegativadorMovimentoRegRetMot) {
		this.collNegativadorMovimentoRegRetMot = collNegativadorMovimentoRegRetMot;
	}
	/**
	 * @return Retorna o campo motivoExclussao.
	 */
	public String getMotivoExclusao() {
		return motivoExclusao;
	}
	/**
	 * @param motivoExclussao O motivoExclussao a ser setado.
	 */
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}
	/**
	 * @return Retorna o campo totalRegistros.
	 */
	public String getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros O totalRegistros a ser setado.
	 */
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	/**
	 * @return Retorna o campo cnpj.
	 */
	public String getCnpj() {
		return cnpj;
	}
	/**
	 * @param cnpj O cnpj a ser setado.
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	/**
	 * @return Retorna o campo dddTelefoneContato.
	 */
	public String getDddTelefoneContato() {
		return dddTelefoneContato;
	}
	/**
	 * @param dddTelefoneContato O dddTelefoneContato a ser setado.
	 */
	public void setDddTelefoneContato(String dddTelefoneContato) {
		this.dddTelefoneContato = dddTelefoneContato;
	}
	/**
	 * @return Retorna o campo diferencialRemessa.
	 */
	public String getDiferencialRemessa() {
		return diferencialRemessa;
	}
	/**
	 * @param diferencialRemessa O diferencialRemessa a ser setado.
	 */
	public void setDiferencialRemessa(String diferencialRemessa) {
		this.diferencialRemessa = diferencialRemessa;
	}
	/**
	 * @return Retorna o campo identificacaoArquivo.
	 */
	public String getIdentificacaoArquivo() {
		return identificacaoArquivo;
	}
	/**
	 * @param identificacaoArquivo O identificacaoArquivo a ser setado.
	 */
	public void setIdentificacaoArquivo(String identificacaoArquivo) {
		this.identificacaoArquivo = identificacaoArquivo;
	}
	
	/**
	 * @return Retorna o campo nomeContato.
	 */
	public String getNomeContato() {
		return nomeContato;
	}
	/**
	 * @param nomeContato O nomeContato a ser setado.
	 */
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	/**
	 * @return Retorna o campo ramalTelefoneContato.
	 */
	public String getRamalTelefoneContato() {
		return ramalTelefoneContato;
	}
	/**
	 * @param ramalTelefoneContato O ramalTelefoneContato a ser setado.
	 */
	public void setRamalTelefoneContato(String ramalTelefoneContato) {
		this.ramalTelefoneContato = ramalTelefoneContato;
	}
	/**
	 * @return Retorna o campo telefoneContato.
	 */
	public String getTelefoneContato() {
		return telefoneContato;
	}
	/**
	 * @param telefoneContato O telefoneContato a ser setado.
	 */
	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
	/**
	 * @return Retorna o campo codigoEnvio.
	 */
	public String getCodigoEnvio() {
		return codigoEnvio;
	}
	/**
	 * @param codigoEnvio O codigoEnvio a ser setado.
	 */
	public void setCodigoEnvio(String codigoEnvio) {
		this.codigoEnvio = codigoEnvio;
	}
	/**
	 * @return Retorna o campo dataOcorrencia.
	 */
	public String getDataOcorrencia() {
		return dataOcorrencia;
	}
	/**
	 * @param dataOcorrencia O dataOcorrencia a ser setado.
	 */
	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	/**
	 * @return Retorna o campo codigoNaturezaOperacao.
	 */
	public String getCodigoNaturezaOperacao() {
		return codigoNaturezaOperacao;
	}
	/**
	 * @param codigoNaturezaOperacao O codigoNaturezaOperacao a ser setado.
	 */
	public void setCodigoNaturezaOperacao(String codigoNaturezaOperacao) {
		this.codigoNaturezaOperacao = codigoNaturezaOperacao;
	}
	/**
	 * @return Retorna o campo codigoPracaEmbratel.
	 */
	public String getCodigoPracaEmbratel() {
		return codigoPracaEmbratel;
	}
	/**
	 * @param codigoPracaEmbratel O codigoPracaEmbratel a ser setado.
	 */
	public void setCodigoPracaEmbratel(String codigoPracaEmbratel) {
		this.codigoPracaEmbratel = codigoPracaEmbratel;
	}
	/**
	 * @return Retorna o campo cpfContratante.
	 */
	public String getCpfContratante() {
		return cpfContratante;
	}
	/**
	 * @param cpfContratante O cpfContratante a ser setado.
	 */
	public void setCpfContratante(String cpfContratante) {
		this.cpfContratante = cpfContratante;
	}
	
	/**
	 * @return Retorna o campo cpfCnpjCoobrigado.
	 */
	public String getCpfCnpjCoobrigado() {
		return cpfCnpjCoobrigado;
	}
	/**
	 * @param cpfCnpjCoobrigado O cpfCnpjCoobrigado a ser setado.
	 */
	public void setCpfCnpjCoobrigado(String cpfCnpjCoobrigado) {
		this.cpfCnpjCoobrigado = cpfCnpjCoobrigado;
	}
	/**
	 * @return Retorna o campo cpfCnpjPrincipal.
	 */
	public String getCpfCnpjPrincipal() {
		return cpfCnpjPrincipal;
	}
	/**
	 * @param cpfCnpjPrincipal O cpfCnpjPrincipal a ser setado.
	 */
	public void setCpfCnpjPrincipal(String cpfCnpjPrincipal) {
		this.cpfCnpjPrincipal = cpfCnpjPrincipal;
	}
	/**
	 * @return Retorna o campo dataTerminoContrato.
	 */
	public String getDataTerminoContrato() {
		return dataTerminoContrato;
	}
	/**
	 * @param dataTerminoContrato O dataTerminoContrato a ser setado.
	 */
	public void setDataTerminoContrato(String dataTerminoContrato) {
		this.dataTerminoContrato = dataTerminoContrato;
	}
	/**
	 * @return Retorna o campo dataVencimentoDebitoMaisAntigo.
	 */
	public String getDataVencimentoDebitoMaisAntigo() {
		return dataVencimentoDebitoMaisAntigo;
	}
	/**
	 * @param dataVencimentoDebitoMaisAntigo O dataVencimentoDebitoMaisAntigo a ser setado.
	 */
	public void setDataVencimentoDebitoMaisAntigo(
			String dataVencimentoDebitoMaisAntigo) {
		this.dataVencimentoDebitoMaisAntigo = dataVencimentoDebitoMaisAntigo;
	}
	/**
	 * @return Retorna o campo dddTelefoneDevedor.
	 */
	public String getDddTelefoneDevedor() {
		return dddTelefoneDevedor;
	}
	/**
	 * @param dddTelefoneDevedor O dddTelefoneDevedor a ser setado.
	 */
	public void setDddTelefoneDevedor(String dddTelefoneDevedor) {
		this.dddTelefoneDevedor = dddTelefoneDevedor;
	}
	/**
	 * @return Retorna o campo motivoBaixa.
	 */
	public String getMotivoBaixa() {
		return motivoBaixa;
	}
	/**
	 * @param motivoBaixa O motivoBaixa a ser setado.
	 */
	public void setMotivoBaixa(String motivoBaixa) {
		this.motivoBaixa = motivoBaixa;
	}
	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return Retorna o campo nomeDevedor.
	 */
	public String getNomeDevedor() {
		return nomeDevedor;
	}
	/**
	 * @param nomeDevedor O nomeDevedor a ser setado.
	 */
	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}
	/**
	 * @return Retorna o campo nomeMae.
	 */
	public String getNomeMae() {
		return nomeMae;
	}
	/**
	 * @param nomeMae O nomeMae a ser setado.
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	/**
	 * @return Retorna o campo nomePai.
	 */
	public String getNomePai() {
		return nomePai;
	}
	/**
	 * @param nomePai O nomePai a ser setado.
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	/**
	 * @return Retorna o campo nossoNumero.
	 */
	public String getNossoNumero() {
		return nossoNumero;
	}
	/**
	 * @param nossoNumero O nossoNumero a ser setado.
	 */
	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
	/**
	 * @return Retorna o campo rgCoobrigado.
	 */
	public String getRgCoobrigado() {
		return rgCoobrigado;
	}
	/**
	 * @param rgCoobrigado O rgCoobrigado a ser setado.
	 */
	public void setRgCoobrigado(String rgCoobrigado) {
		this.rgCoobrigado = rgCoobrigado;
	}
	/**
	 * @return Retorna o campo rgPrincipal.
	 */
	public String getRgPrincipal() {
		return rgPrincipal;
	}
	/**
	 * @param rgPrincipal O rgPrincipal a ser setado.
	 */
	public void setRgPrincipal(String rgPrincipal) {
		this.rgPrincipal = rgPrincipal;
	}
	/**
	 * @return Retorna o campo telefoneDevedor.
	 */
	public String getTelefoneDevedor() {
		return telefoneDevedor;
	}
	/**
	 * @param telefoneDevedor O telefoneDevedor a ser setado.
	 */
	public void setTelefoneDevedor(String telefoneDevedor) {
		this.telefoneDevedor = telefoneDevedor;
	}
	/**
	 * @return Retorna o campo tipoPessoaCoobrigado.
	 */
	public String getTipoPessoaCoobrigado() {
		return tipoPessoaCoobrigado;
	}
	/**
	 * @param tipoPessoaCoobrigado O tipoPessoaCoobrigado a ser setado.
	 */
	public void setTipoPessoaCoobrigado(String tipoPessoaCoobrigado) {
		this.tipoPessoaCoobrigado = tipoPessoaCoobrigado;
	}
	/**
	 * @return Retorna o campo tipoPessoaPrincipal.
	 */
	public String getTipoPessoaPrincipal() {
		return tipoPessoaPrincipal;
	}
	/**
	 * @param tipoPessoaPrincipal O tipoPessoaPrincipal a ser setado.
	 */
	public void setTipoPessoaPrincipal(String tipoPessoaPrincipal) {
		this.tipoPessoaPrincipal = tipoPessoaPrincipal;
	}
	/**
	 * @return Retorna o campo tipoPrimeiroDocumentoCoobrigado.
	 */
	public String getTipoPrimeiroDocumentoCoobrigado() {
		return tipoPrimeiroDocumentoCoobrigado;
	}
	/**
	 * @param tipoPrimeiroDocumentoCoobrigado O tipoPrimeiroDocumentoCoobrigado a ser setado.
	 */
	public void setTipoPrimeiroDocumentoCoobrigado(
			String tipoPrimeiroDocumentoCoobrigado) {
		this.tipoPrimeiroDocumentoCoobrigado = tipoPrimeiroDocumentoCoobrigado;
	}
	/**
	 * @return Retorna o campo tipoPrimeiroDocumentoPrincipal.
	 */
	public String getTipoPrimeiroDocumentoPrincipal() {
		return tipoPrimeiroDocumentoPrincipal;
	}
	/**
	 * @param tipoPrimeiroDocumentoPrincipal O tipoPrimeiroDocumentoPrincipal a ser setado.
	 */
	public void setTipoPrimeiroDocumentoPrincipal(
			String tipoPrimeiroDocumentoPrincipal) {
		this.tipoPrimeiroDocumentoPrincipal = tipoPrimeiroDocumentoPrincipal;
	}
	/**
	 * @return Retorna o campo tipoSegundoDocumentoCoobrigado.
	 */
	public String getTipoSegundoDocumentoCoobrigado() {
		return tipoSegundoDocumentoCoobrigado;
	}
	/**
	 * @param tipoSegundoDocumentoCoobrigado O tipoSegundoDocumentoCoobrigado a ser setado.
	 */
	public void setTipoSegundoDocumentoCoobrigado(
			String tipoSegundoDocumentoCoobrigado) {
		this.tipoSegundoDocumentoCoobrigado = tipoSegundoDocumentoCoobrigado;
	}
	/**
	 * @return Retorna o campo tipoSegundoDocumentoPrincipal.
	 */
	public String getTipoSegundoDocumentoPrincipal() {
		return tipoSegundoDocumentoPrincipal;
	}
	/**
	 * @param tipoSegundoDocumentoPrincipal O tipoSegundoDocumentoPrincipal a ser setado.
	 */
	public void setTipoSegundoDocumentoPrincipal(
			String tipoSegundoDocumentoPrincipal) {
		this.tipoSegundoDocumentoPrincipal = tipoSegundoDocumentoPrincipal;
	}
	/**
	 * @return Retorna o campo ufDoRgCoobrigado.
	 */
	public String getUfDoRgCoobrigado() {
		return UfDoRgCoobrigado;
	}
	/**
	 * @param ufDoRgCoobrigado O ufDoRgCoobrigado a ser setado.
	 */
	public void setUfDoRgCoobrigado(String ufDoRgCoobrigado) {
		UfDoRgCoobrigado = ufDoRgCoobrigado;
	}
	/**
	 * @return Retorna o campo ufDoRgPrincipal.
	 */
	public String getUfDoRgPrincipal() {
		return UfDoRgPrincipal;
	}
	/**
	 * @param ufDoRgPrincipal O ufDoRgPrincipal a ser setado.
	 */
	public void setUfDoRgPrincipal(String ufDoRgPrincipal) {
		UfDoRgPrincipal = ufDoRgPrincipal;
	}
	/**
	 * @return Retorna o campo valorToralCompromisso.
	 */
	public String getValorToralCompromisso() {
		return valorToralCompromisso;
	}
	/**
	 * @param valorToralCompromisso O valorToralCompromisso a ser setado.
	 */
	public void setValorToralCompromisso(String valorToralCompromisso) {
		this.valorToralCompromisso = valorToralCompromisso;
	}
	/**
	 * @return Retorna o campo descricaoCodigoEnvio.
	 */
	public String getDescricaoCodigoEnvio() {
		return descricaoCodigoEnvio;
	}
	/**
	 * @param descricaoCodigoEnvio O descricaoCodigoEnvio a ser setado.
	 */
	public void setDescricaoCodigoEnvio(String descricaoCodigoEnvio) {
		this.descricaoCodigoEnvio = descricaoCodigoEnvio;
	}
	/**
	 * @return Retorna o campo indicadorCorrecao.
	 */
	public String getIndicadorCorrecao() {
		return indicadorCorrecao;
	}
	/**
	 * @param indicadorCorrecao O indicadorCorrecao a ser setado.
	 */
	public void setIndicadorCorrecao(String indicadorCorrecao) {
		this.indicadorCorrecao = indicadorCorrecao;
	}
	/**
	 * @return Retorna o campo idNegativadorMovimentoReg.
	 */
	public String getIdNegativadorMovimentoReg() {
		return idNegativadorMovimentoReg;
	}
	/**
	 * @param idNegativadorMovimentoReg O idNegativadorMovimentoReg a ser setado.
	 */
	public void setIdNegativadorMovimentoReg(String idNegativadorMovimentoReg) {
		this.idNegativadorMovimentoReg = idNegativadorMovimentoReg;
	}

	
	
}