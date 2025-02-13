package com.example.demo.magasin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommandeRepository commandeRepository;
    
    // Ajoute un article à une commande identifiée par son ID.
     
    @PostMapping("/add")
    public ResponseEntity<?> addArticle(@RequestParam Long commandeId, @RequestBody Article article) {
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeId);
        if (commandeOpt.isPresent()) {
            article.setCommande(commandeOpt.get());
            Article savedArticle = articleService.addArticle(article);
            return ResponseEntity.ok(savedArticle);
        }
        return ResponseEntity.badRequest().body("Commande non trouvée");
    }
    
    // Supprime un article spécifié par son identifiant.
     
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        Optional<Article> articleOpt = articleService.getArticleById(id);
        if (articleOpt.isPresent()) {
            articleService.deleteArticle(id);
            return ResponseEntity.ok("Article supprimé avec succès.");
        }
        return ResponseEntity.notFound().build();
    }
}
