package gcom.api.ordemservico.bo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import gcom.api.ordemservico.dto.FotoDTO;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.enums.TipoImagem;
import gcom.fachada.Fachada;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class RequisicaoFotosOrdemServico {

	private Fachada fachada = Fachada.getInstancia();
	private List<FotoDTO> listaDTO;

	public RequisicaoFotosOrdemServico(List<FotoDTO> listaDTO) {
		super();
		this.listaDTO = listaDTO;
	}

	public void processar() throws Exception {
		for (FotoDTO dto : listaDTO) {
			OrdemServico ordemServico = new OrdemServico(dto.getOrdemServicoId(), dto.getImovelId());

			OrdemServicoFoto ordemServicoFoto = new OrdemServicoFoto(
					ordemServico,
					Util.converterStringParaDateComDataETempo(dto.getData()), 
					dto.getDescricao(),
					dto.getNome());

			byte[] arquivoEmBytes = Base64.decodeBase64(dto.getArquivo().getBytes());
			
			inserirFoto(ordemServicoFoto, arquivoEmBytes);
		}
	}
	
	private void inserirFoto(
			OrdemServicoFoto ordemServicoFoto,
			byte[] arquivoEmBytes) {
		
		File diretorioFinal = null;
		String nomeArquivo = ordemServicoFoto.getNomeFoto();

		try {
			String diretorioArquivos = fachada.getCaminhoDownloadArquivos(null);
			String subdiretorio = montarSubdiretorio(ordemServicoFoto.getOrdemServico().getImovel().getId());
			diretorioFinal = criarDiretorio(diretorioArquivos + subdiretorio);
			escreverBytesNoArquivo(arquivoEmBytes, diretorioFinal, nomeArquivo);
			ordemServicoFoto.setCaminhoFoto(subdiretorio + nomeArquivo);
			fachada.inserir(ordemServicoFoto);
		} catch (Exception e) {
			File arquivo = new File(diretorioFinal, nomeArquivo);
			if (arquivo.exists()) {
				arquivo.delete();
			}

			e.printStackTrace();
		}
	}
	
	private File criarDiretorio(String diretorio) throws ControladorException {
		File diretorioFinal = new File(diretorio.replace("//", "/"));

		if (!diretorioFinal.exists()) {
			diretorioFinal.mkdirs();
		}

		return diretorioFinal;
	}
	
	@SuppressWarnings("unchecked")
	private String montarSubdiretorio(Integer idImovel) throws ControladorException {
		Filtro filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA_ROTA);

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Imovel.class.getName()));

		String diretorioRota =  Util.completaStringComZeroAEsquerda(imovel.getLocalidade().getId() + "", 3) + "_" +
								Util.completaStringComZeroAEsquerda(imovel.getSetorComercial().getCodigo() + "", 3) + "_" +
								Util.completaStringComZeroAEsquerda(imovel.getQuadra().getRota().getCodigo() + "", 2);
		
		return "/atendimento/ordem_servico_foto/" + diretorioRota + "/";
	}
	
	private void escreverBytesNoArquivo(
			byte[] arquivoEmBytes, 
			File diretorioFinal, 
			String nomeArquivo) throws IOException {
		
		File arquivo = new File(diretorioFinal, nomeArquivo);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(arquivoEmBytes));
		ImageIO.write(img, TipoImagem.JPEG.name(), arquivo);
	}
}
