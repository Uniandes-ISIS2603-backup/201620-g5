
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.ejbs.PrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.PrestamoPersistence;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import java.util.List;

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

    BibliotecaEntity bibliotecaEntity;

    LibroEntity libroEntity;
    
    VideoEntity videoEntity;
    
    SalaEntity salaEntity;

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
    private List<UsuarioEntity> usuarioData = new ArrayList<>();
    
    private List<VideoEntity> videoData = new ArrayList<>();
    
    private List<SalaEntity> salaData = new ArrayList<>();

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
        usuarioEntity.setId(1L);
        em.persist(usuarioEntity);
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);
        em.persist(bibliotecaEntity);
        libroEntity = factory.manufacturePojo(LibroEntity.class);
        libroEntity.setId(1L);
        em.persist(libroEntity);
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
        PrestamoEntity result = prestamoLogic.createPrestamo(newEntity);
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
        prest.setBiblioteca(bibliotecaEntity);
        prest.setRecurso(libroEntity);
        prest.setUsuario(usuarioEntity);
        prest.setCosto(-2.0);
        PrestamoEntity result = prestamoLogic.createPrestamo(prest);
    }
    /**
     * Prueba para crear un Prestamo con un recurso con 0 unidades disponibles.
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createPrestamoTest3() throws BibliotecaLogicException {
        PrestamoEntity prest = factory.manufacturePojo(PrestamoEntity.class);
        prest.setBiblioteca(bibliotecaEntity);
        libroEntity.setCantidadDisponible(0L);
        prest.setRecurso(libroEntity);
        prest.setUsuario(usuarioEntity);
        
        PrestamoEntity result = prestamoLogic.createPrestamo(prest);
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
    public void deletePrestamoTest() throws BibliotecaLogicException {
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

        prestamoLogic.updatePrestamo(pojoEntity);

        PrestamoEntity resp = em.find(PrestamoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

  
}
