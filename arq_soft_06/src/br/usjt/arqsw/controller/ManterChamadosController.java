package br.usjt.arqsw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/** @authora Marina Barros
 * 	816155372
 **/

@Transactional
@Controller("/chamado")
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosController(FilaService fs, ChamadoService cs) {
		filaService = fs;
		chamadoService = cs;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("chamado/index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
	
	/**
	 * @return JSP de Listar Chamados
	 */
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/novoChamado")
	public String novoChamado(Model model) {
		try {
			Chamado chamado = new Chamado();
			model.addAttribute("filas", listarFilas());
			chamado.setDescricao("TESTAND");
			model.addAttribute("chamado", chamado);
			return "NovoChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	@RequestMapping("/excluir_chamado")
	public String excluirChamado(int id, Model model) {
		try {
			Chamado chamado = new Chamado();
			model.addAttribute("filas", listarFilas());
			chamado.setDescricao("TESTAND");
			model.addAttribute("chamado", chamado);
			return "NovoChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	@RequestMapping("/salvarChamado")
	public String salvarChamado(Fila fila, Chamado chamado, BindingResult result, Model model) {
		try {
			System.out.println(fila.getId());
			//Pegando data atual e convertendo 
			chamado.setFila(fila);
			//Criar Chamado
			chamadoService.novoChamado(chamado);
			//Passar uma lista de todos chamados
			List<Chamado> chamados = chamadoService.listarChamados(fila);
			model.addAttribute("chamados", chamados);

		} catch (IOException e) {
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "Erro";
		}
		return "ChamadoListarExibir";
	}
	
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);

			// TODO  carregar os chamados
			List<Chamado> chamados = chamadoService.listarChamados(fila);
			model.addAttribute("chamados", chamados);
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
