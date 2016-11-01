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

import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.LibroLogic;
import co.edu.uniandes.g5.bibliotecas.ejbs.LibroLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
import co.edu.uniandes.g5.bibliotecas.persistence.LibroPersistence;
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
public class LibroLogicTest {

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
    private List<LibroEntity> libroData = new ArrayList<LibroEntity>();

    /**
     *
     */
    private List<BibliotecaEntity> bibliotecaData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroLogic.class.getPackage())
                .addPackage(ILibroLogic.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
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
        em.createQuery("delete from LibroEntity").executeUpdate();
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
        Long isbn = 9780000000001L;
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            entity.setBiblioteca(fatherEntity);
            entity.setNumEjemplares(Math.abs(entity.getNumEjemplares()));
            entity.setEjemplaresDisponibles(entity.getNumEjemplares());
            entity.setIsbn(isbn);
            isbn++;
            em.persist(entity);
            libroData.add(entity);
        }
    }

    /**
     * Prueba para crear un Libro
     *
     *
     */
    @Test
    public void testCreateLibro1() throws BibliotecaLogicException {
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        LibroEntity result = libroLogic.createLibro(newEntity);
        Assert.assertNotNull(result);
        LibroEntity entity = em.find(LibroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para crear un libro con una biblioteca que no existe
     */
    @Test(expected = BibliotecaLogicException.class)
    public void testCreateLibro2() throws Exception {
        LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
        BibliotecaEntity noExiste = factory.manufacturePojo(BibliotecaEntity.class);
        entity.setBiblioteca(noExiste);
        LibroEntity result = libroLogic.createLibro(entity);
    }

    /**
     * Prueba para crear un libro con un isbn que ya existe
     */
    @Test(expected = BibliotecaLogicException.class)
    public void testCreateLibro3() throws Exception {
        LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
        entity.setBiblioteca(fatherEntity);
        entity.setIsbn(libroData.get(0).getIsbn());
        LibroEntity result = libroLogic.createLibro(entity);
    }

    /**
     * Prueba para crear un libro que tiene mas unidades disponibles que su
     * cantidad total
     */
    @Test(expected = BibliotecaLogicException.class)
    public void testCreateLibro4() throws Exception {
        LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
        entity.setBiblioteca(fatherEntity);
        entity.setEjemplaresDisponibles(entity.getNumEjemplares() + 1);
        LibroEntity result = libroLogic.createLibro(entity);
    }

    /**
     * Prueba para crear un libro con un isbn que no es permitido
     */
    @Test(expected = BibliotecaLogicException.class)
    public void testCreateLibro5() throws Exception {
        LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
        entity.setBiblioteca(fatherEntity);
        entity.setIsbn(9790000000001L);
        LibroEntity result = libroLogic.createLibro(entity);
    }

    /**
     * Prueba para consultar la lista de Libros
     *
     *
     */
    @Test
    public void testGetLibros() {
        List<LibroEntity> list = libroLogic.getLibros();
        Assert.assertEquals(libroData.size(), list.size());
        for (LibroEntity entity : list) {
            boolean found = false;
            for (LibroEntity storedEntity : libroData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Libro
     *
     *
     */
    @Test
    public void testGetLibro() {
        LibroEntity entity = libroData.get(0);
        LibroEntity resultEntity = libroLogic.getLibro(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Libro
     *
     *
     */
    @Test
    public void testDeleteLibro() {
        LibroEntity entity = libroData.get(1);
        libroLogic.deleteLibro(entity.getId());
        LibroEntity deleted = em.find(LibroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Libro
     *
     *
     */
    @Test
    public void testUpdateLibro1() throws BibliotecaLogicException {
        LibroEntity entity = libroData.get(0);
        LibroEntity pojoEntity = factory.manufacturePojo(LibroEntity.class);

        pojoEntity.setId(entity.getId());

        libroLogic.updateLibro(pojoEntity);

        LibroEntity resp = em.find(LibroEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    @Test(expected = BibliotecaLogicException.class)
    public void testUpdateLibro2() throws BibliotecaLogicException {
        LibroEntity entity = libroData.get(0);
        LibroEntity pojoEntity = factory.manufacturePojo(LibroEntity.class);
        BibliotecaEntity noExiste = factory.manufacturePojo(BibliotecaEntity.class);
        pojoEntity.setBiblioteca(noExiste);
        pojoEntity.setId(entity.getId());

        LibroEntity result = libroLogic.updateLibro(pojoEntity);
    }

    @Test(expected = BibliotecaLogicException.class)
    public void testUpdateLibro3() throws BibliotecaLogicException {
        LibroEntity entity = libroData.get(0);
        LibroEntity pojoEntity = factory.manufacturePojo(LibroEntity.class);
        entity.setEjemplaresDisponibles(entity.getNumEjemplares() + 1);
        pojoEntity.setId(entity.getId());
        LibroEntity result = libroLogic.updateLibro(pojoEntity);
    }

    /**
     * Test of getLibros method, of class LibroLogic.
     */
    @Test
    public void testGetLibrosByBiblioteca() {
        List<LibroEntity> list = libroLogic.getLibrosByBiblioteca(fatherEntity.getId());
        Assert.assertEquals(libroData.size(), list.size());
        for (LibroEntity entity : list) {
            boolean found = false;
            for (LibroEntity storedEntity : libroData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getLibroByName method, of class LibroLogic.
     */
    @Test
    public void testGetLibroByName() {
        LibroEntity entity = libroData.get(0);
        LibroEntity resultEntity = libroLogic.getLibroByName(entity.getName(), entity.getBiblioteca().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Test of getLibroByName method, of class LibroLogic.
     */
    @Test
    public void testGetLibroByISBN() {
        LibroEntity entity = libroData.get(0);
        LibroEntity resultEntity = libroLogic.getLibroByISBN(entity.getIsbn(), entity.getBiblioteca().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
