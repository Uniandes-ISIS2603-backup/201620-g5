
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.ejbs.MultaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IMultaLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.MultaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.MultaPersistence;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
 
/**
 *
 */
@RunWith(Arquillian.class)
public class MultaLogicTest {


    UsuarioEntity usuarioEntity;

    BibliotecaEntity bibliotecaEntity;

    LibroEntity libroEntity;
    
    

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private IMultaLogic multaLogic;

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
    private List<MultaEntity> multaData = new ArrayList<MultaEntity>();



    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MultaEntity.class.getPackage())
                .addPackage(MultaLogic.class.getPackage())
                .addPackage(IMultaLogic.class.getPackage())
                .addPackage(MultaPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(LibroEntity.class.getPackage())

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

        em.createQuery("delete from MultaEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        usuarioEntity = factory.manufacturePojo(UsuarioEntity.class);
        usuarioEntity.setId(1L);
        em.persist(usuarioEntity);
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);
        em.persist(bibliotecaEntity);
        libroEntity = factory.manufacturePojo(LibroEntity.class);
        libroEntity.setId(1L);
        em.persist(libroEntity);
        for (int i = 0; i < 3; i++) {
            MultaEntity entity = factory.manufacturePojo(MultaEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            entity.setUsuario(usuarioEntity);
            entity.setRecurso(libroEntity);
            em.persist(entity);
            multaData.add(entity);

            
        }
    }

    /**
     * Prueba para crear un Department.
     *
     *
     */
    @Test
    public void createMultaTest1() throws BibliotecaLogicException{
        MultaEntity newEntity = factory.manufacturePojo(MultaEntity.class);
        newEntity.setRecurso(libroEntity);
        MultaEntity result = multaLogic.createMulta(newEntity,1L,1L,1L,1);
        Assert.assertNotNull(result);
        MultaEntity entity = em.find(MultaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear un Multa con un costo negativo.
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createMultaTest2() throws BibliotecaLogicException {
        MultaEntity prest = factory.manufacturePojo(MultaEntity.class);
        prest.setCosto(-2.0);
        MultaEntity result = multaLogic.createMulta(prest,1L,1L,1L,2);
    }
    /**
     * Prueba para crear un Multa con un recurso con 0 unidades disponibles.
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     
    @Test(expected = BibliotecaLogicException.class)
    public void createMultaTest3() throws BibliotecaLogicException {
        MultaEntity prest = factory.manufacturePojo(MultaEntity.class);
      
        libroEntity.setTipoRecurso(LibroEntity.LIBRO);
        libroEntity.setEjemplaresDisponibles(0);
        


        MultaEntity result = multaLogic.createMulta(prest,1L,1L,1L,2);
    }
    */
   
    /**
     * Prueba para consultar la lista de Departments
     *
     *
     */
    @Test
    public void getMultasTest() {
        List<MultaEntity> list = multaLogic.getMultas();
        Assert.assertEquals(multaData.size(), list.size());
        for (MultaEntity entity : list) {
            boolean found = false;
            for (MultaEntity storedEntity : multaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un multa
     *
     *
     */
    @Test
    public void getMultaTest() {
        MultaEntity entity = multaData.get(0);
        MultaEntity resultEntity = multaLogic.getMulta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un multa
     *
     *
     */
    @Test
    public void deleteMultaTest() throws BibliotecaLogicException, Exception {
        MultaEntity entity = multaData.get(1);
        multaLogic.deleteMulta(entity.getId());
        MultaEntity deleted = em.find(MultaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una reserva
     *
     *
     */
    @Test
    public void updateReservaTest() throws BibliotecaLogicException {
        MultaEntity entity = multaData.get(0);
        MultaEntity pojoEntity = factory.manufacturePojo(MultaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setRecurso(libroEntity);
        pojoEntity.setBiblioteca(bibliotecaEntity);
        pojoEntity.setUsuario(usuarioEntity);

        multaLogic.updateMulta(pojoEntity);

        MultaEntity resp = em.find(MultaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}

