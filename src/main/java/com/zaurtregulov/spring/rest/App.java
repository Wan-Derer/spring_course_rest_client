package com.zaurtregulov.spring.rest;


import com.zaurtregulov.spring.rest.configuration.MyConfig;
import com.zaurtregulov.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        System.out.println(communication.getAllEmployees());
        System.out.println(communication.getEmployeeById(3));

//        Employee emp = new Employee("Маша", "Васькина", "Секретариат", 1234);
//        communication.saveEmployee(emp);

        Employee emp = communication.getEmployeeById(21);
        emp.setSalary(123);
        communication.saveEmployee(emp);

        communication.deleteEmployee(190);

    }
}
