package com.zaurtregulov.spring.rest;

import com.zaurtregulov.spring.rest.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class Communication {

  private final String URL = "http://localhost:8080/spring_course_rest/api/employees";
  private final RestTemplate restTemplate;

  public List<Employee> getAllEmployees(){

    ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
      URL,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<Employee>>() {}
    );

    return responseEntity.getBody();
  }

  public Employee getEmployeeById(int id){

    Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);

    return employee;
  }

  public void saveEmployee(Employee employee){
    int id = employee.getId();

    if (id == 0){
      ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
      System.out.println("Добавлен новый сотрудник");
      System.out.println(responseEntity.getBody());
    } else {
      restTemplate.put(URL, employee);
      System.out.println("Сотрудник с ID " + id + " изменён");
    }

  }

  public void deleteEmployee(int id){

    try {
      restTemplate.delete(URL + "/" + id);
      System.out.println("Сотрудник с ID " + id + " уволен");
    } catch (RestClientException e) {
      System.out.println("Сотрудник с ID " + id + " не найден");
    }

  }


}
