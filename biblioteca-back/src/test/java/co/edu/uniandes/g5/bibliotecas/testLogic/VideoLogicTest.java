/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.g5.bibliotecas.testLogic;

import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.VideoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
import co.edu.uniandes.g5.bibliotecas.persistence.VideoPersistence;
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
public class VideoLogicTest {

    /**
     *
     */
    BibliotecaEntity fatherEntity;

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private IVideoLogic videoLogic;

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
    private List<VideoEntity> videoData = new ArrayList<VideoEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoEntity.class.getPackage())
                .addPackage(VideoLogic.class.getPackage())
                .addPackage(IVideoLogic.class.getPackage())
                .addPackage(VideoPersistence.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(BibliotecaPersistence.class.getPackage())
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
        em.createQuery("delete from VideoEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        fatherEntity = factory.manufacturePojo(BibliotecaEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            VideoEntity entity = factory.manufacturePojo(VideoEntity.class);
            entity.setBiblioteca(fatherEntity);
            entity.setNumEjemplares(Math.abs(entity.getNumEjemplares()));
            entity.setEjemplaresDisponibles(entity.getNumEjemplares());
            em.persist(entity);
            videoData.add(entity);
        }
    }

    /**
     * Prueba para crear un Video
     *
     *
     */
    @Test
    public void testCreateVideo1() {
        try {
            VideoEntity newEntity = factory.manufacturePojo(VideoEntity.class);
            newEntity.setBiblioteca(fatherEntity);
            newEntity.setNumEjemplares(Math.abs(newEntity.getNumEjemplares()));
            newEntity.setEjemplaresDisponibles(newEntity.getNumEjemplares());
            VideoEntity result = videoLogic.createVideo(newEntity);
            Assert.assertNotNull(result);
            VideoEntity entity = em.find(VideoEntity.class, result.getId());
            Assert.assertEquals(newEntity.getName(), entity.getName());
            Assert.assertEquals(newEntity.getId(), entity.getId());
        } catch (BibliotecaLogicException ex) {
            Assert.fail("No deberia fallar");
        }
    }

    /**
     * Prueba para crear un video con un id que ya existe
     */
    @Test
    public void testCreateVideo2() {
        try {
            VideoEntity entity = factory.manufacturePojo(VideoEntity.class);
            entity.setBiblioteca(fatherEntity);
            entity.setNumEjemplares(Math.abs(entity.getNumEjemplares()));
            entity.setEjemplaresDisponibles(entity.getNumEjemplares());
            entity.setId(videoData.get(0).getId());
            VideoEntity result = videoLogic.createVideo(entity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("Ya existe un video con el mismo id", ex.getMessage());
        }
    }

    /**
     * Prueba para crear un video que tiene un numero de unidades disponibles
     * negativo
     */
    @Test
    public void testCreateVideo3() {
        try {
            VideoEntity entity = factory.manufacturePojo(VideoEntity.class);
            entity.setBiblioteca(fatherEntity);
            entity.setNumEjemplares(-1);
            entity.setEjemplaresDisponibles(-1);
            VideoEntity result = videoLogic.createVideo(entity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("Un video no puede tener un numero negativo de ejemplares", ex.getMessage());
        }
    }

    /**
     * Prueba para crear un video que tiene mas unidades disponibles que su
     * cantidad total
     */
    @Test
    public void testCreateVideo4() {
        try {
            VideoEntity entity = factory.manufacturePojo(VideoEntity.class);
            entity.setBiblioteca(fatherEntity);
            entity.setNumEjemplares(Math.abs(entity.getNumEjemplares()));
            entity.setEjemplaresDisponibles(entity.getNumEjemplares() + 1);
            VideoEntity result = videoLogic.createVideo(entity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("Un video no puede tener mas ejemplares disponibles que su cantidad total", ex.getMessage());
        }
    }

    @Test
    public void testCreateVideo5() {
        try {
            VideoEntity entity = factory.manufacturePojo(VideoEntity.class);
            entity.setBiblioteca(factory.manufacturePojo(BibliotecaEntity.class));
            entity.setNumEjemplares(Math.abs(entity.getNumEjemplares()));
            entity.setEjemplaresDisponibles(entity.getNumEjemplares() + 1);
            VideoEntity result = videoLogic.createVideo(entity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("No existe la biblioteca", ex.getMessage());
        }
    }

    /**
     * Prueba para consultar la lista de Videos
     *
     *
     */
    @Test
    public void testGetVideos() {
        List<VideoEntity> list = videoLogic.getVideos();
        Assert.assertEquals(videoData.size(), list.size());
        for (VideoEntity entity : list) {
            boolean found = false;
            for (VideoEntity storedEntity : videoData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Video
     *
     *
     */
    @Test
    public void testGetVideo() {
        VideoEntity entity = videoData.get(0);
        VideoEntity resultEntity = videoLogic.getVideo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Video
     *
     *
     */
    @Test
    public void testDeleteVideo() {
        VideoEntity entity = videoData.get(1);
        videoLogic.deleteVideo(entity.getId());
        VideoEntity deleted = em.find(VideoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Video
     *
     *
     */
    @Test
    public void testUpdateVideo1() {
        try {
            VideoEntity entity = videoData.get(0);
            VideoEntity pojoEntity = factory.manufacturePojo(VideoEntity.class);
            pojoEntity.setId(entity.getId());
            pojoEntity.setBiblioteca(fatherEntity);
            pojoEntity.setNumEjemplares(Math.abs(pojoEntity.getNumEjemplares()));
            pojoEntity.setEjemplaresDisponibles(pojoEntity.getNumEjemplares());
            videoLogic.updateVideo(pojoEntity);
            VideoEntity resp = em.find(VideoEntity.class, entity.getId());
            Assert.assertEquals(pojoEntity.getName(), resp.getName());
            Assert.assertEquals(pojoEntity.getId(), resp.getId());
        } catch (BibliotecaLogicException ex) {
            Assert.fail("No deberia fallar");
        }
    }

    @Test
    public void testUpdateVideo2() {
        try {
            VideoEntity entity = videoData.get(0);
            VideoEntity pojoEntity = factory.manufacturePojo(VideoEntity.class);
            pojoEntity.setNumEjemplares(-1);
            pojoEntity.setEjemplaresDisponibles(-1);
            pojoEntity.setId(entity.getId());
            pojoEntity.setBiblioteca(fatherEntity);
            VideoEntity resp = videoLogic.updateVideo(pojoEntity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("Un video no puede tener un numero negativo de ejemplares", ex.getMessage());
        }
    }
    
    @Test
    public void testUpdateVideo3() {
        try {
            VideoEntity entity = videoData.get(0);
            VideoEntity pojoEntity = factory.manufacturePojo(VideoEntity.class);
            pojoEntity.setId(entity.getId());
            pojoEntity.setNumEjemplares(Math.abs(pojoEntity.getNumEjemplares()));
            pojoEntity.setEjemplaresDisponibles(pojoEntity.getNumEjemplares() + 1);
            pojoEntity.setBiblioteca(fatherEntity);
            VideoEntity resp = videoLogic.updateVideo(pojoEntity);
            Assert.fail("Deberia fallar");
        } catch (BibliotecaLogicException ex) {
            Assert.assertEquals("Un video no puede tener mas ejemplares disponibles que su cantidad total", ex.getMessage());
        }
    }

    
    /**
     * Test of getVideos method, of class VideoLogic.
     */
    @Test
    public void testGetVideosByBiblioteca() {
        List<VideoEntity> list = videoLogic.getVideosByBiblioteca(fatherEntity.getId());
        Assert.assertEquals(videoData.size(), list.size());
        for (VideoEntity entity : list) {
            boolean found = false;
            for (VideoEntity storedEntity : videoData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getVideoByName method, of class VideoLogic.
     */
    @Test
    public void testGetVideoByName() {
        VideoEntity entity = videoData.get(0);
        VideoEntity resultEntity = videoLogic.getVideoByName(entity.getName(), entity.getBiblioteca().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
