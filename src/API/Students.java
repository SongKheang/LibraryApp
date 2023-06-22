package API;

public class Students {
    private String studentID;
    private String studentName;
    private String studentPhone;
    private String studentPassword;
    
    public Students(String studentID, String studentName, String studentPhone) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
    }
    public Students(String studentID, String studentName, String studentPhone, String studentPassword) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentPassword = studentPassword;
    }
    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentPhone() {
        return studentPhone;
    }
    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
    public String getStudentPassword() {
        return studentPassword;
    }
    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

}
