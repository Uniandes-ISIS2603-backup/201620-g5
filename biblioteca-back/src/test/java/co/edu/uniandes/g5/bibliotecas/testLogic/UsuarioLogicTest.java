
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.UsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.persistence.UsuarioPersistence;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
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
public class UsuarioLogicTest {


    UsuarioEntity usuarioEntity;

    BibliotecaEntity bibliotecaEntity;

    
    

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private IUsuarioLogic usuarioLogic;

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
    private List<UsuarioEntity> usuarioData = new ArrayList<UsuarioEntity>();


    
    


    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(IUsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
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

        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
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
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setBiblioteca(bibliotecaEntity);
            em.persist(entity);
            usuarioData.add(entity);

            
        }
    }

    /**
     * Prueba para crear un Department.
     *
     *
     */
    @Test
    public void createUsuarioTest1() throws BibliotecaLogicException{
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioLogic.createUsuario(newEntity);
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear un Usuario que ya existe.
     */
    @Test(expected = BibliotecaLogicException.class)
    public void createUsuarioTest2() throws BibliotecaLogicException {
        UsuarioEntity prest = factory.manufacturePojo(UsuarioEntity.class);
        prest.setBiblioteca(bibliotecaEntity);
        prest.setId(usuarioData.get(0).getId());
        UsuarioEntity result = usuarioLogic.createUsuario(prest);
    }
   
    /**
     * Prueba para consultar la lista de Departments
     *
     *
     */
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = usuarioLogic.getUsuarios();
        Assert.assertEquals(usuarioData.size(), list.size());
        for (UsuarioEntity entity : list) {
            boolean found = false;
            for (UsuarioEntity storedEntity : usuarioData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un usuario
     *
     *
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = usuarioData.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getUsuario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un usuario
     *
     *
     */
    @Test
    public void deleteUsuarioTest() throws BibliotecaLogicException, Exception {
        UsuarioEntity entity = usuarioData.get(1);
        usuarioLogic.deleteUsuario(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una reserva
     *
     *
     */
    @Test
    public void updateReservaTest() throws BibliotecaLogicException {
        UsuarioEntity entity = usuarioData.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setBiblioteca(bibliotecaEntity);

        usuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}

