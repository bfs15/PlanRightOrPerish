package objects;
import java.util.List;
import java.util.Scanner;

public class User {
    private static Project project = new Project();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Check how many arguments were passed in
     if(args.length == 0)
        {
            System.out.println("Error: wrong args\nUsage: java program projectFilename");
            System.exit(0);
        }

        try {
            project.openFile(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print activities so user can make stages
        printActivities();

        String stageName;

        while( !(stageName = readStageName()).isEmpty() && !stageName.equals("end")) {

            int stageID = project.newStage(stageName);
            int actID;

            actID = readAddActivityToStage(stageName);
            while (actID > -1) {
                boolean succ = project.addActivity(stageID, actID);
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
            choice = choose();

            while(choice != 0) {
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
                choice = choose();
            }

            gameRunning = project.endDay();
        }
        System.out.println("You perished.");
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

    private static void printActivity(Activity a){
        System.out.println(a.getName());
        System.out.println(a.getCost());
        System.out.println(a.getMaxComputerNo());
        System.out.println(a.getType());
        System.out.println();
    }


    private static void printActivities(){
        List<Activity> acts = project.getActivities();
        System.out.println("Activities:\n");

        for(int i = 0; i < acts.size(); i++){
            System.out.println("ID: " + i);
            Activity a = acts.get(i);
            printActivity(a);
        }
    }

    private static void printCurrentActivities(){
        List<Activity> acts = project.getCurrentActivities();
        System.out.println("Activities:\n");

        for(int i = 0; i < acts.size(); i++){
            System.out.println("ID: " + i);
            Activity a = acts.get(i);
            printActivity(a);
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

        String ca$h = "";
        int len = String.valueOf(project.getMoney()).length();
        for(int i=0;i<13+len;++i) ca$h+="-";
        System.out.println(" "+ca$h);

        String miniSpace = "";
        int spaces =  4 - (currentStageName.length()/2);
        for (int i = 0;i<spaces;++i) miniSpace+=" ";

        if(currentStageName.length() >= 5) System.out.print("|  "+project.getCurrentStage().getName()+" "+miniStagePadding+"|");
        else System.out.print("|"+miniSpace+project.getCurrentStage().getName()+miniSpace+" "+"|");

        System.out.println(" |  BCZ$ = "+project.getMoney()+"  |");
        System.out.print(">>>"+stagePadding+"-----"+stagePadding+"<<<");
        System.out.println(" "+ca$h);
        int workingDevs = project.getDevs().size()-project.getIdleDevs().size();
        int workingComputers =project.getComputers().size()-project.getIdleComputers().size();
        int boxLength = ("| Developers:    Working "+workingDevs+"/"+project.getDevs().size()+" |").length();
        String boxCloseBox = "";
        for(int i = 0; i < boxLength; ++i) boxCloseBox+="-";
        System.out.println(boxCloseBox);
        System.out.println("| Developers:    Working "+workingDevs+"/"+project.getDevs().size()+" |");
        System.out.println("| Computers:     Working "+workingComputers+"/"+project.getComputers().size()+" |");

        System.out.println(boxCloseBox);
        System.out.println("1: See current activities");
        System.out.println("2: See developers");
        System.out.println("3: Buy/Sell computers");

        System.out.println("0: End day");
    }

    private static void menuActivities(){
        menuActivitiesPrint();
        int choice = choose();
        while(choice != 0){
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
            choice = choose();
        }
    }
    private static void menuActivitiesPrint(){
        printIdleDevs();
        System.out.println();
        printCurrentActivities();
        System.out.println("-- Options --");
        System.out.println("1: Allocate Developer");
        System.out.println("2: Deallocate Developer");
        System.out.println("3: Set computers");
        System.out.println("0: Exit");
    }


    private static void printIdleDevs(){
        System.out.println("Idle developers:");
        List<Dev> devList = project.getIdleDevs();
        int i =0;
        for (Dev d : devList) {
        	System.out.println("ID :" + i);
            print(d);
            ++i;
        }
    }

    private static void printActivityDevs(int actID){
        System.out.println("Developers of the activity " + actID);
        List<Dev> devList = project.getActivityDevs(actID);
        int i = 0;
        for (Dev d : devList) {
        	System.out.println("ID :" + i);
            print(d);
            ++i;
        }
    }

    private static void print(Dev d){

        System.out.println("Name: " + d.getName());
        System.out.println("Role: " + d.getRole());
        System.out.println("Productivity: " + d.getProductivity());
        System.out.println("Salary: " + d.getSalary());
        System.out.println("Event: " + d.getEvent());
        System.out.println();
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
                            System.out.println("ID: " + i);
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

    private static void menuDevelopersPrint(){
        // Print devs
        List<Dev> devs = project.getDevs();
        for(Dev d : devs){
            print(d);
        }
        System.out.println("1: Hire");
        System.out.println("2: Dismiss");
        System.out.println("0: Exit");
    }

    private static void printComputerUsage(){
        int idleNo = project.getIdleComputers().size();
        int computerNo = project.getComputers().size();
        System.out.println("Project computers usage " + idleNo + "/" + computerNo);
    }

    private static void menuComputers(){
        int choice;
        double money;

        menuComputersPrint();
        choice = choose();
        while(choice != 0){
            int quantity;
            switch (choice){
                case 1:
                    System.out.println("Type the quantity to buy");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    money = project.addComputer(quantity);
                    System.out.println("Project money: " + money);
                    break;

                case 2:
                    System.out.println("Type the quantity to sell");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    money = project.rmComputer(quantity);
                    System.out.println("Project money: " + money);
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
        System.out.println("price per computer: " + Computer.getPrice());
        System.out.println("sell value: " + Computer.getPricePenalized());
        System.out.println("1: Buy");
        System.out.println("2: Sell");
        System.out.println("0: Exit");
    }
}
