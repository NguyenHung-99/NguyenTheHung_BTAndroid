package test.room.api.myapplication;

public class User {
    private int id;
    private String ten;
    private int tuoi;


    public User(int id, String ten, int tuoi) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                '}';
    }
}
