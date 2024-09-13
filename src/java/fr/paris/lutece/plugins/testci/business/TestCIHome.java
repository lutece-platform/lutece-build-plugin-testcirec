/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
 package fr.paris.lutece.plugins.testci.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

/**
 * This class provides instances management methods (create, find, ...) for TestCI objects
 */
public final class TestCIHome
{
    // Static variable pointed at the DAO instance
    private static ITestCIDAO _dao = SpringContextService.getBean( "testci.testCIDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "testci" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private TestCIHome(  )
    {
    }

    /**
     * Create an instance of the testCI class
     * @param testCI The instance of the TestCI which contains the informations to store
     * @return The  instance of testCI which has been created with its primary key.
     */
    public static TestCI create( TestCI testCI )
    {
        _dao.insert( testCI, _plugin );

        return testCI;
    }

    /**
     * Update of the testCI which is specified in parameter
     * @param testCI The instance of the TestCI which contains the data to store
     * @return The instance of the  testCI which has been updated
     */
    public static TestCI update( TestCI testCI )
    {
        _dao.store( testCI, _plugin );

        return testCI;
    }

    /**
     * Remove the testCI whose identifier is specified in parameter
     * @param nKey The testCI Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a testCI whose identifier is specified in parameter
     * @param nKey The testCI primary key
     * @return an instance of TestCI
     */
    public static TestCI findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin);
    }

    /**
     * Load the data of all the testCI objects and returns them as a list
     * @return the list which contains the data of all the testCI objects
     */
    public static List<TestCI> getTestCIsList( )
    {
        return _dao.selectTestCIsList( _plugin );
    }
    
    /**
     * Load the id of all the testCI objects and returns them as a list
     * @return the list which contains the id of all the testCI objects
     */
    public static List<Integer> getIdTestCIsList( )
    {
        return _dao.selectIdTestCIsList( _plugin );
    }
    
    /**
     * Load the data of all the testCI objects and returns them as a referenceList
     * @return the referenceList which contains the data of all the testCI objects
     */
    public static ReferenceList getTestCIsReferenceList( )
    {
        return _dao.selectTestCIsReferenceList(_plugin );
    }
}

