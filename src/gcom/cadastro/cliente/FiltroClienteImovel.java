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
package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente imovel
 * 
 * @author Sávio Luiz
 * @created 2 de Agosto de 2005
 */

public class FiltroClienteImovel extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteImovel
	 */
	public FiltroClienteImovel() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroClienteImovel(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE = "cliente";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_NOME = "cliente.nome";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL = "imovel";
	
	public final static String IMOVEL_INDICADOR_IMOVEL_AREA_COMUM = "imovel.indicadorImovelAreaComum";

	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "imovel.localidade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE = "imovel.localidade";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";
	
	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL = "imovel.setorComercial";

	/**
	 * Description of the Field
	 */
	public final static String SETOR_COMERCIAL_CODIGO = "imovel.setorComercial.codigo";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_ID = "imovel.quadra.id";
	
	/**
	 * Description of the Field
	 */
	public final static String QUADRA = "imovel.quadra";

	/**
	 * Description of the Field
	 */
	public final static String QUADRA_NUMERO = "imovel.quadra.numeroQuadra";

	/**
	 * Description of the Field
	 */
	public final static String CEP_CODIGO = "imovel.logradouroCep.cep.codigo";

	/**
	 * Description of the Field
	 */
	public final static String CEP_ID = "imovel.logradouroCep.cep.cepId";
	
	/**
	 * Description of the Field
	 */
	public final static String CEP = "imovel.logradouroCep.cep";

	/**
	 * Description of the Field
	 */
	public final static String MUNICIPIO_CODIGO = "imovel.logradouroBairro.bairro.municipio.id";
	
	/**
	 * Description of the Field
	 */
	public final static String MUNICIPIO = "imovel.logradouroBairro.bairro.municipio";

	/**
	 * Description of the Field
	 */
	public final static String MUNICIPIO_SETOR_COMERICAL_CODIGO = "imovel.setorComercial.municipio.id";

	/**
	 * Description of the Field
	 */
	public final static String BAIRRO_ID = "imovel.logradouroBairro.bairro.id";
	
	/**
	 * Description of the Field
	 */
	public final static String BAIRRO = "imovel.logradouroBairro.bairro";

	/**
	 * Description of the Field
	 */
	public final static String LOTE = "imovel.lote";

	/**
	 * Description of the Field
	 */
	public final static String SUBLOTE = "imovel.subLote";

	/**
	 * Description of the Field
	 */
	public final static String BAIRRO_CODIGO = "imovel.logradouroBairro.bairro.codigo";

	/**
	 * Description of the Field
	 */
	public final static String LOGRADOURO_NOME = "imovel.logradouroCep.logradouro.nome";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "cliente.indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_PERFIL = "imovel.imovelPerfil.id";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_PERFIL_INDICADOR_USO = "imovel.imovelPerfil.indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo";
	
	public final static String FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo.id";

	public final static String DATA_FIM_RELACAO = "dataFimRelacao";

	public final static String DATA_INICIO_RELACAO = "dataInicioRelacao";
	
	public final static String GERA_ARQUIVO_TEXTO = "cliente.indicadorGeraArquivoTexto";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo";
	
	public final static String CLIENTE_RELACAO_TIPO_DESCRICAO = "clienteRelacaoTipo.descricao";
	
	
	public final static String CLIENTE_RELACAO_TIPO_ID = "clienteRelacaoTipo.id";

	public final static String SETOR_COMERCIAL_MUNICIPIO_ID = "imovel.setorComercial.municipio.id";// tem
																									// em
																									// imovel

	public final static String GERENCIA_REGIONAL_ID = "imovel.quadra.setorComercial.localidade.gerenciaRegional.id";

	public final static String LOGRADOURO_ID = "imovel.logradouroCep.logradouro.id";
	
	public final static String LOGRADOURO = "imovel.logradouroCep.logradouro";

	public final static String LIGACAO_AGUA_HIDROMETRO_INSTALACAO_HISTORICO_ID = "imovel.ligacaoAgua.hidrometroInstalacaoHistorico.id";

	public final static String IMOVEL_HIDROMETRO_INSTALACAO_HISTORICO_ID = "imovel.hidrometroInstalacaoHistorico.id";

	public final static String ID_IMOVEL_PRINCIPAL = "imovel.imovelPrincipal.id";

	public final static String ID_NOME_CONTA = "imovel.nomeConta.id";

	public final static String ID_IMOVEL_CONDOMINIO = "imovel.imovelCondominio.id";

	public final static String ID_CLIENTES_IMOVEIS_CLIENTE_RELACAO_TIPO_ID = "clienteRelacoTipo.id";

	public final static String ID_CLIENTE_IMOVEIS_CLIENTE_ID = "cliente.id";

	public final static String MUNICIPIO_ID = "imovel.logradouroBairro.bairro.municipio.id";

	public final static String IMOVEL_POCO_TIPO_ID = "imovel.pocoTipo.id";

	public final static String IMOVEL_IMOVEL_PERFIL_ID = "imovel.imovelPerfil.id";

	public final static String IMOVEL_CATEGORIA_IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria.id";

	public final static String IMOVEL_SUBCATEGORIA_IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.id";

	public final static String IMOVEL_SITUACAOAGUA_ID = "imovel.situacaoAgua.id";

	public final static String IMOVEL_LIGACAO_ESGOTO_SITUACAO_ID = "imovel.ligacaoEsgotoSituacao.id";

	public final static String IMOVEL_MEDICAO_HISTORICOS_MEDICAO_TIPO_ID = "imovel.medicaoHistoricos.medicaoTipo.id";

	public final static String IMOVEL_LIGACAO_ESGOTO_CONSUMO_MINIMO = "imovel.ligacaoEsgoto.consumoMinimo";

	public final static String IMOVEL_LIGACAO_AGUA_CONSUMO_MINIMO = "imovel.ligacaoAgua.numeroConsumoMinimoAgua";

	public final static String IMOVEL_LIGACAO_ESGOTO_PERCENTUAL = "imovel.ligacaoEsgoto.percentual";
	
	public final static String INDICADOR_IMOVEL_EXCLUIDO = "imovel.indicadorExclusao";

	public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
	
	public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
	
	public final static String CLIENTE_TIPO = "cliente.clienteTipo";
	
	public final static String INDICADOR_NOME_CONTA = "indicadorNomeConta";
	
	
	//***********************************************
	// Autor: Ivan Sergio
	// Date: 29/05/20093
	// CRC2014
	//***********************************************
	public final static String UNIDADE_FEDERACAO = "cliente.unidadeFederacao";
	public final static String CLIENTE_FONE = "cliente.clienteFones";
	public final static String CLIENTE_FONE_INDICADOR_TELEFONE_PADRAO = "cliente.clienteFones.indicadorTelefonePadrao";
	public final static String FONE_TIPO = "cliente.clienteFones.foneTipo";
	public final static String ORGAO_EXPEDIDOR_RG = "cliente.orgaoExpedidorRg";
	//***********************************************
	
	// public final static String IMOVEL_LIGACAO_ESGOTO_PERCENTUAL =
	// "imovel.ligacaoEsgoto.percentual";
}