/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;

/**
 *
 * @author js.prieto10
 */
public class UsuarioDTO {
    
    private String nombre;
    
    private String apellido;
    
    private String login;
    
    private Long id;
    
    private String contrasenha;
    
    private String direccion;
    
    
    
    public UsuarioDTO()
    {
 
    }
    
    public UsuarioDTO(UsuarioEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getName();
            this.apellido = entity.getApellido();
            this.login = entity.getLogin();
            this.contrasenha = entity.getClave();
            this.direccion = entity.getDireccion();
            
                
        }  
    }
    
    /**
     * Convierte un objeto PrestamoDTO a PrestamoEntity.
     *
     * @return Nueva objeto PrestamoEntity.
     * 
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setApellido(this.apellido);
        entity.setLogin(this.login);
        entity.setClave(this.contrasenha);
        entity.setDireccion(this.direccion);
        
        return entity;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    

    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
