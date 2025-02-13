package com.example.demo.magasin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    
    // Ajoute un article à une commande.
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }
    
    // Retourne la liste des articles pour une commande donnée.
    public List<Article> getArticlesByCommande(Commande commande) {
        return articleRepository.findByCommande(commande);
    }

    // Retourne un article par son identifiant.
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    // Supprime un article par son identifiant.
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
