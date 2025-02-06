package com.example.demo.magasin;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Page d'accueil
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("/home");
    }

    // Création de compte
    @PostMapping("/register")
    public RedirectView register(@RequestParam String email, 
                                 @RequestParam String mdp, 
                                 @RequestParam String nom, 
                                 @RequestParam String prenom) {
        try {
            clientService.register(email, mdp, nom, prenom);
            return new RedirectView("/home"); // Redirection vers la page d'accueil après inscription
        } catch (RuntimeException e) {
            return new RedirectView("/home?error=" + e.getMessage()); // Message d'erreur si l'email est déjà utilisé
        }
    }

    // Page d'utilisateur
    @GetMapping("/client")
    public ModelAndView client() {
        return new ModelAndView("/client");
    }

    // Authentification
    @PostMapping("/authenticate")
    public RedirectView authenticate(@RequestParam String email, 
                                     @RequestParam String mdp, 
                                     HttpSession session) {
        try {
            Optional<Client> client = clientService.authenticate(email, mdp);
            session.setAttribute("client", client.get());  // Enregistrer le client dans la session
            return new RedirectView("/client"); // Redirection vers la page client après authentification réussie
        } catch (RuntimeException e) {
            return new RedirectView("/home?error=" + e.getMessage()); // Message d'erreur si l'authentification échoue
        }
    }

    // Déconnexion
    @PostMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();  // Invalidée la session pour déconnecter le client
        return new RedirectView("/home"); // Rediriger vers la page d'accueil après déconnexion
    }
}
