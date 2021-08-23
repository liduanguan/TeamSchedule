package com.liduan.team.service;

import com.liduan.team.domain.Architect;
import com.liduan.team.domain.Designer;
import com.liduan.team.domain.Employee;
import com.liduan.team.domain.Programmer;
import static com.liduan.team.service.Status.*;

/**
 * @Description add and delete employee from a team
 * @author Liduan Guan
 * @data Aug 23, 2021
 */
public class TeamService {
	
	private static int counter = 1; //value of memberId
	private final int MAX_MEMBER = 5; //Max limit of a team
	private Programmer[] team = new Programmer[MAX_MEMBER]; //contain's member of team
	private int total; //the real number of people in the team
	
	/**
	 * @Description return a Programmer[] with employees in a team
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for(int i = 0; i < total; i++) {
			team[i] = this.team[i];
		}
		return team;
	}
	
	/**
	 * @Description add a member to a team
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException{
		
//		 addMember errors
//		 Team is full
		if(total >= 5) {
			throw new TeamException("Team is full! Add member fail");
		}
//		 member is not a developer
		if(!(e instanceof Programmer)) {
			throw new TeamException("Employee is not a developer!");
		}
//		 employee is already in the team
		if(isExist(e)) {
			throw new TeamException("Employee is already in the team!");
		}
//		 employee is in another team
//		 employee is on vocation
		Programmer programmer = (Programmer) e;//ClassCastException will not occur
		if(programmer.getStatus() == BUSY) {
			throw new TeamException("Employee is already in another team!");
		}else if(programmer.getStatus() == VOCATION) {
			throw new TeamException("Employee is on vocation!");
		}

//		 a team can have at most 1 architect
//		 a team can have at most designer
//		 a team can have at most 3 developer
		
		//Obtain number of each type of develop in the team
		 int numOfArch = 0; int numOfDes = 0; int numOfPro = 0;
		 for(int i = 0; i < total; i++) {
			 if(team[i] instanceof Architect) {
				 numOfArch++;
			 }
			 if(team[i] instanceof Designer) {
				 numOfDes++;
			 }
			 if(team[i] instanceof Programmer) {
				 numOfPro++;
			 }
		 }
		 
		 if(programmer instanceof Architect) {
			 if(numOfArch >= 1) {
				 throw new TeamException("There can be no more than 1 Architect in the team!");
			 }
		 }else if(programmer instanceof Designer) {
			 if(numOfDes >= 2) {
				 throw new TeamException("There can be no more than 3 Designer in the team!"); 
			 }
		 }else if(numOfPro >= 3) {
			 throw new TeamException("There can be no more than 3 Programmer in the team!");
		 }
		 
		 // add the employee to the team
		 team[total++] = programmer;
		 programmer.setStatus(BUSY);
		 programmer.setMemberId(counter++);
	}
	
	/**
	 * @Description check if the member aready in the team
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @param e
	 * @return
	 */
	private boolean isExist(Employee e) {
		for(int i = 0; i < total; i++) {
			if(team[i].getId() == e.getId())
				return true;
		}
		return false;
	}

	/**
	 * @Description remove a member from a team
	 * @author Liduan Guan
	 * @date Aug 23, 2021
	 * @param memberId
	 * @throws TeamException
	 */
	public void removeMember(int memberId) throws TeamException{
		int i = 0;
		for(i = 0; i < total; i++) {
			if(team[i].getMemberId() == memberId) {
				team[i].setStatus(FREE);
				break;
			}
		}
		
		// memberId not found
		if( i == total) {
			throw new TeamException("Member " + memberId +" not found!");
		}
		// move elements forward to delete
		for(int j = i + 1; j < total; j++) {
			team[j - 1]= team[j];
		}
		
		team[--total] = null;
				
	}
}
