/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.prieto10
 */
@XmlRootElement
public class BlogDetailDTO extends BlogDTO{
    
    @PodamExclude
    private LibroDTO libro;
    
    
    public BlogDetailDTO()
    {
        super();
    }
    
    public BlogDetailDTO(BlogEntity entity)
    {
        super(entity);
        if (entity.getLibro() != null)
            this.libro = new LibroDTO(entity.getLibro());
    }
    
    
    public BlogEntity toEntity()
    {
        BlogEntity entity = super.toEntity();
        if (this.getLibro() != null)
            entity.setLibro(this.getLibro().toEntity());
        return entity;
    }
    
    
    public LibroDTO getLibro()
    {
        return libro;
    }
    
    public void setLibro(LibroDTO libro)
    {
        this.libro = libro;
    }
    
}
