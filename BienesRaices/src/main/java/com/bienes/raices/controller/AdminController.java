package com.bienes.raices.controller;

import com.bienes.raices.model.Propiedad;
import com.bienes.raices.repository.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/propiedades")
public class AdminController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @GetMapping
    public String listarPropiedades(Model model) {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        model.addAttribute("propiedades", propiedades);
        return "admin/propiedades";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("propiedad", new Propiedad());
        return "admin/crear_propiedad";
    }

    @PostMapping("/crear")
    public String crearPropiedad(@ModelAttribute("propiedad") Propiedad propiedad) {
        propiedadRepository.save(propiedad);
        return "redirect:/admin/propiedades";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid propiedad Id:" + id));
        model.addAttribute("propiedad", propiedad);
        return "admin/editar_propiedad";
    }

    @PostMapping("/editar/{id}")
    public String editarPropiedad(@PathVariable("id") Long id, @ModelAttribute("propiedad") Propiedad propiedad) {
        propiedad.setId(id);
        propiedadRepository.save(propiedad);
        return "redirect:/admin/propiedades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPropiedad(@PathVariable("id") Long id) {
        propiedadRepository.deleteById(id);
        return "redirect:/admin/propiedades";
    }
}
