package objects;
import java.util.List;
import java.util.Scanner;

public class User {
    private static Project project = new Project();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Check how many arguments were passed in
        if(args.length == 0)
        {
            System.out.println("Wrong args\nUsage: java program projectFilename");
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

        stageName = readStageName();
        while(!stageName.equals("end")) {

            int stageID = project.newStage(stageName);
            int actID;

            actID = readAddActivityToStage(stageName);
            while (actID > -1) {
                project.addActivity(stageID, actID);

                actID = readAddActivityToStage(stageName);
            }

            System.out.println("Type how many work days this stage will have");
            project.setSchedule(stageID, scanner.nextInt());

            stageName = readStageName();
        }

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
    }

    private static int choose(){
        System.out.println("Type the ID of the action you want to take");
        return scanner.nextInt();
    }

    private static String readStageName(){
        System.out.println("Type the name of a new stage (type \"end\" to complete)");
        return scanner.nextLine();
    }

    private static int readAddActivityToStage(String stageName){
        System.out.println("Type ID of the activity to add to stage " + stageName + " (type \"-1\" to complete)");
        return scanner.nextInt();
    }


    private static void printActivities(){
        List<Activity> acts = project.getActivities();
        System.out.println("Activities\n");

        for(int i = 0; i < acts.size(); i++){
            System.out.println("ID: " + i);
            System.out.println(acts.get(i));
        }
    }

    private static void printDay(){
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
            int devID;
            switch (choice){
                case 1:
                    System.out.println("Type the ID of the developer");
                    devID = scanner.nextInt();
                    project.addDevOnActivity(actID, devID);
                    break;

                case 2:
                    printActivityDevs(actID);
                    System.out.println("Type the ID of the developer");
                    devID = scanner.nextInt();
                    project.addDevOnActivity(actID, devID);
                    break;

                case 3:
                    printComputerUsage();
                    System.out.println("Type the set quantity you want for this activity");
                    devID = scanner.nextInt();
                    project.addDevOnActivity(actID, devID);
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
        printActivities();
        System.out.println("1: Allocate Developer");
        System.out.println("2: Deallocate Developer");
        System.out.println("3: Set computers");
        System.out.println("0: Exit");
    }


    private static void printIdleDevs(){
        System.out.println("Idle developers");
        List<Dev> devList = project.getIdleDevs();
        for (Dev d : devList) {
            print(d);
        }
    }

    private static void printActivityDevs(int actID){
        System.out.println("Developers of the activity " + actID);
        List<Dev> devList = project.getActivityDevs(actID);
        for (Dev d : devList) {
            print(d);
        }
    }

    private static void print(Dev d){
        System.out.println("Deallocate from ");
        System.out.println("Name: " + d.getName());
        System.out.println("Role: " + d.getRole());
        System.out.println("Productivity: " + d.getProductivity());
        System.out.println("Salary: " + d.getSalary());
        System.out.println("Status: " + d.getStatus());
    }

    private static void menuDevelopers(){
        menuDevelopersPrint();
        int choice = choose();

        while(choice != 0){
            int devID;
            switch (choice){
                case 1:
                    // Print daily devs
                    List<Dev> dailyDevs = project.getDailyDevs();
                    for(Dev d : dailyDevs){
                        print(d);
                    }

                    System.out.println("Type the ID of the developer to hire");
                    devID = scanner.nextInt();
                    project.addDev(devID);
                    break;

                case 2:
                    System.out.println("Type the ID of the developer to dismiss");
                    devID = scanner.nextInt();
                    project.rmDev(devID);
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
                    money = project.addComputer(quantity);
                    System.out.println("Project money: " + money);
                    break;

                case 2:
                    System.out.println("Type the quantity to sell");
                    quantity = scanner.nextInt();
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
