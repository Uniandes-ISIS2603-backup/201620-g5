
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.ejbs.PrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.PrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.PrestamoPersistence;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
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
public class PrestamoLogicTest {


    UsuarioEntity usuarioEntity;

    UsuarioEntity usuarioEntityFail;
    BibliotecaEntity bibliotecaEntity;

    LibroEntity libroEntity;
    LibroEntity libroEntityFail;
    
    MultaEntity multaEntity;
    
    ArrayList<MultaEntity> multas = new ArrayList<>();


    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private IPrestamoLogic prestamoLogic;

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
    private List<PrestamoEntity> prestamoData = new ArrayList<PrestamoEntity>();




    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(PrestamoLogic.class.getPackage())
                .addPackage(IPrestamoLogic.class.getPackage())
                .addPackage(PrestamoPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(VideoEntity.class.getPackage())
                .addPackage(LibroEntity.class.getPackage())

                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public PrestamoLogicTest() {
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

        em.createQuery("delete from PrestamoEntity").executeUpdate();
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
        em.persist(usuarioEntity);
        
        multaEntity = factory.manufacturePojo(MultaEntity.class);
        em.persist(multaEntity);
        multas.add(multaEntity);
        
        usuarioEntityFail = factory.manufacturePojo(UsuarioEntity.class);
        usuarioEntityFail.setMultas(multas);
        em.persist(usuarioEntityFail);
        
        em.flush();
        UsuarioEntity usuario = em.find(UsuarioEntity.class, usuarioEntityFail.getId());
        usuarioEntityFail = usuario;
        
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        em.persist(bibliotecaEntity);
        
        libroEntity = factory.manufacturePojo(LibroEntity.class);
        em.persist(libroEntity);
        
        libroEntityFail = factory.manufacturePojo(LibroEntity.class);
        libroEntityFail.setEjemplaresDisponibles(0);
        libroEntityFail.setTipoRecurso(LibroEntity.LIBRO);
        em.persist(libroEntityFail);
        for (int i = 0; i < 3; i++) {
            PrestamoEntity entity = factory.manufacturePojo(PrestamoEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            entity.setUsuario(usuarioEntity);
            entity.setRecurso(libroEntity);
            em.persist(entity);
            prestamoData.add(entity);

            
        }
    }

    /**
     * Prueba para crear un Department.
     *
     *
     */
    @Test
    public void createPrestamoTest1() throws BibliotecaLogicException{
        PrestamoEntity newEntity = factory.manufacturePojo(PrestamoEntity.class);
        PrestamoEntity result = prestamoLogic.createPrestamo(newEntity, bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntity.getId());
        Assert.assertNotNull(result);
        PrestamoEntity entity = em.find(PrestamoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear un Prestamo con un costo negativo.
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createPrestamoTest2() throws BibliotecaLogicException {
        PrestamoEntity prest = factory.manufacturePojo(PrestamoEntity.class);
        prest.setCosto(-2.0);
        PrestamoEntity result = prestamoLogic.createPrestamo(prest,bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntity.getId());
    }
    /**
     * Prueba para crear un Prestamo con un recurso con 0 unidades disponibles.
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createPrestamoTest3() throws BibliotecaLogicException {
        PrestamoEntity prest = factory.manufacturePojo(PrestamoEntity.class);


        PrestamoEntity result = prestamoLogic.createPrestamo(prest,bibliotecaEntity.getId(), libroEntityFail.getTipoRecurso(), libroEntityFail.getId(), usuarioEntity.getId());
    }
    
    /**
     * Prueba para crear un Prestamo con un usuario con multas
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     
    @Test(expected = BibliotecaLogicException.class)
    public void createPrestamoTest4() throws BibliotecaLogicException {
        PrestamoEntity prest = factory.manufacturePojo(PrestamoEntity.class);

        PrestamoEntity result = prestamoLogic.createPrestamo(prest,bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntityFail.getId());
    }
   
    /**
     * Prueba para consultar la lista de Departments
     *
     *
     */
    @Test
    public void getPrestamosTest() {
        List<PrestamoEntity> list = prestamoLogic.getPrestamos();
        Assert.assertEquals(prestamoData.size(), list.size());
        for (PrestamoEntity entity : list) {
            boolean found = false;
            for (PrestamoEntity storedEntity : prestamoData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un prestamo
     *
     *
     */
    @Test
    public void getPrestamoTest() {
        PrestamoEntity entity = prestamoData.get(0);
        PrestamoEntity resultEntity = prestamoLogic.getPrestamo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un prestamo
     *
     *
     */
    @Test
    public void deletePrestamoTest() throws BibliotecaLogicException, Exception {
        PrestamoEntity entity = prestamoData.get(1);
        prestamoLogic.deletePrestamo(entity.getId());
        PrestamoEntity deleted = em.find(PrestamoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una reserva
     *
     *
     */
    @Test
    public void updateReservaTest() throws BibliotecaLogicException {
        PrestamoEntity entity = prestamoData.get(0);
        PrestamoEntity pojoEntity = factory.manufacturePojo(PrestamoEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setRecurso(libroEntity);
        pojoEntity.setBiblioteca(bibliotecaEntity);
        pojoEntity.setUsuario(usuarioEntity);

        prestamoLogic.updatePrestamo(pojoEntity,bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntity.getId());

        PrestamoEntity resp = em.find(PrestamoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}

