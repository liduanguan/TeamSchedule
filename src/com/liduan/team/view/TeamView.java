package com.liduan.team.view;

import com.liduan.team.domain.Employee;
import com.liduan.team.domain.Programmer;
import com.liduan.team.service.NameListService;
import com.liduan.team.service.TeamException;
import com.liduan.team.service.TeamService;

public class TeamView {
	
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();
	
	public void entermailMenu() {
		
		boolean loopFlag = true;
		char menu = 0;
		while (loopFlag) {
			
			if(menu != '1') {
				listAllEmployee();
			}
			System.out.print("1-Team List 2-Add team member 3-Delete team member 4-Exit Please select(1-4): ");
			menu = TSUtility.readMenuSelection();
			switch (menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.print("Do you want to quit(Y/N): ");
				char isExit = TSUtility.readConfirmSelection();
				if( isExit == 'Y') {
					loopFlag = false;
				}
				break;
			}
		}
	}
	
	private void listAllEmployee() {
		Employee[] employees = listSvc.getAllEmployees();
		System.out.println("--------------------------Developer Team Management App--------------------------\n");
		System.out.print("iD\t" + "Name\t" + "Age\t" + "Salary\t" + "Position\t" + "Status\t" + "Bonus\t" + "Stock\t" + "Equipment\t");
		if (employees == null || employees.length == 0) {
			System.out.println("No employee data!");
		} else {
			for (Employee employee : employees) {
				System.out.println(employee.toString());
			}
		}
		System.out.println("----------------------------------------------------------------------------------\n");
		
	}
	
	private void getTeam() {
		System.out.println("--------------------------Team Member List--------------------------");
		Programmer[] team = teamSvc.getTeam();
		if( team == null || team.length == 0) {
			System.out.println("No Team Member!");
			
		}else {
			System.out.println("TID/ID" + "\tName" + "\tAge" + "\tSalary" + "\tPosition" + "\tBonus" + "\tStock");
			for(int i = 0; i < team.length; i++) {
				System.out.println(team[i].getTeamDetail());
			}
		}
		
		System.out.println("--------------------------------------------------------------------");
	}
	
	private void addMember() {
		System.out.println("--------------------------Add Member-------------------------");
		System.out.print("Please input an employee ID:");
		int id = TSUtility.readInt();
		
		try {
		Employee emp = listSvc.getEmployee(id);
			teamSvc.addMember(emp);
			System.out.println("add member successd");
		} catch (TeamException e) {
			System.out.println("Add member fail. Reason: " + e.getMessage());
		}
		//Press enter to continue...
		TSUtility.readReturn();
		
	}
	
	private void deleteMember() {

		System.out.println("--------------------------Remove Member-------------------------");
		System.out.print("Pelase enter member TID: ");
		int memberId = TSUtility.readInt();
		System.out.print("Delete(Y/N): ");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete == 'N') {
			
		}
		try {
			teamSvc.removeMember(memberId);
			System.out.println("Remove Member Success");
		}catch(TeamException e){
			System.out.println("Remove momber failed. Reason:" + e.getMessage());
		}
		//Press enter to continue...
		TSUtility.readReturn();
	}
	
	public static void main(String[] args) {
		TeamView teamView = new TeamView();
		teamView.entermailMenu();
	}
}
