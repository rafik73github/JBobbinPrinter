package pl.jbobbinprinter;

public class YarnTypes {

    private int typeId;
    private String type;
    private int typeArchived;

    public YarnTypes(String type){
        this.type = type;

    }

    public YarnTypes(int typeId, String type){
        this.typeId = typeId;
        this.type = type;

    }

    public YarnTypes(int typeId, String type, int typeArchived){
        this.typeId = typeId;
        this.type = type;
        this.typeArchived = typeArchived;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeArchived() {
        return typeArchived;
    }

    public void setTypeArchived(int typeArchived) {
        this.typeArchived = typeArchived;
    }

    @Override
    public String toString(){
        return type;
    }
}
