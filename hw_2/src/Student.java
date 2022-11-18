public class Student {
    private String name;
    private String surname;
    private String presence;
    private int mark;

    public Student(String name, String surname, String presence) {
        this.name = name;
        this.surname = surname;
        this.presence = presence;
        this.mark = -1;
    }

    @Override
    public String toString() {
        return "Фамилия: " + name + " Имя: " + surname + " Присутствие: " + presence;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getPresence() {
        return presence;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String yourMark() {
        return surname + " " + name + " " + "получил/a " + mark;
    }
}
