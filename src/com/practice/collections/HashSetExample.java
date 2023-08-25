package com.practice.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashSetExample {

	public static void main(String[] args) {

		Set<Employee> employeeSet  = new HashSet<>();
		employeeSet.add(new Employee("Bob","A",24));
		employeeSet.add(new Employee("Foo","B",27));
		employeeSet.add(new Employee("Maha","C",27));

		Map<String, Employee> employeeMap = new HashMap<String,Employee>();
		employeeMap.put("A", new Employee("Bob","A",24));
		employeeMap.put("B", new Employee("Foo","B",24));

		for(Map.Entry<String,Employee> entry: employeeMap.entrySet()) {
			System.out.printf("Key: %s value: %s",
					entry.getKey(), entry.getValue());
		}
	}

}

class Employee{
	private String name;
	private String id;
	private int age;
	
	public Employee(String name, String id, int age) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name +
				", id=" + id +
				", age=" + age + "]";
	}
}
