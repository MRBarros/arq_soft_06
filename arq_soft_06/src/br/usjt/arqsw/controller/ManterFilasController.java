package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.FilaService;

/** @authora Marina Barros
 * 	816155372
 **/

@Transactional
@Controller("/fila")
public class ManterFilasController {
	private FilaService filaService;

	@Autowired
	public ManterFilasController(FilaService fs) {
		filaService = fs;
	}

	/**
	 * @return
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	/**
	 * 
	 * @param model
	 *            Acessa a  request http
	 * @return JSP de Listar Chamados
	 */
	@RequestMapping("/listar_filas")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "FilaListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/novaFila")
	public String novoChamado(Model model) {
		return "NovaFila";
	}

	@RequestMapping("/salvarFila")
	public String salvarChamado(Fila fila, BindingResult result, Model model) {

		try {
			System.out.println(fila.getNome());
			filaService.novaFila(fila);
			model.addAttribute("filas", listarFilas());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FilaListar";
	}
	
	@RequestMapping("/excluir_fila")
	public String excluirChamado(int id, Model model) {
		try {
			Fila fila = new Fila();
			fila.setId(id);
			filaService.excluirFila(fila);
			model.addAttribute("filas", listarFilas());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FilaListar"; 
	}
	
	
}
