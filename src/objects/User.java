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
        project.openFile(args[0]);

        // Print activities so user can make stages
        printActivities();

        String stageName;

        stageName = readStageName();
        while(stageName.equals("end")) {

            int stageID = project.newStage(stageName);
            int actID;

            actID = readAddActivityToStage();
            while (actID > 0) {
                project.addActivity(stageID, actID);

                actID = readAddActivityToStage();

            }

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

    private static int readAddActivityToStage(){
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
        printIdleDevs();
        printActivities();
        System.out.println("1: Allocate Developer");
        System.out.println("2: Deallocate Developer");
        System.out.println("3: Set computers");
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
                    int idleNo = project.getIdleComputers().size();
                    int computerNo = project.getComputers().size();
                    System.out.println("Project computers usage " + idleNo + "/" + computerNo);
                    System.out.println("Type the set quantity you want for this activity");
                    devID = scanner.nextInt();
                    project.addDevOnActivity(actID, devID);
                    break;
            }

            printActivities();
            System.out.println("1: Allocate Developer");
            System.out.println("2: Deallocate Developer");
            System.out.println("3: Set computers");
            choice = choose();
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
        printDevelopers();
        while(){

        }
    }

    private static void menuComputers(){
        printComputers();

        while(){

        }
    }
}
