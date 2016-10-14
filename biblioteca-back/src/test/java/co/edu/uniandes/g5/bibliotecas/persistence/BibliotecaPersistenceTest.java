/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;


/**
 *
 * @author sf.munera10
 */
@RunWith(Arquillian.class)
public class BibliotecaPersistenceTest {
    
    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(BibliotecaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private BibliotecaPersistence bibliotecaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<BibliotecaEntity> tuplas = new ArrayList<BibliotecaEntity>();

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            tuplas.add(entity);
        }
    }

    /**
     * Prueba para crear un Biblioteca.
     */
    @Test
    public void createBibliotecaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);

        BibliotecaEntity result = bibliotecaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        BibliotecaEntity entity = em.find(BibliotecaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Bibliotecas.
     *
     *
     */
    @Test
    public void getBibliotecasTest() {
        List<BibliotecaEntity> list = bibliotecaPersistence.findAll();
        Assert.assertEquals(tuplas.size(), list.size());
        for (BibliotecaEntity ent : list) {
            boolean found = false;
            for (BibliotecaEntity entity : tuplas) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Biblioteca.
     */
    @Test
    public void getBibliotecaTest() {
        BibliotecaEntity entity = tuplas.get(0);
        BibliotecaEntity newEntity = bibliotecaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para consultar una Biblioteca.
     */
    @Test
    public void getBibliotecaByNameTest() {
        BibliotecaEntity entity = tuplas.get(0);
        BibliotecaEntity newEntity = bibliotecaPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Biblioteca.
     */
    @Test
    public void deleteBibliotecaTest() {
        BibliotecaEntity entity = tuplas.get(0);
        bibliotecaPersistence.delete(entity.getId());
        BibliotecaEntity deleted = em.find(BibliotecaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    
    

    /**
     * Prueba para actualizar un Biblioteca.
     */
    @Test
    public void updateBibliotecaTest() {
        BibliotecaEntity entity = tuplas.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);

        newEntity.setId(entity.getId());

        bibliotecaPersistence.update(newEntity);

        BibliotecaEntity resp = em.find(BibliotecaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
