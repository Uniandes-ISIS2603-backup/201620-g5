
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.ReservaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.ReservaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.ReservaPersistence;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
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
public class ReservaLogicTest {


    UsuarioEntity usuarioEntity;
    

    BibliotecaEntity bibliotecaEntity;

    LibroEntity libroEntity;
    LibroEntity libroEntityFail;
    
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
    private IReservaLogic reservaLogic;
    
    @Inject
    private ILibroLogic libroLogic;

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
    private List<ReservaEntity> reservaData = new ArrayList<ReservaEntity>();

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
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
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

        em.createQuery("delete from ReservaEntity").executeUpdate();
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
        libroEntityFail = factory.manufacturePojo(LibroEntity.class);
        libroEntityFail.setEjemplaresDisponibles(0);
        libroEntityFail.setId(2L);
        libroEntityFail.setTipoRecurso(LibroEntity.LIBRO);
        em.persist(libroEntityFail);
        bibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        bibliotecaEntity.setId(1L);

        em.persist(bibliotecaEntity);
        libroEntity = factory.manufacturePojo(LibroEntity.class);
        libroEntity.setId(1L);
        em.persist(libroEntity);
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            entity.setUsuario(usuarioEntity);
            entity.setRecurso(libroEntity);
            em.persist(entity);
            reservaData.add(entity);

            
        }
    }

    /**
     * Prueba para crear un Department.
     *
     *
     */
    @Test
    public void createReservaTest1() throws BibliotecaLogicException{
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaLogic.createReserva(newEntity,bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntity.getId());
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear un Reserva con un recurso con 0 unidades disponibles.
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createReservaTest2() throws BibliotecaLogicException {
        ReservaEntity prest = factory.manufacturePojo(ReservaEntity.class);
        
        ReservaEntity result = reservaLogic.createReserva(prest,bibliotecaEntity.getId(), libroEntityFail.getTipoRecurso(), libroEntityFail.getId(), usuarioEntity.getId());
    }
   
    /**
     * Prueba para consultar la lista de Departments
     *
     *
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaLogic.getReservas();
        Assert.assertEquals(reservaData.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity storedEntity : reservaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un reserva
     *
     *
     */
    @Test
    public void getReservaTest() {
        ReservaEntity entity = reservaData.get(0);
        ReservaEntity resultEntity = reservaLogic.getReserva(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un reserva
     *
     *
     */
    @Test
    public void deleteReservaTest() throws BibliotecaLogicException, Exception {
        ReservaEntity entity = reservaData.get(1);
        reservaLogic.deleteReserva(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una reserva
     *
     *
     */
    @Test
    public void updateReservaTest() throws BibliotecaLogicException {
        ReservaEntity entity = reservaData.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setRecurso(libroEntity);
        pojoEntity.setBiblioteca(bibliotecaEntity);
        pojoEntity.setUsuario(usuarioEntity);

        reservaLogic.updateReserva(pojoEntity,bibliotecaEntity.getId(), libroEntity.getTipoRecurso(), libroEntity.getId(), usuarioEntity.getId());

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}

