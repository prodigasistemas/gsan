package gcom.gerencial.cadastro;


import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaAguaLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoEconomiaSemQuadraHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoHelper;
import gcom.gerencial.cadastro.bean.HistogramaEsgotoLigacaoSemQuadraHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoHelper;
import gcom.gerencial.cadastro.bean.ResumoColetaEsgotoPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaHelper;
import gcom.gerencial.cadastro.bean.ResumoConsumoAguaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoLigacaoEconomiaPorAnoHelper;
import gcom.gerencial.cadastro.bean.ResumoMetasHelper;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class  RepositorioGerencialCadastroHBM implements
		IRepositorioGerencialCadastro {

	private static IRepositorioGerencialCadastro instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioGerencialCadastroHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialCadastro getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialCadastroHBM();
		}

		return instancia;
	}

	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Thiago Toscano, Bruno Barrros, Ivan Sergio
	 * @date 19/04/2006, 16/04/2007, 11/08/2008, 09/01/2009
	 * @alteracao 11/08/2008 - Adicionado a situacao Ligado Em Analise na consulta.
	 * 			  09/01/2009 - CRC937 - Adicionado indicadorImovelCondominio a consulta.
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLigacaoEconomias(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
					+ "   imovel.id,"
					+ "   imovel.localidade.gerenciaRegional.id,"
					+ "   imovel.localidade.unidadeNegocio.id,"
					+ "   imovel.localidade.id,"
					+ "   imovel.localidade.localidade.id,"
					+ "   imovel.setorComercial.id,"
					+ "   imovel.quadra.rota.id,"
					+ "   imovel.quadra.id,"
					+ "   imovel.setorComercial.codigo,"
					+ "   imovel.quadra.numeroQuadra,"
					+ "   imovel.imovelPerfil.id,"
					+ "   imovel.ligacaoAguaSituacao.id,"
					+ "   imovel.ligacaoEsgotoSituacao.id,"
					+ "   case when ("
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then"
					+ "     0"
					+ "   else"
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id"
					+ "   end,"
					+ "   case when ("
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then"
					+ "     0"
					+ "   else"
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id"
					+ "   end,"
					+ "   case when ("
					+ "       ( imovel.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado, :ligacaoAguaSituacaoLigadoEmAnalise) and"
					+ "         imovel.ligacaoAgua.hidrometroInstalacaoHistorico is not null ) or"
					+ "       ( imovel.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and"
					+ "         imovel.hidrometroInstalacaoHistorico is not null ) ) then"
					+ "     1"
					+ "   else"
					+ "     2"
					+ "   end,"
					+ "   case when ("
					+ "     imovel.hidrometroInstalacaoHistorico is not null ) then"
					+ "     1"
					+ "   else"
					+ "     2"
					+ "   end,"
					+ "   case when ( ligacaoAgua.numeroConsumoMinimoAgua > 0 ) then"
					+ "     1" + "   else" + "     2" + "   end,"
					+ "   case when ( ligacaoEsgoto.consumoMinimo > 0 ) then"
					+ "     1" + "   else" + "     2" + "   end,"
					+ "   case when ( imovel.pocoTipo is not null and imovel.pocoTipo > 0 ) then"
					+ "     1" + "   else" + "     2" + "   end,"
					+ "   1 as qtdligacao,"
					+ "   imovel.quantidadeEconomias, "
					+ "   imovel.consumoTarifa.id, "
					+ "	  rota.codigo, "
					+ "	  imovel.indicadorImovelCondominio, "
					+ "   ligacaoAgua.dataLigacao, "
					+ "   ligacaoEsgoto.dataLigacao, "
					+ "   ligacaoAgua.dataCorte, "
					+ "   ligacaoAgua.dataReligacao "					
					+ "  from " + "   gcom.cadastro.imovel.Imovel as imovel"
					+ "   left join imovel.ligacaoAgua ligacaoAgua"
					+ "   left join imovel.ligacaoEsgoto ligacaoEsgoto "
					+ "   inner join imovel.quadra.rota rota "
					+ " where imovel.setorComercial.id = :idSetor and"
					+ "   imovel.indicadorExclusao = 2";

			retorno = session.createQuery(hql).setInteger(
					"ligacaoAguaSituacaoLigado", LigacaoAguaSituacao.LIGADO)
					.setInteger("ligacaoAguaSituacaoCortado",
							LigacaoAguaSituacao.CORTADO).setInteger(
							"ligacaoEsgotoSituacaoLigado",
							LigacaoEsgotoSituacao.LIGADO)
							.setInteger("ligacaoAguaSituacaoLigadoEmAnalise", LigacaoAguaSituacao.LIGADO_EM_ANALISE)
							.setInteger("idSetor", idSetor).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa os dados
	 * iniciais dos imoves selecionados do setor comercial, contendo os dados
	 * iniciais para o agrupamento Histograma de Agua e Esgoto
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @return Cole��o de contas
	 * @throws ErroRepositorioException
	 */
	public List getContasHistograma(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "select " 
					+ "  c.id, " // 0
					+ "  l.gerenciaRegional.id, " // 1
					+ "  l.unidadeNegocio.id, " // 2
					+ "  l.localidade.id, "// 3
					+ "  l.id, "// 4 
					+ "  q.setorComercial.id, "// 5
					+ "  c.codigoSetorComercial, "// 6 
					+ "  q.id, "// 7
					+ "  c.quadra, " // 8
					+ "  c.consumoTarifa.id, "// 9
					+ "  c.imovelPerfil.id, "// 10 
					+ "  c.ligacaoAguaSituacao.id, "// 11
					+ "  imo.id," // 12
					+ "  c.ligacaoEsgotoSituacao.id, "// 13
					+ "  c.percentualEsgoto "// 14
					+ "from "
					+ "  Conta c " 
					+ "  inner join c.quadraConta q "
					+ "  inner join c.localidade l "
					+ "  inner join c.imovel imo " + "where "
					+ "  c.referencia = ( select "
					+ "                      anoMesFaturamento "
					+ "  					from " 
					+ "  					  SistemaParametro sp ) and "
					+ "  ( c.debitoCreditoSituacaoAtual = 0 or "
					+ "    c.debitoCreditoSituacaoAnterior = 0 ) and "
					+ "  q.setorComercial.id = :idSetor and " 
					
					/**TODO:COSANPA
					 * Data:13/10/2011
					 * Autor Adriana Muniz
					 * 
					 * Limitar que apenas imoveis n㯠excluidos sejam retornados na consulta
					 * */
					+ " imo.indicadorExclusao = 2 ";
					//+ " l.id in (647, 411, 589, 590)";

			retorno = session.createQuery(hql).setInteger("idSetor", idSetor)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa os dados
	 * iniciais dos imoves n�o faturados selecionados do setor comercial, contendo os dados
	 * iniciais para o agrupamento Histograma de Agua e Esgoto
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @return Cole��o de contas
	 * @throws ErroRepositorioException
	 */
	public List getConsumoHistoricoImoveisNaoFaturados(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				"select \n" +
				"  gr.id, \n" + // 0
				"  un.id, \n" + // 1
				"  elo.id, \n" + // 2
				"  loc.id, \n" + // 3
				"  setCom.id, \n" + // 4
				"  setCom.codigo, \n" + // 5
				"  qua.id, \n" + // 6
				"  qua.numeroQuadra, \n" + // 7
				"  conTar.id, \n" + // 8
				"  imoPer.id, \n" + // 9
				"  las.id, \n" + // 10
				"  imo.id, \n" + // 11
				"  les.id, \n" + // 12
				"  imo.quantidadeEconomias, \n" + // 13
				"  ligTp.id, \n" + // 14
				"  conHist.numeroConsumoFaturadoMes, \n" + // 15
				"  pocTp.id, \n" + // 16
				"  conTp.id, \n" + // 17
				"  ligEsg.percentual \n" + // 18
				"from \n" + 
				"  ConsumoHistorico conHist \n" + 
				"  inner join conHist.imovel imo \n" + 
				"  inner join imo.localidade loc \n" +
				"  inner join loc.gerenciaRegional gr \n" +
				"  inner join loc.unidadeNegocio un \n" +
				"  inner join loc.localidade elo \n" +
				"  inner join imo.setorComercial setCom \n" +
				"  inner join imo.quadra qua \n" +
				"  inner join imo.consumoTarifa conTar \n" +
				"  inner join imo.imovelPerfil imoPer \n" +
				"  inner join imo.ligacaoAguaSituacao las \n" +
				"  inner join imo.ligacaoEsgotoSituacao les \n" +
				"  inner join conHist.ligacaoTipo ligTp \n" +
				"  left join conHist.pocoTipo pocTp \n" +
				"  inner join conHist.consumoTipo conTp \n" +
				"  left join imo.ligacaoEsgoto ligEsg, \n" +
				"  SistemaParametro sp \n" +
				"where \n" +
				"  conHist.referenciaFaturamento = sp.anoMesFaturamento and \n" +
				"  conHist.indicadorFaturamento = 1 and \n" +
				"  conHist.indicadorImovelCondominio = 2 and \n" +
				"  ( not exists( from Conta con where con.imovel.id = conHist.imovel.id and con.referencia = sp.anoMesFaturamento ) or \n" +
                "    conHist.indicadorFaturamento = 2 and conHist.indicadorImovelCondominio = 2 ) and \n" +
				"  setCom.id = :idSetor \n" +
				"order by \n" +
				"  imo.id, \n" +
				"  ligTp.id ";				

			retorno = session.createQuery(hql).setInteger("idSetor", idSetor)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public List<Categoria> getCategoriasImovelDistintas( int idImovel ) 
	  throws ErroRepositorioException {
		List<Categoria> retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql =
				" select scat.categoria " +
				" from " +
				"   Subcategoria scat " +
				" where " +
				"   scat.id in ( " +
				" 			    select distinct isc.comp_id.subcategoria.id " +
				" 			    from " +
				" 			      ImovelSubcategoria as isc " +
				" 			    where " +
				" 			      isc.comp_id.imovel.id = :idImovel ) ";

			retorno = session.createQuery(hql).setInteger("idImovel", idImovel)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;		
	}


	/**
	 * Seleciona todos os historicos de consumo de uma determinada localidade
	 * por imovel
	 * 
	 * @author Bruno Barrros, Ivan S鲧io
	 * @date 20/04/2007, 04/06/2008
	 * @alteracao: Adicionado a verificacao para cshi_icfaturamento = 1
	 * 
	 * @param idLocalidade
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosConsumoAgua(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			
			String hql = 
				"select "                
				+" imovel.id, "               
				+" case when ( conta is not null ) then "
				+"   locconta.gerenciaRegional.id "
				+" else "
				+"   locimo.gerenciaRegional.id "
				+" end, "               
				+" case when ( conta is not null ) then "
				+"   locconta.unidadeNegocio.id "
				+" else "
				+"   locimo.unidadeNegocio.id "
				+" end, "
				+" case when ( conta is not null ) then "
				+"   locconta.id "
				+" else "
				+"   locimo.id "
				+" end, "                  
				+" case when ( conta is not null ) then "
				+"   locconta.localidade.id "
				+" else "
				+"   locimo.localidade.id "
				+" end, "                 
				+" case when ( conta is not null ) then "
				+"  setcomcon.id "
				+" else "
				+"  setcomimo.id "
				+" end, "               
				+" case when ( conta is not null ) then "
				+"   quacon.rota.id "
				+" else "
				+"   quaimo.rota.id "
				+" end, "                 
				+" case when ( conta is not null ) then "
				+"   quacon.id "
				+" else "
				+"   quaimo.id "
				+" end, "                 
				+" case when ( conta is not null ) then "
				+"   conta.codigoSetorComercial "
				+" else "
				+"   setcomimo.codigo "
				+" end, "                 
				+" case when ( conta is not null ) then "
				+"   quacon.numeroQuadra "
				+" else "
				+"  quaimo.numeroQuadra "
				+" end, "                
				+" case when ( conta is not null ) then "
				+"   imoperfcon.id "
				+" else "
				+"   imovel.imovelPerfil.id "
				+" end, "                
				+" case when ( conta is not null ) then "
				+"   ligaguasitcon.id "
				+" else "
				+"   imovel.ligacaoAguaSituacao.id "
				+" end, "                  
				+" case when ( conta is not null ) then "
				+"   ligesgsitcon.id "
				+" else "
				+"   imovel.ligacaoEsgotoSituacao.id "
				+" end, "                 
				+" case when ( "
				+"   ligaguaimo.ligacaoAguaPerfil.id is null ) then "
				+"   0 "
				+" else "
				+"   ligaguaimo.ligacaoAguaPerfil.id "
				+" end, "                
				+" case when ( "
				+"   ligesgimo.ligacaoEsgotoPerfil.id is null ) then "
				+"   0 "
				+" else "
				+"   ligesgimo.ligacaoEsgotoPerfil.id "
				+" end, "                
				+" consumohistorico.consumoTipo.id, "               
				+" consumohistorico.numeroConsumoFaturadoMes,  "                 
				+" ligaguaimo.numeroConsumoMinimoAgua, "                  
				+" consumohistorico.referenciaFaturamento,  "                
				+" consumohistorico.ligacaoTipo.id, "
				+" conta.id, "                
				+" conta.consumoAgua,  "               
				+" conta.valorAgua "
				+" from "
				+" gcom.micromedicao.consumo.ConsumoHistorico consumohistorico "
				+" inner join consumohistorico.imovel imovel "
				+" inner join imovel.localidade locimo "
				+" inner join imovel.quadra quaimo "
				+" inner join imovel.setorComercial setcomimo "
				+" left join imovel.ligacaoAgua ligaguaimo "
				+" left join imovel.ligacaoEsgoto ligesgimo "
				+" left join imovel.contas conta with ( conta.referencia in ( select "
				+" sistemaParametro.anoMesFaturamento "
				+" from "
				+" gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro ) and ( conta.debitoCreditoSituacaoAtual = 0 "
				+" or conta.debitoCreditoSituacaoAnterior = 0 ) ) "
				+" left join conta.localidade locconta "
				+" left join conta.quadraConta quacon "
				+" left join conta.imovelPerfil imoperfcon "
				+" left join conta.ligacaoAguaSituacao ligaguasitcon "
				+" left join conta.ligacaoEsgotoSituacao ligesgsitcon "
				+" left join quacon.setorComercial setcomcon "
				+" where "
				+" imovel.setorComercial.id = :idSetor and "
				/**TODO:COSANPA
				 * Data:13/10/2011
				 * Autor Adriana Muniz
				 * 
				 * Limitar que apenas imoveis não excluidos sejam retornados na consulta
				 * */
				+" imovel.indicadorExclusao = 2 and " 
				+" consumohistorico.ligacaoTipo.id = :ligacaoTipo and "
				+" consumohistorico.indicadorFaturamento = 1 and "
				+" consumohistorico.indicadorImovelCondominio <> 1 and "
				+" consumohistorico.referenciaFaturamento = ( select "
				+" sistemaParametro.anoMesFaturamento "
				+" from "
				+" gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro ) ";



			retorno = session.createQuery(hql)
					.setInteger("idSetor", idSetor)
					.setInteger("ligacaoTipo", LigacaoTipo.LIGACAO_AGUA)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * 
	 * @author Bruno Barrros
	 * @date 19/04/2007
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getPagamentosResumoArrecadacaoAguaEsgoto(Integer idLocalidade)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "SELECT "
					+ "  imovel.id, "
					+ "  imovel.localidade.gerenciaRegional.id, "
					+ "  imovel.localidade.unidadeNegocio.id,"
					+ "  imovel.eloAnormalidade.id,"
					+ "  imovel.localidade.id,"
					+ "  imovel.setorComercial.id,"
					+ "  imovel.quadra.rota.id,"
					+ "  imovel.quadra.id,"
					+ "  imovel.imovelPerfil.id,"
					+
					// Verificamos a esfera de poder do cliente
					"  CASE WHEN ("
					+ "    clienteRelacaoTipo.id = :clienteResponsavel and"
					+ " 	 clienteImoveis.dataFimRelacao is null and esferaPoder.id is not null ) THEN"
					+ "    esferaPoder.id "
					+ "  ELSE"
					+ "    0 "
					+ "  END, "
					+
					// Verficiamos o tipo do cliente responsavel pelo imovel
					"  CASE WHEN ("
					+ "    clienteRelacaoTipo = :clienteResponsavel and"
					+ "    clienteImoveis.dataFimRelacao is null and cliente.clienteTipo.id is not null ) THEN"
					+ "    cliente.clienteTipo.id"
					+ "  ELSE"
					+ "    0"
					+ "  END,"
					+ "  imovel.ligacaoAguaSituacao.id, "
					+ "  imovel.ligacaoEsgotoSituacao.id, "
					+ "  imovel.ligacaoagua.ligacaoAguaPerfil.id, "
					+ "  imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "
					+ "  pagamento.documentoTipo.id,"
					+ "  pagamento.pagamentoSituacaoAtual.id,"
					+
					// Verificamos se o mes ano do pagamento e menor do que o
					// mes ano de referencia do
					// pagamento
					"  CASE WHEN ("
					+ "    cast( year( pagamento.dataPagamento ) || month(pagamento.dataPagamento), Integer ) <"
					+ "    anoMesReferenciaPagamento ) THEN"
					+ "    2"
					+ "  ELSE"
					+ "    1"
					+ "  END,"
					+
					// Realizamos os calculos necessarios para determinar a
					// epoca de pagamento correta
					"  CASE WHEN ("
					+ "    pagamento.contaGeral is null && "
					+ "    pagamento.guiaPagamento is null && "
					+ "    pagamento.debitoACobrarGeral is null ) THEN"
					+ "    9"
					+ "  ELSE"
					+ "    CASE WHEN ("
					+ "      pagamento.contaGeral is not null ) THEN"
					+ "      CASE WHEN ("
					+ "        pagamento.dataPagamento <= pagamento.contaGeral.conta.dataVencimentoConta ) THEN"
					+ "        0"
					+ "      ELSE"
					+ "        CASE WHEN ("
					+ "          pagamento.dataPagamento > pagamento.contaGeral.conta.dataVencimentoConta && ) THEN"
					+ "          CASE WHEN"
					+ "            month( dataPagamento ) = month( pagamento.contaGeral.conta.dataVencimentoConta ) ) THEN"
					+ "            1"
					+ "          ELSE"
					+ "            CASE WHEN ("
					+ "              month( dataPagamento ) = ( month( pagamento.contaGeral.conta.dataVencimentoConta ) + 1 ) ) THEN"
					+ "              2"
					+ "            ELSE"
					+ "              CASE WHEN ("
					+ "                month( dataPagamento ) = ( month( pagamento.contaGeral.conta.dataVencimentoConta ) + 2 ) ) THEN"
					+ "                3"
					+ "              ELSE"
					+ "                CASE WHEN ("
					+ "                  month( dataPagamento ) = ( month( pagamento.contaGeral.conta.dataVencimentoConta ) + 3 ) ) THEN"
					+ "                  4"
					+ "                ELSE"
					+ "                  CASE WHEN ("
					+ "                    month( dataPagamento ) > ( month( pagamento.contaGeral.conta.dataVencimentoConta ) + 3 ) ) THEN"
					+ "                    5"
					+ "				   END"
					+ "				 END"
					+ "			   END"
					+ "			 END"
					+ "		   END"
					+ "		 ELSE"
					+ "          CASE WHEN ("
					+ "            pagamento.guiaPagamento is not null ) THEN"
					+ "      	     CASE WHEN ("
					+ "              pagamento.dataPagamento <= pagamento.guiaPagamento.dataVencimento ) THEN"
					+ "              0"
					+ "      		 ELSE"
					+ "              CASE WHEN ("
					+ "                pagamento.dataPagamento > pagamento.guiaPagamento.dataVencimento && ) THEN"
					+ "                CASE WHEN"
					+ "                  month( dataPagamento ) = month( pagamento.guiaPagamento.dataVencimento ) ) THEN"
					+ "                  1"
					+ "          	     ELSE"
					+ "                  CASE WHEN ("
					+ "                    month( dataPagamento ) = ( month( pagamento.guiaPagamento.dataVencimento ) + 1 ) ) THEN"
					+ "                    2"
					+ "                  ELSE"
					+ "                    CASE WHEN ("
					+ "                      month( dataPagamento ) = ( month( pagamento.guiaPagamento.dataVencimento ) + 2 ) ) THEN"
					+ "                      3"
					+ "                    ELSE"
					+ "                      CASE WHEN ("
					+ "                        month( dataPagamento ) = ( month( pagamento.guiaPagamento.dataVencimento ) + 3 ) ) THEN"
					+ "                        4"
					+ "                      ELSE"
					+ "                        CASE WHEN ("
					+ "                          month( dataPagamento ) > ( month( pagamento.guiaPagamento.dataVencimento ) + 3 ) ) THEN"
					+ "                          5"
					+ "				         END"
					+ "				 	   END"
					+ "			         END"
					+ "			       END"
					+ "		         END"
					+ "              END"
					+ "		   ELSE"
					+ "            CASE WHEN ("
					+ "              pagamento.debitoACobrarGeral is not null ) THEN"
					+ "              0" + "            END" + "          END"
					+ "        END" + "      END" + "    END"
					+ "  END epocaPagamento,"
					+ "  imovel.setorComercial.codigo"
					+ "  imovel.quadra.numeroQuadra,"
					+ "  pagamento.contaGeral.conta.valorAgua,"
					+ "  pagamento.contaGeral.conta.valorEsgoto," + "  CASE WHEN ("
					+ "    pagamento.contaGeral is null && "
					+ "    pagamento.guiaPagamento is null && "
					+ "    pagamento.debitoACobrarGeral is null ) THEN"
					+ "    valorPagamento" + "  ELSE" + "    0"
					+ "  END valorNaoIdentificado" + "FROM"
					+ "  gcom.arrecadacao.pagamento.Pagamento pagamento, "
					+ "  left join pagamento.imovel imovel "
					+ "  left join imovel.clienteImoveis clienteImoveis "
					+ "  left join clienteRelacaoTipo clienteRelacaoTipo "
					+ "  left join clienteTipo.esferaPoder esferaPoder "
					+ "  left join clienteImoveis.cliente cliente "
					+ " WHERE " + " imovel.localidade.id = :idLocalidade ";

			retorno = session.createQuery(hql).setShort("clienteResponsavel",
					ClienteRelacaoTipo.RESPONSAVEL).setShort(
					"clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("ligacaoAguaSituacaoLigado",
							LigacaoAguaSituacao.LIGADO).setInteger(
							"ligacaoAguaSituacaoCortado",
							LigacaoAguaSituacao.CORTADO).setInteger(
							"ligacaoEsgotoSituacaoLigado",
							LigacaoEsgotoSituacao.LIGADO).setInteger(
							"idLocalidade", idLocalidade).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel, id da regi�o, id da microrregi�o, id do munic�pio, id do
	 * bairro, id do perfil do im�vel, esfera de poder, id do tipo de cliente,
	 * id da situa��o da liga��o �gua, id situacao da ligacao de esgoto,
	 * principal categoria do imovel, principal sub categoria do imovel perfil
	 * da liga��o da �gua, perfil da liga��o do esgoto
	 * 
	 * @author Ivan S�rgio
	 * @date 19/04/2007
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLigacaoEconomiaRegiao(int idLocalidade)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "SELECT "
					+ "	imovel.id, "
					+ "	imovel.bairro.municipio.microrregiao.regiao.id, "
					+ "	imovel.bairro.municipio.microrregiao.id, "
					+ "	imovel.bairro.municipio.id, "
					+ "	imovel.bairro.id, "
					+ "	imovel.imovelPerfil.id, "
					+ "	imovel.ligacaoAguaSituacao.id, "
					+ "	imovel.ligacaoEsgotoSituacao.id, "
					+
					// Verificamos a esfera de poder do cliente
					"  	CASE WHEN ("
					+ "   	clienteRelacaoTipo.id = :clienteResponsavel and"
					+ " 		clienteImoveis.dataFimRelacao is null and esferaPoder.id is not null ) THEN"
					+ "   	esferaPoder.id "
					+ "	ELSE"
					+ "   	0 "
					+ "	END, "
					+
					// Verficiamos o tipo do cliente responsavel pelo imovel
					"  	CASE WHEN ("
					+ "   	clienteRelacaoTipo = :clienteResponsavel and"
					+ "   	clienteImoveis.dataFimRelacao is null and cliente.clienteTipo.id is not null ) THEN"
					+ "   	cliente.clienteTipo.id"
					+ "	ELSE"
					+ "   	0"
					+ "	END,"
					+ "	imovel.ligacaoagua.ligacaoAguaPerfil.id, "
					+ "	imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id, "
					+
					// Haver� hidrometro instalado quando a situa��o da agua for
					// [ligada ou cortada] e houver hist�rico,
					// ou quando a situa��o estiver como ligada e n�o houver
					// hist�rico
					"	CASE WHEN ("
					+ "		( ( imovel.ligacaoAguaSituacao.id = :ligacaoAguaSituacaoLigado OR"
					+ "       	imovel.ligacaoAguaSituacao.id = :ligacaoAguaSituacaoCortado ) AND"
					+ "      		imovel.ligacaoAgua.hidrometroInstalacaoHistorico is not null ) OR"
					+ "      	(	imovel.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado AND "
					+ "			imovel.hidrometroInstalacaoHistorico is not null ) ) THEN"
					+ "		1"
					+ "	ELSE"
					+ "		2"
					+ "	END,"
					+
					// Caso HIDI_ID nao seja nulo, entao marcarmos como tendo
					// hidrometro...
					"  	CASE WHEN ("
					+ "		imovel.hidrometroInstalacaoHistorico is not null ) THEN"
					+ "		1"
					+ "	ELSE"
					+ "		2"
					+ "	END,"
					+
					// Verificamos se o imovel possue volume minimo de agua
					// fixado
					"	CASE WHEN ("
					+ "		imovel.ligacaoAgua.numeroConsumoMinimoAgua > 0 ) THEN"
					+ "		1"
					+ "	ELSE"
					+ "		2"
					+ "	END,"
					+
					// Verificamos se o imovel possue volume minimo de esgoto
					// fixado
					"	CASE WHEN ("
					+ "		imovel.ligacaoEsgoto.consumoMinimo > 0 ) THEN"
					+ "		1"
					+ "	ELSE"
					+ "		2"
					+ "	END,"
					+
					// Verificamos se o imovel tem poco
					"	CASE WHEN ( "
					+ "		imovel.pocoTipo is not null ) THEN"
					+ "		1"
					+ "	ELSE"
					+ "		2"
					+ "	END,"
					+
					// Apos todos os agrupamentos, somamos 1 nas quantidades de
					// ligacao
					"	1 as qtdLigacao,"
					+ "	quantidadeEconomias"
					+ "FROM"
					+ "	gcom.cadastro.imovel.Imovel as imovel "
					+ "  	left join imovel.clienteImoveis clienteImoveis "
					+ "  	left join clienteImoveis.clienteRelacaoTipo clienteRelacaoTipo "
					+ "  	left join clienteTipo.esferaPoder esferaPoder "
					+ "  	left join clienteImoveis.cliente cliente "
					+ "WHERE " + "	imovel.localidade.id = :idLocalidade ";

			retorno = session.createQuery(hql).setShort("clienteResponsavel",
					ClienteRelacaoTipo.RESPONSAVEL).setShort(
					"clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("ligacaoAguaSituacaoLigado",
							LigacaoAguaSituacao.LIGADO).setInteger(
							"ligacaoAguaSituacaoCortado",
							LigacaoAguaSituacao.CORTADO).setInteger(
							"ligacaoEsgotoSituacaoLigado",
							LigacaoEsgotoSituacao.LIGADO).setInteger(
							"idLocalidade", idLocalidade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0269] - Consultar Resumo das Ligacoes / Economia
	 * 
	 * @author Thiago Toscano
	 * @date 29/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoLigacoesEconomias(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		List retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(
					GeradorSqlRelatorio.RESUMO_LIGACOES_ECONOMIAS,
					informarDadosGeracaoRelatorioConsultaHelper);

			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"
					+ informarDadosGeracaoRelatorioConsultaHelper
							.getDescricaoOpcaoTotalizacao() + "'", "", "", "",
					false, false);

			// faz a pesquisa
			retorno = session
					.createSQLQuery(sql)
					.addScalar("estado", Hibernate.STRING)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("descricaoGerencia", Hibernate.STRING)
					.addScalar("idElo", Hibernate.INTEGER)
					.addScalar("descricaoElo", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("descricaoLocalidade", Hibernate.STRING)
					.addScalar("idSetorComercial", Hibernate.INTEGER)
					.addScalar("descricaoSetorComercial", Hibernate.STRING)
					.addScalar("idQuadra", Hibernate.INTEGER)
					.addScalar("descricaoQuadra", Hibernate.STRING)
					.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoAguaSituacao", Hibernate.STRING)
					.addScalar("idLigacaoEsgotoSituacao", Hibernate.INTEGER)
					.addScalar("descricaoLigacaoEsgotoSituacao",
							Hibernate.STRING).addScalar("idCategoria",
							Hibernate.INTEGER).addScalar("descricaoCategoria",
							Hibernate.STRING).addScalar(
							"qtdLigacoesComHidrometro", Hibernate.INTEGER)
					.addScalar("qtdLigacoesSemHidrometro", Hibernate.INTEGER)
					.addScalar("qtdLigacoesTotal", Hibernate.INTEGER)
					.addScalar("qtdEconomiasComHidrometro", Hibernate.INTEGER)
					.addScalar("qtdEconomiasSemHidrometro", Hibernate.INTEGER)
					.addScalar("qtdEconomiasTotal", Hibernate.INTEGER).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o com os resultados da pesquisa
		return retorno;
	}

	public Long maximoIdImovel() throws ErroRepositorioException {

		Long retorno = 0L;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select max(imovel.id) from Imovel imovel";
			retorno = ((Integer) session.createQuery(hql).uniqueResult())
					.longValue();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que retona uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id rota id guradra numero da quadra id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Marcio Roberto
	 * @date 04/05/2007
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoParcelamento(int idLocalidade, int anoMes)
			throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
					+ "  parc.id, "
					+ "  imo.id, "
					+ "  parc.localidade.gerenciaRegional.id, "
					+ "  parc.localidade.unidadeNegocio.id, "
					+ "  parc.localidade.localidade.id, "
					+ "  parc.localidade.id, "
					+ "  parc.quadra.setorComercial.id, "
					+ "  parc.quadra.rota.id, "
					+ "  parc.quadra.id, "
					+ "  parc.codigoSetorComercial, "
					+ "  parc.numeroQuadra, "
					+ "  imo.imovelPerfil.id, "
					+ "  parc.ligacaoAguaSituacao.id, "
					+ "  parc.ligacaoEsgotoSituacao.id, "
					+ "  case when ( "
					+ "    imo.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "
					+ "    0 "
					+ "  else "
					+ "    imo.ligacaoAgua.ligacaoAguaPerfil.id "
					+ "  end, "
					+ "  case when ( "
					+ "    imo.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "
					+ "    0 "
					+ "  else "
					+ "    imo.ligacaoEsgoto.ligacaoEsgotoPerfil.id "
					+ "  end, "
					+ "  parc.valorConta, "
					+ "  parc.valorGuiaPapagamento, " // 17
					+ "  parc.valorCreditoARealizar, " // 18
					+ "  parc.valorDescontoAcrescimos, " // 19
					+ "  parc.valorDescontoInatividade, " // 20
					+ "  parc.valorDescontoAntiguidade, " // 21
					+ "  (parc.valorPrestacao * parc.numeroPrestacoes) as totalparcelamento, "// 22
					+ "  parc.anoMesReferenciaFaturamento, " // 23
					+ "  parc.valorServicosACobrar, " // 24
					+ "  (parc.valorAtualizacaoMonetaria + parc.valorJurosMora + parc.valorMulta) as debitosACobrarAcrescimos, " // 25
					+ "  parc.valorParcelamentosACobrar, " // 26
					+ "  parc.valorEntrada,  "// 27
					+ "  parc.valorJurosParcelamento, "// 28
					+ "  parc.numeroPrestacoes, "// 29
					+ "  imo.consumoTarifa.id,  "// 30
					+ "  imo.numeroReparcelamentoConsecutivos, "// 31
					+ "  rota.codigo "//32
					+ " from "
					+ "  gcom.cobranca.parcelamento.Parcelamento parc "
					+ "  inner join parc.imovel imo "
					+ "  inner join imo.quadra quad "
					+ "  inner join quad.rota rota "
					+ "  inner join quad.setorComercial stcom "
					+ "  inner join stcom.localidade locali "
					+ "  left join imo.ligacaoAgua ligacaoAgua "
					+ "  left join imo.ligacaoEsgoto ligacaoEsgoto "
					+ " where locali.id = :idLocalidade and "
					+ "  imo.indicadorExclusao = 2 and "
					+ "  parc.parcelamentoSituacao.id = 1 and "
					+ "  parc.anoMesReferenciaFaturamento = :anoMesReferencia ";
			retorno = session.createQuery(hql).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferencia", anoMes).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * pesquisarObterQuantidadeContas
	 * 
	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId
	 *            id do parcelamento para relacionar-se com parcelamentoItem
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Integer pesquisarObterQuantidadeContas(Integer parcelamentoId)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "select count(parcelamentoitem.contaGeral.id) as qtdContas "
					+ "from  ParcelamentoItem parcelamentoitem "
					+ "inner join parcelamentoitem.parcelamento parcelamento "
					+ "inner join parcelamentoitem.contaGeral contageral "
					+ "where parcelamento.id = :parcelamentoId "
					+ "and parcelamentoitem.contaGeral.id is not null ";
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"parcelamentoId", parcelamentoId).setMaxResults(1)
					.uniqueResult();
			//+ "inner join contageral.conta as conta "
			// parcelamento.intValue()).uniqueResult();
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * pesquisarObterQuantidadeGuias
	 * 
	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId
	 *            id do parcelamento para relacionar-se com parcelamentoItem
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Integer pesquisarObterQuantidadeGuias(Integer parcelamentoId)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select count(parcelamentoitem.guiaPagamentoGeral.id ) as qtdGuias "
					+ "from  ParcelamentoItem parcelamentoitem "
					+ "inner join parcelamentoitem.parcelamento parcelamento "
					+ "inner join parcelamentoitem.guiaPagamentoGeral guiaPagamentoGeral "
					+ "inner join guiaPagamentoGeral.guiaPagamento guiaPagamento "
					+ "where parcelamento.id = :parcelamentoId "
					+ "and parcelamentoitem.guiaPagamentoGeral.id is not null ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"parcelamentoId", parcelamentoId).setMaxResults(1)
					.uniqueResult();
			// parcelamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * pesquisarObterQuantidadeServicosIndiretos
	 * 
	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId
	 *            id do parcelamento para relacionar-se com parcelamentoItem
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Short pesquisarObterQuantidadeServicosIndiretos(
			Integer parcelamentoId) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select count(parcelamentoitem.debitoACobrarGeral.id ) as qtdServicosIndiretos "
					+ " from  ParcelamentoItem parcelamentoitem "
					+ "  inner join parcelamentoitem.parcelamento parcelamento "
					+ "  inner join parcelamentoitem.debitoACobrarGeral debitoCobrarGeral "
					+ "  inner join debitoCobrarGeral.debitoACobrar debitoCobrar "
					+ " where parcelamento.id = :parcelamentoId "
					+ "  and parcelamentoitem.debitoACobrarGeral.id is not null ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"parcelamentoId", parcelamentoId).setMaxResults(1)
					.uniqueResult();
			// parcelamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno.shortValue();
	}

	/**
	 * pesquisarObterValorServicosIndiretos
	 * 
	 * @author Marcio Roberto
	 * @date 07/05/2007
	 * 
	 * @param parcelamentoId
	 *            id do parcelamento para relacionar-se com parcelamentoItem
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public BigDecimal pesquisarObterValorServicosIndiretos(
			Integer parcelamentoId, String condicao)
			throws ErroRepositorioException {

		BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select sum(debitoacobrar.valorDebito) as valorServicosIndiretos "
					+ "from  ParcelamentoItem parcelamentoitem "
					+ "inner join parcelamentoitem.parcelamento parcelamento "
					+ "inner join parcelamentoitem.debitoACobrarGeral debitoacobrargeral "
					+ "inner join debitoacobrargeral.debitoACobrar debitoacobrar "
					+ "where parcelamento.id = :parcelamentoId "
					+ "and parcelamentoitem.debitoACobrarGeral.id is not null "
					+ "and " + condicao;

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"parcelamentoId", parcelamentoId).setMaxResults(1)
					.uniqueResult();
			// parcelamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * pesquisaQuantidadeCategorias
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @param conta
	 *            id da conta a qual procuraremos as categorias
	 * 
	 * @return quantidade de categorias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeCategorias(Integer conta)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  count(*) "
					+ "from "
					+ "  gcom.faturamento.conta.ContaCategoria cc "
					+ "  inner join cc.comp_id.conta c "
					+ "where "
					+ "  c.referencia = ( select  "
					+ "                     anoMesFaturamento  "
					+ "   				from   "
					+ "    				  gcom.cadastro.sistemaparametro.SistemaParametro sp ) and   "
					+ "  ( c.debitoCreditoSituacaoAtual = 0 or   "
					+ "    c.debitoCreditoSituacaoAnterior = 0 ) and "
					+ "  c.id = :idConta ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idConta", conta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * pesquisarEsferaPoderClienteResponsavelImovel
	 * 
	 * @author Bruno Barros
	 * @date 16/05/2007
	 * 
	 * @param id
	 *            do imovel a ser pesquisado
	 * @return Esfera de poder do cliente responsavel pelo imovel
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarEsferaPoderClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  case when ep.id is not null then "
					+ "    ep.id "
					+ "  else  "
					+ "    0 "
					+ "  end "
					+ "from "
					+ "  gcom.cadastro.imovel.Imovel i "
					+ "  inner join i.clienteImoveis ci "
					+ "  inner join ci.cliente c "
					+ "  inner join c.clienteTipo ct "
					+ "  inner join ct.esferaPoder ep "
					+ "  inner join ci.clienteRelacaoTipo crt "
					+ "where "
					+ "  crt.id = :clienteResponsavel and ci.dataFimRelacao is null and i.id =:idImovel";

			retorno = session.createQuery(consulta).setInteger(
					"clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return (Integer) retorno;
		} else {
			return 0;
		}
	}

	/**
	 * pesquisarTipoClienteClienteResponsavelImovel
	 * 
	 * @author Bruno Barros
	 * @date 16/05/2007
	 * 
	 * @param imovel
	 *            a ser pesquisado
	 * @return Tipo de cliente do cliente responsavel pelo imovel
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarTipoClienteClienteResponsavelImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  case when ct.id is not null then "
					+ "    ct.id "
					+ "  else  "
					+ "    0 "
					+ "  end "
					+ "from "
					+ "  gcom.cadastro.imovel.Imovel i "
					+ "  inner join i.clienteImoveis ci "
					+ "  inner join ci.cliente c "
					+ "  inner join c.clienteTipo ct "
					+ "  inner join ci.clienteRelacaoTipo crt "
					+ "where "
					+ "  crt.id = :clienteResponsavel and ci.dataFimRelacao is null and i.id =:idImovel";

			retorno = session.createQuery(consulta).setInteger(
					"clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return (Integer) retorno;
		} else {
			return 0;
		}
	}

	/**
	 * pesquisaQuantidadeEconomias
	 * 
	 * @author Bruno Barros
	 * @date 14/05/2007
	 * 
	 * @param idConta
	 *            id da conta a qual procuraremos as categorias
	 * @return quantidade de economias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeEconomias(Integer idConta,
			Integer idCategoria) throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  sum( ccat.quantidadeEconomia ) "
					+ "from "
					+ "  gcom.faturamento.conta.ContaCategoria ccat "
					+ "where "
					+ "  ccat.comp_id.conta.id = :idConta and ccat.comp_id.categoria.id = :idCategoria";

			retorno = session.createQuery(consulta).setInteger("idConta",
					idConta).setInteger("idCategoria", idCategoria)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return new Integer((Short) retorno);
		} else {
			return 0;
		}
	}
	
	//********************************************************
	//Alterado por: Ivan S鲧io
	//Data: 19/01/2009
	//CRC1012 - Adicionado o reca_icligacaofaturada
	//********************************************************
	public void inserirResumoConsumoAgua(Integer anoMesReferencia,
			ResumoConsumoAguaHelper resConsumo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into micromedicao.un_resumo_consumo_agua ("
					+ "  reca_id, "
					+ "  uneg_id, "
					+ "  reca_amreferencia, "
					+ "  greg_id, "
					+ "  loca_id, "
					+ "  loca_cdelo, "
					+ "  stcm_id, "
					+ "  qdra_id, "
					+ "  reca_cdsetorcomercial, "
					+ "  rota_id, "
					+ "  reca_nnquadra, "
					+ "  last_id, "
					+ "  lest_id, "
					+ "  catg_id, "
					+ "  scat_id , "
					+ "  epod_id , "
					+ "  cltp_id , "
					+ "  lapf_id , "
					+ "  reca_qteconomias , "
					+ "  lepf_id , "
					+ "  reca_consumoagua , "
					+ "  reca_tmultimaalteracao, "
					+ "  reca_consumoexcedente , "
					+ "  reca_qtligacoes , "
					+ "  iper_id , "
					+ "  cstp_id ,"
					+ "  reca_icvolumeexcedente,"
					+ "  reca_ichidrometro,"
					+ "  reca_voconsumofaturado, "
					+ "  reca_icligacaofaturada )"
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_resumo_consumo_agua") + ", "
					+ resConsumo.getIdUnidadeNegocio() + ", "
					+ anoMesReferencia + ", "
					+ resConsumo.getIdGerenciaRegional() + ", "
					+ resConsumo.getIdLocalidade() + ", "
					+ resConsumo.getIdElo() + ", "
					+ resConsumo.getIdSetorComercial() + ", "
					+ resConsumo.getIdQuadra() + ", "
					+ resConsumo.getCodigoSetorComercial() + ", "
					+ resConsumo.getIdRota() + ", "
					+ resConsumo.getNumeroQuadra() + ", "
					+ resConsumo.getIdLigacaoAguaSituacao() + ", "
					+ resConsumo.getIdLigacaoEsgotoSituacao() + ", "
					+ resConsumo.getIdCategoria() + ", "
					+ resConsumo.getIdSubCategoria() + ", "
					+ resConsumo.getIdEsferaPoder() + ", "
					+ resConsumo.getIdClienteTipo() + ", "
					+ resConsumo.getIdLigacaoAguaPerfil() + ", "
					+ resConsumo.getQuantidadeEconomias() + ", "
					+ resConsumo.getIdLigacaoEsgotoPerfil() + ", "
					+ resConsumo.getQuantidadeConsumoAgua() + ", " 
					+ Util.obterSQLDataAtual() + " , "
					+ resConsumo.getQuantidadeConsumoAguaExcedente() + ", "
					+ resConsumo.getQuantidadeLigacoes() + ", "
					+ resConsumo.getIdImovelPerfil() + ", "
					+ resConsumo.getIdConsumoTipo() + ", "
					+ resConsumo.getIdVolumeExcedente() + ","
			        + resConsumo.getIdHidrometro() + ","
			        + resConsumo.getVolumeFaturado() + ","
			        + resConsumo.getIndicadorLigacaoFaturada() + ")";
			
			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	/**
	 * Verifica se o consumo do im�vel � real.
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu consumo verificado *
	 * @return 1 se consumo real, 2 sen�o
	 * @throws ErroRepositorioException
	 */
	public Integer verificarConsumoReal(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  ch.consumoTipo.id "
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 1 and "
					+ "  ch.referenciaFaturamento = ( select "
					+ "  									anoMesFaturamento "
					+ "  								from "
					+ "  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return ((Integer) retorno == 1 ? 1 : 2);
		} else {
			return 2;
		}
	}

	/**
	 * Verifica se o im�vel possue hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu consumo verificado *
	 * @return 1 se possuir hidrimetro, 2 sen�o
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaHidrometro(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  count(*) "
					+ "from "
					+ "  gcom.micromedicao.medicao.MedicaoHistorico mh "
					+ "where "
					+ "  mh.ligacaoAgua.id = :idImovel and "
					+ "  mh.anoMesReferencia = ( 	select "
					+ "  								anoMesFaturamento "
					+ "  							from "
					+ "  								gcom.cadastro.sistemaparametro.SistemaParametro sp ) ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return ((Integer) retorno >= 1 ? 1 : 2);
		} else {
			return 2;
		}
	}

	/**
	 * Verifica se o imovel possue posso.
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu consumo verificado *
	 * @return 1 se consumo real, 2 sen�o
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaPoco(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  case when ch.pocoTipo.id is null or ch.pocoTipo.id = 0 then "
					+ "    1 " 
					+ "  else "
					+ "    2 "
					+ "  end "					
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 1 and "
					+ "  ch.referenciaFaturamento = ( 	select "
					+ "  								anoMesFaturamento "
					+ "  							from "
					+ "  								gcom.cadastro.sistemaparametro.SistemaParametro sp )";
			
			Object retorno = session.createQuery(consulta).setInteger("idImovel", idImovel).uniqueResult();
			
			if ( retorno == null ){
				return 1;
			} 		

			return (Integer) retorno;
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Verifica se o imovel volume fixo de agua
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu volume fixo verificado
	 * @return 1 se consumo exite, 2 sen�o
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaVolumeFixoAgua(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  ch.consumoMinimo "
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 1 and "
					+ "  ch.referenciaFaturamento = ( select "
					+ "  									anoMesFaturamento "
					+ "  								from "
					+ "  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return ((Integer) retorno != 0 ? 1 : 2);
		} else {
			return 2;
		}
	}

	/**
	 * Verifica se o imovel volume fixo de agua
	 * 
	 * @author Bruno Barros
	 * @date 23/05/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu volume fixo verificado
	 * @return 1 se consumo exite, 2 sen�o
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaVolumeFixoEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  ch.consumoMinimo "
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 2 and "
					+ "  ch.referenciaFaturamento = ( select "
					+ "  									anoMesFaturamento "
					+ "  								from "
					+ "  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return ((Integer) retorno != 0 ? 1 : 2);
		} else {
			return 2;
		}
	}
	
	/**
	 * Seleciona o percentual de consumo de esgoto
	 * 
	 * @author Bruno Barros
	 * @date 27/07/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu volume fixo verificado
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Float percentualColetaEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  ch.percentualColeta "
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 1 and "
					+ "  ch.referenciaFaturamento = ( select "
					+ "  									anoMesFaturamento "
					+ "  								from "
					+ "  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";

			return (Float) session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
	}	

	public void inserirHistogramaAguaLigacao(Integer anoMesReferencia,
			HistogramaAguaLigacaoHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			
			

			insert = "insert into "
					+ "  faturamento.histograma_agua_ligacao ( "
					+ "  hagl_id, "
					+ "  hagl_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hagl_cdsetorcomercial, "
					+ "  qdra_id, "
					+ "  hagl_nnquadra, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "  hagl_icligacaomista, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  last_id, "
					+ "  hagl_icconsumoreal, "
					+ "  hagl_ichidrometro, "
					+ "  hagl_icpoco, "
					+ "  hagl_icvolfixadoagua, "
					+ "  hagl_qtconsumo, "
					+ "  hagl_qtligacao, "
					+ "  hagl_qteconomialigacao, "
					+ "  hagl_vlfaturadoligacao, "
					+ "  hagl_vofaturadoligacao, "
					+ "  hagl_tmultimaalteracao ) "
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histograma_agua_ligacao") +", "
					+ anoMesReferencia + ", " + helper.getIdGerenciaRegional()
					+ ", " + helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", " + helper.getIdLocalidade()
					+ ", " + helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdQuadra() + ", " + helper.getIdNumeroQuadra()
					+ ", " + helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdLigacaoMista() + ", "
					+ helper.getIdContaCaregoria() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoAgua() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoAgua() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeLigacao() + ", "
					+ helper.getQuantidadeEconomiaLigacao() + ", "
					+ helper.getValorFaturadoLigacao() + ", "
					+ helper.getVolumeFaturadoLigacao() + ", " + Util.obterSQLDataAtual() +" )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexoes");
			}
		}
	}

	public void inserirHistogramaAguaEconomima(Integer anoMesReferencia,
			HistogramaAguaEconomiaHelper helper)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histograma_agua_economia ( "
					+ "  hage_id, "
					+ "  hage_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hage_cdsetorcomercial, "
					+ "  qdra_id, "
					+ "  hage_nnquadra, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  last_id, "
					+ "  hage_icconsumoreal, "
					+ "  hage_ichidrometro, "
					+ "  hage_icpoco, "
					+ "  hage_icvolfixadoagua, "
					+ "  hage_qtconsumo, "
					+ "  hage_qteconomia, "
					+ "  hage_vlfaturadoeconomia, "
					+ "  hage_vofaturadoeconomia, "
					+ "  hage_tmultimaalteracao,  "
					+ "  hage_qtligacao )"					
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histograma_agua_economia") + ", "
					+ anoMesReferencia + ", " + helper.getIdGerenciaRegional()
					+ ", " + helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", " + helper.getIdLocalidade()
					+ ", " + helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdQuadra() + ", " + helper.getIdNumeroQuadra()
					+ ", " + helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoAgua() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoAgua() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeEconomia() + ", "
					+ helper.getValorFaturadoEconomia() + ", "
					+ helper.getVolumeFaturadoEconomia() + ", " + Util.obterSQLDataAtual() + " , " 
					+ helper.getQuantidadeLigacoes() +")";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public void inserirHistogramaEsgotoLigacao(Integer anoMesReferencia,
			HistogramaEsgotoLigacaoHelper helper)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histograma_esg_ligacao ( "
					+ "  hegl_id, "
					+ "  hegl_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hegl_cdsetorcomercial, "
					+ "  qdra_id, "
					+ "  hegl_nnquadra, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "  hegl_icligacaomista, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  lest_id, "
					+ "  hegl_icconsumoreal, "
					+ "  hegl_ichidrometro, "
					+ "  hegl_icpoco, "
					+ "  hegl_icvolfixadoesgoto, "
					+ "  hegl_nnpercentualesgoto, "
					+ "  hegl_qtconsumo, "
					+ "  hegl_qtligacao, "
					+ "  hegl_qteconomialigacao, "
					+ "  hegl_vlfaturadoligacao, "
					+ "  hegl_vofaturadoligacao, "
					+ "  hegl_tmultimaalteracao )"
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histograma_esg_ligacao") + ", "
					+ anoMesReferencia + ", " + helper.getIdGerenciaRegional()
					+ ", " + helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", " + helper.getIdLocalidade()
					+ ", " + helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdQuadra() + ", " + helper.getIdNumeroQuadra()
					+ ", " + helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdLigacaoMista() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoEsgoto() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoEsgoto() + ", "
					+ helper.getPercentualColetaEsgoto() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeLigacao() + ", "
					+ helper.getQuantidadeEconomiaLigacao() + ", "
					+ helper.getValorFaturadoLigacao() + ", "
					+ helper.getVolumeFaturadoLigacao() + ", " 
					+ Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

			System.out.print("1 - INSERINDO HistogramaEsgotoLigacaoHelper ");
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	public void inserirHistogramaEsgotoEconomia(Integer anoMesReferencia,
			HistogramaEsgotoEconomiaHelper helper)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histograma_esg_economia ( "
					+ "  hege_id, "
					+ "  hege_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hege_cdsetorcomercial, "
					+ "  qdra_id, "
					+ "  hege_nnquadra, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  lest_id, "
					+ "  hege_icconsumoreal, "
					+ "  hege_ichidrometro, "
					+ "  hege_icpoco, "
					+ "  hege_icvolfixadoesgoto, "
					+ "  hege_nnpercentualesgoto, "
					+ "  hege_qtconsumo, "
					+ "  hege_qteconomia, "
					+ "  hege_vlfaturadoeconomia, "
					+ "  hege_vofaturadoeconomia, "
					+ "  hege_tmultimaalteracao, "
					+ "  hege_qtligacao )"
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histograma_esg_economia") + ", "
					+ anoMesReferencia + ", " + helper.getIdGerenciaRegional()
					+ ", " + helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", " + helper.getIdLocalidade()
					+ ", " + helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdQuadra() + ", " + helper.getIdNumeroQuadra()
					+ ", " + helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoEsgoto() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoEsgoto() + ", "
					+ helper.getPercentualEsgoto() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeEconomia() + ", "
					+ helper.getValorFaturadoEconomia() + ", "
					+ helper.getVolumeFaturadoEconomia() + ", " + Util.obterSQLDataAtual() + ", " 
					+ helper.getQuantidadeLigacoes() + ")";

			stmt.executeUpdate(insert);

			System.out.print("1 - INSERINDO HistogramaEsgotoEconomiaHelper ");
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	/**
	 * 
	 * [UC0623] - Gerar Resumo de Metas (CAERN)
	 * 
	 * Seleciona todos os im�veis, com o ano/mes arrecada��o de sistema
	 * parametros de uma determinada ger�ncia regional por imovel
	 * 
	 * @author S�vio Luiz
	 * @date 20/07/2007
	 * 
	 * @param idLocalidade
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List pesquisarDadosResumoMetas(int idSetor, Date dataInicial,
			Date dataFinal) throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
					+ "   imovel.id, "// 0
					+ "   imovel.localidade.gerenciaRegional.id, "// 1
					+ "   imovel.localidade.unidadeNegocio.id,  "// 2
					+ "   imovel.localidade.id,  "// 3
					+ "   imovel.localidade.localidade.id, "// 4
					+ "   imovel.setorComercial.id,   "// 5
					+ "   imovel.quadra.rota.id,  "// 6
					+ "   imovel.quadra.id,  "// 7
					+ "   imovel.setorComercial.codigo, "// 8
					+ "   imovel.quadra.numeroQuadra,  "// 9
					+ "   imovel.imovelPerfil.id,  "// 10
					+ "   imovel.ligacaoAguaSituacao.id, "// 11
					+ "   imovel.ligacaoEsgotoSituacao.id, "// 12
					+ "   case when ( "
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "
					+ "     0 "
					+ "   else "
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id "
					+ "   end, "// 13
					+ "   case when ( "
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "
					+ "     0 "
					+ "   else "
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id "
					+ "   end, "// 14
					+ "ligacaoAgua.dataLigacao, "// 15
					+ "ligacaoAgua.dataSupressao, "// 16
					+ "ligacaoAgua.dataCorte, "// 17
					+ "ligacaoAgua.dataReligacao, "// 18
					+ "ligacaoAgua.dataRestabelecimentoAgua, "// 19
					+ "hidrometroInstalacaoHistorico.id "// 20
					+ "  from gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua, "
					+ "   gcom.cadastro.imovel.Imovel imovel "
					+ "   left join imovel.ligacaoEsgoto ligacaoEsgoto "
					+ "   left join ligacaoAgua.hidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
					+ " where imovel.id = ligacaoAgua.id and "
					+ "   imovel.setorComercial.id = :idSetor "
					+ " and ("
					+ "(ligacaoAgua.dataLigacao >= :dataInicial and ligacaoAgua.dataLigacao <= :dataFinal) or "
					+ "(ligacaoAgua.dataSupressao >= :dataInicial and ligacaoAgua.dataSupressao <= :dataFinal) or "
					+ "(ligacaoAgua.dataCorte >= :dataInicial and ligacaoAgua.dataCorte <= :dataFinal) or "
					+ "(ligacaoAgua.dataReligacao >= :dataInicial and ligacaoAgua.dataReligacao <= :dataFinal) or "
					+ "(ligacaoAgua.dataRestabelecimentoAgua >= :dataInicial and ligacaoAgua.dataRestabelecimentoAgua <= :dataFinal))";

			retorno = session.createQuery(hql).setInteger("idSetor", idSetor)
					.setDate("dataInicial", dataInicial).setDate("dataFinal",
							dataFinal).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public void inserirResumoMetas(Integer anoMesReferencia,
			ResumoMetasHelper resMetas) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into micromedicao.un_resumo_metas ("
					+ "  remt_id, " + "  uneg_id, " + "  remt_amreferencia, "
					+ "  greg_id, " + "  loca_id, " + "  loca_cdelo, "
					+ "  stcm_id, " + "  qdra_id, "
					+ "  remt_cdsetorcomercial, " + "  rota_id, "
					+ "  remt_nnquadra, " + "  last_id, " + "  lest_id, "
					+ "  catg_id, " + "  scat_id , " + "  epod_id , "
					+ "  cltp_id , " + "  lapf_id , " + "  remt_qteconomias , "
					+ "  lepf_id , " + "  remt_qtligacoescadastradas , "
					+ "  remt_tmultimaalteracao, "
					+ "  remt_qtligacoescortadas , "
					+ "  remt_qtligacoessuprimidas , "
					+ "  remt_qtligacoesativas , "
					+ "  remt_qtligacoesativasdebito3m , "
					+ "  remt_qtligacoesconsumomedido , "
					+ "  remt_qtligacoesconsumonaomed , "
					+ "  remt_qtligacoesconsumoate5m3 , "
					+ "  remt_qtligacoesconsumomedia , "
					+ "  iper_id , "
					+ "  remt_cdgruposubcat ) "
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_resumo_metas") + ", "
					+ resMetas.getIdUnidadeNegocio() + ", " + anoMesReferencia
					+ ", " + resMetas.getIdGerenciaRegional() + ", "
					+ resMetas.getIdLocalidade() + ", " + resMetas.getIdElo()
					+ ", " + resMetas.getIdSetorComercial() + ", "
					+ resMetas.getIdQuadra() + ", "
					+ resMetas.getCodigoSetorComercial() + ", "
					+ resMetas.getIdRota() + ", " + resMetas.getNumeroQuadra()
					+ ", " + resMetas.getIdLigacaoAguaSituacao() + ", "
					+ resMetas.getIdLigacaoEsgotoSituacao() + ", "
					+ resMetas.getIdCategoria() + ", "
					+ resMetas.getIdSubCategoria() + ", "
					+ resMetas.getIdEsferaPoder() + ", "
					+ resMetas.getIdClienteTipo() + ", "
					+ resMetas.getIdLigacaoAguaPerfil() + ", "
					+ resMetas.getQuantidadeEconomias() + ", "
					+ resMetas.getIdLigacaoEsgotoPerfil() + ", "
					+ resMetas.getQuantidadeLigacoesCadastradas() + ", "
					+ Util.obterSQLDataAtual() + " , " + resMetas.getQuantidadeLigacoesCortadas()
					+ ", " + resMetas.getQuantidadeLigacoesSuprimidas() + ", "
					+ resMetas.getQuantidadeLigacoesAtivas() + ", "
					+ resMetas.getQuantidadeLigacoesAtivasDebito3M() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoMedido() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoNaoMedido() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoAte5M3() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoMedio() + ", "
					+ resMetas.getIdImovelPerfil() + " , "
					+ resMetas.getCodigoGrupoSubcategoria() + ") ";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}

	/**

	 * 

	 * [UC0623] - Gerar Resumo de Metas 2 (CAERN)

	 * 

	 * Seleciona todos os im�veis, com o ano/mes arrecada��o de sistema

	 * parametros de uma determinada ger�ncia regional por imovel

	 * 

	 * @author S�vio Luiz

	 * @date 20/07/2007

	 * 

	 * @param idLocalidade

	 *            id do Setor a ser pesquisado

	 * @return Colecao com os historicos selecionados

	 * @throws ErroRepositorioException

	 */

	public List pesquisarDadosResumoMetasAcumulado(int idSetor)

			throws ErroRepositorioException {



		List retorno = null;



		Session session = HibernateUtil.getSession();



		try {

			String hql = "select "
					+ "   imovel.id, "// 0
					+ "   imovel.localidade.gerenciaRegional.id, "// 1
					+ "   imovel.localidade.unidadeNegocio.id,  "// 2
					+ "   imovel.localidade.id,  "// 3
					+ "   imovel.localidade.localidade.id, "// 4
					+ "   imovel.setorComercial.id,   "// 5
					+ "   imovel.quadra.rota.id,  "// 6
					+ "   imovel.quadra.id,  "// 7
					+ "   imovel.setorComercial.codigo, "// 8
					+ "   imovel.quadra.numeroQuadra,  "// 9
					+ "   imovel.imovelPerfil.id,  "// 10
					+ "   imovel.ligacaoAguaSituacao.id, "// 11
					+ "   imovel.ligacaoEsgotoSituacao.id, "// 12
					+ "   case when ( "
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "
					+ "     0 "
					+ "   else "
					+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id "
					+ "   end, "// 13
					+ "   case when ( "
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "
					+ "     0 "
					+ "   else "
					+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id "
					+ "   end, "// 14		
					+ "   case when ( "
					+ "     ligacaoAgua.dataLigacao is null ) then "
					+ "     ligacaoEsgoto.dataLigacao "
					+ "   else "
					+ "     ligacaoAgua.dataLigacao "
					+ "   end, "// 15
					+ "ligacaoAgua.dataSupressao, "// 16
					+ "ligacaoAgua.dataCorte, "// 17
					+ "ligacaoAgua.dataReligacao, "// 18
					+ "ligacaoAgua.dataRestabelecimentoAgua, "// 19				
					+ "   case when ( "
					+ "     hidrometroInstalacaoHistorico.id is null ) then "
					+ "     hidrometroInstalacaoHistoricoImov.id "
					+ "   else "
					+ "    hidrometroInstalacaoHistorico.id "
					+ "   end "// 20
					+ "  from gcom.cadastro.imovel.Imovel imovel "
					+ " left join imovel.ligacaoEsgoto ligacaoEsgoto "				
					+ " left join imovel.ligacaoAgua ligacaoAgua "
					+ " left join ligacaoAgua.hidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "				
					+ " left join imovel.hidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImov "
					+ " where imovel.setorComercial.id = :idSetor and imovel.indicadorExclusao <> :indicadorExclusao";

			retorno = session.createQuery(hql).setInteger("idSetor", idSetor)

					.setShort("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO)

					.list();



		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void inserirResumoMetasAcumulado(Integer anoMesReferencia,
			ResumoMetasHelper resMetas) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection jdbcCon = session.connection();
		PreparedStatement st = null;

		try {

			

			insert = "insert into micromedicao.un_res_mt_acum ("
					+ "  rema_id, " + "  uneg_id, " + "  rema_amreferencia, "
					+ "  greg_id, " + "  loca_id, " + "  loca_cdelo, "
					+ "  stcm_id, " + "  qdra_id, "
					+ "  rema_cdsetorcomercial, " + "  rota_id, "
					+ "  rema_nnquadra, " + "  last_id, " + "  lest_id, "
					+ "  catg_id, " + "  scat_id , " + "  epod_id , "
					+ "  cltp_id , " + "  lapf_id , " + "  rema_qteconomias , "
					+ "  lepf_id , " + "  rema_qtligacoescadastradas , "
					+ "  rema_tmultimaalteracao, "
					+ "  rema_qtligacoescortadas , "
					+ "  rema_qtligacoessuprimidas , "
					+ "  rema_qtligacoesativas , "
					+ "  rema_qtligacoesativasdebito3M , "
					+ "  rema_qtligacoesconsumomedido , "
					+ "  rema_qtligacoesconsumonaomed , "
					+ "  rema_qtligacoesconsumoate5m3 , "
					+ "  rema_qtligacoesconsumomedia , " + "  iper_id ," 
					+ "  rema_cdgruposubcat ) "
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_res_mt_acum") + ", "
					+ resMetas.getIdUnidadeNegocio() + ", " + anoMesReferencia
					+ ", " + resMetas.getIdGerenciaRegional() + ", "
					+ resMetas.getIdLocalidade() + ", " + resMetas.getIdElo()
					+ ", " + resMetas.getIdSetorComercial() + ", "
					+ resMetas.getIdQuadra() + ", "
					+ resMetas.getCodigoSetorComercial() + ", "
					+ resMetas.getIdRota() + ", " + resMetas.getNumeroQuadra()
					+ ", " + resMetas.getIdLigacaoAguaSituacao() + ", "
					+ resMetas.getIdLigacaoEsgotoSituacao() + ", "
					+ resMetas.getIdCategoria() + ", "
					+ resMetas.getIdSubCategoria() + ", "
					+ resMetas.getIdEsferaPoder() + ", "
					+ resMetas.getIdClienteTipo() + ", "
					+ resMetas.getIdLigacaoAguaPerfil() + ", "
					+ resMetas.getQuantidadeEconomias() + ", "
					+ resMetas.getIdLigacaoEsgotoPerfil() + ", "
					+ resMetas.getQuantidadeLigacoesCadastradas() + ", "
					+ " ?  , " 
					+ resMetas.getQuantidadeLigacoesCortadas()
					+ ", " + resMetas.getQuantidadeLigacoesSuprimidas() + ", "
					+ resMetas.getQuantidadeLigacoesAtivas() + ", "
					+ resMetas.getQuantidadeLigacoesAtivasDebito3M() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoMedido() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoNaoMedido() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoAte5M3() + ", "
					+ resMetas.getQuantidadeLigacoesConsumoMedio() + ", "
					+ resMetas.getIdImovelPerfil() + ","
					+ resMetas.getCodigoGrupoSubcategoria() + ") ";
			
			st = jdbcCon.prepareStatement(insert);

			st.setTimestamp(1, Util.getSQLTimesTemp(new Date()));
		
			// executa o update
			st.executeUpdate();
			session.flush();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	public Integer getConsumoMinimoImovelCategoria( int idImovel, int idCategoria ) 
	  throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = 
				" select " +
				"   max( ctc.numeroConsumoMinimo ) " +
				" from " +
				"   Imovel imo " +
				"   inner join imo.consumoTarifa ct " +
				"   inner join ct.consumoTarifaVigencias ctv with( ctv.dataVigencia <= (select sp.anoMesFaturamento from SistemaParametro sp) ) " +
				"   inner join ctv.consumoTarifaCategorias ctc " +
				" where " +
				"   imo.id = :idImovel and ctc.categoria.id = :idCategoria ";

			retorno = 
				(Integer) session.createQuery(hql).
					setInteger("idImovel", idImovel).
					setInteger("idCategoria", idCategoria).
					uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;		
	}	
	
	/**
	 * Seleciona todos os historicos de coleta de uma determinada localidade
	 * por imovel
	 * 
	 * @author Marcio Roberto, Ivan S鲧io
	 * @date 20/09/2007, 30/07/2008
	 * @alteracao: Ivan S鲧io - 30/07/2008 - Adicionado o AnoMesFaturamento para agilizar o processo.
	 * 			   Ivan S鲧io - 22/09/2008 - CRC185 - Considerar somente os im?s que foram faturados.
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 *            anoMesFaturamento da tabela sistema parametros
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosColetaEsgoto(int idSetor, Integer anoMesFaturamento)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = 
				"select \n" +
				// 0 - Imovel
				"  imo.id, \n" + 
				// 1 - Gerencia Regional
				"  case when ( grCon is not null ) then \n" +
				"    grCon.id \n" +
				"  else \n" +
				"    grImo.id \n" +
				"  end, \n" +
				// 2 - Unidade de Negocio
				"  case when ( unCon is not null ) then \n" +
				"    unCon.id \n" +
				"  else \n" +
				"    unImo.id \n" +
				"  end, \n" +
				// 3 - Localidade 
				"  case when ( locCon is not null ) then \n" +
				"    locCon.id \n" +
				"  else \n" +
				"    locImo.id \n" +
				"  end, \n" +				
				// 4 - Elo
				"  case when ( eloCon is not null ) then \n" +
				"    eloCon.id \n" +
				"  else \n" +
				"    eloImo.id \n" +
				"  end, \n" +
				// 5 - Setor comercial
				"  case when ( stCon is not null ) then \n" +
				"    stCon.id \n" +
				"  else \n" +
				"    stImo.id \n" +
				"  end, \n" +
				// 6 - Rota
				"  case when ( rotCon is not null ) then \n" +
				"    rotCon.id \n" +
				"  else \n" +
				"    rotImo.id \n" +
				"  end, \n" +
				// 7 - Quadra
				"  case when ( quaCon is not null ) then \n" +
				"    quaCon.id \n" +
				"  else \n" +
				"    quaImo.id \n" +
				"  end, \n" +
				// 8 - Codigo do Setor Comercial
				"  case when ( stCon is not null ) then \n" +
				"    stCon.codigo \n" +
				"  else \n" +
				"    stImo.codigo \n" +
				"  end, \n" +
				// 9 - Numero da Quadra
				"  case when ( quaCon is not null ) then \n" +
				"    quaCon.numeroQuadra \n" +
				"  else \n" +
				"    quaImo.numeroQuadra \n" +
				"  end, \n" +
				// 10 - Imovel Perfil
				"  case when ( ipCon is not null ) then \n" +
				"    ipCon.id \n" +
				"  else \n" +
				"    ipImo.id \n" +
				"  end, \n" +
				// 11 - Ligacao Agua Situa磯
				"  case when ( lasCon is not null ) then \n" +
				"    lasCon.id \n" +
				"  else \n" +
				"    lasImo.id \n" +
				"  end, \n" +
				// 12 - Ligacao Esgoto Situacao
				"  case when ( lesCon is not null ) then \n" +
				"    lesCon.id \n" +
				"  else \n" +
				"    lesImo.id \n" +
				"  end, \n" +
				// 13 - Ligacao Agua Perfil
				"  coalesce( lapImo.id, 0 ), \n" +
				// 14 - Ligacao Esgoto Perfil
				"  coalesce( lepImo.id, 0 ), \n" +
				// 15 - Consumo Tipo
				"  ct.id, \n" +
				// 16 - Numero de Consumo Fatuarado no mes
				"  coalesce( ch.numeroConsumoFaturadoMes, 0), \n" +
				// 17 - Referencia do faturamento
				"  coalesce( ch.referenciaFaturamento, 0 ), \n" +
				// 18 - Indicador de consumo m?mo
				"  case when ( leImo.consumoMinimo > 0 ) then \n" +
				"    1 \n" +
				"  else \n" +
				"    2 \n" +
				"  end, \n" +
				// 19 - Percentual da liga磯 de esgoto
				"  case when ( leImo.percentual > 0 ) then \n" +
				"    0.0 \n" +
				"  else \n" +
				"    coalesce( leImo.percentual, 0.0 ) \n" +
				"  end, \n" +
				// 20 - Indicador de hidrometro instalado
				"  case when ( imo.hidrometroInstalacaoHistorico is not null ) then \n" +
				"    1 \n" +
				"  else \n" +
				"    2 \n" +
				"  end, \n" +
				// 21 - Percentual de coleta
				"  coalesce( ch.percentualColeta, 0.0 ), \n" +
				// 22 - C?o da conta
				"  coalesce( con.id, 0 ), \n" +
				// 23 - Numero do consumo de esgoto
				"  coalesce( con.consumoEsgoto, 0 ), \n" +
				//24 - Rota
				"  case when ( rotCon is not null ) then \n" +
				"    rotCon.codigo \n" +
				"  else \n" +
				"    rotImo.codigo \n" +
				"  end, \n" +
				// 25 Indicador Faturamento
				"  ch.indicadorFaturamento, \n" +
				//26 Situacao de Faturamento
				"  ch.faturamentoSituacaoTipo.id \n" +
				"from \n" +
				//"  SistemaParametro sp, \n" +
				// Joins de Consumo Historico
				"  ConsumoHistorico ch \n" +
				"  inner join ch.imovel imo \n" +
				"  inner join imo.localidade locImo \n" +
				"  inner join locImo.gerenciaRegional grImo \n" +
				"  inner join locImo.unidadeNegocio unImo \n" +
				"  inner join locImo.localidade eloImo \n" +
				"  inner join imo.setorComercial stImo \n" +
				"  inner join imo.quadra quaImo \n" +
				"  inner join quaImo.rota rotImo \n" +
				"  inner join imo.imovelPerfil ipImo \n" +
				"  left  join imo.ligacaoAguaSituacao lasImo \n" +
				"  left  join imo.ligacaoEsgotoSituacao lesImo \n" +
				"  left  join imo.ligacaoAgua laImo \n" +
				"  left  join imo.ligacaoEsgoto leImo \n" +
				"  left  join laImo.ligacaoAguaPerfil lapImo \n" +
				"  left  join leImo.ligacaoEsgotoPerfil lepImo \n" +
				"  left  join ch.consumoTipo ct \n" +
				"  left  join ch.ligacaoTipo lt \n" +				
				// Joins de Conta
				"  left join imo.contas con \n" +
				"	   with ( con.referencia = :anoMesFaturamento and \n" +
				"			    ( con.debitoCreditoSituacaoAtual = 0 or con.debitoCreditoSituacaoAnterior = 0 ) \n" +
				"			) \n" +
				//"    with ( con.referencia = ( select sis.anoMesFaturamento from SistemaParametro sis ) and \n" +
				//"           ( con.debitoCreditoSituacaoAtual = 0 or con.debitoCreditoSituacaoAnterior = 0 ) \n" +
				//"         ) \n" +
				"  left  join con.localidade locCon \n" +
				"  left  join locCon.gerenciaRegional grCon \n" +
				"  left  join locCon.unidadeNegocio unCon \n" +
				"  left  join locCon.localidade eloCon \n" +
				"  left  join con.quadraConta quaCon \n" +
				"  left  join quaCon.setorComercial stCon \n" +
				"  left  join quaCon.rota rotCon \n" +
				"  left  join con.imovelPerfil ipCon \n" +
				"  left  join con.ligacaoAguaSituacao lasCon \n" +
				"  left  join con.ligacaoEsgotoSituacao lesCon " +
				"where \n" +
				"  imo.setorComercial.id = :idSetor and \n" + 
				"  lt.id = :ligacaoTipo and \n" + 
				"  ch.indicadorImovelCondominio <> 1 and \n" +
				"  ch.referenciaFaturamento = :anoMesFaturamento and " +
				
				/**TODO:COSANPA
				 * Data:13/10/2011
				 * Autor Adriana Muniz
				 * 
				 * Limitar que apenas imoveis n㯠excluidos sejam retornados na consulta
				 * */
				"  imo.indicadorExclusao = 2 "
//				"  ch.indicadorFaturamento = 1 "
				;
			    //"  ch.referenciaFaturamento = sp.anoMesFaturamento ";	
			
			// HQL ANTIGO, QUE NÏ LEVA A CONTA EM CONSIDERAǃO PARA SELEǃO DOS DADOS			
//			String hq2 = " select " +
//				 "    imovel.id, " +                                    // Imovel
//				 "    locImo.gerenciaRegional.id, " +                    // Gerencia Regional
//				 "    locImo.unidadeNegocio.id, " +                      // Unidade de negocio
//				 "    locImo.id, " +                                     // Localidade
//				 "    locImo.localidade.id, " +                          // Elo
//				 "    setComImo.id, " +                                  // Id Setor Comercial
//				 "    quaImo.rota.id, " +                                // Rota
//				 "    quaImo.id, " +                                     // Quadra
//				 "    setComImo.codigo, " +                              // Codigo do Setor Comercial
//				 "    quaImo.numeroQuadra, " +                           // Numero Quadra
//				 "    imovel.imovelPerfil.id, " +                        // Perfil do Imovel
//				 "    imovel.ligacaoAguaSituacao.id, " +                 // Situacao Ligacao Agua
//				 "    imovel.ligacaoEsgotoSituacao.id, " +               // Situacao Ligacao Esgoto
//				 "  case when ( " +                                      // 13 ]
//				 "    ligAguaImo.ligacaoAguaPerfil.id is null ) then " + // 13 |
//				 "    0 " +                                              // 13 |
//				 "  else " +                                             // 13 +- Perfil da ligacao de agua
//				 "    ligAguaImo.ligacaoAguaPerfil.id " +                // 13 |
//				 "  end, " +                                             // 13 ]
//					
//				 "  case when ( " +                                      // 14 ]
//				 "    ligEsgImo.ligacaoEsgotoPerfil.id is null ) then " +// 14 |
//				 "    0 " +                                              // 14 +- Perfil da ligacao de esgoto
//				 "  else " +                                             // 14 |
//				 "    ligEsgImo.ligacaoEsgotoPerfil.id " +               // 14 |
//				 "  end, " +                                             // 14 |
//				 "  consumoHistorico.consumoTipo.id, " +                 // 15 |- Tipo Consumo
//				 "  consumoHistorico.numeroConsumoFaturadoMes, " +       // 16 |- Volume Esgoto
//				 "  consumoHistorico.referenciaFaturamento, " +          // 17 |- AnoMes 
//				 "  case when ( ligEsgImo.consumoMinimo > 0 ) then " +     // 18 |
//				 "     1" + "   else" + "     2" + "   end, " +            // 18 +- Consumo Minimo
//				 "  case when ( ligEsgImo.percentual is null ) then " +      // 19 |
//				 "  0.0 " + " else " + " ligEsgImo.percentual " + " end, " +  // 19 +- Percentual Esgoto
//				 "   case when ( " +                                                   // 20 ]
//				 "     imovel.hidrometroInstalacaoHistorico is not null ) then " +     // 20 |
//				 "     1 " +                                                           // 20 |
//				 "   else " +                                                          // 20 +- Existencia de Hidrometro no Po�o
//				 "     2 " +                                                           // 20 |  
//				 "   end, " +                                                          // 20 ]
//				 "   consumoHistorico.percentualColeta " +							  // 21 +- Percentual Coleta
//				 " from " + 
//				 "  gcom.micromedicao.consumo.ConsumoHistorico as consumoHistorico " + 				
//				 "  inner join consumoHistorico.imovel as imovel " +				
//				 "  inner join imovel.localidade as locImo " +
//				 "  inner join imovel.quadra as quaImo " +
//				 "  inner join imovel.setorComercial as setComImo " +
//				 "  left join imovel.ligacaoAgua as ligAguaImo " +
//				 "  left join imovel.ligacaoEsgoto as ligEsgImo "+
//				 "  left join imovel.contas as conta with ( conta.referencia = ( select " +  
//				 "    										      sistemaParametro.anoMesFaturamento " +  
//				 "    										    from " + 
//			 	 "      										  gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro  ) and ( conta.debitoCreditoSituacaoAtual = 0 or conta.debitoCreditoSituacaoAnterior = 0 ) ) " +
//				 "  left join conta.ligacaoAguaSituacao as ligAguaSitCon " +
//				 "  left join conta.ligacaoEsgotoSituacao as ligEsgSitCon " +
//				 " where " + 
//				 "  imovel.setorComercial.id = :idSetor and " + 
//				 "  consumoHistorico.ligacaoTipo.id = :ligacaoTipo and " + 
//				 "  consumoHistorico.indicadorImovelCondominio <> 1 and " +
//			     "  consumoHistorico.referenciaFaturamento = (  select " +  
//				 "    										      sistemaParametro.anoMesFaturamento " +  
//				 "    										    from " + 
//			 	 "      										  gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro  )";
				
			retorno = session.createQuery(hql)
				.setInteger("anoMesFaturamento", anoMesFaturamento)
				.setInteger("idSetor", idSetor)
				.setInteger("ligacaoTipo", LigacaoTipo.LIGACAO_ESGOTO)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}

	/**
	 * Insere resumo Coleta Esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 20/09/2007
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoColetaEsgoto(Integer anoMesReferencia,
			ResumoColetaEsgotoHelper resConsumo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into micromedicao.un_resumo_coleta_esgoto ("
					+ "  rece_id, "                // 01
					+ "  uneg_id, "                // 02
					+ "  rece_amreferencia, "      // 03
					+ "  greg_id, "                // 04
					+ "  loca_id, "                // 05
					+ "  loca_cdelo, "             // 06
					+ "  stcm_id, "                // 07
					+ "  qdra_id, "                // 08
					+ "  rece_cdsetorcomercial, "  // 09
					+ "  rota_id, "                // 10
					+ "  rece_nnquadra, "          // 11
					+ "  last_id, "                // 12
					+ "  lest_id, "                // 13
					+ "  catg_id, "                // 14
					+ "  scat_id, "                // 15
					+ "  epod_id, "                // 16
					+ "  cltp_id, "                // 17
					+ "  lapf_id, "                // 18
					+ "  rece_qteconomias , "      // 19
					+ "  lepf_id , "               // 20
					+ "  rece_voesgoto   , "       // 21
					+ "  rece_tmultimaalteracao, " // 22
					+ "  rece_icvlexcedente, "     // 23
					+ "  rece_qtligacoes , "       // 24
					+ "  iper_id , "               // 25
					+ "  cstp_id ,"                // 26
					+ "  rece_voexcedente, "       // 27
					+ "  rece_icmedidoagua, "      // 28
					+ "  rece_icpoco, "            // 29 
					+ "  rece_icmedidofontealternativa, "  // 30
					+ "  rece_pcesgoto, "          // 31
					+ "  rece_pccoleta, "          // 32 
					+ "  rece_icvlminimoesgoto, "  // 33
					+ "  rece_vofaturado,"		   // 34
					+ "  rece_ichidrometro,"       // 35
					+ "  rece_cdrota," // 36
					+ "  rece_icfaturamento, " //37
					+ "  ftst_id, " //38
					+ "  ftsm_id " //39
					+ "  )"			   
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_resumo_coleta_esgoto") + ", "
					+ resConsumo.getIdUnidadeNegocio() + ", "                    // 02
					+ anoMesReferencia + ", "                                    // 03
					+ resConsumo.getIdGerenciaRegional() + ", "                  // 04
					+ resConsumo.getIdLocalidade() + ", "                        // 05
					+ resConsumo.getIdElo() + ", "                               // 06
					+ resConsumo.getIdSetorComercial() + ", "                    // 07
					+ resConsumo.getIdQuadra() + ", "                            // 08
					+ resConsumo.getCodigoSetorComercial() + ", "                // 09
					+ resConsumo.getIdRota() + ", "                              // 10
					+ resConsumo.getNumeroQuadra() + ", "                        // 11
					+ resConsumo.getIdLigacaoAguaSituacao() + ", "               // 12
					+ resConsumo.getIdLigacaoEsgotoSituacao() + ", "             // 13
					+ resConsumo.getIdCategoria() + ", "                         // 14
					+ resConsumo.getIdSubCategoria() + ", "                      // 15
					+ resConsumo.getIdEsferaPoder() + ", "                       // 16
					+ resConsumo.getIdClienteTipo() + ", "                       // 17
					+ resConsumo.getIdLigacaoAguaPerfil() + ", "                 // 18
					+ resConsumo.getQuantidadeEconomias() + ", "                 // 19
					+ resConsumo.getIdLigacaoEsgotoPerfil() + ", "               // 20
					+ resConsumo.getQuantidadeColetaEsgoto() + ", "              // 21
					+ Util.obterSQLDataAtual() + " , "                                                 // 22   
					+ resConsumo.getIndicadorColetaEsgotoExcedente() + ", "      // 23
					+ resConsumo.getQuantidadeLigacoes() + ", "                  // 24
					+ resConsumo.getIdImovelPerfil() + ", "                      // 25
					+ resConsumo.getIdConsumoTipo() + ", "                       // 26      
					+ resConsumo.getVolumeExcedente() + ", "                     // 27
					// Ic medido de Agua deve ser zero, pois esse informa磯 jᠥstፊ					// gravada no ichidrometro
					+ "0,"														 // 28
					+ resConsumo.getPoco() + ", "                                // 29
					+ resConsumo.getMedidoFonteAlternativa() + ", "              // 30
					+ resConsumo.getPcEsgoto() + ", "                            // 31
					+ resConsumo.getPcColeta() + ", "                            // 32
					+ resConsumo.getIcVlMinimoEsgoto()+ ","                      // 33
					+ resConsumo.getVoFaturado() +", "                           // 34
				    + resConsumo.getIndicadorHidrometro() + ", "                 // 35
				    + resConsumo.getCodigoRota() + ", "							 // 36
				    + resConsumo.getIndicadorFaturamento() + ", "				 // 37
				    + resConsumo.getFaturamentoSituacao() + ", "				 // 38
				    + resConsumo.getMotivoFaturamentoSituacao()					 // 39
				    + ")";							 

			stmt.executeUpdate(insert);

			System.out.print("1 - INSERINDO RESUMO COLETA ESGOTO ");
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate coleta esgoto ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert coleta esgoto ");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}
	
	/**
	 * Deleta resumo Coleta Esgoto antes de gerar
	 * 
	 * @author Marcio Roberto
	 * @date 27/09/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public void deletaResumoColetaEsgoto(Integer anoMesReferencia) throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;
		Session session = HibernateUtil.getSessionGerencial();
		String delete; 
		try {
			con = session.connection();
			stmt = con.createStatement();
			delete = "delete from micromedicao.un_resumo_coleta_esgoto where rece_amreferencia = "+anoMesReferencia; 
			 		   
					  
					 
			stmt.executeUpdate(delete);
			System.out.print("1 - DELETANDO RESUMO COLETA ESGOTO ");
		}catch(SQLException e) {
			throw new ErroRepositorioException(e, "Erro ao deletar resumo coleta esgoto");
		}
	}
	
	/**
	 * Seleciona o percentual de consumo de esgoto
	 * 
	 * @author Marcio Roberto
	 * @date 02/10/2007
	 * 
	 * @param idImovel
	 *            id do imovel que ter� seu volume fixo verificado
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public float percentualEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			// + "  ch.percentualColeta "
			consulta = "select "
		            +" case when ( "
				 	+"	 ch.percentualColeta is null ) then "
				 	+"     0.0 "
				    +"   else "
				    +"     ch.percentualColeta "
				    +"   end "				
					+" from "
					+"  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+" where "
					+"  ch.imovel.id = :idImovel and "
					+"  ch.ligacaoTipo.id = 2 and "
					+"  ch.referenciaFaturamento = ( select "
					+"  									anoMesFaturamento "
					+"  								from "
					+"  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";

			return ( (Float) session.createQuery(consulta).setInteger("idImovel",	idImovel).uniqueResult() ).floatValue();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada  
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
	}
    
    public Categoria obterPrincipalCategoriaConta( int idConta ) 
      throws ErroRepositorioException {
        Categoria retorno = null;

        Session session = HibernateUtil.getSession();

        try {
            String hql =
            	" select " +
            	"   cc.comp_id.categoria.id as id " +
            	" from " +
            	"   ContaCategoria cc " +
            	" where " +
            	"   cc.comp_id.conta.id = :idConta " +
            	" group by " +
            	"   cc.comp_id.categoria.id " +
            	" order by sum( cc.quantidadeEconomia ) desc ";                

            Integer idCategoria = (Integer)session.createQuery(hql).setInteger("idConta", idConta)
            		.setMaxResults( 1 ).uniqueResult();            
            
            retorno = new Categoria();
            
            retorno.setId( idCategoria );
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
        return retorno;     
    }
    
	/**
	 * Seleciona o maior m곯ano de referꮣia da tabela un_resumo_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Comercializa磯
	 * 
	 * @author Rafael Corrꡍ
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoLigacaoEconomia()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resLigEcon.referencia) "
					+ " FROM "
					+ " gcom.gerencial.cadastro.UnResumoLigacaoEconomia resLigEcon ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce磯 para a pr?a camada  
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess㯍
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * Seleciona o maior m곯ano de referꮣia da tabela un_resumo_indicador_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Comercializa磯
	 * 
	 * @author Rafael Corrꡍ
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadorLigacaoEconomia()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionGerencial();
		String consulta;
		Integer retorno = null;

		try {
			// + "  ch.percentualColeta "
			consulta = "SELECT max(resIndLigEcon.anoMesReferencia) "
					+ " FROM "
					+ " gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomia resIndLigEcon ";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce磯 para a pr?a camada  
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess㯍
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	/**
	 * Atualiza os dados na tabela un_resumo_indicador_ligacao_economia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Comercializa磯
	 * 
	 * @author Rafael Corrꡍ
	 * @date 06/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadorLigacaoEconomia(
			Integer anoMesReferencia) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {
			
			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO cadastro.un_resi_lig_eco "
					+ " SELECT * "
					+ " FROM cadastro.vw_un_resi_lig_eco ";
	
			if (anoMesReferencia != null) {
				consulta = consulta
						+ " WHERE rele_amreferencia = " + anoMesReferencia;
			}
			
			System.out.println("inicio Batch atualizarDadosResumoIndicadorLigacaoEconomia:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorLigacaoEconomia:" + new Date());

		} catch (HibernateException e) {
			// levanta a exce磯 para a pr?a camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sess㯍
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão");
			}
		}

	}
    
   /**
    * Verifica se ja foi gerado o ano mes de referencia de faturamento
    * para o resumo de consumo de agua
    * 
    * @author Bruno Leonardo Rodrigues Barros 
    * @return
    * @throws ErroRepositorioException
    public boolean resumoConsumoAguaGerado( int anomesreferencia, int idSetor ) throws ErroRepositorioException{
       Session session = HibernateUtil.getSessionGerencial();
       Object quantidade = null;

       try {
           String hql =
        	 " select " +
        	 "   count(*) " +
        	 " from " +
        	 "   UnResumoConsumoAgua r" +
        	 " where " +
        	 " r.referencia = :anomesreferencia and r.gerSetorComercial.id = :id";             

           quantidade = session.createQuery(hql)
           		.setInteger( "anomesreferencia", anomesreferencia )
           		.setInteger( "id", idSetor ).uniqueResult();
       } catch (HibernateException e) {
           throw new ErroRepositorioException(e, "Erro no Hibernate");
       } finally {
           HibernateUtil.closeSession(session);
       }
       
       return ( quantidade != null && 
   		        quantidade instanceof Integer &&
   		        ( ( Integer ) quantidade ) > 0 );       
   }    
    
    /**
     * Apaga os dados do consumo de agua gerado para o mes de faturamento
     * 
     * @author Bruno Leonardo Rodrigues Barros 
     * @throws ErroRepositorioException
     public void excluirResumoConsumoAguaJaGerado ( int anomesreferencia, int idSetor ) throws ErroRepositorioException{
 		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
            String delete =
         	 " delete from micromedicao.un_resumo_consumo_agua r " +
         	 " where " +
         	 " r.reca_amreferencia = " + anomesreferencia + " and r.stcm_id = " + idSetor;               

			stmt. executeUpdate(delete);
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no delete");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
    }    

     /**
      * Apaga os dados do resumo gerado para o anomes e o setor informado
      * 
      * @author Bruno Leonardo Rodrigues Barros/ Roberto Barbalho 
      * @throws ErroRepositorioException
      */
      
      public void excluirResumo ( 
    		  int anomesreferencia , 
    		  String resumoGerencial, 
    		  String resumoCampoAnoMes, 
    		  int idSetor, 
    		  boolean excluirComercial ) throws ErroRepositorioException{
    	  
    	Session session;    	  
    	
    	String setor = "" ;
 
        if ( excluirComercial ){
        	session = HibernateUtil.getSession();
        	setor = " and setorComercial in ( " + idSetor + " , null ) ";
        } else {
        	session = HibernateUtil.getSessionGerencial();
        	setor = " and gerSetorComercial in ( " + idSetor + " , null ) "; 
        }

 		//Connection con = null;
 		//Statement stmt = null;

 		try {

 			//con = session.connection();
 			//stmt = con.createStatement();

 			String delete = 
          	 " delete from " + resumoGerencial + 
          	 " where " + resumoCampoAnoMes + " = " + anomesreferencia + 
             setor; 
             
 			//stmt. executeUpdate(delete);
 			
 			session.createQuery(delete).executeUpdate();
 			
 		} catch (HibernateException e) {
 			// levanta a exce��o para a pr�xima camada
 			throw new ErroRepositorioException(e, "Erro no Hibernate");
 		/*} catch (SQLException e) {
 			throw new ErroRepositorioException(e, "Erro no delete");*/
 		} finally {
 			// fecha a sess?o
 			HibernateUtil.closeSession(session);

 			/*try {
 				stmt.close();
 				con.close();
 			} catch (SQLException e) {
 				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
 			}*/
 		}
     }
      
      public void excluirResumoGerencial ( 
    		  int anomesreferencia , 
    		  String resumoGerencial, 
    		  String resumoCampoAnoMes,
    		  String resumoCampoUnidadeProcessamento,
    		  int idUnidadeProcessamento ) throws ErroRepositorioException{
    	  
    	Session session;    	  
    	
    	String unidadeProcessamento = "" ;
 
    	session = HibernateUtil.getSessionGerencial();
    	unidadeProcessamento = " and " + resumoCampoUnidadeProcessamento + " in ( " + idUnidadeProcessamento + " , null ) ";

 		try {

 			String delete = 
          	 " delete from " + resumoGerencial + 
          	 " where " + resumoCampoAnoMes + " = " + anomesreferencia + 
          	unidadeProcessamento;
             
 			session.createQuery(delete).executeUpdate();
 			
 		} catch (HibernateException e) {
 			// levanta a excessao para a pr?a camada
 			throw new ErroRepositorioException(e, "Erro no Hibernate");
 		} finally {
 			// fecha a sess㯍
 			HibernateUtil.closeSession(session);
 		}
     }      
      
     /**
      * Apaga os dados do resumo gerado para o anomes e o setor informado
      * 
      * @author Bruno Leonardo Rodrigues Barros/ Roberto Barbalho 
      * @throws ErroRepositorioException
      */  
      public void excluirResumoSQL ( 
     		  int anomesreferencia , 
     		  String resumoGerencial, 
     		  String resumoCampoAnoMes, 
     		  int idSetor, 
     		  boolean excluirComercial ) throws ErroRepositorioException{
     	  
     	Session session;    	  
     	  
         if ( excluirComercial ){
         	session = HibernateUtil.getSession();
         } else {
         	session = HibernateUtil.getSessionGerencial();
         }

  		Connection con = null;
  		Statement stmt = null;

  		try {

  			con = session.connection();
  			stmt = con.createStatement();

  			String delete = 
           	 " delete from " + resumoGerencial + 
           	 " where " + resumoCampoAnoMes + " = " + anomesreferencia + 
             " and stcm_id = " + idSetor +
			 " or stcm_id is null"; 
              
  			stmt. executeUpdate(delete);
  			
  			
  		} catch (HibernateException e) {
  			// levanta a exce??o para a pr?xima camada
  			throw new ErroRepositorioException(e, "Erro no Hibernate");
  		} catch (SQLException e) {
  			throw new ErroRepositorioException(e, "Erro no delete");
  		} finally {
  			// fecha a sess?o
  			HibernateUtil.closeSession(session);

  			try {
  				stmt.close();
  				con.close();
  			} catch (SQLException e) {
  				throw new ErroRepositorioException(e, "Erro ao fechar conex?es");
  			}
  		}
      }
 
     
/**
 * Apaga os dados do resumo gerado para o anomes e um campo especifico informado
 * 
 * @author Bruno Leonardo Rodrigues Barros/ Roberto Barbalho 
 * @throws ErroRepositorioException
 */
 
 public void excluirResumoGerencialC ( int anomesreferencia , String resumoGerencial, String resumoCampoAnoMes, String campoEspecifico, int idcampoespecifico) throws ErroRepositorioException{
		Session session = HibernateUtil.getSessionGerencial();

	Connection con = null;
	Statement stmt = null;

	try {

		con = session.connection();
		stmt = con.createStatement();

		String delete = 
     	 " delete from " + resumoGerencial + 
     	 " where " + resumoCampoAnoMes + " = " + anomesreferencia +
     	 " and " + campoEspecifico +" = " + idcampoespecifico ;
        
		stmt. executeUpdate(delete);
	} catch (HibernateException e) {
		// levanta a exce��o para a pr�xima camada
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} catch (SQLException e) {
		throw new ErroRepositorioException(e, "Erro no delete");
	} finally {
		// fecha a sess�o
		HibernateUtil.closeSession(session);

		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
		}
	}
}
 	
 	/***
 	 * [UC0573] - Gerar Resumo Coleta Esgoto
	 * 
	 * @author Ivan Sergio
	 * @date 25/08/2008
 	 * 
 	 * @param idConta
 	 * @return
 	 * @throws ErroRepositorioException
 	 */
 	public Integer pesquisarConsumoMinimoEsgotoConta(Integer idConta) throws ErroRepositorioException {
 		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;
		Integer retorno = null;

		try {
			consulta = 
				"SELECT sum(cc.ctcg_nnconsumominimoesgoto) as consumoMinimoEsgoto " +
				"FROM faturamento.conta_categoria cc " +
				"WHERE cc.cnta_id = :idConta ";

			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("consumoMinimoEsgoto", Hibernate.INTEGER)
				.setInteger("idConta", idConta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
 	}
 	
 	/**
	 * M鴯do que retona uma lista de objeto que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 29/04/2010
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoLigacaoEconomiasPorAno(int idSetor, int numeroIndice,
			int quantidadeRegistros)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
				+ "   imovel.id," //0
				+ "   imovel.localidade.gerenciaRegional.id," //1
				+ "   imovel.localidade.unidadeNegocio.id," //2
				+ "   imovel.localidade.id," //3
				+ "   imovel.localidade.localidade.id," //4
				+ "   imovel.setorComercial.id," //5
				+ "   imovel.setorComercial.codigo," //6
				+ "   imovel.imovelPerfil.id," //7
				+ "   imovel.ligacaoAguaSituacao.id," //8
				+ "   imovel.ligacaoEsgotoSituacao.id," //9
				+ "   case when (" //10
				+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id is null ) then"
				+ "     0"
				+ "   else"
				+ "     imovel.ligacaoAgua.ligacaoAguaPerfil.id"
				+ "   end,"
				+ "   case when (" //11
				+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then"
				+ "     0"
				+ "   else"
				+ "     imovel.ligacaoEsgoto.ligacaoEsgotoPerfil.id"
				+ "   end,"
				+ "   case when (" //12
				+ "       ( imovel.ligacaoAguaSituacao.id in (:ligacaoAguaSituacaoLigado, :ligacaoAguaSituacaoCortado, :ligacaoAguaSituacaoLigadoEmAnalise) and"
				+ "         imovel.ligacaoAgua.hidrometroInstalacaoHistorico is not null ) or"
				+ "       ( imovel.ligacaoEsgotoSituacao.id = :ligacaoEsgotoSituacaoLigado and"
				+ "         imovel.hidrometroInstalacaoHistorico is not null ) ) then"
				+ "     1"
				+ "   else"
				+ "     2"
				+ "   end,"
				+ "   case when (" //13
				+ "     imovel.hidrometroInstalacaoHistorico is not null ) then"
				+ "     1"
				+ "   else"
				+ "     2"
				+ "   end,"
				+ "   case when ( ligacaoAgua.numeroConsumoMinimoAgua > 0 ) then" //14
				+ "     1" + "   else" + "     2" + "   end,"
				+ "   case when ( ligacaoEsgoto.consumoMinimo > 0 ) then" //15
				+ "     1" + "   else" + "     2" + "   end,"
				+ "   case when ( imovel.pocoTipo is not null and imovel.pocoTipo > 0 ) then" //16
				+ "     1" + "   else" + "     2" + "   end,"
				+ "   1 as qtdligacao," //17
				+ "   imovel.quantidadeEconomias, " //18
				+ "   imovel.consumoTarifa.id, " //19
				+ "	  imovel.indicadorImovelCondominio, " //20
				+ "   ligacaoAgua.dataLigacao, " //21
				+ "   ligacaoEsgoto.dataLigacao " //22 			
				+ "  from " 
				+ "   gcom.cadastro.imovel.Imovel as imovel"
				+ "   left join imovel.ligacaoAgua ligacaoAgua"
				+ "   left join imovel.ligacaoEsgoto ligacaoEsgoto "
				+ "   inner join imovel.quadra.rota rota "
				+ " where imovel.setorComercial.id = :idSetor and"
				+ "   imovel.indicadorExclusao = 2";


			retorno = session.createQuery(hql).setInteger(
					"ligacaoAguaSituacaoLigado", LigacaoAguaSituacao.LIGADO)
					.setInteger("ligacaoAguaSituacaoCortado",
							LigacaoAguaSituacao.CORTADO).setInteger(
							"ligacaoEsgotoSituacaoLigado",
							LigacaoEsgotoSituacao.LIGADO)
							.setInteger("ligacaoAguaSituacaoLigadoEmAnalise", LigacaoAguaSituacao.LIGADO_EM_ANALISE)
							.setInteger("idSetor", idSetor)
							.setMaxResults(quantidadeRegistros)
							.setFirstResult(numeroIndice)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
 	
	/**
	 * 
	 * Atualizar Resumo de Liga絥s e Economias Por ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 30/04/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 */
	public int atualizarResumoLigacoesEconomiasPorAno(int idGerenciaRegional,
			int idUnidadeNegocio,
			int idLocalidade,
			int idElo,
			int idSetorComercial,
			int codigoSetorComercial,
			int idPerfilImovel,
			int idEsfera,
			int idTipoClienteResponsavel,
			int idSituacaoLigacaoAgua,
			int idSituacaoLigacaoEsgoto,
			int idCategoria,
			int idSubCategoria,
			int idPerfilLigacaoAgua,
			int idPerfilLigacaoEsgoto,
			short idHidrometro,
			short idHidrometroPoco,
			short idVolFixadoAgua,
			short idVolFixadoEsgoto,
			short idPoco,
			int idTipoTarifaConsumo,
			int qtEconomias,
			int anoMes,
			Boolean incrementaQtdLigacoesNovasAgua,
			Boolean incrementaQtdLigacoesNovasEsgoto)
	throws ErroRepositorioException{
		
		int retorno = 0;
		
//		Session session = HibernateUtil.getSession();
		
		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();

		try {
			
			String sql = "UPDATE cadastro.un_resumo_ligacao_economia_ref_2010"
				+ " SET " 
				+ " rele_qtligacoes= rele_qtligacoes + 1, " 
				+ " rele_qteconomias= rele_qteconomias + ? " ;//1 
				
				if ( incrementaQtdLigacoesNovasAgua!= null && incrementaQtdLigacoesNovasAgua == true ){
					sql = sql + ", rele_qtligacoesnovasagua= rele_qtligacoesnovasagua + 1 ";
				}
				if ( incrementaQtdLigacoesNovasEsgoto != null && incrementaQtdLigacoesNovasEsgoto == true ){
					sql = sql + ", rele_qtligacoesnovasesgoto= rele_qtligacoesnovasesgoto + 1 ";
				}
				
			 	sql = sql + "WHERE "
				+ " rele_amreferencia = ? AND" //2
				+ " rele_ichidrometro = ? AND " //3
				+ " rele_ichidrometropoco = ? AND " //4
				+ " rele_icvolumefixadoagua = ? AND " //5
				+ " rele_icvolumefixadoesgoto = ? AND " //6
				+ " rele_cdsetorcomercial = ? AND " //7
				+ " rele_icpoco = ? AND " //8
				+ " epod_id = ? AND " //9
				+ " catg_id = ? AND " //10
				+ " scat_id = ? AND " //11
				+ " iper_id = ? AND " //12
				+ " cltp_id = ? AND " //13
				+ " last_id = ? AND " //14
				+ " lest_id = ? AND " //15
				+ " lapf_id = ? AND " //16
				+ " lepf_id = ? AND " //17
				+ " greg_id = ? AND " //18
				+ " uneg_id = ? AND " //19
				+ " loca_id = ? AND " //20
				+ " loca_cdelo = ? AND " //21
				+ " stcm_id = ? AND " //22
				+ " cstf_id = ? "; //23
				
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, qtEconomias);
			stmt.setInt(2, anoMes);
			stmt.setShort(3,idHidrometro);
			stmt.setShort(4,idHidrometroPoco);
			stmt.setShort(5,idVolFixadoAgua);
			stmt.setShort(6, idVolFixadoEsgoto);
			stmt.setInt(7, codigoSetorComercial);
			stmt.setShort(8, idPoco);
			stmt.setInt(9, idEsfera);
			stmt.setInt(10, idCategoria);
			stmt.setInt(11, idSubCategoria);
			stmt.setInt(12, idPerfilImovel);
			stmt.setInt(13, idTipoClienteResponsavel);
			stmt.setInt(14, idSituacaoLigacaoAgua);
			stmt.setInt(15, idSituacaoLigacaoEsgoto);
			stmt.setInt(16, idPerfilLigacaoAgua);
			stmt.setInt(17, idPerfilLigacaoEsgoto);
			stmt.setInt(18, idGerenciaRegional);
			stmt.setInt(19, idUnidadeNegocio);
			stmt.setInt(20, idLocalidade);
			stmt.setInt(21, idElo);
			stmt.setInt(22, idSetorComercial);
			stmt.setInt(23, idTipoTarifaConsumo);
			
			System.out.println("Atualizando Res. Ligacoes e Economias: " +idSetorComercial);
			
			retorno = stmt.executeUpdate();
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != stmt)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * 
	 * Inserir Resumo Ligacoes Economias Por Ano
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 30/04/2010
	 *
	 */
	public void inserirResumoLigacoesEconomiasPorAno(Integer anoMes, ResumoLigacaoEconomiaPorAnoHelper helper)
		throws ErroRepositorioException{
		
		//Connection con = null;
		//PreparedStatement stmt = null;

		String sql;

		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		PreparedStatement stmt = null;
		con = session.connection();
		
			try {
				
				//con = session.connection();
				
				sql = "INSERT INTO cadastro.un_res_lig_econ_ref_2010 ( "
					+ "rele_id, "
		            + " rele_amreferencia, " //0
		            + " greg_id, " //1
		            + " uneg_id, " //2
		            + " loca_id, " //3
		            + " loca_cdelo, " //4
		            + " stcm_id, " //5
		            + " rele_cdsetorcomercial, " //6
		            + " iper_id, " //7
		            + " last_id, " //8
		            + " lest_id, " //9
		            + " catg_id, " //10
		            + " scat_id, " //11
		            + " epod_id, " //12
		            + " cltp_id, " //13
		            + " lapf_id, " //14
		            + " lepf_id, " //15
		            + " rele_ichidrometro, " //16
		            + " rele_icvolumefixadoagua, " //17
		            + " rele_icvolumefixadoesgoto, " //18
		            + " rele_icpoco, " //19
		            + " rele_qtligacoes, " //20
		            + " rele_qteconomias, " //21
		            + " rele_tmultimaalteracao, " //22 
		            + " rele_ichidrometropoco, " //23
		            + " cstf_id, " //24
		            + "rele_qtligacoesnovasesgoto, " //25
		            + "rele_qtligacoesnovasagua )  " //26
			    + "VALUES (" 
			    		+ Util.obterNextValSequence("cadastro.seq_un_res_lig_econ_ref_2007") + ", "
			    		+ " ?, " //1
			    		+ " ?, " //2
			    		+ " ?, " //3
			    		+ " ?, " //4
			    		+ " ?, " //5
			    		+ " ?, " //6
			            + " ?, " //7
			            + " ?, " //8
			            + " ?, " //9
			            + " ?, " //10
			            + " ?, " //11
			            + " ?, " //12
			            + " ?, " //13
			            + " ?, " //14
			            + " ?, " //15
			            + " ?, " //16
			            + " ?, " //17
			            + " ?, " //18
			            + " ?, " //19
			            + " ?, " //20
			            + " ?, " //21
			            + " ?, " //22
			            + Util.obterSQLDataAtual() + " , " //
			            + " ?, " //23
			            + " ?, " //24
			            + " ?, " //25
			            + " ?" //26
			            + " )";
				
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, anoMes);
				stmt.setInt(2,helper.getIdGerenciaRegional());
				stmt.setInt(3,helper.getIdUnidadeNegocio());
				stmt.setInt(4,helper.getIdLocalidade());
				stmt.setInt(5, helper.getIdElo());
				stmt.setInt(6, helper.getIdSetorComercial());
				stmt.setInt(7, helper.getCodigoSetorComercial());
				stmt.setInt(8, helper.getIdPerfilImovel());
				stmt.setInt(9, helper.getIdSituacaoLigacaoAgua());
				stmt.setInt(10, helper.getIdSituacaoLigacaoEsgoto());
				stmt.setInt(11, helper.getIdCategoria());
				stmt.setInt(12, helper.getIdSubCategoria());
				stmt.setInt(13, helper.getIdEsfera());
				stmt.setInt(14, helper.getIdTipoClienteResponsavel());
				stmt.setInt(15, helper.getIdPerfilLigacaoAgua());
				stmt.setInt(16, helper.getIdPerfilLigacaoEsgoto());
				stmt.setShort(17, helper.getIdHidrometro().shortValue());
				stmt.setShort(18, helper.getIdVolFixadoAgua().shortValue());
				stmt.setShort(19, helper.getIdVolFixadoEsgoto().shortValue());
				stmt.setShort(20, helper.getIdPoco().shortValue());
				stmt.setInt(21, helper.getQtdLigacoes());
				stmt.setInt(22, helper.getQtdEconomias());
				stmt.setShort(23, helper.getIdHidrometroPoco().shortValue());
				stmt.setInt(24, helper.getIdTipoTarifaConsumo());
				stmt.setInt(25, helper.getQtdLigacoesNovasEsgoto()); 
				stmt.setInt(26, helper.getQtdLigacoesNovasAgua()); 
				
				System.out.println("Inserindo Res. Ligacoes e Economias p/ Ano: "+helper.getIdSetorComercial());
				
				stmt.executeUpdate();
				
				//session.createSQLQuery(sql);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				if (null != stmt)
					try {
						stmt.close();
					} catch (SQLException e) {
						throw new ErroRepositorioException(e, "Erro no Hibernate");
					}
				HibernateUtil.closeSession(session);
			}
		
	}
	
	/**
	 * Metodo pesquisa a quantidade de economias
	 * de uma conta, por subcategoria
	 * 
	 * @author Bruno Barros
	 * @date 25/05/2010
	 * 
	 * @param idConta - id da conta a qual procuraremos as categorias
	 * @param idCategoria - id da categoria a ser pesquisada
	 * @param idSubcategoria - id da subcategoria a ser pesquisada
	 * @return quantidade de economias
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisaQuantidadeEconomiasPorSubcategoria(Integer idConta,
			Integer idCategoria, Integer idSubcategoria ) throws ErroRepositorioException {
		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "  ccat.quantidadeEconomia "
					+ "from "
					+ "  gcom.faturamento.conta.ContaCategoria ccat "
					+ "where "
					+ "  ccat.comp_id.conta.id = :idConta and ccat.comp_id.categoria.id = :idCategoria and ccat.comp_id.subcategoria.id = :idSubcategoria ";

			retorno = session.createQuery(consulta).setInteger("idConta",
					idConta).setInteger("idCategoria", idCategoria)
					.setInteger("idSubcategoria", idSubcategoria)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce??o para a pr?xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess?o
			HibernateUtil.closeSession(session);
		}

		if (retorno != null) {
			return new Integer((Short) retorno);
		} else {
			return 0;
		}
	}
	
	/**
	 * Seleciona todos os historicos de consumo de uma determinada localidade
	 * por imovel
	 * 
	 * @author Fernando Fontelles
	 * @date 24/05/2010
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosConsumoAguaPorAno(int idSetor)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			
			String hql = 
						"select " + 
						// 0
						"  imovel.id, " +
						// 1
						"  case when ( conta <> null ) then " +
						"    locConta.gerenciaRegional.id " +    
						"  else " +  
						"    locImo.gerenciaRegional.id " +
						"  end, " +
						// 2
						"  case when ( conta <> null ) then " +
						"    locConta.unidadeNegocio.id " +
						"  else " +  
						"    locImo.unidadeNegocio.id " +
						"  end,   " +
						// 3
						"  case when ( conta <> null ) then " +
						"    locConta.id " +
						"  else " +  
						"    locImo.id " +
						"  end, " +
						// 4
						"  case when ( conta <> null ) then " +
						"    locConta.localidade.id " +
						"  else   " +
						"    locImo.localidade.id " +
						"  end, " +
						// 5
						" case when ( conta <> null ) then " +
				        "   setComCon.id " + 
				        " else " +    
				        "   setComImo.id " +
				        " end, " +
				        // 6
//						"  case when ( conta <> null ) then " +
//						"    quaCon.rota.id " +
//						"  else " +  
//						"    quaImo.rota.id " +
//						"  end, " +
						// 7
//						"  case when ( conta <> null ) then " +
//						"    quaCon.id " +
//						"  else " +  
//						"    quaImo.id " +
//						"  end, " +
						// 6
						"  case when ( conta <> null ) then " +
						"    conta.codigoSetorComercial " +
						"  else " +  
						"    setComImo.codigo " +
						"  end, " +
						// 9
//						"  case when ( conta <> null ) then " +
//						"    quaCon.numeroQuadra " +
//						"  else " +  
//						"    quaImo.numeroQuadra " +
//						"  end, " +
						// 7
						"  case when ( conta <> null ) then " +
						"    imoPerfCon.id " +
						"  else " +  
						"    imovel.imovelPerfil.id " +
						"  end, " +
						// 8
						"  case when ( conta <> null ) then " +
						"    ligAguaSitCon.id " +
						"  else " +  
						"    imovel.ligacaoAguaSituacao.id " +
						"  end, " +
						// 9
						"  case when ( conta <> null ) then " +
						"    ligEsgSitCon.id " +
						"  else " +  
						"    imovel.ligacaoEsgotoSituacao.id " +
						"  end, " +
						// 10
						"  case when ( " + 
						"    ligAguaImo.ligacaoAguaPerfil.id is null ) then " + 
						"    0 " + 
						"  else " + 
						"    ligAguaImo.ligacaoAguaPerfil.id " + 
						"  end, " +
						// 11
						"  case when ( " + 
						"    ligEsgImo.ligacaoEsgotoPerfil.id is null ) then " + 
						"    0 " + 
						"  else " + 
						"    ligEsgImo.ligacaoEsgotoPerfil.id " + 
						"  end, " +
						// 12
						"  consumoHistorico.consumoTipo.id, " +
						// 13
						"  consumoHistorico.numeroConsumoFaturadoMes, " +
						// 14
						"  ligAguaImo.numeroConsumoMinimoAgua, " +
						// 15
						"  consumoHistorico.referenciaFaturamento, " +
						// 16
						"  consumoHistorico.ligacaoTipo.id, " +
						// 17						
						"  conta.id, " +
						// 18
						"  conta.consumoAgua, " +
						// 19
						"  conta.valorAgua " +
						"from " + 
						"  gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico " + 
						"  inner join consumoHistorico.imovel imovel " +
						"  inner join imovel.localidade locImo " +
						//"  inner join imovel.quadra as quaImo " +
						"  inner join imovel.setorComercial setComImo " +
						"  left join imovel.ligacaoAgua ligAguaImo " +
						"  left join imovel.ligacaoEsgoto ligEsgImo " +
						"  left join imovel.contas conta with ( conta.referencia = ( select " + 
		   			    "	      	  							       						sistemaParametro.anoMesFaturamento " +  
						"	    								     					from " + 
			  			"			  							       						gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro ) and ( conta.debitoCreditoSituacaoAtual = 0 or conta.debitoCreditoSituacaoAnterior = 0 ) ) " +
						"  left join conta.localidade locConta " +  
						"  left join conta.quadraConta quaCon " +
						"  left join conta.imovelPerfil imoPerfCon " +
						"  left join conta.ligacaoAguaSituacao ligAguaSitCon " +
						"  left join conta.ligacaoEsgotoSituacao ligEsgSitCon " +
						"  left join quaCon.setorComercial setComCon " +
						"where " + 
						"  imovel.setorComercial.id = :idSetor and " + 
						"  consumoHistorico.ligacaoTipo.id = :ligacaoTipo and " +
						"  consumoHistorico.indicadorFaturamento = 1 and " +
						"  consumoHistorico.indicadorImovelCondominio <> 1 and " +
					    "  consumoHistorico.referenciaFaturamento = ( select " + 
						"    										    sistemaParametro.anoMesFaturamento " +  
						"    										  from " + 
						"      										    gcom.cadastro.sistemaparametro.SistemaParametro sistemaParametro ) ";

			retorno = session.createQuery(hql)
					.setInteger("idSetor", idSetor)
					.setInteger("ligacaoTipo", LigacaoTipo.LIGACAO_AGUA)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Insere os dados do Resumo Consumo de Agua Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 24/05/2010
	 * 
	 * @param anoMesReferencia
	 * @param resConsumo
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoConsumoAguaPorAno(Integer anoMesReferencia,
			ResumoConsumoAguaPorAnoHelper resConsumo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into micromedicao.un_res_con_agua_ref_2010 ("
					+ "  reca_id, "
					+ "  uneg_id, "
					+ "  reca_amreferencia, "
					+ "  greg_id, "
					+ "  loca_id, "
					+ "  loca_cdelo, "
					+ "  stcm_id, "
					//+ "  qdra_id, "
					+ "  reca_cdsetorcomercial, "
					//+ "  rota_id, "
					//+ "  reca_nnquadra, "
					+ "  last_id, "
					+ "  lest_id, "
					+ "  catg_id, "
					+ "  scat_id , "
					+ "  epod_id , "
					+ "  cltp_id , "
					+ "  lapf_id , "
					+ "  reca_qteconomias , "
					+ "  lepf_id , "
					+ "  reca_consumoagua , "
					+ "  reca_tmultimaalteracao, "
					+ "  reca_consumoexcedente , "
					+ "  reca_qtligacoes , "
					+ "  iper_id , "
					+ "  cstp_id ,"
					+ "  reca_icvolumeexcedente,"
					+ "  reca_ichidrometro,"
					+ "  reca_voconsumofaturado, "
					+ "  reca_icligacaofaturada )"
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_res_con_agua_ref_2007") + ", "
					+ resConsumo.getIdUnidadeNegocio() + ", "
					+ anoMesReferencia + ", "
					+ resConsumo.getIdGerenciaRegional() + ", "
					+ resConsumo.getIdLocalidade() + ", "
					+ resConsumo.getIdElo() + ", "
					+ resConsumo.getIdSetorComercial() + ", "
					//+ resConsumo.getIdQuadra() + ", "
					+ resConsumo.getCodigoSetorComercial() + ", "
					//+ resConsumo.getIdRota() + ", "
					//+ resConsumo.getNumeroQuadra() + ", "
					+ resConsumo.getIdLigacaoAguaSituacao() + ", "
					+ resConsumo.getIdLigacaoEsgotoSituacao() + ", "
					+ resConsumo.getIdCategoria() + ", "
					+ resConsumo.getIdSubCategoria() + ", "
					+ resConsumo.getIdEsferaPoder() + ", "
					+ resConsumo.getIdClienteTipo() + ", "
					+ resConsumo.getIdLigacaoAguaPerfil() + ", "
					+ resConsumo.getQuantidadeEconomias() + ", "
					+ resConsumo.getIdLigacaoEsgotoPerfil() + ", "
					+ resConsumo.getQuantidadeConsumoAgua() + ", " + Util.obterSQLDataAtual() + " , "
					+ resConsumo.getQuantidadeConsumoAguaExcedente() + ", "
					+ resConsumo.getQuantidadeLigacoes() + ", "
					+ resConsumo.getIdImovelPerfil() + ", "
					+ resConsumo.getIdConsumoTipo() + ", "
					+ resConsumo.getIdVolumeExcedente() + ","
			        + resConsumo.getIdHidrometro() + ","
			        + resConsumo.getVolumeFaturado() + ","
			        + resConsumo.getIndicadorLigacaoFaturada() + ")";

			stmt.executeUpdate(insert);
			
			System.out.println("Inserindo Resumo Consumo Agua PorAno: "+resConsumo.getIdSetorComercial());

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}
	
	/**
	 * Seleciona todos os historicos de coleta de uma determinada localidade
	 * por imovel - Resumo Coleta Esgoto Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 16/06/2010
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 *            anoMesFaturamento da tabela sistema parametros
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public List getHistoricosColetaEsgotoPorAno(int idSetor, Integer anoMesFaturamento)
			throws ErroRepositorioException {

		List retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = 
				"select \n" +
				// 0 - Imovel
				"  imo.id, \n" + 
				// 1 - Gerencia Regional
				"  case when ( grCon is not null ) then \n" +
				"    grCon.id \n" +
				"  else \n" +
				"    grImo.id \n" +
				"  end, \n" +
				// 2 - Unidade de Negocio
				"  case when ( unCon is not null ) then \n" +
				"    unCon.id \n" +
				"  else \n" +
				"    unImo.id \n" +
				"  end, \n" +
				// 3 - Localidade 
				"  case when ( locCon is not null ) then \n" +
				"    locCon.id \n" +
				"  else \n" +
				"    locImo.id \n" +
				"  end, \n" +				
				// 4 - Elo
				"  case when ( eloCon is not null ) then \n" +
				"    eloCon.id \n" +
				"  else \n" +
				"    eloImo.id \n" +
				"  end, \n" +
				// 5 - Setor comercial
//				"  case when ( stCon is not null ) then \n" +
//				"    stCon.id \n" +
//				"  else \n" +
				"    stImo.id, \n" +
//				"  end, \n" +
				// 6 - Codigo do Setor Comercial
//				"  case when ( stCon is not null ) then \n" +
//				"    stCon.codigo \n" +
//				"  else \n" +
				"    stImo.codigo, \n" +
//				"  end, \n" +
				// 7 - Imovel Perfil
				"  case when ( ipCon is not null ) then \n" +
				"    ipCon.id \n" +
				"  else \n" +
				"    ipImo.id \n" +
				"  end, \n" +
				// 8 - Ligacao Agua Situa磯
				"  case when ( lasCon is not null ) then \n" +
				"    lasCon.id \n" +
				"  else \n" +
				"    lasImo.id \n" +
				"  end, \n" +
				// 9 - Ligacao Esgoto Situacao
				"  case when ( lesCon is not null ) then \n" +
				"    lesCon.id \n" +
				"  else \n" +
				"    lesImo.id \n" +
				"  end, \n" +
				// 10 - Ligacao Agua Perfil
				"  coalesce( lapImo.id, 0 ), \n" +
				// 11 - Ligacao Esgoto Perfil
				"  coalesce( lepImo.id, 0 ), \n" +
				// 12 - Consumo Tipo
				"  ct.id, \n" +
				// 13 - Numero de Consumo Fatuarado no mes
				"  coalesce( ch.numeroConsumoFaturadoMes, 0), \n" +
				// 14 - Referencia do faturamento
				"  coalesce( ch.referenciaFaturamento, 0 ), \n" +
				// 15 - Indicador de consumo m?mo
				"  case when ( leImo.consumoMinimo > 0 ) then \n" +
				"    1 \n" +
				"  else \n" +
				"    2 \n" +
				"  end, \n" +
				// 16 - Percentual da liga磯 de esgoto
				"  case when ( leImo.percentual > 0 ) then \n" +
				"    0.0 \n" +
				"  else \n" +
				"    coalesce( leImo.percentual, 0.0 ) \n" +
				"  end, \n" +
				// 17 - Indicador de hidrometro instalado
				"  case when ( imo.hidrometroInstalacaoHistorico is not null ) then \n" +
				"    1 \n" +
				"  else \n" +
				"    2 \n" +
				"  end, \n" +
				// 18 - Percentual de coleta
				"  coalesce( ch.percentualColeta, 0.0 ), \n" +
				// 19 - C?o da conta
				"  coalesce( con.id, 0 ), \n" +
				// 20 - Numero do consumo de esgoto
				"  coalesce( con.consumoEsgoto, 0 ), \n" +
				// 21 Indicador Faturamento
				"  ch.indicadorFaturamento, \n" +
				// 22 Situacao de Faturamento
				"  ch.faturamentoSituacaoTipo.id \n" +
				"from \n" +
				// Joins de Consumo Historico
				"  ConsumoHistorico ch \n" +
				"  inner join ch.imovel imo \n" +
				"  inner join imo.localidade locImo \n" +
				"  inner join locImo.gerenciaRegional grImo \n" +
				"  inner join locImo.unidadeNegocio unImo \n" +
				"  inner join locImo.localidade eloImo \n" +
				"  inner join imo.setorComercial stImo \n" +
				"  inner join imo.imovelPerfil ipImo \n" +
				"  left  join imo.ligacaoAguaSituacao lasImo \n" +
				"  left  join imo.ligacaoEsgotoSituacao lesImo \n" +
				"  left  join imo.ligacaoAgua laImo \n" +
				"  left  join imo.ligacaoEsgoto leImo \n" +
				"  left  join laImo.ligacaoAguaPerfil lapImo \n" +
				"  left  join leImo.ligacaoEsgotoPerfil lepImo \n" +
				"  left  join ch.consumoTipo ct \n" +
				"  left  join ch.ligacaoTipo lt \n" +				
				"  left join imo.contas con \n" +
				"	   with ( con.referencia = :anoMesFaturamento and \n" +
				"			    ( con.debitoCreditoSituacaoAtual = 0 or con.debitoCreditoSituacaoAnterior = 0 ) \n" +
				"			) \n" +
				"  left  join con.localidade locCon \n" +
				"  left  join locCon.gerenciaRegional grCon \n" +
				"  left  join locCon.unidadeNegocio unCon \n" +
				"  left  join locCon.localidade eloCon \n" +
				"  left  join con.imovelPerfil ipCon \n" +
				"  left  join con.ligacaoAguaSituacao lasCon \n" +
				"  left  join con.ligacaoEsgotoSituacao lesCon " +
				"where \n" +
				"  imo.setorComercial.id = :idSetor and \n" + 
				"  lt.id = :ligacaoTipo and \n" + 
				"  ch.indicadorImovelCondominio <> 1 and \n" +
				"  ch.referenciaFaturamento = :anoMesFaturamento" /*and \n" +
				"  ch.indicadorFaturamento = 1 "*/
				;
				
			retorno = session.createQuery(hql)
				.setInteger("anoMesFaturamento", anoMesFaturamento)
				.setInteger("idSetor", idSetor)
				.setInteger("ligacaoTipo", LigacaoTipo.LIGACAO_ESGOTO)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Insere resumo Coleta Esgoto Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 16/06/2010
	 * 
	 * @param idSetor
	 *            id do Setor a ser pesquisado
	 * @return Colecao com os historicos selecionados
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoColetaEsgotoPorAno(Integer anoMesReferencia,
			ResumoColetaEsgotoPorAnoHelper resConsumo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into micromedicao.un_res_col_esg_ref_2010 ("
					+ "  rece_id, "                // 01
					+ "  uneg_id, "                // 02
					+ "  rece_amreferencia, "      // 03
					+ "  greg_id, "                // 04
					+ "  loca_id, "                // 05
					+ "  loca_cdelo, "             // 06
					+ "  stcm_id, "                // 07
//					+ "  qdra_id, "                // 08
					+ "  rece_cdsetorcomercial, "  // 08
//					+ "  rota_id, "                // 10
//					+ "  rece_nnquadra, "          // 11
					+ "  last_id, "                // 9
					+ "  lest_id, "                // 10
					+ "  catg_id, "                // 11
					+ "  scat_id, "                // 12
					+ "  epod_id, "                // 13
					+ "  cltp_id, "                // 14
					+ "  lapf_id, "                // 15
					+ "  rece_qteconomias , "      // 16
					+ "  lepf_id , "               // 17
					+ "  rece_voesgoto   , "       // 18
					+ "  rece_tmultimaalteracao, " // 19
					+ "  rece_icvlexcedente, "     // 20
					+ "  rece_qtligacoes , "       // 21
					+ "  iper_id , "               // 22
					+ "  cstp_id ,"                // 23
					+ "  rece_voexcedente, "       // 24
					+ "  rece_icmedidoagua, "      // 25
					+ "  rece_icpoco, "            // 26 
					+ "  rece_icmedidofontealternativa, "  // 27
					+ "  rece_pcesgoto, "          // 28
					+ "  rece_pccoleta, "          // 29 
					+ "  rece_icvlminimoesgoto, "  // 30
					+ "  rece_vofaturado,"		   // 31
					+ "  rece_ichidrometro,"       // 32
//					+ "  rece_cdrota " 			   // 36
					+ "  rece_icfaturamento, " //33
					+ "  ftst_id, " //34
					+ "  ftsm_id " //35
					+ " ) "			   
					+ "  values ( "
					+ Util.obterNextValSequence("micromedicao.seq_un_res_col_esg_ref_2007") + ", " //01
					+ resConsumo.getIdUnidadeNegocio() + ", "                    // 02
					+ anoMesReferencia + ", "                                    // 03
					+ resConsumo.getIdGerenciaRegional() + ", "                  // 04
					+ resConsumo.getIdLocalidade() + ", "                        // 05
					+ resConsumo.getIdElo() + ", "                               // 06
					+ resConsumo.getIdSetorComercial() + ", "                    // 07
//					+ resConsumo.getIdQuadra() + ", "                            // 08
					+ resConsumo.getCodigoSetorComercial() + ", "                // 08
//					+ resConsumo.getIdRota() + ", "                              // 10
//					+ resConsumo.getNumeroQuadra() + ", "                        // 11
					+ resConsumo.getIdLigacaoAguaSituacao() + ", "               // 09
					+ resConsumo.getIdLigacaoEsgotoSituacao() + ", "             // 10
					+ resConsumo.getIdCategoria() + ", "                         // 11
					+ resConsumo.getIdSubCategoria() + ", "                      // 12
					+ resConsumo.getIdEsferaPoder() + ", "                       // 13
					+ resConsumo.getIdClienteTipo() + ", "                       // 14
					+ resConsumo.getIdLigacaoAguaPerfil() + ", "                 // 15
					+ resConsumo.getQuantidadeEconomias() + ", "                 // 16
					+ resConsumo.getIdLigacaoEsgotoPerfil() + ", "               // 17
					+ resConsumo.getQuantidadeColetaEsgoto() + ", "              // 18
					+ Util.obterSQLDataAtual() + " , "                                                 // 19   
					+ resConsumo.getIndicadorColetaEsgotoExcedente() + ", "      // 20
					+ resConsumo.getQuantidadeLigacoes() + ", "                  // 21
					+ resConsumo.getIdImovelPerfil() + ", "                      // 22
					+ resConsumo.getIdConsumoTipo() + ", "                       // 23      
					+ resConsumo.getVolumeExcedente() + ", "                     // 24
					// Ic medido de Agua deve ser zero, pois esse informa磯 jᠥstፊ					// gravada no ichidrometro
					+ "0,"														 // 25
					+ resConsumo.getPoco() + ", "                                // 26
					+ resConsumo.getMedidoFonteAlternativa() + ", "              // 27
					+ resConsumo.getPcEsgoto() + ", "                            // 28
					+ resConsumo.getPcColeta() + ", "                            // 29
					+ resConsumo.getIcVlMinimoEsgoto()+ ","                      // 30
					+ resConsumo.getVoFaturado() +", "                           // 31
				    + resConsumo.getIndicadorHidrometro() + ", "                 // 32
//				    + resConsumo.getCodigoRota() 								 // 36
				    + resConsumo.getIndicadorFaturamento() + ", "				 // 33
				    + resConsumo.getFaturamentoSituacao() + ", "				 // 34
				    + resConsumo.getMotivoFaturamentoSituacao()					 // 35
				    + ")";							 

			stmt.executeUpdate(insert);

			System.out.print("1 - INSERINDO RESUMO COLETA ESGOTO ");
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate coleta esgoto ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert coleta esgoto ");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexoes");
			}
		}
	}
	
	/**
	 * M鴯do que retorna uma lista de objeto xxxxx que representa o agrupamento
	 * id do imovel id gerencia regional id licalidade id do setor comercial
	 * codigo do setor comercial id perfil
	 * do imovel id situacao da ligacao de agua id situacao da ligacao de esgoto
	 * principal categoria do imovel esfera de poder do cliente responsavel
	 * indicador de existencia de hidrometro
	 * 
	 * @author Fernando Fontelles
	 * @date 21/06/2010
	 * 
	 * @param idLocalidade
	 *            id da localida a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getImoveisResumoParcelamentoPorAno(int idLocalidade, int anoMes)
			throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
					+ "  parc.id, " //0
					+ "  imo.id, " //1
					+ "  parc.localidade.gerenciaRegional.id, " //2
					+ "  parc.localidade.unidadeNegocio.id, " //3
					+ "  parc.localidade.localidade.id, " //4
					+ "  parc.localidade.id, " //5
					+ "  parc.imovel.setorComercial.id, " //6
//					+ "  parc.quadra.setorComercial.id, "
//					+ "  parc.quadra.rota.id, "
//					+ "  parc.quadra.id, "
					+ "  parc.codigoSetorComercial, "  //7
//					+ "  parc.numeroQuadra, "
					+ "  imo.imovelPerfil.id, " //8
					+ "  parc.ligacaoAguaSituacao.id, " //9
					+ "  parc.ligacaoEsgotoSituacao.id, " //10
					+ "  case when ( " //11
					+ "    imo.ligacaoAgua.ligacaoAguaPerfil.id is null ) then "
					+ "    0 "
					+ "  else "
					+ "    imo.ligacaoAgua.ligacaoAguaPerfil.id "
					+ "  end, "
					+ "  case when ( " //12
					+ "    imo.ligacaoEsgoto.ligacaoEsgotoPerfil.id is null ) then "
					+ "    0 "
					+ "  else "
					+ "    imo.ligacaoEsgoto.ligacaoEsgotoPerfil.id "
					+ "  end, "
					+ "  parc.valorConta, " //13
					+ "  parc.valorGuiaPapagamento, " // 14
					+ "  parc.valorCreditoARealizar, " // 15
					+ "  parc.valorDescontoAcrescimos, " // 16
					+ "  parc.valorDescontoInatividade, " // 17
					+ "  parc.valorDescontoAntiguidade, " // 18
					+ "  (parc.valorPrestacao * parc.numeroPrestacoes) as totalparcelamento, "// 19
					+ "  parc.anoMesReferenciaFaturamento, " // 20
					+ "  parc.valorServicosACobrar, " // 21
					+ "  (parc.valorAtualizacaoMonetaria + parc.valorJurosMora + parc.valorMulta) as debitosACobrarAcrescimos, " // 22
					+ "  parc.valorParcelamentosACobrar, " // 23
					+ "  parc.valorEntrada,  "// 24
					+ "  parc.valorJurosParcelamento, "// 25
					+ "  parc.numeroPrestacoes, "// 26
					+ "  imo.consumoTarifa.id,  "// 27
					+ "  imo.numeroReparcelamentoConsecutivos "// 28
//					+ "  rota.codigo "//32
					+ " from "
					+ "  gcom.cobranca.parcelamento.Parcelamento parc "
					+ "  inner join parc.imovel imo "
//					+ "  inner join imo.quadra as quad "
//					+ "  inner join quad.rota rota "
//					+ "  inner join quad.setorComercial as stcom "
					+ "  inner join imo.setorComercial stcom "
					+ "  inner join stcom.localidade locali "
					+ "  left join imo.ligacaoAgua ligacaoAgua "
					+ "  left join imo.ligacaoEsgoto ligacaoEsgoto "
					+ " where locali.id = :idLocalidade and "
					+ "  imo.indicadorExclusao = 2 and "
					+ "  parc.parcelamentoSituacao.id = 1 and "
					+ "  parc.anoMesReferenciaFaturamento = :anoMesReferencia ";
			
			retorno = session.createQuery(hql).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferencia", anoMes).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * Seleciona o valor do Motivo Situacao do Faturamento
	 * 
	 * @author Fernando Fontelles
	 * @date 15/07/2010
	 * 
	 * @param idImovel
	 * @param amReferenciaConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer getMotivoSituacaoFaturamento(Integer idImovel, Integer amReferenciaConta)
		throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select  fsh.faturamentoSituacaoMotivo.id \n" +
				" FROM gcom.faturamento.FaturamentoSituacaoHistorico fsh " +
				" WHERE fsh.imovel.id = :idImovel and " +
				" :amReferenciaConta between fsh.anoMesFaturamentoSituacaoInicio " +
				" and fsh.anoMesFaturamentoSituacaoFim"
				;
				
				retorno = (Integer)session.createQuery(hql)
				.setInteger("idImovel", idImovel)
				.setInteger("amReferenciaConta", amReferenciaConta)
				.setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 11/08/2010
	 * 
	 * @param idSetor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasHistogramaSemQuadra(int idSetor, int anoMesReferenciaFaturamento)
		throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	c.id, " +						//0 
				"	l.gerenciaRegional.id, " + 		//1
				"	l.unidadeNegocio.id, " +		//2
				"	l.localidade.id, " +			//3
				"	l.id, " +						//4
				"	q.setorComercial.id, " +		//5
				"	c.codigoSetorComercial, " +		//6
				"	c.consumoTarifa.id, " +			//7
				"	c.imovelPerfil.id, " +			//8
				"	c.ligacaoAguaSituacao.id, " +	//9
				"	imo.id, " +						//10
				"	c.ligacaoEsgotoSituacao.id, " +	//11
				"	c.percentualEsgoto " +			//12
				"from " +
				"	Conta c " +
				"	inner join c.quadraConta q " +
				"	inner join c.localidade l " +
				"	inner join c.imovel imo " +
				"where " +
				"	c.referencia = :anoMesReferenciaFaturamento " +
				"	and (c.debitoCreditoSituacaoAtual = 0 or c.debitoCreditoSituacaoAnterior = 0) " +
				"	and q.setorComercial.id = :idSetor";
			
			retorno = session.createQuery(hql)
				.setInteger("anoMesReferenciaFaturamento", anoMesReferenciaFaturamento)
				.setInteger("idSetor", idSetor)
				.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * Filtra a Conta na tabela ContaCategoria por Categoria ordenando pela quantidade
	 * de Economia da Categoria.
	 * 
	 * @author Ivan Sergio
	 * @date: 12/08/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List filtrarContaCategoriaHistogramaPorCategoria(Integer idConta) throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	distinct (cc.comp_id.categoria.id), " +					// 0
				"	cc.comp_id.categoria.categoriaTipo.id, " +				// 1
				"	sum(cc.quantidadeEconomia), " +	// 2
				"	sum(coalesce(cc.valorAgua, 0)), " +						// 3
				"	sum(coalesce(cc.consumoAgua, 0)), " +					// 4
				"	sum(coalesce(cc.consumoMinimoAgua, 0)), " +				// 5
				"	sum(coalesce(cc.valorEsgoto, 0)), " +					// 6
				"	sum(coalesce(cc.consumoEsgoto, 0)), " +					// 7
				"	sum(coalesce(cc.consumoMinimoEsgoto, 0)) " +			// 8
				"from ContaCategoria cc " +
				"	inner join cc.comp_id.categoria ct " +
				"	inner join cc.comp_id.subcategoria sct " +
				"	inner join ct.categoriaTipo ctt " +
				"where cc.comp_id.conta.id = :idConta " +
				"group by " +
				"	cc.comp_id.categoria.id, " +
				"	cc.comp_id.categoria.categoriaTipo.id " +
				"order by " +
				"	3 desc";
			
			retorno = session.createQuery(hql).setInteger("idConta", idConta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1057] - Gerar Resumo Histograma Agua Esgoto Sem Quadras
	 * Filtra a Conta na tabela ContaCategoria por SubCategoria ordenando pela quantidade
	 * de Economia da SubCategoria.
	 * 
	 * @author Ivan Sergio
	 * @date: 12/08/2010
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List filtrarContaCategoriaHistograma(Integer idConta) throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select " +
				"	cc.comp_id.categoria.id, " +					// 0
				"	cc.comp_id.categoria.categoriaTipo.id, " +		// 1
				"	cc.quantidadeEconomia, " +						// 2
				"	coalesce(cc.valorAgua, 0), " +					// 3
				"	coalesce(cc.consumoAgua, 0), " +				// 4
				"	coalesce(cc.consumoMinimoAgua, 0), " +			// 5
				"	coalesce(cc.valorEsgoto, 0), " +				// 6
				"	coalesce(cc.consumoEsgoto, 0), " +				// 7
				"	coalesce(cc.consumoMinimoEsgoto, 0), " +		// 8
				"	cc.comp_id.subcategoria.id " +					// 9
				"from ContaCategoria cc " +
				"	inner join cc.comp_id.categoria ct " +
				"	inner join cc.comp_id.subcategoria sct " +
				"	inner join ct.categoriaTipo ctt " +
				"where cc.comp_id.conta.id = :idConta " +
				"order by cc.quantidadeEconomia desc";
			
			retorno = session.createQuery(hql).setInteger("idConta", idConta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Verifica o consumo real do Esgoto
	 * @author Ivan Sergio
	 * @data 16/08/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarConsumoRealEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Object retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select "
					+ "  ch.consumoTipo.id "
					+ "from "
					+ "  gcom.micromedicao.consumo.ConsumoHistorico ch "
					+ "where "
					+ "  ch.imovel.id = :idImovel and "
					+ "  ch.ligacaoTipo.id = 2 and "
					+ "  ch.referenciaFaturamento = ( select "
					+ "  									anoMesFaturamento "
					+ "  								from "
					+ "  									gcom.cadastro.sistemaparametro.SistemaParametro sp )";
		
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		
		if (retorno != null) {
			return ((Integer) retorno == 1 ? 1 : 2);
		} else {
			return 2;
		}
	}
	
	/**
	 * [UC1057] - Gerar Histograma de Agua e Esgoto Sem Quadras
	 * @author Ivan Sergio
	 * @date: 19/08/2010
	 * 
	 * @param idConta
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarSubCategoriaContaCategoria(
			Integer idConta, Integer idCategoria) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = 
				"select " +
				"	cc.comp_id.subcategoria.id " +
				"from " +
				"	ContaCategoria cc " +
				"where " +
				"	cc.comp_id.conta.id = :idConta " +
				"	and cc.comp_id.categoria.id = :idCategoria " +
				"order by " +
				"	cc.quantidadeEconomia desc";
			
			retorno = (Integer) session.createQuery(consulta)
				.setInteger("idConta", idConta)
				.setInteger("idCategoria", idCategoria)
				.setMaxResults(1)
				.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaAguaLigacaoSemQuadra(Integer anoMesReferencia,
			HistogramaAguaLigacaoSemQuadraHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {
			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histo_agua_ligacao_sqdra ( "
					+ "  hals_id, "
					+ "  hals_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hals_cdsetorcomercial, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "	 scat_id, "
					+ "  hals_icligacaomista, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  last_id, "
					+ "  hals_icconsumoreal, "
					+ "  hals_ichidrometro, "
					+ "  hals_icpoco, "
					+ "  hals_icvolfixadoagua, "
					+ "  hals_qtconsumo, "
					+ "  hals_qtligacao, "
					+ "  hals_qteconomialigacao, "
					+ "  hals_vlfaturadoligacao, "
					+ "  hals_vofaturadoligacao, "
					+ "  hals_tmultimaalteracao ) "
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histo_agua_ligacao_sqdra") + ", "
					+ anoMesReferencia + ", "
					+ helper.getIdGerenciaRegional() + ", "
					+ helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", "
					+ helper.getIdLocalidade() + ", "
					+ helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdSubCategoria() + ", "
					+ helper.getIdLigacaoMista() + ", "
					+ helper.getIdContaCaregoria() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoAgua() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoAgua() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeLigacao() + ", "
					+ helper.getQuantidadeEconomiaLigacao() + ", "
					+ helper.getValorFaturadoLigacao() + ", "
					+ helper.getVolumeFaturadoLigacao() + ", "
					+ Util.obterSQLDataAtual() + " )";
			
			stmt.executeUpdate(insert);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexoes");
			}
		}
	}
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaAguaEconomimaSemQuadra(
			Integer anoMesReferencia,
			HistogramaAguaEconomiaSemQuadraHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {
			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histo_agua_econ_sqdra ( "
					+ "  haes_id, "
					+ "  haes_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  haes_cdsetorcomercial, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "	 scat_id, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  last_id, "
					+ "  haes_icconsumoreal, "
					+ "  haes_ichidrometro, "
					+ "  haes_icpoco, "
					+ "  haes_icvolfixadoagua, "
					+ "  haes_qtconsumo, "
					+ "  haes_qteconomia, "
					+ "  haes_vlfaturadoeconomia, "
					+ "  haes_vofaturadoeconomia, "
					+ "  haes_tmultimaalteracao,  "
					+ "  haes_qtligacao )"					
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histo_agua_econ_sqdra") + ", "
					+ anoMesReferencia + ", "
					+ helper.getIdGerenciaRegional() + ", "
					+ helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", "
					+ helper.getIdLocalidade() + ", "
					+ helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdSubCategoria() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoAgua() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoAgua() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeEconomia() + ", "
					+ helper.getValorFaturadoEconomia() + ", "
					+ helper.getVolumeFaturadoEconomia() + ", " + Util.obterSQLDataAtual() + " , " 
					+ helper.getQuantidadeLigacoes() +")";

			stmt.executeUpdate(insert);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaEsgotoLigacaoSemQuadra(Integer anoMesReferencia,
			HistogramaEsgotoLigacaoSemQuadraHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {
			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histo_esgt_ligacao_sqdra ( "
					+ "  hels_id, "
					+ "  hels_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hels_cdsetorcomercial, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "	 scat_id, "
					+ "  hels_icligacaomista, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  lest_id, "
					+ "  hels_icconsumoreal, "
					+ "  hels_ichidrometro, "
					+ "  hels_icpoco, "
					+ "  hels_icvolfixadoesgoto, "
					+ "  hels_nnpercentualesgoto, "
					+ "  hels_qtconsumo, "
					+ "  hels_qtligacao, "
					+ "  hels_qteconomialigacao, "
					+ "  hels_vlfaturadoligacao, "
					+ "  hels_vofaturadoligacao, "
					+ "  hels_tmultimaalteracao )"
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histo_esgt_ligacao_sqdra") + ", "
					+ anoMesReferencia + ", "
					+ helper.getIdGerenciaRegional() + ", "
					+ helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", "
					+ helper.getIdLocalidade() + ", "
					+ helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdSubCategoria() + ", "
					+ helper.getIdLigacaoMista() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoEsgoto() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoEsgoto() + ", "
					+ helper.getPercentualColetaEsgoto() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeLigacao() + ", "
					+ helper.getQuantidadeEconomiaLigacao() + ", "
					+ helper.getValorFaturadoLigacao() + ", "
					+ helper.getVolumeFaturadoLigacao() + ", " + Util.obterSQLDataAtual() +" )";

			stmt.executeUpdate(insert);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * 
	 * @author Ivan Sergio
	 * @date 18/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirHistogramaEsgotoEconomiaSemQuadra(Integer anoMesReferencia,
			HistogramaEsgotoEconomiaSemQuadraHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {
			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into "
					+ "  faturamento.histo_esgt_econ_sqdra ( "
					+ "  hees_id, "
					+ "  hees_amreferencia, "
					+ "  greg_id, "
					+ "  uneg_id, "
					+ "  loca_cdelo, "
					+ "  loca_id, "
					+ "  stcm_id, "
					+ "  hees_cdsetorcomercial, "
					+ "  cgtp_id, "
					+ "  catg_id, "
					+ "	 scat_id, "
					+ "  cstf_id, "
					+ "  iper_id, "
					+ "  epod_id, "
					+ "  lest_id, "
					+ "  hees_icconsumoreal, "
					+ "  hees_ichidrometro, "
					+ "  hees_icpoco, "
					+ "  hees_icvolfixadoesgoto, "
					+ "  hees_nnpercentualesgoto, "
					+ "  hees_qtconsumo, "
					+ "  hees_qteconomia, "
					+ "  hees_vlfaturadoeconomia, "
					+ "  hees_vofaturadoeconomia, "
					+ "  hees_tmultimaalteracao, "
					+ "  hees_qtligacao )"
					+ " values ( "
					+ Util.obterNextValSequence("faturamento.seq_histo_esgt_econ_sqdra") +", "
					+ anoMesReferencia + ", "
					+ helper.getIdGerenciaRegional() + ", "
					+ helper.getIdUnidadeNegocio() + ", "
					+ helper.getIdElo() + ", "
					+ helper.getIdLocalidade() + ", "
					+ helper.getIdSetorComercial() + ", "
					+ helper.getCodigoSetorComercial() + ", "
					+ helper.getIdTipoCategoria() + ", "
					+ helper.getIdCategoria() + ", "
					+ helper.getIdSubCategoria() + ", "
					+ helper.getIdConsumoTarifa() + ", "
					+ helper.getIdPerfilImovel() + ", "
					+ helper.getIdEsferaPoder() + ", "
					+ helper.getIdSituacaoLigacaoEsgoto() + ", "
					+ helper.getIdConsumoReal() + ", "
					+ helper.getIdExistenciaHidrometro() + ", "
					+ helper.getIdExistenciaPoco() + ", "
					+ helper.getIdExistenciaVolumeFixoEsgoto() + ", "
					+ helper.getPercentualEsgoto() + ", "
					+ helper.getQuantidadeConsumo() + ", "
					+ helper.getQuantidadeEconomia() + ", "
					+ helper.getValorFaturadoEconomia() + ", "
					+ helper.getVolumeFaturadoEconomia() + ", " + Util.obterSQLDataAtual() + " , " 
					+ helper.getQuantidadeLigacoes() + ")";

			stmt.executeUpdate(insert);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conex�es");
			}
		}
	}
	
	/**
	 * [UC1057] Gerar Histograma de Agua e Esgoto Sem Quadra
	 * [SB2000] Gerar Histograma Imoveis Nao Faturados
	 * @author Ivan Sergio
	 * @date 19/08/2010
	 * 
	 * @param idSetor
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getConsumoHistoricoImoveisNaoFaturadosSemQuadra(int idSetor, int anoMesReferencia)
			throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql =
				"select " +
				"  gr.id, " + 								// 0
				"  un.id, " + 								// 1
				"  elo.id, " + 								// 2
				"  loc.id, " + 								// 3
				"  setCom.id, " + 							// 4
				"  setCom.codigo, " + 						// 5
				"  conTar.id, " + 							// 6
				"  imoPer.id, " + 							// 7
				"  las.id, " + 								// 8
				"  imo.id, " + 								// 9
				"  les.id, " + 								// 10
				"  imo.quantidadeEconomias, " + 			// 11
				"  ligTp.id, " + 							// 12
				"  conHist.numeroConsumoFaturadoMes, " + 	// 13
				"  pocTp.id, " + 							// 14
				"  conTp.id, " + 							// 15
				"  ligEsg.percentual " + 					// 16
				"from " + 
				"  ConsumoHistorico conHist " + 
				"  inner join conHist.imovel imo " + 
				"  inner join imo.localidade loc " +
				"  inner join loc.gerenciaRegional gr " +
				"  inner join loc.unidadeNegocio un " +
				"  inner join loc.localidade elo " +
				"  inner join imo.setorComercial setCom " +
				"  inner join imo.consumoTarifa conTar " +
				"  inner join imo.imovelPerfil imoPer " +
				"  inner join imo.ligacaoAguaSituacao las " +
				"  inner join imo.ligacaoEsgotoSituacao les " +
				"  inner join conHist.ligacaoTipo ligTp " +
				"  left join conHist.pocoTipo pocTp " +
				"  inner join conHist.consumoTipo conTp " +
				"  left join imo.ligacaoEsgoto ligEsg " +
				"where " +
				"  conHist.referenciaFaturamento = :anoMesReferencia and " +
				"  conHist.indicadorFaturamento = 1 and " +
				"  conHist.indicadorImovelCondominio = 2 and " +
				"  ( not exists( from Conta con where con.imovel.id = conHist.imovel.id and con.referencia = conHist.referenciaFaturamento ) or " +
				"    conHist.indicadorFaturamento = 2 and conHist.indicadorImovelCondominio = 2 ) and " +
				"  setCom.id = :idSetor " +
				"order by " +
				"  imo.id, " +
				"  ligTp.id ";				

			retorno = session.createQuery(hql)
				.setInteger("idSetor", idSetor)
				.setInteger("anoMesReferencia", anoMesReferencia)
				.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * COSANPA - Mantis 414 - Felipe Santos - 24/10/2013
	 * 
	 * Retorna a Quantidade de Ligacoes Totais e Hidrometricas
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarLigacoesTotalHidrometricaRelatorioBIG(
			Integer anoMesReferencia, Integer idLocalidade) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {
			consulta = "SELECT coalesce(sum(ativas), 0) as ativas, coalesce(sum(hidrometricas), 0) as hidrometricas "
				+ "FROM("
				+ "SELECT coalesce(sum(rele_qtligacoes), 0) as ativas, 0 as hidrometricas "
				+ "FROM cadastro.un_res_lig_econ "
				+ "WHERE loca_id = :idLocalidade "
				+ "AND rele_amreferencia = :anoMesReferencia "
				+ "AND last_id = :situacao "
				
				+ "UNION "
				
				+ "SELECT 0 as ativas, coalesce(sum(rele_qtligacoes), 0) as hidrometricas "
				+ "FROM cadastro.un_res_lig_econ "
				+ "WHERE loca_id = :idLocalidade "
				+ "AND rele_amreferencia = :anoMesReferencia "
				+ "AND rele_ichidrometro = 1 "
				+ "AND last_id = :situacao"
				
				+ ") as lagu";
			
			retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("ativas", Hibernate.INTEGER)
				.addScalar("hidrometricas", Hibernate.INTEGER)
				.setInteger("idLocalidade", idLocalidade)
				.setInteger("anoMesReferencia", anoMesReferencia)
				.setInteger("situacao", LigacaoAguaSituacao.LIGADO)
				.setMaxResults(1)
				.uniqueResult();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
}    

 
