/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;


import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.runner.RunWith;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.arquillian.container.test.api.Deployment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author ce.gonzalez13
 */
@RunWith(Arquillian.class)
public class MultaPersistenceTest {
    
    public MultaPersistenceTest() {
    }
    
    /**
     * 
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MultaEntity.class.getPackage())
                .addPackage(MultaPersistence.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(RecursoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private MultaPersistence multaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    @Inject 
    UserTransaction utx;
    
    
    
    private List<MultaEntity> data = new ArrayList<MultaEntity>();
    /**
     * Usuario que contiene los multas.
     */
    private UsuarioEntity usuarioEntity;

    /**
     * Biblioteca que contiene los multas.
     */
    private BibliotecaEntity bibliotecaEntity;
    
     /**
     * Recurso que contiene los multas.
     */
    private LibroEntity libroEntity;
    
    
     /**
     * Configuración inicial de la prueba
     */
    @Before
    public void setUp()
    {
        try
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch(Exception e1){
                 e1.printStackTrace();
            fail("configuration data base fail");
            }
           
        }
    }
    
   
    
    /**
     * Limpia las tablas que están implicadas en la prueba
     */
    private void clearData(){
        em.createQuery("delete from MultaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento
     * de las pruebas
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        usuarioEntity = factory.manufacturePojo(UsuarioEntity.class);
        usuarioEntity.setId(1L);
        em.persist(usuarioEntity);
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);
        em.persist(bibliotecaEntity);
        libroEntity = factory.manufacturePojo(LibroEntity.class);
        libroEntity.setId(1L);
        em.persist(libroEntity);
        for(int i =0; i<3; i++){
            MultaEntity entity = factory.manufacturePojo(MultaEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            entity.setUsuario(usuarioEntity);
            entity.setRecurso(libroEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
   
    
    
    /**
     * Test of create method, of class MultaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MultaEntity newEntity = factory.manufacturePojo(MultaEntity.class);
        newEntity.setBiblioteca(bibliotecaEntity);
        newEntity.setUsuario(usuarioEntity);
        newEntity.setRecurso(libroEntity);
        MultaEntity result = multaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MultaEntity entity = em.find(MultaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of find method, of class MultaPersistence.
     */
    @Test
    public void testGetMulta() throws Exception {
        MultaEntity entity = data.get(0);
        MultaEntity newEntity = multaPersistence.getMulta(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
        /**
     * Test of findAll method, of class MultaPersistence.
     */
    @Test
    public void testGetMultas() throws Exception {
         List<MultaEntity> list = multaPersistence.getMultas();
        Assert.assertEquals(data.size(), list.size());
        for (MultaEntity ent : list) {
            boolean found = false;
            for (MultaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

    }

    /**
     * Test of findAllInUsuario method, of class MultaPersistence.
     */
    @Test
    public void testGetMultasByUsuario() throws Exception {
        List<MultaEntity> list = multaPersistence.getByUsuario(usuarioEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MultaEntity ent : list) {
            boolean found = false;
            for (MultaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }


    /**
     * Test of findAllInBiblioteca method, of class MultaPersistence.
     */
    @Test
    public void testGetMultasByBiblioteca() throws Exception 
    {
       List<MultaEntity> list = multaPersistence.getMultasByBiblioteca(bibliotecaEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MultaEntity ent : list) {
            boolean found = false;
            for (MultaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }


    

    /**
     * Test of findAllInRecurso method, of class MultaPersistence.
     */
    @Test
    public void testGetMultasByRecurso() throws Exception {
         
       List<MultaEntity> list = multaPersistence.getMultasByRecurso(libroEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MultaEntity ent : list) {
            boolean found = false;
            for (MultaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }



    

    /**
     * Test of update method, of class MultaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        MultaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MultaEntity newEntity = factory.manufacturePojo(MultaEntity.class);

        newEntity.setId(entity.getId());

        multaPersistence.update(newEntity);

        MultaEntity resp = em.find(MultaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of delete method, of class MultaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
       MultaEntity entity = data.get(0);
        multaPersistence.delete(entity.getId());
        MultaEntity deleted = em.find(MultaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
