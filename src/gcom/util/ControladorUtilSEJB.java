package gcom.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.geografico.FiltroMunicipioFeriado;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.FiltroSegurancaParametro;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ControladorUtilSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	private IRepositorioUtil repositorioUtil;

	SessionContext sessionContext;

	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}

	public void ejbRemove() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
	public Object obterPorId(Class classe, Integer id) throws ControladorException{
		try {
			return repositorioUtil.obterPorId(classe, id);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}		
	}

	@SuppressWarnings("rawtypes")
	public int registroMaximo(Class classe) throws ControladorException {
		try {
			return repositorioUtil.registroMaximo(classe);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo) throws ControladorException {
		try {
			return repositorioUtil.valorMaximo(classe, atributo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public int valorMaximo(Class classe, String atributo, String parametro1, String parametro2) throws ControladorException {
		try {
			return repositorioUtil.valorMaximo(classe, atributo, parametro1, parametro2);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public SistemaParametro pesquisarParametrosDoSistema() throws ControladorException {
		try {
			return repositorioUtil.pesquisarParametrosDoSistema();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	@SuppressWarnings("rawtypes")
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro, String pacoteNomeObjeto, int limite) throws ControladorException {
		try {
			return repositorioUtil.limiteMaximoFiltroPesquisa(filtro, pacoteNomeObjeto, limite);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Object inserir(Object objeto) throws ControladorException {
		try {

			Object sequence = null;
			Integer id = null;

			if (objeto instanceof Imovel) {
				sequence = repositorioUtil.obterNextVal(objeto);

				id = Util.obterDigitoVerificadorModulo11(((Integer) sequence).toString());

				((Imovel) objeto).setId(new Integer((((Integer) sequence).toString() + id.toString())));
				((Imovel) objeto).setCodigoDebitoAutomatico(new Integer((((Integer) sequence).toString() + id.toString())));

			} else if (objeto instanceof Cliente) {
				sequence = repositorioUtil.obterNextVal(objeto);

				id = Util.obterDigitoVerificadorModulo11(((Integer) sequence).toString());
				((Cliente) objeto).setId(new Integer((((Integer) sequence).toString() + id.toString())));
			}

			return repositorioUtil.inserir(objeto);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Collection ids, Filtro filtro, String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisar(ids, filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public void remover(String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ControladorException {

		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				int id = Integer.parseInt(ids[i]);

				try {
					repositorioUtil.remover(id, pacoteNomeObjeto, operacaoEfetuada, acaoUsuarioHelper);

				} catch (RemocaoRegistroNaoExistenteException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.registro_remocao_nao_existente", ex);
				} catch (RemocaoInvalidaException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.dependencias.existentes", ex);
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				}
			}
		}
	}

	public void removerUm(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {
		try {
			repositorioUtil.remover(id, pacoteNomeObjeto, operacaoEfetuada, acaoUsuarioHelper);
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente", ex);
		} catch (RemocaoInvalidaException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes", ex);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * FAVOR NÃO USAR!!! Método para ser utilizada apenas em logradouro
	 * (atualizar)
	 */
	public void verificaObjetoRemocao(int id, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {
		try {
			repositorioUtil.remover(id, pacoteNomeObjeto, operacaoEfetuada, acaoUsuarioHelper);
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente", ex);
		} catch (RemocaoInvalidaException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes", ex);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		sessionContext.setRollbackOnly();
	}

	public void remover(Object object) throws ControladorException {
		try {
			repositorioUtil.remover(object);
		} catch (RemocaoRegistroNaoExistenteException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente", ex);
		} catch (RemocaoInvalidaException ex1) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.dependencias.existentes", ex1);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	public Object inserirOuAtualizar(Object objeto) throws ControladorException {
		try {
			return repositorioUtil.inserirOuAtualizar(objeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public void atualizar(Object objeto) throws ControladorException {
		try {
			repositorioUtil.atualizar(objeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void validarCampoFinalMaiorIgualCampoInicial(Date inicio, Date fim, String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.after(fim)) {
					throw new ControladorException(msgErro);
				}

			}
		}

	}

	public void validarCampoFinalMaiorIgualCampoInicial(Integer inicio, Integer fim, String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.compareTo(fim) == 1) {
					throw new ControladorException(msgErro);
				}

			}
		}
	}

	public void validarCampoFinalMaiorIgualCampoInicial(BigDecimal inicio, BigDecimal fim, String msgErro) throws ControladorException {

		if (inicio != null && fim != null) {
			if (!inicio.equals("") && !fim.equals("")) {

				if (inicio.compareTo(fim) == 1) {
					throw new ControladorException(msgErro);
				}
			}
		}
	}

	public void validarDataMenorDataAtual(Date data, String msgErro) throws ControladorException {

		Date dataAtual = new Date();

		if (data != null) {
			if (!data.equals("")) {

				if (data.compareTo(dataAtual) == 1) {
					throw new ControladorException(msgErro, null, Util.formatarData(dataAtual));
				}
			}
		}
	}

	public void validarAnoMesMenorAnoMesAtual(Integer anoMes, String msgErro) throws ControladorException {

		Date dataAtual = new Date();
		Integer anoMesAtual = Util.recuperaAnoMesDaData(dataAtual);

		if (anoMes != null) {
			if (!anoMes.equals("")) {

				if (anoMes.compareTo(anoMesAtual) == 1) {
					throw new ControladorException(msgErro);
				}

			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, int pageOffset, String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtro, pageOffset, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.totalRegistrosPesquisa(filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public void validarAnoMesInicialFinalPeriodo(String anoMesReferenciaInicial, String anoMesReferenciaFinal, String descricaoCampoAnoMesReferenciaInicial,
			String descricaoAnoMesReferenciaFinal, String mensagemErroDoApplicationProperties) throws ControladorException {

		if ((anoMesReferenciaInicial != null && !anoMesReferenciaInicial.equals("")) && (anoMesReferenciaFinal != null && !anoMesReferenciaFinal.equals(""))) {
			if (anoMesReferenciaInicial.length() == 7 & anoMesReferenciaFinal.length() == 7) {

				String anoInicial = anoMesReferenciaInicial.substring(3, 7);
				String mesInicial = anoMesReferenciaInicial.substring(0, 2);

				String anoFinal = anoMesReferenciaFinal.substring(3, 7);
				String mesFinal = anoMesReferenciaFinal.substring(0, 2);

				boolean valida = Util.validarAnoMes(anoMesReferenciaInicial);
				if (valida) {
					throw new ControladorException("errors.invalid", null, descricaoCampoAnoMesReferenciaInicial);
				}

				valida = Util.validarAnoMes(anoMesReferenciaFinal);
				if (valida) {
					throw new ControladorException("errors.invalid", null, descricaoAnoMesReferenciaFinal);
				}

				Calendar periodoInicial = new GregorianCalendar();
				periodoInicial.set(Calendar.DATE, 1);
				periodoInicial.set(Calendar.MONTH, (new Integer(mesInicial).intValue() - 1));
				periodoInicial.set(Calendar.YEAR, new Integer(anoInicial).intValue());

				Calendar periodoFinal = new GregorianCalendar();
				periodoFinal.set(Calendar.DATE, 1);
				periodoFinal.set(Calendar.MONTH, (new Integer(mesFinal).intValue() - 1));
				periodoFinal.set(Calendar.YEAR, new Integer(anoFinal).intValue());

				if (periodoInicial.compareTo(periodoFinal) > 0) {
					throw new ControladorException(mensagemErroDoApplicationProperties);
				}
			} else {
				if (anoMesReferenciaInicial.length() < 7) {
					throw new ControladorException("errors.invalid", null, descricaoCampoAnoMesReferenciaInicial);
				}

				if (anoMesReferenciaFinal.length() < 7) {
					throw new ControladorException("errors.invalid", null, descricaoAnoMesReferenciaFinal);
				}

			}
		}

	}

	public void verificarDataInicialFinalPeriodo(String dataPeriodoInicial, String dataPeriodoFinal, String descricaoCampoDataReferenciaInicial,
			String descricaoDataReferenciaFinal, String mensagemErroDoApplicationProperties) throws ControladorException {

		if ((dataPeriodoInicial != null && !dataPeriodoInicial.equals("")) && (dataPeriodoFinal != null && !dataPeriodoFinal.equals(""))) {

			if (dataPeriodoInicial.length() == 10 & dataPeriodoFinal.length() == 10) {
				String anoInicial = dataPeriodoInicial.substring(6, 10);
				String mesInicial = dataPeriodoInicial.substring(3, 5);
				String diaInicial = dataPeriodoInicial.substring(0, 2);

				String anoFinal = dataPeriodoFinal.substring(6, 10);
				String mesFinal = dataPeriodoFinal.substring(3, 5);
				String diaFinal = dataPeriodoFinal.substring(0, 2);

				boolean valida = Util.validarDiaMesAno(dataPeriodoInicial);
				if (valida) {
					throw new ControladorException("errors.invalid", null, descricaoCampoDataReferenciaInicial);
				}
				valida = Util.validarDiaMesAno(dataPeriodoFinal);
				if (valida) {
					throw new ControladorException("errors.invalid", null, descricaoDataReferenciaFinal);
				}

				Calendar periodoInicial = new GregorianCalendar();
				periodoInicial.set(Calendar.DATE, new Integer(diaInicial).intValue());
				periodoInicial.set(Calendar.MONTH, (new Integer(mesInicial).intValue() - 1));
				periodoInicial.set(Calendar.YEAR, new Integer(anoInicial).intValue());

				Calendar periodoFinal = new GregorianCalendar();
				periodoFinal.set(Calendar.DATE, new Integer(diaFinal).intValue());
				periodoFinal.set(Calendar.MONTH, (new Integer(mesFinal).intValue() - 1));
				periodoFinal.set(Calendar.YEAR, new Integer(anoFinal).intValue());

				if (periodoInicial.compareTo(periodoFinal) > 0) {
					throw new ControladorException(mensagemErroDoApplicationProperties);
				}
			} else {
				if (dataPeriodoInicial.length() < 10) {
					throw new ControladorException("errors.invalid", null, descricaoCampoDataReferenciaInicial);
				}

				if (dataPeriodoFinal.length() < 10) {
					throw new ControladorException("errors.invalid", null, descricaoDataReferenciaFinal);
				}

			}
		}

	}

	@SuppressWarnings("rawtypes")
	public void inserirBatch(List list) throws ControladorException {
		try {
			repositorioUtil.inserirBatch(list);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto, String nomeTabela) throws ControladorException {
		Collection colecao = null;
		try {
			colecao = repositorioUtil.pesquisar(filtro, pacoteNomeObjeto);

			if (colecao == null || colecao.isEmpty()) {
				throw new ControladorException("atencao.entidade_sem_dados_para_selecao", null, nomeTabela);
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecao;
	}

	public Collection<NacionalFeriado> pesquisarFeriadosNacionais() throws ControladorException {
		try {
			return repositorioUtil.pesquisarFeriadosNacionais();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public void atualizarSistemaParametro(SistemaParametro sistemaParametro) throws ControladorException {

		if (sistemaParametro != null) {

			SistemaParametro sistemaParametroNaBase = this.pesquisarParametrosDoSistema();

			if ((sistemaParametroNaBase.getUltimaAlteracao().after(sistemaParametro.getUltimaAlteracao()))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
			sistemaParametro.setUltimaAlteracao(new Date());
		}
		this.atualizar(sistemaParametro);
	}

	public DbVersaoBase pesquisarDbVersaoBase() throws ControladorException {
		try {
			return repositorioUtil.pesquisarDbVersaoBase();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Integer, Map<Object, Object>> dividirColecao(Collection colecao) {

		Map<Integer, Map<Object, Object>> mapOrdenada = new HashMap();
		List listColecao = new ArrayList();
		listColecao.addAll(colecao);
		int quantidadeContas = 0;
		int quantidadeContasColecao = listColecao.size();
		int metadeColecao = 0;

		if (quantidadeContasColecao % 2 == 0) {
			metadeColecao = quantidadeContasColecao / 2;
		} else {
			metadeColecao = (quantidadeContasColecao / 2) + 1;
		}
		while (quantidadeContas < metadeColecao) {
			Map<Object, Object> map = new HashMap();
			Object object1 = (Object) listColecao.get(quantidadeContas);
			Object object2 = null;
			if (metadeColecao + quantidadeContas < quantidadeContasColecao) {
				object2 = (Object) listColecao.get(metadeColecao + quantidadeContas);
			}
			map.put(object1, object2);
			mapOrdenada.put(quantidadeContas, map);
			map = null;

			quantidadeContas++;
		}
		quantidadeContasColecao = 0;

		return mapOrdenada;
	}

	public void mandaArquivoLeituraEmail(String nomeArquivo, StringBuilder arquivo, String emailReceptor, String emailRemetente, String tituloMensagem,
			String corpoMensagem) throws ControladorException {
		try {
			File leitura = File.createTempFile(getCaminhoDownloadArquivos("") + nomeArquivo, ".txt");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
			out.write(arquivo.toString());
			out.close();

			File compactado = new File(getCaminhoDownloadArquivos("") + nomeArquivo + ".zip"); // nomeZip
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compactado));
			ZipUtil.adicionarArquivo(zos, leitura);

			zos.close();

			ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor, emailRemetente, tituloMensagem, corpoMensagem, compactado);

			leitura.delete();

		} catch (IOException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Integer calcularDiferencaDiasUteisEntreDuasDatas(Date dataInicio, Date dataFim, Municipio municipio) throws ControladorException {

		Integer qtdDiasUteis = 0;

		Collection<NacionalFeriado> colecaoFeriadosNacionais = null;

		try {
			colecaoFeriadosNacionais = repositorioUtil.pesquisarFeriadosNacionais();
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();
		filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(FiltroMunicipioFeriado.ID_MUNICIPIO, municipio.getId()));

		Collection<MunicipioFeriado> colecaoFeriadosMunicipais = this.pesquisar(filtroMunicipioFeriado, MunicipioFeriado.class.getName());

		Calendar dataInicioCalendar = new GregorianCalendar();
		dataInicioCalendar.setTime(dataInicio);

		while (dataInicioCalendar.getTime().compareTo(Util.formatarDataSemHora(dataFim)) < 0) {

			if (Util.ehDiaUtil(Util.formatarDataSemHora(dataInicioCalendar.getTime()), colecaoFeriadosNacionais, colecaoFeriadosMunicipais)) {

				qtdDiasUteis++;
			}

			dataInicioCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return qtdDiasUteis;
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto) throws ControladorException {
		try {
			return repositorioUtil.pesquisarGerencial(filtro, pacoteNomeObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Object inserirComCommit(Object objeto) throws ControladorException {
		try {
			return repositorioUtil.inserir(objeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String getCaminhoDownloadArquivos(String modulo) {
		Filtro filtro = new FiltroSegurancaParametro();
		filtro.adicionarParametro(new ParametroSimples(FiltroSegurancaParametro.NOME, SegurancaParametro.NOME_PARAMETRO_SEGURANCA.CAMINHO_ARQUIVOS.toString()));

		Collection parametros = Fachada.getInstancia().pesquisar(filtro, SegurancaParametro.class.getName());

		SegurancaParametro parametro = (SegurancaParametro) parametros.iterator().next();
		
		String caminho = parametro.getValor() + "/";
		
		if (modulo != null) {
			caminho += modulo + "/";
		}
		
		return caminho;
	}
	
	public Collection listar(Class tipo) throws ControladorException{
        try {
            return repositorioUtil.listar(tipo);
        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }
	}
	
	public void salvarArquivoZip(StringBuilder arquivo, String nomeZip, String diretorio) throws ControladorException {
		
		File leituraTipo = new File(this.getCaminhoDownloadArquivos(diretorio) +nomeZip + ".txt");
		File compactadoTipo = new File(this.getCaminhoDownloadArquivos(diretorio) + nomeZip + ".zip");

		try {
			ZipUtil.salvarArquivoZip(arquivo, nomeZip, compactadoTipo, leituraTipo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ControladorException("Erro ao salvar arquivo zip: " + compactadoTipo.getAbsolutePath(), e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("Erro ao salvar arquivo zip: " + compactadoTipo.getAbsolutePath(), e);
		}
	}
}
