package Entity;

import DAO.MemberDAO;
import java.util.Comparator;
    
public class Member extends Person implements Comparator<Member> {
    private int AssignedTrainerID;
    private double weight;
    private int ExercisePlanID;
    private int ScheduleID;
    private MemberDAO memberDAO = new MemberDAO();

    public Member(String fName, String lName, int age, String phone, String address, String gender, int AssignedTrainerID, double weight, int ExercisePlanID, int ScheduleID) {
        super(fName, lName, age, phone, address, gender);
        this.AssignedTrainerID = AssignedTrainerID;
        this.weight = weight;
        this.ExercisePlanID = ExercisePlanID;
        this.ScheduleID = ScheduleID;
    }

    public int getAssignedTrainer() {
        return AssignedTrainerID;
    }

    public void setAssignedTrainer(int assignedTrainer) {
        this.AssignedTrainerID = assignedTrainer;
        memberDAO.updateAssignedTrainer(this.getId(), assignedTrainer);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        memberDAO.updateWeight(this.getId(), weight);
    }

    public int getExercisePlan() {
        return ExercisePlanID;
    }

    public void setExercisePlan(int exercisePlan) {
        this.ExercisePlanID = exercisePlan;
        memberDAO.updateExercisePlan(this.getId(), exercisePlan);
    }

    public int getSchedule() {
        return ScheduleID;
    }

    public void setSchedule(int schedule) {
        this.ScheduleID = schedule;
        memberDAO.updateSchedule(this.getId(), schedule);
    }

    @Override
    public String toString() {
        return super.toString() + "Member{" + "AssignedTrainerID=" + AssignedTrainerID + ", weight=" + weight + ", ExercisePlanID=" + ExercisePlanID + ", ScheduleID=" + ScheduleID + '}';
    }

    @Override
    public int compare(Member o1, Member o2) {
        if(o1.getfName().compareTo(o2.getfName()) != 0){
            return o1.getfName().compareTo(o2.getfName());
        }else {
            return o1.getlName().compareTo(o2.getlName());
        }
    }
    
}
