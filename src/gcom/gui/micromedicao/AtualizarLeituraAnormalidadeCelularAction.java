package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.relatorio.micromedicao.RelatorioResumoProblemasLeiturasAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Thiago Nascimento
 * 
 */
public class AtualizarLeituraAnormalidadeCelularAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null/* actionMapping.findForward("telaSucesso") */;
		String nomeArquivo = null;
		StringBuffer arquivo = new StringBuffer();
		Integer localidade = null;
		Empresa empresa = null;
		Integer grupo = null;
		Usuario usuario = null;
		try {
			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);

			FileItem item = null;
			Fachada fachada = Fachada.getInstancia();

			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				item = (FileItem) iter.next();

				// verifica se não é diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					if (nomeItem.endsWith(".TXT")) {
						nomeArquivo = nomeItem;
						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item
								.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);
//						Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();
						Vector<DadosMovimentacao> v = fachada.gerarVetorDadosParaLeitura(buffer);
						
						while (buffer.ready()) {
							String linha = buffer.readLine();
							arquivo.append(linha);
							arquivo.append(System.getProperty("line.separator"));
							
						}
						
						if (v.isEmpty()) {
							// Arquivo texto Vazio
							throw new ActionServletException(
							"atencao.arquivo_sem_dados",null, nomeItem);
						}
						localidade = v.get(0).getLocalidade();
						grupo = v.get(0).getGrupoFaturamento();
						
						FiltroFaturamentoGrupo  filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
						filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupo));
						
						Collection colecaoFaturamentoGupo = fachada.pesquisar(filtroFaturamentoGrupo,FaturamentoGrupo.class.getName());
						
						FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFaturamentoGupo);
						
						Rota rota = null;
						
						if(faturamentoGrupo != null){
							rota = fachada.buscarRotaPorMatriculaImovel(v.get(0).getMatriculaImovel(),faturamentoGrupo.getAnoMesReferencia());
						}
						
						
						if(rota == null){
							FiltroRota filtroRota = new FiltroRota();
							filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
							filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURISTA);
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,localidade));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, v.get(0).getSetorComercial()));
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, v.get(0).getCodigoRota()));
							
							Collection colecao = fachada.pesquisar(filtroRota,Rota.class.getName());
							
							if(colecao!=null && !colecao.isEmpty() && colecao.size()==1){
								rota = (Rota)colecao.iterator().next();
							}
						}
						
						empresa = fachada.buscarEmpresaPorMatriculaImovel(v.get(0).getMatriculaImovel());
						usuario = (Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado");
						
						Leiturista leiturista = null;
						
						if(rota!=null 
								&& rota.getLeiturista()!=null){
							leiturista = rota.getLeiturista();
						}
						
						byte[] relatorio =  fachada.atualizarLeituraAnormalidadeCelular(v,usuario, nomeItem, leiturista);
						OutputStream out = null;
						if (relatorio != null) {								
						
							//fachada.efetuarRateioDeConsumoPorRota(rota, rota.getFaturamentoGrupo().getAnoMesReferencia());
															
							
							httpServletResponse
									.setContentType("application/pdf");
							out = httpServletResponse.getOutputStream();
							out.write(relatorio);
							out.flush();
							out.close();
						}
					}
					
				}
			}

		} catch (FileUploadException e) {
			throw new ActionServletException("atencao.arquivo_nao_encontrado");
		} catch (IOException e) {
			throw new ActionServletException("atencao.arquivo_nao_encontrado");
		} catch(NumberFormatException e){
			
			this.gerarRelatorioProblema("Arquivo com Dados Inválidos", 
				grupo, 
				empresa,
				localidade, 
				nomeArquivo, 
				arquivo.toString().getBytes(),
				httpServletResponse, 
				usuario, "");
			
		}catch (StringIndexOutOfBoundsException e) {
			this.gerarRelatorioProblema("Arquivo com registros de tamanho Inválido", grupo, empresa,
					localidade, nomeArquivo, arquivo.toString().getBytes(),
					httpServletResponse, usuario, "");
				
		}catch (FachadaException e) {
			e.printStackTrace();
			String setor = "";
			List<String> parametros = e.getParametroMensagem();
			if(parametros != null && !parametros.isEmpty()){
				setor = parametros.get(0);
			}			
			this.gerarRelatorioProblema(e.getMessage(), grupo, empresa,
					localidade, nomeArquivo, arquivo.toString().getBytes(),
					httpServletResponse, usuario, setor);
		}

		return retorno;
	}
	
	/**
	 * 
	 * @param erro
	 * @param grupo
	 * @param empresa
	 * @param localidade
	 * @param nomeArquivo
	 * @param arquivo
	 * @param httpServletResponse
	 * @param usuario
	 * @param setor
	 */
	private void gerarRelatorioProblema(String erro, Integer grupo, Empresa empresa,
			Integer localidade, String nomeArquivo, byte[] arquivo,
			HttpServletResponse httpServletResponse, Usuario usuario, String setor) {
		try{
			RelatorioResumoProblemasLeiturasAnormalidade relatorio = new RelatorioResumoProblemasLeiturasAnormalidade(usuario);
			
			if(grupo != null){
				relatorio.addParametro("grupo", grupo);
			}else{
				relatorio.addParametro("grupo", "");
			}
			
			if(empresa != null){
				relatorio.addParametro("codigoEmpresa", empresa.getId());
				relatorio.addParametro("empresa", empresa.getDescricao());
			}else{
				relatorio.addParametro("codigoEmpresa", "");
				relatorio.addParametro("empresa", "");
			}
			if(localidade != null){
				relatorio.addParametro("localidade", localidade);
			}else{
				relatorio.addParametro("localidade", "");
			}
			if(setor !=null){
				relatorio.addParametro("setor", setor);
			}else{
				relatorio.addParametro("setor", "");
			}
		
			relatorio.addParametro("erro", erro);
			
			Fachada.getInstancia().enviarEmailProblemasRegistrarConsistir(
					nomeArquivo, arquivo);
			httpServletResponse
			.setContentType("application/pdf");
			OutputStream out;
			out = httpServletResponse.getOutputStream();
			out.write((byte[])relatorio.executar());
			out.flush();
			out.close();
	} catch (IOException e1) {
		throw new ActionServletException(
		"erro.sistema");
	}
}
	
}
