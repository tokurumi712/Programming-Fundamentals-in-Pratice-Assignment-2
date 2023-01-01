package models;

import utils.Utilities;

public class EducationApp extends App{

    private int level = 0;

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        setDeveloper(developer);
        setAppName(appName);
        setAppSize(appSize);
        setAppVersion(appVersion);
        setAppCost(appCost);
        setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (Utilities.validRange(level, 1, 10)) {
            this.level = level;
        }
    }

    /*  <method isRecommendedApp>
    Override the method to determine whether the app is recommended.
     */
    public boolean isRecommendedApp() {

        if (getAppCost() >= 0.99 && Utilities.greaterThanOrEqualTo(calculateRating(), 3.5) && Utilities.greaterThanOrEqualTo(level, 3)) {
            return true;
        }

        return false;
    }

    /*  <method appSummary>
    Override the parent method and add level description.
     */
    public String appSummary() {
        return super.appSummary() + ",level" + getLevel();
    }

    /*  <method toString>
    Overrides parent methods and contains information for the apps and returns it.
     */
    public String toString() {
        return super.toString() + ",level:" + getLevel();
    }
}
