package entity;

public class Item {
    private int id;
    private String name;
    private byte[] picture;
    private int parentId;
    private float quantity;
    private String unit;
    public Item(int id, String name, byte[] picture, int parentId, float quantity, String unit){
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.parentId = parentId;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Item(int id, String name,float quantity, String unit){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Item(id="+id+",name='"+name+"',picture=..."+",parentId="+parentId+",quantity="+quantity+",unit='"+unit+"')\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
