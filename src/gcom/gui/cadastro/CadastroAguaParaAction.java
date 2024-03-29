package gcom.gui.cadastro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.cobranca.DebitoCreditoDadosSelecaoExtratoActionForm;
import gcom.gui.portal.AguaParaActionForm;
import gcom.util.ConstantesSistema;


public class CadastroAguaParaAction extends GcomAction {
	
private static Logger logger = Logger.getLogger(CadastroAguaParaAction.class);
private String caminhoJboss = System.getProperty("jboss.server.home.dir");
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		CadastroAguaPara cadastroAguaPara = new CadastroAguaPara();
		ActionForward retorno = new ActionForward();
		Fachada fachada = Fachada.getInstancia();		
		
		AguaParaActionForm form = (AguaParaActionForm) actionForm;
		
		String cpf = form.getCpf();
		Integer idImovel = Integer.parseInt(form.getIdImovel());
		String nis = form.getNis();
		String nome = form.getNome();
		String rg = form.getRg();
		String telefone = form.getTelefone();
		
		if(telefone.length()<15)
			throw new ActionServletException("atencao.erro_telefone_invalido");
		
		if(ehVazia(cpf)||ehVazia(nis)||ehVazia(telefone)||ehVazia(rg)||ehVazia(nome)||ehVazia(form.getIdImovel())) 
		    throw new ActionServletException("atencao.erro_um_dos_campos_nao_foi_preenchido");
	
		Imovel imovel = fachada.pesquisarImovelDigitado(idImovel);
		
		if(imovel == null)
		    throw new ActionServletException("atencao.erro_imovel_nao_encontrado");
		
		cadastroAguaPara.setCpf(cpf);
		cadastroAguaPara.setImovel(imovel);
		cadastroAguaPara.setRg(rg);
		cadastroAguaPara.setNome(nome);
		cadastroAguaPara.setNumeroNIS(nis);
		cadastroAguaPara.setTelefone(telefone);
		cadastroAguaPara.setSituacao(CadastroAguaPara.PENDENTE);
		
		if(!fachada.pesquisarCpfCadastroAguaPara(cpf)) {		
			throw new ActionServletException("atencao.erro_cpf_ja_cadastrado");
		}
		
		if(!fachada.pesquisarNisCadastroAguaPara(nis)) {		
			throw new ActionServletException("atencao.erro_nis_ja_cadastrado");
		}
		
		FormFile arquivoRg =  form.getArquivoRg();
		FormFile arquivoCpf =  form.getArquivoCpf();
		FormFile arquivoConta =  form.getArquivoConta();
		FormFile arquivoBolsaFamiliaNis =  form.getArquivoBolsaFamiliaNis();
		
		String extensaoRG = pegaExtensaoArquivo(arquivoRg.toString());
		String extensaoCpf = pegaExtensaoArquivo(arquivoCpf.toString());
		String extensaoConta = pegaExtensaoArquivo(arquivoConta.toString());
		String extensaoBolsaFamiliaNis = pegaExtensaoArquivo(arquivoBolsaFamiliaNis.toString());
		
		validarExtencao(extensaoRG, "RG");
		validarExtencao(extensaoCpf, "Cpf");
		validarExtencao(extensaoConta, "Conta");
		validarExtencao(extensaoBolsaFamiliaNis, "Bolsa Familia ou NIS");
		
		renomearArquivosESalvar(form.getCpf(), "_RG"+extensaoRG, arquivoRg);
		renomearArquivosESalvar(form.getCpf(), "_Cpf"+extensaoCpf, arquivoCpf);
		renomearArquivosESalvar(form.getCpf(), "_Conta"+extensaoConta, arquivoConta);
		renomearArquivosESalvar(form.getCpf(), "_BolsaFamiliaNis"+extensaoBolsaFamiliaNis, arquivoBolsaFamiliaNis);

		
		fachada.inserir(cadastroAguaPara);	
		
		retorno.setName("sucesso-agua-para");
		retorno.setPath("/jsp/portal/sucesso_agua_para.jsp");
		retorno.setRedirect(false);
		retorno.setContextRelative(false);
		return retorno;
		
	}
	
	public String pegaExtensaoArquivo(String filename) {
	    int index = filename.lastIndexOf('.');

	    if (index == -1) {
	        return "";
	    } else {
	        return filename.substring(index);
	    }
	}


	private void salvarArquivoZip(FormFile arquivoCarregado, String nomeArquivo, String cpf) {
		try {
			File pasta = new File(caminhoJboss + "/arquivos/portal/cadastro_agua_para", cpf);

			if (!pasta.exists()) {
				pasta.mkdir();
			} else {
				File arquivos[] = pasta.listFiles();

				for (File arquivo : arquivos) {
					if (nomeArquivo.equals(arquivo.getName())) {
						arquivo.delete();
					}
				}
			}

			byte tamanho[] = arquivoCarregado.getFileData();
			File arquivoZip = new File(pasta, nomeArquivo);
			OutputStream out = new FileOutputStream(arquivoZip);
			out.write(tamanho);
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ActionServletException("atencao.erro_salvar_arquivo_agua_para", nomeArquivo);
		}
	}
	
	private void renomearArquivosESalvar(String cpf, String tipo, FormFile arquivo) {	
		if(ehVazia(arquivo.getFileName())) {
			throw new ActionServletException("atencao.erro_salvar_arquivo_agua_para_tipo", tipo);
		}
		FormFile arquivo1 = arquivo;	
		String nomeArquivo = cpf + tipo;
		this.salvarArquivoZip(arquivo1, nomeArquivo, cpf);
		
	}
	
	private Boolean ehVazia(String variavel) {
		return variavel==null||variavel.equals(""); 
	}
	
	private void validarExtencao(String extencao, String tipoArquivo) {
		if (!extencao.equals(".jpg") && !extencao.equals(".png") && !extencao.equals(".pdf") && !extencao.equals(".jpeg")
				&& !extencao.equals(".heic")) {
			throw new ActionServletException("atencao.erro_salvar_arquivo_agua_para_formato", extencao, tipoArquivo);
		}
	}
	
}
