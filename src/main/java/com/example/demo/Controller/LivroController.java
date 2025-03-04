package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "lista_livros";
    }

    @GetMapping("/novo")
    public String exibirFormularioAdicao(Model model) {
        model.addAttribute("livro", new Livro());
        return "formulario_livro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }
}