package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Client;
import com.example.demo.services.ClientItf;
import com.example.demo.services.CommandeItf;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class ClientController {
    
    @Autowired
    private ClientItf service;
    
    @Autowired
    private CommandeItf commandeService;

    @GetMapping("/home")
    public String home() {
        return "/store/home";
    }

    @PostMapping("/create")
    public RedirectView create(@RequestParam String email, @RequestParam String password, @RequestParam String nom, @RequestParam String prenom) {
        service.create(email, password, nom, prenom);
        return new RedirectView("/store/home");
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Client client = service.findByEmailAndPassword(email, password);
        if (client != null) {
            session.setAttribute("email", email); 
            return new RedirectView("/store/client");
        }
        return new RedirectView("/store/home");
    }
    
    @GetMapping("/client")
    public ModelAndView clientPage(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("/store/home");
        }
        
        Client client = service.findByEmail(email);
        var commandes = commandeService.findAllByClient(client); 

        var model = Map.of(
            "client", email,
            "commandes", commandes
        );
        return new ModelAndView("/store/client", model);
    }


    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
    	session.removeAttribute("email");
        session.invalidate(); 
        return new RedirectView("/store/home");
    }
}
