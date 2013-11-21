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
package gcom.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.bean.ObterValorDebitoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Interfe, que disponibiliza os serviços do Repositório Atendimento Público 
 *
 * @author Leandro Cavalcanti
 * @date 10/07/2006
 */
public interface IRepositorioAtendimentoPublico {
	
	/**
	 * [UC-0355] - Efetuar Corte de Ligaçã de Àgua
	 * [SB001] Atualizar Hidrometro - (corte de ligação de água)
	 * Atualizar um os campos lastId,dataUltimaAtualização do imovel na base
	 * 
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idLigacaoAguaSituacao, Integer numeroSeloCorte ) throws ErroRepositorioException;
	/**
	 * [UC-0355] - Efetuar Corte de Ligaçã de Àgua
	 * [SB001] Atualizar Hidrometro - (corte de ligação de água)
	 * Atualizar os campos hidi_nnleituracorte e hidi_tmultimaalteracao de HidrometroInstalacaoHistorico
	 *			
	 *
	 * @author Leandro Cavalcanti
	 * @param imovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void atualizarHidrometroLIgacaoAgua(Integer imovelId,Integer numeroLeituraCorte) throws ErroRepositorioException ;
		
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB002] Atualizar Ligação de Água 
	 * Atualizar os campos hidi_id e lagu_tmultimaalteracao de LigacaoAgua
	 * 		
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idLigacaoAgua
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacaoHistoricoLigacaoAgua(Integer idLigacaoAgua, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB002] Atualizar Imóvel
	 * Atualizar os campos hidi_id e imov_tmultimaalteracao de Imovel
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 13/07/2006
	 *
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroIntalacaoHistoricoImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico, Integer idPocoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoHidrometro(Integer idHidrometro, Integer situacaoHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0396] - Inserir Tipo de retorno da OS Referida
	 * 
	 * [FS0005] Validar indicador de deferimento
	 * 
	 * @author lms
	 * @date 31/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public int consultarTotalIndicadorDeferimentoAtivoPorServicoTipoReferencia(OsReferidaRetornoTipo osReferidaRetornoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 * 
	 * @param ligacaoAgua
	 * @exception ErroRepositorioExceptions
	 */
	public void atualizarConsumoMinimoLigacaoAgua(LigacaoAgua ligacaoAgua) throws ErroRepositorioException;	
	

	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarLocalArmazanagemHidrometro(Integer idHidrometro,Integer localArmazanagemHidrometro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC-0362] - Efetuar Instalação de Hidrômetro
	 * [SB003] Atualizar Hidrômetro
	 * Atualizar o campo hisi_id 
	 * 
	 * @exception ErroRepositorioExceptions
	 *                Descrição da exceção
	 *
	 * @author Ana Maria
	 * @date 17/07/2006
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarHidrometroInstalacoHistorico(HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) 
		throws ErroRepositorioException;	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na ligação de água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro no imóvel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pela Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pelo Imóvel.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não 
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmImovel(Integer imovelId) throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Valor do Debito pelos parâmtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do débito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebito(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * Método que retorna o número do hidrômetro da ligação de água
	 * 
	 * @author Ana Maria
	 * @date 12/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */

	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * Método que retorna o tipo da ligação de água e a data do corte da ligação de água
	 * 
	 * @author Ana Maria
	 * @date 18/08/2006
	 * 
	 * 
	 * @param idLigacaoAgua
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os dados das ordens de serviço para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ErroRepositorioException;
	
	/**
	 * [UC0404] Manter Especificação da Situação do Imovel
	 * 
	 * Este caso de uso remove a especificação e os critério
	 * 
	 * [SB0002] Remover Especificação da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovelCriterio(String[] idsEspecificacaoSituacaoImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os ids das situações de ligação de água.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ErroRepositorioException ;
	
	
	/**
	 * Pesquisa todos os ids das situações de ligação de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto() throws ErroRepositorioException ;
	
	/**
	 * 
	 * Este cso de uso permite efetuar a ligação de água e eventualmente 
	 * a instalação de hidrômetro, sem informação de RA sendo chamado direto pelo menu.
	 *
	 * [UC0579] - Efetuar Ligação de Água com Intalação de Hidrômetro
	 *
	 * @author Flávio Leonardo
	 * @date 25/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEfetuarLigacaoAguaHidrometroSemRA(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Prestação de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 03/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosContratoPrestacaoServico(
			Integer idImovel) throws ErroRepositorioException;
	
	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(Integer idImovel, Integer idHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obtém os dados necessário da ligação de água, de esgoto e do hidrômetro
	 * instalado na ligação de água
	 * 
	 * @author Rafael Corrêa
	 * @date 17/05/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ErroRepositorioException;
	
//	*********************************************************
	//****************CONTRATO PESSOA JURIDICA*****************
	
	public Cliente pesquisaClienteContrato(Integer idCliente) throws ErroRepositorioException;
	
	public ClienteImovel pesquisarDadosContratoJuridica(Integer idImovel) throws ErroRepositorioException;
	
	public String pesquisarMunicipio(Integer idImovel) throws ErroRepositorioException;
	
	//*********************************************************

	/**
	 * Substituicao de hidrometro
	 */
	public void atualizarSubstituicaoHidrometroInstalacoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico)
			throws ErroRepositorioException;
	
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 *obter numero do hidrômetro na ligação de água.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ErroRepositorioException;
	
	/**
	 * [UC0738] Retorna as informações para o relatório de certidão negativa
	 * @param imo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativa( Imovel imo )
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados necessários para a geração do relatório
	 * 
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioCertidaoNegativaCliente(Collection<Integer> idsClientes)
			throws ErroRepositorioException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente não associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cpf
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCPF(Integer idImovel, String cpf)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0004] - Cliente não associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cnpj
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComCNPJ(Integer idImovel, String cnpj)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0482] Emitir 2a Via Conta
	 *
	 * [FS0002] - Cliente sem documento
	 *
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 *
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteAssociadoImovelComDocumentoInformado(Integer idImovel)
		throws ErroRepositorioException ;
	
	 /**
     * [UC0150] Retificar Conta
     * @author Vivianne Sousa
     * @date 26/11/2008
     */
    public BigDecimal obterPercentualAguaConsumidaColetadaImovel(Integer idImovel)
            throws ErroRepositorioException ;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public Collection<AutosInfracao> pesquisarAutoInfracaoRecursoVencido(Integer idSituacaoAutoInfracao,Date prazoEntregaRecursoVencido)
            throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * [SB0001] Atualizar Autos Infração
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public void atualizarAutosInfracao(Collection idsAutosInfracao,
    		Integer idSituacaoAutoInfracao) throws ErroRepositorioException;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
    public Collection<FiscalizacaoSituacaoServicoACobrar> pesquisarFiscalizacaoSituacaoServicoACobrar(Integer idFiscalizacaoSituacao)
            throws ErroRepositorioException;
    
    /**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
    public Collection<Object[]> pesquisarImoveisBatchEmitirOrdemFiscalizacao(
    		Integer idSetorComercial,Date data,Integer quantidadeInicio, Integer quantidadeMaxima)throws ErroRepositorioException;
    
	/**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 * 	Inseri objeto na base do tipo ImovelSuprimido
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void inserirImovelSuprimido(ImovelSuprimido imovelSuprimido)throws ErroRepositorioException;
	
	 /**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 * 	Pesquisas linhas do txt
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public Collection<ImovelSuprimido> pesquisarDadosEmitirArquivoTextoDeOrdemFiscalizacao(
			Integer quantidadeInicio, Integer quantidadeMaxima)
			throws ErroRepositorioException;
	
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo serviço, e retorna uma coleção. 
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 [SB0002] – Replicar os serviços existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo serviço, e retorna o total.   
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ErroRepositorioException;
	
	/**
	 * [SB0002] – Replicar os Valores de Cobrança de Serviço existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo Cobrança, e retorna uma coleção. 
	 * 
	 * @author Hugo Leonardo
	 * @date 23/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(String[] selecionados) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0391] Inserir valor cobrança Serviço.
	 * 
	 * Verificar se existe vigência já cadastrada para o Serviço Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ErroRepositorioException
	 * @data 03/05/2010
	 * 
	 * return String
	 * 
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar Serviço Tipo.
	 * @see Caso a opcao = 2 - verifica a situação de retificar Serviço Tipo.
	 */
	public String verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo)
		throws ErroRepositorioException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Valor do Debito pelos parâmtros passados.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param obterValorDebitoHelper
	 * @return o valor do débito 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebitoHidrometroCapacidade(ObterValorDebitoHelper params) throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urgência
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   03/06/2010
	 * 
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 *  
	 * Retorna um ou todos usuários da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 *  
	 * Retorna um ou todos usuários da unidade relacionada a RA, 
	 *  da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário 
	 * @throws ErroRepositorioException
	 * @data   04/06/2010
	 * 
	 */
	public Collection pesquisarVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario)
		throws ErroRepositorioException;
		
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento já está relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ErroRepositorioException;
	
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urgência
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ErroRepositorioException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ErroRepositorioException;
	
	
	/**  
	 * Atualiza um ou vários campos da tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento, ID da Unidade, ID do Usuário, indicador Tramite e indicador Visualizacao 
	 * @throws ErroRepositorioException
	 * @data   10/06/2010
	 * 
	 */
	public void atualizarUsuarioRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, String idUsuarios, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
            throws ErroRepositorioException;
	
    /**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ErroRepositorioException;
	
	/**
	 * [UC1056] ? Gerar Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/10/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado( FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return Integer
	 */
	public Integer verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, Integer idRepavimentadora,
			Integer idPavimento, Date dataInicio, Date dataFinal, Integer indicadorPavimento, Integer tipo)
		throws ErroRepositorioException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2011
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return Integer
	 * 
	 * VerificarExistenciDiasSemValor
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, 
			Date dataFinal, Integer indicadorPavimento, Integer tipo) throws ErroRepositorioException;
	
	/**
     * [UC0412] Manter Tipo de Serviço
     * 
     * @author Vivianne Sousa
     * @created 07/01/2011
     */
    public void removerServicoTipoBoletim(Integer idServicoTipo)
            throws ErroRepositorioException ;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 28/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			Integer os, Integer imovel, Date dataEncerramentoOS, Integer tipo) throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 31/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( Collection<Integer> idsOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 01/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesRecorrentes( 
			Integer imovel, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes.
	 *
	 * @author Hugo Leonardo
	 * @date 09/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentesDatasOS( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper, Integer imovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 16/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRecorrenciaPorUsuarioQueAbriuOuEncerrouOS( 
			Integer usuario, FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) 
		throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0008 – Validar Motivo Encerramento]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarAtendimentoMotivoEncerramento(Integer idMotivoEncerramento) throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
	 * 
	 * O segundo parâmetro (boletimGerado) é um booleano que
	 * indica se para um dado grupo de cobrança e um mês referencia
	 * foi gerado um boletim de medição.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<FiltrarRelatorioOSSituacaoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper helper, boolean boletimGerado)
		throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de perfis de tipo de serviço para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	public Collection obterColecaoTipoOSgerada() throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel,
			  String[] perfilImovel,
			  String[] gerenciaRegional,
			  String[] unidadeNegocio,
			  String idLocalidadeInicial,
			  String idLocalidadeFinal,
			  String idSetorComercialInicial,
			  String idSetorComercialFinal,
			  String idQuadraInicial,
			  String idQuadraFinal,
			  String tipoServico,
			  String comando) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * O segundo parâmetro (relatorioDefinitivo) é um booleano que
	 * indica se o relatório é definitivo ou não, pois o resultado
	 * da query é diferente para os relatórios definitivos e os
	 * de simulação
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro, boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * Método que vai retornar as quantidades acumuladas e os valores acumulados
	 * no período para geração do relatório de acompanhamento do boletim de medição.
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicaoAcumuladas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * O segundo parâmetro (relatorioDefinitivo) é um booleano que
	 * indica se o relatório é definitivo ou não, pois o resultado
	 * da query é diferente para os relatórios definitivos e os
	 * de simulação
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<BigDecimal> filtrarRelatorioAcompanhamentoBoletimMedicaoPenalidades(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro,
			boolean relatorioDefinitivo) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 01/08/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return Taxa de Sucesso do Boletim de Medição
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarTaxaSucessoBoletimMedicao(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ErroRepositorioException;
	
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar as Ordens de serviços a partir de seu imóvel e tipo de serviço
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
     * Obtém a quantida de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1189] Inserir Registro de Atendimento Loja Virtual
	 * 
	 * @author Magno Gouveia
	 * @date 12/07/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ErroRepositorioException;
	
	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Municípios da Região
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ErroRepositorioException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0005] Exibir Dados da Loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do municipio
	 * @return colecaoDeLojas
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * Método que vai retornar um booleano indicando se o relatório de acompanhamento de boletim
	 * medição é definitivo ou não definitivo.
	 * 
	 * @author Diogo Peixoto
	 * @date 26/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean gruposIniciaodsJaForamEncerrados(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro)	throws ErroRepositorioException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Penalizadas para determinado boletim de medição
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSPenalizadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 28/07/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Quantidade de OS Executadas para determinado boletim de medição
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeOSExecutadas(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
		throws ErroRepositorioException;
	
	public Collection obterColecaoOSFiscalizacaoNaoExecutadas() throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Ordem de Serviço
	 * 
	 * @return Collection<Object[]> - Coleção das Fotos da OS
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServico(Integer idFoto) throws ErroRepositorioException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID da Foto da Ordem de Serviço
	 * 
	 * @return Collection<Object[]> - Foto da Ordem de Serviço
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarFotosOrdemServicoPorIdFoto(Integer idFoto) throws ErroRepositorioException;
	
	public Object[] pesquisarPrazoMedioAtendimentoOSRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Object[] pesquisarNovasLigacoesAguaRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;
	
	public Object[] pesquisarNovasLigacoesEsgotoRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadeConsumidoresRelatorioBIG(Integer idLocalidade,
			Integer situacao) throws ErroRepositorioException;
	
}