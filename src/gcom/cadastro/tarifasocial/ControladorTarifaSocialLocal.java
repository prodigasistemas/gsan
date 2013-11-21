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
package gcom.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTarifaSocialLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Faz verificações da inserção de dados de tarifa social de um imóvel
	 * 
	 * @param idImovel
	 *            Código do Imóvel
	 */
	public void verificarProprietarioImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Faz verificações da inserção de dados de tarifa social de um imóvel
	 * 
	 * @param idImovel
	 *            Código do Imóvel
	 */
	public Cliente verificarUsuarioImovel(Integer idImovel)
			throws ControladorException;
	
	
	/**
	 * Verificar os pré-requisitos para o cadastramento de um imóvel na tarifa
	 * social
	 * 
	 * @param idImovel
	 *            Código do imovel
	 */
	public String[] verificarPreRequisitosCadastramentoTarifaSocial(Integer idImovel)
			throws ControladorException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param tarifaSocialCartaoTipo
	 *            Descrição do parâmetro
	 */
	public void atualizarTarifaSocialCartaoTipo(
			TarifaSocialCartaoTipo tarifaSocialCartaoTipo)
			throws ControladorException;
	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * [FS0014] - Verificar duplicidade do cartão do programa social
	 * @param numeroCartao
	 * @throws ControladorException
	 */
	public void verificarDuplicidadeCartaoProgramaSocial(Long numeroCartao, 
			TarifaSocialCartaoTipo tipoCartao, Integer idImovel) throws ControladorException;

	
	/**
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 */
	public String[] verificarPreenchimentoInserirDadosTarifaSocial(Long numeroCelpe, 
			BigDecimal areaConstruida, BigDecimal numeroIPTU, Integer idImovel, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar, String tarifaSocialCartaoTipo,
			String tipoRenda) throws ControladorException;
	
	/**
	 * 
	 * @param clienteImovelEconomia
	 * @param numeroCartaoSocial
	 * @param dataValidadeCartaoSocial
	 * @param numeroParcelasCartaoSocial
	 * @param consumoMedio
	 * @param valorRendaFamiliar
	 * @param tarifaSocialCartaoTipo
	 * @param tipoRenda
	 * @param imovel
	 * @throws ControladorException
	 */
	public String[] verificarPreenchimentoInserirDadosTarifaSocialMultiplas(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;

	/**
	 * Atualiza um tarifaSocialDadoEconomia
	 * 
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 */
	public void atualizarTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;

	/**
	 * Enquadra um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param tarifaSocialDado
	 *            Descrição do parâmetro
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 */
/*	public void inserirDadosTarifaSocialImovel(
			TarifaSocialDado tarifaSocialDado,
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;
	*/
	
	
	/**
	 * Atualiza o perfil do imóvel para tarifa social
	 * @param imovel
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfilTarifaSocial(Imovel imovel) throws ControladorException;
	

	/**
	 * Atualiza o enquadramento de um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param tarifaSocialDado
	 *            Descrição do parâmetro
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 */
	public void atualizarDadosTarifaSocialImovel(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;
	
	
	/**
	 * 
	 * @param tarifaSocialDado
	 * @throws ControladorException
	 */
//	public void atualizarTarifaSocialDadoRecadastramento(TarifaSocialDado tarifaSocialDado) 
	//throws ControladorException;	

	/**
	 * Pesquisa uma coleção de Tarifa Social Dado Economia.
	 * 
	 * @param filtroTarifaSocialDadoEconomia
	 *            Description of the Parameter
	 * @author Thiago
	 * @date 12/12/2005           
	 * @return Description of the Return Value
	 */

	public Collection pesquisarTarifaSocialDadoEconomia(
			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia)
			throws ControladorException;
	
	
	/**
	 * Método que remover o imover da tarifa social 
	 * 
	 * @param idImovel
	 * @param idMotivoTarifaSocial
	 * @throws ControladorException
	 */
	public void removerImovelTarfiaSocial( Integer idImovel, Integer idMotivoTarifaSocial) throws ControladorException;

	/**
	 * Pesquisa todas as tarifas sociais com o filtro passado
	 * 	
	 * @param filtroClienteImovel
	 * @return
	 */
	public Collection pesquisarImovelTarfiaSocial( FiltroClienteImovel filtroClienteImovel, Integer numeroPagina ) throws ControladorException;
	
	/**
	 * Método que verifica se o usuario esta cadastrado em outro imovel que esteja na tarifa social e 
	 * verifica se ja esta cadastrado como usuario de algum imovel economia,
	 *
	 * Caso o idImovel seja diferente de nula ele verifa se o usuario esta cadastrado num imovel diferente 
	 * do id passado.
	 *
	 * Caso o idImovelEconomia seja diferente de nula ele verifaca se o usuario esta cadastrado num imovel economia 
	 * do idImovelEconomia passado.
	 *
	 * @param idImovel
	 * @param idImovelEconomia
	 * @param idEconomiaAtual
	 * @param idClienteUsuario
	 */
	public void verificarClienteUsuarioEmOutroEconomia(Integer idImovel, Integer idImovelEconomia, Integer idClienteUsuario) throws ControladorException;
	
	/**
	 * Método que pesquisa a quantidade de tarifa social
	 * 
	 *  @author Rafael Santos
	 *  @since 05/09/2006
	 * 
	 * @param filtroClienteImovel
	 * @return
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomia(Integer idImovel)  
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 02/01/2007
	 */
	public Collection verificarClienteCadastradoTarifaSocial(Integer idCliente)
		throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int pesquisarClienteImovelEconomiaCount(Integer idImovel,
			Integer idCliente) throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int verificarExistenciaDebitosCliente(Integer idCliente)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os clientes usuários das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public Collection pesquisarClientesUsuariosImovelEconomia(Integer idImovel) 
		throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário está vinculado na tarifa social a outro
	 * imóvel ou economia com motivo de revisão que permita recadastramento
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 04/01/2007
	 */
	public Collection pesquisarClientesUsuarioExistenteTarifaSocial(Integer idCliente) 
		throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe um motivo de exclusão para o cliente que não permite
	 * recadastramento na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public void verificarClienteMotivoExclusaoRecadastramento(Integer idCliente)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os cliente a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Integer pesquisarClienteImovelEconomia(Integer idClienteImovelEconomia) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/01/2007
	 */
	public Collection pesquisarDadosClienteTarifaSocial(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna a tarifa social a partir do seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocial(Integer idTarifaSocial) 
		throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se existe tarifa social para o imóvel que não tenha sido
	 * excluído
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public Collection pesquisarTarifaSocialImovel(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocial(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovel, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelTarifaSocial(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 22/01/2007
	 */
	public Cliente pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário para cada economia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 25/01/2007
	 */
	public Collection pesquisarDadosClienteEconomiaTarifaSocial(Integer idImovel)
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialUmaEconomia(Integer idCliente, Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário da economia do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(Integer idCliente, Integer idImovelEconomia) 
		throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para múltiplas economias
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocialMultiplasEconomias(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa a economia do imóvel pelo seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloId(Integer idImovelEconomia)
		throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Seta o indicador do nome da conta para 2 nos clientes proprietário e
	 * usuários
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public void atualizarNomeContaClienteImovelTarifaSocial(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(Integer idImovelEconomia)  
			throws ControladorException;

     /**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Recadastrar, atualiza ou remove a tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 13/02/2007
	 */
	public AtendimentoMotivoEncerramento manterTarifaSocial(Imovel imovelSessao,
			Collection colecaoTarifaSocialHelperAtualizar,
			Collection colecaoImoveisExcluidosTarifaSocial,
			Collection colecaoTarifaSocialExcluida,
			Collection colecaoTarifasSociaisRecadastradas,  Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Recadastrar ou insere a tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 13/02/2007
	 */
	public void inserirTarifaSocial(Imovel imovelSessao,
			ClienteImovel clienteImovel,
			RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado, Integer idTarifaSocialDadoEconomiaExcluida,
			Collection colecaoTarifaSocialDadoEconomia,
			Collection colecaoClienteImovelEconomia,
			Collection colecaoTarifaSocialRecadastrar, Imovel imovelAtualizar,
			Collection colecaoImovelEconomiaAtualizar)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna a economia do imóvel a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloCliente(Integer idClienteImovelEconomia) 
		throws ControladorException;
	
	/**
	 * [UC0009] - Manter Cliente 
	 * 
	 * Verifica se o cliente usuário está na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 16/02/2007
	 */
	public Collection verificarClienteUsuarioCadastradoTarifaSocial(Integer idCliente) 
		throws ControladorException;

	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ControladorException;
	
}
