/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class PrestamoPersistenceTest {
    
    public PrestamoPersistenceTest() {
    }
    
    /**
     * 
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(PrestamoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PrestamoPersistence prestamoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject 
    UserTransaction utx;
    
    
    
    private List<PrestamoEntity> data = new ArrayList<PrestamoEntity>();
    
   
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
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
    
    @After
    public void tearDown() {
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba
     */
    private void clearData(){
        em.createQuery("delete from PrestamoEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
        em.createQuery("delete from RecursoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento
     * de las pruebas
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i =0; i<3; i++){
            PrestamoEntity entity = factory.manufacturePojo(PrestamoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Test of find method, of class PrestamoPersistence.
     */
    @Test
    public void testGetPrestamo() throws Exception {
        PrestamoEntity entity = data.get(0);
        PrestamoEntity newEntity = prestamoPersistence.getPrestamo(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAllInUsuario method, of class PrestamoPersistence.
     */
    @Test
    public void testGetPrestamosByUsuario() throws Exception {
        PrestamoEntity entity = data.get(0);
        PrestamoEntity newEntity = prestamoPersistence.getPrestamosByUsuario(entity.getUsuario().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAllInBiblioteca method, of class PrestamoPersistence.
     */
    @Test
    public void testGetPrestamosByBiblioteca() throws Exception {
        PrestamoEntity entity = data.get(0);
        PrestamoEntity newEntity = prestamoPersistence.getPrestamosByBiblioteca(entity.getBiblioteca().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());

    }

    /**
     * Test of findAllInRecurso method, of class PrestamoPersistence.
     */
    @Test
    public void testGetPrestamosByRecurso() throws Exception {
        PrestamoEntity entity = data.get(0);
        PrestamoEntity newEntity = prestamoPersistence.getPrestamosByRecurso(entity.getRecurso().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());      
    }

    /**
     * Test of findAll method, of class PrestamoPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        fail("testFindAll");

    }

    /**
     * Test of create method, of class PrestamoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        fail("testCreate");

    }

    /**
     * Test of update method, of class PrestamoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        fail("testUpdate");
    }

    /**
     * Test of delete method, of class PrestamoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        fail("testDelete");
    }
    
}
