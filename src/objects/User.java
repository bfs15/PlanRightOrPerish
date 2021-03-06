package objects;
import java.util.List;
import java.util.Scanner;

public class User {
    private static Project project = new Project();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Check how many arguments were passed in
        if(args.length == 0) {
            System.out.println("Error: wrong args\nUsage: java program projectFilename");
            System.exit(0);
        }

        try {
            project.openFile(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Project:");
        System.out.println(project.getDescription());
        // Print activities so user can make stages
        printActivities();

        System.out.println("Type the name of the life cycle model");
        String lifeCycleModel = scanner.nextLine();
        project.setLifeCycleModel(lifeCycleModel);

        System.out.println("Add stages to your project in order.");
        String stageName;

        while( !(stageName = readStageName()).isEmpty() && !stageName.equals("end")) {

            int stageID = project.newStage(stageName);
            int actID;

            actID = readAddActivityToStage(stageName);
            while (actID > -1) {
                boolean succ = project.stageAddActivity(stageID, actID);
                if( ! succ){
                    System.out.println("Activity couldn't be added to stage");
                }

                actID = readAddActivityToStage(stageName);
            }

            System.out.println("How many work days this stage will have?");
            project.setSchedule(stageID, scanner.nextInt());
            scanner.nextLine();
        }

        System.out.println("What will be the project budget?");
        project.establishExpenses(scanner.nextInt());
        scanner.nextLine();

        // start game
        boolean gameRunning = true;

        while(gameRunning) {
            int choice;
            printDay();

            while( (choice = choose()) != 0 ) {
                switch (choice){
                    case 1:
                        menuActivities();
                        break;
                    case 2:
                        menuDevelopers();
                        break;
                    case 3:
                        menuComputers();
                        break;
                    default:
                        System.out.println("Invalid action");
                        break;
                }

                printDay();
            }

            gameRunning = project.endDay();
        }

        if(project.getMoney() < 0){
            System.out.println("You perished.");
        } else {
            System.out.println("Project finished on budget.");
        }

    }

    private static int choose(){
        System.out.println("Type the ID of the action you want to take");
        int r = scanner.nextInt();
        scanner.nextLine();
        return r;
    }

    private static String readStageName(){
        System.out.println("Type the name of a new stage (type \"end\" to complete)");
        String r = scanner.nextLine();
        //System.out.println(r);
        return r;
    }

    private static int readAddActivityToStage(String stageName){
        System.out.println("Type ID of the activity to add to stage " + stageName + " (type \"-1\" to complete)");
        int r = scanner.nextInt();
        scanner.nextLine();
        return r;
    }

    private static void print(Activity a){
        String activityString = " | ";
        activityString+="Name: "+a.getName()+" | ";
        activityString+="Work: "+String.format("%.2f",a.getWorkDone())+"/";
        activityString+=String.format("%.2f",a.getCost())+" | ";
        activityString+="Computers: "+a.getComputerNo()+"/"+a.getMaxComputerNo()+" | ";
        activityString+="Type: "+a.getType()+" | ";
        System.out.println(activityString);
    }


    private static void printActivities(){
        List<Activity> acts = project.getActivities();
        System.out.println("Activities:");

        for(int i = 0; i < acts.size(); i++){
            System.out.print("| ID " + i);
            Activity a = acts.get(i);
            print(a);
        }
    }

    private static void printCurrentActivities(){
        List<Activity> acts = project.getCurrentActivities();
        System.out.println("Current Activities:\n");
        for(int i = 0; i < acts.size(); i++){
            System.out.print("| ID " + i);
            Activity a = acts.get(i);
            print(a);
        }
    }

    private static void printDay(){
        String currentStageName = project.getCurrentStage().getName();

        String miniStagePadding = "";
        if (currentStageName.length()%2 == 1) miniStagePadding = " ";
        int stagePaddingSize = (currentStageName.length()-5)/2 ;
        String stagePadding = "";
        for(int i=0;i<stagePaddingSize;++i) stagePadding+="-";
        System.out.print(">>>"+stagePadding+"Stage"+stagePadding+"<<<");

        String ca$h = "  ";
        
        
        int len = String.format(" | BCZ$ = %.2f |\n",project.getMoney()).length();
        for(int i=0;i<len-4;++i) ca$h+="-";
        System.out.println(ca$h);

        String miniSpace = "";
        int spaces =  4 - (currentStageName.length()/2);
        for (int i = 0;i<spaces;++i) miniSpace+=" ";

        if(currentStageName.length() >= 5) System.out.print("|  "+project.getCurrentStage().getName()+" "+miniStagePadding+"|");
        else System.out.print("|"+miniSpace+project.getCurrentStage().getName()+miniSpace+" "+"|");
        System.out.printf(" | BCZ$ = %.2f |\n",project.getMoney());
        System.out.print(">>>"+stagePadding+"-----"+stagePadding+"<<<");
        System.out.println(ca$h);
        int workingDevs = project.getDevs().size()-project.getIdleDevs().size();
        int workingComputers =project.getComputers().size()-project.getIdleComputers().size();
        int devSize = project.getDevs().size();
        int compSize = project.getComputers().size();
        
        
        int resourcePaddingSize = Math.max(devSize, compSize);
        String devString = "| Developers:    Working "+workingDevs+"/"+devSize;
        String compString ="| Computers:     Working "+workingComputers+"/"+compSize;
        int deltaStringSize = (devString.length()) - (compString.length());
        
        String resourcePadding = "";
        for(int i = 0; i < Math.abs(deltaStringSize);++i) resourcePadding += " ";
        if (deltaStringSize > 0) compString += resourcePadding;
        else if (deltaStringSize < 0) devString += resourcePadding;
        compString+=" |";
        devString+=" |";
        	
        int boxLength =compString.length();
        String boxCloseBox = "";
        for(int i = 0; i < boxLength; ++i) boxCloseBox+="-";
        
        System.out.println(boxCloseBox);       
        System.out.println(devString);
        System.out.println(compString);
        System.out.println(boxCloseBox);
        System.out.println("1: See current activities");
        System.out.println("2: See developers");
        System.out.println("3: Buy/Sell computers");

        System.out.println("0: End day");
    }

    private static void menuActivities(){
        menuActivitiesPrint();
        int choice;
        while( (choice = choose()) != 0 ){
            System.out.println("Type the ID of the activity");
            int actID = scanner.nextInt();
            scanner.nextLine();
            int devID;
            switch (choice){
                case 1:
                    System.out.println("Type the ID of the developer");
                    devID = scanner.nextInt();
                    scanner.nextLine();
                    project.addDevOnActivity(actID, devID);
                    break;

                case 2:
                    printActivityDevs(actID);
                    System.out.println("Type the ID of the developer");
                    devID = scanner.nextInt();
                    scanner.nextLine();
                    project.rmDevOnActivity(actID, devID);
                    break;

                case 3:
                    printComputerUsage();
                    System.out.println("Type the set quantity of computers you want for this activity");
                    int computerQnt = scanner.nextInt();
                    scanner.nextLine();
                    project.setComputer(actID, computerQnt);
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }

            menuActivitiesPrint();
        }
    }
    private static void menuActivitiesPrint(){
        printDevs();
        System.out.println();
        printCurrentActivities();
        System.out.println("-- Options --");
        System.out.println("1: Allocate Developer");
        System.out.println("2: Deallocate Developer");
        System.out.println("3: Set computers");
        System.out.println("0: Exit");
    }

    private static void printActivityDevs(int actID){
        System.out.println("Developers of the activity " + actID);
        List<Dev> devList = project.getActivityDevs(actID);
        int i = 0;

        for (Dev d : devList) {
        	System.out.print("| ID " + i);
            print(d);
            ++i;
        }
    }

    private static void print(Dev d){
        String devString = " | ";
        devString+="Name: "+d.getName()+" | ";
        devString+="Role: "+d.getRole()+" | ";
        devString+="Productivity "+String.format("%.2f",d.getProductivity())+" | ";
        devString+="Salary: "+String.format("%.2f",d.getSalary())+" | ";
        devString+="Event: "+ d.getEvent()+" | ";
        if(d.getActivity() != null)
            devString+="Activity: "+ d.getActivity().getName()+" | ";
        else
            devString+="Activity: -------------- | ";
        System.out.println(devString);

    }

    private static void menuDevelopers(){
        menuDevelopersPrint();
        int choice = choose();

        while(choice != 0){
            int devID;
            switch (choice){
                case 1:
                    // Print daily devs
                    System.out.println("Today's dev shortlist:");
                    System.out.println();
                    List<Dev> dailyDevs = project.getDailyDevs();
                    for(int i = 0; i < dailyDevs.size(); ++i){
                        Dev d = dailyDevs.get(i);
                        if( ! d.getName().equals("")) {
                            System.out.print("| ID " + i);
                            print(d);
                        }
                    }

                    System.out.println("Type the ID of the developer to hire");
                    devID = scanner.nextInt();
                    scanner.nextLine();
                    project.addDev(devID);
                    break;

                case 2:
                    System.out.println("Type the ID of the developer to dismiss");
                    devID = scanner.nextInt();
                    scanner.nextLine();
                    boolean succ = project.rmDev(devID);
                    if(succ){
                        System.out.println("Dev " + devID + " removed");
                    } else {
                        System.out.println("Dev " + devID + " not found");
                    }
                    break;

                default:
                    System.out.println("Invalid action");
                    break;
            }
            menuDevelopersPrint();
            choice = choose();
        }
    }

    private static void printDevs(){
        List<Dev> devs = project.getDevs();
        int i = 0;
        for(Dev d : devs){
            System.out.print("| ID " + i);
            print(d);
            ++i;
        }
    }

    private static void menuDevelopersPrint(){
        printDevs();
        System.out.println("1: Hire");
        System.out.println("2: Dismiss");
        System.out.println("0: Exit");
    }

    private static void printComputerUsage(){
        int idleNo = project.getIdleComputers().size();
        int computerNo = project.getComputers().size();
        System.out.println("Project computers usage " + (computerNo-idleNo) + "/" + computerNo);
    }

    private static void menuComputers(){
        int choice;
        menuComputersPrint();
        choice = choose();
        while(choice != 0){
            int quantity;
            switch (choice){
                case 1:
                    System.out.println("Type the quantity to buy");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    project.addComputer(quantity);                  
                    break;

                case 2:
                    System.out.println("Type the quantity to sell");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    project.rmComputer(quantity);
                    break;

                default:
                    System.out.println("Invalid action");
                    break;
            }

            menuComputersPrint();
            choice = choose();
        }
    }

    private static void menuComputersPrint(){
        printComputerUsage();
        double money;
        money = project.getMoney();
        System.out.printf("Project money: BCZ$ = %.2f\n", money);
        System.out.printf("Price per computer: %.2f\n", Computer.getPrice());
        System.out.printf("Sell value: %.2f\n", Computer.getPricePenalized());
        System.out.println("1: Buy");
        System.out.println("2: Sell");
        System.out.println("0: Exit");
    }
}
