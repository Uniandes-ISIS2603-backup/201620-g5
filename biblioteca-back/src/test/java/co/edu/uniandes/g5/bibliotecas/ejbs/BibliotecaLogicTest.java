/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author sf.munera10
 */
@RunWith(Arquillian.class)
public class BibliotecaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IBibliotecaLogic bibliotecaLogic;
    /**
     *
     */
    @Inject
    private SalaPersistence salaPersistence;
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
    private List<BibliotecaEntity> data = new ArrayList<BibliotecaEntity>();

    private List<RecursoEntity> recursoData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(BibliotecaLogic.class.getPackage())
                .addPackage(IBibliotecaLogic.class.getPackage())
                .addPackage(BibliotecaPersistence.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaLogic.class.getPackage())
                .addPackage(RecursoEntity.class.getPackage())
                .addPackage(ISalaLogic.class.getPackage())
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
        em.createQuery("delete from RecursoEntity").executeUpdate();
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

        for (int i = 0; i < 3; i++) {
            RecursoEntity employees = factory.manufacturePojo(RecursoEntity.class);
            em.persist(employees);
            recursoData.add(employees);
        }

        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            for (SalaEntity d : entity.getSalas()) {
                d.setBiblioteca(entity);
            }
            em.persist(entity);
            data.add(entity);
            
            if (i == 0) {
                recursoData.get(i).setBiblioteca(entity);
            }
        }
    }

    /**
     * Test of getBibliotecas method, of class BibliotecaLogic.
     */
    @Test
    public void testGetBibliotecas() throws Exception {
        List<BibliotecaEntity> list = bibliotecaLogic.getBibliotecas();
        Assert.assertEquals(data.size(), list.size());
        for (BibliotecaEntity entity : list) {
            boolean found = false;
            for (BibliotecaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getBiblioteca method, of class BibliotecaLogic.
     */
    @Test
    public void testGetBiblioteca() throws Exception {
        BibliotecaEntity entity = data.get(0);
        BibliotecaEntity resultEntity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Test of createBiblioteca method, of class BibliotecaLogic.
     */
    @Test
    public void testCreateBiblioteca() throws Exception {
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        for (SalaEntity d : newEntity.getSalas()) {
            d.setBiblioteca(newEntity);
        }

        BibliotecaEntity result = bibliotecaLogic.createBiblioteca(newEntity);
        Assert.assertNotNull(result);

        BibliotecaEntity entity = em.find(BibliotecaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getSalas());
        Assert.assertNotNull(result.getSalas());
        Assert.assertEquals(result.getSalas().size(), entity.getSalas().size());

        for (SalaEntity d : result.getSalas()) {
            boolean found = false;
            for (SalaEntity oracle : entity.getSalas()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }

    }

    @Test(expected = BibliotecaLogicException.class)
    public void testCreateBiblioteca2() {
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        newEntity.setName(data.get(0).getName());
        BibliotecaEntity result = bibliotecaLogic.createBiblioteca(newEntity);
    }

    /**
     * Test of updateBiblioteca method, of class BibliotecaLogic.
     */
    @Test
    public void testUpdateBiblioteca() throws Exception {
        BibliotecaEntity entity = data.get(0);
        BibliotecaEntity pojoEntity = factory.manufacturePojo(BibliotecaEntity.class);

        pojoEntity.setId(entity.getId());

        bibliotecaLogic.updateBiblioteca(pojoEntity);

        BibliotecaEntity resp = em.find(BibliotecaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    /**
     * Test of deleteBiblioteca method, of class BibliotecaLogic.
     */
    @Test
    public void testDeleteBiblioteca() throws Exception {
        BibliotecaEntity entity = data.get(1);
        bibliotecaLogic.deleteBiblioteca(entity.getId());
        BibliotecaEntity deleted = em.find(BibliotecaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of listRecursos method, of class BibliotecaLogic.
     */
    @Test
    public void testListRecursos() throws Exception {
        List<RecursoEntity> list = bibliotecaLogic.listRecursos(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * Test of getRecurso method, of class BibliotecaLogic.
     */
    @Test
    public void testGetRecurso() throws Exception {
        BibliotecaEntity entity = data.get(0);
        RecursoEntity employeeEntity = recursoData.get(0);
        RecursoEntity response = bibliotecaLogic.getRecurso(entity.getId(), employeeEntity.getId());

        Assert.assertEquals(employeeEntity.getName(), response.getName());
        Assert.assertEquals(employeeEntity.getId(), response.getId());
    }

    /**
     * Test of addRecurso method, of class BibliotecaLogic.
     */
    @Test
    public void testAddRecurso() throws Exception {
        BibliotecaEntity entity = data.get(0);
        RecursoEntity employeeEntity = recursoData.get(1);
        RecursoEntity response = bibliotecaLogic.addRecurso(entity.getId(), employeeEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(employeeEntity.getId(), response.getId());
    }

    /**
     * Test of replaceRecursos method, of class BibliotecaLogic.
     */
    @Test
    public void testReplaceRecursos() throws Exception {
        BibliotecaEntity entity = data.get(0);
        List<RecursoEntity> list = recursoData.subList(1, 3);
        bibliotecaLogic.replaceRecursos(entity.getId(), list);

        entity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertFalse(entity.getRecursos().contains(recursoData.get(0)));
        Assert.assertTrue(entity.getRecursos().contains(recursoData.get(1)));
        Assert.assertTrue(entity.getRecursos().contains(recursoData.get(2)));
    }

    /**
     * Test of removeRecurso method, of class BibliotecaLogic.
     */
    @Test
    public void testRemoveRecurso() throws Exception {
        bibliotecaLogic.removeRecurso(data.get(0).getId(), recursoData.get(0).getId());
        RecursoEntity response = bibliotecaLogic.getRecurso(data.get(0).getId(), recursoData.get(0).getId());
        Assert.assertNull(response);
    }

}
