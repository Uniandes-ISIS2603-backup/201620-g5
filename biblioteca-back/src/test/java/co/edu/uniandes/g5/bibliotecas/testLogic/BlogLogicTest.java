package co.edu.uniandes.g5.bibliotecas.testLogic;


 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.g5.bibliotecas.api.IBlogLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.BlogLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BlogPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 * @author js.prieto10
 * */
@RunWith(Arquillian.class)
public class BlogLogicTest {
    
    private Random random;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IBlogLogic blogLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject 
    private UserTransaction utx;
    
    private List<BlogEntity> data = new ArrayList<BlogEntity>();
    
    private List<LibroEntity> librosData = new ArrayList<LibroEntity>();
    
    private List<UsuarioEntity> usuariosData = new ArrayList<UsuarioEntity>();
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
                .addPackage(IBlogLogic.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
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
    
    
    
    private void clearData() {
        em.createQuery("delete from BlogEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
   private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            LibroEntity libro = factory.manufacturePojo(LibroEntity.class);
            em.persist(usuario);
            em.persist(libro);
            usuariosData.add(usuario);
            librosData.add(libro);
            
        }
        for (int i = 0; i < 3; i++) {
            BlogEntity entity = factory.manufacturePojo(BlogEntity.class);
            entity.setLibro(librosData.get(i));
            entity.setUsuario(usuariosData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    } 
    
   @Test
    public void createBlogTest() throws BibliotecaLogicException
    {
        random = new Random();
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        int usuarioSize = usuariosData.size();
        Long usuarioId = usuariosData.get(random.nextInt(usuarioSize)).getId();
        int libroSize = librosData.size();
        Long libroId = librosData.get(random.nextInt(libroSize)).getId();
        BlogEntity result = blogLogic.createBlog(newEntity, usuarioId, libroId);
        Assert.assertNotNull(result);
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    
    @Test
    public void getBlogsTest() {
        List<BlogEntity> list = blogLogic.getBlogs();
        Assert.assertEquals(data.size(), list.size());
        for (BlogEntity entity : list) {
            boolean found = false;
            for (BlogEntity b : data) {
                if (entity.getId().equals(b.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getBlogTest() {
        BlogEntity entity = data.get(0);
        BlogEntity resultEntity = blogLogic.getBlog(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void deleteBlogTest() {
        BlogEntity entity = data.get(1);
        blogLogic.deleteBlog(entity.getId());
        BlogEntity deleted = em.find(BlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    @Test
    public void updateBlogTest() {
        BlogEntity entity = data.get(0);
        BlogEntity pojoEntity = factory.manufacturePojo(BlogEntity.class);

        pojoEntity.setId(entity.getId());

        blogLogic.updateBlog(pojoEntity);

        BlogEntity resp = em.find(BlogEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
}
