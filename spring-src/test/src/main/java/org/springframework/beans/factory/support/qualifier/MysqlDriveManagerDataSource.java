package org.springframework.beans.factory.support.qualifier;

public class MysqlDriveManagerDataSource implements DataSource {

    public void connection() {
        System.out.println("mysql database connecting...");
    }

}
