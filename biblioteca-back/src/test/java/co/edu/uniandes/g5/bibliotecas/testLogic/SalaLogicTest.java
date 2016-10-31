/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.SalaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.SalaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author sf.munera10
 */
@RunWith(Arquillian.class)
public class SalaLogicTest {
    
    /**
     *
     */
    BibliotecaEntity bibliotecaEntity;

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private ISalaLogic salaLogic;

    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<SalaEntity> salaData = new ArrayList<SalaEntity>();

    /**
     *
     */
    private List<BibliotecaEntity> bibliotecaData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaLogic.class.getPackage())
                .addPackage(ISalaLogic.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);
        em.persist(bibliotecaEntity);
        for (int i = 0; i < 3; i++) {
            SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            em.persist(entity);
            salaData.add(entity);
        }
    }

    /**
     * Test of getSalas method, of class SalaLogic.
     */
    @Test
    public void testGetSalas() throws Exception {
        List<SalaEntity> list = salaLogic.getSalas(bibliotecaEntity.getId());
        Assert.assertEquals(salaData.size(), list.size());
        for (SalaEntity entity : list) {
            boolean found = false;
            for (SalaEntity storedEntity : salaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getSala method, of class SalaLogic.
     */
    @Test
    public void testGetSala() throws Exception {
        SalaEntity entity = salaData.get(0);
        SalaEntity resultEntity = salaLogic.getSala(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Test of createSala method, of class SalaLogic.
     */
    @Test
    public void testCreateSala() throws Exception {
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
        SalaEntity result = salaLogic.createSala(bibliotecaEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        SalaEntity entity = em.find(SalaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = BibliotecaLogicException.class)
    public void testCreateSala2() throws Exception {
        SalaEntity dept = factory.manufacturePojo(SalaEntity.class);
        dept.setBiblioteca(bibliotecaEntity);
        dept.setName(salaData.get(0).getName());
        SalaEntity result = salaLogic.createSala(salaData.get(0).getBiblioteca().getId(), dept);
    }

    /**
     * Test of updateSala method, of class SalaLogic.
     */
    @Test
    public void testUpdateSala() throws Exception {
        SalaEntity entity = salaData.get(0);
        SalaEntity pojoEntity = factory.manufacturePojo(SalaEntity.class);

        pojoEntity.setId(entity.getId());

        salaLogic.updateSala(bibliotecaEntity.getId(), pojoEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    /**
     * Test of deleteSala method, of class SalaLogic.
     */
    @Test
    public void testDeleteSala() throws Exception {
        SalaEntity entity = salaData.get(1);
        salaLogic.deleteSala(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
