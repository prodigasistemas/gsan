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
 * Thiago Silva Toscano de Brito
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
package gcom.spcserasa;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegParcelamento;
import gcom.cobranca.ResumoNegativacao;
import gcom.cobranca.ResumoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Administrador
 */
public interface IRepositorioSpcSerasa {

	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007 *
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosNegativador(int idNegativador)
			throws ErroRepositorioException;

	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosContratoNegativador(int idNegativador)
			throws ErroRepositorioException;

	/**
	 * Insere comando Negativação
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer inserirComandoNegativacao(int idNegativador,
			int idUsuarioResponsavel, String identificacaoCI)
			throws ErroRepositorioException;

	/**
	 * Insere comando Negativação
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param
	 * @return
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getComandoCriterioNegativacao(int idNegativador,
			int idUsuarioResponsavel, String identificacaoCI)
			throws ErroRepositorioException;

	/**
	 * Obtem dados Imovel
	 * 
	 * @author Marcio Roberto
	 * @date 01/10/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosImoveis(int idImovel) throws ErroRepositorioException;

	/**
	 * Obtem DadosCliente
	 * 
	 * @author Marcio Roberto
	 * @date 01/10/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosCliente(int idCliente) throws ErroRepositorioException;

	/**
	 * Insere RegistroNegativacao
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer geraRegistroNegativacao(int idNegativador, int saenvio,
			int idNegativadorComando) throws ErroRepositorioException;

	/**
	 * Insere RegistroNegativacao
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer geraRegistroNegativacaoReg(int idNegativador, int saenvio,
			int idNegativadorComando, int idNegativacaoMovimento,
			StringBuilder registroHeader, int quantidadeRegistros,
			Integer idNegCriterio) throws ErroRepositorioException;

	/**
	 * Obtem Negativador Movimento id
	 * 
	 * @author Marcio Roberto
	 * @date 07/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getNegativadorMovimento() throws ErroRepositorioException;

	/**
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 07/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getTpoRegistro(int idNegativador, char tipo)
			throws ErroRepositorioException;

	/**
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getSaEnvioContratoNegativador(int idNegativador)
			throws ErroRepositorioException;

	/**
	 * Obtem getDadosEnderecoCliente
	 * 
	 * @author Marcio Roberto
	 * @date 09/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosEnderecoCliente(int idCliente)
			throws ErroRepositorioException;

	/**
	 * Obtem getDadosEnderecoClienteAlternativo
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getBairroCep(int idCliEnder) throws ErroRepositorioException;

	/**
	 * Obtem getBairro
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getCep(int idCliente) throws ErroRepositorioException;

	/**
	 * Obtem getMunicipio
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getMunicipio(int idCliEnder) throws ErroRepositorioException;

	/**
	 * Obtem getMunicipioCep
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String getMunicipioCep(int idCliEnder)
			throws ErroRepositorioException;

	/**
	 * Obtem getUnidadeFederativa
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getUnidadeFederativa(int idLogradouroBairro)
			throws ErroRepositorioException;

	/**
	 * Obtem getDddFone
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDddFone(int idCliente) throws ErroRepositorioException;

	/**
	 * Obtem geraRegistroNegativacaoRegDetalhe
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer geraRegistroNegativacaoRegDetalhe(int idNegativador,
			int idUsuarioResponsavel, int saenvio, int idNegativadorComando,
			int idNegativacaoMovimento, StringBuilder registro,
			int quantidadeRegistros, int idNegCriterio)
			throws ErroRepositorioException;

	/**
	 * Obtem geraRegistroNegativacaoRegDetalheSPC
	 * 
	 * @author Marcio Roberto
	 * @date 12/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer geraRegistroNegativacaoRegDetalheSPC(int idNegativador,
			int idNegativacaoMovimento, StringBuilder registro,
			int quantidadeRegistros, BigDecimal valorTotalDebitos,
			int idDebitoSituacao, int idImovel, int idLocalidade, int idQuadra,
			int stComercialCD, int numeroQuadra, int iper_id, int idCliente,
			int idCategoria, String cpfCliente, String cnpjCliente,
			Integer idNegCriterio) throws ErroRepositorioException;

	/**
	 * obtemDebitoSituacao
	 * 
	 * @author Marcio Roberto
	 * @date 12/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obtemDebitoSituacao() throws ErroRepositorioException;

	/**
	 * geraRegistroNegativacaoMovimentoRegItem
	 * 
	 * @author Marcio Roberto
	 * @date 13/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void geraRegistroNegativacaoMovimentoRegItem(int idDebSit,
			BigDecimal valorDoc, int idDetalheRegSPC, int idDocTipo,
			Integer idGuiaPagamento, Integer idConta)
			throws ErroRepositorioException;

	/**
	 * geraRegistroImovelNegativacao
	 * 
	 * @author Marcio Roberto
	 * @date 13/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void geraRegistroImovelNegativacao(int idNegativadorComando,
			int idImovel) throws ErroRepositorioException;

	/**
	 * getNextNegativadorMovimentoReg
	 * 
	 * @author Marcio Roberto
	 * @date 14/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getNextNegativadorMovimentoReg()
			throws ErroRepositorioException;

	/**
	 * Obtem Negativacao Comando
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getNegativacaoComando(int idNegativacaoComando)
			throws ErroRepositorioException;

	/**
	 * Obtem Negativacao Criterio
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getNegativacaoCriterio(int idNegativacaoComando)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarComandoNegativacao(
			ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoParaPaginacao(
			ComandoNegativacaoHelper comandoNegativacaoHelper,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarDadosInclusoesComandoNegativacao(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(
			Integer idComandoNegativacao, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 09/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarParametrosComandoNegativacao(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTitularidadeCpfCnpjNegativacao(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoCobranca(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerenciaRegional(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarUnidadeNegocio(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEloPolo(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarSubcategoria(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPerfilImovel(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTipoCliente(Integer idComandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * Obtem Negativacao Criterio
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisClienteCriterio(int idCliente)
			throws ErroRepositorioException;

	/**
	 * Verifica se há negativação para aquele imovel
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaNegativacao(int idImovel)
			throws ErroRepositorioException;

	/**
	 * obtem titularidade dos documentos
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List obtemTitularidadesDocumentos(int idNegativadorCriterio)
			throws ErroRepositorioException;

	/**
	 * obtem dados cliente da negativacao
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List obtemDadosClienteNegativacao(int idImovel,
			Short idClienteRelacaoTipo) throws ErroRepositorioException;

	/**
	 * Verifica se há ocorrencia do imovel na tabela cobranca situacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaImovelCobrancaSituacao(int idImovel)
			throws ErroRepositorioException;

	/**
	 * Verifica se as subCategorias do imovel corresponde as subCategorias do
	 * criterio da negativacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaSubCategoriaImovelNegativacaoCriterio(int idImovel,
			int idCriterio) throws ErroRepositorioException;

	/**
	 * Verifica se os Perfis do imovel corresponde aos Perfis do criterio da
	 * negativacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaPerfilImovelNegativacaoCriterio(int idCriterio,
			int imovelPerfil) throws ErroRepositorioException;

	/**
	 * Verifica se o cliente usuario do imovel corresponde ao cliente tipo da
	 * negativacao criterio
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaTipoClienteNegativacaoCriterio(int idImovel,
			int idCriterio) throws ErroRepositorioException;

	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto
	 * @date 05/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaDebitoCobradoConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto
	 * @date 05/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List verificaImovelParcelamento(int idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0651] Inserir Comando Negativação [FS0015] Verificar existência de
	 * negativação para o imóvel no negativador
	 * 
	 * @author Ana Maria
	 * @date 04/12/2007
	 * 
	 * @param idImovel
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0651] Inserir Comando Negativação [SB0003] Determinar Data Prevista
	 * para Execução do Comando
	 * 
	 * @author Ana Maria
	 * @date 11/12/2007
	 * 
	 * @param idNegativador
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador,
			Integer icSimulacao) throws ErroRepositorioException;

	/**
	 * [UC0651] Inserir Comando Negativação //[FS0014]- Verificar existência de
	 * comando para os mesmos parâmetros
	 * 
	 * @author Ana Maria
	 * @date 13/12/2007
	 * 
	 * @param InserirComandoNegativacaoPorCriterioHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String verificarExistenciaComandoMesmoParametro(
			InserirComandoNegativacaoPorCriterioHelper helper)
			throws ErroRepositorioException;

	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto
	 * @date 05/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaCartaAvisoParcelamento(int idImovel,
			int numeroDiasAtrasoRecebCartaParcel)
			throws ErroRepositorioException;

	/**
	 * Referente a [SB0004] UC0671 - Gerar Movimento de Inclusão de Negativação
	 * 
	 * Condições 1,2,3,4,5 e 6 referente a diferentes critérios
	 * 
	 * @author Marcio Roberto
	 * @date 10/12/2007
	 * 
	 * @param
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List getImovelCondicao(NegativacaoCriterio nCriterio,
			int tipoCondicao) throws ErroRepositorioException;

	/**
	 * Referente a [SB0004] UC0671 - Gerar Movimento de Inclusão de Negativação
	 * 
	 * Método que consulta todos os imoveis que estão nas condições 1,2,3,4,5 e
	 * 6
	 * 
	 * @author Thiago Toscano
	 * @date 27/02/2007
	 * 
	 * @param
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List getImovelCondicao(List IdImovel)
			throws ErroRepositorioException;

	/**
	 * Método consuta os Negativadores que tenham movimento de Exclusão do spc
	 * ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação [SB0003] -
	 * Selecionar Negativadores
	 * 
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection consultarNegativadoresParaExclusaoMovimento()
			throws ErroRepositorioException;

	/**
	 * Método consuta os NegativadoresMovimentoReg que tenham movimento de
	 * Exclusão do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação [SB0003] -
	 * Selecionar Negativadores
	 * 
	 * 
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection consultarNegativacoesParaExclusaoMovimento(Integer[] ids)
			throws ErroRepositorioException;

	/**
	 * Consulta todos os contratos em vigencia de um negativador
	 * 
	 * @author Thiago Toscano
	 * @date 26/12/2007
	 * 
	 * @param negativador
	 * @return
	 * @throws ErroRepositorioException
	 */
	public NegativadorContrato consultarNegativadorContratoVigente(
			Integer negativador) throws ErroRepositorioException;

	/**
	 * Método que consulta os NegativadorMovimentoReg que representam o arquivo
	 * dos movimentos de exclusao de negativacao, para a geracao do arquvo txt
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação [SB0009] - Gerar
	 * Arquivo TxT para Envio ao Negativador
	 * 
	 * @author Thiago Toscano
	 * @date 27/12/2007
	 * 
	 * @param idMovimento
	 * @return o arquivo
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativadorMovimentoRegistroParaGerarArquivo(
			Integer codigoNegativadorMovimento, String tipoRegistro)
			throws ErroRepositorioException;

	/**
	 * Método usado para pesquisa de Comando Negativação (Helper)
	 * 
	 * [UC0655] Filtrar Comando Negativação
	 * 
	 * @author Thiago Vieira
	 * @date 02/01/2008
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoHelper(
			ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ErroRepositorioException;

	/**
	 * Método que retorna todas NegativacaoComando que ainda nao tenha sido
	 * executada (dataHoraRealizacao == null) [UC0687] Executar Comando de
	 * Negativação [Fluxo Principal] - Item 2.0
	 * 
	 * @author Thiago Toscano
	 * @date 21/01/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComandadoParaExecutar()
			throws ErroRepositorioException;

	/**
	 * 
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação Fluxo principal Item 1.0
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */

	public List consultarLocalidadeParaGerarResumoDiarioNegativacao()
			throws ErroRepositorioException;

	/**
	 * 
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação Fluxo principal Item 1.0
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(
			Integer idLocalidade) throws ErroRepositorioException;

	/**
	 * 
	 * Apaga todos os ResumoNegativacao [UC0688] Gerar Resumo Diário da
	 * Negativação Fluxo principal Item 2.0
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public void apagarResumoNegativacao() throws ErroRepositorioException;

	/**
	 * 
	 * Apaga todos os ResumoNegativacao [UC0688] Gerar Resumo Diário da
	 * Negativação Fluxo principal Item 2.0
	 * 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public void apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat)
			throws ErroRepositorioException;

	/**
	 * 
	 * Consulta os itens do registro do NegativadorMovimentoReg passado [UC0688]
	 * Gerar Resumo Diário da Negativação [SB0001] Processar Itens da
	 * Negativação
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public List consultarNegativadorMovimentoRegItem(
			Integer codigoNegativadorMovimentoReg)
			throws ErroRepositorioException;

	/**
	 * Consulta a NegativacaoComando de um negativadormovimento
	 * 
	 * [UC0688] Gerar Resumo Diário da Negativação [SB0005] Determinar
	 * Negativação do Imovel
	 * 
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public NegativacaoImoveis consultarNegativacaoImoveisDoNegativadorMovimento(
			Integer codigoNegativadorMovimento, Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método que atualiza o imovel cobranca situacao tipo [UC0688] Gerar Resumo
	 * Diario da Negativacao [SB0005] Determinar Negativação do Imovle Item
	 * 2.1.4
	 * 
	 * @author Thiago Toscano
	 * @date 08/01/2008
	 * 
	 * @param codigoImovel
	 * @param codigoCobrancaSituacao
	 */
	// public List consultarImovelCobrancaSituacao(Integer codigoImovel, Integer
	// codigoCobrancaSituacao);
	public List consultarImovelCobrancaSituacao(Integer codigoImovel,
			Integer codigoCobrancaSituacao) throws ErroRepositorioException;

	/**
	 * 
	 * 
	 * [UC0688] Gerar Resumo Diário da Negativação [SB0002] Determinar Situação
	 * do Débito do Item da Negativação Item 1.1.2.2.3
	 * 
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 * 
	 * @param codigoConta
	 * @return
	 */
	public ContaHistorico consultaContaHistoricoMaisAtual(Integer codigoConta,
			int anoMesReferenciaConta) throws ErroRepositorioException;

	/**
	 * Consulta uma conta caso a conta mais atual ainda não esteja no historico
	 * 
	 * [UC0688] Gerar Resumo Diário da Negativação [SB0002] Determinar Situação
	 * do Débito do Item da Negativação Item 1.1.2.2.3
	 * 
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 * 
	 * @param codigoConta
	 * @return
	 */
	public Conta consultaContaMaisAtual(Integer codigoConta,
			int anoMesReferenciaConta) throws ErroRepositorioException;

	/**
	 * Método que consulta os pagamentos passando o codigo da conta ou do guia
	 * de pagamento [UC0688] Gerar Resumo Diário da Negativação [SB0002]
	 * Determinar Situação do Débito do Item da Negativação Item 4.1.1 ou Item
	 * 4.2.1
	 * 
	 * @author Thiago Toscano
	 * @date 10/01/2008
	 * 
	 * @param codigoConta
	 * @param codigoGuiaPagamento
	 * @return
	 */
	public Collection consultarPagamentoDoItem(Integer codigoConta,
			Integer codigoGuiaPagamento) throws ErroRepositorioException;



	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 * (nesse caso critério)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper)
			throws ErroRepositorioException;

	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 * (nesse caso matrícula)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 * (nesse caso matrícula)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper)
			throws ErroRepositorioException;

	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 * (nesse caso critério)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 *  (nesse caso matrícula)usado no caso de uso [UC0691] (sem paginação)
	 * 
	 * @author Yara Taciane ,Vivianne Sousa
	 * @date 21/01/2008,14/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Integer pesquisarNegativadorMovimentoCount(
			NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para contar a quantidade de ocorrências de
	 * negativadorMovimento Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer verificarTotalRegistrosAceitos(Integer idNegativadorMovimento)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimentoRegistroAceito(
			NegativadorMovimentoHelper negativadorMovimentoHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * @author Ana Maria
	 * @date 21/01/2008
	 * 
	 * @param idComandoNegativacao
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public NegativacaoCriterio pesquisarNegativacaoCriterio(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * Remove Titularidades do CPF/CNPJ da Negativação, Subcategorias, Perfis de
	 * imóvel, Tipos de cliente, Grupos de Cobrança, Gerências Regionais,
	 * Unidades Negócio, Elos Pólo do critério
	 * 
	 * @author Ana Maria
	 * @date 21/01/2008
	 * 
	 * @param idNegativacaoCriterio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void removerParametrosCriterio(Integer idNegativacaoCriterio)
			throws ErroRepositorioException;

	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * Remove Negativação Comando
	 * 
	 * @author Ana Maria
	 * @date 22/01/2008
	 * 
	 * @param idNegativacaoCriterio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void removerNegativacaoComando(Integer idNegativacaoComando)
			throws ErroRepositorioException;

	/**
	 * [UC0652] Atualizar Comando Negativação //[FS0012]- Verificar existência
	 * de comando para os mesmos parâmetros
	 * 
	 * @author Ana Maria
	 * @date 24/01/2008
	 * 
	 * @param InserirComandoNegativacaoPorCriterioHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String verificarExistenciaComandoMesmoParametroAtualizacao(
			InserirComandoNegativacaoPorCriterioHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0671 - [SB0012] - Gerar Arquivo TXT para envio ao negativador] -
	 * geraRegistroNegativacaoRegTrailler
	 * 
	 * @author Marcio Roberto
	 * @date 30/01/2008
	 * 
	 * @param int
	 *            idNegativador, int idNegativacaoMovimento, StringBuilder
	 *            registro, int quantidadeRegistros, int idNegCriterio
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void geraRegistroNegativacaoRegTrailler(int idNegativador,
			int idNegativacaoMovimento, StringBuilder registro,
			int quantidadeRegistros, Integer idNegCriterio)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para pesquisa de negativador movimento usado no caso de uso
	 * [UC0682]
	 * 
	 * @author Yara Taciane
	 * @date 21/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimento(
			NegativadorMovimentoHelper negativadorMovimentoHelper,
			Integer numeroPagina) throws ErroRepositorioException;

	// /**
	// * Método que consulta os pagamentos historcio passando o codigo da conta
	// historico ou do guia de pagamento
	// * [UC0688] Gerar Resumo Diário da Negativação
	// * [SB0002] Determinar Situação do Débito do Item da Negativação
	// * Item 4.1.2 ou Item 4.2.2
	// *
	// * @author Thiago Toscano
	// * @date 10/01/2008
	// *
	// * @param codigoContaHistociro
	// * @param codigoGuiaPagamento
	// * @return
	// */
	// public Collection consultarPagamentoHistorcioDoItem(Integer
	// codigoContaHistociro, Integer codigoGuiaPagamento ) throws
	// ErroRepositorioException;

	/**
	 * Método que consulta os pagamentos historcio passando o codigo da conta
	 * historico ou do guia de pagamento [UC0688] Gerar Resumo Diário da
	 * Negativação [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 4.1.2 ou Item 4.2.2
	 * 
	 * @author Thiago Toscano
	 * @date 10/01/2008
	 * 
	 * @param codigoContaHistociro
	 * @param codigoGuiaPagamento
	 * @return
	 */
	public Collection consultarPagamentoHistorcioDoItem(ContaHistorico ch,
			GuiaPagamentoHistorico gp) throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegativadorMovimentoRegistroAceito(
			NegativadorMovimentoHelper negativadorMovimentoHelper,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 13/02/2008
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosNegativadorCriterio(int idCommandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 13/02/2008
	 * 
	 * @param
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDadosContratoNegativadorCriterio(int idCommandoNegativacao)
			throws ErroRepositorioException;

	/**
	 * Método consutla um negativacaoComando [UC0671] Gerar Movimento de
	 * Inclusao de Negativacao [SB003] Gear moviemnto de inclusao de negativacao
	 * para os imoveis do clietne item 1.0
	 * 
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public List consultarImoveisCliente(NegativacaoCriterio nCriterio,
			Integer idLocalidade) throws ErroRepositorioException;

	/**
	 * Método consutla um negativacaoComando [UC0671] Gerar Movimento de
	 * Inclusao de Negativacao [Fluixo princiapal] 4.0
	 * 
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 * 
	 * @param idNegativacaoComando
	 * @param datahora
	 * @param quantidade
	 * @param valorTotalDebito
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComando(
			Integer idNegativacaoComando) throws ErroRepositorioException;

	/**
	 * Pesquisa a coleção de clientes do imóvel para negativação sem o cliente
	 * empresa do sistema parâmetro
	 * 
	 * @author Ana Maria
	 * @date 17/12/2008
	 * @param idImovel
	 * @return Collection
	 * @exception ErroRepositorioException
	 * 
	 */
	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel,
			String cnpjEmpresa) throws ErroRepositorioException;

	/**
	 * Obtem dados Imovel
	 * 
	 */
	public List pesquisarImovel(int idImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para apresentar os registros de negativadorMovimento
	 * Registro aceitos usado no caso de uso [UC0681]
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarNegatiacaoParaImovel(Imovel imovel,
			Negativador negativador) throws ErroRepositorioException;

	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para apresentar o parcelamento da conta usado no caso de uso
	 * [UC0688]
	 * 
	 * @author Yara Taciane
	 * @date 13/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Parcelamento consultaParcelamentoConta(Integer idConta, Integer situacaoParcelamento, Date dataEnvioNegativadorMovimento)
			throws ErroRepositorioException;

	/**
	 * 
	 * Método usado para apresentar o parcelamento da guia Pagamento usado no
	 * caso de uso [UC0688]
	 * 
	 * @author Yara Taciane
	 * @date 13/01/2008
	 * 
	 * @param negativadorMovimentoHelper
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Parcelamento consultaParcelamentoGuiaPagamento(Integer idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna a maior data do Negativador Moviemnto registro Item [UC0688]
	 * Gerar Resumo Diário da Negativação.
	 * 
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */

	public Date getMaiorDataNegativadorMovimentoRegItem(
			CobrancaDebitoSituacao cobrancaDebitoSituacao,
			NegativadorMovimentoReg negativadorMovimentoReg)
			throws ErroRepositorioException;

	/**
	 * 
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relatório
	 * Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCount(
			DadosConsultaNegativacaoHelper helper)
			throws ErroRepositorioException;

	/**
	 * 
	 * Conta a quantidade de Clientes Negativados [UC0693] Gerar Relatório
	 * Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection pesquisarRelatorioAcompanhamentoClientesNegativador(
			DadosConsultaNegativacaoHelper helper)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o somatório do valor do Débito do NegativadoMovimentoReg pela
	 * CobrancaDebitoSituacao [UC0693] Gerar Relatório Acompanhamaneto de
	 * Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public BigDecimal pesquisarSomatorioValorDebito(
			NegativadorMovimentoReg negativadorMovimentoReg,
			CobrancaDebitoSituacao cobrancaDebitoSituacao)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * 
	 * Conta a quantidade de Neg [UC0693] Gerar Relatório Negativacoes Excluidas
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Integer pesquisarRelatorioNegativacoesExcluidasCount(
			DadosConsultaNegativacaoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0693] Gerar Relatório Negativacoes Excluidas
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Collection pesquisarRelatorioNegativacoesExcluidas(
			DadosConsultaNegativacaoHelper helper)
			throws ErroRepositorioException;

	/**
	 * [UC0672] Registrar Moviemento Retorno dos Negativadores
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel,
			CobrancaSituacao cobrancaSituacao) throws ErroRepositorioException;

	/**
	 * [UC0672] Registrar Moviemento Retorno dos Negativadores
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public NegativadorMovimentoReg getNegativadorMovimentoReg(
			NegativadorMovimento negativadorMovimento, Integer numRegistro)
			throws ErroRepositorioException;

	/**
	 * [UC0672] Registrar Moviemento Retorno dos Negativadores
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public NegativadorMovimento getNegativadorMovimento(
			Negativador negativador, Integer numeroSequencialEnvio)
			throws ErroRepositorioException;

	/**
	 * [UC0672] Registrar Moviemento Retorno dos Negativadores
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection consultarImovelCobrancaSituacaoPorNegativador(
			Imovel imovel, Integer codigoNegativador)
			throws ErroRepositorioException;

	/**
	 * [UC0672] Registrar Moviemento Retorno dos Negativadores
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */

	public Collection pesquisarImovelCobrancaSituacao(Imovel imovel,
			CobrancaSituacao cobrancaSituacao) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisar a negativação do imóvel . [UC0675] Excluir Negativação Online.
	 * 
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Collection pesquisarImovelNegativado(Imovel imovel,
			Negativador negativador) throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o NegativadorMovimentoReg [UC0673] Excluir Negativação Online
	 * 
	 * @author Yara Taciane
	 * @date 27/03/2008
	 */
	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(
			Imovel imovel, Negativador negativador)
			throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar Movimento de Inclusão de Negativação]
	 * SB0004 -  Gerar Movimento de Inclusão de Negativação para os Imóveis
	 * @author Anderson Italo
	 * @date 19/03/2010
	 */
	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio)
	throws ErroRepositorioException;
	
	public List pesquisarImoveisParaNegativacao(Integer idLocalidade, Integer integer)
			throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar imoveis do comando de simulacao por rota
	 * 
	 * @author Unknown, Francisco do Nascimento
	 * @date Unknown, 23/01/2009
	 *
	 * @param nComando Comando de negativacao
	 * @param idRota Identificador da rota
	 * 
	 * @return Colecao de matriculas de imoveis
	 * 
	 * @throws ErroRepositorioException
	 */	
	public List consultarImoveisNegativacaoSimulada(NegativacaoComando nComando,
		Integer idRota) throws ErroRepositorioException;

	public List pesquisarImoveisOutrasCondicoes(Integer idNegativacaoCriterio,
			Integer numeroIndice, Integer quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o ResumoNegativacao [UC0688] Gerar Resumo Diário da Negativação
	 * 
	 * @author Yara Taciane
	 * @date 09/04/2008
	 */
	public ResumoNegativacao pesquisarResumoNegativacao(
			ResumoNegativacaoHelper resumoNegativacaoHelper)
			throws ErroRepositorioException;

	public NegativadorMovimentoReg pesquisarRegistroTipoConsumidor(
			Integer numeroRegistro, Integer idNegMovimento)
			throws ErroRepositorioException;

	public NegativadorExclusaoMotivo pesquisarCodigoMotivoExclusao(
			Integer idCobrancaDebitoSituacao, Integer idNegativador,
			String descricaoExclusao) throws ErroRepositorioException;
	
	public NegativadorExclusaoMotivo pesquisarCodigoMotivoExclusaoSERASA(
			Integer idCobrancaDebitoSituacao, Integer idNegativador,
			Short codigoExclusaoMotivo) throws ErroRepositorioException;

	public Conta consultaConta(Integer codigoConta)
			throws ErroRepositorioException;

	public ContaHistorico consultaContaHistorico(Integer codigoConta)
			throws ErroRepositorioException;

	public GuiaPagamento consultaGuiaPagamento(Integer codigoGuiaPagamento)
			throws ErroRepositorioException;

	public GuiaPagamentoHistorico consultaGuiaPagamentoHistorico(
			Integer codigoGuiaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0651] Inserir Comando Negativação [FS0026] Verificar existência de
	 * comando para o negativador na data
	 * 
	 * @author Ana Maria
	 * @date 07/05/2008
	 * 
	 * @param idNegativador
	 * @param Data
	 * @return boolean
	 */
	public boolean verificarExistenciaComandoNegativador(String idNegativador,
			Date dataPrevista) throws ErroRepositorioException;

	/**
	 * Pesquisa a Data da Exclusão da Negativação
	 * 
	 * @author Yara Taciane
	 * @date 9/05/2008
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataExclusaoNegativacao(int idImovel,
			int idNegativacaoComando) throws ErroRepositorioException;

	public Object[] pesquisarComandoNegativacaoSimulado(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * Pesquisa a Negativador Resultado Simulacao
	 * 
	 * @author Yara Taciane
	 * @date 9/05/2008
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarNegativadorResultadoSimulacao(
			Integer idNegativacaoComando) throws ErroRepositorioException;

	/**
	 * Verifica se a situação da ligação de água do imovel corresponde as
	 * situação da ligação de água do criterio da negativacao.
	 * 
	 * @author Ana Maria
	 * @date 12/06/2008
	 * 
	 * @param int
	 * @param int
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificaLigacaoAguaImovelNegativacaoCriterio(int idCriterio,
			int idLigacaoAguaSituacao) throws ErroRepositorioException;

	/**
	 * Verifica se a situação da ligação de esgoto do imovel corresponde as
	 * situação da ligação de esgoto do criterio da negativacao.
	 * 
	 * @author Ana Maria
	 * @date 12/06/2008
	 * 
	 * @param int
	 * @param int
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificaLigacaoEsgotoImovelNegativacaoCriterio(
			int idCriterio, int idLigacaoEsgotoSituacao)
			throws ErroRepositorioException;

	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSituacaoLigacaoAguaComando(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSituacaoLigacaoEsgotoComando(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	/**
	 * Verificar existência critérios do comando
	 * 
	 * @author Ana Maria
	 * @date 09/06/2008
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] verificarExistenciaCriterio(Integer idCriterio)
			throws ErroRepositorioException;

	/**
	 * Consultar o Motivo da Exclusao do Negativador
	 * 
	 * @author Yara Taciane
	 * @date 22/07/2008
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public NegativadorExclusaoMotivo pesquisarMotivoExclusao(
			Integer idMotivoExclusao) throws ErroRepositorioException;

	/**
	 * 
	 * Informações Atualizadas em (maior data e hora da última execução
	 * 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public Date getDataUltimaAtualizacaoResumoNegativacao(
			Integer numeroExecucaoResumoNegativacao)
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisar as rotas dos Imóveis
	 * 
	 * @author Ana Maria, Francisco do Nascimento
	 * @date 05/06/2008, 14/01/09
	 * 
	 */
	public Collection pesquisarRotasImoveis() throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisar as localidades dos Imóveis que estão resultado da simulação
	 * 
	 * @author Ana Maria
	 * @date 05/06/2008
	 * 
	 */
	public Collection pesquisarRotasImoveisComandoSimulacao(
			Integer idNegativacaoComando) throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegComando)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadeInclusaoItemNegativacao(
			Integer idNegComando) throws ErroRepositorioException;

	public Collection consultarNegativadorMovimentoRegistroParaGerarArquivoInclusao(
			Integer codigoNegativadorMovimento, String tipoRegistro)
			throws ErroRepositorioException;

	/**
	 * Verifica a quantidade de imóveis que já foram incluidos para o comando da
	 * negativação
	 * 
	 * @author Ana Maria
	 * @date 25/06/2008
	 * 
	 * @param Integer
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQtdImovelNegativacaoComando(
			Integer idComandoNegativacao) throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(
			Integer idNegComando) throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por grupo de cobrança para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 * 
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorCobrancaGrupoParaNegativacao(
			NegativacaoCriterio nCriterio) throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por gerencia regional para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 * 
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorGerenciaRegionalParaNegativacao(
			NegativacaoCriterio nCriterio) throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por unidade de negócio para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 * 
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List pesquisarRotasPorUnidadeNegocioParaNegativacao(
			NegativacaoCriterio nCriterio) throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por localidade para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 * 
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List pesquisarRotasPorLocalidadeParaNegativacao(
			NegativacaoCriterio nCriterio) throws ErroRepositorioException;

	public Integer pesquisarNegativadorMovimentoRegRetMot(
			Integer idNegativadorMovimentoReg,
			Integer idNegativadorRetornoMotivo) throws ErroRepositorioException;

	/**
	 * [UC0694] - Gerar Relatório de Negativações Excluídas
	 * 
	 * @author Yara T. Souza
	 * @date 16/01/2009
	 */
	public List pesquisarNegativadorMovimentoRegItens(
			Integer idNegativadorMovimentoReg) throws ErroRepositorioException;

	/**
	 * [UC0014] - ManterImovel
	 * 
	 * Verificar existência de negativação para o cliente-imóvel
	 * 
	 * @author Victor Cisneiros
	 * @date 12/01/2009
	 */
	public boolean verificarNegativacaoDoClienteImovel(
			Integer idCliente, Integer idImovel) throws ErroRepositorioException;

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por localidade inicial e final para um critério de
	 * negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 * 
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List pesquisarRotasPorLocalidadesParaNegativacao(
			NegativacaoCriterio nCriterio) throws ErroRepositorioException;
	
	
	
	/**
	 * Gerar Relatório Negaticações Excluídas
	 * 
	 * Pesquisar o somatório do valor paga ou parcelado pelo registro negativador
	 * 
	 * @author Yara T. Souza
	 * @date 14/01/2009
	 * 
	 * @param idNegativadorMovimentoReg,idCobrancaDebitoSituacao
	 * @return
	 * @throws ErroRepositorioException
	 */

	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg, Integer idCobrancaDebitoSituacao)throws ErroRepositorioException;
	
	/**
	 *
	 * Consulta as rotas para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Francisco do Nascimento
	 * @date 03/02/2009
	 */
	public List consultarRotasParaGerarResumoDiarioNegativacao()
		throws ErroRepositorioException;
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * @author Vivianne Sousa
	 * @date 02/04/2009
	 */
	public Integer consultaDebitoACobrarParcelamento(Integer idParcelamento)
		throws ErroRepositorioException;


	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * @author Vivianne Sousa
	 * @date 02/04/2009
	 */
	public Integer consultaDebitoACobrarHistoricoParcelamento(Integer idParcelamento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

    /**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrarHistorico
	 */
    public DebitoACobrarHistorico obterDebitoACobrarHistorico(Integer idDebitoACobrarHistorico) throws ErroRepositorioException ;
    
    /**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public Integer obterIDParcelamentoAtual(Integer idDebitoACobrar) throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdContas
     */
    public Collection pesquisarIdsContaHistoricoEntradaParcelamento(Integer idParcelamento)throws ErroRepositorioException ;

    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdContas
     */
    public Collection pesquisarIdsContaEntradaParcelamento(Integer idParcelamento)throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public BigDecimal pesquisarValorPagamentoDeContas(Collection idsColecaoConta)throws ErroRepositorioException;
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public BigDecimal pesquisarValorPagamentoDeContasHistorico(Collection idsColecaoConta) throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuiasHistorico
     */
    public GuiaPagamentoHistorico pesquisarGuiaHistoricoEntradaParcelamento(Integer idParcelamento)throws ErroRepositorioException;

    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Integer pesquisarIdGuiaEntradaParcelamento(Integer idParcelamento)throws ErroRepositorioException;
    
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 06/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public BigDecimal pesquisarValorPagamentoDeGuiaHistorico(Integer idGuia) throws ErroRepositorioException;

    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public BigDecimal pesquisarValorPagamentoDeGuia(Integer idGuia)throws ErroRepositorioException;
	
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Collection pesquisarIdsContasHistoricoCobrancaParcelamento(Integer idImovelParcelamento, Integer referenciaParcelamento)
            throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Collection pesquisarIdsContasCobrancaParcelamento(Integer idImovelParcelamento, Integer referenciaParcelamento)
            throws ErroRepositorioException;
    
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 07/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public Integer pesquisarQtdeContasHistoricoPagas(Collection idsColecaoConta) throws ErroRepositorioException;

    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 07/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public Integer pesquisarQtdeContasPagas(Collection idsColecaoConta)throws ErroRepositorioException;
	
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 07/04/2009
     * 
     * @param idParcelamento
     * @return parcelamento
     */
    public Parcelamento pesquisarDadosParcelamento(Integer idParcelamento)throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 15/04/2009
     * 
     * @param idParcelamento
     * @return parcelamento
     */
    public BigDecimal pesquisarValorPagamentoDeGuiaHistorico(Integer idGuia, Integer anoMesReferencia) throws ErroRepositorioException;
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @param idParcelamento
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public NegativadorMovimentoRegParcelamento pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg,Integer idParcelamento)throws ErroRepositorioException;
	
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * com NMRP_ICPARCELAMENTOATIVO=1 e NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Collection pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoAtivo(
			Integer idNegativadorMovimentoReg)throws ErroRepositorioException;
	
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * menor PARC_ID da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * com NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG.
     * 
     * @author Vivianne Sousa
     * @data 23/04/2009
	 */
	public Integer pesquisarMenorParcelamentoNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg)throws ErroRepositorioException;
	
	/**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG e PARC_ID=Identificador do Parcelamento)
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @param idParcelamento
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public Integer pesquisarIdNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg,Integer idParcelamento)
			throws ErroRepositorioException;
	
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG e 
     * PARC_ID=Identificador do Parcelamento e NMRP_ICPARCELAMENTOATIVO=2
     * 
     * @author Vivianne Sousa
     * @data 23/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Integer pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoInativo(
			Integer idNegativadorMovimentoReg, Integer idParcelamento)throws ErroRepositorioException;
	
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * [UC0694] - Gerar Relatório Negativações Excluídas
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public Collection pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg)throws ErroRepositorioException;
	
	/**
	 *
	 * Retorna o somatório do VALOR PAGO e do VALOR CANCELADO 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Vivianne Sousa
	 * @date 29/04/2009
	 */
	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg) throws ErroRepositorioException;

	/**
     * [UC0937] Obter Itens de Negativação Associados à Conta
     * 
     * pesquisa a partir da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 E CNTA_ID com o valor diferente de nulo 
     * E ( (CNTA_ID=CNTA_ID da tabela CONTA com IMOV_ID=Id do Imóvel recebido 
     * e CNTA_AMREFERENCIACONTA=Referência recebida) 
     * E NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG com IMOV_ID=Id do Imóvel recebido
     * 
     * @author Vivianne Sousa
     * @data 10/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAConta(
			Integer idImovel, Integer referencia) throws ErroRepositorioException ;
	
	/**
     * [UC0937] Obter Itens de Negativação Associados à Conta
     * 
     * pesquisa a partir da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 E CNTA_ID com o valor diferente de nulo 
     * E ((CNTA_ID=CNTA_ID da tabela CONTA_HISTORICO com IMOV_ID=Id do Imóvel recebido
     * e CNHI_AMREFERENCIACONTA=Referência recebida) ) 
     * E NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG com IMOV_ID=Id do Imóvel recebido
     * 
     * @author Vivianne Sousa
     * @data 10/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAContaHistorico(
			Integer idImovel, Integer referencia) throws ErroRepositorioException;
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores
	 * [SB0017] - Atualizar Item da Negativação
	 * 
	 * [UC0265] Inserir Pagamentos 
	 * [SB0006] - Atualizar Item da Negativação
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(Integer idNegativadorMovimentoRegItem,
			BigDecimal valorPago, Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao) throws ErroRepositorioException ;
	
	/**
     * [UC0242] - Registrar Movimento dos Arrecadadores
     * Fluxo Principal 10.1.2.2
     * 
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 e GPAG_ID=GPAG_ID do PAGAMENTO
     * 
     * @author Vivianne Sousa
     * @data 11/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ErroRepositorioException ;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0007] - Verifica Associação do Pagamento com Itens de Negativação
	 * 1.1.	O sistema obtém dados da conta do pagamento 
	 * (a partir da tabela CONTA com CNTA_ID=CNTA_ID da tabela PAGAMENTO ou 
	 * a partir da tabela CONTA_HISTORICO com CNTA_ID=CNTA_ID da tabela PAGAMENTO).
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2009
	 */
	public Object[] pesquisarImovelEReferenciaDaConta(Integer idContaGeral) throws ErroRepositorioException;
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0008] - Atualizar Item da Negativação - Desfazer Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem) throws ErroRepositorioException;
	
	/**
	 * [UC0147] Cancelar Conta
	 * [SB0001] - Atualizar Item da Negativação
	 * [SB0002] - Atualizar Item Negativação - Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada) throws ErroRepositorioException;
	
	/**
	 * [UC0329] Restabelecer Situação Anterior de Conta 
	 * [SB0002] - Atualizar Item da Negativação-Desfazer Retificação
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada,
			BigDecimal valorTotalContaRetificada,
			BigDecimal valorPago) throws ErroRepositorioException ;
	
	/**
	 * [UC0329] Restabelecer Situação Anterior de Conta 
	 * [SB0001] - Atualizar Item da Negativação-Desfazer Cancelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			BigDecimal valorCancelado) throws ErroRepositorioException;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * verifica se existe negativador movimento reg item 
	 * associado a guia de pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ErroRepositorioException ;
	
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAConta(
			Integer idConta) throws ErroRepositorioException ;
	
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosADebitoACobrar(
			Integer idDebitoACobrar) throws ErroRepositorioException ;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * [SB0001] - Atualizar Item da Negativação
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [SB0013] - Atualizar Item da Negativação
	 * 
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public Integer atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Integer idCobrancaDebitoSituacao,
			Date dataSituacaoDebito,
			boolean indicadorVerificaSeParcelado) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public Integer pesquisarIdNegativadorMovimentoReg(
			Integer idNegativadorMovimentoRegItem)
			throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public Integer pesquisarIdContaNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem)
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(
			Integer idContaGeral) throws ErroRepositorioException ;
	
	/**
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public void atualizarIndicadorSituacaoDefinitivaNmri(
			Integer idNegativadorMovimentoRegItem,
			Short indicadorSituacaoDefinitiva) throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 2.0
	 *
	 * @author Vivianne Sousa
	 * @date 30/09/2009
	 */
	public void  apagarResumoNegativacao(Integer numeroExecucaoResumoNegativacao, Integer idRota)
		throws ErroRepositorioException;
	
	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 30/10/2009
	 */
	public List consultarNegativadorMovimentoReg(Integer idSetor, int quantidadeInicio, int quantidadeMaxima)
		throws ErroRepositorioException;
	
	/**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id do negativador movimento reg que a situação de débito de cobrança da negativação 
	 * corresponda a “Parcelado” (CDST_ID da tabela NEGATIVADOR_MOVIMENTO_REG com o valor correspondente a parcelado) 
	 * e o parcelamento esteja ativo (existe ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com NMRP_ICPARCELAMENTOATIVO=1)
     * 
     * @author Vivianne Sousa
     * @data 30/10/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegComParcelamentoAtivo(
			Integer idRota)throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 09/02/2010
	 */
	public List consultarSetorParaGerarResumoDiarioNegativacao()
	throws ErroRepositorioException;
	
	/**
	 *
	 * Verificar ocorrência de movimento de exclusão incompleto
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public Integer  verificarOcorrenciaMovimentoExclusaoIncompleto()
		throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void atualizarNegativacaoImoveis(
			Integer idNegativadorMovimento) throws ErroRepositorioException ;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void atualizarCodigoExclusaoTipoNegativadorMovimentoReg(
			Integer idNegativadorMovimento) throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void  apagarNegativadorMovimentoReg(Integer idNegativadorMovimento)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void  apagarNegativadorMovimento(Integer idNegativadorMovimento)
		throws ErroRepositorioException ;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 04/03/2010
	 */
	public void atualizarSituacaoCobrancaNegativadorMovimentoReg(Integer idSituacaoCobranca, Integer idNegativadorMovimentoReg)
	throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 04/03/2010
	 */
	public Integer pesquisarQuantidadeImovelCobrancaSituacao(Integer idImovel,Integer idSituacaoCobranca) throws ErroRepositorioException;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarNegativadorMovimentoReg(Integer idCobrancaDebitoSituacao,
			Date dataSituacaoDebito, Short indicadorSituacaoDefinitiva, 
			Short indicadorItemAtualizado,Integer idNegativadorMovimentoReg)throws ErroRepositorioException ;
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarNegativadorMovimentoReg(Short indicadorSituacaoDefinitiva, 
			Short indicadorItemAtualizado,Integer idNegativadorMovimentoReg)throws ErroRepositorioException ;
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarIndicadorItemAtualizadoNegativadorMovimentoReg(Short indicadorItemAtualizado, 
			Collection idsNegativadorMovimentoRegItem)throws ErroRepositorioException;
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	
	public void atualizarDataConfirmacaoNegativacaoImoveis(
			Integer idNegativacaoImoveis, Date dataConfirmacao) throws ErroRepositorioException;
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public Collection pesquisarNegativadorMovimentoReg(Integer idLocalidade, 
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException;
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao()
		throws ErroRepositorioException ;
	
	/**
	 * [UC0473] Consultar Dados Complementares do Imóvel
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2010
	 */
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel)
	throws ErroRepositorioException;
	
	/**
	 * [UC0651] - Inserir Comando de Negativação
	 * [FS0030] - Verificar existência de inclusão no negativador para o imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Integer verificarExistenciaDeInclusaoNoNegativadorParaImovel(
			Integer idImovel,Integer idNegativador) throws ErroRepositorioException;
	
	/**
	 * [UC0671] - Gerar Movimento de Inclusão de Negativação
	 * [SB0006] – Verificar Critério de Negativação para o Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Integer verificarExistenciaDeCobrancaSituacaoTipoParaImovel(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0671] - Gerar Movimento de Inclusão de Negativação
	 * [SB0006] – Verificar Critério de Negativação para o Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Collection verificarExistenciaDeCobrancaSituacaoHistoricoParaImovel(
			Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
     * @author Vivianne Sousa
     * @data 08/07/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivo(
			Integer idNegativacaoCriterio) throws ErroRepositorioException;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 08/07/2010
	 */
	public Collection pesquisarNegativadorMovimentoRegPorImovel(
			Integer idImovel, Date dataAtualMenosNNDiasRetorno,
			Integer idNegativador) throws ErroRepositorioException;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 12/07/2010
	 */
	public Integer pesquisarUltimoNegativadorRetornoMotivoDoReg(
			Integer idImovel, Integer idNegativador) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 12/07/2010
	 */
	public Integer pesquisarIdNegativCritNegRetMot(
			Integer idNegativadorRetornoMotivo,Integer idNegativacaoCriterio) 
			throws ErroRepositorioException ;
	
	/**
	 * [UC0472] Consultar Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 03/12/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivoDoReg(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0681] Consultar Movimentos dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 07/12/2010
	 */
	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(
			Integer usuarioCorrecao, Short indicadorCorrecao, 
			Collection colecaoIdsNegativadorMovimentoReg) throws ErroRepositorioException;
	
	/**
	 * Verifica se situacao cobranca tipo e a mesma do imovel
	 * 
	 * @author Rafael Pinto
	 * @date 27/01/2010
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaCobrancaSituacaoEspecialNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException ;
	
	/**
	 * Verifica Situacao Cobranca
	 * 
	 * @author Rafael Pinto
	 * @date 26/01/2011
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaCobrancaSituacaoNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException ;

	/**
	 *
	 * Conta a quantidade de Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 10/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException;
	
	/**
	 *
	 * Soma os valores de débitos dos Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException;
	
	/**
	 *
	 * Soma os valores Pagos dos Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException;
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados com contas pagas na Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade Total de Itens (quantidade de linhas na tabela cobranca.NEGATD_MOV_REG_ITEM com NMRG_ID=NMRG_ID da tabela 
	 * cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando 
	 * de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeTotalItens(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade Total de Regs (quantidade de linhas na tabela cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela 
	 * cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeTotalRegistros(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Reg (quantidade de linhas na tabela cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela 
	 * cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmRegistro(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Negativação Imóveis (quantidade de linhas na tabela cobranca.NEGATIVAOCAO_IMOVEIS com 
	 * NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmNegativacao(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Situção de Cobrança (quantidade de linhas na tabela cadastro.IMOVEL_COBRANCA_SITUACAO com 
	 * ISCB_DTRETIRADACOBRANCA com o valor nulo e CBST_ID=16 ou 17 (“Em Análise para Negativação”) e IMOV_ID=IMOV_ID da 
	 * tabela cobranca.NEGATIVAOCAO_IMOVEIS com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmCobrancaSituacao(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarImovelCobrancaSituacao(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoImoveis(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoMovRegItem(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoMovReg(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativadorMovimentoPorComando(Integer idComando) throws ErroRepositorioException ;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 02/05/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativadorMovimentoRegParcelamento(Integer idComando) throws ErroRepositorioException ;
	
	
	/**
	 * [UC1005] - Determinar Confirmação da Negativação
	 * [SB0002] – Atualizar Data da Retirada da Situação Carta Enviada
	 * 
	 * @author Arthur Carvalho
	 * @date 18/05/2011
	 * @param dataExlusao
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataRetiradaSituacaoCartaEnviada(Date dataExlusao, Integer idImovel) throws ErroRepositorioException;
	
	
}