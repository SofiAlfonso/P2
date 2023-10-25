public class Students
{
    private String id;
    private String first_name;
    private String last_name;
    private String gender;
    private String career_aspiration;
    private int math_score;

    // Constructor

    public Students( String id, String first_name,String last_name, String gender, String career_aspiration, int math_score)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.career_aspiration= career_aspiration;
        this.gender=gender;
        this.math_score=math_score;
    }

    // Getters

    public String getId()
    {
        return id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public  String  getGender()
    {
        return gender;
    }

    public String getCareer_aspiration()
    {
        return career_aspiration;
    }

    public int getMath_score()
    {
        return math_score;
    }

    // Setters

    public void setId(String id)
    {
        this.id = id;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public void setGender()
    {
        this.gender=gender;

    }

    public void setCareer_aspiration(String career_aspiration)
    {
        this.career_aspiration=career_aspiration;
    }

    public void setMath_score(int math_score)
    {
        this.math_score=math_score;
    }
    public String toString() {
        return "Estudiante{ID='" + this.id + "', Nombre='" + this.first_name + "', Apellido=" + this.last_name + ", Género='" + this.gender + ", Carrera='" + this.career_aspiration + ", Puntaje='" + this.math_score +"'}";
    }


}
