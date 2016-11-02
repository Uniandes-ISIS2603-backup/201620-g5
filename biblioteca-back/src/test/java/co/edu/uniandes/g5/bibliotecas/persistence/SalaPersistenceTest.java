/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 *
 * @author sf.munera10
 */
@RunWith(Arquillian.class)
public class SalaPersistenceTest {
    
   /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    BibliotecaEntity bibliotecaEntity;

    @Inject
    private SalaPersistence salaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SalaEntity> tuplas = new ArrayList<SalaEntity>();

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
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);
        em.persist(bibliotecaEntity);
        for (int i = 0; i < 3; i++) {
            SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            tuplas.add(entity);
            em.persist(entity);
        }
    }
    /**
     * Test of find method, of class SalaPersistence.
     */
    @Test
    public void testFind() throws Exception {
        SalaEntity entity = tuplas.get(0);
        SalaEntity newEntity = salaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class SalaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<SalaEntity> list = salaPersistence.findAll();
        Assert.assertEquals(tuplas.size(), list.size());
        for (SalaEntity ent : list) {
            boolean found = false;
            for (SalaEntity entity : tuplas) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of findAllInSala method, of class SalaPersistence.
     */
    @Test
    public void testFindAllInBiblioteca() throws Exception {
        List<SalaEntity> list = salaPersistence.findAllInBiblioteca(bibliotecaEntity.getId());
        Assert.assertEquals(tuplas.size(), list.size());
        for (SalaEntity ent : list) {
            boolean found = false;
            for (SalaEntity entity : tuplas) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class SalaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);

        SalaEntity result = salaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        SalaEntity entity = em.find(SalaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class SalaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        SalaEntity entity = tuplas.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);

        newEntity.setId(entity.getId());

        salaPersistence.update(newEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class SalaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        SalaEntity entity = tuplas.get(0);
        salaPersistence.delete(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
