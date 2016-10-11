/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
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
public class VideoLogicTest {
    
    public VideoLogicTest() {
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
     * Test of getVideos method, of class VideoLogic.
     */
    @Test
    public void testGetVideos() throws Exception {
        System.out.println("getVideos");
        VideoLogic instance = new VideoLogic();
        List<VideoEntity> expResult = null;
        List<VideoEntity> result = instance.getVideos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLibro method, of class VideoLogic.
     */
    @Test
    public void testGetLibro() throws Exception {
        System.out.println("getLibro");
        Long id = null;
        VideoLogic instance = new VideoLogic();
        VideoEntity expResult = null;
        VideoEntity result = instance.getLibro(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLibro method, of class VideoLogic.
     */
    @Test
    public void testCreateLibro() throws Exception {
        System.out.println("createLibro");
        VideoEntity entity = null;
        VideoLogic instance = new VideoLogic();
        VideoEntity expResult = null;
        VideoEntity result = instance.createLibro(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLibro method, of class VideoLogic.
     */
    @Test
    public void testUpdateLibro() throws Exception {
        System.out.println("updateLibro");
        VideoEntity entity = null;
        VideoLogic instance = new VideoLogic();
        VideoEntity expResult = null;
        VideoEntity result = instance.updateLibro(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLibro method, of class VideoLogic.
     */
    @Test
    public void testDeleteLibro() throws Exception {
        System.out.println("deleteLibro");
        Long id = null;
        VideoLogic instance = new VideoLogic();
        instance.deleteLibro(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
