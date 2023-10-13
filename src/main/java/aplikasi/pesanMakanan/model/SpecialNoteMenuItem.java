package aplikasi.pesanMakanan.model;

public class SpecialNoteMenuItem extends MenuItem {
    private String specialNote;

    public SpecialNoteMenuItem(String menu, int qty, int price, String specialNote) {
        super(menu, qty, price);
        this.specialNote = specialNote;
    }

    @Override
    public String getSpecialNote() {
        return specialNote;
    }

    @Override
    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }
}
