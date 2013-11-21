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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gerencial.arrecadacao;

import gcom.arrecadacao.DevolucaoSituacao;
import gcom.cobranca.DocumentoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialArrecadacaoHBM implements IRepositorioGerencialArrecadacao {

	private static IRepositorioGerencialArrecadacao instancia;

	/**
	 * Construtor da classe RepositorioArrecadacaoHBM
	 */
	private RepositorioGerencialArrecadacaoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialArrecadacao getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialArrecadacaoHBM();
		}

		return instancia;
	}
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto]
	 * 
	 * @author Ivan Sérgio
	 * @date 19/05/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgoto(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {
	
		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = 
				"select " +
				" 	case when (conta.id is not null) then " +
				"		conta.imovel.id " +
				"	else " +
				"		paga.imovel.id " +
				"	end, " + // 0
				" 	case when (conta.id is not null) then " +
				"		locaConta.gerenciaRegional.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.gerenciaRegional.id " +
				"		end " +
				"	end, " + // 1
				"	case when (conta.id is not null) then " +
				"		locaConta.unidadeNegocio.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.unidadeNegocio.id " +
				"		end " +
				"	end, " + // 2
				"	case when (conta.id is not null) then " +
				"		locaConta.localidade.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.localidade.id " +
				"		end " +
				"	end, " + // 3
				"	case when (conta.id is not null) then " +
				"		locaConta.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.id " +
				"		end " +
				"	end, " + // 4
				"	case when (conta.id is not null) then " +
				"		qdra.setorComercial.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 5
				"	case when (conta.id is not null) then " +
				"		qdra.rota.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 6
				"	case when (conta.id is not null) then " +
				"		qdra.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 7
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		5 " +
				"	else " +
				"       case when (conta.id is not null) then " +
				"            contaPerfil.id " +
				"   else " +
				"		imov.imovelPerfil.id " +
				"    end " +
				"	end, " + // 8
				"	case when (conta.id is not null) then " +
				"		conta.ligacaoAguaSituacao.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 9
				"	case when (conta.id is not null) then " +
				"		conta.ligacaoEsgotoSituacao.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 10
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"			liga.ligacaoAguaPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 11
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"			lige.ligacaoEsgotoPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				"	0 as indicadorContasRecebida, " + // 15
				"	case when ( (conta.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +

				"		9 " +
				"	else " +
				"		case when (conta.id is not null) then " +
				"			case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
				"				0 " +
				"			else " +
				/*"				case when ( (paga.dataPagamento > paga.contaGeral.conta.dataVencimentoConta) and " +
				"					      ( ( year(paga.dataPagamento) || month(paga.dataPagamento) ) = " +
				"						  ( year(paga.contaGeral.conta.dataVencimentoConta) || month(paga.contaGeral.conta.dataVencimentoConta) ) ) ) then " +
				"					1 " +*/
				"				case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
				"						    ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
				" 					      ) then " +
				"					1 " +
				"				else " +
				"					case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
				"						99 " +
				"					end " +
				"				end " +
				"			end " +
				"		end " +
				
				"	end, " + // 16
				"	case when (conta.id is not null) then " +
				"		conta.codigoSetorComercial " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 17
				"	case when (conta.id is not null) then " +
				"		conta.quadra " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 18
				"	case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"		coalesce(conta.valorAgua, 0) " +
				"	else " +
				"		0 " +
				"	end, " + // 19
				"	case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"		coalesce(conta.valorEsgoto, 0) " +
				"	else " +
				"		0 " +
				"	end, " + // 20
				"	case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
				"		coalesce(paga.valorPagamento, 0) " +
				"	else " +
				"		0 " +
				"	end as valorNaoIdentificado, " + // 21
				"	paga.arrecadacaoForma.id, " + // 22
				"	aviso.arrecadador.id, " + // 23
				"	case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"		coalesce(conta.valorImposto, 0) " +
				"	else " +
				"		0 " +
				"	end, " + // 24
				"	paga.anoMesReferenciaPagamento, " + // 25
				"	paga.dataPagamento, " + // 26
				" 	case when (conta.id is not null) then " +
				"		conta.dataVencimentoConta " +
				"	else " +
				"		null " +
				"	end " + // 27
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	inner join paga.contaGeral contaGeral " +
				"	inner join paga.localidade locaPaga " +
				"	left join contaGeral.conta conta " +
				"	left join conta.imovel imov " +				
				"   left join conta.imovelPerfil contaPerfil " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	left join conta.localidade locaConta " +
				"	left join conta.quadraConta qdra " +
				"	left join paga.avisoBancario as aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade";
			
			
			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Aguá/Esgoto - Valor Nao Identificado]
	 * 
	 * @author Ivan Sérgio
	 * @date 02/06/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado(
			int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {
	
		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	imov.id, " + // 0
				"	locaPaga.gerenciaRegional.id, " + // 1
				"	locaPaga.unidadeNegocio.id, " + // 2
				"	locaPaga.localidade.id, " + // 3
				"	locaPaga.id, " + // 4
				"	0, " + // 5
				"	0, " + // 6
				"	0, " + // 7
				"	5, " + // 8
				"	1, " + // 9
				"	1, " + // 10
				"	0, " + // 11
				"	0, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				//"	case when ( (year(paga.dataPagamento) || month(paga.dataPagamento)) < paga.anoMesReferenciaPagamento) then " +
				//"		2 " +
				//"	else " +
				//"		1 " +
				//"	end as indicadorContasRecebida, " + // 15
				"	0 as indicadorContasRecebida, " + // 15
				"	9, " + // 16
				"	0, " + // 17
				"	0, " + // 18
				"	0, " + // 19
				"	0, " + // 20
				"	case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
				"		coalesce(paga.valorPagamento, 0) " +
				"	else " +
				"		0 " +
				"	end as valorNaoIdentificado, " + // 21
				"	paga.arrecadacaoForma.id, " + // 22
				"	aviso.arrecadador.id, " + // 23
				"	0, " + // 24
				"	paga.anoMesReferenciaPagamento, " + // 25
				"	paga.dataPagamento, " + // 26
				"	'' " + // 27
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	left join paga.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	inner join paga.localidade locaPaga " +
				"	left join paga.avisoBancario as aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade " +
				"	and paga.contaGeral.id is null " +
				"	and paga.debitoACobrarGeral.id is null " +
				"	and paga.guiaPagamento.id is null"; 
			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - CONTA]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosConta(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	imov.id, " + // 0
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.gerenciaRegional.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.gerenciaRegional.id " +
				"		end " +
				"	end, " + // 1
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.unidadeNegocio.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.unidadeNegocio.id " +
				"		end " +
				"	end, " + // 2
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.localidade.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.localidade.id " +
				"		end " +
				"	end, " + // 3
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.id " +
				"		end " +
				"	end, " + // 4
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.setorComercial.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 5
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.rota.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 6
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 7
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		5 " +
				"	else " +
				"		imov.imovelPerfil.id " +
				"	end, " + // 8
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.ligacaoAguaSituacao.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 9
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.ligacaoEsgotoSituacao.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 10
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"			liga.ligacaoAguaPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 11
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"			lige.ligacaoEsgotoPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				"	0 as indicadorContasRecebida, " + // 15
				"	case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +
				"		9 " +
				"	else " +
				"		case when (paga.contaGeral.id is not null) then " +
				"			case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
				"				0 " +
				"			else " +
				/*"				case when ( (paga.dataPagamento > paga.contaGeral.conta.dataVencimentoConta) and " +
				"					      ( ( year(paga.dataPagamento) || month(paga.dataPagamento) ) = " +
				"						  ( year(paga.contaGeral.conta.dataVencimentoConta) || month(paga.contaGeral.conta.dataVencimentoConta) ) ) ) then " +
				"					1 " +*/
				"				case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
				"						    ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
				" 					      ) then " +
				"					1 " +
				
				"				else " +
				"					case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
				"						99 " +
				"					end " +
				"				end " +
				"			end " +
				"		end " +
				"	end, " + // 16
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.codigoSetorComercial " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 17
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.quadra " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 18
				"	paga.arrecadacaoForma.id, " + // 19
				"	aviso.arrecadador.id, " + // 20
				"	paga.anoMesReferenciaPagamento, " + // 21
				"	paga.dataPagamento, " + // 22
				"	conta.dataVencimentoConta, " + // 23
				"	'', " + // 24
				"	case when (paga.contaGeral.id is not null) then " +
				"		case when (conta.debitos is not null) then " +
				"			debitoCobrado.financiamentoTipo.id " +
				"		end " +
				"	end, " + // 25
				"	case when (paga.contaGeral.id is not null) then " +
				"		debitoCobrado.lancamentoItemContabil.id " +
				"	end, " + // 26
				"	case when (paga.contaGeral.id is not null) then " +
				"		case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"			coalesce(debitoCobrado.valorPrestacao, 0) " +
				"		else " +
				"			0 " +
				"		end " +
				"	else " +
				"		0 " +
				"	end, " + // 27
				"	0, " + // 28
				"	paga.id " + // 29
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	inner join paga.contaGeral contaGeral " +
				"	inner join contaGeral.conta conta " +
				"	inner join conta.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	inner join conta.localidade locaConta " +
				"	inner join paga.localidade locaPaga " +
				"	inner join conta.quadraConta qdra " +
				"	left join conta.debitoCobrados as debitoCobrado " +
				"	left join paga.avisoBancario as aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade " +
				"order by " +
				"	paga.id";
			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade)
				.list();
			
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - GUIA DE PAGAMENTO]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamento(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = "select imovelPagamento.imov_id as idImovel, " + //0
				"case when gpag.gpag_id is not null then " + 
				"	locaGuia.greg_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.greg_id " +
				"	end " +
				"end as idGerenciaRegional, " + //1
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.uneg_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.uneg_id " +
				"	end " +
				"end as idUnidadeOrganizacional, " + //2
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.loca_cdelo " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.loca_cdelo " +
				"	end " +
				"end as codigoElo, " + //3
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.loca_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.loca_id " +
				"	end " +
				"end as idLocalidade, " + //4
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		quadraImovel.stcm_id " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as idSetorComercial, " + //5
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		quadraImovel.rota_id " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as idRota, " + //6
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		quadraImovel.qdra_id " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as idQuadra, " + //7
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	5 " +
				"else imovelPagamento.iper_id  end as idImovelPerfil, " + //8
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	1 " +
				"else " +
				"	case when (gpag.gpag_id is not null) and (pgmt.imov_id is not null) then " +
				"		imovelPagamento.last_id " +
				"	end " +
				"end as idLigacaoAguaSituacao, " + //9
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	1 " +
				"else " +
				"	case when (gpag.gpag_id is not null) and (pgmt.imov_id is not null) then " +
				"		imovelPagamento.lest_id " +
				"	end " +
				"end as idLigacaoEsgotoSituacao, " + //10
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	0 " +
				"else " +
				"	case when ligacaoAgua.lapf_id is not null then " +
				"		ligacaoAgua.lapf_id " +
				"	else 0 end " +
				"end as idLigacaoAguaPerfil, " + //11
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	0 " +
				"else " +
				"	case when ligacaoEsgoto.lepf_id is not null then " +
				"		ligacaoEsgoto.lepf_id " +
				"	else 0 end " +
				"end as idLigacaoEsgotoPerfil, " + //12
				"pgmt.dotp_id as idDocumentoTipo, pgmt.pgst_idatual as idPagamentoSituacaoAtual, 0 as indicadorContasRecebida, " + //13, 14, 15
				"case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	9 " +
				"else " +
				"	case when gpag.gpag_id is not null then " +
				"		case when pgmt.pgmt_dtpagamento<=gpag.gpag_dtvencimento then " +
				"			0 " +
				"		else " +
				"			case when pgmt.pgmt_dtpagamento>gpag.gpag_dtvencimento " +
				"			and to_char(pgmt.pgmt_dtpagamento, 'YYYYMM')=to_char(gpag.gpag_dtvencimento, 'YYYYMM') then " +
				"				1 " +
				"			else " +
				"				case when pgmt.pgmt_dtpagamento>gpag.gpag_dtvencimento then " +
				"					98 " +
				"				end " +
				"			end " +
				"		end " +
				"	end " +
				"end as epocaPagamento, " + //16
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		setorImovel.stcm_cdsetorcomercial " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as codigoSetorComercial, " + //17
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		quadraImovel.qdra_nnquadra " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as numeroQuadra, " + //18
				"pgmt.arfm_id as idArrecadacaoForma, aviso.arrc_id as idArrecadador, pgmt.pgmt_amreferenciapagamento as anoMesReferenciaPagamento, " + //19, 20, 21
				"pgmt.pgmt_dtpagamento as dataPagamento, '' as branco, gpag.gpag_dtvencimento as dataVencimentoGuia, " + //22, 23, 24
				"case when gpag.gpag_id is not null then " +
				"	gpag.fntp_id " +
				"end as idFinanciamentoTipo, " + //25
				"case when gpag.gpag_id is not null then " +
				"	gpag.lict_id " +
				"end as idLancamentoItemContabil, " + //26
				"case when gpag.gpag_id is not null then " +
				"	case when pgmt.pgst_idatual=0 then " +
				"		coalesce(pgmt.pgmt_vlpagamento, 0) " +
				"	else 0 end " +
				"else 0 end as vlPagamentoClass, " + //27
				"case when pgmt.pgst_idatual<>0 then " +
				"	coalesce(pgmt.pgmt_vlpagamento, 0) " +
				"else 0 end as vlPagamentoNaoClass, " + //28
				"pgmt.pgmt_id as idPagamento " + //29
				"from arrecadacao.pagamento pgmt " +
				"inner join faturamento.guia_pagamento_geral gpge on pgmt.gpag_id = gpge.gpag_id " +
				"inner join cadastro.localidade locaPagamento on pgmt.loca_id = locaPagamento.loca_id " +
				"left outer join faturamento.guia_pagamento gpag on gpge.gpag_id = gpag.gpag_id " +
				"left outer join cadastro.localidade locaGuia on gpag.loca_id = locaGuia.loca_id " +
				"left outer join cadastro.imovel imovelPagamento on pgmt.imov_id = imovelPagamento.imov_id " +
				"left outer join cadastro.setor_comercial setorImovel on imovelPagamento.stcm_id = setorImovel.stcm_id " +
				"left outer join atendimentopublico.ligacao_agua ligacaoAgua on imovelPagamento.imov_id = ligacaoAgua.lagu_id " +
				"left outer join atendimentopublico.ligacao_esgoto ligacaoEsgoto on imovelPagamento.imov_id = ligacaoEsgoto.lesg_id " +
				"left outer join cadastro.quadra quadraImovel on imovelPagamento.qdra_id = quadraImovel.qdra_id " +
				"left outer join arrecadacao.aviso_bancario aviso on pgmt.avbc_id = aviso.avbc_id " +
				"where pgmt.pgmt_amreferenciaarrecadacao = :anoMesReferenciaArrecadacao and locaPagamento.loca_id = :idLocalidade " +
				"order by pgmt.pgmt_id";
				
				retorno = session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("idGerenciaRegional", Hibernate.INTEGER)
						.addScalar("idUnidadeOrganizacional", Hibernate.INTEGER)
						.addScalar("codigoElo", Hibernate.INTEGER)
						.addScalar("idLocalidade", Hibernate.INTEGER)
						.addScalar("idSetorComercial", Hibernate.INTEGER)
						.addScalar("idRota", Hibernate.INTEGER)
						.addScalar("idQuadra", Hibernate.INTEGER)
						.addScalar("idImovelPerfil", Hibernate.INTEGER)
						.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
						.addScalar("idLigacaoEsgotoSituacao", Hibernate.INTEGER)
						.addScalar("idLigacaoAguaPerfil", Hibernate.INTEGER)
						.addScalar("idLigacaoEsgotoPerfil", Hibernate.INTEGER)
						.addScalar("idDocumentoTipo", Hibernate.INTEGER)
						.addScalar("idPagamentoSituacaoAtual", Hibernate.INTEGER)
						.addScalar("indicadorContasRecebida", Hibernate.INTEGER)
						.addScalar("epocaPagamento", Hibernate.INTEGER)
						.addScalar("codigoSetorComercial", Hibernate.INTEGER)
						.addScalar("numeroQuadra", Hibernate.INTEGER)
						.addScalar("idArrecadacaoForma", Hibernate.INTEGER)
						.addScalar("idArrecadador", Hibernate.INTEGER)
						.addScalar("anoMesReferenciaPagamento", Hibernate.INTEGER)
						.addScalar("dataPagamento", Hibernate.DATE)
						.addScalar("branco", Hibernate.STRING)
						.addScalar("dataVencimentoGuia", Hibernate.DATE)
						.addScalar("idFinanciamentoTipo", Hibernate.INTEGER)
						.addScalar("idLancamentoItemContabil", Hibernate.INTEGER)
						.addScalar("vlPagamentoClass", Hibernate.BIG_DECIMAL)
						.addScalar("vlPagamentoNaoClass", Hibernate.BIG_DECIMAL)
						.addScalar("idPagamento", Hibernate.INTEGER)
						.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
						.setInteger("idLocalidade", idLocalidade).list();
				
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0553 - Gerar Resumo da Arrecadacao - Outros - DEBITO A COBRAR]
	 * 
	 * @author Ivan Sérgio
	 * @date 20/05/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrar(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql =
				"select " +
				" 	case when (debito.id is not null) then " +
				"		debito.imovel.id " +
				"	else " +
				"		paga.imovel.id " +
				"	end, " + // 0
				"	case when (debito.id is not null) then " +
				"		locaDebi.gerenciaRegional.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			locaPaga.gerenciaRegional.id " +
				"		end " +
				"	end, " + // 1
				"	case when (debito.id is not null) then " +
				"		locaDebi.unidadeNegocio.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			locaPaga.unidadeNegocio.id " +
				"		end " +
				"	end, " + // 2
				"	case when (debito.id is not null) then " +
				"		locaDebi.localidade.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			locaPaga.localidade.id " +
				"		end " +
				"	end, " + // 3
				"	case when (debito.id is not null) then " +
				"		locaDebi.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			locaPaga.id " +
				"		end " +
				"	end, " + // 4
				"	case when (debito.id is not null) then " +
				"		qdra.setorComercial.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 5
				"	case when (debito.id is not null) then " +
				"		qdra.rota.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 6
				"	case when (debito.id is not null) then " +
				"		qdra.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 7
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"		5 " +
				"	else " +
				"		imov.imovelPerfil.id " +
				"	end, " + // 8
				"	case when (debito.id is not null) then " +
				"		imov.ligacaoAguaSituacao.id " +
				"	end, " + // 9
				"	case when (debito.id is not null) then " +
				"		imov.ligacaoEsgotoSituacao.id " +
				"	end, " + // 10
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"			liga.ligacaoAguaPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 11
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"			lige.ligacaoEsgotoPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				"	0 as indicadorContasRecebida, " + // 15
				"	case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (debito.id is null) ) then " +
				"		9 " +
				"	else " +
				"		case when (debito.id is not null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 16
				"	case when (debito.id is not null) then " +
				"		debito.codigoSetorComercial " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 17
				"	case when (debito.id is not null) then " +
				"		debito.numeroQuadra " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 18
				"	paga.arrecadacaoForma.id, " + // 19
				"	aviso.arrecadador.id, " + // 20
				"	paga.anoMesReferenciaPagamento, " + // 21
				"	paga.dataPagamento, " + // 22
				"	'', " + // 23
				"	'', " + // 24
				"	case when (debito.id is not null) then " +
				"		debito.financiamentoTipo.id " +
				"	end, " + // 25
				"	case when (debito.id is not null) then " +
				"		debito.lancamentoItemContabil.id " +
				"	end, " + // 26
				"	case when (debito.id is not null) then " +
				"		case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"			coalesce(paga.valorPagamento, 0) " +
				"		else " +
				"			0 " +
				"		end " +
				"	else " +
				"		0 " +
				"	end, " + // 27
				"	case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
				"		coalesce(paga.valorPagamento, 0) " +
				"	else " +
				"		0 " +
				"	end as valorNaoIdentificado, " + // 28
				"	paga.id " + // 29
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	inner join paga.debitoACobrarGeral debitoGeral " +
				"	inner join paga.localidade locaPaga " +
				"	left join debitoGeral.debitoACobrar debito " +
				"	left join paga.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	left join debito.localidade locaDebi " +
				"	left join debito.quadra qdra " +
				"	left join paga.avisoBancario as aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade " +
				"order by " +
				"	paga.id"; 
								
				retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade)
				.list(); 
				
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * @author Ivan Sérgio
	 * @date 22/05/2008
	 * 		 08/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 * 					  O indicador sera obtido a partir do controlador.
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditos(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql =
				"select " +
				"	imov.id, " + // 0
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.gerenciaRegional.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.gerenciaRegional.id " +
				"		end " +
				"	end, " + // 1
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.unidadeNegocio.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.unidadeNegocio.id " +
				"		end " +
				"	end, " + // 2
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.localidade.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.localidade.id " +
				"		end " +
				"	end, " + // 3
				"	case when (paga.contaGeral.id is not null) then " +
				"		locaConta.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.id " +
				"		end " +
				"	end, " + // 4
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.setorComercial.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 5
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.rota.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 6
				"	case when (paga.contaGeral.id is not null) then " +
				"		qdra.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 7
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		5 " +
				"	else " +
				"		imov.imovelPerfil.id " +
				"	end, " + // 8
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.ligacaoAguaSituacao.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 9
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.ligacaoEsgotoSituacao.id " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 10
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"			liga.ligacaoAguaPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 11
				"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"			lige.ligacaoEsgotoPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				"	0 as indicadorContasRecebida, " + // 15
				"	case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +
				"		9 " +
				"	else " +
				"		case when (paga.contaGeral.id is not null) then " +
				"			case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
				"				0 " +
				"			else " +
				/*"				case when ( (paga.dataPagamento > paga.contaGeral.conta.dataVencimentoConta) and " +

				"					      ( ( year(paga.dataPagamento) || month(paga.dataPagamento) ) = " +
				"						  ( year(paga.contaGeral.conta.dataVencimentoConta) || month(paga.contaGeral.conta.dataVencimentoConta) ) ) ) then " +
				"					1 " +*/
				"				case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
				"						    ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
				" 					      ) then " +
				"					1 " +
				
				"				else " +
				"					case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
				"						99 " +
				"					end " +
				"				end " +
				"			end " +
				"		end " +
				"	end, " + // 16
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.codigoSetorComercial " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 17
				"	case when (paga.contaGeral.id is not null) then " +
				"		conta.quadra " +
				"	else " +
				"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 18
				"	paga.arrecadacaoForma.id, " + // 19
				"	aviso.arrecadador.id, " + // 20
				"	paga.anoMesReferenciaPagamento, " + // 21
				"	paga.dataPagamento, " + // 22
				"	conta.dataVencimentoConta, " + // 23
				"	'', " + // 24
				"	creditoRealizado.creditoOrigem.id, " + // 25
				"	creditoRealizado.lancamentoItemContabil.id, " + // 26
				"	case when (paga.contaGeral.id is not null) then " +
				"		case when (paga.pagamentoSituacaoAtual.id = 0) then " +
				"			coalesce(creditoRealizado.valorCredito, 0) " +
				"		else " +
				"			0 " +
				"		end " +
				"	else " +
				"		0 " +
				"	end " + // 27
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	inner join paga.contaGeral contaGeral " +
				"	inner join contaGeral.conta conta " +
				"	inner join conta.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	inner join conta.localidade locaConta " +
				"	inner join paga.localidade locaPaga " +
				"	inner join conta.quadraConta qdra " +
				"	left join paga.avisoBancario as aviso" +
				" 	inner join conta.creditoRealizados creditoRealizado	" +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade"; 
 			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
			
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}// getImoveisResumoArrecadacaoCreditos
	
	
	

	
	/**
	 * Seleciona o maior mês/ano de referência da tabela un_resumo_arrecadacao
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoArrecadacao()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resReArrec.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.arrecadacao.UnResumoArrecadacao resReArrec ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada  
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
	 * 
 	 * [UC0533] - Gerar Resumo da Arrecadacao
	 * 
	 * @author Ivan Sérgio
	 * @date 12/06/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarDadosPagamentoSemContaGuiaDebito(Integer idLocalidade)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Object retorno = null;
		
		try {
			String hql =
				"select " +
				"	setor.id, " + // 0
				"	setor.codigo, " + // 1
				"	quadra.rota.id, " + // 2
				"	quadra.id, " + // 3
				"	quadra.numeroQuadra " + // 4
				"from " +
				"	gcom.cadastro.localidade.Quadra quadra " +
				"	inner join quadra.setorComercial setor " +
				"where " +
				"	setor.localidade.id = :idLocalidade " +
				"	and setor.indicadorUso = :indicadorUso " +
				"	and quadra.indicadorUso = :indicadorUso " +
				"	and quadra.rota.indicadorUso = :indicadorUso " +
				"order by " +
				"	setor.id";

			retorno = session.createQuery(hql)
				.setInteger("idLocalidade", idLocalidade)
				.setShort("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
				.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada  
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/***
	 * [UC0533] - Gerar Resumo da Arrecadacao - Devolucao
	 * 
	 * @author Ivan Sérgio
	 * @date 09/10/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucao(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException {
	
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	imov.id, " + // 0
				"	loca.gerenciaRegional.id, " + // 1
				"	loca.unidadeNegocio.id, " + // 2
				"	loca.localidade.id, " + // 3
				"	loca.id, " + // 4
				"	qdra.setorComercial.id, " + // 5
				"	qdra.rota.id, " + // 6
				"	qdra.id, " + // 7
				"	imov.imovelPerfil.id, " + // 8
				"	imov.ligacaoAguaSituacao.id, " + // 9
				"	imov.ligacaoEsgotoSituacao.id, " + // 10

				"	case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"		liga.ligacaoAguaPerfil.id " +
				"	else " +
				"		0 " +
				"	end, " + // 11

				"	case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"		lige.ligacaoEsgotoPerfil.id " +
				"	else " +
				"		0 " +
				"	end, " + // 12
				"	 " +
					DocumentoTipo.DEVOLUCAO_VALOR + ", " + // 13
				"	devo.dataDevolucao, " + // 14
				"	devo.anoMesReferenciaDevolucao, " + // 15
				"	0, " + // 16 Epoca de Pagamento
				"	setor.codigo, " + // 17
				"	qdra.numeroQuadra, " + // 18

				"	case when ( (devo.devolucaoSituacaoAtual.id = :situacaoDevolucaoClassificada) or " +
				"				(devo.devolucaoSituacaoAtual.id = :situacaoDevolucaoOutrosValores) ) then " +
				"		coalesce(devo.valorDevolucao, 0) " +
				"	else " +
				"		0 " +
				"	end, " + // 19

				"	case when ( (devo.devolucaoSituacaoAtual.id != :situacaoDevolucaoClassificada) and " +
				"				(devo.devolucaoSituacaoAtual.id != :situacaoDevolucaoOutrosValores) ) then " +
				"		coalesce(devo.valorDevolucao, 0) " +
				"	else " +
				"		0 " +
				"	end, " + // 20

				"	devo.devolucaoSituacaoAtual.id " + // 21
				"from " +
				"	gcom.arrecadacao.Devolucao devo " +
				"	inner join devo.imovel imov " +
				"	inner join devo.localidade loca " +
				"	inner join imov.quadra qdra " +
				"	inner join imov.setorComercial setor " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	left join devo.avisoBancario as aviso " +
				"where " +
				"	devo.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and loca.id = :idLocalidade"; 
 						
			retorno = session.createQuery(hql)
				.setInteger("situacaoDevolucaoClassificada", DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)
				.setInteger("situacaoDevolucaoOutrosValores", DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
     * Gerar Resumo da Arrecadacao Por Ano - Aguá/Esgoto
     *
     * @author Fernando Fontelles
     * @date 04/06/2010
     *
     * @param     idLocalidade
     *              anoMesReferenciaPagamento
     * @return     list
     * @throws ErroRepositorioException
     */
    public List getImoveisResumoArrecadacaoAguaEsgotoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
        throws ErroRepositorioException {
   
        List retorno = null;
       
        Session session = HibernateUtil.getSession();
       
        try {
				
            String hql = 
				"select " +
				" 	case when (conta.id is not null) then " +
				"		conta.imovel.id " +
				"	else " +
				"		paga.imovel.id " +
				"	end, " + // 0
				" 	case when (conta.id is not null) then " +
				"		locaConta.gerenciaRegional.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.gerenciaRegional.id " +
				"		end " +
				"	end, " + // 1
				"	case when (conta.id is not null) then " +
				"		locaConta.unidadeNegocio.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.unidadeNegocio.id " +
				"		end " +
				"	end, " + // 2
				"	case when (conta.id is not null) then " +
				"		locaConta.localidade.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.localidade.id " +
				"		end " +
				"	end, " + // 3
				"	case when (conta.id is not null) then " +
				"		locaConta.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			locaPaga.id " +
				"		end " +
				"	end, " + // 4
				"	case when (conta.id is not null) then " +
				"		qdra.setorComercial.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 5
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		5 " +
				"	else " +
				"		imov.imovelPerfil.id " +
				"	end, " + // 6
				"	case when (conta.id is not null) then " +
				"		conta.ligacaoAguaSituacao.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 7
				"	case when (conta.id is not null) then " +
				"		conta.ligacaoEsgotoSituacao.id " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			1 " +
				"		end " +
				"	end, " + // 8
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
				"			liga.ligacaoAguaPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 9
				"	case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"		0 " +
				"	else " +
				"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
				"			lige.ligacaoEsgotoPerfil.id " +
				"		else " +
				"			0 " +
				"		end " +
				"	end, " + // 10
				"	paga.documentoTipo.id, " + // 11
				"	paga.pagamentoSituacaoAtual.id, " + // 12
				"	0 as indicadorContasRecebida, " + // 13
				"	case when ( (conta.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +

				"		9 " +
				"	else " +
				"		case when (conta.id is not null) then " +
				"			case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
				"				0 " +
				"			else " +
				/*"				case when ( (paga.dataPagamento > paga.contaGeral.conta.dataVencimentoConta) and " +
				"					      ( ( year(paga.dataPagamento) || month(paga.dataPagamento) ) = " +
				"						  ( year(paga.contaGeral.conta.dataVencimentoConta) || month(paga.contaGeral.conta.dataVencimentoConta) ) ) ) then " +
				"					1 " +*/
				"				case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
				"						    ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
				" 					      ) then " +
				"					1 " +
				"				else " +
				"					case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
				"						99 " +
				"					end " +
				"				end " +
				"			end " +
				"		end " +
				
				"	end, " + // 14
				"	case when (conta.id is not null) then " +
				"		conta.codigoSetorComercial " +
				"	else " +
				"		case when (conta.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
				"			0 " +
				"		end " +
				"	end, " + // 15
				"    case when (paga.pagamentoSituacaoAtual.id = 0) then " +
                "        coalesce(conta.valorAgua, 0.00) " +
                "    else " +
                "        0.00 " +
                "    end, " + // 16
                "    case when (paga.pagamentoSituacaoAtual.id = 0) then " +
                "        coalesce(conta.valorEsgoto, 0.00) " +
                "    else " +
                "        0.00 " +
                "    end, " + // 17
                "    case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
                "        coalesce(paga.valorPagamento, 0.00) " +
                "    else " +
                "        0.00 " +
                "    end as valorNaoIdentificado, " + // 18
                "    paga.arrecadacaoForma.id, " + // 19
                "    aviso.arrecadador.id, " + // 20
                "    case when (paga.pagamentoSituacaoAtual.id = 0) then " +
                "        coalesce(conta.valorImposto, 0.00) " +
                "    else " +
                "        0.00 " +
                "    end, " + // 21
                "    paga.anoMesReferenciaPagamento, " + // 22
                "    paga.dataPagamento, " + // 23
                " 	case when (conta.id is not null) then " +
				"		conta.dataVencimentoConta " +
				"	else " +
				"		null " +
				"	end " + // 24
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	inner join paga.contaGeral contaGeral " +
				"	inner join paga.localidade locaPaga " +
				"	left join contaGeral.conta conta " +
				"	left join conta.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	left join conta.localidade locaConta " +
				"	left join conta.quadraConta qdra " +
				"	left join paga.avisoBancario aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade";
           
            retorno = session.createQuery(hql)
                .setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
                .setInteger("idLocalidade", idLocalidade).list();
        }catch (HibernateException e) {           
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {           
            HibernateUtil.closeSession(session);
        }
       
        return retorno;
    }
	
    /**
     * Caso em que o Pagamento nao possui Conta, Guia de Pagamento e Debio a Cobrar
     *
      * Gerar Resumo da Arrecadacao Por Ano
     *
     * @author Fernando Fontelles
     * @date 04/06/2010
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Object pesquisarDadosPagamentoSemContaGuiaDebitoPorAno(Integer idLocalidade)
            throws ErroRepositorioException {
       
        Session session = HibernateUtil.getSession();
        Object retorno = null;
       
        try {
            String hql =
                "select " +
                "    setor.id, " + // 0
                "    setor.codigo " + // 1
//                "    quadra.rota.id, " + // 2
//                "    quadra.id, " + // 3
//                "    quadra.numeroQuadra " + // 4
                "from " +
                " gcom.cadastro.localidade.SetorComercial setor " +
//                "    gcom.cadastro.localidade.Quadra quadra " +
//                "    inner join quadra.setorComercial setor " +
                "where " +
                "    setor.localidade.id = :idLocalidade " +
                "    and setor.indicadorUso = :indicadorUso " +
//                "    and quadra.indicadorUso = :indicadorUso " +
//                "    and quadra.rota.indicadorUso = :indicadorUso " +
                "order by " +
                "    setor.id";

            retorno = session.createQuery(hql)
                .setInteger("idLocalidade", idLocalidade)
                .setShort("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
                .setMaxResults(1).uniqueResult();

        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada 
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }
       
        return retorno;
       
    }
	
    /**
	 * Gerar Resumo da Arrecadacao Por Ano - Aguá/Esgoto - Valor Nao Identificado
	 * 
	 * @author Fernando Fontelles
	 * @date 05/06/2010
	 * 
	 * @param 	idLocalidade
	 *		  	anoMesReferenciaPagamento
	 * @return 	list
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificadoPorAno(
			int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {
	
		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	imov.id, " + // 0
				"	locaPaga.gerenciaRegional.id, " + // 1
				"	locaPaga.unidadeNegocio.id, " + // 2
				"	locaPaga.localidade.id, " + // 3
				"	locaPaga.id, " + // 4
				"	0, " + // 5
//				"	0, " + // 6
//				"	0, " + // 7
				"	5, " + // 8
				"	1, " + // 9
				"	1, " + // 10
				"	0, " + // 11
				"	0, " + // 12
				"	paga.documentoTipo.id, " + // 13
				"	paga.pagamentoSituacaoAtual.id, " + // 14
				"	0 as indicadorContasRecebida, " + // 15
				"	9, " + // 16
				"	0, " + // 17
//				"	0, " + // 18
				"	0, " + // 19
				"	0, " + // 20
				"	case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
				"		coalesce(paga.valorPagamento, 0) " +
				"	else " +
				"		0 " +
				"	end as valorNaoIdentificado, " + // 21
				"	paga.arrecadacaoForma.id, " + // 22
				"	aviso.arrecadador.id, " + // 23
				"	0, " + // 24
				"	paga.anoMesReferenciaPagamento, " + // 25
				"	paga.dataPagamento, " + // 26
				"	'' " + // 27
				"from " +
				"	gcom.arrecadacao.pagamento.Pagamento paga " +
				"	left join paga.imovel imov " +
				"	left join imov.ligacaoAgua liga " +
				"	left join imov.ligacaoEsgoto lige " +
				"	inner join paga.localidade locaPaga " +
				"	left join paga.avisoBancario as aviso " +
				"where " +
				"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
				"	and locaPaga.id = :idLocalidade " +
				"	and paga.contaGeral.id is null " +
				"	and paga.debitoACobrarGeral.id is null " +
				"	and paga.guiaPagamento.id is null"; 
			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
		}catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {			
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
    
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - CONTA
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosContaPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
    throws ErroRepositorioException {

	    List retorno = null;
	   
	    Session session = HibernateUtil.getSession();
	   
	    try {
	        String hql =
	            "select " +
	            "    imov.id, " + // 0
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        locaConta.gerenciaRegional.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            locaPaga.gerenciaRegional.id " +
	            "        end " +
	            "    end, " + // 1
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        locaConta.unidadeNegocio.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            locaPaga.unidadeNegocio.id " +
	            "        end " +
	            "    end, " + // 2
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        locaConta.localidade.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            locaPaga.localidade.id " +
	            "        end " +
	            "    end, " + // 3
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        locaConta.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            locaPaga.id " +
	            "        end " +
	            "    end, " + // 4
	            "    case when (paga.contaGeral.id is not null) then " +
//	            "        qdra.setorComercial.id " +
	            "        imov.setorComercial.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            0 " +
	            "        end " +
	            "    end, " + // 5
//	            "    case when (paga.contaGeral.id is not null) then " +
//	            "        qdra.rota.id " +
//	            "    else " +
//	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//	            "            0 " +
//	            "        end " +
//	            "    end, " + // 6
//	            "    case when (paga.contaGeral.id is not null) then " +
//	            "        qdra.id " +
//	            "    else " +
//	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//	            "            0 " +
//	            "        end " +
//	            "    end, " + // 7
	            "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "        5 " +
	            "    else " +
	            "        imov.imovelPerfil.id " +
	            "    end, " + // 6
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        conta.ligacaoAguaSituacao.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            1 " +
	            "        end " +
	            "    end, " + // 7
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        conta.ligacaoEsgotoSituacao.id " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            1 " +
	            "        end " +
	            "    end, " + // 8
	            "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "        0 " +
	            "    else " +
	            "        case when (liga.ligacaoAguaPerfil.id is not null) then " +
	            "            liga.ligacaoAguaPerfil.id " +
	            "        else " +
	            "            0 " +
	            "        end " +
	            "    end, " + // 9
	            "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "        0 " +
	            "    else " +
	            "        case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
	            "            lige.ligacaoEsgotoPerfil.id " +
	            "        else " +
	            "            0 " +
	            "        end " +
	            "    end, " + // 10
	            "    paga.documentoTipo.id, " + // 11
	            "    paga.pagamentoSituacaoAtual.id, " + // 12
	            "    0 as indicadorContasRecebida, " + // 13
	            "    case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +
	            "        9 " +
	            "    else " +
	            "        case when (paga.contaGeral.id is not null) then " +
	            "            case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
	            "                0 " +
	            "            else " +
	            "                case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
	            "                            ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
	            "                           ) then " +
	            "                    1 " +
	           
	            "                else " +
	            "                    case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
	            "                        99 " +
	            "                    end " +
	            "                end " +
	            "            end " +
	            "        end " +
	            "    end, " + // 14
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        conta.codigoSetorComercial " +
	            "    else " +
	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
	            "            0 " +
	            "        end " +
	            "    end, " + // 15
//	            "    case when (paga.contaGeral.id is not null) then " +
//	            "        conta.quadra " +
//	            "    else " +
//	            "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//	            "            0 " +
//	            "        end " +
//	            "    end, " + // 18
	            "    paga.arrecadacaoForma.id, " + // 16
	            "    aviso.arrecadador.id, " + // 17
	            "    paga.anoMesReferenciaPagamento, " + // 18
	            "    paga.dataPagamento, " + // 19
	            "    conta.dataVencimentoConta, " + // 20
	            "    '', " + // 21
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        case when (conta.debitos is not null) then " +
	            "            debitoCobrado.financiamentoTipo.id " +
	            "        end " +
	            "    end, " + // 22
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        debitoCobrado.lancamentoItemContabil.id " +
	            "    end, " + // 23
	            "    case when (paga.contaGeral.id is not null) then " +
	            "        case when (paga.pagamentoSituacaoAtual.id = 0) then " +
	            "            coalesce(debitoCobrado.valorPrestacao, 0) " +
	            "        else " +
	            "            0 " +
	            "        end " +
	            "    else " +
	            "        0 " +
	            "    end, " + // 24
	            "    0, " + // 25
	            "    paga.id " + // 26
	            "from " +
	            "    gcom.arrecadacao.pagamento.Pagamento paga " +
	            "    inner join paga.contaGeral contaGeral " +
	            "    inner join contaGeral.conta conta " +
	            "    inner join conta.imovel imov " +
	            "    left join imov.ligacaoAgua liga " +
	            "    left join imov.ligacaoEsgoto lige " +
	            "    inner join conta.localidade locaConta " +
	            "    inner join paga.localidade locaPaga " +
//	            "    inner join conta.quadraConta qdra " +
	            "    left join conta.debitoCobrados as debitoCobrado " +
	            "    left join paga.avisoBancario as aviso " +
	            "where " +
	            "    paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
	            "    and locaPaga.id = :idLocalidade " +
	            "order by " +
	            "    paga.id";
	       
	        retorno = session.createQuery(hql)
	            .setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
	            .setInteger("idLocalidade", idLocalidade)
	            .list();
	       
	    }catch (HibernateException e) {           
	        throw new ErroRepositorioException(e, "Erro no Hibernate");
	    } finally {           
	        HibernateUtil.closeSession(session);
	    }
	   
	    return retorno;
	}
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - GUIA DE PAGAMENTO
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosGuiaPagamentoPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
    throws ErroRepositorioException {

	    List retorno = null;
	   
	    Session session = HibernateUtil.getSession();
	   
	    try {
	        
	        String consulta = "select imovelPagamento.imov_id as idImovel, " + //0
				"case when gpag.gpag_id is not null then " + 
				"	locaGuia.greg_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.greg_id " +
				"	end " +
				"end as idGerenciaRegional, " + //1
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.uneg_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.uneg_id " +
				"	end " +
				"end as idUnidadeOrganizacional, " + //2
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.loca_cdelo " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.loca_cdelo " +
				"	end " +
				"end as codigoElo, " + //3
				"case when gpag.gpag_id is not null then " +
				"	locaGuia.loca_id " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		locaPagamento.loca_id " +
				"	end " +
				"end as idLocalidade, " + //4
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		quadraImovel.stcm_id " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as idSetorComercial, " + //5
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	5 " +
				"else imovelPagamento.iper_id  end as idImovelPerfil, " + //6
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	1 " +
				"else " +
				"	case when (gpag.gpag_id is not null) and (pgmt.imov_id is not null) then " +
				"		imovelPagamento.last_id " +
				"	end " +
				"end as idLigacaoAguaSituacao, " + //7
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	1 " +
				"else " +
				"	case when (gpag.gpag_id is not null) and (pgmt.imov_id is not null) then " +
				"		imovelPagamento.lest_id " +
				"	end " +
				"end as idLigacaoEsgotoSituacao, " + //8
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	0 " +
				"else " +
				"	case when ligacaoAgua.lapf_id is not null then " +
				"		ligacaoAgua.lapf_id " +
				"	else 0 end " +
				"end as idLigacaoAguaPerfil, " + //9
				"case when (gpag.gpag_id is not null) and (pgmt.imov_id is null) or (pgmt.cnta_id is null) " +
				"and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	0 " +
				"else " +
				"	case when ligacaoEsgoto.lepf_id is not null then " +
				"		ligacaoEsgoto.lepf_id " +
				"	else 0 end " +
				"end as idLigacaoEsgotoPerfil, " + //10
				"pgmt.dotp_id as idDocumentoTipo, pgmt.pgst_idatual as idPagamentoSituacaoAtual, 0 as indicadorContasRecebida, " + //11, 12, 13
				"case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"	9 " +
				"else " +
				"	case when gpag.gpag_id is not null then " +
				"		case when pgmt.pgmt_dtpagamento<=gpag.gpag_dtvencimento then " +
				"			0 " +
				"		else " +
				"			case when pgmt.pgmt_dtpagamento>gpag.gpag_dtvencimento " +
				"			and to_char(pgmt.pgmt_dtpagamento, 'YYYYMM')=to_char(gpag.gpag_dtvencimento, 'YYYYMM') then " +
				"				1 " +
				"			else " +
				"				case when pgmt.pgmt_dtpagamento>gpag.gpag_dtvencimento then " +
				"					98 " +
				"				end " +
				"			end " +
				"		end " +
				"	end " +
				"end as epocaPagamento, " + //14
				"case when gpag.gpag_id is not null then " +
				"	case when gpag.imov_id is not null then " +
				"		setorImovel.stcm_cdsetorcomercial " +
				"	else 0 end " +
				"else " +
				"	case when (pgmt.cnta_id is null) and (gpag.gpag_id is null) and (pgmt.dbac_id is null) then " +
				"		0 " +
				"	end " +
				"end as codigoSetorComercial, " + //15
				"pgmt.arfm_id as idArrecadacaoForma, aviso.arrc_id as idArrecadador, pgmt.pgmt_amreferenciapagamento as anoMesReferenciaPagamento, " + //16, 17, 18
				"pgmt.pgmt_dtpagamento as dataPagamento, '' as branco, gpag.gpag_dtvencimento as dataVencimentoGuia, " + //19, 20, 21
				"case when gpag.gpag_id is not null then " +
				"	gpag.fntp_id " +
				"end as idFinanciamentoTipo, " + //22
				"case when gpag.gpag_id is not null then " +
				"	gpag.lict_id " +
				"end as idLancamentoItemContabil, " + //23
				"case when gpag.gpag_id is not null then " +
				"	case when pgmt.pgst_idatual=0 then " +
				"		coalesce(pgmt.pgmt_vlpagamento, 0) " +
				"	else 0 end " +
				"else 0 end as vlPagamentoClass, " + //24
				"case when pgmt.pgst_idatual<>0 then " +
				"	coalesce(pgmt.pgmt_vlpagamento, 0) " +
				"else 0 end as vlPagamentoNaoClass, " + //25
				"pgmt.pgmt_id as idPagamento " + //26
		        "from arrecadacao.pagamento pgmt " +
				"inner join faturamento.guia_pagamento_geral gpge on pgmt.gpag_id = gpge.gpag_id " +
				"inner join cadastro.localidade locaPagamento on pgmt.loca_id = locaPagamento.loca_id " +
				"left outer join faturamento.guia_pagamento gpag on gpge.gpag_id = gpag.gpag_id " +
				"left outer join cadastro.localidade locaGuia on gpag.loca_id = locaGuia.loca_id " +
				"left outer join cadastro.imovel imovelPagamento on pgmt.imov_id = imovelPagamento.imov_id " +
				"left outer join cadastro.setor_comercial setorImovel on imovelPagamento.stcm_id = setorImovel.stcm_id " +
				"left outer join atendimentopublico.ligacao_agua ligacaoAgua on imovelPagamento.imov_id = ligacaoAgua.lagu_id " +
				"left outer join atendimentopublico.ligacao_esgoto ligacaoEsgoto on imovelPagamento.imov_id = ligacaoEsgoto.lesg_id " +
				"left outer join cadastro.quadra quadraImovel on imovelPagamento.qdra_id = quadraImovel.qdra_id " +
				"left outer join arrecadacao.aviso_bancario aviso on pgmt.avbc_id = aviso.avbc_id " +
				"where pgmt.pgmt_amreferenciaarrecadacao = :anoMesReferenciaArrecadacao and locaPagamento.loca_id = :idLocalidade " +
				"order by pgmt.pgmt_id";
	       
		        retorno = session.createSQLQuery(consulta)
				.addScalar("idImovel", Hibernate.INTEGER)
				.addScalar("idGerenciaRegional", Hibernate.INTEGER)
				.addScalar("idUnidadeOrganizacional", Hibernate.INTEGER)
				.addScalar("codigoElo", Hibernate.INTEGER)
				.addScalar("idLocalidade", Hibernate.INTEGER)
				.addScalar("idSetorComercial", Hibernate.INTEGER)
				.addScalar("idImovelPerfil", Hibernate.INTEGER)
				.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
				.addScalar("idLigacaoEsgotoSituacao", Hibernate.INTEGER)
				.addScalar("idLigacaoAguaPerfil", Hibernate.INTEGER)
				.addScalar("idLigacaoEsgotoPerfil", Hibernate.INTEGER)
				.addScalar("idDocumentoTipo", Hibernate.INTEGER)
				.addScalar("idPagamentoSituacaoAtual", Hibernate.INTEGER)
				.addScalar("indicadorContasRecebida", Hibernate.INTEGER)
				.addScalar("epocaPagamento", Hibernate.INTEGER)
				.addScalar("codigoSetorComercial", Hibernate.INTEGER)
				.addScalar("idArrecadacaoForma", Hibernate.INTEGER)
				.addScalar("idArrecadador", Hibernate.INTEGER)
				.addScalar("anoMesReferenciaPagamento", Hibernate.INTEGER)
				.addScalar("dataPagamento", Hibernate.DATE)
				.addScalar("branco", Hibernate.STRING)
				.addScalar("dataVencimentoGuia", Hibernate.DATE)
				.addScalar("idFinanciamentoTipo", Hibernate.INTEGER)
				.addScalar("idLancamentoItemContabil", Hibernate.INTEGER)
				.addScalar("vlPagamentoClass", Hibernate.BIG_DECIMAL)
				.addScalar("vlPagamentoNaoClass", Hibernate.BIG_DECIMAL)
				.addScalar("idPagamento", Hibernate.INTEGER)
				.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
				.setInteger("idLocalidade", idLocalidade).list();
	           
	    }catch (HibernateException e) {           
	        throw new ErroRepositorioException(e, "Erro no Hibernate");
	    } finally {           
	        HibernateUtil.closeSession(session);
	    }
	   
	    return retorno;
	}
	
	/**
	 * Gerar Resumo da Arrecadacao Por Ano - Outros - DEBITO A COBRAR
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoOutrosDebitoACobrarPorAno(int idLocalidade, 
			int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

	        List retorno = null;
	       
	        Session session = HibernateUtil.getSession();
	       
	        try {
	            
	            String hql =
					"select " +
					" 	case when (debito.id is not null) then " +
					"		debito.imovel.id " +
					"	else " +
					"		paga.imovel.id " +
					"	end, " + // 0
					"	case when (debito.id is not null) then " +
					"		locaDebi.gerenciaRegional.id " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			locaPaga.gerenciaRegional.id " +
					"		end " +
					"	end, " + // 1
					"	case when (debito.id is not null) then " +
					"		locaDebi.unidadeNegocio.id " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			locaPaga.unidadeNegocio.id " +
					"		end " +
					"	end, " + // 2
					"	case when (debito.id is not null) then " +
					"		locaDebi.localidade.id " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			locaPaga.localidade.id " +
					"		end " +
					"	end, " + // 3
					"	case when (debito.id is not null) then " +
					"		locaDebi.id " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			locaPaga.id " +
					"		end " +
					"	end, " + // 4
					"	case when (debito.id is not null) then " +
					"		qdra.setorComercial.id " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			0 " +
					"		end " +
					"	end, " + // 5
					"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"		5 " +
					"	else " +
					"		imov.imovelPerfil.id " +
					"	end, " + // 6
					"	case when (debito.id is not null) then " +
					"		imov.ligacaoAguaSituacao.id " +
					"	end, " + // 7
					"	case when (debito.id is not null) then " +
					"		imov.ligacaoEsgotoSituacao.id " +
					"	end, " + // 8
					"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"		0 " +
					"	else " +
					"		case when (liga.ligacaoAguaPerfil.id is not null) then " +
					"			liga.ligacaoAguaPerfil.id " +
					"		else " +
					"			0 " +
					"		end " +
					"	end, " + // 9
					"	case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"		0 " +
					"	else " +
					"		case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
					"			lige.ligacaoEsgotoPerfil.id " +
					"		else " +
					"			0 " +
					"		end " +
					"	end, " + // 10
					"	paga.documentoTipo.id, " + // 11
					"	paga.pagamentoSituacaoAtual.id, " + // 12
					"	0 as indicadorContasRecebida, " + // 13
					"	case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (debito.id is null) ) then " +
					"		9 " +
					"	else " +
					"		case when (debito.id is not null) then " +
					"			0 " +
					"		end " +
					"	end, " + // 14
					"	case when (debito.id is not null) then " +
					"		debito.codigoSetorComercial " +
					"	else " +
					"		case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and debito.id is null) then " +
					"			0 " +
					"		end " +
					"	end, " + // 15
					"	paga.arrecadacaoForma.id, " + // 16
					"	aviso.arrecadador.id, " + // 17
					"	paga.anoMesReferenciaPagamento, " + // 18
					"	paga.dataPagamento, " + // 19
					"	'', " + // 20
					"	'', " + // 21
					"	case when (debito.id is not null) then " +
					"		debito.financiamentoTipo.id " +
					"	end, " + // 22
					"	case when (debito.id is not null) then " +
					"		debito.lancamentoItemContabil.id " +
					"	end, " + // 23
					"	case when (debito.id is not null) then " +
					"		case when (paga.pagamentoSituacaoAtual.id = 0) then " +
					"			coalesce(paga.valorPagamento, 0) " +
					"		else " +
					"			0 " +
					"		end " +
					"	else " +
					"		0 " +
					"	end, " + // 24
					"	case when (paga.pagamentoSituacaoAtual.id <> 0) then " +
					"		coalesce(paga.valorPagamento, 0) " +
					"	else " +
					"		0 " +
					"	end as valorNaoIdentificado, " + // 25
					"	paga.id " + // 26
		            "from " +
					"	gcom.arrecadacao.pagamento.Pagamento paga " +
					"	inner join paga.debitoACobrarGeral debitoGeral " +
					"	inner join paga.localidade locaPaga " +
					"	left join debitoGeral.debitoACobrar debito " +
					"	left join paga.imovel imov " +
					"	left join imov.ligacaoAgua liga " +
					"	left join imov.ligacaoEsgoto lige " +
					"	left join debito.localidade locaDebi " +
					"	left join debito.quadra qdra " +
					"	left join paga.avisoBancario as aviso " +
					"where " +
					"	paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
					"	and locaPaga.id = :idLocalidade " +
					"order by " +
					"	paga.id";
	                               
	                retorno = session.createQuery(hql)
	                .setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
	                .setInteger("idLocalidade", idLocalidade)
	                .list();
	               
	        }catch (HibernateException e) {           
	            throw new ErroRepositorioException(e, "Erro no Hibernate");
	        } finally {           
	            HibernateUtil.closeSession(session);
	        }
	       
	        return retorno;
	    }
	
	/**
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoCreditosPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
		throws ErroRepositorioException {

		List retorno = null;
	       
        Session session = HibernateUtil.getSession();
       
        try {
            String hql =
                "select " +
                "    imov.id, " + // 0
                "    case when (paga.contaGeral.id is not null) then " +
                "        locaConta.gerenciaRegional.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            locaPaga.gerenciaRegional.id " +
                "        end " +
                "    end, " + // 1
                "    case when (paga.contaGeral.id is not null) then " +
                "        locaConta.unidadeNegocio.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            locaPaga.unidadeNegocio.id " +
                "        end " +
                "    end, " + // 2
                "    case when (paga.contaGeral.id is not null) then " +
                "        locaConta.localidade.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            locaPaga.localidade.id " +
                "        end " +
                "    end, " + // 3
                "    case when (paga.contaGeral.id is not null) then " +
                "        locaConta.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            locaPaga.id " +
                "        end " +
                "    end, " + // 4
                "    case when (paga.contaGeral.id is not null) then " +
//                "        qdra.setorComercial.id " +
                "        imov.setorComercial.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            0 " +
                "        end " +
                "    end, " + // 5
//                "    case when (paga.contaGeral.id is not null) then " +
//                "        qdra.rota.id " +
//                "    else " +
//                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//                "            0 " +
//                "        end " +
//                "    end, " + // 6
//                "    case when (paga.contaGeral.id is not null) then " +
//                "        qdra.id " +
//                "    else " +
//                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//                "            0 " +
//                "        end " +
//                "    end, " + // 7
                "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "        5 " +
                "    else " +
                "        imov.imovelPerfil.id " +
                "    end, " + // 6
                "    case when (paga.contaGeral.id is not null) then " +
                "        conta.ligacaoAguaSituacao.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            1 " +
                "        end " +
                "    end, " + // 7
                "    case when (paga.contaGeral.id is not null) then " +
                "        conta.ligacaoEsgotoSituacao.id " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            1 " +
                "        end " +
                "    end, " + // 8
                "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "        0 " +
                "    else " +
                "        case when (liga.ligacaoAguaPerfil.id is not null) then " +
                "            liga.ligacaoAguaPerfil.id " +
                "        else " +
                "            0 " +
                "        end " +
                "    end, " + // 9
                "    case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "        0 " +
                "    else " +
                "        case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
                "            lige.ligacaoEsgotoPerfil.id " +
                "        else " +
                "            0 " +
                "        end " +
                "    end, " + // 10
                "    paga.documentoTipo.id, " + // 11
                "    paga.pagamentoSituacaoAtual.id, " + // 12
                "    0 as indicadorContasRecebida, " + // 13
                "    case when ( (paga.contaGeral.id is null) and (paga.guiaPagamento.id is null) and (paga.debitoACobrarGeral.id is null) ) then " +
                "        9 " +
                "    else " +
                "        case when (paga.contaGeral.id is not null) then " +
                "            case when (paga.dataPagamento <= conta.dataVencimentoConta) then " +
                "                0 " +
                "            else " +
                "                case when ( (paga.dataPagamento > conta.dataVencimentoConta) and " +
                "                            ( to_char(paga.dataPagamento,'YYYYMM') = to_char(conta.dataVencimentoConta,'YYYYMM') ) " +
                "                           ) then " +
                "                    1 " +
               
                "                else " +
                "                    case when ( (paga.dataPagamento > conta.dataVencimentoConta) ) then " +
                "                        99 " +
                "                    end " +
                "                end " +
                "            end " +
                "        end " +
                "    end, " + // 14
                "    case when (paga.contaGeral.id is not null) then " +
                "        conta.codigoSetorComercial " +
                "    else " +
                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
                "            0 " +
                "        end " +
                "    end, " + // 15
//                "    case when (paga.contaGeral.id is not null) then " +
//                "        conta.quadra " +
//                "    else " +
//                "        case when (paga.contaGeral.id is null and paga.guiaPagamento.id is null and paga.debitoACobrarGeral.id is null) then " +
//                "            0 " +
//                "        end " +
//                "    end, " + // 18
                "    paga.arrecadacaoForma.id, " + // 16
                "    aviso.arrecadador.id, " + // 17
                "    paga.anoMesReferenciaPagamento, " + // 18
                "    paga.dataPagamento, " + // 19
                "    conta.dataVencimentoConta, " + // 20
                "    '', " + // 21
                "    creditoRealizado.creditoOrigem.id, " + // 22
                "    creditoRealizado.lancamentoItemContabil.id, " + // 23
                "    case when (paga.contaGeral.id is not null) then " +
                "        case when (paga.pagamentoSituacaoAtual.id = 0) then " +
                "            coalesce(creditoRealizado.valorCredito, 0) " +
                "        else " +
                "            0 " +
                "        end " +
                "    else " +
                "        0 " +
                "    end " + // 24
                "from " +
                "    gcom.arrecadacao.pagamento.Pagamento paga " +
                "    inner join paga.contaGeral contaGeral " +
                "    inner join contaGeral.conta conta " +
                "    inner join conta.imovel imov " +
                "    left join imov.ligacaoAgua liga " +
                "    left join imov.ligacaoEsgoto lige " +
                "    inner join conta.localidade locaConta " +
                "    inner join paga.localidade locaPaga " +
//                "    inner join conta.quadraConta qdra " +
                "    left join paga.avisoBancario as aviso" +
                "     inner join conta.creditoRealizados creditoRealizado    " +
                "where " +
                "    paga.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
                "    and locaPaga.id = :idLocalidade";
            
            retorno = session.createQuery(hql)
                .setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
                .setInteger("idLocalidade", idLocalidade).list();
           
        }catch (HibernateException e) {           
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {           
            HibernateUtil.closeSession(session);
        }
       
        return retorno;
    }// getImoveisResumoArrecadacaoCreditos
	
	/***
	 * Gerar Resumo da Arrecadacao Por Ano - Devolucao
	 * 
	 * @author Fernando Fontelles
	 * @date 10/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoArrecadacaoDevolucaoPorAno(int idLocalidade, int anoMesReferenciaArrecadacao)
			throws ErroRepositorioException {
	
		List retorno = null;
        Session session = HibernateUtil.getSession();
       
        try {
            String hql =
                "select " +
                "    imov.id, " + // 0
                "    loca.gerenciaRegional.id, " + // 1
                "    loca.unidadeNegocio.id, " + // 2
                "    loca.localidade.id, " + // 3
                "    loca.id, " + // 4
//                "    qdra.setorComercial.id, " + // 5
                "    imov.setorComercial.id, " + // 5
//                "    qdra.rota.id, " + // 6
//                "    qdra.id, " + // 7
                "    imov.imovelPerfil.id, " + // 6
                "    imov.ligacaoAguaSituacao.id, " + // 7
                "    imov.ligacaoEsgotoSituacao.id, " + // 8
                "    case when (liga.ligacaoAguaPerfil.id is not null) then " +
                "        liga.ligacaoAguaPerfil.id " +
                "    else " +
                "        0 " +
                "    end, " + // 9

                "    case when (lige.ligacaoEsgotoPerfil.id is not null) then " +
                "        lige.ligacaoEsgotoPerfil.id " +
                "    else " +
                "        0 " +
                "    end, " + // 10
                "     " +
                    DocumentoTipo.DEVOLUCAO_VALOR + ", " + // 11
                "    devo.dataDevolucao, " + // 12
                "    devo.anoMesReferenciaDevolucao, " + // 13
                "    0, " + // 14 Epoca de Pagamento
                "    setor.codigo, " + // 15
//                "    qdra.numeroQuadra, " + // 18
                "    case when ( (devo.devolucaoSituacaoAtual.id = :situacaoDevolucaoClassificada) or " +
                "                (devo.devolucaoSituacaoAtual.id = :situacaoDevolucaoOutrosValores) ) then " +
                "        coalesce(devo.valorDevolucao, 0) " +
                "    else " +
                "        0 " +
                "    end, " + // 16

                "    case when ( (devo.devolucaoSituacaoAtual.id != :situacaoDevolucaoClassificada) and " +
                "                (devo.devolucaoSituacaoAtual.id != :situacaoDevolucaoOutrosValores) ) then " +
                "        coalesce(devo.valorDevolucao, 0) " +
                "    else " +
                "        0 " +
                "    end, " + // 17

                "    devo.devolucaoSituacaoAtual.id " + // 18
                "from " +
                "    gcom.arrecadacao.Devolucao devo " +
                "    inner join devo.imovel imov " +
                "    inner join devo.localidade loca " +
//                "    inner join imov.quadra qdra " +
                "    inner join imov.setorComercial setor " +
                "    left join imov.ligacaoAgua liga " +
                "    left join imov.ligacaoEsgoto lige " +
                "    left join devo.avisoBancario as aviso " +
                "where " +
                "    devo.anoMesReferenciaArrecadacao = :anoMesReferenciaArrecadacao " +
                "    and loca.id = :idLocalidade";
                        
            retorno = session.createQuery(hql)
                .setInteger("situacaoDevolucaoClassificada", DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)
                .setInteger("situacaoDevolucaoOutrosValores", DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)
                .setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao)
                .setInteger("idLocalidade", idLocalidade).list();
        }catch (HibernateException e) {           
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {           
            HibernateUtil.closeSession(session);
        }
       
        return retorno;
    }
	
}