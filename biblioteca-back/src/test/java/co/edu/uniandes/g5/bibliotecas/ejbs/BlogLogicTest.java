/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBlogLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.BlogPersistence;
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
 * @author js.prieto10

    @RunWith(Arquillian.class)
    public class BlogLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BlogLogic blogLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    
   @Inject
   private UserTransaction utx;
   
   private List<BlogEntity> data = new ArrayList<BlogEntity>();
   
   private List<LibroEntity> libroData = new ArrayList<>();
   
   
   @Deployment
   public static JavaArchive createDeployment()
   {
       return ShrinkWrap.create(JavaArchive.class)
               .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
                .addPackage(IBlogLogic.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
   
   
   private void clearData()
   {
       em.createQuery("delete from BlogEntity").executeUpdate();
       em.createQuery("delete from LibroEntity").executeUpdate();
   }
   
   
   private void insertData()
   {
       for (int i = 0; i < 4; i++)
       {
           LibroEntity libro = factory.manufacturePojo(LibroEntity.class);
           em.persist(libro);
           libroData.add(libro);
       }
       for (int i = 0; i < 4; i++)
       {
           BlogEntity entity = factory.manufacturePojo(BlogEntity.class);
           entity.setLibro(libroData.get(i));
           
           
           em.persist(entity);
           data.add(entity);
       }
   }
   
   
   
   
   
   @Before
   public void setUp()
   {
       try {
           utx.begin();
           clearData();
           insertData();
           utx.commit();
       }
       catch (Exception e)
       {
           e.printStackTrace();
           try {
               utx.rollback();
           }
           catch(Exception e1)
           {
               e1.printStackTrace();
           }
       }
       
       
       
   }
   
   
   
   @Test
    public void createBlogTest() {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        BlogEntity result = blogLogic.createBlog(newEntity);
        Assert.assertNotNull(result);
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
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
*/