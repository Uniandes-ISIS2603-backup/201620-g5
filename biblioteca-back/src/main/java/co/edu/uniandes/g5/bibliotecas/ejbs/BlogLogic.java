/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBlogLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.BlogPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author js.prieto10
 */
public class BlogLogic implements IBlogLogic {
    
    @Inject
    private BlogPersistence persistence;

    @Override
    public List<BlogEntity> getBlogs() {
        return persistence.findAll();
    }

    @Override
    public BlogEntity getBlog(Long id) {
        return persistence.find(id);
    }

    @Override
    public BlogEntity createBlog(BlogEntity entity) {
        return persistence.create(entity);
    }

    @Override
    public BlogEntity updateBlog(BlogEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteBlog(Long id) {
        persistence.delete(id);
    }
    
}
