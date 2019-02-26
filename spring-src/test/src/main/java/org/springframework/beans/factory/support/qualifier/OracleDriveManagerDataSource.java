package org.springframework.beans.factory.support.qualifier;

public class OracleDriveManagerDataSource implements DataSource {

    public void connection() {
        System.out.println("oracle database connecting...");
    }

}
