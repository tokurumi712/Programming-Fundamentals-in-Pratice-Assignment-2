package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI {

    private ArrayList<App> apps = new ArrayList<>();

    /*  <method addApp>
    Adds an object to the ArrayList app and returns a Boolean judgment about whether the object was successfully added.
     */
    public boolean addApp(App app) {
        return apps.add(app);
    }

    /*  <method listAllApps>
    If the ArrayList is empty, "No apps" is returned, otherwise all applications in the array list are listed.
     */
    public String listAllApps() {

        if (apps.isEmpty()) {
            return "No apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            sb.append(i).append(": ").append(apps.get(i)).append('\n');
        }

        return sb.toString();
    }

    /*  <method ListSummaryOfAllApps>
    If the ArrayList is empty, "No apps" is printed, otherwise all the app summaries are listed.
     */
    public String listSummaryOfAllApps() {
        
        if (apps.isEmpty()) {
            return "No apps";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < apps.size(); i++) {
            sb.append(i).append(": ").append(apps.get(i).appSummary()).append('\n');
        }
        
        return sb.toString();
    }

    /*  <method ListAllGameApps>
    If the arrayList is empty or there are no game apps, print "No Game apps", otherwise all game apps are listed.
     */
    public String listAllGameApps() {
        
        if (apps.isEmpty()) {
            return "No Game apps";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof GameApp) {
                sb.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }
        
        if (sb.isEmpty()) {
            sb.append("No Game apps");
        }
        
        return sb.toString();
    }

    /*  <method numberOfGameApps>
    List the number of game apps in the ArrayList memory.
     */
    public int numberOfGameApps() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof GameApp) {
                number++;
            }
        }

        return number;
    }

    /*  <method ListAllEducationApps>
    If the ArrayList is empty or there are no educational apps, print "No Education apps", otherwise all educational apps are listed.
     */
    public String listAllEducationApps() {

        if (apps.isEmpty()) {
            return "No Education apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof EducationApp) {
                sb.append(i).append(": ").append(apps.get(i)).append("\n");
            }
        }

        if (sb.isEmpty()) {
            sb.append("No education apps");
        }

        return sb.toString();
    }

    /*  <method numberOfEducationApps>
    Lists the number of educational apps in ArrayList memory.
     */
    public int numberOfEducationApps() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof EducationApp) {
                number++;
            }
        }

        return number;
    }

    /*  <method listAllProductivityApps>
    If the ArrayList is empty or there are no productivity apps, print "No Productivity apps," otherwise all productivity apps are listed.
     */
    public String listAllProductivityApps() {

        if (apps.isEmpty()) {
            return "No Productivity apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof ProductivityApp) {
                sb.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (sb.isEmpty()) {
            sb.append("No Productivity apps");
        }

        return sb.toString();
    }

    /*  <method numberOfProductivityApp>
    List the number of productivity apps in the ArrayList memory.
     */
    public int numberOfProductivityApp() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                number++;
            }
        }

        return number;
    }

    /*  <method listAllAppsByName>
    These apps are listed based on the incoming app name.
     */
    public String listAllAppsByName(String name) {

        if (apps.isEmpty() || !isValidAppName(name)) {
            return "No apps for name " + name + " exists";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getAppName().equalsIgnoreCase(name)) {
                builder.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (builder.isEmpty()) {
            builder.append("No apps for name ").append(name).append(" exists");
        }

        return builder.toString();
    }

    /*  <method listAllAppsAboveOrEqualAGivenStarRating>
    If no objects in the ArrayList meet the requirements, return "No apps have a rating of 'rating' or above", otherwise list apps above or equal to the given rating.
     */
    public String listAllAppsAboveOrEqualAGivenStarRating(int rating) {

        if (apps.isEmpty() || rating < 1 || rating > 5) {
            return "No apps have a rating of " + rating + " or above";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).calculateRating() >= rating) {
                builder.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (builder.isEmpty()) {
            return "No apps have a rating of " + rating + " or above";
        }

        return builder.toString();
    }

    /*  <method listAllRecommendedApps>
    If there are no objects or no apps in the ArrayList, return "No recommended apps", otherwise all apps that meet the recommended criteria are listed.
     */
    public String listAllRecommendedApps() {

        if (apps.isEmpty()) {
            return "No recommended apps";
        }

        String str = "";

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).isRecommendedApp()) {
                str += apps.get(i).getAppName();
            } else str = "No recommended apps";
        }

        return str;
    }

    /*  <method listAllAppsByChosenDeveloper>
    If there are no objects or no eligible apps in the ArrayList, return "No apps for developer:'developer'", otherwise the apps are listed based on the author name passed in.
     */
    public String listAllAppsByChosenDeveloper(Developer developer) {

        if (apps.isEmpty()) {
            return "No apps for developer: " + developer;
        }

        StringBuilder a = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper().equals(developer)) {
                a.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (a.isEmpty()) {
            a.append("No apps for developer: ").append(developer);
        }

        return a.toString();
    }

    /*  <method updateEducationApp>
    Updates the education app in the ArrayList based on the incoming data, and returns a Boolean judgment of whether the update was successful.
     */
    public boolean updateEducationApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof EducationApp)) {
            ((EducationApp) foundApp).setDeveloper(developer);
            ((EducationApp) foundApp).setAppName(appName);
            ((EducationApp) foundApp).setAppCost(appCost);
            ((EducationApp) foundApp).setAppVersion(appVersion);
            ((EducationApp) foundApp).setAppSize(appSize);
            ((EducationApp) foundApp).setLevel(level);
            return true;
        }

        return false;
    }

    /*  <method updateGameApp>
    Updates the game apps in the ArrayList based on the incoming data, and returns a Boolean judgment of whether the update was successful.
     */
    public boolean updateGameApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof GameApp)) {
            ((GameApp) foundApp).setDeveloper(developer);
            ((GameApp) foundApp).setAppName(appName);
            ((GameApp) foundApp).setAppSize(appSize);
            ((GameApp) foundApp).setAppCost(appCost);
            ((GameApp) foundApp).setAppVersion(appVersion);
            ((GameApp) foundApp).setMultiplayer(isMultiplayer);
            return true;
        }

        return false;
    }

    /*  <method updateProductivityApp>
    Updates the productivity apps in the ArrayList based on incoming data and returns a Boolean judgment of whether the update was successful.
     */
    public boolean updateProductivityApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof ProductivityApp)) {
            ((ProductivityApp) foundApp).setAppName(appName);
            ((ProductivityApp) foundApp).setAppSize(appSize);
            ((ProductivityApp) foundApp).setAppVersion(appVersion);
            ((ProductivityApp) foundApp).setAppCost(appCost);
            ((ProductivityApp) foundApp).setDeveloper(developer);
            return true;
        }

        return false;
    }

    /*  <method numberOfAppsByChosenDeveloper>
    List the number of apps according to the developers.
     */
    public int numberOfAppsByChosenDeveloper(Developer developer) {

        if (apps.isEmpty()) {
            return 0;
        }

        int number = 0;

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper().equals(developer)) {
                number++;
            } else {
                number += 0;
            }
        }

        return number;
    }

    /*  <method deleteAppByIndex>
    Determine whether the incoming data is valid, and then delete the object in the appropriate location.
     */
    public App deleteAppByIndex(int indexToDelete) {

        if (isValidIndex(indexToDelete)) {
            return apps.remove(indexToDelete);
        }

        return null;
    }

    /*  <method randomApp>
    Returns an object stored in the ArrayList randomly.
     */
    public App randomApp() {
        if (apps.isEmpty()) {
            return null;
        } else {
            return apps.get(new Random().nextInt(apps.size()));
        }
    }

    /*  <method simulateRating>
    Gives the apps in the ArrayList a random rating.
     */
    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }

    /*  <method isValidAppName>
    Determines whether the String passed in is valid.
     */
    public boolean isValidAppName(String appName) {

        for (App app : apps) {
            if (app.getAppName().equals(appName)) {
                return true;
            }
        }

        return false;
    }

    /*  <method getAppByName>
    Gets the corresponding object in the ArrayList based on the app name passed in.
     */
    public App getAppByName(String name) {

        if (name == null) {
            return null;
        }

        for (App app : apps) {
            if (app.getAppName().equalsIgnoreCase(name)) {
                return app;
            }
        }

        return null;
    }

    /*  <method isValidIndex>
    Determine if the data is valid.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    /*  <method numberOfApps>
    List the number of objects in the ArrayList memory.
     */
    public int numberOfApps() {
        return apps.size();
    }

    /*  <method sortAppsByNameAscending>
    Sort existing apps in the ArrayList.
     */
    public void sortAppsByNameAscending() {

        for (int i = apps.size() - 1; i >= 0; i--) {

            int highestIndex = 0;

            for (int j = 0; j <= i; j++) {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }

            swapApps(apps, i, highestIndex);
        }
    }

    /*  <method findApp>
    Returns the appropriate app based on the incoming data.
     */
    public App findApp(int index) {
        if (isValidIndex(index)) {
            return apps.get(index);
        }
        return null;
    }

    private void swapApps(ArrayList<App> apps, int i, int j) {

        App smaller = apps.get(i);
        App bigger = apps.get(j);

        apps.set(i, bigger);
        apps.set(j, smaller);
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {

        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (ArrayList<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName(){
        return "apps.xml";
    }

}
