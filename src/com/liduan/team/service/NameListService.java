package com.liduan.team.service;

import com.liduan.team.domain.Architect;
import com.liduan.team.domain.Designer;
import com.liduan.team.domain.Employee;
import com.liduan.team.domain.Equipment;
import com.liduan.team.domain.NoteBook;
import com.liduan.team.domain.PC;
import com.liduan.team.domain.Printer;
import com.liduan.team.domain.Programmer;

import static com.liduan.team.service.Data.*;

/**
 * @Description Encapuslate Data into Employee[], and methods to manipulate it
 * @author Liduan Guan
 * @data Aug 22, 2021
 */
public class NameListService {
	
	private Employee[] employees;
	
	/**
	 * @Description 
	 */
	public NameListService() {		
		employees = new Employee[EMPLOYEES.length];
		
		for(int i = 0; i < employees.length; i++) {
			// get common prperties
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			Equipment equipment;
			double bonus;
			switch(type) {
			case EMPLOYEE:
				employees[i] = new Employee(id,name,age,salary);
				break;
			case PROGRAMMER:
				equipment = createEquipment(i);
				//System.out.println(equipment);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;			
			case DESIGNER:
				equipment = createEquipment(i);
				//System.out.println(equipment);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				employees[i] = new Designer(id, name, age, salary, equipment, bonus);
				break;
			case ARCHITECT:
				equipment = createEquipment(i);
				//System.out.println(equipment);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				int stock = Integer.parseInt(EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}
		}
	}

	
	/**
	 * @Description Create an Equipment object for the corressponding employee
	 * @author Liduan Guan
	 * @date Aug 22, 2021
	 * @param i
	 * @return Equipment
	 */
	private Equipment createEquipment(int index) {
		int type = Integer.parseInt(EQUIPMENTS[index][0]);
		switch(type) {
		case PC:
			return new PC(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
		case NOTEBOOK:
			int price = Integer.parseInt(EQUIPMENTS[index][2]);
			return new NoteBook(EQUIPMENTS[index][1],price);
		case PRINTER:
			return new Printer(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
		}
		return null;
	}

	public NameListService(Employee[] employees) {
		super();
		this.employees = employees;
	}

	/**
	 * @Description return the employee with the corresponding Id
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @param eId
	 * @return employees[eId]
	 * @throws TeamException 
	 */
	public Employee getEmployee(int eId) throws TeamException {
		
		for (Employee employee : employees) {
			if(employee.getId() == eId) {
				return employee;
			}				
		}
		throw new TeamException("No Employee found!");
	}

	/**
	 * @Description return an array of employees 
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}
	
	public static void main(String[] args) {
		
		NameListService nameListService = new NameListService();
		Employee[] employees = nameListService.getAllEmployees();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		
		try {
			System.out.println(nameListService.getEmployee(100));
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
