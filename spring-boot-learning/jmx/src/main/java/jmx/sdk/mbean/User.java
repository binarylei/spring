package jmx.sdk.mbean;

import java.time.LocalTime;
import java.util.Date;

public class User implements UserMBean {

    private int id;
    private String name;
    private Date birthDate;
    private LocalTime time;
    private TestBean test;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    @Override
    public Date getBirthDate() {
        return this.birthDate;
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public void printUserInfo() {
        System.out.printf("User: { id=%s, name=%s }\r\n", this.id, this.name);
    }

    @Override
    public Date currentDate() {
        return new Date();
    }

    @Override
    public void setTest(TestBean test) {
        this.test = test;
    }

    @Override
    public TestBean getTest() {
        return this.test;
    }

}
