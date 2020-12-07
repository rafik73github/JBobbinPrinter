package pl.jbobbinprinter;

import java.util.Map;

public class Yarn {

    private int key;
    private String yarnName;
    private String yarnType;
    private String yarnWeight;
    private int archived;

    public Yarn(String yarnName, String yarnType, String yarnWeight){
        this.yarnName = yarnName;
        this.yarnType = yarnType;
        this.yarnWeight = yarnWeight;
    }

    public Yarn(int key, String yarnName, String yarnType, String yarnWeight){
        this.key = key;
        this.yarnName = yarnName;
        this.yarnType = yarnType;
        this.yarnWeight = yarnWeight;
    }

    public Yarn(int key, String yarnName, String yarnType, String yarnWeight, int archived){
        this.key = key;
        this.yarnName = yarnName;
        this.yarnType = yarnType;
        this.yarnWeight = yarnWeight;
        this.archived = archived;
    }

    public int getYarnKey() {
        return key;
    }

    public void setYarnKey(int key) {
        this.key = key;
    }

    public String getYarnName() {
        return yarnName;
    }

    public void setYarnName(String yarnName) {
        this.yarnName = yarnName;
    }

    public String getYarnType() {
        return yarnType;
    }

    public void setYarnType(String yarnType) {
        this.yarnType = yarnType;
    }

    public String getYarnWeight() {
        return yarnWeight;
    }

    public void setYarnWeight(String yarnWeight) {
        this.yarnWeight = yarnWeight;
    }

    public int getYarnArchived() {
        return archived;
    }

    public void setYarnArchived(int archived) {
        this.archived = archived;
    }

    public void clearList(Map<Integer, Yarn> listToAdd){
        listToAdd.clear();
    }

}
