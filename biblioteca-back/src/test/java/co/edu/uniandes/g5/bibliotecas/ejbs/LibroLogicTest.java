/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s.rojas19
 */
public class LibroLogicTest {
    
    public LibroLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLibros method, of class LibroLogic.
     */
    @Test
    public void testGetLibros() throws Exception {
        System.out.println("getLibros");
        LibroLogic instance = new LibroLogic();
        List<LibroEntity> expResult = null;
        List<LibroEntity> result = instance.getLibros();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLibro method, of class LibroLogic.
     */
    @Test
    public void testGetLibro() throws Exception {
        System.out.println("getLibro");
        Long id = null;
        LibroLogic instance = new LibroLogic();
        LibroEntity expResult = null;
        LibroEntity result = instance.getLibro(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLibro method, of class LibroLogic.
     */
    @Test
    public void testCreateLibro() throws Exception {
        System.out.println("createLibro");
        LibroEntity entity = null;
        LibroLogic instance = new LibroLogic();
        LibroEntity expResult = null;
        LibroEntity result = instance.createLibro(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLibro method, of class LibroLogic.
     */
    @Test
    public void testUpdateLibro() throws Exception {
        System.out.println("updateLibro");
        LibroEntity entity = null;
        LibroLogic instance = new LibroLogic();
        LibroEntity expResult = null;
        LibroEntity result = instance.updateLibro(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLibro method, of class LibroLogic.
     */
    @Test
    public void testDeleteLibro() throws Exception {
        System.out.println("deleteLibro");
        Long id = null;
        LibroLogic instance = new LibroLogic();
        instance.deleteLibro(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
