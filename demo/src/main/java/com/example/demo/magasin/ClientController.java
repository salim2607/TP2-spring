package com.example.demo.magasin;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Page d'accueil (connexion et inscription)
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home"); // Affiche home.html
    }

    // Création de compte
    @PostMapping("/register")
    public RedirectView register(@RequestParam String email, 
                                 @RequestParam String mdp, 
                                 @RequestParam String nom, 
                                 @RequestParam String prenom,
                                 RedirectAttributes redirectAttributes) {
        try {
            clientService.register(email, mdp, nom, prenom);
            redirectAttributes.addFlashAttribute("success", "Compte créé avec succès !");
            return new RedirectView("/home");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new RedirectView("/home");
        }
    }

    // Page utilisateur
    @GetMapping("/client")
    public ModelAndView client(HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        if (clientId == null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("client");
    }

    // Authentification
    @PostMapping("/authenticate")
    public RedirectView authenticate(@RequestParam String email, 
                                     @RequestParam String mdp, 
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Optional<Client> client = clientService.authenticate(email, mdp);
        if (client.isPresent()) {
            session.setAttribute("clientId", client.get().getId());
            return new RedirectView("/client");
        } else {
            redirectAttributes.addFlashAttribute("error", "Identifiants incorrects");
            return new RedirectView("/home");
        }
    }

    // Déconnexion
    @PostMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/home");
    }
}
