package bitcamp.project2.controller;

import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.util.LinkedList;

public class AppointmentController {
    PlanController pc = PlanController.getInstance();
    UserController uc = UserController.getInstance();

    LinkedList<Plan> planList = pc.getPlanList();
    LinkedList<User> userList = uc.getUserList();

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    AppointmentController(){

    }




    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public void menu(){

    }
}
