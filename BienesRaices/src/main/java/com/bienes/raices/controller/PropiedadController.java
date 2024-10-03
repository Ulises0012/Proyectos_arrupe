package com.bienes.raices.controller;

import com.bienes.raices.model.Propiedad;
import com.bienes.raices.repository.PropiedadRepository;
import com.bienes.raices.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/propiedades")
    public String listarPropiedades(Model model) {
        model.addAttribute("propiedades", propiedadRepository.findAll());
        return "propiedades/listar";
    }

    @GetMapping("/propiedades/nueva")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("propiedad", new Propiedad());
        return "propiedades/formulario";
    }

    @PostMapping("/propiedades/guardar")
    public String guardarPropiedad(@Valid @ModelAttribute("propiedad") Propiedad propiedad, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "propiedades/formulario";
        }

        propiedadRepository.save(propiedad);
        redirectAttributes.addFlashAttribute("mensaje", "Propiedad guardada exitosamente.");
        return "redirect:/propiedades";
    }

    @GetMapping("/propiedades/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Propiedad propiedad = propiedadRepository.findById(id).orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
        model.addAttribute("propiedad", propiedad);
        return "propiedades/formulario";
    }

    @PostMapping("/propiedades/eliminar/{id}")
    public String eliminarPropiedad(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        propiedadRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Propiedad eliminada exitosamente.");
        return "redirect:/propiedades";
    }
}
