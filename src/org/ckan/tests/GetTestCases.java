package org.ckan.tests;

import org.ckan.*;

import org.junit.* ;
import static org.junit.Assert.* ;

import com.google.gson.Gson;
import java.util.UUID;

public class GetTestCases {

    @Test
    public void test_GetDataset() {
        Client c = new Client( new Connection("http://localhost", 5000),
                               "1de4a922-732d-40ad-8169-abd3d5d0e196");
        try {
            Dataset ds = new Dataset();
            ds.setName( UUID.randomUUID().toString() );
            ds.setTitle("Test Dataset");
            ds.setNotes("A description");
            c.createDataset(ds);

            Dataset get = c.getDatasetByName( ds.getName() );
            assertEquals( get.getTitle(), ds.getTitle() );
            assertEquals( get.getName(), ds.getName() );
            assertEquals( get.getNotes(), ds.getNotes() );
        } catch ( CKANException e ) {
            System.out.println(e);
        }
    }

    @Test
    public void test_GetDatasetFail() {
        Client c = new Client( new Connection("http://localhost", 5000),
                               "1de4a922-732d-40ad-8169-abd3d5d0e196");
        try {
            Dataset get = c.getDatasetByName( UUID.randomUUID().toString() );
        } catch ( CKANException e ) {
            assertEquals( e.getErrorMessages().size(), 2 );
        }
    }

}


