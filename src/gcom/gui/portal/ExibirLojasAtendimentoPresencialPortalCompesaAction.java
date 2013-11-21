package gcom.gui.portal;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por Exibir as Lojas de Atendimento na Loja Virtual
 * da Compesa
 * 
 * @author Magno Gouveia
 * @date 07/07/2011
 */
public class ExibirLojasAtendimentoPresencialPortalCompesaAction extends
		GcomAction {

	private static final String ATTRIBUTE_LOCAL = "local";

	private static final String ATTRIBUTE_COLECAO_MUNICIPIOS_REGIAO_METROPOLITANA = "colecaoMunicipiosRegiaoMetropolitana";

	private static final String ATTRIBUTE_COLECAO_MUNICIPIOS_INTERIOR = "colecaoMunicipiosInterior";

	private static final int CODIGO_REGIAO_METROPOLITANA = 192;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String retorno = "exibirLojasAtendimentoPresencialPortalCompesaAction";

		// HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String local = httpServletRequest.getParameter(ATTRIBUTE_LOCAL);

		// Microrregião
		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		filtroMicrorregiao.adicionarParametro(new ParametroSimples(FiltroRegiao.ID, CODIGO_REGIAO_METROPOLITANA));
		Microrregiao microrregiao = (Microrregiao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroMicrorregiao, Microrregiao.class.getName()));

		// Municípios e Bairros
		Collection<Object[]> colecaoMunicipios = fachada.pesquisarMunicipiosLojaVirtualCompesa();

		Collection<Municipio> colecaoMunicipiosRegiaoMetropolitana = new ArrayList<Municipio>();
		Collection<Municipio> colecaoMunicipiosInterior = new ArrayList<Municipio>();

		if (!Util.isVazioOrNulo(colecaoMunicipios)) {

			for (Object[] object : colecaoMunicipios) {
				Municipio municipio = new Municipio();
				municipio.setId((Integer) object[1]);
				municipio.setNome((String) object[2]);

				// Se o município pertencer a microrregiao "REGIAO
				// METROPOLITANA"
				if (((Integer) object[0]).equals(microrregiao.getId())) {
					colecaoMunicipiosRegiaoMetropolitana.add(municipio);
				} else {
					// Interior
					colecaoMunicipiosInterior.add(municipio);
				}
			}
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum município cadastrado!");
			return actionMapping.findForward(retorno);
			// throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "MUNICIPIOS");
		}

		// Município Vazio
		Municipio municipioVazio = new Municipio();
		municipioVazio.setId(-1);
		municipioVazio.setNome(" -- NENHUM MUNICÍPIO PARA A REGIÃO SELECIONADA -- ");

		// Municípios da Região Metropolitana
		if (Util.isVazioOrNulo(colecaoMunicipiosRegiaoMetropolitana)) {
			colecaoMunicipiosRegiaoMetropolitana.add(municipioVazio);
		}
		httpServletRequest.setAttribute(ATTRIBUTE_COLECAO_MUNICIPIOS_REGIAO_METROPOLITANA, colecaoMunicipiosRegiaoMetropolitana);

		// Municípios do Interior
		if (Util.isVazioOrNulo(colecaoMunicipiosInterior)) {
			colecaoMunicipiosInterior.add(municipioVazio);
		}
		httpServletRequest.setAttribute(ATTRIBUTE_COLECAO_MUNICIPIOS_INTERIOR, colecaoMunicipiosInterior);

		if (local != null) {
			if (local.equalsIgnoreCase("regiaoMetropolitana") || local.equalsIgnoreCase("interior")) {

				httpServletRequest.setAttribute(ATTRIBUTE_LOCAL, local);

				ExibirLojasAtendimentoPresencialPortalCompesaActionForm form = (ExibirLojasAtendimentoPresencialPortalCompesaActionForm) actionForm;

				if (form.getMunicipio() != null && Util.verificarIdNaoVazio(form.getMunicipio().toString())) {

					Collection<Object[]> lojas = fachada.pesquisarLojasDeAtendimentoLojaVirtualCompesa(form.getMunicipio());

					Collection<LojaAtendimentoCompesaHelper> colecaoLojas = new ArrayList<LojaAtendimentoCompesaHelper>();

					for (Object[] object : lojas) {
						String nomeLoja = (String) object[0];
						String logradouro = (String) object[1];
						String numero = (String) object[2];
						String municipio = (String) object[3];
						String bairro = (String) object[4];
						String pontoReferencia = (String) object[5];
						String ddd = (String) object[6];
						String fone = (String) object[7];
						String fax = (String) object[8];
						String email = (String) object[9];
						Blob imagem = (Blob) object[10];
						String logradouroTipo = (String) object[11];
						String logradouroTitulo = (String) object[12];
						
						if (!Util.verificarNaoVazio(nomeLoja)) {
							nomeLoja = "Loja sem Nome";
						}
						
						if (numero == null) {
							numero = "S/N";
						}

						ddd = (ddd != null) ? "(" + ddd + ")" : "";

						fone = (fone != null) ? ddd + " " + fone : "";
						fax = (fax != null) ? ddd + " " + fax : "";

						String separador = (!fone.equals("") && !fax.equals("")) ? " / " : "";

						LojaAtendimentoCompesaHelper loja = new LojaAtendimentoCompesaHelper();
						loja.setNomeLoja(nomeLoja);
						
						logradouroTitulo = (logradouroTitulo != null)? logradouroTitulo + " " : "";

						String enderecoFormatado = logradouroTipo + " " + logradouroTitulo + logradouro + ", " + numero + ", " + bairro + " - " + municipio;
						loja.setEndereco(enderecoFormatado);
						
						loja.setPontoReferencia(pontoReferencia);
						loja.setFoneFax(fone + separador + fax);
						loja.setEmail(email);
						
						if(imagem != null){
							loja.setImagem(this.recuperaImagem(imagem, nomeLoja));
						} 

						colecaoLojas.add(loja);
					}

					httpServletRequest.setAttribute("colecaoLojas", colecaoLojas);
				}

			} else {
				throw new ActionServletException("Você deve selecionar uma das opções do menu");
			}
		}

		return actionMapping.findForward(retorno);
	}

	/*
	 * Recupera a imagem para ser exibida no jsp
	 */ 
	private String recuperaImagem(Blob imagem, String nomeLoja) {

		nomeLoja = nomeLoja.replace(" ", "_").toLowerCase();
		String imageCompletePath = "";
		
		try {
			File file = this.createTempDirectory();
			
			imageCompletePath = file.getAbsolutePath() + File.separator + nomeLoja + ".jpg";
			
			File foto = new File(imageCompletePath);
			
			if (!foto.exists()) {
				FileOutputStream fos;
				fos = new FileOutputStream(imageCompletePath);
				fos.write(this.toByteArray(imagem));
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String imagePathToImgTag = File.separator + "gsan" + File.separator + imageCompletePath.substring(imageCompletePath.indexOf("imagens"));
		
		return imagePathToImgTag.replace(File.separator, "/");
	}
	
	/*
	 * Chama o método toByteArrayImpl e trata as exceptions
	 */
	private byte[] toByteArray(Blob imagem) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(imagem, baos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	/*
	 * Método que implementa a funcionalidade de converte o Blob (formato recuperado do banco) em um array de bytes
	 */
	private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)
			throws SQLException, IOException {
		byte[] buf = new byte[4000];
		InputStream is = fromBlob.getBinaryStream();
		try {
			for (;;) {
				int dataSize = is.read(buf);

				if (dataSize == -1)
					break;
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
		return baos.toByteArray();
	}
	
	/*
	 * Cria uma pasta para colocar as imagens das lojas de atendimento
	 */
	private File createTempDirectory() throws IOException {
		File dir = new File(getServlet().getServletContext().getRealPath("imagens" + File.separator + "portal" + File.separator + "lojasatendimento"));

		if(!dir.exists()) {
			if (!(dir.mkdir())) {
				throw new IOException("Could not create temp directory: " + dir.getAbsolutePath());
			}
		}
		
		return dir;
	}

}