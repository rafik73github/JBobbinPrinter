package pl.jbobbinprinter;

public class YarnWeight {

    private int weightId;
    private String weight;
    private int weightArchived;

    public YarnWeight(String weight){
        this.weight = weight;

    }

    public YarnWeight(int weightId, String weight){
        this.weightId = weightId;
        this.weight = weight;

    }

    public YarnWeight(int weightId, String weight, int weightArchived){
        this.weightId = weightId;
        this.weight= weight;
        this.weightArchived = weightArchived;

    }

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getWeightArchived() {
        return weightArchived;
    }

    public void setWeightArchived(int weightArchived) {
        this.weightArchived = weightArchived;
    }
}
