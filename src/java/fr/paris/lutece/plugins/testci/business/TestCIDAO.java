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
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for TestCI objects
 */
public final class TestCIDAO implements ITestCIDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_test_c_i ) FROM testci_testci";
    private static final String SQL_QUERY_SELECT = "SELECT id_test_c_i, name FROM testci_testci WHERE id_test_c_i = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO testci_testci ( id_test_c_i, name ) VALUES ( ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM testci_testci WHERE id_test_c_i = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE testci_testci SET id_test_c_i = ?, name = ? WHERE id_test_c_i = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_test_c_i, name FROM testci_testci";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_test_c_i FROM testci_testci";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin)
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK , plugin  );
        daoUtil.executeQuery( );
        int nKey = 1;

        if( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free();
        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( TestCI testCI, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        testCI.setId( newPrimaryKey( plugin ) );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , testCI.getId( ) );
        daoUtil.setString( nIndex++ , testCI.getName( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TestCI load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeQuery( );
        TestCI testCI = null;

        if ( daoUtil.next( ) )
        {
            testCI = new TestCI();
            int nIndex = 1;
            
            testCI.setId( daoUtil.getInt( nIndex++ ) );
            testCI.setName( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return testCI;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1 , nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( TestCI testCI, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;
        
        daoUtil.setInt( nIndex++ , testCI.getId( ) );
        daoUtil.setString( nIndex++ , testCI.getName( ) );
        daoUtil.setInt( nIndex , testCI.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<TestCI> selectTestCIsList( Plugin plugin )
    {
        List<TestCI> testCIList = new ArrayList<TestCI>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            TestCI testCI = new TestCI(  );
            int nIndex = 1;
            
            testCI.setId( daoUtil.getInt( nIndex++ ) );
            testCI.setName( daoUtil.getString( nIndex++ ) );

            testCIList.add( testCI );
        }

        daoUtil.free( );
        return testCIList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdTestCIsList( Plugin plugin )
    {
        List<Integer> testCIList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            testCIList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return testCIList;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectTestCIsReferenceList( Plugin plugin )
    {
        ReferenceList testCIList = new ReferenceList();
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            testCIList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return testCIList;
    }

    /**
     * {@inheritDoc }
     */
    public void untested( Plugin plugin )
    {
        System.out.println("This is not tested and should be reported as such by our tools");
    }
}